
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/incApp.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>同步频道管理</title>
<style type="text/css">
.wrap{ 
    font-size: 12px; 
    margin:40px auto; 
    width:400px; 
} 
/*进度条样式*/ 
.progressbar_1{ 
    background-color:#eee; 
    color:#222; 
    height:16px; 
    width:150px; 
    border:1px solid #bbb; 
} 
.progressbar_1 .bar { 
    background-color:#6CAF00; 
    height:16px; 
    width:0; 
} 
/*绝对定位*/ 
.progressbar_2{ 
    background-color:#eee; 
    color:#222; 
    height:16px; 
    width:150px; 
    border:1px solid #bbb; 
    text-align:center; 
    position:relative; 
} 
.progressbar_2 .bar { 
    background-color:#6CAF00; 
    height:16px; 
    width:0; 
    position:absolute; 
    left:0; 
    top:0; 
} 
.progressbar_2 .text { 
    height:16px; 
    position:absolute; 
    left:0; 
    top:0; 
    width:100%; 
    line-height:16px; 
} 
/*绝对定位 + z-index */ 
.progressbar_3{ 
    background-color:#eee; 
    color:#222; 
    height:16px; 
    width:120px; 
    border:1px solid #bbb; 
    text-align:center; 
    position:relative; 
} 
.progressbar_3 .bar { 
    background-color:#6CAF00; 
    height:16px; 
    width:0; 
    position:absolute; 
    left:0; 
    top:0; 
    z-index:10; 
} 
.progressbar_3 .text { 
    height:16px; 
    position:absolute; 
    left:0; 
    top:0; 
    width:100%; 
    line-height:16px; 
     
    z-index:100; 
} 
</style>
<script src="${pageContext.request.contextPath }/jslib/datePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
  

var dataGrid;
$(function() {
	getData();
});


function getData(queryData){

	dataGrid = $('#dataGrid').datagrid({
		url : '${pageContext.request.contextPath}/dataSyncController/getFileporgedList.html',
		fitColumns : true,
		border : false,
		pagination : true,
		queryParams:queryData, //查询条件
		idField : 'ID',
		pageSize : 10,
		pageList : [ 1,10, 20, 30, 40, 50 ],
		sortName : 'ID',
		loadMsg : '数据装载中......',
		sortOrder : 'desc',
		checkOnSelect : false,
		selectOnCheck : false,
		rownumbers : true,
		nowrap : false,
		frozenColumns : [ [  
			{field:'ck',checkbox:true}
		] ],
		columns : [ [ 
		{
			field : 'CHANNEL_NAME',
			title : '频道名称',
			width : 150,
			sortable : true
		}, 
		{
			field : 'FILECOUNT',
			title : '文件个数',
			width : 80,
			sortable : true
		}, 
		{
			field : 'FILESIZE',
			title : '文件大小',
			width : 80,
			sortable : true
		},
		{
			field : 'START_VALID_DATE_STR',
			title : '开始时间',
			width : 150,
			sortable : true,
			
		},
		{
			field : 'END_VALID_DATE_STR',
			title : '结束时间',
			width : 150,
			sortable : true,
			
		},
		
		{
			field : 'CUR_SEND_END_TIME_STR',
			title : '文件投递结束时间',
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				var id = row.ID;
				var ret = '<span id="endTime_'+id+'">'+value+'</span>';
				return ret;
			}
			
		},
		{
			field : 'action',
			title : '操作',
			width : 100,
			formatter : function(value, row, index) {
				  var showUrl = "${pageContext.request.contextPath}/dataSyncController/showFileprogFile.html?fileporgId="+row.ID;
				  var r = '<a href="#" mce_href="#" onclick="showFun(\'查看文件\',\''+ showUrl +'\')">查看文件</a> ';  
	              return r;   
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
	var START_TIME = $("#START_TIME").val();
	var END_TIME = $("#END_TIME").val();
	 var queryData = {
			 startValidDate: START_TIME,
			 endValidDate: END_TIME,
    };
	 getData(queryData);
}


function deleteFun(){
	var deleteUrl = "${pageContext.request.contextPath}/dataSyncController/deleteFileprog.html";
	deleterow(deleteUrl,'dataGrid','ID');
}

</script>
</head>
<body>
	<div class="search">
		<div style="margin-left: 10px;">
			时间：
			<input name="START_TIME" id="START_TIME" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
			&nbsp;至&nbsp;
			<input name="END_TIME" id="END_TIME" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
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
							<a onclick="deleteFun();" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">删除</a>
							</td>
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