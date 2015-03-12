<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/managerController/createmanager.html',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
			//	window.location = "${pageContext.request.contextPath}/coursewareController/createCoursewareNodeList.html";
				parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
				<tr>
					<th>登录帐号：</th>
					<td><input name="name" type="text" placeholder="请输入登录帐号" class="easyui-validatebox span2" data-options="required:true" value=""></td>
					<th>密码：</th>
					<td><input name="name" type="password" placeholder="请输入密码" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<th>姓名：</th>
					<td><input name="actualName" type="text" placeholder="请输入姓名" class="easyui-validatebox span2" data-options="required:true" value="">
					</td>
					<th>职务：</th>
					<td><input name="department" type="text" placeholder="请输入职业" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<th>职业：</th>
					<td><input name="post" type="text" placeholder="请输入职业" class="easyui-validatebox span2" data-options="required:true" value=""></td>
					<th>教师类型：</th>
					<td><td><select name="useType" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							
					</select></td>
				</tr>
	
			</table>
		</form>
	</div>
</div>