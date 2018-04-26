package com.iss.cityioc.scs.service.impl;

import java.util.HashMap;

import java.util.List;
import java.util.Map;


import com.iss.cityioc.scs.dao.IIocCategoryDao;
import com.iss.cityioc.scs.entity.IocCategory;
import com.iss.cityioc.scs.service.IIocCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class IocCategoryServiceImpl implements IIocCategoryService {
	@Autowired
	private IIocCategoryDao dao;

	@Override
	public List<IocCategory> findCategoryById(String categoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PARENT_ID", categoryId);
		List<IocCategory> data = dao.findAllByProperties(map);
		return data;
	}

	@Override
	public String save(IocCategory category) {
		return (String)dao.save(category);
	}
}
