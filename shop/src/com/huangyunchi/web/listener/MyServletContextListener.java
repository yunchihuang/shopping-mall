package com.huangyunchi.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.huangyunchi.entity.Category;
import com.huangyunchi.service.CategoryService;

/**
 * 自定义的应用上下文监听器<br/>
 * 使用Servlet3.0特性-->注解方式的配置
 * @author Administrator
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	
	//本方法会在Web程序部署成功后，立即执行。一般是用来对本程序中的数据做一些初始化工作
	@Override
	public void contextInitialized(ServletContextEvent event) {
		//放置web应用上下文根路径
		ServletContext context = event.getServletContext();
		String ctx = context.getContextPath(); //JSP页面的链接的基准路径
		
		//往ServletContext作用域里存放一个名为ctx的变量。
		context.setAttribute("ctx", ctx);
		
		//JSP页面中要显示的后台动态上传的图片的基准路径
		context.setAttribute("pic_base", ctx + "/img/");
		
		//加载所有的类目列表数据
		CategoryService service = new CategoryService();
		List<Category> list = service.findAll();
		context.setAttribute("cates", list);
	}
}
