day05的任务一： 完成“会员”的登录/退出功能

一、登录流程
 1. 浏览器---->提交“登录”请求（参数：手机号，密码）
 2. 服务器程序中用Servlet来接收请求。
 3. 在Servlet中获取请求的参数（手机号，密码）
 4. 调用MemberService中的业务处理方法来判断用户是否存在
 5. 用户存在，Servlet就需要把用户引导到“登录成功后的页面-->会员后台首页”
           用户不存在，Servlet给用户返回“登录失败”的提示
   
二、静态页面修改为JSP
 1. 把CSS、images、JS、ZUI复制到WebContent目录下
 2. 把页面公用的部分单独抽取成JSP。
           在需要使用的页面中使用include包含过去。

三、把JSP页面的中链接路径（CSS，JS，image，超链接）都应该改成相对于当前项目路径的方式。
 使用ServletContextListener来实现
 
四、为了防止POST请求中的中文数据乱码，我们需要添加一个过滤器来处理请求数据的编码问题。

五、编写MemberLoginServlet来处理会员登录的请求。


day05的任务二： 完成“会员”的注册功能

/member_register.jsp  --->提交请求
                           |--MemberRegisterServlet--> 调用Service完成save()功能
                                       |-->跳转

day05的任务三：对“会员”的相关操作进行保护：



