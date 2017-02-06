/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：LoginServlet   
 * 类描述：   
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
 * @ClassName: LoginServlet
 * @Description:用户登录
 * @author Echo
 * @date 2015年5月11日 下午1:58:29
 * 
 */
public class LoginServlet2 extends BaseServlet implements
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

			String nickname = mstUserReqPara.getNickname();
			// password
			String password = mstUserReqPara.getPassword();
			// op type
			String optype = mstUserReqPara.getOptype();

			/**
			 * 1, normal login 2, qq login 3, weibo login 4, weixin login 5,
			 * others login
			 */
			if (optype.equals("1")) {
				// nickname
				if (StringUtils.isBlank(nickname)) {
					mstUserResPara.setCode(CommCode.M_ERROR);
					mstUserResPara.setMessage(CommCode.M_BP00104);
				} else if (StringUtils.isBlank(password)) {
					mstUserResPara.setCode(CommCode.M_ERROR);
					mstUserResPara.setMessage(CommCode.M_A000013);
				} else {
					MstUser user = new MstUser();

					user.setNickname(nickname);
					user.setType(Integer.valueOf(optype));

					MstUser mstuser = mstUserMapper.selectByNikename(user);

					if (mstuser == null) {
						mstUserResPara.setCode(CommCode.M_ERROR);
						mstUserResPara.setMessage(CommCode.M_A000026);
					} else {
						if (mstuser.getPassword().equals(password)) {
							mstUserResPara.setCode(CommCode.M_Y000000);
							mstUserResPara.setMessage(CommCode.M_Y000001);
							mstUserResPara.setMstuser(mstuser);
						} else {
							mstUserResPara.setCode(CommCode.M_ERROR);
							mstUserResPara.setMessage(CommCode.M_A000027);
						}
					}
				}
			} else {
				String nickName = mstUserReqPara.getNickname();

				String[] nickNameArray = nickName.split(",");
				// [0] openId, [1] nickName [2] gender (0: Man 1:Woman)

				if (StringUtils.isBlank(nickName)) {
					mstUserResPara.setCode(CommCode.M_ERROR);
					mstUserResPara.setMessage(CommCode.M_BP00104);
				} else {
					MstUser user = new MstUser();

					user.setNickname(nickname);
					user.setType(Integer.valueOf(optype));
					
					try {
						if ( nickNameArray[3] != null ){
							user.setPortrait(nickNameArray[3]);
							user.setReserved9(nickNameArray[3]);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					user.setGender(false);
					if (nickNameArray[2].equals("0")) {
						user.setGender(true);
					}

					MstUser mstuser = mstUserMapper.selectByNikename(user);

					if (mstuser == null) {
						user.setHeight(160);
						user.setWeight(BigDecimal.valueOf(120));
						user.setReserved7(true);
						user.setIsdeleted(false);

						int count = mstUserMapper.insert(user);

						// 大于0 注册成功
						if (count > 0) {
							mstUserResPara.setCode(CommCode.M_Y000000);
							mstUserResPara.setMessage(CommCode.M_Y000001);
							mstUserResPara.setRegistered("0");
							
							user = mstUserMapper.selectByNikename(user);
							mstUserResPara.setMstuser(user);
						} else {
							mstUserResPara.setCode(CommCode.M_ERROR);
							mstUserResPara.setMessage(CommCode.M_A000015);
						}
					} else {
						mstUserResPara.setCode(CommCode.M_Y000000);
						mstUserResPara.setMessage(CommCode.M_Y000001);
						mstUserResPara.setRegistered("1");
						
						user = mstUserMapper.selectByNikename(user);
						mstUserResPara.setMstuser(user);
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

			log.info(" LoginServlet result : " + jsonResult);

		} catch (Exception e) {
			log.error(" LoginServlet error : " + e.getMessage(), e);
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

			mstUserReqPara.setNickname(CommFun.Filter(request
					.getParameter("nickname")));
			mstUserReqPara.setPassword(request.getParameter("password"));
			mstUserReqPara.setOptype(request.getParameter("optype"));

			log.info(" getMstUserReqPara para nickname:"
					+ CommFun.Filter(request.getParameter("nickname"))
					+ " password:" + request.getParameter("password"));
		} catch (Exception e) {
			log.error(" MstUserReqPara error : " + e.getMessage(), e);
		}

		return mstUserReqPara;
	}
}
