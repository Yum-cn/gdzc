<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<%
	java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy");    
	String currentYear=sdf.format(new java.util.Date()); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>固定资产管理系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/css/global.css" media="all">
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery.nicescroll.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/font-awesome/css/font-awesome.min.css">
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery-1.10.2.min.js"></script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header header header-demo">
  <div class="layui-main">
    <a class="logo" style="width:350px;">
      <%-- <img src="${pageContext.request.contextPath}/plug-in/skin2.0/images/logo-1.png" alt="FMS" style="width:390px;height:38px;"> --%>
    </a>
      <ul class="layui-nav admin-header-item">
		<c:forEach items="${menuMap }" var="functionMap">
			<c:forEach items="${functionMap.value }" var="value">
				<c:if test="${value.TSFunction==null}">
					<li class="layui-nav-item ${value.id==systemId?'layui-this':'' }"><a href="javascript:;" onclick="topMenu('${value.id }')"><t:mutiLang langKey="${value.functionName }"></t:mutiLang></a></li>
				</c:if>
			</c:forEach>
		</c:forEach>

        <li class="layui-nav-item"> <a href="javascript:;" class="admin-header-user"> <img src="${pageContext.request.contextPath}/plug-in/skin2.0/images/0.jpg" /> <span>${realName }</span> </a>
          <dl class="layui-nav-child">
            <dd> <a href="javascript:;" onclick="personal()"><i class="fa fa-user-circle" aria-hidden="true"></i> 个人信息</a> </dd>
			<dd> <a href="javascript:;" onclick="goChangePSW()"><i class="fa fa-sign-out" aria-hidden="true"></i>修改密码</a> </dd>
<!--             <dd> <a href="javascript:;"><i class="fa fa-gear" aria-hidden="true"></i> 设置</a> </dd> -->
            <dd> <a href="javascript:;" onclick="exit('loginController.do?logout','确定退出该系统吗 ?');"><i class="fa fa-sign-out" aria-hidden="true"></i> 退出系统</a> </dd>
          </dl>
        </li>
      </ul>
      <ul class="layui-nav admin-header-item-mobile">
        <li class="layui-nav-item"> <a href="login.html"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a> </li>
      </ul>
    </div>
  </div>
  <div class="layui-side layui-bg-black" id="admin-side">
    <div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side">
		<ul class="layui-nav layui-nav-tree site-demo-nav">
  			<t:menu menuFun="${menuMap}" style="hplus" systemId="${systemId }"></t:menu>
<!-- 			<li class="layui-nav-item" style="height: 30px; text-align: center"></li> -->
		</ul>
    </div>
  </div>
  
  <div class="layui-body " lay-filter="demoTitle">
    <!--<ul class="layui-tab-title site-demo-title">
      <li class="layui-this" style="width:120px;">项目信息管理</li>
    </ul>-->
    <div class="layui-tab-content  layui-tab-brief">
      <div class="layui-tab-item layui-show">
		<div class="site-demo-result" style=" width: 100%; height: 99%;"><a></a>
			<iframe src="${pageContext.request.contextPath}/DeskMainCountController.do?list" id="mainFrame" name="mainFrame" frameborder="no"></iframe>
		</div>
      </div>

  </div>
  </div><a></a>
  <div class="layui-footer footer footer-demo" id="admin-footer">
    <div class="layui-main">
      <p>Copyright &copy; 2012-<%=currentYear %>  中共北辰区纪律检查委员会 北辰区监察委员会</p>
    </div>
  </div>
  <div class="site-tree-mobile layui-hide"> <i class="layui-icon">&#xe602;</i> </div>
  <div class="site-mobile-shade"></div>
  <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js"></script>
<%--   <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/skin2.0/datas/nav.js" ></script> --%>
  <script src="${pageContext.request.contextPath}/plug-in/skin2.0/js/index.js"></script>

</div>
</body>
</html>
<script type="text/javascript">
function topMenu(systemId){
	var url = "loginController.do?login&systemId="+systemId;
	location.href=url;
	//$("#mainIframe").attr("src",url);
}
function exit(url, content) {
    layer.open({
        type: 1
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: '260px;'
        ,shade: 0.8
        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        ,btn: ['确定注销', '继续使用']
        ,moveType: 1 //拖拽模式，0或者1
        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">您确定需要退出系统吗？</div>'
        ,success: function(layero){
          var btn = layero.find('.layui-layer-btn');
          btn.css('text-align', 'center');
          btn.find('.layui-layer-btn0').attr({
            href: "${pageContext.request.contextPath}/"+url
            ,target: '_self'
          });
        }
      });
// 	if(confirm(content)){
// 		parent.location.href="${pageContext.request.contextPath}/"+url;
// 	}
}

//修改个人信息
	function personal(){
		layer.open({
			type:2,
			content:'userController.do?personal',
			title:'修改个人信息',
			area:['500px','400px'],
			btnAlign: 'c',
			btn:['保存'],
			yes:function(index,layero){
			      //保存的回调函数
			     
				  var body = layer.getChildFrame('body', index);
				  var userName=body.find('#userName').val();
				  var realName=body.find('#realName').val();
				  var mobilePhone=body.find('#mobilePhone').val();
				  var officePhone=body.find('#officePhone').val();
				  var email=body.find('#email').val();
				  $.post(
					"userController.do?updatePersonalInfo",
					{userName:userName,realName:realName,mobilePhone:mobilePhone,officePhone:officePhone,email:email},
					function(res){
						var resO=$.parseJSON(res);
						layer.close(index);
						layer.msg(resO.msg, {
				    		  icon: 1,
				    		  time: 1000 //1秒关闭（如果不配置，默认是3秒） 单位是毫秒
				    	  }, function(){
				    		 window.parent.location.reload();
				    	}); 
					}
				  );
			  }
			
		});
	
	}


//修改密码

  function goChangePSW(){
	layer.open({
		type:2,
		content:'userController.do?goChangePSW',
		title:'修改密码',
		area:['500px','300px']
	})
	
  }
</script>
