package com.smlearning.domain.activity;


import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smlearning.domain.entity.Manager;
import com.smlearning.domain.entity.OnlineForum;
import com.smlearning.domain.entity.OnlineForumExample;
import com.smlearning.domain.entity.UserInfo;
import com.smlearning.infrastructure.dao.ManagerMapper;
import com.smlearning.infrastructure.dao.OnlineForumMapper;
import com.smlearning.infrastructure.dao.UserInfoMapper;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 在线交流领域活动 聚合
 * @author Administrator
 */
@Repository
public class OnlineForumActivity {

	static Logger logger = Logger.getLogger(OnlineForumActivity.class.getName());
	
	//学员基础层
	@Autowired
	private OnlineForumMapper onlineForumMapper;
	
	//学员基础层
	@Autowired
	private UserInfoMapper userMapper;
	
	//学员基础层 
	@Autowired
	private ManagerMapper managerMapper;
	
	/**
	 * 返回学员列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryForumPaning(PageHelper ph) {
		logger.info("queryForumPaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<OnlineForum> sList = null;
		int sCount = 0;
		try{
			OnlineForumExample mEx = new OnlineForumExample();
			
			OnlineForumExample.Criteria criteria = mEx.createCriteria();
			
			criteria.andRootIdEqualTo(0l);
			
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  onlineForumMapper.selectByExamplePaging(mEx); 
			sCount = onlineForumMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of queryForumPaning",ex);
		}
		logger.info("queryForumPaning result :" +  sList.size() );
		logger.info("queryForumPaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	/**
	 * 返回学员列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryForumPaning(PageHelper ph, Long classId) {
		logger.info("queryForumPaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<OnlineForum> sList = null;
		int sCount = 0;
		try{
			OnlineForumExample mEx = new OnlineForumExample();
			
			OnlineForumExample.Criteria criteria = mEx.createCriteria();
			
			criteria.andClassIdEqualTo(classId);
			criteria.andRootIdEqualTo(0l);
			
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  onlineForumMapper.selectByExamplePaging(mEx); 
			sCount = onlineForumMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of queryForumPaning",ex);
		}
		logger.info("queryForumPaning result :" +  sList.size() );
		logger.info("queryForumPaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	/**
	 * 返回学员列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryForumChildPaning(Long id, PageHelper ph) {
		logger.info("queryForumChildPaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<OnlineForum> sList = null;
		int sCount = 0;
		try{
			OnlineForumExample mEx = new OnlineForumExample();
		
			mEx.or().andRootIdEqualTo(id);
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  onlineForumMapper.selectByExamplePaging(mEx); 
			sCount = onlineForumMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of queryForumChildPaning",ex);
		}
		
		logger.info("queryForumChildPaning result :" +  sList.size() );
		logger.info("queryForumChildPaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	/**
	 * 创建跟贴
	 * @param name
	 * @param courseId
	 * @param question
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public OnlineForum createOnlineForum(String name, String question, Long classId, Long creator) throws Exception{
		
		//判断是否存在此用户,若不存在则抛异常
		Manager manager = this.managerMapper.selectByPrimaryKey(creator);
		
		if(manager == null){
			throw new Exception("无此教师！");
		}
				
		OnlineForum forum = new OnlineForum();
		
		forum.setName(name);
		forum.setQuestion(question);
		forum.setCreatedTime(new Date());
		forum.setRootId(0l);
		forum.setChildNum(0);
		forum.setCreator(manager.getActualName());
		forum.setClassId(classId);
		
		this.onlineForumMapper.insert(forum);
		
		return forum;
		
	}
	
	
	/**
	 * 修改论坛信息
	 * @param onlineForumId
	 * @param name
	 * @param question
	 * @param classId
	 * @return
	 * @throws Exception
	 */
	public OnlineForum modifyOnlineForum(Long onlineForumId, String name, String question, Long classId) throws Exception{
		
		OnlineForum forum = this.onlineForumMapper.selectByPrimaryKey(onlineForumId);
		
		//判断消息是否存在
		if (forum == null) {
			throw new Exception("无此公告信息");
		}	
		
		forum.setName(name);
		forum.setQuestion(question);
		forum.setCreatedTime(new Date());
		forum.setClassId(classId);
		
		this.onlineForumMapper.updateByPrimaryKey(forum);
		
		return forum;
		
	}
	
	/**
	 * @param rootId
	 * @param question
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public OnlineForum createChildOnlineForum(Long rootId, String question, Long creator, Long classId) throws Exception{
		
//		//判断是否存在此用户,若不存在则抛异常
//		UserInfo user = this.userMapper.selectByPrimaryKey(creator);
//		
//		System.out.println(user);
//		
//		if(user == null){
//			throw new Exception("无此学员！");
//		}
		
		//判断是否存在此用户,若不存在则抛异常
		Manager manager = this.managerMapper.selectByPrimaryKey(creator);
		
		if(manager == null){
			throw new Exception("无此教师！");
		}
		
		OnlineForum root = this.onlineForumMapper.selectByPrimaryKey(rootId);
		
		//判断是否存在此根贴
		if (root == null) {
			throw new Exception();
		}
				
		OnlineForum forum = new OnlineForum();
		
		forum.setRootId(rootId);
		forum.setName(root.getName());
		forum.setQuestion(question);
		forum.setCreatedTime(new Date());
		forum.setChildNum(1);
		forum.setCreator(manager.getActualName());
		forum.setClassId(classId);

		this.onlineForumMapper.insert(forum);
		
		return forum;
		
	}
	
	
	/**
	 * 学员
	 * @param rootId
	 * @param question
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public OnlineForum createChildUserForum(Long rootId, String question, Long creator) throws Exception{
		
		//判断是否存在此用户,若不存在则抛异常
		UserInfo user = this.userMapper.selectByPrimaryKey(creator);
		
		System.out.println(user);
		
		if(user == null){
			throw new Exception("无此学员！");
		}
		
		OnlineForum root = this.onlineForumMapper.selectByPrimaryKey(rootId);
		
		//判断是否存在此根贴
		if (root == null) {
			throw new Exception();
		}
				
		OnlineForum forum = new OnlineForum();
		
		forum.setRootId(rootId);
		forum.setName(root.getName());
		forum.setQuestion(question);
		forum.setCreatedTime(new Date());
		forum.setChildNum(0);
		forum.setCreator(user.getActualName());

		this.onlineForumMapper.insert(forum);
		
		return forum;
		
	}
	
	/**
	 * 删除子
	 * @param rootId
	 * @param creator
	 * @throws Exception
	 */
	public void removeForum(Long rootId) throws Exception{
		
		OnlineForum forum = this.onlineForumMapper.selectByPrimaryKey(rootId);
		//判断是否存在此根贴
		if (forum == null) {
			throw new Exception();
		}
		
		OnlineForumExample mEx = new OnlineForumExample();
		mEx.or().andRootIdEqualTo(rootId);
		
		List<OnlineForum> foumList = this.onlineForumMapper.selectByExample(mEx);
		
		for(OnlineForum forums : foumList){
		
			this.onlineForumMapper.deleteByPrimaryKey(forums.getId());
		}
		
		this.onlineForumMapper.deleteByPrimaryKey(rootId);
				
	}
	
	/**
	 * 获得论坛消息
	 * @param id 消息编号
	 */
	public OnlineForum getOnlineForum(Long id) throws Exception{
		
		OnlineForum forum = this.onlineForumMapper.selectByPrimaryKey(id);
		
		//判断消息是否存在
		if (forum == null) {
			throw new Exception("无此公告信息");
		}
		
		return forum;
	}
	
	
}
