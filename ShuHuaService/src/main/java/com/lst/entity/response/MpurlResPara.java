/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：MpurlServlet   
 * 类描述：   
 * 创建人：Chen sy 
 * 创建时间：2015年4月8日 下午6:19:24   
 * 修改人：Chen sy   
 * 修改时间：2015年4月8日 下午6:19:24   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.entity.response;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.lst.base.entity.BaseResponse;
import com.lst.model.SystemSetting;

/**
 * @ClassName: MpurlResPara
 * @Description: 图片url
 * @author Chen sy
 * @date 2015年4月8日 下午6:19:24
 * 
 */
public class MpurlResPara extends BaseResponse {

	private static final long serialVersionUID = 1L;
	@Expose
	private List<SystemSetting> settingList;

	/**
	 * @return the settingList
	 */
	public List<SystemSetting> getSettingList() {
		return settingList;
	}

	/**
	 * @param settingList
	 *            the settingList to set
	 */
	public void setSettingList(List<SystemSetting> settingList) {
		this.settingList = settingList;
	}

}
