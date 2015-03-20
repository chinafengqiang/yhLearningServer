<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>分册管理</title>
<jsp:include page="../../../incApp.jsp"></jsp:include>
<style type="text/css">
body {
	font-size: 14px;
	color: #000;
	font-family: Microsoft Yahei, arial, verdana, sans-serif;
}

html>body {
	font-size: 16px;
}

.toolbar-btn {
	display: inline-block;
	vertical-align: top;
	width: auto;
	min-width: 100px;
	line-height: 30px;
	font-size: 14px;
	font-family: Microsoft Yahei;
	padding: 0;
	margin: 0 4px;
	text-align: center;
}

.datagrid-btn-separator {
	float: left;
	height: 30px;
	border-left: 1px solid #ccc;
	border-right: 1px solid #fff;
	margin: 2px 1px;
}
</style>


<script type="text/javascript"></script>
<script type="text/javascript">
	var gradeId = '${gradeId}';
	var categoryId = '${categoryId}';
	
	$(function() {

	});

	function searchList() {
		var actualName = $("#actualName").val();
		var name = $("#name").val();

		var queryData = {
			actualName : actualName,
			name : name,

		};
		getData(queryData);
	}

	//添加
	function addFun() {
		var url = '${pageContext.request.contextPath}/bookController/addBookpart.html?gradeId='+gradeId+"&categoryId="+categoryId;
		parent.$
				.modalDialog({
					title : '添加分册',
					width : 400,
					height : 300,
					href : url,
					buttons : [ {
						text : '添加',
						handler : function() {
							//parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
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
		parent.$
				.modalDialog({
					title : '编辑管理员信息',
					width : 400,
					height : 300,
					href : '${pageContext.request.contextPath}/managerController/editAdmin.html?id='
							+ id,
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
												'${pageContext.request.contextPath}/managerController/removeAdmin.html',
												{
													id : id
												},
												function(result) {
													if (result.success) {
														parent.$.messager
																.alert(
																		'提示',
																		result.msg,
																		'info');
														dataGrid
																.datagrid('reload');
													} else {
														parent.$.messager
																.alert(
																		'错误',
																		result.msg,
																		'error');
														dataGrid
																.datagrid('reload');
													}
													parent.$.messager
															.progress('close');
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

	function setPassFun() {
		var url = "${pageContext.request.contextPath}/managerController/setUserPass.html";
		setrow(url, 'dataGrid', 'id');
	}
</script>
</head>
<body>



	<div style="width: 100%; text-align: center;">
		<table
			style="text-align: center; width: 100%; background-color: #F4F4F4;">
			<tr>
				<td align="center">
					<table style="text-align: center;">
						<tr style="line-height: 30px;">
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="addFun();" href="javascript:void(0);"
								class="toolbar-btn"
								data-options="plain:true,iconCls:'pencil_add'">添加分册</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="addFun();" href="javascript:void(0);"
								class="toolbar-btn"
								data-options="plain:true,iconCls:'pencil_add'">编辑分册</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>


		<table style="width: 100%; margin-top: 20px;">
			<tr align="center">
				<td>
					<div style="position: relative; width: 124px; height: 175px;">
						<img src="${pageContext.request.contextPath}/images/fm.png"
							width="124" height="176" alt=""> <span
							style="position: absolute; top: 60px; left: 5px; right: 5px; font-weight: bold;">
							添加文字添加 </span>
					</div>
				</td>
				<td>
					<div style="position: relative; width: 124px; height: 175px;">
						<img src="${pageContext.request.contextPath}/images/fm.png"
							width="124" height="176" alt=""> <span
							style="position: absolute; top: 60px; left: 5px; right: 5px; font-weight: bold;">
							添加文字添加 </span>
					</div>
				</td>
				<td>
					<div style="position: relative; width: 124px; height: 175px;">
						<img src="${pageContext.request.contextPath}/images/fm.png"
							width="124" height="176" alt=""> <span
							style="position: absolute; top: 60px; left: 5px; right: 5px; font-weight: bold;">
							添加文字添加 </span>
					</div>
				</td>
				<td>
					<div style="position: relative; width: 124px; height: 175px;">
						<img src="${pageContext.request.contextPath}/images/fm.png"
							width="124" height="176" alt=""> <span
							style="position: absolute; top: 60px; left: 5px; right: 5px; font-weight: bold;">
							添加文字添加 </span>
					</div>
				</td>
			</tr>
			<tr align="center">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
</body>
</html>