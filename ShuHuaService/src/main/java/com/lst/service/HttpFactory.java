/**
 * 
 * 项目名称：ShuHuaService
 * 类名称：HttpFactory
 * 类描述：
 * 创建人：Echo
 * 创建时间：2015年3月12日 下午5:14:34
 * 修改人：Echo
 * 修改时间：2015年3月12日 下午5:14:34
 * 修改备注：
 * @version 
 * 
 */
package com.lst.service;

/**
 * @ClassName: HttpFactory
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Echo
 * @date 2015年3月12日 下午5:14:34
 * 
 */
public class HttpFactory extends AbstractFactory {

	@Override
	public IHttpService create() {
		return new HttpCtrl();
	}

}
