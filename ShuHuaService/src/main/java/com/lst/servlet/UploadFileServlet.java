/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：UploadFileServlet   
 * 类描述：   
 * 创建人： aluo
 * 创建时间：2015年5月4日   
 * 修改人：aluo   
 * 修改时间：2015年5月4日  
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lst.base.entity.BaseResponse;
import com.lst.common.CommCode;
import com.lst.entity.request.MstUserReqPara;

/**
 * @ClassName: UploadFileServlet
 * @Description:上传图片
 * @author aluo
 * @date 2015年5月4日
 * 
 */
@SuppressWarnings("deprecation")
public class UploadFileServlet extends BaseServlet implements
		IBaseServlet<MstUserReqPara> {
	private static final long serialVersionUID = 1L;
	
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

		String temp = request.getSession().getServletContext().getRealPath("/") + "temp";  //临时目录
		String loadpath = request.getSession().getServletContext().getRealPath("/") + "Image";  //上传文件存放目录
		System.out.println("临时路径："+temp+"上传路径："+loadpath);
		// loadpath="g:\ftp_file";
		
		BaseResponse res = new BaseResponse();

		File tempdir = new File(temp);
		if (!tempdir.exists()) {
			tempdir.mkdir();  //创建文件夹
		}
		File loadpathdir = new File(loadpath);
		if (!loadpathdir.exists()) {
			loadpathdir.mkdir();
		}
		System.out.println("loadpath=" + loadpath);
		DiskFileUpload fu = new DiskFileUpload();
		fu.setSizeMax(1 * 1024 * 1024);
		fu.setSizeThreshold(4096);
		fu.setRepositoryPath(temp);

		// 开始读取上传信息
		int index = 0;
		List fileItems = null;

		try {
			fileItems = fu.parseRequest(request);
			System.out.println("fileItems：上传文件lisM_Y000000t=" + fileItems);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(fileItems != null){
			String msg = "error";
			Iterator iter = fileItems.iterator();  //依次处理每个上传的文件
			try {
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();  //忽略其他不是文件域的所有表单信息
					if (!item.isFormField()) {
						String name = item.getName();  //获取上传文件名,包括路径
						name = name.substring(name.lastIndexOf("\\") + 1);  //从全路径中提取文件名
						long size = item.getSize();
						if ((name == null || name.equals("")) && size == 0)
							continue;
	
						name = (new Date()).getTime() + ".jpg";
						index++;
						File fNew = new File(loadpath, name);
						item.write(fNew);
						msg = name;
						res.setMessage(name);
					}
				}
				res.setCode(CommCode.M_SUCCESSC);
			} catch (Exception e) {
				res.setCode(CommCode.M_ERROR);
				res.setMessage(e.getMessage());
			}
			// response.getWriter().write(msg);
			
		}else{
			res.setCode(CommCode.M_ERROR);
			res.setMessage(CommCode.M_A000015);
		}
		
		//返回页面
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.excludeFieldsWithoutExposeAnnotation().create();
		String jsonResult = gson.toJson(res);
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");

		PrintWriter out = response.getWriter();
		out.print(jsonResult);
		out.flush();
		out.close();
	}

	@Override
	public MstUserReqPara getReqPara(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
