package com.smlearning.domain.activity;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smlearning.datasync.IDataSync;
import com.smlearning.domain.entity.UserInfo;
import com.smlearning.domain.entity.UserInfoExample;
import com.smlearning.domain.entity.userTestPaper;
import com.smlearning.domain.entity.userTestPaperExample;
import com.smlearning.domain.entity.enums.UserStatusEnum;
import com.smlearning.domain.vo.UserInfoVO;
import com.smlearning.infrastructure.dao.UserInfoMapper;
import com.smlearning.infrastructure.dao.userTestPaperMapper;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.DateUtil;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 学员领域活动 聚合
 * @author Administrator
 */
@Repository
public class UserActivity {

	static Logger logger = Logger.getLogger(UserActivity.class.getName());
	
	//学员基础层
	@Autowired
	private UserInfoMapper userMapper;
	
	//学员基础层
	@Autowired
	private userTestPaperMapper userTestPaperMapper;

	
	/**
	 * 返回学员列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryUserPaning(UserInfoVO userVO, PageHelper ph) {
		logger.info("queryUserPaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<UserInfo> sList = null;
		int sCount = 0;
		try{
			UserInfoExample mEx = new UserInfoExample();
			if(StringUtils.isNotBlank(userVO.getName())){
				
				mEx.or().andNameEqualTo(userVO.getName().trim());
			}
			
			if(StringUtils.isNotBlank(userVO.getActualName())){
				mEx.or().andActualNameEqualTo(userVO.getActualName().trim());
			}
			if(userVO.getClassId() > 0){
				mEx.or().andClassIdEqualTo(userVO.getClassId());
			}
			
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  userMapper.selectByExamplePaging(mEx); 
			sCount = userMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of queryUserPaning",ex);
		}
		logger.info("queryUserPaning result :" +  sList.size() );
		logger.info("queryUserPaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
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
		
		
		UserInfoExample example = new UserInfoExample();
		example.or().andNameEqualTo(name);
		
		List<UserInfo> list = this.userMapper.selectByExample(example);
		
		logger.info("userInfo list="+list.size());
		//验证帐号是否相同
		if(list.size() > 0){
			throw new Exception("学员帐号重复！");
		}
		
		UserInfo user = new UserInfo();
		
		user.setName(name);
		user.setPassword(password);
		user.setActualName(actualName);
		user.setSex(sex);
		user.setAge(age);
		user.setEmail(email);
		user.setStatus(UserStatusEnum.OPENED.toValue());
		user.setNation(nation);
		user.setBirthdate(DateUtil.stringsToDate(birthdate));
		user.setAddress(address);
		user.setClassId(classId);
		
		this.userMapper.insert(user);
		
		return user;
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
		
		UserInfoExample example = new UserInfoExample();
		example.or().andNameEqualTo(name);
		
		List<UserInfo> list = this.userMapper.selectByExample(example);
		
		logger.info("userInfo list="+list.size());
		//验证帐号是否相同
		if(list.size() > 0){
			throw new Exception("学员帐号重复！");
		}
		
		UserInfo user = new UserInfo();
		
		user.setName(name);
		user.setPassword("888888");
		user.setActualName(actualName);
		user.setSex(sex);
		user.setAge(age);
		user.setEmail(email);
		user.setStatus(UserStatusEnum.OPENED.toValue());
		user.setNation(nation);
		user.setBirthdate(DateUtil.stringsToDate(birthdate));
		user.setAddress(address);
		user.setClassId(classId);
		
		this.userMapper.insert(user);
		
		return user;
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
			 String email, String nation, String birthdate, String address, Long classId) throws Exception{
		
		//判断是否存在此用户,若不存在则抛异常
		UserInfo user = this.userMapper.selectByPrimaryKey(userId);
		
		if(user == null){
			throw new Exception("无此学员！");
		}

		UserInfo userTemp = null;
		
		UserInfoExample example = new UserInfoExample();
		example.or().andNameEqualTo(name);
		
		List<UserInfo> list = this.userMapper.selectByExample(example);
		if(list.size() > 0){
			userTemp = list.get(0);
		}
		
		//验证帐号是否相同
		if (userTemp != null) {
			if (!userTemp.getId().equals(userId)) {
				throw new Exception("学员帐号重复！");
			}
		}
		
		user.setName(name);
		user.setActualName(actualName);
		user.setSex(sex);
		user.setAge(age);
		user.setEmail(email);
		user.setNation(nation);
		user.setBirthdate(DateUtil.stringsToDate(birthdate));
		user.setAddress(address);
		user.setClassId(classId);
		
		this.userMapper.updateByPrimaryKey(user);
		
		return user;
		
	}
	
	/** 
	 * 删除用户
	 * @param userId用户编号
	 * @throws NoUserException无此用户
	 */
	public void removeUser(Long userId) throws Exception{
		
		//判断是否存在此用户,若不存在则抛异常
		UserInfo user = this.userMapper.selectByPrimaryKey(userId);
		
		if(user == null){
			throw new Exception("无此学员！");
		}
		
		//编写存储过程
		
		this.userMapper.deleteByPrimaryKey(userId);
		
	}
	
