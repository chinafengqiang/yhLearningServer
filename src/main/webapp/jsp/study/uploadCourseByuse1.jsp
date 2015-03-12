<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery.uploadify/jquery-1.4.2.min.js"></script>
<link href="<%=request.getContextPath()%>/jquery.uploadify/uploadify.css" rel="stylesheet" type="text/css"></link>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery.uploadify/swfobject.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery.uploadify/jquery.uploadify.v2.1.4.min.js"></script>
<style type="text/css">
body {
 font: 14px/16px Arial, Helvetica, sans-serif,text-align:center;
}
#fileQueue {
 width: 400px;
 height: 300px;
 overflow: auto;
 margin-bottom: 10px;
}
　　 a:link { text-decoration: none;color: black}
　　 a:active { text-decoration:blink}
　　 a:hover { text-decoration:underline;color: red} 
　　 a:visited { text-decoration: none;color: green}

#container{
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$("#fileInput").uploadify({
		'uploader'       : '<%=request.getContextPath()%>/jquery.uploadify/uploadify.swf',
		'script'         : '/coursewareController/uploadFile.html',
		'cancelImg'      : '<%=request.getContextPath()%>/jquery.uploadify/cancel.png',
		'folder'         : 'uploadFile',
		'fileDataName': 'fileInput', 
		'queueID'        : 'fileQueue',
		'fileExt'        : '*.3gp', //允许文件上传类型,和fileDesc一起使用.
		'fileDesc'       : '*.3gp',  //将不允许文件类型,不在浏览对话框的出现.
		'auto'           : true,
		'multi'          : false,
		'sizeLimit'   : 100000000,
		'buttonText': 'Browse',//按钮上的文字   
		'onComplete':function(event,queueId,fileObj,response,data){
			$('#result1').text("成功上传文件" + $('#result').text() + ''+response+'');//在页面上显示文件相对路径
			$('#result').text($('#result').text() + ''+response+'');
		},
		
		'onError' : function(event, queueID, fileObj,errorObj){
  			 alert(errorObj.type + "Error:" + errorObj.info);
         }
	});
});
function getURl(){
	var path = $('#result').text();
	window.returnValue = path;
	}

</script>
</head>
<body>

<div id="container">
	<input type="file" name="fileInput" id="fileInput" />
	<img src="<%=request.getContextPath()%>/images/tqtm.gif"  style="cursor:pointer;" onclick="getURl()">
	(支持30M文件)
	</div>
	<div style="display:none">
	<div id="result"></div><!--显示结果-->   
	</div>
	<div id="result1"></div>
     <div id="fileQueue"></div>  
</body>
</html>