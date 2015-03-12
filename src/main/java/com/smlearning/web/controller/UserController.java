package com.smlearning.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.smlearning.application.service.SysKeyService;
import com.smlearning.application.service.UserService;
import com.smlearning.domain.entity.UserInfo;
import com.smlearning.domain.entity.userTestPaper;
import com.smlearning.domain.entity.extend.Student;
import com.smlearning.domain.vo.UserInfoVO;
import com.smlearning.infrastructure.utils.BASE64Decoder;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.DateUtil;
import com.smlearning.infrastructure.utils.FileUtil;
import com.smlearning.infrastructure.utils.ImportExecl;
import com.smlearning.infrastructure.utils.Json;
import com.smlearning.infrastructure.utils.PageHelper;
import com.smlearning.infrastructure.utils.ParamUtils;
import com.smlearning.infrastructure.utils.VersionInfo;
  
/**
 *学员控制层处理
 */
@Controller
@RequestMapping("/userController")
public class UserController extends BaseController{

	static Logger logger = Logger.getLogger(UserController.class.getName());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SysKeyService sysKeyService;
	
	/**
	 * 跳转到添加用户页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		long classId = ParamUtils.getLongParameter(request, "classId",0);
		request.setAttribute("classId",classId);
		request.setAttribute("classList", sysKeyService.queryAllByClass());
		return "jsp/system/addUser";
	}
	
	/**
	 * 跳转到用户管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manageuser")
	public String manager() {
		return "jsp/system/manageUser";
	}
	
	/**
	 * 跳转到用户页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		
		UserInfo user;
		try {
			user = userService.getUserInfoById(id);
			if(user.getBirthdate() != null){
				request.setAttribute("birthdate", DateUtil.formateDateToString(user.getBirthdate()));
			}
			request.setAttribute("user", user);
			request.setAttribute("classList", sysKeyService.queryAllByClass());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jsp/system/editUser";
	}
	
	/**
	 * 获取用户数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(UserInfoVO userVO, PageHelper ph) {
		return userService.queryUserPaning(userVO, ph);
	}
	
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping("/reg")
//	public Json reg(UserInfoVO user) {
//		Json json = new Json();
//		try {
//			userService.registerUser(user.getName(), user.getPassword(), user.getActualName(), user.getSex(), user.getAge(), 
//					user.getSuperClass(), user.getEmail(), user.getNation(), user.getBirthdate(), user.getAddress());
//			json.setSuccess(true);
//			json.setMsg("注册成功！");
//			json.setObj(user);
//		} catch (Exception e) {
//			json.setMsg(e.getMessage());
//		}
//		return json;
//	}
	
	
	/**
	 * 创建用户
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/createuser")
	public Json createUser(UserInfoVO user) {
		Json json = new Json();
		try {
			userService.createUser(user.getName(), user.getPassword(), user.getActualName(), user.getSex(), user.getAge(), 
					 user.getEmail(), user.getNation(), user.getBirthdate(), user.getAddress(), user.getClassId());
			json.setSuccess(true);
			json.setMsg("添加成功！");
			json.setObj(user);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyuser")
	public Json modifyUser(UserInfoVO user) {
		Json json = new Json();
		try {
			userService.modifyUser(user.getId(), user.getName(), user.getActualName(), user.getSex(), user.getAge(), 
					user.getEmail(), user.getNation(), user.getBirthdate(), user.getAddress(), user.getClassId());
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(user);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 删除学员
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/removeuser")
	@ResponseBody
	public Json removeUser(Long id, HttpSession session) {
		Json json = new Json();
		
		try {
			this.userService.removeUser(id);
			json.setMsg("删除成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	
	@RequestMapping("/deleteUser")
	@ResponseBody
	public Json deleteUser(HttpServletRequest request) {
		Json json = new Json();
	long[] ids = ParamUtils.getLongParameters(request, "id",0);
	try {
		this.userService.removeUsers(ids);;
		json.setMsg("删除成功！");
		json.setSuccess(true);
	} catch (Exception e) {
		json.setMsg(e.getMessage());
	}
	return json;
	}
	/**
	 * 启用用户
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/openuser")
	@ResponseBody
	public Json openUser(Long id) {
		Json json = new Json();
		
		try {
			this.userService.openUser(id);
			json.setMsg("启用成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	
	/**
	 * 停用用户
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/closeuser")
	@ResponseBody
	public Json closeUser(Long id) {
		Json json = new Json();
		
		try {
			this.userService.closeUser(id);
			json.setMsg("停用成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	
	/**
	 * 学员登录
	 * @param session
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/login")
	public Json login(HttpSession session, HttpServletRequest request) {
		Json json = new Json();
		String name = null;
		String password = null;
		
		if (request.getParameter("name") != null) {
			byte[] nameb;
			try {
				nameb = request.getParameter("name").getBytes("ISO-8859-1");
				name = new String(nameb, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				json.setMsg(e.getMessage());
			}
			 
		}

		if (request.getParameter("password") != null) {
			byte[] passwordb;
			try {
				passwordb = request.getParameter("password").getBytes(
						"ISO-8859-1");
				password = new String(passwordb, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				json.setMsg(e.getMessage());
			}
		
		}
		
		UserInfo userInfo;
		try {
				userInfo = userService.loginUser(name, password);
			if (userInfo != null) {
				json.setSuccess(true);
				json.setMsg("登陆成功！");
				
				UserInfoVO userInfoVO = new UserInfoVO();
				
				BeanUtils.copyProperties(userInfo, userInfoVO, new String[] { "birthdate" });
				userInfoVO.setBirthdate(DateUtil.dateToString(userInfo.getBirthdate(), true));
				Gson gson = new Gson();
				String result = gson.toJson(userInfoVO);
				logger.info("result=="+result);
				json.setObj(result);
	
			} else {
			    json.setSuccess(false);
				json.setMsg("用户名或密码错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		
		return json;
	}
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyuserpassword")
	public Json modifyUserPassword(UserInfoVO user) {
		Json json = new Json();
		try {
			userService.modifyUserPassword(user.getId(), user.getPassword(), user.getNewPassword());
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(user);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	
	/**
	 * 重置密码
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resetpassword")
	public Json resetPassword(HttpServletRequest request) {
		Json json = new Json();
		
		String userId = request.getParameter("id");
		if(StringUtils.isBlank(userId)){
			json.setMsg("学员名不为空！");
		}
		try {
			userService.resetPassword(new Long(userId));
			json.setSuccess(true);
			json.setMsg("重置成功,初始密码：888888");
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	 @RequestMapping(value = "downloadpdfApk")  
	 public ModelAndView downloadpdfApk(HttpServletRequest request,  
	            HttpServletResponse response) throws Exception {  
	  
	        String storeName = "FoxitPDF.apk";  
	        String realName = "PDF.apk";  
	        String contentType = "application/octet-stream";  
	  
	        FileUtil.download(request, response, storeName, contentType,  
	                realName);  
	  
	        return null;  
	    } 
	 
	 @RequestMapping(value = "downloadApk")  
	 public ModelAndView downloadApk(HttpServletRequest request,  
	            HttpServletResponse response) throws Exception {  
	  
	        String storeName = "SmartLearningClient.apk";  
	        String realName = "yhlearning.apk";  
	        String contentType = "application/octet-stream";  
	  
	        FileUtil.download(request, response, storeName, contentType,  
	                realName);  
	  
	        return null;  
	  }
	 
	 
	 /**
	 * 导入excel
	 */
	@ResponseBody
	@RequestMapping("/importFromExcel")
	public Json importFromExcel(@RequestParam MultipartFile file,HttpServletRequest request) {
		 Json json = new Json();
		ArrayList<UserInfo>  userList = null;
		long classId = ParamUtils.getLongParameter(request, "classId",0);
		try {
			userList = readExcel(file);
			
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		for(int i = 0; i < userList.size(); i++){
			UserInfo users = userList.get(i);
			
			try {
				userService.registerUser(users.getName(), "888888", users.getActualName(), "男", 20,  "123QQ.com", "汉", "", "", classId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		json.setSuccess(true);
		json.setMsg("成功导入" + userList.size() + "条记录!");
		
		return json;
		 
	 }
	 
	 
	 /**从excel中读入数据
		 * @return
		 */
		private ArrayList<UserInfo>  readExcel(MultipartFile file) throws Exception{
		
			ArrayList<UserInfo> users = new ArrayList<UserInfo>();
			ImportExecl poi = new ImportExecl();

			String extName = FileUtil.getExtName(file.getOriginalFilename());
			boolean flag;
			if(".xls".equals(extName)){
				flag = true;
			} else {
				flag = false;
			}
			
			List<List<String>> list = poi.read(file.getInputStream(), flag);
			
			if (list != null)
			{

				for (int i = 1; i < list.size(); i++)
				{
					UserInfo userInfo = new UserInfo();
					
					userInfo.setName(list.get(i).get(0));
					userInfo.setActualName(list.get(i).get(1));
					
					users.add(userInfo);
				}

			}
			
			return users;	
		}
		
		
		/**
		 * 跳转到添加用户页面
		 * @param request
		 * @return
		 */
		@RequestMapping("/impUser")
		public String impUser(HttpServletRequest request) {
			long classId = ParamUtils.getLongParameter(request, "classId",0);
			request.setAttribute("classId",classId);
			return "jsp/system/importUser";
		}
		
		
		@ResponseBody
		@RequestMapping("/getVersion")
		public Json getVersion() {
			Json json = new Json();
			String cVersion = this.userService.getMap();
			String strJSON = "{'serverVersion':'2','clientVersion':'"+cVersion+"'}"; 
			json.setSuccess(true);
			json.setObj(strJSON);
			
			return json;
		}
	 
		/**
		 * 保存试卷记录
		 * @param 
		 * @return
		 */
		@RequestMapping("/createUserTestPaper")
		@ResponseBody
		public Json createUserTestPaper(userTestPaper userTestPaper, HttpServletRequest request, HttpSession session){
			Json json = new Json();
			
			if(StringUtils.isBlank(String.valueOf(userTestPaper.getUserId()))){
				json.setMsg("用户编号不为空！");
			}
			
			try {
				if(this.userService.queryUserTestPaperByIdAndUserId(userTestPaper.getQuestionId(), userTestPaper.getUserId()).size() > 0){
					json.setSuccess(true);
					json.setMsg("请不要重复上传！");
				} else {
					this.userService.createUserTestPaper(userTestPaper.getUserId(), userTestPaper.getQuestionId(), userTestPaper.getTime(), 
							userTestPaper.getScore(), userTestPaper.getIsCorrect(), userTestPaper.getTestPaperId(), null);
					json.setSuccess(true);
					json.setMsg("上传成功！");
				}
			
			} catch (Exception e) {
				json.setMsg(e.getMessage());
			}
			
			return json;
			
		}
		
		
		/**
		 * 返回数据信息
		 * @return
		 */
		@RequestMapping("/queryUserTestPaper")
		@ResponseBody
		public List<userTestPaper> queryUserTestPaper(Long id) {
			
			List<userTestPaper> list = this.userService.queryUserTestPaper(id);
			
//			for(userTestPaper ut : list){
//				 System.out.println("ut.getNoSelectAnswer(): " + ut.getNoSelectAnswer());
//				if(ut.getNoSelectAnswer() != null){
//					byte[] b = ut.getNoSelectAnswer();
//					String uploadDir = request.getSession().getServletContext().getRealPath("/tools/1.jpg");
//					byte2image(b,uploadDir);
//				}
//			}
			
			return list;
		}
		
		private void byte2image(byte[] data,String path){
		    if(data.length<3||path.equals("")) return;
		    try{
		    FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
		    imageOutput.write(data, 0, data.length);
		    imageOutput.close();
		    System.out.println("Make Picture success,Please find image in " + path);
		    } catch(Exception ex) {
		      System.out.println("Exception: " + ex);
		      ex.printStackTrace();
		    }
		  }
		
		 
		public static boolean GenerateImage(String imgStr) throws Exception {// 对字节数组字符串进行Base64解码并生成图片
			if (imgStr == null) // 图像数据为空
				return false;
			BASE64Decoder decoder = new BASE64Decoder();
			 FileOutputStream os = new FileOutputStream(new File("D:\\123456789.txt"), true);
		     os.write((imgStr).getBytes());
			try {
				// Base64解码
				byte[] b = decoder.decodeBuffer(imgStr);
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {// 调整异常数据
						b[i] += 256;
					}
				}
				// 生成jpeg图片
				String imgFilePath = "d:\\qq.bak.jpg";// 新生成的图片
				OutputStream out = new FileOutputStream(imgFilePath);
				out.write(b);
				out.flush();
				out.close();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		
	
		/**
		 * 返回数据信息
		 * @return
		 */
		@RequestMapping("/queryStudentByClassId")
		@ResponseBody
		public List<Student> queryStudentByClassId(Long classId) {
			
			List<UserInfo> uList = this.userService.queryStudentByClassId(classId);
			List<Student> sList = new ArrayList<Student>();
			for(UserInfo ui : uList){
				Student s = new Student();
				
				s.setId(ui.getId());
				s.setName(ui.getActualName());
				
				sList.add(s);
			}
			
			return sList;
		}
		
		
		@RequestMapping("/setUserPass")
		@ResponseBody
		public Json setUserPass(HttpServletRequest request) {
			Json json = new Json();
		long[] ids = ParamUtils.getLongParameters(request, "id",0);
		try {
			this.userService.setUserPass(ids);
			json.setMsg("重置成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		return json;
		}
}
