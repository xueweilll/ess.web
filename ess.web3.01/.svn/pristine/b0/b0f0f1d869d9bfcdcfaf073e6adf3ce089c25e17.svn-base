package com.protocol;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import com.message.ResMgr;

public abstract class Analysis {
	protected byte[] buff;
	protected int dtuId;
	protected int lmuId;
	public int getDtuId() {
		return dtuId;
	}

	public void setDtuId(int dtuId) {
		this.dtuId = dtuId;
	}

	protected Calendar cal;
	public Analysis(byte[] buff,int dtuId) {
		this.setBuff(buff);
		this.setDtuId(dtuId);
		cal = Calendar.getInstance();
		cal.setTime(new Date());
	}

	public abstract void registers() throws Exception;

	public byte[] getBuff() {
		return buff;
	}

	public void setBuff(byte[] buff) {
		this.buff = buff;
	}
	
	protected static DecimalFormat df = new DecimalFormat("0.00");

	// 格式化调压时间
	protected String FormatT(int time) {
		String strT = String.valueOf(time);
		if (strT.length() == 1) {
			strT = "0" + strT + "00";
		} else if (strT.length() == 3) {
			strT = "0" + strT;
		}
		return strT;
	}

	private String expStr;

	protected String ExceptionCodes(int ExceptionCodes) throws Exception {
		// System.out.println(ExceptionCodes);
		switch (ExceptionCodes) {
		case 1:
			expStr = ResMgr.CreateResMgr().GetStr(
					"LMU_STATUS_EXCEPTION_OVER_CURRENT");
			break;// 过流
		case 2:
			expStr = ResMgr.CreateResMgr().GetStr(
					"LMU_STATUS_EXCEPTION_UNDER_CURRENT");
			break;// 欠流
		case 3:
			expStr = ResMgr.CreateResMgr().GetStr(
					"LMU_STATUS_EXCEPTION_OVER_VOLTAGE");
			break;// 过压
		case 4:
			expStr = ResMgr.CreateResMgr().GetStr(
					"LMU_STATUS_EXCEPTION_UNDER_VOLTAGE");
			break;// 欠压
		case 5:
			expStr = ResMgr.CreateResMgr().GetStr(
					"LMU_STATUS_EXCEPTION_TEMPERATURE");
			break;// 温度异常
		case 6:
			expStr = ResMgr.CreateResMgr().GetStr("LMU_STATUS_EXCEPTION_I2C");
			break;// I2C异常
		case 7:
			expStr = ResMgr.CreateResMgr().GetStr(
					"LMU_STATUS_EXCEPTION_DS18B20");
			break;// DS18B20异常
		case 8:
			expStr = ResMgr.CreateResMgr().GetStr(
					"LMU_STATUS_EXCEPTION_IGBT_UP");
			break;// 上管异常
		case 9:
			expStr = ResMgr.CreateResMgr().GetStr(
					"LMU_STATUS_EXCEPTION_IGBT_DOWN");
			break;// 下管异常
		default:
			expStr = "Null";
			ExceptionCodes = 0;
			break;
		}
		// InputRegisters[8] = String.valueOf(ExceptionCodes);
		return expStr;
	}
}
