/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：LoginServlet   
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
 * @author Chen sy
 * @date 2015年4月7日 下午1:58:29
 * 
 */
public class LoginServlet extends BaseServlet implements
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
			MstUserReqPara mstUserReqPara = getReqPara(req);

			String nickname = mstUserReqPara.getNickname();
			// password
			String password = mstUserReqPara.getPassword();
			System.out.println("hah=" + mstUserReqPara.getOptype());

			if (mstUserReqPara.getOptype().equals(
					MstUserReqPara.LOGIN_NORMAL_TYPE)) {

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
					// Set the query condition
					user.setType(1);

					MstUser mstuser = mstUserMapper.selectByNikename(user);

					log.info("MstUser reslut  ： " + mstuser);

					if (mstuser == null) {
						mstUserResPara.setCode(CommCode.M_ERROR);
						mstUserResPara.setMessage(CommCode.M_A000026);
					} else {
						if (mstuser.getPassword().equals(password)) {
							mstUserResPara.setCode(CommCode.M_SUCCESSC);
							mstUserResPara.setMessage(CommCode.M_Y000001);
							mstUserResPara.setMstuser(mstuser);
						} else {
							mstUserResPara.setCode(CommCode.M_ERROR);
							mstUserResPara.setMessage(CommCode.M_A000027);
						}
					}
				}

			} else {

				String uid = mstUserReqPara.getNickname();
				if (StringUtils.isBlank(uid)) {
					mstUserResPara.setCode(CommCode.M_ERROR);
					mstUserResPara.setMessage(CommCode.M_BP00104);
				} else {
					MstUser user = new MstUser();
					MstUser mstuser = null;
					if (mstUserReqPara.getOptype().equals(
							MstUserReqPara.LOGIN_QQ_TYPE)) {
						user.setQq(uid);
						mstuser = mstUserMapper.selectByQQName(user);
					} else if (mstUserReqPara.getOptype().equals(
							MstUserReqPara.LOGIN_WEIBO_TYPE)) {
						user.setWeibo(uid);
						mstuser = mstUserMapper.selectByWeiBoName(user);
					} else if (mstUserReqPara.getOptype().equals(
							MstUserReqPara.LOGIN_WEIXIN_TYPE)) {
						user.setWeixin(uid);
						mstuser = mstUserMapper.selectByWeiXinName(user);
					}

					log.info("MstUser reslut  ： " + mstuser);

					if (mstuser == null) {
						// user.setGender(gender);
						mstuser = new MstUser();

						if (mstUserReqPara.getOptype().equals(
								MstUserReqPara.LOGIN_QQ_TYPE)) {
							mstuser.setQq(uid);
						} else if (mstUserReqPara.getOptype().equals(
								MstUserReqPara.LOGIN_WEIBO_TYPE)) {
							mstuser.setWeibo(uid);
						} else if (mstUserReqPara.getOptype().equals(
								MstUserReqPara.LOGIN_WEIXIN_TYPE)) {
							mstuser.setWeixin(uid);
						}

						mstuser.setHeight(160);
						mstuser.setIsdeleted(false);
						mstuser.setWeight(BigDecimal.valueOf(120));
						mstuser.setReserved7(Boolean.FALSE);

						int count = mstUserMapper.insert(user);

						// 大于0 注册成功
						if (count > 0) {
							mstUserResPara.setCode(CommCode.M_SUCCESSC);
							mstUserResPara.setMessage(CommCode.M_Y000001);

							user = mstUserMapper.selectByQQName(user);
							mstUserResPara.setMstuser(user);
						} else {
							mstUserResPara.setCode(CommCode.M_ERROR);
							mstUserResPara.setMessage(CommCode.M_A000015);
						}
					} else {
						mstUserResPara.setCode(CommCode.M_SUCCESSC);
						mstUserResPara.setMessage(CommCode.M_Y000001);

						mstUserResPara.setMstuser(mstuser);
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

			String logintype = request.getParameter("optype");
			System.out.println("logintype==" + logintype);

			if (logintype == null) {
				logintype = MstUserReqPara.LOGIN_NORMAL_TYPE;

			} else if (logintype.equals(MstUserReqPara.LOGIN_QQ_TYPE)) {

				String gender = request.getParameter("gender");
				// nickname
				String qq = request.getParameter("qq");

			}
			mstUserReqPara.setOptype(logintype);

			log.info(" getMstUserReqPara para nickname:"
					+ CommFun.Filter(request.getParameter("nickname"))
					+ " password:" + request.getParameter("password"));

		} catch (Exception e) {
			log.error(" MstUserReqPara error : " + e.getMessage(), e);
		}

		return mstUserReqPara;
	}
}
