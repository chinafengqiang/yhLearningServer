package com.smlearning.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.iactive.db.DataGridModel;

import com.smlearning.application.service.SysGradeService;
import com.smlearning.domain.entity.Manager;
import com.smlearning.domain.vo.Tree;
import com.smlearning.infrastructure.utils.ParamUtils;
import com.smlearning.infrastructure.utils.WebUtil;

@Controller
@RequestMapping("/sysGradeController")
public class SysGradeController {

  @Autowired
  private SysGradeService gradeService;

  @RequestMapping("/gradeList")
  public String gradeList() {
    return "jsp/system/sysgrade/gradeList";
  }

  @RequestMapping(value = "getGradeList")
  @ResponseBody
  public HashMap<String, Object> getGradeList(DataGridModel dm, HttpServletRequest request) {
    return gradeService.getGradeList(dm);
  }

  @RequestMapping("/addGrade")
  public String addGrade() {
    return "jsp/system/sysgrade/addGrade";
  }

  @RequestMapping("/getOrgJson")
  @ResponseBody
  public List<HashMap<String, Object>> getOrgJson() {
    return gradeService.getOrgList();
  }

  @RequestMapping("/saveGrade")
  @ResponseBody
  public HashMap<String, Object> saveGrade(HttpServletRequest request) {
    HashMap<String, Object> resMap = new HashMap<String, Object>();
    HashMap<String, String> grade = ParamUtils.getParameters(request);
    Manager manager = WebUtil.getLoginInfo(request);
    if (manager != null) {
      grade.put("creator", manager.getId() + "");
    }
    boolean res = gradeService.saveGrade(grade);
    resMap.put("success", res);
    return resMap;
  }


  @RequestMapping(value = "editGrade")
  public ModelAndView editGrade(HttpServletRequest request) {
    ModelAndView view = new ModelAndView("jsp/system/sysgrade/editGrade");
    HashMap<String, Object> grade =
        gradeService.getGrade(Integer.parseInt(request.getParameter("id")));
    view.addObject("grade", grade);
    return view;
  }

  @RequestMapping("/updateGrade")
  @ResponseBody
  public HashMap<String, Object> updateGrade(HttpServletRequest request) {
    HashMap<String, Object> resMap = new HashMap<String, Object>();
    HashMap<String, String> grade = ParamUtils.getParameters(request);
    Manager manager = WebUtil.getLoginInfo(request);
    if (manager != null) {
      grade.put("creator", manager.getId() + "");
    }
    boolean res = gradeService.updateGrade(grade);
    resMap.put("success", res);
    return resMap;
  }
  
  @RequestMapping("/deleteGrade")
  @ResponseBody
  public HashMap<String, Object> deleteGrade(HttpServletRequest request) {
    HashMap<String, Object> resMap = new HashMap<String, Object>();
    boolean res = gradeService.deleteGrade(Integer.parseInt(request.getParameter("id")));
    resMap.put("success", res);
    return resMap;
  }
  
  @RequestMapping("/getGradeTreeJson")
  @ResponseBody
  public List<Tree> getGradeTreeJson(HttpServletRequest request){
    return gradeService.getGradeTreeJson();
  }
  
  
  
  @RequestMapping("/getGradeJson")
  @ResponseBody
  public List<HashMap<String, Object>> getGradeJson() {
    return gradeService.getAllGradeList();
  }
  
  @RequestMapping("/getGradeAndClassTreeJson")
  @ResponseBody
  public List<Tree> getGradeAndClassTreeJson(HttpServletRequest request){
    String checkIds = ParamUtils.getParameter(request, "checkIds","");
    return gradeService.getGradeAndClassTreeJson(checkIds);
  }

}
