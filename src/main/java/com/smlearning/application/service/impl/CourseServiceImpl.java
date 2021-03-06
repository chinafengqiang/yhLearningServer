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

import com.smlearning.application.service.CourseService;
import com.smlearning.domain.activity.CourseActivity;
import com.smlearning.domain.entity.Course;
import com.smlearning.domain.entity.CoursePlan;
import com.smlearning.domain.entity.classBook;
import com.smlearning.domain.entity.courseTable;
import com.smlearning.domain.entity.enums.CourseStatusEnum;
import com.smlearning.domain.entity.extend.CourseExtend;
import com.smlearning.domain.vo.CoursePlanVO;
import com.smlearning.domain.vo.CourseVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.DateUtil;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 课程业务方法
 * @author Administrator
 */
@Service
public class CourseServiceImpl implements CourseService{

    static Logger logger = Logger.getLogger(CourseServiceImpl.class.getName());
    
    @Autowired
    private CourseActivity courseActivity;

    @Autowired
    private IACDB<HashMap<String,Object>> iacDB;
    /**
     * 返回课程列表数据信息
     * @param sysMessageVO
     * @param ph
     * @return
     */
    public DataGrid queryCoursePaning(CourseVO courseVO, PageHelper ph) {
        return  courseActivity.queryCoursePaning(courseVO, ph);
    }
    
    /**
     * 创建课程
     * @param name 课程名称
     * @param courseCategoryId 课程分类编号
     * @param lectuer主讲人
     * @param hour学时
     * @param description课程简介
     * @param teacherDescription 老师简介
     * @param canUpload  是否上传
     * @param courseDescription
     * @param creator 创建人
     * @return
     * @throws Exception
     */
    public Course createCourse(String name, Long coursewareCategoryId, String lectuer, Integer hour, String description, 
            String teacherDescription, String pic, String url, Long creator,Long gradeId,Integer isPublic) throws Exception {
        return courseActivity.createCourse(name, coursewareCategoryId, lectuer, hour, description, teacherDescription, 
                pic, url, creator,gradeId,isPublic);
    }
    
    
    /**
     * 修改课程
     * @param courseId 课程编号
     * @param name 课程名称
     * @param lectuer 主讲人
     * @param hour 学时
     * @param courseTypeEnum 使用类型
     * @param description 课程简介
     * @param teacherDescription 老师简介
     * @param canUpload 是否上传
     * @return
     * @throws NoCourseException 无此课程
     */
    public Course modifyCourse(Long courseId, Long coursewareCategoryId, String name, String lectuer, Integer hour, String description, 
            String teacherDescription, String pic, String url, Long creator,Long gradeId,Integer isPublic ) throws Exception {
        return courseActivity.modifyCourse(courseId, coursewareCategoryId, name, lectuer, hour, description, teacherDescription, pic, url, creator,gradeId,isPublic);
    }
    
    /**
     * 删除课程
     * @param courseId 课程编号
     * @param userId 操作者
     * @throws NoCourseException 无此课程
     * @throws CourseAlreadyUseException 此课程已经被选用
     */
    public void removeCourse(Long courseId) throws Exception {
        courseActivity.removeCourse(courseId);
    }
    
    /**
     * 修改课程状态
     * @param courseId 课程编号
     * @param statusEnum 状态
     * @param userId 操作者
     * @throws NoCourseException 无此课程
     * @throws NonEnoughAccessException 无足够的权限
     */
    public void modifyCourseStatus(Long courseId, CourseStatusEnum statusEnum) throws Exception {
        courseActivity.modifyCourseStatus(courseId, statusEnum);
    }
    
    /**
     * 获得课程对象信息
     * @param courseId 课程编号
     */
    public Course getCourse(Long courseId) throws Exception {
        return courseActivity.getCourse(courseId);
    }
    
    /**
     * 返回数据信息
     * @return
     */
    public List<CourseExtend> queryCourse(Long classId) {
        return courseActivity.queryCourse(classId);
    }
    
    /**
     * 返回数据信息
     * @return
     */
    public CourseExtend queryCourseById(Long courseId) {
        
        return courseActivity.queryCourseById(courseId);
    }
    

    /**
     * 返回数据信息
     * @return
     */
    public List<CoursePlan> queryCoursePlan() {
        return courseActivity.queryCoursePlan();
    }
    
    /**
     * 返回课程列表数据信息
     * @param sysMessageVO
     * @param ph
     * @return
     */
    public DataGrid queryCoursePlanPaning(CoursePlanVO coursePlanVO, PageHelper ph) {
        return courseActivity.queryCoursePlanPaning(coursePlanVO, ph);
    }
    
