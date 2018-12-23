<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/webpage/login/assets/images/login-logo.png" type="image/png">

    <title>固定资产管理系统</title>

    <link href="${pageContext.request.contextPath}/webpage/login/assets/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/webpage/login/assets/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/webpage/login/assets/js/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/webpage/login/assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-body">

<div class="container">

    <form id="loinForm" class="form-signin"  check="loginController.do?checkuser"  role="form" action="loginController.do?login"  method="post">
        <div class="form-signin-heading text-center">
            <h1 class="sign-title">&nbsp;</h1>
            <img src="${pageContext.request.contextPath}/webpage/login/assets/images/login-logo.png" alt=""/>
        </div>
        
        <div class="login-wrap">
        	<div class="alert alert-danger" id="message" style="display:none;"></div>
            <input type="text" class="form-control" placeholder="请输入用户名" autofocus name="userName" id="userName" value="admin"> 
            <input type="password" class="form-control" placeholder="请输入密码" name="password" id="password" value="123456">
            <button class="btn btn-lg btn-login btn-block" type="button" onclick="checkUser()">
                <i class="fa fa-check"></i>
            </button>

            <label class="checkbox" style="display:none;">
                <span class="pull-right">
					<t:dictSelect id="langCode" field="langCode" typeGroupCode="lang" hasLabel="false" extendJson="{style:'padding:2px; width:80px;'}" defaultVal="zh-cn"></t:dictSelect>
                </span>
            </label>

        </div>



    </form>

</div>



<!-- Placed js at the end of the document so the pages load faster -->

<!-- Placed js at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/modernizr.min.js"></script>

</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/mutiLang/en.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/mutiLang/zh-cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/login/js/jquery.tipsy.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/login/js/iphone.check.js"></script>
<script type="text/javascript">
	$(function(){
		optErrMsg();
	});
	$("#errMsgContiner").hide();
	function optErrMsg(){
		$("#showErrMsg").html('');
		$("#errMsgContiner").hide();
	}

   //输入验证码，回车登录
  $(document).keydown(function(e){
  	if(e.keyCode == 13) {
  		$("#but_login").click();
  	}
  });

  //验证用户信息
  function checkUser(){
    if(!validForm()){
      return false;
    }
    newLogin();
  }
  //表单验证
  function validForm(){
    if($.trim($("#userName").val()).length==0){
      showErrorMsg("请输入用户名");
      return false;
    }

    if($.trim($("#password").val()).length==0){
      showErrorMsg("请输入密码");
      return false;
    }

//     if($.trim($("#randCode").val()).length==0){
//       showErrorMsg("请输入验证码");
//       return false;
//     }
    return true;
  }

  //登录处理函数
  function newLogin(orgId) {
    setCookie();
    var actionurl=$('form').attr('action');//提交路径
    var checkurl=$('form').attr('check');//验证路径
    var formData = new Object();
    var data=$(":input").each(function() {
      formData[this.name] =$("#"+this.name ).val();
    });
    formData['orgId'] = orgId ? orgId : "";
    //语言
    formData['langCode']=$("#langCode").val();
    formData['langCode'] = $("#langCode option:selected").val();
    $.ajax({
      async : false,
      cache : false,
      type : 'POST',
      url : checkurl,// 请求的action路径
      data : formData,
      error : function() {// 请求失败处理函数
      },
      success : function(data) {
        var d = $.parseJSON(data);
        if (d.success) {
          if (d.attributes.orgNum > 1) {
          	  //用户拥有多个部门，需选择部门进行登录
        	  var title, okButton;
              if($("#langCode").val() == 'en') {
                title = "Please select Org";
                okButton = "Ok";
              } else {
                title = "请选择组织机构";
                okButton = "确定";
              }
            $.dialog({
              id: 'LHG1976D',
              title: title,
              max: false,
              min: false,
              drag: false,
              resize: false,
              content: 'url:userController.do?userOrgSelect&userId=' + d.attributes.user.id,
              lock:true,
              button : [ {
                name : okButton,
                focus : true,
                callback : function() {
                  iframe = this.iframe.contentWindow;
                  var orgId = $('#orgId', iframe.document).val();
                  formData['orgId'] = orgId ? orgId : "";
                  $.ajax({
              		async : false,
              		cache : false,
              		type : 'POST',
              		url : 'loginController.do?changeDefaultOrg',// 请求的action路径
              		data : formData,
              		error : function() {// 请求失败处理函数
              		},
              		success : function(data) {
              			window.location.href = actionurl;
              		}
                  });
                  this.close();
                  return false;
                }
              }],
              close: function(){
                setTimeout("window.location.href='"+actionurl+"'", 10);
              }
            });
          } else {
            window.location.href = actionurl;
          }
       } else {
			showErrorMsg(d.msg);
        }
      }
    });
  }
  //登录提示消息显示
  function showErrorMsg(msg){//alert(msg)
	  $("#message").show();
	  $("#message").html(msg);
    //$("#errMsgContiner").show();
   // $("#showErrMsg").html(msg);
    //window.setTimeout(optErrMsg,3000); 
  }
  /**
   * 刷新验证码
   */
$('#randCodeImage').click(function(){
    reloadRandCodeImage();
});
function reloadRandCodeImage() {
    var date = new Date();
    var img = document.getElementById("randCodeImage");
    img.src='randCodeImage?a=' + date.getTime();
}

  function darkStyle(){
    $('body').attr('class', 'login-layout');
    $('#id-text2').attr('class', 'red');
    $('#id-company-text').attr('class', 'blue');
    e.preventDefault();
  }
  function lightStyle(){
    $('body').attr('class', 'login-layout light-login');
    $('#id-text2').attr('class', 'grey');
    $('#id-company-text').attr('class', 'blue');

    e.preventDefault();
  }
  function blurStyle(){
    $('body').attr('class', 'login-layout blur-login');
    $('#id-text2').attr('class', 'white');
    $('#id-company-text').attr('class', 'light-blue');

    e.preventDefault();
  }
//设置cookie
  function setCookie()
  {
  	if ($('#on_off').val() == '1') {
  		$("input[iscookie='true']").each(function() {
  			$.cookie(this.name, $("#"+this.name).val(), "/",24);
  			$.cookie("COOKIE_NAME","true", "/",24);
  		});
  	} else {
  		$("input[iscookie='true']").each(function() {
  			$.cookie(this.name,null);
  			$.cookie("COOKIE_NAME",null);
  		});
  	}
  }
  //读取cookie
  function getCookie()
  {
  	var COOKIE_NAME=$.cookie("COOKIE_NAME");
  	if (COOKIE_NAME !=null) {
  		$("input[iscookie='true']").each(function() {
  			$($("#"+this.name).val( $.cookie(this.name)));
              if("admin" == $.cookie(this.name)) {
                  $("#randCode").focus();
              } else {
                  $("#password").val("");
                  $("#password").focus();
              }
          });
  		$("#on_off").attr("checked", true);
  		$("#on_off").val("1");
  	} 
  	else
  	{
  		$("#on_off").attr("checked", false);
  		$("#on_off").val("0");
        $("#randCode").focus();
  	}
  }
</script>