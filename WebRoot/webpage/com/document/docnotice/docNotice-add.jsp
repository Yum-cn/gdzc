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
    <li class="layui-this">添加公告</li>
  </ul>
  <div class="layui-tab-content" style="height: 100px;">
    <div class="layui-tab-item layui-show">
		<form name="form1" id="form1" method="post" action="docNoticeController.do?doAdd">
		<div class="formbody">
		  <ul class="forminfo">
		    <li>
		      <label class="from-label-title">标题:</label>
		      	<input id="noticeTitle" name="noticeTitle" type="text" class="dfinput" datatype="*"/>
		      <i class="Validform_checktip"></i>
		    </li>
		    <li>
		      <label class="from-label-title">发布人:</label>
		      	<input id="noticeSender" name="noticeSender" type="text" class="dfinput" datatype="*" value="${realName}" readonly="readonly"/>
		      <i class="Validform_checktip"></i>
		    </li>
		    <li>
		      <label class="from-label-title">过期时间:</label>
		      	<input id="deadline" name="deadline" type="text" class="dfinput" datatype="*" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
		      <i class="Validform_checktip"></i>
		    </li>
		    <li>
		      <label class="from-label-title">阅读范围:</label>
		        <input type="text" tabindex="1"  class="dfinput" id="noticeAudience" name="noticeAudience" datatype="*" onclick="selectUser()">
                <input type="hidden" class="form-control" id="audienceId" name="audienceId" datatype="*">
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
						<input id="filePath" name="filePath" type="hidden" style="width: 150px" class="inputxt"/>
						<input id="fileName" name="fileName" type="hidden" style="width: 150px" class="inputxt"/>
						<label id="template_href" style="width:150px;margin-left: 20px">暂时未上传文件</label>
						<input name="updateButton" id="updateButton" type="button" class="btn" style="width:80px;height:30px;" value="上传附件"  onclick="commonUpload(uploadTemplateCallBack);"/>
						<span class="Validform_checktip"></span>
						<label style="display: none;">附件</label>
					</td>
				</tr>
		    </li>
		    <li>
		      <label class="from-label-title">公告正文:</label>
		      	<textarea id="noticeContent" style="display: none;" name="noticeContent"></textarea>
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


$(function(){
	$("#form1").Validform({
		tiptype:3
	});
	$("#saveForm").click(function(){
    	$("#form1").submit();
  	});
})


layui.use(['form','layedit','laydate'], function(){
  var form = layui.form();
  var layedit = layui.layedit;
  var laydate = layui.laydate;
  layedit.build('noticeContent'); //建立编辑器
});

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

