<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<t:base type="jquery,tools,DatePicker"></t:base>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/Validform_v5.3.2/js/Validform_v5.3.2_min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>
</head>
<body>

  <div class="layui-tab-content">
    
<table class="layui-table" style="width: 580px">
					    <thead>
					      <tr>
									<th>阅读人</th>
									<th width="200px">阅读时间</th>
									<th>阅读次数</th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${readCount}" var="readCount">
					      <tr>
									<td>${readCount[0]}</td>			
									<td><fmt:formatDate value="${readCount[1]}" pattern="yyyy-MM-dd hh:mm:ss"/></td>

									<td>${readCount[2]}</td>

					      </tr>
					      </c:forEach>
					      
					    </tbody>
					  </table>



	
</div> 
</body>

</html>
<script type="text/javascript">
$(function(){
	$("#form1").Validform({
		tiptype:3
	});
	$("#saveForm").click(function(){
    	$("#form1").submit();
  	});
})
</script>

