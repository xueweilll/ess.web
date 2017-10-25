package com.protocol;

public class ReadR {
	// CRC crc = CRC.CreateCRC();
	private CRC crc = null;

	// ��ȡ����IR
	public byte[] SendReadIRCommand(int DeviceAddress) {
		// int CRCCode;
		byte[] data = new byte[8];
		data[0] = (byte) DeviceAddress;
		data[1] = 0x04;
		data[2] = 0x00;
		data[3] = 0x00;
		data[4] = 0x00;
		data[5] = 0x0C;
		crc = new CRC();
		crc.CrcMake(6, data);
		data[6] = crc.getBYCRCHi();
		data[7] = crc.getBYCRCLo();
		// data[6]=crc.BYCRCHi;
		// data[7]=crc.BYCRCLo;
		return data;
	}

	public byte[] SendReadIRCommandByGroup(int num0, int num1, int num2) {
		byte[] data = new byte[11];
		data[0] = 0x55;
		data[1] = (byte) 0xAA;
		data[2] = 0x14;
		data[3] = (byte) num0;
		data[4] = (byte) num1;
		data[5] = 0x07;
		data[6] = (byte) num2;
		data[7] = (byte) 0xCC;
		data[8] = 0x33;
		data[9] = (byte) 0xC3;
		data[10] = 0x3C;
		return data;
	}

	// 04 03 01 36 00 03 E4 6C --��ȡ��ѹ����������
	// 07 03 01 30 00 21 84 47 --��ȡȫ��
	public byte[] SendReadEqCommand(int DeviceAddress) {
		byte[] data = new byte[8];
		data[0] = (byte) DeviceAddress;
		data[1] = 0x03;
		data[2] = 0x01;
		// data[3]=0x36;
		data[3] = 0x30;
		data[4] = 0x00;
		// data[5]=0x03;
		data[5] = 0x21;
		crc.CrcMake(6, data);
		data[6] = crc.getBYCRCHi();
		data[7] = crc.getBYCRCLo();
		return data;
	}

	public String GetReadIRCom(int DeviceAddress) {
		String str = "";
		byte[] by = SendReadIRCommand(DeviceAddress);
		for (int i = 0; i < by.length; i++) {
			str += Convert.IntToHex(CRC.toUnsiged(by[i])) + " ";
		}
		str = str.substring(0, str.length() - 1);
		// System.out.println(str);
		return str;
	}

	// ��ȡ����HR
	public byte[] SendReadHRCommand(int DeviceAddress) {
		// int CRCCode;
		byte[] data = new byte[8];
		data[0] = (byte) DeviceAddress;
		data[1] = 0x03;
		data[2] = 0x00;
		data[3] = 0x00;
		data[4] = 0x00;
		data[5] = 0x20;
		crc = new CRC();
		crc.CrcMake(6, data);
		data[6] = crc.getBYCRCHi();
		data[7] = crc.getBYCRCLo();
		return data;
	}

	public String GetReadHRCom(int DeviceAddress) {
		String str = "";
		byte[] by = SendReadHRCommand(DeviceAddress);
		for (int i = 0; i < by.length; i++) {
			str += Convert.IntToHex(CRC.toUnsiged(by[i])) + " ";
		}
		str = str.substring(0, str.length() - 1);
		// System.out.println(str);
		return str;
	}

	public byte[] SendReadHRCommandByGroup(int num0, int num1, int num2) {
		byte[] data = new byte[11];
		data[0] = (byte) 0x55;
		data[1] = (byte) 0xAA;
		data[2] = 0x13;
		data[3] = (byte) num0;
		data[4] = (byte) num1;
		data[5] = 0x07;
		data[6] = (byte) num2;
		data[7] = (byte) 0xCC;
		data[8] = 0x33;
		data[9] = (byte) 0xC3;
		data[10] = 0x3C;
		return data;
	}

	public String GetReadHRComByGroup(int num0, int num1, int num2) {
		String str = "";
		byte[] by = SendReadHRCommandByGroup(num0, num1, num2);
		for (int i = 0; i < by.length; i++) {
			str += Convert.IntToHex(CRC.toUnsiged(by[i])) + " ";
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

	public static void main(String[] agrs) {
		String str = new ReadR().GetReadHRComByGroup(1, 6, 2);

		System.out.println(str);
	}
}
