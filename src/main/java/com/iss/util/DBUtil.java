/**
 * @Title: Client.java
* @Package com.iss.Client
* @Description: 工具类
* @author Administrator
* @date 2018年10月16日
* @version V1.0

 */
package com.iss.util;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * 
 * @ClassName: DBUtil
* @Description: 连接收据库工具类
* @author Administrator
* @date 2018年10月16日
*
 */
public class DBUtil {
	private static Connection con = null;
	public static Connection getConn() {
		try {
			if (con == null) {
				// 加载数据驱动包
				Class.forName(PropertiesUtil.getValue("driverName"));
				// 创建连接对象
			  con = DriverManager.getConnection(PropertiesUtil.getValue("url"), PropertiesUtil.getValue("user"),
						PropertiesUtil.getValue("passward"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
