package com.smlearning.application.service;

import java.util.HashMap;
import java.util.List;

import cn.com.iactive.db.DataGridModel;

import com.smlearning.domain.entity.Course;
import com.smlearning.domain.entity.CoursePlan;
import com.smlearning.domain.entity.classBook;
import com.smlearning.domain.entity.courseTable;
import com.smlearning.domain.entity.enums.CourseStatusEnum;
import com.smlearning.domain.entity.extend.CourseExtend;
import com.smlearning.domain.vo.CoursePlanVO;
import com.smlearning.domain.vo.CourseVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;


/**
 * 课程接口
 * 
 * @author Administrator
 */
public interface CourseService {

  /**
   * 返回课程列表数据信息
   * 
   * @param sysMessageVO
   * @param ph
   * @return
   */
  public DataGrid queryCoursePaning(CourseVO courseVO, PageHelper ph);

  /**
   * 创建课程
   * 
   * @param name 课程名称
   * @param courseCategoryId 课程分类编号
   * @param lectuer主讲人
   * @param hour学时
   * @param description课程简介
   * @param teacherDescription 老师简介
   * @param canUpload 是否上传
   * @param courseDescription
   * @param creator 创建人
   * @return
   * @throws Exception
   */
  public Course createCourse(String name, Long coursewareCategoryId, String lectuer, Integer hour,
      String description, String teacherDescription, String pic, String url, Long creator,
      Long gradeId, Integer isPublic) throws Exception;

  /**
   * 修改课程
   * 
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
  public Course modifyCourse(Long courseId, Long coursewareCategoryId, String name, String lectuer,
      Integer hour, String description, String teacherDescription, String pic, String url,
      Long creator, Long gradeId, Integer isPublic) throws Exception;


  /**
   * 删除课程
   * 
   * @param courseId 课程编号
   * @param userId 操作者
   * @throws NoCourseException 无此课程
   * @throws CourseAlreadyUseException 此课程已经被选用
   */
  public void removeCourse(Long courseId) throws Exception;

  /**
   * 修改课程状态
   * 
   * @param courseId 课程编号
   * @param statusEnum 状态
   * @param userId 操作者
   * @throws NoCourseException 无此课程
   * @throws NonEnoughAccessException 无足够的权限
   */
  public void modifyCourseStatus(Long courseId, CourseStatusEnum statusEnum) throws Exception;

  /**
   * 获得课程对象信息
   * 
   * @param courseId 课程编号
   */
  public Course getCourse(Long courseId) throws Exception;

  /**
   * 返回数据信息
   * 
   * @return
   */
  public List<CourseExtend> queryCourse(Long classId);

  /**
   * 返回数据信息
   * 
   * @return
   */
  public CourseExtend queryCourseById(Long courseId);

  /**
   * 返回数据信息
   * 
   * @return
   */
  public List<CoursePlan> queryCoursePlan();

  /**
   * 返回课程列表数据信息
   * 
   * @param sysMessageVO
   * @param ph
   * @return
   */
  public DataGrid queryCoursePlanPaning(CoursePlanVO coursePlanVO, PageHelper ph);

  /**
   * 修改课件
   * 
   * @param coursewareId 课件编号
   * @param name 课件名称
   * @param url 课件网址
   * @return
   * @throws NoCoursewareException 无此课件
   */
  public CoursePlan modifyCoursePlan(Long coursePlanId, String name, String imageUrl, long gradeId)
      throws Exception;

  /**
   * 创建
   * 
   * @param name 课件名称
   * @param courseSubjectId 课程专题编号
   * @param url 课件网址
   * @return
   * @throws NocourseSubjectException 无此课程专题
   */
  public CoursePlan createCoursePlan(String name, String imageUrl, long gradeId) throws Exception;

  /**
   * 删除课件
   * 
   * @param coursewareId 课件编号
   * @throws NoCoursewareException 无此课件
   */
  public void removeCoursePlan(Long coursePlanId) throws Exception;

  /**
   * 获得课程对象信息
   * 
   * @param courseId 课程编号
   */
  public CoursePlan getCoursePlan(Long coursePlanId) throws Exception;

  /**
   * 返回数据信息
   * 
   * @return
   */
  public List<courseTable> queryCourseTable();

