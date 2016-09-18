package com.huangyunchi.web.controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huangyunchi.entity.Address;
import com.huangyunchi.entity.Member;
import com.huangyunchi.service.AddressService;

/**
 * 会员的地址列表
 */
@WebServlet("/member/address/list")
public class AddressListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		Member mbr = (Member)request.getSession().getAttribute("curr_mbr");
	
		
		//
		AddressService service = new AddressService();
		List<Address> list = service.findByMember(mbr.getId());
		
		//
		request.setAttribute("list", list);
		request.getRequestDispatcher("/member/address.jsp").forward(request, response);
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
