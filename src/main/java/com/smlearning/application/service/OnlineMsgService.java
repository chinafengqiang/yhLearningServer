package com.smlearning.application.service;

import java.util.HashMap;
import java.util.List;

import cn.com.iactive.db.PagerModel;

public interface OnlineMsgService {
    public int saveOnlineMsg(HashMap<String,Object> msg);
    
    public void saveOnlineReplyMsg(HashMap<String,Object> msg);
    
    public PagerModel<HashMap<String,Object>> getOnlineMessage(int userId, int classId, int offset,
        int pageSize);
    
    public List<HashMap<String,Object>> getOnlineReplyMessage(int msgId);
}

