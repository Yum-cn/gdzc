<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<t:base type="jquery,tools,DatePicker"></t:base>
<!--<link href="${r'${pageContext.request.contextPath}'}/plug-in/frame/default/css/style.css" rel="stylesheet" type="text/css" />-->
<link href="${r'${pageContext.request.contextPath}'}/plug-in/frame/default/mainFrame/css/style.css" rel="stylesheet" type="text/css" />
<link href="${r'${pageContext.request.contextPath}'}/plug-in/frame/default/mainFrame/css/skin_/table.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${r'${pageContext.request.contextPath}'}/plug-in/frame/default/mainFrame/css/jquery.grid.css" />
<script type="text/javascript" src="${r'${pageContext.request.contextPath}'}/plug-in/frame/default/mainFrame/js/jquery.select.js"></script>
<link rel="stylesheet" href="${r'${pageContext.request.contextPath}'}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>
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
    	<form name="searchForm" id="searchForm" method="post" action="${entityName?uncap_first}Controller.do?list" class="layui-form layui-form-pane">
    	  <div class="layui-form-item">
    	    <#list columns as po>
               <#if po.isQuery =='Y'>
		    <label class="layui-form-label">${po.content}:</label>
		    <div class="layui-input-inline">
		      	<#if po.showType=='text'>
				<input id="${po.fieldName}" name="${po.fieldName}" type="text" value="${r'${'}${po.fieldName}${r'}'}" placeholder="请输入${po.content}" autocomplete="off" class="layui-input"/>
				<#elseif po.showType=='textarea'>
				<input id="${po.fieldName}" name="${po.fieldName}" type="text" value="${r'${'}${po.fieldName}${r'}'}" placeholder="请输入${po.content}" autocomplete="off" class="layui-input"/>
				<#elseif po.showType=='date'>
				<input id="${po.fieldName}" name="${po.fieldName}" type="text" class="Wdate" onClick="WdatePicker()" value="<fmt:formatDate value="${r'${'}${po.fieldName}${r'}'}" pattern="yyyy-MM-dd"/>" placeholder="请选择日期" autocomplete="off" class="layui-input"/>
				<#elseif po.showType=='datetime'>
				<input id="${po.fieldName}" name="${po.fieldName}" type="text" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value="${r'${'}${po.fieldName}${r'}'}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="请选择日期" autocomplete="off" class="layui-input"/>
				</#if>
		    </div>
	           </#if>
            </#list>		    
		    <div class="layui-input-inline">
		      &nbsp;&nbsp;&nbsp;&nbsp;<button class="layui-btn layui-btn-normal" onclick="$('#searchForm').submit();">查&nbsp;&nbsp;询</button>
		    </div>
		  </div>
    	  </form>
          <button class="layui-btn" onclick="add('${entityName?uncap_first}Controller.do?goAdd')"><i class="layui-icon"></i> 添加</button>
          <button class="layui-btn" onclick="deleteNewStyleALLSelect('提示','${entityName?uncap_first}Controller.do?doBatchDel','')"><i class="layui-icon">&#xe640;</i> 批量删除</button>
          <form name="searchForm" id="searchForm" method="post" action="${entityName?uncap_first}Controller.do?list" >
            <div class="table">


                <div class="grid">
					  <table class="layui-table">
					    <thead>
					      <tr>
					        <#list columns as po>
								<#if po.fieldName == 'id'>
									<th><input name="selectAll" id="selectAll" type="checkbox"  /></th>			
								<#elseif po.isShowList?if_exists?html !='N'>
									<th>${po.content}</th>
								</#if>
					        </#list>
					        <th>操作</th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${r'${resultList}'}" var="resultList">
					      <tr>
					        <#list columns as po>
					        	<#if po.showType?index_of("datetime")!=-1 && po.isShowList?if_exists?html !='N'>
									<td><fmt:formatDate value="${r'${resultList.'}${po.fieldName}${r'}'}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<#elseif po.showType?index_of("date")!=-1 && po.isShowList?if_exists?html !='N'>
									<td><fmt:formatDate value="${r'${resultList.'}${po.fieldName}${r'}'}" pattern="yyyy-MM-dd"/></td>
								<#elseif po.fieldName == 'id'>
									<td><input name="${po.fieldName}" id="${po.fieldName}" type="checkbox" value="${r'${resultList.'}${po.fieldName}${r'}'}" />	</td>			
								<#elseif po.dictTable?if_exists?html!="">
									<td><t:listDictParse table="${po.dictTable}" id="${po.dictField}" name="${po.dictText}" parseId="${r'${resultList.'}${po.fieldName}${r'}'}"></t:listDictParse></td>	
								<#elseif po.dictTable?if_exists?html=="" && po.dictField?if_exists?html!="">
									<td><t:listDictParse parseId="${r'${resultList.'}${po.fieldName}${r'}'}" style="1" typecode="${po.dictField}"></t:listDictParse></td>	
								<#elseif po.isShowList?if_exists?html !='N'>
									<td>${r'${resultList.'}${po.fieldName}${r'}'}</td>

								</#if>
							</#list>
					        <td><a href="${entityName?uncap_first}Controller.do?goUpdate&id=${r'${resultList.id}'}" class="tablelink">修改</a>  <a href="javascript:void(0);" onclick="deleteDialog('提示','${entityName?uncap_first}Controller.do?doDel','${r'${resultList.id}'}')" class="tablelink" > 删除</a></td>
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
				
    				<div class="message">共<i class="blue">&nbsp;&nbsp;${r'${totalRow }'}&nbsp;&nbsp;</i>条记录，当前显示第&nbsp;<i class="blue">&nbsp;&nbsp;${r'${currentPage }'}&nbsp;&nbsp;</i>页</div>
				    <ul class="paginList">
				    			<%
										if(currentPage>1){
								%>
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('${entityName?uncap_first}Controller.do?list&str_currentPage=<%=currentPage-1 %>')"  style="width:76px;text-decoration: none;"><span class="pagepre"> < 上一页</span></a></li>
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
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('${entityName?uncap_first}Controller.do?list&str_currentPage=<%=i+1 %>')"><%=i+1 %></a></li>
								
								<%
											}
											}
										}
										if(currentPage<totalPage){
								%>
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('${entityName?uncap_first}Controller.do?list&str_currentPage=<%=currentPage+1 %>')"  style="width:76px;text-decoration: none;"><span class="pagenxt">下一页 > </span></a></li>
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
		openuploadwin('Excel导入', '${entityName?uncap_first}Controller.do?upload', "${entityName?uncap_first}List");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("${entityName?uncap_first}Controller.do?exportXls","${entityName?uncap_first}List");
	}
</script>
</body>
</html>
<script src="${r'${pageContext.request.contextPath}'}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
<script>
layui.use(['form'], function(){
  var form = layui.form()
});
</script>
