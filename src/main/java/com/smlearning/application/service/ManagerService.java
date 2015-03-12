package com.smlearning.application.service;

import com.smlearning.domain.entity.Manager;
import com.smlearning.domain.vo.ManagerVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 管理员接口
 * @author Administrator
 */
public interface ManagerService {

	/**
	 * 管理员登录
	 * @param name 用户
	 * @param password 密码
	 * @return
	 * @throws Exception 异常处理
	 */
	public Manager login(String name, String password) throws Exception;
	
	/**
	 * 创建系统管理员
	 * @return系统管理员
	 * @throws Exception 已存在系统管理员!
	 */
	public Manager createSuperManager() throws Exception;
	
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
	public Manager createManager(String name, String password, String actualName, String department, 
			String post, Integer useType, Long classId, Long superManagerId) throws Exception;
	
	
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
	public Manager modifyManager(Long managerId, String name, String password, String actualName, String department,
			String post, Integer useType, Long classId, Long superManagerId) throws Exception;
	
	/**
	 * 启用管理员
	 * @param managerId 管理员编号
	 * @param superManagerId 超级管理员编号
	 * @throws NoManagerException 无此管理员
	 * @throws InvalidAccessException 权限不够
	 * @throws NoSuperManagerException 无此超级管理员
	 */
	public void openManager(Long managerId, Long superManagerId) throws Exception;
	
	/**
	 * 停用管理员
	 * @param managerId 管理员编号
	 * @param superManagerId 超级管理员编号
	 * @throws NoManagerException 无此管理员
	 * @throws InvalidAccessException 权限不够
	 * @throws NoSuperManagerException 无此超级管理员
	 */
	public void closeManager(Long managerId, Long superManagerId) throws Exception;
	
	/**
	 * 删除管理员
	 * @param managerId 管理员编号
	 * @param superManagerId 超级管理员编号
	 * @throws NoManagerException 无此管理员
	 * @throws InvalidAccessException 权限不够
	 * @throws NoSuperManagerException 无此超级管理员
	 */
	public void removeManager(Long managerId, Long superManagerId) throws Exception;
		
	/**
	 * 修改管理员密码
	 * @param managerId 管理员编号
	 * @param password 旧密码
	 * @param newPassword 新密码
	 * @throws NoManagerException 无此管理员
	 * @throws InvalidAccessException 权限不够
	 */
	public void modifyPassword(Long managerId, String password, String newPassword) throws Exception;
	
	/**
	 * 返回教师列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryManagerPaning(ManagerVO managerVO, PageHelper ph);
	
	/**
	 * 获得管理员
	 * @param sysMessageId 消息编号
	 */
	public Manager getManagerById(Long managerId) throws Exception;
	
	/**
	 * 重置管理员密码
	 * @param userId用户编号
	 * @param password旧密码
	 * @param newPassword新密码
	 * @throws NoUserException无此用户
	 * @throws InvalidPasswordException密码错误
	 */
	public void resetPassword(Long managerId) throws Exception;
	
	public void setUserPass(long[] ids);
	
	public void removeUser(long[] ids);
	
	 public Manager createManager(String name, String password, String actualName) throws Exception ;
	 
	 public Manager editManager(long id,String name, String password, String actualName) throws Exception ;
}
