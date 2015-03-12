<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>课程表管理</title>
<jsp:include page="../../incApp.jsp"></jsp:include>
<script type="text/javascript"></script>
<script type="text/javascript">
var dataGrid;
$(function() {
	getData();
});

function getData(queryData){
	dataGrid = $('#dataGrid').datagrid({
		url : '${pageContext.request.contextPath}/courseController/getLessonList.html',
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'ID',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		queryParams:queryData, //查询条件
		sortName : 'ID',
		loadMsg : '数据装载中......',
		sortOrder : 'asc',
		checkOnSelect : false,
		selectOnCheck : false,
		rownumbers : true,
		nowrap : false,
		frozenColumns : [ [ 
			{field:'ck',checkbox:true},     
			{
				field : 'NAME',
				title : '名称',
				width : 150,
				sortable : true
			},
		{
			field : 'YEAR',
			title : '学年',
			width : 150,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'TERM',
			title : '学期',
			width : 80,
			sortable : true
		},{
			field : 'gradeName',
			title : '年级',
			width : 150,
			sortable : true
		},
		{
			field : 'action',
			title : '操作',
			width : 100,
			formatter : function(value, row, index) {
                var e = '<a href="#" mce_href="#" onclick="editFun(\''+ row.id + '\')">编辑</a> ';  
                var r = '<a href="#" mce_href="#" onclick="deleteFun(\''+ row.id +'\')">删除</a> ';  
                var s = '<a href="#" mce_href="#" onclick="show(\''+ row.ID +'\')">详细课程表</a> ';
                return s;  
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
}

//添加
function addFun() {
	parent.$.modalDialog({
		title : '课程进度信息',
		width : 800,
		height : 400,
		href : '${pageContext.request.contextPath}/courseController/addLesson.html',
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
		title : '编辑课程进度信息',
		width : 800,
		height : 400,
		href : '${pageContext.request.contextPath}/courseController/editLesson.html?id=' + id,
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
				$.post('${pageContext.request.contextPath}/courseController/removeLesson.html', {
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


function importExcel(){
	parent.$.modalDialog({
		title : '课程表',
		width : 500,
		height : 400,
		href : '${pageContext.request.contextPath}/courseController/impLesson.html',
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

function searchList(){
	var grade_id = $("#grade_id").val();
	 var queryData = {
			 gradeId: grade_id,
    };
	 getData(queryData);
}

function show(id){
	var url = "${pageContext.request.contextPath}/courseController/getLessonDetailList.html?id="+id;
	showFun("课程表信息",url);
}

function deleteFun(){
	var deleteUrl = "${pageContext.request.contextPath}/courseController/deleteLesson.html";
	deleterow(deleteUrl,'dataGrid','ID');
}
</script>
</head>
<body>
<div class="search" style="height: 50px;">
		<div style="margin-left: 10px;">
					年级： 
			<select name="gradeId" id="grade_id" style="width:160px;height: 40px;">
			<option value="-1">&nbsp;</option>
			</select>
			       <script type="text/javascript">
			       attachGradeSelectBox(document.getElementById("grade_id"),'',"${pageContext.request.contextPath }/sysGradeController/getGradeJson.html;");	
					</script>
	
				&nbsp;
				&nbsp;
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
							<a onclick="importExcel();" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">导入</a>
							</td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td>
							<a onclick="deleteFun();" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">删除</a>
							</td>
							<td><div class="datagrid-btn-separator"></div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',border:false">
		<table id="dataGrid"></table>
	</div>
</body>
</html>