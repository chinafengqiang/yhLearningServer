<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		
		$('#form').form({
			
			url : '${pageContext.request.contextPath}/courseController/importLessonPlan.html',
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
		<form id="form" enctype="multipart/form-data" method="post">  
			<table class="table table-hover table-condensed">
				<tr>
				<th width="40%">
				开始时间： 
				</th>
					<td>					
			 <input name="startTime" id="startTime" class="easyui-validatebox span3"
				onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" data-options="required:true" placeholder="请输入开始时间">
				<input type="hidden" name="lessonId" value="${lessonId}">
					</td>
				</tr>
				<tr>
				<th>
				结束时间： 
				</th>
					<td>					
			 <input name="endTime" id="endTime" class="easyui-validatebox span3"
				onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" data-options="required:true" placeholder="请输入结束时间">
					</td>
				</tr>
				<tr>
					<th>
					  教学计划Excel文件
					</th>
					<td>
					<input id="exportexcel" name="exportexcel" type="hidden" />  
                      <input  id="file" name="file" type="file"  style="width: 400px; background: White" class="easyui-validatebox" validtype="length[1,100]" />  
					</td>
					
				</tr>
			</table>
		</form>
	</div>
</div>