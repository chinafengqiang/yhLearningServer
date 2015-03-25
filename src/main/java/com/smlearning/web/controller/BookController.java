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
  
  @RequestMapping("/addBookresource")
  public ModelAndView addBookresource(HttpServletRequest request){
    ModelAndView mv = new ModelAndView("jsp/system/book/addBookresource");
    int partId = ParamUtils.getIntParameter(request,"partId",0);
    int pid = ParamUtils.getIntParameter(request,"pid",0);
    mv.addObject("partId",partId);
    mv.addObject("pid",pid);
    return mv;
  }
  
}
