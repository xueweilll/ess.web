package com.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.DTU;
import com.hibernate.entity.LMU;
import com.hibernate.entity.PartSensor;
import com.service.DtuService;
import com.service.LmuLimitService;
import com.service.LmuService;
import com.unit.LmuMsgQuence;
import com.unit.MessageQueue;
import com.unit.PartsQuence;

@Service("dtuService")
public class DtuServiceImpl implements DtuService {

	@Autowired
	private BaseDao baseDao;
	@Autowired
	private LmuService lmuService;
	@Autowired
	private LmuLimitService lmuLimitService;
	@Autowired
	private MessageQueue messageQueue;
	@Autowired
	private PartsQuence partsQuence;
	@Autowired
	private LmuMsgQuence lmuMsgQuence;

/*	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}*/

	@Override
	public List<DTU> DTUlist() {
		return baseDao.findAll("from DTU", DTU.class);
	}

	@Override
	public DTU dtu(String code) {
		//return baseDao.get(DTU.class, code);
		return baseDao.findAll("from DTU where code ="+code, DTU.class).get(0);
	}

	@Override
	public void saveDTU(DTU dtu) {
		baseDao.save(dtu);
	}

	@Override
	public void updateDTU(DTU dtu) {
		baseDao.update(dtu);
	}

	@Override
	public void deleteDTU(int id) {
		baseDao.delete(DTU.class, id);
	}

	@Override
	public List<DTU> DTUlistByStationId(int stationId) {
		return baseDao.findAll("from DTU where stationId='" + stationId + "'",
				DTU.class);
	}

	@Override
	public List<DTU> DTUlistByStationIds(String stationIds) {
		return baseDao.findAll("from DTU where stationId in(" + stationIds
				+ ")", DTU.class);
	}

	@Override
	public DTU dtu(int id) {
		return baseDao.get(DTU.class, id);
	}

	@Override
	public void SaveDTUAndLMUS(DTU dtu, List<LMU> listLmu) {
		baseDao.save(dtu);
		for (LMU lmu : listLmu) {
			lmu.setDtuId(dtu.getId());
			lmu.setDtu(dtu);
			baseDao.save(lmu);
		}
	}

	@Override
	public void UpdateDTUAndLMUS(DTU dtu, List<LMU> listLmu) {
		//更新站点
		baseDao.update(dtu);
		//更新messagequence中的信息
		for(Map.Entry<DTU, PartSensor> e: messageQueue.getCacheData().entrySet() ){
			if(e.getKey().getId() == dtu.getId()){
				e.getKey().setIP(dtu.getIP());
				e.getKey().setName(dtu.getName());
				e.getValue().setDuoName(dtu.getName());
				break;
			}
		}
		//更新partsquence，lmumsgquence
		for (LMU lmu : listLmu) {
			if(lmu.getId() == 0){
				lmu.setDtuId(dtu.getId());
				lmu.setDtu(dtu);
				baseDao.save(lmu);
				for(Map.Entry<DTU, PartSensor> e: messageQueue.getCacheData().entrySet() ){
					if(e.getKey().getId() == lmu.getDtuId()){
						Set<LMU> set = e.getKey().getLmus();
						set.add(lmu);
						break;
					}
				}
			}else{
				//更新设备
				baseDao.update(lmu);
				for(Map.Entry<DTU, PartSensor> e: messageQueue.getCacheData().entrySet() ){
					if(e.getKey().getId() == lmu.getDtuId()){
						Set<LMU> set = e.getKey().getLmus();
						for (LMU lmu2 : set) {
							if(lmu2.getId() == lmu.getId()){
								lmu2.setCode(lmu.getCode());
								lmu2.setType(lmu.getType());
								break;
							}
						}
					}
				}
			}
		}
		List<LMU> LIST = lmuService.LMUlist(dtu.getId());
		for (LMU lmu : LIST) {
			 boolean flag = true;
			for (LMU lmu1 : listLmu) {
				if(lmu.getId() == lmu1.getId()){
					flag = false;
				}
			}
			if(flag){
				lmuService.deleteLMU(lmu.getId());
				lmuLimitService.deleteLmuLimit(lmu.getId());
				partsQuence.remove(lmu.getId());
				lmuMsgQuence.remove(lmu.getId());
				/*for(Map.Entry<DTU, PartSensor> e: messageQueue.getCacheData().entrySet() ){
					if(e.getKey().getId() == dtu.getId()){
						if(lmu.get){
							
						}
					}
				}*/
			}
		}
	}

	@Override
	public void DeleteDTUAndLMUS(int dtuId) {
		baseDao.delete("delete from AlarmMsg a where a.DtuId=" +dtuId);
		baseDao.delete(DTU.class, dtuId);
		baseDao.delete("delete from LMU l where l.dtuId=" + dtuId);
	}

	@Override
	public int findIndexCode(String id) {
		List<DTU> dtu=baseDao.findAll("from DTU where indexCode ='" + id + "'", DTU.class);
		if(dtu.size()==0){
		return -1;	
		}else {
		return dtu.get(0).getId();
		}
	}
}
