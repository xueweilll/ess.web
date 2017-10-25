package com.protocol;

public class Convert {
	public static int[] ArrByteToInts(byte[] bytes) {
		int[] ints = new int[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			ints[i] = CRC.toUnsiged(bytes[i]);
		}
		return ints;
	}

	public static String[] ArrByteToHex(byte[] bytes) {
		String[] res = new String[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			res[i] = IntToHex(CRC.toUnsiged(bytes[i]));
		}
		return res;
	}
	
	public static int ArrByteToInt(byte[] bytes,int start,int len){
		int n = 0;
		byte[] buff = new byte[len];
		for(int i =0;i<len;i++){
			buff[i] = bytes[start+i];
		}
		
		String hex = ArrByteToHexStr(buff);
		n = Integer.parseInt(hex.replace(" ", ""), 16);
		
		return n;
	}

	public static String ArrByteToHexStr(byte[] bytes) {
		String res = "";
		for (int i = 0; i < bytes.length; i++) {
			res += IntToHex(CRC.toUnsiged(bytes[i])) + " ";
		}
		res.trim();
		return res;
	}

	public static String ArrByteToHex(String str) {
		String[] strs = str.split(" ");
		str = "";
		for (int i = 0; i < strs.length; i++) {
			str += IntToHex(CRC.toUnsiged(Integer.parseInt(strs[i]))) + " ";
		}
		return str;
	}

	public static byte[] HexStrToArrByte(String str) {
		String[] strs = str.split(" ");
		int len = strs.length;
		byte[] buff = new byte[len];
		for (int i = 0; i < len; i++) {
			buff[i] =  (byte)Integer.parseInt(strs[i],16);
		}
		return buff;
	}

	public static String IntToHex(int x) {
		String res = "";
		res = Integer.toHexString(x).toUpperCase();
		if (res.length() < 2) {
			res = "0" + res;
		}
		return res;
	}

	public static void main(String[] args) {
		// System.out.println(Convent.IntToHex(14));
		byte[] by = new byte[] { 85, -86, 20, 1, 4, 24, 0, -56, 0, 9, 0, 4, 0,
				3, 0, 1, 3, 34, 0, 0, 0, -120, 0, 0, 3, -83, 0, 42, 0, 0, 17,
				37, 2, 4, 24, 0, -56, 0, 8, 0, 4, 0, 3, 0, 1, 2, -3, -18, 0, 0,
				0, 90, 0, 0, 3, -81, 0, 43, 0, 0, -126, 10, 3, 4, 24, 0, -56,
				0, 8, 0, 4, 0, 2, 0, 1, 3, 16, 0, 0, 0, -120, 0, 0, 3, -82, 0,
				43, 0, 0, -4, 83, 7, 3, 66, 2, 50, 2, 46, 2, 54, 2, 49, 0, 0,
				0, 0, 86, 69, 86, 67, 86, 68, 86, 67, 0, 0, -107, 106, -107,
				96, -107, 106, -107, 102, 0, 0, 0, -91, -3, -18, -76, 1, 114,
				17, -123, -61, 80, 0, 54, -1, -111, 0, 122, 17, -112, 0, 54,
				-1, -110, 0, 122, 17, -63, 0, 54, -1, -113, 0, 123, 17, 96,
				-13, -123, -52, 51, -61, 60 };
		System.out.println(by.length);
		String[] str = Convert.ArrByteToHex(by);
		String s = "";
		for (int i = 0; i < str.length; i++) {
			s += str[i] + " ";
		}
		System.out.println(s);
	}

	public static int toUnsiged(byte b) {
		return (b < 0 ? b + 256 : b);
	}

	public static int toUnsiged(int i) {
		return (i < 0 ? i + 256 : i);
	}

	public static boolean isregst(byte[] by) {
		if (by.length != 21) {
			return false;
		}
		int i;
		for (i = 4; i < 15; i++) {
			int j = (int) by[i];
			if (!(j >= '0' && j <= '9'))
				return false;
		}
		return true;
	}

