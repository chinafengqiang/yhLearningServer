<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统登录</title>
<jsp:include page="inc.jsp"></jsp:include>
<script type="text/javascript">
	function login() {
		
		if ($('#mainForm').form('validate')) {
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			$.post('${pageContext.request.contextPath}/managerController/login.html', $('#mainForm').serialize(), function(result) {
				if (result.success) {
					$('#registerDialog').dialog('close');
					$.messager.show({
						title : '提示',
						msg : result.msg,
						showType : 'slide'
					});
					window.location.href = "/jsp/index.jsp";
					$('#loginForm').form('load', result.obj);
				} else {
					$.messager.alert('错误', result.msg, 'error');
					$("#password").val("");
					$("#userName").val("");
				}
				$.messager.progress('close');
			}, "JSON");
		}
	}
	
	
	function downloadPdf()
	{
		location.href="${pageContext.request.contextPath}/userController/downloadpdfApk.html";
	}
	
	function downloadSoft()
	{
		location.href="${pageContext.request.contextPath}/userController/downloadApk.html";
	}
	
	//获得回车事件的方法
	function BindEnter(obj)
	{
	    //使用document.getElementById获取到按钮对象
	    var button = document.getElementById('btnLogin');
	    if(obj.keyCode == 13)
	        {
	            button.click();
	            obj.returnValue = false;
	        }
	}	

</script>
</head>
<body class="login_bg" onkeydown="BindEnter(event)">

    <div style="height: 60px; width: 300px;">
    </div>
    <div class="login_right" id="login">
    <form  name="mainForm" id="mainForm" action="" method="post">
        <table width="180" align="center" cellspacing="0">
            <tr>
                <td height="36" colspan="2" align="left">用 户：<input name="name" type="text" id="userName" class="login_input174" data-options="required:true" />
                </td>
            </tr>
            <tr align="left">
                <td height="36" colspan="2">密 码：<input name="password" type="password" id="password" class="login_input174" data-options="required:true"/>
                </td>
            </tr>
            <tr align="left">
                <td width="280" height="27" colspan="2" style="color: #FF0000"></td>
            </tr>
            <tr align="left">
                <td height="67" colspan="2" align="center" valign="bottom">
                    <input name="btnLogin" id="btnLogin" class="login_sub" alt="登录" value=""
                        style="border-width: 0px;cursor: pointer;" onclick="login();" />
                </td>
            </tr>
        </table>
      
        </form>
    </div>
  <table width="180" align="center" cellspacing="0">
        <tr>
               <td height="36" colspan="2" align="left">
               <a onclick="downloadPdf();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'"><font size="26">PDF下载</font></a>
               </td>
            </tr>
             <tr>
             <td height="36" colspan="2" align="left">
             <a onclick="downloadSoft();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'"><font size="26">育恒平台下载</font></a>
        </td>
       </tr>
        </table>
</body>
</html>