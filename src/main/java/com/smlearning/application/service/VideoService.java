package com.smlearning.application.service;

import java.util.HashMap;
import java.util.List;

import cn.com.iactive.db.DataGridModel;

public interface VideoService {
  public List<HashMap<String,Object>> getVideopart(int gradeId,int categroyId);
  
  public HashMap<String, Object> getVideoResList(DataGridModel dm,HashMap<String,String> params);

  public long createVideoRes(HashMap<String,String> videores);
  
  public HashMap<String,Object> getVideoResById(int id);
  
  public void updateVideores(HashMap<String,String> videores);
  
  public void deleteVideoRes(String ids);
  
  public List<HashMap<String, Object>> getVideoResByPartIdAndCategoryId(int partId,int categoryId);
  
  public List<HashMap<String, Object>> searchVideoRes(long gradeId,String value);
  
}
