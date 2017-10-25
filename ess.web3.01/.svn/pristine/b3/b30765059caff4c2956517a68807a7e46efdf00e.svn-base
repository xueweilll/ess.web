package com.action.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.google.gson.reflect.TypeToken;
import com.hibernate.entity.DTU;
import com.hibernate.entity.LMU;
import com.service.DtuService;
import com.service.LmuService;

public class DTUAction extends ActionBase {

	/**
	 * ҳ��Action
	 */
	private static final long serialVersionUID = 1L;

	public String execute() {

		return "success";
	}

	/**
	 * ajax method
	 */
	@Autowired
	private DtuService dtuService;

	/*public void setDtuService(DtuService dtuService) {
		this.dtuService = dtuService;
	}*/

	private int status;

	public void setStatus(int status) {
		this.status = status;
	}

	/*
	 * private int id; private String code; private String name; private String
	 * address; private String IP; private double dtuX; private double dtuY;
	 * private int stationId; private int level; private int Port; private
	 * boolean isTogether; private int eqNum;
	 * 
	 * public void setId(int id) { this.id = id; }
	 * 
	 * public void setCode(String code) { this.code = code; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 * 
	 * public void setAddress(String address) { this.address = address; }
	 * 
	 * public void setIP(String iP) { IP = iP; }
	 * 
	 * public void setDtuX(double dtuX) { this.dtuX = dtuX; }
	 * 
	 * public void setDtuY(double dtuY) { this.dtuY = dtuY; }
	 * 
	 * public void setStationId(int stationId) { this.stationId = stationId; }
	 * 
	 * public void setLevel(int level) { this.level = level; }
	 * 
	 * public void setPort(int port) { Port = port; }
	 * 
	 * public void setTogether(boolean isTogether) { this.isTogether =
	 * isTogether; }
	 * 
	 * public void setEqNum(int eqNum) { this.eqNum = eqNum; }
	 */
	private boolean result;

	public boolean getResult() {
		return result;
	}

	private DTU dtu;

	public void setDtu(String dtu) {
		//this.dtu = (DTU) JsonUtil.getDTO(dtu, DTU.class);
		this.dtu = gson.fromJson(dtu, DTU.class);
	}

	private List<LMU> listLmu =  new ArrayList<LMU>();

	//@SuppressWarnings("unchecked")
	public void setListLmu(String listLmu) {
		//this.listLmu = (List<LMU>) JsonUtil.getDTOList(listLmu, LMU.class);
		//this.listLmu = gson.fromJson(listLmu,(new ArrayList<LMU>()).getClass());
		this.listLmu = gson.fromJson(listLmu,new TypeToken<List<LMU>>(){}.getType());
	}

	public String DtuEdit() {
		/*
		 * System.out.println("this received data is "+jsonObj); JSONArray array
		 * = JSONArray.fromObject(jsonObj); List<LMU> list = new ArrayList<>();
		 * for(int i=0;i<array.size();i++){ JSONObject obj =
		 * array.getJSONObject(i); LMU lmu = (LMU)
		 * JSONObject.toBean(obj,LMU.class); list.add(lmu);
		 * System.out.println("this is the lmu's name "+lmu.getName()); }
		 */
		/*
		 * System.out.println("this is the dtu's name "+dtu.getName()); for(LMU
		 * lmu : listLmu){
		 * System.out.println("this is the lmu's name "+lmu.getName()); }
		 */

		result = false;

		if (status == 0) { // ɾ��
			try {
				dtu = dtuService.dtu(dtuId);
				List<LMU> lmulist = lmuService.LMUlist(dtuId);
				dtuService.DeleteDTUAndLMUS(dtuId);
				SaveSysLogByUser(1,
						this.getText("PUBLIC_DL") + this.getText("DTU_T") + ":"
								+ dtu.getName());
				for (LMU lmu : lmulist) {
					SaveSysLogByUser(1,
							this.getText("PUBLIC_DL") + this.getText("LMU_T")
									+ ":" + lmu.getName() + "(" + dtu.getName()
									+ ")");
				}
				result = true;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			if (status == 1) { // ����
				try {
					dtuService.SaveDTUAndLMUS(dtu, listLmu);
					SaveSysLogByUser(1,
							this.getText("PUBLIC_IS") + this.getText("DTU_T")
									+ ":" + dtu.getName());
					for (LMU lmu : listLmu) {
						SaveSysLogByUser(
								1,
								this.getText("PUBLIC_IS")
										+ this.getText("LMU_T") + ":"
										+ lmu.getName() + "(" + dtu.getName()
										+ ")");
					}
					result = true;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else if (status == 2) { // �༭
				try {
					dtuService.UpdateDTUAndLMUS(dtu, listLmu);
					SaveSysLogByUser(1,
							this.getText("PUBLIC_UP") + this.getText("DTU_T")
									+ ":" + dtu.getName());
					for (LMU lmu : listLmu) {
						SaveSysLogByUser(
								1,
								this.getText("PUBLIC_UP")
										+ this.getText("LMU_T") + ":"
										+ lmu.getName() + "(" + dtu.getName()
										+ ")");
					}
					result = true;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return "success";
	}

	private List<LMU> arrLmu;

	public List<LMU> getArrLmu() {
		return arrLmu;
	}

	@Autowired
	private LmuService lmuService;

	/*public void setLmuService(LmuService lmuService) {
		this.lmuService = lmuService;
	}*/

	private int dtuId;

	public void setDtuId(int dtuId) {
		this.dtuId = dtuId;
	}

	public String CreateLmus() {
		arrLmu = lmuService.LMUlist(dtuId);
		/*if(arrLmu==null){
			arrLmu = new ArrayList<LMU>();
		}*/
		return "success";
	}
}
