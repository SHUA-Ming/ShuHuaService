/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：UploadBinaryServlet   
 * 类描述： 上传图片二进制 
 * 创建人： Echo
 * 创建时间：2015年5月12日   
 * 修改人：Echo   
 * 修改时间：2015年5月12日  
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
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
import com.lst.entity.request.ImageReqPara;
import com.lst.entity.response.ImageResPara;
import com.lst.model.MstUser;
import com.lst.utils.CommFunUtils;
import com.lst.utils.CommonUtils;
import com.lst.utils.DateUtil;
import com.lst.utils.RequestUtils;

/**
 * @ClassName: UploadBinaryServlet
 * @Description:上传图片二进制
 * @author Echo
 * @date 2015年5月12日
 * 
 */
public class UploadBinaryServlet extends BaseServlet implements
		IBaseServlet<ImageReqPara> {

	private static final long serialVersionUID = 1L;

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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// response
			ImageResPara imageResPara = new ImageResPara();
			// request
			ImageReqPara imageReqPara = getReqPara(request);

			// userid
			String userId = imageReqPara.getUserid();
			// fileName
			String fileName = imageReqPara.getFileName();
			// imagebinary
			String imagebinary = imageReqPara.getImageBinary();

			MstUser user = new MstUser();

			user.setId(Integer.valueOf(userId));
			user.setUpdatedate(new Date());

			if (StringUtils.isNotBlank(imagebinary)) {

				String imageDate = fileName
						+ "-"
						+ DateUtil
								.getCurrentTime(DateUtil.ALL_DATETIME_STRING_QUEUE);

				String filePath = CommonUtils.getImg_file_path_linux()
						+ imageDate + "_" + userId;

				String urlPath = "Image/" + imageDate + "_" + userId;

				this.log("filePath : " + filePath);
				this.log("urlPath : " + urlPath);

				user.setPortrait(urlPath + ".jpg");
				user.setReserved9(urlPath + ".jpg");

				CommFunUtils.base64StringToImage4One(imagebinary, filePath);

				// update the url
				int count = mstUserMapper.updateByPrimaryKeySelective(user);

				// 大于0 更新URL成功
				if (count > 0) {
					imageResPara.setCode(CommCode.M_Y000000);
					imageResPara.setMessage(CommCode.M_Y000001);

					imageResPara.setFileUrl(urlPath + ".jpg");
				} else {
					imageResPara.setCode(CommCode.M_ERROR);
					imageResPara.setMessage(CommCode.M_A000015);
				}
			}

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.excludeFieldsWithoutExposeAnnotation().create();

			String jsonResult = gson.toJson(imageResPara);

			response.setContentType("application/json;charset=utf-8");
			response.setHeader("pragma", "no-cache");
			response.setHeader("cache-control", "no-cache");

			PrintWriter out = response.getWriter();
			out.print(jsonResult);
			out.flush();
			out.close();

			log.info(" UploadBinaryServlet result : " + jsonResult);

		} catch (Exception e) {
			log.error(" UploadBinaryServlet error : " + e.getMessage(), e);
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
	public ImageReqPara getReqPara(HttpServletRequest request) {

		ImageReqPara imageReqPara = null;

		try {
			BaseRequest baseRequest = RequestUtils.getRequestPara(request,
					new ImageReqPara());

			imageReqPara = (ImageReqPara) baseRequest.clone();

			imageReqPara.setUserid(request.getParameter("userid"));
			imageReqPara.setFileName(CommFun.Filter(request
					.getParameter("filename")));

			String imagebinary = URLDecoder.decode(
					request.getParameter("imagebinary"), "utf-8");
			imagebinary = imagebinary.replaceAll(" ", "+");

			imageReqPara.setImageBinary(imagebinary);
		} catch (Exception e) {
			log.error("getReqPara error : " + e.getMessage(), e);
		}

		return imageReqPara;
	}
}
