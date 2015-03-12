package com.smlearning.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.com.iactive.db.DataGridModel;

import com.google.gson.Gson;
import com.smlearning.application.service.SysKeyService;
import com.smlearning.application.service.UserService;
import com.smlearning.domain.entity.Manager;
import com.smlearning.domain.entity.SysClass;
import com.smlearning.domain.entity.SysKey;
import com.smlearning.domain.entity.SysSubject;
import com.smlearning.domain.entity.enums.SysKeyEnum;
import com.smlearning.domain.vo.FileData;
import com.smlearning.domain.vo.SysClassVO;
import com.smlearning.domain.vo.SysKeyVO;
import com.smlearning.domain.vo.SysSubjectVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.Json;
import com.smlearning.infrastructure.utils.PageHelper;
import com.smlearning.infrastructure.utils.ParamUtils;
import com.smlearning.infrastructure.utils.WebUtil;

/**
 *数据字典控制层处理
 */
@Controller
@RequestMapping("/sysKeyController")
public class SysKeyController extends BaseController{

	static Logger logger = Logger.getLogger(UserController.class.getName());
	
	@Autowired
	private SysKeyService sysKeyService;
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 指定的上传类型   zip 和   图片格式的文件
	 */
	private static final String[] types = { "application/x-zip-compressed",
			"ZIP", "image/pjpeg","image/x-png" };  //"application/octet-stream; charset=utf-8",
	
	/***
	 * 判断文件的类型是否为指定的文件类型
	 * @return
	 */
	public boolean filterType(MultipartFile img) {
		boolean isFileType = false;
		String fileType = img.getContentType();
		System.out.println(fileType);
		for (String type : types) {
			if (type.equals(fileType)) {
				isFileType = true;
				break;
			}
		}
		return true;
	}

	public String getSavePath(HttpServletRequest request, FileData fileData) {
		String realPath = request.getRealPath(fileData.getSavePath());
		System.out.println("savePaht -- " + realPath);
		return realPath;
	}
	
