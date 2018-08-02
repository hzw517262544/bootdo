package com.bootdo.blog.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-07-30 21:36:53
 */
public class CatalogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//分类名称
	private String name;
	//描述
	private String description;
	//创建者
	private Integer createBy;
	//创建时间
	private Date createDate;
	//更新者
	private Long updateBy;
	//更新时间
	private Date updateDate;

	/**
	 * 设置：编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：分类名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：分类名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：创建者
	 */
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建者
	 */
	public Integer getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：更新者
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：更新者
	 */
	public Long getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
}
