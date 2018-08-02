package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.DictTypeDao;
import com.bootdo.system.domain.DictTypeDO;
import com.bootdo.system.service.DictTypeService;



@Service
public class DictTypeServiceImpl implements DictTypeService {
	@Autowired
	private DictTypeDao dictTypeDao;
	
	@Override
	public DictTypeDO get(Long id){
		return dictTypeDao.get(id);
	}
	
	@Override
	public List<DictTypeDO> list(Map<String, Object> map){
		return dictTypeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dictTypeDao.count(map);
	}
	
	@Override
	public int save(DictTypeDO dictType){
		return dictTypeDao.save(dictType);
	}
	
	@Override
	public int update(DictTypeDO dictType){
		return dictTypeDao.update(dictType);
	}
	
	@Override
	public int remove(Long id){
		return dictTypeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return dictTypeDao.batchRemove(ids);
	}
	
}
