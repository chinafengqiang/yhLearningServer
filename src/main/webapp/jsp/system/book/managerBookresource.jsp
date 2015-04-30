<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>目录管理</title>
<jsp:include page="../../../incApp.jsp"></jsp:include>
<style type="text/css">
.book {
	width: 100%;
}

.book table td {
	
}
</style>
<script type="text/javascript">
	var partId = '${partId}';
	var partName = '${name}';
	var encPartName = encodeURI(partName);
	var pid = -1;
	$(function() {
		//动态菜单数据

		//实例化树形菜单
		/* $("#tree").tree({
		     data : treeData,
		     lines : true,
		     onClick : function (node) {
		     	Open(node.id);
		     }
		 });*/

		initTree();

	});

	function initTree() {
		$('#tree')
				.tree(
						{
							checkbox : false,
							url : "${pageContext.request.contextPath}/bookController/getChapterTreeJson.html?partId="
									+ partId + "&rootName=" + encPartName,
							animate : true,
							lines : true,
							onClick : function(node) {
								var isAdd = node.attributes.isAddRes;
								if (isAdd == 2) {
									var npid = node.attributes.pid;
									initResData(node.id, node.text, npid);
									pid = -1;
								} else {
									Open(node.id, node.text);
								}

							},
							onBeforeExpand : function(node, param) {
								//$('#taskTree').tree('options').url = ctx + "/rims/rescue/loadRescueTaskTreeRootNodes.do?parentId="+node.id;                
							}
						});
	}

	function Open(id, name) {
		pid = id;

	}

	function treeLoad() {
		initTree();
	}

	var gctgId = 0;
	var gpartId = 0;

	function initResData(id, name, pid) {
		gctgId = id;
		gpartId = pid;

		$("#titleId").hide();
		$("#search").show();

		var queryData = {
			courseware_category_id : id,
			class_id : pid
		};
		
		if(id >= 100){
			getVideoData(queryData);
		}else{
			getData(queryData);	
		}
		
	}

	//添加
	function addFun() {
		if (pid < 0) {
			alert("请选择下方目录进行添加");
			return;
		}
		var url = '${pageContext.request.contextPath}/bookController/addBookChapter.html?partId='
				+ partId + "&pid=" + pid;
		parent.$.modalDialog({
			title : '添加目录',
			width : 400,
			height : 300,
			href : url,
			buttons : [ {
				text : '添加',
				handler : function() {
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	//编辑
	function editFun() {
		if (pid < 0) {
			alert("请选择下方正确的目录进行编辑");
			return;
		}
		var url = '${pageContext.request.contextPath}/bookController/editBookChapter.html?id='
				+ pid;

		parent.$.modalDialog({
			title : '编辑目录',
			width : 400,
			height : 300,
			href : url,
			buttons : [ {
				text : '编辑',
				handler : function() {
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	//删除
	function deleteFun() {

		if (pid < 0) {
			alert("请选择下方正确的目录进行删除");
			return;
		}
		parent.$.messager
				.confirm(
						'询问',
						'您是否要删除当前信息？',
						function(b) {
							if (b) {
								parent.$.messager.progress({
									title : '提示',
									text : '数据处理中，请稍后....'
								});
								$
										.post(
												'${pageContext.request.contextPath}/bookController/deleteBookchapter.html',
												{
													id : pid
												},
												function(result) {
													if (result.success) {
														parent.$.messager
																.alert('提示',
																		"操作成功",
																		'info');
														treeLoad();
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

	
	function getVideoData(queryData){
		var url = '${pageContext.request.contextPath}/videoController/getVideoResList.html';
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : url,
							fitColumns : true,
							border : false,
							pagination : true,
							idField : 'id',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							remoteSort : true, //服务器端排序
							sortName : 'id',
							queryParams : queryData, //查询条件
							loadMsg : '数据装载中......',
							sortOrder : 'desc',
							checkOnSelect : false,
							selectOnCheck : false,
							rownumbers : true,
							nowrap : false,
							frozenColumns : [ [ {
								field : 'ck',
								checkbox : true
							}, {
								field : 'id',
								title : '编号',
								width : 100,
								hidden : true
							}, {
								field : 'name',
								title : '名称',
								width : 150,
								sortable : true
							} ] ],
							columns : [ [
									{
										field : 'url',
										title : 'url',
										width : 250,
										sortable : true
									},
									{
										field : 'strCreateTime',
										title : '创建时间',
										width : 110
									},
									{
										field : 'lectuer',
										title : '主讲人',
										width : 80
									},
									{
										field : 'status',
										title : '状态',
										width : 50,
										formatter : function(value, row, index) {
											if (value == 1) {
												return "已推送";
											} else {
												return "待推送";
											}
										}
									},
									{
										field : 'action',
										title : '操作',
										width : 80,
										formatter : function(value, row, index) {
											var e = '<a href="#" mce_href="#" onclick="editRes(\''
													+ row.id + '\')">编辑</a> ';
											var r = '<a href="#" mce_href="#" onclick="deleteRes(\''+ row.id+ '\')">删除</a> ';
											var t = '<a href="#" mce_href="#" onclick="sendFun(\''
													+ row.id + '\')">推送</a> ';
											return e + r + t;
										}
									} ] ],
							toolbar : '#toolbar',
							onLoadSuccess : function() {
								$('#searchForm table').show();
								//		parent.$.messager.progress('close');

								//		$(this).datagrid('tooltip');
							},
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
	
	function getData(queryData) {
		var url = '${pageContext.request.contextPath}/bookController/getBookResList.html';
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : url,
							fitColumns : true,
							border : false,
							pagination : true,
							idField : 'id',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							remoteSort : true, //服务器端排序
							sortName : 'id',
							queryParams : queryData, //查询条件
							loadMsg : '数据装载中......',
							sortOrder : 'desc',
							checkOnSelect : false,
							selectOnCheck : false,
							rownumbers : true,
							nowrap : false,
							frozenColumns : [ [ {
								field : 'ck',
								checkbox : true
							}, {
								field : 'id',
								title : '编号',
								width : 100,
								hidden : true
							}, {
								field : 'name',
								title : '电子书名称',
								width : 150,
								sortable : true
							} ] ],
							columns : [ [
									{
										field : 'url',
										title : '附件名称',
										width : 250,
										sortable : true
									},
									{
										field : 'strCreateTime',
										title : '创建时间',
										width : 110
									},
									{
										field : 'status',
										title : '状态',
										width : 50,
										formatter : function(value, row, index) {
											if (value == 1) {
												return "已推送";
											} else {
												return "待推送";
											}
										}
									},
									{
										field : 'action',
										title : '操作',
										width : 80,
										formatter : function(value, row, index) {
											var e = '<a href="#" mce_href="#" onclick="editRes(\''
													+ row.id + '\')">编辑</a> ';
											var r = '<a href="#" mce_href="#" onclick="deleteRes(\''+ row.id+ '\')">删除</a> ';
											var t = '<a href="#" mce_href="#" onclick="sendFun(\''
													+ row.id + '\')">推送</a> ';
											return e + r + t;
										}
									} ] ],
							toolbar : '#toolbar',
							onLoadSuccess : function() {
								$('#searchForm table').show();
								//		parent.$.messager.progress('close');

								//		$(this).datagrid('tooltip');
							},
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

	function addRes() {
		if (gctgId == 0 || gpartId == 0) {
			alert("请选择相应分类进行添加");
			return;
		}
		var url = '${pageContext.request.contextPath}/bookController/addBookRes.html?ctgId='
			+ gctgId + "&partId=" + gpartId;
		var title = '电子书资料';
		if(gctgId >= 100){
			title = '视频资料';
			url = '${pageContext.request.contextPath}/videoController/addVideoRes.html?ctgId='
				+ gctgId + "&partId=" + gpartId;
		}
		parent.$
				.modalDialog({
					title : title,
					width : 500,
					height : 400,
					href : url,
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
	function editRes(id) {
		
		var title = '编辑电子书资料';
		var url = '${pageContext.request.contextPath}/bookController/editBookRes.html?id='
		+ id;
		if(gctgId >= 100){
			title = '编辑视频资料';
			url = '${pageContext.request.contextPath}/videoController/editVideoRes.html?id='
					+ id;
		}
		
		/*if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}*/
		
		parent.$
				.modalDialog({
					title : title,
					width : 500,
					height : 400,
					href : url,
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
	function deleteRes(id) {
		var url = '${pageContext.request.contextPath}/bookController/deleteBookRes.html';
		if(gctgId >= 100){
			url = '${pageContext.request.contextPath}/videoController/deleteVideoRes.html';
		}
		
		var ids = "";
		if(id > 0){
			ids = id;
		}else{
			var rows = dataGrid.datagrid('getChecked');
			$.each(rows, function(i, item) {
				ids += item.id+",";
			});
		}
		
		if(ids.length <= 0){
			alert("请选择要删除的资源");
			return false;
		}

		parent.$.messager
				.confirm(
						'询问',
						'您是否要删除当前信息？',
						function(b) {
							if (b) {
								parent.$.messager.progress({
									title : '提示',
									text : '数据处理中，请稍后....'
								});
								$
										.post(
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
														if(gctgId >= 100){
															getVideoData();
														}else{
															getData();
														}
														
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

	function searchList() {
		var START_TIME = $("#START_TIME").val();
		var END_TIME = $("#END_TIME").val();
		var name = $("#name").val();
		var status = $("#status").val();
		/*var url = '${pageContext.request.contextPath}/coursewareController/getCoursewareList.html';
		url += '?startTime='+START_TIME+"&endTime="+END_TIME+"&name="+name+"&gradeId="+grade_id;
		url += "&category="+category+"&status="+status;
		getData(url);*/

		var queryData = {
			startTime : START_TIME,
			endTime : END_TIME,
			name : name,
			status : status,
			courseware_category_id : gctgId,
			class_id : gpartId
		};
		if(gctgId >= 100){
			getVideoData(queryData);
		}else{
			getData(queryData);
		}
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
			href : '${pageContext.request.contextPath}/bookController/editSendBookRes.html?id=' + id,
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
			href : '${pageContext.request.contextPath}/bookController/editSendBookRess.html?ids='+ids,
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
<body class="easyui-layout">
	<div data-options="region:'west',title:'目录管理',split:true,border:false" style="width:200px;" title="目录管理">

		<div style="text-align: center; padding-top: 10px;">
			<button onclick="addFun();">添加</button>
			&nbsp;&nbsp;
			<button onclick="editFun();">编辑</button>
			&nbsp;&nbsp;
			<button onclick="deleteFun();">删除</button>
		</div>

		<div id="nav"  class="easyui-accordion">
			<ul id="tree" style="padding-top: 10px;"></ul>
		</div>
	</div>



	<div region="center">
		<div data-options="region:'center',border:false" id="flList">

			<div id="search" class="search" style="height: 100px; display: none;">
				<div style="margin-left: 10px;">

					时间： <input name="START_TIME" id="START_TIME"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
					&nbsp;至&nbsp; <input name="END_TIME" id="END_TIME"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'});">&nbsp;
					&nbsp;&nbsp;<br> 名称： <input name="name" id="name">
					&nbsp;&nbsp; 状态： <select name="status" id="status"
						style="width: 160px; height: 40px;">
						<option value="-1">&nbsp;</option>
						<option value="0">待推送</option>
						<option value="1">已推送</option>
					</select> &nbsp; &nbsp; <a href="#" class="easyui-linkbutton" id="searchBtn"
						onclick="searchList();">查询</a>
				</div>

			</div>

			<table id="dataGrid">
				<h2 id="titleId" align="center">请点击左侧课堂板书、补充资料、课后练习及其他获取资源！</h2>
			</table>
		</div>

	</div>





	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<table>
						<tr>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="addRes();" href="javascript:void(0);"
								class="toolbar-btn"
								data-options="plain:true,iconCls:'pencil_add'">添加</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="deleteRes(0);" href="javascript:void(0);"
								class="toolbar-btn"
								data-options="plain:true,iconCls:'pencil_add'">删除</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="sendFunBatch();" href="javascript:void(0);"
								class="toolbar-btn"
								data-options="plain:true,iconCls:'pencil_add'">推送</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>



</body>
</html>