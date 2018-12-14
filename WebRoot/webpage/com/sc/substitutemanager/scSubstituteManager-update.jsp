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
<script type="text/javascript">
	var windowapi = frameElement.api, W = windowapi.opener;
	function selectUser() {
		$.dialog({
			content : 'url:userController.do?selectUserCheckBox',
			lock : true,
			width : 1000,
			height : 450,
			title : "选择成员",
			opacity : 0.3,
			cache : false,
			cancelVal : '关闭',
			cancel : true, /*为true等价于function(){}*/
			button : [ {
				name : '确定',
				callback : clickcallback_402880e858ceec720158cef6614a0007,
				focus : true
			} ]
		});
	}

	function clickcallback_402880e858ceec720158cef6614a0007() {
		iframe = this.iframe.contentWindow;
		var realName = iframe.getuserListSelections('realName');
		if ($('#leaveMan').length >= 1) {
			$('#leaveMan').val(realName);
			$('#leaveMan').blur();
		}

		var id = iframe.getuserListSelections('id');
		if (id !== undefined && id != "") {
			$('#leaveUserid').val(id);
		}
	}

	function selectUser1() {
		$.dialog({
			content : 'url:userController.do?selectUserCheckBox',
			lock : true,
			width : 1000,
			height : 450,
			title : "选择成员",
			opacity : 0.3,
			cache : false,
			cancelVal : '关闭',
			cancel : true, /*为true等价于function(){}*/
			button : [ {
				name : '确定',
				callback : clickcallback_402880e858ceec720158cef6614a00071,
				focus : true
			} ]
		});
	}

	function clickcallback_402880e858ceec720158cef6614a00071() {
		iframe = this.iframe.contentWindow;
		var realName = iframe.getuserListSelections('realName');
		if ($('#substituteMan').length >= 1) {
			$('#substituteMan').val(realName);
			$('#substituteMan').blur();
		}

		var id = iframe.getuserListSelections('id');
		if (id !== undefined && id != "") {
			$('#substituteUserid').val(id);
		}
	}
</script>
<body>

<form name="form1" id="form1" method="post" action="scSubstituteManagerController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${scSubstituteManagerPage.id }">
					<input id="createName" name="createName" type="hidden" value="${scSubstituteManagerPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${scSubstituteManagerPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${scSubstituteManagerPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${scSubstituteManagerPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${scSubstituteManagerPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${scSubstituteManagerPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${scSubstituteManagerPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${scSubstituteManagerPage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${scSubstituteManagerPage.bpmStatus }">
<div class="formbody">
  <div class="formtitle"><span>基本信息</span></div>
  <ul class="forminfo">
    <li>
      <label>请假人:</label>
      	<input id="leaveMan" name="leaveMan" type="text" value="${scSubstituteManagerPage.leaveMan }" class="dfinput" datatype="*" onfocus="selectUser()"/>
      <i class="Validform_checktip"></i>
    </li>
    <li style="display:none;">
      <label>请假人ID:</label>
      	<input id="leaveUserid" name="leaveUserid" type="text" value="${scSubstituteManagerPage.leaveUserid }" class="dfinput" />
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label>代课时间:</label>
      	<input id="substituteTime" name="substituteTime" type="text" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${scSubstituteManagerPage.substituteTime }" />" class="dfinput Wdate" datatype="*" onFocus="WdatePicker({isShowClear:false,readOnly:true})"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label>代课人:</label>
      	<input id="substituteMan" name="substituteMan" type="text" value="${scSubstituteManagerPage.substituteMan }" class="dfinput" datatype="*" onfocus="selectUser1()"/>
      <i class="Validform_checktip"></i>
    </li>
    <li style="display:none;">
      <label>代课人ID:</label>
      	<input id="substituteUserid" name="substituteUserid" type="text" value="${scSubstituteManagerPage.substituteUserid }" class="dfinput"/>
      <i class="Validform_checktip"></i>
    </li>
    <li style="display:none;">
      <label>代课人OPENID:</label>
      	<input id="substituteOepnid" name="substituteOepnid" type="text" value="${scSubstituteManagerPage.substituteOepnid }" class="dfinput" />
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label>代课班级:</label>
      	<input id="substituteClass" name="substituteClass" type="text" value="${scSubstituteManagerPage.substituteClass }" class="dfinput" datatype="*"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label>代课节次:</label>
      	<t:dictSelect field="substituteSection" typeGroupCode="JIECI" hasLabel="false" defaultVal="${scSubstituteManagerPage.substituteSection }"/>
<%--       	<input id="substituteSection" name="substituteSection" type="text" value="${scSubstituteManagerPage.substituteSection }" class="dfinput" datatype="*"/> --%>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label>代课地点:</label>
      
      	<input id="address" name="address" type="text" class="dfinput" datatype="*" value="${scSubstituteManagerPage.address }"/>
<!--       	<input id="substituteSection" name="substituteSection" type="text" class="dfinput" datatype="*"/> -->
      <i class="Validform_checktip"></i>
    </li>    
    <li>
      <label>备注:</label>
      	<textarea id="remarks" name="remarks" style="border:solid 1px #a7b5bc;" cols="55" rows="10">${scSubstituteManagerPage.remarks }</textarea>
      <i class="Validform_checktip"></i>
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

