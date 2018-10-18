/**
 * @Title: Client.java
* @Package com.iss.Client
* @Description: 客户端运行入口
* @author Administrator
* @date 2018年10月16日
* @version V1.0

 */
package com.iss.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 
 * @ClassName: PropertiesUtil
* @Description: 读取配置文件工具类
* @author Administrator
* @date 2018年10月16日
*
 */
public class PropertiesUtil {
	public static String getValue(String key) {
		String val = "";
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("src\\AppConfig.properties"));
			val = prop.getProperty(key);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return val;
	}

}
