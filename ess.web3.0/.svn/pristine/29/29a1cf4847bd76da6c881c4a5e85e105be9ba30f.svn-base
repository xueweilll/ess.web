package com.common.socket;

import com.common.socket.impl.IoHandlerImpl;
import com.common.socket.impl.MinaServiceImpl;
import com.common.socket.impl.ProtocolCodecFactoryImpl;
import com.common.socket.impl.ProtocolDecoderImpl;
import com.common.socket.impl.ProtocolEncoderImpl;

public class CoreSocketService {
	private CoreSocketService(){}
	
	private static CoreSocketService instance;
	
	public static CoreSocketService Instance(){
		if(instance==null){
			instance = new CoreSocketService();
		}
		return instance;
	}
	
	private boolean isrun;

	public boolean isIsrun() {
		return isrun;
	}

	public void setIsrun(boolean isrun) {
		this.isrun = isrun;
	}
	
	private MinaService minaService;
	
	public void start(){
		try {
			 minaService = new MinaServiceImpl(new IoHandlerImpl(), new ProtocolCodecFactoryImpl(new ProtocolEncoderImpl(),new ProtocolDecoderImpl()), 1218, 1024);
			 minaService.start();
			 isrun = minaService.monitorRun();
		} catch (Exception e) {
			e.printStackTrace();
			isrun = false;
		}
	}
	
	public void stop(){
		isrun = false;
		minaService.stop();
	}
}
