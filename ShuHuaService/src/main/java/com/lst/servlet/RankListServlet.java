/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：RankListServlet   
 * 类描述：   
 * 创建人：Aluo
 * 创建时间：2015年4月7日 下午1:58:29   
 * 修改人：Aluo   
 * 修改时间：2015年4月7日 下午1:58:29   
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lst.common.CommCode;
import com.lst.dao.MstUserMapper;
import com.lst.entity.request.ReocrdReqPara;
import com.lst.entity.response.RankResPara;
import com.lst.model.MstUser;

/**
 * @ClassName: RankListServlet
 * @Description:Rank List
 * @author Aluo
 * @date 2015年4月7日 下午1:58:29
 * 
 */
public class RankListServlet extends BaseServlet implements
		IBaseServlet<ReocrdReqPara> {

	private static final long serialVersionUID = 1L;

	private static MstUserMapper mstUserMapper = ctx
			.getBean(MstUserMapper.class);

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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {

			RankResPara rankResPara = new RankResPara();
			List<MstUser> reocrdList = mstUserMapper.selectTop10();

			rankResPara.setCode(CommCode.M_SUCCESSC);
			rankResPara.setMessage(CommCode.M_Y000001);
			rankResPara.setUserList(reocrdList);

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.excludeFieldsWithoutExposeAnnotation().create();

			String jsonResult = gson.toJson(rankResPara);

			resp.setContentType("application/json;charset=utf-8");
			resp.setHeader("pragma", "no-cache");
			resp.setHeader("cache-control", "no-cache");

			PrintWriter out = resp.getWriter();
			out.print(jsonResult);
			out.flush();
			out.close();

			log.info(" RankListServlet result : " + jsonResult);

		} catch (Exception e) {
			log.error(" RankListServlet error : " + e.getMessage(), e);
		}
	}

	@Override
	/**
	 * 
	 */
	public ReocrdReqPara getReqPara(HttpServletRequest request) {
		return null;
	}
}
