package com.hibernate.entity;

import java.util.Date;

public class Register {
	private Date RegisterDate;
	private String RegisterCode;

	public Date getRegisterDate() {
		return RegisterDate;
	}

	public void setRegisterDate(Date registerDate) {
		RegisterDate = registerDate;
	}

	public String getRegisterCode() {
		return RegisterCode;
	}

	public void setRegisterCode(String registerCode) {
		RegisterCode = registerCode;
	}

}
