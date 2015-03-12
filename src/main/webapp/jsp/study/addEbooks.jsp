<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="<%=request.getContextPath() %>/jslib/jquery.ocupload.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/jslib/jquery.bpopup.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		$('#pid').combotree({
			url : '${pageContext.request.contextPath}/coursewareController/allTree.html',
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
		
		
		
		//点击导入按钮事件(******)
		$("#btnImportFromFile").upload({
	        action: '${pageContext.request.contextPath}/coursewareController/uploadFile.html',
	        name: 'file',
	        iframeName: 'ImportFromFile',
	        params: {},
	        onSelect: function (self, element) {
	            this.autoSubmit = false;
				this.submit();
	        },
	        onSubmit: function (self, element) {
	        },
	        onComplete: function (data, self, element) {
	        	var dataObject =  eval('(' + data + ')');;
				alert(dataObject.actionMessage);
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
<style>
<!--
.option_btn_g{width:66px; height:25px; background:#4a8227; color:#edf5ff; line-height:25px; cursor:pointer;}
-->
</style>


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
					<th>分类：</th>
					<td><select id="pid" name="pid" style="width: 140px; height: 29px;"></select>
					 <img src="<%=request.getContextPath() %>/style/images/extjs_icons/cut_red.png"  onclick="$('#pid').combotree('clear');" /></td>
					
				</tr>
				<tr>
					<th>附件名称：</th>
					<td><input id="url" name="url" type="text" placeholder="" class="easyui-validatebox span4" data-options="required:true"  value="">
						<img src="/images/shangc.gif"  style="cursor:pointer;" onclick="uploadSave()"> 
			
			 	<!--  <input id="btnImportFromFile" class="option_btn_g" type="button" value="上&nbsp;&nbsp;&nbsp;传">-->
					</td>
				</tr>
				<tr>
					<!--  <th>班级：</th>
					<td><select name="classId" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<c:forEach items="${classList}" var="list">
								<option value="${list.id}">${list.name}</option>
							</c:forEach>
					</select></td>-->
					<th>所属年级</th>
					<td>
					 <select name="gradeId" id="grade_id" style="width:160px;height: 30px;"></select>
			       <script type="text/javascript">
			       attachGradeSelectBox(document.getElementById("grade_id"),'',"${pageContext.request.contextPath }/sysGradeController/getGradeJson.html;");	
					</script>
					</td>
				</tr>
				<tr>
					<th>电子书图片：</th>
					<td><input id="pic" name="pic" type="text" placeholder="" class=""   value="">
					<img src="<%=request.getContextPath()%>/images/shangc.gif"  style="cursor:pointer;" onclick="uploadPic()">
					不添加图片或显示默认图片 
					</td>
					
				</tr>
	<tr>
					
					<th>是否公开</th>
					<td>
					 <input type="hidden" id="isPublic" name="isPublic" value="0">
					  <input type="checkbox" id="isPublicV">
			       <script type="text/javascript">
			       attachCheckBox(document.getElementById("isPublicV"),document.getElementById("isPublic"));
					</script>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>