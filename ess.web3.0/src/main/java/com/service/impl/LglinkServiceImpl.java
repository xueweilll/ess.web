package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.Lglink;
import com.service.LglinkService;

@Service
public class LglinkServiceImpl implements LglinkService {
	
	@Autowired
	private BaseDao baseDao;
	/*public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}*/
	@Override
	public List<Lglink> Lglinklist(int Gid) {
		return baseDao.findAll("from Lglink where Gid="+Gid+"",Lglink.class);
	}
	@Override
	public Lglink lglink(int id) {
		return baseDao.get(Lglink.class, id);
	}
	@Override
	public void saveLglink(Lglink lglink) {
		baseDao.save(lglink);
	}
	@Override
	public void updateLglink(Lglink lglink) {
		baseDao.update(lglink);
	}
	@Override
	public void deleteLglink(int Gid) {
		baseDao.delete("delete Lglink where Gid="+Gid+"");
	}
	@Override
	public void deleteLglink(int Gid, int Lid) {
		baseDao.delete("delete Lglink where Gid="+Gid+" and Lid="+Lid+"");
	}
}
