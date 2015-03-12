<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>本单位管理</title>
<jsp:include page="../../incApp.jsp"></jsp:include>
<script type="text/javascript">
function addFun() {
	if ($('#mainForm').form('validate')) {
		$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		$.post('${pageContext.request.contextPath}/organController/modifyselforgan.html', $('#mainForm').serialize(), function(result) {
			if (result.success) {
				$('#registerDialog').dialog('close');
				$.messager.show({
					title : '提示',
					msg : result.msg,
					showType : 'slide'
				});
			} else {
				$.messager.alert('错误', result.msg, 'error');
			}
			$.messager.progress('close');
		}, "JSON");
	}
}

</script>
</head>

<div class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'north',title:'编辑----保存',border:false" style="height: 300px; overflow: hidden;">
<div id="toolbar" >
		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">保存</a>
</div>
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form  name="mainForm" id="mainForm" action="" method="post">
		<input type="hidden" id="id" name="id" value="${organ.id}">
			<table class="table table-hover table-condensed">
				<tr>
					<th>单位名称</th>
					<td colspan="3"><input id="name" name="name" style="width: 375px; height: 18px;" data-options="editable:false"  value="${organ.name}"></td>
				</tr>
				<tr>
					<th>单位级别</th>
					<td><input name="grade" style="width: 155px; height: 18px;" value="${organ.grade}"></td>
					<th>联系人</th>
					<td><input name="linkman" style="width: 155px; height: 18px;" value="${organ.linkman}"></td>
				</tr>
				<tr>
					<th>联系电话</th>
					<td><input name="tel" style="width: 155px; height: 18px;" value="${organ.tel}"></td>
					<th></th>
				    <td></td>
				</tr>
			</table>
		</form>
	</div>
	</div>
</div>
</html>