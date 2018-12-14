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
<div class="layui-tab layui-tab-brief" >
  <ul class="layui-tab-title">
    <li class="layui-this">基本信息</li>
  </ul>
  <div class="layui-tab-content" >
    <div class="layui-tab-item layui-show">
<form name="form1" id="form1" method="post" action="docBasicManageController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${docBasicManagePage.id }">
					<input id="createName" name="createName" type="hidden" value="${docBasicManagePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${docBasicManagePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${docBasicManagePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${docBasicManagePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${docBasicManagePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${docBasicManagePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${docBasicManagePage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${docBasicManagePage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${docBasicManagePage.bpmStatus }">
<div class="formbody" >

  <ul class="forminfo">
    <li>
      <label class="from-label-title" style="width:160px">全文索引目录:</label>
      	<input id="indexList" name="indexList" type="text" value="${docBasicManagePage.indexList }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title" style="width:160px">smtp服务器:</label>
      	<input id="smtpService" name="smtpService" type="text" value="${docBasicManagePage.smtpService }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title" style="width:160px">发件人地址:</label>
      	<input id="senderAddress" name="senderAddress" type="text" value="${docBasicManagePage.senderAddress }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title" style="width:160px">发件人邮箱密码:</label>
      	<input id="senderEmailPsd" name="senderEmailPsd" type="text" value="${docBasicManagePage.senderEmailPsd }" class="dfinput" datatype="*"/>
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

