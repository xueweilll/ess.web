package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.User;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BaseDao baseDao;
	
	/*public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}*/



	@Override
	public User login(User user) {
		List<User> list =baseDao.findAll("from User where username=?and password=?",
				User.class,new Object[]{user.getUsername(),user.getPassword()});
		if(list.size()==1){
			return list.get(0);
		}
		return null;
	}



	@Override
	public List<User> listUser(String hql, int firstResult,
			int maxResult) {
		List<User> list = baseDao.findByPage(hql, User.class, firstResult, maxResult);
		return list;
	}

	@Override
	public void saveUser(User user) {
		baseDao.save(user);
	}



	@Override
	public void updateUser(User user) {
		baseDao.update(user);
	}



	@Override
	public void deleteUser(int id) {
		baseDao.delete(User.class, id);
	}



	@Override
	public User user(int id) {
		return baseDao.get(User.class, id);
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

}
