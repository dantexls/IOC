package com.iss.cityioc.scs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "MS_Resource")
public class Resource extends IdEntity{

	private  String   name;         //  服务名：业务_子系统_模块
	private  String   language;     //  开发语言
	private  String   svn;          //  服务源代码地址
	private  String   svn_usr;      //  SVN用户名
	private  String   svn_pass;     //  SVN密码
	private  String   build_script; //  服务打包脚本   （maven\ant）
	private  String   test_case;    //  功能测试用例
	
	
	@Column(name = "MS_NAME", length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Column(name = "MS_LANGUAGE", length = 100)
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
	@Column(name = "MS_SVN", length = 100)
	public String getSvn() {
		return svn;
	}
	public void setSvn(String svn) {
		this.svn = svn;
	}
	
	
	@Column(name = "SVN_USR", length = 100)
	public String getSvn_usr() {
		return svn_usr;
	}
	public void setSvn_usr(String svn_usr) {
		this.svn_usr = svn_usr;
	}
	
	
	@Column(name = "SVN_PASS", length = 100)
	public String getSvn_pass() {
		return svn_pass;
	}
	public void setSvn_pass(String svn_pass) {
		this.svn_pass = svn_pass;
	}
	
	
	@Column(name = "BUILD_SCRIPT", length = 100)
	public String getBuild_script() {
		return build_script;
	}
	public void setBuild_script(String build_script) {
		this.build_script = build_script;
	}
	
	
	@Column(name = "TEST_CASE", length = 100)
	public String getTest_case() {
		return test_case;
	}
	public void setTest_case(String test_case) {
		this.test_case = test_case;
	}
	
	
	 
}
