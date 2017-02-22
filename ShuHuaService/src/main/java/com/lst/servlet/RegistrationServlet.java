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
 * @author mingming
 * @date 2017年2月13日 下午1:58:29
 * 
 */

public class RegistrationServlet extends BaseServlet implements
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

			//获取值
			String mobileNo = req.getParameter("mobileNo");
			String password = req.getParameter("password");
			String confirmPassword = req.getParameter("confirmPassword");
			
			//判断
			if(StringUtils.isBlank(mobileNo)){
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_BP00105);
			}else if(StringUtils.isBlank(password)){
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_A000013);
			}else if(StringUtils.isBlank(confirmPassword)){
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_A000016);
			}else if(!password.equals(confirmPassword)){
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_A000017);
			}else{  //创建用户
				MstUser mstUser = new MstUser();
				mstUser.setMobileno(mobileNo);
				MstUser mstuser = mstUserMapper.selectByMobileno(mstUser);
				if(mstuser == null){  //判断该用户是否存在
					mstUser.setPassword(password);
					mstUser.setCreatedate(new Date());
					mstUser.setType(1);
					mstUser.setIsdeleted(false);
					int count = mstUserMapper.insert(mstUser);
					if (count > 0) {
						mstUserResPara.setCode(CommCode.M_SUCCESSC);
						mstUserResPara.setMessage(CommCode.M_Y000001);
					} else {
						mstUserResPara.setCode(CommCode.M_ERROR);
						mstUserResPara.setMessage(CommCode.M_A000015);
					}
				}else{
					mstUserResPara.setCode(CommCode.M_ERROR);
					mstUserResPara.setMessage(CommCode.M_BP00106);
				}
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
