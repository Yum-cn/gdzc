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

<!-- <form name="form1" id="form1" method="post" action="scheduleManagerController.do?doUpdate"> -->
					<input id="id" name="id" type="hidden" value="${scheduleManagerPage.id }">
					<input id="createName" name="createName" type="hidden" value="${scheduleManagerPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${scheduleManagerPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${scheduleManagerPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${scheduleManagerPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${scheduleManagerPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${scheduleManagerPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${scheduleManagerPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${scheduleManagerPage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${scheduleManagerPage.bpmStatus }">
<div class="formbody">
  <div class="formtitle"><span>基本信息</span></div>
  <ul class="forminfo">
<!--     <li> -->
<!--       <label>微信id:</label> -->
<%--       	<input id="openid" name="openid" type="text" value="${scheduleManagerPage.openid }" class="dfinput" datatype="*"/> --%>
<!--       <i class="Validform_checktip"></i> -->
<!--     </li> -->
    <li>
      <label style="width:100%">日程类型:&nbsp;&nbsp;<t:listDictParse parseId="${scheduleManagerPage.type }" style="1" typecode="RCLX"></t:listDictParse></label>
      	
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label style="width:100%">日程标题:&nbsp;&nbsp;${scheduleManagerPage.title }</label>
      	
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label style="width:100%">日程时间:&nbsp;&nbsp;${scheduleManagerPage.timeString }</label>
      	
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label style="width:100%">是否有课:&nbsp;&nbsp;${scheduleManagerPage.isCourse }</label>
      	
      <i class="Validform_checktip"></i>
    </li>
<!--     <li> -->
<!--       <label>结束时间:</label> -->
<%--       	<input id="endTime" name="endTime" type="text" value="${scheduleManagerPage.endTime }" class="dfinput" datatype="*"/> --%>
<!--       <i class="Validform_checktip"></i> -->
<!--     </li> -->
<!--     <li> -->
<!--       <label>&nbsp;</label> -->
<!--       <input name="saveForm" id="saveForm" type="button" class="btn" value="返回列表"/> -->
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
		location.href="scheduleManagerController.do?list";
  	});
})
</script>

