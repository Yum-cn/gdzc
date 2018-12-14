<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<t:base type="jquery,tools,DatePicker"></t:base>
<!--<link href="${pageContext.request.contextPath}/plug-in/frame/default/css/style.css" rel="stylesheet" type="text/css" />-->
<link href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/skin_/table.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/jquery.grid.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/jquery.select.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all">
</link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/bootstrap-3.3.7-dist/runoob/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/plug-in/bootstrap-3.3.7-dist/runoob/jquery.min.2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/bootstrap-3.3.7-dist/runoob/bootstrap.min.js"></script>
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

  
 
});
</script>
<style type="text/css">
.layui-upload-button {
	position: relative;
	display: inline-block;
	vertical-align: middle;
	min-width: 60px;
	height: 36px;
	line-height: 36px;
	border: 1px solid #DFDFDF;
	border-radius: 2px;
	overflow: hidden;
	background-color: #009688;
	color: #fff;
	
		white-space: nowrap;
	text-align: center;
	border: none;
	border-radius: 2px;
	cursor: pointer;
	opacity: .9;
	filter: alpha(opacity = 90)
}
</style>
</head>
<body>

<div class="rightinfo">
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">最新文档</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
      <div id="container">
      <div id="hd"></div>
      <div id="bd">
      <div id="main">
      <form name="searchForm" id="searchForm" method="post" action="docMyFileController.do?queryRecentFile" class="layui-form ">
        <!-- pId是当前点击的文件或文件夹ID -->
        <input name="pId" value="${pId}" type="hidden" id="pId"/>
        <div class="table" style="z-index: 99999999999;">
          <div class="grid" style="z-index: 99999999999;">
            <table class="layui-table" style="z-index: 99999999999;">
              <thead>
                <tr>
                  <!-- <th><input name="selectAll" id="selectAll" type="checkbox"  /></th> -->
                  <th>序号</th>
                  <th>文件名称</th>
                  <th>文件大小</th>
                  <th>上传时间</th>
                  <th>操作</th>
                  <!-- <th>操作</th> -->
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${resultList}" var="resultList" varStatus="i">
                  <tr>
                    <input id="fileType" id="fileType" value="${resultList.fileType}" type="hidden">
                    </input>
                    <%-- <td><input name="id" id="id" type="checkbox" value="${resultList.id}" />
                    </td> --%>
                    <td>${i.count}</td>
                    <td><c:choose>
                        <c:when test="${resultList.fileType=='pdf'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/pdf.ico"  style="height:18px;"/> </c:when>
                        <c:when test="${resultList.fileType=='doc'}"> <img src="${pageContext.request.contextPath}/images/file_icon/office/Word.ico" style="height:18px;"/> </c:when>
                        <c:when test="${resultList.fileType=='docx'}"> <img src="${pageContext.request.contextPath}/images/file_icon/office/Word.ico" style="height:18px;" /> </c:when>
                        <c:when test="${resultList.fileType=='png'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/png.ico"  style="height:18px;"/> </c:when>
                        <c:when test="${resultList.fileType=='txt'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/text.ico"  style="height:18px;"/> </c:when>
                        <c:when test="${resultList.fileType=='avi'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/avi.ico"  style="height:18px;"/> </c:when>
                        <c:when test="${resultList.fileType=='fla'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/fla.ico"  style="height:18px;"/> </c:when>
                        <c:when test="${resultList.fileType=='mp3'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/mp3.ico"  style="height:18px;"/> </c:when>
                        <c:when test="${resultList.fileType=='gif'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/gif.ico"  style="height:18px;"/> </c:when>
                        <c:when test="${resultList.fileType=='jpeg'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/jpeg.ico"  style="height:18px;"/> </c:when>
                        <c:when test="${resultList.fileType=='jpg'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/jpeg.ico"  style="height:18px;"/> </c:when>
                        <c:when test="${resultList.fileType=='mov'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/mov.ico"  style="height:18px;"/> </c:when>
                        <c:when test="${resultList.fileType=='xls'}"> <img src="${pageContext.request.contextPath}/images/file_icon/office/Excel.ico"  style="height:18px;"/> </c:when>
                        <c:when test="${resultList.fileType=='xlsx'}"> <img src="${pageContext.request.contextPath}/images/file_icon/office/Excel.ico"  style="height:18px;"/> </c:when>
                        <c:when test="${resultList.fileType=='ppt'}"> <img src="${pageContext.request.contextPath}/images/file_icon/office/PowerPoint.ico"  style="height:18px;"/> </c:when>
                        <c:when test="${resultList.fileType=='pptx'}"> <img src="${pageContext.request.contextPath}/images/file_icon/office/PowerPoint.ico"  style="height:18px;"/> </c:when>
                      </c:choose>
                      ${resultList.fileName}.${resultList.fileType}</td>
                    <td>${resultList.fileSize}</td>
                    <td><fmt:formatDate value="${resultList.uploadTime}" pattern="yyyy-MM-dd"/></td>
                    <td>
						<div class="btn-group">
						  <button data-toggle="dropdown" class="btn btn-success dropdown-toggle btn-xs" type="button"> 文档操作 <span class="caret"></span> </button>
						  <ul role="menu" class="dropdown-menu">
						  	<c:choose>
	                        <c:when test="${resultList.fileType=='doc' || resultList.fileType=='docx'}"> 
	                        <li><a href="javascript:void(0);" onclick="openMaxWindow('officeWordController.do?editWord&id=${resultList.id}')">编辑文档</a></li>
	                        <li><a href="javascript:void(0);" onclick="openMaxWindow('officeWordController.do?readWord&id=${resultList.id}')">阅读文档</a></li>
	                        <li><a href="javascript:void(0);" onclick="openMaxWindow('officeWordController.do?printWord&id=${resultList.id}')">在线打印</a></li>

	                        <li><a href="javascript:void(0);" onclick="sentTeamwork('${resultList.id}','${resultList.fileName}.${resultList.fileType}')">发布协同</a></li>
	                        <li class="divider"></li>
	                        <li><a href="javascript:void(0);" onclick="version('${resultList.id}')">历史版本</a></li>
	                        </c:when>
						  	<c:when test="${resultList.fileType=='xls' || resultList.fileType=='xlsx'}"> 
						  	<li><a href="javascript:void(0);" onclick="openMaxWindow('officeExcelController.do?editExcel&id=${resultList.id}')">编辑文档</a></li>
	                        <li><a href="javascript:void(0);" onclick="openMaxWindow('officeExcelController.do?readExcel&id=${resultList.id}')">阅读文档</a></li>
	                        <li><a href="javascript:void(0);" onclick="openMaxWindow('officeExcelController.do?printExcel&id=${resultList.id}')">在线打印</a></li>
	                        <li><a href="javascript:void(0);" onclick="sentTeamwork('${resultList.id}','${resultList.fileName}.${resultList.fileType}')">发布协同</a></li>
	                        
	                        <li class="divider"></li>
	                        <li><a href="javascript:void(0);" onclick="version('${resultList.id}')">历史版本</a></li>
	                        </c:when>
	                        <c:when test="${resultList.fileType=='ppt' || resultList.fileType=='pptx'}"> 
	                        <li><a href="javascript:void(0);" onclick="openMaxWindow('officePPTController.do?editPPT&id=${resultList.id}')">编辑文档</a></li>
	                        <li><a href="javascript:void(0);" onclick="openMaxWindow('officePPTController.do?readPPT&id=${resultList.id}')">阅读文档</a></li>
	                        <li><a href="javascript:void(0);" onclick="openMaxWindow('officePPTController.do?printPPT&id=${resultList.id}')">在线打印</a></li>
	                        <li><a href="javascript:void(0);" onclick="sentTeamwork('${resultList.id}','${resultList.fileName}.${resultList.fileType}')">发布协同</a></li>
	                        <li class="divider"></li>
	                        <li><a href="javascript:void(0);" onclick="version('${resultList.id}')">历史版本</a></li>
	                        
	                        </c:when>
	                        <c:when test="${resultList.fileType=='gif' || resultList.fileType=='jpeg' || resultList.fileType=='jpg' || resultList.fileType=='png'}"> 
	                        <li><a href="javascript:void(0);" onclick="viewImages('${resultList.filePath}')">查看</a></li>
	                        </c:when>
	                      </c:choose>
