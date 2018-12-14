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
<script>
function uploadTemplateCallBack(url,name,id){
  	//var point = url.lastIndexOf(".");
  	//var type = url.substr(point);
	$("#template_href").attr('href',url);
	$("#template_href").html(name);
	$("#attFile").val(url);
	$("#attFileName").val(name);
	$("#title").val(name);
   };
</script>
</head>
<body>

<form name="form1" id="form1" method="post" action="templateController.do?doAdd">
<div class="formbody">
  <div class="formtitle"><span>基本信息</span></div>
  <ul class="forminfo">
    <li>
      <label>格式文档标题:</label>
      	<input id="title" name="title" type="text" class="dfinput" datatype="*"/>
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
				<input id="attFile" name="attFile" type="hidden" style="width: 150px" class="inputxt"/>
				<input id="attFileName" name="attFileName" type="hidden" style="width: 150px" class="inputxt"/>
				<label id="template_href" style="width:150px;">暂时未上传文件</label>
				<input name="updateButton" id="updateButton" type="button" class="btn" style="width:80px;height:30px;" value="上传附件"  onclick="commonUpload(uploadTemplateCallBack);"/>
				<span class="Validform_checktip"></span>
				<label style="display: none;">附件</label>
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
</script>

