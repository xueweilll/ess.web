package com.protocol;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.entity.AlarmMsg;
import com.hibernate.entity.DTU;
import com.hibernate.entity.LMU;
import com.hibernate.entity.PartSensor;
import com.hibernate.entity.Sensor;
import com.service.AlarmMsgService;
import com.service.SensorService;
import com.unit.LmuMsgQuence;
import com.unit.MessageQueue;
import com.unit.PartsQuence;
@Service("sDProtocol")
public class SDProtocol implements YZCProtocol{
	@Autowired
	private PartsQuence partsQuence;
	@Autowired
	private SensorService sensorService;
	@Autowired
	private LmuMsgQuence lmuMsgQuence;
	@Autowired
	private AlarmMsgService alarmMsgService;
	@Autowired
	private MessageQueue messageQueue;
	
	@Override
	public byte[] readCmd(int code) {
		byte[] cmd = new byte[8];
		cmd[0] = (byte) code;
		cmd[1] = 0x03;
		cmd[2] = 0x07;
		cmd[3] = 0x00;
		cmd[4] = 0x00;
		cmd[5] = 0x08;
		CRC crc = new CRC();
		crc.CrcMake(6, cmd);
		cmd[6] = crc.getBYCRCHi();
		cmd[7] = crc.getBYCRCLo();
		return cmd;
	}

