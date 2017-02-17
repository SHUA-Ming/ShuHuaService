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
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lst.common.CommCode;
import com.lst.dao.MstUserMapper;
import com.lst.entity.request.MstUserReqPara;
import com.lst.entity.response.MstUserResPara;
import com.lst.model.MstUser;
import com.lst.utils.CommFunUtils;
import com.lst.utils.DateUtil;

/**
 * @ClassName: PerfectInformationServlet
 * @Description:用户完善信息
 * @author mingming
 * @date 2017年2月14日
 * 
 */

public class PerfectInformationServlet extends BaseServlet implements
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
		
			MstUserResPara mstUserResPara = new MstUserResPara();
			MstUser user = new MstUser();
			
			String userId = req.getParameter("userId");
			int matUserId = Integer.parseInt(userId);
			String portrait = req.getParameter("portrait");  //图片名字
			String nickName = req.getParameter("nickName");
			
			/*if(StringUtils.isBlank(nickName)){
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_BP00104);
			}else if(StringUtils.isBlank(nickNimageBinaryame)){
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_BP00107);
			}*/
			
			/*if(StringUtils.isNotBlank(imageBinary)) {
				String filePath;
				try {
					filePath = path + DateUtil.getCurrentTime(DateUtil.ALL_DATETIME_STRING_QUEUE);
					String urlPath = "/UploadImages/" + DateUtil.getCurrentTime(DateUtil.ALL_DATETIME_STRING_QUEUE);
					user.setPortrait(urlPath + ".jpg");
					CommFunUtils.base64StringToImage4One(imageBinary,filePath);
				} catch (ParseException e) {
					e.printStackTrace();
				}/ShuHuaService/src/main/java/com/lst/servlet/PerfectInformationServlet.java
			}*/
			
			user.setId(matUserId);
			System.out.println("nickName == " + nickName);
			user.setPortrait(portrait);
			user.setNickname(nickName);
			int count = mstUserMapper.updateByPrimaryKeySelective(user);
			System.out.println("tiaoshu == "+ count);
			if (count > 0) {
				mstUserResPara.setCode(CommCode.M_SUCCESSC);
				mstUserResPara.setMessage(CommCode.M_Y000001);
			} else {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_A000015);
			}
			
			//返回页面
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
	}

	@Override
	public MstUserReqPara getReqPara(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
