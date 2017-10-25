package com.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
//import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
//import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hibernate.entity.DTU;
import com.hibernate.entity.LMU;
import com.hibernate.entity.Lmulimit;
import com.hibernate.entity.PartSensor;
import com.protocol.PortocolFactory;
import com.protocol.YZCProtocol;
import com.service.DtuService;
import com.service.LmuLimitService;
import com.unit.LmuMsgQuence;
import com.unit.MessageQueue;
import com.unit.PartsQuence;
import com.untils.BeanToMapUtils;

@Scope("singleton")
@Service("minaServer")
public class MinaServer {

	public static void main(String[] args) {
		// DtuService dtuService =
		// (DtuService)MyApplicationContext.getInstance().getCtx().getBean("dtuService");
	}

	public SocketAcceptor acceptor;

	@Autowired
	private DtuService dtuService;

	private boolean isrun;

	private Thread thread;
	@Autowired
	private LmuMsgQuence lmuMsgQuence;
	@Autowired
	private LmuLimitService limitService;
	@Autowired
	private PartsQuence partsQuence;
	// ApplicationContext ctx;
	@SuppressWarnings("unchecked")
	private void init() {
		System.out.println("--------------------------socket开始初始化-------------------------------");
		acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE, 120);// 读(接收通道)空闲时间:40秒
		// DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		// chain.addLast("codec", new ProtocolCodecFilter(new ));
		// ctx = new ClassPathXmlApplicationContext("spring-hibernate.xml");
		
		//messageQueue = (MessageQueue) MyApplicationContext.getInstance().getCtx().getBean("messageQueue");
		//partsQuence = (PartsQuence) MyApplicationContext.getInstance().getCtx().getBean("partsQuence");
		
