package com.test;

import com.protocol.Convert;

public class testIntercept {

	private static byte[] recursiveIntercept(byte[] buffC, int start, int end) {
		int lenT = end + 1 - start;
		byte[] buffT = new byte[lenT];
		for (int i = 0; i < lenT; i++) {
			buffT[i] = buffC[start++];
		}
		return buffT;
	}
	
	public static void main(String[] args){
		String str = "55 AA 14 01 04 18 00 E8 00 03 00 00 00 07 00 03 04 FC 00 00 00 00 00 00 02 95 00 22 00 02 99 AF 02 04 18 00 EB 00 05 00 01 00 04 00 03 04 FC 00 00 00 00 00 00 02 90 00 23 00 02 FA 27 03 04 18 00 E9 00 05 00 02 00 01 00 03 04 FC 00 00 00 00 00 00 02 93 00 22 00 02 DF 09 07 03 42 00 EC 00 EF 01 21 00 FD 00 00 00 00 5A A0 5A 81 5A B1 5A 9B 00 00 9C D6 9C EA 9C FE 9C E9 00 00 00 3A FF 58 00 B0 0D 0C C3 1E 00 0C FF C9 00 34 09 5D 00 12 FF CB 00 35 0D 7A 00 1A FF C3 00 41 10 14 5F 66 CC 33 C3 3C ";
		byte[] buff = Convert.HexStrToArrByte(str);
		intercept(buff);
	}
	
	private static void intercept(byte[] buffI) {
		int len = buffI.length - 7;
		int n = 0;
		byte[] buff;
		String str = "";
		if (buffI[2] == 20) {
			switch (len % 29) {
			case 13:// 29*n+71*1
				n = (len - 71) / 29;
				buff = new byte[29];
				for(int i = 0;i<n;i++){
					buff = recursiveIntercept(buffI,3+i*29,(i+1)*29+3-1);
					str= Convert.ArrByteToHexStr(buff);
					System.out.println(str);
				}
				buff = new byte[71];
				buff = recursiveIntercept(buffI, 3+29*n, n*29+3+71-1);
				str= Convert.ArrByteToHexStr(buff);
				System.out.println(str);
				break;
			case 26:// 29*n+71*2
				n = (len - 71 * 2) / 29;
				buff = new byte[29];
				for(int i = 0;i<n;i++){
					buff = recursiveIntercept(buffI,3+i*29,(i+1)*29+3-1);
					str= Convert.ArrByteToHexStr(buff);
					System.out.println(str);
				}
				buff = new byte[71];
				buff = recursiveIntercept(buffI, 3+29*n, n*29+3+71-1);
				str= Convert.ArrByteToHexStr(buff);
				buff = recursiveIntercept(buffI, 3+29*n+71, n*29+3+71*2-1);
				str= Convert.ArrByteToHexStr(buff);
				System.out.println(str);
				break;
			case 0:// 29*n
				n = len / 29;
				buff = new byte[29];
				for(int i = 0;i<n;i++){
					buff = recursiveIntercept(buffI,3+i*29,(i+1)*29+3-1);
					str= Convert.ArrByteToHexStr(buff);
					System.out.println(str);
				}
				break;
			}
		}

		if (buffI[2] == 19) {

		}
	}
}
