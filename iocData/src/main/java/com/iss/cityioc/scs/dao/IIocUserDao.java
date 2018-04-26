package com.iss.cityioc.scs.dao;


import com.iss.cityioc.scs.common.dao.GenericDao;
import com.iss.cityioc.scs.entity.IocUser;

public interface IIocUserDao extends GenericDao<IocUser> {
	public static final String DAO_NAME = "iocUserDao";
}
