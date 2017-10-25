package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.CurrentIrData;
import com.service.CurrentIrDataService;

@Service
public class CurrentIrDataServiceImpl implements CurrentIrDataService {

	@Autowired
	private BaseDao baseDao;

	/*public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}*/

	@Override
	// ��lmuIdΪLmuCode
	public CurrentIrData LatestData(int dtuId, int lmuId) {
		List<CurrentIrData> list = baseDao.findAll(
				"from CurrentIrData where dtuId='" + dtuId + "' and lmuId='"
						+ lmuId + "' order by addtime desc",
				CurrentIrData.class);
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public List<CurrentIrData> HisIrListByPage(String hql, int firstResult,
			int maxResult) {
		List<CurrentIrData> list = baseDao.findByPage(hql, CurrentIrData.class,
				firstResult, maxResult);
		return list;
	}

	@Override
	public int count(String hql) {
		int count = 0;
		List<Integer> list = baseDao.findAll(hql, Integer.class);
		if (list != null) {
			Number num = (Number) list.get(0);
			count = num.intValue();
			System.out.println(count);
		}
		return count;
	}

	@Override
	public List<CurrentIrData> LastestDataList(int dtuId) {
		List<CurrentIrData> list = baseDao.findAll(
				"from CurrentIrData where dtuId='" + dtuId
						+ "' order by addtime desc", CurrentIrData.class);
		return list;
	}

	@Override
	public List<CurrentIrData> LastestDataList(String lmuIds) {
		List<CurrentIrData> list = baseDao.findAll(
				"from CurrentIrData where lmuId in (" + lmuIds
						+ ") order by addtime desc", CurrentIrData.class);
		return list;
	}

	@Override
	public List<CurrentIrData> LastestDataList(Integer[] dtuIds) {
		//String hql="select id,dtuId,lmuId,max(addtime),ir0,ir1,ir2,ir3,ir4,ir5,ir6,ir7,ir8,ir9,ir10,ir11 from CurrentIrData where dtuId in (:DtuIds) group by lmuId";
		//String hql = "select max(addtime),id from CurrentIrData where dtuId in(:DtuIds)";
		//String hql = "SELECT * FROM (SELECT * FROM (SELECT * FROM currentirdata ORDER BY Addtime DESC) AS c GROUP BY c.dtuId) AS cc WHERE cc.dtuid in (:DtuIds)";
		//return baseDao.findAll(hql, CurrentIrData.class,dtuIds,"DtuIds");
		return baseDao.findByProc("CurrIrData", CurrentIrData.class);
	}

	@Override
	public void InsertCurrentIrData(CurrentIrData currentIrData) {
		baseDao.save(currentIrData);
	}

}
