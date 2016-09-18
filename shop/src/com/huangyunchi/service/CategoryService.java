package com.huangyunchi.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.huangyunchi.common.DbHelper;
import com.huangyunchi.entity.Category;


/**
 * 
 * @author qiujy
 */
public class CategoryService {
	private QueryRunner qr = new QueryRunner();
	private ScalarHandler<Long> scalarHandler = new ScalarHandler<Long>();
	private BeanHandler<Category> beanHandler = new BeanHandler<Category>(Category.class);
	private BeanListHandler<Category> beanListHandler = new BeanListHandler<Category>(Category.class);

	public Category save(Category cate) throws RuntimeException{
		String sql = "INSERT INTO category(name, alias, order_weight, p_id) VALUES(?,?,?,?)";
		
		Object[] params = {cate.getName(), cate.getAlias(), 
				cate.getOrder_weight(), cate.getP_id()};
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn(); //获取数据库连接
			conn.setAutoCommit(false); //开启事务
			System.out.println(sql);
			//执行数据库操作的插入操作，返回生成的主键值
			Long id = qr.insert(conn, sql, scalarHandler, params);
			cate.setId(id.intValue());
			
			DbUtils.commitAndCloseQuietly(conn); //提交事务并关闭连接
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn); //回滚事务并关闭连接
			 
			 throw new RuntimeException(e);
		}
		
		return cate;
	}
	
	public void update(Category cate) throws RuntimeException{
		String sql = "UPDATE category SET name=?, alias=?, order_weight=?, p_id=? WHERE id=?";
		
		Object[] params = {cate.getName(), cate.getAlias(), 
				cate.getOrder_weight(), cate.getP_id(), cate.getId()};
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn(); //获取数据库连接
			conn.setAutoCommit(false); //开启事务
			
			System.out.println(sql);
			//执行数据库的更新操作
			qr.update(conn, sql, params);
			
			DbUtils.commitAndCloseQuietly(conn); //提交事务并关闭连接
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn); //回滚事务并关闭连接
			 
			 throw new RuntimeException(e);
		}
	}
	
	public Category findOne(Integer id)throws RuntimeException{
		Category cate = null;
		String sql = "SELECT * FROM category WHERE id=?";
	
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			System.out.println(sql);
			//执行数据库的查询操作
			cate = qr.query(conn, sql, beanHandler, id);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
	
		return cate;
	}
	
	public List<Category> findAll()throws RuntimeException{
		List<Category> list = new ArrayList<Category>();
		String sql = "SELECT * FROM category ORDER BY order_weight DESC,id ASC";

		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			System.out.println(sql);
			//执行数据库的查询操作
			list = qr.query(conn, sql, beanListHandler);
			
			list = convert(list);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return list;
	}
	

	public void delete(Integer id) throws RuntimeException{
		String sql = "SELECT count(id) FROM category where p_id=?";
		String sql2 = "DELETE FROM  category WHERE id=?";
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			conn.setAutoCommit(false); 
			
			System.out.println(sql);
			
			Long count = qr.query(sql, scalarHandler, id);
			if(count !=null && count.longValue() > 0){
				 throw new RuntimeException("删除失败，有子类目");
			} else {
				 qr.update(conn, sql2, id);
			}
			
			DbUtils.commitAndCloseQuietly(conn); 
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn);
			 throw new RuntimeException(e);
		}
	}
	
	//组装父子关系
	private List<Category> convert(List<Category> categories){
		List<Category> parents = new ArrayList<Category>();
		List<Category> childs = new LinkedList<Category>();
		for (Category category : categories) {
			if(category.getP_id() == null){
				parents.add(category);
			}else{
				childs.add(category);
			}
		}
		for (Category parent : parents) {
			for (Category child : childs) {
				if(parent.getId().equals(child.getP_id())){
					parent.getChilds().add(child);
				}
			}
		}
		
		return parents;
	}
	
}