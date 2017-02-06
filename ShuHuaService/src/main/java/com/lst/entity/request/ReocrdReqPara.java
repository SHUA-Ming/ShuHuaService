/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：ReocrdReqPara   
 * 类描述：   
 * 创建人：Chen sy 
 * 创建时间：2015年4月8日 上午10:52:58   
 * 修改人：Chen sy   
 * 修改时间：2015年4月8日 上午10:52:58   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.entity.request;

import com.lst.base.entity.BaseRequest;

/**
 * @ClassName: ReocrdReqPara
 * @Description: 运动请求参数
 * @author Chen sy
 * @date 2015年4月8日 上午10:52:58
 * 
 */
public class ReocrdReqPara extends BaseRequest {
	// 用户id
	private String userid;
	// 设备id
	private String machineid;
	// 计划id
	private String planid;
	// 消耗卡路里
	private String calorie;
	// 开始时间
	private String startdate;
	// 结束时间
	private String enddate;
	// 距离
	private String kilometer;
	// 当前页
	private String currentPage;
	// 每页显示多少条
	private String pageSize;

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the machineid
	 */
	public String getMachineid() {
		return machineid;
	}

	/**
	 * @param machineid
	 *            the machineid to set
	 */
	public void setMachineid(String machineid) {
		this.machineid = machineid;
	}

	/**
	 * @return the planid
	 */
	public String getPlanid() {
		return planid;
	}

	/**
	 * @param planid
	 *            the planid to set
	 */
	public void setPlanid(String planid) {
		this.planid = planid;
	}

	/**
	 * @return the calorie
	 */
	public String getCalorie() {
		return calorie;
	}

	/**
	 * @param calorie
	 *            the calorie to set
	 */
	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}

	/**
	 * @return the startdate
	 */
	public String getStartdate() {
		return startdate;
	}

	/**
	 * @param startdate
	 *            the startdate to set
	 */
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	/**
	 * @return the enddate
	 */
	public String getEnddate() {
		return enddate;
	}

	/**
	 * @param enddate
	 *            the enddate to set
	 */
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	/**
	 * @return the kilometer
	 */
	public String getKilometer() {
		return kilometer;
	}

	/**
	 * @param kilometer
	 *            the kilometer to set
	 */
	public void setKilometer(String kilometer) {
		this.kilometer = kilometer;
	}

	/**
	 * @return the currentPage
	 */
	public String getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the pageSize
	 */
	public String getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

}
