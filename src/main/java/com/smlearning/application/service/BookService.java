package com.smlearning.application.service;

import java.util.HashMap;
import java.util.List;

import com.smlearning.domain.vo.Tree;


public interface BookService {
  public void createBookpart(HashMap<String,String> bookpart);
  
  public void updateBookpart(HashMap<String,String> bookpart);
  
  public List<HashMap<String,Object>> getBooKpart(int gradeId,int categroyId);
  
  public HashMap<String,Object> getBookpartById(int id);
  
  public void deleteBookpart(String ids);
  
  public void createBookchapter(HashMap<String,String> bookchapter);
  
  public List<Tree> getChapterTreeJson(int partId,String rootName);
  
  public List<HashMap<String,Object>> getChapterByPartId(int partId);
  
  public List<HashMap<String,Object>> getBookResCategory();
}
