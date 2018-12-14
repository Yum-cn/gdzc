<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增试卷</title>
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
    <li class="layui-this">新增试卷</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
		<form name="form1" id="form1" class="layui-form"  method="post" action="questionnaireController.do?doAdd">
		<div class="formbody">
		  <ul class="forminfo">
		    <li>
		      <label class="from-label-title">试卷名称:</label>
		      	<input id="name" name="name" type="text" style="width: 350px" class="dfinput" lay-verify="required">
		      <i class="Validform_checktip"></i>
		    </li>
		    <li>
		      <label class="from-label-title">导语:</label>
		      	<textarea name="synopsis" id="synopsis"   lay-verify="required"  style="width: 354px;height:100px;border: 1px solid #ced9df"></textarea>
		      <i class="Validform_checktip"></i>
		    </li>
		    <li  id="content">
		      <label class="from-label-title">试题列表:</label>
		      	<a href="#"  style="line-height: 35px" onClick="choose_402893814fcb1a65014fcb1bef100003()">
					<i class="layui-icon">&#xe615;</i>选择试题
				</a>
		      <i class="Validform_checktip"></i>
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
</html>
<script src = "webpage/com/train/questionnaire/questionnaire.js"></script>	
  		<script type="text/javascript">
  		layui.use('form', function(){
  		  var form = layui.form();
  		});
  		
  		
		    var windowapi = frameElement.api,
		    W = windowapi.opener;
		    function choose_402893814fcb1a65014fcb1bef100003() {
		    	var questionIdObj = $("input[name='questionId']");
		    	var questionId = "";
		    	if(questionIdObj != undefined){
			    	for(var i = 0;i < questionIdObj.length;i++){
			    		questionId = questionId + "," + $(questionIdObj[i]).val();
			    	}
			    	questionId = questionId.substr(1,questionId.length);
		    	}
		    	
		        if (typeof(windowapi) == 'undefined') {
		            $.dialog({
		                content: 'url:questionLibraryController.do?selectQuestionLibrary&qid='+questionId,
		                zIndex: 2100,
		                title: '题库列表',
		                lock: false,
		                width: 400,
		                height: 350,
		                left: '85%',
		                top: '65%',
		                opacity: 0.4,
		                button: [{
		                    name: '确认',
		                    callback: clickcallback_402893814fcb1a65014fcb1bef100003,
		                    focus: true
		                },
		                {
		                    name: '取消',
		                    callback: function() {}
		                }]
		            }).max();
		        } else {
		            $.dialog({
		                content: 'url:questionLibraryController.do?selectQuestionLibrary&qid='+questionId,
		                zIndex: 2100,
		                title: '题库列表',
		                lock: false,
		                parent: windowapi,
		                width: 400,
		                height: 350,
		                left: '85%',
		                top: '65%',
		                opacity: 0.4,
		                button: [{
		                    name: '确认',
		                    callback: clickcallback_402893814fcb1a65014fcb1bef100003,
		                    focus: true
		                },
		                {
		                    name: '取消',
		                    callback: function() {}
		                }]
		            }).max();
		        }
		    }

		    function clickcallback_402893814fcb1a65014fcb1bef100003() {
		        iframe = this.iframe.contentWindow;
		        var questionTitle = iframe.getquestionLibraryListSelections('title');
		        var questionStyle = iframe.getquestionLibraryListSelections('style');
		        var questionIds = iframe.getquestionLibraryListSelections('id');
		        //$("#qid").val(questionIds);
		        if (questionTitle != undefined && questionTitle != "") {
		            for(var i=0;i<questionTitle.length;i++){
		            	var styleName = "";
		            	if(questionStyle[i]=='DANXUAN')
		            		styleName='单选题';
		            	if(questionStyle[i]=='DUOXUAN')
		            		styleName='多选题';
		            	if(questionStyle[i]=='PAIXU')
		            		styleName='排序题';
		            	if(questionStyle[i]=='TIANKONG')
		            		styleName='填空题';
		            	if(questionStyle[i]=='JIANDATI')
		            		styleName='简答题';
		            	
			    		var html ='<li><input type="hidden" name="questionId" class="inputxt" value="'+questionIds[i]+'" id="questionId"  />';
			    		html = html+ '		<label class="from-label-title">';
			    		html = html+ '			'+styleName+':';
			    		html = html+ '		</label>';
			    		html = html+ '	<span style="line-height:35px">'+questionTitle[i]+'<i class="layui-icon" onclick="remove_line(this);">&#xe640;</i> </span>';
			    		html = html+ '	</li>';
			    		$("#content").append(html);
		            }
		        }
		        //alert($("#content").html());
		    }
			function remove_line(index) {
				$(index).parent().parent().remove();
			}
			
			 
		</script>

