package com.hibernate.entity;

import java.util.Calendar;

public class HisIrData {
	private String id;
	private String dtuId;
	private int lmuId;
	private Calendar addtime;
	private String ir0;
	private String ir1;
	private String ir2;
	private String ir3;
	private String ir4;
	private String ir5;
	private String ir6;
	private String ir7;
	private String ir8;
	private String ir9;
	private String ir10;
	private String ir11;
	private LMU lmu;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDtuId() {
		return dtuId;
	}

	public void setDtuId(String dtuId) {
		this.dtuId = dtuId;
	}

	public int getLmuId() {
		return lmuId;
	}

	public void setLmuId(int lmuId) {
		this.lmuId = lmuId;
	}

	public Calendar getAddtime() {
		return addtime;
	}

	public void setAddtime(Calendar addtime) {
		this.addtime = addtime;
	}

	public String getIr0() {
		return ir0;
	}

	public void setIr0(String ir0) {
		this.ir0 = ir0;
	}

	public String getIr1() {
		return ir1;
	}

	public void setIr1(String ir1) {
		this.ir1 = ir1;
	}

	public String getIr2() {
		return ir2;
	}

	public void setIr2(String ir2) {
		this.ir2 = ir2;
	}

	public String getIr3() {
		return ir3;
	}

	public void setIr3(String ir3) {
		this.ir3 = ir3;
	}

	public String getIr4() {
		return ir4;
	}

	public void setIr4(String ir4) {
		this.ir4 = ir4;
	}

	public String getIr5() {
		return ir5;
	}

	public void setIr5(String ir5) {
		this.ir5 = ir5;
	}

	public String getIr6() {
		return ir6;
	}

	public void setIr6(String ir6) {
		this.ir6 = ir6;
	}

	public String getIr7() {
		return ir7;
	}

	public void setIr7(String ir7) {
		this.ir7 = ir7;
	}

	public String getIr8() {
		return ir8;
	}

	public void setIr8(String ir8) {
		this.ir8 = ir8;
	}

	public String getIr9() {
		return ir9;
	}

	public void setIr9(String ir9) {
		this.ir9 = ir9;
	}

	public String getIr10() {
		return ir10;
	}

	public void setIr10(String ir10) {
		this.ir10 = ir10;
	}

	public String getIr11() {
		return ir11;
	}

	public void setIr11(String ir11) {
		this.ir11 = ir11;
	}

	public LMU getLmu() {
		return lmu;
	}

	public void setLmu(LMU lmu) {
		this.lmu = lmu;
	}

	public String getName() {
		return lmu.getDtu().getName() + "-" + lmu.getName();
	}

}
