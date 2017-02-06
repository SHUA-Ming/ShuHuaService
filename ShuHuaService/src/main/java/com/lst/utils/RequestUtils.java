/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：RequestUtils   
 * 类描述：   
 * 创建人：Echo   
 * 创建时间：2014年6月11日 下午5:51:32   
 * 修改人：Echo   
 * 修改时间：2014年6月11日 下午5:51:32   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.utils;

import javax.servlet.http.HttpServletRequest;

import com.lst.base.entity.BaseRequest;

/**
 * @ClassName: RequestUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Echo
 * @date 2014年6月11日 下午5:51:32
 * 
 */
public class RequestUtils<T extends BaseRequest> {

	public static <T> BaseRequest getRequestPara(HttpServletRequest request, T t) {
		BaseRequest requestPara = (BaseRequest) t;

		requestPara.setChannel(request.getParameter("channel"));
		requestPara.setIccidno(request.getParameter("iccidno"));
		requestPara.setImeino(request.getParameter("imeino"));
		requestPara.setImsino(request.getParameter("imsino"));
		requestPara.setMdnno(request.getParameter("mdnno"));
		requestPara.setSignmsg(request.getParameter("signmsg"));
		requestPara.setSigntype(request.getParameter("signtype"));
		requestPara.setTerminalno(request.getParameter("terminalno"));
		requestPara.setTimestamp(request.getParameter("timestamp"));
		requestPara.setUsertype(request.getParameter("usertype"));
		requestPara.setVersion(request.getParameter("version"));

		return requestPara;
	}

	private T t;

	public RequestUtils(T oT) {
		this.t = oT;
	}

	public BaseRequest getRequestPara(HttpServletRequest request) {
		BaseRequest requestPara = this.t;

		requestPara.setChannel(request.getParameter("channel"));
		requestPara.setIccidno(request.getParameter("iccidno"));
		requestPara.setImeino(request.getParameter("imeino"));
		requestPara.setImsino(request.getParameter("imsino"));
		requestPara.setMdnno(request.getParameter("mdnno"));
		requestPara.setSignmsg(request.getParameter("signmsg"));
		requestPara.setSigntype(request.getParameter("signtype"));
		requestPara.setTerminalno(request.getParameter("terminalno"));
		requestPara.setTimestamp(request.getParameter("timestamp"));
		requestPara.setUsertype(request.getParameter("usertype"));
		requestPara.setVersion(request.getParameter("version"));

		return requestPara;
	}
}
