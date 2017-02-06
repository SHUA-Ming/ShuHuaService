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
import java.util.Date;

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
import com.lst.dao.SuggestInfoMapper;
import com.lst.entity.request.SuggestReqPara;
import com.lst.entity.response.MachineResPara;
import com.lst.model.MstUser;
import com.lst.model.SuggestInfo;
import com.lst.utils.DateUtil;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: SuggestServlet
 * @Description:反馈信息
 * @author Chen sy
 * @date 2015年4月7日 下午1:58:29
 * 
 */

public class SuggestServlet extends BaseServlet implements
		IBaseServlet<SuggestReqPara> {

	private static final long serialVersionUID = 1L;

	private static SuggestInfoMapper suggestInfoMapper = ctx
			.getBean(SuggestInfoMapper.class);

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
			MachineResPara machineResPara = new MachineResPara();

			// request
			// Get the VerifyCode request parameter
			SuggestReqPara suggestReqPara = getReqPara(req);

			// model
			String nickname = suggestReqPara.getNickname();
			String title = suggestReqPara.getTitle();
			String content = suggestReqPara.getContent();

			if (StringUtils.isBlank(nickname)) {

				machineResPara.setCode(CommCode.M_Y000000);
				machineResPara.setMessage(CommCode.M_BP00104);
			} else {

				MstUser user = new MstUser();

				user.setNickname(nickname);

				MstUser mstUser = mstUserMapper.selectByNikename(user);

				log.info("MstUsr result :" + mstUser);

				if (mstUser == null) {

					machineResPara.setCode(CommCode.M_Y000000);
					machineResPara.setMessage(CommCode.M_A000026);
				} else {
					Date startDate = new Date();

					log.info("SuggestInfo add  begin time :" + startDate);

					SuggestInfo suggest = new SuggestInfo();

					suggest.setUserid(mstUser.getId());
					suggest.setContent(content);
					suggest.setTitle(title);
					suggest.setCreatedate(new Date());
					suggest.setCreateuser(mstUser.getId());
					suggest.setIsdeleted(false);
					suggest.setIsread(false);

					int count = suggestInfoMapper.insert(suggest);

					log.info("SuggestInfo add  end time :"
							+ DateUtil.calLastedTime(startDate));

					if (count > 0) {
						machineResPara.setCode(CommCode.M_Y000000);
						machineResPara.setMessage(CommCode.M_Y000001);

					} else {
						machineResPara.setCode(CommCode.M_Y000000);
						machineResPara.setMessage(CommCode.M_A000015);
					}
				}
			}
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.excludeFieldsWithoutExposeAnnotation().create();

			String jsonResult = gson.toJson(machineResPara);

			resp.setContentType("application/json;charset=utf-8");
			resp.setHeader("pragma", "no-cache");
			resp.setHeader("cache-control", "no-cache");

			PrintWriter out = resp.getWriter();
			out.print(jsonResult);
			out.flush();
			out.close();

			log.info(" MachineServlet result : " + jsonResult);

		} catch (Exception e) {
			log.error(" MachineServlet result : " + e.getMessage(), e);
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
	public SuggestReqPara getReqPara(HttpServletRequest request) {
		SuggestReqPara suggestReqPara = null;

		try {
			BaseRequest baseRequest = RequestUtils.getRequestPara(request,
					new SuggestReqPara());

			suggestReqPara = (SuggestReqPara) baseRequest.clone();

			suggestReqPara.setNickname(CommFun.Filter(request.getParameter("nickname")));
			suggestReqPara.setTitle(request.getParameter("title"));
			suggestReqPara.setContent(request.getParameter("content"));

		} catch (Exception e) {
			log.error(" SuggestReqPara result : " + e.getMessage(), e);
		}

		return suggestReqPara;
	}
}
