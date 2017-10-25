package com.action.station;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.hibernate.entity.DTU;
import com.hibernate.entity.LMU;
import com.hibernate.entity.PartSensor;
import com.hibernate.entity.Station;
import com.service.DtuService;
import com.service.LmuService;
import com.service.StationService;
import com.unit.MessageQueue;

public class LimitAtation extends ActionBase{
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
	
	private List<Map<String, Object>> selectList=new ArrayList<Map<String,Object>>();
	private List<Object> list1;
	
	
	public List<Object> getList1() {
		return list1;
	}



	public void setList1(List<Object> list1) {
		this.list1 = list1;
	}



	public String limitAtation() throws IOException{
		ConcurrentHashMap<DTU, PartSensor> cacheData = messageQueue.getCacheData();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Station> stationList = stationService.stationlist();
		for (Station station : stationList) {
			Map<String, Object> map = null;
			map=new HashMap<String,Object>();
			map.put("areaName", station.getStationname());
			int sb = 0;
			int police = 0;
			List<Map<String, Object>> listsx = new ArrayList<Map<String,Object>>();
			for (Map.Entry<DTU, PartSensor> e : cacheData.entrySet()) {
				int s=station.getId();
				int d= e.getKey().getStationId();

				if(station.getId() == e.getKey().getStationId()){
					Map<String, Object> mapsx = null;
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
						mapsx = new HashMap<>();
						//氨气
						if(i == 0){
							if(obj[i] != null){
								//区域名称
								mapsx.put("areaName", station.getStationname());
								mapsx.put("dataValue", obj[i]);
								mapsx.put("facilityType", 1);
								mapsx.put("isWarn", e.getValue().getAqPlice());
								sb ++;
								if(e.getValue().getAqPlice() == 1){
									police ++;
								}
								mapsx.put("siteName", e.getKey().getName());
								listsx.add(mapsx);
							}
						}
						//温度
						if(i == 1){
							if(obj[i] != null){
								//区域名称
								mapsx.put("areaName", station.getStationname());
								mapsx.put("dataValue", obj[i]);
								mapsx.put("facilityType", 2);
								mapsx.put("isWarn", e.getValue().getWdPlice());
								sb ++;
								if(e.getValue().getWdPlice() == 1){
									police ++;
								}
								mapsx.put("siteName", e.getKey().getName());
								listsx.add(mapsx);
							}
						}
						//湿度
						if(i == 2){
							if(obj[i] != null){
								//区域名称
								mapsx.put("areaName", station.getStationname());
								mapsx.put("dataValue", obj[i]);
								mapsx.put("facilityType", 3);
								mapsx.put("isWarn", e.getValue().getSdPlice());
								sb ++;
								if(e.getValue().getSdPlice()== 1){
									police ++;
								}
								mapsx.put("siteName", e.getKey().getName());
								listsx.add(mapsx);
							}
						}
						//市电
						if(i > 2){
							if(obj[i] != null){
								//区域名称
								mapsx.put("areaName", station.getStationname());
								mapsx.put("dataValue", obj[i]);
								mapsx.put("facilityType", i+1);
								/*if(i < 3){*/
									sb ++;
								/*}*/
								mapsx.put("siteName", e.getKey().getName());
								listsx.add(mapsx);
							}
						}
					}
					map.put("facilityDetails", listsx);
					map.put("facilitySum", sb);
					map.put("facilityWarnNumber", police);
				}
			}
			selectList.add(map);
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
	
	private Map<String, Object> getLmu(Set<LMU> set){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("LmuId", "");
		map.put("LmuName", "");
		for (LMU lmu : set) {
			map.put("lmuId", lmu.getId());
			map.put("lmuName", lmu.getName());
		}
		return map;
	}
		
	
	
	private String getStation(int id){
		String stationName = null;
		List<Station> stationList = stationService.stationlist();
		for (Station station : stationList) {
			if(station.getId() == id){
				stationName = station.getStationname();
				break;
			}
		}
		return stationName;
	}
}
