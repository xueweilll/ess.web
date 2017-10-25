package com.action.monitor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hibernate.entity.AlarmMsg;
import com.service.AlarmMsgService;


public class PoliceAppAction {
	private int id;
	
	private String dtuid;
	
	private int lmuid;
	
	private int type;
	
	private Map<String, Object> rMap=new HashMap<>();
	
	@Autowired
	private AlarmMsgService alarmMsgService;
	
	public String cancleAlarm(){
        AlarmMsg alarmMsg=new AlarmMsg();
        alarmMsg.setId(id);
		try {
			String judge=alarmMsgService.updateAlarm(alarmMsg);
			if(judge.equals("success")){
				rMap.put("return", "success");
			}else {
				rMap.put("return", "error");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			rMap.put("return", "error");
		}
		 return "success";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, Object> getrMap() {
		return rMap;
	}

	public void setrMap(Map<String, Object> rMap) {
		this.rMap = rMap;
	}

	public String getDtuid() {
		return dtuid;
	}

	public void setDtuid(String dtuid) {
		this.dtuid = dtuid;
	}

	public int getLmuid() {
		return lmuid;
	}

	public void setLmuid(int lmuid) {
		this.lmuid = lmuid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
