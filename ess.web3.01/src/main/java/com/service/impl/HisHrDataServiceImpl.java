package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.HisHrData;
import com.service.HisHrDataService;

@Service("hisHrDataService")
public class HisHrDataServiceImpl implements HisHrDataService {

	@Autowired
	private BaseDao baseDao;
/*	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}*/
	
	@Override
	//��lmuIdΪLmuCode
	public HisHrData LatestData(String dtuId, int lmuId) {
		//System.out.println("________________________________________________");
		List<HisHrData> list=baseDao.findAll("from HisHrData where dtuId='"+dtuId+"' and lmuId='"+lmuId+"' order by addtime desc", HisHrData.class);
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	public List<HisHrData> hisHrDataList(Integer[] dtuIds) {
		/*String hql = "from HisHrData where dtuId in (:DtuIds)";
		return baseDao.findAll(hql, HisHrData.class,dtuIds,"DtuIds");*/
		String hql = "from HisHrData";
		return baseDao.findAll(hql, HisHrData.class);
	}

	@Override
	public void InsertHisHrdata(HisHrData hisHrData) {
		baseDao.delete("delete HisHrData where dtuId='"+hisHrData.getDtuId()+"' and lmuId='"+hisHrData.getLmuId()+"'");
		baseDao.save(hisHrData);
	}

}
