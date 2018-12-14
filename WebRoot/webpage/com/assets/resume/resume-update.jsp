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
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>
<style>
.from-label-title{font-size:14px;text-align: right;padding-right:16px;}
</style>
</head>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">基本信息</li>
  </ul>
  <div class="layui-tab-content" >
    <div class="layui-tab-item layui-show">
<form name="form1" id="form1" method="post" action="resumeController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${resumePage.id }">
					<input id="createName" name="createName" type="hidden" value="${resumePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${resumePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${resumePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${resumePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${resumePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${resumePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${resumePage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${resumePage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${resumePage.bpmStatus }">
<div class="formbody">

  <ul class="forminfo">
    <li>
      <label class="from-label-title">处理时间:</label>
      	<input id="handleTime" name="handleTime" type="text" value="${resumePage.handleTime }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">处理人:</label>
      	<input id="handleUser" name="handleUser" type="text" value="${resumePage.handleUser }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">事件类型:</label>
      	<input id="type" name="type" type="text" value="${resumePage.type }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">处理内容:</label>
      	<input id="content" name="content" type="text" value="${resumePage.content }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">资产ID:</label>
      	<input id="storeId" name="storeId" type="text" value="${resumePage.storeId }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">资产名称:</label>
      	<input id="storeName" name="storeName" type="text" value="${resumePage.storeName }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label>&nbsp;</label>
      <input name="saveForm" id="saveForm" type="button" class="layui-btn" lay-submit="" lay-filter="demo1" value="确认保存"/>
    </li>
  </ul>
</div>
</form>

	</div>
  </div>
</div> 
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

