package com.action.system;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.hibernate.entity.LMU;
import com.hibernate.entity.Lmulimit;
import com.service.LmuLimitService;
import com.service.LmuService;
import com.unit.LmuMsgQuence;
import com.untils.BeanToMapUtils;

public class LmuAction extends ActionBase {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	
	private int totalCount;
	
	private int start;
	
	private int limit;
	
	private int type;
	
	private int lmuId;
	
	
	private int status;
	
	private int id;
	
	private int dtuId;
	
	private BigDecimal a1;
	
	private BigDecimal a2;
	
	private BigDecimal w1;
	
	private BigDecimal w2;
	
	private BigDecimal s1;
	
	private BigDecimal s2;
	
	private BigDecimal sd1;
	
	private BigDecimal sd2;
	
	private BigDecimal sd3;
	
	private BigDecimal sd4;
	
	private BigDecimal sd5;
	
	private BigDecimal sd6;
	
	private BigDecimal sd7;
	
	private BigDecimal sd8;
	
	private BigDecimal arate;
	
	private BigDecimal wrate;
	
	private BigDecimal srate;
	
	
	private List<LMU> lmuLimitList;
	@Autowired
	private LmuLimitService limitService;
	@Autowired
	private LmuService lmuService;
	@Autowired
	private LmuMsgQuence lmuMsgQuence;
	
	public String execute() {
		return "success";
	}
	
	
	public List<LMU> getLmuLimitList() {
		return lmuLimitList;
	}



	public void setLmuLimitList(List<LMU> lmuLimitList) {
		this.lmuLimitList = lmuLimitList;
	}

	
	

	/*private Lmulimit getLmuLimit(){
		Lmulimit lmuLimit = new Lmulimit();
		lmuLimit.setId(id);
		lmuLimit.setMsg(msg);
		lmuLimit.setDown(new BigDecimal(down));
		lmuLimit.setUp(new BigDecimal(up));
		lmuLimit.setRate(rate);
		lmuLimit.setType(type);
		return lmuLimit;
	}*/
	
	public String ListLmuLimit() {
		String hql = "from LMU L where L.dtu.id = '" + dtuId +"'";
		int firstResult = start;
		int maxResult = limit;
		totalCount = 0;
		try {
			totalCount = lmuService.count("select count(*) " + hql);
		} catch (Exception ex) {
			ex.printStackTrace();
			totalCount = 0;
		}
		
		lmuLimitList = lmuService.lmuLimitList(hql, firstResult, maxResult);
		if(lmuLimitList.size() != 0){
			List<Lmulimit> listLimit = limitService.getBylmuIdIn(lmuLimitList);
			for (LMU lmu : lmuLimitList) {
				for (Lmulimit lmulimit : listLimit) {
					if(lmu.getId() == lmulimit.getLmuId()){
						lmu.setLmulimit(lmulimit);
					}
				}
			}
		}
		return "success";
	}
	
	@SuppressWarnings("unchecked")
	public String UpdateLmuLimit(){
		Lmulimit lmulimit = getLmuLimit(type);
		limitService.updateLmuLimit(lmulimit);
		lmuMsgQuence.put(lmulimit.getLmuId(), BeanToMapUtils.toMap(lmulimit));
		return "success";
	}
	
	private Lmulimit getLmuLimit(int type){
		Lmulimit lmulimit = new Lmulimit();
		lmulimit.setId(id);
		lmulimit.setType(1);
		lmulimit.setLmuId(lmuId);
		if(type == 1){
			lmulimit.setA1(a1);
			lmulimit.setA2(a2);
			lmulimit.setArate(arate);
		}else if(type == 2){
			lmulimit.setW1(w1);
			lmulimit.setW2(w2);
			lmulimit.setS1(s1);
			lmulimit.setS2(s2);
			lmulimit.setWrate(wrate);
			lmulimit.setSrate(srate);
		}else if(type == 3){
			lmulimit.setSd1(sd1);
			lmulimit.setSd2(sd2);
			lmulimit.setSd3(sd3);
			lmulimit.setSd4(sd4);
			lmulimit.setSd5(sd5);
			lmulimit.setSd6(sd6);
			lmulimit.setSd7(sd7);
			lmulimit.setSd8(sd8);
		}
		return lmulimit;
	}

	
	
	/*public String EditLmuLimit(){
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
	}*/


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


	public int getDtuId() {
		return dtuId;
	}


	public void setDtuId(int dtuId) {
		this.dtuId = dtuId;
	}


	public BigDecimal getA1() {
		return a1;
	}


	public void setA1(BigDecimal a1) {
		this.a1 = a1;
	}


	public BigDecimal getA2() {
		return a2;
	}


	public void setA2(BigDecimal a2) {
		this.a2 = a2;
	}


	public BigDecimal getW1() {
		return w1;
	}


	public void setW1(BigDecimal w1) {
		this.w1 = w1;
	}


	public BigDecimal getW2() {
		return w2;
	}


	public void setW2(BigDecimal w2) {
		this.w2 = w2;
	}


	public BigDecimal getS1() {
		return s1;
	}


	public void setS1(BigDecimal s1) {
		this.s1 = s1;
	}


	public BigDecimal getS2() {
		return s2;
	}


	public void setS2(BigDecimal s2) {
		this.s2 = s2;
	}


	public BigDecimal getSd1() {
		return sd1;
	}


	public void setSd1(BigDecimal sd1) {
		this.sd1 = sd1;
	}


	public BigDecimal getSd2() {
		return sd2;
	}


	public void setSd2(BigDecimal sd2) {
		this.sd2 = sd2;
	}


	public BigDecimal getSd3() {
		return sd3;
	}


	public void setSd3(BigDecimal sd3) {
		this.sd3 = sd3;
	}


	public BigDecimal getSd4() {
		return sd4;
	}


	public void setSd4(BigDecimal sd4) {
		this.sd4 = sd4;
	}


	public BigDecimal getSd5() {
		return sd5;
	}


	public void setSd5(BigDecimal sd5) {
		this.sd5 = sd5;
	}


	public BigDecimal getSd6() {
		return sd6;
	}


	public void setSd6(BigDecimal sd6) {
		this.sd6 = sd6;
	}


	public BigDecimal getSd7() {
		return sd7;
	}


	public void setSd7(BigDecimal sd7) {
		this.sd7 = sd7;
	}


	public BigDecimal getSd8() {
		return sd8;
	}


	public void setSd8(BigDecimal sd8) {
		this.sd8 = sd8;
	}


	public BigDecimal getArate() {
		return arate;
	}


	public void setArate(BigDecimal arate) {
		this.arate = arate;
	}


	public BigDecimal getWrate() {
		return wrate;
	}


	public void setWrate(BigDecimal wrate) {
		this.wrate = wrate;
	}


	public BigDecimal getSrate() {
		return srate;
	}


	public void setSrate(BigDecimal srate) {
		this.srate = srate;
	}


	public int getLmuId() {
		return lmuId;
	}


	public void setLmuId(int lmuId) {
		this.lmuId = lmuId;
	}


	
	
	
	
}