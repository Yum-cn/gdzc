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
<form name="form1" id="form1" method="post" action="useController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${usePage.id }">
					<input id="createName" name="createName" type="hidden" value="${usePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${usePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${usePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${usePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${usePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${usePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${usePage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${usePage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${usePage.bpmStatus }">
<div class="formbody">

  <ul class="forminfo">
		    <li>
		      <label class="from-label-title">资产:</label>
		      	<input name="relationId" id="relationId" type="hidden" value="${usePage.relationId }" >
                <input name="relationName" class="dfinput"  value="${usePage.relationName }" id="relationName" readonly="readonly" datatype="*" />
<!--                 <a href="#" class="layui-btn" plain="true" icon="icon-search" onClick="choose_402881f3635381d50163539e13530004()">选择</a> -->
<!--                 <a href="#" class="layui-btn" plain="true" icon="icon-redo" onClick="clearAll_402881f3635381d50163539e13530004();">清空</a> -->
		      <i class="Validform_checktip"></i>
		    </li>  
    <li>
      <label class="from-label-title">使用人:</label>
      	<input id="useUser" name="useUser" type="text" value="${usePage.useUser }" class="dfinput" datatype="*" readonly="readonly"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">使用室:</label>
      	<input id="address" name="address" type="text" value="${usePage.address }" class="dfinput" datatype="*" readonly="readonly"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">领用时间:</label>
      	<input id="useTime" name="useTime" type="text" value="<fmt:formatDate value="${usePage.useTime }" pattern="yyyy-MM-dd"/>" class="dfinput" class="Wdate"  datatype="*" readonly="readonly"/>
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
		      	<input id="remark" name="remark"  class="dfinput" datatype="*" value="${usePage.remark }" readonly="readonly"></input>
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
<script type="text/javascript">
	var windowapi = frameElement.api, W = windowapi.opener;
	function choose_402881f3635381d50163539e13530004() {
		var url = 'storeController.do?selectList&status=lingyong';
		var initValue = $('#roleid').val();
		url += '&ids=' + initValue;
		if (typeof (windowapi) == 'undefined') {
			$.dialog({
				content : 'url:' + url,
				zIndex : 2100,
				title : '资产列表',
				lock : true,
				width : 800,
				height : 350,
				left : '85%',
				top : '65%',
				opacity : 0.4,
				button : [ {
					name : '确定',
					callback : clickcallback_402881f3635381d50163539e13530004,
					focus : true
				}, {
					name : '取消',
					callback : function() {
					}
				} ]
			});
		} else {
			$.dialog({
				content : 'url:' + url,
				zIndex : 2100,
				title : '资产列表',
				lock : true,
				parent : windowapi,
				width : 800,
				height : 350,
				left : '85%',
				top : '65%',
				opacity : 0.4,
				button : [ {
					name : '确定',
					callback : clickcallback_402881f3635381d50163539e13530004,
					focus : true
				}, {
					name : '取消',
					callback : function() {
					}
				} ]
			});
		}
	}
	function clearAll_402881f3635381d50163539e13530004() {
		if ($('#relationName').length >= 1) {
			$('#relationName').val('');
			$('#relationName').blur();
		}
		if ($("input[name='relationName']").length >= 1) {
			$("input[name='relationName']").val('');
			$("input[name='relationName']").blur();
		}
		$('#relationId').val("");
	}
	function clickcallback_402881f3635381d50163539e13530004() {
		iframe = this.iframe.contentWindow;
		var code = iframe.getstoreListSelections('code');
		var name = iframe.getstoreListSelections('name');
		var returnName = name + "("+code+")";
		if ($('#relationName').length >= 1) {
			$('#relationName').val(returnName);
			$('#relationName').blur();
		}
		if ($("input[name='relationName']").length >= 1) {
			$("input[name='relationName']").val(returnName);
			$("input[name='relationName']").blur();
		}
		var id = iframe.getstoreListSelections('id');
		if (id !== undefined && id != "") {
			$('#relationId').val(id);
		}
	}
</script>
