/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：WebServiceUtil   
 * 类描述：   
 * 创建人：Echo  
 * 创建时间：2014年8月15日 下午11:15:10   
 * 修改人：Echo   
 * 修改时间：2014年8月15日 下午11:15:10   
 * 修改备注：   
 * @version    
 *    
 */
package com.lst.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName: WebServiceUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Echo
 * @date 2014年8月15日 下午11:15:10
 * 
 */
public final class WS_MD_Util
{

    private static String path;
    private static String user;

    private static String password;

    private static String signature;
    private static int sendno;
    private static String imageurl;
    static
    {
        try
        {
            Properties props = new Properties();
            // ����������
            ClassLoader cl = WS_MD_Util.class.getClassLoader();
            // ������������ķ���ȥ��������·���µ��ļ�
            InputStream is = cl.getResourceAsStream("showayq.properties");

            // ���ļ������ݶ���props������
            props.load(is);

            path = props.getProperty("path");
            user = props.getProperty("user");
            password = props.getProperty("password");
            signature = props.getProperty("signature");
            sendno = Integer.parseInt(props.getProperty("sendno"));
            imageurl = props.getProperty("imageurl");

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    /**
     * @return the password
     */
    public static String getPassword()
    {
        return password;
    }


    public static String getPath()
    {
        return path;
    }


    /**
     * @return the sendno
     */
    public static int getSendno()
    {
        return sendno;
    }


    /**
     * @return the signature
     */
    public static String getSignature()
    {
        return signature;
    }


    /**
     * @return the user
     */
    public static String getUser()
    {
        return user;
    }


    /**
     * @param password the password to set
     */
    public static void setPassword(String password)
    {
        WS_MD_Util.password = password;
    }


    public static void setPath(String path)
    {
        WS_MD_Util.path = path;
    }


    /**
     * @param sendno the sendno to set
     */
    public static void setSendno(int sendno)
    {
        WS_MD_Util.sendno = sendno;
    }


    /**
     * @param signature the signature to set
     */
    public static void setSignature(String signature)
    {
        WS_MD_Util.signature = signature;
    }


    /**
     * @param user the user to set
     */
    public static void setUser(String user)
    {
        WS_MD_Util.user = user;
    }


    /**
     * @return the imageurl
     */
    public static String getImageurl()
    {
        return imageurl;
    }


    /**
     * @param imageurl the imageurl to set
     */
    public static void setImageurl(String imageurl)
    {
        WS_MD_Util.imageurl = imageurl;
    }

}
