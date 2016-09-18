package com.huangyunchi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 类目
 * 
 * @author qiujy
 */
public class Category implements Serializable {
	private static final long serialVersionUID = -3350763396111688050L;
	/** 编号 */
	private Integer id;
	/** 类目名称 */
	private String name;
	/** 类目别名 */
	private String alias;
	/** 父类目p_id */
	private Integer p_id;
	/** 排序权重 */
	private int order_weight = 10;
	
	/** 子类目列表 */
	private List<Category> childs = new ArrayList<Category>();

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the p_id
	 */
	public Integer getP_id() {
		return p_id;
	}

	/**
	 * @param p_id the p_id to set
	 */
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	/**
	 * @return the order_weight
	 */
	public int getOrder_weight() {
		return order_weight;
	}

	/**
	 * @param order_weight the order_weight to set
	 */
	public void setOrder_weight(int order_weight) {
		this.order_weight = order_weight;
	}

	/**
	 * @return the childs
	 */
	public List<Category> getChilds() {
		return childs;
	}

	/**
	 * @param childs the childs to set
	 */
	public void setChilds(List<Category> childs) {
		this.childs = childs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", alias=" + alias
				+ ", p_id=" + p_id + ", order_weight=" + order_weight
				+ ", childs=" + childs + "]";
	}
}