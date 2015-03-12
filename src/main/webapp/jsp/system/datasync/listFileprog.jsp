
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
		url : '${pageContext.request.contextPath}/dataSyncController/getFileporgList.html',
		fitColumns : true,
		border : false,
		pagination : true,
		queryParams:queryData, //查询条件
		idField : 'id',
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
			//{field:'ck',checkbox:true}
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
			field : 'STATUS',
			title : '状 态',
			width : 80,
			sortable : true,
			formatter : function(value, row, index) {
				 if(value == 2){
					 return "已生效";
				 }
				 if(value == 5){
					 return "删除待审";
				 }
				 if(value == 6){
					 return "已删除";
				 }
		
				 return value;
			}
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
			field : 'FILE_PORG_JD',
			title : '投递进度',
			width : 200,
			formatter : function(value, row, index) {
				var fileprogId = row.ID;
				var strSchData = row.SCH_DATA_STR;
				/*StringBuffer buf = new StringBuffer();
				buf.append('<div class="progressbar_3">');
				buf.append('<div class="text" id="sch_'+fileprogId+'_text">');
				buf.append(strSchData);
				buf.append('</div> ');
				buf.append('<div class="bar" id="sch_'+fileprogId+'_width" style="width: '+strSchData+';"></div>');
				buf.append('</div> ');
				alert(buf.toString());*/
				var str = '<div class="progressbar_3">';
				str += '<div class="text" id="sch_'+fileprogId+'_text">';
				str += strSchData;
				str += '</div> ';
				str += '<div class="bar" id="sch_'+fileprogId+'_width" style="width: '+strSchData+';"></div>';
				str += '</div> ';
				//alert(str);
				return str;
			}
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

$( function() {	
	setInterval("reload()",10000);
});

function reloadF(){
	var START_TIME = $("#START_TIME").val();
	var END_TIME = $("#END_TIME").val();
	 var queryData = {
			 startValidDate: START_TIME,
			 endValidDate: END_TIME,
    };
	 getData(queryData);
}

function reload(){
	//var channelId = document.getElementById("channelId").value;
	var rows = $("#dataGrid").datagrid('getRows');
	var ids = 'null';
	for(var i=0;i<rows.length;i++){
		ids += ","+rows[i].ID;
	}
	
	jQuery.ajax({
		   type: "POST",
		   url: "${pageContext.request.contextPath}/dataSyncController/getFileporgProcess.html",
		   cache: false,
		   data:{fileprogIds:ids},
		   dataType: "script",
		   success: function(data){
			   data = eval(data);
			   var dates = data.split(";");
				for(var n=0;n<dates.length;n++){
					var info = dates[n];
					var infos = info.split("|");
					var id = infos[0];
					var sch = infos[1];
					var date = infos[2];
					//alert(id+" "+sch+" "+date);
					//alert($("#sch_"+id+"_text").html());
					$("#sch_"+id+"_text").html(sch);
					$("#sch_"+id+"_width").attr("style","width:"+sch);
					$("#endTime_"+id).html(date);
				}
					
	
		   }
		}); 
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