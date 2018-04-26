package com.iss.cityioc.scs.dao.impl;
import com.iss.cityioc.scs.common.dao.GenericDaoHibernateSupport;
import com.iss.cityioc.scs.dao.IIocLayerDao;
import com.iss.cityioc.scs.entity.IocLayer;
import org.springframework.stereotype.Repository;


@Repository(value = IIocLayerDao.DAO_NAME)
public class IocLayerDaoImpl extends GenericDaoHibernateSupport<IocLayer> implements IIocLayerDao{



}
