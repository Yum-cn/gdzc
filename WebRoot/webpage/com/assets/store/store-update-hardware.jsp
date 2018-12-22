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
<script>
function uploadTemplateCallBack(url,name){
//   	var point = url.lastIndexOf(".");
//   	var type = url.substr(point);
//   	if(type==".doc" || type==".docx"){
		$("#template_href").attr('href',url);
		$("#template_href").html(name);
		$("#contractPath").val(url);
		$("#contractName").val(name);
//   	}else{
//   		alert("模板文件格式不正确");
//   	}
}
</script>
</head>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">基本信息</li>
  </ul>
  <div class="layui-tab-content" >
    <div class="layui-tab-item layui-show">
<form name="form1" id="form1" method="post" action="storeController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${storePage.id }">
					<input id="createName" name="createName" type="hidden" value="${storePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${storePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${storePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${storePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${storePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${storePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${storePage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${storePage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${storePage.bpmStatus }">
<div class="formbody">

  <ul class="forminfo">
    <li>
      <label class="from-label-title">资产编码:</label>
      	<input id="code" name="code" type="text" value="${storePage.code }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">资产类别:</label>
									<t:dictSelect field="type" type="list"
										typeGroupCode="zclb" defaultVal="${storePage.type}" hasLabel="false"  title="资产类别"></t:dictSelect>      
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">入库时间:</label>
      	<input id="storageTime" name="storageTime" type="text" value="<fmt:formatDate value="${storePage.storageTime }" pattern="yyyy-MM-dd"/>" class="dfinput" class="Wdate" onClick="WdatePicker()" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">资产名称:</label>
      	<input id="name" name="name" type="text" value="${storePage.name }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">来源:</label>
<%--       	<input id="source" name="source" type="text" value="${storePage.source }" class="dfinput" datatype="*"/> --%>
		      	<t:dictSelect field="source" type="list"
				typeGroupCode="LY" defaultVal="${storePage.source}" hasLabel="false"  title="来源"></t:dictSelect>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">品牌:</label>
      	<input id="brand" name="brand" type="text" value="${storePage.brand }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">渠道:</label>
      	<input id="channel" name="channel" type="text" value="${storePage.channel }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">单价:</label>
      	<input id="amount" name="amount" type="text" value="${storePage.amount }" class="dfinput" datatype="n"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">过保时间:</label>
      	<input id="overInsuranceTime" name="overInsuranceTime" type="text" value="<fmt:formatDate value="${storePage.overInsuranceTime }" pattern="yyyy-MM-dd"/>" class="dfinput" class="Wdate" onClick="WdatePicker()" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
				<li>
					<label class="from-label-title">
							合同文件:
					</label>
							<input id="contractPath" name="contractPath" type="hidden" value="${storePage.contractPath }" style="width: 150px" class="inputxt">
							<input id="contractName" name="contractName" type="hidden" value="${storePage.contractName }" style="width: 150px" class="inputxt">
							<c:if test="${storePage.contractPath ne ''}">
							<a id="template_href" style="width:180px;" href="${storePage.contractPath }">${storePage.contractName }</a>
							</c:if>
							<c:if test="${storePage.contractPath eq ''}">
							<a id="template_href" style="width:180px;">暂时未上传合同文件</a>
							</c:if>
							<input class="ui-button" type="button" value="上传合同文件" onclick="commonUpload(uploadTemplateCallBack);"/>   
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同文件</label>
				</li>    
<!--     <li> -->
<!-- 		<label class="from-label-title">数量:</label> -->
<%-- 		<input id="number" name="number" type="text" class="dfinput" value="${storePage.number }" datatype="*"/> --%>
<!-- 		<i class="Validform_checktip"></i> -->
<!-- 	</li>	 -->
<!--     <li> -->
<!--       <label class="from-label-title">预计报废时间:</label> -->
<%--       	<input id="discardedTime" name="discardedTime" type="text" value="<fmt:formatDate value="${storePage.discardedTime }" pattern="yyyy-MM-dd"/>" class="dfinput" class="Wdate" onClick="WdatePicker()" datatype="*"/> --%>
<!--       <i class="Validform_checktip"></i> -->
<!--     </li> -->
    <li>
      <label class="from-label-title">备注:</label>
      	<textarea id="remark" name="remark" cols="" rows="" class="textinput" >${storePage.remark }</textarea>
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

