<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/courseController/modifyClassBook.html',
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
	
	function  uploadSave(){
		var URL = "${pageContext.request.contextPath}/coursewareController/uploadFoword.html?id="+Math.ceil(Math.random()*35);
		if(window.ActiveXObject){ //IE  
			var str = window.showModalDialog(URL,'a1',"dialogWidth=420px;dialogHeight=305px;help=0");
			if(typeof(str) != 'undefined'){
				setValue(str);
			}
		}else{
			window.open(URL, 'newwindow','height=305,width=420,top=150,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');  
		}
	}
	
	function setValue(str){  
		document.getElementById("url").value = str;
	} 
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
		<input type="hidden" id="id" name="id"  value="${classBook.id}">
			<table class="table table-hover table-condensed">
				<tr>
					<th>名称：</th>
					<td><input name="name" type="text" placeholder="请输入名称" class="easyui-validatebox span3" data-options="required:true"   value="${classBook.name}"></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>附件名称：</th>
					<td><input id="url" name="imageUrl" type="text" placeholder="" class="easyui-validatebox span4"  readOnly="readonly""  value="${classBook.imageUrl}">
					<img src="<%=request.getContextPath()%>/images/shangc.gif"  style="cursor:pointer;" onclick="uploadSave()">
					</td>
					<th></th>
					<td></td>
				</tr>
	
			</table>
		</form>
	</div>
</div>