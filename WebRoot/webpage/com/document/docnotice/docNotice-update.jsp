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
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
<style>
.from-label-title{font-size:14px;text-align: right;padding-right:16px;}
</style>
</head>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">基本信息</li>
  </ul>
  <div class="layui-tab-content" style="height: 100px;">
    <div class="layui-tab-item layui-show">
<form name="form1" id="form1" method="post" action="docNoticeController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${docNoticePage.id }">
					<input id="createName" name="createName" type="hidden" value="${docNoticePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${docNoticePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${docNoticePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${docNoticePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${docNoticePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${docNoticePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${docNoticePage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${docNoticePage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${docNoticePage.bpmStatus }">
<div class="formbody">
		  <ul class="forminfo">
		    <li>
		      <label class="from-label-title">标题:</label>
		      	<input id="noticeTitle" name="noticeTitle" type="text" value="${docNoticePage.noticeTitle}" class="dfinput" datatype="*"/>
		      <i class="Validform_checktip"></i>
		    </li>
		    <li>
		      <label class="from-label-title">发布人:</label>
		      	<input id="noticeSender" name="noticeSender" type="text" value="${docNoticePage.noticeSender}"  class="dfinput" datatype="*"/>
		      <i class="Validform_checktip"></i>
		    </li>
		    <li>
		      <label class="from-label-title">过期时间:</label>
		      	<input id="deadline" name="deadline" type="text" class="dfinput"  value="${docNoticePage.deadline}" datatype="*" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
		      <i class="Validform_checktip"></i>
		    </li>
		    <li>
		      <label class="from-label-title">阅读范围:</label>
		        <input type="text" tabindex="1"  class="dfinput" id="noticeAudience" name="noticeAudience"  value="${docNoticePage.noticeAudience}" datatype="*" onclick="selectUser()">
                <input type="hidden" class="form-control" id="audienceId" name="audienceId"  value="${docNoticePage.audienceId}" datatype="*">
		      <i class="Validform_checktip"></i>
		    </li>
		    <li>
			    <tr>
					<td align="right">
						<label class="from-label-title">
							附件:
						</label>
					</td>
					<td class="value">
						<input id="filePath" name="filePath" type="hidden"    value="${docNoticePage.filePath}" style="width: 150px" class="inputxt"/>
						<input id="fileName" name="fileName" type="hidden"   value="${docNoticePage.fileName}" style="width: 150px" class="inputxt"/>
						<c:if test="${docNoticePage.fileName==null}">
					    	<label id="template_href" style="width:150px;margin-left: 20px">${docNoticePage.fileName}</label>
					    </c:if>
						<input name="updateButton" id="updateButton" type="button" class="btn" style="width:80px;height:30px;" value="上传附件"  onclick="commonUpload(uploadTemplateCallBack);"/>
						<span class="Validform_checktip"></span>
						<label style="display: none;">附件</label>
					</td>
				</tr>
		    
		    </li>
		    <li>
		      <label class="from-label-title">公告正文:</label>
		      	<textarea id="noticeContent" style="display: none;" name="noticeContent">${docNoticePage.noticeContent}</textarea>
		      <i class="Validform_checktip"></i>
		    </li>
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
	layui.use(['form','layedit','laydate'], function(){
	  var form = layui.form();
	  var layedit = layui.layedit;
	  var laydate = layui.laydate;
	  layedit.build('noticeContent'); //建立编辑器
	});

$(function(){
	$("#form1").Validform({
		tiptype:3
	});
	$("#saveForm").click(function(){
    	$("#form1").submit();
  	});
})

//人员选择框
function selectUser() {
	$.dialog({
		content : 'url:userController.do?selectUserCheckBox',
		lock : true,
		width : 1000,
		height : 450,
		title : "协作人",
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


//回调函数
function clickcallback_402880e858ceec720158cef6614a0007() {
	iframe = this.iframe.contentWindow;
	var realName = iframe.getuserListSelections('realName');
	if ($('#noticeAudience').length >= 1) {
		$('#noticeAudience').val(realName);
		$('#noticeAudience').blur();
	}

	var id = iframe.getuserListSelections('id');
	if (id !== undefined && id != "") {
		$('#audienceId').val(id);
	}
}


//上传文件

function uploadTemplateCallBack(url,name,id){
  	//var point = url.lastIndexOf(".");
  	//var type = url.substr(point);
	$("#template_href").attr('href',url);
	$("#template_href").html(name);
	$("#filePath").val(url);
	$("#fileName").val(name);
   };

</script>

