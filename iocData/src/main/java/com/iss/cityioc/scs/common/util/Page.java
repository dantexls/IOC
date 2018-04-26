package com.iss.cityioc.scs.common.util;

/**
 * 存储分页查询返回信息.
 */

import java.util.List;

/**
 * 
 * <p> 内容描述 : 存储分页查询返回信息.</p> 
 * <p> 修改日期： 2015年4月15日 下午1:27:06 </p>
 * @author Dp,Zhao
 * @version V1.0 
 * @param <T>
 */
public class Page<T> {
	/** 当前分页号. */
	private int currentPage;

	/** 总信息条数. */
	private long totalCount;

	/** 总页数. */
	private int pages;

	/** 查询结果. */
	private List<T> result;

	/** 一页显示条数. */
	private int pageSize;

	/**
	 * 
	 * <p> 内容描述 : 返回分页号</p> 
	 * @return int 分页号
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 
	 * <p> 内容描述 : 设置分页号</p> 
	 * @param currentPage 分页号
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 
	 * <p> 内容描述 : 返回总页数</p> 
	 * @return
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * 
	 * <p> 内容描述 : 设置总页数</p> 
	 * @param pages 总页数
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}

	/**
	 * 
	 * <p> 内容描述 : 返回总信息条数</p> 
	 * @return long 总信息条数
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 
	 * <p> 内容描述 : 设置总信息条数</p> 
	 * @param totalCount 总信息条数
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		// 计算总页数
		if (0 < this.pageSize) {
			if (0 == this.totalCount % this.pageSize) {
				this.pages = (int) (this.totalCount / this.pageSize);
			} else {
				this.pages = (int) (this.totalCount / this.pageSize + 1);
			}
		}
	}

	/**
	 * 
	 * <p> 内容描述 : 返回查询结果</p> 
	 * @return List<T> 查询结果
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 
	 * <p> 内容描述 : 设置查询结果</p> 
	 * @param result 查询结果
	 */
	public void setResult(List<T> result) {
		this.result = result;
	}

	/**
	 * 
	 * <p> 内容描述 : 返回一页显示条数</p> 
	 * @return int 一页显示条数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 
	 * <p> 内容描述 : 设置一页显示条数</p> 
	 * @param pageSize 一页显示条数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
