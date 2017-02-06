/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：ChangeIconServlet   
 * 类描述：   
 * 创建人：aluo
 * 创建时间：2015年4月7日 下午1:58:29   
 * 修改人：aluo 
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
 * @ClassName: ChangeIconServlet
 * @Description:修改图标
 * @author aluo
 * @date 2015年4月7日 下午1:58:29
 * 
 */

public class ChangeIconServlet extends BaseServlet implements IBaseServlet<MstUserReqPara> {

	private static final long serialVersionUID = 1L;

	private static MstUserMapper mstUserMapper = ctx.getBean(MstUserMapper.class);

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
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// response
			MstUserResPara mstUserResPara = new MstUserResPara();

			// request
			MstUserReqPara mstUserReqPara = getReqPara(req);

			// nickname
			String id = mstUserReqPara.getId();

			String imgurl = mstUserReqPara.getImagebinary();

			if (StringUtils.isBlank(id)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_A000026);
			} else if (StringUtils.isBlank(imgurl)) {
				mstUserResPara.setCode(CommCode.M_ERROR);
				mstUserResPara.setMessage(CommCode.M_BP00107);
			} else {
				MstUser user = new MstUser();
				user.setId(Integer.parseInt(id));
				user.setReserved9("/Image/" + imgurl);

				MstUser mstuser = mstUserMapper.selectById(user);
				log.info("MstUser reslut  ： " + mstuser);
				if (mstuser == null) {
					mstUserResPara.setCode(CommCode.M_ERROR);
					mstUserResPara.setMessage(CommCode.M_A000026);
				} else {
					int count = mstUserMapper.updateIcon(user);
					if (count > 0) {
						// TDo
						mstUserResPara.setCode(CommCode.M_SUCCESSC);
						mstUserResPara.setMessage(CommCode.M_Y000001);
						mstUserResPara.setMstuser(mstuser);

					} else {
						mstUserResPara.setCode(CommCode.M_ERROR);
						mstUserResPara.setMessage(CommCode.M_B000001);
					}
				}
			}
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").excludeFieldsWithoutExposeAnnotation()
					.create();

			String jsonResult = gson.toJson(mstUserResPara);

			resp.setContentType("application/json;charset=utf-8");
			resp.setHeader("pragma", "no-cache");
			resp.setHeader("cache-control", "no-cache");

			PrintWriter out = resp.getWriter();
			out.print(jsonResult);
			out.flush();
			out.close();

			log.info(" ChangeIconServlet result : " + jsonResult);

		} catch (Exception e) {
			log.error(" ChangeIconServlet error : " + e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc) <p>Title: getReqPara</p> <p>Description: </p>
	 * 
	 * @param request
	 * 
	 * @return
	 * 
	 * @see com.lst.servlet.IBaseServlet#getReqPara(javax.servlet.http.
	 * HttpServletRequest )
	 */
	@Override
	public MstUserReqPara getReqPara(HttpServletRequest request) {
		MstUserReqPara mstUserReqPara = null;

		try {
			BaseRequest baseRequest = RequestUtils.getRequestPara(request, new MstUserReqPara());

			mstUserReqPara = (MstUserReqPara) baseRequest.clone();

			mstUserReqPara.setId(request.getParameter("id"));
			mstUserReqPara.setImagebinary(request.getParameter("imagebinary"));

		} catch (Exception e) {
			log.error(" MstUserReqPara error : " + e.getMessage(), e);
		}

		return mstUserReqPara;
	}
}
