package com.iss.cityioc.scs.controller;

import java.util.Date;
import java.util.List;


import com.iss.cityioc.scs.entity.IocResourceType;
import com.iss.cityioc.scs.service.IIocResourceTypeService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping(value = "/resourcetype")
public class IocResourceTypeController {

	@Autowired
	private IIocResourceTypeService facade;

	@RequestMapping("/saveResourceType/{typeName}/{parentId}")
	@ResponseBody
	public JSONObject saveResourceType(@PathVariable("typeName") String typeName, @PathVariable("parentId") String parentId) {
		JSONObject data = new JSONObject();
		IocResourceType type = new IocResourceType();
		type.setResourceCategoryName(typeName);
		type.setCreateTime(new Date());
		if (null == parentId || "".equals(parentId)) {
			type.setParentId("0");
		} else {
			type.setParentId(parentId);
		}
		String res = facade.saveResourceType(type);
			data.put("success", true);
			data.put("res", res);
		return data;
	}

	@RequestMapping("/findResourceTypeList/{resourceTypeId}")
	@ResponseBody
	public JSONObject findResourceTypeList(@PathVariable("resourceTypeId") String resourceTypeId) {
		JSONObject jsonObject = new JSONObject();
		List<IocResourceType> data = facade
				.getResourceTypebyPid(resourceTypeId);
		jsonObject.put("success", true);
		jsonObject.put("data", data);
		jsonObject.put("length", data.size());
		return jsonObject;
	}

}
