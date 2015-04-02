package com.smlearning.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.smlearning.application.service.BookService;

public class ApiService implements IApi{
  
  @Autowired
  private BookService bookService;

  @Override
  public List<HashMap<String, Object>> getBookCategory(int gradeId) {
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    try {
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
  public List<HashMap<String, Object>> getBookPart(int gradeId, int categoryId) {
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    try {
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
  
  
  
}
