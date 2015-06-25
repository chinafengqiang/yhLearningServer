package com.smlearning.application.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.IACDB;
import cn.com.iactive.db.PagerModel;

import com.smlearning.application.service.OnlineMsgService;
import com.smlearning.jdbc.TableInfo;

@Service
public class OnlineMsgServiceImpl implements OnlineMsgService{

    @Autowired
    private IACDB<HashMap<String, Object>> iacDB;
    @Override
    public int saveOnlineMsg(HashMap<String, Object> msg) {
        return (int)iacDB.insertDynamicRInt(TableInfo.ONLINE_MSG,msg);
    }
    
    
    
    @Override
    public void saveOnlineReplyMsg(HashMap<String, Object> msg) {
      iacDB.insertDynamic(TableInfo.ONLINE_REPLY_MSG,msg);
    }



    @Override
    public PagerModel<HashMap<String,Object>> getOnlineMessage(int userId, int classId, int offset,
        int pageSize) {
      long total = 0;
      HashMap<String,Object> params = new HashMap<String,Object>();
      params.put("CLASS_ID",classId);
      params.put("UID",userId);
      params.put("OFFSET",offset);
      params.put("PAGESIZE",pageSize);
      HashMap<String,Object> countMap = iacDB.get("getOnlineMessageCount", params);
      if(countMap != null)
        total = (Long)countMap.get("COUNT");
      List<HashMap<String,Object>> resList = iacDB.getList("getOnlineMessage", params);
      PagerModel<HashMap<String,Object>> pm = new PagerModel<HashMap<String,Object>>();
      pm.setTotals(total);
      pm.setItems(resList);
      return pm;
    }



    @Override
    public List<HashMap<String, Object>> getOnlineReplyMessage(int msgId) {
      HashMap<String,Object> params = new HashMap<String,Object>();
      params.put("msgId",msgId);
      return iacDB.getList("getOnlineReplyMessage",params);
    }
    
    
    
    
    
}

