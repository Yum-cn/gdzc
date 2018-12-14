<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/plug-in/frame/default/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery-1.8.3.js"></script>

<script language="javascript">
	$(function(){
	    $('.warn_404').css({'position':'absolute','left':($(window).width()-490)/2});
	    $('.warn_404').css({'position':'absolute','top':($(window).outerHeight()-280)/2});
		$(window).resize(function(){  
	    	$('.warn_404').css({'position':'absolute','left':($(window).width()-490)/2});
	    	$('.warn_404').css({'position':'absolute','top':($(window).outerHeight()-280)/2});
	    })  
	});  
</script>
<style type="text/css">
.linkblue{color:#007bb9; text-decoration:none;}
.linkblue:hover{color:#007bb9; text-decoration:underline;}

.warn_404{width:448px; height:186px; border:1px solid #ccc; border-top:2px solid #2fa1d7; padding:5px; background:#e8e8e8;}
.warn_404 .cont{width:408px; height:161px; padding:25px 20px 0 20px; background:#fff;}
.warn_404 .cont .warnimg{float:left;}
.warn_404 .cont .warntxt{width:245px; float:left; font-size:12px; color:#666; margin:15px auto auto 20px;}
.warn_404 .cont .warntxt .title{width:100%; height:40px; line-height:40px; margin-bottom:8px; color:#114a7f; font-size:16px; font-weight:bold; background:url(images/underline.jpg) repeat-x bottom;}
.warn_404 .cont .warntxt .tips{line-height:25px;}

.Wdate{border:#abadb3 1px solid; height:20px; background:#fff url(${pageContext.request.contextPath}/plug-in/frame/default/images/datePicker.jpg) no-repeat 135px 4px;}
.btnyes{width:81px; height:25px; line-height:25px; color:#fff; border:0; padding:0; background:url(images/btn_ok.jpg) no-repeat;}
.reindex{padding-left:0px;}
.reindex a{width:96px; height:35px; font-size:14px; font-weight:bold; color:#fff; background:#3c95c8; display:block; line-height:35px; text-align:center;border-radius: 3px; behavior:url(js/pie.htc);margin-top:20px;}

</style>
</head>


<body>


    

<div class="warn_404">
	<div class="cont">
    	<div class="warnimg"><img src="${pageContext.request.contextPath}/plug-in/frame/default/images/img_ok.jpg" /></div>
        <div class="warntxt">
        	<div class="title">恭喜您,${message }！</div>
            <div class="tips">页面 <strong>3</strong> 秒钟内将会自动跳转到首页<br />
            <div class="reindex"><a href="${returnURL}">返回</a></div>
            &nbsp;&nbsp;
            </div>
        </div>
    </div>
</div>
</body>

</html>
<script>
setTimeout('locationReturnUrl()', 3000);
function locationReturnUrl(){
	location.href="${returnURL}";
}
</script>