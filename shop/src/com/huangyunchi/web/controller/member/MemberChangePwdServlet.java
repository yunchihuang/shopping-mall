package com.huangyunchi.web.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huangyunchi.entity.Member;
import com.huangyunchi.service.MemberService;

/**
 * 会员的修改密码功能
 */
@WebServlet("/member/changepwd")
public class MemberChangePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//step1: 获取请求中的参数数据
		String pwd = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		
		
		//step2: 处理业务逻辑
		//step3: 跳转
		Member mbr = (Member)request.getSession().getAttribute("curr_mbr");
		if(mbr.getPwd().equals(pwd)){
			mbr.setPwd(pwd2);
			
			MemberService service = new MemberService();
			service.update(mbr);
			
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/member_login.jsp");
		}else{
			request.setAttribute("msg", "密码修改失败，原密码有误！");
			request.getRequestDispatcher("/member/updatepwd.jsp").forward(request, response);
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
