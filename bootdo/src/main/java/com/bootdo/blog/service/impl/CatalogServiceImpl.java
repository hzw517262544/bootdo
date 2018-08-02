package com.bootdo.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.blog.dao.CatalogDao;
import com.bootdo.blog.domain.CatalogDO;
import com.bootdo.blog.service.CatalogService;



@Service
public class CatalogServiceImpl implements CatalogService {
	@Autowired
	private CatalogDao catalogDao;
	
	@Override
	public CatalogDO get(Long id){
		return catalogDao.get(id);
	}
	
	@Override
	public List<CatalogDO> list(Map<String, Object> map){
		return catalogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return catalogDao.count(map);
	}
	
	@Override
	public int save(CatalogDO catalog){
		return catalogDao.save(catalog);
	}
	
	@Override
	public int update(CatalogDO catalog){
		return catalogDao.update(catalog);
	}
	
	@Override
	public int remove(Long id){
		return catalogDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return catalogDao.batchRemove(ids);
	}
	
}
