package com.hibernate.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Lmulimit implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 8945580636237702131L;
	private int id;
	private int lmuId;
	private BigDecimal a1;
	private BigDecimal a2;
	private BigDecimal w1;
	private BigDecimal w2;
	private BigDecimal s1;
	private BigDecimal s2;
	private BigDecimal sd1;
	private BigDecimal sd2;
	private BigDecimal sd3;
	private BigDecimal sd4;
	private BigDecimal sd5;
	private BigDecimal sd6;
	private BigDecimal sd7;
	private BigDecimal sd8;
	private BigDecimal wrate;
	private BigDecimal Srate;
	private BigDecimal arate;
	private int type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLmuId() {
		return lmuId;
	}
	public void setLmuId(int lmuId) {
		this.lmuId = lmuId;
	}
	public BigDecimal getA1() {
		return a1;
	}
	public void setA1(BigDecimal a1) {
		this.a1 = a1;
	}
	public BigDecimal getA2() {
		return a2;
	}
	public void setA2(BigDecimal a2) {
		this.a2 = a2;
	}
	public BigDecimal getW1() {
		return w1;
	}
	public void setW1(BigDecimal w1) {
		this.w1 = w1;
	}
	public BigDecimal getW2() {
		return w2;
	}
	public void setW2(BigDecimal w2) {
		this.w2 = w2;
	}
	public BigDecimal getS1() {
		return s1;
	}
	public void setS1(BigDecimal s1) {
		this.s1 = s1;
	}
	public BigDecimal getS2() {
		return s2;
	}
	public void setS2(BigDecimal s2) {
		this.s2 = s2;
	}
	public BigDecimal getSd1() {
		return sd1;
	}
	public void setSd1(BigDecimal sd1) {
		this.sd1 = sd1;
	}
	public BigDecimal getSd2() {
		return sd2;
	}
	public void setSd2(BigDecimal sd2) {
		this.sd2 = sd2;
	}
	public BigDecimal getSd3() {
		return sd3;
	}
	public void setSd3(BigDecimal sd3) {
		this.sd3 = sd3;
	}
	public BigDecimal getSd4() {
		return sd4;
	}
	public void setSd4(BigDecimal sd4) {
		this.sd4 = sd4;
	}
	public BigDecimal getSd5() {
		return sd5;
	}
	public void setSd5(BigDecimal sd5) {
		this.sd5 = sd5;
	}
	public BigDecimal getSd6() {
		return sd6;
	}
	public void setSd6(BigDecimal sd6) {
		this.sd6 = sd6;
	}
	public BigDecimal getSd7() {
		return sd7;
	}
	public void setSd7(BigDecimal sd7) {
		this.sd7 = sd7;
	}
	public BigDecimal getSd8() {
		return sd8;
	}
	public void setSd8(BigDecimal sd8) {
		this.sd8 = sd8;
	}
	public BigDecimal getWrate() {
		return wrate;
	}
	public void setWrate(BigDecimal wrate) {
		this.wrate = wrate;
	}
	public BigDecimal getSrate() {
		return Srate;
	}
	public void setSrate(BigDecimal srate) {
		Srate = srate;
	}
	public BigDecimal getArate() {
		return arate;
	}
	public void setArate(BigDecimal arate) {
		this.arate = arate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
