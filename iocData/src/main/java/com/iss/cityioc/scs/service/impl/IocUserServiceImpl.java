package com.iss.cityioc.scs.service.impl;

import java.util.List;
import java.util.Map;

import com.iss.cityioc.scs.dao.IIocUserDao;
import com.iss.cityioc.scs.entity.IocUser;
import com.iss.cityioc.scs.service.IIocUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class IocUserServiceImpl implements IIocUserService {

	@Autowired
	private IIocUserDao userDao;

	@Override
	public String save(IocUser user) {
		return (String) userDao.save(user);
	}

	@Override
	public List<IocUser> findUser(Map<String, Object> map) {
		return userDao.findAllByProperties(map);
	}

	@Override
	public void delete(IocUser user) {
		userDao.delete(user);
	}

	@Override
	public void update(IocUser user) {
		userDao.update(user);
	}

	@Override
	public List<IocUser> findAll() {
		return userDao.findAll();
	}

}
