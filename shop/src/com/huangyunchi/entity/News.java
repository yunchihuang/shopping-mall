package com.huangyunchi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告
 * @author qiujy
 */
public class News implements Serializable {
	
	private static final long serialVersionUID = 8419106325554272464L;
	
	private Integer id;
	/*标题*/
	private String title;
	/*主配图*/
	private String thumbnail;
	/*是否为轮播图*/
	private boolean top;
	/*主内容*/
	private String content;
	
	private int hits;
	/*发布时间*/
	private Date pub_time;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the top
	 */
	public boolean isTop() {
		return top;
	}
	/**
	 * @param top the top to set
	 */
	public void setTop(boolean top) {
		this.top = top;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the pub_time
	 */
	public Date getPub_time() {
		return pub_time;
	}
	/**
	 * @param pub_time the pub_time to set
	 */
	public void setPub_time(Date pub_time) {
		this.pub_time = pub_time;
	}
	/**
	 * @return the hits
	 */
	public int getHits() {
		return hits;
	}
	/**
	 * @param hits the hits to set
	 */
	public void setHits(int hits) {
		this.hits = hits;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", thumbnail="
				+ thumbnail + ", top=" + top + ", content=" + content
				+ ",hits=" + hits + ", pub_time=" + pub_time + "]";
	}
}
