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
			ReocrdReqPara reocrdReqPara = getReqPara(req);

			// usreid
			String userid = reocrdReqPara.getUserid();
			if (StringUtils.isBlank(userid)) {
				medalResPara.setCode(CommCode.M_ERROR);
				medalResPara.setMessage(CommCode.M_BP00301);
			} else {
				PageBounds pageBounds = new PageBounds();
				
				// 查询健身记录
				List<UserReocrd> recordList = userRecordMapper.selectByUserid(
						Integer.parseInt(userid), pageBounds);
				if(recordList.size() > 0){
					
					// 查询所有勋章等级
					List<MstMedal> medalList = mstMedalMapper.selectMedal();
	
					String flag = "";  //所得勋章id
					double kilomster = 0;  //总公里数
					int date = 0;  //总时间
					int recordListLen = recordList.size();  //健身记录条数
	
					for (int i = 0; i < recordListLen; i++) {
						UserReocrd userRecord = recordList.get(i);  //每条健身记录
	
						kilomster += Double.parseDouble(userRecord.getKilometer()
								.toString());  //总公里数
	
						long end = userRecord.getEndtime().getTime();  //结束时间
						long start = userRecord.getStarttime().getTime();  //开始时间
	
						int time = (int) ((end - start) / 1000) / 60;
	
						date += time;  //总时间
					}
	
					int medalListLen = medalList.size();  //勋章等级条数
					for (int j = 0; j < medalListLen; j++) {
						MstMedal medal = medalList.get(j);  //每条勋章等级的内容
						
						if (medal.getType() == 60) {  // 按健身次数勋章等级来比较
							if (medal.getRule() == recordListLen) {
								flag += medal.getId() + ",";
								continue;
							}
						} else if (medal.getType() == 61) {  // 按距离勋章等级来比较
							if (kilomster >= medal.getRule()) {
								flag += medal.getId() + ",";
								continue;
							}
						} else if (medal.getType() == 62) {  // 时间勋章
							if (date >= medal.getRule()) {
								flag += medal.getId() + ",";
								continue;
							}
						}
					}
					
					if(flag != ""){
						UserMedal userMedal = new UserMedal();
						userMedal.setUserid(Integer.parseInt(userid));
						userMedal.setCreatedate(new Date());
						userMedal.setCreateuser(Integer.parseInt(userid));
		
						int count = 0;
						for (String str : flag.split(",")) {
							
							//获取勋章表该用户已有的勋章list
							List<UserMedal> userMedalList = userMedalMapper.selectByUserid(Integer.parseInt(userid));
							//获取新的勋章
							int tag = 0;  //标注
							for(int index = 0;index < userMedalList.size();index++){
								int medalId = userMedalList.get(index).getMedalid();
								int strMedalId = Integer.parseInt(str);
								if(medalId == strMedalId){
									tag = 1;
								}
							}
							if(tag == 0){
								userMedal.setMedalid(Integer.parseInt(str));
								count = userMedalMapper.insert(userMedal);
							}
						}
						if (count > 0) {
							medalResPara.setCode(CommCode.M_SUCCESSC);
							medalResPara.setMessage(CommCode.M_BP00312);
						} else {
							medalResPara.setCode(CommCode.M_ERROR);
							medalResPara.setMessage(CommCode.M_BP00311);
						}
					}else{
						medalResPara.setCode(CommCode.M_ERROR);
						medalResPara.setMessage(CommCode.M_BP00310);
					}
				}else{
					medalResPara.setCode(CommCode.M_ERROR);
					medalResPara.setMessage(CommCode.M_BP00307);
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
