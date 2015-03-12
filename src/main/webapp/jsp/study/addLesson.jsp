<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/courseController/createLesson.html',
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
			<table class="table table-hover table-condensed">
				<tr>
					<th>班级：</th>
					<td><select name="classId" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<c:forEach items="${classList}" var="list">
								<option value="${list.id}">${list.name}</option>
							</c:forEach>
					</select></td>
					<th>科目：</th>
					<td><select name="subjectId" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<c:forEach items="${subjectList}" var="list">
								<option value="${list.id}">${list.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>星期：</th>
					<td><select name="day" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="1">星期一</option>
							<option value="2">星期二</option>
							<option value="3">星期三</option>
							<option value="4">星期四</option>
							<option value="5">星期五</option>
							<option value="6">星期六</option>
							<option value="7">星期日</option>
					</select></td>
					
					<th>节数：</th>
					<td><select name="thetime" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="1">第一节</option>
							<option value="2">第二节</option>
							<option value="3">第三节</option>
							<option value="4">第四节</option>
							<option value="5">第五节</option>
							<option value="6">第六节</option>
							<option value="7">第七节</option>
							<option value="8">第八节</option>
							<option value="9">第九节</option>
							<option value="10">第十节</option>
					</select></td>
				</tr>
					<tr>
					<th>学期：</th>
					<td><select name="term" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="1">第一学期</option>
							<option value="2">第二学期</option>
					</select></td>
					
				</tr>
			</table>
		</form>
	</div>
</div>