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
<form name="searchForm" id="searchForm" method="post" action="scSubstituteManagerController.do?statisticsList">
<div id="container">
	<div id="hd"></div>
    <div id="bd">
    	<div id="main">
        	<div class="search-box ue-clear">
            	<div class="search-area">
                    <div class="kv-item">
		                        <label>&nbsp;&nbsp;&nbsp;&nbsp;开始时间:</label>
		                        <div class="kv-item-content">
							      	<input id="startTime" name="startTime" type="text" type="text" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${startTime }" />" class="Wdate"  onFocus="WdatePicker({isShowClear:false,readOnly:true})"/>
		                        </div>
		                        <label>&nbsp;&nbsp;&nbsp;&nbsp;结束时间:</label>
		                        <div class="kv-item-content">
							      	<input id="endTime" name="endTime" type="text" type="text" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${endTime }" />" class="Wdate"  onFocus="WdatePicker({isShowClear:false,readOnly:true})"/>
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

                        <a href="javascript:;" onclick="exportStatisticsXls()" class="add">
                            <i class="icon"></i>
                            <span class="text">导出Excel</span>
                        </a>
                    </span>
                </div>
                
                <div class="grid">
					  <table class="tablelist">
					    <thead>
					      <tr>

									<th>代课人</th>
									<th>代课总结数</th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${resultList}" var="resultList">
					      <tr>
							<td>${resultList[0]}</td>
					        <td>${resultList[1]}节次</td>
					      </tr>
					      </c:forEach>
					      
					    </tbody>
					  </table>
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
	//导出
	function exportStatisticsXls() {
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		location.href="scSubstituteManagerController.do?exportStatisticsXls&startTime="+startTime+"&endTime="+endTime;
// 		JeecgExcelExport("scSubstituteManagerController.do?exportXls","scSubstituteManagerList");
	}
</script>
</body>
</html>
