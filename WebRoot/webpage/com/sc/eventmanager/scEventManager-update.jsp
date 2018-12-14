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

<!-- <form name="form1" id="form1" method="post" action="scEventManagerController.do?doUpdate"> -->
					<input id="id" name="id" type="hidden" value="${scEventManagerPage.id }">
					<input id="createName" name="createName" type="hidden" value="${scEventManagerPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${scEventManagerPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${scEventManagerPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${scEventManagerPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${scEventManagerPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${scEventManagerPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${scEventManagerPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${scEventManagerPage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${scEventManagerPage.bpmStatus }">
<div class="formbody">
  <div class="formtitle"><span>基本信息</span></div>
  <ul class="forminfo">
    <li>
      <label style="width:100%">事件类型:&nbsp;&nbsp;<t:listDictParse parseId="${scEventManagerPage.type }" style="1" typecode="JJSJLX"></t:listDictParse></label>
<!--       	<input id="type" name="type" type="text" value="" class="dfinput" datatype="*"/> -->
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label style="width:100%">事件标题:&nbsp;&nbsp;${scEventManagerPage.title }</label>
<!--       	<input id="title" name="title" type="text" value="" class="dfinput" datatype="*"/> -->
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label style="width:100%">事件日期:&nbsp;&nbsp;${scEventManagerPage.timestring }</label>
<!--       	<input id="timestring" name="timestring" type="text" value="" class="dfinput" datatype="*"/> -->
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label style="width:100%">是否有课:&nbsp;&nbsp;${scEventManagerPage.isCourse }</label>
      	
      <i class="Validform_checktip"></i>
    </li>    
<!--     <li> -->
<!--       <label>&nbsp;</label> -->
<!--       <input name="saveForm" id="saveForm" type="button" class="btn" value="返回列表" /> -->
<!--     </li> -->
  </ul>
</div>
<!-- </form> -->
</body>
</html>
<script type="text/javascript">
$(function(){
	$("#form1").Validform({
		tiptype:3
	});
	$("#saveForm").click(function(){
    	location.href="scEventManagerController.do?list";
  	});
})
</script>

