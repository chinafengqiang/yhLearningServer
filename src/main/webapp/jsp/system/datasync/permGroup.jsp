<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
<!--
/*信息列表抬头标题行*/
.listTitle {
	font: bold 10pt/26px "宋体";
	color: #000000;
	background: #F4F4F4;
	text-align: center;
	border: 1px solid #E3E3E3;
}
/*信息列表内容*/
.listContext {
	background: #FBFCF8;
	border-bottom: 1px dotted #E3E3E3;
	line-height: 22px;
	border-right: 1px solid #E3E3E3;
	border-left: 1px solid #E3E3E3;
}
-->
</style>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});
	
	function permFileprog(fileprog,sid){
		var id = fileprog.value;
		var tag = -1;
		var gId = '${gid}';
		if(fileprog.checked){
			tag = 1;
			//document.getElementById("DialogContent").style.display="block";
			$("#DialogContent").show();
			$("#waiting_info").html("正在授权中请等待...");
		}else{
			//document.getElementById("DialogContent").style.display="block";
			$("#DialogContent").show();
			$("#waiting_info").html("正在清除授权中请等待...");
		}
		
		$.post("${pageContext.request.contextPath}/dataSyncController/setPermGroup.html",{channelId:id,sid:sid,gid:gId,tag:tag},function(data){
			$("#DialogContent").hide();
			if(tag == 1){
				if(data == 1){
					alert("授权成功！");
				}else{
					alert("授权失败！");
				}
			}else{
				if(data == 1){
					alert("取消授权成功！");
				}else{
					alert("取消授权失败！");
				}
			}
		});

	}
</script>

<table style="width: 99%;">
			<tr>
				<td class="listTitle">
					授    权
				</td>
				<td class="listTitle">
					频道编号
				</td>
                <td class="listTitle">
					频道名称
				</td>
				<td class="listTitle">
					组播IP
				</td>
				<td class="listTitle">
					组播端口
				</td>
				<td class="listTitle">
					带  宽
				</td>
				<td class="listTitle">
					频道类型 
				</td>
			</tr>
			
	<c:forEach var="channel" items="${channelList}">
		<tr align="center">
		  <td class="listContext">
		  	<c:choose>
		  		<c:when test="${channel.PERMED == 1}">
		  			<input id="checkValues" name="checkValues" type="checkbox" value="${channel.id}" checked="checked" onclick="permFileprog(this,'${channel.sid}')">
		  		</c:when>
		  		<c:otherwise>
		  			<input id="checkValues" name="checkValues" type="checkbox" value="${channel.id}" onclick="permFileprog(this,'${channel.sid}')">
		  		</c:otherwise>
		  	</c:choose>
				
		   </td>
		   <td class="listContext">${channel.id}</td>
			<td class="listContext">${channel.name}</td>
			<td class="listContext">${channel.mip}</td>
			<td class="listContext">${channel.mport}</td>
			<c:if test="${channel.bandwidth==0}">
			<td class="listContext">不限流量</td>
			</c:if>
			<c:if test="${channel.bandwidth!=0}">
			<td class="listContext">${channel.bandwidth}kbps</td>
			</c:if>
			<td class="listContext">
				<c:if test="${channel.type == 2}">
				文件投递频道
				</c:if>
			</td>
			
		</tr>
	</c:forEach>
</table>

<div  id="DialogContent"  style="display:none;background-color:white;width: 99%">
	<table style="width: 99%;height: 100px;text-align: center;border: 0;">
	  		<tr>
				<td  align=center><span id="waiting_info" style="color:red"></span></td>
	  		</tr>

			<tr>
				<td align="center"><img src="${pageContext.request.contextPath}/images/loader.gif"></td>
			</tr>
  	</table>
</div>