package com.protocol;

import org.springframework.beans.factory.annotation.Autowired;

import com.hibernate.entity.HisHrData;
import com.service.HisHrDataService;

public class HRAnalysis extends Analysis {

	public HRAnalysis(byte[] buff,int dtuId) {
		super(buff, dtuId);
		hisHrData = new HisHrData();
	}
	
	private HisHrData hisHrData;
	@Autowired
	private HisHrDataService hisHrDataService;

	@Override
	public void registers() throws Exception {
		hisHrData.setDtuId(dtuId);
		lmuId = (int) (buff[0]);
		hisHrData.setLmuId(lmuId);
		hisHrData.setAddtime(cal);
		hisHrData.setHr0(String.valueOf(Convert.toUnsiged(buff[3]) * 256
				+ Convert.toUnsiged(buff[4])));// 预热时间
		hisHrData.setHr1(String.valueOf(Convert.toUnsiged(buff[5]) * 256
				+ Convert.toUnsiged(buff[6])));// 休眠电流
		hisHrData.setHr2(String.valueOf(Convert.toUnsiged(buff[7]) * 256
				+ Convert.toUnsiged(buff[8])));// 唤醒电流
		hisHrData.setHr3(String.valueOf(Convert.toUnsiged(buff[9]) * 256
				+ Convert.toUnsiged(buff[10])));// 系统保留
		hisHrData.setHr4(String.valueOf(Convert.toUnsiged(buff[11])
				* 256 + Convert.toUnsiged(buff[12])));// 调压速度
		hisHrData.setHr5(FormatT(Convert.toUnsiged(buff[13]) * 256
				+ Convert.toUnsiged(buff[14])));// 调压时间1
		hisHrData.setHr6(String.valueOf(Convert.toUnsiged(buff[15])
				* 256 + Convert.toUnsiged(buff[16])));// 预设电压1
		hisHrData.setHr7(FormatT(Convert.toUnsiged(buff[17]) * 256
				+ Convert.toUnsiged(buff[18])));// 调压时间2
		hisHrData.setHr8(String.valueOf(Convert.toUnsiged(buff[19])
				* 256 + Convert.toUnsiged(buff[20])));// 预设电压2
		hisHrData.setHr9(FormatT(Convert.toUnsiged(buff[21]) * 256
				+ Convert.toUnsiged(buff[22])));// 调压时间3
		hisHrData.setHr10(String.valueOf(Convert.toUnsiged(buff[23])
				* 256 + Convert.toUnsiged(buff[24])));// 预设电压3
		hisHrData.setHr11(FormatT(Convert.toUnsiged(buff[25]) * 256
				+ Convert.toUnsiged(buff[26])));// 调压时间4
		hisHrData.setHr12(String.valueOf(Convert.toUnsiged(buff[27])
				* 256 + Convert.toUnsiged(buff[28])));// 预设电压4
		hisHrData.setHr13(FormatT(Convert.toUnsiged(buff[29]) * 256
				+ Convert.toUnsiged(buff[30])));// 调压时间5
		hisHrData.setHr14(String.valueOf(Convert.toUnsiged(buff[31])
				* 256 + Convert.toUnsiged(buff[32])));// 预设电压5
		hisHrData.setHr15(FormatT(Convert.toUnsiged(buff[33]) * 256
				+ Convert.toUnsiged(buff[34])));// 调压时间6
		hisHrData.setHr16(String.valueOf(Convert.toUnsiged(buff[35])
				* 256 + Convert.toUnsiged(buff[36])));// 预设电压6

		hisHrData.setHr17(FormatT(Convert.toUnsiged(buff[37]) * 256
				+ Convert.toUnsiged(buff[38])));// 调压时间7
		hisHrData.setHr18(String.valueOf(Convert.toUnsiged(buff[39])
				* 256 + Convert.toUnsiged(buff[40])));// 预设电压7
		hisHrData.setHr19(FormatT(Convert.toUnsiged(buff[41]) * 256
				+ Convert.toUnsiged(buff[42])));// 调压时间8
		hisHrData.setHr20(String.valueOf(Convert.toUnsiged(buff[43])
				* 256 + Convert.toUnsiged(buff[44])));// 预设电压8
		hisHrData.setHr21(String.valueOf(Convert.toUnsiged(buff[45])
				* 256 + Convert.toUnsiged(buff[46])));// 系统保留
		hisHrData.setHr22(String.valueOf(Convert.toUnsiged(buff[47])
				* 256 + Convert.toUnsiged(buff[48])));// 系统保留
		hisHrData.setHr23(String.valueOf(Convert.toUnsiged(buff[49])
				* 256 + Convert.toUnsiged(buff[50])));// 系统保留
		hisHrData.setHr24(String.valueOf(Convert.toUnsiged(buff[51])
				* 256 + Convert.toUnsiged(buff[52])));// 系统保留
		hisHrData.setHr25(String.valueOf(Convert.toUnsiged(buff[53])
				* 256 + Convert.toUnsiged(buff[54])));// 系统保留
		hisHrData.setHr26(String.valueOf(Convert.toUnsiged(buff[55])
				* 256 + Convert.toUnsiged(buff[56])));// 设备波特率
		hisHrData.setHr27(String.valueOf(Convert.toUnsiged(buff[57])
				* 256 + Convert.toUnsiged(buff[58])));// 目标电压
		hisHrData.setHr28(String.valueOf(Convert.toUnsiged(buff[59])
				* 256 + Convert.toUnsiged(buff[60])));// 设备软旁路
		hisHrDataService.InsertHisHrdata(hisHrData);
	}

}
