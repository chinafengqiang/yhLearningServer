<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		//submitForm("${pageContext.request.contextPath}/dataSyncController/saveUser.html");
	});
</script>
			<table class="table" style="width: 100%;text-align: center;">

				<tr align="center">
				<th align="center">文件名称</th>
				<th>文件大小</th>
				</tr>
				<c:forEach var="file" items="${fileList}">
					<tr align="center">
						<td>${file.TITLE }</td>
						<td>${file.FILESIZE }</td>
					</tr>
				</c:forEach>

			</table>
