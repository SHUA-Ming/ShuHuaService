/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：MaxRecordReqPara   
 * 类描述： 最大纪录请求参数  
 * 创建人：Echo 
 * 创建时间：2015年4月8日 上午10:52:58   
 * 修改人：Echo   
 * 修改时间：2015年4月8日 上午10:52:58   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.entity.request;

import com.lst.base.entity.BaseRequest;

/**
 * @ClassName: MaxRecordReqPara
 * @Description: 最大纪录请求参数
 * @author Echo
 * @date 2015年4月8日 上午10:52:58
 * 
 */
public class MaxRecordReqPara extends BaseRequest {
	// 用户id
	private String userid;
	// 起始日期
	private String startdate;
	// 结束日期
	private String enddate;

	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getStartdate() {
		return startdate;
	}
	
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	
	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
}
