package com.smlearning.infrastructure.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class StringUtil {
	
	public static String getPercent(Integer item,Integer count){
		
		DecimalFormat df=new DecimalFormat("##%");
		
		double c=0.0;
		double a=item/1.0;
		double b=count/1.0;
		
		if(count != 0){
			c=a/b;
		}
		
		return df.format(c);
	}
	
	public static String getPercent(double number){
		
		DecimalFormat df=new DecimalFormat("##%");
		
		double numberValue = number / 1.0;
		
		return df.format(numberValue);
	}
	
	
	public static String numberToString(int index){
		
		switch(index){
		
		case 0:
			return "A";
			
		case 1:
			return "B";
		
		case 2:
			return "C";
			
		case 3:
			return "D";
			
		case 4:
			return "E";
			
		case 5:
			return "F";
			
		case 6:
			return "H";
			
		case 7:
			return "F";
		
		default:
			return "";
		
		}
		
	}
	
	public static String  numberToChineseNub(int num){
		
		switch(num){
			
		case 0:
			
			return "一";
			
		case 1:
			
			return "二";
			
		case 2:
			
			return "三";
			
		case 3:
			
			return "四";
		
		 case 4:
			   
		   return "五";
  
		   case 5:
		   
		   return "六";
   
		   case 6:
		   
		   return "七";

		   case 7:
		   
		   return "八";

		   case 8:
		   
		   return "九";

		   case 9:
		   
		   return "十";

		   case 10:
		  
		   return "十一";
		   case 11:
		  
		   return "十二";

		   case 12:
		   
		   return "十三";

		   case 13:
		   
		   return "十四";

		   case 14:
		   
		   return "十五";

		   case 15:
		   
		   return "十六";
 
		   case 16:
		   
		   return "十七";

		   case 17:
		   
		   return "十八";

		   case 18:
		   
		   return "十九";

		   case 19:
		   
		   return "二十";

		   default:
		   return "";
		}
		
	}
	
	
	/**
	 * 去掉字符串中的空格
	 * @param s
	 * @return
	 */
	public static String trimSpaceCharacter(String s) {
		
		String rt = s;
		rt = rt.replaceAll("[ ]*", "");
		rt = rt.replaceAll("[　]*", "");
		rt = rt.replaceAll("[]*", "");
		return rt;
	}
	
	/**
	 * 判断字符串中是否存在汉字
	 * @param s
	 * @return Boolean
	 */
	public static Boolean checkChinese(String str) {
		
		Matcher m = Pattern.compile("[\u4e00-\u9fa5]").matcher(str); 
		return m.find();
	}
	
	/**
	 * GZIP压缩文件
	 * @param text文件
	 * @return
	 */
	public static String gzip(String text) {
		 
		ByteArrayInputStream   in = new ByteArrayInputStream(text.getBytes());   
		ByteArrayOutputStream  out = new ByteArrayOutputStream();

		System.out.println("out Size=" + out.size());
		try {
			GZIPOutputStream gzout = new GZIPOutputStream(out);
			byte[] buf=new byte[1024];//设定读入缓冲区尺寸 
			int num; 
	
			while (-1 != (num = in.read(buf))) 
			{ 
				gzout.write(buf,0,num); 
			} 
			gzout.close();//!!!关闭流,必须关闭所有输入输出流.保证输入输出完整和释放系统资源. 
			System.out.println("out Size=" + out.size());
			String rt = new String(out.toByteArray());
			out.close(); 
			in.close(); 
			return rt;

		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return "";
	}
	
	/**
	 * GZIP解压文件
	 * @param text文件
	 * @return
	 */
	public static String gunzip(String text){
		
        ByteArrayInputStream byteStream = new ByteArrayInputStream(text.getBytes());

        GZIPInputStream gzipStream;
		try {
			gzipStream = new GZIPInputStream(byteStream);
	        ObjectInputStream objectStream = new ObjectInputStream(gzipStream);

	        Object object;
			try {
				object = objectStream.readObject();
		        objectStream.close();

		        gzipStream.close();

		        return object.toString();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "";

	}
	
	public static String stringConvert(String s){
		if (s == null) {
			return null ;
		}

        String result = "" ;
        byte[] temp ;

        try{
//            temp = s.getBytes("iso-8859-1");
//            result =  new String(temp,"utf-8");
        	result = new String(s.getBytes("iso-8859-1"),"utf-8");
        } catch(Exception ex) {
       	 	ex.printStackTrace() ;
        }
        
        return result;
    }
}
