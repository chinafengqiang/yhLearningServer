package com.smlearning.domain.activity;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smlearning.application.service.CoursewareCategoryService;
import com.smlearning.domain.entity.Manager;
import com.smlearning.domain.entity.ManagerExample;
import com.smlearning.domain.entity.enums.CourseUseTypeEnum;
import com.smlearning.domain.entity.enums.ManagerDegreeEnum;
import com.smlearning.domain.entity.enums.ManagerStatusEnum;
import com.smlearning.domain.vo.ManagerVO;
import com.smlearning.infrastructure.dao.ManagerMapper;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 管理员或教师领域活动 聚合
 * @author Administrator
 */
@Repository
public class ManagerActivity {

	static Logger logger = Logger.getLogger(ManagerActivity.class.getName());
	
	//管理员基础层
	@Autowired
	private ManagerMapper managerMapper;
	
	@Autowired
	private CoursewareCategoryService coursewareCategoryService;
	
	
	
	/**
	 * 返回教师列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryManagerPaning(ManagerVO managerVO, PageHelper ph) {
		logger.info("queryManagerPaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<Manager> sList = null;
		int sCount = 0;
		try{
			ManagerExample mEx = new ManagerExample();
			
			if(StringUtils.isNotBlank(managerVO.getName())){
				
				mEx.createCriteria().andNameEqualTo(managerVO.getName().trim());
			}
			
			if(StringUtils.isNotBlank(managerVO.getActualName())){
				
				mEx.createCriteria().andActualNameEqualTo(managerVO.getActualName().trim());
			}
			if(managerVO.getDegree() != null){
				mEx.createCriteria().andDegreeEqualTo(managerVO.getDegree());
			}
					
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  managerMapper.selectByExamplePaging(mEx); 
			sCount = managerMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of queryManagerPaning",ex);
		}
		logger.info("queryManagerPaning result :" +  sList.size() );
		logger.info("queryManagerPaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	/**
	 * 创建系统管理员
	 * @return系统管理员
	 * @throws Exception 已存在系统管理员!
	 */
	public Manager createSuperManager() throws Exception {
		
		
		ManagerExample example = new ManagerExample();
		example.or().andDegreeEqualTo(ManagerDegreeEnum.SUPER_MANAGER.toValue());
		
		//获得管理员列表
		List<Manager> list = this.managerMapper.selectByExample(example);
		if(list.size() > 0){
			throw new Exception("已存在系统管理员!");
		}
		
		//创建系统管理员记录
		Manager manager = new Manager();
//		manager.setName(SysConfig.superManangerName);
//		manager.setActualName(SysConfig.superManangerActualName);
//		manager.setPassword(SysConfig.superManangerPassword);
		manager.setDegree(ManagerDegreeEnum.SUPER_MANAGER.toValue());
		manager.setStatus(ManagerStatusEnum.OPENED.toValue());
		
		this.managerMapper.insert(manager);
		
		return manager;
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
			
		//验证系统管理员的身份
		verifySuperManager(superManagerId);
		
		//查询用户名是否存在
		ManagerExample example = new ManagerExample();
		example.or().andNameEqualTo(name);
		
		//获得管理员列表
		List<Manager> list = this.managerMapper.selectByExample(example);
		if(list.size() > 0 ){
			throw new Exception("管理员帐号重复了!");
		}
		
		//查询用户名是否存在
		ManagerExample exampleActualName = new ManagerExample();
		exampleActualName.or().andActualNameEqualTo(actualName);
			
		//获得管理员列表
		List<Manager> listActualName = this.managerMapper.selectByExample(exampleActualName);
		//判断姓名是否重名
		if(listActualName.size() > 0 ){
			throw new Exception("管理员姓名不能重复!");
		}
		
		//创建管理者记录
		Manager manager = new Manager();
		manager.setName(name);
		manager.setPassword("999999");
		manager.setActualName(actualName);
		manager.setDepartment(department);
		manager.setPost(post);
		manager.setDegree(ManagerDegreeEnum.MANAGER.toValue());
		manager.setStatus(ManagerStatusEnum.OPENED.toValue());
		manager.setUseType(useType);
		manager.setClassId(classId);
		
		this.managerMapper.insert(manager);

		return manager;
	}
	
