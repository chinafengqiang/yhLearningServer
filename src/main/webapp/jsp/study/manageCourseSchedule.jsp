<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>课程进度管理</title>
<jsp:include page="../../incApp.jsp"></jsp:include>
<script type="text/javascript"></script>
<script type="text/javascript">
var dataGrid;
$(function() {

	dataGrid = $('#dataGrid').datagrid({
		url : '${pageContext.request.contextPath}/courseController/dataGridSchedule.html',
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
			field : 'weekName',
			title : '星期名称',
			width : 130,
			sortable : true
		} ] ],
		columns : [ [  {
			field : 'levelOne',
			title : '第一节课',
			width : 80,
			sortable : true
		}, {
			field : 'levelTwo',
			title : '第二节课',
			width : 80,
			sortable : true
		},{
			field : 'levelThree',
			title : '第三节课',
			width : 80
		}, {
			field : 'levelFour',
			title : '第四节课',
			width : 80
		}, {
			field : 'levelFive',
			title : '第五节课',
			width : 80
		}, {
			field : 'levelSix',
			title : '第六节课',
			width : 80
		}, {
			field : 'levelSeven',
			title : '第七节课',
			width : 80
		}, {
			field : 'levelEight',
			title : '第八节课',
			width : 80
		}, {
			field : 'superGrade',
			title : '年级',
			width : 80
		}, {
			field : 'superClass',
			title : '班级',
			width : 80
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
		onLoadSuccess : function() {
				$('#searchForm table').show();
		//		parent.$.messager.progress('close');

		//		$(this).datagrid('tooltip');
			},
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
});

//添加
function addFun() {
	parent.$.modalDialog({
		title : '课程表进度',
		width : 600,
		height : 500,
		href : '${pageContext.request.contextPath}/courseController/addPageSchedule.html',
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
		title : '编辑课程表进度',
		width : 600,
		height : 500,
		href : '${pageContext.request.contextPath}/courseController/editCourseSchedule.html?id=' + id,
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
				$.post('${pageContext.request.contextPath}/courseController/removeCourseSchedule.html', {
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

</script>
</head>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px; overflow: hidden;">
		<form id="searchForm">
			<table class="table table-hover table-condensed" style="display: none;">
				<tr>
					<th>年级</th>
					<td><input name="superGrade" id="superGrade" placeholder="查询" class="span2" /></td>
				</tr>
				<tr>
				<th>班级</th>
					<td><input name="superClass" id="superClass" placeholder="查询" class="span2" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="dataGrid"></table>
	</div>
</div>
<div id="toolbar" style="display: none;">
		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">过滤条件</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a>
</div>

<div id="menu" class="easyui-menu" style="width: 120px; display: none;">

		<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>


		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>


		<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>

</div>
</body>
</html>