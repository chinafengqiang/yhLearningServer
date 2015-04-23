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
import com.smlearning.application.service.VideoService;
import com.smlearning.domain.entity.Manager;
import com.smlearning.domain.vo.Tree;
import com.smlearning.infrastructure.utils.DateUtil;
import com.smlearning.infrastructure.utils.FileOperation;
import com.smlearning.infrastructure.utils.Json;
import com.smlearning.infrastructure.utils.ParamUtils;
import com.smlearning.infrastructure.utils.SystemUtil;
import com.smlearning.infrastructure.utils.VersionInfo;

@Controller
@RequestMapping("/videoController")
  
public class VideoController {
    
    @Autowired
    private VideoService videoService;
    
    @RequestMapping("/managerVideo")
    public String managerVideo() {
      return "jsp/system/video/managerVideo";
    }
    
    @RequestMapping("/managerVideopart")
    public ModelAndView managerVideopart(HttpServletRequest request){
      ModelAndView mv = new ModelAndView("jsp/system/video/managerVideopart");
      int gradeId = ParamUtils.getIntParameter(request,"gradeId",0);
      int categoryId = ParamUtils.getIntParameter(request,"categoryId",0);
      mv.addObject("gradeId",gradeId);
      mv.addObject("categoryId",categoryId);
      return mv;
    }
    
    @RequestMapping(value = "getVideoResList")
    @ResponseBody
    public HashMap<String, Object> getVideoList(DataGridModel dm, HttpServletRequest request) {
      HashMap<String, String> params = ParamUtils.getFilterStringParams(request);
      HashMap<String, Object> resMap = videoService.getVideoResList(dm, params);
      return resMap;
    }
    
    @RequestMapping("/addVideoRes")
    public ModelAndView addVideoRes(HttpServletRequest request) {
      ModelAndView mv = new ModelAndView("jsp/system/video/addVideoRes");
      int ctgId = ParamUtils.getIntParameter(request, "ctgId",0);
      int partId = ParamUtils.getIntParameter(request, "partId",0);
      mv.addObject("ctgId", ctgId);
      mv.addObject("partId", partId);
      return mv;
    }
    
    @RequestMapping("/createVideoRes")
    @ResponseBody
    public Json createBookRes(HttpServletRequest request,HttpSession session) {
      Json json = new Json();
      try {
        Manager manager = (Manager) session.getAttribute("manager");
        HashMap<String,String> videores = ParamUtils.getParameters(request);
        videores.put("creator",manager.getId()+"");
        videores.put("created_time",DateUtil.long2TimeString(new Date().getTime()));
        long id = videoService.createVideoRes(videores);
        json.setSuccess(true);
      } catch (Exception e) {
        json.setSuccess(false);
        json.setMsg(e.getMessage());
      }
      return json;
    }
    
    @RequestMapping("/editVideoRes")
    public ModelAndView editVideoRes(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("jsp/system/video/editVideoRes");
        int id = ParamUtils.getIntParameter(request,"id",0);
        HashMap<String,Object> videores = videoService.getVideoResById(id);
        mv.addObject("video",videores);
        return mv;
    }

    @RequestMapping("/updateVideoRes")
    @ResponseBody
    public Json updateVideoRes(HttpServletRequest request) {
      Json json = new Json();
      try {
        HashMap<String,String> videores = ParamUtils.getParameters(request);
        videoService.updateVideores(videores);
        json.setSuccess(true);
      } catch (Exception e) {
        json.setSuccess(false);
        json.setMsg(e.getMessage());
      }
      return json;
    }
    
    @RequestMapping("/deleteVideoRes")
    @ResponseBody
    public Json deleteVideokRes(HttpServletRequest request){
      Json json = new Json();
      try {
        String ids = ParamUtils.getParameter(request, "ids","");
        videoService.deleteVideoRes(ids);
        json.setSuccess(true);
      } catch (Exception e) {
        json.setSuccess(false);
        json.setMsg(e.getMessage());
      }
      return json;
    }
}
