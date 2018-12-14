<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <t:base type="ckeditor,jquery,tools,DatePicker"></t:base>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="ThemeBucket">
  <link rel="shortcut icon" href="#" type="image/png">

  <title>Mail Compose</title>

  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webpage/login/assets/js/bootstrap-wysihtml5/bootstrap-wysihtml5.css" />

  <link href="${pageContext.request.contextPath}/webpage/login/assets/css/style.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/webpage/login/assets/css/style-responsive.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="${pageContext.request.contextPath}/webpage/login/assets/js/html5shiv.js"></script>
  <script src="${pageContext.request.contextPath}/webpage/login/assets/js/respond.min.js"></script>
  <![endif]-->
  <meta charset="utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>



</head>

<body>

<!--body wrapper start-->
                <section class="mail-box-info">
                    <section class="mail-list"  style="margin-top: 6px;height: 487px;">
                        <div class="compose-mail">
                            <form class="layui-form" role="form-horizontal" method="post">
                                <div class="form-group">
                                    <label for="to" class="">接收人:</label>
                                    <input type="text" tabindex="1" class="form-control" id="receiveUser" name="receiveUser" datatype="*" onclick="selectUser()">
                                    <input type="hidden" class="form-control" id="receiveUserId" name="receiveUserId" datatype="*">
                                    <input type="hidden" class="form-control" id="fileType" name="fileType" datatype="*" value="${fileType }">
                                	<i id="Validform_email"></i>
                                </div>

                                <div class="form-group ">
                                    <label for="cc" class="">使用权限:</label>
								      <input type="checkbox" name="usePermission" title="编辑" value="1"  >
								      <input type="checkbox" name="usePermission" title="阅读" checked="" value="2">
								      <input type="checkbox" name="usePermission" title="打印" value="3">
								      <input type="checkbox" name="usePermission" title="下载" value="4">
                                </div>

                                <div class="form-group" style="margin-top: 3px;">
                                    <label for="subject" class="">主题:</label>
                                    <input type="text" tabindex="3" id="title" name="title" class="form-control" datatype="*" value="${fileName }">
                                </div>

                                <div class="form-group">
                                    <t:ckeditor name="content" isfinder="false" value=""></t:ckeditor>
                                </div>
                            </form>
                        </div>
                    </section>
                </section>

        <!--body wrapper end-->

<!-- Placed js at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/modernizr.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery.nicescroll.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/webpage/login/assets/js/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webpage/login/assets/js/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>


<!--common scripts for all pages-->
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/scripts.js"></script>
<script>
    jQuery(document).ready(function(){
        $('.wysihtml5').wysihtml5();
    });
</script>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/lhgDialog/lhgdialog.min.js?skin=metrole"></script>
<script type="text/javascript">
// 	var windowapi = frameElement.api, W = windowapi.opener;
	function selectUser() {
		$.dialog({
			content : 'url:userController.do?selectUserCheckBox',
			lock : true,
			width : 1000,
			height : 450,
			title : "接收人",
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
		if ($('#receiveUser').length >= 1) {
			$('#receiveUser').val(realName);
			$('#receiveUser').blur();
		}

		var id = iframe.getuserListSelections('id');
		if (id !== undefined && id != "") {
			$('#receiveUserId').val(id);
		}
	}
	
	layui.use('form', function(){
		  var form = layui.form(); //只有执行了这一步，部分表单元素才会修饰成功
	}); 
</script>

