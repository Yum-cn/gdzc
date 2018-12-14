<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统公告</title>

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
<script src="${pageContext.request.contextPath}/plug-in/zeroclipboard-master/dist/ZeroClipboard.min.js"></script>
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
    <li class="layui-this">通知公告</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
<div id="container">
	<div id="hd"></div>
    <div id="bd">
    	<div id="main">
    	<form name="searchForm" id="searchForm" method="post" action="docNoticeController.do?list" class="layui-form layui-form-pane">
    	  <div class="layui-form-item">
	    	  <label class="layui-form-label" style="width:120px;">标题:</label>
	          <div class="layui-input-inline">
	            <input id="title" name="title" placeholder="请输入标题" type="text" value="${title}" class="layui-input"/>
	          </div>
	    	  <label class="layui-form-label" style="width:120px;">发布人:</label>
	          <div class="layui-input-inline">
	            <input id="sender" name="sender" placeholder="请输入发布人" type="text" value="${sender}" class="layui-input"/>
	          </div>
		    <div class="layui-input-inline">
		      &nbsp;&nbsp;&nbsp;&nbsp;<button class="layui-btn layui-btn-normal" onclick="$('#searchForm').submit();">查&nbsp;&nbsp;询</button>
		    </div>
		  </div>
    	  </form>
          <button class="layui-btn" onclick="add('docNoticeController.do?goAdd')"><i class="layui-icon"></i> 添加</button>
          <button class="layui-btn" onclick="deleteNewStyleALLSelect('提示','docNoticeController.do?doBatchDel','')"><i class="layui-icon">&#xe640;</i> 批量删除</button>
            <div class="table" style="z-index: 99999999999;">
                <div class="grid" style="z-index: 99999999999;">
					  <table class="layui-table" style="z-index: 99999999999;">
					    <thead>
					      <tr>
									<th><input name="selectAll" id="selectAll" type="checkbox"  /></th>			
									<th>标题</th>
									<th>发布人</th>
									<th>过期状态</th>
									<th>阅读范围</th>
									<!-- <th>文档附件名字</th>
									<th>文档路径</th>
									<th>公告正文</th> -->
					        <th>操作</th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${resultList}" var="resultList">
					      <tr>
									<td><input name="id" id="id" type="checkbox" value="${resultList.id}" />	</td>			
									<td>${resultList.noticeTitle}</td>

									<td>${resultList.noticeSender}</td>
									<td>
										<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
										<c:choose>
											<c:when test="${nowDate-resultList.deadline.getTime()>0}">
												已过期
											</c:when>
											<c:otherwise>
												未过期
											</c:otherwise>
										</c:choose>
									</td>
									<td>${resultList.noticeAudience}</td>

									<%-- <td>${resultList.fileName}</td>

									<td>${resultList.filePath}</td>

									<td>${resultList.noticeContent}</td> --%>
									<td>
										<div class="btn-group">
										  <button data-toggle="dropdown" class="btn btn-success dropdown-toggle" type="button"> 文件夹操作 <span class="caret"></span> </button>
										  <ul role="menu" class="dropdown-menu">
										  	<li><a href="#" onClick="readNotice('${resultList.id}')">阅读</a></li>
	          								<li><a href="#" onClick="readCount('${resultList.id}')">阅读记录</a></li>
	          								<li><a href="docNoticeController.do?goUpdate&id=${resultList.id}" class="tablelink">修改</a></li>
	          								<li><a href="#" onclick="doDel('${resultList.id}')"> 删除</a></li>
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
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('docNoticeController.do?list&str_currentPage=<%=currentPage-1 %>')"  style="width:76px;text-decoration: none;"><span class="pagepre"> < 上一页</span></a></li>
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
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('docNoticeController.do?list&str_currentPage=<%=i+1 %>')"><%=i+1 %></a></li>
								
								<%
											}
											}
										}
										if(currentPage<totalPage){
								%>
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('docNoticeController.do?list&str_currentPage=<%=currentPage+1 %>')"  style="width:76px;text-decoration: none;"><span class="pagenxt">下一页 > </span></a></li>
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
		openuploadwin('Excel导入', 'docNoticeController.do?upload', "docNoticeList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("docNoticeController.do?exportXls","docNoticeList");
	}
</script>
</body>
</html>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
<script>

//加载弹出层
layui.use('layer', function(){
	  var layer = layui.layer;
}); 

    //阅读
	function readNotice(noticeId){
		layer.open({
			type:2
			,title:'阅读公告'
			,content: 'docNoticeController.do?goReadNotice&noticeId='+noticeId
			,area:['650px','500px']
			,btn:'关闭'
			,cancel:function(index){
			 	//关闭弹出层
			}
		});
	} 
	
    
    //阅读记录
    function readCount(noticeId){
    	layer.open({
    		type:2
    		,title:'阅读记录'
			,content: 'docNoticeCountController.do?goReadNoticeCount&noticeId='+noticeId
			,area:['600px','500px']
			,btn:'关闭'
			,cancel:function(index){
			 	//关闭弹出层
			}
    	})
    }
    
    //删除通知公告
    function doDel(id){
    	layer.confirm('是否删除此通知?', {icon: 3, title:'删除通知'}, function(index){
    		  //do something
    		  $.post(
    			"docNoticeController.do?doDel",
    			{noticeId:id},
    			function(res){
    				var resOBJ = $.parseJSON(res);
    				layer.msg(resOBJ.msg,{icon: 1,time:1500},function(){
    					location.reload();//刷新页面
    				});//提示信息
    				
    			}
    		  );
    		  
    		});
    }
    //修改通知公告
    function doUpdate(id){
    	
    }
</script>
