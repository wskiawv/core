package com.htrj.core.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.htrj.core.util.JqGridPage;
import com.htrj.core.util.Page;

public interface BaseDaoI {
	
	/**
	 * 保存一个对象
	 * 
	 * @param o
	 *            对象
	 * @return 对象的ID
	 */
	public Serializable save(Object o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void delete(Object o);
	
	/**
	 * 删除所有数据
	 * @param hql
	 */
	public int deleteAll(String hql);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void update(Object o);

	/**
	 * 保存或更新一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void saveOrUpdate(Object o);

	/**
	 * 通过主键获得对象
	 * 
	 * @param c
	 *            类名.class
	 * @param id
	 *            主键
	 * @return 对象
	 */
	public Object getById(Class c, Serializable id);

	/**
	 * 通过HQL语句获取一个对象
	 * 
	 * @param hql
	 *            HQL语句
	 * @return 对象
	 */
	public Object getByHql(String hql);

	/**
	 * 通过HQL语句获取一个对象
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @return 对象
	 */
	public Object getByHql(String hql, Map<String, Object> params);

	/**
	 * 获得对象列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @return List
	 */
	public List find(String hql);

	/**
	 * 获得对象列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @return List
	 */
	public List find(String hql, Map<String, Object> params);

	/**
	 * 获得分页后的对象列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @param pageNo
	 *            要显示第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return List
	 */
	public List find(String hql, int pageNo, int pageSize);

	/**
	 * 获得分页后的对象列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @param pageNo
	 *            要显示第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return List
	 */
	public List find(String hql, Map<String, Object> params, int pageNo, int pageSize);

	/**
	 * 统计数目
	 * 
	 * @param hql
	 *            HQL语句(select count(*) from T)
	 * @return long
	 */
	public Long count(String hql);
	/**
	 * 根据实体和查询参数返回实体List extjs分页
	 * @param clazz
	 * @param params
	 * @return
	 */
	public Page find(Class clazz,Map<String, Object> params);
	
	
	
	/**
	 * 根据实体类和查询参数统计
	 * @param clazz
	 * @param params
	 * @return
	 */
	public Long count(Class clazz,Map<String, Object> params);
	/**
	 * 统计数目
	 * 
	 * @param hql
	 *            HQL语句(select count(*) from T where xx = :xx)
	 * @param params
	 *            参数
	 * @return long
	 */
	public Long count(String hql, Map<String, Object> params);

	/**
	 * 执行一条HQL语句
	 * 
	 * @param hql
	 *            HQL语句
	 * @return 响应结果数目
	 */
	public int executeHql(String hql);

	/**
	 * 执行一条HQL语句
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @return 响应结果数目
	 */
	public int executeHql(String hql, Map<String, Object> params);

	/**
	 * 获得结果集
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 结果集
	 */
	public List<Map> findBySql(String sql);

	/**
	 * 获得结果集
	 * 
	 * @param sql
	 *            SQL语句
	 * @param pageNo
	 *            要显示第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 结果集
	 */
	public List<Map> findBySql(String sql, int pageNo, int pageSize);

	/**
	 * 获得结果集
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @return 结果集
	 */
	public List<Map> findBySql(String sql, Map<String, Object> params);

	/**
	 * 获得结果集
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @param pageNo
	 *            要显示第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 结果集
	 */
	public List<Map> findBySql(String sql, Map<String, Object> params, int pageNo, int pageSize);

	/**
	 * 执行SQL语句
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 响应行数
	 */
	public int executeSql(String sql);

	/**
	 * 执行SQL语句
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @return 响应行数
	 */
	public int executeSql(String sql, Map<String, Object> params);

	/**
	 * 统计
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 数目
	 */
	public BigInteger countBySql(String sql);

	/**
	 * 统计
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @return 数目
	 */
	public BigInteger countBySql(String sql, Map<String, Object> params);
	/**
	 * jqGrid组件分页
	 * @param clazz
	 * @param params
	 * @return
	 */
	public JqGridPage findJqGridPage(Class clazz,Map<String, Object> params);
}
