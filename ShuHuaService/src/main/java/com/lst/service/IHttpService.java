/**
 * 
 * 项目名称：ShuHuaService
 * 类名称：IHttpService
 * 类描述：
 * 创建人：Echo
 * 创建时间：2015年3月12日 下午5:11:08
 * 修改人：Echo
 * 修改时间：2015年3月12日 下午5:11:08
 * 修改备注：
 * @version 
 * 
 */
package com.lst.service;

import org.apache.log4j.Logger;

/**
 * @ClassName: IHttpService
 * @Description: http服务
 * @author Echo
 * @date 2015年3月12日 下午5:11:08
 * 
 */
public interface IHttpService {

	String httpResult(String url);

	Logger log = Logger.getLogger("log");

}
