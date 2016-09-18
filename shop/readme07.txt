任务一：处理前台的类目显示
1. 由于所有前台页面都 需要展示类目列表，因为我们在ServletContextListener的contextInitialized()方法中来加载所有的类目数据。
2. 把加载出来的类目数据存放到ServletContext中，以便整个项目的Servlet/JSP中都可以访问。
3. 在JSP页面中使用EL表达式就能获取到类目列表数据了。。

任务二、前台首页

1. /index.jsp--foward--> /main --->IndexServlet-->XxxService取数据---> /main.jsp展示 ---> HTML发送给客户端浏览器 -->JS代码


任务三：公告
 1. 列表页：--/news/list--> NewsListServlet-->NewsService.findAll() --> /news_list.jsp
 2. 详情页: --/news/detail?id=xxx-->NewsDetailServlet-->NewsService.findOne() --> /news_detail.jsp
 
任务四：指定分类下的商品列表
 1. 指定一级分类下的：/product/list?level=top&id=xxx
 2. 指定二级分类下的：/product/list?level=second&id=xxx
任务五：热门产品列表
 1. 指定一级分类下的：/product/list?level=hots&id=xxx
任务六：“手机”分类下的产品列表
 1. 指定一级分类下的：/product/list?id=1
任务七：“电脑、平板”分类下的产品列表
  1. 指定一级分类下的：/product/list?level=hots&id=2
  
  
任务八：商品的详情页
请求流程：/product_detail?id=xxx ---> ProductDetailServlet ---> ProductService.findOne() -->/product_detail.jsp
注意：因为商品的详细描述中的图片用的是./img/xxxx.png，所以，把请求路径改在/product_detail?id=xxx格式。。。

