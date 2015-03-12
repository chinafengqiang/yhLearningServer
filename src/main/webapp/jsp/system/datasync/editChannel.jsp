<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(function() {
	submitForm('${pageContext.request.contextPath}/dataSyncController/updateChannel.html');
});
	
</script>
<form method="post" class="form" id="form">
		<fieldset>
			<table class="table" style="width: 100%;">
				<tr>
					<th width="25%">频道类型</th>
					<td>
					<select id="TYPE" name="TYPE" style="width: 160px;height: 30px;">
						<option value="2">文件投递频道</option>
						<!--<option value="6">文件预告频道</option>
						<option value="100">文件点播频道</option>-->
					</select>
					<input type="hidden" id="SID" name="SID" value="${channel.SID}"/>
					<input type="hidden" id="ID" name="ID" value="${channel.ID}"/>
					<input type="hidden" id="STATUS" name="STATUS" value="2"/>
					</td>
				</tr>
				<tr>
				<th>频道名称</th>
				<td><input type="text" name="NAME" class="easyui-validatebox" data-options="required:true" value="${channel.NAME }"/></td>
				</tr>
				<tr>
					<th>组播IP</th>
					<td>
						<input type="text" name="MIP" value="${channel.MIP}" class="easyui-validatebox" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<th>组播端口</th>
					<td>
						<input type="text" name="MPORT" value="${channel.MPORT}" class="easyui-validatebox" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<th>带宽</th>
					<td>
					   <input type="text" id="BANDWIDTH" name="BANDWIDTH" value="${channel.BANDWIDTH}" class="easyui-validatebox" data-options="required:true"><strong>&nbsp;kbps</strong>
					   <select onchange="document.getElementById('BANDWIDTH').value=this.options[this.selectedIndex].value;" style="width: 100px;height: 30px;">
					   	 	 <option value="">可选择</option>
					   	 	 <option value="0">unlimited</option>
			                 <option value="64">64kbps</option>
			                 <option value="128">128kbps</option>
			                 <option value="384">384kbps</option>
			                 <option value="512">512kbps</option>
			                 <option value="768">768kbps</option>
			                 <option value="1024">1Mbps</option>
			                 <option value="1536">1.5Mbps</option>
			                 <option value="2048">2Mbps</option>
			                 <option value="4096">4Mbps</option>
			                 <option value="10240">10Mbps</option>
			                 <option value="20480">20Mbps</option>
			                 <option value="40960">40Mbps</option>
					   </select>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>