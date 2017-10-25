package com.hibernate.entity;

import java.util.Calendar;

public class Instrument {
	private String Id;
	private int dtuId;
	private int lmuId;
	private String I;
	private String U;
	private String P;
	private String Pf;
	private Calendar addTime;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public int getDtuId() {
		return dtuId;
	}
	public void setDtuId(int dtuId) {
		this.dtuId = dtuId;
	}
	public int getLmuId() {
		return lmuId;
	}
	public void setLmuId(int lmuId) {
		this.lmuId = lmuId;
	}
	public String getI() {
		return I;
	}
	public void setI(String i) {
		I = i;
	}
	public String getU() {
		return U;
	}
	public void setU(String u) {
		U = u;
	}
	public String getP() {
		return P;
	}
	public void setP(String p) {
		P = p;
	}
	public String getPf() {
		return Pf;
	}
	public void setPf(String pf) {
		Pf = pf;
	}
	public Calendar getAddTime() {
		return addTime;
	}
	public void setAddTime(Calendar addTime) {
		this.addTime = addTime;
	}
	
}
