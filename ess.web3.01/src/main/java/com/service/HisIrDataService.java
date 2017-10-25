package com.service;

import java.util.List;

import com.hibernate.entity.HisIrData;

public interface HisIrDataService {
	// public List<HisIrData> DTUlist(String cmd);
	// public HisIrData hisIrData(String id);
	public HisIrData LatestData(String dtuId, int lmuId);

	/*
	 * public void saveHisIrData(HisIrData hisIrData); public void
	 * updateHisIrData(HisIrData hisIrData); public void deleteHisIrData(String
	 * id);
	 */
	
	public int count(String hql);

	public List<HisIrData> HisIrListByPage(String hql, int firstResult,
			int maxResult);
}
