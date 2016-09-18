package com.huangyunchi.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huangyunchi.entity.Member;
import com.huangyunchi.service.MemberService;

/**
 * 处理注册会员的Servlet
 */
@WebServlet("/member_register")
public class MemberRegisterServlet extends HttpServlet {
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
		
		Member mbr = new Member();
		mbr.setMobile(mobile);
		mbr.setPwd(pwd);
		mbr.setNick_name("匿名");
		mbr.setRegister_time(new Date());
		
		//step2: 业务逻辑处理
		MemberService service = new MemberService();
		//根据手机号查询会员对象
		Member temp = service.findByMobile(mobile);
		if(temp != null){ //手机号已经存在 
			request.setAttribute("msg", "注册失败，手机号已经被注册了！");
			
			request.getRequestDispatcher("/member_register.jsp").forward(request, response);
		}else{
			service.save(mbr);
			
			//跳转到登录页面
			response.sendRedirect(request.getContextPath() + "/member_login.jsp");
		}
	}

}
