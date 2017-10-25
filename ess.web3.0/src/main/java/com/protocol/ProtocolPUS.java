package com.protocol;

public class ProtocolPUS implements Protocol {

	public final static int NUM_IR=12;
	public final static int NUM_HR=33;
	public static enum CMD_TYPE
	{
		CMD_VOID,
		CMD_READ_IR,
		CMD_READ_HR,
		CMD_WRITE_HR,
		CMD_WRITE_HR_PAIR
	}
	public final static int POS_TARGET_VOLTAGE=27;
	public final static int POS_TIME_PRELOAD = 0;
    public final static int TIME_PRELOAD_MIN = 2;
    public final static int TIME_PRELOAD_MAX = 180;

    public final static int POS_CURRENT_SLEEP = 1;
    public final static int CURRENT_SLEEP_MIN = 0;
    public final static int CURRENT_SLEEP_MAX = 100;

    public final static int POS_CURRENT_WAKEUP = 2;
    public final static int CURRENT_WAKEUP_MIN = 1;
    public final static int CURRENT_WAKEUP_MAX = 100;

    public final static int POS_VOLTAGE_ADJUSTMENT_RATE = 4;
    public final static int VOLTAGE_ADJUSTMENT_MIN = 1;
    public final static int VOLTAGE_ADJUSTMENT_MAX = 200;

    public final static int POS_BAUDRATE = 26;
    public final static int POS_BYPASS = 28;

    public final static int POS_TIME_HOUR = 29;
    public final static int TIME_HOUR_MIN = 0;
    public final static int TIME_HOUR_MAX = 23;

    public final static int POS_TIME_MINUTE = 30;
    public final static int TIME_MINUTE_MIN = 0;
    public final static int TIME_MINUTE_MAX = 59;

    public final static int POS_ADDRESS = 32;


    public final static int VOLTAGE_MIN = 160;
    public final static int VOLTAGE_MAX = 250;

    public final static int TIME_MIN = 0;
    public final static int TIME_MAX = 1439;

    public final static int POS_TIME_1 = 5;
    public final static int POS_VOLTAGE_1 = 6;
    public final static int POS_TIME_2 = 7;
    public final static int POS_VOLTAGE_2 = 8;
    public final static int POS_TIME_3 = 9;
    public final static int POS_VOLTAGE_3 = 10;
    public final static int POS_TIME_4 = 11;
    public final static int POS_VOLTAGE_4 = 12;
    public final static int POS_TIME_5 = 13;
    public final static int POS_VOLTAGE_5 = 14;
    public final static int POS_TIME_6 = 15;
    public final static int POS_VOLTAGE_6 = 16;
    public final static int POS_TIME_7 = 17;
    public final static int POS_VOLTAGE_7 = 18;
    public final static int POS_TIME_8 = 19;
    public final static int POS_VOLTAGE_8 = 20;
    
    public static CRC crc = null;
    private static byte[] arrReadIR=new byte[8];
    private static byte[] arrReadHR=new byte[8];
    private static byte[] arrWriteHR=new byte[8];
    
    public ProtocolPUS(){
    	
    }
	
	@Override
	public byte[] GetReadCommand(String dataType) {
		return null;
	}

	@Override
	public byte[] GetWriteCommand(String dataType) {
		return null;
	}

	@Override
	public void Parse(byte[] data) {

	}
	
	/*public static void main(String[] args){
		ProtocolPUS.GenCmdReadIR(1);
	}*/
	
	public static byte[] GenCmdReadIR(int DeviceAddress){
		arrReadIR[0]=(byte)DeviceAddress;
		arrReadIR[1]=0x04;
		arrReadIR[2]=0x00;
		arrReadIR[3]=0x00;
		arrReadIR[4]=0x00;
		arrReadIR[5]=0x0c;
		crc = new CRC();
		crc.CrcMake(6, arrReadIR);
		arrReadIR[6]=crc.getBYCRCHi();
		arrReadIR[7]=crc.getBYCRCLo();
		//CRCCode=CRC.CreateCRC().CrcMake(6, arrReadIR);
		//arrReadIR[6]=CRC.BYCRCHi;
		//arrReadIR[7]=CRC.BYCRCLo;
		//System.out.println(arrReadHR[6]);
		return arrReadIR;
	}
	
	public static byte[] GenCmdReadHR(int DeviceAddress){
		arrReadHR[0]=(byte)DeviceAddress;
		arrReadHR[1]=0x03;
		arrReadHR[2]=0x00;
		arrReadHR[3]=0x00;
		arrReadHR[4]=0x00;
		arrReadHR[5]=0x20;
		crc = new CRC();
		crc.CrcMake(6, arrReadHR);
		arrReadIR[6]=crc.getBYCRCHi();
		arrReadIR[7]=crc.getBYCRCLo();
		//CRCCode=CRC.CreateCRC().CrcMake(6, arrReadHR);
		//arrReadHR[6]=CRC.BYCRCHi;
		//arrReadHR[7]=CRC.BYCRCLo;
		return arrReadHR;
	}
	
	public static byte[] GenCmdWriteHR(String HRText, int POS, int DeviceAddress)
    {
        arrWriteHR[0] = (byte)DeviceAddress;
        arrWriteHR[1] = 0x06;
        arrWriteHR[2] = (byte)(POS / 256);
        arrWriteHR[3] = (byte)(POS % 256);
        arrWriteHR[4] = (byte)(Integer.parseInt(HRText) / 256);
        arrWriteHR[5] = (byte)(Integer.parseInt(HRText) % 256);
        //CRCCode = crc.CrcMake(6, arrWriteHR);
        crc = new CRC();
        crc.CrcMake(6, arrWriteHR);
		arrReadIR[6]=crc.getBYCRCHi();
		arrReadIR[7]=crc.getBYCRCLo();
        //CRCCode=CRC.CreateCRC().CrcMake(6, arrWriteHR);
        //arrWriteHR[6] = CRC.BYCRCHi;
        //arrWriteHR[7] = CRC.BYCRCLo;
        return arrWriteHR;
    }
	
	public static byte[] GenCmdWriteHRPair(String v1, String v2, int POS, int DeviceAddress)
    {
        byte[] data = new byte[13];
        data[0] = (byte)DeviceAddress;
        data[1] = 0x10;
        data[2] = (byte)(POS / 256);
        data[3] = (byte)(POS % 256);
        data[4] = (byte)0;
        data[5] = (byte)2;
        data[6] = (byte)2 * 2;
        data[7] = (byte)(Integer.parseInt(v1) / 256);
        data[8] = (byte)(Integer.parseInt(v1) % 256);
        data[9] = (byte)(Integer.parseInt(v2) / 256);
        data[10] = (byte)(Integer.parseInt(v2) % 256);
        //CRCCode = crc.CrcMake(11, data);
        crc = new CRC();
        crc.CrcMake(11,data);
		arrReadIR[11]=crc.getBYCRCHi();
		arrReadIR[12]=crc.getBYCRCLo();
        //CRCCode = CRC.CreateCRC().CrcMake(11,data);
        //data[11] = CRC.BYCRCHi;
        //data[12] = CRC.BYCRCLo;
        return data;
    }
}
