package com.bootdo.system.dao;

import com.bootdo.system.domain.DictTypeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-07-29 16:00:46
 */
@Mapper
public interface DictTypeDao {

	DictTypeDO get(Long id);
	
	List<DictTypeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DictTypeDO dictType);
	
	int update(DictTypeDO dictType);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
