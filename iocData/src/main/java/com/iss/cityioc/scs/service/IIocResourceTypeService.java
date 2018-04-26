package com.iss.cityioc.scs.service;


import com.iss.cityioc.scs.entity.IocResourceType;

import java.util.List;

public interface IIocResourceTypeService {



	public String saveResourceType(IocResourceType type);
	
	public List<IocResourceType> getResourceTypebyPid(String pid);
}
