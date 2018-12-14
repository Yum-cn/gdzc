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


</head>

<body>

<!--body wrapper start-->
                <section class="mail-box-info">
                    <section class="mail-list"  style="margin-top: 6px;height: 487px;">
                        <div class="compose-mail">
                            <form role="form-horizontal" method="post">
                                <div class="form-group">
                                    <label for="to" class="">收件人:</label>
                                    <input type="text" tabindex="1" class="form-control" id="addressee" name="addressee" datatype="*">
                                	<i id="Validform_email"></i>
                                </div>

                                <div class="form-group ">
                                    <label for="cc" class="">抄送:</label>
                                    <input type="text" tabindex="2" class="form-control" id="carbonCopy" name="carbonCopy" datatype="*">
                                </div>


                                <div class="form-group">
                                    <label for="subject" class="">邮件主题:</label>
                                    <input type="text" tabindex="3" id="emailTitle" name="emailTitle" class="form-control" datatype="*" value="${fileName }">
                                </div>

                                <div class="form-group">
                                    <t:ckeditor name="emailContent" isfinder="false" value=""></t:ckeditor>
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


