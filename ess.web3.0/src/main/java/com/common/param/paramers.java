package com.common.param;

import java.text.SimpleDateFormat;

import org.apache.struts2.dispatcher.Dispatcher;

import com.opensymphony.xwork2.inject.Container;

public class paramers {
	private static paramers param;
	public static SimpleDateFormat datetimeformate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat dateformate = new SimpleDateFormat("yyyy-MM-dd");
	private paramers(){
		Container con=Dispatcher.getInstance().getConfigurationManager().getConfiguration().getContainer();
		extension=con.getInstance(String.class,"struts.action.extension");
		//System.out.println(extension);
	}
	public static paramers create(){
		if(param==null){
			param=new paramers();
		}
		return param;
	}
	public static String extension;
}
