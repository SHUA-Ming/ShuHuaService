/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：TestSMS   
 * 类描述：   
 * 创建人：Echo   
 * 创建时间：2015年5月5日 下午8:58:59   
 * 修改人：Echo   
 * 修改时间：2015年5月5日 下午8:58:59   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.test;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lst.common.CommFun;
import com.lst.dao.MstCodeMapper;
import com.lst.dao.MstUserMapper;
import com.lst.model.MstUser;

/**
 * @ClassName: TestSMS
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Echo
 * @date 2015年5月5日 下午8:58:59
 * 
 */
public class TestSMS {

	private static ApplicationContext ctx;

	@Autowired
	private MstUserMapper mapper = ctx.getBean(MstUserMapper.class);

	static {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	public TestSMS() {

	}

	/**
	 * @Title: setUp
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @throws java.lang.Exception 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @Title: tearDown
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @throws java.lang.Exception 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		List<MstUser> listUser = mapper.selectUser();

		int listUserLen = listUser.size();

		HashMap<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < listUserLen; i++) {
			MstUser user = listUser.get(i);

			String mobileNo = user.getMobileno();

			if (mobileNo == null || mobileNo == "" ) {
				continue;
			}
			
//			if (!isMobile(mobileNo)){
//				continue;
//			}

			if (map.containsKey(mobileNo)) {
				continue;
			} else {
				map.put(mobileNo, "");

				boolean bclIsSend = CommFun
						.sendMessage(
								"01",
								mobileNo,
								"尊敬的用户，您好！因舒华运动APP服务器系统升级维护，导致升级维护期间APP无法 正常登陆使用，由此带来的不便请您谅解。为了能够更好地为您服务，请重新下载更新APP，谢谢！（来自应用：舒华运动）");

			}
		}

		// boolean bclIsSend = CommFun.sendMessage("01", "13621660856",
		// "您的验证码是：123456。请不要把验证码泄露给其他人。");

		boolean bclIsSend = CommFun
				.sendMessage(
						"01",
						"13621660856",
						"尊敬的用户，您好！因舒华运动APP服务器系统升级维护，导致升级维护期间APP无法 正常登陆使用，由此带来的不便请您谅解。为了能够更好地为您服务，请重新下载更新APP，谢谢！（来自应用：舒华运动）");

		Assert.assertTrue("Is true", bclIsSend);
	}

	private boolean isMobile(String mobieNo){
		
//		String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";  
//
//		Pattern p = Pattern.compile(regExp);  
//
//		Matcher m = p.matcher(mobieNo);  
//
//		return m.find();
		
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  

		Matcher m = p.matcher(mobieNo);  
		
		return m.matches();  
	}
}
