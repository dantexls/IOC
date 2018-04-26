package com.iss.cityioc.scs.common.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.util.ClassUtils;

import com.iss.cityioc.scs.common.model.Page;

public class GenericDaoHibernateSupport<T> extends HibernateDaoSupport implements GenericDao<T> {

	@Autowired
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	/** 当前Dao所要操作的Bean的class地址. */
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericDaoHibernateSupport() {
		
		Type type = getClass().getGenericSuperclass();
		
		if (type instanceof ParameterizedType) {
			persistentClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
		} else {
			throw new RuntimeException("No specific parameterized type set.");
		}
	}

	public GenericDaoHibernateSupport(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
		/*if(null==session){
			session=sessionFactory.openSession();
		}
		return session;*/
	}

	/**
	 * 删除一个Bean对象.
	 * 
	 * @param o
	 *            实体对象
	 */
	public void delete(T o) {
		this.getSession().delete(o);

	}

	/**
	 * 删除一个集合内的所有对象
	 * 
	 * @param list
	 *            对象集合
	 */
	public void deleteAll(Collection<T> list) {

		for (T t : list) {
			this.getSession().delete(t);
		}
	}

	/**
	 * 依靠id删除一个实体对象.
	 * 
	 * @param id
	 *            实体对象的id
	 */
	public void deleteById(Serializable id) {
		T o = this.loadById(id);
		if (null == o) {
			throw new HibernateException("This Object by id is " + id + " does not exist");
		}
		this.getSession().delete(o);
	}

	/**
	 * 执行SQL语句获得Set结果,此SQL语句非HQL语句.
	 * 
	 * @param sql
	 *            基本sql语句
	 * 
	 * @return Set集合
	 */
	public ResultSet executeQuery(final String sql) throws Exception {
		try {

			return this.getSession().doReturningWork(new ReturningWork<ResultSet>() {
				public ResultSet execute(Connection connection) throws SQLException {
					PreparedStatement pstmt = connection.prepareStatement(sql);
					return pstmt.executeQuery();
				}
			});
		} catch (Exception e) {
			throw new Exception(this.getClass() + " execute " + sql + " ERROR!");
		}
	}

	/**
	 * 查询所有信息.
	 * 
	 * @return 实体Bean List
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return this.getSession().createQuery("FROM " + ClassUtils.getShortName(persistentClass)).list();
	}

	/**
	 * 根据某些属性条件查询.
	 * 
	 * @param map
	 *            存放属性name-value 相对应的条件集.
	 * @return 根据条件获得的所有数据集
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllByProperties(Map<String, Object> map) {
		String hql = "from " + ClassUtils.getShortName(persistentClass) + " where 1=1 ";
		String[] names = new String[map.size()];
		Object[] values = new Object[map.size()];
		int i = 0;
		for (String str : map.keySet()) {
			hql = hql + " and " + str + "=:" + ("key" + i);
			names[i] = "key" + i;
			values[i] = map.get(str);
			i++;
		}
		Query query = this.getSession().createQuery(hql);

		for (int j = 0; j < names.length; j++) {
			query.setParameter(names[j], values[j]);
		}
		return query.list();
	}

	/**
	 * 根据属性名值进行查询.
	 * 
	 * @param propertyName
	 *            属性名字：如id，username。。。
	 * @param propertyValue
	 *            属性值： 如11，'admin'。。。
	 * @return 根据条件获得的所有数据集
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllByProperty(final String propertyName, Object propertyValue) {

		String hql = "from " + ClassUtils.getShortName(persistentClass) + " where " + propertyName + "= :" + propertyName;
		return this.getSession().createQuery(hql).setParameter(propertyName, propertyValue).list();
	}

	/**
	 * 依靠当前实体的id查找该实体.
	 * 
	 * @param idValue
	 *            主键值
	 * @return 该id实体对象
	 */
	@SuppressWarnings("unchecked")
	public T findById(Serializable idValue) {
		return (T) this.getHibernateTemplate().get(persistentClass, idValue);
	}
	
