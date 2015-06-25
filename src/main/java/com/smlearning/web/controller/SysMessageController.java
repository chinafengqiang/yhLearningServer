package com.smlearning.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.iactive.db.DataGridModel;

import com.smlearning.application.service.OnlineForumService;
import com.smlearning.application.service.SysKeyService;
import com.smlearning.application.service.SysMessageService;
import com.smlearning.domain.entity.Manager;
import com.smlearning.domain.entity.OnlineForum;
import com.smlearning.domain.entity.SysMessage;
import com.smlearning.domain.vo.SysMessageExtend;
import com.smlearning.domain.vo.SysMessageVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.DateUtil;
import com.smlearning.infrastructure.utils.Json;
import com.smlearning.infrastructure.utils.PageHelper;
import com.smlearning.infrastructure.utils.ParamUtils;

/**
 *公告通知控制层处理
 */
@Controller
@RequestMapping("/sysMessageController")
public class SysMessageController extends BaseController{
	
	static Logger logger = Logger.getLogger(SysMessageController.class.getName());

	@Autowired
	private SysMessageService sysMessageService;
	
	@Autowired
	private OnlineForumService onlineForumService;
	
	@Autowired
	private SysKeyService sysKeyService;
	
	/**
	 * 跳转到添加消息页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		return "jsp/system/addSysMessage";
	}
	
	/**
	 * 跳转到消息管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/managesysmessage")
	public String manager() {
		return "jsp/system/manageSysMessage";
	}
	
	/**
	 * 获取消息数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(SysMessageVO sysMessageVO, PageHelper ph) {
		return sysMessageService.querySysMessagePaning(sysMessageVO,ph);
	}
	
	/**
	 * 创建消息
	 * @param sysMessageVO
	 * @return
	 */
	@RequestMapping("/createsysmessage")
	@ResponseBody
	public Json createSysMessage(SysMessageVO sysMessageVO, HttpSession session){
		Json json = new Json();
		
		Manager manager = (Manager) session.getAttribute("manager");
		
		if(manager == null){
			json.setMsg("请重新登录，无此教师！");
		}
		
		try {
			sysMessageService.createSysMessage(sysMessageVO.getName(), sysMessageVO.getContent(), manager.getId());
			json.setSuccess(true);
			json.setMsg("添加成功！");
			json.setObj(sysMessageVO);
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * @param sysMessageVO
	 * @return
	 */
	@RequestMapping("/createmessage")
	@ResponseBody
	public Json createMessage(SysMessageVO sysMessageVO, HttpSession session){
		Json json = new Json();
		
		try {
			sysMessageService.createMessage(sysMessageVO.getName(), sysMessageVO.getContent(), sysMessageVO.getCanSendAll(), sysMessageVO.getCreator(), sysMessageVO.getClassId());
			json.setSuccess(true);
			json.setMsg("添加成功！");
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	
	/**
	 * @param sysMessageVO
	 * @return
	 */
	@RequestMapping("/updateMessage")
	@ResponseBody
	public Json updateMessage(Long id){
		Json json = new Json();
		
		try {
			sysMessageService.modifySysMessage(id);
			json.setSuccess(true);
			json.setMsg("更新成功！");
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * 跳转到消息页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		
		SysMessage sysMessage;
		try {
			sysMessage = sysMessageService.getSysMessage(id);
			request.setAttribute("sysMessage", sysMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jsp/system/editSysMessage";
	}
	
	/**
	 * 修改消息
	 * @param sysMessageVO
	 * @return
	 */
	@RequestMapping("/modifysysmessage")
	@ResponseBody
	public Json modifySysMessage(SysMessageVO sysMessageVO){
		Json json = new Json();
		
		try {
			sysMessageService.modifySysMessage(sysMessageVO.getId(), sysMessageVO.getName(), sysMessageVO.getContent());
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(sysMessageVO);
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
	}
	
	/**
	 * 删除消息
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/removesysmessage")
	@ResponseBody
	public Json removeSysMessage(Long id) {
		Json json = new Json();
		
		try {
			this.sysMessageService.removeSysMessage(id);
			json.setMsg("删除成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	
	/**
	 * 启用消息
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/opensysmessage")
	@ResponseBody
	public Json openSysMessage(Long id) {
		Json json = new Json();
		
		try {
			this.sysMessageService.openSysMessage(id);
			json.setMsg("启用成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * 停用消息
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/closesysmessage")
	@ResponseBody
	public Json closeSysMessage(Long id) {
		Json json = new Json();
		
		try {
			this.sysMessageService.closeSysMessage(id);
			json.setMsg("停用成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	@RequestMapping("/querySysMessage")
	@ResponseBody
	public List<SysMessageExtend> querySysMessage(Long userId, Long classId) {
		
		List<SysMessageExtend> listItem =  new ArrayList<SysMessageExtend>();
		
		List<SysMessage> list = this.sysMessageService.querySysMessage(classId);
		for(SysMessage SysMessages : list){
			SysMessageExtend sysMessage = new SysMessageExtend();
			
			sysMessage.setId(SysMessages.getId());
			sysMessage.setName(SysMessages.getName());
			sysMessage.setContent(SysMessages.getContent());
			sysMessage.setCreatedTime(DateUtil.dateToString(SysMessages.getCreatedTime(), false));
			
			listItem.add(sysMessage);
		}
		
		return listItem;
	}
	
	
	/**
	 * 跳转到消息管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manageforum")
	public String manageForum() {
		return "jsp/system/manageForum";
	}
	
	
	/**
	 * 获取消息数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGridForums")
	@ResponseBody
	public DataGrid dataGridForums(PageHelper ph) {
		return onlineForumService.queryForumPaning(ph);
	}
	
	/**
	 * 获取消息数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGridForum")
	@ResponseBody
	public DataGrid dataGridForum(PageHelper ph,  Long classId) {
		return onlineForumService.queryForumPaning(ph, classId);
	}
	
	/**
	 * 跳转到添加消息页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPageForum")
	public String addPageForum(HttpServletRequest request) {
		
		request.setAttribute("classList", sysKeyService.queryAllByClass());
		return "jsp/system/addForum";
	}
	
	
	/**
	 * @param sysMessageVO
	 * @return
	 */
	@RequestMapping("/createforum")
	@ResponseBody
	public Json createForum(OnlineForum onlineForum, HttpSession session){
		Json json = new Json();
		
		Manager manager = (Manager) session.getAttribute("manager");
		
		if(manager == null){
			json.setMsg("无此教师信息");
		}
		
		try {
			onlineForumService.createOnlineForum(onlineForum.getName(), onlineForum.getQuestion(), onlineForum.getClassId(), manager.getId());
			json.setSuccess(true);
			json.setMsg("添加成功！");
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * 跳转到消息页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPageForum")
	public String editPageForum(HttpServletRequest request, Long id) {
		
		OnlineForum onlineForum;
		try {
			onlineForum = onlineForumService.getOnlineForum(id);
			request.setAttribute("onlineForum", onlineForum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("classList", sysKeyService.queryAllByClass());
		
		return "jsp/system/editForum";
	}
	
	
	/**
	 * 修改消息
	 * @param modifyForum
	 * @return
	 */
	@RequestMapping("/modifyForum")
	@ResponseBody
	public Json modifyForum(OnlineForum onlineForum){
		Json json = new Json();
		
		try {
			onlineForumService.modifyOnlineForum(onlineForum.getId(), onlineForum.getName(), onlineForum.getQuestion(), onlineForum.getClassId());
			json.setSuccess(true);
			json.setMsg("修改成功！");
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
	}
	
	
	/**
	 * @param sysMessageVO
	 * @return
	 */
	@RequestMapping("/createchildforum")
	@ResponseBody
	public Json createChildForum(OnlineForum onlineForum, HttpSession session){
		Json json = new Json();
		
		try {
			onlineForumService.createChildOnlineForum(onlineForum.getRootId(), onlineForum.getQuestion(), onlineForum.getId(), onlineForum.getClassId());
			json.setSuccess(true);
			json.setMsg("添加成功！");
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * @param sysMessageVO
	 * @return
	 */
	@RequestMapping("/createChildUserForum")
	@ResponseBody
	public Json createChildUserForum(OnlineForum onlineForum, HttpSession session){
		Json json = new Json();
		
		try {
			onlineForumService.createChildUserForum(onlineForum.getRootId(), onlineForum.getQuestion(), onlineForum.getId());
			json.setSuccess(true);
			json.setMsg("添加成功！");
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * 获取回复列表
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGridChildForum")
	@ResponseBody
	public DataGrid dataGridChildForum(long id, PageHelper ph) {
		return onlineForumService.queryForumChildPaning(id, ph);
	}
	
	/**
	 * 删除消息
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/removeForum")
	@ResponseBody
	public Json removeForum(Long id) {
		Json json = new Json();
		
		try {
			this.onlineForumService.removeForum(id);
			json.setMsg("删除成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * 获取消息数据表格
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGridMessage")
	@ResponseBody
	public DataGrid dataGridMessage(SysMessageVO sysMessageVO, PageHelper ph) {
		return sysMessageService.queryUserSysMessagePaning(sysMessageVO,ph);
	}
	
	
	/**
	 * @param sysMessageVO
	 * @return
	 */
	@RequestMapping("/createManagerForum")
	@ResponseBody
	public Json createManagerForum(OnlineForum onlineForum){
		Json json = new Json();
		
		try {
			onlineForumService.createOnlineForum(onlineForum.getName(), onlineForum.getQuestion(), onlineForum.getClassId(), new Long(onlineForum.getCreator()));
			json.setSuccess(true);
			json.setMsg("添加成功！");
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	   @RequestMapping("/manageOnlineMessage")
	    public String managerOnlineMessage() {
	        return "jsp/system/online/manageOnline";
	    }
	   
	   @RequestMapping(value = "getOnlineMessageList")
	   @ResponseBody
	   public HashMap<String, Object> getCoursewareList(DataGridModel dm, HttpServletRequest request) {
	     HashMap<String, String> params = ParamUtils.getFilterStringParams(request);
	     HashMap<String, Object> resMap = onlineForumService.getOnlineMessageList(dm, params);
	     return resMap;
	   }
	   
	   @RequestMapping("/setOnlineStatus")
	   @ResponseBody
	   public Json setOnlineStatus(HttpServletRequest request){
	     Json json = new Json();
	     try {
	       String ids = ParamUtils.getParameter(request, "ids","");
	       int isValid = ParamUtils.getIntParameter(request, "isValid",0);
	       onlineForumService.setOnlineStatus(ids,isValid);
	       json.setSuccess(true);
	     } catch (Exception e) {
	       json.setSuccess(false);
	       json.setMsg(e.getMessage());
	     }
	     return json;
	   }
	   
	   @RequestMapping("/showReplyMessage")
	   public ModelAndView showReplyMessage(HttpServletRequest request){
	     ModelAndView mv = new ModelAndView("jsp/system/online/showReplyMessage");
	     int id = ParamUtils.getIntParameter(request, "id",0);
	     List<HashMap<String, Object>> resList = onlineForumService.getReplyMessage(id);
	     mv.addObject("replyList",resList);
	     int count = 0;
	     if(resList != null)
	       count = resList.size();
	     mv.addObject("replyCount",count);
	     return mv;
	   }
	   
	   @RequestMapping("/setOnlineReplyStatus")
       @ResponseBody
       public Json setOnlineReplyStatus(HttpServletRequest request){
         Json json = new Json();
         try {
           int id = ParamUtils.getIntParameter(request, "id",0);
           int isValid = ParamUtils.getIntParameter(request, "isValid",0);
           onlineForumService.setOnlineReplyStatus(id, isValid);
           json.setSuccess(true);
         } catch (Exception e) {
           json.setSuccess(false);
           json.setMsg(e.getMessage());
         }
         return json;
       }
	
}
