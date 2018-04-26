package com.iss.cityioc.scs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.iss.cityioc.scs.dao.IIocScenesDao;
import com.iss.cityioc.scs.entity.IocScenes;
import com.iss.cityioc.scs.service.IIocScenesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class IocScenesServiceImpl implements IIocScenesService {

	@Autowired
	private IIocScenesDao iocScenesDao;

	@Override
	public String save(IocScenes s) {
		return (String) iocScenesDao.save(s);
	}

	@Override
	public IocScenes queryById(String id) {
		return iocScenesDao.findById(id);
	}

	@Override
	public List<IocScenes> queryAll() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "1");
		return iocScenesDao.findAllByProperties(map);
	}

	@Override
	public List<IocScenes> queryByCategoryId(String categoryId) {
		return iocScenesDao.queryByCategoryId(categoryId);
	}

	@Override
	public void update(IocScenes value) {
		iocScenesDao.update(value);
	}

	@Override
	public List<IocScenes> queryAllsort(Map<String, Object> map) {
		return iocScenesDao.queryAllsort(map);
	}
}
