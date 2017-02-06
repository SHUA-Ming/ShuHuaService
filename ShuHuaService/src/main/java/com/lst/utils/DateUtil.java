/**
 * 
 * 项目名称：ShuHuaService
 * 类名称：DateUtil
 * 类描述：
 * 创建人：Echo
 * 创建时间：2015年3月12日 下午10:06:34
 * 修改人：Echo
 * 修改时间：2015年3月12日 下午10:06:34
 * 修改备注：
 * @version 
 * 
 */
package com.lst.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @ClassName: DateUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Echo
 * @date 2015年3月12日 下午10:06:34
 * 
 */
public class DateUtil extends DateUtils {

	public static final String ALL_DATETIME_HORIZONTAL = "yyyy-MM-dd HH:mm:ss";

	public static final String ALL_DATE_HORIZONTAL = "yyyy-MM-dd";

	public static final String ALL_DATETIME_STRING_QUEUE = "yyyyMMddHHmmss";

	public static final String ALL_TIME = "HH:mm:ss";

	public static final String ALL_DATETIME_SEPARATOR = "yyyy/MM/dd HH:mm:ss";

	public static final String ALL_DATE_SEPARATOR = "yyyy/MM/dd";

	private DateUtil() {
	}

	public static Date getCurrentDate() throws ParseException {
		Timestamp curtTs = new Timestamp(System.currentTimeMillis());
		return curtTs;
	}

	public static Date getCurrentDate(String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(sdf.format(new Date()));
	}

	public static String getCurrentTime(String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	public static Date getFormatDate(Date date, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(sdf.format(date));
	}

	public static String getFormatTime(Date date, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获得默认的 date pattern
	 */
	public static String getDatePattern() {
		return ALL_DATE_HORIZONTAL;
	}

	/**
	 * 使用预设格式将字符串转为Date
	 */
	public static Date parse(String strDate) throws ParseException {
		return StringUtils.isBlank(strDate) ? null : parse(strDate, getDatePattern());
	}

	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parse(String strDate, String pattern) throws ParseException {
		return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
	}

	/**
	 * 字符串转换成日期 如果转换格式为空，则利用默认格式进行转换操作
	 * 
	 * @param str
	 *            字符串
	 * @param format
	 *            日期格式
	 * @return 日期
	 * @throws java.text.ParseException
	 */
	public static Date str2Date(String str, String format) {
		if (null == str || "".equals(str)) {
			return null;
		}
		// 如果没有指定字符串转换的格式，则用默认格式进行转换
		if (null == format || "".equals(format)) {
			format = ALL_DATETIME_HORIZONTAL;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(Date date, String format) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 时间戳转换为字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String timestamp2Str(Timestamp time) {
		Date date = null;
		if (null != time) {
			date = new Date(time.getTime());
		}
		return date2Str(date, ALL_DATETIME_HORIZONTAL);
	}

	/**
	 * 字符串转换时间戳
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp str2Timestamp(String str) {
		Date date = str2Date(str, ALL_DATETIME_HORIZONTAL);
		return new Timestamp(date.getTime());
	}

	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parseTry(String strDate, String pattern) {
		try {
			return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * 
	 * @Title: calLastedTime
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param startDate
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public static int calLastedTime(Date startDate) {
		long a = new Date().getTime();
		long b = startDate.getTime();
		int c = (int) ((a - b) / 1000);
		return c;
	}
}
