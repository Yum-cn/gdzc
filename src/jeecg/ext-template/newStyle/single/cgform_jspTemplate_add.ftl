<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<t:base type="jquery,tools,DatePicker"></t:base>
<link rel="stylesheet" href="${r'${pageContext.request.contextPath}'}/plug-in/Validform_v5.3.2/css/style.css" type="text/css" media="all" />
<script type="text/javascript" src="${r'${pageContext.request.contextPath}'}/plug-in/Validform_v5.3.2/js/Validform_v5.3.2_min.js"></script>
<link href="${r'${pageContext.request.contextPath}'}/plug-in/frame/default/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${r'${pageContext.request.contextPath}'}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>
<style>
.from-label-title{font-size:14px;text-align: right;padding-right:16px;}
</style>
</head>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">基本信息</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
		<form name="form1" id="form1" method="post" action="${entityName?uncap_first}Controller.do?doAdd">
		<div class="formbody">
		  <ul class="forminfo">
			<#list pageColumns as po>
		    <li>
		      <label class="from-label-title">${po.content}:</label>
		      <#if po.showType=='text'>
		      	<input id="${po.fieldName}" name="${po.fieldName}" type="text" class="dfinput" datatype="*"/>
		      <#elseif po.showType=='textarea'>
		      	<textarea id="${po.fieldName}" name="${po.fieldName}" cols="" rows="" class="textinput" datatype="*"></textarea>
		      <#elseif po.showType=='date'>
		      	<input id="${po.fieldName}" name="${po.fieldName}" type="text" class="dfinput" class="Wdate" onClick="WdatePicker()" datatype="*"/>
		      <#elseif po.showType=='datetime'>
		      	<input id="${po.fieldName}" name="${po.fieldName}" type="text" class="dfinput" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*"/>
		      <#elseif po.showType=='radio' || po.showType=='select' || po.showType=='checkbox' || po.showType=='list'>	 
				<t:dictSelect field="${po.fieldName}" type="${po.showType?if_exists?html}"
				<#if po.dictTable?if_exists?html != ''>dictTable="${po.dictTable?if_exists?html}" dictField="${po.dictField?if_exists?html}" dictText="${po.dictText?if_exists?html}"<#else>typeGroupCode="${po.dictField}"</#if> defaultVal="${'$'}{${entityName?uncap_first}Page.${po.fieldName}}" hasLabel="false"  title="${po.content}"></t:dictSelect>  
		
		      </#if>
		      <i class="Validform_checktip"></i>
		    </li>
		    </#list>
		    <li>
		      <label>&nbsp;</label>
		      <button class="layui-btn" lay-submit="" lay-filter="demo1" name="saveForm" id="saveForm">确认保存</button>
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

