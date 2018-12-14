
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/skin_/main.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/jquery.dialog.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/global.js"></script>
<title>天津第一商业学校项目管理系统</title>
<script type="text/javascript">
function exit(url, content) {
	if(confirm(content)){
		parent.location.href="${pageContext.request.contextPath}/"+url;
	}
}
</script>
</head>

<body>
<div id="container">
	<div id="hd">
    	<div class="hd-top">
            <h1 class="logo"><a href="javascript:;" class="logo-icon"></a></h1>
            <div class="user-info">
                <a href="javascript:;" class="user-avatar" id="user-avatar"><span><i class="info-num">99</i></span></a>
                <span class="user-name">${realName }</span>
                <a href="javascript:;" class="more-info"></a>
            </div>
            <div class="setting ue-clear">
<!--             	<div class="setting-skin"> -->
<!--                 	<div class="switch-bar"> -->
<!--                     	<i class="skin-icon"></i> -->
<!--                         <span class="text">皮肤</span> -->
<!--                         <i class="arrow-icon"></i> -->
<!--                     </div> -->
<!--                 </div> -->
                <ul class="setting-main ue-clear">
                	<li><a href="javascript:;">桌面</a></li>
                    <li><a href="javascript:;">设置</a></li>
                    <li><a href="javascript:;">帮助</a></li>
                    <li><a href="javascript:;" onclick="exit('loginController.do?logout','确定退出该系统吗 ?');" class="close-btn exit"></a></li>
                </ul>
            </div>
        </div>
        <div class="hd-bottom">
        	<i class="home"><a href="javascript:;"></a></i>
        	<div class="nav-wrap">
                <ul class="nav ue-clear">
                <c:forEach items="${menuMap }" var="functionMap">
					<c:forEach items="${functionMap.value }" var="value">
						<c:if test="${value.TSFunction==null}">
                    		<li><a href="javascript:void(0);" onclick="topMenu('${value.id }')"><t:mutiLang langKey="${value.functionName }"></t:mutiLang>
                    		</a></li>
                        </c:if>
		        	</c:forEach>
		        </c:forEach>
                </ul>
            </div>
            <div class="nav-btn">
            	<a href="javascript:;" class="nav-prev-btn"></a>
                <a href="javascript:;" class="nav-next-btn"></a>
            </div>
        </div>
    </div>
    <div id="bd">
        <iframe width="100%" height="100%" id="mainIframe" src="loginController.do?left&systemId=${systemId }" frameborder="0"></iframe>
    </div>
    
    <div id="ft" class="ue-clear">
    	<div class="ft1 ue-clear">
        	<i class="ft-icon1"></i>
            <span>天津第一商业学校项目管理系统</span>
            <em></em>
        </div>
        <div class="ft2 ue-clear">
        	<span></span>
            <em>V1.0 2016</em>
            <i class="ft-icon2"></i>
        </div>
    </div>
</div>



<div class="opt-panel user-opt" id="user-opt" style="top:52px;left:330px;">
	<ul>
    	<li><a class="text">用户资料</a></li>
        <li><a class="text">短消息<span class="num">(20)</span></a></li>
        <li><a class="text">资料信息</a></li>
        <li><a class="text">注销</a></li>
        <li><a class="text">自定义</a></li>
    </ul>
    <div class="opt-panel-tl"></div>
    <div class="opt-panel-tc"></div>
    <div class="opt-panel-tr"></div>
    <div class="opt-panel-ml"></div>
    <div class="opt-panel-mr"></div>
    <div class="opt-panel-bl"></div>
    <div class="opt-panel-bc"></div>
    <div class="opt-panel-br"></div>
    <div class="opt-panel-arrow"></div>
</div>

