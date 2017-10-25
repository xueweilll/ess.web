package com.action.system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.hibernate.entity.Params;
import com.service.ParamsService;

public class ParamsAction extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8209198308737687778L;
	private String IP;
	private String Port;
	private String Language;
	private String Refresh;
	@Autowired
	private ParamsService paramsService;
	private boolean isdefault = false;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public void setParamsService(ParamsService paramsService) {
		this.paramsService = paramsService;
	}*/

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getPort() {
		return Port;
	}

	public void setPort(String port) {
		Port = port;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public String getRefresh() {
		return Refresh;
	}

	public void setRefresh(String refresh) {
		Refresh = refresh;
	}

	private List<Params> paramsList;

	public List<Params> getParamsList() {
		return paramsList;
	}

	private void init() {
		Params params = null;
		for (Iterator<Params> it = paramsList.iterator(); it.hasNext();) {
			params = it.next();
			System.out.println(params.getName());
			switch (params.getName()) {
			case "IP":
				if (isdefault) {
					setIP(params.getDefaultValue());
				} else {
					setIP(params.getValue());
				}
				break;
			case "Port":
				if (isdefault) {
					setPort(params.getDefaultValue());
				} else {
					setPort(params.getValue());
				}
				break;
			case "Language":
				if (isdefault) {
					setLanguage(params.getDefaultValue());
				} else {
					setLanguage(params.getValue());
				}
				break;
			case "Refresh":
				if (isdefault) {
					setRefresh(params.getDefaultValue());
				} else {
					setRefresh(params.getValue());
				}
				break;
			}
		}
	}

	public String param() {
		paramsList = paramsService.Paramslist();
		init();
		return "success";
	}

	public String update() {
		paramsList = null;
		paramsList = getParams();
		paramsService.updateParams(paramsList);
		paramsList = paramsService.Paramslist();
		init();
		return "success";
	}

	public String getdefault() {
		paramsList = paramsService.Paramslist();
		isdefault = true;
		init();
		return "success";
	}

	private List<Params> getParams() {
		paramsList = new ArrayList<Params>();
		Params params = new Params();
		params.setName("IP");
		params.setValue(IP);
		paramsList.add(params);
		params = new Params();
		params.setName("Port");
		params.setValue(Port);
		paramsList.add(params);
		params = new Params();
		params.setName("Refresh");
		params.setValue(Refresh);
		paramsList.add(params);
		params = new Params();
		params.setName("Language");
		params.setValue(Language);
		paramsList.add(params);
		return paramsList;
	}
}
