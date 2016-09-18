package com.huangyunchi.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 * @author qiujy
 */
public class Orders implements Serializable {
	private static final long serialVersionUID = -181747313514354329L;
	private Integer id;
	private String number;/* '订单号', */
	private Integer buyer_id;/* '所属买家会员编号', */
	private Integer total_amount;/* '商品总数量', */
	private BigDecimal total_price;/* '总的费用', */
	private BigDecimal payment_price;/* '实付的费用', */
	private String remark;/* '买家留言', */
	private String contact;/* '收货人', */
	private String mobile;/* '联系电话', */
	private String street;/* '详细地址', */
	private String zipcode;/* '邮编', */
	private Date create_time;/* '下单时间', */
	private Date payment_time;/* '支付时间', */
	private Date delivery_time;/* '发货时间', */
	private Date end_time;/* '完成时间', */
	
	/* comment '订单状态：0下单,1待付款,2已付款,3待发货,4已发货,5己收货,6已完成,-1已取消' */
	private int status;
	
	private List<Item> items = new ArrayList<Item>();

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
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the buyer_id
	 */
	public Integer getBuyer_id() {
		return buyer_id;
	}

	/**
	 * @param buyer_id the buyer_id to set
	 */
	public void setBuyer_id(Integer buyer_id) {
		this.buyer_id = buyer_id;
	}

	/**
	 * @return the total_amount
	 */
	public Integer getTotal_amount() {
		return total_amount;
	}

	/**
	 * @param total_amount the total_amount to set
	 */
	public void setTotal_amount(Integer total_amount) {
		this.total_amount = total_amount;
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
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	 * @return the payment_time
	 */
	public Date getPayment_time() {
		return payment_time;
	}

	/**
	 * @param payment_time the payment_time to set
	 */
	public void setPayment_time(Date payment_time) {
		this.payment_time = payment_time;
	}

	/**
	 * @return the delivery_time
	 */
	public Date getDelivery_time() {
		return delivery_time;
	}

	/**
	 * @param delivery_time the delivery_time to set
	 */
	public void setDelivery_time(Date delivery_time) {
		this.delivery_time = delivery_time;
	}

	/**
	 * @return the end_time
	 */
	public Date getEnd_time() {
		return end_time;
	}

	/**
	 * @param end_time the end_time to set
	 */
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Orders [id=" + id + ", number=" + number + ", buyer_id="
				+ buyer_id + ", total_amount=" + total_amount + ", total_price="
				+ total_price + ", payment_price=" + payment_price + ", remark="
				+ remark + ", contact=" + contact + ", mobile=" + mobile
				+ ", street=" + street + ", zipcode=" + zipcode
				+ ", create_time=" + create_time + ", payment_time="
				+ payment_time + ", delivery_time=" + delivery_time
				+ ", end_time=" + end_time + ", status=" + status + ", items="
				+ items + "]";
	}
}
