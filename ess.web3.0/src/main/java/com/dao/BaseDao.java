package com.dao;

import java.util.List;

public interface BaseDao {
	public <T> void save(T t);
	
	public <T> void delete(T t);
	
	public <T> void delete(Class<T> entityClass,Integer id);
	
	public <T> void delete(Class<T> entityClass,String id);
	
	public <T> void delete(String hql);
	
	public <T> void update(T t);
	
	public <T> T get(Class<T> entityClass,Integer id);
	
	public <T> T get(Class<T> entityClass,String id);
	
	public <T> T getbywhere(Class<T> entityClass,String hql);
	
	public<T> List<T> findByProc(String procName,Class<T> entityClass);
	
	public <T> List<T> findAll(String hql,Class<T> entityClass);
	
	public <T> List<T> findAll(String hql,Class<T> entityClass, Object param);
	
	public <T> List<T> findAll(String hql,Class<T> entityClass, Object[] params);
	
	public <T> List<T> findAll(String hql,Class<T> entityClass, Object[] params, String paramname);
	
	public <T> List<T> findByPage(final String hql,final Class<T> entityClass,final int firstResilt,final int maxResult);
	
	public <T> List<T> findByPage(final String hql,final Class<T> entityClass,final Object param,final int firstResult,final int maxResult);
	
	public <T> List<T> findByPage(final String hql,final Class<T> entityClass,final Object[] params,final int firstResult,final int maxResult);
	
}
