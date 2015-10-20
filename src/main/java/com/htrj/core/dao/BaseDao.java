package com.htrj.core.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.htrj.core.util.Page;

@Repository
public class BaseDao<T> implements BaseDaoI<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 获得当前事物的session
	 * 
	 * @return org.hibernate.Session
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//接口实现方法
	public Serializable save(T o) {
		if(o !=null){
			return getSession().save(o);
		}
		return null;
	}

	public void delete(T o) {
		if(o != null){
			getSession().delete(o);
		}		
	}

	public void update(T o) {
		if(o != null){
			getSession().update(o);
		}		
	}

	public void saveOrUpdate(T o) {
		if(o != null){
			getSession().saveOrUpdate(o);
		}
	}

	public T getById(Class<T> c, Serializable id) {
		return (T)getSession().get(c, id);
	}

	public T getByHql(String hql) {
		Query q=getSession().createQuery(hql);
		List<T> l=q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	public T getByHql(String hql, Map<String, Object> params) {
		Query q=getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	public List<T> find(String hql) {
		Query q=getSession().createQuery(hql);		
		return q.list();
	}

	public List<T> find(String hql, Map<String, Object> params) {
		Query q=getSession().createQuery(hql);	
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();		
	}

	public List<T> find(String hql, int pageNo, int pageSize) {
		Query q=getSession().createQuery(hql);		
		return q.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
	}

	public List<T> find(String hql, Map<String, Object> params, int pageNo, int pageSize) {
		Query q=getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
	}

	public Long count(String hql) {
		Query q=getSession().createQuery(hql);
		return (Long)q.uniqueResult();
	}

	public Long count(String hql, Map<String, Object> params) {
		Query q=getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long)q.uniqueResult();
	}

	public int executeHql(String hql) {
		Query q = getSession().createQuery(hql);
		return q.executeUpdate();
	}

	public int executeHql(String hql, Map<String, Object> params) {
		Query q = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	public List<Map> findBySql(String sql) {
		SQLQuery q = getSession().createSQLQuery(sql);
		return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	public List<Map> findBySql(String sql, int pageNo, int pageSize) {
		SQLQuery q = getSession().createSQLQuery(sql);
		return q.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	public List<Map> findBySql(String sql, Map<String, Object> params) {
		SQLQuery q = getSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	public List<Map> findBySql(String sql, Map<String, Object> params, int pageNo, int pageSize) {
		SQLQuery q = getSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	public int executeSql(String sql) {
		SQLQuery q = getSession().createSQLQuery(sql);
		return q.executeUpdate();
	}

	public int executeSql(String sql, Map<String, Object> params) {
		SQLQuery q = getSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	public BigInteger countBySql(String sql) {
		SQLQuery q = getSession().createSQLQuery(sql);
		return (BigInteger) q.uniqueResult();
	}

	public BigInteger countBySql(String sql, Map<String, Object> params) {
		SQLQuery q = getSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (BigInteger) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public Page<T> find(Class clazz, Map<String, Object> params) {
		Page page=new Page(params);
		Criteria  c= properyFiter(clazz,params);
		Long totalCount=count(clazz,params);		
		page.setTotalCount(totalCount);
		c.setFirstResult(page.getStart());
		c.setMaxResults(page.getLimit());
		c.addOrder(Order.desc("id"));
		page.setResult(c.list());	
		return page;
	}
	public Criteria properyFiter(Class clazz,Map<String,Object> params){
		Criteria  c= getSession().createCriteria(clazz);
		for(String name:params.keySet()){
			//判断是否包含有过虑属性
			if (!StringUtils.startsWithIgnoreCase(name, "filter_")) {
				continue;
			}
			String value = (String)params.get(name);
			if (StringUtils.isBlank(value)) {
				continue;
			}
			//截取下划线右边的属性名称
			String propertyName = StringUtils.substringAfterLast(name, "_");
			String matchType=StringUtils.substringBetween(name, "_");
			if(matchType.equals("LIKE")){
				c.add(Restrictions.like(propertyName, "%"+value+"%"));
			}else if(matchType.equals("EQ")){
				c.add(Restrictions.eq(propertyName, value));
			}else if(matchType.equals("GT")){
				c.add(Restrictions.gt(propertyName, value));
			}else if(matchType.equals("LT")){
				c.add(Restrictions.lt(propertyName, value));
			}else if(matchType.equals("GE")){
				c.add(Restrictions.ge(propertyName, value));
			}else if(matchType.equals("LE")){
				c.add(Restrictions.le(propertyName, value));
			}else if(matchType.equals("IN")){
				//c.add(Restrictions.in(propertyName, value));
				if (value.getClass().isArray())
					c.add(Restrictions.in(propertyName, (Object[]) value.split(",")));
				/*else if ((value instanceof Collection)) {
					c.add(Restrictions.in(propertyName, (Collection) value));
				}*/
			}
		}
		return c;
	}
	public Long count(Class clazz, Map<String, Object> params) {
		Criteria  c= properyFiter(clazz,params);
		return (long) c.list().size()<=0?0:(long) c.list().size();
	}

}
