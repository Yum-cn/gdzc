<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>教师日程管理系统</title>
    <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">

<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">

<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/lib/weui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/css/jquery-weui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/demos/css/demos.css">

  </head>

  <body ontouchstart>



<form name="form1" id="form1" method="post" action="userController.do?mobileSavenewpwd">
    <div class="weui-cells_form">
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">原密码</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input" placeholder="请输入原密码" type="password" value="" name="password" id="password">
        </div>
      </div>
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">新密码</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input" placeholder="请输入新密码" type="password" value="" name="newpassword" id="newpassword">
        </div>
      </div>
<!--       <div class="weui-cell"> -->
<!--         <div class="weui-cell__hd"><label class="weui-label">重复密码</label></div> -->
<!--         <div class="weui-cell__bd"> -->
<!--           <input class="weui-input" placeholder="请输入重复密码" name="title" id="renewpassword" type="password"> -->
<!--         </div> -->
<!--       </div>       -->
      <div class="weui-cell">
        <div class="weui-cell__hd"><label for="" class="weui-label"></label></div>
        <div class="weui-cell__bd">
        </div>
      </div>
    </div>
 

    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips" onclick="checkinfo();">修改密码</a>
    </div>
	<br>
	</form>
    <script src="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/lib/jquery-2.1.4.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/lib/fastclick.js"></script>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
<script src="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/js/jquery-weui.js"></script>
<script src="${pageContext.request.contextPath}/webpage/com/sc/schedulemanager/tools.js"></script>
  </body>

</html>
<script>
$(function(){
	 var msg = "${message }";
	 if(msg!=null && msg!='' && msg!='null' && msg!=undefined){
		 $.toptip(msg, 10000, 'error');
	 }
});
function checkinfo(){
	 var password = $("#password").val();
	 var newpassword = $("#newpassword").val();
	 if($.trim(password)==""){
		 $.toast("密码信息不完整", "cancel");
		 return false;
	 }else if($.trim(newpassword)==""){
		 $.toast("密码信息不完整", "cancel");
		 return false;
	 }
	 $('#form1').submit();
	 return true;
	 
}
</script>