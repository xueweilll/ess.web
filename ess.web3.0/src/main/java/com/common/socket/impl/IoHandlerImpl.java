package com.common.socket.impl;

import java.util.List;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.protocol.Analysis;
import com.protocol.Convert;
import com.protocol.IRAnalysis;
import com.protocol.MTAnalysis;

public class IoHandlerImpl extends IoHandlerAdapter {

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(session, cause);
	}

	private byte[] recursiveIntercept(byte[] buffC, int start, int end) {
		int lenT = end + 1 - start;
		byte[] buffT = new byte[lenT];
		for (int i = 0; i < lenT; i++) {
			buffT[i] = buffC[start++];
		}
		return buffT;
	}
	
	private Analysis analysis;
	
	private void intercept(byte[] buffI,int dtuId) throws Exception {
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
					analysis = new IRAnalysis(buff,dtuId);
					analysis.registers();
				}
				buff = new byte[71];
				buff = recursiveIntercept(buffI, 3+29*n, n*29+3+71-1);
				str= Convert.ArrByteToHexStr(buff);
				System.out.println(str);
				analysis = new MTAnalysis(buff,dtuId);
				analysis.registers();
				break;
			case 26:// 29*n+71*2
				n = (len - 71 * 2) / 29;
				buff = new byte[29];
				for(int i = 0;i<n;i++){
					buff = recursiveIntercept(buffI,3+i*29,(i+1)*29+3-1);
					str= Convert.ArrByteToHexStr(buff);
					System.out.println(str);
					analysis = new IRAnalysis(buff,dtuId);
					analysis.registers();
				}
				buff = new byte[71];
				buff = recursiveIntercept(buffI, 3+29*n, n*29+3+71-1);
				str= Convert.ArrByteToHexStr(buff);
				System.out.println(str);
				analysis = new MTAnalysis(buff,dtuId);
				analysis.registers();
				buff = recursiveIntercept(buffI, 3+29*n+71, n*29+3+71*2-1);
				str= Convert.ArrByteToHexStr(buff);
				System.out.println(str);
				analysis = new MTAnalysis(buff,dtuId);
				analysis.registers();
				break;
			case 0:// 29*n
				n = len / 29;
				buff = new byte[29];
				for(int i = 0;i<n;i++){
					buff = recursiveIntercept(buffI,3+i*29,(i+1)*29+3-1);
					str= Convert.ArrByteToHexStr(buff);
					System.out.println(str);
					analysis = new IRAnalysis(buff,dtuId);
					analysis.registers();
				}
				break;
			}
		}

		if (buffI[2] == 19) {
			
		}
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// super.messageReceived(session, message);
		int dtuId = (int)session.getAttribute("dtuId");
		@SuppressWarnings("unchecked")
		List<byte[]> listBuff = (List<byte[]>) message;
		for (byte[] by : listBuff) {
			String str = Convert.ArrByteToHexStr(by);
			System.out.println(str);
			intercept(by,dtuId);
		}
		/*
		 * for(int i = 0;i<buff.length;i++){
		 * str+=Integer.toHexString(buff[i])+" "; }
		 */

	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionCreated(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// TODO Auto-generated method stub
		super.sessionIdle(session, status);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionOpened(session);
	}

}
