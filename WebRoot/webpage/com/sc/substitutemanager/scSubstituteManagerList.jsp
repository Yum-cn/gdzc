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
<form name="searchForm" id="searchForm" method="post" action="scSubstituteManagerController.do?list">
<div id="container">
	<div id="hd"></div>
    <div id="bd">
    	<div id="main">
        	<div class="search-box ue-clear">
            	<div class="search-area">
                    <div class="kv-item">
                               <label>&nbsp;&nbsp;&nbsp;&nbsp;请假人:</label>
		                        <div class="kv-item-content">
							      	<input id="leaveMan" name="leaveMan" type="text" type="text" value="${leaveMan}"/>
		                        </div>
                                <label>&nbsp;&nbsp;&nbsp;&nbsp;代课人:</label>
		                        <div class="kv-item-content">
							      	<input id="substituteMan" name="substituteMan" type="text" type="text" value="${substituteMan}"/>
		                        </div>
		                        <label>&nbsp;&nbsp;&nbsp;&nbsp;代课班级:</label>
		                        <div class="kv-item-content">
							      	<input id="substituteClass" name="substituteClass" type="text" type="text" value="${substituteClass}"/>
		                        </div>
		                        <label>&nbsp;&nbsp;&nbsp;&nbsp;代课时间:</label>
		                        <div class="kv-item-content">
							      	<input id="substituteTime" name="substituteTime" type="text" type="text" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${substituteTime }" />" class="Wdate"  onFocus="WdatePicker({isShowClear:false,readOnly:true})"/>
		                        </div>
                        <div class="kv-item-content">
                            &nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" class="scbtn" value="查  询" onclick="$('#searchForm').submit();"/>
                        </div>
                        
                    </div>
                </div>

             </div>
             
            <div class="table">
            	<div class="opt ue-clear">

                	<span class="optarea">
                        <a href="javascript:;" onclick="add('scSubstituteManagerController.do?goAdd')" class="add">
                            <i class="icon"></i>
                            <span class="text">分配代课</span>
                        </a>
                        <a href="javascript:;"  onclick="deleteNewStyleALLSelect('提示','scSubstituteManagerController.do?doBatchDel','')" class="delete">
                            <i class="icon"></i>
                            <span class="text">批量删除</span>
                        </a>
                        <a href="javascript:;" onclick="ExportXls()" class="add">
                            <i class="icon"></i>
                            <span class="text">导出Excel</span>
                        </a>
                    </span>
                </div>
                
                <div class="grid">
					  <table class="tablelist">
					    <thead>
					      <tr>
									<th><input name="selectAll" id="selectAll" type="checkbox"  /></th>			
<!-- 									<th>流程状态</th> -->
									<th>请假人</th>
<!-- 									<th>请假人ID</th> -->
									<th>代课人</th>
<!-- 									<th>代课人ID</th> -->
<!-- 									<th>代课人OPENID</th> -->
									<th>代课班级</th>
									<th>代课节次</th>
									<th>代课时间</th>
<!-- 									<th>备注</th> -->
					        <th>操作</th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${resultList}" var="resultList">
					      <tr>
									<td><input name="id" id="id" type="checkbox" value="${resultList.id}" />	</td>			
<%-- 									<td><t:listDictParse parseId="${resultList.bpmStatus}" style="1" typecode="bpm_status"></t:listDictParse></td>	 --%>
									<td>${resultList.leaveMan}</td>

<%-- 									<td>${resultList.leaveUserid}</td> --%>

									<td>${resultList.substituteMan}</td>

<%-- 									<td>${resultList.substituteUserid}</td> --%>

<%-- 									<td>${resultList.substituteOepnid}</td> --%>

									<td>${resultList.substituteClass}</td>

									<td><t:listDictParse parseId="${resultList.substituteSection}" style="1" typecode="JIECI"></t:listDictParse>
									</td>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${resultList.substituteTime }" /></td>
<%-- 									<td>${resultList.remarks}</td> --%>

					        <td><a href="scSubstituteManagerController.do?goUpdate&id=${resultList.id}" class="tablelink">修改</a>  <a href="javascript:void(0);" onclick="deleteDialog('提示','scSubstituteManagerController.do?doDel','${resultList.id}')" class="tablelink" > 删除</a></td>
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
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('scSubstituteManagerController.do?list&str_currentPage=<%=currentPage-1 %>')"  style="width:76px;text-decoration: none;"><span class="pagepre"> < 上一页</span></a></li>
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
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('scSubstituteManagerController.do?list&str_currentPage=<%=i+1 %>')"><%=i+1 %></a></li>
								
								<%
											}
											}
										}
										if(currentPage<totalPage){
								%>
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('scSubstituteManagerController.do?list&str_currentPage=<%=currentPage+1 %>')"  style="width:76px;text-decoration: none;"><span class="pagenxt">下一页 > </span></a></li>
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
		openuploadwin('Excel导入', 'scSubstituteManagerController.do?upload', "scSubstituteManagerList");
	}
	
	//导出
	function ExportXls() {
		location.href="scSubstituteManagerController.do?exportXls";
// 		JeecgExcelExport("scSubstituteManagerController.do?exportXls","scSubstituteManagerList");
	}
</script>
</body>
</html>
