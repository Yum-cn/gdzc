<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>部署流程</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">

<t:tabs id="tableGroupTabs" iframe="false" tabPosition="bottom">
 <c:forEach items="${tsTypeList}" var="type">
  <t:tab iframe="dbController.do?tableList&typeid=${type.id}" icon="icon-add" title="${type.typename}" id="${type.typecode}"></t:tab>
 </c:forEach>
</t:tabs>
</body>
</html>
