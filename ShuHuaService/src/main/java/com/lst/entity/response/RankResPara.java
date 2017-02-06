
/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：RankResPara   
 * 类描述：   
 * 创建人：Aluo 
 * 创建时间：2015年4月8日 上午9:34:45   
 * 修改人：Aluo
 * 修改时间：2015年4月8日 上午9:34:45   
 * 修改备注：   
 * @version    
 *    
 */

package com.lst.entity.response;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.lst.base.entity.BaseResponse;
import com.lst.model.MstUser;

/**
 * 
* @ClassName: RankResPara
* @Description: RankResPara
* @author Aluo
* @date 2015年4月21日 下午7:41:03
*
 */
public class RankResPara extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	@Expose
	private List<MstUser> userList;
	
	public List<MstUser> getUserList() {
		return userList;
	}
	
	public void setUserList(List<MstUser> userList) {
		this.userList = userList;
	}
}
