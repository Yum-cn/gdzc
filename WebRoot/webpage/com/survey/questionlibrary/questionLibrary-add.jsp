<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>题库管理</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/Validform_v5.3.2/css/style.css" type="text/css" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/Validform_v5.3.2/js/Validform_v5.3.2_min.js"></script>
<link href="${pageContext.request.contextPath}/plug-in/frame/default/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
<style>
.from-label-title{font-size:14px;text-align: right;padding-right:16px;}
</style>
</head>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">题库管理</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
		<form name="form1" id="form1" class="layui-form"  method="post" action="questionLibraryController.do?doAdd">
		<div class="formbody">
		  <ul class="forminfo">
		    <li>
		      <label class="from-label-title">题库大类:</label>
		      	<t:dictSelect field="type"  typeGroupCode="TIKUDALEI"  hasLabel="false" extendJson="{'style':'width:120px'}"></t:dictSelect>
		    </li>
		    <li>
		      <label class="from-label-title">题库小类:</label>
		      	<t:dictSelect field="smallType"  typeGroupCode="TIKUXIAOL"  hasLabel="false" extendJson="{'style':'width:120px'}"></t:dictSelect> 
		    </li>
		    <li>
		      <label class="from-label-title">试题类型:</label>
		      	<t:dictSelect field="style" id="style" type="list"  extendJson="{ 'onchange': 'selectType()','datatype':'*','style':'width:120px'}" 
		      			typeGroupCode="SHITILEIX"  hasLabel="false"  title="试题类型"></t:dictSelect>  
		    </li>
		    <li>
		      <label class="from-label-title">题目标题:</label>
		      	<textarea name="title" id="title"   lay-verify="required"  style="width: 354px;height:70px;border: 1px solid #ced9df"></textarea> 
		    </li>
		    <li id="content">
		      <label class="from-label-title">题目选项:</label>
				<input id="title" name="option" type="text"  lay-verify="required" class="dfinput"><i class="layui-icon" onclick="add_line()">&#xe608;</i>
			</li>
		    <li>
		      <label>&nbsp;</label>
		      <button class="layui-btn" lay-submit lay-filter="demo1" name="saveForm" id="saveForm">确认保存</button>
		    </li>
		  </ul>
		</div>
		</form>
	</div>
  </div>
</div> 
 </body>
  <script src = "webpage/com/train/questionlibrary/questionLibrary.js"></script>
  <script type="text/javascript">
		layui.use('form', function(){
		  var form = layui.form();
		});
  		
	  var currentStep = 0;
	  var max_line_num = 1;
	  function add_line() {
		var html ='<li>';
		html = html+ '		<label class="from-label-title">';
		html = html+ '			题目选项:';
		html = html+ '		</label>';
		html = html+ '		     	<input id="title" name="option"  type="text" lay-verify="required" class="dfinput">';
		html = html+ '				<i class="layui-icon" onclick="remove_line(this)">&#xe640;</i>';
		html = html+ '	</li>';
		$("#content").append(html);
		max_line_num += 1;

	  }
	  function remove_line(index) {
		  $(index).parent().remove();

	  }
	  function selectType(){
		  var styleVal = $("#style").val();
		  if(styleVal=='TIANKONG'){
			  $("#optionTR").hide();
			  $("#title").val("填空标题<fill>后续内容");
		  }else if(styleVal=='JIANDATI'){
			  $("#optionTR").hide();
			  $("#title").val("");
		  }else{
			  $("#optionTR").show();
			  $("#title").val("");
		  }
	  }
  </script>
  </html>		