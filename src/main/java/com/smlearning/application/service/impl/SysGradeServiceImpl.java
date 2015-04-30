package com.smlearning.application.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.DataGridModel;
import cn.com.iactive.db.IACDB;

import com.smlearning.application.service.SysGradeService;
import com.smlearning.domain.vo.Tree;
import com.smlearning.infrastructure.utils.DBTableInfo;
import com.smlearning.infrastructure.utils.DateUtil;

@Service
public class SysGradeServiceImpl implements SysGradeService {

  @Autowired
  private IACDB<HashMap<String, Object>> iacDB;

  @Override
  public void getGradeList() {}

  @Override
  public List<HashMap<String, Object>> getOrgList() {
    return iacDB.getList("getOrgList");
  }

  @Override
  public boolean saveGrade(HashMap<String, String> grade) {
    try {
      grade.put("create_time",DateUtil.formateDateToString(new Date()));
      iacDB.insertDynamic(DBTableInfo.SYS_GRADE_TABLE_NAME, grade);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public HashMap<String, Object> getGradeList(DataGridModel dm) {
    return iacDB.getDataGrid("getGradeList",dm);
  }

  @Override
  public HashMap<String, Object> getGrade(int id) {
    return iacDB.get("getGradeById",id);
  }

  @Override
  public boolean updateGrade(HashMap<String, String> grade) {
    try {
      grade.put("create_time",DateUtil.formateDateToString(new Date()));
      iacDB.updateDynamic(DBTableInfo.SYS_GRADE_TABLE_NAME, DBTableInfo.SYS_GRADE_TABLE_PK,grade);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean deleteGrade(int id) {
    try {
      iacDB.delete("deleteGrade", id);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public List<Tree> getGradeTreeJson() {
    List<HashMap<String, Object>> orgList = this.getOrgList();
    List<Tree> resList = new ArrayList<Tree>();
    if(orgList != null){
      for(HashMap<String, Object> org : orgList){
        if(org != null){
          long orgId = (Long)org.get("id");
          String orgName = (String)org.get("name");
          Tree rootTree = new Tree("0",orgName);
          rootTree.setState("open");
          resList.add(rootTree);
          List<HashMap<String, Object>> gradeList = getGradeList(orgId);
          if(gradeList!=null){
            Tree tree;
            List<Tree> treeList = new ArrayList<Tree>(gradeList.size());
            for(HashMap<String, Object> grade : gradeList){
              if(grade != null){
                Object id = grade.get("id");
                String name = (String)grade.get("name");
                tree = new Tree(id.toString(),name);
                tree.setState("open");
                treeList.add(tree);
              }
            }
            rootTree.setChildren(treeList);
          }
        }
      }
    }
    return resList;
  }

  @Override
  public List<HashMap<String, Object>> getGradeList(long orgId) {
    return iacDB.getList("getGradeByorgId",orgId);
  }
  
  @Override
  public List<HashMap<String, Object>> getAllGradeList() {
    return iacDB.getList("getAllGradeList");
  }

  @Override
  public List<Tree> getGradeAndClassTreeJson(String checkIds) {
    List<HashMap<String, Object>> orgList = this.getOrgList();
    List<Tree> resList = new ArrayList<Tree>();
    if(orgList != null){
      for(HashMap<String, Object> org : orgList){
        if(org != null){
          long orgId = (Long)org.get("id");
          String orgName = (String)org.get("name");
          Tree rootTree = new Tree("0",orgName);
          rootTree.setState("open");
          resList.add(rootTree);
          List<HashMap<String, Object>> gradeList = getGradeList(orgId);
          if(gradeList!=null){
            HashMap<String,List<Tree>> treeMap = getClassTree(checkIds);
            Tree tree;
            List<Tree> treeList = new ArrayList<Tree>(gradeList.size());
            for(HashMap<String, Object> grade : gradeList){
              if(grade != null){
                Object id = grade.get("id");
                String name = (String)grade.get("name");
                tree = new Tree(id.toString(),name);
                tree.setState("open");
                List<Tree> childList = treeMap.get(id.toString());
                if(childList != null && childList.size() > 0){
                  tree.setChildren(childList);
                }
                treeList.add(tree);
              }
            }
            rootTree.setChildren(treeList);
          }
        }
      }
    }
    return resList;
  }

  @Override
  public List<HashMap<String, Object>> getAllClass() {
     return iacDB.getList("getAllClass");
  }
  
  private HashMap<String,List<Tree>> getClassTree(String checkIds){
    HashMap<String,List<Tree>> resMap = new HashMap<String, List<Tree>>();
    List<HashMap<String, Object>> classList = getAllClass();
    if(classList != null && classList.size() > 0){
      Tree tree = null;
      String[] ids = null;
      if(StringUtils.isNotBlank(checkIds)){
        ids = checkIds.split(",");
      }
      for(HashMap<String, Object> map : classList){
        tree = new Tree();
        String iddb = map.get("id").toString();
        if(ids!=null){
          for(String id : ids){
            if(iddb.equals(id)){
              tree.setChecked(true);
              break;
            }
          }
        }
        tree.setId(map.get("id").toString());
        tree.setText(map.get("name").toString());
        String pid = map.get("grade_id").toString();
        tree.setPid(pid);
        tree.setState("open");
        List<Tree> treeList = resMap.get(pid);
        if(treeList != null && treeList.size() > 0){
          treeList.add(tree);
        }else{
          treeList = new ArrayList<Tree>();
          treeList.add(tree);
          resMap.put(pid, treeList);
        }
      }
    }
    return resMap;
  }

  
  @Override
  public long getUserGradeId(int classId) {
    HashMap<String,Object> params = new HashMap<String, Object>();
    params.put("classId",classId);
    long gradeId = -1;
    HashMap<String,Object> sysclass = iacDB.get("getSysClass",params);
    if(sysclass != null){
      Long gradeIdLg = (Long)sysclass.get("grade_id");
      if(gradeIdLg != null){
        gradeId = gradeIdLg.longValue();
      }
    }
    return gradeId;
  }
  
  

  
  


}
