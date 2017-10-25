package com.hibernate.entity;

import java.util.Calendar;

public class AlarmDetials {
	private int id;
	private String alarmId;
	private String hisirdataId;
	private Calendar addtime;
	private String information;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public String getHisirdataId() {
		return hisirdataId;
	}

	public void setHisirdataId(String hisirdataId) {
		this.hisirdataId = hisirdataId;
	}

	public Calendar getAddtime() {
		return addtime;
	}

	public void setAddtime(Calendar addtime) {
		this.addtime = addtime;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

}
