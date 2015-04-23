package com.smlearning.application.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.DataGridModel;
import cn.com.iactive.db.IACDB;

import com.smlearning.application.service.VideoService;
import com.smlearning.jdbc.TableInfo;

@Service
public class VideoServiceImpl implements VideoService{
  
  /**
   * 上传视频路径名称
   */
  private final static String fileName = "/uploadFile/file/";

  @Autowired
  private IACDB<HashMap<String, Object>> iacDB;
  
  @Override
  public List<HashMap<String, Object>> getVideopart(int gradeId, int categroyId) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("GRADE_ID", gradeId);
    params.put("CATEGORY_ID", categroyId);
    List<HashMap<String, Object>> resList = iacDB.getList("getVideopart",
            params);
    return resList;
  }

  @Override
  public HashMap<String, Object> getVideoResList(DataGridModel dm, HashMap<String, String> params) {
    HashMap<String,Object> search = new HashMap<String, Object>();
    String startTime = params.get("startTime");
    String endTime = params.get("endTime");
    String name = params.get("name");
    String category = params.get("category");
    String status = params.get("status");
    
    search.put("courseware_category_id",params.get("courseware_category_id"));
    search.put("part_id",params.get("class_id"));
    
    if(StringUtils.isNotBlank(startTime)){
        search.put("startTime", startTime);
    }
    if(StringUtils.isNotBlank(endTime)){
        search.put("endTime", endTime);
    }
    if(StringUtils.isNotBlank(name)){
        search.put("name", name);
    }
  
    if(StringUtils.isNotBlank(category)&&Integer.parseInt(category) > -1){
        search.put("category", category);
    }
    if(StringUtils.isNotBlank(status)&&Integer.parseInt(status) > -1){
        search.put("status", status);
    }
    return iacDB.getDataGrid("getVideoResList",dm,search);
  }

  @Override
  public long createVideoRes(HashMap<String, String> videores) {
    videores.put("url",fileName+videores.get("url"));
    return iacDB.insertDynamicRInt(TableInfo.VIDEO_RESOURCE, videores);
  }

  @Override
  public HashMap<String, Object> getVideoResById(int id) {
    return iacDB.get("getVideoResById", id);
  }

  @Override
  public void updateVideores(HashMap<String, String> videores) {
    String url = videores.get("url");
    if(url.contains("uploadFile")){
      
    } else{
      url = fileName+url;
    }
    videores.put("url",url);
    iacDB.updateDynamic(TableInfo.VIDEO_RESOURCE,"id",videores);
  }

  @Override
  public void deleteVideoRes(String ids) {
    if(StringUtils.isNotBlank(ids)){
      List<String> pkList = new ArrayList<String>();
      String[] idArr = ids.split(",");
      for (String id : idArr) {
        if(StringUtils.isNotBlank(id)){
          pkList.add(id);
        }
      }
      iacDB.deleteBatchDynamic(TableInfo.VIDEO_RESOURCE,"id",pkList);
    }
  }

  @Override
  public List<HashMap<String, Object>> getVideoResByPartIdAndCategoryId(int partId, int categoryId) {
    HashMap<String,Object> params = new HashMap<String, Object>();
    params.put("partId",partId);
    params.put("categoryId",categoryId);
    return iacDB.getList("getVideoResByPartIdAndCategoryId", params);
  }
  
  
  
  
}
