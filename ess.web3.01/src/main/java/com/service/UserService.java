package com.service;

import java.util.List;

import com.hibernate.entity.User;

public interface UserService {
	public User login(User user);
	public User user(int id);
	public List<User> listUser(String hql, int firstResult,
			int maxResult);
 	public void saveUser(User user);
 	public void updateUser(User user);
 	public void deleteUser(int id);
 	public int count(String hql);
}
