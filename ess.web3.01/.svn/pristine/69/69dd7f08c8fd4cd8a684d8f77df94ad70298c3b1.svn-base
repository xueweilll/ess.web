package com.action.analysis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.common.Interceptor.HibernateInterceptor;
//import com.hibernate.entity.HisIrData;
import com.hibernate.entity.Sensor;
//import com.service.HisIrDataService;
import com.service.SensorService;

public class HistoryAction extends ActionBase {

	/**
	 * 椤甸潰 Action
	 */
	private static final long serialVersionUID = 8824207978201144546L;

	public String history() {
		return "success";
	}

	// private final static int pagesize = 20;
	@Autowired
	private HibernateInterceptor hibernateInterceptor;

	/*@Autowired
	private HisIrDataService hisIrDataService;*/
	
	@Autowired
	private SensorService sensorService;

	private int dtuId;
	private Integer[] lmuIds;
	private String selectDate;
	//private int alarmType;

	public void setDtuId(int dtuId) {
		this.dtuId = dtuId;
	}

	public void setLmuIds(Integer[] lmuIds) {
		this.lmuIds = lmuIds;
	}

	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}

	/*public void setAlarmType(int alarmType) {
		this.alarmType = alarmType;
	}*/

	private int totalCount;
	private int start;
	private int limit;
	/*private List<HisIrData> hisIrList;*/
	private List<Sensor> hisIrList;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	/*public List<HisIrData> getHisIrList() {
		return hisIrList;
	}*/
	
	public List<Sensor> getHisIrList() {
		return hisIrList;
	}

	@SuppressWarnings("deprecation")
	public String HistroyData() {
		String partion = "";
		/*String hql = "from HisIrData where 1=1";*/
		//String hql = "from Sensor where 1=1";
		String hql = "from Sensor where 1=1";
		if (dtuId != 0) {
			hql += " and dtuId='" + dtuId + "'";
		}
		if (lmuIds != null) {
			if (lmuIds[0] != 0) {
				String lmuIdsStr = "";
				for (int i = 0; i < lmuIds.length; i++) {
					lmuIdsStr += lmuIds[i] + ",";
				}
				lmuIdsStr = lmuIdsStr.substring(0, lmuIdsStr.length() - 1);
				hql += " and lmuId in (" + lmuIdsStr + ")";
			}
		}
		/*if (alarmType != 0) {
			String exStr = "";
			switch (alarmType) {
			case 1:
				exStr = "杩囨祦";
				break;
			case 2:
				exStr = "娆犳祦";
				break;
			case 3:
				exStr = "杩囧帇";
				break;
			case 4:
				exStr = "娆犲帇";
				break;
			case 5:
				exStr = "杩囨俯";
				break;
			case 6:
				exStr = "涓婄寮傚父";
				break;
			case 7:
				exStr = "涓嬬寮傚父";
				break;
			}
			hql += " and Ir7='" + exStr + "'";
		}*/
		Date date = new Date();
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd");
		if (!selectDate.isEmpty()) {
			try {
				date = timeformat.parse(selectDate);
				hql += " and Addtime > '" + timeformat.format(date)
						+ " 00:00:00' and Addtime <'" + timeformat.format(date)
						+ " 23:59:59'";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -1);
			date = c.getTime();
			hql += " and Addtime > '" + timeformat.format(date) + " 00:00:00'";
		}
		switch (date.getMonth() + 1) {
		case 1:
			partion = "p1";
			break;
		case 2:
			partion = "p2";
			break;
		case 3:
			partion = "p3";
			break;
		case 4:
			partion = "p4";
			break;
		case 5:
			partion = "p5";
			break;
		case 6:
			partion = "p6";
			break;
		case 7:
			partion = "p7";
			break;
		case 8:
			partion = "p8";
			break;
		case 9:
			partion = "p9";
			break;
		case 10:
			partion = "p10";
			break;
		case 11:
			partion = "p11";
			break;
		case 12:
			partion = "p12";
			break;
		default:
			partion = "p0";
			break;
		}
		hibernateInterceptor.setTargetTableName("hisirdata ");
		hibernateInterceptor.setTempTableName("hisirdata PARTITION(" + partion
				+ ") ");

		hql += " order by Addtime desc";

		try {
			//totalCount = hisIrDataService.count("select count(*) " + hql);
			totalCount = sensorService.count("select count(*) " + hql);
		} catch (Exception ex) {
			ex.printStackTrace();
			totalCount = 0;
		}

		try {
			//hisIrList = hisIrDataService.HisIrListByPage(hql, start, limit);
			hisIrList = sensorService.SensorListByPage(hql, start, limit);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "success";
	}
}
