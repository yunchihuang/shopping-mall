package com.huangyunchi.entity;

import java.io.Serializable;

/**
 * 会员的地址
 * @author qiujy
 */
public class Address implements Serializable {
	private static final long serialVersionUID = 529932754573978762L;
	
	/*编号*/
	private Integer id;
	/* '收货人' */
	private String contact;
	/* '联系电话' */
	private String mobile;
	/* '详细地址' */
	private String street;
	/* '邮编' */
	private String zipcode;
	/* '所属会员' */
	private Integer mbr_id;
	/* '是否为所属会员的默认收货地址' */
	private boolean default_value;
	
	
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
	 * @return the mbr_id
	 */
	public Integer getMbr_id() {
		return mbr_id;
	}
	/**
	 * @param mbr_id the mbr_id to set
	 */
	public void setMbr_id(Integer mbr_id) {
		this.mbr_id = mbr_id;
	}
	/**
	 * @return the default_value
	 */
	public boolean getDefault_value() {
		return default_value;
	}
	/**
	 * @param default_value the default_value to set
	 */
	public void setDefault_value(boolean default_value) {
		this.default_value = default_value;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Address [id=" + id + ", contact=" + contact + ", mobile="
				+ mobile + ", street=" + street + ", zipcode=" + zipcode
				+ ", mbr_id=" + mbr_id + ", default_value=" + default_value
				+ "]";
	}
}
