package com.huangyunchi.web.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huangyunchi.entity.Address;
import com.huangyunchi.entity.Member;
import com.huangyunchi.entity.Orders;
import com.huangyunchi.service.AddressService;
import com.huangyunchi.service.OrdersService;

/**
 * 保存订单数据到数据库，并跳转到支付界面
 */
@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		String address_id = request.getParameter("address_id");
		String remark = request.getParameter("remark");
		
		
		HttpSession session = request.getSession();
		Orders order = (Orders)session.getAttribute("curr_order");
		
		Member mbr = (Member)session.getAttribute("curr_mbr");
		order.setBuyer_id(mbr.getId());
		order.setRemark(remark);
		
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		order.setNumber(df.format(new Date())); //生成一个有意义订单编号
		order.setStatus(2);
		
		AddressService addressService = new AddressService();
		Address address = addressService.findOne(Integer.valueOf(address_id));
		order.setContact(address.getContact());
		order.setMobile(address.getMobile());
		order.setStreet(address.getStreet());
		order.setZipcode(address.getZipcode());
		
		//保存订单
		OrdersService ordersService = new OrdersService();
		ordersService.save(order);
		
		//用户购物流程结束------>清除当前session中的购物车对象
		session.removeAttribute("cart");
		
		response.sendRedirect(request.getContextPath() + "/member/orders");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
