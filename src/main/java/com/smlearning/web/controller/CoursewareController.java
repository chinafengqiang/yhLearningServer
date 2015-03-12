package com.smlearning.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.com.iactive.db.DataGridModel;
import cn.com.iactive.db.PagerModel;

import com.google.gson.Gson;
import com.smlearning.application.service.CoursewareCategoryService;
import com.smlearning.application.service.CoursewareService;
import com.smlearning.application.service.SysKeyService;
import com.smlearning.domain.entity.Courseware;
import com.smlearning.domain.entity.CoursewareCategory;
import com.smlearning.domain.entity.Manager;
import com.smlearning.domain.entity.SysClass;
import com.smlearning.domain.entity.userCourseNote;
import com.smlearning.domain.entity.enums.CourseUseTypeEnum;
import com.smlearning.domain.vo.CoursewareNode;
import com.smlearning.domain.vo.CoursewareVO;
import com.smlearning.domain.vo.Tree;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.DateUtil;
import com.smlearning.infrastructure.utils.FileOperation;
import com.smlearning.infrastructure.utils.Json;
import com.smlearning.infrastructure.utils.PageHelper;
import com.smlearning.infrastructure.utils.ParamUtils;
import com.smlearning.infrastructure.utils.SystemUtil;
import com.smlearning.infrastructure.utils.VersionInfo;

/**
 * 课件控制层处理
 */
@Controller
@RequestMapping("/coursewareController")
public class CoursewareController extends BaseController {

  static Logger logger = Logger.getLogger(CoursewareController.class.getName());

  @Autowired
  private CoursewareService coursewareService;

  @Autowired
  private CoursewareCategoryService coursewareCategoryService;

  @Autowired
  private SysKeyService sysKeyService;

  /**
   * 跳转到课件管理页面
   * 
   * @return
   */
  @RequestMapping("/manageEbooks")
  public String manageEbooks() {
    return "jsp/study/manageEbook";
  }

  /**
   * 跳转到课件管理页面
   * 
   * @return
   */
  @RequestMapping("/manageCourseware")
  public String manageCourseware() {
    return "jsp/study/manageCourseware";
  }

  /**
   * 获取电子书数据表格
   * 
   * @param coursewareVO
   * @return
   */
  @RequestMapping("/dataGrid")
  @ResponseBody
  public DataGrid dataGrid(CoursewareVO coursewareVO, PageHelper ph) {
    return coursewareService.queryBookPaning(coursewareVO, ph);
  }


  @RequestMapping(value = "getCoursewareList")
  @ResponseBody
  public HashMap<String, Object> getCoursewareList(DataGridModel dm, HttpServletRequest request) {
    HashMap<String, String> params = ParamUtils.getFilterStringParams(request);
    HashMap<String, Object> resMap = coursewareService.getCoursewareList(dm, params);
    return resMap;
  }

  /**
   * 获取课件数据表格
   * 
   * @param coursewareVO
   * @return
   */
  @RequestMapping("/dataGridCourseware")
  @ResponseBody
  public DataGrid dataGridCourseware(CoursewareVO coursewareVO, PageHelper ph) {
    return coursewareService.queryCoursewarePaning(coursewareVO, ph);
  }


  /**
   * 跳转到添加课件页面
   * 
   * @param request
   * @return
   */
  @RequestMapping("/addPage")
  public String addPage(HttpServletRequest request) {
    request.setAttribute("classList", sysKeyService.queryAllByClass());
    return "jsp/addCourseware";
  }

  /**
   * 跳转到添加课件页面
   * 
   * @param request
   * @return
   */
  @RequestMapping("/addEbooks")
  public String addEbooks(HttpServletRequest request) {
    // request.setAttribute("classList", sysKeyService.queryAllByClass());
    return "jsp/study/addEbooks";
  }

