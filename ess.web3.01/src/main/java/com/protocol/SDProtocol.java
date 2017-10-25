package com.protocol;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.entity.DTU;
import com.hibernate.entity.LMU;
import com.hibernate.entity.Sensor;
import com.service.SensorService;
import com.unit.PartsQuence;
@Service("sDProtocol")
public class SDProtocol implements YZCProtocol{
	@Autowired
	private PartsQuence partsQuence;
	@Autowired
	private SensorService sensorService;
	
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
			sensor.setV4(v4);
			sensor.setV5(v5);
			sensor.setV6(v6);
			sensor.setV7(v7);
			sensor.setV8(v8);
			sensor.setV9(v9);
			sensor.setV10(v10);
			sensor.setV11(v11);
			sensor.setAddtime(Calendar.getInstance());
			map.put("v4", String.valueOf(v4));
			map.put("v5", String.valueOf(v5));
			map.put("v6", String.valueOf(v6));
			map.put("v7", String.valueOf(v7));
			map.put("v8", String.valueOf(v8));
			map.put("v9", String.valueOf(v9));
			map.put("v10", String.valueOf(v10));
			map.put("v11", String.valueOf(v11));
			try {
				sensorService.insert(sensor);
			} catch (Exception e) {
				e.printStackTrace();
			}
			partsQuence.put(sensor.getLmu().getId(), map);
		}	
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
