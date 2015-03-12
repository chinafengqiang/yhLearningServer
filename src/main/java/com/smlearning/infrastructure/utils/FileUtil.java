package com.smlearning.infrastructure.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件操作工具�?
 * @author 
 */
public class FileUtil {

	private static final int BUFFER_SIZE = 16 * 1024 ;
    private static final String UPLOADDIR = "/tools/";
	
	public static FileTypeEnum getFileType(String fileName) {
		
		String extName = getExtName(fileName);
		if (extName.equalsIgnoreCase(".jpg") || extName.equalsIgnoreCase(".jpeg")) {
			return FileTypeEnum.JPG;
		}
		if (extName.equalsIgnoreCase(".gif")) {
			return FileTypeEnum.GIF;
		}
		if (extName.equalsIgnoreCase(".png")) {
			return FileTypeEnum.PNG;
		}
		return FileTypeEnum.OTHER;
	}
	
	public static String getPathByQuestionFile() {
		
		return "file/exam/questionFile/";
	}	
	
	/**
	 * 上传文件
	 * @param src 源文�?
	 * @param dst 目标文件
	 */
	public static void uploadFile(File src, File dst)  {
		try  {
			InputStream in = null ;
			OutputStream out = null ;
			try  {                
				in = new BufferedInputStream( new FileInputStream(src), BUFFER_SIZE);
				out = new BufferedOutputStream( new FileOutputStream(dst), BUFFER_SIZE);
				byte [] buffer = new byte [BUFFER_SIZE];
				while (in.read(buffer) > 0 )  {
					out.write(buffer);
				} 
			} finally  {
				if ( null != in)  {
					in.close();
				} 
				if ( null != out)  {
					out.close();
				} 
			} 
		} catch (Exception e)  {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 下载附件
	 * @param src源文�?
	 */
	public static void downloadFile(File src, HttpServletResponse response)  {
		try  {
			InputStream in = null ;
			OutputStream out = null ;
			try  {                
				in =  new FileInputStream(src);
				out = response.getOutputStream();
				byte [] buffer = new byte [BUFFER_SIZE];
				while (in.read(buffer) > 0 )  {
					out.write(buffer);
				} 
			} finally  {
				if ( null != in)  {
					in.close();
				} 
				if ( null != out)  {
					out.close();
				} 
			} 
		} catch (Exception e)  {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 下载附件
	 * @param src源文�?
	 */
	public static void downloadFile(HttpServletResponse response, String contentType, String localFilePath, String downloadFileName)  {
		
		try {
			response.setContentType(contentType);
			response.setHeader("Content-Disposition","attachment;filename=\"" + URLEncoder.encode(downloadFileName, "UTF-8") + "\"");
			response.setHeader("Cache-Control","must-revalidate,post-check=0,pre-check=0");
			response.setHeader("Pragma","public");
			response.setDateHeader("Expires", 0);
			
			File file = new File(localFilePath);
			downloadFile(file, response);
			
		} catch (Exception e)  {
			e.printStackTrace();
		} 
	}
	
	 /** 
     * 下载 
     *  
     * @author geloin 
     * @date 2012-5-5 下午12:25:39 
     * @param request 
     * @param response 
     * @param storeName 
     * @param contentType 
     * @param realName 
     * @throws Exception 
     */  
    public static void download(HttpServletRequest request,  
            HttpServletResponse response, String storeName, String contentType,  
            String realName) throws Exception {  
        response.setContentType("application/vnd.android.package-archive;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
  
        String ctxPath = request.getSession().getServletContext()  
                .getRealPath("/")  + FileUtil.UPLOADDIR;  
        String downLoadPath = ctxPath + storeName;  
  
        long fileLength = new File(downLoadPath).length();  
  
       // response.setContentType(contentType);  
        response.setHeader("Content-disposition", "attachment; filename="  
                + new String(realName.getBytes("utf-8"), "ISO8859-1"));  
        response.setHeader("Content-Length", String.valueOf(fileLength));  
  
        bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
        bos = new BufferedOutputStream(response.getOutputStream());  
        byte[] buff = new byte[2048];  
        int bytesRead;  
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) { 
            bos.write(buff, 0, bytesRead);  
        }  
        bis.close();  
        bos.close();  
    }  

	/**
	 * 得到文件扩展�?
	 * @param fileName文件�?
	 * @return
	 */
	public static String getExtName(String fileName)  {
		
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos).toLowerCase();
   }
	
	
	/**
	 * 创建目录
	 * @param filePath文件目录
	 * @return
	 */
	public static void createDirectory(String filePath){
		File dirFile = null;
		StringTokenizer stokenizer = new StringTokenizer(filePath,"/");
		String  segPath = stokenizer.nextToken() + "/";
		String  path = segPath;
		while(stokenizer.hasMoreElements()){
			segPath = stokenizer.nextToken() + "/";
			if(stokenizer.hasMoreElements() == false){
				return ;
			}
			path += segPath;
			dirFile = new File(path);
			if(!dirFile.exists()){
				dirFile.mkdir();
			}
		}
	}
	
	/**
	 * 把file转化字符串类型
	 * @param file
	 * @return
	 */
	public static String loadFileToString(File f){
		
        InputStream is = null;   
        String ret = null;   
        try {   
            is = new BufferedInputStream( new FileInputStream(f) );   
            long contentLength = f.length();   
            ByteArrayOutputStream outstream = new ByteArrayOutputStream( contentLength > 0 ? (int) contentLength : 1024);   
            byte[] buffer = new byte[4096];   
            int len;   
            while ((len = is.read(buffer)) > 0) {   
                outstream.write(buffer, 0, len);   
            }               
            outstream.close();   
            ret = outstream.toString();
    	}catch (Exception e){
			e.printStackTrace();
        } finally {   
            if(is!=null) {try{is.close();} catch(Exception e){} }   
        }
		return ret;  
	}
	
	   /** 
     * 对图片BASE64 解码 
     *  
     */  
    public static void getPicFormatBASE64(String str, String picPath) {  
        try {  
            byte[] result = new sun.misc.BASE64Decoder().decodeBuffer(str.trim());  
            RandomAccessFile inOut = new RandomAccessFile(picPath, "rw"); // r,rw,rws,rwd  
            // 用FileOutputStream亦可  
            inOut.write(result);  
            inOut.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
