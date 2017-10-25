package com.action.monitor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.hibernate.entity.Model;
import com.service.ModelService;

public class ModelAction extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Model> modelList;

	public List<Model> getModelList() {
		return modelList;
	}

	@Autowired
	private ModelService modelService;

	/*public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}*/

	public String ModelData() {
		modelList = modelService.Modellist();
		return "success";
	}

	private Model model;

	public void setModel(String model) {
		//this.model = (Model)JsonUtil.getDTO(model, Model.class);
		this.model = gson.fromJson(model, Model.class);
	}

	private int oper;

	public void setOper(int oper) {
		this.oper = oper;
	}

	private boolean result;

	public boolean getResult() {
		return result;
	}

	public String ModelEdit() {
		switch (oper) {
		case 0:
			try {
				modelService.saveModel(model);
				SaveSysLogByUser(1,this.getText("PUBLIC_IS") + this.getText("MODEL_T")
						+ ":" + model.getModelName());
				result = true;
			} catch (Exception ex) {
				ex.printStackTrace();
				result = false;
			}
			break;
		case 1:
			try {
				modelService.updateModel(model);
				SaveSysLogByUser(1,this.getText("PUBLIC_UP") + this.getText("MODEL_T")
						+ ":" + model.getModelName());
				result = true;
			} catch (Exception ex) {
				ex.printStackTrace();
				result = false;
			}
			break;
		case 2:
			try {
				modelService.deleteModel(model.getId());
				SaveSysLogByUser(1,this.getText("PUBLIC_DL") + this.getText("MODEL_T")
						+ ":" + model.getModelName());
				result = true;
			} catch (Exception ex) {
				ex.printStackTrace();
				result = false;
			}
			break;
		}
		return "success";
	}
}
