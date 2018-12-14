<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<!--<link href="${pageContext.request.contextPath}/plug-in/frame/default/css/style.css" rel="stylesheet" type="text/css" />-->
<link href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/skin_/table.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/jquery.grid.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/jquery.select.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/bootstrap-3.3.7-dist/runoob/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/plug-in/bootstrap-3.3.7-dist/runoob/jquery.min.2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/bootstrap-3.3.7-dist/runoob/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/zeroclipboard-master/dist/ZeroClipboard.min.js"></script>

<t:base type="jquery,tools,DatePicker"></t:base>
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

  //当节点为根目录时  隐藏两个按钮
  var packageId=$("#pId").val();
  if(packageId=='1'){
	  $(".hiddenBtn").hide();
  }else{
	  $(".hiddenBtn").show();
  }
  
 
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
    <li class="layui-this">我的文档</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
      <div id="container">
      <div id="hd"></div>
      <div id="bd">
      <div id="main">
      <form name="searchForm" id="searchForm" method="post" action="docMyFileController.do?list" class="layui-form layui-form-pane ">
        <!-- pId是当前点击的文件或文件夹ID -->
        <input name="pId" value="${pId}" type="hidden" id="pId"/>
        <div class="layui-form-item">
          <label class="layui-form-label" style="width:120px;">文件名称:</label>
          <div class="layui-input-inline">
            <input id="fileName" name="fileName" placeholder="请输入文件名称" type="text" value="${fileName}" class="layui-input"/>
          </div>
          
          <div class="layui-input-inline"> &nbsp;&nbsp;&nbsp;&nbsp;
            <button class="layui-btn layui-btn-normal" onClick="$('#searchForm').submit();">查&nbsp;&nbsp;询</button>
          </div>
        </div>
      </form>
      <div class="btn-group">
        <button type="button" class="btn btn-primary" data-toggle="dropdown">新建在线文档</button>
        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"> <span class="caret"></span> <span class="sr-only">切换下拉菜单</span> </button>
        <ul class="dropdown-menu" role="menu">
          <li><a href="#" onClick="createOnline('officeWordController.do?createWord&pId=${pId}')">新建Word文档</a></li>
          <li><a href="#" onClick="createOnline('officeExcelController.do?createExcel&pId=${pId}')">新建Excel文档</a></li>
          <li><a href="#" onClick="createOnline('officePPTController.do?createPPT&pId=${pId}')">新建PPT文档</a></li>
        </ul>
      </div>
      <div class="btn-group">
        <button type="button" class="btn btn-primary" data-toggle="dropdown">新建格式文档</button>
        <button type="button" class="btn dropdown-toggle btn-primary" data-toggle="dropdown"> <span class="caret"></span> <span class="sr-only">切换下拉菜单</span> </button>
        <ul class="dropdown-menu" role="menu">
          <c:forEach items="${templateList }" var="templateList">
<%--           <li><a href="#" onClick="javascript:location.href='templateController.do?createTemplateOnline&id=${templateList.id }'">${templateList.title }</a></li> --%>
          <li><a href="#" onClick="createOnline('templateController.do?createTemplateOnline&pId=${pId}&id=${templateList.id }')">${templateList.title }</a></li>
          
          </c:forEach>
        </ul>
      </div>      
      <div class="btn-group">
        <button type="button" class="btn btn-primary " data-toggle="dropdown">文件夹管理</button>
        <button type="button" class="btn dropdown-toggle btn-primary " data-toggle="dropdown"> <span class="caret"></span> <span class="sr-only">切换下拉菜单</span> </button>
        <ul class="dropdown-menu" role="menu">
          <li><a href="#" onClick="createPackage()">新建文件夹</a></li>
          <li><a href="#" onClick="movePackage('${pId}')">移动文件夹</a></li>
          <li><a href="#" onClick="reNamePackage('${pId}')">重命名文件夹</a></li>
          <li><a href="#" onClick="delPackage('${pId}')">打包下载</a></li>
          <li><a href="#" onClick="delPackage('${pId}')">删除文件夹</a></li>
        </ul>
      </div>     
    
      <button class="btn btn-primary" onClick="deleteNewStyleALLSelect('提示','docMyFileController.do?doBatchDel','')"><i class="layui-icon">&#xe640;</i> 批量删除</button>
      <button class="btn btn-primary" onClick="deleteNewStyleALLSelect('提示','docMyFileController.do?doBatchDel','')"><i class="layui-icon">&#xe640;</i> 批量下载</button>
    <input class="layui-upload-file " id="fileElem" name="file" type="file" value="上传文件" lay-type="file"  multiple="multiple" lay-title="上传文件"/>    
        <div class="table" style="z-index: 99999999999;">
          <div class="grid" style="z-index: 99999999999;">
            <table class="layui-table" style="z-index: 99999999999;">
              <thead>
                <tr>
                  <th style="width:30px"><input name="selectAll" id="selectAll" type="checkbox"  /></th>
                  <th>文件名称</th>
                  <th style="width:120px">文件大小</th>
                  <th style="width:120px">上传时间</th>
                  <th style="width:120px">操作</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${resultList}" var="resultList">
                  <tr>
                    <input id="fileType" id="fileType" value="${resultList.fileType}" type="hidden">
                    </input>
                    <td><input name="id" id="id" type="checkbox" value="${resultList.id}" />
                    </td>
                    <td>
                    	<c:choose>
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
                    <td>
                    	<c:choose>
                    		<c:when test="${resultList.fileSize>1000000}">
                    			<fmt:formatNumber value="${resultList.fileSize/(1024*1024)}" type="number" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber>
                    			MB
                    		</c:when>
                    		<c:when test="${resultList.fileSize<1000000}">
                    			<fmt:formatNumber value="${resultList.fileSize/(1024)}" type="number" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber>
                    			KB
                    		</c:when>
                    	</c:choose>
                    </td>
                    <td><fmt:formatDate value="${resultList.uploadTime}" pattern="yyyy-MM-dd"/></td>
                    <td>
						<div class="btn-group">
						  <button data-toggle="dropdown" class="btn btn-success dropdown-toggle  btn-xs" type="button" > 文档操作 <span class="caret"></span> </button>
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
              <li class="paginItem"><a href="javascript:void(0);" onClick="onSearchForm('docMyFileController.do?list&str_currentPage=<%=currentPage-1 %>')"  style="width:76px;text-decoration: none;"><span class="pagepre"> < 上一页</span></a></li>
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
              <li class="paginItem"><a href="javascript:void(0);" onClick="onSearchForm('docMyFileController.do?list&str_currentPage=<%=i+1 %>')"><%=i+1 %></a></li>
              <%
											}
											}
										}
										if(currentPage<totalPage){
								%>
              <li class="paginItem"><a href="javascript:void(0);" onClick="onSearchForm('docMyFileController.do?list&str_currentPage=<%=currentPage+1 %>')"  style="width:76px;text-decoration: none;"><span class="pagenxt">下一页 > </span></a></li>
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
  </div><br><br><br><br><br><br><br><br><br><br><br><br><br>
</div>

</body>
</html>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/webpage/com/document/docmyfile/layuiJavaScript.js" charset="utf-8"></script>