	public static String GetID(byte[] by) {
		/*
		 * String sid=""; for (int i = 3; i >= 0; i--) { String
		 * temp=Integer.toHexString(by[i]); if(temp.length()==1) {
		 * temp="0"+temp; } sid+=temp; } return sid;
		 */
		if(by.length<3){
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder("");
		if (by == null || by.length <= 0) {
			return null;
		}
		for (int i = 3; i >= 0; i--) {
			int v = by[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();

	}

	public static String GetPhone(byte[] by) {
		String phone = "";
		for (int i = 4; i < 16; i++) {
			String temp = Integer.toHexString(by[i]);
			if (temp.length() == 1) {
				temp = "0" + temp;
			}
			phone += temp;
		}
		return phone;
	}

	public static String GetIP(byte[] by) {
		String ip = "";
		for (int i = 17; i < 21; i++) {
			String temp = Integer.toHexString(by[i]);
			if (temp.length() == 1) {
				temp = "0" + temp;
			}
			ip += temp;
		}
		return ip;
	}

	public static boolean isheart(byte[] s) {
		for (int i = 0; i < s.length; i++) {
			if ((int) s[i] != -2)// -1����FE
			{
				return false;
			}
		}
		return true;
	}

	public static byte[] ConverToPc(byte[] by) {
		int le = by.length;
		byte[] t1 = new byte[le + 1];
		byte[] t2 = new byte[le + 1];
		int j = 0, k = 0, g = 0;
		byte fe = (byte) 0xfe;
		byte fd = (byte) 0xfd;
		byte ed = (byte) 0xed;
		byte ee = (byte) 0xee;
		// _________________________����ȥ�����е�FE____________________________
		for (int i = 0; i < le; i++) {
			if (by[i] != fe) {
				t1[j++] = by[i];
			}
		}
		// _________________________��FD EDת��ΪFD_____________________________
		for (int i = 0; i < j; i++) {
			if (t1[i] == fd && t1[i + 1] == ed) {
				t2[k++] = fd;
				i++;
			} else {
				t2[k++] = t1[i];
			}

		}
		// _________________________��FD EEת��ΪFE_____________________________
		for (int i = 0; i < k; i++) {
			if (t2[i] == fd && t2[i + 1] == ee) {
				t1[g++] = fe;
				i++;
			} else {
				t1[g++] = t2[i];
			}

		}
		// _______________________��t1���ǰg��Ԫ��copy��һ����������________________
		byte[] fn = new byte[g];
		for (int i = 0; i < g; i++) {
			fn[i] = t1[i];
		}
		// _______________________________end________________________________________
		return fn;
	}

	public static byte[] ConverToByte(String msg) {
		if (msg.substring(msg.length() - 1, msg.length()) == " ") {
			msg = msg.substring(0, msg.length() - 1);
		}
		String[] str = msg.split(" ");
		byte[] by = new byte[str.length];
		int it;
		for (int i = 0; i < str.length; i++) {
			it = Integer.parseInt(str[i], 16);
			by[i] = (byte) it;
			// System.out.println(by[i]);
		}
		return ConverToDTU(by);
	}

	/*public static void main(String[] arg) {
		// ConverToByte("01 04 00 00 00 0C F0 0F ");
		byte[] by = new byte[] { 63, 48, 0, 18 };
		System.out.println(GetID(by));
	}
*/
	public static byte[] ConverToDTU(byte[] by) {
		int le = by.length;
		byte[] t1 = new byte[le * 3];
		byte[] t2 = new byte[le * 3];
		int j = 0, k = 0;
		byte fe = (byte) 0xfe;
		byte fd = (byte) 0xfd;
		byte ed = (byte) 0xed;
		byte ee = (byte) 0xee;
		// _________________________0xfd ת��Ϊ 0xfd
		// 0xed____________________________
		for (int i = 0; i < le; i++) {
			if (by[i] != fd) {
				t1[j++] = by[i];
			} else {
				t1[j++] = fd;
				t1[j++] = ed;
			}
		}
		// _________________________�� 0xfe ת��Ϊ 0xfd
		// 0xee_____________________________
		for (int i = 0; i < j; i++) {
			if (t1[i] != fe) {
				t2[k++] = t1[i];
			} else {
				t2[k++] = fd;
				t2[k++] = ee;
			}
		}

		// _______________________��t1���ǰg��Ԫ��copy��һ����������________________
		byte[] fn = new byte[k];
		for (int i = 0; i < k; i++) {
			fn[i] = t2[i];
		}
		// _______________________________end________________________________________
		return fn;
	}

	public static byte[] ConbyteL(byte[] by, int length) {
		byte[] newby = new byte[length];
		for (int i = 0; i < length; i++) {
			newby[i] = by[i];
		}
		return newby;
	}
	
}
