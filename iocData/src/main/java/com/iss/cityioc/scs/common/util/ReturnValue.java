package com.iss.cityioc.scs.common.util;

import java.io.Serializable;

/**
 * 
 * <p> 内容描述 : 操作结果返回对象</p> 
 * <p> 修改日期： 2015年4月15日 下午1:32:45 </p>
 * @author Dp,Zhao
 * @version V1.0
 */
public class ReturnValue implements Serializable {
	
	private static final long serialVersionUID = -8001893618625869384L;
	
	/**
	 * 处理结果
	 */
	private boolean success = false;
	/**
	 * 信息
	 */
	private String message;
	
	/**
	 * 
	 * <p> 内容描述 : 返回处理结果</p> 
	 * @return boolean 
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * 
	 * <p> 内容描述 : 设置处理结果</p> 
	 * @param success 处理结果
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	/**
	 * 
	 * <p> 内容描述 : 获取信息</p> 
	 * @return String 
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * 
	 * <p> 内容描述 :设置信息 </p> 
	 * @param message 信息
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 
	 * <p> 内容描述 : 获取字节序列</p> 
	 * @return long
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
