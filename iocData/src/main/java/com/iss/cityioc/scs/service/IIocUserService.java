package com.iss.cityioc.scs.service;


import com.iss.cityioc.scs.entity.IocUser;

import java.util.List;
import java.util.Map;


public interface IIocUserService {
	/**
	 * user Service
	 */

	
	String save(IocUser user);
	
	List<IocUser> findUser(Map<String, Object> map);
	
	List<IocUser> findAll();
	
	void delete(IocUser user);
	
	void update(IocUser user);
}
