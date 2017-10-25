package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.AlarmMsg;
import com.service.AlarmMsgService;

@Service("alarmMsgService")
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
	
	@Override
	public int count(String hql) {
		int count = 0;
		List<Integer> list = baseDao.findAll(hql, Integer.class);
		if(list!=null){
			Number num = (Number)list.get(0);
			count = num.intValue();
			//System.out.println(count);
		}
		return count;
	}
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public String updateAlarm(AlarmMsg alarmMsg) {
		String result="";
		String listString="from AlarmMsg where 1=1 and id="+alarmMsg.getId();
		List<Integer> list = baseDao.findAll(listString, Integer.class);
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    if (list.size()!=0) {
			String hql="update alarmmsg set status=1,endtime='"+simpleDateFormat.format(date)+"' where 1=1 and id="+alarmMsg.getId();
		    Query query = getSession().createSQLQuery(hql);
			query.executeUpdate();
			result="success";
		}else{
			result="error";
		}
	    return result;
	}
	@Override
	public void deleteByDutid(int dtuid) {
		baseDao.delete("delete from AlarmMsg a where a.DtuId=" + dtuid);
	}

	@Override
	public AlarmMsg getByid(int id) {
		return baseDao.get(AlarmMsg.class, id);
	}

}
