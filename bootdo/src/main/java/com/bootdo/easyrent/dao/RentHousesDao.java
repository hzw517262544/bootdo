package com.bootdo.easyrent.dao;

import com.bootdo.easyrent.domain.RentHousesDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-10-21 21:04:04
 */
@Mapper
public interface RentHousesDao {

	RentHousesDO get(Integer id);
	
	List<RentHousesDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RentHousesDO rentHouses);
	
	int update(RentHousesDO rentHouses);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
