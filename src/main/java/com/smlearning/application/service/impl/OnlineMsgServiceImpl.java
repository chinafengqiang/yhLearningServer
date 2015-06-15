package com.smlearning.application.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.IACDB;

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
    
}

