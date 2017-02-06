/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：RecordListServlet   
 * 类描述：   
 * 创建人：Chen sy 
 * 创建时间：2015年4月7日 下午1:58:29   
 * 修改人：Chen sy   
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

import org.apache.commons.lang3.StringUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lst.base.entity.BaseRequest;
import com.lst.common.CommCode;
import com.lst.dao.UserReocrdMapper;
import com.lst.entity.request.ReocrdReqPara;
import com.lst.entity.response.RecordResPara;
import com.lst.model.UserReocrd;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: RecordListServlet
 * @Description:健身集合
 * @author Chen sy
 * @date 2015年4月7日 下午1:58:29
 * 
 */

public class RecordListServlet extends BaseServlet implements
		IBaseServlet<ReocrdReqPara> {

	private static final long serialVersionUID = 1L;

	private static UserReocrdMapper userRecordMapper = ctx
			.getBean(UserReocrdMapper.class);

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
			// response
			RecordResPara recordResPara = new RecordResPara();

			// request
			ReocrdReqPara reocrdReqPara = getReqPara(req);

			// usreid
			String userid = reocrdReqPara.getUserid();
			// currpage
			String currentPage = reocrdReqPara.getCurrentPage();
			// pageSize
			String pageSize = reocrdReqPara.getPageSize();

			if (StringUtils.isBlank(userid)) {
				recordResPara.setCode(CommCode.M_ERROR);
				recordResPara.setMessage(CommCode.M_BP00301);
			} else if (StringUtils.isBlank(currentPage)) {
				recordResPara.setCode(CommCode.M_ERROR);
				recordResPara.setMessage(CommCode.M_BP00308);
			} else if (StringUtils.isBlank(pageSize)) {
				recordResPara.setCode(CommCode.M_ERROR);
				recordResPara.setMessage(CommCode.M_BP00309);
			} else {
				PageBounds pageBounds = new PageBounds(
						Integer.parseInt(currentPage),
						Integer.parseInt(pageSize));

				List<UserReocrd> reocrdList = userRecordMapper.selectByUserid(
						Integer.parseInt(userid), pageBounds);

				if (reocrdList.size() > 0) {
					recordResPara.setCode(CommCode.M_SUCCESSC);
					recordResPara.setMessage(CommCode.M_Y000001);
					
					recordResPara.setUserRecordList(reocrdList);
				} else {
					recordResPara.setCode(CommCode.M_ERROR);
					recordResPara.setMessage(CommCode.M_BP00307);
				}
			}

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.excludeFieldsWithoutExposeAnnotation().create();

			String jsonResult = gson.toJson(recordResPara);

			resp.setContentType("application/json;charset=utf-8");
			resp.setHeader("pragma", "no-cache");
			resp.setHeader("cache-control", "no-cache");

			PrintWriter out = resp.getWriter();
			out.print(jsonResult);
			out.flush();
			out.close();

			log.info(" RecordListServlet result : " + jsonResult);

		} catch (Exception e) {
			log.error(" RecordListServlet error : " + e.getMessage(), e);
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
	public ReocrdReqPara getReqPara(HttpServletRequest request) {
		ReocrdReqPara reocrdReqPara = null;

		try {
			BaseRequest baseRequest = RequestUtils.getRequestPara(request,
					new ReocrdReqPara());

			reocrdReqPara = (ReocrdReqPara) baseRequest.clone();

			reocrdReqPara.setUserid(request.getParameter("userid"));
			reocrdReqPara.setPageSize(request.getParameter("pageSize"));
			reocrdReqPara.setCurrentPage(request.getParameter("currentPage"));

			log.info(" getReocrdReqPara para userid:"
					+ request.getParameter("userid") + " pageSize:"
					+ request.getParameter("pageSize") + " currentPage:"
					+ request.getParameter("currentPage"));

		} catch (Exception e) {
			log.error(" ReocrdReqPara error : " + e.getMessage(), e);
		}

		return reocrdReqPara;
	}
}
