package com.smlearning.domain.activity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smlearning.domain.entity.Manager;
import com.smlearning.domain.entity.Organ;
import com.smlearning.domain.entity.OrganExample;
import com.smlearning.domain.entity.SysMessage;
import com.smlearning.domain.entity.SysMessageExample;
import com.smlearning.domain.entity.enums.ManagerStatusEnum;
import com.smlearning.domain.entity.enums.OrganDegreeEnum;
import com.smlearning.domain.entity.enums.SysMessageStatusEnum;
import com.smlearning.domain.vo.SysMessageExtend;
import com.smlearning.domain.vo.SysMessageVO;
import com.smlearning.infrastructure.dao.ManagerMapper;
import com.smlearning.infrastructure.dao.OrganMapper;
import com.smlearning.infrastructure.dao.SysMessageMapper;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.DateUtil;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 公告通知领域活动 聚合
 * @author Administrator
 */
@Repository
public class SysMessageActivity {

	static Logger logger = Logger.getLogger(SysMessageActivity.class.getName());
	
	//数据字典基础层
	@Autowired
	private SysMessageMapper sysMessageMapper;
	
	//组织机构基础层
	@Autowired
	private OrganMapper organMapper;
	
	//学员基础层 
	@Autowired
	private ManagerMapper managerMapper;

	
	/**
	 * 返回消息表表数据
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid querySysMessagePaning(SysMessageVO sysMessageVO, PageHelper ph) {
		logger.info("querySysMessagePaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<SysMessage> sList = null;
		int sCount = 0;
		try{
			SysMessageExample mEx = new SysMessageExample();
			if(StringUtils.isNotBlank(sysMessageVO.getName())){
				
				String nameLike = "%" + sysMessageVO.getName() + "%";
				mEx.or().andNameLike(nameLike);
			}
			
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  sysMessageMapper.selectByExamplePaging(mEx); 
			sCount = sysMessageMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of queryMemberPaning",ex);
		}
		logger.info("querySysMessagePaning result :" +  sList.size() );
		logger.info("querySysMessagePaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<SysMessage> querySysMessage(Long classId) {
		
		List<SysMessage> sList = null;
		SysMessageExample ex = new SysMessageExample();
		SysMessageExample.Criteria criteria = ex.createCriteria();
		
		criteria.andStatusEqualTo(SysMessageStatusEnum.OPENED.toValue());
		criteria.andClassIdEqualTo(classId);
		
		ex.setOrderByClause("id ");
		
		sList =  sysMessageMapper.selectByExampleWithBLOBs(ex); 
		
		return sList;
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
		
		logger.info("creator=="+creator);
		
		Organ organ = null;
		
		OrganExample example = new OrganExample();
		example.or().andDegreeEqualTo(OrganDegreeEnum.SELF.toValue());
		
		//获取本单位
		List<Organ> list = this.organMapper.selectByExample(example);
		if(list.size() > 0){
			organ = list.get(0);
		}
		
		//判断本单位是否存在
		if (organ == null) {
			throw new Exception("无此单位");
		}
		
		//创建系统消息对像
		SysMessage sysMessage = new SysMessage();
		
		sysMessage.setName(name);
		sysMessage.setContent(content);
		sysMessage.setCreator(creator);
		sysMessage.setCreatedTime(new Date());
		sysMessage.setOrganName(organ.getName());
		sysMessage.setOrganGrade(organ.getGrade());
		sysMessage.setOrganDegree(organ.getDegree());
		sysMessage.setSentTime(null);
		sysMessage.setStatus(SysMessageStatusEnum.OPENED.toValue());
		
		this.sysMessageMapper.insert(sysMessage);
		
		return sysMessage;
	}
	
	
	/**
	 * 创建消息
	 * @param name 标题
	 * @param content内容
	 * @param creator 创建人
	 * @return
	 * @throws NoOrganException 无此单位
	 */
	public SysMessage createMessage(String name, String content, Integer canSendAll, Long creator, Long classId) throws Exception{
		
		
		Organ organ = null;
		
		OrganExample example = new OrganExample();
		example.or().andDegreeEqualTo(OrganDegreeEnum.SELF.toValue());
		
		//获取本单位
		List<Organ> list = this.organMapper.selectByExample(example);
		if(list.size() > 0){
			organ = list.get(0);
		}
		
		//判断本单位是否存在
		if (organ == null) {
			throw new Exception("无此单位");
		}
		
		//创建系统消息对像
		SysMessage sysMessage = new SysMessage();
		
	//	sysMessage.setId(SystemUtil.createUUID());
		sysMessage.setName(name);
		sysMessage.setContent(content);
		sysMessage.setCreator(creator);
		sysMessage.setCreatedTime(new Date());
		sysMessage.setOrganName(organ.getName());
		sysMessage.setOrganGrade(organ.getGrade());
		sysMessage.setOrganDegree(organ.getDegree());
		sysMessage.setSentTime(null);
		sysMessage.setCanSendAll(canSendAll);
		sysMessage.setStatus(SysMessageStatusEnum.OPENED.toValue());
		sysMessage.setClassId(classId);
		
		this.sysMessageMapper.insert(sysMessage);
		
		return sysMessage;
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
		
		SysMessage sysMessage = this.sysMessageMapper.selectByPrimaryKey(sysMessageId);
		
		//判断消息是否存在
		if (sysMessage == null) {
			throw new Exception("无此公告信息");
		}
		
		//判断是否修改本单位的消息
		if (!sysMessage.getOrganDegree().equals(OrganDegreeEnum.SELF.toValue())) {
			throw new Exception("只允许修改本单位创建的消息！"); 
		}
		
		sysMessage.setName(name);
		sysMessage.setContent(content);
		
		this.sysMessageMapper.updateByPrimaryKeyWithBLOBs(sysMessage);
		
		return sysMessage;
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
		
		SysMessage sysMessage = this.sysMessageMapper.selectByPrimaryKey(sysMessageId);
		
		//判断消息是否存在
		if (sysMessage == null) {
			throw new Exception("无此公告信息");
		}
		
		//判断是否修改本单位的消息
		if (!sysMessage.getOrganDegree().equals(OrganDegreeEnum.SELF.toValue())) {
			throw new Exception("只允许修改本单位创建的消息！"); 
		}
		
		sysMessage.setOrganGrade("1");
		this.sysMessageMapper.updateByPrimaryKeyWithBLOBs(sysMessage);
		
		return sysMessage;
	}
	
