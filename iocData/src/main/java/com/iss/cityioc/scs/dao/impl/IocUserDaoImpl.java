package com.iss.cityioc.scs.dao.impl;

import com.iss.cityioc.scs.common.dao.GenericDaoHibernateSupport;
import com.iss.cityioc.scs.dao.IIocUserDao;
import com.iss.cityioc.scs.entity.IocUser;
import org.springframework.stereotype.Repository;



@Repository(value = IIocUserDao.DAO_NAME)
public class IocUserDaoImpl extends GenericDaoHibernateSupport<IocUser> implements IIocUserDao{

}
