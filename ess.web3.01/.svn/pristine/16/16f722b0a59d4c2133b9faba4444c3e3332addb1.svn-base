package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.Menu;
import com.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private BaseDao baseDao;
	
	/*public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	*/
	@Override
	public List<Menu> menulist() {
		List<Menu> listmenu = baseDao.findAll("from Menu", Menu.class);
		return listmenu;
 	}

	@Override
	public int IndexToMid(String name) {
		List<Menu> list = baseDao.findAll("from Menu where url='"+name+"'", Menu.class);
		if(list!=null){
			if(list.size()!=0){
				return list.get(0).getId();
			}
		}
		return 0;
	}

}
