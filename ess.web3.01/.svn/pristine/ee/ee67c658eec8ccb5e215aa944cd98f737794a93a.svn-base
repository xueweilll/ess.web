package com.common.contral;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.common.param.paramers;
import com.hibernate.entity.DTU;
import com.hibernate.entity.HisHrData;
import com.hibernate.entity.LMU;
import com.hibernate.entity.PartSensor;
import com.service.HisHrDataService;
import com.service.LmuService;
import com.unit.MessageQueue;
@Scope("singleton")
@DependsOn({"lmuService","hisHrDataService","messageQueue"})
@Service("currStore")
public class CurrStore {
	@Autowired
	private LmuService lmuService;
	@Autowired
	private HisHrDataService hisHrDataService;

	private ArrayList<CurrIrItem> arrIrList;

	@Autowired
	MessageQueue messageQueue;

	public ArrayList<CurrIrItem> CreateCurrIrData(Integer[] dtuIds) {
		arrIrList = new ArrayList<>();
		CurrIrItem currIrItem;
		PartSensor partSensor;
		ConcurrentHashMap<DTU, PartSensor> cacheData = messageQueue.getCacheData();
		for (Map.Entry<DTU, PartSensor> e : cacheData.entrySet()) {
			for(int i = 0 ; i < dtuIds.length ; i ++){
				if(dtuIds[i] == e.getKey().getId()){
					DTU dtu = e.getKey();
					partSensor = e.getValue();
					currIrItem = new CurrIrItem();
//					currIrItem.setId(0);
					currIrItem.setName(partSensor.getDuoName());
//					currIrItem.setV0(String.valueOf(partSensor.getV0()) + " mg/L");
					currIrItem.setV1(String.valueOf(partSensor.getV1()));
					currIrItem.setV2(String.valueOf(partSensor.getV2()));
					currIrItem.setV3(String.valueOf(partSensor.getV3()));
					currIrItem.setV4(String.valueOf(partSensor.getV4()));
					currIrItem.setV5(String.valueOf(partSensor.getV5()));
					currIrItem.setV6(String.valueOf(partSensor.getV6()));
					currIrItem.setV7(String.valueOf(partSensor.getV7()));
					currIrItem.setV8(String.valueOf(partSensor.getV8()));
					currIrItem.setV9(String.valueOf(partSensor.getV9()));
					currIrItem.setV10(String.valueOf(partSensor.getV10()));
					currIrItem.setV11(String.valueOf(partSensor.getV11()));
					currIrItem.setAddTime(paramers.datetimeformate.format(partSensor.getAddtime().getTime()));
					arrIrList.add(currIrItem);
				}
			}
			
		}
		return arrIrList;
	}

	public ArrayList<CurrIrItem> CreateCurrIrData(int dtuId) {
		arrIrList = new ArrayList<>();
		CurrIrItem currIrItem;
		Map.Entry<DTU, PartSensor> e = messageQueue.getById(dtuId);
		if (e == null) {
			return null;
		}
		PartSensor partSensor = e.getValue();
		DTU dtu = e.getKey();
		currIrItem = new CurrIrItem();
	//	currIrItem.setId(partSensor.getLmu().getId());
		//currIrItem.setName(dtu.getName() + "-" + partSensor.getLmu().getName());
		//currIrItem.setV0(String.valueOf(partSensor.getV0()) + " ppm");
		currIrItem.setV1(String.valueOf(partSensor.getV1()) + " %");
		currIrItem.setV2(String.valueOf(partSensor.getV2()) + " ℃");
		currIrItem.setV3(String.valueOf(partSensor.getV3()) + " ppm");
		currIrItem.setV4(String.valueOf(partSensor.getV4()) + " ppm");
//		currIrItem.setV5(String.valueOf(partSensor.getV5()) + " ppm");
//		currIrItem.setV6(String.valueOf(partSensor.getV6()) + " ppm");
//		currIrItem.setV7(String.valueOf(partSensor.getV7()) + " ppm");
//		currIrItem.setV8(String.valueOf(partSensor.getV8()) + " ppm");
//		currIrItem.setV9(String.valueOf(partSensor.getV9()) + " ppm");
		currIrItem.setAddTime(paramers.datetimeformate.format(partSensor.getAddtime().getTime()));
		arrIrList.add(currIrItem);
		return arrIrList;
	}

	private ArrayList<CurrHrItem> arrHrList;

	public ArrayList<CurrHrItem> CreateCurrHrData(Integer[] dtuIds) {
		arrHrList = new ArrayList<>();
		CurrHrItem currHrItem;
		List<HisHrData> hisHrDataList = hisHrDataService.hisHrDataList(dtuIds);
		List<LMU> lmuList = lmuService.LMUlistbyDtuId(dtuIds);
		for (LMU lmu : lmuList) {
			currHrItem = new CurrHrItem();
			currHrItem.setId(lmu.getId());
			currHrItem.setName(lmu.getDtu().getName() + "-" + lmu.getName());
			for (HisHrData hisHrData : hisHrDataList) {
				if (lmu.getId() == hisHrData.getLmuId()) {
					currHrItem.setHr0(hisHrData.getHr0());
					currHrItem.setHr4(hisHrData.getHr4());
					currHrItem.setHr5(hisHrData.getHr5());
					currHrItem.setHr6(hisHrData.getHr6());
					currHrItem.setHr7(hisHrData.getHr7());
					currHrItem.setHr8(hisHrData.getHr8());
					currHrItem.setHr9(hisHrData.getHr9());
					currHrItem.setHr10(hisHrData.getHr10());
					currHrItem.setHr11(hisHrData.getHr11());
					currHrItem.setHr12(hisHrData.getHr12());
					currHrItem.setHr13(hisHrData.getHr13());
					currHrItem.setHr14(hisHrData.getHr14());
					currHrItem.setHr15(hisHrData.getHr15());
					currHrItem.setHr16(hisHrData.getHr16());
					currHrItem.setHr17(hisHrData.getHr17());
					currHrItem.setHr18(hisHrData.getHr18());
					currHrItem.setHr19(hisHrData.getHr19());
					currHrItem.setHr20(hisHrData.getHr20());
					currHrItem.setAddTime(paramers.datetimeformate.format(hisHrData.getAddtime().getTime()));
					break;
				}
			}
			arrHrList.add(currHrItem);
		}
		return arrHrList;
	}
}
