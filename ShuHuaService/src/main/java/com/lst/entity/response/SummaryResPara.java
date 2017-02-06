/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：SummaryResPara   
 * 类描述： 统计分析 
 * 创建人：Echo 
 * 创建时间：2015年5月18日 上午11:55:45   
 * 修改人：Echo   
 * 修改时间：2015年5月18日 上午11:55:45   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.entity.response;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.lst.base.entity.BaseResponse;
import com.lst.model.SummaryReocrd;

/**
 * @ClassName: SummaryResPara
 * @Description: 统计分析
 * @author Echo
 * @date 2015年5月18日 上午11:55:45
 * 
 */
public class SummaryResPara extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	@Expose
	private List<SummaryReocrd> summaryReocrd;
	
	public List<SummaryReocrd> getSummaryReocrd() {
		return summaryReocrd;
	}
	
	public void setSummaryReocrd(List<SummaryReocrd> summaryReocrd) {
		this.summaryReocrd = summaryReocrd;
	}
}
