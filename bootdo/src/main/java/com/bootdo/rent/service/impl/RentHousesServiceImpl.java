package com.bootdo.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.rent.dao.RentHousesDao;
import com.bootdo.rent.domain.RentHousesDO;
import com.bootdo.rent.service.RentHousesService;



@Service
public class RentHousesServiceImpl implements RentHousesService {
	@Autowired
	private RentHousesDao rentHousesDao;
	
	@Override
	public RentHousesDO get(Integer id){
		return rentHousesDao.get(id);
	}
	
	@Override
	public List<RentHousesDO> list(Map<String, Object> map){
		return rentHousesDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return rentHousesDao.count(map);
	}
	
	@Override
	public int save(RentHousesDO rentHouses){
		return rentHousesDao.save(rentHouses);
	}
	
	@Override
	public int update(RentHousesDO rentHouses){
		return rentHousesDao.update(rentHouses);
	}
	
	@Override
	public int remove(Integer id){
		return rentHousesDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return rentHousesDao.batchRemove(ids);
	}
	
}
