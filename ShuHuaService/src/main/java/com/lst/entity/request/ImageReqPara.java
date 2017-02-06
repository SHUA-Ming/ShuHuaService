/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：ImageReqPara   
 * 类描述：   
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
 * @ClassName: ImageReqPara
 * @Description: 二进制image请求
 * @author Echo
 * @date 2015年5月12日 下午8:42:19
 * 
 */
public class ImageReqPara extends BaseRequest {
	// File name
	private String fileName;
	// Binary String
	private String ImageBinary;

	public String getImageBinary() {
		return ImageBinary;
	}

	public void setImageBinary(String imageBinary) {
		ImageBinary = imageBinary;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
