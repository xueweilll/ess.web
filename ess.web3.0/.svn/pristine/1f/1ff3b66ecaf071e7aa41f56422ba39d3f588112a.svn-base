package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.SysLog;
import com.service.SysLogService;

@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
	
	@Resource
	private BaseDao baseDao;
	/*public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}*/

	@Override
	public void saveSysLog(SysLog sysLog) {
		baseDao.save(sysLog);
	}
	@Override
	public List<SysLog> SysLoglist(String hql, int firstResult,
			int maxResult) {
		/*String condition = "";
		if(log !="" && log != null) {
			condition = condition+" and log like '%" + log + "%'";
		}
		if(addTime !="" && addTime != null) {
			condition = condition+" and addtime like '%" + addTime + "%'";
		}
		String hql="from SysLog where 1=1 " + condition;*/
		
		List<SysLog> list = baseDao.findByPage(hql, SysLog.class, firstResult, maxResult);
		return list;
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

}
