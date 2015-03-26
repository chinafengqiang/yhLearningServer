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
		getData();
	});

	//row.find("#ID").text(obj[i].CKRID); 

	function getData() {

		var url = '${pageContext.request.contextPath}/bookController/getBookpart.html';
		$.getJSON(url, {
			gradeId : gradeId,
			categoryId : categoryId
		}, function(json) {
			$("#dataTB tr").eq(1).nextAll().remove(); //将除模板行的tr删除
			var size = json.length;
			var row;
			var optrow;
			for (var i = 1; i <= size; i++) {
				var tag = i % 4;
				//alert(tag);
				if (tag == 1) {
					row = $("#dataTr").clone();
					row.show();
					row.appendTo("#dataTB");

					optrow = $("#dataTropt").clone();
					optrow.show();
					optrow.appendTo("#dataTB");
				}
				row.find("#td" + tag).show();
				row.find("#sp" + tag).text(json[i - 1].NAME);
				row.find("#tdiv" + tag).click({
					name : json[i - 1].NAME,
					back : json[i - 1].ID
				}, operation);

				optrow.find("#tdopt" + tag).show();
				optrow.find("#ck" + tag).val(json[i - 1].ID);
			}

		});
	}

	function operation(event) {
		var name = event.data.name;
		var id = event.data.back;
		var encname = encodeURI(name);
		var url = '${pageContext.request.contextPath}/bookController/managerBookresource.html?partId='+id+"&name="+encname;
		self.parent.addTab(name+'资源管理',url,'');
	}


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
		var url = '${pageContext.request.contextPath}/bookController/addBookpart.html?gradeId='
				+ gradeId + "&categoryId=" + categoryId;
		parent.$.modalDialog({
			title : '添加分册',
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
		var obj = $("input:checked");
		if (obj.length <= 0) {
			alert("请选择要编辑的分册");
			return;
		}
		if (obj.length > 1) {
			alert("只能选择一个进行编辑");
			return;
		}

		var idObj = obj[0];
		var id = idObj.value;
		var url = '${pageContext.request.contextPath}/bookController/editBookpart.html?id='
				+ id;

		parent.$.modalDialog({
			title : '编辑分册',
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
	function deleteFun(id) {

		var obj = $("input:checked");
		if (obj.length <= 0) {
			alert("请选择要删除的分册");
			return;
		}

		var ids="";
		$.each(obj, function(i, item) {
			ids += $(this).val()+",";
		});
		
		
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
								$.post(
												'${pageContext.request.contextPath}/bookController/deleteBookpart.html',
												{
													ids : ids
												},
												function(result) {
													if (result.success) {
														parent.$.messager
																.alert(
																		'提示',
																		"操作成功",
																		'info');
														getData();
													} else {
														parent.$.messager
																.alert(
																		'错误',
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
							<td><a onclick="editFun();" href="javascript:void(0);"
								class="toolbar-btn"
								data-options="plain:true,iconCls:'pencil_add'">编辑分册</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="deleteFun();" href="javascript:void(0);"
								class="toolbar-btn"
								data-options="plain:true,iconCls:'pencil_add'">删除分册</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>


		<table style="width: 100%; margin-top: 20px;" id="dataTB">
			<tr align="center" id="dataTr" style="display: none;">
				<td id="td1" style="display: none; cursor: pointer;">
					<div id="tdiv1" style="position: relative; width: 124px; height: 175px;"
						title="点击进行目录及资源管理">
						<img src="${pageContext.request.contextPath}/images/fm.png"
							width="124" height="176" alt=""> <span id="sp1"
							style="position: absolute; top: 60px; left: 5px; right: 5px; font-weight: bold;">
						</span>
					</div>
				</td>
				<td id="td2" style="display: none; cursor: pointer;">
					<div id="tdiv2" style="position: relative; width: 124px; height: 175px;"
						title="点击进行目录及资源管理">
						<img src="${pageContext.request.contextPath}/images/fm.png"
							width="124" height="176" alt=""> <span id="sp2"
							style="position: absolute; top: 60px; left: 5px; right: 5px; font-weight: bold;">
						</span>
					</div>
				</td>
				<td id="td3" style="display: none; cursor: pointer;">
					<div id="tdiv3" style="position: relative; width: 124px; height: 175px;"
						title="点击进行目录及资源管理">
						<img src="${pageContext.request.contextPath}/images/fm.png"
							width="124" height="176" alt=""> <span id="sp3"
							style="position: absolute; top: 60px; left: 5px; right: 5px; font-weight: bold;">
						</span>
					</div>
				</td>
				<td id="td0" style="display: none; cursor: pointer;">
					<div id="tdiv0" style="position: relative; width: 124px; height: 175px;"
						title="点击进行目录及资源管理">
						<img src="${pageContext.request.contextPath}/images/fm.png"
							width="124" height="176" alt=""> <span id="sp0"
							style="position: absolute; top: 60px; left: 5px; right: 5px; font-weight: bold;">
						</span>
					</div>
				</td>
			</tr>
			<tr align="center" id="dataTropt" style="display: none;">
				<td id="tdopt1" style="display: none; padding-bottom: 30px;"><input
					type="checkbox" id="ck1"></td>
				<td id="tdopt2" style="display: none; padding-bottom: 30px;"><input
					type="checkbox" id="ck2"></td>
				<td id="tdopt3" style="display: none; padding-bottom: 30px;"><input
					type="checkbox" id="ck3"></td>
				<td id="tdopt0" style="display: none; padding-bottom: 30px;"><input
					type="checkbox" id="ck0"></td>

			</tr>
		</table>
	</div>
</body>
</html>