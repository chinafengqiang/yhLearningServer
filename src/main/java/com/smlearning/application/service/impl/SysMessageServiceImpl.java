package com.smlearning.application.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smlearning.application.service.SysMessageService;
import com.smlearning.domain.activity.SysMessageActivity;
import com.smlearning.domain.entity.SysMessage;
import com.smlearning.domain.vo.SysMessageVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 公告通知业务方法
 * @author Administrator
 */
@Service
public class SysMessageServiceImpl implements SysMessageService{

	static Logger logger = Logger.getLogger(SysMessageServiceImpl.class.getName());
	
	@Autowired
	private SysMessageActivity sysMessageActivity;
	
	/**
	 * 消息分页
	 * @param ph
	 * @return
	 */
	public DataGrid querySysMessagePaning(SysMessageVO sysMessageVO,PageHelper ph) {
		return sysMessageActivity.querySysMessagePaning(sysMessageVO,ph);
	}
	
	/**
	 * 创建消息
	 * @param name 标题
	 * @param content内容
	 * @param creator 创建人
	 * @return
	 * @throws NoOrganException 无此单位
	 */
	public SysMessage createSysMessage(String name, String content,Long creator) throws Exception{
		
		return sysMessageActivity.createSysMessage(name, content, creator);
	}
	
	/**
	 * 修改消息
	 * @param sysMessageId 消息编号
	 * @param name 标题
	 * @param content内容
	 * @return
	 * @throws NoSysMessageException 无此消息
	 * @throws InvalidAccessException  允许修改本单位创建的消息
	 */
	public SysMessage modifySysMessage(Long sysMessageId, String name, String content) throws Exception{
		return sysMessageActivity.modifySysMessage(sysMessageId, name, content);
	}
	
	/**
	 * 删除消息
	 * @param sysMessageId 消息编号
	 * @throws NoSysMessageException 无此消息
	 * @throws InvalidAccessException 允许修改本单位创建的消息
	 */
	public void removeSysMessage(Long sysMessageId) throws Exception{
		sysMessageActivity.removeSysMessage(sysMessageId);
	}
	
	/**
	 * 获得消息
	 * @param sysMessageId 消息编号
	 */
	public SysMessage getSysMessage(Long sysMessageId) throws Exception{
		return sysMessageActivity.getSysMessage(sysMessageId);
	}
	
	/**
	 * 启用消息
	 * @param sysMessageId 消息编号
	 * @throws Exception 权限不够
	 */
	public void openSysMessage(Long sysMessageId) throws Exception{
		sysMessageActivity.openSysMessage(sysMessageId);
	}
	
	/**
	 * 停用消息
	 * @param sysMessageId 消息编号
	 * @throws Exception 权限不够
	 */
	public void closeSysMessage(Long sysMessageId) throws Exception {
		sysMessageActivity.closeSysMessage(sysMessageId);
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<SysMessage> querySysMessage(Long classId) {
		return sysMessageActivity.querySysMessage(classId);
	}
	
	/**
	 * 创建消息
	 * @param name 标题
	 * @param content内容
	 * @param creator 创建人
	 * @return
	 * @throws NoOrganException 无此单位
	 */
	public SysMessage createMessage(String name, String content, Integer canSendAll, Long creator,  Long classId) throws Exception{
		return sysMessageActivity.createMessage(name, content, canSendAll, creator, classId);
	}
	
	/**
	 * 返回消息表表数据
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryUserSysMessagePaning(SysMessageVO sysMessageVO, PageHelper ph) {
		return sysMessageActivity.queryUserSysMessagePaning(sysMessageVO, ph);
	}
	
	/**
	 * 修改消息
	 * @param sysMessageId 消息编号
	 * @param name 标题
	 * @param content内容
	 * @return
	 * @throws NoSysMessageException 无此消息
	 * @throws InvalidAccessException  允许修改本单位创建的消息
	 */
	public SysMessage modifySysMessage(Long sysMessageId) throws Exception{
		return sysMessageActivity.modifySysMessage(sysMessageId);
	}

}
