package com.hibernate.entity;

public class LmuGroup {
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