    /**
     * 创建
     * @param name 课件名称
     * @param courseSubjectId 课程专题编号
     * @param url 课件网址
     * @return
     * @throws NocourseSubjectException 无此课程专题
     */
    public CoursePlan createCoursePlan(String name,  String imageUrl,long gradeId) throws Exception{
        return courseActivity.createCoursePlan(name, imageUrl,gradeId);
    }
    
    /**
     * 修改课件
     * @param coursewareId 课件编号
     * @param name 课件名称
     * @param url 课件网址
     * @return
     * @throws NoCoursewareException 无此课件
     */
    public CoursePlan modifyCoursePlan(Long coursePlanId, String name,  String imageUrl,long gradeId) throws Exception{
        return courseActivity.modifyCoursePlan(coursePlanId, name, imageUrl,gradeId);
        
    }
    
    /**
     * 删除课件
     * @param coursewareId 课件编号
     * @throws NoCoursewareException 无此课件
     */
    public void removeCoursePlan(Long coursePlanId) throws Exception{
        courseActivity.removeCoursePlan(coursePlanId);
    }
    
    /**
     * 获得课程对象信息
     * @param courseId 课程编号
     */
    public CoursePlan getCoursePlan(Long coursePlanId) throws Exception {
        return  courseActivity.getCoursePlan(coursePlanId);
    }
    
    
    /**
     * 返回数据信息
     * @return
     */
    public List<courseTable> queryCourseTable() {
        return  courseActivity.queryCourseTable();
    }
    
    /**
     * 返回课程列表数据信息
     * @param sysMessageVO
     * @param ph
     * @return
     */
    public DataGrid queryCourseTablePaning(PageHelper ph) {
        return  courseActivity.queryCourseTablePaning(ph);
    }
    
    /**
     * 创建
     * @param name 课件名称
     * @param courseSubjectId 课程专题编号
     * @param url 课件网址
     * @return
     * @throws NocourseSubjectException 无此课程专题
     */
    public courseTable createCourseTable(String name,  String imageUrl) throws Exception{
        return  courseActivity.createCourseTable(name, imageUrl);
    }
    
    /**
     * 修改课件
     * @param coursewareId 课件编号
     * @param name 课件名称
     * @param url 课件网址
     * @return
     * @throws NoCoursewareException 无此课件
     */
    public courseTable modifyCourseTable(Long courseTableId, String name,  String imageUrl) throws Exception{
        return  courseActivity.modifyCourseTable(courseTableId,name,imageUrl);
    }
    
    /**
     * 删除课件
     * @param coursewareId 课件编号
     * @throws NoCoursewareException 无此课件
     */
    public void removeCourseTable(Long courseTableId) throws Exception{
        courseActivity.removeCourseTable(courseTableId);
    }
    
    /**
     * 获得课程对象信息
     * @param courseId 课程编号
     */
    public courseTable getCourseTable(Long courseTableId) throws Exception {
        return  courseActivity.getCourseTable(courseTableId);
    }
    
    /**
     * 返回数据信息
     * @return
     */
    public List<classBook> queryClassBook() {
        return  courseActivity.queryClassBook();
    }
    
    /**
     * 获得课程对象信息
     * @param courseId 课程编号
     */
    public classBook getClassBook(Long classBookId) throws Exception {
        return  courseActivity.getClassBook(classBookId);
    }
    
    /**
     * 删除课件
     * @param coursewareId 课件编号
     * @throws NoCoursewareException 无此课件
     */
    public void removeClassBook(Long classBookId) throws Exception{
        courseActivity.removeClassBook(classBookId);
    }
    
    /**
     * 修改课件
     * @param coursewareId 课件编号
     * @param name 课件名称
     * @param url 课件网址
     * @return
     * @throws NoCoursewareException 无此课件
     */
    public classBook modifyclassBook(Long classBookId, String name,  String imageUrl) throws Exception{
        return  courseActivity.modifyclassBook(classBookId,name, imageUrl);
    }
    
    /**
     * 创建
     * @param name 课件名称
     * @param courseSubjectId 课程专题编号
     * @param url 课件网址
     * @return
     * @throws NocourseSubjectException 无此课程专题
     */
    public classBook createClassBook(String name,  String imageUrl) throws Exception{
        return  courseActivity.createClassBook(name, imageUrl);
    }
    
