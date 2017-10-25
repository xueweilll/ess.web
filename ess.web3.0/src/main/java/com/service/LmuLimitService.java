package com.service;

import java.util.List;

import com.hibernate.entity.LMU;
import com.hibernate.entity.Lmulimit;

public interface LmuLimitService {
	public List<Lmulimit> getAllLmuLimit();
	
	public int count(String hql);
	
	public List<Lmulimit> lmuLimitList(String hql, int firstResult,int maxResult);
	
	public void saveLmuLimit(Lmulimit lmulimit);
	
	public void updateLmuLimit(Lmulimit lmulimit);
	
	public List<Lmulimit> getByLmuId(int lmuId);
	
	public void deleteLmuLimit(int lmuId);
	
	public Lmulimit getLmuLimit(int lmuId);
	
	public List<Lmulimit> getBylmuIdIn(List<LMU> lmuLimitList);
	
}
