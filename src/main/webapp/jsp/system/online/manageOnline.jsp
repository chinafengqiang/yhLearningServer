<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>在线交流管理</title>
<jsp:include page="../../../incApp.jsp"></jsp:include>
<script src="${pageContext.request.contextPath }/jslib/datePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
var dataGrid;
var classId;
$(function() {
	getData();
});

function getData(queryData){
	dataGrid = $('#dataGrid').datagrid({
		url : '${pageContext.request.contextPath}/sysMessageController/getOnlineMessageList.html',
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'ID',
		pageSize : 10,
		queryParams:queryData, //查询条件
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'ID',
		loadMsg : '数据装载中......',
		sortOrder : 'desc',
		checkOnSelect : false,
		selectOnCheck : false,
		rownumbers : true,
		nowrap : false,
		frozenColumns : [ [ 
		 {field:'ck',checkbox:true},   
		 {
				field : 'ID',
				title : '编号',
				width : 100,
				hidden : true
			},
		 {
			field : 'MESSAGE',
			title : '内容',
			width : 250,
			sortable : true
		} ] ],
		columns : [ [  {
			field : 'M_TIME',
			title : '发起时间',
			width : 150,
			sortable : true
		},  {
			field : 'SRC_NAME',
			title : '发起者',
			width : 120,
			sortable : true
		}, {
			field : 'OBJECT_NAME',
			title : '目标',
			width : 120
		},  {
			field : 'IS_VALID',
			title : '状态',
			width : 80,
			formatter : function(value, row, index) {
				var isValid = row.IS_VALID;
				if(isValid == 1){
					return '有效';
				}else{
					return '无效';
				}
				
			}
		}, 
		{
			field : 'LOCAL_IMAGE_PATH',
			title : '图片',
			width : 80,
			formatter : function(value, row, index) {
				var imagePath = row.IMAGE_PATH;
				var e = "";
				if(imagePath != ""){
					e = '<a href="#" mce_href="#" onclick="zoomImage(\''+ imagePath + '\')">查看</a> ';
				}
				return e;
			}
		},
		{
			field : 'action',
			title : '操作',
			width : 80,
			formatter : function(value, row, index) {
				/*var e = '<a href="#" mce_href="#" onclick="showReply(\''
						+ row.ID + '\')">查看回复</a> ';*/
				var showUrl = "${pageContext.request.contextPath}/sysMessageController/showReplyMessage.html?id="+row.ID;
				var e = '<a href="#" mce_href="#" onclick="showFun(\'查看回复\',\''+ showUrl +'\',900,600)">查看回复</a> ';  
				return e;
			}
		}
		] ],
		toolbar : '#toolbar',
	onBeforeLoad:function(){
		$('#dataGrid').datagrid('loadData',{total:0,rows:[]});
	},
	onRowContextMenu : function(e, rowIndex, rowData) {
		e.preventDefault();
		$(this).datagrid('unselectAll').datagrid(
				'uncheckAll');
		$(this).datagrid('selectRow', rowIndex);
		$('#menu').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
	}
		

	});
}

function zoomImage(imagePath){
	var url = '${pageContext.request.contextPath}/managerController/zoomImage.html?imagePath='+imagePath;
	window.open(url,'','menubar=no,location=no,toolbar=no,scrollbars=no');
}

function searchFun() {
	dataGrid.datagrid('load', serializeObject($('#searchForm')));
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
	    			 classId: id,
	    			 startTime: $("#startTime").val(),
	    	 			endTime: $("#endTime").val(),
	    	 			sender:$("#sender").val()
	    	};
	    	getData(queryData);
    }
});


function searchList(){
 	var queryData = {
 			startTime: $("#startTime").val(),
 			endTime: $("#endTime").val(),
 			sender:$("#sender").val(),
 			classId:classId
	};
	getData(queryData);
}



//删除
function setStatus(type) {
	var url = '${pageContext.request.contextPath}/sysMessageController/setOnlineStatus.html';
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
												ids : ids,
												isValid:type
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
<body class="easyui-layout">



<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<table>
						<tr>
							<td><div class="datagrid-btn-separator"></div></td>
							<td>
							<a onclick="setStatus('0');" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">禁用</a>
							</td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td>
							<a onclick="setStatus('1');" href="javascript:void(0);" class="toolbar-btn" data-options="plain:true,iconCls:'pencil_add'">启用</a>
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
			时间： <input name="startTime" id="startTime"
				onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
			&nbsp;至&nbsp; <input name="endTime" id="endTime"
				onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'});">&nbsp;

			&nbsp;&nbsp;
			姓名： <input name="sender" id="sender"/>
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