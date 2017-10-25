package com.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Program {

	private static Program program;
	private Properties p;
	private String mysqlUrl;
	private String mysqlUser;
	private String mysqlPwd;

	private String oracleUrl;
	private String oracleUser;
	private String oraclePwd;
	private String dataInterchargeType;
	
	private boolean dataCenter;
	private boolean debug;
	
	public String getDataInterchargeType() {
		dataInterchargeType=GetVal("dataInterchargeType");
		return dataInterchargeType;
	}

	public String getMysqlUrl() {
		mysqlUrl=GetVal("mysql");
		return mysqlUrl;
	}
	
	public String getMysqlUser(){
		mysqlUser=GetVal("mysqluser");
		return mysqlUser;
	}
	
	public String getMysqlPwd(){
		mysqlPwd=GetVal("mysqlpwd");
		return mysqlPwd;
	}
	
	public String getOracleUser(){
		oracleUser=GetVal("oracleuser");
		return oracleUser;
	}
	
	public String getOraclePwd(){
		oraclePwd=GetVal("oraclepwd");
		return oraclePwd;
	}

	public String getOracleUrl() {
		oracleUrl=GetVal("oracle");
		return oracleUrl;
	}
	

	public boolean getDataCenter() {
		String str = GetVal("dataCenter");
		dataCenter=Boolean.parseBoolean(str);
		return dataCenter;
	}

	public boolean getDebug() {
		String str = GetVal("debug");
		debug=Boolean.parseBoolean(str);
		return debug;
	}

	public static void main(String[] args) {
		Program pro = new Program();
		//String str = pro.GetVal("oracle");
		JOptionPane.showConfirmDialog(null, pro.getDebug());
	}

	private void loadConfig() {
		InputStream inputStream = Program.class.getClassLoader()
				.getResourceAsStream("DBConfig.properties"); 
		p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		/*String str = "mysql:" + p.getProperty("mysql") + ",oracle:"
				+ p.getProperty("oracle");
		System.out.println(str);
		JOptionPane.showConfirmDialog(null, str);*/
	}

	
	public String GetVal(String dom){
		return p.getProperty(dom);
	}

	private Program(){
		loadConfig();
	}

	public static Program GetInstance(){
		if(program==null){
			program=new Program();
		}
		return program;
	}
}
