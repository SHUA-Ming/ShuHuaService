/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：BaseResponse   
 * 类描述：   
 * 创建人：Echo   
 * 创建时间：2014年6月12日 下午4:37:14   
 * 修改人：Echo   
 * 修改时间：2014年6月12日 下午4:37:14   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.base.entity;

import java.io.Serializable;
import java.util.Map;

import com.google.gson.annotations.Expose;

/**
 * @ClassName: BaseResponse
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Echo
 * @date 2014年6月12日 下午4:37:14
 * 
 */
public class BaseResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3498215338531420540L;
	@Expose
	protected String code;
	@Expose
	protected String message;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
