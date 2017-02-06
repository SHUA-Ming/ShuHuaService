/**   
 *    
 * 项目名称：ShuhuaService   
 * 类名称：CommonCode   
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

/**
 * @ClassName: CommonCode
 * @Description: 返回错误信息
 * @author Echo
 * @date 2014年6月12日 下午3:00:45
 * 
 */
public class CommCode {
	// 公共
	//public static final String M_ERROR = "1111111";
	
	public static final String M_ERROR = "0000000";  //code值-错误
	public static final String M_SUCCESSC = "1111111";  //code值-正确

	public static final String M_Y000000 = "0000000";
	public static final String M_Y000001 = "success";
	public static final String M_A000008 = "操作类型非法";

	// 注册
	public static final String M_AP00101 = "邮箱地址为空";
	public static final String M_AP00102 = "姓名为空";
	public static final String M_BP00103 = "性别为空";
	public static final String M_BP00104 = "昵称为空";
	public static final String M_BP00105 = "手机号为空";
	public static final String M_A000010 = "出生日期为空";
	public static final String M_A000011 = "体重为空";
	public static final String M_AP00112 = "身高为空";
	public static final String M_A000013 = "密码为空";
	public static final String M_A000014 = "昵称已注册";
	public static final String M_A000015 = "失败";

	public static final String M_BP00106 = "手机号已注册!";
	public static final String M_BP00107 = "图片为空！";
	// 登录
	public static final String M_A000026 = "没有该用户";
	public static final String M_A000027 = "密码错误";

	// 重置密码
	public static final String M_B000001 = "重置密码失败";
	public static final String M_B000002 = "修改密码失败";
	public static final String M_B000003 = "原始密码输入错误";

	// 设备信息
	public static final String M_BP00201 = "设备型号为空";
	public static final String M_BP00202 = "没有设备信息";

	// 健身信息
	public static final String M_BP00301 = "用户id为空";
	public static final String M_BP00302 = "设备为空";
	public static final String M_BP00303 = "开始时间为空";
	public static final String M_BP00304 = "结束时间为空";
	public static final String M_BP00305 = "消耗卡路里为空";
	public static final String M_BP00306 = "距离为空";
	public static final String M_BP00307 = "没有健身记录";
	public static final String M_BP00308 = "当前页为空";
	public static final String M_BP00309 = "每页条数为空";

	public static final String M_BP00310 = "没有勋章";
	public static final String M_BP00311 = "没有新勋章";
	public static final String M_BP00312 = "添加勋章成功";

	// 验证码
	public static final int MIN_NO = 100000;
	public static final int MAX_NO = 999999;

	/*
	 * 登录类型枚举
	 */
	public enum LoginEnum {
		NORMAL, QQ, WEOBO, WEIXIN, OTHERS;
	}
}
