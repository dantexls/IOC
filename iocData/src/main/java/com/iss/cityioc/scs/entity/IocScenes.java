package com.iss.cityioc.scs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ioc_scenes")
public class IocScenes extends IdEntity {
	private String scenesName; // 场景名称
	private String categoryId; // 场景分类id
	private String userId; // 用户id
	private String status; //启用状态  0-停用   1-启用
	private String magnet; //磁贴状态  0-关闭   1-开启
	private int sort; //场景排序
	private Date createTime; //创建时间

	@Column(name = "SCENES_NAME")
	public String getScenesName() {
		return scenesName;
	}

	public void setScenesName(String scenesName) {
		this.scenesName = scenesName;
	}

	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "CATEGORY_ID")
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="SORT")
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "MAGNET")
	public String getMagnet() {
		return magnet;
	}

	public void setMagnet(String magnet) {
		this.magnet = magnet;
	}
	
	
}