	/**
	 * 依靠当前实体的id查找该实体.
	 * 
	 * @param idValue
	 *            主键值
	 * @return 该id实体对象
	 */
	@SuppressWarnings("unchecked")
	public T loadById(Serializable idValue) {
		return (T) this.getSession().load(persistentClass, idValue);
	}

	/**
	 * 根据属性名值进行查询该列记录数
	 * 
	 * @param propertyName
	 *            属性名字：如id，username。。。
	 * @param propertyValue
	 *            属性值： 如11，'admin'。。。
	 * @return 获得符合条件的数据的条数
	 */
	public int findColumnCount(String propertyName, Object propertyValue) {
		String hql = "select count(*) from " + ClassUtils.getShortName(persistentClass) + " where " + propertyName + "= :" + propertyName;
		return ((Number) this.getSession().createQuery(hql).setParameter(propertyName, propertyValue).uniqueResult()).intValue();

	}

	/**
	 * 新增一条信息.
	 * 
	 */
	public Serializable save(T value) {
		return this.getSession().save(value);
	}

	/**
	 * 保存一组对象集合.
	 * 
	 * @param coll
	 *            对象集合
	 * 
	 */
	public void saveAll(Collection<T> coll) {
		for (T t : coll) {
			this.getSession().save(t);
		}
	}

