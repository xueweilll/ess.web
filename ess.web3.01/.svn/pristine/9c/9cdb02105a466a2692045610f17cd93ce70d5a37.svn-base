package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.DTU;
import com.hibernate.entity.LMU;
import com.service.DtuService;

@Service("dtuService")
public class DtuServiceImpl implements DtuService {

	@Autowired
	private BaseDao baseDao;

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
		baseDao.update(dtu);
		baseDao.delete("delete from LMU l where l.dtuId=" + dtu.getId());
		for (LMU lmu : listLmu) {
			lmu.setDtuId(dtu.getId());
			lmu.setDtu(dtu);
			baseDao.save(lmu);
		}
	}

	@Override
	public void DeleteDTUAndLMUS(int dtuId) {
		baseDao.delete(DTU.class, dtuId);
		baseDao.delete("delete from LMU l where l.dtuId=" + dtuId);
	}
}
