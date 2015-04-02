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
    public List<HashMap<String, Object>> getBookCategory(HttpServletRequest request){
      int gradeId = ParamUtils.getIntParameter(request, "gradeId",0);
      List<HashMap<String, Object>> resList = apiService.getBookCategory(gradeId);
      return resList;
    }
    
    @RequestMapping("/getBookPart")
    @ResponseBody
    public List<HashMap<String, Object>> getBookPart(HttpServletRequest request){
      int gradeId = ParamUtils.getIntParameter(request, "gradeId",0);
      int categoryId = ParamUtils.getIntParameter(request, "categoryId",0);
      List<HashMap<String, Object>> resList = apiService.getBookPart(gradeId, categoryId);
      return resList;
    }
}
