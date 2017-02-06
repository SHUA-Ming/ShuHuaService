/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：MaxRecordResPara   
 * 类描述： 最大纪录 
 * 创建人：Echo 
 * 创建时间：2015年5月18日 上午11:55:45   
 * 修改人：Echo   
 * 修改时间：2015年5月18日 上午11:55:45   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.entity.response;

import com.google.gson.annotations.Expose;
import com.lst.base.entity.BaseResponse;
import com.lst.model.MaxReocrd;

/**
 * @ClassName: MaxRecordResPara
 * @Description: 最大纪录
 * @author Echo
 * @date 2015年5月18日 上午11:55:45
 * 
 */
public class MaxRecordResPara extends BaseResponse {

	private static final long serialVersionUID = 1L;

	@Expose
	private MaxReocrd maxReocrd;

	public MaxReocrd getMaxReocrd() {
		return maxReocrd;
	}

	public void setMaxReocrd(MaxReocrd maxReocrd) {
		this.maxReocrd = maxReocrd;
	}
}
