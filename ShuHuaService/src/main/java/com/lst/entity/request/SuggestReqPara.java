/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：SuggestReqPara   
 * 类描述：   
 * 创建人：Chen sy 
 * 创建时间：2015年4月8日 上午10:17:02   
 * 修改人：Chen sy   
 * 修改时间：2015年4月8日 上午10:17:02   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.entity.request;

import com.lst.base.entity.BaseRequest;

/**
 * @ClassName: SuggestReqPara
 * @Description: 反馈请求参数
 * @author Chen sy
 * @date 2015年4月8日 上午10:17:02
 * 
 */
public class SuggestReqPara extends BaseRequest {
	// 昵称
	private String nickname;
	// 标题
	private String title;
	// 内容
	private String content;

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}