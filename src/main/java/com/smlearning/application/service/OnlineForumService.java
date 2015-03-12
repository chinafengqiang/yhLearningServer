package com.smlearning.application.service;

import com.smlearning.domain.entity.OnlineForum;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;



/**
 * 学员业务接口
 * @author Administrator
 */
public interface OnlineForumService {

	/**
	 * 返回学员列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryForumPaning(PageHelper ph, Long classId);
	
	/**
	 * 创建跟贴
	 * @param name
	 * @param courseId
	 * @param question
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public OnlineForum createOnlineForum(String name, String question, Long classId, Long creator) throws Exception;
	
	/**
	 * @param rootId
	 * @param question
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public OnlineForum createChildOnlineForum(Long rootId, String question, Long creator,  Long classId) throws Exception;
	
	/**
	 * 返回学员列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryForumChildPaning(Long id, PageHelper ph);
	
	/**
	 * 获得论坛消息
	 * @param id 消息编号
	 */
	public OnlineForum getOnlineForum(Long id) throws Exception;
	
	/**
	 * 修改论坛信息
	 * @param onlineForumId
	 * @param name
	 * @param question
	 * @param classId
	 * @return
	 * @throws Exception
	 */
	public OnlineForum modifyOnlineForum(Long onlineForumId, String name, String question, Long classId) throws Exception;
	
	/**
	 * 删除子
	 * @param rootId
	 * @param creator
	 * @throws Exception
	 */
	public void removeForum(Long rootId) throws Exception;
	
	/**
	 * 返回学员列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryForumPaning(PageHelper ph);
	
	/**
	 * 学员
	 * @param rootId
	 * @param question
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public OnlineForum createChildUserForum(Long rootId, String question, Long creator) throws Exception;
}
