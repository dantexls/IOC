package com.iss.cityioc.scs.common.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.iss.cityioc.scs.common.model.Page;


/**
 * 
 * <p> 内容描述 : DAO</p> 
 * <p> 修改日期： 2015年4月15日 上午10:52:46 </p>
 * @author Dp,Zhao
 * @version V1.0 
 * @param <T>
 */
public interface GenericDao<T> {
	
	/**
	 * 
	 * <p> 内容描述 : 删除一个Bean对象.</p> 
	 * @param o 对象
	 */
	void delete(T o);

	/**
	 * 
	 * <p> 内容描述 : 删除一个集合内的所有对象</p> 
	 * @param list 集合
	 */
	void deleteAll(Collection<T> list);

	/**
	 * 
	 * <p> 内容描述 : 根据对象id删除对象.</p> 
	 * @param id 对象ID
	 */
	void deleteById(Serializable id);

	/**
	 * 
	 * <p> 内容描述 : 执行SQL语句获得Set结果,此SQL语句非HQL语句.</p> 
	 * @param sql 基本sql语句
	 * @return Set集合
	 * @throws Exception
	 */
	ResultSet executeQuery(final String sql) throws Exception;

	/**
	 * 
	 * <p> 内容描述 : 查询所有信息.</p> 
	 * @return List<T> 
	 */
	List<T> findAll();

	/**
	 * 
	 * <p> 内容描述 : 根据某些属性条件查询.</p> 
	 * @param map 存放属性name-value 相对应的条件集.
	 * @return List<T>
	 */
	List<T> findAllByProperties(Map<String, Object> map);

	/**
	 * 
	 * <p> 内容描述 : 根据属性名值进行查询.</p> 
	 * @param propertyName  属性名字：如id，username。。。
	 * @param propertyValue 属性值： 如11，'admin'。。。
	 * @return List<T>
	 */
	List<T> findAllByProperty(String propertyName, Object propertyValue);

	/**
	 * 
	 * <p> 内容描述 : 依靠当前实体的id查找该实体.</p> 
	 * @param idValue  主键值
	 * @return T
	 */
	T findById(Serializable idValue);

	/**
	 * 
	 * <p> 内容描述 : 新增一条信息.</p> 
	 * @param value JavaBean
	 * @return 主键ID
	 */
	Serializable save(T value);

	/**
	 * 
	 * <p> 内容描述 : 保存一组对象集合.</p> 
	 * @param coll 对象集合
	 */
	void saveAll(Collection<T> coll);

	/**
	 * 
	 * <p> 内容描述 : 修改一条信息.</p> 
	 * @param value JavaBean
	 */
	void update(T value);

	/**
	 * 
	 * <p> 内容描述 : 保存或者修改对象</p> 
	 * @param o JavaBean
	 */
	void saveOrUpdate(T o);

	/**
	 * 
	 * <p> 内容描述 : 根据hql查找对象列表</p> 
	 * @param hql hql语句
	 * @return List<T>
	 */
	List<T> findByHQL(String hql);
	
	/**
	 * 
	 * <p> 内容描述 : 根据hql查找对象列表</p> 
	 * @param hql hql语句
	 * @param params 参数
	 * @return List<T>
	 */
	List<T> findByHQLAndParams(String hql, Object... params);

	/**
	 * 
	 * <p> 内容描述 : 根据属性名值进行查询.</p> 
	 * @param propertyName 属性名字：如id，username。。。
	 * @param propertyValue 属性值： 如11，'admin'。。。
	 * @return int 获得符合条件的数据的条数
	 */
	int findColumnCount(String propertyName, Object propertyValue);

	/**
	 * 
	 * <p> 内容描述 : 根据条件查询某个Bean.</p> 
	 * @param strings  可以传个数为n的任意数量参数。名值之间用英文的::=分开 例
	 *                 selectBSql("id::=11","name::='myName'");
	 * @return List<T> 返回find(from 表 where id=11 and name='myName')的结果
	 */
	List<T> selectBySqlCondition(String... strings);

