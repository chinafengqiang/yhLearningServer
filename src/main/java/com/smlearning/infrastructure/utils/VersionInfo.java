package com.smlearning.infrastructure.utils;


/**
 * 版本信息
 * @author
 *
 */
public class VersionInfo {
	
	
	
	//private static Properties properties = new Properties();
	static Configuration rc = null;
	
	static{
			rc = new Configuration("config/version.properties");
	}
	
	/**服务端版本号 */
	public static final String SERVER_VERSION = rc.getValue("SERVER_VERSION");
	/**客户端版本号*/
	public static final String CLIENT_VERSION = rc.getValue("CLIENT_VERSION");
	
	public static final String CHANNELID = rc.getValue("channelID");
	
	public static final String PRIORITY = rc.getValue("priority");
	
	public static final String BANDWIDTH = rc.getValue("bandwidth");
	
	public static final String PACKFILE = rc.getValue("PackFile");
	
	public static final String SENDMODE = rc.getValue("sendMode");
	
	public static final String SENDTIME = rc.getValue("sendTime");
	
	public static final String REPEATCOUNT = rc.getValue("repeatcount");
	
	public static final String VALIDRATE = rc.getValue("validRate");

}

