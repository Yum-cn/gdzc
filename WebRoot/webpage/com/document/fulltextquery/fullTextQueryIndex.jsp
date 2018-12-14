<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<t:base type="jquery,tools,DatePicker"></t:base>
<!--<link href="${pageContext.request.contextPath}/plug-in/frame/default/css/style.css" rel="stylesheet" type="text/css" />-->
<link href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/skin_/table.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/jquery.grid.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/jquery.select.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>
<script type="text/javascript">
$(document).ready(function(){
  //$(".click").click(function(){
  //$(".tip").fadeIn(200);
  //});
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

  
  var packageId=$("#pId").val();
  if(packageId=='1'){
	  $(".hiddenBtn").hide();
  }else{
	  $(".hiddenBtn").show();
  }
  
});
</script>

</head>
<body>

<div class="rightinfo">
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
<!--   <ul class="layui-tab-title"> -->
<!--     <li class="layui-this">全文检索</li> -->
<!--   </ul> -->
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
<div id="container">
	<div id="hd"></div>
    <div id="bd">
    	<div id="main">
    	  <form name="searchForm" id="searchForm" method="post" action="fullTextQueryController.do?list" class="layui-form ">
    	    <!-- pId是当前点击的文件或文件夹ID -->
    	    <input name="pId" value="${pId}" type="hidden" id="pId"/>
	    	  <div class="layui-form-item" style="position: absolute;left: 38%;top: 20%;">
    <a class="logo" style="width:350px;">
      <img src="${pageContext.request.contextPath}/plug-in/skin2.0/images/logo-1.png" alt="FMS" style="width:350px;">
    </a>
			  </div>
	    	  <div class="layui-form-item" style="position: absolute;left: 30%;top: 30%;">
	    	  
	    	    <label class="layui-form-label">关键字:</label>
                <div class="layui-input-inline" style="width:290px;">
   					<input id="keyword" name="keyword" placeholder="请输入关键字" type="text" value="${keyword}" class="layui-input" style="width:300px;" />
                </div>
			    <div class="layui-input-inline">
			      &nbsp;&nbsp;&nbsp;&nbsp;<button class="layui-btn layui-btn-normal" onclick="$('#searchForm').submit();">搜索一下</button>
			    </div>
			  </div>
    	  </form>

                

            </div>
        </div>
    </div>
</div>

</form>

    </div>

  </div>
</div> 

  

</body>
</html>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>


