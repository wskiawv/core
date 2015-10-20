package com.htrj.core.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.htrj.core.dao.BaseDaoI;
import com.htrj.core.util.Page;

@Service
//@Repository
public class BaseService<T> implements BaseServiceI<T> {
	
	@Autowired
	private BaseDaoI<T> baseDao;
	
	public Serializable save(T o) {
		
		return baseDao.save(o);
	}

	public void delete(T o) {
		baseDao.delete(o);
		
	}

	public void update(T o) {
		baseDao.update(o);
		
	}

	public void saveOrUpdate(T o) {
		baseDao.saveOrUpdate(o);
		
	}

	public T getById(Class<T> c, Serializable id) {
		
		return baseDao.getById(c, id);
	}

	public T getByHql(String hql) {
		
		return baseDao.getByHql(hql);
	}

	public T getByHql(String hql, Map<String, Object> params) {
	
		return baseDao.getByHql(hql, params);
	}

	public List<T> find(String hql) {
		
		return baseDao.find(hql);
	}

	public List<T> find(String hql, Map<String, Object> params) {
		
		return baseDao.find(hql, params);
	}

	public List<T> find(String hql, int pageNo, int pageSize) {
		
		return baseDao.find(hql, pageNo, pageSize);
	}

	public List<T> find(String hql, Map<String, Object> params, int pageNo, int pageSize) {
		
		return baseDao.find(hql, params, pageNo, pageSize);
	}

	public Long count(String hql) {
		
		return baseDao.count(hql);
	}

	public Page<T> find(Class clazz, Map<String, Object> params) {
		
		return baseDao.find(clazz, params);
	}

	public Long count(Class clazz, Map<String, Object> params) {
		
		return baseDao.count(clazz, params);
	}

	public Long count(String hql, Map<String, Object> params) {
		
		return baseDao.count(hql, params);
	}

	public int executeHql(String hql) {
		
		return baseDao.executeHql(hql);
	}

	public int executeHql(String hql, Map<String, Object> params) {
		
		return baseDao.executeHql(hql, params);
	}

	public List<Map> findBySql(String sql) {
		
		return baseDao.findBySql(sql);
	}

	public List<Map> findBySql(String sql, int pageNo, int pageSize) {
	
		return baseDao.findBySql(sql, pageNo, pageSize);
	}

	public List<Map> findBySql(String sql, Map<String, Object> params) {
		
		return baseDao.findBySql(sql, params);
	}

	public List<Map> findBySql(String sql, Map<String, Object> params, int pageNo, int pageSize) {
		
		return baseDao.findBySql(sql, params, pageNo, pageSize);
	}

	public int executeSql(String sql) {
		
		return baseDao.executeHql(sql);
	}

	public int executeSql(String sql, Map<String, Object> params) {
		
		return baseDao.executeHql(sql, params);
	}

	public BigInteger countBySql(String sql) {
	
		return baseDao.countBySql(sql);
	}

	public BigInteger countBySql(String sql, Map<String, Object> params) {
		
		return baseDao.countBySql(sql, params);
	}

	
}
