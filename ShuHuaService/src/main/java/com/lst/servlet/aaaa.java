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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lst.base.entity.BaseRequest;
import com.lst.dao.MstUserMapper;
import com.lst.dao.UserReocrdMapper;
import com.lst.entity.request.ReocrdReqPara;
import com.lst.entity.response.MachineResPara;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: RecordServlet
 * @Description:用户健身记录
 * @author Chen sy
 * @date 2015年4月7日 下午1:58:29
 * 
 */

public class aaaa extends BaseServlet implements
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
			System.out.println("1++++++++++++++++++");
			// response
			MachineResPara machineResPara = new MachineResPara();

			// request
			ReocrdReqPara reocrdReqPara = getReqPara(req);
			// usreid
			String userid = reocrdReqPara.getUserid();
			
			
			
			//本地视频播放代码
			 try{
				 	System.out.println("2++++++++++++++++++");
		            Runtime.getRuntime().exec("\"C:/Program Files/Windows Media Player/wmplayer.exe\""+"D:/Youku Files/download/1.mp4");    
		        
			 } catch (Exception e){    
		            System.out.println("Error!");    
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
