/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：MedalResPara   
 * 类描述：   
 * 创建人：Chen sy 
 * 创建时间：2015年4月9日 上午10:36:56   
 * 修改人：Chen sy   
 * 修改时间：2015年4月9日 上午10:36:56   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.entity.response;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.lst.base.entity.BaseResponse;
import com.lst.model.MstMedal;

/**
 * @ClassName: MedalResPara
 * @Description: 返回勋章
 * @author Chen sy
 * @date 2015年4月9日 上午10:36:56
 * 
 */
public class MedalResPara extends BaseResponse {

	private static final long serialVersionUID = 1L;
	@Expose
	private List<MstMedal> mstMedalList;

	/**
	 * @return the mstMedalList
	 */
	public List<MstMedal> getMstMedalList() {
		return mstMedalList;
	}

	/**
	 * @param mstMedalList
	 *            the mstMedalList to set
	 */
	public void setMstMedalList(List<MstMedal> mstMedalList) {
		this.mstMedalList = mstMedalList;
	}

}
