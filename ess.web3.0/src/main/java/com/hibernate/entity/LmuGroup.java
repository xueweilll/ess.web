package com.hibernate.entity;

import java.io.Serializable;

public class LmuGroup implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -9217403673560400631L;
	private int Id;
	private String GroupName;
	private int PareId;
	private int Mid;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupName) {
		GroupName = groupName;
	}

	public int getPareId() {
		return PareId;
	}

	public void setPareId(int pareId) {
		PareId = pareId;
	}

	public int getMid() {
		return Mid;
	}

	public void setMid(int mid) {
		Mid = mid;
	}

}
