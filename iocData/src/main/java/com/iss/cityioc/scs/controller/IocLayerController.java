package com.iss.cityioc.scs.controller;


import java.util.ArrayList;

import java.util.List;


import com.iss.cityioc.scs.common.util.RSTestReq;
import com.iss.cityioc.scs.entity.IocLayer;
import com.iss.cityioc.scs.service.IIocLayerService;
import net.minidev.json.JSONObject;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin()
@RestController
@RequestMapping(value = "/layer")
public class IocLayerController {

	@Autowired
	private IIocLayerService service;

	@RequestMapping(value = "/saveLayer/{scenesId}/{divs}")
	@ResponseBody
	public JSONObject saveLayer(@PathVariable("scenesId") String scenesId, @PathVariable("divs") String divs) {
		JSONObject jsonObject = new JSONObject();
		JSONArray array = JSONArray.fromObject(divs);
		List<String> list = JSONArray.toList(array);
		if (scenesId.equals("agent") || scenesId.equals("tempWeb")) {
			for (String s : list) {
				String[] div = s.split("@");
				IocLayer layer = new IocLayer();
				layer.setScenesId(scenesId);
				layer.setStyle(div[0]);
				layer.setContent(div[1]);
				service.save(layer);
			}
		} else {
			for (String s : list) {
				String[] div = s.split("@");
				IocLayer layer = new IocLayer();
				layer.setScenesId(scenesId);
				layer.setStyle(div[0]);
				layer.setContent(div[1]);
				layer.setChannelId(div[2]);
				layer.setCurrentLocation(div[3]);
				layer.setRowNumber(div[4]);
				layer.setColumnNumber(div[5]);
				service.save(layer);
			}
		}
		jsonObject.put("success", true);
		return jsonObject;
	}

	@RequestMapping(value = "/delLayer/{scenseId}")
	@ResponseBody
	public JSONObject delLayer(@PathVariable("scenseId") String scenseId) {
		JSONObject jsonObject = new JSONObject();
		List<IocLayer> layers = service.queryAllByScenesId(scenseId);
		if (layers.size() > 0) {
			service.deleteAllByScenesId(layers);
		}
		jsonObject.put("success", true);
		return jsonObject;
	}

	@RequestMapping(value = "/queryByScenesId/{scenseId}")
	@ResponseBody
	public JSONObject queryByScenesId(@PathVariable("scenseId") String scenesId) {
		List<IocLayer> layers = new ArrayList<IocLayer>();
		layers = service.queryByScenesId(scenesId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		jsonObject.put("data", layers);
		jsonObject.put("size", layers.size());
		return jsonObject;
	}

	@RequestMapping(value = "/queryByScenesIdAndchannelId/{scenesId}/{channelId}")
	@ResponseBody
	public JSONObject queryByScenesId(@PathVariable("scenesId") String scenesId, @PathVariable("channelId") String channelId) {
		List<IocLayer> layers = new ArrayList<IocLayer>();
		RSTestReq req = new RSTestReq();
		req.setScenesId(scenesId);
		req.setChannelId(channelId);
		layers = service.queryByScenesIdAndchannelId(req.getScenesId(), req.getChannelId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		jsonObject.put("data", layers);
		jsonObject.put("size", layers.size());
		return jsonObject;
	}
}
