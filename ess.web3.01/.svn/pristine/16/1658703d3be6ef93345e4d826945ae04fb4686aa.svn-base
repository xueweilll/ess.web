package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.Model;
import com.service.ModelService;

@Service
public class ModelServiceImpl implements ModelService{
	@Autowired
	private BaseDao baseDao;
/*
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
*/
	@Override
	public List<Model> Modellist() {
		return baseDao.findAll("from Model", Model.class);
	}

	@Override
	public Model model(int id) {
		return baseDao.get(Model.class, id);
	}

	@Override
	public void saveModel(Model model) {
		baseDao.save(model);
	}

	@Override
	public void updateModel(Model model) {
		baseDao.update(model);
	}

	@Override
	public void deleteModel(int id) {
		baseDao.delete(Model.class,id);
	}

	/*@Override
	public List<Model> Modellist(String lmuIds) {
		return baseDao.findAll("from Model Where lmuId in ("+lmuIds+")", Model.class);
	}*/

}
