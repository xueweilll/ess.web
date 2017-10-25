package com.hibernate.entity;

import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 2555270242386221852L;
	private int Id;
	private Date Addtime;
	private int LogType;
	private String Log;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Date getAddtime() {
		return Addtime;
	}

	public void setAddtime(Date addtime) {
		Addtime = addtime;
	}

	public int getLogType() {
		return LogType;
	}

	public void setLogType(int logType) {
		LogType = logType;
	}

	public String getLog() {
		return Log;
	}

	public void setLog(String log) {
		Log = log;
	}
}
