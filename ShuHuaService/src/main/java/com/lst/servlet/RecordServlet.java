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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lst.base.entity.BaseRequest;
import com.lst.common.CommCode;
import com.lst.dao.MstUserMapper;
import com.lst.dao.UserReocrdMapper;
import com.lst.entity.request.ReocrdReqPara;
import com.lst.entity.response.MachineResPara;
import com.lst.model.MstUser;
import com.lst.model.UserReocrd;
import com.lst.utils.DateUtil;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: RecordServlet
 * @Description:用户健身记录
 * @author Chen sy
 * @date 2015年4月7日 下午1:58:29
 * 
 */

public class RecordServlet extends BaseServlet implements
		IBaseServlet<ReocrdReqPara> {

	private static final long serialVersionUID = 1L;

	private static UserReocrdMapper userRecordMapper = ctx
			.getBean(UserReocrdMapper.class);

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
			// Get the ReocrdReqPara request parameter
			ReocrdReqPara reocrdReqPara = getReqPara(req);

			// calorid
			String calorie = reocrdReqPara.getCalorie();
			// usreid
			String userid = reocrdReqPara.getUserid();
			// planid
			String planid = reocrdReqPara.getPlanid();
			// machineid
			String machineid = reocrdReqPara.getMachineid();
			// startdate
			String startdate = reocrdReqPara.getStartdate();
			// enddate
			String enddate = reocrdReqPara.getEnddate();
			// kilometer
			String kilometer = reocrdReqPara.getKilometer();

			if (StringUtils.isBlank(userid)) {
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00301);
			} else if (StringUtils.isBlank(machineid)) {
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00302);
			} else if (StringUtils.isBlank(startdate)) {
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00303);
			} else if (StringUtils.isBlank(enddate)) {
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00304);
			} else if (StringUtils.isBlank(kilometer)) {
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00306);
			} else if (StringUtils.isBlank(calorie)) {
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00305);
			} else {
				Date startDate = new Date();

				log.info("UserReocrd add begin time :" + startDate);

				UserReocrd record = new UserReocrd();

				record.setUserid(Integer.parseInt(userid));
				// TODO: remove the id due to not used
				//record.setMachineid(Integer.parseInt(machineid));
				
				record.setCalorie(new BigDecimal(calorie));
				record.setKilometer(new BigDecimal(kilometer));
				
				record.setStarttime(DateUtil.parse(startdate,
						DateUtil.ALL_DATETIME_HORIZONTAL));
				record.setEndtime(DateUtil.parse(enddate,
						DateUtil.ALL_DATETIME_HORIZONTAL));
				
				record.setIsdeleted(false);
				record.setCreatedate(new Date());
				record.setCreateuser(Integer.parseInt(userid));

				if (!"".equals(planid) && planid != null) {
					record.setPlanid(Integer.parseInt(planid));
				}

				int count = userRecordMapper.insert(record);

				// 增加汇总
				MstUser user = mstUserMapper.selectByPrimaryKey(Integer
						.parseInt(userid));
				
				if (user.getTotalnumber() == null) {
					user.setTotalnumber(1);
				} else {
					user.setTotalnumber(user.getTotalnumber() + 1);
				}

				// calculte the Totalcalorie
				double totalCalorie = 0;
				
				if (user.getTotalcalorie() != null){
					totalCalorie = user.getTotalcalorie().doubleValue();
					totalCalorie += (new BigDecimal(calorie)).doubleValue();
				}else{
					totalCalorie += (new BigDecimal(calorie)).doubleValue();
				}
				
				user.setTotalcalorie(new BigDecimal(totalCalorie));
				
				// claculte the kilometer
				double totalKilometer = 0;
				
				if ( user.getTotalkilometer() != null){
					totalKilometer = user.getTotalkilometer().doubleValue();
					totalKilometer += (new BigDecimal(kilometer)).doubleValue();
				}else{
					totalKilometer += (new BigDecimal(kilometer)).doubleValue();
				}
				
				user.setTotalkilometer(new BigDecimal(totalKilometer));

				// space time
				long spacetime = record.getEndtime().getTime()
						- record.getStarttime().getTime();
				long dbSpacetime = 0;
				
				if (user.getReserved1() != null) {
					dbSpacetime = user.getReserved1();
					dbSpacetime+=(int) spacetime
							/ 1000;
				}else{
					dbSpacetime+=(int) spacetime
							/ 1000;
				}
				
				user.setReserved1((int)dbSpacetime);

				count =  mstUserMapper.updateByPrimaryKeySelective(user);

				log.info("UserReocrd end time :"
						+ DateUtil.calLastedTime(startDate));

				if (count > 0) {
					machineResPara.setCode(CommCode.M_SUCCESSC);
					machineResPara.setMessage(CommCode.M_Y000001);
					
					user = mstUserMapper.selectByPrimaryKey(user.getId());
					machineResPara.setUser(user);

				} else {
					machineResPara.setCode(CommCode.M_ERROR);
					machineResPara.setMessage(CommCode.M_A000015);
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

			log.info(" RecordServlet result : " + jsonResult);

		} catch (Exception e) {
			log.error(" RecordServlet error : " + e.getMessage(), e);
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
			reocrdReqPara.setMachineid(request.getParameter("machineid"));
			reocrdReqPara.setPlanid(request.getParameter("planid"));
			reocrdReqPara.setStartdate(request.getParameter("startdate"));
			reocrdReqPara.setEnddate(request.getParameter("enddate"));
			reocrdReqPara.setCalorie(request.getParameter("calorie"));
			reocrdReqPara.setKilometer(request.getParameter("kilometer"));

			log.info(" getReocrdReqPara para userid:"
					+ request.getParameter("userid") + " machineid:"
					+ request.getParameter("machineid") + " planid:"
					+ request.getParameter("planid") + " startdate:"
					+ request.getParameter("startdate") + " enddate:"
					+ request.getParameter("enddate") + " calorie:"
					+ request.getParameter("calorie") + " kilometer:"
					+ request.getParameter("kilometer"));

		} catch (Exception e) {
			log.error(" ReocrdReqPara error : " + e.getMessage(), e);
		}

		return reocrdReqPara;
	}
}
