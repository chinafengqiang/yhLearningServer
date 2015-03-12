<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/incApp.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>同步用户管理</title>
<script type="text/javascript">
var dataGrid;
$(function() {

	dataGrid = $('#dataGrid').datagrid({
		url : '${pageContext.request.contextPath}/dataSyncController/getUser.html',
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'ID',
		loadMsg : '数据装载中......',
		sortOrder : 'asc',
		checkOnSelect : false,
		selectOnCheck : false,
		rownumbers : true,
		nowrap : false,
		frozenColumns : [ [  
		
		] ],
		columns : [ [ 
		{
			field : 'UID',
			title : '用户编号',
			width : 100,
			sortable : true
		},  
		{
			field : 'USERNAME',
			title : '用户名',
			width : 150,
			sortable : true
		},
		{
			field : 'PASSWORD',
			title : '昵称',
			width : 150,
			sortable : true
		},
		{
			field : 'GNAME',
			title : '所属分组',
			width : 150,
			sortable : true
		},
		{
			field : 'action',
			title : '操作',
			width : 100,
			formatter : function(value, row, index) {
				  var editUrl = "${pageContext.request.contextPath}/dataSyncController/edUser.html?id="+row.UID;
				  var deleteUrl = "${pageContext.request.contextPath}/dataSyncController/deleteUser.html?id="+row.UID;
				  var permUrl = "${pageContext.request.contextPath}/dataSyncController/permUser.html?id="+row.UID;
				  var e = '<a href="#" mce_href="#" onclick="editFun(\'编辑用户\',\''+editUrl+'\',\'form\',\'400\',\'300\');">编辑</a>&nbsp;&nbsp;';
				  var r = '<a href="#" mce_href="#" onclick="deleteFun(\''+ deleteUrl +'\')">删除</a>&nbsp;&nbsp;';  
				  var p = '<a href="#" mce_href="#" onclick="showFun(\'用户授权\',\''+permUrl+'\',\'800\',\'600\')">授权</a>&nbsp;&nbsp;';  
	              return e+r+p;   
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
});
</script>
</head>
<body>

	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<table>
						<tr>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="toolbar-btn"  onclick="addFun('添加用户','<%=request.getContextPath() %>/dataSyncController/addUser.html','form',400,300);">添加</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
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