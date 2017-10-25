package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.LmuGroup;
import com.service.LmuGroupService;

@Service
public class LmuGroupServiceImpl implements LmuGroupService {

	@Autowired
	private BaseDao baseDao;
	/*public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}*/
	@Override
	public List<LmuGroup> Grouplist() {
		return baseDao.findAll("from LmuGroup",LmuGroup.class);
	}

	@Override
	public LmuGroup lmuGroup(int id) {
		return baseDao.get(LmuGroup.class, id);
	}

	@Override
	public void saveGroup(LmuGroup lmuGroup) {
		baseDao.save(lmuGroup);
	}

	@Override
	public void updateGroup(LmuGroup lmuGroup) {
		baseDao.update(lmuGroup);
	}

	@Override
	public void deleteGroup(int id) {
		baseDao.delete(LmuGroup.class,id);
	}
	/*@Override
	public Integer[] LmuIds(int id) {
		Integer[] lmuIds;
		List<LmuGroup> list = baseDao.findAll("from LmuGroup where GroupId='"+id+"'",LmuGroup.class);
		if(list==null){
			return null;
		}
		lmuIds=new Integer[list.size()];
		int i=0;
		for(Iterator<LmuGroup> it = list.iterator();it.hasNext();){
			lmuIds[i]=it.next().;
		}
		return null;
	}
*/
}
