/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：IBaseServlet   
 * 类描述：   
 * 创建人：Chen sy 
 * 创建时间：2015年4月11日 下午1:24:13   
 * 修改人：Chen sy   
 * 修改时间：2015年4月11日 下午1:24:13   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: IBaseServlet
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Chen sy
 * @date 2015年4月11日 下午1:24:13
 * 
 */
public interface IBaseServlet<T> {
	T getReqPara(HttpServletRequest request);
}
