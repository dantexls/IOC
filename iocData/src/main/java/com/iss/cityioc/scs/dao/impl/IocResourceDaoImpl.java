package com.iss.cityioc.scs.dao.impl;

import java.util.List;

import com.iss.cityioc.scs.common.dao.GenericDaoHibernateSupport;
import com.iss.cityioc.scs.dao.IIocResourceDao;
import com.iss.cityioc.scs.entity.IocResource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository(value = IIocResourceDao.DAO_NAME)
public class IocResourceDaoImpl extends GenericDaoHibernateSupport<IocResource> implements IIocResourceDao{

	@Override
	public List<IocResource> queryResourceByName(IocResource resource) {
		String hql="FROM IocResource a WHERE 1=1 ";
		if(resource!=null){
			if(resource.getResourceName()!=null && !resource.getResourceName().equals("")){
				hql+=" and a.resourceName like '%"+resource.getResourceName()+"%'";		
			}
		}
		Session session= this.getSession();
		Query query =session.createQuery(hql);
		return query.list();
		
	}

}
