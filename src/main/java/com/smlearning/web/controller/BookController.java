package com.smlearning.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.iactive.db.DataGridModel;

import com.smlearning.application.service.BookService;
import com.smlearning.domain.entity.Manager;
import com.smlearning.domain.vo.CoursewareVO;
import com.smlearning.domain.vo.Tree;
import com.smlearning.infrastructure.utils.DateUtil;
import com.smlearning.infrastructure.utils.FileOperation;
import com.smlearning.infrastructure.utils.Json;
import com.smlearning.infrastructure.utils.ParamUtils;
import com.smlearning.infrastructure.utils.StringUtil;
import com.smlearning.infrastructure.utils.SystemUtil;
import com.smlearning.infrastructure.utils.VersionInfo;

@Controller
@RequestMapping("/bookController")
public class BookController extends BaseController{
  
  @Autowired
  private BookService bookService;
  
  @RequestMapping("/managerBook")
  public String managerBook() {
    return "jsp/system/book/managerBook";
  }
  
  @RequestMapping("/managerBookpart")
  public ModelAndView managerBookpart(HttpServletRequest request){
    ModelAndView mv = new ModelAndView("jsp/system/book/managerBookpart");
    int gradeId = ParamUtils.getIntParameter(request,"gradeId",0);
    int categoryId = ParamUtils.getIntParameter(request,"categoryId",0);
    mv.addObject("gradeId",gradeId);
    mv.addObject("categoryId",categoryId);
    return mv;
  }
  
  @RequestMapping("/addBookpart")
  public ModelAndView addBookpart(HttpServletRequest request){
    ModelAndView mv = new ModelAndView("jsp/system/book/addBookpart");
    int gradeId = ParamUtils.getIntParameter(request,"gradeId",0);
    int categoryId = ParamUtils.getIntParameter(request,"categoryId",0);
    mv.addObject("gradeId",gradeId);
    mv.addObject("categoryId",categoryId);
    return mv;
  }
  
  @RequestMapping("/createBookpart")
  @ResponseBody
  public Json createBookpart(HttpServletRequest request) {
    Json json = new Json();
    try {
      HashMap<String,String> bookpart = ParamUtils.getParameters(request);
      bookService.createBookpart(bookpart);
      json.setSuccess(true);
    } catch (Exception e) {
      json.setSuccess(false);
      json.setMsg(e.getMessage());
    }
    return json;
  }
  
  
  @RequestMapping("/updateBookpart")
  @ResponseBody
  public Json updateBookpart(HttpServletRequest request) {
    Json json = new Json();
    try {
      HashMap<String,String> bookpart = ParamUtils.getParameters(request);
      bookService.updateBookpart(bookpart);
      json.setSuccess(true);
    } catch (Exception e) {
      json.setSuccess(false);
      json.setMsg(e.getMessage());
    }
    return json;
  }
  
  
  @RequestMapping("/getBookpart")
  @ResponseBody
  public List<HashMap<String,Object>> getBookpart(HttpServletRequest request) {
    int gradeId = ParamUtils.getIntParameter(request,"gradeId",0);
    int categoryId = ParamUtils.getIntParameter(request,"categoryId",0);
    return bookService.getBooKpart(gradeId, categoryId);
  }
  
  @RequestMapping("/editBookpart")
  public ModelAndView editBookpart(HttpServletRequest request){
      ModelAndView mv = new ModelAndView("jsp/system/book/editBookpart");
      int id = ParamUtils.getIntParameter(request,"id",0);
      HashMap<String,Object> bookpart = bookService.getBookpartById(id);
      mv.addObject("bookpart",bookpart);
      return mv;
  }
  
