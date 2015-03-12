<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/coursewareController/createcoursewarecategory.html',
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
					//parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
				   location.reload();
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
		<input type="hidden" id="id" name="id" value="${id}">
			<table class="table table-hover table-condensed">
				<tr>
					<th>名称：</th>
					<td><input name="name" type="text" placeholder="请输入名称" class="easyui-validatebox span3" data-options="required:true" value=""></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>排序：</th>
					<td><input name="sortFlag" type="text" placeholder="请输入排序" class="easyui-validatebox span2" data-options="required:true" value="">
					</td>
					<th></th>
					<td></td>
				</tr>
	
			</table>
		</form>
	</div>
</div>