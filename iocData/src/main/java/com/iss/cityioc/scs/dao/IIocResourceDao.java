package com.iss.cityioc.scs.dao;


import com.iss.cityioc.scs.common.dao.GenericDao;
import com.iss.cityioc.scs.entity.IocResource;

import java.util.List;

public interface IIocResourceDao extends GenericDao<IocResource> {
	/** 
	 * @Fields DAO_NAME : 资源表DAO
	 */ 
	public static final String DAO_NAME = "iIocResourceDao";

	List<IocResource> queryResourceByName(IocResource resource);

}
