package com.bootdo.blog.dao;

import com.bootdo.blog.domain.CatalogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-07-30 21:36:53
 */
@Mapper
public interface CatalogDao {

	CatalogDO get(Long id);
	
	List<CatalogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CatalogDO catalog);
	
	int update(CatalogDO catalog);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
