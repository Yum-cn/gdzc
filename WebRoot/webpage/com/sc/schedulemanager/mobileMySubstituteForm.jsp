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




		     <div class="">
		      <div class="weui-cell ">
		        <div class="weui-cell__bd">代课班级</div>
		        <div class="weui-cell__ft" style="font-size: 0">
		          <span style="vertical-align:middle; font-size: 17px;">${scSubstituteManagerPage.substituteClass }</span>
		        </div>
		      </div>
		      <div class="weui-cell ">
		        <div class="weui-cell__bd">代课节次</div>
		        <div class="weui-cell__ft" style="font-size: 0">
		          <span style="vertical-align:middle; font-size: 17px;"><t:listDictParse parseId="${scSubstituteManagerPage.substituteSection}" style="1" typecode="JIECI"></t:listDictParse></span>
		        </div>
		      </div>
		      <div class="weui-cell ">
		        <div class="weui-cell__bd">代课时间</div>
		        <div class="weui-cell__ft" style="font-size: 0">
		          <span style="vertical-align:middle; font-size: 17px;"><fmt:formatDate pattern="yyyy-MM-dd" value="${scSubstituteManagerPage.substituteTime }" /></span>
		        </div>
		      </div>  
		      <div class="weui-cell ">
		        <div class="weui-cell__bd">代课地点</div>
		        <div class="weui-cell__ft" style="font-size: 0">
		          <span style="vertical-align:middle; font-size: 17px;">${scSubstituteManagerPage.address }</span>
		        </div>
		      </div>  
		      <div class="weui-cell ">
		                  <div class="weui-media-box__bd">
              <h4 class="weui-media-box__title">备注</h4>
              <p class="weui-media-box__desc">${scSubstituteManagerPage.remarks }</p>
            </div>
		      </div>
  		      
		      <div class="weui-cell ">
		        <div class="weui-cell__bd"></div>
		        <div class="weui-cell__ft" style="font-size: 0">
		          <span style="vertical-align:middle; font-size: 17px;"></span>
		        </div>
		      </div>                  
			</div>
 

    <div class="weui-btn-area">
<!--       <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">接受代课</a> -->
<!--       <a class="weui-btn weui-btn_warn" href="javascript:" id="show-confirm">拒绝代课</a> -->
      <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips" onclick="javascript:location='scheduleManagerController.do?mobileIndex'">返回首页</a>
    </div>
	<br>
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
$(document).on("click", "#show-confirm", function() {
    $.confirm("您确定要拒绝本条代课信息吗?", "确认拒绝代课?", function() {
      $.toast("文件已经删除!");
    }, function() {
      //取消操作
    });
  });
</script>