package com.bootdo.blog.service;

import com.bootdo.blog.domain.CommentDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-08-04 19:58:35
 */
public interface CommentService {
	
	CommentDO get(Integer id);
	
	List<CommentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommentDO comment);
	
	int update(CommentDO comment);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
