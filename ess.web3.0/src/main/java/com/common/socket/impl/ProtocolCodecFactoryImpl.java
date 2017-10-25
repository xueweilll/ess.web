package com.common.socket.impl;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/** 
* @ClassName: ProtocolCodecFactoryImpl 
* @Description: TODO(解码工厂) 
* @author Yf.Lee
* @date 2014年8月22日 上午4:00:44 
*  
*/
public class ProtocolCodecFactoryImpl implements ProtocolCodecFactory{
	public ProtocolCodecFactoryImpl(ProtocolEncoder protocolEncode,ProtocolDecoder protocolDecode){
		this.protocolDecode=protocolDecode;
		this.protocolEncode=protocolEncode;
	}

	private ProtocolDecoder protocolDecode;
	
	private ProtocolEncoder protocolEncode;
	
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return protocolDecode;
	}

	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return protocolEncode;
	}

}
