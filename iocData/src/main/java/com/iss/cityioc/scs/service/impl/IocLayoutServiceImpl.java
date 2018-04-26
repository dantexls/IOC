package com.iss.cityioc.scs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.iss.cityioc.scs.dao.IIocLayoutDao;
import com.iss.cityioc.scs.entity.IocLayout;
import com.iss.cityioc.scs.service.IIocLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class IocLayoutServiceImpl implements IIocLayoutService {

	@Autowired
	private IIocLayoutDao iocLayoutDao;

	@Override
	public String save(IocLayout layout) {
		return (String) iocLayoutDao.save(layout);
	}

	@Override
	public List<IocLayout> queryByLayoutName(String LayoutName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("layoutName", LayoutName);
		List<IocLayout> layoutList = new ArrayList<IocLayout>();
		List<IocLayout> layouts = iocLayoutDao.findAllByProperties(map);
		for (IocLayout layout : layouts) {
			if (layout.getCurrentLocation() == null
					|| ("1").equals(layout.getCurrentLocation())) {
				layoutList.add(layout);
			}
		}
		return layoutList;
	}

	@Override
	public List<IocLayout> queryAllByLayoutName(String LayoutName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("layoutName", LayoutName);
		List<IocLayout> layoutList = iocLayoutDao.findAllByProperties(map);
		return layoutList;
	}

	@Override
	public List<IocLayout> queryByLayoutNameAndchannelId(String LayoutName,
			String channelId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("channelId", channelId);
		map.put("layoutName", LayoutName);
		List<IocLayout> layoutList = iocLayoutDao.findAllByProperties(map);
		return layoutList;
	}

	@Override
	public void deleteAllByLayoutName(List<IocLayout> layouts) {
		iocLayoutDao.deleteAll(layouts);

	}

	@Override
	public List<IocLayout> querydistinctbyName() {
		String hql=" from IocLayout l group by l.layoutName HAVING(COUNT(l.layoutName)>1)";
		return iocLayoutDao.findByHQL(hql);
	}

}
