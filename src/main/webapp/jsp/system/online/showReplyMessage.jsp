<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	
	function zoomImage(imagePath){
		var url = '${pageContext.request.contextPath}/managerController/zoomImage.html?imagePath='+imagePath;
		window.open(url,'','menubar=no,location=no,toolbar=no,scrollbars=no');
	}
	
	function setStatus(id,isValid){
		var url = "${pageContext.request.contextPath}/sysMessageController/setOnlineReplyStatus.html";
		$.post(
				url,
				{
					id : id,
					isValid:isValid
				},
				function(result) {
					if (result.success) {
						parent.$.messager.alert('提示',"操作成功",'info');
						var statusText = '有效';
						if(isValid == 0){
							statusText = '无效';
						}
						$("#status_"+id).html(statusText);
					} else {
						parent.$.messager.alert('错误',"操作失败",'error');
					}
				}, 'JSON');
	}
</script>
			<table class="table" style="width: 100%;text-align: center;">
				<tr align="center">
					<th align="center" colspan="6">
					 共&nbsp;${replyCount}&nbsp;条回复
					</th>
				</tr>
				
				<tr align="center">
					<th align="center">内容</th>
					<th>回复者</th>
					<th>回复时间</th>
					<th>图片</th>
					<th>状态</th>
					<th align="center">
					操作
					</th>
				</tr>

				<c:forEach var="reply" items="${replyList}">
					<tr align="center">
						<td>${reply.RP_MSG}</td>
						<td>${reply.RP_UNAME}</td>
						<td>${reply.RP_TIME}</td>
						<td>
							<c:choose>
								<c:when test="${reply.RP_IMAGE_PATH ne ''}">
									<a href="#" onclick="zoomImage('${reply.RP_IMAGE_PATH}')" style="color: blue;">查看</a>
								</c:when>
							</c:choose>
						</td>
						<td>
							<span id="status_${reply.ID}">
								<c:choose>
									<c:when test="${reply.IS_VALID == 1}">
										有效
									</c:when>
									<c:otherwise>
										无效
									</c:otherwise>
								</c:choose>
							</span>
						</td>
						 <td align="center">
						 	<a href="#" style="color: blue;" onclick="setStatus('${reply.ID}','0')">禁用</a>&nbsp;&nbsp;
						 	<a href="#" style="color: blue;" onclick="setStatus('${reply.ID}','1')">启用</a>
						 </td>
					</tr>
				</c:forEach>

			</table>
