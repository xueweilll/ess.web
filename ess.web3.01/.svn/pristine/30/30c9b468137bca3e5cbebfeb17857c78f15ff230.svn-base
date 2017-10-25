package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.Lmulimit;
import com.hibernate.entity.Role;
import com.service.LmuLimitService;
@Service("lmuLimitService")
public class LmuLimitServiceImpl implements LmuLimitService {

	@Autowired
	private BaseDao baseDao;

	@Override
	public List<Lmulimit> getAllLmuLimit() {
		
		return baseDao.findAll("from Lmulimit", Lmulimit.class);
	}

	@Override
	public int count(String hql) {
		int count = 0;
		List<Integer> list = baseDao.findAll(hql, Integer.class);
		if(list!=null){
			Number num = (Number)list.get(0);
			count = num.intValue();
		}
		return count;
	}

	@Override
	public List<Lmulimit> lmuLimitList(String hql, int firstResult, int maxResult) {
		List<Lmulimit> list = baseDao.findByPage(hql, Lmulimit.class, firstResult, maxResult);
		return list;
	}

	@Override
	public void saveLmuLimit(Lmulimit lmulimit) {
		// TODO Auto-generated method stub
		baseDao.save(lmulimit);
	}

	@Override
	public void updateLmuLimit(Lmulimit lmulimit) {
		// TODO Auto-generated method stub
		baseDao.update(lmulimit);
	}	
	


	



}
