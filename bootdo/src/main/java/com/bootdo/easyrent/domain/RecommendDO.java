package com.bootdo.easyrent.domain;

import java.io.Serializable;


/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-10-14 09:36:30
 */
public class RecommendDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//户型介绍
	private String houseType;
	//简介
	private String houseAbstract;
	//位置
	private String location;
	//价格
	private Float price;
	//图片路径
	private String imgUrl;
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
	 * 设置：户型介绍
	 */
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	/**
	 * 获取：户型介绍
	 */
	public String getHouseType() {
		return houseType;
	}
	/**
	 * 设置：简介
	 */
	public void setHouseAbstract(String houseAbstract) {
		this.houseAbstract = houseAbstract;
	}
	/**
	 * 获取：简介
	 */
	public String getHouseAbstract() {
		return houseAbstract;
	}
	/**
	 * 设置：位置
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * 获取：位置
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * 设置：价格
	 */
	public void setPrice(Float price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public Float getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
