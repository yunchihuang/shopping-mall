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
import com.huangyunchi.entity.Product;
import com.huangyunchi.entity.common.Page;


/**
 * 商品的业务逻辑类
 * @author qiujy
 */
public class ProductService {
	private QueryRunner qr = new QueryRunner();
	private ScalarHandler<Long> scalarHandler = new ScalarHandler<Long>();
	private BeanHandler<Product> beanHandler = new BeanHandler<Product>(Product.class);
	private BeanListHandler<Product> beanListHandler = new BeanListHandler<Product>(Product.class);

	public Product save(Product product) throws RuntimeException{
		String sql = "INSERT INTO product(name, cate_id, thumbnail, inventory, "
				+ "sales_volume,price,sale_price,detail_description,"
				+ "selling_description,create_time,sale_time) "
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		
		Object[] params = {product.getName(), product.getCate_id(), 
				product.getThumbnail(), product.getInventory(), 
				product.getSales_volume(), product.getPrice(), 
				product.getSale_price(), product.getDetail_description(), 
				product.getSelling_description(), product.getCreate_time(),
				product.getSale_time()};
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn(); 
			conn.setAutoCommit(false);

			Long id = qr.insert(conn, sql, scalarHandler, params);
			product.setId(id.intValue());
			
			DbUtils.commitAndCloseQuietly(conn);
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn);
			 
