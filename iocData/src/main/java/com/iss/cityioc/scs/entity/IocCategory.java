package com.iss.cityioc.scs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ioc_scenes_category")
public class IocCategory extends IdEntity {
	private String cateGoryName;
	private String parentId;
	private Date createDate;

	@Column(name="CATEGORY_NAME")
	public String getCateGoryName() {
		return cateGoryName;
	}
	
	public void setCateGoryName(String cateGoryName) {
		this.cateGoryName = cateGoryName;
	}

	@Column(name="PARENT_ID")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name="CREATE_TIME")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
