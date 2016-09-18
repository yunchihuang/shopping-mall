package com.huangyunchi.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.huangyunchi.common.DbHelper;
import com.huangyunchi.entity.News;
import com.huangyunchi.entity.common.Page;


/**
 * 公告的业务逻辑类
 * @author qiujy
 */
public class NewsService {
	private QueryRunner qr = new QueryRunner();
	private ScalarHandler<Long> scalarHandler = new ScalarHandler<Long>();
	private BeanHandler<News> beanHandler = new BeanHandler<News>(News.class);
	private BeanListHandler<News> beanListHandler = new BeanListHandler<News>(News.class);

	public News save(News news) throws RuntimeException{
		String sql = "INSERT INTO news(title, thumbnail, content, top,"
				+ " hits, pub_time) VALUES(?,?,?,?,?,?)";
		
		Object[] params = {news.getTitle(), news.getThumbnail(), 
				news.getContent(), news.isTop(), news.getHits(), news.getPub_time()};
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn(); //获取数据库连接
			conn.setAutoCommit(false); //开启事务

			//执行数据库操作的插入操作，返回生成的主键值
			Long id = qr.insert(conn, sql, scalarHandler, params);
			news.setId(id.intValue());
			
			DbUtils.commitAndCloseQuietly(conn); //提交事务并关闭连接
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn); //回滚事务并关闭连接
			 
			 throw new RuntimeException(e);
		}
		
		return news;
	}
	
	public void update(News news) throws RuntimeException{
		String sql = "UPDATE news SET title=?, thumbnail=?, content=?, top=?,"
				+ " hits=?, pub_time=? WHERE id=?";
		
		Object[] params = {news.getTitle(), news.getThumbnail(), 
				news.getContent(), news.isTop(), news.getHits(),
				news.getPub_time(), news.getId()};
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn(); //获取数据库连接
			conn.setAutoCommit(false); //开启事务
			
			//执行数据库的更新操作
			qr.update(conn, sql, params);
			
			DbUtils.commitAndCloseQuietly(conn); //提交事务并关闭连接
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn); //回滚事务并关闭连接
			 
			 throw new RuntimeException(e);
		}
	}
	
	public News findOne(Integer id)throws RuntimeException{
		News news = null;
		String sql = "SELECT * FROM news WHERE id=?";
	
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			news = qr.query(conn, sql, beanHandler, id);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
	
		return news;
	}
	
	public List<News> findAll()throws RuntimeException{
		List<News> list = new ArrayList<News>();
		String sql = "SELECT * FROM news ORDER BY id DESC";

		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			list = qr.query(conn, sql, beanListHandler);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return list;
	}

	public void delete(Integer id) throws RuntimeException{
		String sql = "DELETE FROM news WHERE id=?";
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			conn.setAutoCommit(false); 
			
			qr.update(conn, sql, id);
			
			DbUtils.commitAndCloseQuietly(conn); 
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn);
			 throw new RuntimeException(e);
		}
	}
	
	public long count() throws RuntimeException{
		long count = 0;
		
		String sql = "SELECT count(id) FROM news";

		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			Long temp = qr.query(conn, sql, scalarHandler);
			if(temp != null && temp.longValue() > 0){
				count = temp.longValue();
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return count;
	}
	
	public Page<News> findAll(int number, int size)throws RuntimeException{
		Page<News> page = new Page<>(number, size);
		
		String sql = "SELECT COUNT(id) FROM news";
		String sql2 = "SELECT * FROM news ORDER BY id DESC LIMIT ?,?";

		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			Long temp = qr.query(conn, sql, scalarHandler);
			if(temp != null && temp.longValue() > 0){
				page.setTotalElements(temp.longValue());
				
				Object[] params = {Integer.valueOf((number - 1) * size),
						Integer.valueOf(size)};
				
				List<News> list = qr.query(conn, sql2, beanListHandler, params);
				page.setItems(list);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return page;
	}
	
	/**
	 * 获取已经发布的轮播新闻列表
	 * @param number
	 * @param size
	 * @return
	 * @throws RuntimeException
	 */
	public Page<News> findTopByPublic(int number, int size)throws RuntimeException{
		Page<News> page = new Page<>(number, size);
		
		String sql = "SELECT COUNT(id) FROM news WHERE top=true AND pub_time<=?";
		String sql2 = "SELECT * FROM news WHERE top=true AND pub_time<=? ORDER BY id DESC LIMIT ?,?";
		Object param1 = new Date();
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			Long temp = qr
					.query(conn, sql, scalarHandler, param1);
			if(temp != null && temp.longValue() > 0){
				page.setTotalElements(temp.longValue());
				
				Object[] params = {param1, Integer.valueOf((number - 1) * size),
						Integer.valueOf(size)};
				
				List<News> list = qr.query(conn, sql2, beanListHandler, params);
				page.setItems(list);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return page;
	}
	
	/**
	 * 获取已经发布的新闻列表
	 * @param number
	 * @param size
	 * @return
	 * @throws RuntimeException
	 */
	public Page<News> findByPublic(int number, int size)throws RuntimeException{
		Page<News> page = new Page<>(number, size);
		
		String sql = "SELECT COUNT(id) FROM news WHERE pub_time<=?";
		String sql2 = "SELECT * FROM news WHERE pub_time<=? ORDER BY id DESC LIMIT ?,?";
		Object param1 = new Date();
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			Long temp = qr.query(conn, sql, scalarHandler, param1);
			if(temp != null && temp.longValue() > 0){
				page.setTotalElements(temp.longValue());
				
				Object[] params = {param1, Integer.valueOf((number - 1) * size),
						Integer.valueOf(size)};
				
				List<News> list = qr.query(conn, sql2, beanListHandler, params);
				page.setItems(list);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return page;
	}
}