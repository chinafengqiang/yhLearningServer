package com.smlearning.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.smlearning.infrastructure.utils.ParamUtils;
import com.smlearning.web.controller.BaseController;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/api")
public class MainApi extends BaseController {
    @Autowired
    private IApi apiService;

    @RequestMapping("/getBookCategory")
    @ResponseBody
    public HashMap<String, Object> getBookCategory(HttpServletRequest request) {
        int classId = ParamUtils.getIntParameter(request, "classId", 0);
        List<HashMap<String, Object>> resList = apiService
                .getBookCategory(classId);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("bookCategoryList", resList);
        return resMap;
    }

    @RequestMapping("/getBookPart")
    @ResponseBody
    public HashMap<String, Object> getBookPart(HttpServletRequest request) {
        int classId = ParamUtils.getIntParameter(request, "classId", 0);
        int categoryId = ParamUtils.getIntParameter(request, "categoryId", 0);
        List<HashMap<String, Object>> resList = apiService.getBookPart(classId,
                categoryId);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("bookPartList", resList);
        return resMap;
    }

    @RequestMapping("/getBookChapter")
    @ResponseBody
    public HashMap<String, Object> getBookChapter(HttpServletRequest request) {
        int pid = ParamUtils.getIntParameter(request, "pid", 0);
        int partId = ParamUtils.getIntParameter(request, "partId", 0);
        int plevel = ParamUtils.getIntParameter(request, "plevel", -1);
        List<HashMap<String, Object>> resList = apiService.getBookChapter(pid,
                partId, plevel);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("bookChapterList", resList);
        return resMap;
    }

    @RequestMapping("/getBookResCategory")
    @ResponseBody
    public HashMap<String, Object> getBookResCategory(HttpServletRequest request) {
        int partId = ParamUtils.getIntParameter(request, "partId", 0);
        int plevel = ParamUtils.getIntParameter(request, "plevel", -1);
        int type = ParamUtils.getIntParameter(request, "type", 0);
        List<HashMap<String, Object>> resList = apiService.getBookResCategory(
                partId, plevel, type);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("bookChapterList", resList);
        return resMap;
    }

    @RequestMapping("/getBookRes")
    @ResponseBody
    public HashMap<String, Object> getBookRes(HttpServletRequest request) {
        int partId = ParamUtils.getIntParameter(request, "partId", 0);
        int categoryId = ParamUtils.getIntParameter(request, "categoryId", 0);
        List<HashMap<String, Object>> resList = apiService.getBookRes(partId,
                categoryId);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("bookResList", resList);
        return resMap;
    }

    @RequestMapping("/updateUserPass")
    @ResponseBody
    public HashMap<String, Integer> updateUserPass(HttpServletRequest request) {
        int userId = ParamUtils.getIntParameter(request, "userId", 0);
        String oldPass = ParamUtils.getParameter(request, "oldPass", "");
        String newPass = ParamUtils.getParameter(request, "newPass", "");
        HashMap<String, Integer> resMap = apiService.updateUserPass(userId,
                oldPass, newPass);
        return resMap;
    }

    @RequestMapping("/getVideoRes")
    @ResponseBody
    public HashMap<String, Object> getVideoRes(HttpServletRequest request) {
        int partId = ParamUtils.getIntParameter(request, "partId", 0);
        int categoryId = ParamUtils.getIntParameter(request, "categoryId", 0);
        List<HashMap<String, Object>> resList = apiService.getVideoRes(partId,
                categoryId);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("videoResList", resList);
        return resMap;
    }

    @RequestMapping("/searchBookRes")
    @ResponseBody
    public HashMap<String, Object> searchBookRes(HttpServletRequest request) {
        String value = ParamUtils.getParameter(request, "value", "");
        int classId = ParamUtils.getIntParameter(request, "classId", 0);
        try {
            value = java.net.URLDecoder.decode(value, "utf-8");
        } catch (Exception e) {
            // TODO: handle exception
        }
        List<HashMap<String, Object>> resList = apiService.searchBookRes(
                classId, value);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("bookResList", resList);
        return resMap;
    }

    @RequestMapping("/searchVideoRes")
    @ResponseBody
    public HashMap<String, Object> searchVideoRes(HttpServletRequest request) {
        String value = ParamUtils.getParameter(request, "value", "");
        int classId = ParamUtils.getIntParameter(request, "classId", 0);
        try {
            value = java.net.URLDecoder.decode(value, "utf-8");
        } catch (Exception e) {
            // TODO: handle exception
        }
        List<HashMap<String, Object>> resList = apiService.searchVideoRes(
                classId, value);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("videoResList", resList);
        return resMap;
    }

    @RequestMapping("/getClassTearch")
    @ResponseBody
    public HashMap<String, Object> getClassTearch(HttpServletRequest request) {
        int classId = ParamUtils.getIntParameter(request, "classId", 0);
        List<HashMap<String, Object>> resList = apiService
                .getClassTearch(classId);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("tearchResList", resList);
        return resMap;
    }