    /**
     * 返回课程列表数据信息
     * @param sysMessageVO
     * @param ph
     * @return
     */
    public DataGrid queryClassBookPaning(PageHelper ph) {
        return  courseActivity.queryClassBookPaning(ph);
    }

  @Override
  public HashMap<String, Object> getCourseList(DataGridModel dm, HashMap<String, String> params) {
    HashMap<String,Object> search = new HashMap<String, Object>();
    String startTime = params.get("startTime");
    String endTime = params.get("endTime");
    String name = params.get("name");
    String gradeId = params.get("gradeId");
    String category = params.get("category");
    String status = params.get("status");
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
  return iacDB.getDataGrid("getCourseList",dm,search);
  }

  
  
  @Override
  public HashMap<String, Object> getCoursePlanList(DataGridModel dm, HashMap<String, String> params) {
    HashMap<String,Object> search = new HashMap<String, Object>();
    String name = params.get("name");
    String gradeId = params.get("gradeId");
    if(StringUtils.isNotBlank(name)){
      search.put("name", name);
    }
    if(StringUtils.isNotBlank(gradeId)&&Integer.parseInt(gradeId) > 0){
        search.put("gradeId", gradeId);
    }
    return iacDB.getDataGrid("getCourseplanList", dm, search);
  }
  
  

  @Override
  public List<CoursePlan> getCoursePlan(long classId) {
    HashMap<String,Object> params = new HashMap<String,Object>();
    params.put("classId",classId);
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
    
    List<HashMap<String,Object>> list = iacDB.getList("getCoursePlan",params);
    List<CoursePlan> resList = new ArrayList<CoursePlan>();
    if(list!=null&&list.size()>0){
      CoursePlan plan = null;
      for(HashMap<String,Object> planMap : list){
        if(planMap != null){
          plan = new CoursePlan();
          plan.setId((Long)planMap.get("id"));
          plan.setName(planMap.get("name").toString());
          plan.setImageUrl(planMap.get("image_url").toString());
          resList.add(plan);
        }
      }
    }
    return resList;
  }

  @Override
  public void deleteCourse(long[] ids) {
    String delIds = null;
    HashMap<String,Object> params = new HashMap<String, Object>();
    if(ids != null){
        for(long id : ids){
            delIds += ","+id;
        }
        params.put("ids",delIds);
    }
    iacDB.delete("deleteCourse", params);
  }

  @Override
  public List<Course> getCourseListByIds(String ids) {
    HashMap<String,Object> params = new HashMap<String, Object>();
    params.put("ids",ids);
    List<HashMap<String,Object>> mapList = iacDB.getList("getCourseListByIds",params);
    List<Course> resList = new ArrayList<Course>();
    if(mapList!=null && mapList.size() > 0){
      Course course = null;
      for(HashMap<String,Object> map : mapList){
        course = new Course();
        course.setCoursewareCategoryId((Long)map.get("courseware_category_id"));
        course.setCreatedTime((Date)map.get("created_time"));
        course.setCreator((Long)map.get("creator"));
        course.setDescription((String)map.get("description"));
        course.setGradeId((Long)map.get("grade_id"));
        course.setHour((Integer)map.get("hour"));
        java.math.BigInteger bIntegerId = (java.math.BigInteger)map.get("id");
        course.setId(bIntegerId.longValue());
        course.setIsPublic((Integer)map.get("ispublic"));
        course.setLectuer((String)map.get("lectuer"));
        course.setName((String)map.get("name"));
        course.setPic((String)map.get("pic"));
        course.setStatus((Integer)map.get("status"));
        course.setTeacherDescription((String)map.get("teacher_description"));
        course.setUrl((String)map.get("url"));
        resList.add(course);
      }
    }
    return resList;
  }
    
  @Override
  public void modifyCourseStauts(String ids) {
    HashMap<String,Object> params = new HashMap<String, Object>();
    params.put("ids",ids);
    iacDB.update("modifyCourseStautsByIds",params);
  }

