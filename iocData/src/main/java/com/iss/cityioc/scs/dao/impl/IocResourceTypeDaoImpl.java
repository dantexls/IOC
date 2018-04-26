package com.iss.cityioc.scs.dao.impl;

import com.iss.cityioc.scs.common.dao.GenericDaoHibernateSupport;
import com.iss.cityioc.scs.dao.IIocResourceTypeDao;
import com.iss.cityioc.scs.entity.IocResourceType;
import org.springframework.stereotype.Repository;



@Repository(value = IIocResourceTypeDao.DAO_NAME)
public class IocResourceTypeDaoImpl extends GenericDaoHibernateSupport<IocResourceType> implements IIocResourceTypeDao {

}
