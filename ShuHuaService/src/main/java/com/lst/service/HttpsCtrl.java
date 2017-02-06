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
import java.net.URL;
import java.net.URLDecoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @ClassName: HttpsCtrl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Echo
 * @date 2015年3月12日 下午5:15:47
 * 
 */
public class HttpsCtrl implements IHttpService {

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

			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { new MyX509TrustManager() }, new java.security.SecureRandom());

			HttpsURLConnection httpsConn = (HttpsURLConnection) reqURL.openConnection();
			httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());
			httpsConn.setHostnameVerifier(new MyHostnameVerifier());
			httpsConn.setRequestMethod("POST");
			httpsConn.setDoOutput(true);
			httpsConn.setConnectTimeout(20000);

			BufferedReader in = new BufferedReader(new InputStreamReader(httpsConn.getInputStream()));

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

	private class MyX509TrustManager implements X509TrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}

	private class MyHostnameVerifier implements HostnameVerifier {

		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
}
