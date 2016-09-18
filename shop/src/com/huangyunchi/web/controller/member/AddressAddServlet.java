package com.huangyunchi.web.controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huangyunchi.entity.Address;
import com.huangyunchi.entity.Member;
import com.huangyunchi.service.AddressService;

/**
 * 会员的新增地址
 */
@WebServlet("/member/address/add")
public class AddressAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取请求的参数数据
		String contact = request.getParameter("contact");
		String mobile = request.getParameter("mobile");
		String street = request.getParameter("street");
		String zipcode = request.getParameter("zipcode");
		String default_value = request.getParameter("default_value");
		
		//把散装数据封装成对象
		Address address = new Address();
		address.setContact(contact);
		address.setMobile(mobile);
		address.setStreet(street);
		address.setZipcode(zipcode);
		boolean dv = Boolean.valueOf(default_value);
		address.setDefault_value(dv);
		
		//设置这个要新增的地址所属的会员ID
		Member mbr = (Member)request.getSession().getAttribute("curr_mbr");
		address.setMbr_id(mbr.getId());
		
		//业务逻辑处理
		AddressService service = new AddressService();
		service.save(address); //保存新增的地址,address的ID就有值了
		
		//处理默认地址的问题
		if(dv){
			service.updateDefault(mbr.getId(), address.getId());
		}
		
		//跳转(重定向)
		//理解：Servlet中的两种跳转方式：重定向、请求分派
		response.sendRedirect(request.getContextPath() + "/member/address/list");
		
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
