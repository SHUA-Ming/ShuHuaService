/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：RegistrationServlet   
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lst.base.entity.BaseRequest;
import com.lst.common.CommCode;
import com.lst.dao.MstMedalMapper;
import com.lst.entity.request.ReocrdReqPara;
import com.lst.entity.response.MedalResPara;
import com.lst.model.MstMedal;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: MedalListServlet
 * @Description:查询勋章
 * @author Chen sy
 * @date 2015年4月7日 下午1:58:29
 * 
 */
public class MedalListServlet extends BaseServlet implements
		IBaseServlet<ReocrdReqPara> {

	private static final long serialVersionUID = 1L;

	private static MstMedalMapper mstMedalMapper = ctx
			.getBean(MstMedalMapper.class);

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
			MedalResPara medalResPara = new MedalResPara();

			// request
			ReocrdReqPara reocrdReqPara = getReqPara(req);

			// usreid
			String userId = reocrdReqPara.getUserid();

			if (StringUtils.isBlank(userId)) {
				medalResPara.setCode(CommCode.M_Y000000);
				medalResPara.setMessage(CommCode.M_BP00301);
			} else {
				List<MstMedal> mstMedalList = mstMedalMapper
						.selectByUserMedal(Integer.valueOf(userId));
				
				if (mstMedalList.size() > 0) {
					medalResPara.setCode(CommCode.M_Y000000);
					medalResPara.setMessage(CommCode.M_Y000001);
					
					medalResPara.setMstMedalList(mstMedalList);
				} else {
					medalResPara.setCode(CommCode.M_Y000000);
					medalResPara.setMessage(CommCode.M_BP00310);
				}
			}
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.excludeFieldsWithoutExposeAnnotation().create();

			String jsonResult = gson.toJson(medalResPara);

			resp.setContentType("application/json;charset=utf-8");
			resp.setHeader("pragma", "no-cache");
			resp.setHeader("cache-control", "no-cache");

			PrintWriter out = resp.getWriter();
			out.print(jsonResult);
			out.flush();
			out.close();

			log.info(" MedalListServlet result : " + jsonResult);

		} catch (Exception e) {
			log.error(" MedalListServlet error : " + e.getMessage(), e);
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

			log.info(" getReocrdReqPara para userid:"
					+ request.getParameter("userid"));

		} catch (Exception e) {
			log.error(" ReocrdReqPara error : " + e.getMessage(), e);
		}

		return reocrdReqPara;
	}
}
