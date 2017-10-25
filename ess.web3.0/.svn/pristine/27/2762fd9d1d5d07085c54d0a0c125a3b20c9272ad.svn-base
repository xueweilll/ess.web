package com.common.socket.impl;

import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.common.socket.MinaService;

/**
 * @ClassName: MinaServiceImpl
 * @Description: TODO(mina服务端代码实现)
 * @author Yf.Lee
 * @date 2014年8月22日 上午3:32:50
 * 
 */
public class MinaServiceImpl implements MinaService {

	private int port;// 端口号
	private int buffersize;// 缓存长度
	private SocketAcceptor acceptor;// 监听
	protected IoHandlerAdapter ioHandlerAdapter;// 服务端handler
	protected ProtocolCodecFactory protocolCodecFactory;// 编解码协议
	private ThreadSend threadSend = null;
	private boolean isrun = false;

	public MinaServiceImpl(IoHandlerAdapter ioHandlerAdapter,
			ProtocolCodecFactory protocolCodecFactory, int port, int buffersize)
			throws Exception {
		this.port = port;
		this.buffersize = buffersize;
		this.ioHandlerAdapter = ioHandlerAdapter;
		this.protocolCodecFactory = protocolCodecFactory;
		init();
	}

	private void init() throws Exception {
		if (port == 0) {
			throw new Exception("the port must be assigned!");
		}
		if (buffersize == 0) {
			throw new Exception("the buffersize must be assigned!");
		}
		if (ioHandlerAdapter == null) {
			throw new Exception("the ioHandlerAdapter must be assigned!");
		}
		/*
		 * if(encoder==null){ throw new
		 * Exception("the encoder must be assigned!"); } if(decoder==null){
		 * throw new Exception("the decoder must be assigned!"); }
		 */
		if (protocolCodecFactory == null) {
			throw new Exception("the protocolCodecFactory must be assigned!");
		}
		acceptor = new NioSocketAcceptor();
		acceptor.getSessionConfig().setReadBufferSize(buffersize);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 5000);
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(protocolCodecFactory));
		acceptor.setHandler(ioHandlerAdapter);
	}

	/*
	 * (非 Javadoc) <p>Title: start</p> <p>Description: mina 启动</p>
	 * 
	 * @return
	 * 
	 * @see com.common.socket.MinaService#start()
	 */
	public boolean start() {
		isrun = true;
		try {
			acceptor.bind(new InetSocketAddress(port));
			threadSend = new ThreadSend();
			threadSend.start();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/*
	 * (非 Javadoc) <p>Title: stop</p> <p>Description:mina 停止 </p>
	 * 
	 * @return
	 * 
	 * @see com.common.socket.MinaService#stop()
	 */
	public boolean stop() {
		isrun = false;
		try {
			acceptor.dispose(true);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	class ThreadSend extends Thread {

		@Override
		public void run() {
			while (isrun) {
				Map<?, ?> sessionMap = acceptor.getManagedSessions();
				Iterator<?> iter = sessionMap.keySet().iterator();
				IoSession session = null;
				while (iter.hasNext()) {
					Object key = iter.next();
					session = (IoSession) sessionMap.get(key);
					//检测发送指令，发送
					byte[] buff=new byte[]{(byte)0xAA,(byte) 0xFF};
					session.write(buff);
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public boolean monitorRun() {
		return isrun;
	}
}
