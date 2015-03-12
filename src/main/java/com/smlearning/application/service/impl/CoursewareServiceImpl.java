package com.smlearning.application.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.DataGridModel;
import cn.com.iactive.db.IACDB;
import cn.com.iactive.db.PagerModel;

import com.smlearning.application.service.CoursewareService;
import com.smlearning.domain.activity.CoursewareActivity;
import com.smlearning.domain.entity.Courseware;
import com.smlearning.domain.entity.userCourseNote;
import com.smlearning.domain.entity.enums.CourseUseTypeEnum;
import com.smlearning.domain.vo.CoursewareVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 课件业务方法
 * @author Administrator
 */
@Service
public class CoursewareServiceImpl implements CoursewareService{

	static Logger logger = Logger.getLogger(CoursewareServiceImpl.class.getName());
	
	@Autowired
	private CoursewareActivity coursewareActivity;
	@Autowired
	private IACDB<HashMap<String,Object>> iacDB;
	
	/**
	 * 返回课件列表数据信息
	 * @param coursewareVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryCoursewarePaning(CoursewareVO coursewareVO, PageHelper ph) {
		return coursewareActivity.queryCoursewarePaning(coursewareVO, ph);
	}
	
	/**
	 * 返回电子书列表数据信息
	 * @param coursewareVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryBookPaning(CoursewareVO coursewareVO, PageHelper ph) {
		return coursewareActivity.queryBookPaning(coursewareVO, ph);
	}
	
	/**
	 * 返回电子书列表数据信息
	 * @param coursewareVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryBookPanings(CoursewareVO coursewareVO, PageHelper ph) {
		return coursewareActivity.queryBookPanings(coursewareVO, ph);
	}
	
	
	/**
	 * 创建课件
	 * @param name 课件名称
	 * @param courseSubjectId 课程专题编号
	 * @param url 课件网址
	 * @return
	 * @throws NocourseSubjectException 无此课程专题
	 */
	public Courseware createCourseware(String name, Long coursewareCategoryId, String url, Long creator, String pic, Long classId,Long gradeId,Integer isPublic) throws Exception{
		return coursewareActivity.createCourseware(name, coursewareCategoryId, url, creator, pic, classId,gradeId,isPublic);
	}
	
	/**
	 * 修改课件
	 * @param coursewareId 课件编号
	 * @param name 课件名称
	 * @param url 课件网址
	 * @return
	 * @throws NoCoursewareException 无此课件
	 */
	public Courseware modifyCourseware(Long coursewareId, Long coursewareCategoryId, String name, String url,  String pic, Long classId,Long gradeId,Integer isPublic) throws Exception{
		return coursewareActivity.modifyCourseware(coursewareId, coursewareCategoryId,name, url, pic, classId,gradeId,isPublic);
	}
	
	/**
	 * 删除课件
	 * @param coursewareId 课件编号
	 * @throws NoCoursewareException 无此课件
	 */
	public void removeCourseware(Long coursewareId) throws Exception{
		coursewareActivity.removeCourseware(coursewareId);
	}
	
	/**
	 * 获取课件信息
	 * @param coursewareId 课件编号
	 * @throws NoCoursewareException 无此课件
	 */
	public Courseware getCoursewareById(Long coursewareId) throws Exception{
		return coursewareActivity.getCoursewareById(coursewareId);
	}
	
	/**
	 * 创建日记
	 * @return
	 * @throws NocourseSubjectException 无此课程专题
	 */
	public userCourseNote createNote(Long userId, Long coursewareId, String note) throws Exception{
		return coursewareActivity.createNote(userId, coursewareId, note);
	}

