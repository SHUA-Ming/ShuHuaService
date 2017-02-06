/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：BaseServlet   
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

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lst.utils.CommonUtils;

/**
 * 
 * @ClassName: BaseServlet
 * @Description: Base Servlet
 * @author Chen sy
 * @date 2015年4月11日 上午11:15:31
 * 
 */
public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected Logger log = Logger.getLogger("log");

	protected static ClassPathXmlApplicationContext ctx;

	protected static String path;

	/**
	 * @return the path
	 */
	public static String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public static void setPath(String path) {
		BaseServlet.path = path;
	}

	static {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

		try {
			Properties props = new Properties();
			// ����������
			ClassLoader cl = CommonUtils.class.getClassLoader();
			// ������������ķ���ȥ��������·���µ��ļ�
			InputStream is = cl.getResourceAsStream("shuhua.properties");

			// ���ļ������ݶ���props������
			props.load(is);

			path = props.getProperty("img_file_path_linux");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
