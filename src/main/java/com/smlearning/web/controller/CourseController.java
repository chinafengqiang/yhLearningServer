package com.smlearning.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.com.iactive.db.DataGridModel;

import com.smlearning.application.service.CourseCategoryService;
import com.smlearning.application.service.CourseCoursewareService;
import com.smlearning.application.service.CourseScheduleService;
import com.smlearning.application.service.CourseService;
import com.smlearning.application.service.SysKeyService;
import com.smlearning.domain.entity.Course;
import com.smlearning.domain.entity.CourseCategory;
import com.smlearning.domain.entity.CourseCourseware;
import com.smlearning.domain.entity.CoursePlan;
import com.smlearning.domain.entity.CourseSchedule;
import com.smlearning.domain.entity.Courseware;
import com.smlearning.domain.entity.CoursewareCategory;
import com.smlearning.domain.entity.Lesson;
import com.smlearning.domain.entity.Manager;
import com.smlearning.domain.entity.UserInfo;
import com.smlearning.domain.entity.classBook;
import com.smlearning.domain.entity.courseTable;
import com.smlearning.domain.entity.enums.CourseStatusEnum;
import com.smlearning.domain.entity.extend.CourseExtend;
import com.smlearning.domain.entity.extend.LessonExtend;
import com.smlearning.domain.vo.CourseNode;
import com.smlearning.domain.vo.CoursePlanVO;
import com.smlearning.domain.vo.CourseScheduleVO;
import com.smlearning.domain.vo.CourseVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.DateUtil;
import com.smlearning.infrastructure.utils.FileOperation;
import com.smlearning.infrastructure.utils.FileUtil;
import com.smlearning.infrastructure.utils.ImportExecl;
import com.smlearning.infrastructure.utils.Json;
import com.smlearning.infrastructure.utils.PageHelper;
import com.smlearning.infrastructure.utils.ParamUtils;
import com.smlearning.infrastructure.utils.SystemUtil;
import com.smlearning.infrastructure.utils.VersionInfo;

/**
 * 课程控制层处理
 */
@Controller
@RequestMapping("/courseController")
public class CourseController extends BaseController {

  static Logger logger = Logger.getLogger(CourseController.class.getName());

  @Autowired
  private CourseService courseService;

  @Autowired
  private CourseCoursewareService courseCoursewareService;

  @Autowired
  private CourseCategoryService courseCategoryService;

  @Autowired
  private CourseScheduleService courseScheduleService;

  @Autowired
  private SysKeyService sysKeyService;

  /**
   * 跳转到课程管理页面
   * 
   * @return
   */
  @RequestMapping("/managecourse")
  public String manageCourse() {
    return "jsp/study/manageCourse";
  }

  /**
   * 获取课程数据表格
   * 
   * @param managerVO
   * @return
   */
  @RequestMapping("/dataGridCourse")
  @ResponseBody
  public DataGrid dactaGridCourse(CourseVO courseVO, PageHelper ph) {
    return courseService.queryCoursePaning(courseVO, ph);
  }

  @RequestMapping(value = "getCourseList")
  @ResponseBody
  public HashMap<String, Object> getCourseList(DataGridModel dm, HttpServletRequest request) {
    HashMap<String, String> params = ParamUtils.getFilterStringParams(request);
    HashMap<String, Object> resMap = courseService.getCourseList(dm, params);
    return resMap;
  }

  /**
   * 跳转到课程进度管理页面
   * 
   * @return
   */
  @RequestMapping("/manageSchedule")
  public String manageSchedule() {
    return "jsp/study/manageCourseSchedule";
  }

  /**
   * 获取课程进度数据表格
   * 
   * @param managerVO
   * @return
   */
  @RequestMapping("/dataGridSchedule")
  @ResponseBody
  public DataGrid dataGridSchedule(CourseScheduleVO scheduleVO, PageHelper ph) {
    return courseScheduleService.querySchedulePaning(scheduleVO, ph);
  }



  /**
   * 跳转到添加课程进度页面
   * 
   * @param request
   * @return
   */
  @RequestMapping("/addPageSchedule")
  public String addPageSchedule(HttpServletRequest request) {
    return "jsp/study/addCourseSchedule";
  }

