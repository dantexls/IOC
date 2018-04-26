package com.iss.cityioc.scs.conf;

import org.springframework.web.context.ContextLoaderListener;

/**
 * config初始化
 * 
 * @author Chen Guang Hua
 *
 */
public class MainConfig {
	/**
	 * @Fields fileUploadTempPath : 上传图片
	 */

	private String fileUploadTempPath;

	/**
	 * @Fields fileTalentTempPath : 预览图片路径
	 */

	private String fileTalentTempPath;
	
	/**
	 * @Fields fileTalentReallyPath : 保存服务器图片路径
	 */
	private String fileUploadReallyPath;
	/**
	 * @Fields fileTalentReallyPath : 保存数据库图片路径
	 */
	private String fileTalentReallyPath;
	/**
	 * @Fields address : 门户地址
	 */
	
	public String getFileUploadTempPath() {
		return fileUploadTempPath;
	}

	public void setFileUploadTempPath(String fileUploadTempPath) {
		this.fileUploadTempPath = fileUploadTempPath;
	}

	public String getFileTalentTempPath() {
		return fileTalentTempPath;
	}

	public void setFileTalentTempPath(String fileTalentTempPath) {
		this.fileTalentTempPath = fileTalentTempPath;
	}

	public String getFileUploadReallyPath() {
		return fileUploadReallyPath;
	}

	public void setFileUploadReallyPath(String fileUploadReallyPath) {
		this.fileUploadReallyPath = fileUploadReallyPath;
	}

	public String getFileTalentReallyPath() {
		return fileTalentReallyPath;
	}

	public void setFileTalentReallyPath(String fileTalentReallyPath) {
		this.fileTalentReallyPath = fileTalentReallyPath;
	}

	public static MainConfig getMainConfig() {
		MainConfig mainConfig = (MainConfig) ContextLoaderListener.getCurrentWebApplicationContext().getBean(
				"mainConfig");
		return mainConfig;
	}

}
