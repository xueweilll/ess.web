package com.protocol;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.hibernate.entity.CurrentIrData;
import com.message.ResMgr;
import com.service.CurrentIrDataService;

public class IRAnalysis extends Analysis {

	public IRAnalysis(byte[] buff,int dtuId) {
		super(buff, dtuId);
		currentIrData = new CurrentIrData();
	}
	
	private CurrentIrData currentIrData;
	@Autowired
	private CurrentIrDataService currIrDataService;

	@Override
	public void registers() throws Exception {
		currentIrData.setId(UUID.randomUUID().toString());
		currentIrData.setDtuId(dtuId);
		lmuId = (int) (buff[0]);
		currentIrData.setLmuId(lmuId);
		currentIrData.setAddtime(cal);
		currentIrData.setIr0(String.valueOf(df.format(Convert.toUnsiged(buff[3])
				* 256
				+ Convert.toUnsiged(buff[4])
				+ (Convert.toUnsiged(buff[5]) * 256 + Convert
						.toUnsiged(buff[6])) * 0.1)));// 目前电压
		currentIrData.setIr1(String.valueOf(df.format((Convert
				.toUnsiged(buff[7]) * 256 + Convert.toUnsiged(buff[8]))
				+ (Convert.toUnsiged(buff[9]) * 256 + Convert
						.toUnsiged(buff[10])) * 0.1)));// 目前电流
		switch ((Convert.toUnsiged(buff[11]) * 256 + Convert
				.toUnsiged(buff[12]))) {
		case 0:
			currentIrData.setIr2(ResMgr.CreateResMgr().GetStr(
					"LMU_STATUS_PRELOAD"));
			break;
		case 1:
			currentIrData.setIr2(ResMgr.CreateResMgr().GetStr(
					"LMU_STATUS_WORKING"));
			break;
		case 2:
			currentIrData.setIr2(ResMgr.CreateResMgr()
					.GetStr("LMU_STATUS_DEBUG"));
			break;
		case 3:
			currentIrData.setIr2( ResMgr.CreateResMgr().GetStr(
					"LMU_STATUS_EXCEPTION"));
			currentIrData.setIr7(ExceptionCodes((buff[25] * 256 + buff[26])));
			break;
		default:
			currentIrData.setIr2("Null");
			break;
		}
		currentIrData.setIr3(String.valueOf(Convert.toUnsiged(buff[13]) * 256
				+ Convert.toUnsiged(buff[14])));// 调节器位置
		currentIrData.setIr4(String
				.valueOf((Convert.toUnsiged(buff[15]) * 256 + Convert
						.toUnsiged(buff[16]))
						* 65536
						+ (Convert.toUnsiged(buff[17]) * 256 + Convert
								.toUnsiged(buff[18]))));// 开机时间
		int Daytime = (Convert.toUnsiged(buff[19]) * 256 + Convert
				.toUnsiged(buff[20]))
				* 65536
				+ (Convert.toUnsiged(buff[21]) * 256 + Convert
						.toUnsiged(buff[22]));
		String second = String.valueOf(Daytime % 60);
		if (second.length() < 2) {
			second = "0" + second;
		}
		String hour = String.valueOf(Daytime / 60);
		if (hour.length() < 2) {
			hour = "0" + hour;
		}
		currentIrData.setIr5(hour + ":" + second);// 当前时间

		currentIrData.setIr6(String.valueOf(Convert.toUnsiged(buff[23]) * 256
				+ Convert.toUnsiged(buff[24])));// 当前温度
		currIrDataService.InsertCurrentIrData(currentIrData);
	}

	public int getLmuId() {
		return lmuId;
	}

	public void setLmuId(int lmuId) {
		this.lmuId = lmuId;
	}

}
