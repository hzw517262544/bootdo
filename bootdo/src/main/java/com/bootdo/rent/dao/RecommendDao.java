package com.bootdo.rent.dao;

import com.bootdo.rent.domain.RecommendDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-10-14 09:36:30
 */
@Mapper
public interface RecommendDao {

	RecommendDO get(Integer id);
	
	List<RecommendDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RecommendDO recommend);
	
	int update(RecommendDO recommend);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
