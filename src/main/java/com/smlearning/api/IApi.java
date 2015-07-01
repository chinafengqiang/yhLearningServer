package com.smlearning.api;
import java.util.HashMap;
import java.util.List;

public interface IApi {
  
    public List<HashMap<String, Object>> getBookCategory(int classId);
    
    public List<HashMap<String, Object>> getBookPart(int gradeId,int categoryId);
    
    public List<HashMap<String,Object>> getBookChapter(int pid,int partId,int plevel);
    
    public List<HashMap<String,Object>> getBookResCategory(int partId,int plevel,int type);
    
    public List<HashMap<String, Object>> getBookRes(int partId,int categoryId);
    
    public List<HashMap<String, Object>> getVideoRes(int partId,int categoryId);
    
    public HashMap<String,Integer> updateUserPass(int userId,String oldPass,String newPass);
    
    public List<HashMap<String, Object>> searchBookRes(int classId,String value);
    
    public List<HashMap<String, Object>> searchVideoRes(int classId,String value);
    
    public List<HashMap<String, Object>> getClassTearch(int classId);
    
    public int saveOnlineMessage(HashMap<String, Object> msg);
    
    public int saveOnlineReplyMessage(HashMap<String, Object> msg);
    
    public void getOnlineMessage(int userId,int classId,int offset,int pageSize, HashMap<String, Object> resMap);
    
    public List<HashMap<String, Object>> getOnlineReplyMessage(int msgId);
    
    public void getLessonPlan(int lessonId,int lessonNum,int lessonWeek,HashMap<String,Object> resMap,boolean isTemp);
    

}
