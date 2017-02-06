/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：BaseRequest   
 * 类描述：   
 * 创建人：Echo   
 * 创建时间：2014年6月11日 下午5:36:43   
 * 修改人：Echo   
 * 修改时间：2014年6月11日 下午5:36:43   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.base.entity;

/**
 * @ClassName: BaseRequest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Echo
 * @date 2014年6月11日 下午5:36:43
 * 
 */
public class BaseRequest implements Cloneable {

	// 渠道
	private String channel = "P";
	// SIM卡全球唯一硬件识别号
	private String iccidno = "";
	// 手机唯一识别号
	private String imeino = "";
	// SIM卡全球唯一用户识别号
	private String imsino = "";
	// 手机号码
	private String mdnno = "";
	// 签名信息
	private String signmsg = "";
	// 签名方式
	private String signtype = "MD5";
	// 终端号
	private String terminalno = "";
	// 时间戳
	private String timestamp = "20140217 18:09:20";
	// 用户类型
	private String usertype = "0000";
	// 版本信息
	private String version = "V1.0";
	// 用户id
	private String userid = "0";

	@Override
	public Object clone() throws CloneNotSupportedException {
		BaseRequest requestParaClone = null;

		try {
			requestParaClone = (BaseRequest) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return requestParaClone;
	}

	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * @return the iccidno
	 */
	public String getIccidno() {
		return iccidno;
	}

	/**
	 * @return the imeino
	 */
	public String getImeino() {
		return imeino;
	}

	/**
	 * @return the imsino
	 */
	public String getImsino() {
		return imsino;
	}

	/**
	 * @return the mdnno
	 */
	public String getMdnno() {
		return mdnno;
	}

	/**
	 * @return the signmsg
	 */
	public String getSignmsg() {
		return signmsg;
	}

	/**
	 * @return the signtype
	 */
	public String getSigntype() {
		return signtype;
	}

	/**
	 * @return the terminalno
	 */
	public String getTerminalno() {
		return terminalno;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @return the usertype
	 */
	public String getUsertype() {
		return usertype;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * @param iccidno
	 *            the iccidno to set
	 */
	public void setIccidno(String iccidno) {
		this.iccidno = iccidno;
	}

	/**
	 * @param imeino
	 *            the imeino to set
	 */
	public void setImeino(String imeino) {
		this.imeino = imeino;
	}

	/**
	 * @param imsino
	 *            the imsino to set
	 */
	public void setImsino(String imsino) {
		this.imsino = imsino;
	}

	/**
	 * @param mdnno
	 *            the mdnno to set
	 */
	public void setMdnno(String mdnno) {
		this.mdnno = mdnno;
	}

	/**
	 * @param signmsg
	 *            the signmsg to set
	 */
	public void setSignmsg(String signmsg) {
		this.signmsg = signmsg;
	}

	/**
	 * @param signtype
	 *            the signtype to set
	 */
	public void setSigntype(String signtype) {
		this.signtype = signtype;
	}

	/**
	 * @param terminalno
	 *            the terminalno to set
	 */
	public void setTerminalno(String terminalno) {
		this.terminalno = terminalno;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @param usertype
	 *            the usertype to set
	 */
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
}
