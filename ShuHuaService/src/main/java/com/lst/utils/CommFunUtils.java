/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：CommonUtil   
 * 类描述：Common Util  
 * 创建人：Echo   
 * 创建时间：2014年6月16日 下午5:29:49   
 * 修改人：Echo   
 * 修改时间：2014年6月16日 下午5:29:49   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @ClassName: CommonUtil
 * @Description: Common Util
 * @author Echo
 * @date 2014年6月16日 下午5:29:49
 * 
 */
@SuppressWarnings("restriction")
public class CommFunUtils {

	private static BASE64Encoder encoder = new BASE64Encoder();
	private static BASE64Decoder decoder = new BASE64Decoder();
	
	private static Logger logger = Logger.getLogger(CommonUtils.class);

	/**
	 * 
	 * @Title: base64StringToImage
	 * @Description: base64 String To Image
	 * @param @param base64String
	 * @param @param path 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void base64StringToImage(String base64String, String path) {
		try {

			String[] base64StrAry = base64String.split(",");

			int base64StrAryLen = base64StrAry.length;

			for (int i = 0; i < base64StrAryLen; i++) {

				byte[] bytes = decoder.decodeBuffer(base64StrAry[i]);

				ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
				BufferedImage bi = ImageIO.read(bais);
				// 可以是jpg,png,gif格式
				File file = new File(path + "_" + (i + 1) + ".jpg");
				// 不管输出什么格式图片，此处不需改动
				ImageIO.write(bi, "jpg", file);
			}
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: base64StringToImage4One
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param base64String
	 * @param @param path 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void base64StringToImage4One(String base64String, String path) {
		try {

			byte[] bytes = decoder.decodeBuffer(base64String);

			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			BufferedImage bi = ImageIO.read(bais);
			// 可以是jpg,png,gif格式
			File file = new File(path + ".jpg");
			
			logger.info("File path :" +path + ".jpg" );
			
			// 不管输出什么格式图片，此处不需改动
			ImageIO.write(bi, "jpg", file);

		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: getImageBinary
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param imgFile
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getImageBinary(String imgFile) {
		File f = new File(imgFile);
		BufferedImage bi;
		try {
			bi = ImageIO.read(f);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);
			byte[] bytes = baos.toByteArray();

			return encoder.encodeBuffer(bytes).trim();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @Title: makeRandomWithRange
	 * @Description: make Random With Range
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
}
