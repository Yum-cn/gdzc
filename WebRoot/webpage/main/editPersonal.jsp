<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改个人信息</title>
<link href="${pageContext.request.contextPath}/webpage/com/document/schedule/schedule.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>

</head>
<body>
      <div class="layui-form layui-form-pane "  style="margin-left: 30px;margin-top: 18px;">
        <div class="layui-form-item" align="center">
          <label class="layui-form-label" style="width:20%;">用户名:</label>
          <div class="layui-input-inline" style="width:68%">
            <input id="userName" name="userName"  type="text" class="layui-input" value="${user.userName }"/>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label" style="width:20%;">真实姓名</label>
          <div class="layui-input-inline" style="width:68%">
          	<input type="text" id="realName" name="realName" class="layui-input" value="${user.realName }">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label" style="width:20%;">手机号码:</label>
          <div class="layui-input-inline" style="width:68%">
          	<input type="text" id="mobilePhone" name="mobilePhone" class="layui-input" value="${user.mobilePhone }">
          </div>
        </div>        
        <div class="layui-form-item">
          <label class="layui-form-label" style="width:20%;">办公电话:</label>
          <div class="layui-input-inline" style="width:68%">
          	<input type="text" id="officePhone" name="officePhone" class="layui-input" value="${user.officePhone }">
          </div>
        </div>        
        <div class="layui-form-item">
          <label class="layui-form-label" style="width:20%;">邮箱:</label>
          <div class="layui-input-inline" style="width:68%">
          	<input type="text" id="email" name="email" class="layui-input" value="${user.email }">
          </div>
        </div>        
    </div>

</body>
</html>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
<script>

</script>