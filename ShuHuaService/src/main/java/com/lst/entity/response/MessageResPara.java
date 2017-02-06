/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：返回系统消息   
 * 类描述：   
 * 创建人： Echo
 * 创建时间：2015年5月4日   
 * 修改人：Echo   
 * 修改时间：2015年5月4日  
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.entity.response;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.lst.base.entity.BaseResponse;
import com.lst.model.MessageInfo;

/**
 * @ClassName: ImageResPara
 * @Description: 返回系统消息
 * @author Echo
 * @date 2015年5月4日  
 * 
 */
public class MessageResPara extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	@Expose
	List<MessageInfo> messageList;

	public List<MessageInfo> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<MessageInfo> messageList) {
		this.messageList = messageList;
	}
}
