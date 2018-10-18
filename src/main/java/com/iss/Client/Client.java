/**
 * @Title: Client.java
* @Package com.iss.Client
* @Description: 客户端运行入口
* @author Administrator
* @date 2018年10月16日
* @version V1.0

 */

package com.iss.Client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iss.bean.BalancePayments;
import com.iss.bean.userInfo;

/**
 * 
 * @ClassName: Client
 * @Description: 客户端
 * @author Administrator
 * @date 2018年10月16日
 *
 */

public class Client {
	static Scanner scan = new Scanner(System.in);
	/**
	 * 
	 * @Title: main
	* @Description: 客户端main方法
	* @param @param args
	* @param @throws Exception    参数
	* @return void    返回类型
	* @throws
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("\t☒启动客户端");
		Socket soc = new Socket("127.0.0.1", 9998);
		InputStream is = soc.getInputStream();
		OutputStream os = soc.getOutputStream();
		Gson gson = new Gson();
		byte[] b = new byte[2048*10];
		int x = 0;
		String choose = "";
		String id = "";
		boolean loginFlag = false;
		while (true) {
			choose = Menu.doMenu(Menu.showMenu());
			Map quitMap = gson.fromJson(choose, Map.class);
			String quit = (String) quitMap.get("method");
			if (quit.equalsIgnoreCase("quit") == true) {
				os.write("quit".getBytes());
				break;
			} else {
				os.write(choose.getBytes());
				x = is.read(b);
				String str = new String(b, 0, x);
				Map map = gson.fromJson(str, Map.class);
				String method = (String) map.get("method");
				switch (method) {
				case "userLogin":
					id = (String) map.get("param");
					if (!id.isEmpty()) {
						System.out.println("※※※※※※※※※※※※※※※※※※※");
						System.out.println("\t☒登录成功！！");
						loginFlag = true;
					} else {
						System.out.println("\t☒账号或者密码输入错误");
						System.out.println("※※※※※※※※※※※※※※※※※※※");
					}
					break;
				case "userRegistration":
					boolean registrationFlag = (boolean) map.get("param");
					if (registrationFlag == true) {
						System.out.println("※※※※※※※※※※※※※※※※※※※");
						System.out.println("\t☒注册成功！！");
					}
				}
				while (loginFlag == true) {
					String loginid = id;
					choose = PersonalInformation.doIncomeandpay(PersonalInformation.showIncomeandpay(), id);
					if (choose.equalsIgnoreCase("quit") == true) {
						os.write("quit".getBytes());
						break;
					} else {
						os.write(choose.getBytes());
						x = is.read(b);						
						String str1 = new String(b, 0, x);						
						Map map1 = gson.fromJson(str1, Map.class);
						String method1 = (String) map1.get("method");
						switch (method1) {
						case "checkBalance":
							System.out.println("----------------------------");
							System.out.println("\t查看个人收支情况信息");
							String param = map1.get("param").toString();
							List<BalancePayments> list = gson.fromJson(param, new TypeToken<List<BalancePayments>>() {
							}.getType());
							for (BalancePayments balancePayments : list) {
								System.out.println(
										"收支余额：" + balancePayments.getB_balance() + "\t日期：" + balancePayments.getTime());
							}
							System.out.println("----------------------------");
							break;
						case "addBalance":
							System.out.println("\t☒添加成功！");
							break;
						case "delBalance":
							System.out.println("\t☒删除成功！");
							break;
						case "updateBalance":
							System.out.println("\t☒修改成功！");
							break;
						case "seeUserInfo":
							System.out.println("----------------------------");
							System.out.println("\t查看系统账号信息");
							String UserInfoparam = map1.get("param").toString();
							List<userInfo> UserInfolist = gson.fromJson(UserInfoparam, new TypeToken<List<userInfo>>() {
							}.getType());
							for (userInfo userInfo : UserInfolist) {
								System.out.println("用户id：" + userInfo.getI_id() + "\t用户名：" + userInfo.getUserName()
										+ "\t性别：" + userInfo.getSex());
							}
							System.out.println("----------------------------");
							break;
						case "seeAllbalanceInfo":
							System.out.println("----------------------------");
							System.out.println("\t查看所有账号收支信息");
							String AllInfoparam = map1.get("param").toString();
							List<BalancePayments> AllInfolist = gson.fromJson(AllInfoparam,
									new TypeToken<List<BalancePayments>>() {
									}.getType());
							for (BalancePayments BalancePayments : AllInfolist) {
								System.out.println("用户id：" + BalancePayments.getId() + "用户名："
										+ BalancePayments.getName() + "收支信息：" + BalancePayments.getB_balance()
										+ "\t入账时间 ：" + BalancePayments.getTime());
							}
							System.out.println("----------------------------");
							break;
						case "downloadBalancexlsx":
							System.out.println("\t☒下载xlsx文件成功！");
							String pathparam = (String) map1.get("param");
							System.out.println("文件保存路径：" + pathparam);
							break;
						case "downloadBalancexml":
							System.out.println("\t☒下载xml文件成功！");
							String pathxmlparam = (String) map1.get("param");
							System.out.println("文件保存路径：" + pathxmlparam);
							break;
						case "updateBalancexlsx":// D:\farmily.xlsx
							System.out.println("入库xlsx文件成功！");
							String pathxmlparam1 = (String) map1.get("param");
							System.out.println("文件保存路径：" + pathxmlparam1);
							break;
						case "exit":
							System.out.println("\t☒用户退出登录！！");
							loginFlag = false;
							break;
						default:
							break;
						}

					}

				}

			}
		}
		soc.close();
		System.out.println("从服务器端，断开连接");
	}

}
