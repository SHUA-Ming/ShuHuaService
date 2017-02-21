/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：ForgetPwdServlet
 * 类描述：   
 * 创建人：aluo
 * 创建时间：2015年4月7日 下午1:58:29   
 * 修改人：aluo 
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
import com.lst.dao.MstUserMapper;
import com.lst.entity.request.MstUserReqPara;
import com.lst.entity.response.MstUserResPara;
import com.lst.model.MstUser;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: ForgetPwdServlet
 * @Description: 忘记密码
 * @author aluo
 * @date 2017年2月17日
 * 
 */

public class ForgetPwdServlet extends BaseServlet implements
		IBaseServlet<MstUserReqPara> {

	private static final long serialVersionUID = 1L;

	private static MstUserMapper mstUserMapper = ctx
			.getBean(MstUserMapper.class);


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			
			MstUserResPara mstUserResPara = new MstUserResPara();
			
			String mobileNo = req.getParameter("mobileNo");
			String newPassWord = req.getParameter("newPassWord");  //新密码
			String confirmPassWord = req.getParameter("confirmPassWord");
			
			//判断
			if(StringUtils.isBlank(mobileNo)){
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_BP00105);
			}else if(StringUtils.isBlank(confirmPassWord)){
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_A000016);
			}else if(!newPassWord.equals(confirmPassWord)){
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_A000017);
			}else{
				MstUser mstUser = new MstUser();
				mstUser.setMobileno(mobileNo);
				mstUser = mstUserMapper.selectByMobileno(mstUser);
				
				if(mstUser == null){
					mstUserResPara.setCode(CommCode.M_ERROR);
					mstUserResPara.setMessage(CommCode.M_A000026);
				}else{
					mstUser.setPassword(mobileNo);
					mstUser.setPassword(newPassWord);
					int con = mstUserMapper.updateResetPwd(mstUser);
					if(con == 1){
						mstUserResPara.setCode(CommCode.M_SUCCESSC);
						mstUserResPara.setMessage(CommCode.M_Y000001);
					}else{
						mstUserResPara.setCode(CommCode.M_ERROR);
						mstUserResPara.setMessage(CommCode.M_A000015);
					}
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

			log.info(" ForgetPwdServlet result : " + jsonResult);

		} catch (Exception e) {
			log.error(" ForgetPwdServlet error : " + e.getMessage(), e);
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

			mstUserReqPara.setMobileno(request.getParameter("mobileno"));
			mstUserReqPara.setNewpwd(request.getParameter("newpwd"));

		} catch (Exception e) {
			log.error(" MstUserReqPara error : " + e.getMessage(), e);
		}

		return mstUserReqPara;
	}
}
