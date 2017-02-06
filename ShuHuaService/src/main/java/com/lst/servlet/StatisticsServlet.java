/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：StatisticsServlet   
 * 类描述： 统计分析
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lst.base.entity.BaseRequest;
import com.lst.common.CommCode;
import com.lst.dao.UserReocrdMapper;
import com.lst.entity.request.SummaryReqPara;
import com.lst.entity.response.SummaryResPara;
import com.lst.model.SummaryReocrd;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: StatisticsServlet
 * @Description:统计分析
 * @author Echo
 * @date 2015年5月12日
 * 
 */
public class StatisticsServlet extends BaseServlet implements
		IBaseServlet<SummaryReqPara> {

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
			SummaryResPara summaryResPara = new SummaryResPara();
			// request
			SummaryReqPara smmaryReqPara = getReqPara(request);

			// usreid
			String userid = smmaryReqPara.getUserid();
			// startdate
			String startdate = smmaryReqPara.getStartdate();
			// enddate
			String enddate = smmaryReqPara.getEnddate();

			if (StringUtils.isBlank(userid)) {
				summaryResPara.setCode(CommCode.M_ERROR);
				summaryResPara.setMessage(CommCode.M_BP00301);
			} else if (StringUtils.isBlank(startdate)) {
				summaryResPara.setCode(CommCode.M_ERROR);
				summaryResPara.setMessage(CommCode.M_BP00303);
			} else if (StringUtils.isBlank(enddate)) {
				summaryResPara.setCode(CommCode.M_ERROR);
				summaryResPara.setMessage(CommCode.M_BP00304);
			} else {
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				map.put("userid", userid);
				map.put("startdate", startdate);
				map.put("enddate", enddate);

				List<SummaryReocrd> srList = urm.getSummaryByUserid(map);

				if (srList.size() > 0) {
					summaryResPara.setCode(CommCode.M_SUCCESSC);
					summaryResPara.setMessage(CommCode.M_Y000001);

					summaryResPara.setSummaryReocrd(srList);
				} else {
					summaryResPara.setCode(CommCode.M_ERROR);
					summaryResPara.setMessage(CommCode.M_BP00307);
				}
			}

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.excludeFieldsWithoutExposeAnnotation().create();

			String jsonResult = gson.toJson(summaryResPara);

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
	public SummaryReqPara getReqPara(HttpServletRequest request) {
		SummaryReqPara summaryReqPara = null;

		try {
			BaseRequest baseRequest = RequestUtils.getRequestPara(request,
					new SummaryReqPara());

			summaryReqPara = (SummaryReqPara) baseRequest.clone();

			summaryReqPara.setUserid(request.getParameter("userid"));
			summaryReqPara.setStartdate(request.getParameter("startdate"));
			summaryReqPara.setEnddate(request.getParameter("enddate"));

			log.info(" SummaryReqPara para userid:"
					+ request.getParameter("userid") + " startdate:"
					+ request.getParameter("startdate") + " enddate:"
					+ request.getParameter("enddate"));

		} catch (Exception e) {
			log.error(" SummaryReqPara error : " + e.getMessage(), e);
		}

		return summaryReqPara;
	}
}
