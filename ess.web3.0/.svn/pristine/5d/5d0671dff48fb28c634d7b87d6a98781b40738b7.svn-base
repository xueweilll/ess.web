package com.action.analysis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.servlet.http.HttpServletRequest;

//import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.common.Interceptor.HibernateInterceptor;
import com.hibernate.entity.AlarmMsg;
import com.hibernate.entity.DTU;
import com.hibernate.entity.Station;
import com.service.AlarmMsgService;
import com.service.DtuService;
import com.service.StationService;
public class HistoryPoliceAction extends ActionBase {

	/**
	 * 椤甸潰 Action
	 */
	private static final long serialVersionUID = 8824207978201144546L;

	public String historyPolice() {
		return "success";
	}
	@Autowired
	private HibernateInterceptor hibernateInterceptor;

	/*@Autowired
	private HisIrDataService hisIrDataService;*/
	
	@Autowired
	private AlarmMsgService alarmMsgService;
	
	@Autowired
	private StationService stationService;
	
	@Autowired
	private DtuService dtuService;


	private int dtuId;
	private Integer[] lmuIds;
	private String selectDate;
	
	private int stationid;
	
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
	private List<Map<String, Object>> hisAlamList =new ArrayList<>();
	
	private int judgeTotal;
	
	//private List<AlarmMsg> hisIrList;

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
	
	/*public List<AlarmMsg> getHisIrList() {
		return hisIrList;
	}*/
/*	HttpServletRequest req = ServletActionContext.getRequest();
	String s1 = req.getHeader("user-agent");
	判断是否为移动端访问
	*/
	@SuppressWarnings("deprecation")
	public String historyPoliceDate() {				
		String partion = "";
		String hql = "from AlarmMsg where 1=1";
		if(judgeTotal!=-1){
		if(stationid !=-1){
			List<DTU> dtu=dtuService.DTUlistByStationId(stationid);
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
		}
	
		if (dtuId != -1) {
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
				hql += " and Endtime > '" + timeformat.format(date)
						+ " 00:00:00' and Endtime <'" + timeformat.format(date)
						+ " 23:59:59'";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -1);
			date = c.getTime();
			hql += " and Endtime > '" + timeformat.format(date) + " 00:00:00'";
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
		hql +="and Status =1";

		//hql += " order by Addtime desc";
		hql += " order by Endtime desc";
		try {
			//totalCount = hisIrDataService.count("select count(*) " + hql);
			totalCount =alarmMsgService.count("select count(*) " + hql);
		} catch (Exception ex) {
			ex.printStackTrace();
			totalCount = 0;
		}

		try {
			hisAlamList=list(alarmMsgService.listAlarmMsg(start, limit,hql));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "success";
	}
	
	private List<Map<String, Object>> list(List<AlarmMsg> aList) {
		List<Map<String, Object>> maplist=new ArrayList<>();
		List<Station> stList=stationService.stationlist();
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i=0;i<aList.size();i++){
			Map<String,Object> map=new HashMap<>();
			if (aList.get(i).getAddtime()!=null) {
				map.put("alarmTime",timeformat.format(aList.get(i).getAddtime().getTime()));//报警时间
			}else {
				map.put("alarmTime","");
			}
			if(aList.get(i).getEndtime()!=null){
				map.put("handleTime",timeformat.format(aList.get(i).getEndtime().getTime()));//处理时间	
			}else{
				map.put("handleTime","");//处理时间
			}
			map.put("type", aList.get(i).getType());//设备类型
			map.put("information",aList.get(i).getInfomation());//报警信息
			map.put("confirm", aList.get(i).getConfirm());//是否高于平均
			map.put("stationName",aList.get(i).getDtu().getName());//站点名
			map.put("status",aList.get(i).getStatus());//处理状态
			map.put("value", aList.get(i).getValue());//報警數值
			for(int a=0;a<stList.size();a++){
				if(aList.get(i).getDtu().getStationId()==stList.get(a).getId()){
					map.put("areaName", stList.get(a).getStationname());
					//break;
				}
			}
			maplist.add(map);
		}
		return maplist;
		
	}

	public List<Map<String, Object>> getHisAlamList() {
		return hisAlamList;
	}

	public void setHisAlamList(List<Map<String, Object>> hisAlamList) {
		this.hisAlamList = hisAlamList;
	}

	public int getStationid() {
		return stationid;
	}

	public void setStationid(int stationid) {
		this.stationid = stationid;
	}

	public int getJudgeTotal() {
		return judgeTotal;
	}

	public void setJudgeTotal(int judgeTotal) {
		this.judgeTotal = judgeTotal;
	}
}
