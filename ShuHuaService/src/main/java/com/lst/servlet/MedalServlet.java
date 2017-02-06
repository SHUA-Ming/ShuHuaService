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
import com.lst.dao.MstMedalMapper;
import com.lst.dao.UserMedalMapper;
import com.lst.dao.UserReocrdMapper;
import com.lst.entity.request.ReocrdReqPara;
import com.lst.entity.response.MedalResPara;
import com.lst.model.MstMedal;
import com.lst.model.UserMedal;
import com.lst.model.UserReocrd;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: MedalServlet
 * @Description:勋章
 * @author Chen sy
 * @date 2015年4月7日 下午1:58:29
 * 
 */

public class MedalServlet extends BaseServlet implements
		IBaseServlet<ReocrdReqPara> {

	private static final long serialVersionUID = 1L;

	private static UserReocrdMapper userRecordMapper = ctx
			.getBean(UserReocrdMapper.class);

	private static MstMedalMapper mstMedalMapper = ctx
			.getBean(MstMedalMapper.class);

	private static UserMedalMapper userMedalMapper = ctx
			.getBean(UserMedalMapper.class);

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
			// Get the VerifyCode request parameter
			ReocrdReqPara reocrdReqPara = getReqPara(req);

			// usreid
			String userid = reocrdReqPara.getUserid();

			if (StringUtils.isBlank(userid)) {

				medalResPara.setCode(CommCode.M_Y000000);
				medalResPara.setMessage(CommCode.M_BP00301);
			} else {

				PageBounds pageBounds = new PageBounds();
				// 查询健身记录
				List<UserReocrd> recordList = userRecordMapper.selectByUserid(
						Integer.parseInt(userid), pageBounds);

				// 查询勋章等级
				List<MstMedal> medalList = mstMedalMapper.selectMedal();

				String flag = "";

				double kilomster = 0;

				int date = 0;
				int recordListLen = recordList.size();

				for (int i = 0; i < recordListLen; i++) {
					UserReocrd userRecord = recordList.get(i);

					kilomster += Double.parseDouble(userRecord.getKilometer()
							.toString());

					long end = userRecord.getEndtime().getTime();
					long start = userRecord.getStarttime().getTime();

					int time = (int) ((end - start) / 1000) / 60;

					date += time;
				}

				int medalListLen = medalList.size();
				for (int j = 0; j < medalListLen; j++) {
					MstMedal medal = medalList.get(j);

					// 按次数勋章等级来比较
					if (medal.getType() == 60) {

						if (medal.getRule() == recordList.size()) {
							flag += medal.getId() + ",";
							continue;
						}
						// 按距离勋章等级来比较
					} else if (medal.getType() == 61) {

						if (kilomster >= medal.getRule()) {

							flag += medal.getId() + ",";
							continue;
						}
						// 时间勋章
					} else if (medal.getType() == 62) {

						if (date >= medal.getRule()) {
							flag += medal.getId() + ",";
							continue;
						}
					}
				}

				UserMedal userMedal = new UserMedal();

				userMedal.setUserid(Integer.parseInt(userid));
				userMedal.setCreatedate(new Date());
				userMedal.setCreateuser(Integer.parseInt(userid));

				int count = 0;
				for (String str : flag.split(",")) {
					userMedal.setMedalid(Integer.parseInt(str));
					count = userMedalMapper.insert(userMedal);
				}

				if (count > 0) {
					medalResPara.setCode(CommCode.M_Y000000);
					medalResPara.setMessage(CommCode.M_Y000001);
				} else {
					medalResPara.setCode(CommCode.M_Y000000);
					medalResPara.setMessage(CommCode.M_A000015);
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

			log.info(" MedalServlet result : " + jsonResult);

		} catch (Exception e) {
			log.error(" MedalServlet error : " + e.getMessage(), e);
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
