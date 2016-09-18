package com.huangyunchi.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单项
 * @author qiujy
 */
public class Item implements Serializable {
	private static final long serialVersionUID = -5513015465167003750L;
	private Integer id;
	private Integer order_id;/* '所属订单编号', */
	private Integer product_id;/* '所购商品编号', */
	private int amount;/* '单品数量', */
	private BigDecimal total_price;/* '单品总价', */
	private BigDecimal payment_price;/* '单品实付的总价' */
	
	private Product product;/* '所购商品' */
	
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
	 * @return the order_id
	 */
	public Integer getOrder_id() {
		return order_id;
	}
	/**
	 * @param order_id the order_id to set
	 */
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	/**
	 * @return the product_id
	 */
	public Integer getProduct_id() {
		return product_id;
	}
	/**
	 * @param product_id the product_id to set
	 */
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * @return the total_price
	 */
	public BigDecimal getTotal_price() {
		return total_price;
	}
	/**
	 * @param total_price the total_price to set
	 */
	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}
	/**
	 * @return the payment_price
	 */
	public BigDecimal getPayment_price() {
		return payment_price;
	}
	/**
	 * @param payment_price the payment_price to set
	 */
	public void setPayment_price(BigDecimal payment_price) {
		this.payment_price = payment_price;
	}
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item [id=" + id + ", order_id=" + order_id + ", product_id="
				+ product_id + ", amount=" + amount + ", total_price="
				+ total_price + ", payment_price=" + payment_price
				+ ", product=" + product + "]";
	}
}
