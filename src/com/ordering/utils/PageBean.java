package com.ordering.utils;

import java.util.List;
/**
 * 分页所使用的BEAN
 * @author 杨谦
 * @date 2015-9-21
 *
 */
public class PageBean<T> {
	
	private int pageCurrent;//当前页码pageCurrent
	@SuppressWarnings("unused")
	private int pageCount;//总页数pageCount(计算得出)
	private int rowCount;//总记录rowCount(根据查询的值即可得到)
	private int pageSize;//每页记录数pageSize(根据需求设定)
	private List<T> beanList;//当前页的记录存储list
	private String url;//也就是跟在URL连接后的
	
	public int getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	/**
	 * 计算总页数
	 * @return
	 */
	public int getPageCount() {
		int pageCount = rowCount / pageSize;
		return pageCount * pageSize == rowCount ? pageCount : pageCount+1;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
