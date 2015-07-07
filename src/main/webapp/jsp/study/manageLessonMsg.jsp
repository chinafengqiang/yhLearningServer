<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>教学通知管理</title>
<jsp:include page="../../incApp.jsp"></jsp:include>
<script type="text/javascript"></script>
<script type="text/javascript">
var dataGrid;
$(function() {
	getData();
});

function getData(queryData){
	dataGrid = $('#dataGrid').datagrid({
		url : '${pageContext.request.contextPath}/courseController/getLessonMsgList.html',
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
				field : 'TITLE',
				title : '标题',
				width : 150,
				sortable : true
			},
		{
			field : 'CONTENT',
			title : '内容',
			width : 250,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'START_DATE',
			title : '有效开始时间',
			width : 80,
			sortable : true
		},{
			field : 'END_DATE',
			title : '有效结束时间',
			width : 80,
			sortable : true
		},{
			field : 'gradeName',
			title : '所属年级',
			width : 50,
			sortable : true
		} ] ],
		toolbar : '#toolbar',
		onLoadSuccess : function() {
				$('#searchForm table').show();

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
		title : '添加教学信息',
		width : 600,
		height : 400,
		href : '${pageContext.request.contextPath}/courseController/addLessonMsg.html',
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
	var rows = dataGrid.datagrid('getChecked');
	if(rows.length <= 0){
		alert("请选择要编辑的项");
		return false;
	}
	if(rows.length > 1){
		alert("只能选择一个要编辑的项");
		return false;
	}
	var id = rows[0].ID;
	parent.$.modalDialog({
		title : '编辑教学信息',
		width : 600,
		height : 400,
		href : '${pageContext.request.contextPath}/courseController/editLessonMsg.html?id=' + id,
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




function searchFun() {
	
	dataGrid.datagrid('load', serializeObject($('#searchForm')));
}


function searchList(){
	var grade_id = $("#grade_id").val();
	var title = $("#title").val();
	 var queryData = {
			 gradeId: grade_id,
			 title:title
    };
	 getData(queryData);
}



//删除
function deleteFun() {
	var url = '${pageContext.request.contextPath}/courseController/deleteLessonMsg.html';
	var ids = "";

	var rows = dataGrid.datagrid('getChecked');
	$.each(rows, function(i, item) {
		ids += item.ID+",";
	});
	
	if(ids.length <= 0){
		alert("请选择要操作的项");
		return false;
	}

	parent.$.messager
			.confirm(
					'询问',
					'您是否要进行当前操作？',
					function(b) {
						if (b) {
							parent.$.messager.progress({
								title : '提示',
								text : '数据处理中，请稍后....'
							});
							$.post(
											url,
											{
												ids : ids
											},
											function(result) {
												if (result.success) {
													parent.$.messager
															.alert('提示',
																	"操作成功",
																	'info');
													searchList();
												} else {
													parent.$.messager
															.alert('错误',
																	"操作失败",
																	'error');
												}
												parent.$.messager
														.progress('close');
											}, 'JSON');
						}
					});
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
				标题：<input name="title" id="title">
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
							<a onclick="addFun();" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">添加</a>
							</td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td>
							<a onclick="editFun();" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">编辑</a>
							</td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td>
							<a onclick="deleteFun();" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">删除</a>
							</td>
	

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