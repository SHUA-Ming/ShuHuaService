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
import com.lst.common.CommCode;
import com.lst.dao.MstUserMapper;
import com.lst.dao.UserMotionRecordMapper;
import com.lst.entity.request.ReocrdReqPara;
import com.lst.entity.response.MachineResPara;
import com.lst.model.UserMotionRecord;

/**
 * @ClassName: RecordServlet
 * @Description:用户健身记录
 * @author mingming
 * @date 2017年2月17日
 * 
 */

public class MotionRecordServlet extends BaseServlet implements
		IBaseServlet<ReocrdReqPara> {

	private static final long serialVersionUID = 1L;

	private static UserMotionRecordMapper userMotionRecordMapper = ctx
			.getBean(UserMotionRecordMapper.class);

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
			MachineResPara machineResPara = new MachineResPara();

			String userId = req.getParameter("userId");
			String machineId = req.getParameter("machineId");  //设备id--------------------------室内
			String userPlanId = req.getParameter("userPlanId");  //计划id
			String startDate = req.getParameter("startDate");  //开始时间
			String endDate = req.getParameter("endDate");  //结束时间
			String longTime = req.getParameter("longTime");  //时长
			
			String calorie = req.getParameter("calorie");  //卡路里
			String kilometer = req.getParameter("kilometer");  //距离（公里）
			String step = req.getParameter("step");  //步数
			String heartRate = req.getParameter("heartRate");  //心率------------------------------室内
			String pace = req.getParameter("pace");  //步频
			
			String averageVelocity = req.getParameter("averageVelocity");  //平均速度
			String averageSpeed = req.getParameter("averageSpeed");  //平均配速--------------------户外
			String averageStride = req.getParameter("averageStride");  //平均步幅
			String averageGrade = req.getParameter("averageGrade");  //平均坡度--------------------室内
			String mark = req.getParameter("mark");  //indoor室内 outdoor室外
			
			if (StringUtils.isBlank(userId)) {
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00301);
			}else if(StringUtils.isBlank(mark)){
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00322);
			}else if(mark.equals("indoor")){
				if (StringUtils.isBlank(machineId)) {
					machineResPara.setCode(CommCode.M_ERROR);
					machineResPara.setMessage(CommCode.M_BP00302);
				}else if (StringUtils.isBlank(heartRate)) {
					machineResPara.setCode(CommCode.M_ERROR);
					machineResPara.setMessage(CommCode.M_BP00315);
				}else if(StringUtils.isBlank(averageGrade)){
					machineResPara.setCode(CommCode.M_ERROR);
					machineResPara.setMessage(CommCode.M_BP00316);
				}
			}else if(mark.equals("outdoor")){
				if (StringUtils.isBlank(averageSpeed)) {
					machineResPara.setCode(CommCode.M_ERROR);
					machineResPara.setMessage(CommCode.M_BP00317);
				}
			}else if(StringUtils.isBlank(startDate)){
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00303);
			}else if(StringUtils.isBlank(endDate)){
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00304);
			}else if(StringUtils.isBlank(longTime)){
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00318);
			}else if(StringUtils.isBlank(calorie)){
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00305);
			}else if(StringUtils.isBlank(kilometer)){
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00306);
			}else if(StringUtils.isBlank(step)){
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00314);
			}else if(StringUtils.isBlank(pace)){
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00319);
			}else if(StringUtils.isBlank(averageVelocity)){
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00320);
			}else if(StringUtils.isBlank(averageStride)){
				machineResPara.setCode(CommCode.M_ERROR);
				machineResPara.setMessage(CommCode.M_BP00321);
			}else {
				UserMotionRecord userMotionRecord = new UserMotionRecord();
				userMotionRecord.setUserId(Integer.parseInt(userId));
				if(mark.equals("indoor")){
					userMotionRecord.setMachineId(Integer.parseInt(machineId));
					userMotionRecord.setHeartRate(Integer.parseInt(heartRate));
					userMotionRecord.setAverageGrade(Float.parseFloat(averageGrade));
				}else{
					userMotionRecord.setAverageSpeed(Integer.parseInt(averageSpeed));
				}
				
				if (!"".equals(userPlanId) && userPlanId != null) {
					userMotionRecord.setUserPlanId(Integer.parseInt(userPlanId));
				}
				userMotionRecord.setStartDate(Long.parseLong(startDate));
				userMotionRecord.setEndDate(Long.parseLong(endDate));
				userMotionRecord.setLongTime(Integer.parseInt(longTime));
				userMotionRecord.setCalorie(Float.parseFloat(calorie));
				userMotionRecord.setKilometer(Float.parseFloat(kilometer));
				userMotionRecord.setStep(Integer.parseInt(step));
				userMotionRecord.setPace(Float.parseFloat(pace));
				userMotionRecord.setAverageVelocity(Float.parseFloat(averageVelocity));
				userMotionRecord.setAverageStride(Integer.parseInt(averageStride));
				userMotionRecord.setCreateDate(new Date());
				userMotionRecord.setMark(mark);

				int count = userMotionRecordMapper.insertSelective(userMotionRecord);
				System.out.println("count == " + count);
				if (count > 0) {
					machineResPara.setCode(CommCode.M_SUCCESSC);
					machineResPara.setMessage(CommCode.M_Y000001);
				} else {
					machineResPara.setCode(CommCode.M_ERROR);
					machineResPara.setMessage(CommCode.M_A000015);
				}
			}

		
		//返回值
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
		
	}
	@Override
	public ReocrdReqPara getReqPara(HttpServletRequest request) {
		return null;
	}
}
