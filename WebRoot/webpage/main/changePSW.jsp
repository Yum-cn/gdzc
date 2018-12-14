<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改个人信息</title>
<link href="${pageContext.request.contextPath}/webpage/com/document/schedule/schedule.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>
<script src="${pageContext.request.contextPath}/plug-in/bootstrap-3.3.7-dist/runoob/jquery.min.2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery.nicescroll.js"></script>

<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery-1.10.2.min.js"></script>

<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>

</head>
<body>
      <form class="layui-form layui-form-pane " id="form1" style="margin-left: 30px;margin-top: 18px;" method="post" action="${pageContext.request.contextPath}/userController.do?updatePSW">
        <div class="layui-form-item" align="center">
          <label class="layui-form-label" style="width:20%;">原密码:</label>
          <div class="layui-input-inline" style="width:68%">
            <input name="oldPSW" id="oldPSW" placeholder="请输入原密码"  lay-verify="checkedOldPsw" autocomplete="off" class="layui-input" type="password">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label" style="width:20%;">新密码</label>
          <div class="layui-input-inline" style="width:68%">
          	<input name="newPSW" id="newPSW" placeholder="请输入新密码" lay-verify="pass" autocomplete="off" class="layui-input" type="password">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label" style="width:20%;">确认新密码:</label>
          <div class="layui-input-inline" style="width:68%">
          	<input name="confirmPSW" id="confirmPSW" placeholder="请再次输入新密码" lay-verify="confirmPass" autocomplete="off" class="layui-input" type="password">
          </div>
        </div>
        <div class="layui-form-item">
		  <div class="layui-input-block">
		    <button class="layui-btn"  lay-submit="" type="button" lay-filter="demo1" onclick="checkPassword()">立即提交</button>
		    <button type="reset" id="resetBTN" class="layui-btn layui-btn-primary">重置</button>
		  </div>
		</div>        
</form>
</body>
</html>

<script>
	layui.use('form', function(){
	  form = layui.form();
	  
	  //各种基于事件的操作，下面会有进一步介绍
	});
	
	function checkPassword(){
		//自定义验证规则
		  form.verify({
		   checkedOldPsw:function(value){
			   //验证密码不能为空
			   var reg = /^\s*$/g;
			   if(reg.test(value)||value==""){
				   return '请输入原密码';

			   }else{
				   //密码不为空时的验证 (是否是原密码)
				   $.post(
							"userController.do?checkOldPSW",
							{password:value},
							function(res){
								var resOBJ = $.parseJSON(res);
								if(!resOBJ.success){
									layer.msg('原密码不正确',{anim:6,icon:5},function(){
										$("#resetBTN").click();
									});
									
								}else{
									//验证新密码长度
									var checkLength = /(.+){6,12}$/;
									if(checkLength.test($("#newPSW").val())){
										//验证二次输入密码是否与新密码一致
										var newPassword = $("#newPSW").val();
										var confirmPSW = $("#confirmPSW").val();
								    	if(confirmPSW==newPassword){
								    		//所有验证都通过  提交表单
								    		layer.msg('密码修改成功!',{time:2000,icon:1},function(){
								    			$("#form1").submit();
								    			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
								    			parent.layer.close(index); //再执行关闭   
								    		});
								    	}else{
								    		layer.msg('两次输入的密码不一致',{anim:6,icon:5},function(){
												$("#resetBTN").click();
											});
								    	}
									}else{
										layer.msg('新密码长度在6-12之间',{anim:6,icon:5},function(){
											$("#resetBTN").click();
										});
									}
								}
							}
					   );
			   }
			   
		   }
		  });
		}
		
</script>