/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：MachineReqPara   
 * 类描述：   
 * 创建人：Chen sy 
 * 创建时间：2015年4月8日 上午9:32:49   
 * 修改人：Chen sy   
 * 修改时间：2015年4月8日 上午9:32:49   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.entity.request;

import com.lst.base.entity.BaseRequest;

/**
 * @ClassName: MachineReqPara
 * @Description: 设备信息的请求参数
 * @author Chen sy
 * @date 2015年4月8日 上午9:32:49
 * 
 */
public class MachineReqPara extends BaseRequest {
	// 设备型号
	private String model;

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

}
