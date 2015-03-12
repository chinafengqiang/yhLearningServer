<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学习管理平台</title>
<jsp:include page="../inc.jsp"></jsp:include>
</head>
 <script type="text/javascript">
        $.ajaxSetup({
            cache: false //关闭AJAX相应的缓存 
        });

        var _menus = {
            "menus": [
             {
                 "menuid": "1", "icon": "icon-sys", "menuname": "系统管理",
                 "menus": [
                         { "menuid": "11", "menuname": "单位管理", "icon": "icon-search", "url":  "${pageContext.request.contextPath}/organController/addselfpage.html" },
                         { "menuid": "12", "menuname": "年级管理", "icon": "icon-search", "url": "${pageContext.request.contextPath}/sysGradeController/gradeList.html" },
                         { "menuid": "13", "menuname": "班级管理", "icon": "icon-search", "url": "${pageContext.request.contextPath}/sysKeyController/manageClass.html" },
                         { "menuid": "14", "menuname": "科目管理", "icon": "icon-search", "url": "${pageContext.request.contextPath}/sysKeyController/manageSubject.html" },
                         { "menuid": "15", "menuname": "管理员管理", "icon": "icon-search", "url": "${pageContext.request.contextPath}/managerController/managerAdmin.html" },
                         { "menuid": "16", "menuname": "教师管理", "icon": "icon-search", "url": "${pageContext.request.contextPath}/managerController/managemanager.html" },
                         { "menuid": "17", "menuname": "学员管理", "icon": "icon-search", "url": "${pageContext.request.contextPath}/userController/manageuser.html" },
                         { "menuid": "18", "menuname": "公告通知", "icon": "icon-search", "url": "${pageContext.request.contextPath}/sysMessageController/managesysmessage.html" },
                         { "menuid": "19", "menuname": "数据字典", "icon": "icon-search", "url": "${pageContext.request.contextPath}/sysKeyController/managesyskey.html" }
                        
                 ]
             },
             {
                 "menuid": "2", "icon": "icon-sys", "menuname": "课程管理",
                 "menus": [
						 { "menuid": "21", "menuname": "课件分类管理", "icon": "icon-search", "url": "${pageContext.request.contextPath}/coursewareController/createCoursewareList.html" },
                         { "menuid": "22", "menuname": "课件管理", "icon": "icon-search", "url": "${pageContext.request.contextPath}/courseController/managecourse.html" },
						 { "menuid": "23", "menuname": "课程教学计划", "icon": "icon-search", "url": "${pageContext.request.contextPath}/courseController/manageLesson.html" },
						 { "menuid": "24", "menuname": "课程进度管理", "icon": "icon-search", "url": "${pageContext.request.contextPath}/courseController/managecourseplan.html" },
						 { "menuid": "25", "menuname": "课程表管理", "icon": "icon-search", "url": "${pageContext.request.contextPath}/courseController/managecoursetable.html" },
						 { "menuid": "26", "menuname": "课堂板书管理", "icon": "icon-search", "url": "${pageContext.request.contextPath}/courseController/manageClassBook.html" }
                 ]
             },
             {
                 "menuid": "3", "icon": "icon-sys", "menuname": "资源管理",
                 "menus": [
                          { "menuid": "31", "menuname": "电子书分类管理", "icon": "icon-search", "url":"${pageContext.request.contextPath}/coursewareController/createBookNodeList.html" },
						 { "menuid": "32", "menuname": "电子书资料", "icon": "icon-search", "url":"${pageContext.request.contextPath}/coursewareController/manageEbooks.html" },
						 { "menuid": "33", "menuname": "在线交流", "icon": "icon-search", "url":"${pageContext.request.contextPath}/sysMessageController/manageforum.html" }
                 ]
             },         
             {
                 "menuid": "4", "icon": "icon-sys", "menuname": "同步管理",
                 "menus": [
						 { "menuid": "41", "menuname": "同步频道管理", "icon": "icon-search", "url":"${pageContext.request.contextPath}/dataSyncController/channel.html" },
						 { "menuid": "42", "menuname": "同步组管理", "icon": "icon-search", "url":"${pageContext.request.contextPath}/dataSyncController/group.html" },
						 { "menuid": "43", "menuname": "同步用户管理", "icon": "icon-search", "url":"${pageContext.request.contextPath}/dataSyncController/user.html" },
						 { "menuid": "44", "menuname": "查看文件投递", "icon": "icon-search", "url":"${pageContext.request.contextPath}/dataSyncController/listFileporg.html" },
						 { "menuid": "45", "menuname": "已完成投递文件", "icon": "icon-search", "url":"${pageContext.request.contextPath}/dataSyncController/listFileporged.html" }
                 ]
             },
            ]
        };

        function openActMgmt() {
            addTab("账号管理", "account.html", "icon-search");
        }
        
        
    </script>
<body class="easyui-layout">
    <div region="north" border="false" style="overflow: hidden; height: 30px; background: #7f99be repeat-x center 50%; line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <table width="100%">
            <tr>
                <td align="left"><span style="font-size: medium; font-weight: bold;">育恒教育 — 学习管理系统</span></td>
                <td align="right">您好，管理员，
     <a href="javascript:void(0)" style="color: #FFFFFF" onclick="confirm_easyui('确认','是否确认退出系统？','<%=request.getContextPath() %>/managerController/loginout.html');">退出系统</a>
                </td>
            </tr>
        </table>
    </div>
    <div region="west" hide="true" split="true" title="导航菜单" style="width: 180px;" id="west">
        <div id="nav" class="easyui-accordion" fit="true" border="false">
            <!--  导航内容 -->
        </div>
    </div>
    <div region="center">
        <div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="欢迎使用" style="padding: 10px; overflow: hidden;" id="home" align="center">
                <iframe scrolling="auto" frameborder="0" src="" style="width: 100%; height: 85%;"
                    align="top"></iframe>
            </div>
        </div>
    </div>
    <div region="south" border="false" style="height: 30px; background: #A9FACD; padding: 10px;">
    </div>
</body>
</html>