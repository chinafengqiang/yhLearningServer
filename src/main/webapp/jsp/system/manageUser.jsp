<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>学员管理</title>
<jsp:include page="../../incApp.jsp"></jsp:include>
<script type="text/javascript"></script>
<script type="text/javascript">
var dataGrid;
var classId;
$(function() {
	getData();
});

/*var queryData = {
		 startTime: START_TIME,
		 endTime: END_TIME,
		 name: name,
		 gradeId: grade_id,
		 category: category,
		 status: status
};
getData(queryData);*/
function getData(queryData){
	dataGrid = $('#dataGrid').datagrid({
		url : '${pageContext.request.contextPath}/userController/dataGrid.html',
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 10,
		queryParams:queryData, //查询条件
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'createdTime',
		loadMsg : '数据装载中......',
		sortOrder : 'asc',
		checkOnSelect : false,
		selectOnCheck : false,
		rownumbers : true,
		nowrap : false,
		frozenColumns : [ [ 
		 {field:'ck',checkbox:true},   
		 {
			field : 'id',
			title : '编号',
			width : 150,
			hidden : true
		}, {
			field : 'name',
			title : '登录帐号',
			width : 150,
			sortable : true
		} ] ],
		columns : [ [  {
			field : 'actualName',
			title : '姓名',
			width : 150,
			sortable : true
		},  {
			field : 'sex',
			title : '性别',
			width : 80,
			sortable : true
		}, {
			field : 'nation',
			title : '民族',
			width : 80
		},  {
			field : 'statusName',
			title : '状态',
			width : 80
		}, {
			field : 'action',
			title : '操作',
			width : 100,
			formatter : function(value, row, index) {
                var e = '<a href="#" mce_href="#" onclick="editFun(\''+ row.id + '\')">编辑</a> ';  
                var r = '<a href="#" mce_href="#" onclick="deleteFun(\''+ row.id +'\')">删除</a> ';  
        		var o = '<a href="#" mce_href="#" onclick="openFun(\''+ row.id + '\')">启用</a> ';  
        		var c = '<a href="#" mce_href="#" onclick="closeFun(\''+ row.id + '\')">停用</a> ';  
                return e+r+o+c;  
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
	if(!(classId > 0)){
		alert("请选择班级");
		return false;
	}
	parent.$.modalDialog({
		title : '学员信息',
		width : 800,
		height : 500,
		href : '${pageContext.request.contextPath}/userController/addPage.html?classId='+classId,
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
		title : '编辑学员信息',
		width : 800,
		height : 500,
		href : '${pageContext.request.contextPath}/userController/editPage.html?id=' + id,
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
				$.post('${pageContext.request.contextPath}/userController/removeuser.html', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}else {
						parent.$.messager.alert('错误', result.msg, 'error');
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
				$.post('${pageContext.request.contextPath}/userController/openuser.html', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}else {
						parent.$.messager.alert('错误', result.msg, 'error');
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
				$.post('${pageContext.request.contextPath}/userController/closeuser.html', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}else {
						parent.$.messager.alert('错误', result.msg, 'error');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
		}
	});
}


//重置密码
function resetFun(id) {
	if (id == undefined) {//点击右键菜单才会触发这个
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	} else {//点击操作里面的删除图标会触发这个
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
	}
	parent.$.messager.confirm('询问', '您是否要重置密码？', function(b) {
		if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${pageContext.request.contextPath}/userController/resetpassword.html', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}else {
						parent.$.messager.alert('错误', result.msg, 'error');
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

function importExcel(){
	if(!(classId > 0)){
		alert("请选择班级");
		return false;
	}
	parent.$.modalDialog({
		title : '基本信息',
		width : 500,
		height : 400,
		href : '${pageContext.request.contextPath}/userController/impUser.html?classId='+classId,
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

$(function () {
    //动态菜单数据
   
   
    $('#tree').tree({
    	checkbox: false,
    	url: "${pageContext.request.contextPath}/sysGradeController/getGradeAndClassTreeJson.html",
    	animate:true,
    	lines:true,
    	onClick:function(node){
    		var isLeaf = $('#tree').tree('isLeaf', node.target);
    		var parent = $('#tree').tree('getParent', node.target);
    		if(isLeaf && parent.id > 0){
    			classId = node.id;
    			Open(node.id);
    		}
    		else{
    			alert("请选择要查看的班级");
    		}
    	},
    	onBeforeExpand:function(node,param){                       
    		//$('#taskTree').tree('options').url = ctx + "/rims/rescue/loadRescueTaskTreeRootNodes.do?parentId="+node.id;                
    	}
    });
    
    function Open(id) {
	    	var queryData = {
	    			 classId: id
	    	};
	    	getData(queryData);
    }
});


function searchList(){
 	var queryData = {
 			name: $("#name").val(),
 			actualName:$("#actualName").val()
	};
	getData(queryData);
}

function deleteFun(){
	var deleteUrl = "${pageContext.request.contextPath}/userController/deleteUser.html";
	deleterow(deleteUrl,'dataGrid','id');
}

function setPassFun(){
	var url = "${pageContext.request.contextPath}/userController/setUserPass.html";
	setrow(url,'dataGrid','id');
}
</script>
</head>
<body class="easyui-layout">



<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<table>
						<tr>
							<td><div class="datagrid-btn-separator"></div></td>
							<td>
							<a onclick="importExcel();" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">导入</a>
							</td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td>
							<a onclick="addFun();" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">添加</a>
							</td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td>
							<a onclick="deleteFun();" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">删除</a>
							</td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td>
							<a onclick="setPassFun();" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">重置密码</a>
							</td>
							<td><div class="datagrid-btn-separator"></div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',border:false">
	<div class="search" style="height: 50px;">
		<div style="margin-left: 10px;">
			帐号：
			<input name="name" id="name"/>
			&nbsp;&nbsp;
			姓名： <input name="actualName" id="actualName"/>
			&nbsp;
			<a href="#" class="easyui-linkbutton" id="searchBtn" onclick="searchList();">查询</a>
		</div>

	</div>
		<table id="dataGrid"></table>
	</div>
    <div region="west"  title="年级导航" style="width: 200px;" id="west">
        <div id="nav" class="easyui-accordion" fit="true" border="false">
			 <ul id="tree" style="padding-top: 10px;"></ul>
        </div>
    </div>
</body>
</html>