	/**
	 * 跳转到数据字典管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/managesyskey")
	public String manageSysKey() {
		return "jsp/system/manageSysKey";
	}
	
	/**
	 * 获取数据字典数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(SysKeyVO sysKeyVO, PageHelper ph) {
		return sysKeyService.querySysKeyPaning(sysKeyVO, ph);
	}
	
	/**
	 * 跳转到数据字典页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		
		SysKey sysKey;
		try {
			sysKey = sysKeyService.getSysKeyById(SysKeyEnum.valueOf(Integer.parseInt(id)));
			request.setAttribute("sysKey", sysKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jsp/system/editSysKey";
	}
	
	/**
	 * 修改系统术语
	 * @param managerVO
	 * @return
	 */
	@RequestMapping("/modifysyskey")
	@ResponseBody
	public Json modifySysKey(SysKeyVO sysKeyVO){
		Json json = new Json();
		logger.info("sysKeyVO.getId()" + sysKeyVO.getId());
		
		try {
			sysKeyService.modifySysKey(sysKeyVO.getId(), sysKeyVO.getKeyValue());
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(sysKeyVO);
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
	}
	
	/**
	 * 跳转到数据字典管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manageClass")
	public String manageClass() {
		return "jsp/system/manageClass";
	}
	
	/**
	 * 跳转到数据字典管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manageSubject")
	public String manageSubject() {
		return "jsp/system/manageSubject";
	}
	
	/**
	 * 获取学习班
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGridClass")
	@ResponseBody
	public DataGrid dataGrid(SysClassVO sysClassVO, PageHelper ph) {
		return sysKeyService.querySysClassPaning(sysClassVO, ph);
	}
	
	   @RequestMapping("/getClassList")
	   @ResponseBody
	   public HashMap<String,Object> getClassList(DataGridModel dm,HttpServletRequest request) {
	        return sysKeyService.getGradeClass(dm,ParamUtils.getLongParameter(request,"gradeId",0));
	   }
	
	/**
	 * 获取科目
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGridSubject")
	@ResponseBody
	public DataGrid dataGridSubject(SysSubjectVO sysSubjectVO, PageHelper ph) {
		return sysKeyService.querySysSubjectPaning(sysSubjectVO, ph);
	}
	
	
	/**
	 * 跳转到添加教师页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPageClass")
	public ModelAndView addPageClass(HttpServletRequest request) {
	  ModelAndView view = new ModelAndView("jsp/system/addClass");
	  view.addObject("gradeId",ParamUtils.getLongParameter(request, "gradeId",0));
	  return view;
	}
	
	/**
	 * 跳转到添加教师页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPageSubject")
	public String addPageSubject(HttpServletRequest request) {
		return "jsp/system/addSubject";
	}
	
	
	/**
	 * 创建班级
	 * @param sysClassVO
	 * @return
	 */
	@RequestMapping("/createClass")
	@ResponseBody
	public Json createClass(SysClassVO sysClassVO, HttpSession session){
		Json json = new Json();
		Manager manager = (Manager) session.getAttribute("manager");
		
		try {
			sysKeyService.createSysClass(sysClassVO.getName(), manager.getId());
			json.setSuccess(true);
			json.setMsg("添加成功！");
			json.setObj(sysClassVO);
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
	}
	
	  @RequestMapping("/saveClass")
	  @ResponseBody
	  public HashMap<String, Object> saveClass(HttpServletRequest request) {
	    HashMap<String, Object> resMap = new HashMap<String, Object>();
	    HashMap<String, String> sysclass = ParamUtils.getParameters(request);
	    Manager manager = WebUtil.getLoginInfo(request);
	    if (manager != null) {
	      sysclass.put("creator", manager.getId() + "");
	    }
	    boolean res = sysKeyService.saveClass(sysclass);
	    resMap.put("success", res);
	    return resMap;
	  }
	
	
	
	/**
	 * 跳转到消息页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPageClass")
	public String editPageClass(HttpServletRequest request, String id) {
		
		SysClass sysClass;
		try {
			sysClass = sysKeyService.getSysClass(new Long(id));
			request.setAttribute("sysClass", sysClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jsp/system/editClass";
	}
	
	/**
	 * 跳转到消息页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPageSubject")
	public String editPageSubject(HttpServletRequest request, String id) {
		
		SysSubject sysSubject;
		try {
			sysSubject = sysKeyService.getSysSubject(new Long(id));
			request.setAttribute("sysSubject", sysSubject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jsp/system/editSubject";
	}
	/**
	 * 修改班级
	 * @param sysClassVO
	 * @return
	 */
	@RequestMapping("/modifyClass")
	@ResponseBody
	public Json modifyClass(SysClassVO sysClassVO){
		Json json = new Json();
		try {
			sysKeyService.modifySysClass(sysClassVO.getId(), sysClassVO.getName());
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(sysClassVO);
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * 删除班级
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/removeClass")
	@ResponseBody
	public Json removeClass(Long id) {
		Json json = new Json();
		try {
			this.sysKeyService.removeSysClass(id);
			json.setMsg("删除成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * 创建科目
	 * @param sysSubjectVO
	 * @return
	 */
	@RequestMapping("/createSubject")
	@ResponseBody
	public Json createSubject(SysSubjectVO sysSubjectVO, HttpSession session){
		Json json = new Json();
		Manager manager = (Manager) session.getAttribute("manager");
		
		try {
			sysKeyService.createSysSubject(sysSubjectVO.getName(), manager.getId());
			json.setSuccess(true);
			json.setMsg("添加成功！");
			json.setObj(sysSubjectVO);
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
	}
	
	/**
	 * 修改科目
	 * @param sysSubjectVO
	 * @return
	 */
	@RequestMapping("/modifySubject")
	@ResponseBody
	public Json modifySubject(SysSubjectVO sysSubjectVO){
		Json json = new Json();
		try {
			sysKeyService.modifySysSubject(sysSubjectVO.getId(), sysSubjectVO.getName());
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(sysSubjectVO);
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * 删除科目
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/removeSubject")
	@ResponseBody
	public Json removeSubject(Long id) {
		Json json = new Json();
		try {
			this.sysKeyService.removeSysSubject(id);
			json.setMsg("删除成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * 修改系统术语
	 * @param managerVO
	 * @return
	 */
	@RequestMapping("/createServerIP")
	@ResponseBody
	public Json createServerIP(SysKeyVO sysKeyVO){
		Json json = new Json();
		
		try {
			 SysKey sysKey = sysKeyService.createSysKey(sysKeyVO.getKeyValue());
			
			if(sysKey != null){
				Gson gson = new Gson();
				String result = gson.toJson(sysKey);
				
				json.setSuccess(true);
				json.setMsg("成功！");
				json.setObj(result);
			}
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
	}
	
	/**
	 * 修改系统术语
	 * @param managerVO
	 * @return
	 */
	@RequestMapping("/getServerIP")
	@ResponseBody
	public Json getServerIP(SysKeyVO sysKeyVO){
		Json json = new Json();
		
		try {
			 SysKey sysKey = sysKeyService.getSysKeyById(SysKeyEnum.SERVER_IP);
			
			 System.out.println(sysKey);
			 
			if(sysKey != null){
				Gson gson = new Gson();
				String result = gson.toJson(sysKey);
				
				json.setSuccess(true);
				json.setMsg("成功！");
				json.setObj(result);
			}
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
	}
	
	
	/**
	 * 取得文件夹大小
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public long getFileSize(File f) throws Exception {
		return f.length();
	}

	public String FormetFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}
	
	
	/**
	 * 上传文件操作
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multipartRequest) throws Exception {
		
		String ct  =  request.getHeader("Content-Type");
		System.out.println("Content-Type=" +ct);
		String result = "unknow error";
		
		//String path = request.getSession().getServletContext().getRealPath("homework");  
		System.out.println("orderId="+request.getParameter("orderId"));
		String userId = request.getParameter("userId");
		String questionId = request.getParameter("questionId");
		String score = request.getParameter("score");
		String isCorrect = request.getParameter("isCorrect");
		String testPaperId = request.getParameter("testPaperId");
		PrintWriter out = response.getWriter();
		
		for (Iterator<?> it = multipartRequest.getFileNames(); it.hasNext();) {
		    String key = (String) it.next();
		    String realPath = "";
		    MultipartFile orderFile = multipartRequest.getFile(key);
		    if (orderFile.getOriginalFilename().length() > 0) {
		    	logger.info("rderFile.getOriginalFilename()=="+orderFile.getOriginalFilename());
		    	
		     realPath = multipartRequest.getSession().getServletContext().getRealPath("/homework");
		     FileUtils.copyInputStreamToFile(orderFile.getInputStream(), new File(realPath, orderFile
		           .getOriginalFilename()));
		    }
		    response.getWriter().println(orderFile.getOriginalFilename());
		    
		    if(this.userService.queryUserTestPaperByIdAndUserId(new Long(questionId), new Long(userId)).size() > 0){
		    //	result = "请不要重复上传！";
		    } else {
		        userService.createUserTestPaper(new Long(userId), new Long(questionId), "/homework/" + orderFile.getOriginalFilename(), score, isCorrect, new Long(testPaperId), null);
		        result = "success !";
		    }
		
		}
		 
		
		

//		if (!filterType(img)) {
//			System.out.println("文件类型不正确");
//			request.setAttribute("typeError","您要上传的文件类型不正确");
//
//			result = "error:" + img.getContentType() + " type not upload file type";
//		} else {
//			
//			System.out.println("当前文件大小为："+ img.getSize());
//			
//		    String fileName = img.getOriginalFilename();  
//	        System.out.println(path);  
//	        File targetFile = new File(path, fileName);  
//	        if(!targetFile.exists()){  
//	            targetFile.mkdirs();  
//	        }  
//	        
//	        
//	        try {  
//	            img.transferTo(targetFile);  
//	            result = "upload file success !";
//	        } catch (Exception e) {  
//	        	result = "upload file failed ! ";
//	            e.printStackTrace();  
//	        }  
			
//			FileOutputStream fos = null;
//			FileInputStream fis = null;
//			try {
//				// 保存文件那一个路径
//				fos = new FileOutputStream(path+ "\\"+ img.getOriginalFilename());
//				fis = new FileInputStream(fileData.getImg());
//				byte[] buffer = new byte[1024];
//				int len = 0;
//				while ((len = fis.read(buffer)) > 0) {
//					fos.write(buffer, 0, len);
//				}
//				result = "upload file success !";
//			} catch (Exception e) {
//				result = "upload file failed ! ";
//				e.printStackTrace();
//			} finally {
//				fos.close();
//				fis.close();
//			}
//		}
		out.print(result);
		return null;
	}
	
	
}
