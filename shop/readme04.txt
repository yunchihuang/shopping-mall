一. eclipse-jee的Web工程目录结构
 项目工程名
   |-- src                         存放Java源代码的目录★
   |-- resources                   存放配置文件的源目录（自定义的）
   |-- test                        存放单元测试的源目录（自定义的）
   |-- JRE...                      Java运行时环境
   |-- Apache Tomcat...            JavaWeb程序需要的运行环境 
   |-- build                       eclipse内部使用的目录
   |-- WebContennt                 存放Web资源的目录★
          |-- META-INF             把JavaWeb项目打成ear包所需要配置目录
          |-- WEB-INF              JavaWeb程序的信息目录
                 |-- lib           存放第三方jar包的目录★
                 |-- web.xml       JavaWeb程序要有的一个配置文件(3.0以后可选的)
          |-- *.jsp、*.html、*.css、*.js、*.jpg 页面资源

二、添加第三方的jar包
 1. 数据库驱动包： mysql-connector-java-5.1.31.jar、
 2. 数据源包： druid-1.0.20.jar、
 3. 数据库操作工具包：commons-dbutils-1.6.jar、
 4. JSTL包：（jstl-api-1.2.jar、javax.servlet.jsp.jstl-1.2.2.jar）、
 5. 文件上传处理包：（commons-io-2.4.jar、commons-fileupload-1.3.1.jar）、
 6. JSON处理包： gson-2.3.1.jar
 7. 单元测试包：junit-4.12.jar、hamcrest-core-1.3.jar

 三、数据源连接池: druid
  1. 编写一个配置文件: dfconfig.properties
  2. 编写一个数据库操作工具类：DbHelper.java
  3. 测试工具类中的方法是否可用：JUnit
  
  
四、使用JDBC执行数据库的CRUD操作

1. 在MySQL数据库服务器中创建了shop的库，创建了一个表member
-- ----
-- 会员表
-- ----
create table member(
  id               integer           primary key          auto_increment,
  mobile           varchar(32)       unique not null      comment '手机号',
  pwd              varchar(32)       not null             comment '密码',
  real_name        varchar(32)       default null         comment '真实名',
  nick_name        varchar(32)       default null         comment '昵称',
  email            varchar(128)      default null         comment '邮箱号',
  gender           tinyint(1)        default false        comment '性别，默认值false',
  register_time    datetime          comment '注册时间'
);

2. 创建一个与会员表对应的实体类：Member.java

3. 创建一个会员业务逻辑类：MemberService.java


五、commons-dbutils.jar数据库操作工具包可以简化JDBC操作的代码。
主要使用到如下几个类和接口：
1. DbUtils类：提供一些关闭操作数据库的对象，一些事务控制方法。

2. QueryRunner类：SQL执行器。它负责把SQL发送到数据库中执行，对返回的结果进行封装。线程安全
  1）public public <T> T insert(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException
  2）public int update(Connection conn, String sql, Object... params) throws SQLException
  3）public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException 

3. ResultSetHandler接口（结果集处理器）几个常用子类：
1）BeanHanlder类：将结果集中的第一行数据封装到一个对应的JavaBean实例中。 注意，表的列名要跟JavaBean的属性名相同。
2）BeanListHanlder类：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。注意，表的列名要跟JavaBean的属性名相同
3）ScalarHanlder类：将结果集中某一条记录的其中某一列的数据存成 Object。
  4）也可以通过实现ResultSetHandler接口来自定义结果集处理器。