  @RequestMapping("/deleteBookpart")
  @ResponseBody
  public Json deleteBookpart(HttpServletRequest request){
    Json json = new Json();
    try {
      String ids = ParamUtils.getParameter(request, "ids","");
      bookService.deleteBookpart(ids);
      json.setSuccess(true);
    } catch (Exception e) {
      json.setSuccess(false);
      json.setMsg(e.getMessage());
    }
    return json;
  }
  
  
  @RequestMapping("/managerBookresource")
  public ModelAndView managerBookresource(HttpServletRequest request){
    ModelAndView mv = new ModelAndView("jsp/system/book/managerBookresource");
    int partId = ParamUtils.getIntParameter(request,"partId",0);
    String name = ParamUtils.getParameter(request, "name","");
    mv.addObject("partId",partId);
    mv.addObject("name",name);
    return mv;
  }
  
  @RequestMapping("/addBookChapter")
  public ModelAndView addBookChapter(HttpServletRequest request){
    ModelAndView mv = new ModelAndView("jsp/system/book/addBookChapter");
    int partId = ParamUtils.getIntParameter(request,"partId",0);
    int pid = ParamUtils.getIntParameter(request,"pid",0);
    mv.addObject("partId",partId);
    mv.addObject("pid",pid);
    return mv;
  }
  
  
  
  @RequestMapping("/createBookchapter")
  @ResponseBody
  public Json createBookchapter(HttpServletRequest request) {
    Json json = new Json();
    try {
      HashMap<String,String> bookchater = ParamUtils.getParameters(request);
      bookService.createBookchapter(bookchater);
      json.setSuccess(true);
    } catch (Exception e) {
      json.setSuccess(false);
      json.setMsg(e.getMessage());
    }
    return json;
  }
  
  @RequestMapping("/getChapterTreeJson")
  @ResponseBody
  public List<Tree> getChapterTreeJson(HttpServletRequest request){
    int partId = ParamUtils.getIntParameter(request, "partId",0);
    String rootName = ParamUtils.getParameter(request, "rootName","");
    return bookService.getChapterTreeJson(partId,rootName);
  }
  
  
  @RequestMapping("/editBookChapter")
  public ModelAndView editBookChapter(HttpServletRequest request){
      ModelAndView mv = new ModelAndView("jsp/system/book/editBookChapter");
      int id = ParamUtils.getIntParameter(request,"id",0);
      HashMap<String,Object> bookchapter = bookService.getBookchapterById(id);
      mv.addObject("bookchapter",bookchapter);
      return mv;
  }
  
  @RequestMapping("/updateBookchapter")
  @ResponseBody
  public Json updateBookchapter(HttpServletRequest request) {
    Json json = new Json();
    try {
      HashMap<String,String> bookchapter = ParamUtils.getParameters(request);
      bookService.updateBookchapter(bookchapter);
      json.setSuccess(true);
    } catch (Exception e) {
      json.setSuccess(false);
      json.setMsg(e.getMessage());
    }
    return json;
  }
  
  @RequestMapping("/deleteBookchapter")
  @ResponseBody
  public Json deleteBookchapter(HttpServletRequest request){
    Json json = new Json();
    try {
      int id = ParamUtils.getIntParameter(request, "id", 0);
      bookService.deleteBookchapter(id);
      json.setSuccess(true);
    } catch (Exception e) {
      json.setSuccess(false);
      json.setMsg(e.getMessage());
    }
    return json;
  }
  
  @RequestMapping("/addBookRes")
  public ModelAndView addBookRes(HttpServletRequest request) {
    ModelAndView mv = new ModelAndView("jsp/system/book/addBookRes");
    int ctgId = ParamUtils.getIntParameter(request, "ctgId",0);
    int partId = ParamUtils.getIntParameter(request, "partId",0);
    
//    HashMap<String,Object> part = bookService.getBookpartById(partId);
//    int gradeId = 0;
//    if(part != null){
//      gradeId = (Integer)part.get("GRADE_ID");
//    }
    int gradeId = bookService.getResGradeId(partId);
    mv.addObject("ctgId", ctgId);
    mv.addObject("partId", partId);
    mv.addObject("gradeId",gradeId);
    return mv;
  }
 
