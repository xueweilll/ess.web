package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.Sensor;
import com.service.SensorService;

@Service("sensorService")
public class SensorServiceImpl implements SensorService {

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public void insert(Sensor sensor) {
		baseDao.save(sensor);
	}

	@Override
	public int count(String hql) {
		int count = 0;
		List<Integer> list = baseDao.findAll(hql, Integer.class);
		if(list!=null){
			Number num = (Number)list.get(0);
			count = num.intValue();
			System.out.println(count);
		}
		return count;
	}

	@Override
	public List<Sensor> SensorListByPage(String hql, int firstResult, int maxResult) {
		List<Sensor> list = baseDao.findByPage(hql, Sensor.class, firstResult, maxResult);
		return list;
	}

}
