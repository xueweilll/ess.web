package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.LMU;
import com.service.LmuService;

@Service("lmuService")
public class LmuServiceImpl implements LmuService{

	@Autowired
	private BaseDao baseDao;
	/*public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}*/
	
	@Override
	public List<LMU> LMUlist() {
		return baseDao.findAll("from LMU", LMU.class);
	}
	
	@Override
	public List<LMU> LMUlist(String Code) {
		try{
			return baseDao.findAll("from LMU l where l.dtu.Code='"+Code+"'", LMU.class);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public LMU lmu(int id) {
		return baseDao.get(LMU.class, id);
	}

	@Override
	public void saveLMU(LMU lmu) {
		baseDao.save(lmu);
	}

	@Override
	public void updateLMU(LMU lmu) {
		baseDao.update(lmu);
	}

	@Override
	public void deleteLMU(int id) {
		baseDao.delete(LMU.class,id);
	}

	@Override
	public List<LMU> LMUlistbyDtuId(Integer[] DtuIds) {
		String hql="from LMU l where l.dtu.id in(:DtuIds)";
		return baseDao.findAll(hql, LMU.class,DtuIds,"DtuIds");
	}

	@Override
	public List<LMU> LMUlistbyLmuId(Integer[] LmuIds) {
		String hql="from LMU l where l.id in(:LmuIds)";
		return baseDao.findAll(hql, LMU.class,LmuIds,"LmuIds");
	}

	@Override
	public List<LMU> LMUlist(int DtuId) {
		try{
			return baseDao.findAll("from LMU l where l.dtu.id='"+DtuId+"'", LMU.class);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	
	@Override
	public int SelectId(int dtuId, int code) {
		LMU lmu = baseDao.getbywhere(LMU.class, "from LMU l where  l.dtuId='"+dtuId+"' and code='"+code+"'");
		return lmu.getId();
	}

	@Override
	public List<LMU> lmuLimitList(String hql, int firstResult, int maxResult) {
		List<LMU> list = baseDao.findByPage(hql, LMU.class, firstResult, maxResult);
		return list;
	}
	
	@Override
	public int count(String hql) {
		int count = 0;
		List<Integer> list = baseDao.findAll(hql, Integer.class);
		if(list!=null){
			Number num = (Number)list.get(0);
			count = num.intValue();
		}
		return count;
	}

}
