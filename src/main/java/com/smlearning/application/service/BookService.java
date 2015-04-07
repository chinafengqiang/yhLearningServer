package com.smlearning.application.service;

import java.util.HashMap;
import java.util.List;

import cn.com.iactive.db.DataGridModel;

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
  
  public HashMap<String,Object> getBookchapterById(int id);
  
  public void updateBookchapter(HashMap<String,String> bookchapter);
  
  public void deleteBookchapter(int id);
  
  public long createBookRes(HashMap<String,String> bookres);
  
  public HashMap<String, Object> getBookResList(DataGridModel dm,HashMap<String,String> params);
  
  public HashMap<String,Object> getBookResById(int id);
  
  public void updateBookres(HashMap<String,String> bookres);
  
  public void deleteBookRes(String ids);
  
  public void updateBookResSendStatus(int id);
  
  public void updateBookResSendStatus(String ids);
  
  public List<HashMap<String, Object>> getBookResListByIds(String ids);
  
  public List<HashMap<String, Object>> getPermBookCategory(long gradeId);
  
  public List<HashMap<String, Object>> getPermBookPart(long gradeId,int categoryId);
  
  public List<HashMap<String, Object>> getBookchapterByPartIdAndPid(int pid,int partId);
  
  public List<HashMap<String, Object>> getBookResByPartIdAndCategoryId(int partId,int categoryId);
  
}
