package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.HisIrData;
import com.service.HisIrDataService;

@Service
public class HisIrDataServiceImpl implements HisIrDataService {

	@Autowired
	private BaseDao baseDao;

	/*public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}*/

	@Override
	// ��lmuIdΪLmuCode
	public HisIrData LatestData(String dtuId, int lmuId) {
		List<HisIrData> list = baseDao.findAll("from HisIrData where dtuId='"
				+ dtuId + "' and lmuId='" + lmuId + "' order by addtime desc",
				HisIrData.class);
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public List<HisIrData> HisIrListByPage(String hql, int firstResult,
			int maxResult) {
		List<HisIrData> list = baseDao.findByPage(hql, HisIrData.class, firstResult, maxResult);
		return list;
	}

	@Override
	public int count(String hql) {
		int count = 0;
		//count = baseDao.findAll(hql, HisIrData.class).size();
		List<Integer> list = baseDao.findAll(hql, Integer.class);
		if(list!=null){
			Number num = (Number)list.get(0);
			count = num.intValue();
			System.out.println(count);
		}
		return count;
	}

}
