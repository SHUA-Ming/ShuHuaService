/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：MessageReqPara   
 * 类描述： 系统消息实体
 * 创建人：Echo   
 * 创建时间：2015年5月12日 下午8:42:19   
 * 修改人：Echo   
 * 修改时间：2015年5月12日 下午8:42:19   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.entity.request;

import com.lst.base.entity.BaseRequest;

/**
 * @ClassName: MessageReqPara
 * @Description: 系统消息实体
 * @author Echo
 * @date 2015年5月12日 下午8:42:19
 * 
 */
public class MessageReqPara extends BaseRequest {

	private String pageSize;

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	private String curPage;

}
