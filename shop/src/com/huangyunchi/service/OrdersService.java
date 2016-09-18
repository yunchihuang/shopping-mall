package com.huangyunchi.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.huangyunchi.common.DbHelper;
import com.huangyunchi.entity.Item;
import com.huangyunchi.entity.Orders;
import com.huangyunchi.entity.Product;
import com.huangyunchi.entity.common.Page;

/**
 * 订单处理相关的业务逻辑类
 * @author qiujy
 */
public class OrdersService {
	
	private QueryRunner qr = new QueryRunner();
	private ScalarHandler<Long> scalarHandler = new ScalarHandler<Long>();
	//private BeanHandler<Orders> beanHandler = new BeanHandler<Orders>(Orders.class);
	//private BeanListHandler<Orders> beanListHandler = new BeanListHandler<Orders>(Orders.class);
	
	public void updateStatus(Integer id, int status)throws RuntimeException{
		String sql = "UPDATE orders SET status=? WHERE id=?";
		Connection conn = null;
		try{
			conn = DbHelper.getConn(); 
			conn.setAutoCommit(false); 
			
			qr.update(conn, sql, status, id);
			
			DbUtils.commitAndCloseQuietly(conn); 
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn); 
			 
			 throw new RuntimeException(e);
		}
	}
	
	/**
	 * 添加订单，并级联添加订单项
	 * @param orders
	 * @return
	 * @throws RuntimeException
	 */
	public Orders save(Orders orders) throws RuntimeException{
		String sql = "INSERT INTO orders(number, buyer_id, total_amount, "
				+ "total_price,payment_price,remark,contact,mobile,street,zipcode,"
				+ "create_time,payment_time,delivery_time,end_time,status)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		String sql2 = "INSERT INTO item(order_id,product_id,amount,"
				+ "total_price,payment_price) VALUES(?,?,?,?,?)";
		
		Object[] params = {orders.getNumber(), orders.getBuyer_id(), 
				orders.getTotal_amount(), orders.getTotal_price(), 
				orders.getPayment_price(), orders.getRemark(),
				orders.getContact(), orders.getMobile(), orders.getStreet(), 
				orders.getZipcode(), orders.getCreate_time(), orders.getPayment_time(),
				orders.getDelivery_time(), orders.getEnd_time(), orders.getStatus()};
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			conn.setAutoCommit(false); 

			Long id = qr.insert(conn, sql, scalarHandler, params);
			orders.setId(id.intValue());
			
			//批量添加订单项
			List<Item> items = orders.getItems();
			int size = items == null ? 0 : items.size();
			Object[][] params2 = new Object[items.size()][];
			for (int i = 0 ; i < size; i++) {
				Item item = items.get(i);
				params2[i] = new Object[] {orders.getId(), item.getProduct_id(),
						item.getAmount(), item.getTotal_price(), item.getPayment_price()};
			}
			qr.batch(conn, sql2, params2);
			
			DbUtils.commitAndCloseQuietly(conn);
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn);
			 
			 throw new RuntimeException(e);
		}
		
		return orders;
	}
	
	/**
	 * 获取进行中的订单分页列表
	 * @param number
	 * @param size
	 * @return
	 * @throws RuntimeException
	 */
	public Page<Orders> findAccumulating(int number, int size)throws RuntimeException{
		Page<Orders> page = new Page<>(number, size);
		
		String sql = "SELECT COUNT(o.id) FROM orders o"
				+ " INNER JOIN item i ON o.id= i.order_id"
				+ " INNER JOIN product p ON i.product_id=p.id"
				+ " WHERE o.status!=6 AND o.status!=-1";
		
		String sql2 = "SELECT o.id oid, o.number onumber, o.buyer_id as obuyer_id, "
				+ "o.total_amount ototal_amount, o.total_price ototal_price, o.payment_price opayment_price,"
				+ "o.remark oremark,o.contact ocontact,o.mobile omobile,o.street ostreet, "
				+ "o.zipcode ozipcode, o.create_time ocreate_time, "
				+ "o.payment_time opayment_time,o.delivery_time,o.end_time oend_time,o.status ostatus, "
				+ "i.id iid, i.order_id iorder_id, i.product_id iproduct_id, "
				+ "i.amount iamount, i.total_price itotal_price, i.payment_price ipayment_price, "
				+ "p.id pid, p.name pname,p.cate_id pcate_id, p.thumbnail pthumbnail, "
				+ "p.inventory pinventory, p.sales_volume psales_volume,p.price pprice, "
				+ "p.sale_price psale_price,p.detail_description pdetail_description, "
				+ "p.selling_description pselling_description,p.create_time pcreate_time, "
				+ "p.sale_time psale_time"
				+ " FROM orders o"
				+ " INNER JOIN item i ON o.id= i.order_id"
				+ " INNER JOIN product p ON i.product_id=p.id"
				+ " WHERE o.status!=6 AND o.status!=-1"
				+ " ORDER BY o.id DESC"
				+ " LIMIT ?,?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DbHelper.getConn();
			
			System.out.println(sql);
			Long temp = qr.query(conn, sql, scalarHandler);
			if(temp != null && temp.longValue() > 0){
				page.setTotalElements(temp.longValue());
				
				List<Orders> list = new ArrayList<Orders>();
				
				System.out.println(sql2);
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, (number - 1) * size);
				pstmt.setInt(2, size);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					int i = 1;
					Orders order = new Orders();
					order.setId(rs.getInt(i++));
					order.setNumber(rs.getString(i++));
					order.setBuyer_id(rs.getInt(i++));
					order.setTotal_amount(rs.getInt(i++));
					
					String total_price = rs.getString(i++);
					if(total_price != null){
						order.setTotal_price(new BigDecimal(total_price));
					}
					String payment_price = rs.getString(i++);
					if(payment_price != null){
						order.setPayment_price(new BigDecimal(payment_price));
					}
					
					order.setRemark(rs.getString(i++));
					order.setContact(rs.getString(i++));
					order.setMobile(rs.getString(i++));
					order.setStreet(rs.getString(i++));
					order.setZipcode(rs.getString(i++));
					Timestamp ts = rs.getTimestamp(i++);
					if(ts != null){
						order.setCreate_time(new Date(ts.getTime()));
					}
					Timestamp ts2 = rs.getTimestamp(i++);
					if(ts2 != null){
						order.setPayment_time(new Date(ts2.getTime()));
					}
					Timestamp ts3 = rs.getTimestamp(i++);
					if(ts3 != null){
						order.setDelivery_time(new Date(ts3.getTime()));
					}
					Timestamp ts4 = rs.getTimestamp(i++);
					if(ts4 != null){
						order.setEnd_time(new Date(ts4.getTime()));
					}
					order.setStatus(rs.getInt(i++));
					
					Item item = new Item();
					item.setId(rs.getInt(i++));
					item.setOrder_id(rs.getInt(i++));
					item.setProduct_id(rs.getInt(i++));
					item.setAmount(rs.getInt(i++));
					
					String total_price2 = rs.getString(i++);
					if(total_price2 != null){
						item.setTotal_price(new BigDecimal(total_price2));
					}
					String payment_price2 = rs.getString(i++);
					if(payment_price2 != null){
						item.setPayment_price(new BigDecimal(payment_price2));
					}
					
					Product prod = new Product();
					prod.setId(rs.getInt(i++));
					prod.setName(rs.getString(i++));
					prod.setCate_id(rs.getInt(i++));
					prod.setThumbnail(rs.getString(i++));
					prod.setInventory(rs.getInt(i++));
					prod.setSales_volume(rs.getInt(i++));
					
					String price = rs.getString(i++);
					if(price != null){
						prod.setPrice(new BigDecimal(price));
					}
					
					String sale_price = rs.getString(i++);
					if(sale_price != null){
						prod.setSale_price(new BigDecimal(sale_price));
					}
					
					prod.setDetail_description(rs.getString(i++));
					prod.setSelling_description(rs.getString(i++));
					
					Timestamp ts5 = rs.getTimestamp(i++);
					if(ts5 != null){
						prod.setCreate_time(new Date(ts5.getTime()));
					}
					Timestamp ts6 = rs.getTimestamp(i++);
					if(ts6 != null){
						prod.setSale_time(new Date(ts6.getTime()));
					}
					
					//处理订单项-->产品的关系
					item.setProduct(prod);
					//处理订单重复的问题
					if(list.size() == 0){
						order.getItems().add(item);
						list.add(order);
					}else{
						boolean flag = false;
						for(int j = 0; j < list.size(); j++){
							Orders o = list.get(j);
							if(order.getId().equals(o.getId())){//已经存在了
								o.getItems().add(item);
								flag = true;
								break;
							}
						}
						if(!flag){//还不存在
							order.getItems().add(item);
							list.add(order);
						}
					}
				}
				
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
	 * 获取指定状态的订单分页列表
	 * @param number
	 * @param size
	 * @return
	 * @throws RuntimeException
	 */
	public Page<Orders> findByStatus(int status, int number, int size)throws RuntimeException{
		Page<Orders> page = new Page<>(number, size);
		
		String sql = "SELECT COUNT(o.id) FROM orders o"
				+ " INNER JOIN item i ON o.id= i.order_id"
				+ " INNER JOIN product p ON i.product_id=p.id"
				+ " WHERE o.status=?";
				
		String sql2 = "SELECT o.id oid, o.number onumber, o.buyer_id as obuyer_id, "
				+ "o.total_amount ototal_amount, o.total_price ototal_price, o.payment_price opayment_price, "
				+ "o.remark oremark,o.contact ocontact,o.mobile omobile,o.street ostreet, "
				+ "o.zipcode ozipcode, o.create_time ocreate_time, "
				+ "o.payment_time opayment_time,o.delivery_time,o.end_time oend_time,o.status ostatus, "
				+ "i.id iid, i.order_id iorder_id, i.product_id iproduct_id, "
				+ "i.amount iamount, i.total_price itotal_price, i.payment_price ipayment_price, "
				+ "p.id pid, p.name pname,p.cate_id pcate_id, p.thumbnail pthumbnail, "
				+ "p.inventory pinventory, p.sales_volume psales_volume,p.price pprice, "
				+ "p.sale_price psale_price,p.detail_description pdetail_description, "
				+ "p.selling_description pselling_description,p.create_time pcreate_time, "
				+ "p.sale_time psale_time"
				+ " FROM orders o"
				+ " INNER JOIN item i ON o.id= i.order_id"
				+ " INNER JOIN product p ON i.product_id=p.id"
				+ " WHERE o.status=?"
				+ " ORDER BY o.id DESC"
				+ " LIMIT ?,?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DbHelper.getConn();
			
			System.out.println(sql);
			Long temp = qr.query(conn, sql, scalarHandler, status);
			if(temp != null && temp.longValue() > 0){
				page.setTotalElements(temp.longValue());
				
				List<Orders> list = new ArrayList<Orders>();
				
				System.out.println(sql2);
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, status);
				pstmt.setInt(2, (number - 1) * size);
				pstmt.setInt(3, size);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					int i = 1;
					Orders order = new Orders();
					order.setId(rs.getInt(i++));
					order.setNumber(rs.getString(i++));
					order.setBuyer_id(rs.getInt(i++));
					order.setTotal_amount(rs.getInt(i++));
					
					String total_price = rs.getString(i++);
					if(total_price != null){
						order.setTotal_price(new BigDecimal(total_price));
					}
					String payment_price = rs.getString(i++);
					if(payment_price != null){
						order.setPayment_price(new BigDecimal(payment_price));
					}
					
					order.setRemark(rs.getString(i++));
					order.setContact(rs.getString(i++));
					order.setMobile(rs.getString(i++));
					order.setStreet(rs.getString(i++));
					order.setZipcode(rs.getString(i++));
					Timestamp ts = rs.getTimestamp(i++);
					if(ts != null){
						order.setCreate_time(new Date(ts.getTime()));
					}
					Timestamp ts2 = rs.getTimestamp(i++);
					if(ts2 != null){
						order.setPayment_time(new Date(ts2.getTime()));
					}
					Timestamp ts3 = rs.getTimestamp(i++);
					if(ts3 != null){
						order.setDelivery_time(new Date(ts3.getTime()));
					}
					Timestamp ts4 = rs.getTimestamp(i++);
					if(ts4 != null){
						order.setEnd_time(new Date(ts4.getTime()));
					}
					order.setStatus(rs.getInt(i++));
					
					Item item = new Item();
					item.setId(rs.getInt(i++));
					item.setOrder_id(rs.getInt(i++));
					item.setProduct_id(rs.getInt(i++));
					item.setAmount(rs.getInt(i++));
					
					String total_price2 = rs.getString(i++);
					if(total_price2 != null){
						item.setTotal_price(new BigDecimal(total_price2));
					}
					String payment_price2 = rs.getString(i++);
					if(payment_price2 != null){
						item.setPayment_price(new BigDecimal(payment_price2));
					}
					
					Product prod = new Product();
					prod.setId(rs.getInt(i++));
					prod.setName(rs.getString(i++));
					prod.setCate_id(rs.getInt(i++));
					prod.setThumbnail(rs.getString(i++));
					prod.setInventory(rs.getInt(i++));
					prod.setSales_volume(rs.getInt(i++));
					
					String price = rs.getString(i++);
					if(price != null){
						prod.setPrice(new BigDecimal(price));
					}
					
					String sale_price = rs.getString(i++);
					if(sale_price != null){
						prod.setSale_price(new BigDecimal(sale_price));
					}
					
					prod.setDetail_description(rs.getString(i++));
					prod.setSelling_description(rs.getString(i++));
					
					Timestamp ts5 = rs.getTimestamp(i++);
					if(ts5 != null){
						prod.setCreate_time(new Date(ts5.getTime()));
					}
					Timestamp ts6 = rs.getTimestamp(i++);
					if(ts6 != null){
						prod.setSale_time(new Date(ts6.getTime()));
					}
					
					//处理订单项-->产品的关系
					item.setProduct(prod);
					//处理订单重复的问题
					if(list.size() == 0){
						order.getItems().add(item);
						list.add(order);
					}else{
						boolean flag = false;
						for(int j = 0; j < list.size(); j++){
							Orders o = list.get(j);
							if(order.getId().equals(o.getId())){//已经存在了
								o.getItems().add(item);
								flag = true;
								break;
							}
						}
						if(!flag){//还不存在
							order.getItems().add(item);
							list.add(order);
						}
					}
				}
				
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
	 * 获取指定会员的订单分页列表
	 * @param number
	 * @param size
	 * @return
	 * @throws RuntimeException
	 */
	public Page<Orders> findByBuyer(Integer buyer_id, int number, int size)throws RuntimeException{
		Page<Orders> page = new Page<>(number, size);
		
		String sql = "SELECT COUNT(distinct o.id) FROM orders o"
				+ " INNER JOIN item i ON o.id= i.order_id"
				+ " INNER JOIN product p ON i.product_id=p.id"
				+ " WHERE o.buyer_id=?";
				
		String sql2 = "SELECT distinct o.id oid, o.number onumber, o.buyer_id as obuyer_id, "
				+ "o.total_amount ototal_amount, o.total_price ototal_price, o.payment_price opayment_price, "
				+ "o.remark oremark,o.contact ocontact,o.mobile omobile,o.street ostreet, "
				+ "o.zipcode ozipcode, o.create_time ocreate_time, "
				+ "o.payment_time opayment_time,o.delivery_time,o.end_time oend_time,o.status ostatus, "
				+ "i.id iid, i.order_id iorder_id, i.product_id iproduct_id, "
				+ "i.amount iamount, i.total_price itotal_price, i.payment_price ipayment_price, "
				+ "p.id pid, p.name pname,p.cate_id pcate_id, p.thumbnail pthumbnail, "
				+ "p.inventory pinventory, p.sales_volume psales_volume,p.price pprice, "
				+ "p.sale_price psale_price,p.detail_description pdetail_description, "
				+ "p.selling_description pselling_description,p.create_time pcreate_time, "
				+ "p.sale_time psale_time"
				+ " FROM orders o"
				+ " INNER JOIN item i ON o.id = i.order_id"
				+ " INNER JOIN product p ON i.product_id=p.id"
				+ " WHERE o.buyer_id=?"
				+ " ORDER BY o.id DESC"
				+ " LIMIT ?,?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DbHelper.getConn();
			
			System.out.println(sql);
			Long temp = qr.query(conn, sql, scalarHandler, buyer_id);
			if(temp != null && temp.longValue() > 0){
				page.setTotalElements(temp.longValue());
				
				List<Orders> list = new ArrayList<Orders>();
				
				System.out.println(sql2);
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, buyer_id);
				pstmt.setInt(2, (number - 1) * size);
				pstmt.setInt(3, size);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					int i = 1;
					Orders order = new Orders();
					order.setId(rs.getInt(i++));
					order.setNumber(rs.getString(i++));
					order.setBuyer_id(rs.getInt(i++));
					order.setTotal_amount(rs.getInt(i++));
					
					String total_price = rs.getString(i++);
					if(total_price != null){
						order.setTotal_price(new BigDecimal(total_price));
					}
					String payment_price = rs.getString(i++);
					if(payment_price != null){
						order.setPayment_price(new BigDecimal(payment_price));
					}
					
					order.setRemark(rs.getString(i++));
					order.setContact(rs.getString(i++));
					order.setMobile(rs.getString(i++));
					order.setStreet(rs.getString(i++));
					order.setZipcode(rs.getString(i++));
					Timestamp ts = rs.getTimestamp(i++);
					if(ts != null){
						order.setCreate_time(new Date(ts.getTime()));
					}
					Timestamp ts2 = rs.getTimestamp(i++);
					if(ts2 != null){
						order.setPayment_time(new Date(ts2.getTime()));
					}
					Timestamp ts3 = rs.getTimestamp(i++);
					if(ts3 != null){
						order.setDelivery_time(new Date(ts3.getTime()));
					}
					Timestamp ts4 = rs.getTimestamp(i++);
					if(ts4 != null){
						order.setEnd_time(new Date(ts4.getTime()));
					}
					order.setStatus(rs.getInt(i++));
					
					Item item = new Item();
					item.setId(rs.getInt(i++));
					item.setOrder_id(rs.getInt(i++));
					item.setProduct_id(rs.getInt(i++));
					item.setAmount(rs.getInt(i++));
					
					String total_price2 = rs.getString(i++);
					if(total_price2 != null){
						item.setTotal_price(new BigDecimal(total_price2));
					}
					String payment_price2 = rs.getString(i++);
					if(payment_price2 != null){
						item.setPayment_price(new BigDecimal(payment_price2));
					}
					
					Product prod = new Product();
					prod.setId(rs.getInt(i++));
					prod.setName(rs.getString(i++));
					prod.setCate_id(rs.getInt(i++));
					prod.setThumbnail(rs.getString(i++));
					prod.setInventory(rs.getInt(i++));
					prod.setSales_volume(rs.getInt(i++));
					
					String price = rs.getString(i++);
					if(price != null){
						prod.setPrice(new BigDecimal(price));
					}
					
					String sale_price = rs.getString(i++);
					if(sale_price != null){
						prod.setSale_price(new BigDecimal(sale_price));
					}
					
					prod.setDetail_description(rs.getString(i++));
					prod.setSelling_description(rs.getString(i++));
					
					Timestamp ts5 = rs.getTimestamp(i++);
					if(ts5 != null){
						prod.setCreate_time(new Date(ts5.getTime()));
					}
					Timestamp ts6 = rs.getTimestamp(i++);
					if(ts6 != null){
						prod.setSale_time(new Date(ts6.getTime()));
					}
					
					//处理订单项-->产品的关系
					item.setProduct(prod);
					
					//处理订单重复的问题
					if(list.size() == 0){
						order.getItems().add(item);
						list.add(order);
					}else{
						boolean flag = false;
						for(int j = 0; j < list.size(); j++){
							Orders o = list.get(j);
							if(order.getId().equals(o.getId())){//已经存在了
								o.getItems().add(item);
								flag = true;
								break;
							}
						}
						if(!flag){//还不存在
							order.getItems().add(item);
							list.add(order);
						}
					}
				}
				
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
	 * 获取指定编号的订单
	 * @param number
	 * @param size
	 * @return
	 * @throws RuntimeException
	 */
	public Orders findOne(Integer id)throws RuntimeException{
		String sql = "SELECT o.id oid, o.number onumber, o.buyer_id as obuyer_id, "
				+ "o.total_amount ototal_amount, o.total_price ototal_price, o.payment_price opayment_price,"
				+ "o.remark oremark,o.contact ocontact,o.mobile omobile,o.street ostreet, "
				+ "o.zipcode ozipcode, o.create_time ocreate_time, "
				+ "o.payment_time opayment_time,o.delivery_time,o.end_time oend_time,o.status ostatus, "
				+ "i.id iid, i.order_id iorder_id, i.product_id iproduct_id, "
				+ "i.amount iamount, i.total_price itotal_price, i.payment_price ipayment_price, "
				+ "p.id pid, p.name pname,p.cate_id pcate_id, p.thumbnail pthumbnail, "
				+ "p.inventory pinventory, p.sales_volume psales_volume,p.price pprice, "
				+ "p.sale_price psale_price,p.detail_description pdetail_description, "
				+ "p.selling_description pselling_description,p.create_time pcreate_time, "
				+ "p.sale_time psale_time"
				+ " FROM orders o "
				+ " INNER JOIN item i ON o.id= i.order_id"
				+ " INNER JOIN product p ON i.product_id=p.id"
				+ " WHERE o.id=?";

		Orders real = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DbHelper.getConn();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			int count = 0;
			
			while(rs.next()){
				count++;
				
				int i = 1;
				Orders order = new Orders();
				order.setId(rs.getInt(i++));
				order.setNumber(rs.getString(i++));
				order.setBuyer_id(rs.getInt(i++));
				order.setTotal_amount(rs.getInt(i++));
				
				String total_price = rs.getString(i++);
				if(total_price != null){
					order.setTotal_price(new BigDecimal(total_price));
				}
				String payment_price = rs.getString(i++);
				if(payment_price != null){
					order.setPayment_price(new BigDecimal(payment_price));
				}
				
				order.setRemark(rs.getString(i++));
				order.setContact(rs.getString(i++));
				order.setMobile(rs.getString(i++));
				order.setStreet(rs.getString(i++));
				order.setZipcode(rs.getString(i++));
				Timestamp ts = rs.getTimestamp(i++);
				if(ts != null){
					order.setCreate_time(new Date(ts.getTime()));
				}
				Timestamp ts2 = rs.getTimestamp(i++);
				if(ts2 != null){
					order.setPayment_time(new Date(ts2.getTime()));
				}
				Timestamp ts3 = rs.getTimestamp(i++);
				if(ts3 != null){
					order.setDelivery_time(new Date(ts3.getTime()));
				}
				Timestamp ts4 = rs.getTimestamp(i++);
				if(ts4 != null){
					order.setEnd_time(new Date(ts4.getTime()));
				}
				order.setStatus(rs.getInt(i++));
				
				Item item = new Item();
				item.setId(rs.getInt(i++));
				item.setOrder_id(rs.getInt(i++));
				item.setProduct_id(rs.getInt(i++));
				item.setAmount(rs.getInt(i++));
				
				String total_price2 = rs.getString(i++);
				if(total_price2 != null){
					item.setTotal_price(new BigDecimal(total_price2));
				}
				String payment_price2 = rs.getString(i++);
				if(payment_price2 != null){
					item.setPayment_price(new BigDecimal(payment_price2));
				}
				
				Product prod = new Product();
				prod.setId(rs.getInt(i++));
				prod.setName(rs.getString(i++));
				prod.setCate_id(rs.getInt(i++));
				prod.setThumbnail(rs.getString(i++));
				prod.setInventory(rs.getInt(i++));
				prod.setSales_volume(rs.getInt(i++));
				
				String price = rs.getString(i++);
				if(price != null){
					prod.setPrice(new BigDecimal(price));
				}
				
				String sale_price = rs.getString(i++);
				if(sale_price != null){
					prod.setSale_price(new BigDecimal(sale_price));
				}
				
				prod.setDetail_description(rs.getString(i++));
				prod.setSelling_description(rs.getString(i++));
				
				Timestamp ts5 = rs.getTimestamp(i++);
				if(ts5 != null){
					prod.setCreate_time(new Date(ts5.getTime()));
				}
				Timestamp ts6 = rs.getTimestamp(i++);
				if(ts6 != null){
					prod.setSale_time(new Date(ts6.getTime()));
				}
				
				//处理订单项-->产品的关系
				item.setProduct(prod);
				//处理订单-->订单项的关系
				if(count == 1){
					order.getItems().add(item);
					real = order;
				}else{
					real.getItems().add(item);
				}
			}

		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return real;
	}
}
