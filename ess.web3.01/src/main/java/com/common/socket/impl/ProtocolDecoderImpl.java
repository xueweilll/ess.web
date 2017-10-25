package com.common.socket.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.protocol.Convert;

public class ProtocolDecoderImpl extends CumulativeProtocolDecoder {

	// private Object obj;
	private byte[] buff;
	private byte[] buffCache;
	private int len;
	private static byte[] buffStart = new byte[] { (byte) 0x55, (byte) 0xAA };
	private static byte[] buffEnd = new byte[] { (byte) 0xCC, (byte) 0x33,
			(byte) 0xC3, (byte) 0x3C };

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		len = in.remaining();
		buff = new byte[len];
		in.get(buff, 0, len);
		// 初始化dtuId
		if (session.getAttribute("dtuId") == null) {
			String dtuId = Convert.GetID(buff);
			System.out.println(dtuId);
			session.setAttribute("dtuId", dtuId);
			buffCache = new byte[0];
			session.setAttribute("buffCache", buffCache);
			in.flip();
			return true;
		} else {

			/*
			 * 合并数组 buffCache = (byte[]) session.getAttribute("cacheBuff"); len
			 * += buffCache.length; buffTemp = new byte[len + buffCache.length];
			 * int n = 0; for (byte by : buffCache) { buffTemp[n] = by; n++; }
			 * for (byte by : buff) { buffTemp[n] = by; n++; }
			 */

			/*
			 * 截取数组 for (int i = 0; i < len; i++) { if (buffTemp[i] ==
			 * buffStart[0] && buffTemp[i + 1] == buffStart[1]) { for (int j =
			 * len - 1; j >= 0; j--) { if (buffTemp[j] == buffEnd[2] &&
			 * buffTemp[j - 1] == buffEnd[1] && buffTemp[j - 2] == buffEnd[0]) {
			 * int bufflen = j + 2 - i; buff = new byte[bufflen]; for (int m =
			 * 0; m < bufflen; m++) { buff[m] = buffTemp[i++]; }
			 * out.write(buff); in.flip(); return true; } } } }
			 */
			buffCache = (byte[]) session.getAttribute("buffCache");
			System.out.println("this cacheBuff is "
					+ new String(Convert.ArrByteToHexStr(buffCache)));
			List<byte[]> listBuff = intercept(buffCache, buff);
			session.setAttribute("buffCache", buffCache);
			if (listBuff.size() > 0) {
				out.write(listBuff);
				return true;
			}

			return false;
		}
	}
	
	private List<byte[]> intercept(byte[] buffC, byte[] buffCr) {
		byte[] buffT, buffI;
		List<byte[]> listBuff = new ArrayList<byte[]>();
		buffT = mergeBytes(buffC, buffCr);
		int flag = 0;
		for (int i = 0; i < buffT.length; i++) {
			if (buffT[i] == buffStart[0] && buffT[i + 1] == buffStart[1]) {
				for (int j = i + 2; j < buffT.length - 3; j++) {
					if (buffT[j] == buffEnd[0] && buffT[j + 1] == buffEnd[1]
							&& buffT[j + 2] == buffEnd[2]
							&& buffT[j + 3] == buffEnd[3]) {
						buffI = recursiveIntercept(buffT, i, j + 3);
						System.out.println("this buff is "
								+ new String(Convert.ArrByteToHexStr(buffI)));
						listBuff.add(buffI);
						//listBuff.addAll(intercept(buffI));
						i = j + 4;
						flag = i;
						break;
					}
				}
			}
		}

		if (buffT.length - flag > 1 && buffCache.length < 1024) {
			buffCache = recursiveIntercept(buffT, flag, buffT.length - 1);
			System.out.println("this buffCache is "
					+ new String(Convert.ArrByteToHexStr(buffCache)));
		} else {
			buffCache = new byte[0];
		}
		return listBuff;
	}

	private byte[] recursiveIntercept(byte[] buffC, int start, int end) {
		int lenT = end + 1 - start;
		byte[] buffT = new byte[lenT];
		for (int i = 0; i < lenT; i++) {
			buffT[i] = buffC[start++];
		}
		return buffT;
	}

	private byte[] mergeBytes(byte[] buff1, byte[] buff2) {
		int len = buff1.length + buff2.length;
		byte[] buff = new byte[len];
		int i = 0;
		for (byte by : buff1) {
			buff[i++] = by;
		}
		for (byte by : buff2) {
			buff[i++] = by;
		}
		return buff;
	}

	/*
	 * public synchronized void test() { buffCache = new byte[0];
	 * 
	 * buffCache[0] = 1; buffCache[1] = 2; buffCache[2] = 3;
	 * 
	 * buff = new byte[5]; buff[0] = 1; buff[1] = 2; buff[2] = 3; buff[3] = 4;
	 * buff[4] = 5; buffTemp = new byte[buff.length + buffCache.length]; int n =
	 * 0; for (byte by : buffCache) { buffTemp[n] = by; n++; } for (byte by :
	 * buff) { buffTemp[n] = by; n++; } for (byte by : buffTemp) {
	 * System.out.println(by); } }
	 */

	/*
	 * public static void main(String[] args) {
	 * 
	 * String str =
	 * "DD 55 AA 14 03 04 18 00 E3 00 07 00 00 00 00 00 03 04 FC 00 00 00 00 00 00 02 99 00 20 00 02 13 DD 07 03 42 00 00 00 00 00 CD 00 44 00 00 00 00 00 00 00 00 58 9D 1D 89 00 00 00 00 58 98 58 98 3B 10 00 00 00 00 FF D0 00 2D 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF D0 00 2D 00 00 7E 43 CC 33 C3 3C "
	 * +
	 * "DD BB 55 AA 14 03 04 18 00 E3 00 07 00 00 00 00 00 03 04 FC 00 00 00 00 00 00 02 99 00 20 00 02 13 DD 07 03 42 00 00 00 00 00 CD 00 44 00 00 00 00 00 00 00 00 58 9D 1D 89 00 00 00 00 58 98 58 98 3B 10 00 00 00 00 FF D0 00 2D 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF D0 00 2D 00 00 7E 43 CC 33 C3 3C "
	 * +
	 * "DD BB 55 AA 14 03 04 18 00 E3 00 07 00 00 00 00 00 03 04 FC 00 00 00 00 00 00 02 99 00 20 00 02 13 DD 07 03 42 00 00 00 00 00 CD 00 44 00 00 00 00 00 00 00 00 58 9D 1D 89 00 00 00 00 58 98 58 98 3B 10 00 00 00 00 FF D0 00 2D 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF D0 00 2D 00 00 7E 43 CC 33 C3 3C "
	 * +
	 * "DD BB 55 AA 14 03 04 18 00 E3 00 07 00 00 00 00 00 03 04 FC 00 00 00 00 00 00 02 99 00 20 00 02 13 DD 07 03 42 00 00 00 00 00 CD 00 44 00 00 00 00 00 00 00 00 58 9D 1D 89 00 00 00 00 58 98 58 98 3B 10 00 00 00 00 FF D0 00 2D 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF D0 00 2D 00 00 7E 43 CC 33 C3 3C AA CC DD"
	 * ; byte[] buff = Convent.HexStrToArrByte(str); for (int i = 0; i <
	 * buff.length; i++) { System.out.println(buff[i]); } List<byte[]> list =
	 * new ProtocolDecoderImpl().intercept(new byte[0], buff);
	 * System.out.println("this.list.size = " + list.size()); // new
	 * ProtocolDecoderImpl().test(); // int len = 69 * 6; //
	 * System.out.println(len % 69); }
	 */
	
	public static void main(String[] args){
		int len = 29*6+71*2;
		System.out.println(len%29);
	}

}
