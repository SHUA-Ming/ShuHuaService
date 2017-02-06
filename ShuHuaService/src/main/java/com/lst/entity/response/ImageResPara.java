/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：返回图片上传消息   
 * 类描述：   
 * 创建人： aluo
 * 创建时间：2015年5月4日   
 * 修改人：aluo   
 * 修改时间：2015年5月4日  
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.entity.response;

import com.google.gson.annotations.Expose;
import com.lst.base.entity.BaseResponse;

/**
 * @ClassName: ImageResPara
 * @Description: 返回图片上传消息
 * @author aluo
 * @date 2015年5月4日  
 * 
 */
public class ImageResPara extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	@Expose
	private String fileUrl;

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
}
