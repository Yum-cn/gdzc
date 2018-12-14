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
</head>
<body>

<div class="rightinfo">
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">新模板</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
	<div id="container">
	<div id="hd"></div>
    <div id="bd">
    	<div id="main">
    	<form name="searchForm" id="searchForm" method="post" action="docLibFileController.do?list" class="layui-form layui-form-pane">
    	    <!-- pId是当前点击的文件或文件夹ID -->
        	<input name="pId" value="${pId}"  type="hidden" id="pId"/>
    	  
    	  <div class="layui-form-item">
	    	  <label class="layui-form-label" style="width:120px;">文件名称:</label>
	          <div class="layui-input-inline">
	            <input id="fileName" name="fileName" placeholder="请输入文件名称" type="text" value="${fileName}" class="layui-input"/>
	          </div>
    	  
		    <div class="layui-input-inline">
		      &nbsp;&nbsp;&nbsp;&nbsp;<button class="layui-btn layui-btn-normal" onclick="$('#searchForm').submit();">查&nbsp;&nbsp;询</button>
		    </div>
		  </div>
    	  </form>
          <button class="layui-btn" onclick="add('docLibFileController.do?goAdd')"><i class="layui-icon"></i> 添加</button>
          <button class="layui-btn" onclick="deleteNewStyleALLSelect('提示','docLibFileController.do?doBatchDel','')"><i class="layui-icon">&#xe640;</i> 批量删除</button>
          <input class="layui-upload-file " id="fileElem" name="file" type="file" value="上传文件" lay-type="file"  multiple="multiple" lay-title="上传文件"/> 
          <form name="searchForm" id="searchForm" method="post" action="docLibFileController.do?list" >
            <div class="table"  style="z-index: 99999999999;">


                <div class="grid"  style="z-index: 99999999999;">
					  <table class="layui-table"  style="z-index: 99999999999;">
					    <thead>
					      <tr>
									<th><input name="selectAll" id="selectAll" type="checkbox"  /></th>			
									<th>文件名称</th>
									<th>文件大小</th>
									<th>上传日期</th>
									<th>文件类型</th>
					        <th>操作</th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${resultList}" var="resultList">
					      <tr>
									<td><input name="id" id="id" type="checkbox" value="${resultList.id}" />	</td>			
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
									${resultList.fileName}</td>

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

									<td><fmt:formatDate value="${resultList.uploadDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>


									<td>${resultList.fileType}</td>

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
								        </c:when>
								        </c:choose>
								        <li class="divider"></li>
										<li><a href="javascript:void(0);" onclick="reName('${resultList.id}','${resultList.fileName}')">重命名</a></li>
										<li><a href="javascript:void(0);" onclick="fileTree('${resultList.id}')">移动</a></li>
										<li><a href="javascript:void(0);" onclick="copyFile('${resultList.id}')">复制</a></li>
										<li><a href="docLibFileController.do?downloadFiles&id=${resultList.id}">下载</a></li>
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
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('docLibFileController.do?list&str_currentPage=<%=currentPage-1 %>')"  style="width:76px;text-decoration: none;"><span class="pagepre"> < 上一页</span></a></li>
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
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('docLibFileController.do?list&str_currentPage=<%=i+1 %>')"><%=i+1 %></a></li>
								
								<%
											}
											}
										}
										if(currentPage<totalPage){
								%>
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('docLibFileController.do?list&str_currentPage=<%=currentPage+1 %>')"  style="width:76px;text-decoration: none;"><span class="pagenxt">下一页 > </span></a></li>
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

  </div><br><br><br><br><br><br><br><br><br><br>
</div> 

  

<script type="text/javascript">
	function add(url){
		window.location.href=url;
	}
	function update(url){
		window.location.href=url;
	}
	//$('.tablelist tbody tr:odd').addClass('odd');
	function onSearchForm(url){
		$("#searchForm").attr("action",url);
		$("#searchForm").submit();
	}
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'docLibFileController.do?upload', "docLibFileList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("docLibFileController.do?exportXls","docLibFileList");
	}
</script>
</body>
</html>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/webpage/com/document/doclibfile/libFileJS.js" charset="utf-8"></script>
<script>
layui.use(['form'], function(){
  var form = layui.form()
});
</script>
