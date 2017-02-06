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

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lst.base.entity.BaseRequest;
import com.lst.common.CommCode;
import com.lst.dao.MstMachineMapper;
import com.lst.entity.request.MachineReqPara;
import com.lst.entity.response.MachineResPara;
import com.lst.model.MstMachine;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: MachineServlet
 * @Description:设备信息
 * @author Chen sy
 * @date 2015年4月7日 下午1:58:29
 * 
 */
public class MachineServlet extends BaseServlet implements
		IBaseServlet<MachineReqPara> {

	private static final long serialVersionUID = 1L;

	private static MstMachineMapper mstMachineMapper = ctx
			.getBean(MstMachineMapper.class);

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
			// Get the VerifyCode request parameter
			MachineReqPara machineReqPara = getReqPara(req);

			// model
			String model = machineReqPara.getModel();

			if (StringUtils.isBlank(model)) {

				machineResPara.setCode(CommCode.M_Y000000);
				machineResPara.setMessage(CommCode.M_BP00201);
			} else {

				MstMachine machine = new MstMachine();

				machine.setModel(model);

				MstMachine mstMachine = mstMachineMapper.selectByModel(machine);

				log.error("MstMachine result :" + mstMachine);

				if (mstMachine == null) {
					machineResPara.setCode(CommCode.M_Y000000);
					machineResPara.setMessage(CommCode.M_BP00202);
				} else {
					machineResPara.setMstMachine(mstMachine);
					machineResPara.setCode(CommCode.M_Y000000);
					machineResPara.setMessage(CommCode.M_Y000001);
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

			log.info(" MachineServlet result : " + jsonResult);

		} catch (Exception e) {
			log.error(" MachineServlet error : " + e.getMessage(), e);
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
	public MachineReqPara getReqPara(HttpServletRequest request) {
		MachineReqPara machineReqPara = null;

		try {
			BaseRequest baseRequest = RequestUtils.getRequestPara(request,
					new MachineReqPara());

			machineReqPara = (MachineReqPara) baseRequest.clone();

			machineReqPara.setModel(request.getParameter("model"));

			log.info(" getMachineReqPara para model:"
					+ request.getParameter("model"));

		} catch (Exception e) {
			log.error(" MachineReqPara error : " + e.getMessage(), e);
		}

		return machineReqPara;
	}
}
