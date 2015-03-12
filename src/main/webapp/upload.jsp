<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  		<script type="text/javascript" src="<%=request.getContextPath() %>/jslib/jquery-2.0.0.js"></script>
		<script src="<%=request.getContextPath() %>/jslib/jquery.ocupload.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=request.getContextPath() %>/jslib/jquery.bpopup.min.js" type="text/javascript" charset="utf-8"></script>
  		
  <body style="font-size: 12px;">
   <script type="text/javascript">
   $(function() {
		
		//点击导入按钮事件(******)
		$("#btnImportFromFile").upload({
	        action: '${pageContext.request.contextPath}/coursewareController/uploadFile.html',
	        name: 'file',
	        iframeName: 'ImportFromFile',
	        params: {},
	        onSelect: function (self, element) {
	            this.autoSubmit = false;
				this.submit();
	        },
	        onSubmit: function (self, element) {
	        },
	        onComplete: function (data, self, element) {
	        	var dataObject =  eval('(' + data + ')');;
				alert(dataObject.actionMessage);
	        }
	    });
		
		
	});

		</script>
		
		<input id="btnImportFromFile" class="option_btn_g" type="button" value="上&nbsp;&nbsp;&nbsp;传">
  </body>
</html>
