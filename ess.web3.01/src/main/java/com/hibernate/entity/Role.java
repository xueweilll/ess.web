package com.hibernate.entity;

public class Role {
	private int Id;
	private String RoleName;
	private int PareId;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getRoleName() {
		return RoleName;
	}

	public void setRoleName(String roleName) {
		RoleName = roleName;
	}

	public int getPareId() {
		return PareId;
	}

	public void setPareId(int pareId) {
		PareId = pareId;
	}
}
