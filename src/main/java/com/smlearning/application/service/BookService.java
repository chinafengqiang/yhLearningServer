package com.smlearning.application.service;

import java.util.HashMap;
import java.util.List;


public interface BookService {
  public void createBookpart(HashMap<String,String> bookpart);
  
  public void updateBookpart(HashMap<String,String> bookpart);
  
  public List<HashMap<String,Object>> getBooKpart(int gradeId,int categroyId);
  
  public HashMap<String,Object> getBookpartById(int id);
  
  public void deleteBookpart(String ids);
}
