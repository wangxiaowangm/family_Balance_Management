/**
 * @Title: Menu.java
* @Package com.iss.Client
* @Description: 系统菜单页面
* @author Administrator
* @date 2018年10月16日
* @version V1.0

 */
package com.iss.Client;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import com.iss.bean.userInfo;
import com.iss.util.JSON;

/**
 * 
 * @ClassName: Menu
 * @Description: 登录和注册功能
 * @author Administrator
 * @date 2018年10月16日
 *
 */
public class Menu {
	private static Scanner scan = new Scanner(System.in);

	/**
	 * 
	 * @Title: showMenu @Description: 显示系统登录和注册菜单 @param @return 参数 @return int
	 * 返回类型 @throws
	 */
	public static int showMenu() {
		System.out.println("☞☞☞☞☞家庭收支管理系统☜☜☜☜☜☜");
		System.out.println("\t1.用户登录");
		System.out.println("\t2.用户注册");
		System.out.println("\t3.关闭系统");
		System.out.println("※※※※※※※※※※※※※※※※※※※");
		System.out.print("☞请输入您的选择：");
		int input = scan.nextInt();
		return input;
	}

	/**
	 * 
	 * @Title: doMenu @Description: 选择登录还是注册 @param @param
	 * choose @param @return 参数 @return String 返回类型 @throws
	 */
	public static String doMenu(int choose) {
		String res = "";
		switch (choose) {
		case 1:
			res = userLogin();// 用户登录
			break;
		case 2:
			res = userRegistration();// 用户注册
			break;
		case 3:
			res = JSON.StringtoJSON("quit", "退出系统！！");
		}
		return res;
	}
/**
 * 
 * @Title: userRegistration
* @Description: 用户注册功能
* @param @return    参数
* @return String    返回类型
* @throws
 */
	public static String userRegistration() {
		System.out.println("☏☏☏☏☏用户注册界面☏☏☏☏☏☏☏☏");
		// 注册用户
		System.out.print("☞请输入用户名：");
		String userName = scan.next();
		// 用户性别
		System.out.print("☞q请输入性别：");
		String sex = scan.next();
		// 注册密码
		System.out.print("☞请输入密码：");
		String userPasswordInput = scan.next();
		// 确认注册密码
		System.out.print("☞确定输入密码：");
		String userid = UUID.randomUUID().toString().replace("-", "");
		String userPasswordConfirm = scan.next();
		ArrayList list = new ArrayList();
		userInfo user = new userInfo(userName, userPasswordInput, sex, userid);
		list.add(user);
		String json = JSON.StringtoJSON("userRegistration", list);
		return json;
	}
/**
 * 
 * @Title: userLogin
* @Description: 用户登录功能
* @param @return    参数
* @return String    返回类型
* @throws
 */
	public static String userLogin() {
		System.out.println("☏☏☏☏☏用户登录界面☏☏☏☏☏☏☏☏");
		System.out.print("☞请输入用户名：");
		String username = scan.next();// 用户名
		System.out.print("☞请输入密码：");
		String userPasswordInput = scan.next();// 密码
		ArrayList list = new ArrayList();
		userInfo user = new userInfo(username, userPasswordInput);
		list.add(user);
		String json = JSON.StringtoJSON("userLogin", list);
		return json;

	}
}
