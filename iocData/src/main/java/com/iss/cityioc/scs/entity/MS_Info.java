package com.iss.cityioc.scs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "MS_Info")
public class MS_Info extends IdEntity{
	
	private  String  name;             // 服务名：业务_子系统_模块
	private  String  version;          // 版本：Ｖ主版本．副版本
	private  String  language;         // 开发语言
	private  String  original_type;    // 源服务接口框架：RESTFUL、WEBSERVICE、RPC等
	private  String  uris;             // 服务调用地址(列表)
	private  String  resource;         // 服务源代码地址
	private  String  lev;              // 服务级别（0，链接，1应用，2业务功能，3原子服务）
	private  String  provider;         // 提供方名称
	private  String  parameters;       // 参数及说明
	private  String  retums;           // 返回内容及说明
	private  String  discription;      // 服务整体功能介绍
	private  String  example;          // 应用案例
	private  Date    publish_date;     // 发布时间
	private  Date    last_update_date; // 后更新时间
	private  String  status;           // 服务运行状态    0-正常，1-测试，2-维护，3-不可用
	private  String  classification;   // 服务分类  （ 餐饮，电影，出行，生活服务等）
	
	
	@Column(name = "MS_NAME", length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "MS_VERSION", length = 100)
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	@Column(name = "MS_LANGUAGE", length = 100)
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	@Column(name = "MS_ORIGINAL_TYPE", length = 100)
	public String getOriginal_type() {
		return original_type;
	}
	public void setOriginal_type(String original_type) {
		this.original_type = original_type;
	}
	
	@Column(name = "MS_URIS", length = 100)
	public String getUris() {
		return uris;
	}
	public void setUris(String uris) {
		this.uris = uris;
	}
	
	@Column(name = "MS_RESOURCE", length = 100)
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	
	@Column(name = "MS_LEV", length = 100)
	public String getLev() {
		return lev;
	}
	public void setLev(String lev) {
		this.lev = lev;
	}
	
	@Column(name = "MS_PROVIDER", length = 100)
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	@Column(name = "MS_PARAMETERS", length = 100)
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	@Column(name = "MS_RETUMS", length = 100)
	public String getRetums() {
		return retums;
	}
	public void setRetums(String retums) {
		this.retums = retums;
	}
	
	@Column(name = "MS_DISCRIPTION", length = 100)
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
	@Column(name = "MS_EXAMPIE", length = 100)
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	
	@Column(name = "MS_PUBLISH_DATE")
	public Date getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}
	
	@Column(name = "MS_LAST_UPDATE_DATE")
	public Date getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(Date last_update_date) {
		this.last_update_date = last_update_date;
	}
	
	
	@Column(name = "MS_STATUS", length = 100)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "MS_CLASS", length = 100)
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	
	

}
