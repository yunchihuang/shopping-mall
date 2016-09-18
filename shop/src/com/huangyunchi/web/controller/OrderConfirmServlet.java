package com.huangyunchi.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huangyunchi.entity.Address;
import com.huangyunchi.entity.Item;
import com.huangyunchi.entity.Member;
import com.huangyunchi.entity.Orders;
import com.huangyunchi.entity.Product;
import com.huangyunchi.service.AddressService;
import com.huangyunchi.service.ProductService;

/**
 * 订单“去结算”的Servlet
 */
@WebServlet("/order_confirm")
public class OrderConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		String[] idStrs = request.getParameterValues("id");
		String[] amountStrs = request.getParameterValues("amount");
		
		//
		HttpSession session = request.getSession();
		ProductService service = new ProductService();
		
		int allAmount = 0;
		BigDecimal allPrice = new BigDecimal(0.00);
		BigDecimal allPaymentPrice = new BigDecimal(0.00);
		List<Item> items = new ArrayList<Item>();
		
		int length = idStrs == null ? 0 : idStrs.length;
		for(int i = 0; i < length; i++){
			Integer id = Integer.valueOf(idStrs[i]);
			int amount = Integer.parseInt(amountStrs[i]);
			allAmount += amount; //计算购物的总数量
			
			//效率有点低
			Product product = service.findOne(id);
			
			//把购物车中的每个商品都转换成一个订单项对象
			Item item = new Item();
			item.setAmount(amount);
			item.setProduct_id(id);
			item.setProduct(product);
			
			BigDecimal am = new BigDecimal(amount);
			BigDecimal total_price = product.getPrice().multiply(am);
			item.setTotal_price(total_price);
			
			BigDecimal payment_price = product.getSale_price().multiply(am);
			item.setPayment_price(payment_price);
			
			items.add(item);//
			
			allPrice = allPrice.add(total_price); //计算总金额
			allPaymentPrice = allPaymentPrice.add(payment_price); //计算要支付的总金额
			
			//改购物车中的相应商品的数量
			@SuppressWarnings("unchecked")
			Map<Product, Integer> cart = (Map<Product, Integer>)session.getAttribute("cart");
			cart.put(product, Integer.valueOf(amount));
		}
		
		//订单实体类
		Orders order = new Orders();
		order.setItems(items); //订单的每个订单项
		order.setTotal_amount(allAmount); //订单的总物品数量
		order.setTotal_price(allPrice);//订单的总金额
		order.setPayment_price(allPaymentPrice); //订单的实际支付金额
		order.setCreate_time(new Date());
		
		//把当前订单数据存储到session中
		session.setAttribute("curr_order", order);
		
		
		
		//判断用户有没有登录
		Member mbr = (Member)session.getAttribute("curr_mbr");
		if(mbr == null){ //没有登录，就跳转到登录页面
			
			request.setAttribute("msg", "提交订单前,请先登录!");
			request.getRequestDispatcher("/member_login.jsp").forward(request, response);
			
		}else{//登录后的，跳转结算页面
			AddressService service2 = new AddressService();
			List<Address> addressList = service2.findByMember(mbr.getId());
			request.setAttribute("addressList", addressList);
			
			request.getRequestDispatcher("/orders.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
