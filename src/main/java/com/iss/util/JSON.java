/**
 * @Title: Client.java
* @Package com.iss.Client
* @Description: 客户端运行入口
* @author Administrator
* @date 2018年10月16日
* @version V1.0

 */
package com.iss.util;

import java.util.HashMap;
import com.google.gson.Gson;
/**
 * 
 * @ClassName: JSON
* @Description: 将字符串转换为JSON工具类
* @author Administrator
* @date 2018年10月16日
*
 */
public class JSON {
	public static String StringtoJSON(String method, Object list) {
		HashMap hm = new HashMap();
		hm.put("method", method);
		hm.put("param", list);
		Gson g = new Gson();
		String Json = g.toJson(hm);
		return Json;
	}
}
