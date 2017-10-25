package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.LMU;
import com.hibernate.entity.Lmulimit;
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

	@Override
	public List<Lmulimit> getByLmuId(int lmuId) {
		try{
			return baseDao.findAll("from Lmulimit l where l.lmuId='"+lmuId+"'", Lmulimit.class);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteLmuLimit(int lmuId) {
		String hql ="delete from Lmulimit l where l.lmuId = '"+lmuId+"'";
		baseDao.delete(hql);
	}

	@Override
	public Lmulimit getLmuLimit(int lmuId) {
		return baseDao.getbywhere(Lmulimit.class, "from Lmulimit l where l.lmuId='"+lmuId+"'");
	}

	@Override
	public List<Lmulimit> getBylmuIdIn(List<LMU> lmuLimitList) {
		StringBuffer sb = new StringBuffer();
		
		for (LMU lmu : lmuLimitList) {
			sb.append(lmu.getId() + ",");
		}
		String hql = "from Lmulimit l where l.lmuId in ("+sb.substring(0,sb.length()-1)+")";
		return baseDao.findAll(hql, Lmulimit.class);
	}
}
