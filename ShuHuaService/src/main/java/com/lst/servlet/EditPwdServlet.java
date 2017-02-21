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
import com.lst.entity.request.MstUserReqPara;
import com.lst.entity.response.MstUserResPara;
import com.lst.model.MstUser;
import com.lst.utils.DateUtil;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: EditPwdServlet
 * @Description: 修改密码
 * @author mingming
 * @date 2017年2月27日
 * 
 */
public class EditPwdServlet extends BaseServlet implements
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
			
			String mobileNo = req.getParameter("mobileNo");
			String oldPassWord = req.getParameter("oldPassWord");
			String newPassWord = req.getParameter("newPassWord");  //新密码
			String confirmPassWord = req.getParameter("confirmPassWord");

			if (StringUtils.isBlank(mobileNo)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_BP00105);
			} else if (StringUtils.isBlank(oldPassWord)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_B000004);
			} else if (StringUtils.isBlank(newPassWord)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_A000013);
			} else if (StringUtils.isBlank(confirmPassWord)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_A000016);
			} else if(!newPassWord.equals(confirmPassWord)){
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_A000017);
			}else {
				MstUser mstUser = new MstUser();
				mstUser.setMobileno(mobileNo);
				mstUser = mstUserMapper.selectByMobileno(mstUser);
				
				if(mstUser == null){
					mstUserResPara.setCode(CommCode.M_ERROR);
					mstUserResPara.setMessage(CommCode.M_A000026);
				}else{
					String passWordOld = mstUser.getPassword();
					if(passWordOld.equals(oldPassWord)){
						mstUser.setPassword(mobileNo);
						mstUser.setPassword(newPassWord);
						int con = mstUserMapper.updateResetPwd(mstUser);
						if(con == 1){
							mstUserResPara.setCode(CommCode.M_SUCCESSC);
							mstUserResPara.setMessage(CommCode.M_Y000001);
						}else{
							mstUserResPara.setCode(CommCode.M_ERROR);
							mstUserResPara.setMessage(CommCode.M_A000015);
						}
					}else{
						mstUserResPara.setCode(CommCode.M_ERROR);
						mstUserResPara.setMessage(CommCode.M_B000003);
					}
				}
			}

			//返回数据
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.excludeFieldsWithoutExposeAnnotation().create();
			String jsonResult = gson.toJson(mstUserResPara);
			resp.setContentType("application/json;charset=utf-8");
			// resp.setContentType("text/plain;charset=UTF-8");
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
