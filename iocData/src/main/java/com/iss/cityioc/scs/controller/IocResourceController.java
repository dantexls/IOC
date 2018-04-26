package com.iss.cityioc.scs.controller;

import java.util.Date;
import java.util.List;


import com.iss.cityioc.scs.entity.IocResource;
import com.iss.cityioc.scs.service.IIocResourceService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin()
@RestController
@RequestMapping(value = "/iocResource")
public class IocResourceController {
	@Autowired
	private IIocResourceService resourceFacade;

	@RequestMapping(value = "/saveResource/{resourceName}/{resourceType}/{resourcePath}")
	@ResponseBody
	public JSONObject saveResource(@PathVariable("resourceName") String resourceName,@PathVariable("resourceType") String resourceType,
								   @PathVariable("resourcePath") String resourcePath) {
		JSONObject jsonObject = new JSONObject();
		IocResource resource = new IocResource();
		resource.setResourceName(resourceName);
		resource.setResourceType(resourceType);
		resource.setResourcePath(resourcePath);
		resource.setCreateTime(new Date());
		String id = resourceFacade.save(resource);
		jsonObject.put("success", true);
		jsonObject.put("data", id);
		return jsonObject;
	}

	@RequestMapping(value = "/findResourceByTypeId/{typeId}")
	@ResponseBody
	public JSONObject findResourceByTypeId(@PathVariable("typeId") String typeId) {
		JSONObject jsonObject = new JSONObject();
		List<IocResource> data = resourceFacade.findResourceByTypeId(typeId);
		jsonObject.put("data", data);
		return jsonObject;
	}

	@RequestMapping(value = "/queryResourceByName/{resource}")
	@ResponseBody
	public JSONObject queryResourceByName(@PathVariable("resource") IocResource resource) {
		JSONObject jsonObject = new JSONObject();
		List<IocResource> data = resourceFacade.queryResourceByName(resource);
		jsonObject.put("data", data);
		jsonObject.put("length", data.size());
		return jsonObject;
	}
}
