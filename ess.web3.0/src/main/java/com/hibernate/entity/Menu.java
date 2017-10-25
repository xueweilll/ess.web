package com.hibernate.entity;

import java.io.Serializable;

public class Menu  implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -6192317192040479254L;
	private int id;
	private String menuname;
	private String url;
	private int pareid;
	private boolean isshow;

	public boolean getIsshow() {
		return isshow;
	}

	public void setIsshow(boolean isshow) {
		this.isshow = isshow;
	}

	public int getPareid() {
		return pareid;
	}

	public void setPareid(int pareId) {
		this.pareid = pareId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Menu() {

	}
}
