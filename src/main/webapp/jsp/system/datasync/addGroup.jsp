<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(function() {
		submitForm("${pageContext.request.contextPath}/dataSyncController/saveGroup.html");
	});
</script>
<form method="post" class="form" id="form">
		<fieldset>
			<table class="table" style="width: 100%;">

				<tr>
				<th width="25%">分组名称</th>
				<td><input type="text" name="NAME" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				
			</table>
		</fieldset>
	</form>