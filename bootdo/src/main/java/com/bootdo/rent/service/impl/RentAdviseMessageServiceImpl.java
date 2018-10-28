package com.bootdo.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.rent.dao.RentAdviseMessageDao;
import com.bootdo.rent.domain.RentAdviseMessageDO;
import com.bootdo.rent.service.RentAdviseMessageService;



@Service
public class RentAdviseMessageServiceImpl implements RentAdviseMessageService {
	@Autowired
	private RentAdviseMessageDao rentAdviseMessageDao;
	
	@Override
	public RentAdviseMessageDO get(Long id){
		return rentAdviseMessageDao.get(id);
	}
	
	@Override
	public List<RentAdviseMessageDO> list(Map<String, Object> map){
		return rentAdviseMessageDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return rentAdviseMessageDao.count(map);
	}
	
	@Override
	public int save(RentAdviseMessageDO rentAdviseMessage){
		return rentAdviseMessageDao.save(rentAdviseMessage);
	}
	
	@Override
	public int update(RentAdviseMessageDO rentAdviseMessage){
		return rentAdviseMessageDao.update(rentAdviseMessage);
	}
	
	@Override
	public int remove(Long id){
		return rentAdviseMessageDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return rentAdviseMessageDao.batchRemove(ids);
	}
	
}
