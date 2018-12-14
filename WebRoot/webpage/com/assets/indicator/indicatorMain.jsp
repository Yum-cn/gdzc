<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>指标管理</title>
<t:base type="jquery,easyui,tools"></t:base>
<link rel="stylesheet" href="plug-in/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="plug-in/zTree/js/jquery.ztree.core.js"></script>
<script>
$(document).ready(function(){
	$("#aaa").css("height",$(document).height());
	$("#listFrame").height($(document).height());
});
	
</script>
</head><frameset rows="800,*" cols="*" frameborder="no" border="0" framespacing="0" style="height:100%">
  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
    <frame src="${pageContext.request.contextPath}/indicatorController.do?indicatorLeft" name="indicatorLeft" id="indicatorLeft" scrolling="no" noresize="noresize"  title="indicatorLeft" />
    <frame src="${pageContext.request.contextPath}/indicatorController.do?indicator" name="listFrame" id="listFrame" title="listFrame" />
  </frameset>
</frameset>
<body>
  </body>
</html>