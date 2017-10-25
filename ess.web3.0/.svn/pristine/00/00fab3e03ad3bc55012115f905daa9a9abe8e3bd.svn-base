package com.action.station;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.hibernate.entity.DTU;
import com.hibernate.entity.LMU;
import com.hibernate.entity.PartSensor;
import com.hibernate.entity.Station;
import com.service.AlarmMsgService;
import com.service.DtuService;
import com.service.LmuService;
import com.service.StationService;
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
	@Autowired
	AlarmMsgService alarmMsgService;
	
	private List<Map<String, Object>> selectList;
	private Map<String,Object> mapList=new HashMap<>();


	public Map<String, Object> getMapList() {
		return mapList;
	}



	public void setMapList(Map<String, Object> mapList) {
		this.mapList = mapList;
	}



	public String showSelection() throws IOException{
		selectList=new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		int police = 0;
		for (Map.Entry<DTU, PartSensor> e : messageQueue.getCacheData().entrySet()) {
			if(e.getValue().getType() == 1){
				//站点名称
				map = new HashMap<String,Object>();
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
				//int police = 0;
				for (int i = 0; i < obj.length; i++) {
					map = getMap(e.getKey(), station.getStationname());
					map.put("id", 0);
					map.put("startWarnTime", "");
					map.put("status", "");
					map.put("endTime", "");
					//氨气
					if(i == 0){
						if(obj[i] != null){
							//值
							map.put("value", obj[i]);
							//是否报警
							//map.put("police", e.getValue().getAqPlice());
							map.put("police", e.getValue().getV1Police());
							//设备ID
							map.put("lmuId",getLmuMesg(e.getKey().getLmus(), 2).get("lmuId"));
							//设备名称
							map.put("lmuName",getLmuMesg(e.getKey().getLmus(), 2).get("lmuName"));
							//设备类型
							map.put("type", 1); //氨气
							//报警值
							//map.put("aqVal",e.getValue().getAqPlice());
							if(e.getValue().getV1Police() == 1){
								map.put("id", e.getValue().getV1AlarmId());
								//氨气报警时间
								map.put("startWarnTime", e.getValue().getV1StartWarnTime());
								
								map.put("status", alarmMsgService.getByid(e.getValue().getV1AlarmId()).getStatus());
								map.put("endTime", alarmMsgService.getByid(e.getValue().getV1AlarmId()).getEndtime() != null ? treatTime(alarmMsgService.getByid(e.getValue().getV1AlarmId()).getEndtime())  : "");
								if(alarmMsgService.getByid(e.getValue().getV1AlarmId()).getStatus() == 0){
									police++;
								}
							}
							selectList.add(map);
							
						}
					}
					
					if(i == 1 || i == 2){
						if(obj[i] != null){
							//值
							map.put("value", obj[i]);
							//是否报警
							if(i == 1){
								//map.put("police", e.getValue().getWdPlice());
								//不显示map.put("police", e.getValue().getV2Poloce());
								//设备类型
								map.put("type", 2); //温湿度
								map.put("police", e.getValue().getV2Police());
								//报警值
							
								
								if(e.getValue().getV2Police() == 1){
									map.put("id", e.getValue().getV2AlarmId());
									//氨气报警时间
									map.put("startWarnTime", e.getValue().getV2StartWarnTime());
									
									map.put("status", alarmMsgService.getByid(e.getValue().getV2AlarmId()).getStatus());
									map.put("endTime", alarmMsgService.getByid(e.getValue().getV2AlarmId()).getEndtime() != null ? treatTime(alarmMsgService.getByid(e.getValue().getV2AlarmId()).getEndtime()) : "");
									if(alarmMsgService.getByid(e.getValue().getV2AlarmId()).getStatus() == 0){
										police++;
									}
								}
								
								
							}else{
								//map.put("police", e.getValue().getSdPlice());
								map.put("police", e.getValue().getV2Police());
								//设备类型
								map.put("type", 3); //温湿度
								
							
								
								if(e.getValue().getV3Police() == 1){
									map.put("id", e.getValue().getV3AlarmId());
									//氨气报警时间
									map.put("startWarnTime", e.getValue().getV3StartWarnTime());
									
									map.put("status", alarmMsgService.getByid(e.getValue().getV3AlarmId()).getStatus());
									map.put("endTime", alarmMsgService.getByid(e.getValue().getV3AlarmId()).getEndtime() != null ? treatTime(alarmMsgService.getByid(e.getValue().getV3AlarmId()).getEndtime()) : "");
									if(alarmMsgService.getByid(e.getValue().getV3AlarmId()).getStatus() == 0){
										police++;
									}
								}
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
							
							map.put("startWarnTime", "");
							
							if(i==3){
								map.put("police", e.getValue().getV4Police());
								if(e.getValue().getV4Police()==1){
									map.put("id", e.getValue().getV4AlarmId());
									map.put("startWarnTime", e.getValue().getV4StartWarnTime());
									map.put("status", alarmMsgService.getByid(e.getValue().getV4AlarmId()).getStatus());
									map.put("endTime", alarmMsgService.getByid(e.getValue().getV4AlarmId()).getEndtime() != null ? treatTime(alarmMsgService.getByid(e.getValue().getV4AlarmId()).getEndtime()) :"");
									if(alarmMsgService.getByid(e.getValue().getV4AlarmId()).getStatus() == 0){
										police++;
									}
								}
								
							}
							if(i==4){
								map.put("police", e.getValue().getV5Police());
								if(e.getValue().getV5Police()==1){
									map.put("id", e.getValue().getV5AlarmId());
									map.put("startWarnTime", e.getValue().getV5StartWarnTime());
									map.put("status", alarmMsgService.getByid(e.getValue().getV5AlarmId()).getStatus());
									map.put("endTime", alarmMsgService.getByid(e.getValue().getV5AlarmId()).getEndtime() != null ? treatTime(alarmMsgService.getByid(e.getValue().getV5AlarmId()).getEndtime()):"");
									if(alarmMsgService.getByid(e.getValue().getV5AlarmId()).getStatus() == 0){
										police++;
									}
								}
								
							}
							if(i==5){
								map.put("police", e.getValue().getV6Police());
								if(e.getValue().getV6Police()==1){
									map.put("id", e.getValue().getV6AlarmId());
									map.put("startWarnTime", e.getValue().getV6StartWarnTime());
									map.put("status", alarmMsgService.getByid(e.getValue().getV6AlarmId()).getStatus());
									map.put("endTime", alarmMsgService.getByid(e.getValue().getV6AlarmId()).getEndtime() != null ? treatTime(alarmMsgService.getByid(e.getValue().getV6AlarmId()).getEndtime()) :"");
									if(alarmMsgService.getByid(e.getValue().getV6AlarmId()).getStatus() == 0){
										police++;
									}
								}
								
							}
							if(i==6){
								map.put("police", e.getValue().getV7Police());
								if(e.getValue().getV7Police()==1){
									map.put("id", e.getValue().getV7AlarmId());
									map.put("startWarnTime", e.getValue().getV7StartWarnTime());
									map.put("status", alarmMsgService.getByid(e.getValue().getV7AlarmId()).getStatus());
									map.put("endTime", alarmMsgService.getByid(e.getValue().getV7AlarmId()).getEndtime() != null ? treatTime(alarmMsgService.getByid(e.getValue().getV7AlarmId()).getEndtime()) : "");
									if(alarmMsgService.getByid(e.getValue().getV7AlarmId()).getStatus() == 0){
										police++;
									}
								}
								
							}
							if(i==7){
								map.put("police", e.getValue().getV8Police());
								if(e.getValue().getV8Police()==1){
									map.put("id", e.getValue().getV8AlarmId());
									map.put("startWarnTime", e.getValue().getV8StartWarnTime());
									map.put("status", alarmMsgService.getByid(e.getValue().getV8AlarmId()).getStatus());
									map.put("endTime", alarmMsgService.getByid(e.getValue().getV8AlarmId()).getEndtime() != null ? treatTime(alarmMsgService.getByid(e.getValue().getV8AlarmId()).getEndtime()): "");
									if(alarmMsgService.getByid(e.getValue().getV8AlarmId()).getStatus() == 0){
										police++;
									}
								}
								
							}
							if(i==8){
								map.put("police", e.getValue().getV9Police());
								if(e.getValue().getV9Police()==1){
									map.put("id", e.getValue().getV9AlarmId());
									map.put("startWarnTime", e.getValue().getV9StartWarnTime());
									map.put("status", alarmMsgService.getByid(e.getValue().getV9AlarmId()).getStatus());
									map.put("endTime", alarmMsgService.getByid(e.getValue().getV9AlarmId()).getEndtime() != null ?treatTime(alarmMsgService.getByid(e.getValue().getV9AlarmId()).getEndtime()) : "");
									if(alarmMsgService.getByid(e.getValue().getV9AlarmId()).getStatus() == 0){
										police++;
									}
								}
								
							}
							if(i==9){
								map.put("police", e.getValue().getV10Police());
								if(e.getValue().getV10Police()==1){
									map.put("id", e.getValue().getV10AlarmId());
									map.put("startWarnTime", e.getValue().getV10StartWarnTime());
									map.put("status", alarmMsgService.getByid(e.getValue().getV10AlarmId()).getStatus());
									map.put("endTime", alarmMsgService.getByid(e.getValue().getV10AlarmId()).getEndtime() != null ? treatTime(alarmMsgService.getByid(e.getValue().getV10AlarmId()).getEndtime()) : "");
									if(alarmMsgService.getByid(e.getValue().getV10AlarmId()).getStatus() == 0){
										police++;
									}
								}
								
							}
							if(i==10){
								map.put("police", e.getValue().getV11Police());
								if(e.getValue().getV11Police()==1){
									map.put("id", e.getValue().getV11AlarmId());
									map.put("startWarnTime", e.getValue().getV11StartWarnTime());
									map.put("status", alarmMsgService.getByid(e.getValue().getV11AlarmId()).getStatus());
									map.put("endTime", alarmMsgService.getByid(e.getValue().getV11AlarmId()).getEndtime() != null ?  treatTime(alarmMsgService.getByid(e.getValue().getV11AlarmId()).getEndtime()) : "");
									if(alarmMsgService.getByid(e.getValue().getV11AlarmId()).getStatus() == 0){
										police++;
									}
								}
								
							}
							
							selectList.add(map);
						}
					}
				}
				mapList.put("selectList", selectList);
				mapList.put("allWranNumber", police);
			
			}
			
		}
		if(selectList.size()<=0){
			mapList.put("selectList", selectList);
			mapList.put("allWranNumber", police);
		}
		return 	"success";
	}

	

	/*public List<Map<String, Object>> getSelectList() {
		return selectList;
	}



	public void setSelectList(List<Map<String, Object>> selectList) {
		this.selectList = selectList;
	}*/



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
	
	private String treatTime(Calendar calendar){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}
	
}
