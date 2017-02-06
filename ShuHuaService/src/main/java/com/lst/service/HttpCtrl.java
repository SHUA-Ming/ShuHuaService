/**
 * 
 * 项目名称：ShuHuaService
 * 类名称：HttpCtrl
 * 类描述：
 * 创建人：Echo
 * 创建时间：2015年3月12日 下午5:15:47
 * 修改人：Echo
 * 修改时间：2015年3月12日 下午5:15:47
 * 修改备注：
 * @version 
 * 
 */
package com.lst.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * @ClassName: HttpCtrl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Echo
 * @date 2015年3月12日 下午5:15:47
 * 
 */
public class HttpCtrl implements IHttpService {

	/*
	 * (非 Javadoc) <p>Title: httpResult</p> <p>Description: </p>
	 * 
	 * @param url
	 * 
	 * @return
	 * 
	 * @see com.lotusoftec.com.service.IHttpService#httpResult(java.lang.String)
	 */
	@Override
	public String httpResult(String url) {

		String result = "";

		try {

			URL reqURL = new URL(url);

			HttpURLConnection connection = (HttpURLConnection) reqURL.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String temp = in.readLine();

			while (temp != null) {

				if (result != null) {
					result += temp;
				} else {
					result = temp;
				}

				temp = in.readLine();
			}

			result = URLDecoder.decode(result, "UTF-8");

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return result;
	}
}
