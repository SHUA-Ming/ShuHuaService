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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lst.common.CommCode;
import com.lst.dao.MstUserMapper;
import com.lst.entity.request.MstUserReqPara;
import com.lst.entity.response.LoginMstUserResPara;
import com.lst.model.LoginBody;
import com.lst.model.MstUser;

/**
 * @ClassName: LoginServlet
 * @Description:用户登录
 * @author mingming
 * @date 2017年2月13日
 * 
 */
public class LoginServlet extends BaseServlet implements
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
		LoginMstUserResPara mstUserResPara = new LoginMstUserResPara();
		
		String mobileNo = req.getParameter("mobileNo");
		String password = req.getParameter("password");
		if(StringUtils.isBlank(mobileNo)){
			mstUserResPara.setCode(CommCode.M_ERROR);
			mstUserResPara.setMessage(CommCode.M_BP00105);
		}else if(StringUtils.isBlank(password)){
			mstUserResPara.setCode(CommCode.M_ERROR);
			mstUserResPara.setMessage(CommCode.M_A000013);
		}else{
			//根据mobileno查询是否有该用户
			MstUser user = new MstUser();
			user.setMobileno(mobileNo);
			MstUser mstuser = mstUserMapper.selectByMobileno(user);
			System.out.println("mstuser ==  "+mstuser);
			
			if(mstuser == null){  
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_A000026);
			}else{
				System.out.println("password== "+password+"mstuser.getPassword()== "+mstuser.getPassword());
				if(!password.equals(mstuser.getPassword())){  //密码是否正确
					mstUserResPara.setCode(CommCode.M_ERROR);
					mstUserResPara.setMessage(CommCode.M_A000027);
				}else{  //登录成功
					mstUserResPara.setCode(CommCode.M_SUCCESSC);
					mstUserResPara.setMessage(CommCode.M_A000028);
					LoginBody loginBody = new LoginBody();
					loginBody = mstUserMapper.returnRegist(mobileNo);
					mstUserResPara.setLoginBody(loginBody);
				}
			}
		}
		
		//返回页面
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().serializeNulls().create();
		String jsonResult = gson.toJson(mstUserResPara);
		resp.setContentType("application/json;charset=utf-8");
		resp.setHeader("pragma", "no-cache");
		resp.setHeader("cache-control", "no-cache");

		PrintWriter out = resp.getWriter();
		out.print(jsonResult);
		out.flush();
		out.close();
	}


	@Override
	public MstUserReqPara getReqPara(HttpServletRequest request) {
		return null;
	}
}
