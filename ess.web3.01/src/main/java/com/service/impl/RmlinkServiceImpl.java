package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.Rmlink;
import com.service.RmlinkService;

@Service
public class RmlinkServiceImpl implements RmlinkService {
	
	@Autowired
	private BaseDao baseDao;
	
	/*public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}*/
	
	@Override
	public List<Rmlink> Rmlinklist(int Rid) {
		return baseDao.findAll("from Rmlink where Rid="+Rid+"", Rmlink.class);
	}

	@Override
	public Rmlink rmlink(int id) {
		return baseDao.get(Rmlink.class, id);
	}

	@Override
	public void saveRmlink(Rmlink rmlink) {
		baseDao.save(rmlink);
	}

	@Override
	public void updateRmlink(Rmlink rmlink) {
		baseDao.update(rmlink);
	}

	@Override
	public void deleteRmlink(int Rid) {
		baseDao.delete("delete Rmlink where Rid="+Rid+"");
	}

	@Override
	public void deleteRmlink(int Rid, int Mid) {
		baseDao.delete("delete Rmlink where Rid="+Rid+" and Mid="+Mid+"");
	}

}
