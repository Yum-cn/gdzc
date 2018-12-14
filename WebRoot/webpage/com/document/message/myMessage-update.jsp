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


<div class="rightinfo">
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">消息正文</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
      <div id="container">
      <div id="hd"></div>
      <div id="bd">
      <div id="main">
      <form name="searchForm" id="searchForm" method="post" action="docMyFileController.do?queryRecentFile" class="layui-form ">
        <!-- pId是当前点击的文件或文件夹ID -->
        <input name="pId" value="${pId}" type="hidden" id="pId"/>
        <div class="table" style="z-index: 99999999999;">
	      	<fieldset class="layui-elem-field layui-field-title">
			  <legend>${myMessagePage.title }</legend>
			</fieldset>
			<blockquote class="layui-elem-quote layui-quote-nm">
				${myMessagePage.content }
			</blockquote>
        </div>
        </div>
        </div>
        </div>
      </form>
    </div>
  </div>
</div>



</body>
</html>


