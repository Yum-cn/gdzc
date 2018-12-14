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
<form name="form1" id="form1" method="post" action="returnStockController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${returnStockPage.id }">
					<input id="createName" name="createName" type="hidden" value="${returnStockPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${returnStockPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${returnStockPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${returnStockPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${returnStockPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${returnStockPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${returnStockPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${returnStockPage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${returnStockPage.bpmStatus }">
<div class="formbody">

  <ul class="forminfo">
		    <li>
		      <label class="from-label-title">资产:</label>
		      	<input name="relationId" id="relationId" type="hidden" value="${returnStockPage.relationId }" >
                <input name="relationName" class="dfinput" value="${returnStockPage.relationName }" id="relationName" readonly="readonly" datatype="*"  readonly="readonly"/>
<!--                 <a href="#" class="layui-btn" plain="true" icon="icon-search" onClick="choose_402881f3635381d50163539e13530004()">选择</a> -->
<!--                 <a href="#" class="layui-btn" plain="true" icon="icon-redo" onClick="clearAll_402881f3635381d50163539e13530004();">清空</a> -->
		      <i class="Validform_checktip"></i>
		    </li>  
    <li>
      <label class="from-label-title">存放地点:</label>
      	<input id="depositAddress" name="depositAddress" type="text" value="${returnStockPage.depositAddress }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">退库时间:</label>
      	<input id="returnStockTime" name="returnStockTime" type="text" value="<fmt:formatDate value="${returnStockPage.returnStockTime }" pattern="yyyy-MM-dd"/>" class="dfinput" class="Wdate" onClick="WdatePicker()" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
				<li>
					<label class="from-label-title">
							附件:
					</label>
							<input id="contractPath" name="contractPath" type="hidden" style="width: 150px" class="inputxt">
							<input id="contractName" name="contractName" type="hidden" style="width: 150px" class="inputxt">
							<a id="template_href" style="width:180px;">暂时未上传附件</a>
							<input class="ui-button" type="button" value="上传附件" onclick="commonUpload(uploadTemplateCallBack);"/>   
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">附件</label>
				</li>		    
		    <li>    
		    <li>
		      <label class="from-label-title">备注:</label>
		      	<input id="remark" name="remark"  class="dfinput" datatype="*" value="${returnStockPage.remark }" readonly="readonly"  readonly="readonly"></input>
		      <i class="Validform_checktip"></i>
		    </li>

    <li>
      <label>&nbsp;</label>
      <input onclick="window.history.back();" type="button" class="layui-btn" lay-submit="" lay-filter="demo1" value="返回"/><input onclick="window.history.back();" type="button" class="layui-btn" lay-submit="" lay-filter="demo1" value="导出"/><input onclick="window.history.back();" type="button" class="layui-btn" lay-submit="" lay-filter="demo1" value="打印"/><br></br>
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

