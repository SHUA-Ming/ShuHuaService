/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：BaseEntity   
 * 类描述：   
 * 创建人：Echo   
 * 创建时间：2014年6月9日 下午12:09:12   
 * 修改人：Echo   
 * 修改时间：2014年6月9日 下午12:09:12   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.base.entity;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

/**
 * @ClassName: BaseEntity
 * @Description: 
 * @author Echo
 * @date 2014年6月9日 下午12:09:12
 * 
 */
public class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3498215338531420540L;

	@Expose
	protected Integer id;

}
