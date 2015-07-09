package com.smlearning.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.iactive.db.PagerModel;

import com.smlearning.application.service.BookService;
import com.smlearning.application.service.CourseService;
import com.smlearning.application.service.OnlineMsgService;
import com.smlearning.application.service.SysGradeService;
import com.smlearning.application.service.UserService;
import com.smlearning.application.service.VideoService;
import com.smlearning.domain.entity.CoursePlan;
import com.smlearning.domain.entity.UserInfo;
import com.smlearning.domain.vo.CoursePlanVO;
import com.smlearning.infrastructure.utils.DateUtil;


public class ApiService implements IApi{
  
  @Autowired
  private BookService bookService;
  
  @Autowired
  private SysGradeService gradeService;
  
  @Autowired
  private UserService userService;

  @Autowired
  private VideoService videoService;
  
  @Autowired
  private OnlineMsgService onlineMsgService;
  
  @Autowired
  private CourseService courseService;
  
  @Override
  public List<HashMap<String, Object>> getBookCategory(int classId) {
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    try {
      long gradeId = gradeService.getUserGradeId(classId);
      if(gradeId <= 0)
        return resList;
      List<HashMap<String, Object>> tempList = bookService.getPermBookCategory(gradeId);
      if(tempList != null){
        HashMap<String, Object> resMap = null;
        for(HashMap<String, Object> res : tempList){
          resMap = new HashMap<String, Object>();
          resMap.put("id",res.get("id"));
          resMap.put("name",res.get("name"));
          resMap.put("gradeId",gradeId);
          resList.add(resMap);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resList;
  }

  @Override
  public List<HashMap<String, Object>> getBookPart(int classId, int categoryId) {
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    try {
      long gradeId = gradeService.getUserGradeId(classId);
      if(gradeId <= 0)
        return resList;
      List<HashMap<String, Object>> tempList = bookService.getPermBookPart(gradeId, categoryId);
      if(tempList != null){
        HashMap<String, Object> resMap = null;
        for(HashMap<String, Object> res : tempList){
          resMap = new HashMap<String, Object>();
          resMap.put("id",res.get("ID"));
          resMap.put("name",res.get("NAME"));
          resMap.put("gradeId",gradeId);
          resMap.put("categoryId",categoryId);
          resList.add(resMap);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resList;
  }

  @Override
  public List<HashMap<String, Object>> getBookChapter(int pid, int partId,int plevel) {
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    try {
      boolean hasParent = true;
      if(pid == 0)
        hasParent = false;
        
      List<HashMap<String, Object>> tempList = bookService.getBookchapterByPartIdAndPid(pid, partId);
      if(tempList != null){
        HashMap<String, Object> resMap = null;
        for(HashMap<String, Object> res : tempList){
          resMap = new HashMap<String, Object>();
          resMap.put("id",res.get("ID").toString());
          resMap.put("nodeName",res.get("NAME"));
          resMap.put("hasParent",hasParent);
          resMap.put("hasChild",true);
          resMap.put("upNodeId",pid+"");
          resMap.put("expanded",false);
          resMap.put("level",plevel+1);
          resMap.put("isAddRes",res.get("IS_ADD_RES"));
          resList.add(resMap);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resList;
  }

  @Override
  public List<HashMap<String, Object>> getBookResCategory(int partId,int plevel,int type) {
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    try {
      List<HashMap<String, Object>> tempList = bookService.getBookResCategory(type);
      if(tempList != null){
        HashMap<String, Object> resMap = null;
        for(HashMap<String, Object> res : tempList){
          resMap = new HashMap<String, Object>();
          resMap.put("id",res.get("ID").toString());
          resMap.put("nodeName",res.get("NAME"));
          resMap.put("hasParent",true);
          resMap.put("hasChild",false);
          resMap.put("upNodeId",partId+"");
          resMap.put("expanded",true);
          resMap.put("level",plevel+1);
          resMap.put("isAddRes",2);
          resList.add(resMap);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resList;
  }

  
  @Override
  public List<HashMap<String, Object>> getBookRes(int partId, int categoryId) {
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    try {
      List<HashMap<String, Object>> tempList = bookService.getBookResByPartIdAndCategoryId(partId, categoryId);
      if(tempList != null){
        HashMap<String, Object> resMap = null;
        for(HashMap<String, Object> res : tempList){
          resMap = new HashMap<String, Object>();
          resMap.put("resId",res.get("id"));
          resMap.put("resName",res.get("name"));
          resMap.put("resUrl",res.get("url"));
          resMap.put("resCreateTime",DateUtil.dateToString((Date)res.get("created_time"), false));
          resList.add(resMap);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resList;
  }

  
  @Override
  public List<HashMap<String, Object>> getVideoRes(int partId, int categoryId) {
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    try {
      List<HashMap<String, Object>> tempList = videoService.getVideoResByPartIdAndCategoryId(partId, categoryId);
      if(tempList != null){
        HashMap<String, Object> resMap = null;
        for(HashMap<String, Object> res : tempList){
          resMap = new HashMap<String, Object>();
          resMap.put("resId",res.get("id"));
          resMap.put("resName",res.get("name"));
          resMap.put("resUrl",res.get("url"));
          resMap.put("resCreateTime",DateUtil.dateToString((Date)res.get("created_time"), false));
          resMap.put("resLectuer",res.get("lectuer"));
          resList.add(resMap);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resList;
  }

  @Override
  public HashMap<String, Integer> updateUserPass(int userId, String oldPass, String newPass) {
    HashMap<String,Integer> res = new HashMap<String,Integer>();
    try {
      if(userId == 0 || StringUtils.isBlank(oldPass)||StringUtils.isBlank(newPass)){
        res.put("status",404);//参数错误
        return res;
      }
      
      UserInfo user = userService.getUserInfoById(new Long(userId));
      if(user == null || user.getId() == null || user.getId() == 0){
        res.put("status",400);//用户不存在
        return res;
      }
      
      String pass = user.getPassword();
      if(!oldPass.equals(pass)){
        res.put("status",401);//旧密码错误
        return res;
      }
      
      userService.modifyUserPassword(new Long(userId), oldPass, newPass);
      
      res.put("status",200);
      return res;
    } catch (Exception e) {
      e.printStackTrace();
      res.put("status",500);
      return res;
    }
    
  }

  @Override
  public List<HashMap<String, Object>> searchBookRes(int classId,String value) {
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    try {
      long gradeId = gradeService.getUserGradeId(classId);
      if(gradeId <= 0)
        return resList;
      List<HashMap<String, Object>> tempList = bookService.searchBookRes(gradeId,value);
      if(tempList != null){
        HashMap<String, Object> resMap = null;
        for(HashMap<String, Object> res : tempList){
          resMap = new HashMap<String, Object>();
          resMap.put("resId",res.get("id"));
          resMap.put("resName",res.get("name"));
          resMap.put("resUrl",res.get("url"));
          resMap.put("resCreateTime",DateUtil.dateToString((Date)res.get("created_time"), false));
          resMap.put("categoryId",res.get("courseware_category_id"));
          String allPath = (String)res.get("alls_path");
          if(StringUtils.isNotBlank(allPath)){
            String[] allArr = allPath.split(";");
            resMap.put("allIds",allArr[0]);
            resMap.put("allNames",allArr[1]);
          }
          resList.add(resMap);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resList;
  }

  @Override
  public List<HashMap<String, Object>> searchVideoRes(int classId, String value) {
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    try {
      long gradeId = gradeService.getUserGradeId(classId);
      if(gradeId <= 0)
        return resList;
      List<HashMap<String, Object>> tempList = videoService.searchVideoRes(gradeId,value);
      if(tempList != null){
        HashMap<String, Object> resMap = null;
        for(HashMap<String, Object> res : tempList){
          resMap = new HashMap<String, Object>();
          resMap.put("resId",res.get("id"));
          resMap.put("resName",res.get("name"));
          resMap.put("resUrl",res.get("url"));
          resMap.put("resCreateTime",DateUtil.dateToString((Date)res.get("created_time"), false));
          resMap.put("resLectuer",res.get("lectuer"));
          String allPath = (String)res.get("alls_path");
          if(StringUtils.isNotBlank(allPath)){
            String[] allArr = allPath.split(";");
            resMap.put("allIds",allArr[0]);
            resMap.put("allNames",allArr[1]);
          }
          resList.add(resMap);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resList;
  }

  @Override
  public List<HashMap<String, Object>> getClassTearch(int classId) {
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    try {
      List<HashMap<String,Object>> tList = userService.getClassTearch(classId);
      HashMap<String, Object> tearch;
      if(tList != null){
        for(HashMap<String, Object> t : tList){
          tearch = new HashMap<String,Object>();
          tearch.put("id",t.get("id"));
          tearch.put("name",t.get("name"));
          tearch.put("truename",t.get("actual_name"));
          tearch.put("category",t.get("category"));
          resList.add(tearch);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resList;
  }

  
  
@Override
public int saveOnlineMessage(HashMap<String, Object> msg) {
    try {
        String objectName = (String)msg.get("OBJECT_NAME");
        int object = gradeService.getTearchCategoryByName(objectName);
        msg.put("OBJECT",object);
        return onlineMsgService.saveOnlineMsg(msg);
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}



@Override
public int saveOnlineReplyMessage(HashMap<String, Object> msg) {
  try {
    onlineMsgService.saveOnlineReplyMsg(msg);
    return 1;
  } catch (Exception e) {
    e.printStackTrace();
    return 0;
  }
}

@Override
public void getOnlineMessage(int userId, int classId, int offset,
    int pageSize, HashMap<String, Object> resMap) {
  PagerModel<HashMap<String, Object>> pm = onlineMsgService.getOnlineMessage(userId, classId, offset, pageSize);
  resMap.put("totals",pm.getTotals());
  List<HashMap<String,Object>> items = pm.getItems();
  List<HashMap<String,Object>> resList = new ArrayList<HashMap<String,Object>>();
  if(items != null){
    HashMap<String,Object> res = null;
    for(HashMap<String,Object> msg : items){
      res = new HashMap<String,Object>();
      res.put("id",msg.get("ID"));
      res.put("msg",msg.get("MESSAGE"));
      res.put("msgtime",DateUtil.dateToString((Date)msg.get("M_TIME"),false));
      res.put("sender",msg.get("SRC_NAME"));
      res.put("imagePath",msg.get("IMAGE_PATH"));
      res.put("localImagePath",msg.get("LOCAL_IMAGE_PATH"));
      resList.add(res);
    }
  }
  resMap.put("messageList",resList);
}

@Override
public List<HashMap<String, Object>> getOnlineReplyMessage(int msgId) {
  List<HashMap<String,Object>> resList = new ArrayList<HashMap<String,Object>>();
  try {
    List<HashMap<String,Object>> msgList = onlineMsgService.getOnlineReplyMessage(msgId);
    if(msgList != null){
      HashMap<String,Object> res = null;
      for(HashMap<String,Object> msg : msgList){
        res = new HashMap<String,Object>();
        res.put("id",msg.get("ID"));
        res.put("rpmsgId",msg.get("MSG_ID"));
        res.put("rpmsg",msg.get("RP_MSG"));
        res.put("rptime",DateUtil.dateToString((Date)msg.get("RP_TIME"),false));
        res.put("rpuname",msg.get("RP_UNAME"));
        res.put("rpuid",msg.get("RP_UID"));
        res.put("rpimagePath",msg.get("RP_IMAGE_PATH"));
        resList.add(res);
      }
    }
  } catch (Exception e) {
    e.printStackTrace();
  }
  
  return resList;
}


@Override
public void getLessonPlan(int lessonId, int lessonNum,int lessonWeek,
    HashMap<String, Object> resMap,boolean isTemp) {
 try {
   HashMap<String, Object> planMap = null;
   if(isTemp){
     planMap = courseService.getLessonTempPlan(lessonId, lessonNum, lessonWeek);
   }else{
     planMap = courseService.getLessonPlan(lessonId, lessonNum,lessonWeek);
   }
   if(planMap != null){
     resMap.put("lessonPlan",(String)planMap.get("LESSON_CONTENT"));
     resMap.put("lessonPrt",(String)planMap.get("LESSON_PRT"));
   }
  } catch (Exception e) {
    e.printStackTrace();
  }
}

@Override
public List<HashMap<String, Object>> getLessonMessage(int classId) {
  List<HashMap<String,Object>> resList = new ArrayList<HashMap<String,Object>>();
  try {
    List<HashMap<String,Object>> msgList = courseService.getLessonMessage(classId);
    if(msgList != null && msgList.size() > 0){
      HashMap<String,Object> res = null;
      for(HashMap<String,Object> msg : msgList){
        res = new HashMap<String,Object>();
        res.put("id",msg.get("ID"));
        res.put("title",msg.get("TITLE"));
        res.put("content",msg.get("CONTENT"));
        res.put("startTime",DateUtil.dateToString((Date)msg.get("START_DATE"),true));
        res.put("endTime",DateUtil.dateToString((Date)msg.get("END_DATE"),true));
        resList.add(res);
      }
    }
  } catch (Exception e) {
    e.printStackTrace();
  }
  return resList;
}

@Override
public int getLessonMessageCount(int classId) {
  try {
    int res = courseService.getLessonMessageCount(classId);
    return res;
  } catch (Exception e) {
    e.printStackTrace();
    return 0;
  }
}

public void getCoursePlan(int classId,int offset,int pagesize,HashMap<String,Object> resMap){
  List<HashMap<String,Object>> resList = new ArrayList<HashMap<String,Object>>();
  try {
    long gradeId = courseService.getGrade(classId);
    if(gradeId > 0){
      long count = courseService.getCoursePlanCount(gradeId);
      List<HashMap<String,Object>> list = courseService.getCoursePlan(gradeId, offset, pagesize);
      HashMap<String, Object> res = null;
      if(list != null && list.size() > 0){
        for(HashMap<String, Object> plan : list){
          res = new HashMap<String, Object>();
          res.put("id",plan.get("id"));
          res.put("name",plan.get("name"));
          res.put("fileUrl",plan.get("image_url"));
          Date startDate = (Date)plan.get("start_date");
          Date endDate = (Date)plan.get("end_date");
          if(startDate != null){
            res.put("startDate",DateUtil.dateToString(startDate, true));
          }
          if(endDate != null){
            res.put("endDate",DateUtil.dateToString(endDate, true));
          }
          resList.add(res);
        }
      }
      resMap.put("planCount",count);
      resMap.put("planList",resList);
    }
  } catch (Exception e) {
    e.printStackTrace();
  }
}
  




}
