package com.huangyunchi.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * 数据库操作助手类、主要作用就是用于获取数据库的连接
 * @author Administrator
 */
public class DbHelper {

	/**创建一个集合类，用于加载配置文件*/
	private static Properties props = new Properties();
	
	/**数据源对象*/
	private static DataSource dds = null;
	
	
	/**静态代码块中加载配置文件*/
	static{
		try {
			InputStream is = Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream("dbconfig.properties");
			
			props.load(is);
			
			//根据配置文件来创建一个Druid连接池
			dds = DruidDataSourceFactory.createDataSource(props);
			
		} catch (IOException e) {
			System.err.println("加载数据库配置文件失败！请检查");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("创建连接池失败！");
			e.printStackTrace();
		}
	}
	
	private DbHelper(){}
	
	/**
	 * 获取数据库的一个连接对象
	 * @return 
	 * @throws SQLException 
	 */
	public static Connection getConn() throws SQLException{
		return dds.getConnection();
	}
}
