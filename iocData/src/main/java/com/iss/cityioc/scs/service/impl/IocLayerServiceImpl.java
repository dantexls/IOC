package com.iss.cityioc.scs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.iss.cityioc.scs.dao.IIocLayerDao;
import com.iss.cityioc.scs.entity.IocLayer;
import com.iss.cityioc.scs.service.IIocLayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class IocLayerServiceImpl implements IIocLayerService {

	@Autowired
	private IIocLayerDao iocLayerDao;

	@Override
	public String save(IocLayer layer) {
		return (String)iocLayerDao.save(layer);
	}

	@Override
	public List<IocLayer> queryByScenesId(String scenesId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("scenesId", scenesId);
		List<IocLayer> layerList = new ArrayList<IocLayer>();
		List<IocLayer> layers = iocLayerDao.findAllByProperties(map);
		for(IocLayer layer : layers){
			if(layer.getCurrentLocation() == null ||("1").equals(layer.getCurrentLocation())){
				layerList.add(layer);
			}
		}
		return layerList;
	}
	
	@Override
	public List<IocLayer> queryAllByScenesId(String scenesId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("scenesId", scenesId);
		List<IocLayer> layers = iocLayerDao.findAllByProperties(map);
		return layers;
	}

	@Override
	public void deleteAllByScenesId(List<IocLayer> layers) {
		iocLayerDao.deleteAll(layers);
	}

	@Override
	public List<IocLayer> queryByScenesIdAndchannelId(String scenesId,
			String channelId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("scenesId", scenesId);
		map.put("channelId", channelId);
		List<IocLayer> layers = iocLayerDao.findAllByProperties(map);
		return layers;
	}

}
