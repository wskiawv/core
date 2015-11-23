package com.htrj.core.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.htrj.core.dao.BaseDaoI;
import com.htrj.core.util.JqGridPage;
import com.htrj.core.util.Page;

@Service
//@Repository
public class BaseService implements BaseServiceI {
	
	@Autowired
	private BaseDaoI baseDao;

	@Override
	public Serializable save(Object o) {
		// TODO Auto-generated method stub
		return baseDao.save(o);
	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub
		baseDao.delete(o);
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		baseDao.update(o);
	}

	@Override
	public void saveOrUpdate(Object o) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(o);
	}

	@Override
	public Object getById(Class c, Serializable id) {
		// TODO Auto-generated method stub
		return baseDao.getById(c, id);
	}

	@Override
	public Object getByHql(String hql) {
		// TODO Auto-generated method stub
		return baseDao.getByHql(hql);
	}

	@Override
	public Object getByHql(String hql, Map params) {
		// TODO Auto-generated method stub
		return baseDao.getByHql(hql, params);
	}

	@Override
	public List find(String hql) {
		// TODO Auto-generated method stub
		return baseDao.find(hql);
	}

	@Override
	public List find(String hql, Map params) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, params);
	}

	@Override
	public List find(String hql, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, pageNo, pageSize);
	}

	@Override
	public List find(String hql, Map params, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, params, pageNo, pageSize);
	}

	@Override
	public Long count(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}

	@Override
	public Page find(Class clazz, Map params) {
		// TODO Auto-generated method stub
		return baseDao.find(clazz, params);
	}

	@Override
	public Long count(Class clazz, Map params) {
		// TODO Auto-generated method stub
		return baseDao.count(clazz, params);
	}

	@Override
	public Long count(String hql, Map params) {
		// TODO Auto-generated method stub
		return baseDao.count(hql, params);
	}

	@Override
	public int executeHql(String hql) {
		// TODO Auto-generated method stub
		return baseDao.executeHql(hql);
	}

	@Override
	public int executeHql(String hql, Map params) {
		// TODO Auto-generated method stub
		return baseDao.executeHql(hql, params);
	}

	@Override
	public List findBySql(String sql) {
		// TODO Auto-generated method stub
		return baseDao.findBySql(sql);
	}

	@Override
	public List findBySql(String sql, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.findBySql(sql, pageNo, pageSize);
	}

	@Override
	public List findBySql(String sql, Map params) {
		// TODO Auto-generated method stub
		return baseDao.findBySql(sql, params);
	}

	@Override
	public List findBySql(String sql, Map params, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.findBySql(sql, params, pageNo, pageSize);
	}

	@Override
	public int executeSql(String sql) {
		// TODO Auto-generated method stub
		return baseDao.executeSql(sql);
	}

	@Override
	public int executeSql(String sql, Map params) {
		// TODO Auto-generated method stub
		return baseDao.executeSql(sql, params);
	}

	@Override
	public BigInteger countBySql(String sql) {
		// TODO Auto-generated method stub
		return baseDao.countBySql(sql);
	}

	@Override
	public BigInteger countBySql(String sql, Map params) {
		// TODO Auto-generated method stub
		return baseDao.countBySql(sql, params);
	}

	@Override
	public JqGridPage findJqGridPage(Class clazz, Map params) {
		// TODO Auto-generated method stub
		return baseDao.findJqGridPage(clazz, params);
	}
	
	

	
}
