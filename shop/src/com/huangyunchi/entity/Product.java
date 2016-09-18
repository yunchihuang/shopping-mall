package com.huangyunchi.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * 
 * @author qiujy
 */
public class Product implements Serializable {
	private static final long serialVersionUID = -379231982515404860L;
	/* 编号 */
	private Integer id; 
	/* '商品名称' */
	private String name;
	/* '所属类目'cate_id */
	private Integer cate_id;
	/* '主配图片' */
	private String thumbnail; 
	/* '库存数量' */
	private int inventory; 
	 /* '售出数量' */
	private int sales_volume;
	/* '定价' */
	private BigDecimal price; 
	/* '售价' */
	private BigDecimal sale_price; 
	/* '详情富文本描述' */
	private String detail_description; 
	/* '卖点富文本描述' */
	private String selling_description; 
	/* '添加时间' */
	private Date create_time; 
	/* '开售时间' */
	private Date sale_time;
	
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
	 * @return the thumbnail
	 */
	public String getThumbnail() {
		return thumbnail;
	}
	/**
	 * @param thumbnail the thumbnail to set
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	/**
	 * @return the inventory
	 */
	public int getInventory() {
		return inventory;
	}
	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	/**
	 * @return the sales_volume
	 */
	public int getSales_volume() {
		return sales_volume;
	}
	/**
	 * @param sales_volume the sales_volume to set
	 */
	public void setSales_volume(int sales_volume) {
		this.sales_volume = sales_volume;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the sale_price
	 */
	public BigDecimal getSale_price() {
		return sale_price;
	}
	/**
	 * @param sale_price the sale_price to set
	 */
	public void setSale_price(BigDecimal sale_price) {
		this.sale_price = sale_price;
	}
	/**
	 * @return the detail_description
	 */
	public String getDetail_description() {
		return detail_description;
	}
	/**
	 * @param detail_description the detail_description to set
	 */
	public void setDetail_description(String detail_description) {
		this.detail_description = detail_description;
	}
	/**
	 * @return the selling_description
	 */
	public String getSelling_description() {
		return selling_description;
	}
	/**
	 * @param selling_description the selling_description to set
	 */
	public void setSelling_description(String selling_description) {
		this.selling_description = selling_description;
	}
	/**
	 * @return the create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}
	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	/**
	 * @return the sale_time
	 */
	public Date getSale_time() {
		return sale_time;
	}
	/**
	 * @param sale_time the sale_time to set
	 */
	public void setSale_time(Date sale_time) {
		this.sale_time = sale_time;
	}
	
	/**
	 * @return the cate_id
	 */
	public Integer getCate_id() {
		return cate_id;
	}
	/**
	 * @param cate_id the cate_id to set
	 */
	public void setCate_id(Integer cate_id) {
		this.cate_id = cate_id;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", cate_id=" + cate_id
				+ ", thumbnail=" + thumbnail + ", inventory=" + inventory
				+ ", sales_volume=" + sales_volume + ", price=" + price
				+ ", sale_price=" + sale_price + ", detail_description="
				+ detail_description + ", selling_description="
				+ selling_description + ", create_time=" + create_time
				+ ", sale_time=" + sale_time + "]";
	} 
	
}
