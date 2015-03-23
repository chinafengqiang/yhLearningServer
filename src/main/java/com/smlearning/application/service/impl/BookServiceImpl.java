package com.smlearning.application.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.IACDB;

import com.smlearning.application.service.BookService;
import com.smlearning.jdbc.TableInfo;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private IACDB<HashMap<String,Object>> iacDB; 
  @Override
  public void createBookpart(HashMap<String, String> bookpart) {
      iacDB.insertDynamic(TableInfo.BOOK_PART, bookpart);
  }
  
  public List<HashMap<String,Object>> getBooKpart(int gradeId,int categroyId){
      HashMap<String,Object> params = new HashMap<String,Object>();
      params.put("GRADE_ID",gradeId);
      params.put("CATEGORY_ID",categroyId);
      List<HashMap<String,Object>> resList = iacDB.getList("getBookpart", params);
      return resList;
  }

}
