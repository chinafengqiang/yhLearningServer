<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		submitForm("${pageContext.request.contextPath}/dataSyncController/saveUser.html");
	});
</script>
<form method="post" class="form" id="form">
		<fieldset>
			<table class="table" style="width: 100%;">

				<tr>
				<th width="25%">用户名</th>
				<td>
				<input type="text" name="USERNAME" class="easyui-validatebox" data-options="required:true" />
				<input type="hidden" name="TYPE"  value="4"/>
				<input type="hidden" name="IS_VALID"  value="1"/>
				</td>
				</tr>
				<tr>
				<th width="25%">昵称</th>
				<td><input type="text" name="PASSWORD" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
				<th width="25%">所属分组</th>
				<td>
			   <select name="GID" id="gid" style="width: 165px;height: 30px;" class="easyui-validatebox" data-options="required:true"></select>
		       <script type="text/javascript">
					attachSelectBox(document.getElementById("gid"),'',"${pageContext.request.contextPath}/dataSyncController/getGroupSelect.html");	
			   </script>
				</td>
				</tr>
			</table>
		</fieldset>
	</form>