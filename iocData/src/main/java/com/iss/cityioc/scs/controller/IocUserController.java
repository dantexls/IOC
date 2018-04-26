package com.iss.cityioc.scs.controller;

import java.io.IOException;
import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.iss.cityioc.scs.common.util.MD5;
import com.iss.cityioc.scs.common.util.VerifyCodeUtils;
import com.iss.cityioc.scs.entity.IocUser;
import com.iss.cityioc.scs.service.IIocUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import net.minidev.json.JSONObject;

@CrossOrigin()
@RestController
@RequestMapping(value = "/iocUser")
public class IocUserController {

	@Autowired
	private IIocUserService iocUserFacade;

	/**
	 * 保存用户
	 * @param username
	 * @param password
	 * @param phone
	 * @param roleId
	 * @param nickname
	 * @param userType
	 * @return
	 */
	@RequestMapping(value = "/saveUser")
	@ResponseBody
	public JSONObject saveUser(String username, String password,
			String phone, String roleId, String nickname, String userType) {
		IocUser user = new IocUser();
		user.setUsername(username);
		MD5 md5 = new MD5(password);
		user.setPassword(md5.compute());
		user.setPhone(phone);
		user.setRoleId(Integer.valueOf(roleId));
		user.setNickname(nickname);
		Date date = new Date();
		user.setRegTime(date);
		user.setUserType(Integer.valueOf(userType));
		String message = iocUserFacade.save(user);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", message);
		return jsonObject;
	}
	
	@RequestMapping(value = "/checkCodes")
	@ResponseBody
	public JSONObject login(@RequestParam("codes") String codes, HttpServletRequest request) {

		JSONObject jsonobject = new JSONObject();
		
		HttpSession session = request.getSession();
		String rand = String.valueOf(session.getAttribute("rand"));
		if(!rand.toUpperCase().equals(codes.toUpperCase())) {
			jsonobject.put("message", "验证码错误");
			jsonobject.put("data", false);
		} else {
			jsonobject.put("data", true);
		}
		
//		return jsonobject.toString();
		return jsonobject;
	}


	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public JSONObject login(String username, String password, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		MD5 md5 = new MD5(password);
		map.put("password", md5.compute());
		
		List<IocUser> users = iocUserFacade.findUser(map);
		IocUser user = null;
		JSONObject jsonobject = new JSONObject();
		if(users.size()>0) {
			user = users.get(0);
			
			HttpSession session = request.getSession();
			//设置session过期时间
			session.setMaxInactiveInterval(15*60);			
			//保存用户信息到session中
			session.setAttribute("user", user);
			
			jsonobject.put("message", true);
//			jsonobject.put("data", user);
		} else {
			jsonobject.put("message", false);
			jsonobject.put("data", "用户名或者密码不正确");
		}
		return jsonobject;
	}

	@RequestMapping(value = "/findAll")
	@ResponseBody
	public JSONObject findAll() {
		JSONObject jsonobject = new JSONObject();
		List<IocUser> users = new ArrayList<IocUser>();
		users = iocUserFacade.findAll();
		jsonobject.put("success", true);
		jsonobject.put("data", users);
		return jsonobject;
	}

	@RequestMapping(value = "/findUser")
	@ResponseBody
	public JSONObject findUser(String id, String username, String phone, String nickname ) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(id != null && !"".equals(id)) {
			map.put("id", id);
		}
		if(username != null && !"".equals(username)) {
			map.put("username", username);		
		}
		if(phone != null && !"".equals(phone)) {
			map.put("phone", phone);
		}
		if(nickname != null && !"".equals(nickname)) {
			map.put("nickname", nickname);
		}
		JSONObject jsonobject = new JSONObject();
		List<IocUser> userList = new ArrayList<IocUser>();
		userList = iocUserFacade.findUser(map);
		jsonobject.put("success", true);
		jsonobject.put("data", userList);
		jsonobject.put("size", userList.size());
		return jsonobject;
	}
	
	@RequestMapping(value = "/delUser")
	@ResponseBody
	public JSONObject delUser(String id) {
		
		IocUser user = new IocUser();
		user.setId(id);
		iocUserFacade.delete(user);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "true");
		
		return jsonObject;
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public JSONObject update(String id, String username, /*String password,*/
			String phone, String roleId, String nickname, String userType) {
		IocUser user = new IocUser();
//		user.setId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<IocUser> userList = iocUserFacade.findUser(map);
		user = userList.get(0);
		user.setUsername(username);
		user.setPhone(phone);
		user.setRoleId(Integer.valueOf(roleId));
		user.setNickname(nickname);
		user.setUserType(Integer.valueOf(userType));
		iocUserFacade.update(user);
		JSONObject jsonobject = new JSONObject();
		jsonobject.put("success", true);
		return jsonobject;
	}
	
	@RequestMapping(value = "/authImg")
	@ResponseBody
	public void authImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg");  
          
        //生成随机字串  
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session  
        HttpSession session = request.getSession(true);  
        session.setAttribute("rand", verifyCode.toLowerCase());  
        //生成图片  
        int w = 200, h = 80;  
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);  
  
    }
}
