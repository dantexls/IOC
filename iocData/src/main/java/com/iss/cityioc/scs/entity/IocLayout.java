package com.iss.cityioc.scs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ioc_layout")
public class IocLayout extends IdEntity {
	private String layoutName; //场景id
	private String channelId; //通道id
	private String rowNumber; //行数（分屏显示）
	private String columnNumber; //列数（分屏显示）
	private String currentLocation; //当前位置（分屏显示）
	private String style; //图层样式
	private String content; //图层内容
	private String extend1; //扩展1
	private String extend2; //扩展2
	private String extend3; //扩展3
	

	@Column(name = "LAYOUT_NAME")
	public String getLayoutName() {
		return layoutName;
	}

	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}
	
	@Column(name = "CHANNEL_ID")
	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	@Column(name = "ROW_NUMBER")
	public String getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}
	
	@Column(name = "COLUMN_NUMBER")
	public String getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(String columnNumber) {
		this.columnNumber = columnNumber;
	}
	
	@Column(name = "CURRENT_LOCATION")
	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "STYLE")
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	@Column(name = "EXTEND1")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Column(name = "EXTEND2")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	
	@Column(name = "EXTEND3")
	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}
	
	
}
