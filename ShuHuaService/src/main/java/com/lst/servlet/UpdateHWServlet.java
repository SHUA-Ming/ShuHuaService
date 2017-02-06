/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：UpdateUserServlet   
 * 类描述： 更新用户信息（身高，体重）
 * 创建人：Echo 
 * 创建时间：2015年5月11日 下午1:58:29   
 * 修改人：Echo   
 * 修改时间：2015年5月11日 下午1:58:29   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lst.base.entity.BaseRequest;
import com.lst.common.CommCode;
import com.lst.common.CommFun;
import com.lst.dao.MstUserMapper;
import com.lst.entity.request.MstUserReqPara;
import com.lst.entity.response.MstUserResPara;
import com.lst.model.MstUser;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: UpdateUserServlet
 * @Description: 更新用户信息（身高，体重）
 * @author Echo
 * @date 2015年5月11日 下午1:58:29
 * 
 */
public class UpdateHWServlet extends BaseServlet implements
		IBaseServlet<MstUserReqPara> {

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
			// response
			MstUserResPara mstUserResPara = new MstUserResPara();

			// request
			// Get the VerifyCode request parameter
			MstUserReqPara mstUserReqPara = getReqPara(req);

			// userid
			String userid = mstUserReqPara.getUserid();
			// height
			String height = mstUserReqPara.getHeight();
			// weight
			String weight = mstUserReqPara.getWeight();
			// optype
			String optype = mstUserReqPara.getOptype();
			// mobileno
			String mobileno = mstUserReqPara.getMobileno();
			// email
			String email = mstUserReqPara.getEmail();

			if (StringUtils.isBlank(userid)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_BP00301);
			} else if (StringUtils.isBlank(height)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_AP00112);
			} else if (StringUtils.isBlank(weight)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_A000011);
			} else {
				MstUser user = new MstUser();

				user.setHeight(Integer.valueOf(height));
				user.setWeight(new BigDecimal(weight));
				user.setId(Integer.valueOf(userid));

				// Normal user
				if (optype.equals("1")) {
					user.setEmail(email);
					user.setMobileno(mobileno);
				}

				int count = mstUserMapper.updateByPrimaryKeySelective(user);

				if (count > 0) {
					mstUserResPara.setCode(CommCode.M_ERROR);
					mstUserResPara.setMessage(CommCode.M_Y000001);
					
					// Get the user by Id
					user = mstUserMapper.selectById(user);
					mstUserResPara.setMstuser(user);
				} else {
					mstUserResPara.setCode(CommCode.M_ERROR);
					mstUserResPara.setMessage(CommCode.M_B000002);
				}
			}

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.excludeFieldsWithoutExposeAnnotation().create();

			String jsonResult = gson.toJson(mstUserResPara);

			resp.setContentType("application/json;charset=utf-8");
			resp.setHeader("pragma", "no-cache");
			resp.setHeader("cache-control", "no-cache");

			PrintWriter out = resp.getWriter();
			out.print(jsonResult);
			out.flush();
			out.close();

			log.info(" UpdateHWServlet result : " + jsonResult);

		} catch (Exception e) {
			log.error(" UpdateHWServlet error : " + e.getMessage(), e);
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
	public MstUserReqPara getReqPara(HttpServletRequest request) {
		MstUserReqPara mstUserReqPara = null;

		try {
			BaseRequest baseRequest = RequestUtils.getRequestPara(request,
					new MstUserReqPara());

			mstUserReqPara = (MstUserReqPara) baseRequest.clone();

			mstUserReqPara.setUserid(request.getParameter("userid"));
			mstUserReqPara.setHeight(request.getParameter("height"));
			mstUserReqPara.setWeight(request.getParameter("weight"));

			mstUserReqPara.setOptype(request.getParameter("optype"));
			mstUserReqPara.setMobileno(request.getParameter("mobileno"));
			mstUserReqPara.setEmail(request.getParameter("email"));

			log.info(" getMstUserReqPara para userid:"
					+ CommFun.Filter(request.getParameter("userid"))
					+ " hight:" + request.getParameter("hight") + " weight:"
					+ request.getParameter("weight"));

		} catch (Exception e) {
			log.error(" getMstUserReqPara error : " + e.getMessage(), e);
		}

		return mstUserReqPara;
	}
}
