package com.iss.cityioc.scs.service;


import com.iss.cityioc.scs.entity.IocLayer;

import java.util.List;


public interface IIocLayerService {
	/**
	 * 场景表<Service
	 */

	
	String save(IocLayer layer);
	
	List<IocLayer> queryByScenesId(String scenesId);
	
	List<IocLayer> queryAllByScenesId(String scenesId);
	
	List<IocLayer> queryByScenesIdAndchannelId(String scenesId, String channelId);
	
	void deleteAllByScenesId(List<IocLayer> layers);

}
