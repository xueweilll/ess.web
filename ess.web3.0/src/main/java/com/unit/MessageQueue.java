package com.unit;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hibernate.entity.DTU;
import com.hibernate.entity.LMU;
import com.hibernate.entity.PartSensor;

@Scope("singleton")
@Service("messageQueue")
public class MessageQueue {
	private ConcurrentHashMap<DTU, PartSensor> cacheData;
	@Autowired
	private PartsQuence partsQuence;
	
	@PostConstruct
	public void init(){
		cacheData = new ConcurrentHashMap<>();
	}
	
	public void put(DTU dtu,PartSensor PartSensor){
		cacheData.put(dtu, PartSensor);
	}
	
	public void remove(DTU dtu){
		if (cacheData.containsKey(dtu)) {
			cacheData.remove(dtu);
		}
	}
	
	public PartSensor get(DTU dtu){
		return cacheData.get(dtu);
	}
	
	public Map.Entry<DTU,PartSensor> getById(int dtuId){
		for(Map.Entry<DTU,PartSensor> e:cacheData.entrySet()){
			DTU dtu = e.getKey();
			if(dtu.getId()==dtuId){
				return e;
			}
		}
		return null;
	}

	public ConcurrentHashMap<DTU, PartSensor> getCacheData() {
		//遍历站点
		for(Map.Entry<DTU, PartSensor> e: cacheData.entrySet() ){
			Set<LMU> set = e.getKey().getLmus();
			//遍历设备 //排除断网的设备
			if(e.getValue().getType() != 2){
				//判断设备是否异常
				boolean flag = false;
				for (LMU lmu : set) {
					boolean getFlag = false;
					for(Map.Entry<Integer, Map<String, Object>> e1: partsQuence.getPartsData().entrySet() ){
						if(lmu.getId() == e1.getKey()){
							//e.getValue().setType(0);
							//获取当前温湿度
							if(lmu.getType() == 2){
								getFlag = true;
								//判断设备是否自动断开
								int wsDestoryTime = Integer.parseInt(e1.getValue().get("wsDestoryTime").toString());
								if(e.getValue().getWsDestoryTime() == wsDestoryTime){
									e.getValue().setTbwsDestoryTime(e.getValue().getTbwsDestoryTime() + 1);
								}else{
									e.getValue().setTbwsDestoryTime(0);
								}
								e.getValue().setWsDestoryTime(wsDestoryTime);
								
								if(e.getValue().getTbwsDestoryTime() > 20){
									getFlag = false;
								}
								e.getValue().setV2(new BigDecimal(e1.getValue().get("v2").toString()));
								e.getValue().setV3(new BigDecimal(e1.getValue().get("v3").toString()));
								e.getValue().setV2Police(Integer.parseInt(e1.getValue().get("v2Police").toString()));
								e.getValue().setV3Police(Integer.parseInt(e1.getValue().get("v3Police").toString()));
								if(e1.getValue().get("v2AlarmMsg") != null){
									e.getValue().setV2AlarmId(Integer.valueOf(e1.getValue().get("v2AlarmMsg").toString()));
								}
								if(e1.getValue().get("v3AlarmMsg") != null){
									e.getValue().setV3AlarmId(Integer.valueOf(e1.getValue().get("v3AlarmMsg").toString()));
								}
								e.getValue().setV2StartWarnTime(e1.getValue().get("v2StartWarnTime") != null ? e1.getValue().get("v2StartWarnTime").toString() : "");
								e.getValue().setV3StartWarnTime(e1.getValue().get("v3StartWarnTime") != null ? e1.getValue().get("v3StartWarnTime").toString() : "");
								if(Integer.parseInt(e1.getValue().get("v2Police").toString()) == 1
									|| Integer.parseInt(e1.getValue().get("v3Police").toString()) == 1 || getFlag == false){
									flag = true;
								}
							}
							//获取当前氨气度
							if(lmu.getType() == 1){
								getFlag = true;
								//判断设备是否自动断开
								int aqDestoryTime = Integer.parseInt(e1.getValue().get("aqDestoryTime").toString());
								if(e.getValue().getAqDestoryTime() == aqDestoryTime){
									e.getValue().setTbaqDestoryTime(e.getValue().getTbaqDestoryTime() + 1);
								}else{
									e.getValue().setTbaqDestoryTime(0);
								}
								e.getValue().setAqDestoryTime(aqDestoryTime);
								
								if(e.getValue().getTbaqDestoryTime() > 20){
									getFlag = false;
								}
								e.getValue().setV1(new BigDecimal(e1.getValue().get("v1").toString()));
								e.getValue().setV1Police(Integer.parseInt(e1.getValue().get("v1Police").toString()));
								if(e1.getValue().get("v1AlarmMsg") != null){
									e.getValue().setV1AlarmId(Integer.valueOf(e1.getValue().get("v1AlarmMsg").toString()));
								}
								e.getValue().setV1StartWarnTime(e1.getValue().get("v1StartWarnTime") != null ? e1.getValue().get("v1StartWarnTime").toString() : "");
								if(Integer.parseInt(e1.getValue().get("v1Police").toString()) == 1 || getFlag == false){
									flag = true;
								}
							}
							//获取当前市电
							if(lmu.getType() == 3){
								getFlag = true;
								//判断设备是否自动断开
								int sdDestoryTime = Integer.parseInt(e1.getValue().get("sdDestoryTime").toString());
								if(e.getValue().getSdDestoryTime() == sdDestoryTime){
									e.getValue().setTbsdDestoryTime(e.getValue().getTbsdDestoryTime() + 1);
								}else{
									e.getValue().setTbsdDestoryTime(0);
								}
								e.getValue().setSdDestoryTime(sdDestoryTime);
								
								if(e.getValue().getTbsdDestoryTime() > 20){
									getFlag = false;
								}
								e.getValue().setV4(new BigDecimal(e1.getValue().get("v4").toString()));
								e.getValue().setV4Police(Integer.parseInt(e1.getValue().get("v4Police").toString()));
								e.getValue().setV4StartWarnTime(e1.getValue().get("v4StartWarnTime") != null ? e1.getValue().get("v4StartWarnTime").toString() : "");
								if(e1.getValue().get("v4AlarmMsg") != null){
									e.getValue().setV4AlarmId(Integer.valueOf(e1.getValue().get("v4AlarmMsg").toString()));
								}
								e.getValue().setV5(new BigDecimal(e1.getValue().get("v5").toString()));
								e.getValue().setV5Police(Integer.parseInt(e1.getValue().get("v5Police").toString()));
								e.getValue().setV5StartWarnTime(e1.getValue().get("v5StartWarnTime") != null ? e1.getValue().get("v5StartWarnTime").toString() : "");
								if(e1.getValue().get("v5AlarmMsg") != null){
									e.getValue().setV5AlarmId(Integer.valueOf(e1.getValue().get("v5AlarmMsg").toString()));
								}
								e.getValue().setV6(new BigDecimal(e1.getValue().get("v6").toString()));
								e.getValue().setV6Police(Integer.parseInt(e1.getValue().get("v6Police").toString()));
								e.getValue().setV6StartWarnTime(e1.getValue().get("v6StartWarnTime") != null ? e1.getValue().get("v6StartWarnTime").toString() : "");
								if(e1.getValue().get("v6AlarmMsg") != null){
									e.getValue().setV6AlarmId(Integer.valueOf(e1.getValue().get("v6AlarmMsg").toString()));
								}
								e.getValue().setV7(new BigDecimal(e1.getValue().get("v7").toString()));
								e.getValue().setV7Police(Integer.parseInt(e1.getValue().get("v7Police").toString()));
								e.getValue().setV7StartWarnTime(e1.getValue().get("v7StartWarnTime") != null ? e1.getValue().get("v7StartWarnTime").toString() : "");
								if(e1.getValue().get("v7AlarmMsg") != null){
									e.getValue().setV7AlarmId(Integer.valueOf(e1.getValue().get("v7AlarmMsg").toString()));
								}
								e.getValue().setV8(new BigDecimal(e1.getValue().get("v8").toString()));
								e.getValue().setV8Police(Integer.parseInt(e1.getValue().get("v8Police").toString()));
								e.getValue().setV8StartWarnTime(e1.getValue().get("v8StartWarnTime") != null ? e1.getValue().get("v8StartWarnTime").toString() : "");
								if(e1.getValue().get("v8AlarmMsg") != null){
									e.getValue().setV8AlarmId(Integer.valueOf(e1.getValue().get("v8AlarmMsg").toString()));
								}
								e.getValue().setV9(new BigDecimal(e1.getValue().get("v9").toString()));
								e.getValue().setV9Police(Integer.parseInt(e1.getValue().get("v9Police").toString()));
								e.getValue().setV9StartWarnTime(e1.getValue().get("v9StartWarnTime") != null ? e1.getValue().get("v9StartWarnTime").toString() : "");
								if(e1.getValue().get("v9AlarmMsg") != null){
									e.getValue().setV9AlarmId(Integer.valueOf(e1.getValue().get("v9AlarmMsg").toString()));
								}
								e.getValue().setV10(new BigDecimal(e1.getValue().get("v10").toString()));
								e.getValue().setV10Police(Integer.parseInt(e1.getValue().get("v10Police").toString()));
								e.getValue().setV10StartWarnTime(e1.getValue().get("v10StartWarnTime") != null ? e1.getValue().get("v10StartWarnTime").toString() : "");
								if(e1.getValue().get("v10AlarmMsg") != null){
									e.getValue().setV10AlarmId(Integer.valueOf(e1.getValue().get("v10AlarmMsg").toString()));
								}
								e.getValue().setV11(new BigDecimal(e1.getValue().get("v11").toString()));
								e.getValue().setV11Police(Integer.parseInt(e1.getValue().get("v11Police").toString()));
								e.getValue().setV11StartWarnTime(e1.getValue().get("v11StartWarnTime") != null ? e1.getValue().get("v11StartWarnTime").toString() : "");
								if(e1.getValue().get("v11AlarmMsg") != null){
									e.getValue().setV11AlarmId(Integer.valueOf(e1.getValue().get("v11AlarmMsg").toString()));
								}
								if(Integer.parseInt(e1.getValue().get("v4Police").toString()) == 1
									|| Integer.parseInt(e1.getValue().get("v5Police").toString()) == 1
									|| Integer.parseInt(e1.getValue().get("v6Police").toString()) == 1
									|| Integer.parseInt(e1.getValue().get("v7Police").toString()) == 1
									|| Integer.parseInt(e1.getValue().get("v8Police").toString()) == 1
									|| Integer.parseInt(e1.getValue().get("v9Police").toString()) == 1
									|| Integer.parseInt(e1.getValue().get("v10Police").toString()) == 1
									|| Integer.parseInt(e1.getValue().get("v11Police").toString()) == 1
									|| getFlag == false
								){
									flag = true;
								}
							}
						}
					}
					//判断设备信息是否被删除
					if(!getFlag){
						if(lmu.getType() == 2){
							e.getValue().setV2(null);
							e.getValue().setV3(null);
							e.getValue().setV2Police(0);
							e.getValue().setV3Police(0);
							e.getValue().setV2StartWarnTime(null);
							e.getValue().setV3StartWarnTime(null);
						}
						//获取当前氨气度
						if(lmu.getType() == 1){
							e.getValue().setV1(null);
							e.getValue().setV1Police(0);
							e.getValue().setV1StartWarnTime(null);
						}
						//获取当前市电
						if(lmu.getType() == 3){
							e.getValue().setV4(null);
							e.getValue().setV4Police(0);
							e.getValue().setV4StartWarnTime(null);
							e.getValue().setV5(null);
							e.getValue().setV5Police(0);
							e.getValue().setV5StartWarnTime(null);
							e.getValue().setV6(null);
							e.getValue().setV6Police(0);
							e.getValue().setV6StartWarnTime(null);
							e.getValue().setV7(null);
							e.getValue().setV7Police(0);
							e.getValue().setV7StartWarnTime(null);
							e.getValue().setV8(null);
							e.getValue().setV8Police(0);
							e.getValue().setV8StartWarnTime(null);
							e.getValue().setV9(null);
							e.getValue().setV9Police(0);
							e.getValue().setV9StartWarnTime(null);
							e.getValue().setV10(null);
							e.getValue().setV10Police(0);
							e.getValue().setV10StartWarnTime(null);
							e.getValue().setV11(null);
							e.getValue().setV11Police(0);
							e.getValue().setV11StartWarnTime(null);
						}
					}
				}
				//判断是否异常
				if(flag){
					e.getValue().setType(1);
				}else{
					e.getValue().setType(0);
				}
			}  
			
			e.getValue().setAddtime(Calendar.getInstance());
		}
		return cacheData;
	}
	
}
