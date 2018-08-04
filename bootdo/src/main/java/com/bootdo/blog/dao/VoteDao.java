package com.bootdo.blog.dao;

import com.bootdo.blog.domain.VoteDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-08-04 22:17:13
 */
@Mapper
public interface VoteDao {

	VoteDO get(Integer id);
	
	List<VoteDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(VoteDO vote);
	
	int update(VoteDO vote);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
