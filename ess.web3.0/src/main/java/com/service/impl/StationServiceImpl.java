package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.Station;
import com.service.StationService;

@Service("stationService")
public class StationServiceImpl implements StationService {

	@Autowired
	private BaseDao baseDao;
	
	/*public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
*/
	@Override
	public void savestation(Station station) {
		baseDao.save(station);
	}

	@Override
	public void updatestation(Station station) {
		baseDao.update(station);
	}

	@Override
	public void deletestation(int id) {
		baseDao.delete(Station.class, id);
	}

	//public <T> T get(Class<T> entityClass,Integer id);
	@Override
	public Station station(int Id) {
		//System.out.println(Id);
		Station station = baseDao.get(Station.class,Id);
		//System.out.println(station.getStationname());
		return station;
	}

	@Override
	public List<Station> stationlist() {
		List<Station> liststation = baseDao.findAll("from Station",Station.class);
		return liststation;
	}

	@Override
	public Station stationByPareId(int pareId) {
		Station station = baseDao.findAll("from Station where pareid='"+pareId+"'", Station.class).get(0);
		return station;
	}

	@Override
	public List<Station> stationlistByPareId(int pareId) {
		List<Station> liststation = baseDao.findAll("from Station where pareid='"+pareId+"'",Station.class);
		return liststation;
	}

}
