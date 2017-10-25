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

/** 
* @ClassName: NQProtocol 
* @Description: TODO(氨气传感器协议) 
* @author lyf-laptop 
* @date 2016年11月25日 下午2:26:29 
*  
*/
@Service("nQProtocol")
public class NQProtocol implements YZCProtocol {
	@Autowired
	private PartsQuence partsQuence;
	@Autowired
	private SensorService sensorService;
	@Autowired
	private LmuMsgQuence lmuMsgQuence;
	@Autowired
	private AlarmMsgService alarmMsgService;
	/* (非 Javadoc) 
	* <p>Title: readCmd</p> 
	* <p>Description: 读取资料</p> 
	* @return 
	* @see com.protocol.YZCProtocol#readCmd() 
	*/
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

	/* (非 Javadoc) 
	* <p>Title: anlysis</p> 
	* <p>Description: 解析指令</p> 
	* @param data
	* @return 
	* @see com.protocol.YZCProtocol#anlysis(byte[]) 
	*/
	@Override
	public void anlysis(byte[] data,IoSession session) {
		
		CRC crc = new CRC();
		if(crc.crc_check(7, data) != 0){
			Map<String, Object> map = new HashMap<String, Object>();
			int ppm = 0;
			//data[3],data[4];
			ppm = Convert.ArrByteToInt(data,3,2);
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
			//氨气
			sensor.setV1(new BigDecimal(String.valueOf(ppm)).setScale(2, BigDecimal.ROUND_HALF_UP));
			sensor.setAddtime(Calendar.getInstance());
			
			try {
				sensorService.insert(sensor);
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("ppm",String.valueOf(new BigDecimal(String.valueOf(ppm)).setScale(2, BigDecimal.ROUND_HALF_UP)));
			//是否报警
			map.put("aqPolice", 0);
			//报警次数
			map.put("aqTime", 0);
			//报警时间
			map.put("aqStartWarnTime","");
			//判断氨气上下限是否存在，1为氨气上下限，根据表lmulimit的type字段来定义，和lmu表的code无关
			if(lmuMsgQuence.get(1) != null){
				Map<String, Object> mapLimit = lmuMsgQuence.get(1);
				//默认氨气下限为0
				BigDecimal down = mapLimit.get("down") != null ? new BigDecimal(mapLimit.get("down").toString()) : new BigDecimal(0);
				//默认氨气上限为0
				BigDecimal up = mapLimit.get("up") != null ? new BigDecimal(mapLimit.get("up").toString()) : new BigDecimal(100);
				//默认报警频率为1
				int rate = mapLimit.get("rate") != null ? Integer.parseInt(mapLimit.get("rate").toString()) : 10;
				//系统启动第一次
				int low = sensor.getV1().compareTo(down);
				int high =  sensor.getV1().compareTo(up);
				if(partsQuence.get(sensor.getLmu().getId()) == null){
					if(low == -1 || high == 1){
						//第一次报警次数
						map.put("aqTime", 1);
					}
				}else{
					int aqTime = Integer.parseInt( partsQuence.get(sensor.getLmu().getId()).get("aqTime").toString());
					if(low == -1 || high == 1){
						aqTime ++;
						if(aqTime > rate && aqTime - rate == 1){
							String hql = "from AlarmMsg where Status = 0 and Type = ? and LmuId = ? and DtuId = ?";
							Object[] obj = new Object[]{1,sensor.getLmu().getId(),sensor.getDtuId()};
							List<AlarmMsg> list = alarmMsgService.getAlarmMsg(hql, obj);
							if(list.size() == 0){
								map.put("aqPolice", 1);
								AlarmMsg alarmMsg = new AlarmMsg();
								alarmMsg.setAddtime(Calendar.getInstance());
								alarmMsg.setDtuId(sensor.getDtuId());
								alarmMsg.setLmuId(sensor.getLmu().getId());
								alarmMsg.setType(1);
								alarmMsg.setInfomation("当前氨气浓度为："+sensor.getV1()+"ppm");
								if(low == -1){
									alarmMsg.setConfirm(0);
								}else{
									alarmMsg.setConfirm(1);
								}
								map.put("aqStartWarnTime",  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
								alarmMsgService.saveAlarmMsg(alarmMsg);
							}
						}
						map.put("aqTime", aqTime);
					}
				}
			}	
			partsQuence.put(sensor.getLmu().getId(), map);
		}
	}
 
	public static void main(String[] args){
		byte[] data = new byte[9];
		data[0]=0x01;
		data[1]=0x03;
		data[2]=0x04;
		data[3]=0x01;
		data[4]=(byte) 0xB1;
		data[5]=0x00;
		data[6]=0x00;
		data[7]=(byte) 0xAB;
		data[8]=(byte) 0xE8;
		
		int i = 0;
		System.out.println(i++);
		int j = 0;
		System.out.println(++j);
		
		System.out.println(i);
		System.out.println(j);
		//new NQProtocol().anlysis(data);
	}
}
