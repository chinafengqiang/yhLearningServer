package com.smlearning.infrastructure.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据类型转换�?
 * @author
 */
public class DataTypeConvertor {

	/**
	 * boolean转换成数�?
	 * @param value
	 * @return
	 */
	public static Integer booleanToInteger(Boolean value) {
		
		if (value==true) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * 数�?�转换成boolean
	 * @param value
	 * @return
	 */
	public static boolean IntegerToBoolean(Integer value) {
		
		if (value.intValue()==1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 将字符串转换为日�?
	 * @param date 日期
	 * @param formatPattern 日期格式类型字符�?
	 * @return 日期
	 */
	public static Date stringToDate(String date, String formatPattern){   
		SimpleDateFormat sdf = new SimpleDateFormat();  
		try{   
			if(date == null || "".equals(date)){
				return null;
			} else {
				if((formatPattern == null)||formatPattern.equals("")){   
					formatPattern = "yyyy-MM-dd HH:mm:ss";   
				}   
				sdf.applyPattern(formatPattern);   
				return   sdf.parse(date);  
			}
		}catch(Exception e){   
			e.printStackTrace();   
			return   null;   
		}   
	}
	
	/**
	 * 将日期转换为字符�?
	 * @param date 日期
	 * @param shortType 是否短格�?
	 * @return 日期字符�?
	 */
	public static String dateToString(Date date, boolean shortType) {
		if(date!=null){
			if (shortType)
				return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
			else
				return (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 将日期转换为字符�?
	 * @param date 日期
	 * @param formatPattern 格式?
	 * @return 日期字符�?
	 */
	public static String dateToString(Date date, String formatPattern) {
		if(date!=null){
			if((formatPattern==null)||formatPattern.equals("")){
				return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
			} else {
				return (new SimpleDateFormat(formatPattern)).format(date);
			}
		}else{
			return "";
		}
	}
	
	public static void main(String[] args){
		System.out.println(DataTypeConvertor.stringToDate("", null));
	}
}
