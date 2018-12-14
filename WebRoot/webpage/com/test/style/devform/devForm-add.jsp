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

<form name="form1" id="form1" method="post" action="devFormController.do?doAdd">
<div class="formbody">
  <div class="formtitle"><span>基本信息</span></div>
  <ul class="forminfo">
    <li>
      <label>更新人登录名称:</label>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label>所属部门:</label>
		<t:dictSelect field="sysOrgCode" type="radio"
		typeGroupCode="msgStatus" defaultVal="${devFormPage.sysOrgCode}" hasLabel="false"  title="所属部门"></t:dictSelect>  

      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label>所属公司:</label>
		<t:dictSelect field="sysCompanyCode" type="checkbox"
		typeGroupCode="msgStatus" defaultVal="${devFormPage.sysCompanyCode}" hasLabel="false"  title="所属公司"></t:dictSelect>  

      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label>流程状态:</label>
      <input name="bpmStatus" value="1"/>

      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label>姓名:</label>
      	<textarea id="name" name="name" cols="" rows="" class="textinput" datatype="*"></textarea>
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