	   public Manager createManager(String name, String password, String actualName) throws Exception {
         
	        //查询用户名是否存在
	        ManagerExample example = new ManagerExample();
	        example.or().andNameEqualTo(name);
	        
	        //获得管理员列表
	        List<Manager> list = this.managerMapper.selectByExample(example);
	        if(list.size() > 0 ){
	            throw new Exception("管理员帐号重复了!");
	        }
	        
	        //查询用户名是否存在
	        ManagerExample exampleActualName = new ManagerExample();
	        exampleActualName.or().andActualNameEqualTo(actualName);
	            
	        //获得管理员列表
	        List<Manager> listActualName = this.managerMapper.selectByExample(exampleActualName);
	        //判断姓名是否重名
	        if(listActualName.size() > 0 ){
	            throw new Exception("管理员姓名不能重复!");
	        }
	        
	        //创建管理者记录
	        Manager manager = new Manager();
	        manager.setName(name);
	        manager.setPassword(password);
	        manager.setActualName(actualName);
	        manager.setUseType(0);
	        manager.setClassId(0L);
	        manager.setStatus(0);
	        manager.setDegree(ManagerDegreeEnum.SUPER_MANAGER.toValue());
	        this.managerMapper.insert(manager);

	        return manager;
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
		
		//验证系统管理员的身份
		verifySuperManager(superManagerId);
		
		//获取管理员信息
		Manager manager = this.managerMapper.selectByPrimaryKey(managerId);
		
		//判断管理员是否存在
		if (manager == null) {
			throw new Exception("无此管理员");
		}
		
		//判断是否姓名重名
		/*if (!manager.getActualName().equals(actualName)) {
			
			//查询用户名是否存在
			ManagerExample exampleActualName = new ManagerExample();
			exampleActualName.or().andActualNameEqualTo(actualName);
			
			List<Manager> list = this.managerMapper.selectByExample(exampleActualName) ;
			if (list.size() > 0) {
				throw new Exception("姓名重复");
			}
			
		}*/
		
		//修改管理者记录
		manager.setActualName(actualName);
		manager.setName(name);
		//manager.setPassword(password);
		manager.setDepartment(department);
		manager.setPost(post);
		manager.setUseType(useType);
		manager.setClassId(classId);
		
		this.managerMapper.updateByPrimaryKey(manager);

		return manager;
	}
	
	
	public Manager modifyManager(Long managerId, String name, String password, String actualName) throws Exception {
      
      
      //获取管理员信息
      Manager manager = this.managerMapper.selectByPrimaryKey(managerId);
      
      //判断管理员是否存在
      if (manager == null) {
          throw new Exception("无此管理员");
      }
      
      
      //修改管理者记录
      manager.setActualName(actualName);
      manager.setName(name);
      manager.setPassword(password);
      manager.setUseType(0);
      manager.setClassId(0L);
      manager.setStatus(0);
      manager.setDegree(ManagerDegreeEnum.SUPER_MANAGER.toValue());
      
      this.managerMapper.updateByPrimaryKey(manager);

      return manager;
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
		

		logger.info("superManagerId =" + superManagerId);
		//校验身份
		verifySuperManager(superManagerId);
		
		//获取管理员信息
		Manager manager = this.managerMapper.selectByPrimaryKey(managerId);
				
		//判断管理员是否存在
		if (manager == null) {
			throw new Exception("无此管理员");
		}
		
		//修改管理者状态
		manager.setStatus(ManagerStatusEnum.OPENED.toValue());
		
		this.managerMapper.updateByPrimaryKey(manager);
		
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

		//校验身份
		verifySuperManager(superManagerId);
		
		//获取管理员信息
		Manager manager = this.managerMapper.selectByPrimaryKey(managerId);
						
		//判断管理员是否存在
		if (manager == null) {
			throw new Exception("无此管理员");
		}
		
		if(managerId.equals(superManagerId)){
			throw new Exception("不可停用自已");
		}
		
		//修改管理者状态
		manager.setStatus(ManagerStatusEnum.CLOSED.toValue());
		
		this.managerMapper.updateByPrimaryKey(manager);
		
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
	
		//校验身份
		verifySuperManager(superManagerId);
		
		//获取管理员信息
		Manager manager = this.managerMapper.selectByPrimaryKey(managerId);
						
		//判断管理员是否存在
		if (manager == null) {
			throw new Exception("无此管理员");
		}
		
		if(managerId.equals(superManagerId)){
			throw new Exception("不可删除自已");
		}
		
		//TODO 判断管理员是否可删除(不可删除系统管理员)
		
		//删除管理员
		this.managerMapper.deleteByPrimaryKey(managerId);
		
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
		
		//获取管理员信息
		Manager manager = this.managerMapper.selectByPrimaryKey(managerId);
		
		//判断管理员是否存在
		if (manager == null) {
			throw new Exception("无此管理员");
		}
		
		//判断旧密码是否正确
		if (!manager.getPassword().equals(password)) {
			throw new Exception("密码错误!");
		}
		
		//修改管理者密码
		manager.setPassword(newPassword);
		
		this.managerMapper.updateByPrimaryKey(manager);

	}
	
	
	/**
	 * 管理员登录
	 * @param name 用户
	 * @param password 密码
	 * @return
	 * @throws Exception 异常处理
	 */
	public Manager login(String name, String password) throws Exception{
		
		 logger.info("name="+name );
		 logger.info("password="+password );
		
		Manager manager = null;
		
		//查询用户名是否存在
		ManagerExample example = new ManagerExample();
		example.createCriteria().andNameEqualTo(name);
		//example.createCriteria().andDegreeEqualTo(0);
		//获得管理员列表
		List<Manager> list = this.managerMapper.selectByExample(example);
		if(list.size() > 0){
			manager = list.get(0);
		}
		
		//判断是否存在此管理员
		if(manager == null){
			throw new Exception("无此管理员");
		}
		
		if (manager.getStatus().equals(ManagerStatusEnum.CLOSED.toValue())) {
			throw new Exception("此管理员已停用!");
		}
		
		//判断密码是否正确
		if(!manager.getPassword().equals(password)){
			throw new Exception("密码错误");
		}
		
		return manager;
		
	}
	
	
	/**
	 * 获得管理员
	 * @param sysMessageId 消息编号
	 */
	public Manager getManagerById(Long managerId) throws Exception{
		
		//获取管理员信息
		Manager manager = this.managerMapper.selectByPrimaryKey(managerId);
		
		//判断管理员是否存在
		if (manager == null) {
			throw new Exception("无此管理员");
		}
		
		return manager;
	}
	
	/**
	 * 校验超级管理员身份
	 * @param superManagerId 超级管理员编号
	 * @throws NoSuperManagerException 
	 * @throws InvalidAccessException 
	 */
	private void verifySuperManager(Long superManagerId) throws Exception {
		
		//获取超级管理员信息
		Manager superManager = this.managerMapper.selectByPrimaryKey(superManagerId);
		
		//判断超级管理员是否存在
		if (superManager == null) {
			throw new Exception("无此超级管理员!");
		}
		
		logger.info("superManager.getDegree() =" + superManager.getDegree());
		
		//判断超级管理员是否存在
		if (!superManager.getDegree().equals(ManagerDegreeEnum.SUPER_MANAGER.toValue())) {
			throw new Exception("权限不够!");
		}
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
		
		//获取管理员信息
		Manager manager = this.managerMapper.selectByPrimaryKey(managerId);
		
		//判断管理员是否存在
		if (manager == null) {
			throw new Exception("无此管理员");
		}
		
		//修改用户密码
		manager.setPassword("999999");
		
		this.managerMapper.updateByPrimaryKeySelective(manager);
		
	}
	
	
	public void removeManager(Long managerId){
	  this.managerMapper.deleteByPrimaryKey(managerId);
	} 
}
