package com.bootdo.blog.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-08-04 19:58:35
 */
public class CommentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer blogId;
	//
	private String comment;
	//
	private Integer createUserId;
	//
	private String createUserName;
	//
	private Date createTime;

	//用户头像url
	private String picUrl;

	private String rownum;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	/**
	 * 获取：
	 */
	public Integer getBlogId() {
		return blogId;
	}
	/**
	 * 设置：
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取：
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * 设置：
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：
	 */
	public Integer getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	/**
	 * 获取：
	 */
	public String getCreateUserName() {
		return createUserName;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
}
