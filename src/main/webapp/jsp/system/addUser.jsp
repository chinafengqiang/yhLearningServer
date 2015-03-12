<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/userController/createuser.html',
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
					<th></th>
					<td><font color="red">学员初始化密码: 888888</font></td>
					<th></th>
					<td>
					</td>
					
					
					<input name="classId" value="${classId}" type="hidden">
				</tr>
				<tr>
					<th>登录帐号：</th>
					<td><input name="name" type="text" placeholder="请输入登录帐号" class="easyui-validatebox span2" data-options="required:true" value=""></td>
					<th>姓名：</th>
					<td><input name="actualName" type="text" placeholder="请输入姓名" class="easyui-validatebox span2" data-options="required:true" value="">
					</td>
				</tr>
				<tr>
					<th>性别：</th>
					<td><select name="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="男">男</option>
							<option value="女">女</option>
					</select></td>
					<th>年龄：</th>
					<td><input name="age" type="text" placeholder="请输入年龄" class="easyui-validatebox span2" data-options="required:true" value="16"></td>
				</tr>
				<tr>
					<th>电子邮件：</th>
					<td><input name="email" type="text" placeholder="请输入电子邮件" class="easyui-validatebox span2" data-options="required:true" value="123@qq.com
					"></td>
					<th>民族：</th>
					<td><input name="nation" type="text" placeholder="请输入民族" class="easyui-validatebox span2" data-options="required:true" value="汉"></td>
				</tr>
				<tr>
					<th>出生日期：</th>
					<td colspan="3"><input class="span2" name="birthdate" placeholder="点击选择出生日期" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					</td>
					<!--<th>班级：</th>
					<td>
					<select name="classId" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<c:forEach items="${classList}" var="list">
								<option value="${list.id}">${list.name}</option>
							</c:forEach>
					</select>
					</td>-->
				</tr>
				<tr>
					<th>通信地址：</th>
					<td colspan="3"><textarea name="address" id="address" placeholder="请输入通信地址" class="easyui-validatebox span4" data-options="" rows="15" cols="55" class="text"></textarea>
					</td>
					
				</tr>
			</table>
		</form>
	</div>
</div>