    @RequestMapping("/saveMessage")
    @ResponseBody
    public int saveMessage(HttpServletRequest request,
            MultipartHttpServletRequest multipartRequest) {
        String msg = ParamUtils.getParameter(request, "msg", "");
        long mTime = ParamUtils.getLongParameter(request, "mTime", 0);
        int src = ParamUtils.getIntParameter(request, "src", 0);
        String objectName =  ParamUtils.getParameter(request, "objectName", "");
        String srcName =  ParamUtils.getParameter(request, "srcName", "");
        int classId = ParamUtils.getIntParameter(request, "classId", 0);
        String localImagePath =  ParamUtils.getParameter(request, "localImagePath", "");
        try {
            String imagePath = "";
            String realPath = multipartRequest.getSession()
                    .getServletContext()
                    .getRealPath("/uploadFile/pic");
            
            for (Iterator<?> it = multipartRequest.getFileNames(); it.hasNext();) {
                String key = (String) it.next();
                MultipartFile orderFile = multipartRequest.getFile(key);
                if (orderFile.getOriginalFilename().length() > 0) {
                    FileUtils
                            .copyInputStreamToFile(
                                    orderFile.getInputStream(),
                                    new File(realPath, orderFile
                                            .getOriginalFilename()));
                    imagePath = "/uploadFile/pic/"+orderFile
                        .getOriginalFilename();
                }

                
            }
            
            HashMap<String,Object> msgMap= new HashMap<String, Object>();
            msgMap.put("MESSAGE", msg);
            msgMap.put("M_TIME", new Date(mTime));
            msgMap.put("SRC", src);
            msgMap.put("SRC_NAME", srcName);
            msgMap.put("OBJECT_NAME", objectName);
            msgMap.put("IMAGE_PATH",imagePath);
            msgMap.put("CLASS_ID",classId);
            msgMap.put("LOCAL_IMAGE_PATH",localImagePath);
            msgMap.put("IS_VALID",1);
            return apiService.saveOnlineMessage(msgMap);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }
    
    
    @RequestMapping("/getOnlineMessage")
    @ResponseBody
    public HashMap<String, Object> getOnlineMessage(HttpServletRequest request) {
        int classId = ParamUtils.getIntParameter(request, "classId", 0);
        int userId = ParamUtils.getIntParameter(request, "userId",0);
        int offset = ParamUtils.getIntParameter(request, "offset",0);
        int pagesize = ParamUtils.getIntParameter(request, "pagesize",5);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        apiService.getOnlineMessage(userId, classId, offset, pagesize, resMap);
        return resMap;
    }
    
    
    @RequestMapping("/saveReplyMessage")
    @ResponseBody
    public int saveReplyMessage(HttpServletRequest request,
            MultipartHttpServletRequest multipartRequest) {
        String msg = ParamUtils.getParameter(request, "msg", "");
        long mTime = ParamUtils.getLongParameter(request, "mTime", 0);
        long msgId = ParamUtils.getLongParameter(request, "msgId", 0);
        int rpUid = ParamUtils.getIntParameter(request, "rpUid", 0);
        String rpName = ParamUtils.getParameter(request, "rpName","");
        try {
            String imagePath = "";
            String realPath = multipartRequest.getSession()
                    .getServletContext()
                    .getRealPath("/uploadFile/pic");
            
            for (Iterator<?> it = multipartRequest.getFileNames(); it.hasNext();) {
                String key = (String) it.next();
                MultipartFile orderFile = multipartRequest.getFile(key);
                if (orderFile.getOriginalFilename().length() > 0) {
                    FileUtils
                            .copyInputStreamToFile(
                                    orderFile.getInputStream(),
                                    new File(realPath, orderFile
                                            .getOriginalFilename()));
                    imagePath = "/uploadFile/pic/"+orderFile
                            .getOriginalFilename();
                }

                
            }
            
            HashMap<String,Object> msgMap= new HashMap<String, Object>();
            msgMap.put("RP_MSG", msg);
            msgMap.put("RP_TIME", new Date(mTime));
            msgMap.put("MSG_ID", msgId);
            msgMap.put("RP_UID", rpUid);
            msgMap.put("RP_UNAME", rpName);
            msgMap.put("RP_IMAGE_PATH",imagePath);
            msgMap.put("IS_VALID",1);
            return apiService.saveOnlineReplyMessage(msgMap);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    @RequestMapping("/getOnlineReplyMessage")
    @ResponseBody
    public HashMap<String, Object> getOnlineReplyMessage(HttpServletRequest request) {
        int msgId = ParamUtils.getIntParameter(request, "msgId", 0);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        List<HashMap<String,Object>> resList = apiService.getOnlineReplyMessage(msgId);
        resMap.put("replyList",resList);
        return resMap;
    }
    
    @RequestMapping("/getLessonPlan")
    @ResponseBody
    public HashMap<String, Object> getLessonPlan(HttpServletRequest request) {
        int lessonId = ParamUtils.getIntParameter(request, "lessonId", 0);
        int lessonNum = ParamUtils.getIntParameter(request, "lessonNum", 0);
        int lessonWeek = ParamUtils.getIntParameter(request, "lessonWeek", 0);
        boolean isTemp = ParamUtils.getBooleanParameter(request,"isTemp",false);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        apiService.getLessonPlan(lessonId, lessonNum,lessonWeek,resMap,isTemp);
        return resMap;
    }
    
    
    @RequestMapping("/getLessonMessage")
    @ResponseBody
    public HashMap<String, Object> getLessonMessage(HttpServletRequest request) {
        int classId = ParamUtils.getIntParameter(request, "classId", 0);
        List<HashMap<String,Object>> resList = apiService.getLessonMessage(classId);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("lessonMessageList",resList);
        return resMap;
    }
    
}
