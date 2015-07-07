<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		
		$('#form').form({
			
			url : '${pageContext.request.contextPath}/courseController/updateLessonMsg.html',
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
					parent.$.messager.alert('正确', result.msg, '');
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
				<th>
				所属年级： 
				</th>
					<td>					
			<select name="GRADE_ID" id="grade_id" style="width:160px;height: 40px;">
			</select>
			       <script type="text/javascript">
			       attachGradeSelectBox(document.getElementById("grade_id"),'${msg.GRADE_ID}',"${pageContext.request.contextPath }/sysGradeController/getGradeJson.html;");	
					</script>
					</td>
				</tr>
								<tr>
				<th>
				标题：
				</th>
					<td>					
			<input name="TITLE" type="text" placeholder="请输入标题" class="easyui-validatebox span3" data-options="required:true" value="${msg.TITLE }">
					<input name="ID" value="${msg.ID }" type="hidden">
					</td>
				</tr>
								<tr>
				<th>
				内容： 
				</th>
					<td>					
		<input name="CONTENT" type="text" placeholder="请输入内容" class="easyui-validatebox span3" data-options="required:true" value="${msg.CONTENT }">
					</td>
				</tr>
				<tr>
				<th>
				开始时间： 
				</th>
					<td>					
			 <input name="START_DATE" id="startTime" class="easyui-validatebox span3" value="${msg.START_DATE }"
				onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" data-options="required:true" placeholder="请输入开始时间">
					</td>
				</tr>
				<tr>
				<th>
				结束时间： 
				</th>
					<td>					
			 <input name="END_DATE" id="endTime" class="easyui-validatebox span3" value="${msg.END_DATE }"
				onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" data-options="required:true" placeholder="请输入结束时间">
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>