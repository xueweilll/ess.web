package com.hibernate.entity;

import java.io.Serializable;
import java.util.Calendar;

public class AlarmMsg  implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 2964659156354200046L;
	private int Id;
	private String DtuId;
	private int LmuId;
	private Calendar Addtime;
	private String Infomation;
	private int Confirm;
	private int Type;
	private int Status;
	private Calendar Endtime;
	private double value;
	public int getConfirm() {
		return Confirm;
	}

	public void setConfirm(int confirm) {
		Confirm = confirm;
	}

	private DTU dtu;
	

	public DTU getDtu() {
		return dtu;
	}

	public void setDtu(DTU dtu) {
		this.dtu = dtu;
	}



	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getDtuId() {
		return DtuId;
	}

	public void setDtuId(String dtuId) {
		DtuId = dtuId;
	}

	public int getLmuId() {
		return LmuId;
	}

	public void setLmuId(int lmuId) {
		LmuId = lmuId;
	}

	public Calendar getAddtime() {
		return Addtime;
	}

	public void setAddtime(Calendar addtime) {
		Addtime = addtime;
	}

	public String getInfomation() {
		return Infomation;
	}

	public void setInfomation(String infomation) {
		Infomation = infomation;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public Calendar getEndtime() {
		return Endtime;
	}

	public void setEndtime(Calendar endtime) {
		Endtime = endtime;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
}
