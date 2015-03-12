package com.smlearning.datasync;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;


public interface IDataSync {
    public DataGrid listChannel(PageHelper ph);
    
    public void createChannel(HashMap<String,String> channel);
    
    public Map<String,Object> getChannel(int id);
    
    public void updateChannel(HashMap<String,String> channel);
    
    public void deleteChannel(int id);
    
    public DataGrid listGroup(PageHelper ph);
    
    public Map<String,Object> getGroup(int id);
    
    public void createGroup(HashMap<String,String> group);
    
    public void updateGroup(HashMap<String,String> group);
    
    public void deleteGroup(int id);
    
    public List<Map<String,Object>> getGroupPermChannel(int gid);
    
    public int setGroupPerm(HashMap<String,String> group);
    
    public void deleteUserRuleByGroup(long gid,long channelId);
    
    public void deleteUserRuleByGroup(long gid);
    
    public List<Map<String,Object>> getGroupSelect();
    
    public DataGrid listUser(PageHelper ph);
    
    public void createUser(HashMap<String,String> user);
    
    public void updateUser(HashMap<String,String> user);
    
    public Map<String,Object> getUser(int id);
    
    public void deleteUser(int id);
    
    public List<Map<String,Object>> listRulesBySrc(int src,int srcType,int ObjetType);
    
    public List<Map<String,Object>> getUserPermChannel(int uid);
    
    public List<Map<String,Object>> listRulesBySrcAndObject(int src,int srcType,int objetType,int object);
    
    public int setUserPerm(HashMap<String,String> user);
    
    public DataGrid listFileprog(int channlId,String startTime,String endTime,PageHelper ph);
    
    public List<Map<String,Object>> listFileprogFile(int fileprogId);
    
    public List<Map<String,Object>> listFileprogByIds(String fileprogIds);
    
    public DataGrid listFileproged(String startTime,String endTime,PageHelper ph);
    
    public void deleteFileprog(int[] ids);
}