  @RequestMapping("/createBookRes")
  @ResponseBody
  public Json createBookRes(HttpServletRequest request,HttpSession session) {
    Json json = new Json();
    try {
      Manager manager = (Manager) session.getAttribute("manager");
      HashMap<String,String> bookres = ParamUtils.getParameters(request);
      bookres.put("creator",manager.getId()+"");
      bookres.put("created_time",DateUtil.long2TimeString(new Date().getTime()));
      long id = bookService.createBookRes(bookres);
      json.setSuccess(true);
    } catch (Exception e) {
      json.setSuccess(false);
      json.setMsg(e.getMessage());
    }
    return json;
  }
  
  @RequestMapping(value = "getBookResList")
  @ResponseBody
  public HashMap<String, Object> getCoursewareList(DataGridModel dm, HttpServletRequest request) {
    HashMap<String, String> params = ParamUtils.getFilterStringParams(request);
    HashMap<String, Object> resMap = bookService.getBookResList(dm, params);
    return resMap;
  }
  
  @RequestMapping("/editBookRes")
  public ModelAndView editBookRes(HttpServletRequest request){
      ModelAndView mv = new ModelAndView("jsp/system/book/editBookRes");
      int id = ParamUtils.getIntParameter(request,"id",0);
      HashMap<String,Object> bookres = bookService.getBookResById(id);
      mv.addObject("res",bookres);
      return mv;
  }
  
  @RequestMapping("/updateBookRes")
  @ResponseBody
  public Json updateBookRes(HttpServletRequest request) {
    Json json = new Json();
    try {
      HashMap<String,String> bookres = ParamUtils.getParameters(request);
      bookService.updateBookres(bookres);
      json.setSuccess(true);
    } catch (Exception e) {
      json.setSuccess(false);
      json.setMsg(e.getMessage());
    }
    return json;
  }
  
  @RequestMapping("/deleteBookRes")
  @ResponseBody
  public Json deleteBookRes(HttpServletRequest request){
    Json json = new Json();
    try {
      String ids = ParamUtils.getParameter(request, "ids","");
      bookService.deleteBookRes(ids);
      json.setSuccess(true);
    } catch (Exception e) {
      json.setSuccess(false);
      json.setMsg(e.getMessage());
    }
    return json;
  }
  
  @RequestMapping("/editSendBookRes")
  public ModelAndView editSendBookRes(HttpServletRequest request){
      ModelAndView mv = new ModelAndView("jsp/system/book/editSendBookRes");
      int id = ParamUtils.getIntParameter(request,"id",0);
      mv.addObject("id",id);
      return mv;
  }
  
  @RequestMapping("/editSendBookRess")
  public String editSendBookRess(HttpServletRequest request) {
    String firstTime = DateUtil.dateToString(new Date(), false);
    request.setAttribute("ids", request.getParameter("ids"));
    request.setAttribute("firstTime", firstTime);
    return "jsp/system/book/editSendBookRes";
  }
  
  
  @RequestMapping("/sendBooks")
  @ResponseBody
  public Json sendBooks(int id, HttpServletRequest request) {
    String firstTime = request.getParameter("beginTime");
    String endTime = request.getParameter("endTime");
    HashMap<String,Object> bookres = bookService.getBookResById(id);
    
    List<HashMap<String,Object>> wareList = new ArrayList<HashMap<String,Object>>(1);
    wareList.add(bookres);
    String insertContent = getInsertContent(wareList);

    String uploadDir = request.getSession().getServletContext().getRealPath(bookres.get("url").toString());
    String uploadDirName = uploadDir.substring(uploadDir.lastIndexOf("\\") + 1, uploadDir.length());
    
    System.out.println("uploadDir==" + uploadDir);
    System.out.println("uploadDirName==" + uploadDirName);

    String targetDir = "book" + id;
    String fileSqlName = "book_" + id + ".sql";
    Json json = new Json();
    String fileName = SystemUtil.createUUID();
    String destDirName = "d:/push_profile";
    String targetDirs = destDirName + "/" + targetDir;
    String filesDir = targetDirs + "/file";
    String path = destDirName + "/" + fileName + ".ini";
   
    FileOperation.createDir(filesDir);
    FileOperation.createDir(targetDirs);
    FileOperation.createDir(destDirName);
    String insertPath = destDirName + "/" + targetDir + "/" + fileSqlName;

    try {
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
      bookService.updateBookResSendStatus(id);
      json.setSuccess(true);
      json.setMsg("下发成功!");
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg("下发失败！");
    }

    return json;
  }
  
