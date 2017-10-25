package com.action;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.service.SysLogService;
import com.common.param.paramers;
import com.google.gson.Gson;
import com.hibernate.entity.SysLog;
import com.hibernate.entity.User;

public class ActionBase extends ActionSupport implements RequestAware,
		SessionAware, ApplicationAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;
	@Autowired
	protected SysLogService sysLogService;
	protected Gson gson = new Gson();

	/*
	 * public void setSysLogService(SysLogService sysLogService) {
	 * this.sysLogService = sysLogService; }
	 */

	public ActionBase() {
		paramers.create();
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, Object> getApplication() {
		return application;
	}

	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public void SaveSysLog(int logType, String log) {
		SysLog sysLog = new SysLog();
		sysLog.setAddtime(new Date());
		sysLog.setLogType(logType);
		sysLog.setLog(log);
		try {
			sysLogService.saveSysLog(sysLog);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void SaveSysLogByUser(int logType, String log) {
		SysLog sysLog = new SysLog();
		sysLog.setAddtime(new Date());
		sysLog.setLogType(logType);
		log = ((User) session.get("user")).getUsername() + ":" + log;
		sysLog.setLog(log);
		try {
			sysLogService.saveSysLog(sysLog);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
