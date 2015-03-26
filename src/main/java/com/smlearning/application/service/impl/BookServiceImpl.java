package com.smlearning.application.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.IACDB;

import com.smlearning.application.service.BookService;
import com.smlearning.domain.vo.Tree;
import com.smlearning.jdbc.TableInfo;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private IACDB<HashMap<String, Object>> iacDB;

    @Override
    public void createBookpart(HashMap<String, String> bookpart) {
        iacDB.insertDynamic(TableInfo.BOOK_PART, bookpart);
    }
    
    

    @Override
    public void updateBookpart(HashMap<String, String> bookpart) {
        iacDB.updateDynamic(TableInfo.BOOK_PART,"ID", bookpart);
    }



    public List<HashMap<String, Object>> getBooKpart(int gradeId, int categroyId) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("GRADE_ID", gradeId);
        params.put("CATEGORY_ID", categroyId);
        List<HashMap<String, Object>> resList = iacDB.getList("getBookpart",
                params);
        return resList;
    }

    @Override
    public HashMap<String, Object> getBookpartById(int id) {
        HashMap<String, Object> res = iacDB.get("getBookpartById", id);
        return res;
    }



    @Override
    public void deleteBookpart(String ids) {
        if(StringUtils.isNotBlank(ids)){
          List<String> pkList = new ArrayList<String>();
          String[] idArr = ids.split(",");
          for (String id : idArr) {
            if(StringUtils.isNotBlank(id)){
              pkList.add(id);
            }
          }
          iacDB.deleteBatchDynamic(TableInfo.BOOK_PART,"ID",pkList);
        }
        
    }



    @Override
    public void createBookchapter(HashMap<String, String> bookchapter) {
        iacDB.insertDynamic(TableInfo.BOOK_CHAPTER,bookchapter);
    }



    @Override
    public List<Tree> getChapterTreeJson(int partId,String rootName) {
      HashMap<String,Tree> treeMap = initChapterTree(partId);
      List<Tree> resList = new ArrayList<Tree>();
      Tree rootTree = new Tree("0",rootName);
      HashMap<String,String> attMap = new HashMap<String,String>();
      attMap.put("isAddRes", "0");
      rootTree.setAttributes(attMap);
      resList.add(rootTree);
      
      if(treeMap != null){
          List<Tree> childList = new ArrayList<Tree>();
          Tree tree;
          for(Tree t : treeMap.values()){
            if((tree = treeMap.get(t.getPid()+""))!=null){
              tree.addChild(t);
            }
            if(Integer.parseInt(t.getPid()) == 0)
              childList.add(t);
          }
          Collections.sort(childList);
          rootTree.setChildren(childList);
      }
      return resList;
    }


    private HashMap<String,Tree> initChapterTree(int partId){
      List<HashMap<String, Object>> chapterList = this.getChapterByPartId(partId);
      HashMap<String,Tree> treeMap = null;
      if(chapterList != null && chapterList.size() > 0){
          List<HashMap<String, Object>> ctgList = this.getBookResCategory();
          Tree tree;
          treeMap = new  HashMap<String,Tree>();
          for(HashMap<String, Object> chapter : chapterList){
             int id = (Integer)chapter.get("ID");
             String name = (String)chapter.get("NAME");
             int pid = (Integer)chapter.get("PID");
             int isAdd = (Integer)chapter.get("IS_ADD_RES");
             tree = new Tree();
             tree.setId(id+"");
             tree.setText(name);
             tree.setPid(pid+"");
             HashMap<String,String> attMap = new HashMap<String,String>();
             attMap.put("isAddRes", isAdd+"");
             tree.setAttributes(attMap);
             if(isAdd == 1){
               if(ctgList != null){
                 Tree temp;
                 List<Tree> childList = new ArrayList<Tree>(ctgList.size());
                 HashMap<String,String> tempAttMap;
                 for(HashMap<String, Object> ct : ctgList){
                   temp = new Tree(ct.get("ID").toString(),ct.get("NAME").toString());
                   temp.setPid(tree.getId());
                   tempAttMap = new HashMap<String,String>();
                   tempAttMap.put("isAddRes", "2");
                   temp.setAttributes(tempAttMap);
                   childList.add(temp);
                 }
                 tree.setChildren(childList);
               }
             }
             treeMap.put(id+"",tree);
          }
      }
      return treeMap;
    }
    

    @Override
    public List<HashMap<String, Object>> getChapterByPartId(int partId) {
      HashMap<String,Object> params = new HashMap<String,Object>();
      params.put("PART_ID",partId);
      return iacDB.getList("getBookchapterByPartId", params);
    }



    @Override
    public List<HashMap<String, Object>> getBookResCategory() {
      return iacDB.getList("getBookResCategory");
    }
    
    
    
    

}
