package com.iss.cityioc.scs.service;


import com.iss.cityioc.scs.entity.IocScenes;

import java.util.List;
import java.util.Map;


public interface IIocScenesService {
	/**
	 * 场景表<Service
	 */

	
	String save(IocScenes s);
	
	IocScenes queryById(String id);
	
	List<IocScenes> queryAll();
	
	List<IocScenes> queryByCategoryId(String categoryId);
	
	void update(IocScenes value);
	
    List<IocScenes> queryAllsort(Map<String, Object> map);

}
