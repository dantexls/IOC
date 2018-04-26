package com.iss.cityioc.scs.dao.impl;

import com.iss.cityioc.scs.common.dao.GenericDaoHibernateSupport;
import com.iss.cityioc.scs.dao.IIocCategoryDao;
import com.iss.cityioc.scs.entity.IocCategory;
import org.springframework.stereotype.Repository;



@Repository(value = IIocCategoryDao.DAO_NAME)
public class IocCategoryDaoImpl extends GenericDaoHibernateSupport<IocCategory> implements IIocCategoryDao {


	
	

}
