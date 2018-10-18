/**
 * @Title: PathNameId.java
* @Package com.iss.bean
* @Description: 入库文件
* @author Administrator
* @date 2018年10月16日
* @version V1.0
 */
package com.iss.bean;

/**
 * 
 * @ClassName: PathNameId
 * @Description: 入库文件的地址和登录人的id
 * @author Administrator
 * @date 2018年10月16日
 *
 */
public class userLogin {
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
	 * 
	 * 创建一个新的实例 userLogin.
	 *
	 * @param userName
	 * @param passWord
	 */
	public userLogin(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}

	/**
	 * 
	 * 创建一个新的实例 userLogin.
	 *
	 */
	public userLogin() {
		super();

	}

	@Override
	public String toString() {
		return "userLogin [userName=" + userName + ", passWord=" + passWord + "]";
	}

}
