package com.hibernate.entity;

import java.math.BigDecimal;
import java.util.Calendar;

public class PartSensor {
	private int dtuId;
	//站点名称
	private String duoName;
	//氨气
	private BigDecimal v1;
	//温度
	private BigDecimal v2;
	//湿度
	private BigDecimal v3;
	//市电1路
	private BigDecimal v4;
	//市电2路
	private BigDecimal v5;
	//市电3路
	private BigDecimal v6;
	//市电4路
	private BigDecimal v7;
	//市电5路
	private BigDecimal v8;
	//市电6路
	private BigDecimal v9;
	//市电7路
	private BigDecimal v10;
	//市电8路
	private BigDecimal v11;
	
	private int wdPlice;
	
	private int sdPlice;
	
	private int aqPlice;
	
	private String wdStartWarnTime;
	
	private String aqStartWarnTime;
	
	private String sdStartWarnTime;

	private Calendar addtime;
	public String getDuoName() {
		return duoName;
	}
	public void setDuoName(String duoName) {
		this.duoName = duoName;
	}
	public BigDecimal getV1() {
		return v1;
	}
	public void setV1(BigDecimal v1) {
		this.v1 = v1;
	}
	public BigDecimal getV2() {
		return v2;
	}
	public void setV2(BigDecimal v2) {
		this.v2 = v2;
	}
	public BigDecimal getV3() {
		return v3;
	}
	public void setV3(BigDecimal v3) {
		this.v3 = v3;
	}
	public BigDecimal getV4() {
		return v4;
	}
	public void setV4(BigDecimal v4) {
		this.v4 = v4;
	}
	public Calendar getAddtime() {
		return addtime;
	}
	public void setAddtime(Calendar addtime) {
		this.addtime = addtime;
	}
	public BigDecimal getV5() {
		return v5;
	}
	public void setV5(BigDecimal v5) {
		this.v5 = v5;
	}
	public BigDecimal getV6() {
		return v6;
	}
	public void setV6(BigDecimal v6) {
		this.v6 = v6;
	}
	public BigDecimal getV7() {
		return v7;
	}
	public void setV7(BigDecimal v7) {
		this.v7 = v7;
	}
	public BigDecimal getV8() {
		return v8;
	}
	public void setV8(BigDecimal v8) {
		this.v8 = v8;
	}
	public BigDecimal getV9() {
		return v9;
	}
	public void setV9(BigDecimal v9) {
		this.v9 = v9;
	}
	public BigDecimal getV10() {
		return v10;
	}
	public void setV10(BigDecimal v10) {
		this.v10 = v10;
	}
	public BigDecimal getV11() {
		return v11;
	}
	public void setV11(BigDecimal v11) {
		this.v11 = v11;
	}
	public int getWdPlice() {
		return wdPlice;
	}
	public void setWdPlice(int wdPlice) {
		this.wdPlice = wdPlice;
	}
	public int getSdPlice() {
		return sdPlice;
	}
	public void setSdPlice(int sdPlice) {
		this.sdPlice = sdPlice;
	}
	public int getAqPlice() {
		return aqPlice;
	}
	public void setAqPlice(int aqPlice) {
		this.aqPlice = aqPlice;
	}
	public int getDtuId() {
		return dtuId;
	}
	public void setDtuId(int dtuId) {
		this.dtuId = dtuId;
	}
	public String getWdStartWarnTime() {
		return wdStartWarnTime;
	}
	public void setWdStartWarnTime(String wdStartWarnTime) {
		this.wdStartWarnTime = wdStartWarnTime;
	}
	public String getAqStartWarnTime() {
		return aqStartWarnTime;
	}
	public void setAqStartWarnTime(String aqStartWarnTime) {
		this.aqStartWarnTime = aqStartWarnTime;
	}
	public String getSdStartWarnTime() {
		return sdStartWarnTime;
	}
	public void setSdStartWarnTime(String sdStartWarnTime) {
		this.sdStartWarnTime = sdStartWarnTime;
	}
	
}