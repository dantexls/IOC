package com.iss.cityioc.scs.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.iss.cityioc.scs.conf.MainConfig;
import com.iss.cityioc.scs.entity.IocResource;
import com.iss.cityioc.scs.service.IIocResourceService;
import net.minidev.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin()
@RestController
@RequestMapping(value = "/fileupload")
public class UploadController {

	@Autowired
	private IIocResourceService resourceFacade;

	/**
	 * 上传单个文件操作
	 * 
	 *
	 * 括号中的参数名file和表单的input节点的name属性值一致
	 * @return
	 */
	@RequestMapping(value = "/doUpload/{ResourceName}/{typeIdval}/{linkAddress}")
	@ResponseBody
	public JSONObject doUploadFile(@RequestParam("file") MultipartFile file,
			@PathVariable("ResourceName") String ResourceName, @PathVariable("typeIdval") String typeIdval,@PathVariable("linkAddress") String linkAddress) {
		JSONObject object = new JSONObject();
		MainConfig mainConfig=MainConfig.getMainConfig();
		String savePath = mainConfig.getFileUploadReallyPath();
		//typeIdval=3， 类型为网页，网页不需要上传图片，但是需要保存网页链接地址
		if(typeIdval.equals("3")) {
			IocResource resource = new IocResource();
			resource.setResourceName(ResourceName);
			if(!linkAddress.contains("http")) {
				linkAddress = "http://" + linkAddress;
			}
			resource.setResourcePath(linkAddress);
			resource.setResourceType(typeIdval);
			resource.setCreateTime(new Date());
			resourceFacade.save(resource);
		} else {//如果上传文件为空，则此次保存作废
			if (!file.isEmpty()) {
				try {
					String str=file.getOriginalFilename();
					String [] names=str.split("\\.");
					String newgfilename=names[0]+new Date().getTime()+"."+names[1];
					File target = new File(savePath,newgfilename);
					FileUtils.copyInputStreamToFile(file.getInputStream(), target);
					IocResource resource = new IocResource();
					resource.setResourceName(ResourceName);
					resource.setResourcePath("upload/"+newgfilename);
					resource.setResourceType(typeIdval);
					resource.setCreateTime(new Date());
					resourceFacade.save(resource);
				} catch (IOException e) {
					object.put("success", false);
					return object;
				}
			}
			
		}
		object.put("success", true);
		return object;
	}
}
