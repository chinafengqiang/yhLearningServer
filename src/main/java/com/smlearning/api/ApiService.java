package com.smlearning.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.smlearning.application.service.BookService;
import com.smlearning.application.service.SysGradeService;
import com.smlearning.infrastructure.utils.DateUtil;

public class ApiService implements IApi{
  
  @Autowired
  private BookService bookService;
  
  @Autowired
  private SysGradeService gradeService;

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
  public List<HashMap<String, Object>> getBookResCategory(int partId,int plevel) {
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    try {
      List<HashMap<String, Object>> tempList = bookService.getBookResCategory();
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
  
  
  
  
  
}
