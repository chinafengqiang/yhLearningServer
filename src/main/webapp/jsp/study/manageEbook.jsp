<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>电子书管理</title>
<jsp:include page="../../incApp.jsp"></jsp:include>
<script type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/jslib/datePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
/*$(function() {
	 loadGrid();
	 $("#searchBtn").click(function(){
		 var queryData = {
				 search: $("#search").val(),
				 searchValue: $("#searchValue").val(),
        };
		 loadGrid(queryData);
	 });
});*/

var dataGrid;
$(function() {

	getData();
});

function getData(queryData){
	var url = '${pageContext.request.contextPath}/coursewareController/getCoursewareList.html';
	dataGrid = $('#dataGrid').datagrid({
		url : url,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		remoteSort: true, //服务器端排序
		sortName : 'id',
		queryParams:queryData, //查询条件
		loadMsg : '数据装载中......',
		sortOrder : 'desc',
		checkOnSelect : false,
		selectOnCheck : false,
		rownumbers : true,
		nowrap : false,
		frozenColumns : [ [ 
		{field:'ck',checkbox:true},                    
		{
			field : 'id',
			title : '编号',
			width : 100,
			hidden : true
		}, {
			field : 'name',
			title : '电子书名称',
			width : 130,
			sortable : true
		} ] ],
		columns : [ [  
			
		   {
			field : 'categoryName',
			title : '分类名称',
			width : 40,
			sortable : true
		}, {
			field : 'gradeName',
			title : '所属年级',
			width : 70,
			sortable : true
		}, 
		
		{
			field : 'url',
			title : '附件名称',
			width : 200,
			sortable : true
		},{
			field : 'strCreateTime',
			title : '创建时间',
			width : 80
		}, {
			field : 'status',
			title : '状态',
			width : 30,
			formatter:function(value,row,index){
				if(value == 1){
					return "已推送";
				}else{
					return "待推送";
				}
			}
		},{
			field : 'action',
			title : '操作',
			width : 80,
			formatter : function(value, row, index) {
				var deleteUrl = "${pageContext.request.contextPath}/coursewareController/removecourseware.html";
                var e = '<a href="#" mce_href="#" onclick="editFun(\''+ row.id + '\')">编辑</a> ';  
                var r = '<a href="#" mce_href="#" onclick="deleterow(\''+deleteUrl+'\',\'dataGrid\',\'id\')">删除</a> ';  
                var t = '<a href="#" mce_href="#" onclick="sendFun(\''+ row.id +'\')">推送</a> ';  
                return e+r+t;  
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
		title : '电子书资料',
		width : 600,
		height : 500,
		href : '${pageContext.request.contextPath}/coursewareController/addEbooks.html',
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
		title : '编辑电子书资料',
		width : 600,
		height : 500,
		href : '${pageContext.request.contextPath}/coursewareController/editEbook.html?id=' + id,
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
				$.post('${pageContext.request.contextPath}/coursewareController/removecourseware.html', {
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

//下发
function sendFun(id){
	
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	} else {
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
	}
	parent.$.modalDialog({
		title : '推送电子书资料',
		width : 500,
		height : 400,
		href : '${pageContext.request.contextPath}/coursewareController/editProfileBook.html?id=' + id,
		buttons : [ {
			text : '推送',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
				var f = parent.$.modalDialog.handler.find('#form');
				f.submit();
			}
		} ]
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


function deleteFun(){
	var deleteUrl = "${pageContext.request.contextPath}/coursewareController/deletecourseware.html";
	deleterow(deleteUrl,'dataGrid','id');
}

function searchList(){
	var START_TIME = $("#START_TIME").val();
	var END_TIME = $("#END_TIME").val();
	var name = $("#name").val();
	var grade_id = $("#grade_id").val();
	var category = $("#category").val();
	var status = $("#status").val();
	/*var url = '${pageContext.request.contextPath}/coursewareController/getCoursewareList.html';
	url += '?startTime='+START_TIME+"&endTime="+END_TIME+"&name="+name+"&gradeId="+grade_id;
	url += "&category="+category+"&status="+status;
	getData(url);*/
	
	 var queryData = {
			 startTime: START_TIME,
			 endTime: END_TIME,
			 name: name,
			 gradeId: grade_id,
			 category: category,
			 status: status
    };
	 getData(queryData);
}

function sendFunBatch(){
	var ids = "null";
	var rows = $('#dataGrid').datagrid('getChecked');
	if(rows.length == 0){
		$.messager.alert('提示','请选择要推送的电子书！','info');
		return;
	}
	$.each(rows,function(i,obj){
		var pkValue = obj.id;
		ids += ","+pkValue;
	});
	parent.$.modalDialog({
		title : '推送电子书资料',
		width : 500,
		height : 400,
		href : '${pageContext.request.contextPath}/coursewareController/editProfileBooks.html?ids='+ids,
		buttons : [ {
			text : '推送',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
				var f = parent.$.modalDialog.handler.find('#form');
				f.submit();
			}
		} ]
	});
	
}
</script>
</head>
<body>

<div class="search" style="height: 100px;">
		<div style="margin-left: 10px;">

			时间：
			<input name="START_TIME" id="START_TIME" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
			&nbsp;至&nbsp;
			<input name="END_TIME" id="END_TIME" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'});">&nbsp;
			&nbsp;&nbsp;
			名称： <input name="name" id="name">
			&nbsp;<br>
					年级： 
			<select name="gradeId" id="grade_id" style="width:160px;height: 40px;">
			<option value="-1">&nbsp;</option>
			</select>
			       <script type="text/javascript">
			       attachGradeSelectBox(document.getElementById("grade_id"),'',"${pageContext.request.contextPath }/sysGradeController/getGradeJson.html;");	
					</script>
				&nbsp;	
				分类：
				<select name="category" id="category" style="width:160px;height: 40px;">
				<option value="-1">&nbsp;</option>
				</select>
			       <script type="text/javascript">
			       attachGradeSelectBox(document.getElementById("category"),'',"${pageContext.request.contextPath }/coursewareController/getCoursewareCategoryJson.html;");	
					</script>
					&nbsp;
					状态：
				<select name="status" id="status" style="width:160px;height: 40px;">
					<option value="-1">&nbsp;</option>
					<option value="0">待推送</option>
					<option value="1">已推送</option>
				</select>
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
							<a onclick="deleteFun();" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">删除</a>
							</td>
							<td><div class="datagrid-btn-separator"></div></td>
									<td>
							<a onclick="sendFunBatch();" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">推送</a>
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