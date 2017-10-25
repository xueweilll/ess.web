package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.Menu;
import com.hibernate.entity.Role;
import com.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private BaseDao baseDao;
	
/*	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}*/
	
	
	@Override
	public List<Role> Rolelist(String hql, int firstResult,
			int maxResult) {
//		return baseDao.findAll("from Role", Role.class);
		List<Role> list = baseDao.findByPage(hql, Role.class, firstResult, maxResult);
		return list;
	}

	@Override
	public Role role(int id) {
		return baseDao.get(Role.class,id);
	}

	@Override
	public void saveRole(Role role) {
		baseDao.save(role);
	}

	@Override
	public void updateRole(Role role) {
		baseDao.update(role);
	}

	@Override
	public void deleteRole(int id) {
		baseDao.delete(Role.class, id);
	}

	@Override
	public int count(String hql) {
		int count = 0;
		List<Integer> list = baseDao.findAll(hql, Integer.class);
		if(list!=null){
			Number num = (Number)list.get(0);
			count = num.intValue();
			System.out.println(count);
		}
		return count;
	}
	
	@Override
	public List<Menu> menuList(String hql) {
		List<Menu> list= baseDao.findAll(hql, Menu.class);
		return list;
	}


	@Override
	public List<Role> Roles() {
		return baseDao.findAll("from Role", Role.class);
	};
}
