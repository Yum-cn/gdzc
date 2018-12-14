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
<form name="form1" id="form1" method="post" action="repairManageController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${repairManagePage.id }">
					<input id="createName" name="createName" type="hidden" value="${repairManagePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${repairManagePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${repairManagePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${repairManagePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${repairManagePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${repairManagePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${repairManagePage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${repairManagePage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${repairManagePage.bpmStatus }">
<div class="formbody">

  <ul class="forminfo">
		    <li>
		      <label class="from-label-title">资产:</label>
		      	<input name="relationId" id="relationId" type="hidden" value="${repairManagePage.relationId }" >
                <input name="relationName" class="dfinput" value="${repairManagePage.relationName }" id="relationName" readonly="readonly" datatype="*"  readonly="readonly"/>
<!--                 <a href="#" class="layui-btn" plain="true" icon="icon-search" onClick="choose_402881f3635381d50163539e13530004()">选择</a> -->
<!--                 <a href="#" class="layui-btn" plain="true" icon="icon-redo" onClick="clearAll_402881f3635381d50163539e13530004();">清空</a> -->
		      <i class="Validform_checktip"></i>
		    </li>  
    <li>
      <label class="from-label-title">报修人:</label>
      	<input id="repairUser" name="repairUser" type="text" value="${repairManagePage.repairUser }" class="dfinput" datatype="*" readonly="readonly"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">报修时间:</label>
      	<input id="repairTime" name="repairTime" type="text" value="<fmt:formatDate value="${repairManagePage.repairTime }" pattern="yyyy-MM-dd"/>" class="dfinput" class="Wdate"  datatype="*" readonly="readonly"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">报修原因:</label>
      	<input id="reason" name="reason" type="text" value="${repairManagePage.reason }" class="dfinput" datatype="*" readonly="readonly"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">维修内容:</label>
      	<input id="content" name="content" type="text" value="${repairManagePage.content }" class="dfinput" datatype="*" readonly="readonly"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">维修费用:</label>
      	<input id="cost" name="cost" type="text" value="${repairManagePage.cost }" class="dfinput" datatype="*" readonly="readonly"/>
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
		      <label class="from-label-title">备注:</label>
		      	<input id="remark" name="remark"  class="dfinput" datatype="*" value="${repairManagePage.remark }" readonly="readonly"  readonly="readonly"></input>
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

