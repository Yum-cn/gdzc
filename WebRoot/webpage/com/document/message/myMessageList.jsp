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

});
</script>
</head>
<body>

<div class="rightinfo">
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">我的消息</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
<div id="container">
	<div id="hd"></div>
    <div id="bd">
    	<div id="main">
    	<form name="searchForm" id="searchForm" method="post" action="messageController.do?list" class="layui-form layui-form-pane">
    	  <div class="layui-form-item">
		    <label class="layui-form-label">创建日期:</label>
		    <div class="layui-input-inline">
				<input id="createDate" name="createDate" type="text" class="Wdate layui-input" onClick="WdatePicker()" value="<fmt:formatDate value="${createDate}" pattern="yyyy-MM-dd"/>" placeholder="请选择日期" autocomplete="off" class="layui-input"/>
		    </div>
		    <label class="layui-form-label">消息标题:</label>
		    <div class="layui-input-inline">
				<input id="title" name="title" type="text" value="${title}" placeholder="请输入消息标题" autocomplete="off" class="layui-input"/>
		    </div>
		    <div class="layui-input-inline">
		      &nbsp;&nbsp;&nbsp;&nbsp;<button class="layui-btn layui-btn-normal" onclick="$('#searchForm').submit();">查&nbsp;&nbsp;询</button>
		    </div>
		  </div>
    	  </form>
<!--           <button class="layui-btn" onclick="add('messageController.do?goAdd')"><i class="layui-icon"></i> 添加</button> -->
<!--           <button class="layui-btn" onclick="deleteNewStyleALLSelect('提示','messageController.do?doBatchDel','')"><i class="layui-icon">&#xe640;</i> 批量删除</button> -->
          <form name="searchForm" id="searchForm" method="post" action="messageController.do?list" >
            <div class="table">


                <div class="grid">
					  <table class="layui-table">
					    <thead>
					      <tr>
									<th style="width:60px"><input name="selectAll" id="selectAll" type="checkbox"  /></th>			
									<th>消息标题</th>
<!-- 									<th>消息内容</th> -->
									<th style="width:80px">消息类型</th>
<!-- 									<th>文档ID</th> -->
									<th style="width:100px">发送人</th>
									<th style="width:150px">发送时间</th>
					        		<th style="width:180px">操作</th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${resultList}" var="resultList">
					      <tr>
									<td><input name="id" id="id" type="checkbox" value="${resultList.id}" />	</td>			
									<td>${resultList.title}</td>

<%-- 									<td>${resultList.content}</td> --%>

									<td>
									<c:if test="${resultList.type eq '1'}">协同文档</c:if>
									<c:if test="${resultList.type eq '2'}">消息文档</c:if>
									</td>

