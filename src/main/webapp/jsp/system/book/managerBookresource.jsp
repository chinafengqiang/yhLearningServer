<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>目录管理</title>
<jsp:include page="../../../incApp.jsp"></jsp:include>
<style type="text/css">

.book{ width:100%;
}

.book table td{  
	
}
</style>
<script type="text/javascript">
var partId = '${partId}';
var partName = '${name}';
var encPartName = encodeURI(partName);
var pid = -1;
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

	initTree();

});

function initTree(){
	   $('#tree').tree({
	    	checkbox: false,
	    	url: "${pageContext.request.contextPath}/bookController/getChapterTreeJson.html?partId="+partId+"&rootName="+encPartName,
	    	animate:true,
	    	lines:true,
	    	onClick:function(node){
	    		var isAdd = node.attributes.isAddRes;
	    		if(isAdd == 2){
	    			initResData();
	    			pid = -1;
	    		}else{
	    			Open(node.id,node.text);
	    		}
	    		
	    	},
	    	onBeforeExpand:function(node,param){                       
	    		//$('#taskTree').tree('options').url = ctx + "/rims/rescue/loadRescueTaskTreeRootNodes.do?parentId="+node.id;                
	    	}
	    });
}

function Open(id,name) {
	pid = id;
	
}

function treeLoad(){
	initTree();
}

function initResData(){
	alert("init res");
}

//添加
function addFun() {
	if(pid < 0){
		alert("请选择下方目录进行添加");
		return;
	}
	var url = '${pageContext.request.contextPath}/bookController/addBookChapter.html?partId='+partId+"&pid="+pid;
	parent.$.modalDialog({
				title : '添加目录',
				width : 400,
				height : 300,
				href : url,
				buttons : [ {
					text : '添加',
					handler : function() {
						var f = parent.$.modalDialog.handler.find('#form');
						f.submit();
					}
				} ]
			});
}

</script>
</head>
<body class="easyui-layout">
    <div region="west"  title="目录管理" style="width: 200px;" id="west">
    
    	<div style="text-align: center;padding-top: 10px;">
    		<button onclick="addFun();">添加</button>&nbsp;&nbsp;
    		<button>编辑</button>&nbsp;&nbsp;
    		<button>删除</button>
    	</div>
    
        <div id="nav" class="easyui-accordion" fit="true" border="false">
			 <ul id="tree" style="padding-top: 10px;"></ul>
        </div>
    </div>

<div region="center">

	<div data-options="region:'center',border:false" id="flList">
		<table id="dataGrid">
			<h2 id="titleId" align="center" >请点击左侧课堂板书、补充资料、课后练习及其他获取资源！</h2>
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