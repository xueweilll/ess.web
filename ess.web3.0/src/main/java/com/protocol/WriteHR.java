package com.protocol;

public class WriteHR {
	public static byte[] data = new byte[256];
	public static int index = 0;
	private CRC crc = null;

	/*
	 * 对寄存器进行写操作 返回8个命令字节
	 */
	// HRText曲线值
	// POS寄存器地址
	// DeviceAddress设备地址
	public byte[] SendWriteHRCommand(String HRText, int POS, int DeviceAddress) {
		crc = new CRC();
		// int CRCCode;
		byte[] data = new byte[8];
		data[0] = (byte) DeviceAddress;
		data[1] = 0x06;
		data[2] = (byte) (POS / 256);
		data[3] = (byte) (POS % 256);
		data[4] = (byte) (Integer.parseInt(HRText) / 256);
		data[5] = (byte) (Integer.parseInt(HRText) % 256);
		crc.CrcMake(6, data);
		data[6] = crc.getBYCRCHi();
		data[7] = crc.getBYCRCLo();
		// byte[] res = new byte[2];
		// CRC16.CheckCRC16(data, 6, res);
		// data[6]=res[0];
		// data[7]=res[1];
		return data;
	}

	public String GetWriteHrCmd(String HRText, int POS, int DeviceAddress) {
		String str = "";
		byte[] by = new WriteHR()
				.SendWriteHRCommand(HRText, POS, DeviceAddress);
		for (int i = 0; i < by.length; i++) {
			// str+=Convent.IntToHex(by[i])+" ";
			str += String.format("%1$02x", CRC.toUnsiged(by[i])) + " ";
		}
		return str.substring(0, str.length() - 1);
		// return CRC16.BitTo16(by, 8);
	}

	
	 public static void main(String[] args){ new
	 WriteHR().SendWriteHrCommandSingle("250", 1, 1); }
	 

	public byte[] SendWriteHrCommandSingle(String HRText, int POS,
			int DeviceAddress) {
		byte[] data = new byte[13];
		data[0] = (byte) 0xAA;
		byte[] singlebyte = SendWriteHRCommand(HRText, POS, DeviceAddress);
		for (int i = 0; i < singlebyte.length; i++) {
			data[i + 1] = singlebyte[i];
		}
		data[9] = (byte) 0xCC;
		data[10] = 0x33;
		data[11] = (byte) 0xC3;
		data[12] = 0x3C;
		return data;
	}

	/*public byte[] SendWriteHrCommandDouble() {
		byte[] data = new byte[11];
		data[0]=(byte) 0xAA;
		
		return data;
	}*/

}
