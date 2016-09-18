package com.huangyunchi.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huangyunchi.entity.Address;
import com.huangyunchi.entity.Member;
import com.huangyunchi.entity.Orders;
import com.huangyunchi.service.AddressService;
import com.huangyunchi.service.MemberService;

/**
 * 处理会员登录的Servlet
 */
@WebServlet("/member_login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//step1： 获取客户端提交的数据
		String mobile = request.getParameter("mobile");
		String pwd = request.getParameter("pwd");
		
		//step2: 业务逻辑处理
		
		
		MemberService service = new MemberService();
		Member mbr = service.findByMobile(mobile);
		
		//step3: 执行跳转
		if(mbr != null){
			if(mbr.getPwd().equals(pwd)){
				//登录成功
				//在会话中记录当前登录的会员信息
				request.getSession().setAttribute("curr_mbr", mbr);
				
				//如果登录后的会员，有提交订单，跳转到/orders.jsp; 没有就跳转到会员的首页
				Orders order = (Orders)request.getSession().getAttribute("curr_order");
				if(order != null){
					AddressService service2 = new AddressService();
					List<Address> addressList = service2.findByMember(mbr.getId());
					request.setAttribute("addressList", addressList);

					request.getRequestDispatcher("/orders.jsp").forward(request, response);;
				}else{
					response.sendRedirect(request.getContextPath() + "/member/orders");
				}
				
			}else{//密码有误
				request.setAttribute("msg", "密码不正确！");
				request.getRequestDispatcher("/member_login.jsp").forward(request, response);
			}
		}else{ //用户名不存在
			request.setAttribute("msg", "用户名不存在！");
			request.getRequestDispatcher("/member_login.jsp").forward(request, response);
		}
	}

}
