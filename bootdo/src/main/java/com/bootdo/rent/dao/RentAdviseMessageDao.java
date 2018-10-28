package com.bootdo.rent.dao;

import com.bootdo.rent.domain.RentAdviseMessageDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-10-27 09:08:59
 */
@Mapper
public interface RentAdviseMessageDao {

	RentAdviseMessageDO get(Long id);
	
	List<RentAdviseMessageDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RentAdviseMessageDO rentAdviseMessage);
	
	int update(RentAdviseMessageDO rentAdviseMessage);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
