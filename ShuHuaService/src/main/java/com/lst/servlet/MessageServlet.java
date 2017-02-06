/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：MessageServlet   
 * 类描述： 系统消息列表
 * 创建人： Echo
 * 创建时间：2015年5月12日   
 * 修改人：Echo   
 * 修改时间：2015年5月12日  
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lst.base.entity.BaseRequest;
import com.lst.common.CommCode;
import com.lst.dao.MessageInfoMapper;
import com.lst.entity.request.MessageReqPara;
import com.lst.entity.response.MessageResPara;
import com.lst.model.MessageInfo;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: MessageServlet.java
 * @Description:系统消息列表
 * @author Echo
 * @date 2015年5月12日
 * 
 */
public class MessageServlet extends BaseServlet implements
		IBaseServlet<MessageReqPara> {

	private static final long serialVersionUID = 1L;

	private static MessageInfoMapper mIMapper = ctx
			.getBean(MessageInfoMapper.class);

	/*
	 * (非 Javadoc) <p>Title: doGet</p> <p>Description: </p>
	 * 
	 * @param req
	 * 
	 * @param resp
	 * 
	 * @throws ServletException
	 * 
	 * @throws IOException
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/*
	 * (非 Javadoc) <p>Title: doPost</p> <p>Description: </p>
	 * 
	 * @param req
	 * 
	 * @param resp
	 * 
	 * @throws ServletException
	 * 
	 * @throws IOException
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// response
			MessageResPara mesResPara = new MessageResPara();
			// request
			MessageReqPara messageReqPara = getReqPara(request);

			// userid
			String userId = messageReqPara.getUserid();
			// pageSize
			String pageSize = messageReqPara.getPageSize();
			// curPage
			String curPage = messageReqPara.getCurPage();
			
			PageBounds pageBounds = new PageBounds(Integer.valueOf(curPage),Integer.valueOf(pageSize));

			List<MessageInfo> messageList = mIMapper.findAll(
					Integer.valueOf(userId), pageBounds);

			// 存在message数据
			if (messageList.size() > 0) {
				mesResPara.setCode(CommCode.M_SUCCESSC);
				mesResPara.setMessage(CommCode.M_Y000001);

				mesResPara.setMessageList(messageList);
			} else {
				mesResPara.setCode(CommCode.M_ERROR);
				mesResPara.setMessage(CommCode.M_A000015);
			}

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.excludeFieldsWithoutExposeAnnotation().create();

			String jsonResult = gson.toJson(mesResPara);

			response.setContentType("application/json;charset=utf-8");
			response.setHeader("pragma", "no-cache");
			response.setHeader("cache-control", "no-cache");

			PrintWriter out = response.getWriter();
			out.print(jsonResult);
			out.flush();
			out.close();

			log.info(" UploadBinaryServlet result : " + jsonResult);

		} catch (Exception e) {
			log.error(" UploadBinaryServlet error : " + e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc) <p>Title: getReqPara</p> <p>Description: </p>
	 * 
	 * @param request
	 * 
	 * @sreturn
	 * 
	 * @see
	 * com.lst.servlet.IBaseServlet#getReqPara(javax.servlet.http.HttpServletRequest
	 * )
	 */
	@Override
	public MessageReqPara getReqPara(HttpServletRequest request) {
		MessageReqPara messageReqPara = null;

		try {
			BaseRequest baseRequest = RequestUtils.getRequestPara(request,
					new MessageReqPara());

			messageReqPara = (MessageReqPara) baseRequest.clone();

			messageReqPara.setUserid(request.getParameter("userid"));
			messageReqPara.setPageSize(request.getParameter("pageSize"));
			messageReqPara.setCurPage(request.getParameter("curPage"));
		} catch (Exception e) {
			log.error("getReqPara error : " + e.getMessage(), e);
		}

		return messageReqPara;
	}
}
