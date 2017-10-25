package com.hibernate.entity;

import java.io.Serializable;
import java.util.Calendar;

public class TestSend implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -3740609274828147630L;
	private int Id;
	private int dtuId;
	private String data;
	private Calendar addtime;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getDtuId() {
		return dtuId;
	}

	public void setDtuId(int dtuId) {
		this.dtuId = dtuId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Calendar getAddtime() {
		return addtime;
	}

	public void setAddtime(Calendar addtime) {
		this.addtime = addtime;
	}

}
