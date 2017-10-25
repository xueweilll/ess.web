package com.action.station;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.hibernate.entity.DTU;
import com.hibernate.entity.LMU;
import com.hibernate.entity.PartSensor;
import com.hibernate.entity.Station;
import com.service.DtuService;
import com.service.LmuService;
import com.service.StationService;
import com.unit.JsonHelper;
import com.unit.MessageQueue;

public class SelectionAtation extends ActionBase{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	MessageQueue messageQueue;
	@Autowired
	StationService stationService;
	@Autowired
	DtuService dtuService;
	@Autowired
	LmuService lmuService;
	
	private List<Map<String, Object>> selectList;
	
	public String showSelection() throws IOException{
		ConcurrentHashMap<DTU, PartSensor> cacheData = messageQueue.getCacheData();
		selectList=new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		for (Map.Entry<DTU, PartSensor> e : cacheData.entrySet()) {
			//站点名称
			int stationId=e.getKey().getStationId();
			Station station=stationService.station(stationId);
			//区域名称
			Object[] obj = new Object[11];
			obj[0] = e.getValue().getV1();
			obj[1] = e.getValue().getV2();
			obj[2] = e.getValue().getV3();
			obj[3] = e.getValue().getV4();
			obj[4] = e.getValue().getV5();
			obj[5] = e.getValue().getV6();
			obj[6] = e.getValue().getV7();
			obj[7] = e.getValue().getV8();
			obj[8] = e.getValue().getV9();
			obj[9] = e.getValue().getV10();
			obj[10] = e.getValue().getV11();
			for (int i = 0; i < obj.length; i++) {
				map = getMap(e.getKey(), station.getStationname());
				//氨气
				if(i == 0){
					if(obj[i] != null){
						//值
						map.put("value", obj[i]);
						//是否报警
						map.put("police", e.getValue().getAqPlice());
						//设备ID
						map.put("lmuId",getLmuMesg(e.getKey().getLmus(), 2).get("lmuId"));
						//设备名称
						map.put("lmuName",getLmuMesg(e.getKey().getLmus(), 2).get("lmuName"));
						//设备类型
						map.put("type", 1); //氨气
						selectList.add(map);
					}
				}
				
				if(i == 1 || i == 2){
					if(obj[i] != null){
						//值
						map.put("value", obj[i]);
						//是否报警
						if(i == 1){
							map.put("police", e.getValue().getWdPlice());
							//设备类型
							map.put("type", 2); //温湿度
						}else{
							map.put("police", e.getValue().getSdPlice());
							//设备类型
							map.put("type", 3); //温湿度
						}
						//设备ID
						map.put("lmuId",getLmuMesg(e.getKey().getLmus(), 1).get("lmuId"));
						//设备名称
						map.put("lmuName",getLmuMesg(e.getKey().getLmus(), 1).get("lmuName"));
						
						selectList.add(map);
					}
				}
				
				if(i > 2){
					if(obj[i] != null){
						//值
						map.put("value", obj[i]);
						//设备ID
						map.put("lmuId",getLmuMesg(e.getKey().getLmus(), 3).get("lmuId"));
						//设备名称
						map.put("lmuName",getLmuMesg(e.getKey().getLmus(),3).get("lmuName"));
						//设备类型
						map.put("type", i+1); //市电
						selectList.add(map);
					}
				}
			}
			
		}
		return 	"success";
	}

	

	public List<Map<String, Object>> getSelectList() {
		return selectList;
	}



	public void setSelectList(List<Map<String, Object>> selectList) {
		this.selectList = selectList;
	}



	private Map<String, Object> getMap(DTU dtu,String station){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("dtuId", dtu.getId());
		map.put("dtuName", dtu.getName());
		map.put("stationId", dtu.getStationId());
		map.put("stationName", station);
		return map;
	}
	
	private Map<String, Object> getLmuMesg(Set<LMU> set,int code){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("LmuId", "");
		map.put("LmuName", "");
		for (LMU lmu : set) {
			if(lmu.getCode() == code){
				map.put("lmuId", lmu.getId());
				map.put("lmuName", lmu.getName());
			}
		}
		return map;
	}
	
}
