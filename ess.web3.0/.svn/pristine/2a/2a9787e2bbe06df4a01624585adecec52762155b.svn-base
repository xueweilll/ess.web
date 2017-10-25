package com.action.monitor;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.hibernate.entity.DTU;
import com.hibernate.entity.LMU;
import com.hibernate.entity.Model;
import com.hibernate.entity.Msg;
import com.protocol.ReadR;
import com.protocol.WriteHR;
import com.service.DtuService;
import com.service.LmuService;
import com.service.MsgService;

public class AllmtAction extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() {
		return "success";
	}

	private Integer[] selections;

	public void setSelections(Integer[] selections) {
		this.selections = selections;
	}

	private Model model;

	public void setModel(String model) {
		//this.model = (Model) JsonUtil.getDTO(model, Model.class);
		this.model = gson.fromJson(model, Model.class);
	}
	
	@Autowired
	private LmuService lmuService;

	public String ModelApply() {
		try {
			List<LMU> list = lmuService.LMUlistbyLmuId(selections);
			for (LMU lmu : list) {
				if (model.getPreTime() != 0) {
					WriteHR(lmu, String.valueOf(model.getPreTime()), 0);
				}
				if (model.getSpeed() != 0) {
					WriteHR(lmu, String.valueOf(model.getSpeed()), 4);
				}
				if (!model.getT1().isEmpty()) {
					WriteHR(lmu, model.getT1(), 5);
				}
				if (!model.getV1().isEmpty()) {
					WriteHR(lmu, model.getV1(), 6);
				}
				if (!model.getT2().isEmpty()) {
					WriteHR(lmu, model.getT2(), 7);
				}
				if (!model.getV2().isEmpty()) {
					WriteHR(lmu, model.getV2(), 8);
				}
				if (!model.getT3().isEmpty()) {
					WriteHR(lmu, model.getT3(), 9);
				}
				if (!model.getV3().isEmpty()) {
					WriteHR(lmu, model.getV3(), 10);
				}
				if (!model.getT4().isEmpty()) {
					WriteHR(lmu, model.getT4(), 11);
				}
				if (!model.getV4().isEmpty()) {
					WriteHR(lmu, model.getV4(), 12);
				}
				if (!model.getT5().isEmpty()) {
					WriteHR(lmu, model.getT5(), 13);
				}
				if (!model.getV5().isEmpty()) {
					WriteHR(lmu, model.getV5(), 14);
				}
				if (!model.getT6().isEmpty()) {
					WriteHR(lmu, model.getT6(), 15);
				}
				if (!model.getV6().isEmpty()) {
					WriteHR(lmu, model.getV6(), 16);
				}
				if (!model.getT7().isEmpty()) {
					WriteHR(lmu, model.getT7(), 17);
				}
				if (!model.getV7().isEmpty()) {
					WriteHR(lmu, model.getV7(), 18);
				}
				if (!model.getT8().isEmpty()) {
					WriteHR(lmu, model.getT8(), 19);
				}
				if (!model.getV8().isEmpty()) {
					WriteHR(lmu, model.getV8(), 20);
				}
				ReadHr(lmu);
				resultStr = "执行成功，请耐心等待结果！";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			resultStr = "执行失败！";
		}
		return "success";
	}

	private String resultStr;

	public String getResultStr() {
		return resultStr;
	}

	public String Reflush() {
		try {
			List<LMU> list = lmuService.LMUlistbyLmuId(selections);
			for (LMU lmu : list) {
				ReadHr(lmu);
			}
			resultStr = "执行成功，请耐心等待结果！";
		} catch (Exception ex) {
			ex.printStackTrace();
			resultStr = "执行失败！";
		}
		return "success";
	}

	@Autowired
	private MsgService msgService;

	@Autowired
	private DtuService dtuService;

	public void ReadHr(LMU lmu) {
		ReadR readR = new ReadR();
		int code = lmu.getCode();
		DTU dtu = dtuService.dtu(lmu.getDtuId());
		String data = "";
		Msg msg = new Msg();
		if (dtu.getIsTogether()) {
			data = readR.GetReadHRComByGroup(code,1, 0);
		} else {
			data = readR.GetReadHRCom(code);
		}
		msg.setPos(0);
		msg.setData(data);
		msg.setDtuId(dtu.getId());
		msg.setEntryDate(Calendar.getInstance());
		msg.setLmuId(lmu.getId());
		msg.setMode(false);
		msg.setType(true);
		msgService.saveMsg(msg);
	}
	
	private String GetPosValue(int pos) {
		String result;
		switch (pos) {
		case 0:
			result = this.getText("PUBLIC_PT");
			break;
		case 4:
			result = this.getText("PUBLIC_AR");
			break;
		case 5:
			result = this.getText("PUBLIC_T") + "1";
			break;
		case 6:
			result = this.getText("PUBLIC_V") + "1";
			break;
		case 7:
			result = this.getText("PUBLIC_T") + "2";
			break;
		case 8:
			result = this.getText("PUBLIC_V") + "2";
			break;
		case 9:
			result = this.getText("PUBLIC_T") + "3";
			break;
		case 10:
			result = this.getText("PUBLIC_V") + "3";
			break;
		case 11:
			result = this.getText("PUBLIC_T") + "4";
			break;
		case 12:
			result = this.getText("PUBLIC_V") + "4";
			break;
		case 13:
			result = this.getText("PUBLIC_T") + "5";
			break;
		case 14:
			result = this.getText("PUBLIC_V") + "5";
			break;
		case 15:
			result = this.getText("PUBLIC_T") + "6";
			break;
		case 16:
			result = this.getText("PUBLIC_V") + "6";
			break;
		case 17:
			result = this.getText("PUBLIC_T") + "7";
			break;
		case 18:
			result = this.getText("PUBLIC_V") + "7";
			break;
		case 19:
			result = this.getText("PUBLIC_T") + "8";
			break;
		case 20:
			result = this.getText("PUBLIC_V") + "8";
			break;
		case 27:
			result = this.getText("PUBLIC_CV");
			break;
		case 28:
			result = this.getText("PUBLIC_P");
			break;
		default:
			result = "";
			break;
		}
		return result;
	}

	private void WriteHR(LMU lmu, String val, int p) {
		WriteHR writeHR = new WriteHR();
		Msg msg = new Msg();
		msg.setData(writeHR.GetWriteHrCmd(val, p, lmu.getCode()));
		msg.setDtuId(lmu.getDtuId());
		msg.setEntryDate(Calendar.getInstance());
		msg.setLmuId(lmu.getId());
		msg.setPos(p);
		msg.setMode(false);
		msg.setType(false);// true 为系统发送，false 为手动发送。
		msgService.saveMsg(msg);
		SaveSysLogByUser(2, " DTU:" + lmu.getDtu().getName() + " LMU:" + lmu.getName() + " "
				+ GetPosValue(p) + " " + this.getText("PUBLIC_SET") + " "
				+ val);
	}
}
