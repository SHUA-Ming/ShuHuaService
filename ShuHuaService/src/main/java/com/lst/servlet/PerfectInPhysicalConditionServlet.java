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

public class PerfectInPhysicalConditionServlet extends BaseServlet implements
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
		
			// response
			MstUserResPara mstUserResPara = new MstUserResPara();
			MstUser user = new MstUser();
			
			String userId = req.getParameter("userId");
			int matUserId = Integer.parseInt(userId);
			String name = req.getParameter("name");
			String sex = req.getParameter("sex");  //性别: 0或1
			String weight = req.getParameter("weight");  //身高
			String height = req.getParameter("height");  //体重
			String birthday = req.getParameter("birthday");
			//userId=1&name=qq&gender=0&weight=165&height=60&birthday=1993-01-05
			
			user.setId(matUserId);
			user.setName(name);
			if (sex.equals("0")) {
				user.setGender(false);
			} else if (sex.equals("1")) {
				user.setGender(true);
			}
			user.setWeight(BigDecimal.valueOf(Integer.parseInt(weight)));
			user.setHeight(Integer.parseInt(height));
			user.setBirthday(birthday);
			int count = mstUserMapper.updateByPrimaryKeySelective(user);
			
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
