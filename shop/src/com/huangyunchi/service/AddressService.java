package com.huangyunchi.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.huangyunchi.common.DbHelper;
import com.huangyunchi.entity.Address;

/**
 * 地址的业务逻辑类
 * @author qiujy
 */
public class AddressService {
	private QueryRunner qr = new QueryRunner();
	private ScalarHandler<Long> scalarHandler = new ScalarHandler<Long>();
	private BeanHandler<Address> beanHandler = new BeanHandler<Address>(Address.class);
	private BeanListHandler<Address> beanListHandler = new BeanListHandler<Address>(Address.class);
	
	public Address save(Address address) throws RuntimeException{
		String sql = "INSERT INTO address(contact, mobile, street, zipcode, "
				+ "default_value, mbr_id) VALUES(?,?,?,?,?,?)";
		
		Object[] params = {address.getContact(), address.getMobile(),
				address.getStreet(), address.getZipcode(), 
				address.getDefault_value(), address.getMbr_id()};
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn(); //获取数据库连接
			conn.setAutoCommit(false); //开启事务

			//执行数据库操作的插入操作，返回生成的主键值
			Long id = qr.insert(conn, sql, scalarHandler, params);
			address.setId(id.intValue());
			
			DbUtils.commitAndCloseQuietly(conn); //提交事务并关闭连接
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn); //回滚事务并关闭连接
			 
			 throw new RuntimeException(e);
		}
		
		return address;
	}
	
	/**
	 * 更新地址信息
	 * @param address
	 * @throws RuntimeException
	 */
	public void update(Address address) throws RuntimeException{
		String sql = "UPDATE address SET contact=?, mobile=?, street=?, "
				+ "zipcode=?,default_value=?,mbr_id=? WHERE id=?";
		
		Object[] params = {address.getContact(), address.getMobile(),
				address.getStreet(), address.getZipcode(), 
				address.getDefault_value(), address.getMbr_id(), address.getId()};
		
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
	
	public void updateDefault(Integer mbr_id, Integer address_id) throws RuntimeException{
		String sql = "UPDATE address SET default_value=false WHERE mbr_id=?";
		String sql2 = "UPDATE address SET default_value=true WHERE mbr_id=? AND id=?";
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn(); 
			conn.setAutoCommit(false);
			
			qr.update(conn, sql, mbr_id);
			qr.update(conn, sql2, mbr_id, address_id);
			
			DbUtils.commitAndCloseQuietly(conn); 
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn);
			 
			 throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据编号删除地址
	 * @param id 地址的编号
	 * @throws RuntimeException
	 */
	public void delete(Integer id) throws RuntimeException{
		String sql = "DELETE FROM address WHERE id=?";
		
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
	
	/**
	 * 获取指定会员的所有地址列表
	 * @param mbr_id 会员的编号
	 * @return 该会员的所有地址列表
	 * @throws RuntimeException
	 */
	public List<Address> findByMember(Integer mbr_id) throws RuntimeException{
		List<Address> list = new ArrayList<>();
		
		String sql = "SELECT * FROM address WHERE mbr_id=? ORDER BY default_value DESC,id DESC";
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			list = qr
					.query(conn, sql, beanListHandler, mbr_id);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return list;
	}
	
	/**
	 * 获取指定编号的地址
	 * @param id 地址的编号
	 * @return 地址
	 * @throws RuntimeException
	 */
	public Address findOne(Integer id) throws RuntimeException{
		Address address = null;
		String sql = "SELECT * FROM address WHERE id=?";
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			address = qr.query(conn, sql, beanHandler, id);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return address;
	}
}