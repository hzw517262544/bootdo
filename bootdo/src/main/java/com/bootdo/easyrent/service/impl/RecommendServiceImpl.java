package com.bootdo.easyrent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.easyrent.dao.RecommendDao;
import com.bootdo.easyrent.domain.RecommendDO;
import com.bootdo.easyrent.service.RecommendService;



@Service
public class RecommendServiceImpl implements RecommendService {
	@Autowired
	private RecommendDao recommendDao;
	
	@Override
	public RecommendDO get(Integer id){
		return recommendDao.get(id);
	}
	
	@Override
	public List<RecommendDO> list(Map<String, Object> map){
		return recommendDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return recommendDao.count(map);
	}
	
	@Override
	public int save(RecommendDO recommend){
		return recommendDao.save(recommend);
	}
	
	@Override
	public int update(RecommendDO recommend){
		return recommendDao.update(recommend);
	}
	
	@Override
	public int remove(Integer id){
		return recommendDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return recommendDao.batchRemove(ids);
	}
	
}
