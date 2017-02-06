/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：MaxHistoryServlet   
 * 类描述：获取最大历史记录
 * 创建人： Echo
 * 创建时间：2015年5月19日   
 * 修改人：Echo   
 * 修改时间：2015年5月19日  
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lst.base.entity.BaseRequest;
import com.lst.common.CommCode;
import com.lst.dao.UserReocrdMapper;
import com.lst.entity.request.MaxRecordReqPara;
import com.lst.entity.response.MaxRecordResPara;
import com.lst.model.MaxReocrd;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: MaxHistoryServlet
 * @Description:获取最大历史记录
 * @author Echo
 * @date 2015年5月19日
 * 
 */
public class MaxHistoryServlet extends BaseServlet implements
		IBaseServlet<MaxRecordReqPara> {

	private static final long serialVersionUID = 1L;

	private static UserReocrdMapper urm = ctx.getBean(UserReocrdMapper.class);

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
			MaxRecordResPara maxRecordResPara = new MaxRecordResPara();
			// request
			MaxRecordReqPara maxRecordReqPara = getReqPara(request);

			// usreid
			String userid = maxRecordReqPara.getUserid();
			// startdate
			String startdate = maxRecordReqPara.getStartdate();
			// enddate
			String enddate = maxRecordReqPara.getEnddate();

			if (StringUtils.isBlank(userid)) {
				maxRecordResPara.setCode(CommCode.M_ERROR);
				maxRecordResPara.setMessage(CommCode.M_BP00301);
			} else if (StringUtils.isBlank(startdate)) {
				maxRecordResPara.setCode(CommCode.M_ERROR);
				maxRecordResPara.setMessage(CommCode.M_BP00303);
			} else if (StringUtils.isBlank(enddate)) {
				maxRecordResPara.setCode(CommCode.M_ERROR);
				maxRecordResPara.setMessage(CommCode.M_BP00304);
			} else {
				HashMap<String, Object> map = new HashMap<String, Object>();

				map.put("userid", userid);
				map.put("startdate", startdate);
				map.put("enddate", enddate);

				MaxReocrd maxReocrd = urm.getMaxRecordByUserid(map);

				if (maxReocrd !=null) {
					maxRecordResPara.setCode(CommCode.M_SUCCESSC);
					maxRecordResPara.setMessage(CommCode.M_Y000001);

					maxRecordResPara.setMaxReocrd(maxReocrd);
				} else {
					maxRecordResPara.setCode(CommCode.M_ERROR);
					maxRecordResPara.setMessage(CommCode.M_BP00307);
				}
			}

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.excludeFieldsWithoutExposeAnnotation().create();

			String jsonResult = gson.toJson(maxRecordResPara);

			response.setContentType("application/json;charset=utf-8");
			response.setHeader("pragma", "no-cache");
			response.setHeader("cache-control", "no-cache");

			PrintWriter out = response.getWriter();
			out.print(jsonResult);
			out.flush();
			out.close();

			log.info(" StatisticsServlet result : " + jsonResult);

		} catch (Exception e) {
			log.error(" StatisticsServlet error : " + e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc) <p>Title: getReqPara</p> <p>Description: </p>
	 * 
	 * @param request
	 * 
	 * @return
	 * 
	 * @see
	 * com.lst.servlet.IBaseServlet#getReqPara(javax.servlet.http.HttpServletRequest
	 * )
	 */
	@Override
	public MaxRecordReqPara getReqPara(HttpServletRequest request) {
		MaxRecordReqPara maxRecordReqPara = null;

		try {
			BaseRequest baseRequest = RequestUtils.getRequestPara(request,
					new MaxRecordReqPara());

			maxRecordReqPara = (MaxRecordReqPara) baseRequest.clone();

			maxRecordReqPara.setUserid(request.getParameter("userid"));
			maxRecordReqPara.setStartdate(request.getParameter("startdate"));
			maxRecordReqPara.setEnddate(request.getParameter("enddate"));

			log.info(" MaxRecordReqPara para userid:"
					+ request.getParameter("userid") + " startdate:"
					+ request.getParameter("startdate") + " enddate:"
					+ request.getParameter("enddate"));

		} catch (Exception e) {
			log.error(" MxRecordReqPara error : " + e.getMessage(), e);
		}

		return maxRecordReqPara;
	}
}
