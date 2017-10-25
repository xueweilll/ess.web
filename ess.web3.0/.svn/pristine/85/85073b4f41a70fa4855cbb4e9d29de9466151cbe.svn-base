package com.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.Params;
import com.service.ParamsService;

@Service
public class ParamsServiceImpl implements ParamsService{

	@Autowired
	private BaseDao baseDao;
	/*public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	*/
	@Override
	public List<Params> Paramslist() {
		return baseDao.findAll("from Params", Params.class);
	}

	@Override
	public void updateParams(List<Params> params) {
		Params p;
		Params p2;
		for(Iterator<Params> it =params.iterator();it.hasNext();){
			p=it.next();
			p2=baseDao.getbywhere(Params.class, "from Params where Name='"+p.getName()+"'");
			p2.setValue(p.getValue());
			baseDao.save(p2);
		}
	}

	@Override
	public String getValue(String Name) {
		return baseDao.findAll("from Params Where Name='"+Name+"'", Params.class).get(0).getValue();
	}

	@Override
	public void updateParam(Params param) {
		baseDao.update(param);
	}

}
