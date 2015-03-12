package com.smlearning.datasync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.Json;
import com.smlearning.infrastructure.utils.PageHelper;
import com.smlearning.infrastructure.utils.ParamUtils;
import com.smlearning.infrastructure.utils.SidGenerator;
import com.smlearning.web.controller.BaseController;


@Controller
@RequestMapping("/dataSyncController")
public class DataSyncController extends BaseController {
  @Autowired
  private IDataSync dataSync;

  @RequestMapping("/channel")
  public String channel() {
    return "jsp/system/datasync/listChannel";
  }

  @RequestMapping("/getChannel")
  @ResponseBody
  public DataGrid getChannel(PageHelper ph) {
    return dataSync.listChannel(ph);
  }

  @RequestMapping("/addChannel")
  public ModelAndView addChannel() {
    ModelAndView view = new ModelAndView("jsp/system/datasync/addChannel");
    view.addObject("sid", SidGenerator.genSid('C'));
    return view;
  }

  @RequestMapping("/saveChannel")
  @ResponseBody
  public Json saveChannel(HttpServletRequest request) {
    Json json = new Json();
    try {
      HashMap<String, String> channel = ParamUtils.getFilterStringParams(request);
      dataSync.createChannel(channel);
      json.setSuccess(true);
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg(e.getMessage());
    }

    return json;
  }

  @RequestMapping("/editChannel")
  public ModelAndView editChannel(int id) {
    ModelAndView view = new ModelAndView("jsp/system/datasync/editChannel");
    Map<String, Object> channel = dataSync.getChannel(id);
    view.addObject("channel", channel);
    return view;
  }

  @RequestMapping("/updateChannel")
  @ResponseBody
  public Json updateChannel(HttpServletRequest request) {
    Json json = new Json();
    try {
      HashMap<String, String> channel = ParamUtils.getFilterStringParams(request);
      dataSync.updateChannel(channel);
      json.setSuccess(true);
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg(e.getMessage());
    }

    return json;
  }

