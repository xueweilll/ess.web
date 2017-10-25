package com.common.socket;


/** 
* @ClassName: MinaService 
* @Description: TODO(mina服务端代码接口) 
* @author Yf.Lee
* @date 2014年8月22日 上午3:40:53 
*  
*/
public interface MinaService {
	
	public boolean start();
	
	public boolean stop();
	
	public boolean monitorRun();
}
