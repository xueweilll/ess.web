package com.hibernate.entity;

import java.math.BigDecimal;

public class LMU {
	private int id;
	private int dtuId;
	private String name;
	private String address;
	private String IP;
	private int code;
	private DTU dtu;
	public DTU getDtu() {
		return dtu;
	}

	public void setDtu(DTU dtu) {
		this.dtu = dtu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDtuId() {
		return dtuId;
	}

	public void setDtuId(int dtuId) {
		this.dtuId = dtuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}





}
