<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<div id="maintabs" class="easyui-tabs" fit="true" border="false">
<div class="easyui-tab" title="文献" href="seeResourcesController.do?seeResources&topId=${topId }" style="padding: 2px; overflow: hidden;"></div>
<div class="easyui-tab" title="学习记录" href="learningRecordController.do?learningRecord&topId=${topId }" style="padding: 2px; overflow: hidden;"></div>
</div>

               </body>
</html>