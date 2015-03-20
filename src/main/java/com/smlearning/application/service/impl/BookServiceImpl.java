package com.smlearning.application.service.impl;

import java.util.HashMap;

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

}
