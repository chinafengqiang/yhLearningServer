<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		$('#pid').combotree({
			url : '${pageContext.request.contextPath}/coursewareController/allTreeByCourseware.html',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			value : '${course.coursewareCategoryId}',
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});
		
		$('#form').form({
			url : '${pageContext.request.contextPath}/courseController/modifyCourse.html',
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
		var URL = "${pageContext.request.contextPath}/coursewareController/uploadFowords.html?id="+Math.ceil(Math.random()*35);
		
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
	
	function  uploadPic(){
		var URL = "${pageContext.request.contextPath}/coursewareController/uploadPic.html?id="+Math.ceil(Math.random()*35);		
		if(window.ActiveXObject){ //IE  
			var str = window.showModalDialog(URL,'a2',"dialogWidth=420px;dialogHeight=305px;help=0");
			if(typeof(str) != 'undefined'){
				setPicValue(str);
			}
		}else{
			window.open(URL, 'newwindow2','height=305,width=420,top=150,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');  
		}
	}
	
	function setPicValue(str){  
		document.getElementById("pic").value = str;
	} 
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
			<form id="form" method="post">
		<input type="hidden" id="id" name="id"  value="${course.id}">
			<table class="table table-hover table-condensed">
				<tr>
					<th>课程名称：</th>
					<td><input name="name" type="text" placeholder="请输入名称" class="easyui-validatebox span3" data-options="required:true" value="${course.name}"></td>
					<th>分类：</th>
					<td><select id="pid" name="pid" style="width: 140px; height: 29px;" onclick="$('#pid').combotree('clear');"></select>
					 <img src="<%=request.getContextPath() %>/style/images/extjs_icons/cut_red.png"  onclick="$('#pid').combotree('clear');" /></td>
				</tr>
					<tr>
					
				<th>所属年级</th>
					<td>
					 <select name="gradeId" id="grade_id" style="width:160px;height: 30px;"></select>
			       <script type="text/javascript">
			       attachGradeSelectBox(document.getElementById("grade_id"),'${course.gradeId}',"${pageContext.request.contextPath }/sysGradeController/getGradeJson.html;");	
					</script>
					</td>	
									<th>是否公开</th>
					<td>
					 <input type="hidden" id="isPublic" name="isPublic" value="${course.isPublic}">
					  <input type="checkbox" id="isPublicV">
			       <script type="text/javascript">
			       attachCheckBox(document.getElementById("isPublicV"),document.getElementById("isPublic"));
					</script>
					</td>
				</tr>
				<tr>
					<th>附件名称：</th>
					<td><input id="url" name="url" type="text" placeholder="" data-options="required:true" class="easyui-validatebox span4"   value="${course.url}">
					<img src="<%=request.getContextPath()%>/images/shangc.gif"  style="cursor:pointer;" onclick="uploadSave()">
					
					
					<th>课程图片：</th>
					<td><input id="pic" name="pic" type="text" placeholder="" class="easyui-validatebox span2" data-options=""  value="${course.pic}">
					<img src="<%=request.getContextPath()%>/images/shangc.gif"  style="cursor:pointer;" onclick="uploadPic()">
					</td>
	
				</tr>
				<tr>
					<th>主讲人：</th>
					<td><input name="lectuer" type="text" placeholder="请输入主讲人" class="easyui-validatebox span3" data-options="" value="${course.lectuer}"></td>
					<th>课程简介：</th>
					<td><textarea name="description" id="description" placeholder="" class="easyui-validatebox span4"  rows="3" cols="20" class="text">${course.description}</textarea>
					</td>
					
				</tr>
				<!--<tr>
					<th>老师简介：</th>
					<td><textarea name="teacherDescription" id="teacherDescription" placeholder="请输入" class="easyui-validatebox span4"  rows="3" cols="30" class="text"></textarea>
					</td>
					<th></th>
					<td></td>
				</tr>-->
				
	
			</table>
		</form>
	</div>
</div>