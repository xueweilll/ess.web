package com.service;

import java.util.List;

import com.hibernate.entity.LMU;

public interface LmuService {
	public int count(String hql);
	public List<LMU> LMUlist();
 	public LMU lmu(int id);
 	public void saveLMU(LMU lmu);
 	public void updateLMU(LMU lmu);
 	public void deleteLMU(int id);
 	public List<LMU> LMUlist(int DtuId);
 	public List<LMU> LMUlist(String Code);
 	public List<LMU> LMUlistbyDtuId(Integer[] DtuIds);
 	public List<LMU> LMUlistbyLmuId(Integer[] LmuIds);
	public int SelectId(int dtuId, int code);
	public List<LMU> lmuLimitList(String hql, int firstResult, int maxResult);
}