			 throw new RuntimeException(e);
		}
		
		return product;
	}
	
	public void update(Product product) throws RuntimeException{
		String sql = "UPDATE product SET name=?, cate_id=?, thumbnail=?, inventory=?, "
				+ "sales_volume=?,price=?,sale_price=?,detail_description=?,"
				+ "selling_description=?,create_time=?,sale_time=? WHERE id=?";
		
		Object[] params = {product.getName(), product.getCate_id(), 
				product.getThumbnail(), product.getInventory(), 
				product.getSales_volume(), product.getPrice(), 
				product.getSale_price(), product.getDetail_description(), 
				product.getSelling_description(), product.getCreate_time(),
				product.getSale_time(), product.getId()};
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn(); 
			conn.setAutoCommit(false);
			
			qr.update(conn, sql, params);
			
			DbUtils.commitAndCloseQuietly(conn);
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn);
			 
			 throw new RuntimeException(e);
		}
	}
	
	public Product findOne(Integer id) throws RuntimeException{
		Product product = null;
		String sql = "SELECT * FROM product WHERE id=?";
	
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			product = qr.query(conn, sql, beanHandler, id);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
	
		return product;
	}
	
	public List<Product> find(Integer... ids) throws RuntimeException{
		List<Product> list = new ArrayList<>();
		
		if(ids == null || ids.length == 0){
			return list;
		}
		
		StringBuilder sql = new StringBuilder("SELECT * FROM product WHERE id IN(");
		for (int i = 0; i< ids.length; i++) {
			if(i > 0){
				sql.append(",");
			}
			sql.append(ids[i]);
		}
		sql.append(")");
	
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			System.out.println(sql.toString());
			list = qr.query(conn, sql.toString(), beanListHandler);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return list;
	}
	
	public Page<Product> findAll(int number, int size) throws RuntimeException{
		Page<Product> page = new Page<>(number, size);
		
		String sql = "SELECT COUNT(id) FROM product ";
		String sql2 = "SELECT * FROM product ORDER BY id DESC LIMIT ?,?";

		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			Long temp = qr.query(conn, sql, scalarHandler);
			if(temp != null && temp.longValue() > 0){
				page.setTotalElements(temp.longValue());
				
				Object[] params = {Integer.valueOf((number - 1) * size),
						Integer.valueOf(size)};
				
				List<Product> list = qr.query(conn, sql2, beanListHandler, params);
				page.setItems(list);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return page;
	}
	

	public Page<Product> findAll(int cate_id, String title, int number, int size) throws RuntimeException{
		Page<Product> page = new Page<>(number, size);
		
		StringBuilder sql = new StringBuilder("SELECT COUNT(p.id) FROM product p WHERE 1=1 ");
		StringBuilder sql2 = new StringBuilder("SELECT p.* FROM product p WHERE 1=1 ");
		List<Object> param = new ArrayList<>();
		if(cate_id > 0){
			sql.append(" AND p.cate_id=?");
			sql2.append(" AND p.cate_id=?");
			
			param.add(Integer.valueOf(cate_id));
		}
		if(title != null && !"".equals(title.trim())){
			sql.append(" AND p.name LIKE ?");
			sql2.append(" AND p.name LIKE ?");
			
			param.add("%" + title.trim() +"%");
		}
		

		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			Long temp = qr.query(conn, sql.toString(), 
					scalarHandler, param.toArray());
			if(temp != null && temp.longValue() > 0){
				page.setTotalElements(temp.longValue());
				
				sql2.append(" ORDER BY p.id DESC LIMIT ?,?");
				param.add(Integer.valueOf((number - 1) * size));
				param.add(Integer.valueOf(size));
				
				List<Product> list = qr.query(conn, sql2.toString(), beanListHandler, param.toArray());
				page.setItems(list);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return page;
	}

	public void delete(Integer id) throws RuntimeException{
		String sql = "DELETE FROM  product WHERE id=?";
		
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
		
		String sql = "SELECT count(id) FROM product";

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
	
	/**
	 * 获取指定的二级类目下的产品分页列表
	 * @param cate_id 类目的编号
	 * @param number 当前页号
	 * @param size 每页要显示的记录数
	 * @return 分页对象
	 * @throws RuntimeException
	 */
	public Page<Product> findBySubCategory(Integer cate_id, int number, int size) throws RuntimeException{
		Page<Product> page = new Page<>(number, size);
		
		String sql = "SELECT COUNT(id) FROM product WHERE cate_id=?";
		String sql2 = "SELECT * FROM product WHERE cate_id=? ORDER BY sale_time DESC LIMIT ?,?";

		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			Long temp = qr.query(conn, sql, scalarHandler, cate_id);
			if(temp != null && temp.longValue() > 0){
				page.setTotalElements(temp.longValue());
				
				Object[] params = {cate_id,
						Integer.valueOf((number - 1) * size),
						Integer.valueOf(size)};
				
				List<Product> list = qr.query(conn, sql2, beanListHandler, params);
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
	 * 获取热门产品分页列表
	 * @param number 当前页号
	 * @param size 每页要显示的记录数
	 * @return 分页对象
	 * @throws RuntimeException
	 */
	public Page<Product> findHot(int number, int size) throws RuntimeException{
		Page<Product> page = new Page<>(number, size);
		
		String sql = "SELECT COUNT(id) FROM product";
		String sql2 = "SELECT * FROM product ORDER BY sales_volume DESC,id DESC LIMIT ?,?";

		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			Long temp = qr.query(conn, sql, scalarHandler);
			if(temp != null && temp.longValue() > 0){
				page.setTotalElements(temp.longValue());
				
				Object[] params = {Integer.valueOf((number - 1) * size),
						Integer.valueOf(size)};
				
				List<Product> list = qr.query(conn, sql2, beanListHandler, params);
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
	 * 获取指定一级类目下的热门产品分页列表
	 * @param cate_id 一级类目
	 * @param number
	 * @param size
	 * @return
	 * @throws RuntimeException
	 */
	public Page<Product> findHotTopCategory(Integer cate_id, int number, int size) throws RuntimeException{
		Page<Product> page = new Page<>(number, size);
		
		String sql = "SELECT COUNT(id) FROM product "
				+ "WHERE cate_id IN(SELECT id FROM category WHERE p_id=?)";
		
		String sql2 = "SELECT * FROM product "
				+ "WHERE cate_id IN(SELECT id FROM category WHERE p_id=?) "
				+ "ORDER BY sales_volume DESC,id DESC LIMIT ?,?";

		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			Long temp = qr.query(conn, sql, scalarHandler, cate_id);
			if(temp != null && temp.longValue() > 0){
				page.setTotalElements(temp.longValue());
				
				Object[] params = {cate_id,
						Integer.valueOf((number - 1) * size),
						Integer.valueOf(size)};
				
				List<Product> list = qr.query(conn, sql2, beanListHandler, params);
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
	 * 获取指定二级类目下的热门产品分页列表
	 * @param cate_id 二级类目的编号
	 * @param number 当前页号
	 * @param size 每页要显示的记录数
	 * @return 分页对象
	 * @throws RuntimeException
	 */
	public Page<Product> findHotSubCategory(Integer cate_id, int number, int size) throws RuntimeException{
		Page<Product> page = new Page<>(number, size);
		
		String sql = "SELECT COUNT(id) FROM product WHERE cate_id=?";
		String sql2 = "SELECT * FROM product WHERE cate_id=? ORDER BY sales_volume DESC,id DESC LIMIT ?,?";

		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			Long temp = qr.query(conn, sql, scalarHandler, cate_id);
			if(temp != null && temp.longValue() > 0){
				page.setTotalElements(temp.longValue());
				
				Object[] params = {cate_id,
						Integer.valueOf((number - 1) * size),
						Integer.valueOf(size)};
				
				List<Product> list = qr.query(conn, sql2, beanListHandler, params);
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
	 * 根据产品标题模糊查询，返回分页列表
	 * @param name 标题
	 * @param number 当前页号
	 * @param size 每页要显示的记录数
	 * @return 分页对象
	 * @throws RuntimeException
	 */
	public Page<Product> findByLikeName(String name, int number, int size) throws RuntimeException{
		Page<Product> page = new Page<>(number, size);
		
		if(name != null){
			name = name.replaceAll("%", "\\%");
		}
		System.out.println(name);
		
		String sql = "SELECT COUNT(id) FROM product WHERE name LIKE ?";
		String sql2 = "SELECT * FROM product WHERE name LIKE ? ORDER BY id DESC LIMIT ?,?";

		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			Long temp = qr.query(conn, sql, scalarHandler, "%" + name + "%");
			if(temp != null && temp.longValue() > 0){
				page.setTotalElements(temp.longValue());
				
				Object[] params = {"%" + name + "%",
						Integer.valueOf((number - 1) * size),
						Integer.valueOf(size)};
				
				List<Product> list = qr.query(conn, sql2, beanListHandler, params);
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
	 * 获取指定一级类目下的所有产品分页列表
	 * @param cate_id
	 * @param number
	 * @param size
	 * @return
	 * @throws RuntimeException
	 */
	public Page<Product> findByTopCategory(Integer cate_id, int number, int size) throws RuntimeException{
		Page<Product> page = new Page<>(number, size);
		
		String sql = "SELECT COUNT(id) FROM product "
				+ "WHERE cate_id IN(SELECT id FROM category WHERE p_id=?)";
		
		String sql2 = "SELECT * FROM product "
				+ "WHERE cate_id IN(SELECT id FROM category WHERE p_id=?) "
				+ "ORDER BY id DESC LIMIT ?,?";

		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			Long temp = qr.query(conn, sql, scalarHandler, cate_id);
			if(temp != null && temp.longValue() > 0){
				page.setTotalElements(temp.longValue());
				
				Object[] params = {cate_id,
						Integer.valueOf((number - 1) * size),
						Integer.valueOf(size)};
				
				List<Product> list = qr.query(conn, sql2, beanListHandler, params);
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