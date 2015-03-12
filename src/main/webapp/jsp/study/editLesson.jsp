<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/courseController/modifyLesson.html',
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
		<input type="hidden" id="id" name="id" value="${lesson.id}">
			<table class="table table-hover table-condensed">
				<tr>
					<th>班级：</th>
					<td><select name="classId" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<c:forEach items="${classList}" var="list">
								<option value="${list.id}" <c:if test="${list.id == lesson.classId}">selected="selected"</c:if>>${list.name}</option>
							</c:forEach>
					</select></td>
					<th>科目：</th>
					<td><select name="subjectId" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<c:forEach items="${subjectList}" var="list">
								<option value="${list.id}" <c:if test="${list.id == lesson.subjectId}">selected="selected"</c:if>>${list.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>星期：</th>
					<td><select name="day" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="1" <c:if test="${lesson.day == 1 }"> selected="selected"</c:if>>星期一</option>
							<option value="2" <c:if test="${lesson.day == 2 }"> selected="selected"</c:if>>星期二</option>
							<option value="3" <c:if test="${lesson.day == 3 }"> selected="selected"</c:if>>星期三</option>
							<option value="4" <c:if test="${lesson.day == 4 }"> selected="selected"</c:if>>星期四</option>
							<option value="5" <c:if test="${lesson.day == 5 }"> selected="selected"</c:if>>星期五</option>
							<option value="6" <c:if test="${lesson.day == 6 }"> selected="selected"</c:if>>星期六</option>
							<option value="7" <c:if test="${lesson.day == 7 }"> selected="selected"</c:if>>星期日</option>
					</select></td>
					
					<th>节数：</th>
					<td><select name="thetime" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="1" <c:if test="${lesson.thetime == 1 }"> selected="selected"</c:if>>第一节</option>
							<option value="2" <c:if test="${lesson.thetime == 2 }"> selected="selected"</c:if>>第二节</option>
							<option value="3" <c:if test="${lesson.thetime == 3 }"> selected="selected"</c:if>>第三节</option>
							<option value="4" <c:if test="${lesson.thetime == 4 }"> selected="selected"</c:if>>第四节</option>
							<option value="5" <c:if test="${lesson.thetime == 5 }"> selected="selected"</c:if>>第五节</option>
							<option value="6" <c:if test="${lesson.thetime == 6 }"> selected="selected"</c:if>>第六节</option>
							<option value="7" <c:if test="${lesson.thetime == 7 }"> selected="selected"</c:if>>第七节</option>
							<option value="8" <c:if test="${lesson.thetime == 8 }"> selected="selected"</c:if>>第八节</option>
							<option value="9" <c:if test="${lesson.thetime == 9 }"> selected="selected"</c:if>>第九节</option>
							<option value="10" <c:if test="${lesson.thetime == 10 }"> selected="selected"</c:if>>第十节</option>
					</select></td>
				</tr>
					<tr>
					<th>学期：</th>
					<td><select name="term" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="1" <c:if test="${lesson.term == 1 }"> selected="selected"</c:if>>第一学期</option>
							<option value="2" <c:if test="${lesson.term == 2 }"> selected="selected"</c:if>>第二学期</option>
					</select></td>
				</tr>
			</table>
		</form>
	</div>
</div>