	/**
	 * 删除消息
	 * @param sysMessageId 消息编号
	 * @throws NoSysMessageException 无此消息
	 * @throws InvalidAccessException 允许修改本单位创建的消息
	 */
	public void removeSysMessage(Long sysMessageId) throws Exception{
		
		SysMessage sysMessage = this.sysMessageMapper.selectByPrimaryKey(sysMessageId);
		
		//判断消息是否存在
		if (sysMessage == null) {
			throw new Exception("无此公告信息");
		}
		
		this.sysMessageMapper.deleteByPrimaryKey(sysMessageId);
		
	}
	
	
	/**
	 * 获得消息
	 * @param sysMessageId 消息编号
	 */
	public SysMessage getSysMessage(Long sysMessageId) throws Exception{
		
		SysMessage sysMessage = this.sysMessageMapper.selectByPrimaryKey(sysMessageId);
		
		//判断消息是否存在
		if (sysMessage == null) {
			throw new Exception("无此公告信息");
		}
		
		return sysMessage;
	}
	
	
	/**
	 * 启用消息
	 * @param sysMessageId 消息编号
	 * @throws Exception 权限不够
	 */
	public void openSysMessage(Long sysMessageId) throws Exception{
		
		//获取消息信息
		SysMessage sysMessage = this.sysMessageMapper.selectByPrimaryKey(sysMessageId);
				
		//判断管理员是否存在
		if (sysMessage == null) {
			throw new Exception("无此公告通知");
		}
		
		//修改消息状态
		sysMessage.setStatus(SysMessageStatusEnum.OPENED.toValue());
		
		this.sysMessageMapper.updateByPrimaryKeySelective(sysMessage);
		
	}
	
	/**
	 * 停用消息
	 * @param sysMessageId 消息编号
	 * @throws Exception 权限不够
	 */
	public void closeSysMessage(Long sysMessageId) throws Exception {

		//获取消息信息
		SysMessage sysMessage = this.sysMessageMapper.selectByPrimaryKey(sysMessageId);
				
		//判断管理员是否存在
		if (sysMessage == null) {
			throw new Exception("无此公告通知");
		}
		
		//修改管理者状态
		sysMessage.setStatus(ManagerStatusEnum.CLOSED.toValue());
		
		this.sysMessageMapper.updateByPrimaryKeySelective(sysMessage);
		
	}
	
	
	/**
	 * 返回消息表表数据
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryUserSysMessagePaning(SysMessageVO sysMessageVO, PageHelper ph) {
		logger.info("queryUserSysMessagePaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<SysMessage> sList = null;
		int sCount = 0;
		List<SysMessageExtend> listExtend = new ArrayList<SysMessageExtend>();
		try{
			SysMessageExample mEx = new SysMessageExample();
			SysMessageExample.Criteria criteria = mEx.createCriteria();
			
			criteria.andCanSendAllEqualTo(1);
			criteria.andClassIdEqualTo(sysMessageVO.getClassId());
			mEx.setOrderByClause("created_time desc");
			
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  sysMessageMapper.selectByExamplePaging(mEx); 
			
			for(SysMessage SysMessages : sList){
				SysMessageExtend sysMessage = new SysMessageExtend();
				
				logger.info(" SysMessages.getName()================ :" +  SysMessages.getName() );
				
				//判断是否存在此用户,若不存在则抛异常
				Manager manager = this.managerMapper.selectByPrimaryKey(SysMessages.getCreator());
				
				if(manager == null){
					throw new Exception("无此教师！");
				}
				
				sysMessage.setCreator(manager.getActualName());
				sysMessage.setId(SysMessages.getId());
				sysMessage.setName(SysMessages.getName());
				sysMessage.setContent(SysMessages.getContent());
				sysMessage.setCreatedTime(DateUtil.dateToString(SysMessages.getCreatedTime(), false));
				sysMessage.setClass_id(SysMessages.getClassId());
				sysMessage.setOrganGrade(SysMessages.getOrganGrade());
				
				logger.info(" sysMessage================ :" +  sysMessage );
				
				listExtend.add(sysMessage);
				
			}
			
			sCount = sysMessageMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of queryUserSysMessagePaning",ex);
		}
		logger.info("queryUserSysMessagePaning result :" +  listExtend.size() );
		logger.info("queryUserSysMessagePaning sCount :" +  sCount );
		
		dg.setRows(listExtend);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
}
