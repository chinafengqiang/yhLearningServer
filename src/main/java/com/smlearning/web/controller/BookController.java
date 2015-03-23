package com.smlearning.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smlearning.application.service.BookService;
import com.smlearning.domain.vo.CoursewareVO;
import com.smlearning.infrastructure.utils.Json;
import com.smlearning.infrastructure.utils.ParamUtils;

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
  
  @RequestMapping("/getBookpart")
  @ResponseBody
  public List<HashMap<String,Object>> getBookpart(HttpServletRequest request) {
    int gradeId = ParamUtils.getIntParameter(request,"gradeId",0);
    int categoryId = ParamUtils.getIntParameter(request,"categoryId",0);
    return bookService.getBooKpart(gradeId, categoryId);
  }
}
