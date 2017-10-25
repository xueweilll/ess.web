package com.protocol;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.entity.AlarmMsg;
import com.hibernate.entity.DTU;
import com.hibernate.entity.LMU;
import com.hibernate.entity.PartSensor;
import com.service.AlarmMsgService;
import com.unit.LmuMsgQuence;
import com.unit.MessageQueue;
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
	private LmuMsgQuence lmuMsgQuence;
	@Autowired
	private AlarmMsgService alarmMsgService;
	@Autowired
	private MessageQueue messageQueue;
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
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				int ppm = 0;
				int lmuId = 0;
				//data[3],data[4];
				ppm = Convert.ArrByteToInt(data,3,2);
				int dtuId = (int) session.getAttribute("dtuId");
				DTU dtu = null;
				for(Map.Entry<DTU, PartSensor> e: messageQueue.getCacheData().entrySet() ){
					if(e.getKey().getId() == dtuId){
						dtu = e.getKey();
					}
				}
				if(dtu == null){
					return;
				}
				
				for (LMU lmu : dtu.getLmus()) {
					if (lmu.getCode() == data[0]) {
						lmuId = lmu.getId();
						break;
					}
				}
				
				map.put("v1",new BigDecimal(String.valueOf(ppm)).setScale(2, BigDecimal.ROUND_HALF_UP));
				//是否报警
				map.put("v1Police", 0);
				//报警次数
				map.put("v1Time", 0);
				//报警时间
				map.put("v1StartWarnTime","");
				//判断氨气上下限是否存在，1为氨气上下限，根据表lmulimit的type字段来定义，和lmu表的code无关
				if(lmuMsgQuence.get(lmuId) != null && !lmuMsgQuence.get(lmuId).get("type").toString().equals("0")){
					Map<String, Object> mapLimit = lmuMsgQuence.get(lmuId);
					//默认氨气上限为0
					BigDecimal up = mapLimit.get("a1") != null ? new BigDecimal(mapLimit.get("a1").toString()) : new BigDecimal(100);
					//默认氨气下限为0
					BigDecimal down = mapLimit.get("a2") != null ? new BigDecimal(mapLimit.get("a2").toString()) : new BigDecimal(0);
					//默认报警频率为1
					int rate = mapLimit.get("arate") != null ? Integer.parseInt(mapLimit.get("arate").toString()) : 10;
					//系统启动第一次
					int low = new BigDecimal(String.valueOf(ppm)).setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(down);
					int high =  new BigDecimal(String.valueOf(ppm)).setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(up);
					if(partsQuence.get(lmuId) == null){
						if(low == -1 || high == 1){
							//第一次报警次数
							map.put("v1Time", 1);
						}
					}else{
						int aqTime = Integer.parseInt( partsQuence.get(lmuId).get("v1Time").toString());
						if(low == -1 || high == 1){
							aqTime ++;
							if(aqTime > rate){
								map.put("v1Police", 1);
								map.put("v1StartWarnTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
								if(aqTime - rate == 1){
									/*String hql = "from AlarmMsg where Status = 0 and Type = ? and LmuId = ? and DtuId = ?";
									Object[] obj = new Object[]{1,sensor.getLmu().getId(),sensor.getDtuId()};
									List<AlarmMsg> list = alarmMsgService.getAlarmMsg(hql, obj);*/
									/*if(list.size() == 0){
										
									}*/
									AlarmMsg alarmMsg = new AlarmMsg();
									alarmMsg.setAddtime(Calendar.getInstance());
									alarmMsg.setDtuId(String.valueOf(dtuId));
									alarmMsg.setLmuId(lmuId);
									alarmMsg.setType(1);
									alarmMsg.setInfomation("当前氨气浓度为："+new BigDecimal(String.valueOf(ppm)).setScale(2, BigDecimal.ROUND_HALF_UP)+"ppm");
									alarmMsg.setValue(Double.valueOf(new BigDecimal(String.valueOf(ppm)).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
									if(low == -1){
										alarmMsg.setConfirm(0);
									}else{
										alarmMsg.setConfirm(1);
									}
									alarmMsgService.saveAlarmMsg(alarmMsg);
									map.put("v1AlarmMsg", alarmMsg.getId());
								}else{
									map.put("v1AlarmMsg",  partsQuence.get(lmuId).get("v1AlarmMsg"));
								}
							}
							map.put("v1Time", aqTime);
						}else{
							map.put("v1AlarmMsg", 0);
						}
					}
				}	
				//递增代表最新消息
				if(partsQuence.get(lmuId) == null){
					map.put("aqDestoryTime", 1);
				}else{
					map.put("aqDestoryTime", Integer.parseInt(partsQuence.get(lmuId).get("aqDestoryTime").toString()) + 1);
				}
				partsQuence.put(lmuId, map);
			
			} catch (Exception e) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < data.length; i++) {
					sb.append(data[i] +" ");
				}
				DTU dtu = (DTU) session.getAttribute("dtu");
				System.out.println(dtu.getName()+",id:"+dtu.getId()+",(氨气)数据处理异常"+sb.toString());
			}
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