<%-- 									<td>${resultList.documentId}</td> --%>

									<td>${resultList.sendUser}</td>

									<td><fmt:formatDate value="${resultList.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

					        <td><a href="messageController.do?goUpdate&id=${resultList.id}" class="tablelink">阅读消息</a>
					        <c:if test="${resultList.type eq '1'}">
					    	  <c:choose>
		                        <c:when test="${resultList.fileType=='doc' || resultList.fileType=='docx'}"> 
		                        <a href="javascript:void(0);" onclick="openMaxWindow('officeWordController.do?editWord&id=${resultList.documentId}')" class="tablelink">编辑文档</a>
		                        </c:when>
							  	<c:when test="${resultList.fileType=='xls' || resultList.fileType=='xlsx'}"> 
							  	<a href="javascript:void(0);" onclick="openMaxWindow('officeExcelController.do?editExcel&id=${resultList.documentId}')" class="tablelink">编辑文档</a>
		                        </c:when>
		                        <c:when test="${resultList.fileType=='ppt' || resultList.fileType=='pptx'}"> 
		                        <a href="javascript:void(0);" onclick="openMaxWindow('officePPTController.do?editPPT&id=${resultList.documentId}')" class="tablelink">编辑文档</a>
		                        </c:when>
		                      </c:choose>
					        </c:if>
					        <c:if test="${resultList.type eq '2'}">
					    	  <c:choose>
		                        <c:when test="${(resultList.fileType=='doc' || resultList.fileType=='docx') && fn:indexOf(resultList.usePermission,'1')!=-1}"> 
		                        <a href="javascript:void(0);" onclick="openMaxWindow('officeWordController.do?editWord&id=${resultList.documentId}')" class="tablelink">编辑文档</a>
		                        </c:when>
							  	<c:when test="${(resultList.fileType=='xls' || resultList.fileType=='xlsx') && fn:indexOf(resultList.usePermission,'1')!=-1}"> 
							  	<a href="javascript:void(0);" onclick="openMaxWindow('officeExcelController.do?editExcel&id=${resultList.documentId}')" class="tablelink">文档</a>
		                        </c:when>
		                        <c:when test="${(resultList.fileType=='ppt' || resultList.fileType=='pptx') && fn:indexOf(resultList.usePermission,'1')!=-1}"> 
		                        <a href="javascript:void(0);" onclick="openMaxWindow('officePPTController.do?editPPT&id=${resultList.documentId}')" class="tablelink">编辑文档</a>
		                        </c:when>
		                      </c:choose>
		                      <c:choose>
		                        <c:when test="${(resultList.fileType=='doc' || resultList.fileType=='docx') && fn:indexOf(resultList.usePermission,'2')!=-1}"> 
		                        <a href="javascript:void(0);" onclick="openMaxWindow('officeWordController.do?readWord&id=${resultList.documentId}')" class="tablelink">阅读</a>
		                        </c:when>
							  	<c:when test="${(resultList.fileType=='xls' || resultList.fileType=='xlsx') && fn:indexOf(resultList.usePermission,'2')!=-1}"> 
							  	<a href="javascript:void(0);" onclick="openMaxWindow('officeExcelController.do?readExcel&id=${resultList.documentId}')" class="tablelink">阅读</a>
		                        </c:when>
		                        <c:when test="${(resultList.fileType=='ppt' || resultList.fileType=='pptx') && fn:indexOf(resultList.usePermission,'2')!=-1}"> 
		                        <a href="javascript:void(0);" onclick="openMaxWindow('officePPTController.do?readPPT&id=${resultList.documentId}')" class="tablelink">阅读</a>
		                        </c:when>
		                      </c:choose>
		                      <c:choose>
		                        <c:when test="${(resultList.fileType=='doc' || resultList.fileType=='docx') && fn:indexOf(resultList.usePermission,'2')!=-1}"> 
		                        <a href="javascript:void(0);" onclick="openMaxWindow('officeWordController.do?printWord&id=${resultList.documentId}')" class="tablelink">打印</a>
		                        </c:when>
							  	<c:when test="${(resultList.fileType=='xls' || resultList.fileType=='xlsx') && fn:indexOf(resultList.usePermission,'2')!=-1}"> 
							  	<a href="javascript:void(0);" onclick="openMaxWindow('officeExcelController.do?printExcel&id=${resultList.documentId}')" class="tablelink">打印</a>
		                        </c:when>
		                        <c:when test="${(resultList.fileType=='ppt' || resultList.fileType=='pptx') && fn:indexOf(resultList.usePermission,'2')!=-1}"> 
		                        <a href="javascript:void(0);" onclick="openMaxWindow('officePPTController.do?printPPT&id=${resultList.documentId}')" class="tablelink">打印</a>
		                        </c:when>
		                      </c:choose>
		                      <c:choose>
		                        <c:when test="${fn:indexOf(resultList.usePermission,'4')!=-1}"> 
		                        <a href="docMyFileController.do?downloadFiles&id=${resultList.documentId}" class="tablelink">下载</a>
		                        </c:when>
		                      </c:choose>
					        </c:if>
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
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('messageController.do?list&str_currentPage=<%=currentPage-1 %>')"  style="width:76px;text-decoration: none;"><span class="pagepre"> < 上一页</span></a></li>
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
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('messageController.do?list&str_currentPage=<%=i+1 %>')"><%=i+1 %></a></li>
								
								<%
											}
											}
										}
										if(currentPage<totalPage){
								%>
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('messageController.do?list&str_currentPage=<%=currentPage+1 %>')"  style="width:76px;text-decoration: none;"><span class="pagenxt">下一页 > </span></a></li>
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
		openuploadwin('Excel导入', 'messageController.do?upload', "myMessageList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("messageController.do?exportXls","myMessageList");
	}
	function openMaxWindow(url){
		window.open("${pageContext.request.contextPath}/"+url, url, "height="+(window.screen.height)+", width="+(window.screen.width)+", toolbar= no, menubar=no, scrollbars=no, resizable=yes, location=no, status=yes,top=0,left=0");
	}
</script>
</body>
</html>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
<script>
layui.use(['form'], function(){
  var form = layui.form()
});
</script>
