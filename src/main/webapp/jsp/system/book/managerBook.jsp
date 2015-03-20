<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>班级管理</title>
<jsp:include page="../../../incApp.jsp"></jsp:include>
<style type="text/css">

.book{ width:100%;
}

.book table td{  
	
}
</style>
<script type="text/javascript">
var globalGrade=-1;
var globalGradeName;
$(function () {
    //动态菜单数据
    
    //实例化树形菜单
   /* $("#tree").tree({
        data : treeData,
        lines : true,
        onClick : function (node) {
        	Open(node.id);
        }
    });*/
    $('#tree').tree({
    	checkbox: false,
    	url: "${pageContext.request.contextPath}/sysGradeController/getGradeTreeJson.html",
    	animate:true,
    	lines:true,
    	onClick:function(node){
    		Open(node.id,node.text);
    	},
    	onBeforeExpand:function(node,param){                       
    		//$('#taskTree').tree('options').url = ctx + "/rims/rescue/loadRescueTaskTreeRootNodes.do?parentId="+node.id;                
    	}
    });
    

});

function Open(id,name) {
	globalGrade = id;
	globalGradeName = name;
	
	if(id > 0){
		var title = "为“"+name+"”添加教学资源";
		$("#titleId").html(title);
		$("#bookList").show();
	}else{
		alert("请选择有效的年级！");
		$("#titleId").html("请点击左侧年级进行教学资源的管理！");
		$("#bookList").hide();
	}
	
}

function gotofc(id,cname){
	var url = '${pageContext.request.contextPath}/bookController/managerBookpart.html?gradeId='+globalGrade+'&categoryId='+id;
	self.parent.addTab(globalGradeName+cname+'分册管理',url,'');
}
</script>
</head>
<body class="easyui-layout">
    <div region="west"  title="年级导航" style="width: 200px;" id="west">
        <div id="nav" class="easyui-accordion" fit="true" border="false">
			 <ul id="tree" style="padding-top: 10px;"></ul>
        </div>
    </div>

<div region="center">

	<div data-options="region:'center',border:false" id="flList">
		<table id="dataGrid">
			<h2 id="titleId" align="center" >请点击左侧年级进行教学资源的管理！</h2>
		</table>
		<table class="book" id="bookList" style="display: none;">
		  <tr align="center">
		  <td>
		  	<img onclick="gotofc(53,'语文')" title="点击进入分册管理"  src="${pageContext.request.contextPath}/images/53.png" style="width:130px;height: 189px;cursor: pointer;">
		  </td>
		  <td>
		  <img onclick="gotofc(60,'数学')" title="点击进入分册管理"  src="${pageContext.request.contextPath}/images/60.png" style="width:130px;height: 189px;cursor: pointer;">
		  </td>
		  <td>
		  <img onclick="gotofc(62,'英语')" title="点击进入分册管理"  src="${pageContext.request.contextPath}/images/62.png" style="width:130px;height: 189px;cursor: pointer;">
		  </td>
		  </tr>
		   <tr align="center">
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
		  </tr>
		  
		  <tr style="line-height: 50px;">
		  	<td colspan="3">&nbsp;</td>
		  </tr>
		  
		 <tr align="center">
		  <td>
		  	<img onclick="gotofc(64,'物理')" title="点击进入分册管理" src="${pageContext.request.contextPath}/images/64.png" style="width:130px;height: 189px;cursor: pointer;">
		  </td>
		  <td>
		  <img onclick="gotofc(68,'化学')" title="点击进入分册管理"  src="${pageContext.request.contextPath}/images/68.png" style="width:130px;height: 189px;cursor: pointer;">
		  </td>
		  <td>
		  <img onclick="gotofc(69,'生物')" title="点击进入分册管理" src="${pageContext.request.contextPath}/images/69.png" style="width:130px;height: 189px;cursor: pointer;">
		  </td>
		  </tr>
		   <tr align="center">
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
		  </tr>
		  
		   <tr style="line-height: 50px;">
		  	<td colspan="3">&nbsp;</td>
		  </tr>
		  
		 <tr align="center">
		  <td>
		  	<img onclick="gotofc(61,'地理')" title="点击进入分册管理" src="${pageContext.request.contextPath}/images/61.png" style="width:130px;height: 189px;cursor: pointer;">
		  </td>
		  <td>
		  <img onclick="gotofc(66,'历史')" title="点击进入分册管理" src="${pageContext.request.contextPath}/images/66.png" style="width:130px;height: 189px;cursor: pointer;">
		  </td>
		  <td>
		  <img onclick="gotofc(67,'政治')" title="点击进入分册管理" src="${pageContext.request.contextPath}/images/67.png" style="width:130px;height: 189px;cursor: pointer;">
		  </td>
		  </tr>
		   <tr align="center">
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
		  </tr>
		</table>
	</div>
</div>


<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<table>
						<tr>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="toolbar-btn"  onclick="addFun();">添加</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</div>



</body>
</html>