package com.lst.servlet;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class TestServlet extends BaseServlet {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String string = "hello world!";
			
			String result = new Gson().toJson(string);

			response.setContentType("application/json;charset=utf-8");
			response.setHeader("pragma", "no-cache");
			response.setHeader("cache-control", "no-cache");

			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();

			log.info(result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
