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
public class PathNameId {
	/**
	 * @fieldName: pathName
	 * @fieldType: String
	 * @Description: 文件地址
	 */
	public String pathName;
	/**
	 * @fieldName: id
	 * @fieldType: String
	 * @Description: 登录人id
	 */
	public String id;

	/**
	 * @return the pathName
	 */
	public String getPathName() {
		return pathName;
	}

	/**
	 * @param pathName the pathName to set
	 */
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

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

	public PathNameId(String pathName, String id) {
		this.pathName = pathName;
		this.id = id;
	}

	@Override
	public String toString() {
		return "PathNameId [pathName=" + pathName + ", id=" + id + "]";
	}

}
