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

<form name="form1" id="form1" method="post" action="templateController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${templatePage.id }">
					<input id="createName" name="createName" type="hidden" value="${templatePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${templatePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${templatePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${templatePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${templatePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${templatePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${templatePage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${templatePage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${templatePage.bpmStatus }">
<div class="formbody">
  <div class="formtitle"><span>基本信息</span></div>
  <ul class="forminfo">
    <li>
      <label>格式文档标题:</label>
      	<input id="title" name="title" type="text" value="${templatePage.title }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <tr>
		<td align="right">
			<label>
				附件:
			</label>
		</td>
		<td class="value">
			<input id="attFile" name="attFile" type="hidden" style="width: 150px" class="inputxt" value="${templatePage.attFile}"/>
			<input id="attFileName" name="attFileName" type="hidden" style="width: 150px" class="inputxt" value="${templatePage.attFileName}"/>
							<c:if test="${templatePage.attFile==''}">
								<a   target="_blank" id="template_href">暂时未上传文件</a>
							</c:if>
							<c:if test="${templatePage.attFile!=''}">
								<a href="${templatePage.attFile}" target="_blank" id="template_href">${templatePage.attFileName}</a>
							</c:if>							
				<input class="btn" type="button" value="上传附件" onclick="commonUpload(uploadTemplateCallBack);"/>   
				<span class="Validform_checktip"></span>
				<label  style="display: none;">附件</label>
			</td>
		</tr>
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
function uploadTemplateCallBack(url,name,id){
  	var point = url.lastIndexOf(".");
  	var type = url.substr(point);
	$("#template_href").attr('href',url);
	$("#template_href").html(name);
	$("#attFile").val(url);
	$("#attFileName").val(name);
};
</script>

