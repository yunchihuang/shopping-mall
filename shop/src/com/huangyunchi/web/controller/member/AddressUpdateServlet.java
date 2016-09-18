package com.huangyunchi.web.controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huangyunchi.entity.Address;
import com.huangyunchi.service.AddressService;

/**
 * 会员的编辑地址功能
 */
@WebServlet("/member/address/update")
public class AddressUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取请求的参数数据
		String id = request.getParameter("id");
		String contact = request.getParameter("contact");
		String mobile = request.getParameter("mobile");
		String street = request.getParameter("street");
		String zipcode = request.getParameter("zipcode");
		String default_value = request.getParameter("default_value");
		
		AddressService service = new AddressService();
		Address address = service.findOne(Integer.parseInt(id));
		address.setContact(contact);
		address.setMobile(mobile);
		address.setStreet(street);
		address.setZipcode(zipcode);
		boolean dv = Boolean.valueOf(default_value);
		address.setDefault_value(dv);
		
		service.update(address);
		//处理默认地址的问题
		if(dv){
			service.updateDefault(address.getMbr_id(), address.getId());
		}
		
		//
		response.sendRedirect(request.getContextPath() + "/member/address/list");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
