package com.smlearning.application.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.IACDB;

import com.smlearning.application.service.UserService;
import com.smlearning.domain.activity.UserActivity;
import com.smlearning.domain.entity.UserInfo;
import com.smlearning.domain.entity.userTestPaper;
import com.smlearning.domain.vo.UserInfoVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 学员业务方法
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService{

	static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
	
	@Autowired
	private UserActivity userActivity;
	@Autowired
	private IACDB<HashMap<String,Object>> iacDB;
	
	private String getApplicationProperty(String propertyName) {
		
		org.springframework.core.io.Resource resource = new ClassPathResource("config/version.properties");
		try {
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			return props.getProperty(propertyName);
		} catch (IOException e) {
			return "";
		}
	}
	

	public String getMap(){
		String version = getApplicationProperty("CLIENT_VERSION");
		return version;
		
	}
	
	/**
	 * 注册用户
	 * @param name帐号
	 * @param actualName密码
	 * @param sex性别
	 * @param age年龄
	 * @param organ单位
	 * @param department部门
	 * @param post职务
	 * @param email电子邮件
	 * @param tel电话
	 * @return
	 * @throws RepeatUserNameException 学员帐号重复
	 */
	public UserInfo registerUser(String name, String password, String actualName,String sex, Integer age,
			 String email, String nation, String birthdate, String address, Long classId) throws Exception{
		return userActivity.registerUser(name, password, actualName, sex, age, email, nation, birthdate, address, classId);
	}
		
	/**
	 * 创建用户
	 * @param name帐号
	 * @param actualName密码
	 * @param sex性别
	 * @param age年龄
	 * @param organ单位
	 * @param department部门
	 * @param post职务
	 * @param email电子邮件
	 * @param tel电话
	 * @return
	 * @throws RepeatUserNameException 学员帐号重复
	 */
	public UserInfo createUser(String name, String password, String actualName,String sex, Integer age,
			 String email, String nation, String birthdate, String address, Long classId) throws Exception{
		return userActivity.createUser(name, password, actualName, sex, age, email, nation, birthdate, address,classId);
	}
	
	
	/**
	 * 修改用户
	 * @param userId用户编号
	 * @param name帐号
	 * @param actualName密码
	 * @param sex性别
	 * @param age年龄
	 * @param organ单位
	 * @param department部门
	 * @param post职务
	 * @param email电子邮件
	 * @param tel电话
	 * @return
	 * @throws NoUserException 无此用户
	 * @throws RepeatUserNameException学员帐号重复
	 */
	public UserInfo modifyUser(Long userId, String name, String actualName,String sex, Integer age,
			 String email, String nation, String birthdate, String address,Long classId) throws Exception{
		return userActivity.modifyUser(userId, name, actualName, sex, age, email, nation, birthdate, address,classId);
		
	}
	
	/** 
	 * 删除用户
	 * @param userId用户编号
	 * @throws NoUserException无此用户
	 */
	public void removeUser(Long userId) throws Exception{
		userActivity.removeUser(userId);
	}
	
	/**
	 * 启用用户
	 * @param userId用户编号
	 * @throws NoUserException 无此用户
	 */
	public void openUser(Long userId) throws Exception{
		userActivity.openUser(userId);
	}
	
	/**
	 * 停用用户
	 * @param userId用户编号
	 * @throws NoUserException无此用户
	 */
	public void closeUser(Long userId) throws Exception{
		userActivity.closeUser(userId);
	}
	
	/**
	 * 用户登录
	 * @param name用户帐户
	 * @param password密码
	 * @return
	 * @throws NoUserException无此用户
	 * @throws PasswordInvalidException 登录密码错误
	 * @throws UserNoOpenedException 此用户已停用
	 */
	public UserInfo loginUser(String name, String password) throws Exception{
		return userActivity.loginUser(name, password);
	}
		
	
	/**
	 * 修改用户密码
	 * @param userId用户编号
	 * @param password旧密码
	 * @param newPassword新密码
	 * @throws NoUserException无此用户
	 * @throws InvalidPasswordException密码错误
	 */
	public void modifyUserPassword(Long userId, String password, String newPassword) throws Exception{
		userActivity.modifyUserPassword(userId, password, newPassword);
	}
	
	/**
	 * 返回学员列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryUserPaning(UserInfoVO userVO, PageHelper ph) {
		return userActivity.queryUserPaning(userVO, ph);
	}
	
	/**
	 * 获得学员
	 * @param userId 学员编号
	 */
	public UserInfo getUserInfoById(Long userId) throws Exception{
		return userActivity.getUserInfoById(userId);
	}
	
	/**
	 * 重置用户密码
	 * @param userId用户编号
	 * @param password旧密码
	 * @param newPassword新密码
	 * @throws NoUserException无此用户
	 * @throws InvalidPasswordException密码错误
	 */
	public void resetPassword(Long userId) throws Exception{
		userActivity.resetPassword(userId);
	}
	
	/**
	 * 保存学习记录
	 * @param userId
	 * @param questionId
	 * @param time
	 * @param score
	 * @param isCorrect
	 * @param testPaperId
	 * @param noSelectAnswer
	 * @return
	 * @throws Exception
	 */
	public userTestPaper createUserTestPaper(Long userId, Long questionId, String time,String score, String isCorrect,
			Long testPaperId, byte[] noSelectAnswer) throws Exception{
		return userActivity.createUserTestPaper(userId, questionId, time, score, isCorrect, testPaperId, noSelectAnswer);
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<userTestPaper> queryUserTestPaperByIdAndUserId(Long questionId, Long userId) {
		return userActivity.queryUserTestPaperByIdAndUserId(questionId, userId);
	}
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<userTestPaper> queryUserTestPaper(Long testPaperId) {
		return userActivity.queryUserTestPaper(testPaperId);
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<UserInfo> queryStudentByClassId(Long classId) {
		return userActivity.queryStudentByClassId(classId);
	}


	@Override
	public void removeUsers(long[] ids) {
		//String delIds = null;
		//HashMap<String,Object> params = new HashMap<String, Object>();
		try {
			if(ids != null){
				for(long id : ids){
					//delIds += ","+id;
						removeUser(id);
				}
				//params.put("ids",delIds);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//iacDB.delete("deleteUser", params);
	}


	@Override
	public void setUserPass(long[] ids) {
		//String delIds = null;
		//HashMap<String,Object> params = new HashMap<String, Object>();
		try {
			if(ids != null){
				for(long id : ids){
					//delIds += ","+id;
					resetPassword(id);
				}
				//params.put("ids",delIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//iacDB.delete("setUserPass", params);
	}


  @Override
  public List<HashMap<String, Object>> getClassTearch(int classId) {
    HashMap<String,Object> params = new HashMap<String,Object>();
    params.put("classId",classId);
    return iacDB.getList("getClassTearch", params);
  }
	
	
	
	
	
	
}
