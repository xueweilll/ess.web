package com.sys;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.config.Program;
//import com.old.domain.Params;
import com.hibernate.entity.Params;
import com.service.ParamsService;

//----ϵͳ���в���----
public class Paramers {

	private static Paramers paramers;
	// ϵͳ����
	private String language = "Ch";
	// public String language="En";

	// ϵͳIP
	private String local = "127.0.0.1";

	// ���������˿�
	private int port;
	// private int port=1218;
	// private int port=1214;

	private boolean debug;

	public String getLanguage() {
		return language;
	}

	public String getLocal() {
		return local;
	}

	public int getPort() {
		return port;
	}

	public boolean getDebug() {
		return debug;
	}

	private Paramers() {
		// Locale locale = Locale.getDefault();
		// language = locale.getLanguage();
		init();
	}

	public static SimpleDateFormat datetimeformate = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat dateformate = new SimpleDateFormat(
			"yyyy-MM-dd");
	@Autowired
	private ParamsService paramsService;

	private void init() {
		/*
		 * Params.SetValue(); local = Params.getIP(); port =
		 * Integer.parseInt(Params.getPort()); language = Params.getLanguage();
		 * SENDTIME = Integer.parseInt(Params.getSENDTIME()); READTIME =
		 * Integer.parseInt(Params.getREADTIME()); POLLTIME =
		 * Integer.parseInt(Params.getPOLLTIME()); SQLTIME =
		 * Integer.parseInt(Params.getSQLTIME()); REFRESH =
		 * Integer.parseInt(Params.getRefresh());
		 */

		//List<Params> list = paramsService.ParamsList();
		List<Params> list = paramsService.Paramslist();
		for (Params params : list) {
			switch (params.getName()) {
			case "IP":
				local = params.getValue();
				continue;
			case "Port":
				port = Integer.parseInt(params.getValue());
				continue;
			case "Refresh":
				REFRESH = Integer.parseInt(params.getValue());
				continue;
			case "Language":
				language = params.getValue();
				continue;
			case "SENDTIME":
				SENDTIME = Integer.parseInt(params.getValue());
				continue;
			case "READTIME":
				READTIME = Integer.parseInt(params.getValue());
				continue;
			case "POLLTIME":
				POLLTIME = Integer.parseInt(params.getValue());
				continue;
			case "SQLTIME":
				SQLTIME = Integer.parseInt(params.getValue());
				continue;
			}
		}

		debug = Program.GetInstance().getDebug();
		// System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+port);
	}

	public int SENDTIME = 5000; // ����ʱ����
	public int READTIME = 500; // ��ȡʱ����
	public int POLLTIME = 5000; // ��ѯʱ����
	public int SQLTIME = 12; // ��ݿ���ʱ��
	public int REFRESH = 5; // ����ˢ��ʱ��

	/*
	 * public static synchronized Paramers GetInstance(){ if(paramers==null){
	 * paramers = new Paramers(); } return paramers; }
	 */

	// ����ģʽ-double-check locking
	public static Paramers GetInstance() {
		if (paramers == null) {
			synchronized (Paramers.class) {
				if (paramers == null) { // double check if it is created
					paramers = new Paramers();
				}
			}
		}
		return paramers;
	}

	public static void Dispose() {
		// paramers.Dispose();
		paramers = null;
	}

	public static void main(String[] args) {
		System.out.println(Paramers.GetInstance().getPort());
	}
}
