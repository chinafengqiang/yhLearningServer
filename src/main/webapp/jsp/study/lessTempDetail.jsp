<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
<!--
#table {
	border-collapse: collapse;
	border-spacing: 0;
	vertical-align: middle;
}

.table th,.table td {
	border: 1px solid #E3E3E3;
	padding: 0.3em 0.7em;
	text-align: center;
	vertical-align: middle;
}

#table th {
	background: #F8F8F8;
	text-align: center;
}

.table thead th {
	text-align: center;
}

.table tbody th {
	text-align: center;
}

.table tr:hover {
	background-color: #f6fafd;
}

.table td:hover {
	background-color: #f9feff;
}
-->
</style>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});
</script>
<div id="racePop" class="raceShow">这里是弹出层效果</div>
<div>
			<table class="table table-hover table-condensed" style="text-align: center;">
				<tr>
				<th>节数</th>
				<th>时间</th>
				<th>星期一</th>
				<th>星期二</th>
				<th>星期三</th>
				<th>星期四</th>
				<th>星期五</th>
				</tr>
				<c:forEach var="d" items="${resList}">
				<tr>
					<td ><a href="#">${d.LESSON_NUM}</a> </td>
					<td><a href="#">${d.LESSON_TIME}</a></td>
					<td ><a href="#" onclick="showPlan('${d.LESSON_ID}','${d.LESSON_NUM }')">${d.WEEK_ONE_LESSON}</a></td>
					<td><a href="#">${d.WEEK_TWO_LESSON}</a></td>
					<td><a href="#">${d.WEEK_THREE_LESSON}</a></td>
					<td id="race"><a href="#">${d.WEEK_FOUR_LESSON}</a></td>
					<td id="race"> <a href="#">${d.WEEK_FIVE_LESSON}</a></td>
				</tr>
				</c:forEach>
				
			</table>
			
</div>

<script type="text/javascript">
// 渐变弹出层
$(document).ready(function(){
 var speed = 600;//动画速度
 /*$("#race a").click(function(event){//绑定事件处理
  event.stopPropagation();
  //svar offset = $(event.target).offset();//取消事件冒泡
  //s$("#racePop").css({ top:offset.top + $(event.target).height() + "px", left:offset.left });//设置弹出层位置
  $("#racePop").show(speed);//动画显示
 });*/
 $(document).click(function(event) { $("#racePop").hide(speed) });//单击空白区域隐藏
 $("#racePop").click(function(event) { $("#racePop").hide(speed) });//单击弹出层则自身隐藏

});

function showPlan(lessonId,lessonNum){
	 var speed = 600;//动画速度
	 $("#racePop").show(speed);
	return;
}

</script>

<style>


/* 渐变弹出层 */

.raceShow{background-color:#f1f1f1;border:solid 1px #ccc;position:absolute;display:none;width:300px;height:100px;padding:5px;font-size:12px;}

</style>