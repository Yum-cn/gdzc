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
<style>
.from-label-title{font-size:14px;text-align: right;padding-right:16px;}
</style>
</head>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <div class="layui-tab-content" style="height: 100px;">
    <div class="layui-tab-item layui-show">
<form name="form1" id="form1" method="post" action="docNoticeController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${docReadNotice.id }">
					<input id="createName" name="createName" type="hidden" value="${docReadNotice.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${docReadNotice.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${docReadNotice.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${docReadNotice.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${docReadNotice.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${docReadNotice.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${docReadNotice.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${docReadNotice.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${docReadNotice.bpmStatus }">
<div class="formbody">

  <ul class="forminfo">
    <li>
      <label class="from-label-title">标题:</label>
      	<input id="noticeTitle" name="noticeTitle" type="text" value="${docReadNotice.noticeTitle }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">发布人:</label>
      	<input id="noticeSender" name="noticeSender" type="text" value="${docReadNotice.noticeSender }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">阅读范围:</label>
      	<input id="noticeAudience" name="noticeAudience" type="text" value="${docReadNotice.noticeAudience }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">文档附件:</label>
      	<input id="fileName" name="fileName" type="text" value="${docReadNotice.fileName }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">公告正文:</label>
      <textarea style="width:400px; height:100px; line-height:32px;border:solid 1px #a7b5bc;text-indent:10px;">${docReadNotice.noticeContent }</textarea>
      <i class="Validform_checktip"></i>
    </li>
    <!-- <li>
      <label>&nbsp;</label>
      <input name="saveForm" id="saveForm" type="button" class="layui-btn" lay-submit="" lay-filter="demo1" value="确认保存"/>
    </li> -->
  </ul>
</div>
</form>

	</div>
  </div>
</div> 
</body>
</html>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
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

