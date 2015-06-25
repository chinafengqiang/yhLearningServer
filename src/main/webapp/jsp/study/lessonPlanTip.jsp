<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 渐变弹出层 -->
<div id="racePop" class="raceShow">这里是弹出层效果</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-1.8.3.js"></script>
<script type="text/javascript">
// 渐变弹出层
$(document).ready(function(){
 var speed = 600;//动画速度
 $("#race a").click(function(event){//绑定事件处理
  event.stopPropagation();
  var offset = $(event.target).offset();//取消事件冒泡
  $("#racePop").css({ top:offset.top + $(event.target).height() + "px", left:offset.left });//设置弹出层位置
  $("#racePop").show(speed);//动画显示
 });
 $(document).click(function(event) { $("#racePop").hide(speed)});//单击空白区域隐藏
 $("#racePop").click(function(event) { $("#racePop").hide(speed)});//单击弹出层则自身隐藏

});
</script>

<style>
body{margin:0 auto;font:12px/1.5 tahoma,arial,5b8b4f53;color:#828282;background:#fff}
body,h1,h2,h3,h4,h5,h6,p,ul,li,dl,dt,dd{padding:0;margin:0;}
li{list-style:none;}img{border:none;}em{font-style:normal;}
a{color:#555;text-decoration:none;outline:none;blr:this.onFocus=this.blur();}
a:hover{color:#000;text-decoration:underline;}
body{font-size:12px;font-family:Arial,Verdana, Helvetica, sans-serif;word-break:break-all;word-wrap:break-word;}
.clear{height:0;overflow:hidden;clear:both;}

/* 渐变弹出层 */
#race{display:block;width:200px;height:50px;line-height:50px;text-align:center;background:#CCC;border:#555 1px solid;margin:10px auto}
.raceShow{background-color:#f1f1f1;border:solid 1px #ccc;position:absolute;display:none;width:300px;height:100px;padding:5px;font-size:12px;}

</style>