		//dtuService = (DtuService) MyApplicationContext.getInstance().getCtx().getBean("dtuService");
		//sensorService = (SensorService) MyApplicationContext.getInstance().getCtx().getBean("sensorService");
		acceptor.setHandler(new BexnHandler());
		dtuList = dtuService.DTUlist();
		for (DTU dtu : dtuList) {
			PartSensor partSensor = new PartSensor();
			partSensor.setType(2);
			partSensor.setDuoName(dtu.getName());
			messageQueue.put(dtu, partSensor);
		}
		isrun = true;
		System.out.println("--------------------------socket初始化结束-------------------------------");
		List<Lmulimit> list = limitService.getAllLmuLimit();
		for (Lmulimit lmulimit : list) {
			lmuMsgQuence.put(lmulimit.getLmuId(), BeanToMapUtils.toMap(lmulimit));
		}
	}

	/*
	private static MinaServer minaServer;

	public static MinaServer instance() throws Exception {
		if (minaServer == null) {
			minaServer = new MinaServer();
		}
		return minaServer;
	}

	private MinaServer() throws Exception {
		init();
	}
	*/
	
	@PostConstruct
	public void start() throws IOException {
		init();
		acceptor.bind(new InetSocketAddress(50000));
		thread = new Thread(new MyRunnable());
		thread.start();
	}

	@PreDestroy
	public void close() {
		isrun = false;
		acceptor.dispose();
	}

	class MyRunnable implements Runnable {
		@Override
		public void run() {
			while (isrun) {
				try {
					for (IoSession session : acceptor.getManagedSessions().values()) {
						sendCmd(session);
					}
				Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Autowired
	PortocolFactory portocolFactory;

	public void sendCmd(IoSession session) {
		/*DTU dtu = (DTU) session.getAttribute("dtu");
		if (dtu == null) {
			return;
		}*/
		
		if(session.getAttribute("dtuId") == null){
			return;
		}
		int dtuId =  (int) session.getAttribute("dtuId");
		Set<LMU> lmuList = new HashSet<LMU>();
		for(Map.Entry<DTU, PartSensor> e: messageQueue.getCacheData().entrySet() ){
			if(e.getKey().getId() == dtuId){
				lmuList = e.getKey().getLmus();
				break;
			}
		}
		try {
			for (LMU lmu : lmuList) {
				YZCProtocol yzcProtocol =  portocolFactory.getProtocol(lmu.getType());
					if(yzcProtocol != null){
						byte[] cmd = yzcProtocol.readCmd(lmu.getCode());
						session.write(IoBuffer.wrap(cmd));
						Thread.sleep(5000);
					}
				}
			}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private List<DTU> dtuList;

	@Autowired
	private MessageQueue messageQueue;

	class BexnHandler extends IoHandlerAdapter {

		private void init(IoSession session) {
			String ip = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();
			for(Map.Entry<DTU, PartSensor> e: messageQueue.getCacheData().entrySet() ){
				if(e.getKey().getIP().equals(ip)){
					e.getValue().setType(0);
					e.getValue().setDtuId(e.getKey().getId());
					e.getValue().setDuoName(e.getKey().getName());
					session.setAttribute("dtuId", e.getKey().getId());
					//session.setAttribute("dtu", e.getKey());
					return;
				}
			}
		/*	
			for (DTU dtu : dtuList) {
				if (ip.equals(dtu.getIP())) {
					session.setAttribute("id", dtu.getId());
					session.setAttribute("dtu", dtu);
					PartSensor partSensor = new PartSensor();
					partSensor.setDtuId(dtu.getId());
					partSensor.setDuoName(dtu.getName());
					partSensor.setType(0);
					messageQueue.put(dtu,partSensor);
					
				}
			}*/
			//session.close(true);
			session.closeNow();
		}

		private void close(IoSession session) {
			
			String ip = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();
			for(Map.Entry<DTU, PartSensor> e: messageQueue.getCacheData().entrySet() ){
				//清空当前站点下所有设备数据
				if(e.getKey().getIP().equals(ip)){
					//清空当前站点下所有设备数据
					Set<LMU> set = e.getKey().getLmus();
					for (LMU lmu : set) {
						partsQuence.remove(lmu.getId());
					}
					PartSensor partSensor = new PartSensor();
					partSensor.setType(2);
					partSensor.setDuoName(e.getKey().getName());
					messageQueue.put(e.getKey(), partSensor);
				}
			}
			//session.close(true);
			session.closeNow();
		}

	/*	private void analysis(IoSession session, Object message) {
			Object[] obj = new Object[4];
			IoBuffer ioBuffer = (IoBuffer) message;
			byte[] buff = new byte[ioBuffer.limit()];
			ioBuffer.get(buff);
			Map<String, String> mapVluue = PortocolFactory.getProtocol(buff[0]).anlysis(buff);
			DTU dtu = (DTU) session.getAttribute("dtu");
			Sensor sensor = new Sensor();
			sensor.setId(UUID.randomUUID().toString());
			sensor.setDtuId(String.valueOf(dtu.getId()));
			for (LMU lmu : dtu.getLmus()) {
				if (lmu.getCode() == buff[0]) {
					sensor.setLmu(lmu);
					break;
				}
			}
			sensor.setDtuName(dtu.getName());
			
			//氨气
			sensor.setV1(mapVluue.get("ppm") != null ? new BigDecimal(mapVluue.get("ppm").toString()).setScale(2, BigDecimal.ROUND_HALF_UP) : null);
			obj[0] = sensor.getV1();
			
			//温度
			sensor.setV2(mapVluue.get("wd") != null ? new BigDecimal(mapVluue.get("wd").toString()).setScale(2, BigDecimal.ROUND_HALF_UP) : null);
			obj[1] = sensor.getV2();
			
			//湿度
			sensor.setV3(mapVluue.get("sd") != null ? new BigDecimal(mapVluue.get("sd").toString()).setScale(2, BigDecimal.ROUND_HALF_UP) : null);
			obj[2] = sensor.getV3();
			
			//市电
			sensor.setV4(mapVluue.get("sdtd") != null ? new BigDecimal(mapVluue.get("sdtd").toString()).setScale(2, BigDecimal.ROUND_HALF_UP) : null);
			obj[3] = sensor.getV4();
			sensor.setAddtime(Calendar.getInstance());
			String duoLmu = sensor.getDtuId() + "-" + sensor.getLmu().getId();
			partsQuence.put(duoLmu, obj);
			if(!messageQueue.getCacheData().contains(dtu)){
				messageQueue.put(dtu, new PartSensor());
			}
			try {
				sensorService.insert(sensor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		//客户端会话打开
		@Override
		public void sessionOpened(IoSession session) throws Exception {
			init(session);
			super.sessionOpened(session);
		}
		//客户端会话关闭"
		@Override
		public void sessionClosed(IoSession session) throws Exception {
			close(session);
			super.sessionClosed(session);
		}
		//客户端会话休眠
		@Override
		public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
			close(session);
			super.sessionIdle(session, status);
		}
		//客户端异常
		@Override
		public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
			close(session);
			super.exceptionCaught(session, cause);
		}
		
		//服务端获取消息
		@Override
		public void messageReceived(IoSession session, Object message) throws Exception {
			int type = 0;
			IoBuffer ioBuffer = (IoBuffer) message;
			byte[] buff = new byte[ioBuffer.limit()];
			ioBuffer.get(buff);
			for(Map.Entry<DTU, PartSensor> e: messageQueue.getCacheData().entrySet() ){
				if(e.getKey().getId() == Integer.parseInt(session.getAttribute("dtuId").toString())){
					Set<LMU> set = e.getKey().getLmus();
					for (LMU lmu : set) {
						if(lmu.getCode() == buff[0]){
							type = lmu.getType();
						}
					}
				}
			}
			portocolFactory.getProtocol(type).anlysis(buff,session);
			super.messageReceived(session, message);
		}

	}
}