	/**
	 * 
	 * <p> 内容描述 : 根据条件查询某个Bean.</p> 
	 * @param strings  可以传个数为n的任意数量参数。名值之间用英文的::=分开 例
	 *                 selectBSql("id::=11","name::='myName'");
	 * @return List<T> 返回find(from 表 where id=11 or name='myName')的结果
	 */
	List<T> selectBySqlConditionOr(String... strings);

	/**
	 * 
	 * <p> 内容描述 : 分页查询.</p> 
	 * @param page  page对象
	 * @param countHql 查询总条数
	 * @param resultHql  查询结果集
	 * @param countParams  总条数参数列表
	 * @param resultParams 结果集参数列表
	 * @return Page<T> Page对象
	 */
	public Page<T> pageQueryByHql(Page<T> page, final String countHql, final String resultHql, Object[] countParams, Object[] resultParams);
	
	/**
	 * 
	 * <p> 内容描述 : 分页查询</p> 
	 * @param filter 过滤器名
	 * @param fpName filter_key名
	 * @param fpVal filter_key属性值
	 * @param page page对象
	 * @param countHql 查询总条数
	 * @param resultHql  查询结果集
	 * @param countParams  总条数参数列表
	 * @param resultParams 结果集参数列表
	 * @return Page<T> Page对象
	 */
	public Page<T> pageQueryByHqlWithFilter(final String filter, final String fpName, Object fpVal, Page<T> page, final String countHql, final String resultHql, Object[] countParams, Object[] resultParams);
	
	/**
	 * 
	 * <p> 内容描述 : 分页查询.</p> 
	 * @param map 条件map
	 * @param page page对象
	 * @param orderby 排序
	 * @return Page对象
	 */
	public Page<T> pageQuery(Map<String, Object> map, Page<T> page, LinkedHashMap<String, String> orderby);

	/**
	 * 
	 * <p> 内容描述 : 分页查询.</p> 
	 * @param map 条件map
	 * @param page page对象
	 * @param orderby 排序
	 * @param like 是否模糊搜索
	 * @return Page<T> Page对象
	 */
	public Page<T> pageQuery(Map<String, Object> map, Page<T> page, LinkedHashMap<String, String> orderby, boolean like);

	/**
	 * 
	 * <p> 内容描述 :分页查询.</p> 
	 * @param map 条件map
	 * @param page page对象
	 * @param like 是否模糊搜索
	 * @return Page<T>
	 */
	public Page<T> pageQuery(Map<String, Object> map, Page<T> page, boolean like);

	/**
	 * 
	 * <p> 内容描述 : 分页查询.</p> 
	 * @param map 条件map
	 * @param page page对象
	 * @return Page<T> Page对象
	 */
	public Page<T> pageQuery(Map<String, Object> map, Page<T> page);

	/**
	 * 
	 * <p> 内容描述 : 分页查询.</p> 
	 * @param page page对象
	 * @param orderby 排序
	 * @return Page<T> Page对象
	 */
	public Page<T> pageQuery(Page<T> page, LinkedHashMap<String, String> orderby);

	/**
	 * 
	 * <p> 内容描述 : 分页查询.</p> 
	 * @param page page对象
	 * @return Page<T> Page对象
	 */
	public Page<T> pageQuery(Page<T> page);
	
	/**
	 * 
	 * <p> 内容描述 : 依靠当前实体的id查找该实体</p> 
	 * @param idValue 主键ID
	 * @return T JavaBean对象
	 */
	public T loadById(Serializable idValue);
	
	/**
	 * 
	 * <p> 内容描述 : 获得Session</p> 
	 * @return Session
	 */
	public Session getSession();

	/**
	 * 
	 * <p> 内容描述 : 根据hql查询满足条件的记录数</p> 
	 * @param hql hql语句
	 * @param params 参数
	 * @return int 满足条件的记录数
	 */
	public int executeHQL(String hql, Object... params);
	
	/**
	 * 
	 * <p> 内容描述 : 根据hql查询满足条件的记录数</p> 
	 * @param hql hql语句
	 * @param filter 过滤器名
	 * @param fpName filter_key名
	 * @param fpVal filter_key属性值
	 * @param params 参数
	 * @return List<T> 满足条件的记录
	 */
	public List<T> findByHQLWithFilterAndParams(String hql, String filter, String fpName, Object fpVal, Object... params);
}