  @RequestMapping("/deleteChannel")
  @ResponseBody
  public Json deleteChannel(int id) {
    Json json = new Json();
    try {
      dataSync.deleteChannel(id);
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  @RequestMapping("/group")
  public String group() {
    return "jsp/system/datasync/listGroup";
  }

  @RequestMapping("/getGroup")
  @ResponseBody
  public DataGrid getGroup(PageHelper ph) {
    return dataSync.listGroup(ph);
  }

  @RequestMapping("/addGroup")
  public ModelAndView addGroup() {
    ModelAndView view = new ModelAndView("jsp/system/datasync/addGroup");
    return view;
  }

  @RequestMapping("/saveGroup")
  @ResponseBody
  public Json saveGroup(HttpServletRequest request) {
    Json json = new Json();
    try {
      HashMap<String, String> group = ParamUtils.getFilterStringParams(request);
      dataSync.createGroup(group);
      json.setSuccess(true);
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg(e.getMessage());
    }

    return json;
  }

  @RequestMapping("/editGroup")
  public ModelAndView editGroup(int id) {
    ModelAndView view = new ModelAndView("jsp/system/datasync/editGroup");
    Map<String, Object> group = dataSync.getGroup(id);
    view.addObject("group", group);
    return view;
  }

  @RequestMapping("/updateGroup")
  @ResponseBody
  public Json updateGroup(HttpServletRequest request) {
    Json json = new Json();
    try {
      HashMap<String, String> group = ParamUtils.getFilterStringParams(request);
      dataSync.updateGroup(group);
      json.setSuccess(true);
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg(e.getMessage());
    }

    return json;
  }

  @RequestMapping("/deleteGroup")
  @ResponseBody
  public Json deleteGroup(int id) {
    Json json = new Json();
    try {
      dataSync.deleteGroup(id);
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  @RequestMapping("/permGroup")
  public ModelAndView permGroup(int id) {
    ModelAndView view = new ModelAndView("jsp/system/datasync/permGroup");
    List<Map<String, Object>> channelList = dataSync.getGroupPermChannel(id);
    view.addObject("gid", id);
    view.addObject("channelList", channelList);
    return view;
  }

  @RequestMapping("/setPermGroup")
  @ResponseBody
  public int setPermGroup(HttpServletRequest request) {
    HashMap<String, String> group = ParamUtils.getFilterStringParams(request);
    int ret = 0;
    ret = dataSync.setGroupPerm(group);
    return ret;
  }

  @RequestMapping("/getGroupSelect")
  @ResponseBody
  public List<Map<String, Object>> getGroupSelect() {
    return dataSync.getGroupSelect();
  }

  @RequestMapping("/user")
  public String user() {
    return "jsp/system/datasync/listUser";
  }

  @RequestMapping("/getUser")
  @ResponseBody
  public DataGrid getUser(PageHelper ph) {
    return dataSync.listUser(ph);
  }

  @RequestMapping("/addUser")
  public ModelAndView addUser() {
    ModelAndView view = new ModelAndView("jsp/system/datasync/addUser");
    return view;
  }

  @RequestMapping("/saveUser")
  @ResponseBody
  public Json saveUser(HttpServletRequest request) {
    Json json = new Json();
    try {
      HashMap<String, String> user = ParamUtils.getFilterStringParams(request);
      dataSync.createUser(user);
      json.setSuccess(true);
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg(e.getMessage());
    }

    return json;
  }

  @RequestMapping("/edUser")
  public ModelAndView editUser(int id) {
    ModelAndView view = new ModelAndView("jsp/system/datasync/editUser");
    Map<String, Object> user = dataSync.getUser(id);
    view.addObject("user", user);
    return view;
  }

  @RequestMapping("/updateUser")
  @ResponseBody
  public Json updateUser(HttpServletRequest request) {
    Json json = new Json();
    try {
      HashMap<String, String> user = ParamUtils.getFilterStringParams(request);
      dataSync.updateUser(user);
      json.setSuccess(true);
    } catch (Exception e) {
      e.printStackTrace();
      json.setMsg(e.getMessage());
    }

    return json;
  }

  @RequestMapping("/deleteUser")
  @ResponseBody
  public Json deleteUser(int id) {
    Json json = new Json();
    try {
      dataSync.deleteUser(id);
      json.setSuccess(true);
    } catch (Exception e) {
      json.setMsg(e.getMessage());
    }

    return json;
  }

  @RequestMapping("/permUser")
  public ModelAndView permUser(int id) {
    ModelAndView view = new ModelAndView("jsp/system/datasync/permUser");
    List<Map<String, Object>> channelList = dataSync.getUserPermChannel(id);
    view.addObject("uid", id);
    view.addObject("channelList", channelList);
    return view;
  }

  @RequestMapping("/setPermUser")
  @ResponseBody
  public int setPermUser(HttpServletRequest request) {
    HashMap<String, String> user = ParamUtils.getFilterStringParams(request);
    int ret = 0;
    ret = dataSync.setUserPerm(user);
    return ret;
  }

  @RequestMapping("/listFileporg")
  public String listFileporg() {
    return "jsp/system/datasync/listFileprog";
  }

  @RequestMapping("/getFileporgList")
  @ResponseBody
  public DataGrid getFileporgList(PageHelper ph, HttpServletRequest request) {
    int channelId = ParamUtils.getIntParameter(request, "channelId", 0);
    String start = ParamUtils.getParameter(request, "startValidDate", "");
    String end = ParamUtils.getParameter(request, "endValidDate", "");
    return dataSync.listFileprog(channelId, start, end, ph);
  }

  @RequestMapping("/listFileporged")
  public String listFileporged() {
    return "jsp/system/datasync/listFileproged";
  }
  
  @RequestMapping("/getFileporgedList")
  @ResponseBody
  public DataGrid getFileporgedList(PageHelper ph, HttpServletRequest request) {
    String start = ParamUtils.getParameter(request, "startValidDate", "");
    String end = ParamUtils.getParameter(request, "endValidDate", "");
    return dataSync.listFileproged(start, end, ph);
  }

  @RequestMapping("/showFileprogFile")
  public ModelAndView showFileprogFile(HttpServletRequest request) {
    int fileprogId = ParamUtils.getIntParameter(request, "fileporgId", 0);
    ModelAndView view = new ModelAndView("jsp/system/datasync/showFileprogFile");
    List<Map<String, Object>> fileList = dataSync.listFileprogFile(fileprogId);
    view.addObject("fileList", fileList);
    return view;
  }

  @RequestMapping("/getFileporgProcess")
  @ResponseBody
  public String getFileporgProcess(HttpServletRequest request) {
    String fileprogIds = ParamUtils.getParameter(request, "fileprogIds", "");
    String info = "";
    List<Map<String, Object>> list = dataSync.listFileprogByIds(fileprogIds);
    if (list != null && list.size() > 0) {
      for (Map<String, Object> fileporg : list) {
        String sch = (String) fileporg.get("SCH_DATA_STR");
        String strDate = (String) fileporg.get("CUR_SEND_END_TIME_STR");
        if (StringUtils.isBlank(strDate))
          strDate = "";
        long id = (Long) fileporg.get("ID");
        info += id + "|" + sch + "|" + strDate;
        info += ";";
      }
      if (info.length() > 1)
        info = info.substring(0, info.length() - 1);
    }

    return info;
  }
  
  
  @RequestMapping("/deleteFileprog")
@ResponseBody
public Json deleteFileprog(HttpServletRequest request) {
 Json json = new Json();
 int[] ids = ParamUtils.getIntParameters(request, "id", 0);
 try {
   dataSync.deleteFileprog(ids);
   json.setMsg("删除成功！");
   json.setSuccess(true);
 } catch (Exception e) {
   json.setMsg(e.getMessage());
 }

 return json;
}

}
