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
			url : '${pageContext.request.contextPath}/courseController/createCoursePlan.html',
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
					<th>名称：</th>
					<td><input name="name" type="text" placeholder="请输入名称" class="easyui-validatebox span3" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<th>附件：</th>
					<td><input id="url" name="imageUrl" type="text" placeholder="" class="easyui-validatebox span4"  readOnly="readonly"  value="">
					<img src="<%=request.getContextPath()%>/images/shangc.gif"  style="cursor:pointer;" onclick="uploadSave()">
					</td>
				</tr>
				<tr>
					<th>所属学科：</th>
					<td colspan="3"><select name="post" id="category"
						style="width: 160px; height: 40px;">

					</select> <script type="text/javascript">
						attachGradeSelectBox(
								document.getElementById("category"),
								'${manager.post}',
								"${pageContext.request.contextPath }/coursewareController/getCoursewareCategorySelect.html;");
					</script></td>
				</tr>
				<tr>
					<th>所属年级</th>
					<td>
					 <select name="gradeId" id="grade_id" style="width:160px;height: 30px;"></select>
			        <script type="text/javascript">
			       		attachGradeSelectBox(document.getElementById("grade_id"),'',"${pageContext.request.contextPath }/sysGradeController/getGradeJson.html;");	
					</script>
					</td>
				</tr>
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
			</table>
		</form>
	</div>
</div>