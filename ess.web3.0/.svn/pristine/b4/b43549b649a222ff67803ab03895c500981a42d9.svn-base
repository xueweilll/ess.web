package com.protocol;

public class CRC {
	// private static CRC crc;
	private byte[] CRCLo = new byte[256];// crc16 values
	private byte[] CRCHi = new byte[256];// crc16 values

	private byte BYCRCHi;// CRC高字节
	private byte BYCRCLo;// CRC低字节

	/*
	 * public static CRC CreateCRC(){ if(crc==null){ crc=new CRC(); } return
	 * crc; }
	 * 
	 * private CRC(){ init(); }
	 */

	public byte getBYCRCHi() {
		return BYCRCHi;
	}

	/*
	 * public void setBYCRCHi(byte bYCRCHi) { BYCRCHi = bYCRCHi; }
	 */

	public byte getBYCRCLo() {
		return BYCRCLo;
	}

	/*
	 * public void setBYCRCLo(byte bYCRCLo) { BYCRCLo = bYCRCLo; }
	 */
	public CRC() {
		init();
	}

	private void init() {
		// 初始化检验表
		int wMask = 0, wBit = 0, wCrc = 0, wMem = 0;
		int PolyCrc16 = 0xA001;
		for (wMask = 0; wMask < 0x100; wMask++) {
			wCrc = wMask;
			for (wBit = 0; wBit < 8; wBit++) {
				wMem = wCrc & 0x0001;
				wCrc /= 2;
				if (wMem != 0)
					wCrc ^= PolyCrc16;
			}
			// System.out.println((byte)(wCrc&0xff));
			// System.out.println((Integer.toString(toUnsiged((byte)(wCrc&0xff)))));
			CRCHi[wMask] = (byte) (wCrc & 0xff);
			CRCLo[wMask] = (byte) (wCrc >> 8);
		}
	}

	public static int toUnsiged(byte b) {
		// return (b<0?b+256:b);
		return (int) ((b + 256) % 256);
	}

	public static int toUnsiged(int i) {
		// return (i<0?i+256:i);
		return (int) ((i + 256) % 256);
	}

	// 计算CRC校验码
	public int CrcMake(int wBuffsize, byte[] pSendBuff) {
		int car, i;
		byte[] crc = new byte[2];
		crc[0] = (byte) 0xff;
		// System.out.println(crc[0]);
		crc[1] = (byte) 0xff;
		for (i = 0; i < wBuffsize; i++) {
			car = pSendBuff[i];
			car ^= crc[0];
			// System.out.println(car);
			car = toUnsiged(car);
			crc[0] = (byte) (crc[1] ^ CRCHi[car]);
			crc[1] = CRCLo[car];
		}
		BYCRCHi = crc[0];
		// System.out.println(BYCRCHi);
		BYCRCLo = crc[1];
		// System.out.println(BYCRCLo);
		return (toUnsiged(crc[0]) * 256 + toUnsiged(crc[1]));
		// return(crc[0]*256+crc[1]);
	}

	// 判断返回的帧是否有校验,即CRC校验
	// 返回类型int (0:错误;1:正确)
	public int crc_check(int data_len, byte[] pSendBuff) {
		int Crc_Code, Crc_LowCode, Crc_HighCode;
		Crc_Code = CrcMake(data_len, pSendBuff);

		Crc_HighCode = pSendBuff[data_len];
		Crc_HighCode = toUnsiged(Crc_HighCode);
		Crc_LowCode = pSendBuff[data_len + 1];
		Crc_LowCode = toUnsiged(Crc_LowCode);
		if (Crc_Code == (Crc_HighCode * 0x100 + Crc_LowCode) && Crc_Code != 0)
			return 1;
		else
			return 0;
	}

	/*
	 * public static void main(String[] args){ int wBuffSize=0; // byte[]
	 * pSendBuff={1,4,0,0,0,12,0,0}; byte[] pSendBuff={3,4,0,0,0,12,-16,60};
	 * wBuffSize=6; int a= new CRC().CrcMake(wBuffSize, pSendBuff);
	 * System.out.println(a); }
	 */

}
