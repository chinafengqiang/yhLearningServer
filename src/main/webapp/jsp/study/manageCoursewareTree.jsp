<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../incApp.jsp"></jsp:include>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <link rel="StyleSheet" href="<%=request.getContextPath()%>/jslib/dtree/dtree.css" type="text/css" />
  <script type="text/javascript" src="<%=request.getContextPath()%>/jslib/dtree/dtree.js"></script>  
  <script type="text/javascript" src="<%=request.getContextPath()%>/jslib/dtree/dtree_menu.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/jslib/jquery-1.8.3.js"></script>
  <script type="text/javascript">
  var dataGrid;
  $(function() {

		dataGrid = $('#dataGrid').datagrid({
			url : '',
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'createdTime',
			loadMsg : '数据装载中......',
			sortOrder : 'asc',
			checkOnSelect : false,
			selectOnCheck : false,
			rownumbers : true,
			nowrap : false,
			columns : [ [  {
				field : 'actualName',
				title : '姓名',
				width : 150,
				sortable : true
			}, {
				field : 'superGrade',
				title : '年级',
				width : 80,
				sortable : true
			}, {
				field : 'superClass',
				title : '班级',
				width : 80,
				sortable : true
			}, {
				field : 'sex',
				title : '性别',
				width : 80,
				sortable : true
			}, {
				field : 'nation',
				title : '民族',
				width : 80
			},  {
				field : 'statusName',
				title : '状态',
				width : 80
			}, {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
	                var e = '<a href="#" mce_href="#" onclick="editFun(\''+ row.id + '\')">编辑</a> ';  
	                var r = '<a href="#" mce_href="#" onclick="deleteFun(\''+ row.id +'\')">删除</a> ';  
	        		var o = '<a href="#" mce_href="#" onclick="openFun(\''+ row.id + '\')">启用</a> ';  
	        		var c = '<a href="#" mce_href="#" onclick="closeFun(\''+ row.id + '\')">停用</a> ';  
	                return e+r+o+c;  
				}
			} ] ],
			toolbar : '#toolbar',
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll').datagrid('uncheckAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
			

		});
	});
  
  
    function check(form)
    {
       if(form.method.value != 'delete')
       {
	         if(form.name.value == "")
	         {
	           jAlert("请填写组织名称！","操作提示",function(r) {});
	           return false;
	         }
	         else
	           return true;
       }
       else if(form.id.value == ""){
       		 jAlert("请选择组织！","操作提示",function(r) {});
	         return false;
       }else{
        	 return true;
       }
    }
    
    function returnFun(){
    	window.location = "${pageContext.request.contextPath}/coursewareController/createCoursewareNodeList.html";
    }
      
  </script>
</head>

<body>
<div id="">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" id="index_content">
            	<tr>
                	
                 <td valign="middle" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1">
					  <tr>
						<td height="21" background="<%=request.getContextPath()%>/image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="<%=request.getContextPath()%>/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">课件分类管理</span></td>
					  </tr>
					</table>
				</td>
				 </tr>
                <tr>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" id="selectTable_content">
							 <tr>
						<td width="40%" height="19" bgcolor="#f2faff"
							style="font-size: 12px;">&nbsp;点击右键进行编辑操作</td>
                	</tr>
                </tr>
                <tr>
                <form name="mainForm" method="post" action="" onsubmit="return check(this);">
				    <input type="hidden" name="method" value="list">
				    <input type="hidden" name="id" value="">
			        <input type="hidden" name="pid" value="">
                	<td class="last">
					 <div class="dtree">
			           <a href="javascript: d.openAll();"><img src="<%=request.getContextPath()%>/images/onAll.png"/></a> | <a href="javascript: d.closeAll();"><img src="<%=request.getContextPath()%>/images/PackUpAll.png"/></a>
			             <script type="text/javascript">
			             
			                   d = new dTree('d');	          
			                  d.add('0', -1, '电子教学系统','#');
			                  	<c:forEach items="${nodeList}"  var="list">
			                  		 d.add( '${list.id }','${list.parentId }','${list.name }','#');
			                   </c:forEach>
			                  document.write(d); 
			                  d.openTo(0, true);
			                  
			             </script>
			           	</div>
					</td>
				</form>
                </tr>
                </table>
            </table>
</div>
<table id="dataGrid"></table>
 <div id="dtreeContextMenu" onclick="event.cancelBubble = true;">
	  <ul>
    	<li><a href="javascript: dtreeContextMenu_add('<%=request.getContextPath()%>/coursewareController/addCategory.html')">增加</a></li>
   	 	<li><a href="javascript: dtreeContextMenu_edit('<%=request.getContextPath()%>/coursewareController/editCategory.html?method=create')">修改</a></li>
    	<li><a href="javascript: dtreeContextMenu_delete('<%=request.getContextPath()%>/coursewareController/removecoursewarecategory.html?method=0')">删除</a></li>
	  </ul>
	 </div>
</body>
</html>