	/**
	 * 返回数据信息
	 * @return
	 */
	public List<userCourseNote> queryNote(Long userId) {
		return coursewareActivity.queryNote(userId);
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<Courseware> queryBookByCatetory(Long catetoryId) {
		return coursewareActivity.queryBookByCatetory(catetoryId);
	}
	
	/**
	 * 修改课件状态
	 * @param coursewareId 课件编号
	 * @return
	 * @throws NoCoursewareException 无此课件
	 */
	public Courseware modifyCoursewareStauts(Long coursewareId) throws Exception{
		return coursewareActivity.modifyCoursewareStauts(coursewareId);
	}

	@Override
	public void deleteCourseware(long[] ids) {
		String delIds = null;
		HashMap<String,Object> params = new HashMap<String, Object>();
		if(ids != null){
			for(long id : ids){
				delIds += ","+id;
			}
			params.put("ids",delIds);
		}
		iacDB.delete("deleteCourseware", params);
	}

	@Override
	public HashMap<String, Object> getCoursewareList(DataGridModel dm,
			HashMap<String, String> params) {
		HashMap<String,Object> search = new HashMap<String, Object>();
		String startTime = params.get("startTime");
		String endTime = params.get("endTime");
		String name = params.get("name");
		String gradeId = params.get("gradeId");
		String category = params.get("category");
		String status = params.get("status");
		search.put("userType", CourseUseTypeEnum.Ebooks.toValue());
		if(StringUtils.isNotBlank(startTime)){
			search.put("startTime", startTime);
		}
		if(StringUtils.isNotBlank(endTime)){
			search.put("endTime", endTime);
		}
		if(StringUtils.isNotBlank(name)){
			search.put("name", name);
		}
		if(StringUtils.isNotBlank(gradeId)&&Integer.parseInt(gradeId) > -1){
			search.put("gradeId", gradeId);
		}
		if(StringUtils.isNotBlank(category)&&Integer.parseInt(category) > -1){
			search.put("category", category);
		}
		if(StringUtils.isNotBlank(status)&&Integer.parseInt(status) > -1){
			search.put("status", status);
		}
		return iacDB.getDataGrid("getCoursewareList",dm,search);
		
	}

  @Override
  public List<Courseware> getCoursewareListByIds(String ids) {
    HashMap<String,Object> params = new HashMap<String, Object>();
    params.put("ids",ids);
    List<HashMap<String,Object>> mapList = iacDB.getList("getCoursewareListByIds",params);
    List<Courseware> resList = new ArrayList<Courseware>();
    if(mapList!=null && mapList.size() > 0){
       Courseware ware = null;
       for(HashMap<String,Object> map : mapList){
         ware = new Courseware();
         ware.setCoursewareCategoryId((Long)map.get("courseware_category_id"));
         ware.setCreatedTime((Date)map.get("created_time"));
         ware.setCreator((Long)map.get("creator"));
         ware.setGradeId((Long)map.get("grade_id"));
         java.math.BigInteger bIntegerId = (java.math.BigInteger)map.get("id");
         ware.setId(bIntegerId.longValue());
         ware.setIsPublic((Integer)map.get("ispublic"));
         ware.setName((String)map.get("name"));
         ware.setPic((String)map.get("pic"));
         ware.setStatus((Integer)map.get("status"));
         ware.setUrl((String)map.get("url"));
         resList.add(ware);
       }
    }
    return resList;
  }

  @Override
  public void modifyCoursewareStauts(String ids) {
    HashMap<String,Object> params = new HashMap<String, Object>();
    params.put("ids",ids);
    iacDB.update("modifyCoursewareStautsByIds",params);
  }

  @Override
  public List<HashMap<String, Object>> getResMPath(int type) {
    HashMap<String,Object> params = new HashMap<String, Object>();
    params.put("type",type);
    return iacDB.getList("getResMPath", params);
  }

  @Override
  public int  getBooks(long classId,long category, List<HashMap<String, Object>> resList) {
    if(classId <= 0)
      return 400;
    HashMap<String,Object> params = new HashMap<String, Object>();
    params.put("classId",classId);
    try {
      long gradeId = -1;
      HashMap<String,Object> sysclass = iacDB.get("getSysClass",params);
      if(sysclass != null){
        Long gradeIdLg = (Long)sysclass.get("grade_id");
        if(gradeIdLg != null){
          gradeId = gradeIdLg.longValue();
        }
      }
      params.clear();
      params.put("gradeId",gradeId);
      params.put("category", category);
      List<HashMap<String, Object>> tempList = iacDB.getList("getPermBooks", params);
      if(tempList != null)
        resList.addAll(tempList);
      return 200;
    } catch (Exception e) {
      e.printStackTrace();
      return 500;
    }
  }
  

  
 
  @Override
  public int getBooks(long classId, long category, PagerModel<HashMap<String, Object>> pmRes,
      int offset, int limit) {
    if(classId <= 0)
      return 400;
    HashMap<String,Object> params = new HashMap<String, Object>();
    params.put("classId",classId);
    try {
      long gradeId = -1;
      HashMap<String,Object> sysclass = iacDB.get("getSysClass",params);
      if(sysclass != null){
        Long gradeIdLg = (Long)sysclass.get("grade_id");
        if(gradeIdLg != null){
          gradeId = gradeIdLg.longValue();
        }
      }
      params.clear();
      params.put("gradeId",gradeId);
      params.put("category", category);
      PagerModel<HashMap<String, Object>> pm = iacDB.getPaginated("getPermBooks", params, offset, limit);
      if(pm != null){
        pmRes.setTotals(pm.getTotals());
        pmRes.setItems(pm.getItems());
      }
      return 200;
    } catch (Exception e) {
      e.printStackTrace();
      return 500;
    }
  }

  @Override
  public int getBooksCategory(long classId, List<HashMap<String, Object>> resList) {
    if(classId <= 0)
      return 400;
    HashMap<String,Object> params = new HashMap<String, Object>();
    params.put("classId",classId);
    try {
      long gradeId = -1;
      HashMap<String,Object> sysclass = iacDB.get("getSysClass",params);
      if(sysclass != null){
        Long gradeIdLg = (Long)sysclass.get("grade_id");
        if(gradeIdLg != null){
          gradeId = gradeIdLg.longValue();
        }
      }
      params.clear();
      params.put("gradeId",gradeId);
      List<HashMap<String, Object>> tempList = iacDB.getList("getPermBooksCategory", params);
      if(tempList != null)
        resList.addAll(tempList);
      return 200;
    } catch (Exception e) {
      e.printStackTrace();
      return 500;
    }
  }

	
  
  
	
	
}
