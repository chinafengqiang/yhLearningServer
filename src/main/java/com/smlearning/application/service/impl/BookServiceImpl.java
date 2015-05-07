package com.smlearning.application.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.DataGridModel;
import cn.com.iactive.db.IACDB;

import com.smlearning.application.service.BookService;
import com.smlearning.domain.entity.enums.CourseUseTypeEnum;
import com.smlearning.domain.entity.enums.CoursewareSendStatusEnum;
import com.smlearning.domain.vo.Tree;
import com.smlearning.jdbc.TableInfo;

@Service
public class BookServiceImpl implements BookService {

  /**
   * 上传电子书路径名称
   */
  private final static String RES_FILE_PATH = "/uploadFile/file/";

  @Autowired
  private IACDB<HashMap<String, Object>> iacDB;

  @Override
  public void createBookpart(HashMap<String, String> bookpart) {
    iacDB.insertDynamic(TableInfo.BOOK_PART, bookpart);
  }



  @Override
  public void updateBookpart(HashMap<String, String> bookpart) {
    iacDB.updateDynamic(TableInfo.BOOK_PART, "ID", bookpart);
  }



  public List<HashMap<String, Object>> getBooKpart(int gradeId, int categroyId) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("GRADE_ID", gradeId);
    params.put("CATEGORY_ID", categroyId);
    List<HashMap<String, Object>> resList = iacDB.getList("getBookpart", params);
    return resList;
  }

  @Override
  public HashMap<String, Object> getBookpartById(int id) {
    HashMap<String, Object> res = iacDB.get("getBookpartById", id);
    return res;
  }



  @Override
  public void deleteBookpart(String ids) {
    if (StringUtils.isNotBlank(ids)) {
      List<String> pkList = new ArrayList<String>();
      String[] idArr = ids.split(",");
      for (String id : idArr) {
        if (StringUtils.isNotBlank(id)) {
          pkList.add(id);
        }
      }
      iacDB.deleteBatchDynamic(TableInfo.BOOK_PART, "ID", pkList);
    }

  }



  @Override
  public void createBookchapter(HashMap<String, String> bookchapter) {
    iacDB.insertDynamic(TableInfo.BOOK_CHAPTER, bookchapter);
  }



  @Override
  public List<Tree> getChapterTreeJson(int partId, String rootName) {
    HashMap<String, Tree> treeMap = initChapterTree(partId);
    List<Tree> resList = new ArrayList<Tree>();
    Tree rootTree = new Tree("0", rootName);
    rootTree.setState("open");
    HashMap<String, String> attMap = new HashMap<String, String>();
    attMap.put("isAddRes", "0");
    rootTree.setAttributes(attMap);
    resList.add(rootTree);

    if (treeMap != null) {
      List<Tree> childList = new ArrayList<Tree>();
      Tree tree;
      /*
       * for(Tree t : treeMap.values()){ if((tree = treeMap.get(t.getPid()+""))!=null){
       * tree.addChild(t); } if(Integer.parseInt(t.getPid()) == 0) childList.add(t); }
       */

      Object[] key_arr = treeMap.keySet().toArray();
      Arrays.sort(key_arr);
      for (Object key : key_arr) {
        Tree t = treeMap.get(key);
        if ((tree = treeMap.get(t.getPid() + "")) != null) {
          tree.addChild(t);
        }
        if (Integer.parseInt(t.getPid()) == 0)
          childList.add(t);
      }

      Collections.sort(childList);
      rootTree.setChildren(childList);
    }
    return resList;
  }


  private HashMap<String, Tree> initChapterTree(int partId) {
    List<HashMap<String, Object>> chapterList = this.getChapterByPartId(partId);
    HashMap<String, Tree> treeMap = null;
    if (chapterList != null && chapterList.size() > 0) {
      List<HashMap<String, Object>> ctgList = this.getBookResCategory();
      Tree tree;
      treeMap = new HashMap<String, Tree>();
      for (HashMap<String, Object> chapter : chapterList) {
        int id = (Integer) chapter.get("ID");
        String name = (String) chapter.get("NAME");
        int pid = (Integer) chapter.get("PID");
        int isAdd = (Integer) chapter.get("IS_ADD_RES");
        tree = new Tree();
        tree.setId(id + "");
        tree.setText(name);
        tree.setPid(pid + "");
        HashMap<String, String> attMap = new HashMap<String, String>();
        attMap.put("isAddRes", isAdd + "");
        tree.setAttributes(attMap);
        if (isAdd == 1) {
          if (ctgList != null) {
            Tree temp;
            List<Tree> childList = new ArrayList<Tree>(ctgList.size());
            HashMap<String, String> tempAttMap;
            for (HashMap<String, Object> ct : ctgList) {
              temp = new Tree(ct.get("ID").toString(), ct.get("NAME").toString());
              temp.setPid(tree.getId());
              tempAttMap = new HashMap<String, String>();
              tempAttMap.put("isAddRes", "2");
              tempAttMap.put("pid", tree.getId());
              temp.setAttributes(tempAttMap);
              temp.setState("open");
              childList.add(temp);
            }
            tree.setChildren(childList);
          }
        } else {
          tree.setState("open");
        }
        treeMap.put(id + "", tree);
      }
    }

    return treeMap;
  }


  @Override
  public List<HashMap<String, Object>> getChapterByPartId(int partId) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("PART_ID", partId);
    return iacDB.getList("getBookchapterByPartId", params);
  }



  @Override
  public List<HashMap<String, Object>> getBookResCategory() {
    return iacDB.getList("getBookResCategory");
  }


  @Override
  public List<HashMap<String, Object>> getBookResCategory(int type) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("TYPE", type);
    return iacDB.getList("getBookResCategoryByType", params);
  }

  @Override
  public HashMap<String, Object> getBookchapterById(int id) {
    return iacDB.get("getBookchapterById", id);
  }



  @Override
  public void updateBookchapter(HashMap<String, String> bookchapter) {
    iacDB.updateDynamic(TableInfo.BOOK_CHAPTER, "ID", bookchapter);
  }



  @Override
  public void deleteBookchapter(int id) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    // TODO 删除此目录下所有电子书

    // 删除目录下的子目录
    params.clear();
    params.put("PID", id);
    iacDB.deleteDynamic(TableInfo.BOOK_CHAPTER, params);
    // 删除此目录
    params.clear();
    params.put("ID", id);
    iacDB.deleteDynamic(TableInfo.BOOK_CHAPTER, params);
  }



  @Override
  public long createBookRes(HashMap<String, String> bookres) {
    bookres.put("url", RES_FILE_PATH + bookres.get("url"));
    return iacDB.insertDynamicRInt(TableInfo.BOOK_RESOURCE, bookres);
  }



  @Override
  public HashMap<String, Object> getBookResList(DataGridModel dm, HashMap<String, String> params) {
    HashMap<String, Object> search = new HashMap<String, Object>();
    String startTime = params.get("startTime");
    String endTime = params.get("endTime");
    String name = params.get("name");
    String gradeId = params.get("gradeId");
    String category = params.get("category");
    String status = params.get("status");

    search.put("courseware_category_id", params.get("courseware_category_id"));
    search.put("class_id", params.get("class_id"));

    if (StringUtils.isNotBlank(startTime)) {
      search.put("startTime", startTime);
    }
    if (StringUtils.isNotBlank(endTime)) {
      search.put("endTime", endTime);
    }
    if (StringUtils.isNotBlank(name)) {
      search.put("name", name);
    }
    if (StringUtils.isNotBlank(gradeId) && Integer.parseInt(gradeId) > -1) {
      search.put("gradeId", gradeId);
    }
    if (StringUtils.isNotBlank(category) && Integer.parseInt(category) > -1) {
      search.put("category", category);
    }
    if (StringUtils.isNotBlank(status) && Integer.parseInt(status) > -1) {
      search.put("status", status);
    }
    return iacDB.getDataGrid("getBookResList", dm, search);
  }



  @Override
  public HashMap<String, Object> getBookResById(int id) {
    return iacDB.get("getBookResById", id);
  }



  @Override
  public void updateBookres(HashMap<String, String> bookres) {
    String url = bookres.get("url");
    if (url.contains("uploadFile")) {

    } else {
      url = RES_FILE_PATH + url;
    }
    bookres.put("url", url);
    iacDB.updateDynamic(TableInfo.BOOK_RESOURCE, "id", bookres);
  }


  public void deleteBookRes(String ids) {
    if (StringUtils.isNotBlank(ids)) {
      List<String> pkList = new ArrayList<String>();
      String[] idArr = ids.split(",");
      for (String id : idArr) {
        if (StringUtils.isNotBlank(id)) {
          pkList.add(id);
        }
      }
      iacDB.deleteBatchDynamic(TableInfo.BOOK_RESOURCE, "id", pkList);
    }

  }



  @Override
  public void updateBookResSendStatus(int id) {
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("id", id + "");
    params.put("status", CoursewareSendStatusEnum.SENDED.toValue() + "");
    iacDB.updateDynamic(TableInfo.BOOK_RESOURCE, "id", params);
  }



  @Override
  public void updateBookResSendStatus(String ids) {
    if (StringUtils.isNotBlank(ids)) {
      String[] arr = ids.split(",");
      for (String id : arr) {
        if (StringUtils.isNotBlank(id) && !"null".equals(id)) {
          updateBookResSendStatus(Integer.parseInt(id));
        }
      }
    }
  }



  @Override
  public List<HashMap<String, Object>> getBookResListByIds(String ids) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("ids", ids);
    List<HashMap<String, Object>> mapList = iacDB.getList("getBookResListByIds", params);
    return mapList;
  }



  @Override
  public List<HashMap<String, Object>> getPermBookCategory(long gradeId) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("GRADE_ID", gradeId);
    return iacDB.getList("getPermBookCategory", params);
  }



  @Override
  public List<HashMap<String, Object>> getPermBookPart(long gradeId, int categoryId) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("GRADE_ID", gradeId);
    params.put("CATEGORY_ID", categoryId);
    return iacDB.getList("getPermBookPart", params);
  }



  @Override
  public List<HashMap<String, Object>> getBookchapterByPartIdAndPid(int pid, int partId) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("PART_ID", partId);
    params.put("PID", pid);
    return iacDB.getList("getBookchapterByPartIdAndPid", params);

  }



  @Override
  public List<HashMap<String, Object>> getBookResByPartIdAndCategoryId(int partId, int categoryId) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("partId", partId);
    params.put("categoryId", categoryId);
    return iacDB.getList("getBookResByPartIdAndCategoryId", params);
  }


  @Override
  public List<HashMap<String, Object>> searchBookRes(long gradeId,String value) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("name", value);
    params.put("grade_id", gradeId);
    return iacDB.getList("searchBookRes", params);
  }



  @Override
    public StringBuilder getFileSql(long chapterId,StringBuilder sql) {
      HashMap<String,Object> chapter = this.getBookchapterById((int)chapterId);

      if(chapter != null){
        int ID = (Integer)chapter.get("ID");
        String NAME = (String)chapter.get("NAME");
        int PID = (Integer)chapter.get("PID");
        int PART_ID = (Integer)chapter.get("PART_ID");
        int IS_ADD_RES = (Integer)chapter.get("IS_ADD_RES");
        
        if(sql == null){
          sql = new StringBuilder();
          HashMap<String,Object> part = getBookpartById(PART_ID);
          if(part != null){
            long pID = (Long)part.get("ID");
            String pNAME = (String)part.get("NAME");
            int pGRADE_ID = (Integer)part.get("GRADE_ID");
            int pCATEGORY_ID = (Integer)part.get("CATEGORY_ID");
            sql.append("INSERT INTO book_part(ID,NAME,GRADE_ID,CATEGORY_ID) ");
            sql.append("SELECT "+pID+",'"+pNAME+"',"+pGRADE_ID+","+pCATEGORY_ID+" FROM DUAL ");
            sql.append("WHERE NOT EXISTS(SELECT ID FROM book_part WHERE ID = "+pID+"); ");
          }
        }
        
        sql.append("INSERT INTO book_chapter(ID,NAME,PID,PART_ID,IS_ADD_RES) ");
        sql.append("SELECT "+ID+",'"+NAME+"',"+PID+","+PART_ID+","+IS_ADD_RES+" FROM DUAL ");
        sql.append("WHERE NOT EXISTS(SELECT ID FROM book_chapter WHERE ID = "+ID+"); ");
        if(PID != 0){
          getFileSql(PID, sql);
        }
      }
      return sql;
    }



}
