package com.service;

import java.util.List;

import com.hibernate.entity.CurrentIrData;

public interface CurrentIrDataService {
	// public List<HisIrData> DTUlist(String cmd);
	// public HisIrData hisIrData(String id);
	public CurrentIrData LatestData(int dtuId, int lmuId);
	
	public List<CurrentIrData> LastestDataList(int dtuId);
	
	public List<CurrentIrData> LastestDataList(Integer[] dtuIds);
	
	public List<CurrentIrData> LastestDataList(String lmuIds);
	
	public void InsertCurrentIrData(CurrentIrData currentIrData);

	/*
	 * public void saveHisIrData(HisIrData hisIrData); public void
	 * updateHisIrData(HisIrData hisIrData); public void deleteHisIrData(String
	 * id);
	 */
	
	public int count(String hql);

	public List<CurrentIrData> HisIrListByPage(String hql, int firstResult,
			int maxResult);
}
