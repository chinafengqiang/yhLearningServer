package com.smlearning.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smlearning.infrastructure.utils.ParamUtils;
import com.smlearning.web.controller.BaseController;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/api")
public class MainApi extends BaseController{
    @Autowired
    private IApi apiService;
    
    @RequestMapping("/getBookCategory")
    @ResponseBody
    public HashMap<String, Object> getBookCategory(HttpServletRequest request){
      int classId = ParamUtils.getIntParameter(request, "classId",0);
      List<HashMap<String, Object>> resList = apiService.getBookCategory(classId);
      HashMap<String,Object> resMap = new HashMap<String, Object>();
      resMap.put("bookCategoryList",resList);
      return resMap;
    }
    
    @RequestMapping("/getBookPart")
    @ResponseBody
    public HashMap<String, Object> getBookPart(HttpServletRequest request){
      int classId = ParamUtils.getIntParameter(request, "classId",0);
      int categoryId = ParamUtils.getIntParameter(request, "categoryId",0);
      List<HashMap<String, Object>> resList = apiService.getBookPart(classId, categoryId);
      HashMap<String,Object> resMap = new HashMap<String, Object>();
      resMap.put("bookPartList",resList);
      return resMap;
    }
    
    @RequestMapping("/getBookChapter")
    @ResponseBody
    public HashMap<String, Object> getBookChapter(HttpServletRequest request){
      int pid = ParamUtils.getIntParameter(request, "pid",0);
      int partId = ParamUtils.getIntParameter(request, "partId",0);
      int plevel =  ParamUtils.getIntParameter(request, "plevel",-1);
      List<HashMap<String, Object>> resList = apiService.getBookChapter(pid, partId, plevel);
      HashMap<String,Object> resMap = new HashMap<String, Object>();
      resMap.put("bookChapterList",resList);
      return resMap;
    }
    
    @RequestMapping("/getBookResCategory")
    @ResponseBody
    public HashMap<String, Object> getBookResCategory(HttpServletRequest request){
      int partId = ParamUtils.getIntParameter(request, "partId",0);
      int plevel =  ParamUtils.getIntParameter(request, "plevel",-1);
      int type = ParamUtils.getIntParameter(request, "type",0);
      List<HashMap<String, Object>> resList = apiService.getBookResCategory(partId,plevel,type);
      HashMap<String,Object> resMap = new HashMap<String, Object>();
      resMap.put("bookChapterList",resList);
      return resMap;
    }
    
    @RequestMapping("/getBookRes")
    @ResponseBody
    public HashMap<String, Object> getBookRes(HttpServletRequest request){
      int partId = ParamUtils.getIntParameter(request, "partId",0);
      int categoryId =  ParamUtils.getIntParameter(request, "categoryId",0);
      List<HashMap<String, Object>> resList = apiService.getBookRes(partId, categoryId);
      HashMap<String,Object> resMap = new HashMap<String, Object>();
      resMap.put("bookResList",resList);
      return resMap;
    }
    
    
    @RequestMapping("/updateUserPass")
    @ResponseBody
    public HashMap<String, Integer> updateUserPass(HttpServletRequest request){
      int userId = ParamUtils.getIntParameter(request, "userId",0);
      String oldPass = ParamUtils.getParameter(request, "oldPass","");
      String newPass = ParamUtils.getParameter(request, "newPass","");
      HashMap<String, Integer> resMap = apiService.updateUserPass(userId, oldPass, newPass);
      return resMap;
    }
    
    @RequestMapping("/getVideoRes")
    @ResponseBody
    public HashMap<String, Object> getVideoRes(HttpServletRequest request){
      int partId = ParamUtils.getIntParameter(request, "partId",0);
      int categoryId =  ParamUtils.getIntParameter(request, "categoryId",0);
      List<HashMap<String, Object>> resList = apiService.getVideoRes(partId, categoryId);
      HashMap<String,Object> resMap = new HashMap<String, Object>();
      resMap.put("videoResList",resList);
      return resMap;
    }
    
    @RequestMapping("/searchBookRes")
    @ResponseBody
    public HashMap<String, Object> searchBookRes(HttpServletRequest request){
      String value = ParamUtils.getParameter(request,"value","");
      int classId = ParamUtils.getIntParameter(request, "classId",0);
      try {
        value = java.net.URLDecoder.decode(value,"utf-8");
      } catch (Exception e) {
        // TODO: handle exception
      }
      List<HashMap<String, Object>> resList = apiService.searchBookRes(classId,value);
      HashMap<String,Object> resMap = new HashMap<String, Object>();
      resMap.put("bookResList",resList);
      return resMap;
    }
    
    @RequestMapping("/searchVideoRes")
    @ResponseBody
    public HashMap<String, Object> searchVideoRes(HttpServletRequest request){
      String value = ParamUtils.getParameter(request,"value","");
      int classId = ParamUtils.getIntParameter(request, "classId",0);
      try {
        value = java.net.URLDecoder.decode(value,"utf-8");
      } catch (Exception e) {
        // TODO: handle exception
      }
      List<HashMap<String, Object>> resList = apiService.searchVideoRes(classId,value);
      HashMap<String,Object> resMap = new HashMap<String, Object>();
      resMap.put("videoResList",resList);
      return resMap;
    }
    
    
    @RequestMapping("/getClassTearch")
    @ResponseBody
    public HashMap<String, Object> getClassTearch(HttpServletRequest request){
      int classId = ParamUtils.getIntParameter(request, "classId",0);
      List<HashMap<String, Object>> resList = apiService.getClassTearch(classId);
      HashMap<String,Object> resMap = new HashMap<String, Object>();
      resMap.put("tearchResList",resList);
      return resMap;
    }
    
    
    
    private ServletFileUpload upload;
    final long MAXSize = 4194304*2L;//4*2MB
    String filedir=null;
    @RequestMapping("/saveMessage")
    @ResponseBody
    public HashMap<String, Object> saveMessage(HttpServletRequest request,HttpSession session){
      String msgctxt = ParamUtils.getParameter(request, "msgctxt","");
      FileItemFactory factory = new DiskFileItemFactory();// Create a factory for disk-based file items
      this.upload = new ServletFileUpload(factory);// Create a new file upload handler
      this.upload.setSizeMax(this.MAXSize);// Set overall request size constraint 4194304
      filedir=session.getServletContext().getRealPath("images");
      try {
        List<FileItem> items = this.upload.parseRequest(request);
        System.out.println(items);
      } catch (Exception e) {
        // TODO: handle exception
      }
      HashMap<String,Object> resMap = new HashMap<String, Object>();
      resMap.put("res",200);
      return resMap;
    }
    
}
