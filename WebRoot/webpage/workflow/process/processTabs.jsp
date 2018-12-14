<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>部署流程</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">


<t:tabs id="tt" iframe="false" tabPosition="bottom">
 <t:tab href="processController.do?processnode&processid=${processid}" icon="icon-search" title="流程节点" id="pnode"></t:tab>
 <t:tab href="processController.do?processpro&processid=${processid }" icon="icon-search" title="流程变量" id="ppro"></t:tab>
 <t:tab href="processController.do?busbase&processid=${processid }" icon="icon-search" title="业务关联" id="bpro"></t:tab>
</t:tabs>

</body>
</html>
