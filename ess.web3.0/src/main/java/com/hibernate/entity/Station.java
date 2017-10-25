package com.hibernate.entity;

import java.io.Serializable;

public class Station implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 467005247765560643L;
	private int id;
	private String stationname;
	private String stationaddress;
	private String stationmap;
	private double stationx;
	private double stationy;
	private int pareid;
	private int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Station() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStationname() {
		return stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	public String getStationaddress() {
		return stationaddress;
	}

	public void setStationaddress(String stationaddress) {
		this.stationaddress = stationaddress;
	}

	public String getStationmap() {
		return stationmap;
	}

	public void setStationmap(String stationmap) {
		this.stationmap = stationmap;
	}

	public double getStationx() {
		return stationx;
	}

	public void setStationx(double stationx) {
		this.stationx = stationx;
	}

	public double getStationy() {
		return stationy;
	}

	public void setStationy(double stationy) {
		this.stationy = stationy;
	}

	public int getPareid() {
		return pareid;
	}

	public void setPareid(int pareid) {
		this.pareid = pareid;
	}

}
