<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});
</script>

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
					<td>${d.LESSON_NUM}</td>
					<td>${d.LESSON_TIME}</td>
					<td>${d.WEEK_ONE_LESSON}</td>
					<td>${d.WEEK_TWO_LESSON}</td>
					<td>${d.WEEK_THREE_LESSON}</td>
					<td>${d.WEEK_FOUR_LESSON}</td>
					<td>${d.WEEK_FIVE_LESSON}</td>
				</tr>
				</c:forEach>
				
			</table>
</div>