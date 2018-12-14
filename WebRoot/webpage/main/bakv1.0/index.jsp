<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/global.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/jquery-ui-1.9.2.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/bootstrap-3.3.7-dist/runoob/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/bootstrap-3.3.7-dist/runoob/style.css">
<link href="${pageContext.request.contextPath}/plug-in/bootstrap-3.3.7-dist/font-awesome/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/skin_/index.css" />
<title>首页</title>
</head>

<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">系统首页</li>
  </ul>
  <div class="layui-tab-content" style="height: 100px;">
    <div class="layui-tab-item layui-show">
    
<div id="container">
	<div id="hd">
    </div>
    <div id="bd">
    	<div id="main">
    	<!--mini statistics start-->
<div class="row">
    <div class="col-md-3">
        <div class="mini-stat clearfix">
            <span class="mini-stat-icon orange"><i class="fa fa-gavel"></i></span>
            <div class="mini-stat-info">
                <span>320G</span>
                	系统存储量
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="mini-stat clearfix">
            <span class="mini-stat-icon tar"><i class="fa fa-tag"></i></span>
            <div class="mini-stat-info">
                <span>22450份</span>
                	文档总数量
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="mini-stat clearfix">
            <span class="mini-stat-icon pink"><i class="fa fa-money"></i></span>
            <div class="mini-stat-info">
                <span>34,320次</span>
                	系统总访问量
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="mini-stat clearfix">
            <span class="mini-stat-icon green"><i class="fa fa-eye"></i></span>
            <div class="mini-stat-info">
                <span>327次</span>
                	今日访问量
            </div>
        </div>
    </div>

</div>
<!--mini statistics end-->

            
            <ul class="content-list">
            	<li class="content-item system">
                	<h2 class="content-hd">
                    	<span class="title">通知公告</span>
                    </h2>
                    <div class="content-bd">
                    	<img src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/img/skin_/pic1.png" />
                    </div>
                </li>
                <li class="content-item dothings">
                	<h2 class="content-hd">
                    	<span class="title">我的协同</span>
                    </h2>
                    <div class="content-bd">
                    	<ul class="content-list things">
                        	<li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">查干湖冬捕壮观景象</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">江西新余：一座城为重病男童圆梦</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">学生建4.5平米1室1厅1厨1卫 欲申请专利</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">央视直播曝光中纪委办公区</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">盘点那些年曾“脱光光”的十大女星</a>
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="content-item richeng">
                	<h2 class="content-hd">
                    	<span class="title">我的消息</span>
                    </h2>
                    <div class="content-bd">
                    	<ul class="content-list things">
                        	<li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">查干湖冬捕壮观景象</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">江西新余：一座城为重病男童圆梦</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">学生建4.5平米1室1厅1厨1卫 欲申请专利</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">央视直播曝光中纪委办公区</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">盘点那些年曾“脱光光”的十大女星</a>
                            </li>
                        </ul>
                    </div>
                </li>
                
                <li class="content-item system">
                	<h2 class="content-hd">
                    	<span class="title">我的日程</span>
                    </h2>
                    <div class="content-bd">
                    	<img src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/img/skin_/pic2.png" />
                    </div>
                </li>
                
                <li class="content-item news">
                	<h2 class="content-hd">
                    	<span class="title">最新文档</span>
                    </h2>
                    <div class="content-bd">
                    	<ul class="content-list things">
                        	<li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">查干湖冬捕壮观景象</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">江西新余：一座城为重病男童圆梦</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">学生建4.5平米1室1厅1厨1卫 欲申请专利</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">央视直播曝光中纪委办公区</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">盘点那些年曾“脱光光”的十大女星</a>
                            </li>
                        </ul>
                    </div>
                </li>
                
                <li class="content-item news">
                	<h2 class="content-hd">
                    	<span class="title">操作日志</span>
                    </h2>
                    <div class="content-bd">
                    	<ul class="content-list things">
                        	<li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">查干湖冬捕壮观景象</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">江西新余：一座城为重病男童圆梦</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">学生建4.5平米1室1厅1厨1卫 欲申请专利</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">央视直播曝光中纪委办公区</a>
                            </li>
                            <li class="content-list-item">
                            	<i class="icon"></i>
                                <a href="javascript:;">盘点那些年曾“脱光光”的十大女星</a>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>    
    
    
	</div>
  </div>
</div> 

</body>

<script type="text/javascript">
	var minwidth = 282;
	resizeWidth();
	$(top.window).resize(function(e) {
       resizeWidth();
    });
	$(function() {
// 		$( ".content-list" ).sortable({
// 		  revert: true,
// 		  handle:'h2'
// 		});
		
	});
	
function resizeWidth (){
	if($('#main').width() / 3 < minwidth){
		$('.content-item').width(($('#main').width() / 2) - 15);
	}else{
		$('.content-item').width(($('#main').width() / 3) - 15);	
	}
		
}
</script>
</html>