	@Override
	public void anlysis(byte[] data,IoSession session) {
		CRC crc = new CRC();
		if(crc.crc_check(18, data)==0){
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				//data[3],data[4];
				BigDecimal v4 = new BigDecimal(Convert.ArrByteToInt(data,3,2));
				BigDecimal v5 = new BigDecimal(Convert.ArrByteToInt(data,5,2));
				BigDecimal v6 = new BigDecimal(Convert.ArrByteToInt(data,7,2));
				BigDecimal v7 = new BigDecimal(Convert.ArrByteToInt(data,9,2));
				BigDecimal v8 = new BigDecimal(Convert.ArrByteToInt(data,11,2));
				BigDecimal v9 = new BigDecimal(Convert.ArrByteToInt(data,13,2));
				BigDecimal v10 = new BigDecimal(Convert.ArrByteToInt(data,15,2));
				BigDecimal v11 = new BigDecimal(Convert.ArrByteToInt(data,17,2));
				int dtuId = (int) session.getAttribute("dtuId");
				DTU dtu = null;
				for(Map.Entry<DTU, PartSensor> e: messageQueue.getCacheData().entrySet() ){
					if(e.getKey().getId() == dtuId){
						dtu = e.getKey();
						break;
					}
				}
				if(dtu == null){
					return;
				}
				Sensor sensor = new Sensor();
				sensor.setId(UUID.randomUUID().toString());
				sensor.setDtuId(String.valueOf(dtu.getId()));
				for (LMU lmu : dtu.getLmus()) {
					if (lmu.getCode() == data[0]) {
						sensor.setLmu(lmu);
						break;
					}
				}
				sensor.setDtuName(dtu.getName());
				sensor.setV4(v4);
				sensor.setV5(v5);
				sensor.setV6(v6);
				sensor.setV7(v7);
				sensor.setV8(v8);
				sensor.setV9(v9);
				sensor.setV10(v10);
				sensor.setV11(v11);
				sensor.setAddtime(Calendar.getInstance());
				try {
					int nqId = 0;
					int wsId = 0;
					for (LMU lmu : dtu.getLmus()) {
						if(lmu.getType() == 1){
							nqId = lmu.getId();
						}
						if(lmu.getType() == 2){
							wsId = lmu.getId();
						}
					}
					if(partsQuence.get(nqId) != null){
						sensor.setV1(new BigDecimal(partsQuence.get(nqId).get("v1").toString()));
					}
					if(partsQuence.get(wsId) != null){
						sensor.setV2(new BigDecimal(partsQuence.get(wsId).get("v2").toString()));
						sensor.setV3(new BigDecimal(partsQuence.get(wsId).get("v3").toString()));
					}
					sensorService.insert(sensor);
				} catch (Exception e) {
					e.printStackTrace();
				}
				map.put("v4", String.valueOf(v4));
				map.put("v5", String.valueOf(v5));
				map.put("v6", String.valueOf(v6));
				map.put("v7", String.valueOf(v7));
				map.put("v8", String.valueOf(v8));
				map.put("v9", String.valueOf(v9));
				map.put("v10", String.valueOf(v10));
				map.put("v11", String.valueOf(v11));
				for (int i = 4; i < 12 ; i++) {
					map.put("v"+i+"Police", 0);
					map.put("v"+i+"StartWarnTime", "");
				}
				//以下代码有点重复，不高兴用批处理了，hibernate不想用
				if(lmuMsgQuence.get(sensor.getLmu().getId()) != null && !lmuMsgQuence.get(sensor.getLmu().getId()).get("type").toString().equals("0")){
					List<Object[]> list = new ArrayList<Object[]>();
					BigDecimal v4rate = lmuMsgQuence.get(sensor.getLmu().getId()).get("sd1") != null ? new BigDecimal(lmuMsgQuence.get(sensor.getLmu().getId()).get("sd1").toString()) : new BigDecimal(0);
					list.add(new Object[]{sensor.getV4(),v4rate,"市电一路",4});
					BigDecimal v5rate = lmuMsgQuence.get(sensor.getLmu().getId()).get("sd2") != null ? new BigDecimal(lmuMsgQuence.get(sensor.getLmu().getId()).get("sd2").toString()) : new BigDecimal(0);
					list.add(new Object[]{sensor.getV5(),v5rate,"市电二路",5});
					BigDecimal v6rate = lmuMsgQuence.get(sensor.getLmu().getId()).get("sd3") != null ? new BigDecimal(lmuMsgQuence.get(sensor.getLmu().getId()).get("sd3").toString()) : new BigDecimal(0);
					list.add(new Object[]{sensor.getV6(),v6rate,"市电三路",6});
					BigDecimal v7rate = lmuMsgQuence.get(sensor.getLmu().getId()).get("sd4") != null ? new BigDecimal(lmuMsgQuence.get(sensor.getLmu().getId()).get("sd4").toString()) : new BigDecimal(0);
					list.add(new Object[]{sensor.getV7(),v7rate,"市电四路",7});
					BigDecimal v8rate = lmuMsgQuence.get(sensor.getLmu().getId()).get("sd5") != null ? new BigDecimal(lmuMsgQuence.get(sensor.getLmu().getId()).get("sd5").toString()) : new BigDecimal(0);
					list.add(new Object[]{sensor.getV8(),v8rate,"市电五路",8});
					BigDecimal v9rate = lmuMsgQuence.get(sensor.getLmu().getId()).get("sd6") != null ? new BigDecimal(lmuMsgQuence.get(sensor.getLmu().getId()).get("sd6").toString()) : new BigDecimal(0);
					list.add(new Object[]{sensor.getV9(),v9rate,"市电六路",9});
					BigDecimal v10rate = lmuMsgQuence.get(sensor.getLmu().getId()).get("sd7") != null ? new BigDecimal(lmuMsgQuence.get(sensor.getLmu().getId()).get("sd7").toString()) : new BigDecimal(0);
					list.add(new Object[]{sensor.getV10(),v10rate,"市电七路",10});
					BigDecimal v11rate = lmuMsgQuence.get(sensor.getLmu().getId()).get("sd8") != null ? new BigDecimal(lmuMsgQuence.get(sensor.getLmu().getId()).get("sd8").toString()) : new BigDecimal(0);	
					list.add(new Object[]{sensor.getV11(),v11rate,"市电八路",11});
					
					
					for (Object[] objects : list) {
						if(new BigDecimal(String.valueOf(objects[0])).compareTo(new BigDecimal(String.valueOf(objects[1]))) != 0){
							map.put("v"+objects[3]+"Police", 1);
							map.put("v"+objects[3]+"StartWarnTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
							if(partsQuence.get(sensor.getLmu().getId()) == null){
								//判断是否存在报警记录
								AlarmMsg alarmMsg = insertAlarmMsg(sensor.getDtuId(), sensor.getLmu().getId(),String.valueOf(objects[2]),new BigDecimal(String.valueOf(objects[0])),new BigDecimal(String.valueOf(objects[1])),Integer.parseInt(String.valueOf(objects[3])));
								alarmMsgService.saveAlarmMsg(alarmMsg);
								map.put("v"+objects[3]+"AlarmMsg", alarmMsg.getId());
							}else{
								if(Integer.parseInt(partsQuence.get(sensor.getLmu().getId()).get("v"+objects[3]+"AlarmMsg").toString()) == 0){
									AlarmMsg alarmMsg = insertAlarmMsg(sensor.getDtuId(), sensor.getLmu().getId(),String.valueOf(objects[2]),new BigDecimal(String.valueOf(objects[0])),new BigDecimal(String.valueOf(objects[1])),Integer.parseInt(String.valueOf(objects[3])));
									alarmMsgService.saveAlarmMsg(alarmMsg);
									map.put("v"+objects[3]+"AlarmMsg", alarmMsg.getId());
								}else{
									map.put("v"+objects[3]+"AlarmMsg", partsQuence.get(sensor.getLmu().getId()).get("v"+objects[3]+"AlarmMsg"));
								}
							}
						}else{
							map.put("v"+objects[3]+"AlarmMsg", 0);
						}
						
					}
				}
				//递增代表最新消息
				if(partsQuence.get(sensor.getLmu().getId()) == null){
					map.put("sdDestoryTime", 1);
				}else{
					map.put("sdDestoryTime", Integer.parseInt(partsQuence.get(sensor.getLmu().getId()).get("sdDestoryTime").toString()) + 1);
				}
				partsQuence.put(sensor.getLmu().getId(), map);
			} catch (Exception e) {
				e.printStackTrace();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < data.length; i++) {
					sb.append(data[i] +" ");
				}
				DTU dtu = (DTU) session.getAttribute("dtu");
				System.out.println(dtu.getName()+",id:"+dtu.getId()+",(氨气)数据处理异常"+sb.toString());
			}
		}	
	}
	
	private AlarmMsg insertAlarmMsg(String dtuId,int lmuId,String name,BigDecimal value,BigDecimal rate,int type){
		AlarmMsg alarmMsg = new AlarmMsg();
		alarmMsg.setAddtime(Calendar.getInstance());
		alarmMsg.setDtuId(dtuId);
		alarmMsg.setLmuId(lmuId);
		alarmMsg.setType(type);
		String bg = value.compareTo(new BigDecimal(0)) == 0 ? "断开":"常通";
		alarmMsg.setInfomation(name+"："+ bg);
		if(rate.compareTo(new BigDecimal(0)) == 0){
			alarmMsg.setConfirm(4);
		}else{
			alarmMsg.setConfirm(3);
		}
		return alarmMsg;
		
	}
	
	
	public static void main(String[] args){
		byte[] data = new byte[21];
		data[0]=0x03;
		data[1]=0x03;
		data[2]=0x10;
		data[3]=0x00;
		data[4]=0x01;
		data[5]=0x00;
		data[6]=0x01;
		data[7]=0x00;
		data[8]=0x01;
		data[9]=0x00;
		data[10]=0x01;
		data[11]=0x00;
		data[12]=0x00;
		data[13]=0x00;
		data[14]=0x00;
		data[15]=0x00;
		data[16]=0x00;
		data[17]=0x00;
		data[18]=0x00;
		data[19]=(byte) 0x2E;
		data[20]=(byte) 0xB4;
		
		new SDProtocol().anlysis(data,null);
	}

	
	
	
}
