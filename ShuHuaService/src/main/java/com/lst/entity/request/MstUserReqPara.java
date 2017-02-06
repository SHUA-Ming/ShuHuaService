/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：MstUserReq   
 * 类描述：   
 * 创建人：Chen sy 
 * 创建时间：2015年4月7日 下午2:05:02   
 * 修改人：Chen sy   
 * 修改时间：2015年4月7日 下午2:05:02   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.entity.request;

import com.lst.base.entity.BaseRequest;

/**
 * @ClassName: MstUserReq
 * @Description: 注册的请求参数
 * @author Chen sy
 * @date 2015年4月7日 下午2:05:02
 * 
 */
public class MstUserReqPara extends BaseRequest {
	// 图片二进制字节
	private String imagebinary;
	// 姓名
	private String name;
	// 性别
	private String gender;
	// 昵称
	private String nickname;
	// 手机
	private String mobileno;
	// 出生日期
	private String birthday;
	// 邮箱
	private String email;
	// 体重
	private String weight;
	// 身高
	private String height;
	// 密码
	private String password;
	// 新密码
	private String newpwd;
	
	private String qq;
	private String weixin;
	
	private String weibo;
	
	private String imgurl;
	private String id;
	
	
	private String optype="nomal";
	
	public static final String REG_NORMAL_TYPE="REG";
	public static final String LOGIN_NORMAL_TYPE="NORMAL";
	
	public static final String LOGIN_QQ_TYPE="QQ";
	public static final String LOGIN_WEIBO_TYPE="WEIBO";
	public static final String LOGIN_WEIXIN_TYPE="WEIXIN";

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getOptype() {
		return optype;
	}

	public void setOptype(String logintype) {
		this.optype = logintype;
	}

	/**
	 * @return the imagebinary
	 */
	public String getImagebinary() {
		return imagebinary;
	}

	/**
	 * @param imagebinary
	 *            the imagebinary to set
	 */
	public void setImagebinary(String imagebinary) {
		this.imagebinary = imagebinary;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the mobileno
	 */
	public String getMobileno() {
		return mobileno;
	}

	/**
	 * @param mobileno
	 *            the mobileno to set
	 */
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the newpwd
	 */
	public String getNewpwd() {
		return newpwd;
	}

	/**
	 * @param newpwd
	 *            the newpwd to set
	 */
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

}
