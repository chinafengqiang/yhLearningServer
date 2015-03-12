<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/incApp.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>同步频道管理</title>
<script type="text/javascript">
var dataGrid;
$(function() {

	dataGrid = $('#dataGrid').datagrid({
		url : '${pageContext.request.contextPath}/dataSyncController/getChannel.html',
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
			field : 'ID',
			title : '频道ID',
			width : 100,
			sortable : true
		},  
		{
			field : 'NAME',
			title : '频道名称',
			width : 150,
			sortable : true
		}, 
		{
			field : 'MIP',
			title : '组播IP',
			width : 150,
			sortable : true
		}, 
		{
			field : 'MPORT',
			title : '组播端口',
			width : 150,
			sortable : true
		},
		{
			field : 'BANDWIDTH',
			title : '带宽(kbps)',
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				  if(value == 0){
					  return "无限制";
				  }
				  
			}
		},
		{
			field : 'TYPE',
			title : '频道类型',
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				  if(value == 2){
					  return "文件投递频道";
				  }
				  if(value == 6){
					  return "文件预告频道";
				  }
				  if(value == 100){
					  return "文件点播频道";
				  }
			}
		},
		{
			field : 'action',
			title : '操作',
			width : 100,
			formatter : function(value, row, index) {
				  var editUrl = "${pageContext.request.contextPath}/dataSyncController/editChannel.html?id="+row.ID;
				  var deleteUrl = "${pageContext.request.contextPath}/dataSyncController/deleteChannel.html?id="+row.ID;;
				  var e = '<a href="#" mce_href="#" onclick="editFun(\'编辑频道\',\''+editUrl+'\',\'form\',\'600\',\'400\');">编辑</a>&nbsp;&nbsp;';
				  var r = '<a href="#" mce_href="#" onclick="deleteFun(\''+ deleteUrl +'\')">删除</a> ';  
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
});
</script>
</head>
<body>
	<!--<div class="search">
		<div style="margin-left: 10px;">

			时间：
			<input name="START_TIME" id="START_TIME" onclick="WdatePicker({skin:'twoer',dateFmt:'yyyy-MM-dd'});" class="Wdate">
			&nbsp;至&nbsp;
			<input name="END_TIME" id="END_TIME" onclick="WdatePicker({skin:'twoer',dateFmt:'yyyy-MM-dd'});" class="Wdate">&nbsp;
			&nbsp;
			订单号： <input name="SID" id="SID">

			
			&nbsp;
			<a href="#" class="easyui-linkbutton" id="searchBtn">查询</a>
		</div>
	</div>-->
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<table>
						<tr>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="toolbar-btn"  onclick="addFun('添加频道','<%=request.getContextPath() %>/dataSyncController/addChannel.html','form',600,400);">添加</a></td>
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