  /**
   * 跳转到消息页面
   * 
   * @return
   */
  @RequestMapping("/editEbook")
  public String editEbook(HttpServletRequest request, Long id) {

    Courseware courseware;
    try {
      logger.info("id==" + id);
      courseware = this.coursewareService.getCoursewareById(id);
      if (courseware != null) {
        logger
            .info("courseware.getCoursewareCategoryId()==" + courseware.getCoursewareCategoryId());
        CoursewareCategory category =
            coursewareCategoryService.getCoursewareCategoryByID(courseware
                .getCoursewareCategoryId());
        request.setAttribute("categoryName", category.getName());
      }
      // request.setAttribute("classList", sysKeyService.queryAllByClass());
      request.setAttribute("courseware", courseware);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "jsp/study/editEbooks";
  }


  /**
   * 跳转到消息页面
   * 
   * @return
   */
  @RequestMapping("/editCourseware")
  public String editCourseware(HttpServletRequest request, Long id) {

    Courseware courseware;
    try {
      logger.info("id==" + id);
      courseware = this.coursewareService.getCoursewareById(id);
      if (courseware != null) {
        logger
            .info("courseware.getCoursewareCategoryId()==" + courseware.getCoursewareCategoryId());
        CoursewareCategory category =
            coursewareCategoryService.getCoursewareCategoryByID(courseware
                .getCoursewareCategoryId());
        request.setAttribute("categoryName", category.getName());
      }

      request.setAttribute("courseware", courseware);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "jsp/study/editCourseware";
  }

  /**
   * 添加课件
   * 
   * @param coursewareVO
   * @return
   */
  @RequestMapping("/createcourseware")
  @ResponseBody
  public Json createCourseware(CoursewareVO coursewareVO, HttpSession session) {
    Json json = new Json();
    Manager manager = (Manager) session.getAttribute("manager");

    try {
      Courseware c =
          this.coursewareService.createCourseware(coursewareVO.getName(), coursewareVO.getPid(),
              coursewareVO.getUrl(), manager.getId(), coursewareVO.getPic(),
              coursewareVO.getClassId(), coursewareVO.getGradeId(), coursewareVO.getIsPublic());

      String uploadDir = session.getServletContext().getRealPath("/log_sql");
      String fileName = "book_" + c.getId() + ".txt";
      String content =
          " insert into courseware (name, courseware_category_id, url, created_time, creator, pic)"
              + " values (" + c.getName() + ", " + c.getCoursewareCategoryId() + ", " + ""
              + c.getUrl() + ", " + c.getCreatedTime() + "," + c.getCreator() + ", " + c.getPic()
              + ") ";

      FileOperation.contentToTxt(uploadDir + "/" + fileName, content);

      json.setSuccess(true);
      json.setMsg("添加成功！");
      json.setObj(coursewareVO);

    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;

  }

  /**
   * 修改课件
   * 
   * @param coursewareVO
   * @return
   */
  @RequestMapping("/modifycourseware")
  @ResponseBody
  public Json modifyCourseware(CoursewareVO coursewareVO) {
    Json json = new Json();
    logger.info("coursewareVO==" + coursewareVO.getPid());
    try {
      this.coursewareService.modifyCourseware(coursewareVO.getId(), coursewareVO.getPid(),
          coursewareVO.getName(), coursewareVO.getUrl(), coursewareVO.getPic(),
          coursewareVO.getClassId(), coursewareVO.getGradeId(), coursewareVO.getIsPublic());
      json.setSuccess(true);
      json.setMsg("修改成功！");
      json.setObj(coursewareVO);

    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;

  }


  /**
   * 删除课件
   * 
   * @param id
   * @param session
   * @return
   */
  @RequestMapping("/removecourseware")
  @ResponseBody
  public Json removeCourseware(Long id, HttpSession session) {
    Json json = new Json();

    try {
      this.coursewareService.removeCourseware(id);
      json.setMsg("删除成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  @RequestMapping("/deletecourseware")
  @ResponseBody
  public Json deletecourseware(HttpServletRequest request) {
    Json json = new Json();
    long[] ids = ParamUtils.getLongParameters(request, "id", 0);
    try {
      this.coursewareService.deleteCourseware(ids);
      json.setMsg("删除成功！");
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  /**
   * 添加课件分类
   * 
   * @param coursewareCategory
   * @return
   */
  @RequestMapping("/createcoursewarecategory")
  @ResponseBody
  public Json createCoursewareCategory(CoursewareCategory coursewareCategory,
      HttpServletRequest request, HttpSession session) {
    Json json = new Json();
    Manager manager = (Manager) session.getAttribute("manager");

    String ids = request.getParameter("id");
    Long id = null;
    if (ids == "" || ids.equals("")) {
      id = 0l;
    }

    try {
      this.coursewareCategoryService
          .createCoursewareCategory(coursewareCategory.getName(), id,
              coursewareCategory.getSortFlag(), CourseUseTypeEnum.Courseware.toValue(),
              manager.getId());
      json.setSuccess(true);
      json.setMsg("添加成功！");
      json.setObj(coursewareCategory);

    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;

  }


  /**
   * 修改课件分类
   * 
   * @param coursewareCategory
   * @return
   */
  @RequestMapping("/modifycoursewarecategory")
  @ResponseBody
  public Json modifyCoursewareCategory(CoursewareCategory coursewareCategory, HttpSession session) {
    Json json = new Json();
    try {
      this.coursewareCategoryService.modifyCoursewareCategory(coursewareCategory.getId(),
          coursewareCategory.getName(), coursewareCategory.getSortFlag());
      json.setSuccess(true);
      json.setMsg("修改成功！");
      json.setObj(coursewareCategory);

    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;

  }

  /**
   * 删除课件分类
   * 
   * @param id
   * @param session
   * @return
   */
  @RequestMapping("/removecoursewarecategory")
  @ResponseBody
  public Json removeCoursewareCategory(Long id, HttpSession session) {
    Json json = new Json();

    try {
      this.coursewareCategoryService.removeCoursewareCategory(id);
      json.setMsg("删除成功！");
      json.setSuccess(true);
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
  @RequestMapping("/createCoursewareList")
  public String createCoursewareList(HttpServletRequest request, Long id) {

    ArrayList<CoursewareNode> coursewareList =
        this.coursewareCategoryService.createCoursewareTree(CourseUseTypeEnum.Courseware.toValue());
    logger.info("coursewareList.size" + coursewareList.size());
    request.setAttribute("nodeList", coursewareList);
    return "jsp/study/manageCoursewareTree";
  }

  /**
   * 生成村节点
   * 
   * @return
   */
  @RequestMapping("/createCoursewareNodeList")
  public String createCoursewareNodeList(HttpServletRequest request, Long id) {

    ArrayList<CoursewareNode> coursewareList =
        this.coursewareCategoryService.createCoursewareNodeList(CourseUseTypeEnum.Courseware
            .toValue());
    logger.info("coursewareList.size" + coursewareList.size());
    request.setAttribute("nodeList", coursewareList);
    return "jsp/study/manageCoursewareTree";
  }

  /**
   * 跳转到添加课件分类页面
   * 
   * @param request
   * @return
   */
  @RequestMapping("/addCategory")
  public String addCategory(HttpServletRequest request) {
    return "jsp/study/addCoursewareCategory";
  }


  /**
   * 跳转到添加课件分类页面
   * 
   * @param request
   * @return
   */
  @RequestMapping("/addBook")
  public String addBook(HttpServletRequest request) {
    return "jsp/study/addBooksCategory";
  }

  /**
   * 跳转到添加课件分类页面
   * 
   * @param request
   * @return
   */
  @RequestMapping("/addCourseware")
  public String addCourseware(HttpServletRequest request) {
    return "jsp/study/addCourseware";
  }

  /**
   * 跳转到消息页面
   * 
   * @return
   */
  @RequestMapping("/editCategory")
  public String editCategory(HttpServletRequest request, Long id) {

    CoursewareCategory coursewareCategory;
    try {
      coursewareCategory = this.coursewareCategoryService.getCoursewareCategoryByID(id);
      request.setAttribute("coursewareCategory", coursewareCategory);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "jsp/study/editCoursewareCategory";
  }

  /**
   * 生成村节点
   * 
   * @return
   */
  @RequestMapping("/createBookNodeList")
  public String createBookNodeList(HttpServletRequest request, Long id) {

    ArrayList<CoursewareNode> coursewareList =
        this.coursewareCategoryService.createCoursewareNodeList(CourseUseTypeEnum.Ebooks.toValue());
    logger.info("coursewareList.size" + coursewareList.size());
    request.setAttribute("nodeList", coursewareList);
    return "jsp/study/manageEbookTree";
  }


  /**
   * 添加课件分类
   * 
   * @param coursewareCategory
   * @return
   */
  @RequestMapping("/createBookCategory")
  @ResponseBody
  public Json createBookCategory(CoursewareCategory coursewareCategory, HttpServletRequest request,
      HttpSession session) {
    Json json = new Json();
    Manager manager = (Manager) session.getAttribute("manager");

    String ids = request.getParameter("id");
    Long id = null;
    if (ids == "" || ids.equals("")) {
      id = 0l;
    }

    logger.info("CourseUseTypeEnum.Ebooks.toValue() == " + CourseUseTypeEnum.Ebooks.toValue());
    try {
      this.coursewareCategoryService.createCoursewareCategory(coursewareCategory.getName(), id,
          coursewareCategory.getSortFlag(), CourseUseTypeEnum.Ebooks.toValue(), manager.getId());
      json.setSuccess(true);
      json.setMsg("添加成功！");
      json.setObj(coursewareCategory);

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
  @RequestMapping("/allTree")
  @ResponseBody
  public List<Tree> allTree() {
    return coursewareCategoryService.createTree(CourseUseTypeEnum.Ebooks.toValue());
  }

  /**
   * 生成村节点
   * 
   * @return
   */
  @RequestMapping("/allTreeByCourseware")
  @ResponseBody
  public List<Tree> allTreeByCourseware() {
    logger
        .info("CourseUseTypeEnum.Courseware.toValue()==" + CourseUseTypeEnum.Courseware.toValue());
    return coursewareCategoryService.createTree(CourseUseTypeEnum.Courseware.toValue());
  }

  /**
   * 上传文件
   * 
   * @return
   */
  @RequestMapping("/uploadFoword")
  public String uploadFoword() {
    return "jsp/study/uploadResourceByuse";
  }

  /**
   * 上传文件
   * 
   * @return
   */
  @RequestMapping("/uploadFowords")
  public String uploadFowords() {
    return "jsp/study/uploadCourseByuse";
  }


  /**
   * 上传文件
   * 
   * @return
   */
  @RequestMapping("/uploadPic")
  public String uploadPic() {
    return "jsp/study/uploadPicByuse";
  }

  /**
   * 文件上传
   * 
   * @param response
   * @param request
   * @param multipartRequest
   * @return
   * @throws Exception
   */
  @RequestMapping("/uploadFilePic")
  @ResponseBody
  public void uploadFilePic(HttpServletResponse response, HttpServletRequest request,
      MultipartHttpServletRequest multipartRequest) throws Exception {

    response.setCharacterEncoding("utf-8");
    String result = "";

    try {
      for (Iterator<?> it = multipartRequest.getFileNames(); it.hasNext();) {
        String key = (String) it.next();
        MultipartFile orderFile = multipartRequest.getFile(key);
        if (orderFile.getOriginalFilename().length() > 0) {
          logger.info("rderFile.getOriginalFilename()==" + orderFile.getOriginalFilename());

          String realPath =
              multipartRequest.getSession().getServletContext().getRealPath("/uploadFile/pic");
          FileUtils.copyInputStreamToFile(orderFile.getInputStream(),
              new File(realPath, orderFile.getOriginalFilename()));
        }
        response.getWriter().println(orderFile.getOriginalFilename() + "|" + "上传成功");
      }
      // result="{result:'上传成功'}";
    } catch (Exception ex) {
      result = "{result:'上传失败'}";
      ex.printStackTrace();
    }
    response.getWriter().print(result);

  }

  /**
   * 文件上传
   * 
   * @param response
   * @param request
   * @param multipartRequest
   * @return
   * @throws Exception
   */
  @RequestMapping("/uploadFile")
  @ResponseBody
  public void uploadFile(HttpServletResponse response, HttpServletRequest request,
      MultipartHttpServletRequest multipartRequest) throws Exception {

    response.setCharacterEncoding("utf-8");
    String result = "";

    try {
      for (Iterator<?> it = multipartRequest.getFileNames(); it.hasNext();) {
        String key = (String) it.next();
        MultipartFile orderFile = multipartRequest.getFile(key);
        if (orderFile.getOriginalFilename().length() > 0) {
          logger.info("rderFile.getOriginalFilename()==" + orderFile.getOriginalFilename());

          String realPath =
              multipartRequest.getSession().getServletContext().getRealPath("/uploadFile/file");
          FileUtils.copyInputStreamToFile(orderFile.getInputStream(),
              new File(realPath, orderFile.getOriginalFilename()));
        }
        response.getWriter().println(orderFile.getOriginalFilename() + "|" + "上传成功");
      }
      // result=",上传成功";
    } catch (Exception ex) {
      result = "上传失败";
      ex.printStackTrace();
    }
    response.getWriter().print(result);

  }

  /**
   * 获取课件数据表格
   * 
   * @param coursewareVO
   * @return
   */
  @RequestMapping("/dataGridBook")
  @ResponseBody
  public DataGrid dataGridBook(CoursewareVO coursewareVO, PageHelper ph) {

    return coursewareService.queryBookPanings(coursewareVO, ph);
  }


  @RequestMapping("/getPermBooks")
  @ResponseBody
  public HashMap<String, Object> getPermBooks(HttpServletRequest request) {
    long classId = ParamUtils.getLongParameter(request, "classId", 0);
    long category = ParamUtils.getLongParameter(request,"category", 0);
    int offset = ParamUtils.getIntParameter(request, "offset",0);
    int limit = ParamUtils.getIntParameter(request, "limit",0);
    HashMap<String, Object> resMap = new HashMap<String, Object>();
    PagerModel<HashMap<String,Object>> pm = new PagerModel<HashMap<String,Object>>();
    int code = coursewareService.getBooks(classId, category, pm, offset, limit);
    long totalSize = 0;
    totalSize = pm.getTotals();
    List<HashMap<String,Object>> resList =  pm.getItems();
    resMap.put("code", code);
    resMap.put("totalSize", totalSize);
    resMap.put("info", resList);
    return resMap;
  }
  
  @RequestMapping("/getPermBooksCategory")
  @ResponseBody
  public HashMap<String, Object> getPermBooksCategory(HttpServletRequest request) {
    long classId = ParamUtils.getLongParameter(request, "classId", 0);
    HashMap<String, Object> resMap = new HashMap<String, Object>();
    List<HashMap<String, Object>> resList = new ArrayList<HashMap<String, Object>>();
    int code = coursewareService.getBooksCategory(classId, resList);
    resMap.put("code", code);
    resMap.put("info", resList);
    return resMap;
  }

  /**
   * 添加记事本
   * 
   * @param courseNote
   * @return
   */
  @RequestMapping("/createNote")
  @ResponseBody
  public Json createNote(userCourseNote courseNote, HttpServletRequest request, HttpSession session) {
    Json json = new Json();
    if (StringUtils.isBlank(courseNote.getUserId().toString())) {
      json.setMsg("用户编号不为空！");
    }

    try {
      this.coursewareService.createNote(courseNote.getUserId(), courseNote.getCoursewareId(),
          courseNote.getNote());
      json.setSuccess(true);
      json.setMsg("添加成功！");

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
  @RequestMapping("/queryNote")
  @ResponseBody
  public List<userCourseNote> queryNote(Long userId) {
    return this.coursewareService.queryNote(userId);
  }

  /**
   * 获取课件数据表格
   * 
   * @param coursewareVO
   * @return
   */
  @RequestMapping("/dataGridHomework")
  @ResponseBody
  public DataGrid dataGridHomework(CoursewareVO coursewareVO, PageHelper ph) {
    coursewareVO.setCategoryName("作业");
    return coursewareService.queryBookPaning(coursewareVO, ph);
  }

  /**
   * 获取课件数据表格
   * 
   * @param coursewareVO
   * @return
   */
  @RequestMapping("/dataGridAdditional")
  @ResponseBody
  public DataGrid dataGridAdditional(CoursewareVO coursewareVO, PageHelper ph) {
    coursewareVO.setCategoryName("补充");
    return coursewareService.queryBookPaning(coursewareVO, ph);
  }

  /**
   * 获取课件数据表格
   * 
   * @param coursewareVO
   * @return
   */
  @RequestMapping("/dataGridLocal")
  @ResponseBody
  public DataGrid dataGridLocal(CoursewareVO coursewareVO, PageHelper ph) {
    coursewareVO.setCategoryName("电子白板");
    return coursewareService.queryBookPaning(coursewareVO, ph);
  }

  @RequestMapping("/queryCategory")
  @ResponseBody
  public List<CoursewareNode> queryCategory() {
    return this.coursewareCategoryService.createCoursewareNodeList(CourseUseTypeEnum.Ebooks
        .toValue());
  }


  @RequestMapping("/queryBookByCatetory")
  @ResponseBody
  public List<Courseware> queryBookByCatetory(Long id) {

    // List<Courseware> list = this.coursewareService.queryBookByCatetory(catetoryId);

    return this.coursewareService.queryBookByCatetory(id);
  }


  @ResponseBody
  @RequestMapping("/queryBookById")
  public Json queryBookById(Long id) {
    Json json = new Json();
    Courseware courseware = null;
    try {
      courseware = coursewareService.getCoursewareById(id);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    if (courseware != null) {
      json.setSuccess(true);
      Gson gson = new Gson();
      String result = gson.toJson(courseware);
      logger.info("result==" + result);
      json.setObj(result);

    } else {
      json.setMsg("无数据！");
    }

    return json;
  }

  @RequestMapping("/swfupload")
  public ModelAndView fileUpload(HttpServletRequest request,
      @RequestParam("filedata") MultipartFile file) throws Exception {
    try {

      System.out.println("dddddddddddddddddddd");
      String uploadDir = request.getSession().getServletContext().getRealPath("/uploadFile/file");
      // String uploadDir = request.getRealPath("/upload");
      File dirPath = new File(uploadDir);
      if (!dirPath.exists()) {
        dirPath.mkdirs();
      }
      file.transferTo(new File(uploadDir + "/" + file.getOriginalFilename()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    ModelAndView mv = new ModelAndView();
    mv.setViewName("system/file/upload");
    return mv;
  }

  private void genMovePathFile(HttpServletRequest request, String destUrl) {
    String mpath = request.getParameter("m_path");
    if (StringUtils.isNotBlank(mpath)) {
      String path = destUrl + "copy_path.ini";
      String content = "Path=" + mpath;
      FileOperation.write(content, path, "UTF-8");
    }
  }

  @RequestMapping("/sendBooks")
  @ResponseBody
  public Json sendBooks(Long id, HttpServletRequest request) {
    String firstTime = request.getParameter("beginTime");
    String endTime = request.getParameter("endTime");
    Courseware courseware = null;
    try {
      courseware = coursewareService.getCoursewareById(id);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    List<Courseware> wareList = new ArrayList<Courseware>(1);
    wareList.add(courseware);
    String insertContent = getInsertContent(wareList);

    String uploadDir = request.getSession().getServletContext().getRealPath(courseware.getUrl());
    String uploadPic = request.getSession().getServletContext().getRealPath(courseware.getPic());
    String uploadDirName = uploadDir.substring(uploadDir.lastIndexOf("\\") + 1, uploadDir.length());
    String uploadPicName = "";
    if(StringUtils.isNotBlank(uploadPic))
      uploadPicName = uploadPic.substring(uploadPic.lastIndexOf("\\") + 1, uploadPic.length());
    System.out.println("uploadDir==" + uploadDir);
    System.out.println("uploadDirName==" + uploadDirName);

    String targetDir = "book" + id;
    String fileSqlName = "book_" + id + ".sql";
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

    try {
      if(StringUtils.isNotBlank(uploadPicName))
        FileOperation.copyFile(new File(uploadPic), new File(picDir + "/" + uploadPicName));
      FileOperation.copyFile(new File(uploadDir), new File(filesDir + "/" + uploadDirName));
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
      coursewareService.modifyCoursewareStauts(id);
      json.setSuccess(true);
      json.setMsg("下发成功!");
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg("下发失败！");
    }

    return json;
  }

  @RequestMapping("/sendBooksBatch")
  @ResponseBody
  public Json sendBooksBatch(HttpServletRequest request) {
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
    String targetDir = "book" + filePk;
    String fileSqlName = "book_" + filePk + ".sql";
    Json json = new Json();
    String fileName = SystemUtil.createUUID();
    String destDirName = "d:/push_profile";
    String targetDirs = destDirName + "/" + targetDir;
    String picDir = targetDirs + "/pic";
    String filesDir = targetDirs + "/file";
    String path = destDirName + "/" + fileName + ".ini";

    String insertPath = destDirName + "/" + targetDir + "/" + fileSqlName;
    List<Courseware> coursewareList = coursewareService.getCoursewareListByIds(ids);
    StringBuilder insertContent = new StringBuilder();
    try {
      if (coursewareList != null && coursewareList.size() > 0) {
        FileOperation.createDir(picDir);
        FileOperation.createDir(filesDir);
        FileOperation.createDir(targetDirs);
        FileOperation.createDir(destDirName);
        for (int i = 0; i < coursewareList.size(); i++) {
          Courseware courseware = coursewareList.get(i);
          String pic = courseware.getPic();
          if(StringUtils.isBlank(pic))
            pic = "";
          insertContent
              .append(" insert into courseware (name, courseware_category_id, url, created_time, creator, pic,grade_id,ispublic)");
          insertContent.append(" values ('" + courseware.getName() + "', "
              + courseware.getCoursewareCategoryId() + ", ");
          insertContent.append("'" + courseware.getUrl() + "', '"
              + DateUtil.dateToString(courseware.getCreatedTime(), false) + "',");
          insertContent.append("" + courseware.getCreator() + ", '" + pic + "',"
              + courseware.getGradeId() + "," + courseware.getIsPublic() + ");");
          if (i == coursewareList.size() - 1) {
            insertContent.append("commit;");
          }

          String uploadDir =
              request.getSession().getServletContext().getRealPath(courseware.getUrl());
          String uploadPic =
              request.getSession().getServletContext().getRealPath(courseware.getPic());
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
      coursewareService.modifyCoursewareStauts(ids);
      json.setSuccess(true);
      json.setMsg("下发成功!");
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg("下发失败！");
    }

    return json;
  }

  // 生成sql语句
  private String getInsertContent(List<Courseware> coursewareList) {
    StringBuilder insertContent = new StringBuilder();
    if (coursewareList != null) {
      for (int i = 0; i < coursewareList.size(); i++) {
        Courseware courseware = coursewareList.get(i);
        String pic =  courseware.getPic();
        if(StringUtils.isBlank(pic))
          pic = "";
        insertContent
            .append(" insert into courseware (name, courseware_category_id, url, created_time, creator, pic,grade_id,ispublic)");
        insertContent.append(" values ('" + courseware.getName() + "', "
            + courseware.getCoursewareCategoryId() + ", ");
        insertContent.append("'" + courseware.getUrl() + "', '"
            + DateUtil.dateToString(courseware.getCreatedTime(), false) + "',");
        insertContent.append("" + courseware.getCreator() + ", '" +pic+ "',"
            + courseware.getGradeId() + "," + courseware.getIsPublic() + ");");
        if (i == coursewareList.size() - 1) {
          insertContent.append("commit;");
        }
      }
    }
    return insertContent.toString();

  }

  /**
   * 跳转到消息页面
   * 
   * @return
   */
  @RequestMapping("/editProfileBook")
  public String editProfileBook(HttpServletRequest request, Long id) {

    Courseware courseware;
    try {
      logger.info("id==" + id);
      courseware = this.coursewareService.getCoursewareById(id);
      if (courseware != null) {
        logger
            .info("courseware.getCoursewareCategoryId()==" + courseware.getCoursewareCategoryId());
        CoursewareCategory category =
            coursewareCategoryService.getCoursewareCategoryByID(courseware
                .getCoursewareCategoryId());
        request.setAttribute("categoryName", category.getName());
      }

      String firstTime = DateUtil.dateToString(new Date(), false);

      request.setAttribute("firstTime", firstTime);
      request.setAttribute("courseware", courseware);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "jsp/study/editProfileBook";
  }

  @RequestMapping("/editProfileBooks")
  public String editProfileBooks(HttpServletRequest request) {
    String firstTime = DateUtil.dateToString(new Date(), false);
    request.setAttribute("ids", request.getParameter("ids"));
    request.setAttribute("firstTime", firstTime);
    return "jsp/study/editProfileBook";
  }


  @RequestMapping("/getCoursewareCategoryJson")
  @ResponseBody
  public List<CoursewareCategory> getCoursewareCategoryJson(HttpServletRequest request) {
    int type = ParamUtils.getIntParameter(request,"type",CourseUseTypeEnum.Ebooks.toValue());
    return coursewareCategoryService.getCoursewareCategoryList(type);
  }

  @RequestMapping("/getCoursewareCategorySelect")
  @ResponseBody
  public List<CoursewareCategory> getCoursewareCategorySelect() {
    return coursewareCategoryService.getCoursewareCategoryList(CourseUseTypeEnum.Courseware
        .toValue());
  }

  @RequestMapping("/getResMPathJson")
  @ResponseBody
  public List<HashMap<String, Object>> getResMPathJson() {
    return coursewareService.getResMPath(CourseUseTypeEnum.Ebooks.toValue());
  }
}
