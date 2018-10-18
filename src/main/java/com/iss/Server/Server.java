/**
 * @Title: Client.java
* @Package com.iss.Client
* @Description: 客户端运行入口
* @author Administrator
* @date 2018年10月16日
* @version V1.0

 */
package com.iss.Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iss.bean.BalancePayments;
import com.iss.bean.PathNameId;
import com.iss.bean.userInfo;
import com.iss.util.DBUtil;
import com.iss.util.JSON;
import com.iss.util.Resultset;

public class Server {
	static Scanner scan = new Scanner(System.in);
/**
 * 
 * @Title: main
* @Description: 服务器端main方法
* @param @param args
* @param @throws IOException    参数
* @return void    返回类型
* @throws
 */
	public static void main(String[] args) throws IOException {
		// TCP通讯
		// 1.服务器端的Socket
		System.out.println("\t☒启动服务器");
		ServerSocket ser = new ServerSocket(9998);

		// 每连接一个客户端，启动一个线程

		while (true) {
			final Socket soc = ser.accept();
			new Thread() {

				public void run() {
					try {
						// 接收客户端消息
						InputStream is = soc.getInputStream();
						OutputStream os = soc.getOutputStream();

						byte[] b = new byte[2048*10];
						int x = 0;
						Gson gson = new Gson();
						while (true) {
							x = is.read(b);							
							String str = new String(b, 0, x);							
							Map map = gson.fromJson(str, Map.class);
							String method = map.get("method").toString();
							String param = map.get("param").toString();
							if (str.equalsIgnoreCase("quit") == true) {
								System.out.println("\t☒用户关闭系统！");					
							}
							 else {
								String res = "";								
								switch (method) {
								case "userLogin":
									List<userInfo> list = gson.fromJson(param, new TypeToken<List<userInfo>>() {
									}.getType());
									String userLoginName = list.get(0).getUserName();
									String userLoginPassword = list.get(0).getPassWord();
									String sql = "select i_id from userLogin where l_userName='" + userLoginName
											+ "'and l_passWord='" + userLoginPassword + "'";
									ResultSet rSet = Resultset.getResultSet(sql, DBUtil.getConn());
									if (rSet.next()) {
										System.out.println("\t☒用户登录系统！！！");
										String userid = rSet.getString(1);
										res = JSON.StringtoJSON("userLogin", userid);
									} else {
										System.out.println("\t☒用户登录失败，请注册账号或者重新输入！");
										res = JSON.StringtoJSON("userRegistration", false);
									}
									break;
								case "userRegistration":
									List<userInfo> list1 = gson.fromJson(param, new TypeToken<List<userInfo>>() {
									}.getType());
									String userRegistrationName = list1.get(0).getUserName();
									String userRegistrationPassWord = list1.get(0).getPassWord();
									String sex = list1.get(0).getSex();
									String userId = list1.get(0).getI_id();
									// 填写注册表
									String userRegistrationSQL = "insert into userInfo values('" + userId + "','"
											+ userRegistrationName + "','" + sex + "')";
									Resultset.getResultSet(userRegistrationSQL, DBUtil.getConn());
									// 填写用户表
									String userMessageSQL = "insert into userLogin values(sys_guid(),'" + userId + "','"
											+ userRegistrationName + "','" + userRegistrationPassWord + "')";
									Resultset.getResultSet(userMessageSQL, DBUtil.getConn());
									res = JSON.StringtoJSON("userRegistration", true);
									System.out.println("\t☒用户注册成功！！！");
									break;
								case "checkBalance":
									String checkid = param;
									String checkBalanceSQL = "select b_balance，b_datetime from balancePayments where i_id='"
											+ checkid + "'";			
									ResultSet rs = Resultset.getResultSet(checkBalanceSQL, DBUtil.getConn());								
									List<BalancePayments> baList = new ArrayList<>();
									while (rs.next()) {										
										BalancePayments ba = new BalancePayments(rs.getDouble(1), rs.getString(2));
										baList.add(ba);
									}
									res = JSON.StringtoJSON("checkBalance", baList);
									System.out.println("\t☒查看个人收支信息成功！");
									break;
								case "addBalance":
									List<BalancePayments> addBalancelist = gson.fromJson(param,
											new TypeToken<List<BalancePayments>>() {
											}.getType());
									String i_id = addBalancelist.get(0).getId();
									double b_balance = addBalancelist.get(0).getB_balance();
									String time = addBalancelist.get(0).getTime();
									String addBalanceSQL = "insert into balancePayments values (sys_guid(),'" + i_id
											+ "','" + b_balance + "','" + time + "')";
									Resultset.getResultSet(addBalanceSQL, DBUtil.getConn());
									res = JSON.StringtoJSON("addBalance", "1");
									System.out.println("\t☒添加个人收支情况成功！");
									break;
								case "delBalance":
									List<BalancePayments> delBalancelist = gson.fromJson(param,
											new TypeToken<List<BalancePayments>>() {
											}.getType());
									String b_id = delBalancelist.get(0).getId();
									String datetime = delBalancelist.get(0).getTime();
									String delBalanceSQL = "select b_id,b_datetime from balancePayments where b_datetime='"
											+ datetime + "'";
									ResultSet delBalancerSet = Resultset.getResultSet(delBalanceSQL, DBUtil.getConn());
									if (delBalancerSet.next()) {
										String bid = delBalancerSet.getString(1);
										String delBalanceSQL1 = "delete  from balancePayments where b_id='" + bid + "'";
										Resultset.getResultSet(delBalanceSQL1, DBUtil.getConn());
									}
									res = JSON.StringtoJSON("delBalance", "1");
									System.out.println("\t☒删除个人收支情况成功！");
									break;
								case "updateBalance":
									List<BalancePayments> updateBalancelist = gson.fromJson(param,
											new TypeToken<List<BalancePayments>>() {
											}.getType());
									String update_id = updateBalancelist.get(0).getId();
									Double updatebalance = updateBalancelist.get(0).getB_balance();
									String updatetime = updateBalancelist.get(0).getTime();
									String updateBalanceSQL = "update balancePayments b set b.b_balance='"
											+ updatebalance + "' where b.b_datetime='" + updatetime + "'";
									ResultSet updateBalancerSet = Resultset.getResultSet(updateBalanceSQL,
											DBUtil.getConn());
									res = JSON.StringtoJSON("updateBalance", "1");
									System.out.println("\t☒修改个人收支情况成功！");
									break;
								case "seeUserInfo":
									String seeUserInfoSQL = "select i_id,l_userName,i_sex from userInfo";
									ResultSet seeUserInfoSet = Resultset.getResultSet(seeUserInfoSQL, DBUtil.getConn());
									List<userInfo> baListS = new ArrayList<>();
									while (seeUserInfoSet.next()) {
										userInfo ba1 = new userInfo(seeUserInfoSet.getString(1),
												seeUserInfoSet.getString(2), seeUserInfoSet.getString(3));
										baListS.add(ba1);
									}
									res = JSON.StringtoJSON("seeUserInfo", baListS);
									System.out.println("\t☒查看系统账号成功！");
									break;
								case "seeAllbalanceInfo":
									String seeAllbalanceInfoSQL = "select b.i_id,l_userName,b_balance,b_datetime from balancePayments b,userInfo u where b.i_id=u.i_id";
									ResultSet seeAllbalanceInfoSet = Resultset.getResultSet(seeAllbalanceInfoSQL,
											DBUtil.getConn());
									List<BalancePayments> baListSI = new ArrayList<>();
									while (seeAllbalanceInfoSet.next()) {
										BalancePayments ba1 = new BalancePayments(seeAllbalanceInfoSet.getString(1),
												seeAllbalanceInfoSet.getString(2), seeAllbalanceInfoSet.getDouble(3),
												seeAllbalanceInfoSet.getString(4));
										baListSI.add(ba1);
									}
									res = JSON.StringtoJSON("seeAllbalanceInfo", baListSI);
									System.out.println("\t☒查看所有账号收支成功！");
									break;
								case "downloadBalancexlsx":
									String downloadBalancexlsxSQL = "select u.l_userName,u.i_sex,b.b_balance,b.b_datetime from balancePayments b,userInfo u where b.i_id=u.i_id";
									Statement cmd = DBUtil.getConn().createStatement();
									ResultSet downloadrs = cmd.executeQuery(downloadBalancexlsxSQL);
									String titleRow[] = { "姓名", "性别", "收支信息", "录入时间" };// 定义列头
									Workbook wb = new XSSFWorkbook();// 定义Excel文件 这个excel文件是以xlsx结尾的
									Sheet sheet = wb.createSheet("家庭收支信息统计表");// 定义sheet页
									// 创建第一行
									Row row = sheet.createRow(0);// 在createRow中，整型参数表示创建的第几行
									row.setHeight((short) 540);// setHeight的参数单位称为twips缇，是屏幕的计量单位，1/20磅
									Cell cell = row.createCell(0);// 创建单元格 createCell，参数表示创建的是第几个单元格
									cell.setCellValue("家庭收支信息统计表");
									CellStyle style = wb.createCellStyle();// 创建单元格样式
									style.setAlignment(HorizontalAlignment.CENTER);// 垂直对齐
									style.setWrapText(true);// 指定当单元格显示不下时自动换行
									Font font = wb.createFont();// 字体对象
									font.setFontName("微软雅黑");// 指定字体名称
									font.setFontHeight((short) 280);// 设置字体大小
									style.setFont(font);// 将字体应用到样式上
									cell.setCellStyle(style);// 单元格应用样式
									sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));// 合并单元格,CellRangeAddress方法有4个参数，开始单元格的行，开始单元格的列，结束单元格的行，结束单元格的列
									// 创建第二行数据，也就是表头
									row = sheet.createRow(1);
									for (int i = 0; i < titleRow.length; i++) {
										cell = row.createCell(i);
										cell.setCellValue(titleRow[i]);
										cell.setCellStyle(style);
										sheet.setColumnWidth(i, 20 * 256);// 第二个参数是单元格的宽度，标准单位是1/256
									}
									row.setHeight((short) 540);
									int i = 0;
									while (downloadrs.next()) {
										row = sheet.createRow(i + 2);
										row.createCell(0).setCellValue(downloadrs.getString(1));
										row.createCell(1).setCellValue(downloadrs.getString(2));
										row.createCell(2).setCellValue(downloadrs.getString(3));
										row.createCell(3).setCellValue(downloadrs.getString(4));
										row.setHeight((short) 480);
										i++;
									}
									// 创建excel文件，使用IO流技术
									OutputStream stream = new FileOutputStream("D:\\farmily.xlsx");
									// 写出数据
									wb.write(stream);
									// 关闭
									stream.close();
									wb.close();
									File dir = new File("D:\\farmily.xlsx");
									if (dir.exists()) {
										System.out.println("\t☒文件存在，下载成功！");
									} else {
										System.out.println("\t☒文件不存在，下载失败！");
									}
									res = JSON.StringtoJSON("downloadBalancexlsx", "D:\\farmily.xlsx");

									System.out.println("\t☒下载xlsx文件成功！");
									break;
								case "downloadBalancexml":
									String downloadBalancexmlSQL = "select * from balancePayments";
									ResultSet XMLrs = Resultset.getResultSet(downloadBalancexmlSQL, DBUtil.getConn());
									// 以下开始处理XML
									// 首先创建一个Document对象，本质上就是XML
									Document doc = DocumentHelper.createDocument();
									// 创建根节点
									Element root = doc.addElement("FamilyBalancexml");
									// 创建一级子节点
									Element student = null;
									while (XMLrs.next()) {
										student = root.addElement("balancePayments");

										Element b_id1 = student.addElement("b_id");
										Element i_id1 = student.addElement("i_id");
										Element b_balance1 = student.addElement("b_balance");
										Element b_datetime = student.addElement("b_datetime");

										b_id1.setText(XMLrs.getString(1));
										i_id1.setText(XMLrs.getString(2));
										b_balance1.setText(XMLrs.getString(3));
										b_datetime.setText(XMLrs.getString(4));
									}

									// 设置输出的字符格式
									OutputFormat format = OutputFormat.createPrettyPrint();
									format.setEncoding("UTF-8");

									// 保存XML文件
									XMLWriter writer = new XMLWriter(new FileOutputStream("D:\\balancePayments.xml"),
											format);
									writer.write(doc);
									writer.close();
									File dir1 = new File("D:\\balancePayments.xml");
									if (dir1.exists()) {
										System.out.println("\t☒文件存在，下载成功！");
									} else {
										System.out.println("\t☒文件不存在，下载失败！");
									}
									res = JSON.StringtoJSON("downloadBalancexml", "D:\\balancePayments.xml");

									System.out.println("\t☒下载XML文件成功！");
									break;
								case "updateBalancexlsx":
									List<PathNameId> pathNameIdslist = gson.fromJson(param,
											new TypeToken<List<PathNameId>>() {
											}.getType());
									String pathName = pathNameIdslist.get(0).getPathName();						
									String pathuserId = pathNameIdslist.get(0).getId();						
									FileInputStream inputStream = new FileInputStream("D:\\" + pathName + ".xlsx");
									Workbook wb1 = new XSSFWorkbook(inputStream);
									Sheet sheet1 = wb1.getSheetAt(0);
									// 获得当前sheet页的结果集
									Iterator<Row> rows = sheet1.rowIterator();
									// 移动到第三行执行两次rows.next()
									rows.next();
									rows.next();
									// 循环数据，hasNext()用于判断是否有下一行数据
									while (rows.hasNext()) {
										Row row1 = rows.next();// 从当前结果集中取出一行数据
										//System.out.println(row1.getCell(2).getStringCellValue());
										//System.out.println(row1.getCell(3).getStringCellValue());
										double balanceda = row1.getCell(2).getNumericCellValue();
										String date11 = row1.getCell(3).getStringCellValue();
										String sqlbalance = "insert into balancePayments values(sys_guid(),'"
												+ pathuserId + "','" + balanceda + "','" + date11 + "')";
										Resultset.getResultSet(sqlbalance, DBUtil.getConn());
									}
									System.out.println("\t☒信息入库成功！！");
									res = JSON.StringtoJSON("updateBalancexlsx", "信息入库成功！！");
									break;
								case "exit":
									System.out.println("\t☒用户退出登录！！");
									res = JSON.StringtoJSON("exit", true);
									break;
								default:
									res = "输入有误！！";
									break;
								}

								os.write(res.getBytes());
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}.start();
		}

	}
}
