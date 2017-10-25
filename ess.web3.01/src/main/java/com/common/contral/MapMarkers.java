package com.common.contral;

public class MapMarkers {
	private String id;
	private String title;
	private double lat;
	private double lon;
	private boolean line;
//	private String listeners;

	public String getId() {
		return id;
	}

	public boolean getLine() {
		return line;
	}

	public void setLine(boolean line) {
		this.line = line;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	/*public String getListeners() {
		this.listeners = "click:function(e){ShowDetial(this.id)}";
		return listeners;
	}

	public void setListeners(String listeners) {
		this.listeners = listeners;
	}*/
}