  /**
   * 添加课程进度
   * 
   * @param courseScheduleVO
   * @return
   */
  @RequestMapping("/createCourseSchedule")
  @ResponseBody
  public Json createCourseSchedule(CourseScheduleVO courseScheduleVO) {
    Json json = new Json();
    try {

      this.courseScheduleService.createCourseSchedule(courseScheduleVO.getWeekName(),
          courseScheduleVO.getLevelOne(), courseScheduleVO.getLevelTwo(),
          courseScheduleVO.getLevelThree(), courseScheduleVO.getLevelFour(),
          courseScheduleVO.getLevelFive(), courseScheduleVO.getLevelSix(),
          courseScheduleVO.getLevelSeven(), courseScheduleVO.getLevelEight(),
          courseScheduleVO.getSuperGrade(), courseScheduleVO.getSuperClass());

      json.setSuccess(true);
      json.setMsg("添加成功！");
      json.setObj(courseScheduleVO);

    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * 跳转到消息页面
   * 
   * @return
   */
  @RequestMapping("/editCourseSchedule")
  public String editCourseSchedule(HttpServletRequest request, Long id) {

    CourseSchedule courseSchedule;
    try {
      courseSchedule = courseScheduleService.getCourseScheduleById(id);
      request.setAttribute("courseSchedule", courseSchedule);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "jsp/study/editCourseSchedule";
  }

  /**
   * 修改课程进度
   * 
   * @param courseScheduleVO
   * @return
   */
  @RequestMapping("/modifyCourseSchedule")
  @ResponseBody
  public Json modifyCourseSchedule(CourseScheduleVO courseScheduleVO) {
    Json json = new Json();

    try {
      this.courseScheduleService.modifyCourseSchedule(courseScheduleVO.getId(),
          courseScheduleVO.getWeekName(), courseScheduleVO.getLevelOne(),
          courseScheduleVO.getLevelTwo(), courseScheduleVO.getLevelThree(),
          courseScheduleVO.getLevelFour(), courseScheduleVO.getLevelFive(),
          courseScheduleVO.getLevelSix(), courseScheduleVO.getLevelSeven(),
          courseScheduleVO.getLevelEight(), courseScheduleVO.getSuperGrade(),
          courseScheduleVO.getSuperClass());

      json.setSuccess(true);
      json.setMsg("修改成功！");
      json.setObj(courseScheduleVO);

    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * 跳转到添加课程页面
   * 
   * @param request
   * @return
   */
  @RequestMapping("/addPage")
  public String addPage(HttpServletRequest request) {
    request.setAttribute("classList", sysKeyService.queryAllByClass());
    return "jsp/study/addCourse";
  }

  /**
   * 跳转到消息页面
   * 
   * @return
   */
  @RequestMapping("/editcourse")
  public String editcourse(HttpServletRequest request, Long id) {

    Course course;
    try {
      course = courseService.getCourse(id);
      request.setAttribute("course", course);
      // request.setAttribute("classList", sysKeyService.queryAllByClass());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "jsp/study/editCourse";
  }


  /**
   * 添加课程
   * 
   * @param courseVO
   * @return
   */
  @RequestMapping("/createCourse")
  @ResponseBody
  public Json createCourse(CourseVO courseVO, HttpSession session) {
    Json json = new Json();
    Manager manager = (Manager) session.getAttribute("manager");
    try {

      this.courseService.createCourse(courseVO.getName(), courseVO.getPid(), courseVO.getLectuer(),
          courseVO.getHour(), courseVO.getDescription(), courseVO.getTeacherDescription(),
          courseVO.getPic(), courseVO.getUrl(), manager.getId(), courseVO.getGradeId(),
          courseVO.getIsPublic());
      json.setSuccess(true);
      json.setMsg("添加成功！");
      json.setObj(courseVO);

    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;

  }


  /**
   * 修改课程
   * 
   * @param courseVO
   * @return
   */
  @RequestMapping("/modifyCourse")
  @ResponseBody
  public Json modifyCourse(CourseVO courseVO) {
    Json json = new Json();

    try {
      this.courseService.modifyCourse(courseVO.getId(), courseVO.getPid(), courseVO.getName(),
          courseVO.getLectuer(), courseVO.getHour(), courseVO.getDescription(),
          courseVO.getTeacherDescription(), courseVO.getPic(), courseVO.getUrl(),
          courseVO.getCreator(), courseVO.getGradeId(), courseVO.getIsPublic());

      json.setSuccess(true);
      json.setMsg("修改成功！");
      json.setObj(courseVO);

    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * 删除课程
   * 
   * @param id
   * @param session
   * @return
   */
  @RequestMapping("/removecourse")
  @ResponseBody
  public Json removeCourse(Long id, HttpSession session) {
    Json json = new Json();

    try {
      this.courseService.removeCourse(id);
      json.setMsg("删除成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }


  /**
   * 删除课程表进度
   * 
   * @param id
   * @param session
   * @return
   */
  @RequestMapping("/removeCourseSchedule")
  @ResponseBody
  public Json removeCourseSchedule(Long id, HttpSession session) {
    Json json = new Json();

    try {
      this.courseScheduleService.removeCourseSchedule(id);
      json.setMsg("删除成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * 停用课程
   * 
   * @param id
   * @param session
   * @return
   */
  @RequestMapping("/closeCourse")
  @ResponseBody
  public Json closeCourse(Long id) {
    Json json = new Json();

    try {
      this.courseService.modifyCourseStatus(id, CourseStatusEnum.CLOSED);
      json.setMsg("停用成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;

  }

  /**
   * 启用课程
   * 
   * @param id
   * @param session
   * @return
   */
  @RequestMapping("/openCourse")
  @ResponseBody
  public Json openCourse(Long id) {
    Json json = new Json();

    try {
      this.courseService.modifyCourseStatus(id, CourseStatusEnum.OPENED);
      json.setMsg("启用成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;

  }


  /**
   * 添加课程分类
   * 
   * @param courseCategory
   * @return
   */
  @RequestMapping("/createcoursecategory")
  @ResponseBody
  public Json createCourseCategory(CourseCategory courseCategory, HttpSession session) {
    Json json = new Json();
    Manager manager = (Manager) session.getAttribute("manager");
    try {
      this.courseCategoryService.createCourseCategory(courseCategory.getName(),
          courseCategory.getParentId(), courseCategory.getSortFlag(), manager.getId());

      json.setSuccess(true);
      json.setMsg("添加成功！");
      json.setObj(courseCategory);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * 修改课程分类
   * 
   * @param courseVO
   * @return
   */
  @RequestMapping("/modifycoursecategory")
  @ResponseBody
  public Json modifyCourseCategory(CourseCategory courseCategory) {
    Json json = new Json();

    try {
      this.courseCategoryService.modifyCourseCategory(courseCategory.getId(),
          courseCategory.getName(), courseCategory.getSortFlag());
      json.setSuccess(true);
      json.setMsg("修改成功！");
      json.setObj(courseCategory);

    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * 删除课程分类
   * 
   * @param id
   * @param session
   * @return
   */
  @RequestMapping("/removecoursecategory")
  @ResponseBody
  public Json removeCourseCategory(Long id, HttpSession session) {
    Json json = new Json();

    try {
      this.courseCategoryService.removeCourseCategory(id);
      json.setMsg("删除成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * 课程引用课件
   * 
   * @param CourseCourseware
   * @return
   */
  @RequestMapping("/usecourseware")
  @ResponseBody
  public Json useCourseware(CourseCourseware courseCourseware, HttpSession session) {
    Json json = new Json();
    Manager manager = (Manager) session.getAttribute("manager");
    try {
      this.courseCoursewareService.useCourseware(courseCourseware.getCourseId(),
          courseCourseware.getCoursewareId(), manager.getId());
      json.setSuccess(true);
      json.setMsg("添加成功！");
      json.setObj(courseCourseware);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }


  /**
   * 取消引用课件
   * 
   * @param courseCourseware
   * @return
   */
  @RequestMapping("/unusecourseware")
  @ResponseBody
  public Json unUseCourseware(CourseCourseware courseCourseware) {
    Json json = new Json();
    try {
      this.courseCoursewareService.unUseCourseware(courseCourseware.getId());
      json.setSuccess(true);
      json.setMsg("修改成功！");
      json.setObj(courseCourseware);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * 生成村节点
   * 
   * @return
   */
  @RequestMapping("/createCourseNodeList")
  public String createCourseNodeList(HttpServletRequest request, Long id) {

    ArrayList<CourseNode> courseList = this.courseCategoryService.createCourseNodeList();
    request.setAttribute("item", courseList);
    return "jsp/study/manageCourseTree";
  }


  /**
   * 获取课程进度数据表格
   * 
   * @return
   */
  @RequestMapping("/dataGridBySchedule")
  @ResponseBody
  public DataGrid dataGridBySchedule(CourseScheduleVO scheduleVO, PageHelper ph) {
    return courseScheduleService.querySchedulePaning(scheduleVO, ph);
  }

  /**
   * 跳转到课程进度管理页面
   * 
   * @return
   */
  @RequestMapping("/manageLesson")
  public String manageLesson() {
    return "jsp/study/manageLesson";
  }
  
  
  @RequestMapping("/manageTempLesson")
  public String manageTempLesson() {
    return "jsp/study/manageTempLesson";
  }
  

  /**
   * 获取课程进度数据表格
   * 
   * @param managerVO
   * @return
   */
  @RequestMapping("/dataGridLesson")
  @ResponseBody
  public DataGrid dataGridSchedule(LessonExtend lessonExtend, PageHelper ph) {
    return courseScheduleService.queryLessonPaning(lessonExtend, ph);
  }

  /**
   * 跳转到添加课程页面
   * 
   * @param request
   * @return
   */
  @RequestMapping("/addLesson")
  public String addLesson(HttpServletRequest request) {

    request.setAttribute("classList", sysKeyService.queryAllByClass());
    request.setAttribute("subjectList", sysKeyService.queryAllBySubject());
    return "jsp/study/addLesson";
  }

  /**
   * 添加课程进度
   * 
   * @return
   */
  @RequestMapping("/createLesson")
  @ResponseBody
  public Json createLesson(Lesson lesson) {
    Json json = new Json();

    try {
      this.courseScheduleService.createLesson(lesson.getClassId(), lesson.getSubjectId(),
          lesson.getDay(), lesson.getThetime(), lesson.getTerm());

      json.setSuccess(true);
      json.setMsg("添加成功！");
      json.setObj(lesson);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * 跳转到消息页面
   * 
   * @return
   */
  @RequestMapping("/editLesson")
  public String editLesson(HttpServletRequest request, Long id) {

    Lesson lesson = null;
    try {
      lesson = courseScheduleService.getLessonById(id);
      request.setAttribute("lesson", lesson);
      request.setAttribute("classList", sysKeyService.queryAllByClass());
      request.setAttribute("subjectList", sysKeyService.queryAllBySubject());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "jsp/study/editLesson";
  }

  /**
   * 修改课程进度
   * 
   * @param courseVO
   * @return
   */
  @RequestMapping("/modifyLesson")
  @ResponseBody
  public Json modifyLesson(Lesson lesson) {
    Json json = new Json();

    try {
      this.courseScheduleService.modifyLesson(lesson.getId(), lesson.getClassId(),
          lesson.getSubjectId(), lesson.getDay(), lesson.getThetime(), lesson.getTerm());
      json.setSuccess(true);
      json.setMsg("修改成功！");
      json.setObj(lesson);

    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * @param id
   * @param session
   * @return
   */
  @RequestMapping("/removeLesson")
  @ResponseBody
  public Json removeLesson(Long id, HttpSession session) {
    Json json = new Json();

    try {
      this.courseScheduleService.removeLesson(id);
      json.setMsg("删除成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }
  
  
  @RequestMapping("/removeLessonTemp")
  @ResponseBody
  public Json removeLessonTemp(Long id, HttpSession session) {
    Json json = new Json();

    try {
      this.courseScheduleService.removeLesson(id);
      json.setMsg("删除成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * @param id
   * @param session
   * @return
   */
  @RequestMapping("/getLesson")
  @ResponseBody
  public List<LessonExtend> getLesson(LessonExtend lessonExtend) {
    List<LessonExtend> list = courseScheduleService.queryLesson(lessonExtend);

    return list;
  }

  /**
   * @param id
   * @param session
   * @return
   */
  @RequestMapping("/getCourseByAll")
  @ResponseBody
  public List<CourseExtend> getCourseByAll(Long classId) {
    List<CourseExtend> list = courseService.queryCourse(classId);
    return list;
  }

  @RequestMapping("/getPermCourses")
  @ResponseBody
  public HashMap<String, Object> getPermCourses(HttpServletRequest request) {
    long classId = ParamUtils.getLongParameter(request, "classId", 0);
    HashMap<String, Object> resMap = new HashMap<String, Object>();
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    int code = courseService.getCourses(classId, resList);
    resMap.put("code", code);
    resMap.put("info", resList);
    return resMap;
  }

  /**
   * @param id
   * @param session
   * @return
   */
  @RequestMapping("/getCourseById")
  @ResponseBody
  public CourseExtend getCourseById(Long courseId) {
    CourseExtend list = courseService.queryCourseById(courseId);
    return list;
  }

  /**
   * 返回数据信息
   * 
   * @return
   */
  @RequestMapping("/queryCourseTable")
  @ResponseBody
  public List<courseTable> queryCourseTable() {
    List<courseTable> list = courseService.queryCourseTable();
    return list;
  }


  /**
   * 跳转到课程管理页面
   * 
   * @return
   */
  @RequestMapping("/managecoursetable")
  public String manageCourseTable() {
    return "jsp/study/manageCourseTable";
  }

  /**
   * 获取课程数据表格
   * 
   * @param managerVO
   * @return
   */
  @RequestMapping("/dataGridCourseTable")
  @ResponseBody
  public DataGrid dataGridCourseTable(PageHelper ph) {
    return courseService.queryCourseTablePaning(ph);
  }


  /**
   * 跳转到添加课程页面
   * 
   * @param request
   * @return
   */
  @RequestMapping("/addCourseTable")
  public String addCourseTable(HttpServletRequest request) {
    return "jsp/study/addCourseTable";
  }

  /**
   * 添加课程进度
   * 
   * @return
   */
  @RequestMapping("/createCourseTable")
  @ResponseBody
  public Json createCourseTable(courseTable courseTable) {
    Json json = new Json();

    try {
      this.courseService.createCourseTable(courseTable.getName(), courseTable.getImageUrl());

      json.setSuccess(true);
      json.setMsg("添加成功！");
      json.setObj(courseTable);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * 跳转到消息页面
   * 
   * @return
   */
  @RequestMapping("/editCourseTable")
  public String editCourseTable(HttpServletRequest request, Long id) {

    courseTable courseTable = null;
    try {
      courseTable = courseService.getCourseTable(id);
      request.setAttribute("courseTable", courseTable);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "jsp/study/editCourseTable";
  }

  /**
   * 修改课程进度
   * 
   * @param courseVO
   * @return
   */
  @RequestMapping("/modifyCourseTable")
  @ResponseBody
  public Json modifyCourseTable(courseTable courseTable) {
    Json json = new Json();

    try {
      this.courseService.modifyCourseTable(courseTable.getId(), courseTable.getName(),
          courseTable.getImageUrl());
      json.setSuccess(true);
      json.setMsg("修改成功！");
      json.setObj(courseTable);

    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * @param id
   * @param session
   * @return
   */
  @RequestMapping("/removeCourseTable")
  @ResponseBody
  public Json removeCourseTable(Long id, HttpSession session) {
    Json json = new Json();

    try {
      this.courseService.removeCourseTable(id);
      json.setMsg("删除成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }


  /**
   * 返回数据信息
   * 
   * @return
   */
  @RequestMapping("/queryCoursePlan")
  @ResponseBody
  public List<CoursePlan> queryCoursePlan() {
    List<CoursePlan> list = courseService.queryCoursePlan();
    return list;
  }


  @RequestMapping("/getCoursePlan")
  @ResponseBody
  public List<CoursePlan> getCoursePlan(HttpServletRequest request) {
    long classId = ParamUtils.getLongParameter(request, "gradeId", 0);
    List<CoursePlan> res = courseService.getCoursePlan(classId);
    return res;
  }

  /**
   * 跳转到课程管理页面
   * 
   * @return
   */
  @RequestMapping("/managecourseplan")
  public String manageCoursePlan() {
    return "jsp/study/manageCoursePlan";
  }

  /**
   * 获取课程数据表格
   * 
   * @param managerVO
   * @return
   */
  @RequestMapping("/dataGridCoursePlan")
  @ResponseBody
  public DataGrid dataGridCoursePlan(CoursePlanVO coursePlanVO, PageHelper ph) {
    return courseService.queryCoursePlanPaning(coursePlanVO, ph);
  }

  @RequestMapping(value = "getCoursePlanList")
  @ResponseBody
  public HashMap<String, Object> getCoursePlanList(DataGridModel dm, HttpServletRequest request) {
    HashMap<String, String> params = ParamUtils.getFilterStringParams(request);
    HashMap<String, Object> resMap = courseService.getCoursePlanList(dm, params);
    return resMap;
  }

  /**
   * 跳转到添加课程页面
   * 
   * @param request
   * @return
   */
  @RequestMapping("/addCoursePlan")
  public String addCoursePlan(HttpServletRequest request) {
    return "jsp/study/addCoursePlan";
  }

  /**
   * 添加课程进度
   * 
   * @return
   */
  @RequestMapping("/createCoursePlan")
  @ResponseBody
  public Json createCoursePlan(CoursePlan coursePlan,HttpServletRequest request) {
    Json json = new Json();

    try {
      //this.courseService.createCoursePlan(coursePlan.getName(), coursePlan.getImageUrl(),
        //  coursePlan.getGradeId());
      HashMap<String,String> plan = ParamUtils.getFilterStringParams(request);
      courseService.saveCoursePlan(plan);
      json.setSuccess(true);
      json.setMsg("添加成功！");
      json.setObj(coursePlan);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * 跳转到消息页面
   * 
   * @return
   */
  @RequestMapping("/editCoursePlan")
  public String editCoursePlan(HttpServletRequest request, Long id) {

//    CoursePlan coursePlan = null;
//    try {
//      coursePlan = courseService.getCoursePlan(id);
//      request.setAttribute("coursePlan", coursePlan);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
    HashMap<String,Object> coursePlan = courseService.getCoursePlanById(id);
    request.setAttribute("coursePlan", coursePlan);
    return "jsp/study/editCoursePlan";
  }

  /**
   * 修改课程进度
   * 
   * @param courseVO
   * @return
   */
  @RequestMapping("/modifyCoursePlan")
  @ResponseBody
  public Json modifyCoursePlan(CoursePlan coursePlan,HttpServletRequest request) {
    Json json = new Json();

    try {
     // this.courseService.modifyCoursePlan(coursePlan.getId(), coursePlan.getName(),
      //    coursePlan.getImageUrl(), coursePlan.getGradeId());
      
      HashMap<String,String> plan = ParamUtils.getFilterStringParams(request);
      courseService.updateCoursePlan(plan);
      
      json.setSuccess(true);
      json.setMsg("修改成功！");
      json.setObj(coursePlan);

    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * @param id
   * @param session
   * @return
   */
  @RequestMapping("/removeCoursePlan")
  @ResponseBody
  public Json removeCoursePlan(Long id, HttpSession session) {
    Json json = new Json();

    try {
      this.courseService.removeCoursePlan(id);
      json.setMsg("删除成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  // -----------------

  /**
   * 返回数据信息
   * 
   * @return
   */
  @RequestMapping("/queryClassBook")
  @ResponseBody
  public List<classBook> queryClassBook() {
    List<classBook> list = courseService.queryClassBook();
    return list;
  }


  /**
   * 跳转到课程管理页面
   * 
   * @return
   */
  @RequestMapping("/manageClassBook")
  public String manageClassBook() {
    return "jsp/study/manageClassBook";
  }

  /**
   * 获取课程数据表格
   * 
   * @param managerVO
   * @return
   */
  @RequestMapping("/dataGridClassBook")
  @ResponseBody
  public DataGrid dataGridClassBook(PageHelper ph) {
    return courseService.queryClassBookPaning(ph);
  }


  /**
   * 跳转到添加课程页面
   * 
   * @param request
   * @return
   */
  @RequestMapping("/addClassBook")
  public String addClassBook(HttpServletRequest request) {
    return "jsp/study/addClassBook";
  }

  /**
   * 添加课程进度
   * 
   * @return
   */
  @RequestMapping("/createClassBook")
  @ResponseBody
  public Json createClassBook(classBook classBook) {
    Json json = new Json();

    try {
      this.courseService.createClassBook(classBook.getName(), classBook.getImageUrl());

      json.setSuccess(true);
      json.setMsg("添加成功！");
      json.setObj(classBook);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * 跳转到消息页面
   * 
   * @return
   */
  @RequestMapping("/editClassBook")
  public String editClassBook(HttpServletRequest request, Long id) {

    classBook classBook = null;
    try {
      classBook = courseService.getClassBook(id);
      request.setAttribute("classBook", classBook);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "jsp/study/editClassBook";
  }

  /**
   * 修改课程进度
   * 
   * @param courseVO
   * @return
   */
  @RequestMapping("/modifyClassBook")
  @ResponseBody
  public Json modifyClassBook(classBook classBook) {
    Json json = new Json();

    try {
      this.courseService.modifyclassBook(classBook.getId(), classBook.getName(),
          classBook.getImageUrl());
      json.setSuccess(true);
      json.setMsg("修改成功！");
      json.setObj(classBook);

    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * @param id
   * @param session
   * @return
   */
  @RequestMapping("/removeClassBook")
  @ResponseBody
  public Json removeClassBook(Long id, HttpSession session) {
    Json json = new Json();

    try {
      this.courseService.removeClassBook(id);
      json.setMsg("删除成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * 跳转到消息页面
   * 
   * @return
   */
  @RequestMapping("/editSenderCourse")
  public String editSenderCourse(HttpServletRequest request, Long id) {

    Course course;
    try {
      course = courseService.getCourse(id);
      String firstTime = DateUtil.dateToString(new Date(), false);

      request.setAttribute("firstTime", firstTime);
      request.setAttribute("course", course);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "jsp/study/editSenderCourse";
  }

  @RequestMapping("/editSenderCourses")
  public String editSenderCourses(HttpServletRequest request) {
    String firstTime = DateUtil.dateToString(new Date(), false);
    request.setAttribute("ids", request.getParameter("ids"));
    request.setAttribute("firstTime", firstTime);
    return "jsp/study/editSenderCourse";
  }

  @RequestMapping("/sendCourses")
  @ResponseBody
  public Json sendCourses(Long id, HttpServletRequest request) {
    Course course = null;
    try {
      course = courseService.getCourse(id);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    String pic = course.getPic();
    String insertContent =
        " insert into course (name, courseware_category_id, lectuer, hour, description, teacher_description"
            + ", status, pic, created_time, creator, url,grade_id,ispublic)" + " values ('"
            + course.getName()
            + "', "
            + course.getCoursewareCategoryId()
            + ", "
            + "'"
            + course.getLectuer()
            + "', "
            + course.getHour()
            + ",'"
            + course.getDescription()
            + "', "
            + "'"
            + course.getTeacherDescription()
            + "', "
            + course.getStatus()
            + " , '"
            + course.getPic()
            + "' , "
            + "'"
            + DateUtil.dateToString(course.getCreatedTime(), false)
            + "' , "
            + course.getCreator()
            + " , '"
            + course.getUrl()
            + "',"
            + course.getGradeId()
            + ","
            + course.getIsPublic()
            + " ); " + " commit; ";

    String uploadDir = request.getSession().getServletContext().getRealPath(course.getUrl());
    String uploadPic = "";
    if(StringUtils.isNotBlank(pic))
      uploadPic = request.getSession().getServletContext().getRealPath(course.getPic());
    String uploadDirName = uploadDir.substring(uploadDir.lastIndexOf("\\") + 1, uploadDir.length());
    String uploadPicName = "";
    if(StringUtils.isNotBlank(uploadPic))
      uploadPicName = uploadPic.substring(uploadPic.lastIndexOf("\\") + 1, uploadPic.length());
    System.out.println("uploadDir==" + uploadDir);
    System.out.println("uploadDirName==" + uploadDirName);

    String targetDir = "course" + id;
    String fileSqlName = "course_" + id + ".sql";
    Json json = new Json();
    String fileName = SystemUtil.createUUID();
    String destDirName = "d:/push_profile";
    String targetDirs = destDirName + "/" + targetDir;
    String picDir = targetDirs + "/pic";
    String filesDir = targetDirs + "/file";
    String path = destDirName + "/" + fileName + ".ini";
    FileOperation.createDir(picDir);
    FileOperation.createDir(filesDir);
    FileOperation.createDir(targetDirs);
    FileOperation.createDir(destDirName);
    String insertPath = destDirName + "/" + targetDir + "/" + fileSqlName;

    String firstTime = request.getParameter("beginTime");
    String endTime = request.getParameter("endTime");

    try {
      if(StringUtils.isNotBlank(uploadPicName))
        FileOperation.copyFile(new File(uploadPic), new File(picDir + "/" + uploadPicName));
      FileOperation.copyFile(new File(uploadDir), new File(filesDir + "/" + uploadDirName));
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    try {
      FileOperation.write(insertContent, insertPath, "UTF-8");

      genMovePathFile(request, destDirName + "/" + targetDir + "/");

      String content =
          "[option] \r\n" + "Dir=" + targetDir + " \r\n" + "channelID=" + VersionInfo.CHANNELID
              + " \r\n" + "priority=" + VersionInfo.PRIORITY + " \r\n" + "bandwidth="
              + VersionInfo.BANDWIDTH + " \r\n" + "PackFile=" + VersionInfo.PACKFILE + " \r\n"
              + "sendMode=" + VersionInfo.SENDMODE + " \r\n" + "sendTime=" + VersionInfo.SENDTIME
              + " \r\n" + "repeatcount=" + VersionInfo.REPEATCOUNT + " \r\n" + "validRate="
              + VersionInfo.VALIDRATE + " \r\n" + "startValidDate=" + firstTime + " \r\n"
              + "endValidDate=" + endTime + " \r\n" + "Completed=0 \r\n";
      FileOperation.contentToTxt(path, content);
      this.courseService.modifyCourseStatus(id, CourseStatusEnum.SENDED);
      json.setSuccess(true);
      json.setMsg("下发成功!");
    } catch (Exception e) {
      json.setMsg("下发失败！");
    }

    return json;
  }


  @RequestMapping("/sendCoursesBatch")
  @ResponseBody
  public Json sendCoursesBatch(HttpServletRequest request) {
    String firstTime = request.getParameter("beginTime");
    String endTime = request.getParameter("endTime");
    String ids = request.getParameter("ids");

    String filePk = "";
    if (StringUtils.isNotBlank(ids)) {
      String[] arr = ids.split(",");
      for (String id : arr) {
        if (id != null && !"null".equals(id) && !"".equals(id)) {
          filePk += id + "_";
        }
      }
    }
    if (filePk.length() > 1) {
      filePk = filePk.substring(0, filePk.length() - 1);
    }
    String targetDir = "course" + filePk;
    String fileSqlName = "course" + filePk + ".sql";
    Json json = new Json();
    String fileName = SystemUtil.createUUID();
    String destDirName = "d:/push_profile";
    String targetDirs = destDirName + "/" + targetDir;
    String picDir = targetDirs + "/pic";
    String filesDir = targetDirs + "/file";
    String path = destDirName + "/" + fileName + ".ini";

    String insertPath = destDirName + "/" + targetDir + "/" + fileSqlName;
    List<Course> courseList = courseService.getCourseListByIds(ids);
    StringBuilder insertContent = new StringBuilder();
    try {
      if (courseList != null && courseList.size() > 0) {
        FileOperation.createDir(picDir);
        FileOperation.createDir(filesDir);
        FileOperation.createDir(targetDirs);
        FileOperation.createDir(destDirName);
        for (int i = 0; i < courseList.size(); i++) {
          Course course = courseList.get(i);

          String pic = course.getPic();
          
          insertContent
              .append(" insert into course (name, courseware_category_id, lectuer, hour, description, teacher_description");
          insertContent.append(", status, pic, created_time, creator, url,grade_id,ispublic)");
          insertContent.append(" values ('" + course.getName() + "', "
              + course.getCoursewareCategoryId() + ", ");
          insertContent.append("'" + course.getLectuer() + "', " + course.getHour() + ",'"
              + course.getDescription() + "', ");
          insertContent.append("'" + course.getTeacherDescription() + "', " + course.getStatus()
              + " , '" + course.getPic() + "' , ");
          insertContent.append("'" + DateUtil.dateToString(course.getCreatedTime(), false) + "' , "
              + course.getCreator() + " , '" + course.getUrl() + "'," + course.getGradeId() + ","
              + course.getIsPublic() + " ); ");
          if (i == courseList.size() - 1) {
            insertContent.append("commit;");
          }

          String uploadDir = request.getSession().getServletContext().getRealPath(course.getUrl());
          String uploadPic = "";
          if(StringUtils.isNotBlank(pic))
            uploadPic = request.getSession().getServletContext().getRealPath(course.getPic());
          String uploadDirName =
              uploadDir.substring(uploadDir.lastIndexOf("\\") + 1, uploadDir.length());
          
          String uploadPicName = "";
          if(StringUtils.isNotBlank(uploadPic))
             uploadPicName = uploadPic.substring(uploadPic.lastIndexOf("\\") + 1, uploadPic.length());

          if(StringUtils.isNotBlank(uploadPicName))
             FileOperation.copyFile(new File(uploadPic), new File(picDir + "/" + uploadPicName));
          FileOperation.copyFile(new File(uploadDir), new File(filesDir + "/" + uploadDirName));
        }
        FileOperation.write(insertContent.toString(), insertPath, "UTF-8");

        genMovePathFile(request, destDirName + "/" + targetDir + "/");

      }

      String content =
          "[option] \r\n" + "Dir=" + targetDir + " \r\n" + "channelID=" + VersionInfo.CHANNELID
              + " \r\n" + "priority=" + VersionInfo.PRIORITY + " \r\n" + "bandwidth="
              + VersionInfo.BANDWIDTH + " \r\n" + "PackFile=" + VersionInfo.PACKFILE + " \r\n"
              + "sendMode=" + VersionInfo.SENDMODE + " \r\n" + "sendTime=" + VersionInfo.SENDTIME
              + " \r\n" + "repeatcount=" + VersionInfo.REPEATCOUNT + " \r\n" + "validRate="
              + VersionInfo.VALIDRATE + " \r\n" + "startValidDate=" + firstTime + " \r\n"
              + "endValidDate=" + endTime + " \r\n" + "Completed=0 \r\n";
      FileOperation.contentToTxt(path, content);
      courseService.modifyCourseStauts(ids);
      json.setSuccess(true);
      json.setMsg("下发成功!");
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg("下发失败！");
    }

    return json;
  }


  private void genMovePathFile(HttpServletRequest request, String destUrl) {
    String mpath = request.getParameter("m_path");
    if (StringUtils.isNotBlank(mpath)) {
      String path = destUrl + "copy_path.ini";
      String content = "Path=" + mpath;
      FileOperation.write(content, path, "UTF-8");
    }
  }

  @RequestMapping("/sendCoursePlans")
  @ResponseBody
  public Json sendCoursePlans(Long id, HttpServletRequest request) {
    //CoursePlan coursePlan = null;
    HashMap<String, Object> planMap = null;
    try {
      //coursePlan = courseService.getCoursePlan(id);
      planMap = courseService.getCoursePlanById(id);
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    String name = (String)planMap.get("name");
    String imageUrl = (String)planMap.get("image_url");
    Date startDate = (Date)planMap.get("start_date");
    Date endDate = (Date)planMap.get("end_date");

    String insertContent =
        " insert into course_plan (name, image_url,start_date,end_date)" + " values ('" + name + "', "
            + "'" + imageUrl + "'";
    if(startDate != null){
      insertContent += ",'"+startDate+"'";
    }else{
      insertContent += ",null";
    }
    if(endDate != null){
      insertContent += ",'"+endDate+"'";
    }else{
      insertContent += ",null";
    }
    
    insertContent += "); commit; ";

    String uploadDir =
        request.getSession().getServletContext().getRealPath(imageUrl);
    String uploadDirName = uploadDir.substring(uploadDir.lastIndexOf("\\") + 1, uploadDir.length());
    System.out.println("uploadDir==" + uploadDir);
    System.out.println("uploadDirName==" + uploadDirName);

    String targetDir = "coursePlan" + id;
    String fileSqlName = "coursePlan_" + id + ".sql";
    Json json = new Json();
    String fileName = SystemUtil.createUUID();
    String destDirName = "d:/push_profile";
    String targetDirs = destDirName + "/" + targetDir;
    String picDir = targetDirs + "/pic";
    String filesDir = targetDirs + "/file";
    String path = destDirName + "/" + fileName + ".ini";
    // FileOperation.createDir(picDir);
    FileOperation.createDir(filesDir);
    FileOperation.createDir(targetDirs);
    FileOperation.createDir(destDirName);
    String insertPath = destDirName + "/" + targetDir + "/" + fileSqlName;

    String firstTime = request.getParameter("beginTime");
    String endTime = request.getParameter("endTime");

    try {
      FileOperation.copyFile(new File(uploadDir), new File(filesDir + "/" + uploadDirName));
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    try {
      FileOperation.write(insertContent, insertPath, "UTF-8");

      String content =
          "[option] \r\n" + "Dir=" + targetDir + " \r\n" + "channelID=" + VersionInfo.CHANNELID
              + " \r\n" + "priority=" + VersionInfo.PRIORITY + " \r\n" + "bandwidth="
              + VersionInfo.BANDWIDTH + " \r\n" + "PackFile=" + VersionInfo.PACKFILE + " \r\n"
              + "sendMode=" + VersionInfo.SENDMODE + " \r\n" + "sendTime=" + VersionInfo.SENDTIME
              + " \r\n" + "repeatcount=" + VersionInfo.REPEATCOUNT + " \r\n" + "validRate="
              + VersionInfo.VALIDRATE + " \r\n" + "startValidDate=" + firstTime + " \r\n"
              + "endValidDate=" + endTime + " \r\n" + "Completed=0 \r\n";
      FileOperation.contentToTxt(path, content);
      courseService.setCoursePlanStatus(id.intValue(), 1);
      json.setSuccess(true);
      json.setMsg("下发成功!");
    } catch (Exception e) {
      json.setMsg("下发失败！");
    }

    return json;
  }

  /**
   * 跳转到消息页面
   * 
   * @return
   */
  @RequestMapping("/editProfilCoursePlan")
  public String editProfilCoursePlan(HttpServletRequest request, Long id) {

    CoursePlan coursePlan = null;
    try {
      coursePlan = courseService.getCoursePlan(id);

      String firstTime = DateUtil.dateToString(new Date(), false);
      request.setAttribute("firstTime", firstTime);
      request.setAttribute("coursePlan", coursePlan);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "jsp/study/editProfileCoursePlan";
  }



  @RequestMapping("/sendCourseTables")
  @ResponseBody
  public Json sendCourseTables(Long id, HttpServletRequest request) {
    courseTable courseTable = null;
    try {
      courseTable = courseService.getCourseTable(id);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    String insertContent =
        " insert into course_table (name, image_url)" + " values ('" + courseTable.getName()
            + "', " + "'" + courseTable.getImageUrl() + "'); commit; ";

    String uploadDir =
        request.getSession().getServletContext().getRealPath(courseTable.getImageUrl());
    String uploadDirName = uploadDir.substring(uploadDir.lastIndexOf("\\") + 1, uploadDir.length());
    System.out.println("uploadDir==" + uploadDir);
    System.out.println("uploadDirName==" + uploadDirName);

    String targetDir = "courseTable" + id;
    String fileSqlName = "courseTable" + id + ".sql";
    Json json = new Json();
    String fileName = SystemUtil.createUUID();
    String destDirName = "d:/push_profile";
    String targetDirs = destDirName + "/" + targetDir;
    String picDir = targetDirs + "/pic";
    String filesDir = targetDirs + "/file";
    String path = destDirName + "/" + fileName + ".ini";
    // FileOperation.createDir(picDir);
    FileOperation.createDir(filesDir);
    FileOperation.createDir(targetDirs);
    FileOperation.createDir(destDirName);
    String insertPath = destDirName + "/" + targetDir + "/" + fileSqlName;

    String firstTime = request.getParameter("beginTime");
    String endTime = request.getParameter("endTime");

    try {
      FileOperation.copyFile(new File(uploadDir), new File(filesDir + "/" + uploadDirName));
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    try {
      FileOperation.write(insertContent, insertPath, "UTF-8");

      String content =
          "[option] \r\n" + "Dir=" + targetDir + " \r\n" + "channelID=" + VersionInfo.CHANNELID
              + " \r\n" + "priority=" + VersionInfo.PRIORITY + " \r\n" + "bandwidth="
              + VersionInfo.BANDWIDTH + " \r\n" + "PackFile=" + VersionInfo.PACKFILE + " \r\n"
              + "sendMode=" + VersionInfo.SENDMODE + " \r\n" + "sendTime=" + VersionInfo.SENDTIME
              + " \r\n" + "repeatcount=" + VersionInfo.REPEATCOUNT + " \r\n" + "validRate="
              + VersionInfo.VALIDRATE + " \r\n" + "startValidDate=" + firstTime + " \r\n"
              + "endValidDate=" + endTime + " \r\n" + "Completed=0 \r\n";
      FileOperation.contentToTxt(path, content);
      // coursewareService.modifyCoursewareStauts(id);
      json.setSuccess(true);
      json.setMsg("下发成功!");
    } catch (Exception e) {
      json.setMsg("下发失败！");
    }

    return json;
  }

  /**
   * 跳转到消息页面
   * 
   * @return
   */
  @RequestMapping("/editProfilCourseTable")
  public String editProfilCourseTable(HttpServletRequest request, Long id) {

    courseTable courseTable = null;
    try {
      courseTable = courseService.getCourseTable(id);
      String firstTime = DateUtil.dateToString(new Date(), false);
      request.setAttribute("firstTime", firstTime);
      request.setAttribute("courseTable", courseTable);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // CoursePlan coursePlan = null;
    // try {
    // coursePlan = courseService.getCoursePlan(id);
    //
    // String firstTime = DateUtil.dateToString(new Date(), false);
    // request.setAttribute("firstTime", firstTime);
    // request.setAttribute("coursePlan", coursePlan);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    return "jsp/study/editProfileCourseTable";
  }


  @RequestMapping("/deleteCourse")
  @ResponseBody
  public Json deleteCourse(HttpServletRequest request) {
    Json json = new Json();
    long[] ids = ParamUtils.getLongParameters(request, "id", 0);
    try {
      this.courseService.deleteCourse(ids);
      json.setMsg("删除成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  @RequestMapping("/impLesson")
  public String impLesson() {
    return "jsp/study/importLesson";
  }
  
  @RequestMapping("/impTempLesson")
  public String impTempLesson() {
    return "jsp/study/importTempLesson";
  }

  @ResponseBody
  @RequestMapping("/importFromExcel")
  public Json importFromExcel(@RequestParam MultipartFile file, HttpServletRequest request) {
    Json json = new Json();
    long classId = ParamUtils.getLongParameter(request, "class_id", 0);
    String year = ParamUtils.getParameter(request, "year", "0");
    int term = ParamUtils.getIntParameter(request, "term", 0);
    try {
      readExcel(file, classId, Integer.parseInt(year), term);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    json.setSuccess(true);
    json.setMsg("成功导入");

    return json;

  }


  private void readExcel(MultipartFile file, long gradeId, int year, int term) throws Exception {

    ImportExecl poi = new ImportExecl();

    String extName = FileUtil.getExtName(file.getOriginalFilename());
    boolean flag;
    if (".xls".equals(extName)) {
      flag = true;
    } else {
      flag = false;
    }

    List<List<String>> list = poi.read(file.getInputStream(), flag);

    if (list != null && list.size() > 0) {
      String name;
      List<String> titleList = list.get(0);
      name = titleList.get(0);
      HashMap<String, Object> lesson = new HashMap<String, Object>();
      lesson.put("NAME", name);
      lesson.put("YEAR", year);
      lesson.put("TERM", term);
      lesson.put("GRADE_ID", gradeId);
      // 添加
      int lessonId = courseService.addLessonDefine(lesson);

      if (lessonId > 0) {
        HashMap<String, Object> detail = null;
        for (int i = 2; i < list.size(); i++) {
          if (i != 7) {// 第八行为上下午分割线
            List<String> infoList = list.get(i);
            if (infoList != null && infoList.size() == 7) {
              detail = new HashMap<String, Object>();
              detail.put("LESSON_ID", lessonId);
              detail.put("LESSON_NUM", infoList.get(0));
              detail.put("LESSON_TIME", infoList.get(1));
              detail.put("WEEK_ONE_LESSON", infoList.get(2));
              detail.put("WEEK_TWO_LESSON", infoList.get(3));
              detail.put("WEEK_THREE_LESSON", infoList.get(4));
              detail.put("WEEK_FOUR_LESSON", infoList.get(5));
              detail.put("WEEK_FIVE_LESSON", infoList.get(6));
              courseService.addLessonDetail(detail);
            }
          }
        }

      }
    }

  }
  
  
  @ResponseBody
  @RequestMapping("/importTempFromExcel")
  public Json importTempFromExcel(@RequestParam MultipartFile file, HttpServletRequest request) {
    Json json = new Json();
    long classId = ParamUtils.getLongParameter(request, "class_id", 0);
    String startTime = ParamUtils.getParameter(request, "startTime", "");
    String endTime = ParamUtils.getParameter(request, "endTime", "");
    try {
      readTempExcel(file, classId, startTime, endTime);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    json.setSuccess(true);
    json.setMsg("成功导入");

    return json;

  }
  
  private void readTempExcel(MultipartFile file, long gradeId,String startTime,String endTime) throws Exception {

    ImportExecl poi = new ImportExecl();

    String extName = FileUtil.getExtName(file.getOriginalFilename());
    boolean flag;
    if (".xls".equals(extName)) {
      flag = true;
    } else {
      flag = false;
    }

    List<List<String>> list = poi.read(file.getInputStream(), flag);

    if (list != null && list.size() > 0) {
      String name;
      List<String> titleList = list.get(0);
      name = titleList.get(0);
      HashMap<String, Object> lesson = new HashMap<String, Object>();
      lesson.put("NAME", name);
      lesson.put("START_DATE",DateUtil.stringsToDate(startTime));
      lesson.put("END_DATE", DateUtil.stringsToDate(endTime));
      lesson.put("GRADE_ID", gradeId);
      // 添加
      int lessonId = courseService.addLessonTempDefine(lesson);

      if (lessonId > 0) {
        HashMap<String, Object> detail = null;
        for (int i = 2; i < list.size(); i++) {
          if (i != 7) {// 第八行为上下午分割线
            List<String> infoList = list.get(i);
            if (infoList != null && infoList.size() == 7) {
              detail = new HashMap<String, Object>();
              detail.put("LESSON_ID", lessonId);
              detail.put("LESSON_NUM", infoList.get(0));
              detail.put("LESSON_TIME", infoList.get(1));
              detail.put("WEEK_ONE_LESSON", infoList.get(2));
              detail.put("WEEK_TWO_LESSON", infoList.get(3));
              detail.put("WEEK_THREE_LESSON", infoList.get(4));
              detail.put("WEEK_FOUR_LESSON", infoList.get(5));
              detail.put("WEEK_FIVE_LESSON", infoList.get(6));
              courseService.addLessonTempDetail(detail);
            }
          }
        }

      }
    }

  }
  
  @RequestMapping(value = "getLessonList")
  @ResponseBody
  public HashMap<String, Object> getLessonList(DataGridModel dm, HttpServletRequest request) {
    HashMap<String, String> params = ParamUtils.getFilterStringParams(request);
    HashMap<String, Object> resMap = courseService.getLessonList(dm, params);
    return resMap;
  }
  
  @RequestMapping(value = "getLessonTempList")
  @ResponseBody
  public HashMap<String, Object> getLessonTempList(DataGridModel dm, HttpServletRequest request) {
    HashMap<String, String> params = ParamUtils.getFilterStringParams(request);
    HashMap<String, Object> resMap = courseService.getLessonTempList(dm, params);
    return resMap;
  }
  
  @RequestMapping(value = "getLessonDetailList")
  public String getLessonDetailList(HttpServletRequest request){
    int lessonId = ParamUtils.getIntParameter(request, "id",0);
    List<HashMap<String,Object>> list = courseService.getLessonDetailList(lessonId);
    request.setAttribute("resList",list);
    return "jsp/study/lessDetail";
  }
  
  @RequestMapping(value = "getLessonTempDetailList")
  public String getLessonTempDetailList(HttpServletRequest request){
    int lessonId = ParamUtils.getIntParameter(request, "id",0);
    List<HashMap<String,Object>> list = courseService.getLessonTempDetailList(lessonId);
    request.setAttribute("resList",list);
    return "jsp/study/lessTempDetail";
  }
  
  @RequestMapping("/deleteLesson")
  @ResponseBody
  public Json deleteLesson(HttpServletRequest request) {
    Json json = new Json();
    long[] ids = ParamUtils.getLongParameters(request, "id", 0);
    try {
      this.courseService.deleteLesson(ids);
      json.setMsg("删除成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }
  
  @RequestMapping("/deleteLessonTemp")
  @ResponseBody
  public Json deleteLessonTemp(HttpServletRequest request) {
    Json json = new Json();
    long[] ids = ParamUtils.getLongParameters(request, "id", 0);
    try {
      this.courseService.deleteLessonTemp(ids);
      json.setMsg("删除成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }
    return json;
  }
  
  @RequestMapping("/getPermLessons")
  @ResponseBody
  public HashMap<String, Object> getPermLessons(HttpServletRequest request) {
    long classId = ParamUtils.getLongParameter(request, "classId", 0);
    HashMap<String, Object> resMap = new HashMap<String, Object>();
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    int code = courseService.getLessons(classId, resList);
    int tempId = courseService.getHasTempLesson(classId);
    resMap.put("code", code);
    resMap.put("info", resList);
    resMap.put("tempId",tempId);
    return resMap;
  }
  
  @RequestMapping("/getPermLessonsTemp")
  @ResponseBody
  public HashMap<String, Object> getPermLessonsTemp(HttpServletRequest request) {
    int lessonId = ParamUtils.getIntParameter(request, "lessonId", 0);
    HashMap<String, Object> resMap = new HashMap<String, Object>();
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    int code = courseService.getLessonsTemp(lessonId, resList);
    resMap.put("code", code);
    resMap.put("info", resList);
    return resMap;
  }
  
  @RequestMapping("/addLessonPlan")
  public ModelAndView addLessonPlan(HttpServletRequest request){
    ModelAndView mv = new ModelAndView("jsp/study/importLessonPlan");
    int lessonId = ParamUtils.getIntParameter(request, "lessonId",0);
    mv.addObject("lessonId",lessonId);
    return mv;
  }
  
  @RequestMapping("/addLessonTempPlan")
  public ModelAndView addLessonTempPlan(HttpServletRequest request){
    ModelAndView mv = new ModelAndView("jsp/study/importLessonTempPlan");
    int lessonId = ParamUtils.getIntParameter(request, "lessonId",0);
    mv.addObject("lessonId",lessonId);
    return mv;
  }
  
  @ResponseBody
  @RequestMapping("/importLessonPlan")
  public Json importLessonPlan(@RequestParam MultipartFile file, HttpServletRequest request) {
    Json json = new Json();
    String startTime = ParamUtils.getParameter(request, "startTime", "");
    String endTime = ParamUtils.getParameter(request, "endTime", "");
    int lessonId = ParamUtils.getIntParameter(request, "lessonId", 0);
    try {
        readLessonPlan(file, lessonId, startTime, endTime);
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg(e.getMessage());
    }

    json.setSuccess(true);
    json.setMsg("成功导入");

    return json;

  }
  
  private void readLessonPlan(MultipartFile file,int lessonId,String startTime,String endTime)throws Exception{
    ImportExecl poi = new ImportExecl();

    String extName = FileUtil.getExtName(file.getOriginalFilename());
    boolean flag;
    if (".xls".equals(extName)) {
      flag = true;
    } else {
      flag = false;
    }

    HashMap<String,HashMap<String,List<String>>> resPlanMap = new HashMap<String,HashMap<String,List<String>>>();
    List<List<String>> list = poi.read(file.getInputStream(), flag);
    if(list!=null&&list.size()>0){
      for(int i=2;i<list.size();i++){
        List<String> resList = list.get(i);
        if(resList !=null&&resList.size()>0){
          String week = getWeek(resList.get(0));
          HashMap<String,List<String>> weekPanMap = resPlanMap.get(week);
          if(weekPanMap == null){
            weekPanMap = new HashMap<String,List<String>>();
            resPlanMap.put(week, weekPanMap);
          }
          for(int j = 1;j<resList.size();j++){
            String category = list.get(1).get(j).trim();
            List<String> categoryList = weekPanMap.get(category);
            if(categoryList == null){
              categoryList = new ArrayList<String>();
              weekPanMap.put(category,categoryList);
            }
            String content = resList.get(j);
            if(StringUtils.isNotBlank(content))
              categoryList.add(content);
          }
        }
      }
    }

    List<HashMap<String,Object>> allPlans = new ArrayList<HashMap<String,Object>>();
    
    List<HashMap<String,Object>> lessonList = courseService.getLessonDetailList(lessonId);
    HashMap<String,Object> planMap= null;
    if(lessonList != null && lessonList.size() > 0){
      for(HashMap<String,Object> lesson : lessonList){
        int lessonNum = (Integer)lesson.get("LESSON_NUM");
        String oneLesson = (String)lesson.get("WEEK_ONE_LESSON");
        HashMap<String,List<String>> cateoryMap =  resPlanMap.get("WEEK_ONE_LESSON");
        if(cateoryMap != null){
          List<String> planList = cateoryMap.get(oneLesson);
          if(planList!=null&&planList.size()>0){
            planMap = new HashMap<String,Object>();
            planMap.put("LESSON_ID",lessonId);
            planMap.put("LESSON_NUM",lessonNum);
            planMap.put("LESSON_WEEK",1);
            planMap.put("LESSON_NAME",oneLesson);
            planMap.put("LESSON_CONTENT",planList.get(0));
            planMap.put("START_DATE",DateUtil.stringsToDate(startTime));
            planMap.put("END_DATE",DateUtil.stringsToDate(endTime));
            planList.remove(0);
            allPlans.add(planMap);
          }
        }
        String twoLesson = (String)lesson.get("WEEK_TWO_LESSON");
        HashMap<String,List<String>> cateory2Map =  resPlanMap.get("WEEK_TWO_LESSON");
        if(cateory2Map != null){
          List<String> planList = cateory2Map.get(twoLesson);
          if(planList!=null&&planList.size()>0){
            planMap = new HashMap<String,Object>();
            planMap.put("LESSON_ID",lessonId);
            planMap.put("LESSON_NUM",lessonNum);
            planMap.put("LESSON_WEEK",2);
            planMap.put("LESSON_NAME",twoLesson);
            planMap.put("LESSON_CONTENT",planList.get(0));
            planMap.put("START_DATE",DateUtil.stringsToDate(startTime));
            planMap.put("END_DATE",DateUtil.stringsToDate(endTime));
            planList.remove(0);
            allPlans.add(planMap);
          }
        }
        String threeLesson = (String)lesson.get("WEEK_THREE_LESSON");
        HashMap<String,List<String>> cateory3Map =  resPlanMap.get("WEEK_THREE_LESSON");
        if(cateory3Map != null){
          List<String> planList = cateory3Map.get(threeLesson);
          if(planList!=null&&planList.size()>0){
            planMap = new HashMap<String,Object>();
            planMap.put("LESSON_ID",lessonId);
            planMap.put("LESSON_NUM",lessonNum);
            planMap.put("LESSON_WEEK",3);
            planMap.put("LESSON_NAME",threeLesson);
            planMap.put("LESSON_CONTENT",planList.get(0));
            planMap.put("START_DATE",DateUtil.stringsToDate(startTime));
            planMap.put("END_DATE",DateUtil.stringsToDate(endTime));
            planList.remove(0);
            allPlans.add(planMap);
          }
        }
        String fourLesson = (String)lesson.get("WEEK_FOUR_LESSON");
        HashMap<String,List<String>> cateory4Map =  resPlanMap.get("WEEK_FOUR_LESSON");
        if(cateory4Map != null){
          List<String> planList = cateory4Map.get(fourLesson);
          if(planList!=null&&planList.size()>0){
            planMap = new HashMap<String,Object>();
            planMap.put("LESSON_ID",lessonId);
            planMap.put("LESSON_NUM",lessonNum);
            planMap.put("LESSON_WEEK",4);
            planMap.put("LESSON_NAME",fourLesson);
            planMap.put("LESSON_CONTENT",planList.get(0));
            planMap.put("START_DATE",DateUtil.stringsToDate(startTime));
            planMap.put("END_DATE",DateUtil.stringsToDate(endTime));
            planList.remove(0);
            allPlans.add(planMap);
          }
        }
        String fiveLesson = (String)lesson.get("WEEK_FIVE_LESSON");
        HashMap<String,List<String>> cateory5Map =  resPlanMap.get("WEEK_FIVE_LESSON");
        if(cateory5Map != null){
          List<String> planList = cateory5Map.get(fiveLesson);
          if(planList!=null&&planList.size()>0){
            planMap = new HashMap<String,Object>();
            planMap.put("LESSON_ID",lessonId);
            planMap.put("LESSON_NUM",lessonNum);
            planMap.put("LESSON_WEEK",5);
            planMap.put("LESSON_NAME",fiveLesson);
            planMap.put("LESSON_CONTENT",planList.get(0));
            planMap.put("START_DATE",DateUtil.stringsToDate(startTime));
            planMap.put("END_DATE",DateUtil.stringsToDate(endTime));
            planList.remove(0);
            allPlans.add(planMap);
          }
        }
        String sixLesson = (String)lesson.get("WEEK_SIX_LESSON");
        HashMap<String,List<String>> cateory6Map =  resPlanMap.get("WEEK_SIX_LESSON");
        if(cateory6Map != null){
          List<String> planList = cateory6Map.get(fiveLesson);
          if(planList!=null&&planList.size()>0){
            planMap = new HashMap<String,Object>();
            planMap.put("LESSON_ID",lessonId);
            planMap.put("LESSON_NUM",lessonNum);
            planMap.put("LESSON_WEEK",6);
            planMap.put("LESSON_NAME",sixLesson);
            planMap.put("LESSON_CONTENT",planList.get(0));
            planMap.put("START_DATE",DateUtil.stringsToDate(startTime));
            planMap.put("END_DATE",DateUtil.stringsToDate(endTime));
            planList.remove(0);
            allPlans.add(planMap);
          }
        }
        String sevenLesson = (String)lesson.get("WEEK_SEVEN_LESSON");
        HashMap<String,List<String>> cateory7Map =  resPlanMap.get("WEEK_SEVEN_LESSON");
        if(cateory7Map != null){
          List<String> planList = cateory7Map.get(fiveLesson);
          if(planList!=null&&planList.size()>0){
            planMap = new HashMap<String,Object>();
            planMap.put("LESSON_ID",lessonId);
            planMap.put("LESSON_NUM",lessonNum);
            planMap.put("LESSON_WEEK",7);
            planMap.put("LESSON_NAME",sevenLesson);
            planMap.put("LESSON_CONTENT",planList.get(0));
            planMap.put("START_DATE",DateUtil.stringsToDate(startTime));
            planMap.put("END_DATE",DateUtil.stringsToDate(endTime));
            planList.remove(0);
            allPlans.add(planMap);
          }
        }
      }
    }
    
    courseService.insertLessonPlan(allPlans);
  }
  
  @ResponseBody
  @RequestMapping("/importLessonTempPlan")
  public Json importLessonTempPlan(@RequestParam MultipartFile file, HttpServletRequest request) {
    Json json = new Json();
    String startTime = ParamUtils.getParameter(request, "startTime", "");
    String endTime = ParamUtils.getParameter(request, "endTime", "");
    int lessonId = ParamUtils.getIntParameter(request, "lessonId", 0);
    try {
        readLessonTempPlan(file, lessonId, startTime, endTime);
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg(e.getMessage());
    }
    json.setSuccess(true);
    json.setMsg("成功导入");

    return json;
  }
    
    private void readLessonTempPlan(MultipartFile file,int lessonId,String startTime,String endTime)throws Exception{
      ImportExecl poi = new ImportExecl();

      String extName = FileUtil.getExtName(file.getOriginalFilename());
      boolean flag;
      if (".xls".equals(extName)) {
        flag = true;
      } else {
        flag = false;
      }

      HashMap<String,HashMap<String,List<String>>> resPlanMap = new HashMap<String,HashMap<String,List<String>>>();
      List<List<String>> list = poi.read(file.getInputStream(), flag);
      if(list!=null&&list.size()>0){
        for(int i=2;i<list.size();i++){
          List<String> resList = list.get(i);
          if(resList !=null&&resList.size()>0){
            String week = getWeek(resList.get(0));
            HashMap<String,List<String>> weekPanMap = resPlanMap.get(week);
            if(weekPanMap == null){
              weekPanMap = new HashMap<String,List<String>>();
              resPlanMap.put(week, weekPanMap);
            }
            for(int j = 1;j<resList.size();j++){
              String category = list.get(1).get(j).trim();
              List<String> categoryList = weekPanMap.get(category);
              if(categoryList == null){
                categoryList = new ArrayList<String>();
                weekPanMap.put(category,categoryList);
              }
              String content = resList.get(j);
              if(StringUtils.isNotBlank(content))
                categoryList.add(content);
            }
          }
        }
      }

      List<HashMap<String,Object>> allPlans = new ArrayList<HashMap<String,Object>>();
      
      List<HashMap<String,Object>> lessonList = courseService.getLessonTempDetailList(lessonId);
      HashMap<String,Object> planMap= null;
      if(lessonList != null && lessonList.size() > 0){
        for(HashMap<String,Object> lesson : lessonList){
          int lessonNum = (Integer)lesson.get("LESSON_NUM");
          String oneLesson = (String)lesson.get("WEEK_ONE_LESSON");
          HashMap<String,List<String>> cateoryMap =  resPlanMap.get("WEEK_ONE_LESSON");
          if(cateoryMap != null){
            List<String> planList = cateoryMap.get(oneLesson);
            if(planList!=null&&planList.size()>0){
              planMap = new HashMap<String,Object>();
              planMap.put("LESSON_ID",lessonId);
              planMap.put("LESSON_NUM",lessonNum);
              planMap.put("LESSON_WEEK",1);
              planMap.put("LESSON_NAME",oneLesson);
              planMap.put("LESSON_CONTENT",planList.get(0));
              planMap.put("START_DATE",DateUtil.stringsToDate(startTime));
              planMap.put("END_DATE",DateUtil.stringsToDate(endTime));
              planList.remove(0);
              allPlans.add(planMap);
            }
          }
          String twoLesson = (String)lesson.get("WEEK_TWO_LESSON");
          HashMap<String,List<String>> cateory2Map =  resPlanMap.get("WEEK_TWO_LESSON");
          if(cateory2Map != null){
            List<String> planList = cateory2Map.get(twoLesson);
            if(planList!=null&&planList.size()>0){
              planMap = new HashMap<String,Object>();
              planMap.put("LESSON_ID",lessonId);
              planMap.put("LESSON_NUM",lessonNum);
              planMap.put("LESSON_WEEK",2);
              planMap.put("LESSON_NAME",twoLesson);
              planMap.put("LESSON_CONTENT",planList.get(0));
              planMap.put("START_DATE",DateUtil.stringsToDate(startTime));
              planMap.put("END_DATE",DateUtil.stringsToDate(endTime));
              planList.remove(0);
              allPlans.add(planMap);
            }
          }
          String threeLesson = (String)lesson.get("WEEK_THREE_LESSON");
          HashMap<String,List<String>> cateory3Map =  resPlanMap.get("WEEK_THREE_LESSON");
          if(cateory3Map != null){
            List<String> planList = cateory3Map.get(threeLesson);
            if(planList!=null&&planList.size()>0){
              planMap = new HashMap<String,Object>();
              planMap.put("LESSON_ID",lessonId);
              planMap.put("LESSON_NUM",lessonNum);
              planMap.put("LESSON_WEEK",3);
              planMap.put("LESSON_NAME",threeLesson);
              planMap.put("LESSON_CONTENT",planList.get(0));
              planMap.put("START_DATE",DateUtil.stringsToDate(startTime));
              planMap.put("END_DATE",DateUtil.stringsToDate(endTime));
              planList.remove(0);
              allPlans.add(planMap);
            }
          }
          String fourLesson = (String)lesson.get("WEEK_FOUR_LESSON");
          HashMap<String,List<String>> cateory4Map =  resPlanMap.get("WEEK_FOUR_LESSON");
          if(cateory4Map != null){
            List<String> planList = cateory4Map.get(fourLesson);
            if(planList!=null&&planList.size()>0){
              planMap = new HashMap<String,Object>();
              planMap.put("LESSON_ID",lessonId);
              planMap.put("LESSON_NUM",lessonNum);
              planMap.put("LESSON_WEEK",4);
              planMap.put("LESSON_NAME",fourLesson);
              planMap.put("LESSON_CONTENT",planList.get(0));
              planMap.put("START_DATE",DateUtil.stringsToDate(startTime));
              planMap.put("END_DATE",DateUtil.stringsToDate(endTime));
              planList.remove(0);
              allPlans.add(planMap);
            }
          }
          String fiveLesson = (String)lesson.get("WEEK_FIVE_LESSON");
          HashMap<String,List<String>> cateory5Map =  resPlanMap.get("WEEK_FIVE_LESSON");
          if(cateory5Map != null){
            List<String> planList = cateory5Map.get(fiveLesson);
            if(planList!=null&&planList.size()>0){
              planMap = new HashMap<String,Object>();
              planMap.put("LESSON_ID",lessonId);
              planMap.put("LESSON_NUM",lessonNum);
              planMap.put("LESSON_WEEK",5);
              planMap.put("LESSON_NAME",fiveLesson);
              planMap.put("LESSON_CONTENT",planList.get(0));
              planMap.put("START_DATE",DateUtil.stringsToDate(startTime));
              planMap.put("END_DATE",DateUtil.stringsToDate(endTime));
              planList.remove(0);
              allPlans.add(planMap);
            }
          }
          String sixLesson = (String)lesson.get("WEEK_SIX_LESSON");
          HashMap<String,List<String>> cateory6Map =  resPlanMap.get("WEEK_SIX_LESSON");
          if(cateory6Map != null){
            List<String> planList = cateory6Map.get(fiveLesson);
            if(planList!=null&&planList.size()>0){
              planMap = new HashMap<String,Object>();
              planMap.put("LESSON_ID",lessonId);
              planMap.put("LESSON_NUM",lessonNum);
              planMap.put("LESSON_WEEK",6);
              planMap.put("LESSON_NAME",sixLesson);
              planMap.put("LESSON_CONTENT",planList.get(0));
              planMap.put("START_DATE",DateUtil.stringsToDate(startTime));
              planMap.put("END_DATE",DateUtil.stringsToDate(endTime));
              planList.remove(0);
              allPlans.add(planMap);
            }
          }
          String sevenLesson = (String)lesson.get("WEEK_SEVEN_LESSON");
          HashMap<String,List<String>> cateory7Map =  resPlanMap.get("WEEK_SEVEN_LESSON");
          if(cateory7Map != null){
            List<String> planList = cateory7Map.get(fiveLesson);
            if(planList!=null&&planList.size()>0){
              planMap = new HashMap<String,Object>();
              planMap.put("LESSON_ID",lessonId);
              planMap.put("LESSON_NUM",lessonNum);
              planMap.put("LESSON_WEEK",7);
              planMap.put("LESSON_NAME",sevenLesson);
              planMap.put("LESSON_CONTENT",planList.get(0));
              planMap.put("START_DATE",DateUtil.stringsToDate(startTime));
              planMap.put("END_DATE",DateUtil.stringsToDate(endTime));
              planList.remove(0);
              allPlans.add(planMap);
            }
          }
        }
      }
      
      courseService.insertLessonTempPlan(allPlans);
    }
  

  private String getWeek(String date){
    String res = "";
    switch (date) {
      case "一":
        res = "WEEK_ONE_LESSON";
        break;
      case "二":
        res = "WEEK_TWO_LESSON";
        break;
      case "三":
        res = "WEEK_THREE_LESSON";
        break;
      case "四":
        res = "WEEK_FOUR_LESSON";
        break;
      case "五":
        res = "WEEK_FIVE_LESSON";
        break;
      case "六":
        res = "WEEK_SIX_LESSON";
        break;
      case "七":
        res = "WEEK_SEVEN_LESSON";
        break;
      default:
        break;
    }
    return res;
  }
  
  @RequestMapping("/deleteLessonPlans")
  @ResponseBody
  public Json deleteLessonPlans(HttpServletRequest request) {
    Json json = new Json();
    String  ids = ParamUtils.getParameter(request, "ids","");
    try {
      String[] idArr = ids.split(",");
      if(idArr != null){
        for(String id : idArr){
          if(StringUtils.isNotBlank(id)){
            courseService.deleteLessonPlans(Integer.parseInt(id));
          }
        }
      }
      json.setMsg("删除成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }
    return json;
  }
  
  
  @RequestMapping("/deleteLessonTempPlans")
  @ResponseBody
  public Json deleteLessonTempPlans(HttpServletRequest request) {
    Json json = new Json();
    String  ids = ParamUtils.getParameter(request, "ids","");
    try {
      String[] idArr = ids.split(",");
      if(idArr != null){
        for(String id : idArr){
          if(StringUtils.isNotBlank(id)){
            courseService.deleteLessonTempPlans(Integer.parseInt(id));
          }
        }
      }
      json.setMsg("删除成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }
    return json;
  }
  
  
  @RequestMapping("/manageCategoryplan")
  public String manageCategoryplan() {
    return "jsp/study/manageCategoryPlan";
  }
  
  
  @RequestMapping(value = "getCategoryPlanList")
  @ResponseBody
  public HashMap<String, Object> getCategoryPlanList(DataGridModel dm, HttpServletRequest request) {
    HashMap<String, String> params = ParamUtils.getFilterStringParams(request);
    HashMap<String, Object> resMap = courseService.getCategoryPlanList(dm, params);
    return resMap;
  }
  
  
  @RequestMapping("/addCategoryPlan")
  public String addCategoryPlan(HttpServletRequest request) {
    return "jsp/study/addCategoryPlan";
  }
  
  @RequestMapping("manageLessonMsg")
  public String managerLessonMsg(){
      return "jsp/study/manageLessonMsg";
  }
  
  @RequestMapping(value = "getLessonMsgList")
  @ResponseBody
  public HashMap<String, Object> getLessonMsgList(DataGridModel dm, HttpServletRequest request){
      HashMap<String, String> params = ParamUtils.getFilterStringParams(request);
      HashMap<String, Object> resMap = courseService.getLessonMsgList(dm, params);
      return resMap;
  }
  
  @RequestMapping("addLessonMsg")
  public String addLessonMsg(){
      return "jsp/study/addLessonMsg";
  }
  
  @ResponseBody
  @RequestMapping("/saveLessonMsg")
  public Json saveLessonMsg(HttpServletRequest request) {
    Json json = new Json();
    try {
            HashMap<String,String> msg = ParamUtils.getParameters(request);
        courseService.saveLessonMsg(msg);
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg(e.getMessage());
    }
    json.setSuccess(true);
    json.setMsg("成功导入");

    return json;
  }
  
  @RequestMapping("/editLessonMsg")
  public ModelAndView editLessonMsg(int id){
      ModelAndView mv = new ModelAndView("jsp/study/editLessonMsg");
      HashMap<String,Object> msg = courseService.getLessonMsg(id);
      mv.addObject("msg",msg);
      return mv;
  }
  
  
  @ResponseBody
  @RequestMapping("/updateLessonMsg")
  public Json updateLessonMsg(HttpServletRequest request) {
    Json json = new Json();
    try {
            HashMap<String,String> msg = ParamUtils.getParameters(request);
        courseService.updateLessonMsg(msg);
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg(e.getMessage());
    }
    json.setSuccess(true);
    json.setMsg("成功导入");

    return json;
  }
  
  @ResponseBody
  @RequestMapping("/deleteLessonMsg")
  public Json deleteLessonMsg(HttpServletRequest request) {
    Json json = new Json();
    try {
            String ids = ParamUtils.getParameter(request, "ids","");
            courseService.deleteLessonMsg(ids);
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg(e.getMessage());
    }
    json.setSuccess(true);
    json.setMsg("成功导入");

    return json;
  }
  
  
  @RequestMapping("/createCategoryPlan")
  @ResponseBody
  public Json createCategoryPlan(HttpServletRequest request) {
    Json json = new Json();

    try {
      HashMap<String,String> plan = ParamUtils.getFilterStringParams(request);
      courseService.saveCategoryPlan(plan);
      json.setSuccess(true);
      json.setMsg("添加成功！");
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }
  
  
  @RequestMapping("/editCategoryPlan")
  public ModelAndView editCategoryPlan(HttpServletRequest request){
      ModelAndView mv = new ModelAndView("jsp/study/editCategoryPlan");
      int id = ParamUtils.getIntParameter(request,"id", 0);
      HashMap<String,Object> plan = courseService.getCategoryPlan(id);
      mv.addObject("plan",plan);
      return mv;
  }
  
  
  @RequestMapping("/updateCategoryPlan")
  @ResponseBody
  public Json updateCategoryPlan(HttpServletRequest request) {
    Json json = new Json();
    try {
      HashMap<String,String> plan = ParamUtils.getFilterStringParams(request);
      courseService.updateCategoryPlan(plan);
      json.setSuccess(true);
      json.setMsg("添加成功！");
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }
  
  
  @ResponseBody
  @RequestMapping("/deleteCategoryPlan")
  public Json deleteCategoryPlan(HttpServletRequest request) {
    Json json = new Json();
    try {
          int id = ParamUtils.getIntParameter(request, "id",0);
          courseService.deleteCategoryPlan(id);
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg(e.getMessage());
    }
    json.setSuccess(true);
    json.setMsg("成功导入");

    return json;
  }
  
  
  @RequestMapping("/editProfileCategoryPlan")
  public String editProfileCategoryPlan(HttpServletRequest request) {

    
    try {
      int id = ParamUtils.getIntParameter(request, "id",0);
      HashMap<String, Object> plan = courseService.getCategoryPlan(id);
      String firstTime = DateUtil.dateToString(new Date(), false);
      request.setAttribute("firstTime", firstTime);
      request.setAttribute("plan", plan);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "jsp/study/editProfileCategoryPlan";
  }
  
  @RequestMapping("/sendCategoryPlans")
  @ResponseBody
  public Json sendCategoryPlans(HttpServletRequest request) {
    //CoursePlan coursePlan = null;
    HashMap<String, Object> planMap = null;
    int id = ParamUtils.getIntParameter(request, "id",0);
    try {
      planMap = courseService.getCategoryPlan(id);
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    String name = (String)planMap.get("NAME");
    String imageUrl = (String)planMap.get("PLAN_URL");
    Date startDate = (Date)planMap.get("START_DATE");
    Date endDate = (Date)planMap.get("END_DATE");
    int categoryId = (Integer)planMap.get("CATEGORY_ID");
    String categoryName = (String)planMap.get("CATEGORY_NAME");
    long gradeId = (Long)planMap.get("GRADE_ID");
    String insertContent =
        " insert into term_plan (NAME, PLAN_URL,START_DATE,END_DATE,CATEGORY_ID,CATEGORY_NAME,GRADE_ID)" + " values ('" + name + "', "
            + "'" + imageUrl + "'";
    if(startDate != null){
      insertContent += ",'"+startDate+"'";
    }else{
      insertContent += ",null";
    }
    if(endDate != null){
      insertContent += ",'"+endDate+"'";
    }else{
      insertContent += ",null";
    }
    insertContent += ","+categoryId+",'"+categoryName+"'"+","+gradeId;
    insertContent += "); commit; ";

    String uploadDir =
        request.getSession().getServletContext().getRealPath(imageUrl);
    String uploadDirName = uploadDir.substring(uploadDir.lastIndexOf("\\") + 1, uploadDir.length());
    System.out.println("uploadDir==" + uploadDir);
    System.out.println("uploadDirName==" + uploadDirName);

    String targetDir = "categoryPlan" + id;
    String fileSqlName = "categoryPlan_" + id + ".sql";
    Json json = new Json();
    String fileName = SystemUtil.createUUID();
    String destDirName = "d:/push_profile";
    String targetDirs = destDirName + "/" + targetDir;
    String picDir = targetDirs + "/pic";
    String filesDir = targetDirs + "/file";
    String path = destDirName + "/" + fileName + ".ini";
    // FileOperation.createDir(picDir);
    FileOperation.createDir(filesDir);
    FileOperation.createDir(targetDirs);
    FileOperation.createDir(destDirName);
    String insertPath = destDirName + "/" + targetDir + "/" + fileSqlName;

    String firstTime = request.getParameter("beginTime");
    String endTime = request.getParameter("endTime");

    try {
      FileOperation.copyFile(new File(uploadDir), new File(filesDir + "/" + uploadDirName));
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    try {
      FileOperation.write(insertContent, insertPath, "UTF-8");

      String content =
          "[option] \r\n" + "Dir=" + targetDir + " \r\n" + "channelID=" + VersionInfo.CHANNELID
              + " \r\n" + "priority=" + VersionInfo.PRIORITY + " \r\n" + "bandwidth="
              + VersionInfo.BANDWIDTH + " \r\n" + "PackFile=" + VersionInfo.PACKFILE + " \r\n"
              + "sendMode=" + VersionInfo.SENDMODE + " \r\n" + "sendTime=" + VersionInfo.SENDTIME
              + " \r\n" + "repeatcount=" + VersionInfo.REPEATCOUNT + " \r\n" + "validRate="
              + VersionInfo.VALIDRATE + " \r\n" + "startValidDate=" + firstTime + " \r\n"
              + "endValidDate=" + endTime + " \r\n" + "Completed=0 \r\n";
      FileOperation.contentToTxt(path, content);
      courseService.setCategoryPlanStatus(id, 1);
      json.setSuccess(true);
      json.setMsg("下发成功!");
    } catch (Exception e) {
      json.setMsg("下发失败！");
    }

    return json;
  }
  
}





