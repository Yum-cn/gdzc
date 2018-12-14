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
  <div class="layui-tab-content" style="height: 100px;">
    <div class="layui-tab-item layui-show">
<form name="form1" id="form1" method="post" action="docLibFileController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${docLibFilePage.id }">
					<input id="createName" name="createName" type="hidden" value="${docLibFilePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${docLibFilePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${docLibFilePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${docLibFilePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${docLibFilePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${docLibFilePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${docLibFilePage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${docLibFilePage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${docLibFilePage.bpmStatus }">
<div class="formbody">

  <ul class="forminfo">
    <li>
      <label class="from-label-title">文件名称:</label>
      	<input id="fileName" name="fileName" type="text" value="${docLibFilePage.fileName }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">文件大小:</label>
      	<input id="fileSize" name="fileSize" type="text" value="${docLibFilePage.fileSize }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">上传日期:</label>
      	<input id="uploadDate" name="uploadDate" type="text" value="<fmt:formatDate value="${docLibFilePage.uploadDate }" pattern="yyyy-MM-dd HH:mm:ss"/>" class="dfinput" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">文件夹ID:</label>
      	<input id="pId" name="pId" type="text" value="${docLibFilePage.pId }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">文件路径:</label>
      	<input id="filePath" name="filePath" type="text" value="${docLibFilePage.filePath }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">文件类型:</label>
      	<input id="fileType" name="fileType" type="text" value="${docLibFilePage.fileType }" class="dfinput" datatype="*"/>
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

