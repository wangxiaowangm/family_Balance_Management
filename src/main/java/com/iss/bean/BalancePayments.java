/**
 * @Title: BalancePayments.java
* @Package com.iss.bean
* @Description: 收支信息类
* @author Administrator
* @date 2018年10月16日
* @version V1.0

 */
package com.iss.bean;

/**
 *
 * @ClassName: BalancePayments
 * @Description: 收支信息表的类
 * @author Administrator
 * @date 2018年10月16日
 *
 */
public class BalancePayments {
	/**
	 * @fieldName: id
	 * @fieldType: String
	 * @Description: 身份id
	 */
	public String id;
	/**
	 * @fieldName: name
	 * @fieldType: String
	 * @Description: 用户名
	 */
	public String name;
	/**
	 * @fieldName: b_balance
	 * @fieldType: double
	 * @Description: 收入与支出总和
	 */
	public double b_balance;
	/**
	 * @fieldName: time
	 * @fieldType: String
	 * @Description: 记账时间
	 */
	public String time;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the b_balance
	 */
	public double getB_balance() {
		return b_balance;
	}

	/**
	 * @param b_balance the b_balance to set
	 */
	public void setB_balance(double b_balance) {
		this.b_balance = b_balance;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * 
	 * 创建一个新的实例 BalancePayments.
	 *
	 * @param id
	 * @param time
	 */
	public BalancePayments(String id, String time) {
		this.id = id;
		this.time = time;
	}

	/***
	 * 
	 * 创建一个新的实例 BalancePayments.
	 *
	 * @param id
	 * @param name
	 * @param b_balance
	 * @param time
	 */
	public BalancePayments(String id, String name, double b_balance, String time) {
		this.b_balance = b_balance;
		this.time = time;
		this.name = name;
		this.id = id;
	}

	/**
	 * 
	 * 创建一个新的实例 BalancePayments.
	 *
	 * @param b_balance
	 * @param time
	 */
	public BalancePayments(double b_balance, String time) {
		super();
		this.b_balance = b_balance;
		this.time = time;
	}

	/**
	 * 
	 * 创建一个新的实例 BalancePayments.
	 *
	 * @param id
	 * @param b_balance
	 */
	public BalancePayments(String id, double b_balance) {
		this.id = id;
		this.b_balance = b_balance;
	}

	/**
	 * 
	 * 创建一个新的实例 BalancePayments.
	 *
	 * @param id
	 * @param b_balance
	 * @param time
	 */
	public BalancePayments(String id, double b_balance, String time) {
		this.id = id;
		this.b_balance = b_balance;
		this.time = time;
	}

	/**
	 * 
	 * 创建一个新的实例 BalancePayments.
	 *
	 */
	public BalancePayments() {

	}

	@Override
	public String toString() {
		return "BalancePayments [id=" + id + ", name=" + name + ", b_balance=" + b_balance + ", time=" + time + "]";
	}

}