<%-- 	                      <li><a href="javascript:void(0);" onclick="sentEmail('${resultList.id}','${resultList.fileName}.${resultList.fileType}')">发送邮件</a></li> --%>
	                      <li><a href="javascript:void(0);" onclick="sentMass('${resultList.id}','${resultList.fileName}.${resultList.fileType}')">内部群发</a></li>
						  <li class="divider"></li>
							<li><a href="javascript:void(0);" onclick="reName('${resultList.id}','${resultList.fileName}')">重命名</a></li>
							<li><a href="javascript:void(0);" onclick="fileTree('${resultList.id}')">移动</a></li>
							<li><a href="javascript:void(0);" onclick="copyFile('${resultList.id}')">复制</a></li>
							<li><a href="docMyFileController.do?downloadFiles&id=${resultList.id}">下载</a></li>
							<li><a href="javascript:void(0);" onclick="falseDel('${resultList.id}')">删除</a></li>
							<li><a href="javascript:void(0);" onclick="createShare('${resultList.id}')">分享</a></li>
							<li><a href="javascript:void(0);" onclick="property('${resultList.id}')">属性</a></li>
						  </ul>
						</div>
                      </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
          <div class="pagin">
            <%
					String currentPageString = String.valueOf(request.getAttribute("currentPage"));
					String pageRowString = String.valueOf(request.getAttribute("pageRow"));
					String totalRowString = String.valueOf(request.getAttribute("totalRow"));
					String totalPageString = String.valueOf(request.getAttribute("totalPage"));
					int currentPage = Integer.parseInt(currentPageString);
					int pageRow = Integer.parseInt(pageRowString);
					int totalRow = Integer.parseInt(totalRowString);
					int totalPage = Integer.parseInt(totalPageString);
				  %>
            <div class="message">共<i class="blue">&nbsp;&nbsp;${totalRow }&nbsp;&nbsp;</i>条记录，当前显示第&nbsp;<i class="blue">&nbsp;&nbsp;${currentPage }&nbsp;&nbsp;</i>页</div>
            <ul class="paginList">
              <%
										if(currentPage>1){
								%>
              <li class="paginItem"><a href="javascript:void(0);" onClick="onSearchForm('docMyFileController.do?queryRecentFile&str_currentPage=<%=currentPage-1 %>')"  style="width:76px;text-decoration: none;"><span class="pagepre"> < 上一页</span></a></li>
              <%
										}else{
								%>
              <li class="paginItem current"><a href="javascript:;" style="width:76px;text-decoration: none;">< 上一页</a></li>
              <%
										}
										int startView = currentPage - 4;
										int endView = currentPage + 4;
										for(int i=0;i<totalPage;i++){
											if((i+1)>=startView && (i+1)<=endView){
											if(String.valueOf((i+1)).equals(currentPageString)){
								%>
              <li class="paginItem current"><a href="javascript:;"><%=i+1 %></a></li>
              <%
											}else{
								%>
              <li class="paginItem"><a href="javascript:void(0);" onClick="onSearchForm('docMyFileController.do?queryRecentFile&str_currentPage=<%=i+1 %>')"><%=i+1 %></a></li>
              <%
											}
											}
										}
										if(currentPage<totalPage){
								%>
              <li class="paginItem"><a href="javascript:void(0);" onClick="onSearchForm('docMyFileController.do?queryRecentFile&str_currentPage=<%=currentPage+1 %>')"  style="width:76px;text-decoration: none;"><span class="pagenxt">下一页 > </span></a></li>
              <%
										}else{
								%>
              <li class="paginItem current"><a href="javascript:;" style="width:76px;text-decoration: none;">下一页 ></a></li>
              <%
										}
									
								%>
            </ul>
          </div>
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
<script src="${pageContext.request.contextPath}/webpage/com/document/docmyfile/layuiJavaScript.js" charset="utf-8"></script>
<!-- <script>
	//layer 的使用 必须要有这个
	layui.use('layer', function(){
	  var layer = layui.layer;
	});              
	//还原
   function restoreFile(rfId){
	   layer.open({
		   title:'确认还原',
		   content: '是否还原此文件'
		   ,btn: ['是', '否']
		   ,yes: function(index, layero){
		      //按钮【按钮一】的回调
			   $.post(
				"docMyFileController.do?restoreFile",
				{id:rfId},
				function(res){
					var resJSON = $.parseJSON(res);
					layer.close(index);
					layer.msg(resJSON.msg,{time:2000,icon:1},function(){
						location.reload();//刷新页面
					});
				}
			   );
		     
		   },btn2: function(index, layero){
		     //按钮【按钮二】的回调
		   }
		   ,cancel: function(){ 
		     //右上角关闭回调
		   }
		 });
   }


</script> -->
