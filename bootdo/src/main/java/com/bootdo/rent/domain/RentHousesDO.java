package com.bootdo.rent.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-10-21 21:04:04
 */
public class RentHousesDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//国家
	private String country;
	//省
	private String province;
	//市
	private String city;
	//区
	private String district;
	//详细地址
	private String address;
	//小区名称
	private String villageName;
	//楼栋
	private Integer buildingNum;
	//楼层
	private Integer floorNum;
	//门牌号
	private String houseNo;
	//总楼层
	private Integer totalFloorNum;
	//介绍
	private String description;
	//卧室数量
	private Integer bedRoomNum;
	//客厅数量
	private Integer livingRoomNum;
	//车位数量
	private Integer parkingNum;
	//餐厅数量
	private Integer diningRoomNum;
	//
	private Integer kitchenNum;
	//卫生间数量
	private Integer restRoomNum;
	//电梯数量
	private Integer elevatorNum;
	//每层户数
	private Integer floorRoomNum;
	//户型图
	private String houseTypeImgUrl;

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
	 * 设置：国家
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * 获取：国家
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * 设置：省
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：省
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：市
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：区
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * 获取：区
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * 设置：详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：详细地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：小区名称
	 */
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	/**
	 * 获取：小区名称
	 */
	public String getVillageName() {
		return villageName;
	}
	/**
	 * 设置：楼栋
	 */
	public void setBuildingNum(Integer buildingNum) {
		this.buildingNum = buildingNum;
	}
	/**
	 * 获取：楼栋
	 */
	public Integer getBuildingNum() {
		return buildingNum;
	}
	/**
	 * 设置：楼层
	 */
	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
	}
	/**
	 * 获取：楼层
	 */
	public Integer getFloorNum() {
		return floorNum;
	}
	/**
	 * 设置：门牌号
	 */
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	/**
	 * 获取：门牌号
	 */
	public String getHouseNo() {
		return houseNo;
	}
	/**
	 * 设置：总楼层
	 */
	public void setTotalFloorNum(Integer totalFloorNum) {
		this.totalFloorNum = totalFloorNum;
	}
	/**
	 * 获取：总楼层
	 */
	public Integer getTotalFloorNum() {
		return totalFloorNum;
	}
	/**
	 * 设置：介绍
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：介绍
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：卧室数量
	 */
	public void setBedRoomNum(Integer bedRoomNum) {
		this.bedRoomNum = bedRoomNum;
	}
	/**
	 * 获取：卧室数量
	 */
	public Integer getBedRoomNum() {
		return bedRoomNum;
	}
	/**
	 * 设置：客厅数量
	 */
	public void setLivingRoomNum(Integer livingRoomNum) {
		this.livingRoomNum = livingRoomNum;
	}
	/**
	 * 获取：客厅数量
	 */
	public Integer getLivingRoomNum() {
		return livingRoomNum;
	}
	/**
	 * 设置：车位数量
	 */
	public void setParkingNum(Integer parkingNum) {
		this.parkingNum = parkingNum;
	}
	/**
	 * 获取：车位数量
	 */
	public Integer getParkingNum() {
		return parkingNum;
	}
	/**
	 * 设置：餐厅数量
	 */
	public void setDiningRoomNum(Integer diningRoomNum) {
		this.diningRoomNum = diningRoomNum;
	}
	/**
	 * 获取：餐厅数量
	 */
	public Integer getDiningRoomNum() {
		return diningRoomNum;
	}
	/**
	 * 设置：
	 */
	public void setKitchenNum(Integer kitchenNum) {
		this.kitchenNum = kitchenNum;
	}
	/**
	 * 获取：
	 */
	public Integer getKitchenNum() {
		return kitchenNum;
	}
	/**
	 * 设置：卫生间数量
	 */
	public void setRestRoomNum(Integer restRoomNum) {
		this.restRoomNum = restRoomNum;
	}
	/**
	 * 获取：卫生间数量
	 */
	public Integer getRestRoomNum() {
		return restRoomNum;
	}
	/**
	 * 设置：电梯数量
	 */
	public void setElevatorNum(Integer elevatorNum) {
		this.elevatorNum = elevatorNum;
	}
	/**
	 * 获取：电梯数量
	 */
	public Integer getElevatorNum() {
		return elevatorNum;
	}
	/**
	 * 设置：每层户数
	 */
	public void setFloorRoomNum(Integer floorRoomNum) {
		this.floorRoomNum = floorRoomNum;
	}
	/**
	 * 获取：每层户数
	 */
	public Integer getFloorRoomNum() {
		return floorRoomNum;
	}
	/**
	 * 设置：户型图
	 */
	public void setHouseTypeImgUrl(String houseTypeImgUrl) {
		this.houseTypeImgUrl = houseTypeImgUrl;
	}
	/**
	 * 获取：户型图
	 */
	public String getHouseTypeImgUrl() {
		return houseTypeImgUrl;
	}
}
