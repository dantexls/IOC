/*    
 * Copyright (c) 2013 ISoftStone, Inc.
 * All right reserved.
 *
 * �ļ���      IdEntity.java
 * ����:     Jacky Wang
 * �������ڣ� 2013-7-23 ����01:28:29
 * �汾��           
 *
 */
package com.iss.cityioc.scs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;


/** 
 * <p> 内容描述 : 序列化实体</p> 
 * <p> 修改日期： 2015年4月14日 下午2:02:40 </p>
 * @author Administrator
 * @version V1.0  
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class IdEntity implements Serializable
{
	protected String id;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId()
	{
		return id;
	}

	public void setId(final String id)
	{
		this.id = id;
	}
	
}
