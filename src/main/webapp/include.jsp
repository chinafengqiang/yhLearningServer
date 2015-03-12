<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>
<%String version = "20140801";%>

<%
Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
Cookie[] cookies = request.getCookies();
if (null != cookies) {
	for (Cookie cookie : cookies) {
		cookieMap.put(cookie.getName(), cookie);
	}
}
String easyuiTheme = "bootstrap";//指定如果用户未选择样式，那么初始化一个默认样式
if (cookieMap.containsKey("easyuiTheme")) {
	Cookie cookie = (Cookie) cookieMap.get("easyuiTheme");
	easyuiTheme = cookie.getValue();
}
%>

<script type="text/javascript">
var fq = fq || {};
fq.contextPath = '<%=contextPath%>';
fq.basePath = '<%=basePath%>';
fq.version = '<%=version%>';
fq.pixel_0 = '<%=contextPath%>/images/pixel_0.gif';//0像素的背景，一般用于占位
</script>

<%-- 引入my97日期时间控件 --%>
<script type="text/javascript" src="<%=contextPath%>/jslib/My97DatePicker4.8b3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<%-- 引入jQuery --%>
<script type="text/javascript" src="<%=contextPath%>/jslib/jquery-1.8.3.js" charset="utf-8"></script>

<%-- 引入jquery扩展 --%>
<script src="<%=contextPath%>/jslib/fqExtJquery.js" type="text/javascript" charset="utf-8"></script>

<%-- 引入public公共 --%>
<script type="text/javascript" src="<%=contextPath%>/jslib/public.js" charset="utf-8"></script>

<%-- 引入EasyUI --%>
<link id="easyuiTheme" rel="stylesheet" href="<%=contextPath%>/jslib/easyUI/themes/<%=easyuiTheme%>/easyui.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/jslib/easyUI/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/jslib/easyUI/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<%-- 引入EasyUI Portal插件 --%>

<%-- 引入easyui扩展 --%>
<script src="<%=contextPath%>/jslib/easyUI/fqExtEasyUI.js" type="text/javascript" charset="utf-8"></script>

<%-- 引入扩展图标 --%>
<link rel="stylesheet" href="<%=contextPath%>/jslib/easyUI/fqExtIcon.css" type="text/css">

<%-- 引入自定义样式 --%>
<link rel="stylesheet" href="<%=contextPath%>/jslib/easyUI/fqExtCss.css" type="text/css">

<%-- 引入javascript扩展 --%>
<script src="<%=contextPath%>/jslib/fqExtJavascript.js" type="text/javascript" charset="utf-8"></script>

<%-- 引入easyUI默认图标样式 --%>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/jslib/easyUI/themes/icon.css" />

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/styles/main.css" />
