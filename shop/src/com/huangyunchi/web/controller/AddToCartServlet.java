package com.huangyunchi.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huangyunchi.entity.Product;
import com.huangyunchi.service.ProductService;

/**
 * 用于处理AJAX提交“添加商品到购物车”的请求的Servlet
 */
@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService service = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		String idStr = request.getParameter("id");
		String numStr = request.getParameter("num");
		Integer id = Integer.valueOf(idStr);
		Integer num = Integer.valueOf(numStr);
		
		//
		Product prod = service.findOne(id);
		
		//购物车是存放在session中的
		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (Map<Product, Integer>)session.getAttribute("cart");
		if(cart == null){
			cart = new ConcurrentHashMap<Product, Integer>();
			session.setAttribute("cart", cart);
		}
		
		//购物车,用商品对象作为key，用数量给value存放到Map
		//先把购物车中获取是否已经存在当前要购买的商品
		Integer oldNum = cart.get(prod);
		if(oldNum != null){
			cart.put(prod, Integer.valueOf(oldNum.intValue() + num.intValue()));
		}else{
			cart.put(prod, num);
		}
		
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