	/**
	 * 修改一条信息.
	 */
	public void update(T value) {
		this.getSession().update(value);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByHQL(String hql) {
		return  new ArrayList<T>(new LinkedHashSet<T>(this.getSession().createQuery(hql).list()));
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByHQLWithFilterAndParams(String hql, String filter, String fpName, Object fpVal, Object... params) {
		Session session = this.getSession();
		session.enableFilter(filter).setParameter(fpName, fpVal);
		
		Query query = session.createQuery(hql);
		setParams(query, params);
		
		return  new ArrayList<T>(new LinkedHashSet<T>(query.list()));
	}
	 
	@SuppressWarnings("unchecked")
	public List<T> findByHQLAndParams(String hql,Object... params) {
 		if (null == params || 1 > params.length) {
			return null;
		}
		Query query = this.getSession().createQuery(hql);
		setParams(query, params);
		return new ArrayList<T>(new LinkedHashSet<T>(query.list()));
	}
	/**
	 * 根据条件查询某个Bean.
	 * 
	 * @param strings
	 *            可以传个数为n的任意数量参数。名值之间用英文的::=分开 例
	 *            selectBSql("id::=11","name::='myName'");
	 * @return 返回find(from 表 where id=11 and name='myName')的结果
	 */
	@SuppressWarnings("unchecked")
	public List<T> selectBySqlCondition(String... strings) {
		String sql = " from " + ClassUtils.getShortName(persistentClass);
		sql += " where 1 = 1";
		for (String str : strings) {
			sql += " and " + str.split("::=")[0] + "=" + str.split("::=")[1];
		}
		return this.getSession().createQuery(sql).list();
	}

	/**
	 * 根据条件查询某个Bean.
	 * 
	 * @param strings
	 *            可以传个数为n的任意数量参数。名值之间用英文的::=分开 例
	 *            selectBSql("id::=11","name::='myName'");
	 * @return 返回find(from 表 where id=11 or name='myName')的结果
	 */
	@SuppressWarnings("unchecked")
	public List<T> selectBySqlConditionOr(String... strings) {
		String sql = " from " + ClassUtils.getShortName(persistentClass);
		sql += " where";
		String a = "";
		for (String str : strings) {
			a += " or " + str.split("::=")[0] + "=" + str.split("::=")[1];
		}
		a = a.substring(3);
		sql += a;
		return this.getSession().createQuery(sql).list();
	}

	/**
	 * 分页查询.
	 * 
	 * @param map
	 *            条件map
	 * @param page
	 *            page对象
	 * @param orderby
	 *            排序
	 * @param like
	 *            是否模糊搜索
	 * @return Page对象
	 */
	public Page<T> pageQuery(Map<String, Object> map, Page<T> page, LinkedHashMap<String, String> orderby, boolean like) {

		// 记录数Hql
		StringBuffer countHql = new StringBuffer();

		countHql.append("SELECT COUNT(*) FROM " + persistentClass.getName());
		// 添加where条件，并返回参数
		final Object[] countArgs = appendHQLCondition(map, like, countHql);

		StringBuffer resultHql = new StringBuffer();
		resultHql.append("FROM " + persistentClass.getName());
		// 添加where条件，并返回参数
		final Object[] resultArgs = appendHQLCondition(map, like, resultHql);

		if (orderby != null) {
			// 添加orderby条件
			resultHql.append(buildOrderBy(orderby));
		}

		// 执行查询
		return excutePageQuery(false, null, null, null, page.getCurrentPage(), page.getPageSize(), countArgs, resultArgs, countHql.toString(), resultHql.toString());
	}
	
	/**
	 * 分页查询.
	 * 
	 * @param page
	 *            page对象
	 * @param countHql
	 *            查询总条数
	 * @param resultHql
	 *            查询结果集
	 * @param params
	 *            参数列表
	 * @return Page对象
	 */
	public Page<T> pageQueryByHql(Page<T> page,final String countHql,final String resultHql,Object... params) {
		return excutePageQuery(false, null, null, null, page.getCurrentPage(), page.getPageSize(), params, params, countHql, resultHql);
	}
	
	/**
	 * 分页查询.
	 * 
	 * @param page
	 *            page对象
	 * @param countHql
	 *            查询总条数
	 * @param resultHql
	 *            查询结果集
	 * @param params
	 *            总条数参数列表
	 * @param params
	 *            结果集参数列表
	 * @return Page对象
	 */
	public Page<T> pageQueryByHql(Page<T> page,final String countHql,final String resultHql,Object[] countParams,Object[] resultParams) {
		return excutePageQuery(false, null, null, null, page.getCurrentPage(), page.getPageSize(), countParams, resultParams, countHql, resultHql);
	}
	
	public Page<T> pageQueryByHqlWithFilter(final String filter, final String fpName, Object fpVal, Page<T> page,final String countHql,final String resultHql,Object[] countParams,Object[] resultParams) {
		return excutePageQuery(true, filter, fpName, fpVal, page.getCurrentPage(), page.getPageSize(), countParams, resultParams, countHql, resultHql);
	}

	/**
	 * 执行分页HQL查询
	 * 
	 * @param pageNo
	 *            起始页号
	 * @param pageSize
	 *            一页查多少条
	 * @param countArgs
	 *            查询总记录数时HQL的参数
	 * @param resultArgs
	 *            查询记录时HQL的参数
	 * @param finalCountHql
	 *            查询总记录数的HQL
	 * @param finalResultHql
	 *            查询记录的HQL
	 * @return Page对象
	 */
	@SuppressWarnings("unchecked")
	private Page<T> excutePageQuery(final boolean filterEnabled, final String filter, final String fpName , Object fpVal, final int pageNo, final int pageSize, final Object[] countArgs, final Object[] resultArgs,
			final String finalCountHql, final String finalResultHql) {
		
		Session session = this.getSession();
		
		if(filterEnabled) {
			session.enableFilter(filter).setParameter(fpName, fpVal);
		}

		Page<T> page = new Page<T>();

		// 处理查询的列表
		Query query = session.createQuery(finalResultHql);
		// 设置参数
		setParams(query, resultArgs);

		// 设置查询页面显示的数据
		if (pageNo != -1 && pageSize != -1) {
			query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
		}
		page.setResult(new ArrayList<T>(new LinkedHashSet<T>(query.list())));

		/*if(filterEnabled) {
			session.disableFilter(filter);
		}*/
		
		// 处理查询的总的记录数
		query = this.getSession().createQuery(finalCountHql);
		// 设置参数
		setParams(query, countArgs);
		page.setCurrentPage(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(((Number) query.uniqueResult()).intValue());

		return page;
	}

	/**
	 * 设置参数
	 * 
	 * @param query
	 * @param params
	 */
	protected void setParams(Query query, Object[] params) {
		if (query == null) {
			return;
		}
		Object param = null;
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				
				param = params[i];
					query.setParameter(i, param);
			}
		}
	}

	/**
	 * 处理排序
	 * 
	 * @param orderby
	 * @return
	 */
	private String buildOrderBy(LinkedHashMap<String, String> orderby) {
		StringBuffer orderBybuf = new StringBuffer("");

		// 处理排序
		if (orderby != null && !orderby.isEmpty()) {
			orderBybuf.append(" order by ");
			for (String key : orderby.keySet()) {

				orderBybuf.append(key + "  " + orderby.get(key) + ",");
			}
			orderBybuf.deleteCharAt(orderBybuf.length() - 1);
		}
		return orderBybuf.toString();
	}

	/**
	 * 增加加查询条件
	 * 
	 * @param map
	 *            查询条件map
	 * @param whereHql
	 * @return 返回查询条件参数
	 */
	private Object[] appendHQLCondition(Map<String, Object> map, boolean like, StringBuffer whereHql) {
		Object[] args = null;
		if (null != map && 0 < map.size()) {
			whereHql.append(" where ");
			args = new Object[map.size()];
			Iterator<?> it = map.keySet().iterator();
			int i = 0;
			while (it.hasNext()) {
				String columnKey = (String) it.next();
				Object value = map.get(columnKey);
				if (like) {
					whereHql.append(columnKey + " like ? ");
					args[i] = "%" + value + "%";
				} else {
					whereHql.append(columnKey + " = ? ");
					args[i] = value;
				}
				if (it.hasNext()) {
					whereHql.append(" and ");
				}
				i++;
			}
		}
		return args;
	}

	public void saveOrUpdate(T o) {

		this.getSession().saveOrUpdate(o);
	}

	/**
	 * 分页查询.
	 * 
	 * @param map
	 *            条件map
	 * @param page
	 *            page对象
	 * @param orderby
	 *            排序
	 * @return Page对象
	 */
	public Page<T> pageQuery(Map<String, Object> map, Page<T> page, LinkedHashMap<String, String> orderby) {
		return this.pageQuery(map, page, orderby, false);
	}

	/**
	 * 分页查询.
	 * 
	 * @param map
	 *            条件map
	 * @param page
	 *            page对象
	 * @param like
	 *            是否模糊搜索
	 * @return Page对象
	 */
	public Page<T> pageQuery(Map<String, Object> map, Page<T> page, boolean like) {
		return this.pageQuery(map, page, null, like);
	}

	/**
	 * 分页查询.
	 * 
	 * @param map
	 *            条件map
	 * @param page
	 *            page对象
	 * @return Page对象
	 */
	public Page<T> pageQuery(Map<String, Object> map, Page<T> page) {
		return this.pageQuery(map, page, null, false);
	}

	/**
	 * 分页查询.
	 * 
	 * @param page
	 *            page对象
	 * @param orderby
	 *            排序
	 * @return Page对象
	 */
	public Page<T> pageQuery(Page<T> page, LinkedHashMap<String, String> orderby) {
		return this.pageQuery(null, page, orderby, false);
	}

	/**
	 * 分页查询.
	 * 
	 * @param page
	 *            page对象
	 * 
	 * @return Page对象
	 */
	public Page<T> pageQuery(Page<T> page) {
		return this.pageQuery(null, page, null, false);
	}
	
	public int executeHQL(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		setParams(query, params);
		return query.executeUpdate();
	}

}
