package com.iss.cityioc.scs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.iss.cityioc.scs.dao.IIocResourceDao;
import com.iss.cityioc.scs.entity.IocResource;
import com.iss.cityioc.scs.service.IIocResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class IocResourceServiceImpl implements IIocResourceService {

	@Autowired
	private IIocResourceDao resourceDao;

	@Override
	public String save(IocResource resource) {
		return (String) resourceDao.save(resource);
	}

	@Override
	public List<IocResource> findResourceByTypeId(String typeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("RESOURCE_TYPE", typeId);
		return resourceDao.findAllByProperties(map);
	}

	@Override
	public List<IocResource> queryResourceByName(IocResource resource) {
		
		return resourceDao.queryResourceByName(resource);
	}

}
