package com.iss.cityioc.scs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.iss.cityioc.scs.dao.IIocResourceTypeDao;
import com.iss.cityioc.scs.entity.IocResourceType;
import com.iss.cityioc.scs.service.IIocResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class IocResourceTypeServiceImpl implements IIocResourceTypeService {
	@Autowired
	private IIocResourceTypeDao dao;

	@Override
	public String saveResourceType(IocResourceType type) {
		return (String) dao.save(type);
	}

	@Override
	public List<IocResourceType> getResourceTypebyPid(String pid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PARENT_ID", pid);
		List<IocResourceType> data = dao.findAllByProperties(map);
		return data;
	}

}