  // 生成sql语句
  private String getInsertContent(List<HashMap<String,Object>> bookresList) {
    StringBuilder insertContent = new StringBuilder();
    if (bookresList != null) {
      for (int i = 0; i < bookresList.size(); i++) {
        HashMap<String,Object> bookres = bookresList.get(i);
        
        long chapterId = (Long)bookres.get("class_id");
        StringBuilder chapterSql = bookService.getFileSql(chapterId, null);
        insertContent.append(chapterSql);
        
        insertContent
            .append(" insert into courseware (name, courseware_category_id, url, created_time, creator, pic,grade_id,ispublic,class_id)");
        insertContent.append(" SELECT '" + bookres.get("name") + "', "
            + bookres.get("courseware_category_id") + ", ");
        insertContent.append("'" + bookres.get("url") + "', '"
            + DateUtil.dateToString((Date)bookres.get("created_time"), false) + "',");
        insertContent.append("" + bookres.get("creator") + ", '',"
            + bookres.get("grade_id") + "," + bookres.get("ispublic") + ","+bookres.get("class_id"));
        insertContent.append(" FROM DUAL");
        insertContent.append(" WHERE NOT EXISTS(SELECT url FROM courseware WHERE url = '"+ bookres.get("url")+"');");
        if (i == bookresList.size() - 1) {
          insertContent.append("commit;");
        }
      }
    }
    return insertContent.toString();

  }
  
  private void genMovePathFile(HttpServletRequest request, String destUrl) {
    String mpath = request.getParameter("m_path");
    if (StringUtils.isNotBlank(mpath)) {
      String path = destUrl + "copy_path.ini";
      String content = "Path=" + mpath;
      FileOperation.write(content, path, "UTF-8");
    }
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
    String filesDir = targetDirs + "/file";
    String path = destDirName + "/" + fileName + ".ini";

    String insertPath = destDirName + "/" + targetDir + "/" + fileSqlName;
    List<HashMap<String, Object>> bookresList = bookService.getBookResListByIds(ids);
    StringBuilder insertContent = new StringBuilder();
    try {
      if (bookresList != null && bookresList.size() > 0) {
        FileOperation.createDir(filesDir);
        FileOperation.createDir(targetDirs);
        FileOperation.createDir(destDirName);
        for (int i = 0; i < bookresList.size(); i++) {
          HashMap<String, Object> bookres = bookresList.get(i);
          
          long chapterId = (Long)bookres.get("class_id");

          StringBuilder chapterSql = bookService.getFileSql(chapterId, null);
          insertContent.append(chapterSql);
          
          insertContent
              .append(" insert into courseware (name, courseware_category_id, url, created_time, creator, pic,grade_id,ispublic,class_id)");
          insertContent.append(" SELECT '" + bookres.get("name") + "', "
              + bookres.get("courseware_category_id") + ", ");
          insertContent.append("'" + bookres.get("url") + "', '"
              + DateUtil.dateToString((Date)bookres.get("created_time"), false) + "',");
          insertContent.append("" + bookres.get("creator") + ", '',"
              + bookres.get("grade_id") + "," + bookres.get("ispublic") + ","+bookres.get("class_id"));
          insertContent.append(" FROM DUAL");
          insertContent.append(" WHERE NOT EXISTS(SELECT url FROM courseware WHERE url = '"+ bookres.get("url")+"');");
          if (i == bookresList.size() - 1) {
            insertContent.append("commit;");
          }

          String uploadDir =
              request.getSession().getServletContext().getRealPath(bookres.get("url").toString());
          
          String uploadDirName =
              uploadDir.substring(uploadDir.lastIndexOf("\\") + 1, uploadDir.length());
         
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
      bookService.updateBookResSendStatus(ids);
      json.setSuccess(true);
      json.setMsg("下发成功!");
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg("下发失败！");
    }

    return json;
  }
  


}
