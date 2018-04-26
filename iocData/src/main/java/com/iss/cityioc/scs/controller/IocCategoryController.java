package com.iss.cityioc.scs.controller;

import java.util.Date;
import java.util.List;

import com.iss.cityioc.scs.entity.IocCategory;
import com.iss.cityioc.scs.service.IIocCategoryService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping(value = "/category")
public class IocCategoryController {

	@Autowired
	private IIocCategoryService service;

	@RequestMapping("/findCategoryById/{categoryId}")
	@ResponseBody
	public JSONObject findCategoryById(@PathVariable("categoryId") String categoryId) {
		List<IocCategory> data = service.findCategoryById(categoryId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		jsonObject.put("data", data);
		jsonObject.put("length", data.size());
		return jsonObject;
	}

	@RequestMapping("/saveCategory/{cateGoryName}/{parentId}")
	@ResponseBody
	public JSONObject saveCategory(@PathVariable("cateGoryName") String cateGoryName,@PathVariable("parentId")String parentId) {
		IocCategory iocCategory = new IocCategory();
		JSONObject data = new JSONObject();
		iocCategory.setCateGoryName(cateGoryName);
		if(null==parentId||"".equals(parentId)){
			iocCategory.setParentId("0");
		}else{
			iocCategory.setParentId(parentId);
		}
		iocCategory.setCreateDate(new Date());
		String returnval = service.save(iocCategory);
		if (null != returnval && "".equals(returnval)) {
			data.put("success", true);
		} else {
			data.put("success", false);
		}
		return data;
	}
}
