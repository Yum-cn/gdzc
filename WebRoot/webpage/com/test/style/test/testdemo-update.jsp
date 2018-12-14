<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<t:base type="jquery,tools,DatePicker"></t:base>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/Validform_v5.3.2/css/style.css" type="text/css" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/Validform_v5.3.2/js/Validform_v5.3.2_min.js"></script>
<link href="${pageContext.request.contextPath}/plug-in/frame/default/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<form name="form1" id="form1" method="post" action="testdemoController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${testdemoPage.id }">
					<input id="createName" name="createName" type="hidden" value="${testdemoPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${testdemoPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${testdemoPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${testdemoPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${testdemoPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${testdemoPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${testdemoPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${testdemoPage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${testdemoPage.bpmStatus }">
<div class="formbody">
  <div class="formtitle"><span>基本信息</span></div>
  <ul class="forminfo">
    <li>
      <label>姓名:</label>
      	<input id="name" name="name" type="text" type="text" value="${testdemoPage.name }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label>单位:</label>
      	<input id="unit" name="unit" type="text" type="text" value="${testdemoPage.unit }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label>&nbsp;</label>
      <input name="saveForm" id="saveForm" type="button" class="btn" value="确认保存"/>
    </li>
  </ul>
</div>
</form>
</body>
</html>
<script type="text/javascript">
$(function(){
	$("#form1").Validform({
		tiptype:3
	});
	$("#saveForm").click(function(){
    	$("#form1").submit();
  	});
})
</script>

