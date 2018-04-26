package com.iss.cityioc.scs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ioc_resource_category")
public class IocResourceType extends IdEntity {
	private String ResourceCategoryName;
	private String ParentId;
	private Date CreateTime;

	@Column(name = "RESOURCE_CATEGORY_NAME")
	public String getResourceCategoryName() {
		return ResourceCategoryName;
	}

	public void setResourceCategoryName(String resourceCategoryName) {
		ResourceCategoryName = resourceCategoryName;
	}
	@Column(name = "PARENT_ID")
	public String getParentId() {
		return ParentId;
	}

	public void setParentId(String parentId) {
		ParentId = parentId;
	}
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

	@Override
	public String toString() {
		return "IocResourceType [ResourceCategoryName=" + ResourceCategoryName
				+ ", ParentId=" + ParentId + ", CreateTime=" + CreateTime + "]";
	}
}
