/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：MstUserResPara   
 * 类描述：   
 * 创建人：Chen sy 
 * 创建时间：2015年4月7日 下午2:19:31   
 * 修改人：Chen sy   
 * 修改时间：2015年4月7日 下午2:19:31   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.entity.response;

import com.google.gson.annotations.Expose;
import com.lst.base.entity.BaseResponse;
import com.lst.model.LoginBody;
import com.lst.model.MstUser;

/**
 * @ClassName: MstUserResPara
 * @Description: 返回的结果
 * @author Chen sy
 * @date 2015年4月7日 下午2:19:31
 * 
 */
public class LoginMstUserResPara extends BaseResponse {

	private static final long serialVersionUID = 1L;
	@Expose
	private LoginBody loginBody;
	public LoginBody getLoginBody() {
		return loginBody;
	}
	public void setLoginBody(LoginBody loginBody) {
		this.loginBody = loginBody;
	}
}
