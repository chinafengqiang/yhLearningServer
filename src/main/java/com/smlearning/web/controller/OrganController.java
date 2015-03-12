package com.smlearning.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smlearning.application.service.OrganService;
import com.smlearning.domain.entity.Organ;
import com.smlearning.domain.vo.OrganVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.Json;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 *管理员控制层处理
 */
@Controller
@RequestMapping("/organController")
public class OrganController extends BaseController{

	@Autowired
	private OrganService organService;
	
	/**
	 * 跳转到添加本级单位页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/addselfpage")
	public String addSelfPage(HttpServletRequest request) {
		
		Organ organ;
		try {
			organ = this.organService.getOrganSlefById();
			request.setAttribute("organ", organ);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "jsp/system/addSelfOrgan";
	}
	
	/**
	 * 跳转到添加上级单位页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/addHigherPage")
	public String addHigherPage(HttpServletRequest request) {
		return "jsp/system/addHigherPage";
	}
	
	/**
	 * 跳转到上级单位管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/managehigherorgan")
	public String manageHigherOrgan() {
		return "jsp/system/manageHigherOrgan";
	}
	
	/**
	 * 获取上级单位数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/datahighergrid")
	@ResponseBody
	public DataGrid dataHigherGrid(OrganVO organVO, PageHelper ph) {
		return organService.queryHigherOrganPaning(organVO, ph);
	}
	
	/**
	 * 获取下级单位数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/datalowergrid")
	@ResponseBody
	public DataGrid dataLowerGrid(OrganVO organVO, PageHelper ph) {
		return organService.queryLowerOrganPaning(organVO, ph);
	}
	
	/**
	 * 跳转到添加下级单位页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/addLowerPage")
	public String addLowerPage(HttpServletRequest request) {
		return "jsp/system/addLowerPage";
	}
	
	/**
	 * 跳转到下级单位管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/managelowerorgan")
	public String manageLowerOrgan() {
		return "jsp/system/manageLowerOrgan";
	}
	
	/**
	 * 跳转到上级单位页面
	 * 
	 * @return
	 */
	@RequestMapping("/edithigherpage")
	public String editHigherPage(HttpServletRequest request, Long id) {
		Organ organ;
		try {
			organ = this.organService.getOrganById(id);
			request.setAttribute("organ", organ);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "jsp/system/editHigherPage";
	}
	
	/**
	 * 跳转到下级单位页面
	 * 
	 * @return
	 */
	@RequestMapping("/editLowerPage")
	public String editLowerPage(HttpServletRequest request, Long id) {
		Organ organ;
		try {
			organ = this.organService.getOrganById(id);
			request.setAttribute("organ", organ);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "jsp/system/editLowerPage";
	}
	
	/**
	 * 修改本单位
	 * @param organVO
	 * @return
	 */
	@RequestMapping("/modifyselforgan")
	@ResponseBody
	public Json modifySelfOrgan(OrganVO organVO){
		Json json = new Json();
		
		try {
			organService.modifySelfOrgan(organVO.getName(), organVO.getGrade(), organVO.getDepartment(),
					organVO.getLinkman(), organVO.getTel());
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(organVO);
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
	}
	
	/**
	 * 修改上级单位
	 * @param organVO
	 * @return
	 */
	@RequestMapping("/modifyhigherorgan")
	@ResponseBody
	public Json modifyHigherOrgan(OrganVO organVO){
		Json json = new Json();
		
		try {
			organService.modifyHigherOrgan(organVO.getId(), organVO.getName(), organVO.getGrade(), organVO.getDepartment(),
					organVO.getLinkman(), organVO.getTel(), organVO.getServerIp(), organVO.getServerPort());
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(organVO);
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
	}
	
	/**
	 * 创建下级单位
	 * @param organVO
	 * @return
	 */
	@RequestMapping("/createlowerorgan")
	@ResponseBody
	public Json createLowerOrgan(OrganVO organVO){
		Json json = new Json();
		
		try {
			this.organService.createLowerOrgan(organVO.getName(), organVO.getGrade(), organVO.getDepartment(), organVO.getLinkman(), 
					organVO.getTel(), organVO.getServerIp(), organVO.getServerPort());
			
			json.setSuccess(true);
			json.setMsg("添加成功！");
			json.setObj(organVO);
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * 修改下级单位
	 * @param managerVO
	 * @return
	 */
	@RequestMapping("/modifyLowerOrgan")
	@ResponseBody
	public Json modifyLowerOrgan(OrganVO organVO){
		Json json = new Json();
		
		try {
			organService.modifyLowerOrgan(organVO.getId(), organVO.getName(), organVO.getGrade(), 
					organVO.getDepartment(), organVO.getLinkman(), organVO.getTel(), organVO.getServerIp(), organVO.getServerPort());
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(organVO);
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
	}
	
	/**
	 * 删除下级单位
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/removelowerorgan")
	@ResponseBody
	public Json removeLowerOrgan(Long id, HttpSession session) {
		Json json = new Json();
		
		try {
			this.organService.removeLowerOrgan(id);
			json.setMsg("删除成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	/**
	 * 创建上级单位
	 * @param organVO
	 * @return
	 */
	@RequestMapping("/createhigherorgan")
	@ResponseBody
	public Json createHigherOrgan(OrganVO organVO){
		Json json = new Json();
		
		try {
			this.organService.createHigherOrgan(organVO.getName(), organVO.getGrade(), organVO.getDepartment(), organVO.getLinkman(), 
					organVO.getTel(), organVO.getServerIp(), organVO.getServerPort());
			
			json.setSuccess(true);
			json.setMsg("添加成功！");
			json.setObj(organVO);
		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	
	/**
	 * 删除上级单位
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/removehigherorgan")
	@ResponseBody
	public Json removeHigherOrgan(Long id, HttpSession session) {
		Json json = new Json();
		
		try {
			this.organService.removeHigherOrgan(id);
			json.setMsg("删除成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	
	

}
