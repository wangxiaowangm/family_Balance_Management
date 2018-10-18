/**
 * @Title: PersonalInformation.java
* @Package com.iss.Client
* @Description: 系统功能页面
* @author Administrator
* @date 2018年10月16日
* @version V1.0

 */
package com.iss.Client;

import java.util.ArrayList;
import java.util.Scanner;
import com.iss.bean.BalancePayments;
import com.iss.bean.PathNameId;
import com.iss.util.JSON;

/**
 * 
 * @ClassName: PersonalInformation
 * @Description: 系统的功能板块
 * @author Administrator
 * @date 2018年10月16日
 *
 */
public class PersonalInformation {
	private static Scanner scan = new Scanner(System.in);

	/**
	 * 
	 * @Title: showIncomeandpay @Description: 显示系统功能 @param @return @param @throws
	 * Exception 参数 @return int 返回类型 @throws
	 */
	public static int showIncomeandpay() throws Exception {
		System.out.println("✲✲✲✲✲系统功能界面✲✲✲✲✲✲✲✲");
		System.out.println("\t1.查看个人收支情况");
		System.out.println("\t2.添加个人收支情况");
		System.out.println("\t3.修改个人收支情况");
		System.out.println("\t4.删除个人收支情况");
		System.out.println("\t5.查看系统账号信息");
		System.out.println("\t6.查看所有账号收支");
		System.out.println("\t7.下载家庭收支信息(.xlsx)");
		System.out.println("\t8.下载家庭收支信息(.xml)");
		System.out.println("\t9.个人收支信息数据表入库");
		System.out.println("\t10.退出登录");
		System.out.println("✲✲✲✲✲✲✲✲✲✲✲✲✲✲✲✲✲✲✲");
		System.out.print("☞请输入您的选择：");
		int input = scan.nextInt();
		return input;
	}

	/**
	 * 
	 * @Title: doIncomeandpay @Description: 选择系统功能 @param @param
	 * choose @param @param id @param @return 参数 @return String 返回类型 @throws
	 */
	public static String doIncomeandpay(int choose, String id) {
		String res = "";
		switch (choose) {
		case 1:
			res = JSON.StringtoJSON("checkBalance", id);// 查看收支情况
			break;
		case 2:
			res = addBalance(id);// 添加收支情况
			break;
		case 3:
			res = updateBalance(id);// 修改个人收支情况
			break;
		case 4:
			res = delBalance(id);// 删除收支情况
			break;
		case 5:
			res = JSON.StringtoJSON("seeUserInfo", id);// 查看系统账号信息
			break;
		case 6:
			res = JSON.StringtoJSON("seeAllbalanceInfo", id);// 查看所有账号收支
			break;
		case 7:
			res = JSON.StringtoJSON("downloadBalancexlsx", id);// 下载家庭收支信息(.xlsx)
			break;
		case 8:
			res = JSON.StringtoJSON("downloadBalancexml", id);// 下载家庭收支信息(.xml)
			break;
		case 9:
			res = updateBalancexlsx(id);// 个人收支信息数据表入库
			break;
		case 10:
			res = JSON.StringtoJSON("exit", id);// 退出登录
			break;
		}
		return res;
	}

	/**
	 * 
	 * @Title: updateBalancexlsx @Description: 个人信息入库功能 @param @param
	 * id @param @return 参数 @return String 返回类型 @throws
	 */
	private static String updateBalancexlsx(String id) {
		System.out.println("\t个人收支信息数据表入库");
		System.out.print("☞请输入入库文件的名字：");// D:\farmily.xlsx
		String urlFile = scan.next();
		ArrayList list = new ArrayList();
		PathNameId pathNamexslx0 = new PathNameId(urlFile, id);
		list.add(pathNamexslx0);
		String json = JSON.StringtoJSON("updateBalancexlsx", list);
		return json;
	}

	/**
	 * 
	 * @Title: updateBalance @Description: 修改个人收支功能 @param @param id @param @return
	 * 参数 @return String 返回类型 @throws
	 */
	private static String updateBalance(String id) {
		System.out.println("----------------------------");
		System.out.println("\t修改个人收支情况信息");
		System.out.println("☞请输入要修改的信息的时间");
		String update = scan.next();
		System.out.println("☞修改的信息修改为");
		Double updatebalance = scan.nextDouble();
		System.out.println("----------------------------");
		ArrayList list = new ArrayList();
		BalancePayments updatebalanceMessage = new BalancePayments(id, updatebalance, update);
		list.add(updatebalanceMessage);
		String json = JSON.StringtoJSON("updateBalance", list);
		return json;
	}

	/**
	 * 
	 * @Title: delBalance @Description: 删除个人收支功能 @param @param id @param @return
	 * 参数 @return String 返回类型 @throws
	 */
	private static String delBalance(String id) {
		System.out.println("----------------------------");
		System.out.println("\t删除个人收支信息");
		System.out.println("☞请输入要删除的信息的时间");
		String del = scan.next();
		System.out.println("----------------------------");
		ArrayList list = new ArrayList();
		BalancePayments delbalanceMessage = new BalancePayments(id, del);
		list.add(delbalanceMessage);
		String json = JSON.StringtoJSON("delBalance", list);
		return json;

	}

	/**
	 * 
	 * @Title: addBalance @Description: 添加个人收支功能 @param @param id @param @return
	 * 参数 @return String 返回类型 @throws
	 */
	public static String addBalance(String id) {
		System.out.println("----------------------------");
		System.out.println("\t添加个人收支信息");
		System.out.print("☞输入收支情况(正数为收入，负数为支出)：");
		double balance = scan.nextDouble();
		System.out.print("☞输入收支时间：");
		String datetime = scan.next();
		System.out.println("----------------------------");
		ArrayList list = new ArrayList();
		BalancePayments balanceMessage = new BalancePayments(id, balance, datetime);
		list.add(balanceMessage);
		String json = JSON.StringtoJSON("addBalance", list);
		return json;
	}
}
