package com.iss.cityioc.scs.service;


import com.iss.cityioc.scs.entity.IocResource;

import java.util.List;

public interface IIocResourceService {
	/**
	 * 资源表<Service
	 */

	
	String save(IocResource resource);
	
	List<IocResource> findResourceByTypeId(String typeId);

	List<IocResource> queryResourceByName(IocResource resource);
}
