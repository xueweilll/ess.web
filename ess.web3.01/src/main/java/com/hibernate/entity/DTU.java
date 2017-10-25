package com.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

public class DTU {
	private int id;
	private String code;
	private String name;
	private String address;
	private String IP;
	private int port;
	private int stationId;
	private boolean isActive;
	private double dtuX;
	private double dtuY;
	private int level;
	private boolean isAlarm;
	private boolean isTogether;
	private int eqNum;
	private String phone;
	public boolean getIsAlarm() {
		return isAlarm;
	}

	public void setIsAlarm(boolean isAlarm) {
		this.isAlarm = isAlarm;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public double getDtuX() {
		return dtuX;
	}

	public void setDtuX(double dtuX) {
		this.dtuX = dtuX;
	}

	public double getDtuY() {
		return dtuY;
	}

	public void setDtuY(double dtuY) {
		this.dtuY = dtuY;
	}

	private Set<LMU> lmus = new HashSet<LMU>(0);

	public Set<LMU> getLmus() {
		return lmus;
	}

	public void setLmus(Set<LMU> lmus) {
		this.lmus = lmus;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean getIsTogether() {
		return isTogether;
	}

	public void setIsTogether(boolean isTogether) {
		this.isTogether = isTogether;
	}

	public int getEqNum() {
		return eqNum;
	}

	public void setEqNum(int eqNum) {
		this.eqNum = eqNum;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setAlarm(boolean isAlarm) {
		this.isAlarm = isAlarm;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}