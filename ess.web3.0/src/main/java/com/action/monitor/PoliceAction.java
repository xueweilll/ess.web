package com.action.monitor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.action.ActionBase;
import com.hibernate.entity.AlarmMsg;
import com.hibernate.entity.DTU;
import com.hibernate.entity.Station;
import com.service.AlarmMsgService;
import com.service.DtuService;
import com.service.StationService;

public class PoliceAction extends ActionBase{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	@Autowired
	private AlarmMsgService alarmMsgService;
	
	@Autowired
	private StationService stationService;
	
	@Autowired
	private DtuService dtuService;

	private int dtuId=-1;
	private int type;
	private int id;
	
	public void setDtuId(int dtuId) {
		this.dtuId = dtuId;
	}

	private int totalCount;
	private int start;
	private int limit;
	private List<Map<String, Object>> hisAlamList =new ArrayList<>();
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
	
	public String currentPoliceDate() {
		String hql = "from AlarmMsg where 1=1";
		if (dtuId != -1) {
				List<DTU> dtu=dtuService.DTUlistByStationId(dtuId);
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
		hql +="and Status =0";
		hql += " order by Addtime desc";
		try {
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
	
	public String currentPolice() {
		return "success";
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
