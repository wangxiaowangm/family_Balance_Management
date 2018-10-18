/**
 * @Title: userInfo.java
* @Package com.iss.bean
* @Description: 用户信息表
* @author Administrator
* @date 2018年10月16日
* @version V1.0
 */
package com.iss.bean;

/**
 * 
 * @ClassName: PathNameId
 * @Description: 用户信息表
 * @author Administrator
 * @date 2018年10月16日
 *
 */
public class userInfo {
	/**
	 * @fieldName: userName
	 * @fieldType: String
	 * @Description: 用户名
	 */
	public String userName;
	/**
	 * @fieldName: passWord
	 * @fieldType: String
	 * @Description: 密码
	 */
	public String passWord;
	/**
	 * @fieldName: sex
	 * @fieldType: String
	 * @Description: 性别
	 */
	public String sex;
	/**
	 * @fieldName: i_id
	 * @fieldType: String
	 * @Description: 用户id
	 */
	public String i_id;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the i_id
	 */
	public String getI_id() {
		return i_id;
	}

	/**
	 * @param i_id the i_id to set
	 */
	public void setI_id(String i_id) {
		this.i_id = i_id;
	}

	/**
	 * 
	 * 创建一个新的实例 userInfo.
	 *
	 * @param userName
	 * @param passWord
	 */
	public userInfo(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}

	/**
	 * 
	 * 创建一个新的实例 userInfo.
	 *
	 * @param i_id
	 * @param userName
	 * @param sex
	 */
	public userInfo(String i_id, String userName, String sex) {
		super();
		this.userName = userName;
		this.sex = sex;
		this.i_id = i_id;
	}

	/**
	 * 
	 * 创建一个新的实例 userInfo.
	 *
	 * @param userName
	 * @param passWord
	 * @param sex
	 * @param i_id
	 */
	public userInfo(String userName, String passWord, String sex, String i_id) {

		this.userName = userName;
		this.passWord = passWord;
		this.sex = sex;
		this.i_id = i_id;
	}

	/**
	 * 
	 * 创建一个新的实例 userInfo.
	 *
	 */
	public userInfo() {
		super();

	}

	@Override
	public String toString() {
		return "userInfo [userName=" + userName + ", passWord=" + passWord + ", sex=" + sex + ", i_id=" + i_id + "]";
	}

}