<div class="opt-panel skin-opt" style="top:36px;right:157px;">
	<ul class="ue-clear">
    	<li attr-color="#0f8dc7"></li>
        <li attr-color="#1ea4a9"></li>
        <li attr-color="#eb7f00"></li>
        <li attr-color="#ed0f04"></li>
        <li attr-color="#f24b39"></li>
        <li attr-color="#ed0f04"></li>
        <li attr-color="#eb7f00"></li>
        <li attr-color="#ed0f04"></li>
        <li attr-color="#1ea4a9"></li>
    </ul>
    <div class="opt-panel-ml"></div>
    <div class="opt-panel-mr"></div>
    <div class="opt-panel-bl"></div>
    <div class="opt-panel-bc"></div>
    <div class="opt-panel-br"></div>
</div>

<!-- <div class="system-switch"> -->
<!-- 	<ul> -->
<%-- 		<c:forEach items="${menuMap }" var="functionMap"> --%>
<%-- 			<c:forEach items="${functionMap.value }" var="value"> --%>
<%-- 				<c:if test="${value.TSFunction==null }"> --%>
<!-- 			    	<li> -->
<%-- 			            <span class="system-name" onclick="javascript:location.href='loginController.do?login&systemId=${value.id }';"><i></i>${value.functionName }</span> --%>
<!-- 			        </li> -->
<%--         		</c:if> --%>
<%--         	</c:forEach> --%>
<%--         </c:forEach> --%>
        
<!--     </ul> -->
<!--     <div class="opt-panel-mr"></div> -->
<!--     <div class="opt-panel-bc"></div> -->
<!--     <div class="opt-panel-br"></div> -->
<!-- </div> -->

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/jquery.dialog.js"></script>
<script type="text/javascript">
$("#bd").height($(window).height()-$("#hd").outerHeight()-26);

$(window).resize(function(e) {
    $("#bd").height($(window).height()-$("#hd").outerHeight()-26);

});




(function(){
	var totalWidth = 0, current = 1;
	
	$.each($('.nav').find('li'), function(){
		totalWidth += $(this).outerWidth();
	});
	
	$('.nav').width(totalWidth);
	
	function currentLeft(){
		return -(current - 1) * 93;	
	}
	
	$('.nav-btn a').click(function(e) {
		var tempWidth = totalWidth - ( Math.abs($('.nav').css('left').split('p')[0]) + $('.nav-wrap').width() );
        if($(this).hasClass('nav-prev-btn')){
			if( parseInt($('.nav').css('left').split('p')[0])  < 0){
				current--;
				Math.abs($('.nav').css('left').split('p')[0]) > 93 ? $('.nav').animate({'left': currentLeft()}, 200) : $('.nav').animate({'left': 0}, 200);
			}
		}else{

			if(tempWidth  > 0)	{
				
			   	current++;
				tempWidth > 93 ? $('.nav').animate({'left': currentLeft()}, 200) : $('.nav').animate({'left': $('.nav').css('left').split('p')[0]-tempWidth}, 200);
			}
		}
    });
	
	
	
	$.each($('.skin-opt li'),function(index, element){
		if((index + 1) % 3 == 0){
			$(this).addClass('third');	
		}
		$(this).css('background',$(this).attr('attr-color'));
	});
	
	$('.setting-skin').click(function(e) {
        $('.skin-opt').show();
    });
	
	$('.skin-opt').click(function(e) {
        if($(e.target).is('li')){
			alert($(e.target).attr('attr-color'));	
		}
    });
	
	$('.hd-top .user-info .more-info .user-avatar').click(function(e) {
       $(this).toggleClass('active'); 
	   $('.user-opt').toggle();
    });
	
	$('#user-avatar').click(function(e) {
	       //$(this).toggleClass('active'); 
		   $('#user-opt').show();
	});
	
	$('.logo-icon').click(function(e) {
         $(this).toggleClass('active'); 
	     $('.system-switch').toggle();
    });
	
	hideElement($('.user-opt'), $('.more-info'), function(current, target){

		$('.more-info').removeClass('active'); 
	});
	
	hideElement($('.skin-opt'), $('.switch-bar'));
	
	hideElement($('.system-switch'), $('.logo-icon'), function(current, target){

		$('.logo-icon').removeClass('active'); 
	});
	
	
	
})();

function topMenu(systemId){
	var url = "loginController.do?left&systemId="+systemId;
	$("#mainIframe").attr("src",url);
}
	

</script>
</html>

