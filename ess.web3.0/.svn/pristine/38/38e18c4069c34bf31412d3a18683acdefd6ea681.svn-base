package com.action.analysis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.hibernate.entity.SysLog;
import com.service.SysLogService;

public class SyslogAction extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7745661588679489015L;
	
	public String syslog() {
		return "success";
	}
	
	@Autowired
	private SysLogService sysLogService;


	/*public void setSysLogService(SysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}*/

	private List<SysLog> arrLog;
	
	public List<SysLog> getArrLog() {
		return arrLog;
	}

	public void setArrLog(List<SysLog> arrLog) {
		this.arrLog = arrLog;
	}
	
	private String operUser;
	private int logType;
	private String addtime;

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public void setLogType(int logType) {
		this.logType = logType;
	}

	public void setOperUser(String operUser) {
		this.operUser = operUser;
	}

	public String getOperUser() {
		return operUser;
	}
	private int totalCount;
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	private int start;
	private int limit;
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String LogGrid(){
		String condition = "";
		if (operUser != "" && operUser != null) {
			condition = condition + " and log like '%" + operUser + "%'";
		}
		if(logType!=0){
			condition = condition+" and LogType ="+logType;
		}
		if (addtime != "" && addtime != null) {
			condition = condition + " and addtime like '%" + addtime + "%'";
		}
		String hql = "from SysLog where 1=1 " + condition;
		int firstResult = start;
		int maxResult = limit;
		totalCount = 0;
		try {
			totalCount = sysLogService.count("select count(*) " + hql);
		} catch (Exception ex) {
			ex.printStackTrace();
			totalCount = 0;
		}
		/*
		if (count == 0) {
			return "";
		}

		if (count % pagesize > 0) {
			pageCount = count / pagesize + 1;
		} else {
			pageCount = count / pagesize;
		}
		firstResult = (currentPage - 1) * pagesize;
		maxResult = pagesize;*/
		hql+=" order by Addtime desc";
		arrLog = sysLogService.SysLoglist(hql, firstResult,
				maxResult);
		return "success";
	}
}
