/**
 * @Title: Client.java
* @Package com.iss.Client
* @Description: 客户端运行入口
* @author Administrator
* @date 2018年10月16日
* @version V1.0

 */
package com.iss.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * 
 * @ClassName: Resultset
* @Description: 执行SQL语句工具类
* @author Administrator
* @date 2018年10月16日
*
 */
public class Resultset {
	public static ResultSet getResultSet(String sql, Connection con) throws Exception {
		ResultSet rs = null;
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		return rs;
	}
}