  @Override
  public int getCourses(long classId, List<HashMap<String, Object>> resList) {
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
      List<HashMap<String, Object>> tempList = iacDB.getList("getPermCourses", params);
      if(tempList != null)
        resList.addAll(tempList);
      return 200;
    } catch (Exception e) {
      e.printStackTrace();
      return 500;
    }
  }

  @Override
  public int addLessonDefine(HashMap<String, Object> lesson) {
    return (int)iacDB.insertDynamicRInt("lesson_define", lesson);
  }

  @Override
  public void addLessonDetail(HashMap<String, Object> detail) {
    iacDB.insertDynamic("lesson_define_detail",detail);
  }

  @Override
  public HashMap<String, Object> getLessonList(DataGridModel dm, HashMap<String, String> params) {
    HashMap<String,Object> search = new HashMap<String, Object>();
    String gradeId = params.get("gradeId");
    if(StringUtils.isNotBlank(gradeId)&&Integer.parseInt(gradeId) > -1){
      search.put("gradeId", gradeId);
    }
    return iacDB.getDataGrid("getLessonList",dm,search);
  }

  @Override
  public List<HashMap<String, Object>> getLessonDetailList(int lessonId) {
    HashMap<String,Object> search = new HashMap<String, Object>();
    search.put("lessonId", lessonId);
    return iacDB.getList("getLessonDetailList",search);
  }
  
  public List<HashMap<String, Object>> getLessonTempDetailList(int lessonId) {
    HashMap<String,Object> search = new HashMap<String, Object>();
    search.put("lessonId", lessonId);
    return iacDB.getList("getLessonTempDetailList",search);
  }
  
  @Override
  public void deleteLesson(long[] ids) {
    String delIds = null;
    HashMap<String,Object> params = new HashMap<String, Object>();
    if(ids != null){
        for(long id : ids){
            delIds += ","+id;
        }
        params.put("ids",delIds);
    }
    iacDB.delete("deleteLessonDetail", params);
    iacDB.delete("deleteLesson", params);
  }
  
  
  
  
  @Override
  public void deleteLessonTemp(long[] ids) {
    String delIds = null;
    HashMap<String,Object> params = new HashMap<String, Object>();
    if(ids != null){
        for(long id : ids){
            delIds += ","+id;
        }
        params.put("ids",delIds);
    }
    iacDB.delete("deleteLessonTempDetail", params);
    iacDB.delete("deleteLessonTemp", params);
  }

  public long getGrade(long classId){
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
  
  @Override
  public int getLessons(long classId, List<HashMap<String, Object>> resList) {
    if(classId <= 0)
      return 400;
    HashMap<String,Object> params = new HashMap<String, Object>();
    try {
      long gradeId = getGrade(classId);
      params.put("GRADE_ID",gradeId);
      int tem = 1;
      if(DateUtil.getCurMonth() > 8) {
        tem = 2;
      } 
      params.put("TERM",tem);
      
      List<HashMap<String, Object>> tempList = iacDB.getList("getPermLessonList", params);
      if(tempList != null){
        resList.addAll(tempList);
      }
      return 200;
    } catch (Exception e) {
      e.printStackTrace();
      return 500;
    }
  }

  @Override
  public void insertLessonPlan(List<HashMap<String, Object>> plans) {
   if(plans != null){
     for(HashMap<String,Object> plan : plans){
       if(plan != null){
         iacDB.insertDynamic("lesson_plans",plan);
       }
     }
   }
    
  }
  
  public void insertLessonTempPlan(List<HashMap<String, Object>> plans) {
    if(plans != null){
      for(HashMap<String,Object> plan : plans){
        if(plan != null){
          iacDB.insertDynamic("lesson_temp_plans",plan);
        }
      }
    }
     
   }

  @Override
  public void deleteLessonPlans(int lessonId) {
    HashMap<String,Object> params = new HashMap<String, Object>();
    params.put("lessonId",lessonId);
    params.put("date",new Date());
    iacDB.delete("deleteLessonPlans", params);
  }
  
  public void deleteLessonTempPlans(int lessonId) {
    HashMap<String,Object> params = new HashMap<String, Object>();
    params.put("lessonId",lessonId);
    params.put("date",new Date());
    iacDB.delete("deleteLessonTempPlans", params);
  }

  @Override
  public HashMap<String, Object> getLessonPlan(int lessonId, int lessonNum,int lessonWeek) {
    HashMap<String,Object> params = new HashMap<String, Object>();
    params.put("lessonId",lessonId);
    params.put("lessonNum",lessonNum);
    params.put("lessonWeek",lessonWeek);
    params.put("date",new Date());
    List<HashMap<String, Object>> list = iacDB.getList("getLessonPlan", params);
    if(list!=null&&list.size()>0)
      return list.get(0);
    return null;
  }

  @Override
  public int addLessonTempDefine(HashMap<String, Object> lesson) {
    return (int)iacDB.insertDynamicRInt("lesson_temp_define", lesson);
  }

  @Override
  public void addLessonTempDetail(HashMap<String, Object> detail) {
    iacDB.insertDynamic("lesson_temp_define_detail",detail);
  }
  
  public HashMap<String, Object> getLessonTempList(DataGridModel dm, HashMap<String, String> params) {
    HashMap<String,Object> search = new HashMap<String, Object>();
    String gradeId = params.get("gradeId");
    if(StringUtils.isNotBlank(gradeId)&&Integer.parseInt(gradeId) > -1){
      search.put("gradeId", gradeId);
    }
    return iacDB.getDataGrid("getLessonTempList",dm,search);
  }

  @Override
  public HashMap<String, Object> getCategoryPlanList(DataGridModel dm,
      HashMap<String, String> params) {
    HashMap<String,Object> search = new HashMap<String, Object>();
    String name = params.get("name");
    String gradeId = params.get("gradeId");
    String category = params.get("category");
    if(StringUtils.isNotBlank(name)){
      search.put("name", name);
    }
    if(StringUtils.isNotBlank(gradeId)&&Integer.parseInt(gradeId) > 0){
        search.put("gradeId", gradeId);
    }
    if(StringUtils.isNotBlank(category)&&Integer.parseInt(category) > 0){
      search.put("category", category);
  }
    return iacDB.getDataGrid("getCategoryplanList", dm, search);
  }

  @Override
  public int getHasTempLesson(long classId) {
    long gradeId = getGrade(classId);
    HashMap<String,Object> params = new HashMap<String, Object>();
    params.put("GRADE_ID",gradeId);
    params.put("NOW_TIME",new Date());
    List<HashMap<String,Object>> idList = iacDB.getList("getLessonTempId", params);
    if(idList != null && idList.size() > 0){
      HashMap<String,Object> temp = idList.get(0);
      if(temp != null){
        int id = (Integer)temp.get("ID");
        return id;
      }
    }
    return 0;
  }

  @Override
  public int getLessonsTemp(int lessonId, List<HashMap<String, Object>> resList) {
    if(lessonId <= 0)
      return 400;
    HashMap<String,Object> params = new HashMap<String, Object>();
    try {
      params.put("LESSON_ID",lessonId);
      
      List<HashMap<String, Object>> tempList = iacDB.getList("getPermLessonTempList", params);
      if(tempList != null){
        resList.addAll(tempList);
      }
      return 200;
    } catch (Exception e) {
      e.printStackTrace();
      return 500;
    }
  }

  @Override
  public HashMap<String, Object> getLessonTempPlan(int lessonId, int lessonNum, int lessWeek) {
    HashMap<String,Object> params = new HashMap<String, Object>();
    params.put("lessonId",lessonId);
    params.put("lessonNum",lessonNum);
    params.put("lessonWeek",lessWeek);
    params.put("date",new Date());
    List<HashMap<String, Object>> list = iacDB.getList("getLessonTempPlan", params);
    if(list!=null&&list.size()>0)
      return list.get(0);
    return null;
  }

@Override
public HashMap<String, Object> getLessonMsgList(DataGridModel dm,
        HashMap<String, String> params) {
    HashMap<String,Object> search = new HashMap<String, Object>();
    String title = params.get("title");
    String gradeId = params.get("gradeId");
    if(StringUtils.isNotBlank(title)){
      search.put("title", title);
    }
    if(StringUtils.isNotBlank(gradeId)&&Integer.parseInt(gradeId) > 0){
        search.put("gradeId", gradeId);
    }

    return iacDB.getDataGrid("getLessonMsgList", dm, search);
}

    @Override
    public void saveLessonMsg(HashMap<String, String> msg) {
        iacDB.insertDynamic("lesson_message", msg);
    }

    @Override
    public HashMap<String, Object> getLessonMsg(int id) {
        return iacDB.get("getLessonMsgById", id);
    }

    @Override
    public void updateLessonMsg(HashMap<String, String> msg) {
        iacDB.updateDynamic("lesson_message", "ID", msg);
    }

    @Override
    public void deleteLessonMsg(String ids) {
        List<String> idList = new ArrayList<String>();
        if(StringUtils.isNotBlank(ids)){
            String[] idArr = ids.split(",");
            for(String id : idArr){
                if(StringUtils.isNotBlank(id)){
                    idList.add(id);
                }
                
            }
        }
        iacDB.deleteBatchDynamic("lesson_message", "ID", idList);
    }

    @Override
    public List<HashMap<String, Object>> getLessonMessage(int classId) {
      long gradeId = this.getGrade(classId);
      HashMap<String,Object> params = new HashMap<String, Object>();
      params.put("gradeId",gradeId);
      params.put("startTime",new Date());
      return iacDB.getList("getPermLessonMessage",params);
    }

    @Override
    public int getLessonMessageCount(int classId) {
      long gradeId = this.getGrade(classId);
      HashMap<String,Object> params = new HashMap<String, Object>();
      params.put("gradeId",gradeId);
      params.put("startTime",new Date());
      HashMap<String,Object> resMap = iacDB.get("getPermLessonMessageCount", params);
      long count = 0;
      if(resMap != null){
        count = (Long)resMap.get("msgCount");
      }
      return (int)count;
    }

    @Override
    public List<HashMap<String, Object>> getCoursePlan(long gradeId,int offset,int pagesize) {
      HashMap<String,Object> params = new HashMap<String, Object>();
      params.put("gradeId",gradeId);
      params.put("offset",offset);
      params.put("pagesize",pagesize);
      return iacDB.getList("getCoursePlanLimit", params);
    }

    @Override
    public long getCoursePlanCount(long gradeId) {
      HashMap<String,Object> params = new HashMap<String, Object>();
      params.put("gradeId",gradeId);
      HashMap<String,Object> resMap = iacDB.get("getCoursePlanCount", params);
      long count = 0;
      if(resMap != null){
        count = (Long)resMap.get("planCount");
      }
      return count;
    }

    private final static String fileName = "/uploadFile/file/";
    @Override
    public void saveCoursePlan(HashMap<String,String> plan) {
      String imageUrl = (String)plan.get("image_url");
      plan.put("image_url",fileName+imageUrl);
      iacDB.insertDynamic("course_plan", plan);
    }

    
    @Override
    public void updateCoursePlan(HashMap<String,String> plan) {
      String imageUrl = (String)plan.get("image_url");
      if(imageUrl.contains("uploadFile")){
        plan.put("image_url",imageUrl);
      } else{
        plan.put("image_url",fileName+imageUrl);
      }
      iacDB.updateDynamic("course_plan","id", plan);
    }

    @Override
    public HashMap<String, Object> getCoursePlanById(long id) {
      return iacDB.get("getCoursePlanById", id);
    }

    @Override
    public void saveCategoryPlan(HashMap<String, String> plan) {
      String imageUrl = (String)plan.get("PLAN_URL");
      plan.put("PLAN_URL",fileName+imageUrl);
      int categoryId = Integer.parseInt(plan.get("CATEGORY_ID"));
      HashMap<String, Object> category = this.getCategoryById(categoryId);
      String categoryName = "";
      if(category != null){
        categoryName = (String)category.get("name");
      }
      plan.put("CATEGORY_NAME",categoryName);
      iacDB.insertDynamic("term_plan", plan);
    }

    @Override
    public HashMap<String, Object> getCategoryById(int id) {
      return iacDB.get("getCourseCategoryById",id);
    }

    @Override
    public HashMap<String, Object> getCategoryPlan(int id) {
      return iacDB.get("getCategoryPlanById",id);
    }

    @Override
    public void updateCategoryPlan(HashMap<String, String> plan) {
      String imageUrl = (String)plan.get("PLAN_URL");
      if(imageUrl.contains("uploadFile")){
        plan.put("PLAN_URL",imageUrl);
      } else{
        plan.put("PLAN_URL",fileName+imageUrl);
      }
      int categoryId = Integer.parseInt(plan.get("CATEGORY_ID"));
      HashMap<String, Object> category = this.getCategoryById(categoryId);
      String categoryName = "";
      if(category != null){
        categoryName = (String)category.get("name");
      }
      plan.put("CATEGORY_NAME",categoryName);
      iacDB.updateDynamic("term_plan","ID", plan);
    }

    @Override
    public void deleteCategoryPlan(int id) {
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("ID",id);
        iacDB.deleteDynamic("term_plan", params);
    }

    @Override
    public void setCoursePlanStatus(int id, int status) {
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("id",id);
      map.put("status",status);
      iacDB.updateDynamic("course_plan","id",map);
    }

    @Override
    public void setCategoryPlanStatus(int id, int status) {
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("ID",id);
      map.put("STATUS",status);
      iacDB.updateDynamic("term_plan","ID",map);
    }

    @Override
    public List<HashMap<String, Object>> getCategoryPlan(long gradeId) {
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("gradeId",gradeId);
      map.put("time",new Date());
      return iacDB.getList("getCategoryPlanList", map);
    }
  
    
    
    
    
    
    
   
  
    
}