<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>部署流程</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">

<c:if test="${not empty taskOperateUrl }">
	<iframe src="${taskOperateUrl}" width="100%" height="80%" FRAMEBORDER=0 id="iframeChild"></iframe>
</c:if>
<c:if test="${empty  taskOperateUrl }">
	<iframe src="activitiController.do?openProcessHandle&taskId=${taskId }"  id="iframeChild" width="100%" height="40%" FRAMEBORDER=0></iframe>
</c:if>
<script type="text/javascript">
		$('#passBtn').linkbutton({   
		});  
		$('#returnBtn').linkbutton({   
		}); 
		function procPass(yes){
			var iframe  = window.frames["iframeChild"].document;
			var inputvar = $("[vartype]", iframe);
			setvar(yes, inputvar, window.frames["iframeChild"]);
			var formData = {};
			$(iframe).find("input,textarea,select").each(function(){
				formData[$(this).attr("name")]= $(this).val();
			});
			var formAction = iframe.forms["formobj"].action;
			//ajax方式提交iframe内的表单
			$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				data : formData,
				url : formAction,// 请求的action路径
				error : function() {// 请求失败处理函数
				},
				success : function(data) {
					var d = $.parseJSON(data);
					if (d.success) {
						var msg = d.msg;
						W.tip(msg);
						W.reloadTable();
						windowapi.close();
					}
				}
			});
		}
</script>
<div style="padding: 3px; height: 25px; width: auto;" class="datagrid-toolbar">
<a id="passBtn" onclick="procPass(true)">同意</a> 
<a id="returnBtn" onclick="procPass(false)">驳回</a>
</div>

</body>
</html>
