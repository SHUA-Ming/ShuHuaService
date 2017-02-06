/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：RecordResPara   
 * 类描述：   
 * 创建人：Chen sy 
 * 创建时间：2015年4月8日 上午11:55:45   
 * 修改人：Chen sy   
 * 修改时间：2015年4月8日 上午11:55:45   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.entity.response;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.lst.base.entity.BaseResponse;
import com.lst.model.UserReocrd;

/**
 * @ClassName: RecordResPara
 * @Description: 运动列表
 * @author Chen sy
 * @date 2015年4月8日 上午11:55:45
 * 
 */
public class RecordResPara extends BaseResponse {

	private static final long serialVersionUID = 1L;
	@Expose
	private List<UserReocrd> userRecordList;

	/**
	 * @return the userRecordList
	 */
	public List<UserReocrd> getUserRecordList() {
		return userRecordList;
	}

	/**
	 * @param userRecordList
	 *            the userRecordList to set
	 */
	public void setUserRecordList(List<UserReocrd> userRecordList) {
		this.userRecordList = userRecordList;
	}

}
