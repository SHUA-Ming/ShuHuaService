/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：GetVerifyCodeServlet   
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
import com.lst.base.entity.BaseResponse;
import com.lst.common.CommCode;
import com.lst.common.CommFun;
import com.lst.dao.MstUserMapper;
import com.lst.entity.request.MstUserReqPara;
import com.lst.model.MstUser;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: GetVerifyCodeServlet
 * @Description:获取验证码
 * @author aluo
 * @date 2015年4月7日 下午1:58:29
 * 
 */

public class GetVerifyCodeServlet extends BaseServlet implements
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
			BaseResponse mstUserResPara = new BaseResponse();

			// request
			String mobileNo = req.getParameter("mobileNo");
			String sign = req.getParameter("sign");
			
			//根据mobileno查询是否有该用户
			MstUser user = new MstUser();
			user.setMobileno(mobileNo);
			MstUser mstuser = mstUserMapper.selectByMobileno(user);
			int key = 0;  //标识
			
			if(sign.equals(CommCode.M_AP00100)){  //如果是注册
				if(mstuser != null){  //如果已经存在用户
					mstUserResPara.setCode(CommCode.M_ERROR);
					mstUserResPara.setMessage(CommCode.M_BP00106);
				}else{  //发送验证码
					key = 1;
				}
			}else if(sign.equals(CommCode.M_B000000)){  //如果是重置密码
				if(mstuser ==null){  //用户不存在
					mstUserResPara.setCode(CommCode.M_ERROR);
					mstUserResPara.setMessage(CommCode.M_A000026);
				}else{
					key = 1;
				}
			}
			
			//发送验证码
			if(key == 1){
				// Make the veryfy code
				String verifycode = String.valueOf(CommFun.makeRandomWithRange(CommCode.MIN_NO,CommCode.MAX_NO));
				String content = "您的验证码是：" + verifycode + "。请不要把验证码泄露给其他人。";
				boolean bclIsSend = CommFun.sendMessage("0000", mobileNo,content);
				if (bclIsSend) {
					mstUserResPara.setCode(CommCode.M_SUCCESSC);
					mstUserResPara.setMessage(verifycode);
				}else{
					mstUserResPara.setCode(CommCode.M_ERROR);
					mstUserResPara.setMessage(CommCode.M_A000015);
				}
			}

			//返回前端
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").excludeFieldsWithoutExposeAnnotation().create();
			String jsonResult = gson.toJson(mstUserResPara);
			resp.setContentType("application/json;charset=utf-8");
			resp.setHeader("pragma", "no-cache");
			resp.setHeader("cache-control", "no-cache");
			PrintWriter out = resp.getWriter();
			out.print(jsonResult);
			out.flush();
			out.close();
			log.info(" GetVerifyCodeServlet result : " + jsonResult);
		} catch (Exception e) {
			log.error(" GetVerifyCodeServlet error : " + e.getMessage(), e);
		}
	}

	@Override
	public MstUserReqPara getReqPara(HttpServletRequest request) {
		return null;
	}
}
