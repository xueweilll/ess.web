package com.hibernate.entity;

import java.util.Date;

public class SysLog {
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
