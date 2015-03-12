<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>管理员管理</title>
<jsp:include page="../../incApp.jsp"></jsp:include>
<script type="text/javascript"></script>
<script type="text/javascript">
var dataGrid;
$(function() {
	getData();
});

function getData(queryData){
	dataGrid = $('#dataGrid').datagrid({
		url : '${pageContext.request.contextPath}/managerController/dataGrid.html?degree=0',
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'id',
		queryParams:queryData, //查询条件
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'createdTime',
		loadMsg : '数据装载中......',
		sortOrder : 'asc',
		checkOnSelect : false,
		selectOnCheck : false,
		rownumbers : true,
		nowrap : false,
		frozenColumns : [ [ 
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
		}, {
			field : 'action',
			title : '操作',
			width : 100,
			formatter : function(value, row, index) {
				 var e = '<a href="#" mce_href="#" onclick="editFun(\''+ row.id + '\')">编辑</a> ';  
	                var r = '<a href="#" mce_href="#" onclick="deleteFun(\''+ row.id +'\')">删除</a> ';  
				if(row.id == 1){
					return e;
				}
               
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

function searchList(){
	var actualName = $("#actualName").val();
	var name = $("#name").val();

	
	 var queryData = {
			 actualName: actualName,
			 name: name,
			
    };
	 getData(queryData);
}

//添加
function addFun() {
	parent.$.modalDialog({
		title : '教师信息',
		width : 400,
		height : 300,
		href : '${pageContext.request.contextPath}/managerController/addAdmin.html',
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
		title : '编辑管理员信息',
		width : 400,
		height : 300,
		href : '${pageContext.request.contextPath}/managerController/editAdmin.html?id=' + id,
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
				$.post('${pageContext.request.contextPath}/managerController/removeAdmin.html', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					} else {
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
				$.post('${pageContext.request.contextPath}/managerController/openmanager.html', {
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
				$.post('${pageContext.request.contextPath}/managerController/closemanager.html', {
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
				$.post('${pageContext.request.contextPath}/managerController/resetpassword.html', {
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

function setPassFun(){
	var url = "${pageContext.request.contextPath}/managerController/setUserPass.html";
	setrow(url,'dataGrid','id');
}

/*function deleteFun(){
	var deleteUrl = "${pageContext.request.contextPath}/managerController/deleteUser.html";
	deleterow(deleteUrl,'dataGrid','id');
}*/

</script>
</head>
<body>
<div class="search">
		<div style="margin-left: 10px;">
			帐号：
			<input name="name" id="name"/>
			&nbsp;&nbsp;
			姓名：<input name="actualName" id="actualName"/>
			<a href="#" class="easyui-linkbutton" id="searchBtn" onclick="searchList();">查询</a>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<table>
						<tr>
							<td><div class="datagrid-btn-separator"></div></td>
							<td>
							<a onclick="addFun();" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">添加</a>
							</td>
							<td><div class="datagrid-btn-separator"></div></td>
							<!--<td>
							<a onclick="deleteFun();" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">删除</a>
							</td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td>
							<a onclick="setPassFun();" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">重置密码</a>
							</td>
							<td><div class="datagrid-btn-separator"></div></td>-->
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	
	<div>
		<table id="dataGrid">
		
		</table>
	</div>
</body>
</html>