package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.AlarmMsg;
import com.service.AlarmMsgService;

@Service
public class AlarmMsgServiceImpl implements AlarmMsgService {

	@Autowired
	private BaseDao baseDao;
/*	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}*/
	@Override
	public List<AlarmMsg> listAlarmMsg(int firstResult, int maxResult,
			String Where) {
		return baseDao.findByPage(Where, AlarmMsg.class, firstResult, maxResult);
	}
	@Override
	public void saveAlarmMsg(AlarmMsg alarmMsg) {
		// TODO Auto-generated method stub
		 baseDao.save(alarmMsg);
	}
	@Override
	public List<AlarmMsg> getAlarmMsg(String hql, Object[] obj) {
		return baseDao.findAll(hql, AlarmMsg.class, obj);
	}

}
