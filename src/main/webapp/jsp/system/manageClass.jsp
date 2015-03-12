<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>班级管理</title>
<jsp:include page="../../incApp.jsp"></jsp:include>
<script type="text/javascript"></script>
<script type="text/javascript">
var dataGrid;
var gradeIdGlob = 0;
$(function() {
	getData("");
});

function getData(url){
	var defaultUrl = "${pageContext.request.contextPath}/sysKeyController/getClassList.html";
	if(url != ""){
		defaultUrl = url; 
	}
	dataGrid = $('#dataGrid').datagrid({
		url : defaultUrl,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'name',
		loadMsg : '数据装载中......',
		sortOrder : 'asc',
		checkOnSelect : false,
		selectOnCheck : false,
		rownumbers : true,
		nowrap : false,
		frozenColumns : [ [ {
			field : 'id',
			title : '编号',
			width : 150,
			hidden : true
		}, {
			field : 'name',
			title : '班级名称',
			width : 150,
			sortable : true
		} ] ],
		columns : [ [  {
			field : 'created_time',
			title : '创建时间',
			width : 150,
			sortable : true
		}, {
			field : 'action',
			title : '操作',
			width : 100,
			formatter : function(value, row, index) {
				  var e = '<a href="#" mce_href="#" onclick="editFun(\''+ row.id + '\')">编辑</a> ';  
	              var r = '<a href="#" mce_href="#" onclick="deleteFun(\''+ row.id +'\')">删除</a> ';  
	              return e+r;   
			}
		} ] ],
		toolbar : '#toolbar',
		onRowContextMenu : function(e, rowIndex, rowData) {
			e.preventDefault();
			$(this).datagrid('unselectAll').datagrid('uncheckAll');
			$(this).datagrid('selectRow', rowIndex);
			$('#menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
		

	});
}

//添加
function addFun() {
	if(gradeIdGlob == 0){
		alert("请选择相应的年级进行添加班级！");
		return false;
	}
	parent.$.modalDialog({
		title : '班级信息',
		width : 400,
		height : 300,
		href : '${pageContext.request.contextPath}/sysKeyController/addPageClass.html?gradeId='+gradeIdGlob,
		buttons : [ {
			text : '添加',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
				var f = parent.$.modalDialog.handler.find('#form');
				f.submit();
			}
		} ]
	});
}

//编辑
function editFun(id) {
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	} else {
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
	}
	parent.$.modalDialog({
		title : '编辑班级信息',
		width : 500,
		height : 300,
		href : '${pageContext.request.contextPath}/sysKeyController/editPageClass.html?id=' + id,
		buttons : [ {
			text : '编辑',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
				var f = parent.$.modalDialog.handler.find('#form');
				f.submit();
			}
		} ]
	});
}

//删除
function deleteFun(id) {
	if (id == undefined) {//点击右键菜单才会触发这个
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	} else {//点击操作里面的删除图标会触发这个
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
	}
	parent.$.messager.confirm('询问', '您是否要删除当前信息？', function(b) {
		if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${pageContext.request.contextPath}/sysKeyController/removeClass.html', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
		}
	});
}

//启用
function openFun(id) {
	if (id == undefined) {//点击右键菜单才会触发这个
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	} else {//点击操作里面的删除图标会触发这个
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
	}
	parent.$.messager.confirm('询问', '您是否要启用当前信息？', function(b) {
		if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${pageContext.request.contextPath}/managerController/openmanager.html', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
		}
	});
}

//停用
function closeFun(id) {
	if (id == undefined) {//点击右键菜单才会触发这个
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	} else {//点击操作里面的删除图标会触发这个
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
	}
	parent.$.messager.confirm('询问', '您是否要停用当前信息？', function(b) {
		if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${pageContext.request.contextPath}/managerController/closemanager.html', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
		}
	});
}

function searchFun() {
	
	dataGrid.datagrid('load', serializeObject($('#searchForm')));
}
function cleanFun() {
	$('#searchForm input').val('');
	dataGrid.datagrid('load', {});
}


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
    		Open(node.id);
    	},
    	onBeforeExpand:function(node,param){                       
    		//$('#taskTree').tree('options').url = ctx + "/rims/rescue/loadRescueTaskTreeRootNodes.do?parentId="+node.id;                
    	}
    });
    
    function Open(id) {
    	gradeIdGlob = id;
    	var url = "${pageContext.request.contextPath}/sysKeyController/getClassList.html?gradeId="+id;
    	getData(url);
    }
});
</script>
</head>
<body class="easyui-layout">
    <div region="west"  title="年级导航" style="width: 200px;" id="west">
        <div id="nav" class="easyui-accordion" fit="true" border="false">
			 <ul id="tree" style="padding-top: 10px;"></ul>
        </div>
    </div>

<div region="center">

	<div data-options="region:'center',border:false">
		<table id="dataGrid"></table>
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