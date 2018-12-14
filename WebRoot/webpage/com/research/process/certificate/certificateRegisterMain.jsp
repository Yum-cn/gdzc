<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<div id="maintabs" class="easyui-tabs" fit="true" border="false">
<div class="easyui-tab" title="集体获奖" href="certificateRegisterController.do?certificateRegister&type=1&version=10283874" style="padding: 2px; overflow: hidden;"></div>
<div class="easyui-tab" title="教师获奖" href="certificateRegisterController.do?certificateRegister1&type=2" style="padding: 2px; overflow: hidden;"></div>
<div class="easyui-tab" title="学生获奖" href="certificateRegisterController.do?certificateRegister2&type=3" style="padding: 2px; overflow: hidden;"></div>

</div>

               </body>
</html>