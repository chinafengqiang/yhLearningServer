<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form')
				.form(
						{
							url : '${pageContext.request.contextPath}/managerController/createmanager.html',
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
									parent.$.modalDialog.openner_dataGrid
											.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
									parent.$.modalDialog.handler
											.dialog('close');
								} else {
									parent.$.messager.alert('错误', result.msg,
											'error');
								}
							}
						});
	});

	$('#tt').tree({
		onCheck: function(node,checked){
			var isLeaf = $('#tt').tree('isLeaf', node.target);
	    		var parent = $('#tt').tree('getParent', node.target);
	    		if(!(isLeaf && parent.id > 0)&&checked){
	    			$('#tt').tree('uncheck', node.target);
	    			alert("此选项不是班级");
	    			return false;
	    		}
		}
	});
	
	function getCheckClass() {
		var nodes = $('#tt').tree('getChecked');
		if(nodes.length <= 0){
			alert("请选择任教班级！");
			return false;
		}
		var dept = "";
		for (var i = 0; i < nodes.length; i++) {
			var node = nodes[i];
			var isLeaf = $('#tt').tree('isLeaf', node.target);
	    		var parent = $('#tt').tree('getParent', node.target);
	    		if(isLeaf && parent.id > 0){
	    			dept += node.id+",";
	    		}else{
	    			
	    		}
		}
		if(dept==""){
			alert("请选择任教班级！每个树形节点的最后一级为班级。");
			return false;
		}else{
			dept = dept.substring(0,dept.length-1);
			$("#department").val(dept);
			alert($("#department").val());
		}
		
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post" onsubmit="getCheckClass()">
			<table class="table table-hover table-condensed">
				<tr>
					<th>
					<input type="hidden" name="department" id="department">
					</th>
					<td colspan="3"><font color="red">教师初始化密码: 999999</font></td>
					
				</tr>
				<tr>
					<th>登录帐号：</th>
					<td><input name="name" type="text" placeholder="请输入登录帐号"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
					<th>姓名：</th>
					<td><input name="actualName" type="text" placeholder="请输入姓名"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
				</tr>
				<tr>
					<th>所属科目：</th>
					<td colspan="3"><select name="post" id="category"
						style="width: 160px; height: 40px;">

					</select> <script type="text/javascript">
						attachGradeSelectBox(
								document.getElementById("category"),
								'',
								"${pageContext.request.contextPath }/coursewareController/getCoursewareCategorySelect.html;");
					</script></td>
				</tr>
				<tr>
					<th>任教班级：</th>
					<td colspan="3">
						<ul id="tt" class="easyui-tree"
							url="${pageContext.request.contextPath}/sysGradeController/getGradeAndClassTreeJson.html"
							checkbox="true" onlyLeafCheck="true" lines="true">
						</ul>
					</td>
				</tr>

			</table>
		</form>
	</div>
</div>