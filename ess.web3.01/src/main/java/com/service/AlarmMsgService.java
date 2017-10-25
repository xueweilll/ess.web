package com.service;

import java.util.List;

import com.hibernate.entity.AlarmMsg;

public interface AlarmMsgService {
	public List<AlarmMsg> listAlarmMsg(int firstResult, int maxResult,String Where);
	
	public void saveAlarmMsg(AlarmMsg alarmMsg);
	
	public List<AlarmMsg> getAlarmMsg(String hql,Object[] obj);
	
}