	/**
	 * 启用用户
	 * @param userId用户编号
	 * @throws NoUserException 无此用户
	 */
	public void openUser(Long userId) throws Exception{
		
		//判断是否存在此用户,若不存在则抛异常
		UserInfo user = this.userMapper.selectByPrimaryKey(userId);
		
		if(user == null){
			throw new Exception("无此学员！");
		}
		
		//修改用户状态
		user.setStatus(UserStatusEnum.OPENED.toValue());
		
		this.userMapper.updateByPrimaryKey(user);
		
	}
	
	/**
	 * 停用用户
	 * @param userId用户编号
	 * @throws NoUserException无此用户
	 */
	public void closeUser(Long userId) throws Exception{
		
		//判断是否存在此用户,若不存在则抛异常
		UserInfo user = this.userMapper.selectByPrimaryKey(userId);
		
		if(user == null){
			throw new Exception("无此学员！");
		}
				
		//修改用户状态
		user.setStatus(UserStatusEnum.CLOSED.toValue());
		
		this.userMapper.updateByPrimaryKey(user);
		
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
		
		UserInfo user = null;
		
		UserInfoExample example = new UserInfoExample();
		example.or().andNameEqualTo(name);
		
		List<UserInfo> list = this.userMapper.selectByExample(example);
		
		if(list.size() > 0){
			user = list.get(0);
		}
		
		//判断是否存在用户
		if(user == null){
			throw new Exception("无此学员！");
		}
		
		//判断密码是否正确
		if (!user.getPassword().equals(password)) {
			throw new Exception("密码错误！");
		}
		
		//判断是否启用
		if (!user.getStatus().equals(UserStatusEnum.OPENED.toValue())) {
			throw new Exception("此学员已停用，请联系老师！");
		}
		
		return user;
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
		
		//判断是否存在此用户,若不存在则抛异常
		UserInfo user = this.userMapper.selectByPrimaryKey(userId);
		
		if(user == null){
			throw new Exception("无此学员！");
		}
		
		//判断旧密码是否正确
		if (!user.getPassword().equals(password)) {
			throw new Exception("密码错误！");
		}
		
		//修改用户密码
		user.setPassword(newPassword);
		
		this.userMapper.updateByPrimaryKey(user);
		
	}
	
	
	/**
	 * 获得学员
	 * @param userId 学员编号
	 */
	public UserInfo getUserInfoById(Long userId) throws Exception{
		
		//判断是否存在此用户,若不存在则抛异常
		UserInfo user = this.userMapper.selectByPrimaryKey(userId);
		
		if(user == null){
			throw new Exception("无此学员！");
		}
		
		
		return user;
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
		
		//判断是否存在此用户,若不存在则抛异常
		UserInfo user = this.userMapper.selectByPrimaryKey(userId);
		
		if(user == null){
			throw new Exception("无此学员！");
		}
		
		//修改用户密码
		user.setPassword("888888");
		
		this.userMapper.updateByPrimaryKeySelective(user);
		
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
		
		userTestPaper uTestPaper = new userTestPaper();
		
		uTestPaper.setUserId(userId);
		uTestPaper.setQuestionId(questionId);
		uTestPaper.setTime(time);
		uTestPaper.setScore(score);
		uTestPaper.setIsCorrect(isCorrect);
		uTestPaper.setTestPaperId(testPaperId);
		uTestPaper.setNoSelectAnswer(noSelectAnswer);
		
		this.userTestPaperMapper.insert(uTestPaper);
		
		return uTestPaper;
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<userTestPaper> queryUserTestPaper(Long testPaperId) {
		
		List<userTestPaper> sList = null;
		userTestPaperExample ex = new userTestPaperExample();
		userTestPaperExample.Criteria criteria = ex.createCriteria();
		
		criteria.andTestPaperIdEqualTo(testPaperId);
		
		sList =  userTestPaperMapper.selectByExampleWithBLOBs(ex);
		
		return sList;
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<userTestPaper> queryUserTestPaperByIdAndUserId(Long questionId, Long userId) {
		
		List<userTestPaper> sList = null;
		userTestPaperExample ex = new userTestPaperExample();
		userTestPaperExample.Criteria criteria = ex.createCriteria();
		
		criteria.andQuestionIdEqualTo(questionId);
		criteria.andUserIdEqualTo(userId);
		
		sList =  userTestPaperMapper.selectByExampleWithBLOBs(ex);
		
		return sList;
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<UserInfo> queryStudentByClassId(Long classId) {
		
		List<UserInfo> sList = null;
		UserInfoExample ex = new UserInfoExample();
		UserInfoExample.Criteria criteria = ex.createCriteria();
		
		criteria.andClassIdEqualTo(classId);
		
		sList =  userMapper.selectByExample(ex);
		
		return sList;
	}
	
}
