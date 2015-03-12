<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(function() {
		submitForm("${pageContext.request.contextPath}/sysGradeController/updateGrade.html");
	});
	
	function attachOrgSelectBox(oInputField,value,url){
		jQuery.getJSON(url,{},function(data){
			var options = new Array();
			for (var i=0;i<data.length;i++){
				option = document.createElement('Option');
				option.text = data[i].name;
				option.value = data[i].id;
				try{
					oInputField.add(option,null);
				}catch(ex){
					oInputField.add(option);
				}
				if (option.value==value){
					option.selected=true;
				}
			}
		});
	}
</script>
<form method="post" class="form" id="form">
		<fieldset>
			<table class="table" style="width: 100%;">

				<tr>
				<th width="25%">年级名称</th>
				<td>
				<input type="hidden" name="id" value="${grade.id}">
				<input type="text" name="name" value="${grade.name }" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
				<th width="25%">所属学校</th>
				<td>
			 <select name="org_id" id="org_id" style="width:160px;height: 30px;"></select>
			       <script type="text/javascript">
			       attachOrgSelectBox(document.getElementById("org_id"),'${grade.org_id}',"${pageContext.request.contextPath }/sysGradeController/getOrgJson.html;");	
					</script>
				</tr>
			</table>
		</fieldset>
	</form>