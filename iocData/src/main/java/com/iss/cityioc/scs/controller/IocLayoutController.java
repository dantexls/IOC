package com.iss.cityioc.scs.controller;


import java.util.ArrayList;
import java.util.List;


import com.iss.cityioc.scs.common.util.RSLayoutReq;
import com.iss.cityioc.scs.entity.IocLayout;
import com.iss.cityioc.scs.service.IIocLayoutService;
import net.minidev.json.JSONObject;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin()
@RestController
@RequestMapping(value = "/layout")
public class IocLayoutController {



	@Autowired
	private IIocLayoutService service;
	@RequestMapping(value = "/saveLayout/{LayoutName}/{divs}")
	@SuppressWarnings({ "unchecked", "deprecation" })
	@ResponseBody
	public JSONObject saveLayer(@PathVariable("LayoutName") String LayoutName,@PathVariable("divs") String divs) {
		JSONObject jsonObject = new JSONObject();
		JSONArray array = JSONArray.fromObject(divs);
		List<String> list = JSONArray.toList(array);
		for (String s : list) {
			String[] div = s.split("@");
			IocLayout layout = new IocLayout();
			layout.setLayoutName(LayoutName);
			layout.setStyle(div[0]);
			layout.setContent(div[1]);
			layout.setChannelId(div[2]);
			layout.setCurrentLocation(div[3]);
			layout.setRowNumber(div[4]);
			layout.setColumnNumber(div[5]);
			service.save(layout);
		}
		jsonObject.put("success", true);
		return jsonObject;
	}

	@RequestMapping(value = "/delLayout/{LayoutName}")
	@ResponseBody
	public JSONObject delLayout(@PathVariable("LayoutName") String LayoutName) {
		JSONObject jsonObject = new JSONObject();
		List<IocLayout> data = service
				.queryAllByLayoutName(LayoutName);
		if (data.size() > 0) {
			service.deleteAllByLayoutName(data);
		}
		jsonObject.put("success", true);
		return jsonObject;
	}

	@RequestMapping(value = "/queryByLayoutName/{LayoutName}")
	@ResponseBody
	public JSONObject queryByLayoutName(@PathVariable("LayoutName") String LayoutName) {
		List<IocLayout> iocLayouts = new ArrayList<IocLayout>();
		iocLayouts = service.queryByLayoutName(LayoutName);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		jsonObject.put("data", iocLayouts);
		jsonObject.put("size", iocLayouts.size());
		return jsonObject;
	}

	@RequestMapping(value = "/queryByLayoutNameAndchannelId/{LayoutName}/{channelId}")
	@ResponseBody
	public JSONObject queryByLayoutNameAndchannelId(@PathVariable("LayoutName") String LayoutName,
			@PathVariable("channelId") String channelId) {
		List<IocLayout> iocLayouts = new ArrayList<IocLayout>();
		RSLayoutReq req = new RSLayoutReq();
		req.setLayoutName(LayoutName);
		req.setChannelId(channelId);
		iocLayouts = service.queryByLayoutNameAndchannelId(
				req.getLayoutName(), req.getChannelId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		jsonObject.put("data", iocLayouts);
		jsonObject.put("size", iocLayouts.size());
		return jsonObject;
	}

	@RequestMapping(value = "/queryLayoutdistinct")
	@ResponseBody
	public JSONObject queryLayoutdistinct() {
		List<IocLayout> iocLayouts = null;
		iocLayouts = service.querydistinctbyName();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		jsonObject.put("data", iocLayouts);
		jsonObject.put("size", iocLayouts.size());
		return jsonObject;
	}
}
