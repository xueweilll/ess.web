package com.action.analysis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.common.Interceptor.HibernateInterceptor;
import com.hibernate.entity.DTU;
//import com.hibernate.entity.HisIrData;
import com.hibernate.entity.Sensor;
import com.service.DtuService;
//import com.service.HisIrDataService;
import com.service.SensorService;

public class HistoryAction extends ActionBase {

	/**
	 * 椤甸潰 Action
	 */
	private static final long serialVersionUID = 8824207978201144546L;

	@Autowired
	private HibernateInterceptor hibernateInterceptor;
	
	@Autowired
	private SensorService sensorService;

	@Autowired
	private DtuService dtuService;
	
	private int dtuId;
	private Integer[] lmuIds;
	private String selectDate;
	
	private int pareId;

	public int getPareId() {
		return pareId;
	}

	public void setPareId(int pareId) {
		this.pareId = pareId;
	}

	public void setDtuId(int dtuId) {
		this.dtuId = dtuId;
	}

	public void setLmuIds(Integer[] lmuIds) {
		this.lmuIds = lmuIds;
	}

	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}

	private int totalCount;
	private int start;
	private int limit;
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
	
	public List<Sensor> getHisIrList() {
		return hisIrList;
	}

	public String history() {
		String partion = "";
		String hql = "from Sensor where 1=1";
		hibernateInterceptor.setTargetTableName("hisirdata ");
		hibernateInterceptor.setTempTableName("hisirdata PARTITION(" + partion
				+ ") ");

		hql += " order by Addtime desc";

		try {
			totalCount = sensorService.count("select count(*) " + hql);
		} catch (Exception ex) {
			ex.printStackTrace();
			totalCount = 0;
		}

		try {
			hisIrList = sensorService.SensorListByPage(hql, start, limit);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "success";
	}
	
	@SuppressWarnings("deprecation")
	public String HistroyData() {
		String hql = "from Sensor where 1=1";
 		String partion = "";
 		//String ids="";
 		System.out.println(pareId);
 		if(pareId!=0){
 		List<DTU> dtu=dtuService.DTUlistByStationId(pareId);
		if (dtu.size()>0) {
			String dutIdStr = "";
			for (int i = 0; i < dtu.size(); i++) {
				dutIdStr += dtu.get(i).getId()+",";
			}
			dutIdStr = dutIdStr.substring(0, dutIdStr.length() - 1);
			hql += " and dtuId in (" + dutIdStr + ")";
		}else{
			hql += " and dtuId =''";
		}
		
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
 		}
		hql += " order by Addtime desc";

		try {
			totalCount = sensorService.count("select count(*) " + hql);
		} catch (Exception ex) {
			ex.printStackTrace();
			totalCount = 0;
		}

		try {
			hisIrList = sensorService.SensorListByPage(hql, start, limit);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "success";
	}
}
