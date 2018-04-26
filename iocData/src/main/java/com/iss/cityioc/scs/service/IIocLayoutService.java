package com.iss.cityioc.scs.service;


import com.iss.cityioc.scs.entity.IocLayout;

import java.util.List;


public interface IIocLayoutService {
	/**
	 * 布局表Service
	 */

	
	String save(IocLayout layout);
	
	List<IocLayout> queryByLayoutName(String LayoutName);
	
	List<IocLayout> queryAllByLayoutName(String LayoutName);
	
	List<IocLayout> queryByLayoutNameAndchannelId(String LayoutName, String channelId);
	
	void deleteAllByLayoutName(List<IocLayout> layouts);
	
	List<IocLayout> querydistinctbyName();

}
