package com.smlearning.web.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.smlearning.application.service.ManagerService;
import com.smlearning.application.service.SysKeyService;
import com.smlearning.domain.entity.Manager;
import com.smlearning.domain.vo.ManagerVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.Json;
import com.smlearning.infrastructure.utils.PageHelper;
import com.smlearning.infrastructure.utils.ParamUtils;

/**
 *管理员控制层处理
 */
@Controller
@RequestMapping("/managerController")
public class ManagerController extends BaseController{

	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private SysKeyService sysKeyService;

	static Logger logger = Logger.getLogger(ManagerController.class.getName());
	
	
	/**
	 * 退出
	 */
	@RequestMapping("/loginout")
	public String loginout(HttpServletRequest request){
		
		request.getSession().invalidate();
		
		return "login";
	}
	
	/**
	 * 管理员登录
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login.html")
	public Json login(HttpServletRequest request, HttpSession session, HttpServletResponse response){
	
		Json json = new Json();
		String name = request.getParameter("name");
		if(StringUtils.isBlank(name)){
			json.setMsg("用户名不为空！");
		}
		
		String password = request.getParameter("password");
		if(StringUtils.isBlank(password)){
			json.setMsg("密码不为空！");
		}
		
		//验证业务方法
		try {
			Manager manager = managerService.login(name, password);
			if(manager != null){
				json.setSuccess(true);
				json.setMsg("登录成功！");
				
				session.setAttribute("manager", manager);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg(e.getMessage());
			logger.info(e.getMessage());
			
		}
		
		return  json;
		
	}
	
	
	/**
	 * 学员登录
	 * @param session
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loginManager")
	public Json loginManager(HttpSession session, HttpServletRequest request) {
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
		
		Manager manager;
		try {
				manager = managerService.login(name, password);
			if (manager != null) {
				json.setSuccess(true);
				json.setMsg("登陆成功！");
				Gson gson = new Gson();
				String result = gson.toJson(manager);
				logger.info("result=="+result);
				json.setObj(result);
	
			} else {
				json.setMsg("用户名或密码错误！");
			}
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
	}
	
	
	/**
	 * 跳转到教师管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/managemanager")
	public String manageManager() {
		return "jsp/system/manageManager";
	}
	
	   @RequestMapping("/managerAdmin")
	    public String manageUser() {
	        return "jsp/system/managerAdmin";
	    }
	
	/**
	 * 获取教师数据表格
	 * 
	 * @param managerVO
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(ManagerVO managerVO, PageHelper ph) {
	 // managerVO.setDegree(1);
	  DataGrid dg =  managerService.queryManagerPaning(managerVO, ph);
	  return dg;
	}
	
	/**
	 * 跳转到添加教师页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		request.setAttribute("classList", sysKeyService.queryAllByClass());
		return "jsp/system/addManager";
	}
	
	/**
	 * 跳转到消息页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		
		Manager manager;
		try {
			manager = managerService.getManagerById(new Long(id));
			request.setAttribute("manager", manager);
			request.setAttribute("classList", sysKeyService.queryAllByClass());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jsp/system/editManager";
	}
	
	
	   @RequestMapping("/editAdmin")
	    public String editAdmin(HttpServletRequest request, String id) {
	        
	        Manager manager;
	        try {
	            manager = managerService.getManagerById(new Long(id));
	            request.setAttribute("manager", manager);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return "jsp/system/editAdmin";
	    }
	
	/**
	 * 添加教师
	 * @param managerVO
	 * @return
	 */
	@RequestMapping("/createmanager")
	@ResponseBody
	public Json createManager(ManagerVO managerVO, HttpSession session){
		Json json = new Json();
		Manager manager = (Manager) session.getAttribute("manager");
		try {
			this.managerService.createManager(managerVO.getName(), managerVO.getPassword(), managerVO.getActualName(), 
					managerVO.getDepartment(),managerVO.getPost(), managerVO.getUseType(), managerVO.getClassId(), manager.getId());
			
			json.setSuccess(true);
			json.setMsg("添加成功！");
			json.setObj(managerVO);
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	 @RequestMapping("/addAdmin")
	public String addAdmin(){
	  return "jsp/system/addAdmin";
	}
	
	   @RequestMapping("/createAdmin")
	    @ResponseBody
	    public Json createAdmin(ManagerVO managerVO, HttpSession session){
	        Json json = new Json();
	        try {
	            this.managerService.createManager(managerVO.getName(), managerVO.getPassword(), managerVO.getActualName());
	            
	            json.setSuccess(true);
	            json.setMsg("添加成功！");
	            json.setObj(managerVO);
	        
	        } catch (Exception e) {
	            json.setMsg(e.getMessage());
	        }
	        
	        return json;
	        
	    }
	    
	
       @RequestMapping("/modifyAdmin")
       @ResponseBody
       public Json modifyAdmin(ManagerVO managerVO, HttpSession session){
           Json json = new Json();
           try {
               this.managerService.editManager(managerVO.getId(), managerVO.getName(), managerVO.getPassword(), managerVO.getActualName());
               
               json.setSuccess(true);
               json.setMsg("修改成功！");
               json.setObj(managerVO);
           
           } catch (Exception e) {
               json.setMsg(e.getMessage());
           }
           
           return json;
           
       }
	
	/**
	 * 修改教师
	 * @param managerVO
	 * @return
	 */
	@RequestMapping("/modifymanager")
	@ResponseBody
	public Json modifyManager(ManagerVO managerVO, HttpSession session){
		Json json = new Json();
		Manager manager = (Manager) session.getAttribute("manager");
		try {
			this.managerService.modifyManager(managerVO.getId(), managerVO.getName(), managerVO.getPassword(), 
					managerVO.getActualName(), managerVO.getDepartment(), managerVO.getPost(), 
					managerVO.getUseType(), managerVO.getClassId(), manager.getId());
			
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(managerVO);
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	
	/**
	 * 启用管理员
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/openmanager")
	@ResponseBody
	public Json openManager(Long id, HttpSession session) {
		Json json = new Json();
		
		Manager manager = (Manager) session.getAttribute("manager");
		
		try {
			this.managerService.openManager(id, manager.getId());
			json.setMsg("启用成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * 停用管理员
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/closemanager")
	@ResponseBody
	public Json closeManager(Long id, HttpSession session) {
		Json json = new Json();
		
		Manager manager = (Manager) session.getAttribute("manager");
		
		try {
			this.managerService.closeManager(id, manager.getId());
			json.setMsg("停用成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * 删除管理员
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/removemanager")
	@ResponseBody
	public Json removeManager(Long id, HttpSession session) {
		Json json = new Json();
		
		Manager manager = (Manager) session.getAttribute("manager");
		
		try {
			this.managerService.removeManager(id, manager.getId());
			json.setMsg("删除成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	
	   @RequestMapping("/removeAdmin")
	    @ResponseBody
	    public Json removeAdmin(Long id, HttpSession session) {
	        Json json = new Json();
	        
	        try {
	           long[] ids = {id};
	            this.managerService.removeUser(ids);
	            json.setMsg("删除成功！");
	            json.setSuccess(true);
	        } catch (Exception e) {
	            json.setMsg(e.getMessage());
	        }
	        
	        return json;
	        
	    }
	
	/**
	 * 修改管理员密码
	 * @param managerVO
	 * @return
	 */
	@RequestMapping("/modifypassword")
	@ResponseBody
	public Json modifyPassword(ManagerVO managerVO){
		Json json = new Json();
		
		try {
			this.managerService.modifyPassword(managerVO.getId(), managerVO.getPassword(),
					managerVO.getNewPassword());
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(managerVO);
		
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
		
		String manageId = request.getParameter("id");
		if(StringUtils.isBlank(manageId)){
			json.setMsg("管理员名不为空！");
		}
		try {
			managerService.resetPassword(new Long(manageId));
			json.setSuccess(true);
			json.setMsg("重置成功,初始密码：999999");
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping("/setUserPass")
	@ResponseBody
	public Json setUserPass(HttpServletRequest request) {
		Json json = new Json();
	long[] ids = ParamUtils.getLongParameters(request, "id",0);
	try {
		this.managerService.setUserPass(ids);
		json.setMsg("重置成功！");
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
		this.managerService.removeUser(ids);;
		json.setMsg("删除成功！");
		json.setSuccess(true);
	} catch (Exception e) {
		json.setMsg(e.getMessage());
	}
	return json;
	}
}
