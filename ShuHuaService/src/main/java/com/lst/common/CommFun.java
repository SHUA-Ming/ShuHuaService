/**   
 *    
 * 项目名称：ShuhuaService   
 * 类名称：CommFun   
 * 类描述：   
 * 创建人：Echo   
 * 创建时间：2014年6月12日 下午3:00:45   
 * 修改人：Echo   
 * 修改时间：2014年6月12日 下午3:00:45   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.lst.service.AbstractFactory;
import com.lst.service.HttpFactory;
import com.lst.service.IHttpService;
import com.lst.utils.CommonUtils;

/**
 * @ClassName: CommFun
 * @Description: Common tools
 * @author Echo
 * @date 2014年6月12日 下午3:00:45
 * 
 */
public class CommFun {
	protected static Logger log = Logger.getLogger("log");

	/**
	 * 
	 * @Title: Filter
	 * @Description: Convert the Chinese
	 * @param @param paramter
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String Filter(String paramter) {
		String toUtf = "";
		try {
			toUtf = new String(paramter.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return toUtf;
	}

	/**
	 * 
	 * @Title: makeRandomWithRange
	 * @Description: Make the random code
	 * @param @param min
	 * @param @param max
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public static int makeRandomWithRange(int min, int max) {

		Random random = new Random();

		int num = random.nextInt(max) % (max - min + 1) + min;

		return num;
	}

	/**
	 * 
	 * @Title: sendMessage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param userId
	 * @param @param mobileNo
	 * @param @param content
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean sendMessage(String userId, String mobileNo,
			String content) {

		boolean bclIsSend = false;

		try {

			AbstractFactory factory = new HttpFactory();
			IHttpService ihs = factory.create();

			StringBuilder sb = new StringBuilder();

			sb.append(CommonUtils.getSms_url());
			sb.append("&account=").append(CommonUtils.getSms_user());
			sb.append("&password=").append(CommonUtils.getSms_pwd());
			sb.append("&mobile=").append(mobileNo);

			String _content = URLEncoder.encode(content, "gbk");

			sb.append("&content=").append(_content);

			String result = ihs.httpResult(sb.toString());

			Document doc;

			try {

				doc = DocumentHelper.parseText(result);

				Element root = doc.getRootElement();

				String code = root.elementText("code");

				if ("2".equals(code)) {
					bclIsSend = true;

					log.info(mobileNo + ":----短信提交成功。");

					// TODO: need to save the message to DB.

					/**
					 * Do it later
					 */
				}else{
					log.info(mobileNo + ":----短信发送失败。");
				}

			} catch (DocumentException e) {
				e.printStackTrace();
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return bclIsSend;
	}

	/**
	 * 
	 * @Title: messageSave
	 * @Description: Save the meesage to DB
	 * @param @param phoneNo
	 * @param @param message
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	/*
	 * private static boolean messageSave(String phoneNo, String message) {
	 * 
	 * boolean bclIsSaveOk = false;
	 * 
	 * try { // save the message to DB BaseDaoImpl<MessageInfo> baseDaoImpl =
	 * new BaseDaoImpl<MessageInfo>();
	 * 
	 * MessageInfo messageInfo = new MessageInfo();
	 * 
	 * Timestamp curtTs = new Timestamp(System.currentTimeMillis());
	 * 
	 * messageInfo.setTitle("验证码"); messageInfo.setContent(message);
	 * messageInfo.setMobileNo(phoneNo); messageInfo.setMesFlag(1);
	 * messageInfo.setCreateDate(curtTs); messageInfo.setCreateUser(0);
	 * 
	 * bclIsSaveOk = baseDaoImpl.SaveRecord(messageInfo);
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * 
	 * log.error("CommFun: " + e.getMessage(), e); }
	 * 
	 * return bclIsSaveOk; }
	 *//**
	 * 
	 * @Title: resultPwd
	 * @Description: Send the meeeage
	 * @param @param phoneNo
	 * @param @param verifyCode
	 * @param @param message
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	/*
	 * public static boolean resultPwd(String phoneNo, String ninkName, String
	 * pwd) {
	 * 
	 * boolean bclSendOk = false;
	 * 
	 * // Send the message info to each user WebService webService = new
	 * WebService();
	 * 
	 * String PWD = MD5Util.string2MD5(WS_MD_Util.getUser() +
	 * WS_MD_Util.getPassword());
	 * 
	 * String sendMessage = " 亲爱的" + ninkName + "，您的账号密码已被重置，请使用系统随机密码" + pwd +
	 * "进行登录。登录成功后请及时修改密码，为了增强账号安全性，建议采用大小写字母与数字的组合。" +
	 * WS_MD_Util.getSignature();
	 * 
	 * String result = webService.getWebServiceSoap().mt(WS_MD_Util.getUser(),
	 * PWD.toUpperCase(), phoneNo, sendMessage, "", "", "");
	 * 
	 * if (Long.parseLong(result) > 0) { bclSendOk = true; messageSave(phoneNo,
	 * sendMessage); log.info("成功 ：" + phoneNo + " " + sendMessage);
	 * 
	 * }
	 * 
	 * log.info("error :" + "Failure"); return bclSendOk; }
	 *//**
	 * 
	 * @Title: send
	 * @Description: Send the meeeage
	 * @param @param phoneNo
	 * @param @param verifyCode
	 * @param @param message
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	/*
	 * public static boolean send(String phoneNo, String verifyCode, String
	 * message, String messInfo) {
	 * 
	 * boolean bclSendOk = false;
	 * 
	 * // Send the message info to each user WebService webService = new
	 * WebService();
	 * 
	 * String PWD = MD5Util.string2MD5(WS_MD_Util.getUser() +
	 * WS_MD_Util.getPassword());
	 * 
	 * String sendMessage = message + verifyCode + "，请勿向任何人提供您收到的短信" + messInfo
	 * + "。" + WS_MD_Util.getSignature();
	 * 
	 * String result = webService.getWebServiceSoap().mt(WS_MD_Util.getUser(),
	 * PWD.toUpperCase(), phoneNo, sendMessage, "", "", "");
	 * 
	 * if (Long.parseLong(result) > 0) { bclSendOk = true; messageSave(phoneNo,
	 * sendMessage); log.info("成功 ：" + phoneNo + " " + sendMessage); }
	 * log.info("error :" + "Failure");
	 * 
	 * return bclSendOk; }
	 *//**
	 * 
	 * @Title: sendInfo
	 * @Description: Send the meeeage
	 * @param @param phoneNo
	 * @param @param verifyCode
	 * @param @param message
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	/*
	 * public static boolean sendInfo(String phoneNo, String mess, String
	 * message) {
	 * 
	 * boolean bclSendOk = false;
	 * 
	 * // Send the message info to each user WebService webService = new
	 * WebService();
	 * 
	 * String PWD = MD5Util.string2MD5(WS_MD_Util.getUser() +
	 * WS_MD_Util.getPassword());
	 * 
	 * String sendMessage = " 亲爱的" + mess + "，欢迎您加入嗅微，享受便捷的社区生活服务。您的登录账号：尾号为" +
	 * message + "的手机号" + WS_MD_Util.getSignature();
	 * 
	 * String result = webService.getWebServiceSoap().mt(WS_MD_Util.getUser(),
	 * PWD.toUpperCase(), phoneNo, sendMessage, "", "", "");
	 * 
	 * if (Long.parseLong(result) > 0) { bclSendOk = true; messageSave(phoneNo,
	 * sendMessage); log.info("成功 ：" + phoneNo + " " + sendMessage); }
	 * 
	 * return bclSendOk; }
	 *//**
	 * 
	 * @Title: sendVerification
	 * @Description: Send the meeeage
	 * @param @param phoneNo
	 * @param @param verifyCode
	 * @param @param message
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	/*
	 * public static boolean sendVerification(String phoneNo, String mess,
	 * String message, String id) {
	 * 
	 * boolean bclSendOk = false;
	 * 
	 * // Send the message info to each user WebService webService = new
	 * WebService();
	 * 
	 * String PWD = MD5Util.string2MD5(WS_MD_Util.getUser() +
	 * WS_MD_Util.getPassword());
	 * 
	 * String sendMessage = " 来自" + mess + "小区，" + "手机尾号为" + message + "的住户," +
	 * " 申请开通嗅微使用权限，同意请回复“1”，拒绝请回复“2”。" + WS_MD_Util.getSignature();
	 * 
	 * String result = webService.getWebServiceSoap().mt(WS_MD_Util.getUser(),
	 * PWD.toUpperCase(), phoneNo, sendMessage, "0" + id, "", "");
	 * 
	 * if (Long.parseLong(result) > 0) { bclSendOk = true;
	 * 
	 * log.info("成功 ：" + phoneNo + " " + sendMessage); messageSave(phoneNo,
	 * sendMessage); }
	 * 
	 * log.info("error :" + "Failure"); return bclSendOk; }
	 */
}
