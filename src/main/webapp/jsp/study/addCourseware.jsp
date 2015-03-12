<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		$('#pid').combotree({
			url : '${pageContext.request.contextPath}/coursewareController/allTreeByCourseware.html',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});
		
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/coursewareController/createcourseware.html',
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
		var str = window.showModalDialog(URL,'a',"dialogWidth=420px;dialogHeight = 305px;help=0");
		if(typeof(str) != 'undefined'){
		    document.getElementById("url").value = str;
		}
	}
	
	function  uploadPic(){
		var URL = "${pageContext.request.contextPath}/coursewareController/uploadPic.html?id="+Math.ceil(Math.random()*35);
		var str = window.showModalDialog(URL,'a',"dialogWidth=420px;dialogHeight = 305px;help=0");
		if(typeof(str) != 'undefined'){
		    document.getElementById("pic").value = str;
		}
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
			<form id="form" method="post">
		<input type="hidden" id="coursewareCategoryId" name="coursewareCategoryId"  >
			<table class="table table-hover table-condensed">
				<tr>
					<th>课件图片：</th>
					<td><input id="pic" name="pic" type="text" placeholder="" class="easyui-validatebox span2"  readOnly="readonly"  value="">
					<img src="<%=request.getContextPath()%>/images/shangc.gif"  style="cursor:pointer;" onclick="uploadPic()">
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>课件名称：</th>
					<td><input name="name" type="text" placeholder="请输入名称" class="easyui-validatebox span3" data-options="required:true" value=""></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>分类：</th>
					<td><select id="pid" name="pid" style="width: 140px; height: 29px;"></select>
					 <img src="<%=request.getContextPath() %>/style/images/extjs_icons/cut_red.png"  onclick="$('#pid').combotree('clear');" /></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>附件名称：</th>
					<td><input id="url" name="url" type="text" placeholder="" class="easyui-validatebox span4"  readOnly="readonly"  value="">
					<img src="<%=request.getContextPath()%>/images/shangc.gif"  style="cursor:pointer;" onclick="uploadSave()">
					</td>
					<th>班级：</th>
					<td><select name="classId" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<c:forEach items="${classList}" var="list">
								<option value="${list.id}">${list.name}</option>
							</c:forEach>
					</select></td>
				</tr>
	
			</table>
		</form>
	</div>
</div>