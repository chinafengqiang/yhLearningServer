package com.smlearning.application.service;

import java.util.List;

import com.smlearning.domain.entity.UserInfo;
import com.smlearning.domain.entity.userTestPaper;
import com.smlearning.domain.vo.UserInfoVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;



/**
 * 学员业务接口
 * @author Administrator
 */
public interface UserService {
	
	public String getMap();

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
			 String email, String nation, String birthdate, String address, Long classId) throws Exception;
	
	
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
			 String email, String nation, String birthdate, String address,Long classId) throws Exception;
	
	
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
			 String email, String nation, String birthdate,String address,Long classId) throws Exception;
	
	
	/** 
	 * 删除用户
	 * @param userId用户编号
	 * @throws NoUserException无此用户
	 */
	public void removeUser(Long userId) throws Exception;
	
	/**
	 * 启用用户
	 * @param userId用户编号
	 * @throws NoUserException 无此用户
	 */
	public void openUser(Long userId) throws Exception;
	
	/**
	 * 停用用户
	 * @param userId用户编号
	 * @throws NoUserException无此用户
	 */
	public void closeUser(Long userId) throws Exception;
	
	/**
	 * 用户登录
	 * @param name用户帐户
	 * @param password密码
	 * @return
	 * @throws NoUserException无此用户
	 * @throws PasswordInvalidException 登录密码错误
	 * @throws UserNoOpenedException 此用户已停用
	 */
	public UserInfo loginUser(String name, String password) throws Exception;
	
	/**
	 * 修改用户密码
	 * @param userId用户编号
	 * @param password旧密码
	 * @param newPassword新密码
	 * @throws NoUserException无此用户
	 * @throws InvalidPasswordException密码错误
	 */
	public void modifyUserPassword(Long userId, String password, String newPassword) throws Exception;
	
	/**
	 * 返回学员列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryUserPaning(UserInfoVO userVO, PageHelper ph);
	
	/**
	 * 获得学员
	 * @param userId 学员编号
	 */
	public UserInfo getUserInfoById(Long userId) throws Exception;
	
	/**
	 * 重置用户密码
	 * @param userId用户编号
	 * @param password旧密码
	 * @param newPassword新密码
	 * @throws NoUserException无此用户
	 * @throws InvalidPasswordException密码错误
	 */
	public void resetPassword(Long userId) throws Exception;
	
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
			Long testPaperId, byte[] noSelectAnswer) throws Exception;
	
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<userTestPaper> queryUserTestPaper(Long testPaperId);
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<UserInfo> queryStudentByClassId(Long classId);
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<userTestPaper> queryUserTestPaperByIdAndUserId(Long questionId, Long userId);
	
	public void removeUsers(long[] ids);
	
	public void setUserPass(long[] ids);
}
