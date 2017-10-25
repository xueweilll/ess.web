package com.protocol;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import com.hibernate.entity.Sensor;
import com.service.AlarmMsgService;
import com.service.SensorService;
import com.unit.LmuMsgQuence;
import com.unit.PartsQuence;
@Service("wSProtocol")
public class WSProtocol implements YZCProtocol{
	@Autowired
	private PartsQuence partsQuence;
	@Autowired
	private SensorService sensorService;
	@Autowired
	private LmuMsgQuence lmuMsgQuence;
	@Autowired
	private AlarmMsgService alarmMsgService;
	@Override
	public byte[] readCmd(int code) {
		byte[] cmd = new byte[8];
		cmd[0] = (byte) code;
		cmd[1] = 0x03;
		cmd[2] = 0x00;
		cmd[3] = 0x00;
		cmd[4] = 0x00;
		cmd[5] = 0x02;
		CRC crc = new CRC();
		crc.CrcMake(6, cmd);
		cmd[6] = crc.getBYCRCHi();
		cmd[7] = crc.getBYCRCLo();
		return cmd;
	}

	@Override
	public void anlysis(byte[] data,IoSession session) {
		CRC crc = new CRC();
		if(crc.crc_check(7, data) != 0){
			Map<String, Object> map = new HashMap<String, Object>();
			int wd = 0;
			int sd = 0;
			//data[3],data[4];
			wd = Convert.ArrByteToInt(data,3,2);
			sd = Convert.ArrByteToInt(data,5,2);
			DTU dtu = (DTU) session.getAttribute("dtu");
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
			//温度
			sensor.setV2(new BigDecimal(String.valueOf(wd)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
			map.put("wd", String.valueOf(new BigDecimal(String.valueOf(wd)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP)));
			//湿度
			sensor.setV3(new BigDecimal(String.valueOf(sd)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
			map.put("sd", String.valueOf(new BigDecimal(String.valueOf(sd)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP)));
			sensor.setAddtime(Calendar.getInstance());
			try {
				sensorService.insert(sensor);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//是否温度报警
			map.put("wdPolice", 0);
			//温度报警次数
			map.put("wdTime", 0);
			//温度报警时间
			map.put("wdStartWarnTime","");
			//判断氨气上下限是否存在，2为温度下限，3，为湿度上下限 ，根据表lmulimit的type字段来定义，和lmu表的code无关
			if(lmuMsgQuence.get(2) != null){
				Map<String, Object> mapLimit = lmuMsgQuence.get(2);
				//默认温度下限为0
				BigDecimal wddown = mapLimit.get("down") != null ? new BigDecimal(mapLimit.get("down").toString()) : new BigDecimal(0);
				//默认温度上限为0
				BigDecimal wdup = mapLimit.get("up") != null ? new BigDecimal(mapLimit.get("up").toString()) : new BigDecimal(30);
				//默认报警频率为1
				int wdrate = mapLimit.get("rate") != null ? Integer.parseInt(mapLimit.get("rate").toString()) : 10;
				//系统启动第一次
				int wdlow = sensor.getV2().compareTo(wddown);
				int wdhigh =  sensor.getV2().compareTo(wdup);
				if(partsQuence.get(sensor.getLmu().getId()) == null){
					if(wdlow == -1 || wdhigh == 1){
						//第一次报警次数
						map.put("wdTime", 1);
					}
				}else{
					int wdTime = Integer.parseInt(partsQuence.get(sensor.getLmu().getId()).get("wdTime").toString());
					if(wdlow == -1 || wdhigh == 1){
						wdTime ++;
						if(wdTime > wdrate && wdTime - wdrate == 1){
							String hql = "from AlarmMsg where Status = 0 and Type = ? and LmuId = ? and DtuId = ?";
							Object[] obj = new Object[]{2,sensor.getLmu().getId(),sensor.getDtuId()};
							List<AlarmMsg> list = alarmMsgService.getAlarmMsg(hql, obj);
							if(list.size() == 0){
								map.put("wdPolice", 1);
								AlarmMsg alarmMsg = new AlarmMsg();
								alarmMsg.setAddtime(Calendar.getInstance());
								alarmMsg.setDtuId(sensor.getDtuId());
								alarmMsg.setLmuId(sensor.getLmu().getId());
								alarmMsg.setType(2);
								alarmMsg.setInfomation("当前温度为："+sensor.getV2()+"°C");
								if(wdlow == -1){
									alarmMsg.setConfirm(0);
								}else{
									alarmMsg.setConfirm(1);
								}
								map.put("wdStartWarnTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
								alarmMsgService.saveAlarmMsg(alarmMsg);
							}
						}
						map.put("wdTime", wdTime);
					}
				}
			}	
			
			//是否湿度报警
			map.put("sdPolice", 0);
			//湿度报警次数
			map.put("sdTime", 0);
			//湿度报警时间
			map.put("sdStartWarnTime","");
			
			
			//判断氨气上下限是否存在，2为温度下限，3，为湿度上下限 ，根据表lmulimit的type字段来定义，和lmu表的code无关
			if(lmuMsgQuence.get(3) != null){
				Map<String, Object> mapLimit = lmuMsgQuence.get(3);
				//默认温度下限为0
				BigDecimal sddown = mapLimit.get("down") != null ? new BigDecimal(mapLimit.get("down").toString()) : new BigDecimal(30);
				//默认温度上限为0
				BigDecimal sdup = mapLimit.get("up") != null ? new BigDecimal(mapLimit.get("up").toString()) : new BigDecimal(80);
				//默认报警频率为1
				int sdrate = mapLimit.get("rate") != null ? Integer.parseInt(mapLimit.get("rate").toString()) : 10;
				//系统启动第一次
				int sdlow = sensor.getV3().compareTo(sddown);
				int sdhigh =  sensor.getV3().compareTo(sdup);
				if(partsQuence.get(sensor.getLmu().getId()) == null){
					if(sdlow == -1 || sdhigh == 1){
						//第一次报警次数
						map.put("sdTime", 1);
					}
				}else{
				int sdTime = Integer.parseInt(partsQuence.get(sensor.getLmu().getId()).get("sdTime").toString());
					if(sdlow == -1 || sdhigh == 1){
						sdTime ++;
						if(sdTime > sdrate && sdTime - sdrate == 1){
							String hql = "from AlarmMsg where Status = 0 and Type = ? and LmuId = ? and DtuId = ?";
							Object[] obj = new Object[]{3,sensor.getLmu().getId(),sensor.getDtuId()};
							List<AlarmMsg> list = alarmMsgService.getAlarmMsg(hql, obj);
							if(list.size() == 0){
								map.put("sdPolice", 1);
								AlarmMsg alarmMsg = new AlarmMsg();
								alarmMsg.setAddtime(Calendar.getInstance());
								alarmMsg.setDtuId(sensor.getDtuId());
								alarmMsg.setLmuId(sensor.getLmu().getId());
								alarmMsg.setType(3);
								alarmMsg.setInfomation("当前湿度为："+sensor.getV3()+"%");
								if(sdlow == -1){
									alarmMsg.setConfirm(0);
								}else{
									alarmMsg.setConfirm(1);
								}
								map.put("sdStartWarnTime",  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
								alarmMsgService.saveAlarmMsg(alarmMsg);
							}
						}
						map.put("sdTime", sdTime);
					}
				}
			}	
			
			
			
			
			partsQuence.put(sensor.getLmu().getId(),map);
		}
	}

}
