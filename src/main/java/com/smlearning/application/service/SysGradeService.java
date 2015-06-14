package com.smlearning.application.service;

import java.util.HashMap;
import java.util.List;

import com.smlearning.domain.vo.Tree;

import cn.com.iactive.db.DataGridModel;

public interface SysGradeService {
  
	public void getGradeList();
	
	public List<HashMap<String,Object>> getOrgList();
	
	public boolean saveGrade(HashMap<String,String> grade);
	
	public HashMap<String, Object> getGradeList(DataGridModel dm);
	
	public HashMap<String, Object> getGrade(int id);
	
	public boolean updateGrade(HashMap<String,String> grade);
	
	public boolean deleteGrade(int id);
	
	public List<Tree> getGradeTreeJson();
	
	public List<HashMap<String, Object>> getGradeList(long orgId);
	
	public List<HashMap<String, Object>> getAllGradeList();
	
	public List<Tree> getGradeAndClassTreeJson(String checkIds);
	
	public List<HashMap<String,Object>> getAllClass();
	
	public long getUserGradeId(int classId);
	
	public int getTearchCategoryByName(String categoryName);
}
