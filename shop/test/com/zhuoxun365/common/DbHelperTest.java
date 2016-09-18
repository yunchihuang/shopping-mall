package com.zhuoxun365.common;

import java.sql.Connection;
import java.sql.SQLException;

import com.huangyunchi.common.DbHelper;

/**
 * DbHelper单元测试类
 * @author Administrator
 *
 */
public class DbHelperTest {
	
	public static void main(String[] args) {
		try {
			Connection conn = DbHelper.getConn();
			
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
