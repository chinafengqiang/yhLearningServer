<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/courseController/createCourseSchedule.html',
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
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
				<tr>
					<th>星期名称</th>
					<td><input name="weekName" type="text" placeholder="请输入星期" class="easyui-validatebox span2" data-options="required:true" value=""></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>第一节课</th>
					<td><input name="levelOne" type="text" placeholder="请输入课" class="easyui-validatebox span2" data-options="required:true" value=""></td>
					<th>第二节课</th>
					<td><input name="levelTwo" type="text" placeholder="请输入课" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<th>第三节课</th>
					<td><input name="levelThree" type="text" placeholder="请输入课" class="easyui-validatebox span2" data-options="required:true" value=""></td>
					<th>第四节课</th>
					<td><input name="levelFour" type="text" placeholder="请输入课" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<th>第五节课 </th>
					<td><input name="levelFive" type="text" placeholder="请输入课" class="easyui-validatebox span2" data-options="required:true" value=""></td>
					<th>第六节课 </th>
					<td><input name="levelSix" type="text" placeholder="请输入课" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<th>第七节课 </th>
					<td><input name="levelSeven" type="text" placeholder="请输入课" class="easyui-validatebox span2" data-options="required:true" value=""></td>
					<th>第八节课 </th>
					<td><input name="levelEight" type="text" placeholder="请输入课" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<th>年级</th>
					<td><select name="superGrade" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="高一">高一</option>
							<option value="高二">高二</option>
							<option value="高三">高三</option>
					</select></td>
					<th>班级</th>
					<td><select name="superClass" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="一班">一班</option>
							<option value="二班">二班</option>
							<option value="三班">三班</option>
							<option value="四班">四班</option>
							<option value="五班">五班</option>
							<option value="六班">六班</option>
							<option value="七班">七班</option>
							<option value="八班">八班</option>
							<option value="九班">九班</option>
							<option value="十班">十班</option>
							<option value="十一班">十一班</option>
							<option value="十二班">十二班</option>
							<option value="十三班">十三班</option>
					</select></td>
				</tr>
			</table>
		</form>
	</div>
</div>