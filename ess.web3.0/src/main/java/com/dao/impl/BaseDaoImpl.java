package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.BaseDao;

@Repository
public class BaseDaoImpl implements BaseDao {

	@Autowired
	private SessionFactory sessionFactory;

	/*public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}*/

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public <T> void save(T t) {
		getSession().save(t);
	}

	@Override
	public <T> void delete(T t) {
		getSession().delete(t);
	}

	@Override
	public <T> void delete(Class<T> entityClass, Integer id) {
		getSession().delete(get(entityClass, id));
	}

	@Override
	public <T> void update(T t) {
		getSession().update(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> entityClass, Integer id) {
		return (T) getSession().get(entityClass, id);
	}

	@Override
	public <T> List<T> findAll(String hql, Class<T> entityClass) {
		return findAll(hql, entityClass, new Object[] {});
	}

	@Override
	public <T> List<T> findAll(String hql, Class<T> entityClass, Object param) {
		return findAll(hql, entityClass, new Object[] { param });
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(String hql, Class<T> entityClass, Object[] params) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.list();
	}

	@Override
	public <T> List<T> findByPage(String hql, Class<T> entityClass,
			int firstResult, int maxResult) {
		return findByPage(hql, entityClass, new Object[] {}, firstResult,
				maxResult);
	}

	@Override
	public <T> List<T> findByPage(String hql, Class<T> entityClass,
			Object param, int firstResult, int maxResult) {
		return findByPage(hql, entityClass, new Object[] { param },
				firstResult, maxResult);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByPage(String hql, Class<T> entityClass,
			Object[] params, int firstResult, int maxResult) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query.list();
	}

	@Override
	public <T> void delete(Class<T> entityClass, String id) {
		getSession().delete(get(entityClass, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> entityClass, String id) {
		return (T) getSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getbywhere(Class<T> entityClass, String hql) {
		Query query = getSession().createQuery(hql);
		return (T) query.iterate().next();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(String hql, Class<T> entityClass,
			Object[] params, String paramname) {
		Query query = getSession().createQuery(hql);
		query.setParameterList(paramname, params);
		return query.list();
	}

	@Override
	public <T> void delete(String hql) {
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByProc(String procName, Class<T> entityClass) {
		Query query = getSession().getNamedQuery(procName);
		return (List<T>)query.list();
	}

}
