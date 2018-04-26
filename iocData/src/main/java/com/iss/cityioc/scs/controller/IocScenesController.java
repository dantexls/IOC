package com.iss.cityioc.scs.controller;


import java.util.*;


import com.iss.cityioc.scs.entity.IocScenes;
import com.iss.cityioc.scs.service.IIocScenesService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping(value = "/scenes")
public class IocScenesController {

	@Autowired
	private IIocScenesService iocScenesFacade;

	@RequestMapping(value = "/saveScenes/{scenesName}/{cateGoryId}/{magnet}")
	@ResponseBody
	public JSONObject saveScenes(@PathVariable("scenesName") String scenesName, @PathVariable("cateGoryId")String cateGoryId,
								 @PathVariable("magnet") String magnet) {
		IocScenes scenes = new IocScenes();
		scenes.setScenesName(scenesName);
		scenes.setStatus("1");
		scenes.setMagnet(magnet);
		scenes.setCreateTime(new Date());
		scenes.setCategoryId(cateGoryId);
		String message = iocScenesFacade.save(scenes);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", message);
		return jsonObject;
	}

	@RequestMapping(value = "/queryById/{id}")
	@ResponseBody
	public IocScenes queryById(@PathVariable("id") String id) {
		IocScenes scenes = iocScenesFacade.queryById(id);
		return scenes;
	}

	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public JSONObject queryAll() {
		JSONObject jsonobject = new JSONObject();
		List<IocScenes> scenes = new ArrayList<IocScenes>();
		scenes = iocScenesFacade.queryAll();
		jsonobject.put("success", true);
		jsonobject.put("data", scenes);
		return jsonobject;
	}

	@RequestMapping(value = "/querySort")
	@ResponseBody
	public JSONObject querySort() {
		JSONObject jsonobject = new JSONObject();
		List<IocScenes> scenes = new ArrayList<IocScenes>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("column", "createTime");
		parameter.put("flag", "1");
		scenes = iocScenesFacade.queryAllsort(parameter);
		jsonobject.put("success", true);
		jsonobject.put("data", scenes);
		return jsonobject;
	}

	@RequestMapping(value = "/queryByCategoryId/{categoryId}")
	@ResponseBody
	public JSONObject queryByCategoryId(@PathVariable("categoryId") String categoryId) {
		JSONObject jsonobject = new JSONObject();
		List<IocScenes> scenes = new ArrayList<IocScenes>();
		scenes = iocScenesFacade.queryByCategoryId(categoryId);
		jsonobject.put("success", true);
		jsonobject.put("data", scenes);
		jsonobject.put("size", scenes.size());
		return jsonobject;
	}

	@RequestMapping(value = "/updateScense/{scenseId}/{magnet}")
	@ResponseBody
	public JSONObject updateScense(@PathVariable("scenseId") String scenseId,@PathVariable("magnet") String magnet) {
		JSONObject jsonobject = new JSONObject();
		IocScenes scense = iocScenesFacade.queryById(scenseId);
		scense.setMagnet(magnet);
		iocScenesFacade.update(scense);
		jsonobject.put("success", true);
		return jsonobject;
	}
}
