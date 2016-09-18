package com.huangyunchi.web.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huangyunchi.entity.Member;
import com.huangyunchi.service.MemberService;

/**
 * 会员的个人资料修改功能------>处理的是异步请求
 */
@WebServlet("/member/profile")
public class MemberProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//step1: 获取请求中的参数数据
		String nick_name = request.getParameter("nick_name");
		String real_name = request.getParameter("real_name");
		String genderStr = request.getParameter("gender");
		String email = request.getParameter("email");
		
		
		//step2: 处理业务逻辑
		Member mbr = (Member)request.getSession().getAttribute("curr_mbr");
		mbr.setNick_name(nick_name);
		mbr.setReal_name(real_name);
		mbr.setEmail(email);
		mbr.setGender(Boolean.parseBoolean(genderStr));
		
		MemberService service = new MemberService();
		service.update(mbr);
		
		//step3: 响应文本
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("ok");
		out.flush();
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