  /**
   * 返回课程列表数据信息
   * 
   * @param sysMessageVO
   * @param ph
   * @return
   */
  public DataGrid queryCourseTablePaning(PageHelper ph);

  /**
   * 创建
   * 
   * @param name 课件名称
   * @param courseSubjectId 课程专题编号
   * @param url 课件网址
   * @return
   * @throws NocourseSubjectException 无此课程专题
   */
  public courseTable createCourseTable(String name, String imageUrl) throws Exception;


  /**
   * 修改课件
   * 
   * @param coursewareId 课件编号
   * @param name 课件名称
   * @param url 课件网址
   * @return
   * @throws NoCoursewareException 无此课件
   */
  public courseTable modifyCourseTable(Long courseTableId, String name, String imageUrl)
      throws Exception;

  /**
   * 删除课件
   * 
   * @param coursewareId 课件编号
   * @throws NoCoursewareException 无此课件
   */
  public void removeCourseTable(Long courseTableId) throws Exception;

  /**
   * 获得课程对象信息
   * 
   * @param courseId 课程编号
   */
  public courseTable getCourseTable(Long courseTableId) throws Exception;

  /**
   * 返回数据信息
   * 
   * @return
   */
  public List<classBook> queryClassBook();

  /**
   * 获得课程对象信息
   * 
   * @param courseId 课程编号
   */
  public classBook getClassBook(Long classBookId) throws Exception;

  /**
   * 删除课件
   * 
   * @param coursewareId 课件编号
   * @throws NoCoursewareException 无此课件
   */
  public void removeClassBook(Long classBookId) throws Exception;

  /**
   * 修改课件
   * 
   * @param coursewareId 课件编号
   * @param name 课件名称
   * @param url 课件网址
   * @return
   * @throws NoCoursewareException 无此课件
   */
  public classBook modifyclassBook(Long classBookId, String name, String imageUrl) throws Exception;

  /**
   * 创建
   * 
   * @param name 课件名称
   * @param courseSubjectId 课程专题编号
   * @param url 课件网址
   * @return
   * @throws NocourseSubjectException 无此课程专题
   */
  public classBook createClassBook(String name, String imageUrl) throws Exception;

  /**
   * 返回课程列表数据信息
   * 
   * @param sysMessageVO
   * @param ph
   * @return
   */
  public DataGrid queryClassBookPaning(PageHelper ph);


  public HashMap<String, Object> getCourseList(DataGridModel dm, HashMap<String, String> params);

  public void deleteCourse(long[] ids);

  public List<Course> getCourseListByIds(String ids);

  public void modifyCourseStauts(String ids);

  public int getCourses(long classId, List<HashMap<String, Object>> resList);

  public HashMap<String, Object> getCoursePlanList(DataGridModel dm, HashMap<String, String> params);

  public List<CoursePlan> getCoursePlan(long gradeId);

  public int addLessonDefine(HashMap<String, Object> lesson);

  public void addLessonDetail(HashMap<String, Object> detail);

  public HashMap<String, Object> getLessonList(DataGridModel dm, HashMap<String, String> params);

  public List<HashMap<String,Object>> getLessonDetailList(int lessonId);
  
  public List<HashMap<String,Object>> getLessonTempDetailList(int lessonId);
  
  public void deleteLesson(long[] ids);
  
  public void deleteLessonTemp(long[] ids);
  
  public int getLessons(long classId, List<HashMap<String, Object>> resList);
  
  public void insertLessonPlan(List<HashMap<String,Object>> plans);
  
  public void insertLessonTempPlan(List<HashMap<String,Object>> plans);
  
  public void deleteLessonPlans(int lessonId);
  
  public void deleteLessonTempPlans(int lessonId);
  
  public HashMap<String,Object> getLessonPlan(int lessonId,int lessonNum,int lessWeek);
  
  public int addLessonTempDefine(HashMap<String, Object> lesson);
  
  public void addLessonTempDetail(HashMap<String, Object> detail);
  
  public HashMap<String, Object> getLessonTempList(DataGridModel dm, HashMap<String, String> params);
  
  public HashMap<String, Object> getCategoryPlanList(DataGridModel dm, HashMap<String, String> params);
}
