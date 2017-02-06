/**
 * 
 * 项目名称：ShuHuaService
 * 类名称：commonUtils
 * 类描述：
 * 创建人：Echo
 * 创建时间：2015年3月12日 下午4:40:31
 * 修改人：Echo
 * 修改时间：2015年3月12日 下午4:40:31
 * 修改备注：
 * @version 
 * 
 */
package com.lst.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName: commonUtils
 * @Description: 读取配置文件信息
 * @author Echo
 * @date 2015年3月12日 下午4:40:31
 * 
 */
public class CommonUtils {

	private static String sms_url;

	private static String sms_user;

	private static String sms_pwd;

	private static String img_file_path;

	private static String excel_file_path;

	private static String img_file_path_linux;

	private static String img_file_path_http;

	static {
		try {
			Properties props = new Properties();
			// 加载
			ClassLoader cl = CommonUtils.class.getClassLoader();
			// 读取
			InputStream is = cl.getResourceAsStream("shuhua.properties");

			// 加载信息
			props.load(is);

			sms_url = props.getProperty("sms_url");
			sms_user = props.getProperty("sms_user");
			sms_pwd = props.getProperty("sms_pwd");
			img_file_path = props.getProperty("img_file_path");
			excel_file_path = props.getProperty("excel_file_path");
			img_file_path_linux = props.getProperty("img_file_path_linux");
			img_file_path_http = props.getProperty("img_file_path_http");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getSms_url() {
		return sms_url;
	}

	public static void setSms_url(String sms_url) {
		CommonUtils.sms_url = sms_url;
	}

	public static String getExcel_file_path() {
		return excel_file_path;
	}

	public static String getImg_file_path() {
		return img_file_path;
	}

	public static String getSms_pwd() {
		return sms_pwd;
	}

	public static String getSms_user() {
		return sms_user;
	}

	public static void setExcel_file_path(String excel_file_path) {
		CommonUtils.excel_file_path = excel_file_path;
	}

	public static void setImg_file_path(String img_file_path) {
		CommonUtils.img_file_path = img_file_path;
	}

	public static void setSms_pwd(String sms_pwd) {
		CommonUtils.sms_pwd = sms_pwd;
	}

	public static void setSms_user(String sms_user) {
		CommonUtils.sms_user = sms_user;
	}

	public static String getImg_file_path_linux() {
		return img_file_path_linux;
	}

	public static void setImg_file_path_linux(String img_file_path_linux) {
		CommonUtils.img_file_path_linux = img_file_path_linux;
	}

	public static String getImg_file_path_http() {
		return img_file_path_http;
	}

	public static void setImg_file_path_http(String img_file_path_http) {
		CommonUtils.img_file_path_http = img_file_path_http;
	}
}
