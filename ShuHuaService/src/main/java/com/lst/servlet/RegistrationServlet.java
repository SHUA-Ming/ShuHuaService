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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
import com.lst.utils.CommFunUtils;
import com.lst.utils.DateUtil;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: RegistrationServlet
 * @Description:用户注册
 * @author Chen sy
 * @date 2015年4月7日 下午1:58:29
 * 
 */

public class RegistrationServlet extends BaseServlet implements
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
			// Get the Registration  request parameter
			MstUserReqPara mstUserReqPara = getReqPara(req);

			// email
			String email = mstUserReqPara.getEmail();
			// birthday
			String birthday = mstUserReqPara.getBirthday();
			// gender
			String gender = mstUserReqPara.getGender();
			// nickname
			String nickname = mstUserReqPara.getNickname();
			// height
			String heigth = mstUserReqPara.getHeight();
			// mobileno
			String mobileno = mstUserReqPara.getMobileno();
			// name
			String name = mstUserReqPara.getName();
			// password
			String password = mstUserReqPara.getPassword();
			// weight
			String weight = mstUserReqPara.getWeight();
			// imagebinary
			String imagebinary = mstUserReqPara.getImagebinary();

			if (StringUtils.isBlank(email)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_AP00101);
			} else if (StringUtils.isBlank(birthday)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_A000010);
			} else if (StringUtils.isBlank(gender)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_BP00103);
			} else if (StringUtils.isBlank(nickname)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_BP00104);
			} else if (StringUtils.isBlank(heigth)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_AP00112);
			} else if (StringUtils.isBlank(name)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_AP00102);
			} else if (StringUtils.isBlank(mobileno)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_BP00105);
			} else if (StringUtils.isBlank(password)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_A000013);
			} else if (StringUtils.isBlank(weight)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_A000011);
			} else {
				// check mobileno
				MstUser checkUser = new MstUser();
				checkUser.setMobileno(mobileno);

				List<MstUser> mstuser = mstUserMapper
						.selectByMobileno(checkUser);

				if (mstuser == null || mstuser.size() == 0) {
					MstUser user = new MstUser();

					user.setNickname(nickname);
					user.setBirthday(DateUtil.parse(birthday,
							DateUtil.ALL_DATE_HORIZONTAL));
					user.setEmail(email);

					if (gender.equals("0")) {
						user.setGender(false);
					} else if (gender.equals("1")) {
						user.setGender(true);
					}

					user.setHeight(Integer.parseInt(heigth));
					user.setWeight(BigDecimal.valueOf(Integer.parseInt(weight)));
					user.setPassword(password);
					user.setName(name);
					user.setMobileno(mobileno);
					user.setCreatedate(new Date());

					if (mstUserReqPara.getOptype().equals(
							MstUserReqPara.REG_NORMAL_TYPE)) {
						if (StringUtils.isNotBlank(imagebinary)) {
							String filePath = path
									+ DateUtil
											.getCurrentTime(DateUtil.ALL_DATETIME_STRING_QUEUE);

							String urlPath = "/UploadImages/"
									+ DateUtil
											.getCurrentTime(DateUtil.ALL_DATETIME_STRING_QUEUE);

							user.setPortrait(urlPath + ".jpg");

							CommFunUtils.base64StringToImage4One(imagebinary,
									filePath);
						}

						MstUser mstUser = mstUserMapper.selectByNikename(user);

						if (mstUser != null) {
							mstUserResPara.setCode(CommCode.M_ERROR);
							mstUserResPara.setMessage(CommCode.M_A000014);
						} else {
							user.setType(1);
							user.setIsdeleted(false);

							int count = mstUserMapper.insert(user);
							// 大于0 注册成功
							if (count > 0) {
								mstUserResPara.setCode(CommCode.M_SUCCESSC);
								mstUserResPara.setMessage(CommCode.M_Y000001);
								mstUserResPara.setMstuser(user);
							} else {
								mstUserResPara.setCode(CommCode.M_ERROR);
								mstUserResPara.setMessage(CommCode.M_A000015);
							}
						}
					} else {
						user.setId(Integer.parseInt(mstUserReqPara.getId()));
						user.setReserved7(true);
						user.setReserved9(mstUserReqPara.getImgurl());
						
						mstUserMapper.updateByPrimaryKeySelective(user);

						mstUserResPara.setCode(CommCode.M_SUCCESSC);
						mstUserResPara.setMessage(CommCode.M_Y000001);
						mstUserResPara.setMstuser(user);
					}
				} else {
					mstUserResPara.setCode(CommCode.M_ERROR);
					mstUserResPara.setMessage(CommCode.M_BP00106);
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

			log.info(" RegistrationServlet result : " + jsonResult);

		} catch (Exception e) {
			log.error(" RegistrationServlet error : " + e.getMessage(), e);
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

			mstUserReqPara.setImagebinary(request.getParameter("imagebinary"));
			mstUserReqPara.setEmail(request.getParameter("email"));
			mstUserReqPara
					.setName(CommFun.Filter(request.getParameter("name")));
			mstUserReqPara.setGender(request.getParameter("gender"));
			mstUserReqPara.setNickname(CommFun.Filter(request
					.getParameter("nickname")));
			mstUserReqPara.setMobileno(request.getParameter("mobileno"));
			mstUserReqPara.setBirthday(request.getParameter("birthday"));
			mstUserReqPara.setWeight(request.getParameter("weight"));
			mstUserReqPara.setHeight(request.getParameter("height"));
			mstUserReqPara.setPassword(request.getParameter("password"));

			String optype = request.getParameter("optype");

			if (optype == null) {
				optype = MstUserReqPara.REG_NORMAL_TYPE;
			} else {
				mstUserReqPara.setImgurl(request.getParameter("imgurl"));
				mstUserReqPara.setId(request.getParameter("id"));
			}
			mstUserReqPara.setOptype(optype);
			log.info(" getReocrdReqPara para imagebinary:"
					+ request.getParameter("imagebinary") + " email:"
					+ request.getParameter("email") + " name:"
					+ CommFun.Filter(request.getParameter("name")) + " gender:"
					+ request.getParameter("gender") + " nickname:"
					+ CommFun.Filter(request.getParameter("nickname"))
					+ " mobileno:" + request.getParameter("mobileno")
					+ " birthday:" + request.getParameter("birthday")
					+ " weight:" + request.getParameter("weight") + " height:"
					+ request.getParameter("height") + " password:"
					+ request.getParameter("height"));

		} catch (Exception e) {
			log.error(" MstUserReqPara error : " + e.getMessage(), e);
		}

		return mstUserReqPara;
	}
}
