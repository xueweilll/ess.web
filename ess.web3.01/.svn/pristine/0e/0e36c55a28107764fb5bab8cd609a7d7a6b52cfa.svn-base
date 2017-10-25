package com.common.socket.impl;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class ProtocolEncoderImpl implements ProtocolEncoder{

	public void dispose(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
			throws Exception {
		byte[] buff = (byte[])message;
		IoBuffer ioBuffer = IoBuffer.wrap(buff);
		out.write(ioBuffer);
		out.flush();
	}

}
