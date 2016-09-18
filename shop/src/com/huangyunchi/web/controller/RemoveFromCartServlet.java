package com.huangyunchi.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huangyunchi.entity.Product;

/**
 * 从购物车中移除指定的商品
 */
@WebServlet("/removeFromCart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("id");
		Integer id = Integer.valueOf(idStr);
		
		//ProductService service = new ProductService();
		//Product prod = service.findOne(id);
		
		//购物车是存放在session中的
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<Product, Integer> cart = (Map<Product, Integer>)session.getAttribute("cart");
		if(cart != null){
			//cart.remove(prod);
			
			Product temp = null;
			for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
				if(entry.getKey().getId().equals(id)){
					temp = entry.getKey();
					break;
				}
			}
			
			if(temp != null){
				cart.remove(temp);
			}
		}
		
		request.getRequestDispatcher("/view_cart.jsp").forward(request, response);
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
