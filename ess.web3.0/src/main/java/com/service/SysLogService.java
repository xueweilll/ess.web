package com.service;

import java.util.List;

import com.hibernate.entity.SysLog;

public interface SysLogService {
//	public List<SysLog> SysLoglist(String Condition);
	public int count(String hql);
	public List<SysLog> SysLoglist(String hql, int firstResult,
			int maxResult);
 	public void saveSysLog(SysLog sysLog);
}
