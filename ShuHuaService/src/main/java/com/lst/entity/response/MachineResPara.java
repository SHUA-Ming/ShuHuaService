/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：MachineResPara   
 * 类描述：   
 * 创建人：Chen sy 
 * 创建时间：2015年4月8日 上午9:34:45   
 * 修改人：Chen sy   
 * 修改时间：2015年4月8日 上午9:34:45   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.entity.response;

import com.google.gson.annotations.Expose;
import com.lst.base.entity.BaseResponse;
import com.lst.model.MstMachine;
import com.lst.model.MstUser;

/**
 * @ClassName: MachineResPara
 * @Description: 设备信息的返回参数
 * @author Chen sy
 * @date 2015年4月8日 上午9:34:45
 * 
 */
public class MachineResPara extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	@Expose
	private MstMachine mstMachine;
	@Expose
	private MstUser user;

	public MstUser getUser() {
		return user;
	}

	public void setUser(MstUser user) {
		this.user = user;
	}

	/**
	 * @return the mstMachine
	 */
	public MstMachine getMstMachine() {
		return mstMachine;
	}

	/**
	 * @param mstMachine
	 *            the mstMachine to set
	 */
	public void setMstMachine(MstMachine mstMachine) {
		this.mstMachine = mstMachine;
	}

}
