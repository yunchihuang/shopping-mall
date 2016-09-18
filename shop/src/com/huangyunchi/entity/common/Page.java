package com.huangyunchi.entity.common;

import java.io.Serializable;
import java.util.List;


/**
 * 分页数据实体类
 * @author Administrator
 *
 * @param <T>
 */
public class Page<T> implements Serializable{
	private static final long serialVersionUID = -89082502669991590L;
	/** 从1开始的页号 */
	private int number;
	/** 每页要返回的记录数 */
	private int size;
	
	
	/** 总记录数 */
	private long totalElements;
	
	/** 总页数，通过计算得出 */
	private int totalPages;
	
	/** 当前页的数据集合 */
	private List<T> items;
	
	public Page(){}
	
	public Page(int number, int size){
		this.number = number;
		this.size = size;
	}
	

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		
		if(totalElements % size == 0){
		    totalPages = (int)(totalElements / size);
		}else{
			totalPages = (int)(totalElements / size + 1);
		}
		
		return totalPages;
	}

	@SuppressWarnings("unused")
	private void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Page [number=" + number + ", size=" + size + ", totalElements=" 
				+ totalElements + ", totalPages="
				+ getTotalPages() + ", items=" + items + "]";
	}
}
