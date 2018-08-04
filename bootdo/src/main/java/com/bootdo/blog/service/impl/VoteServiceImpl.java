package com.bootdo.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.blog.dao.VoteDao;
import com.bootdo.blog.domain.VoteDO;
import com.bootdo.blog.service.VoteService;



@Service
public class VoteServiceImpl implements VoteService {
	@Autowired
	private VoteDao voteDao;
	
	@Override
	public VoteDO get(Integer id){
		return voteDao.get(id);
	}
	
	@Override
	public List<VoteDO> list(Map<String, Object> map){
		return voteDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return voteDao.count(map);
	}
	
	@Override
	public int save(VoteDO vote){
		return voteDao.save(vote);
	}
	
	@Override
	public int update(VoteDO vote){
		return voteDao.update(vote);
	}
	
	@Override
	public int remove(Integer id){
		return voteDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return voteDao.batchRemove(ids);
	}
	
}
