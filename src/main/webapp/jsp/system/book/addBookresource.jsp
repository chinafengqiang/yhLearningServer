<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		var currTab =$('#tabs').tabs('getSelected');
		parent.$.messager.progress('close');
		$('#form')
				.form(
						{
							url : '${pageContext.request.contextPath}/bookController/createBookpart.html',
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
									//parent.frames[fsize-1].getData();
									currTab.find('iframe').get(0).contentWindow.getData();
									parent.$.modalDialog.handler
											.dialog('close');
									
								} else {
									parent.$.messager.alert('错误', result.msg,
											'error');
								}
							}
						});
	});


</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
				
				<tr>
					<th>目录名称：</th>
					<td>
					<input name="PART_ID" type="hidden" value="${partId}">
					<input name="PID" type="hidden" value="${pid}">
					<input name="NAME" type="text" placeholder="请输入目录名称"
						class="easyui-validatebox span2" data-options="required:true"
						value="">
						
				  </td>
					
				</tr>
					<tr>
					<th>是否上传资源：</th>
					<td>
					   <input type="hidden" id="IS_ADD_RES" name="IS_ADD_RES" value="0">
					  <input type="checkbox" id="IS_ADD_RESV">
			       <script type="text/javascript">
			       attachCheckBox(document.getElementById("IS_ADD_RESV"),document.getElementById("IS_ADD_RES"));
					</script>
						
				  </td>
					
				</tr>

			</table>
		</form>
	</div>
</div>