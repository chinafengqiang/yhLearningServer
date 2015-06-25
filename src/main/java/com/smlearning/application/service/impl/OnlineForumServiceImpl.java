package com.smlearning.application.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.DataGridModel;
import cn.com.iactive.db.IACDB;

import com.smlearning.application.service.OnlineForumService;
import com.smlearning.domain.activity.OnlineForumActivity;
import com.smlearning.domain.entity.OnlineForum;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 学员业务方法
 * @author Administrator
 */
@Service
public class OnlineForumServiceImpl implements OnlineForumService {

	static Logger logger = Logger.getLogger(OnlineForumServiceImpl.class.getName());
	
	@Autowired
	private OnlineForumActivity onlineForumActivity;
	
	@Autowired
	private IACDB<HashMap<String,Object>> iacDB;
	
	/**
	 * 返回学员列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryForumPaning(PageHelper ph,  Long classId) {
		return onlineForumActivity.queryForumPaning(ph, classId);
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
		return onlineForumActivity.createOnlineForum(name, question, classId, creator);
	}
	
	/**
	 * @param rootId
	 * @param question
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public OnlineForum createChildOnlineForum(Long rootId, String question, Long creator, Long classId) throws Exception{
		return onlineForumActivity.createChildOnlineForum(rootId, question, creator, classId);
	}
	
	/**
	 * 返回学员列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryForumChildPaning(Long id, PageHelper ph) {
		return onlineForumActivity.queryForumChildPaning(id, ph);
	}
	
	/**
	 * 获得论坛消息
	 * @param id 消息编号
	 */
	public OnlineForum getOnlineForum(Long id) throws Exception{
		return onlineForumActivity.getOnlineForum(id);
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
		return onlineForumActivity.modifyOnlineForum(onlineForumId, name, question, classId);
	}
	
	/**
	 * 删除子
	 * @param rootId
	 * @param creator
	 * @throws Exception
	 */
	public void removeForum(Long rootId) throws Exception{
		onlineForumActivity.removeForum(rootId);
	}
		
	/**
	 * 返回学员列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryForumPaning(PageHelper ph) {
		return onlineForumActivity.queryForumPaning(ph);
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
		return onlineForumActivity.createChildUserForum(rootId, question, creator);
	}

  @Override
  public HashMap<String, Object> getOnlineMessageList(DataGridModel dm,
      HashMap<String, String> params) {
    HashMap<String, Object> search = new HashMap<String, Object>();
    String startTime = params.get("startTime");
    String endTime = params.get("endTime");
    String sender = params.get("sender");
    String classIdStr = params.get("classId");
    int classId = 0;
    if(StringUtils.isNotBlank(classIdStr)){
      classId = Integer.parseInt(classIdStr);
    }
    if (StringUtils.isNotBlank(startTime)) {
      search.put("startTime", startTime);
    }
    if (StringUtils.isNotBlank(endTime)) {
      search.put("endTime", endTime);
    }
    if(StringUtils.isNotBlank(sender)){
      search.put("sender",sender);
    }
    if(classId > 0){
      search.put("classId",classId);
    }
    return iacDB.getDataGrid("getOnlineMessageList", dm, search);
  }

  @Override
  public void setOnlineStatus(String ids,int isValid) {
      if(StringUtils.isNotBlank(ids)){
        String[] idArr = ids.split(",");
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("IS_VALID", isValid);
        
        for(String id : idArr){
          if(StringUtils.isNotBlank(id)){
            params.put("ID",id);
            iacDB.updateDynamic("online_message","ID",params);
          }
        }
        
      }
  }

  @Override
  public List<HashMap<String, Object>> getReplyMessage(int msgId) {
    HashMap<String,Object> params = new HashMap<String,Object>();
    params.put("msgId",msgId);
    return iacDB.getList("getReplyMessageByMsgId", params);
  }

  @Override
  public void setOnlineReplyStatus(int id, int isValid) {
    if(id>0){
      HashMap<String,Object> params = new HashMap<String,Object>();
      params.put("IS_VALID", isValid);
      params.put("ID", id);
      iacDB.updateDynamic("online_message_reply","ID", params);
    }
    
  }
	
  
	
		
}
