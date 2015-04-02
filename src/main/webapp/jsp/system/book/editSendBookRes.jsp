<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(function() {
	parent.$.messager.progress('close');
	var url = '${pageContext.request.contextPath}/bookController/sendBooks.html';
	var ids = '${ids}';
	if(ids != ""){
		url = "${pageContext.request.contextPath}/bookController/sendBooksBatch.html";
	}
	$('#form').form({
		url : url,
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
	
	
function attachResMPathSelectBox(oInputField,value,url){
	jQuery.getJSON(url,{},function(data){
		var options = new Array();
		for (var i=0;i<data.length;i++){
			option = document.createElement('Option');
			option.text = data[i].name;
			option.value = data[i].m_path;
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


<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
		<input type="hidden" id="id" name="id"  value="${id}">
		<input type="hidden" id="ids" name="ids"  value="${ids}">
			<table class="table table-hover table-condensed">
				<tr>
					<th>发送带宽：</th>
					<td><input name="bandwidth" type="text" placeholder="请输入发送带宽" class="easyui-validatebox span3" 
					data-options="required:true"  value="1000">(kpbs)</td>
	
				</tr>
				<tr>
					<th>重发次数：</th>
					<td><input name="repeatcount" type="text" placeholder="请输入发送带宽" class="easyui-validatebox span3" 
					data-options="required:true"  value="1">(次)</td>
			
				</tr>
				<tr>
					<th>开始发送时间：</th>
					<td><input class="easyui-validatebox span3" value="${firstTime}" name="beginTime" placeholder="点击开始发送时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</td>
		
				</tr>
				<tr>
					<th>结束发送时间：</th>
					<td><input class="easyui-validatebox span3" value="${firstTime}" name="endTime" placeholder="点击结束发送时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</td>
				</tr>
				
				<tr>
					<th>投递到的地址：</th>
					<td>
					<select name="m_path" id="m_path" style="width:210px;height: 30px;"></select>
			       <script type="text/javascript">
			       attachResMPathSelectBox(document.getElementById("m_path"),'',"${pageContext.request.contextPath }/coursewareController/getResMPathJson.html;");	
					</script>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>