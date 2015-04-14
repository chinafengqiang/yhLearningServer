package com.smlearning.api;

import javax.servlet.http.HttpServletRequest;

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
      List<HashMap<String, Object>> resList = apiService.getBookResCategory(partId,plevel);
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
    
}
