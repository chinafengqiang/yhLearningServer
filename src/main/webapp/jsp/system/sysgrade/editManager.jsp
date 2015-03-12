<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/managerController/modifymanager.html',
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
		<input type="hidden" id="id" name="id" value="${manager.id}">
			<table class="table table-hover table-condensed">
				<tr>
					<th>登录帐号：</th>
					<td><input name="name" type="text" placeholder="请输入登录帐号" class="easyui-validatebox span2" data-options="required:true"  value="${manager.name}"></td>
					<th>姓名：</th>
					<td><input name="actualName" type="text" placeholder="请输入姓名" class="easyui-validatebox span2" data-options="required:true"  value="${manager.actualName}">
					</td>
				</tr>
				<tr>
					<th>部门：</th>
					<td><input name="department" type="text" placeholder="请输入部门" class="easyui-validatebox span2" data-options="required:true" value="${manager.department}"></td>
					<th>职务：</th>
					<td><input name="post" type="text" placeholder="请输入职务" class="easyui-validatebox span2" data-options="required:true"  value="${manager.post}"></td>
				</tr>
				<tr>
					<th>教师类型：</th>
					<td><select name="useType" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="0"  <c:if test="${manager.useType == 0 }"> selected="selected"</c:if> >主教</option>
							<option value="1"  <c:if test="${manager.useType == 1 }"> selected="selected"</c:if> >助教</option>
					</select></td>
					<th>班级：</th>
					<td><select name="classId" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<c:forEach items="${classList}" var="list">
								<option value="${list.id}" <c:if test="${list.id == manager.classId}">selected="selected"</c:if>>${list.name}</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
		</form>
	</div>
</div>