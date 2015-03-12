package com.smlearning.application.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smlearning.application.service.ManagerService;
import com.smlearning.domain.activity.CoursewareCategoryActivity;
import com.smlearning.domain.activity.ManagerActivity;
import com.smlearning.domain.activity.SysKeyActivity;
import com.smlearning.domain.entity.CourseCategory;
import com.smlearning.domain.entity.CoursewareCategory;
import com.smlearning.domain.entity.Manager;
import com.smlearning.domain.entity.SysClass;
import com.smlearning.domain.entity.enums.CourseUseTypeEnum;
import com.smlearning.domain.vo.ManagerVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 管理员业务方法
 * @author Administrator
 */
@Service
public class ManagerServiceImpl implements ManagerService {

	static Logger logger = Logger.getLogger(ManagerServiceImpl.class.getName());
	
	@Autowired
	private ManagerActivity managerActivity;

	
	@Autowired
    private SysKeyActivity sysKeyActivity;
	
	@Autowired
	private CoursewareCategoryActivity coursewareCategoryActivity;
	/**
	 * 管理员登录
	 * @param name 用户
	 * @param password 密码
	 * @return
	 * @throws Exception 异常处理
	 */
	public Manager login(String name, String password) throws Exception {
		return managerActivity.login(name, password);
	}
	
	/**
	 * 创建系统管理员
	 * @return系统管理员
	 * @throws Exception 已存在系统管理员!
	 */
	public Manager createSuperManager() throws Exception {
		return managerActivity.createSuperManager();
	}
	
	/**
	 * 创建管理员
	 * @param name 注册帐号
	 * @param password 密码
	 * @param actualName 姓名
	 * @param department 部门
	 * @param post 职务
	 * @param superManagerId 系统管理员编号
	 * @return 管理员对象
	 * @throws Exception 管理员帐号重复了、无此系统管理员无此权限、无此系统管理员
	 */
	public Manager createManager(String name, String password, String actualName, String department, String post, Integer useType, Long classId, Long superManagerId) throws Exception {
		return managerActivity.createManager(name, password, actualName, department, post, useType, classId, superManagerId);
	}
	
	/**
	 * 修改企业管理员信息
	 * @param managerId 管理员编号
	 * @param password 密码
	 * @param actualName 姓名
	 * @param department 部门
	 * @param post 职务
	 * @return 管理员对象
	 * @throws RepeatedManagerNameException 姓名重复
	 * @throws NoManagerException 无此管理员
	 * @throws InvalidAccessException 无此权限
	 * @throws NoSuperManagerException 无此系统管理员
	 */
	public Manager modifyManager(Long managerId, String name, String password, String actualName, String department, String post, Integer useType, Long classId, Long superManagerId) throws Exception {
		return managerActivity.modifyManager(managerId, name, password, actualName, department, post, useType, classId, superManagerId);
	}
	

	/**
	 * 启用管理员
	 * @param managerId 管理员编号
	 * @param superManagerId 超级管理员编号
	 * @throws NoManagerException 无此管理员
	 * @throws InvalidAccessException 权限不够
	 * @throws NoSuperManagerException 无此超级管理员
	 */
	public void openManager(Long managerId, Long superManagerId) throws Exception{
		managerActivity.openManager(managerId, superManagerId);
	}
	
	/**
	 * 停用管理员
	 * @param managerId 管理员编号
	 * @param superManagerId 超级管理员编号
	 * @throws NoManagerException 无此管理员
	 * @throws InvalidAccessException 权限不够
	 * @throws NoSuperManagerException 无此超级管理员
	 */
	public void closeManager(Long managerId, Long superManagerId) throws Exception {
		managerActivity.closeManager(managerId, superManagerId);
	}

	/**
	 * 删除管理员
	 * @param managerId 管理员编号
	 * @param superManagerId 超级管理员编号
	 * @throws NoManagerException 无此管理员
	 * @throws InvalidAccessException 权限不够
	 * @throws NoSuperManagerException 无此超级管理员
	 */
	public void removeManager(Long managerId, Long superManagerId) throws Exception {
		managerActivity.removeManager(managerId, superManagerId);
	}
	
	/**
	 * 修改管理员密码
	 * @param managerId 管理员编号
	 * @param password 旧密码
	 * @param newPassword 新密码
	 * @throws NoManagerException 无此管理员
	 * @throws InvalidAccessException 权限不够
	 */
	public void modifyPassword(Long managerId, String password, String newPassword) throws Exception {
		managerActivity.modifyPassword(managerId, password, newPassword);
	}
	
	/**
	 * 返回教师列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryManagerPaning(ManagerVO managerVO, PageHelper ph) {
	  DataGrid dg =  managerActivity.queryManagerPaning(managerVO, ph);
	  if(dg != null){
	    List<Manager> mList = (List<Manager>)dg.getRows();
	    if(mList!=null){
	      HashMap<String,String> clsMap = getUserClass();
	      HashMap<String,String> ctMap = getCategory();
	      for(Manager m : mList){
	        String dept = m.getDepartment();
	        String deptName = "";
	        if(StringUtils.isNotBlank(dept)){
	          String[] deptArr = dept.split(",");
	          for(String dp : deptArr){
	             String name = clsMap.get(dp);
	             if(StringUtils.isNotBlank(name))
	               deptName+=name+"  ";
	          }
	        }
	        m.setDeptName(deptName);
	        
	        String post = m.getPost();
	        String postName = "";
	        if(StringUtils.isNotBlank(post)){
	           postName = ctMap.get(post);
	        }
	        m.setPostName(postName);
	      }
	    }
	  }
	  return dg;
	}
		
	private HashMap<String,String> getUserClass(){
	  List<SysClass> sysclasss = sysKeyActivity.queryAllByClass();
	  HashMap<String,String> map= new HashMap<String, String>();
	  if(sysclasss != null){
	    for(SysClass sysclass :sysclasss ){
	      map.put(sysclass.getId()+"",sysclass.getName());
	    }
	  }
	  return map;
	}
	
	   private HashMap<String,String> getCategory(){
	     List<CoursewareCategory> categoryList = coursewareCategoryActivity.getCoursewareCategoryList(CourseUseTypeEnum.Courseware.toValue());;
	      HashMap<String,String> map= new HashMap<String, String>();
	      if(categoryList != null){
	        for(CoursewareCategory ct :categoryList ){
	          map.put(ct.getId()+"",ct.getName());
	        }
	      }
	      return map;
	    }
	
	/**
	 * 获得管理员
	 * @param sysMessageId 消息编号
	 */
	public Manager getManagerById(Long managerId) throws Exception{
		return managerActivity.getManagerById(managerId);
	}
	
	/**
	 * 重置管理员密码
	 * @param userId用户编号
	 * @param password旧密码
	 * @param newPassword新密码
	 * @throws NoUserException无此用户
	 * @throws InvalidPasswordException密码错误
	 */
	public void resetPassword(Long managerId) throws Exception{
		managerActivity.resetPassword(managerId);
	}

	@Override
	public void setUserPass(long[] ids) {
		try {
			if(ids != null){
				for(long id : ids){
					resetPassword(id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void removeUser(long[] ids) {
		try {
			if(ids != null){
				for(long id : ids){
				  this.managerActivity.removeManager(id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	  public Manager createManager(String name, String password, String actualName)throws Exception {
	    return this.managerActivity.createManager(name, password, actualName);
	  }

    @Override
    public Manager editManager(long id, String name, String password, String actualName)
        throws Exception {
     return this.managerActivity.modifyManager(id, name, password, actualName);
    }
	  
	  
}
