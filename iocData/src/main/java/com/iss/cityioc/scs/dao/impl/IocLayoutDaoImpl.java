package com.iss.cityioc.scs.dao.impl;



import com.iss.cityioc.scs.common.dao.GenericDaoHibernateSupport;
import com.iss.cityioc.scs.dao.IIocLayoutDao;
import com.iss.cityioc.scs.entity.IocLayout;
import org.springframework.stereotype.Repository;

@Repository(value = IIocLayoutDao.DAO_NAME)
public class IocLayoutDaoImpl extends GenericDaoHibernateSupport<IocLayout> implements IIocLayoutDao{



}
