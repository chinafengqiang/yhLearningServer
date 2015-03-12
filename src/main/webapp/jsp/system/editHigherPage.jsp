<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/organController/modifyhigherorgan.html',
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
		<input type="hidden" id="id" name="id" value="${organ.id}">
			<table class="table table-hover table-condensed">
				<tr>
					<th>单位名称：</th>
					<td><input name="name" type="text" placeholder="请输入单位名称" class="easyui-validatebox span2" data-options="required:true"  value="${organ.name}"></td>
					<th>单位级别：</th>
					<td><input name="grade" type="text" placeholder="请输入单位级别" class="easyui-validatebox span2" data-options="required:true"  value="${organ.grade}">
					</td>
				</tr>
					<tr>
					<th>联系人：</th>
					<td><input name="linkman" type="text" placeholder="请输入联系人" class="easyui-validatebox span2" data-options="required:true"  value="${organ.linkman}"></td>
					<th>电话：</th>
					<td><input name="tel" type="text" placeholder="请输入电话" class="easyui-validatebox span2" data-options="required:true"  value="${organ.tel}">
					</td>
				</tr>
				<tr>
					<th>服务器IP：</th>
					<td><input name="serverIp" type="text" placeholder="请输入服务器IP" class="easyui-validatebox span2" data-options="required:true"  value="${organ.serverIp}"></td>
					<th>服务器端口：</th>
					<td><input name="serverPort" type="text" placeholder="请输入服务器端口" class="easyui-validatebox span2" data-options="required:true"  value="${organ.serverPort}">
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>