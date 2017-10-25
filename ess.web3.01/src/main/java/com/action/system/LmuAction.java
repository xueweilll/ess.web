package com.action.system;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.hibernate.entity.Lmulimit;
import com.service.LmuLimitService;
import com.unit.LmuMsgQuence;

public class LmuAction extends ActionBase {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	
	private int totalCount;
	
	private int start;
	
	private int limit;
	
	private int type;
	
	private int rate;
	
	private String msg;
	
	private String up;
	
	private String down;
	
	private int status;
	
	private int id;
	
	private List<Lmulimit> lmuLimitList;
	@Autowired
	private LmuLimitService limitService;
	@Autowired
	private LmuMsgQuence lmuMsgQuence;
	
	public String execute() {
		return "success";
	}
	
	
	public List<Lmulimit> getLmuLimitList() {
		return lmuLimitList;
	}



	public void setLmuLimitList(List<Lmulimit> lmuLimitList) {
		this.lmuLimitList = lmuLimitList;
	}

	
	
	private Lmulimit getLmuLimit(){
		Lmulimit lmuLimit = new Lmulimit();
		lmuLimit.setId(id);
		lmuLimit.setMsg(msg);
		lmuLimit.setDown(new BigDecimal(down));
		lmuLimit.setUp(new BigDecimal(up));
		lmuLimit.setRate(rate);
		lmuLimit.setType(type);
		return lmuLimit;
	}
	
	public String ListLmuLimit() {
		String hql = "from Lmulimit where 1=1";
		int firstResult = start;
		int maxResult = limit;
		totalCount = 0;
		try {
			totalCount = limitService.count("select count(*) " + hql);
		} catch (Exception ex) {
			ex.printStackTrace();
			totalCount = 0;
		}
		lmuLimitList = limitService.lmuLimitList(hql, firstResult, maxResult);
		return "success";
	}
	
	
	public String EditLmuLimit(){
		Lmulimit lmulimit = getLmuLimit();
		if(status == 0){
			limitService.saveLmuLimit(lmulimit);
			SaveSysLogByUser(1,this.getText("PUBLIC_IS")+this.getText("MENU_VIII")+":"+lmulimit.getMsg());
		}else{
			limitService.updateLmuLimit(lmulimit);
			SaveSysLogByUser(1,this.getText("PUBLIC_UP")+this.getText("MENU_VIII")+":"+lmulimit.getMsg());
		}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("up", lmulimit.getUp());
			map.put("down", lmulimit.getDown());
			map.put("rate", lmulimit.getRate());
			lmuMsgQuence.put(lmulimit.getType(), map);
		return "success";
	}


	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int getRate() {
		return rate;
	}


	public void setRate(int rate) {
		this.rate = rate;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	


	public String getUp() {
		return up;
	}


	public void setUp(String up) {
		this.up = up;
	}


	public String getDown() {
		return down;
	}


	public void setDown(String down) {
		this.down = down;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
