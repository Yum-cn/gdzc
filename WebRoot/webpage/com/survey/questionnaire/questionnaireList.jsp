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
<script src = "webpage/com/train/questionnaire/questionnaireList.js"></script>	
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
    <li class="layui-this">问卷管理</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
      <div id="container">
      <div id="hd"></div>
      <div id="bd">
      <div id="main">
      <form name="searchForm" id="searchForm" method="post" action="questionnaireController.do?questionnaire" class="layui-form ">
        <div class="layui-form-item">
          <label class="layui-form-label" style="width:120px;">试卷名称:</label>
          <div class="layui-input-inline">
            <input id="name" name="name" placeholder="请输入试卷名称" type="text" value="${name}" class="layui-input"/>
          </div>
          <div class="layui-input-inline"> &nbsp;&nbsp;&nbsp;&nbsp;
            <button class="layui-btn layui-btn-normal" onClick="$('#searchForm').submit();">查&nbsp;&nbsp;询</button>
          </div>
        </div>
      </form>
      <button class="layui-btn" onclick="add('questionnaireController.do?goAdd')"><i class="layui-icon"></i> 添加</button>
<!--       <button class="btn btn-primary" onClick="deleteNewStyleALLSelect('提示','docMyFileController.do?doBatchDel','')"><i class="layui-icon">&#xe640;</i> 批量删除</button>  --> 
        <div class="table">
          <div class="grid">
            <table class="layui-table">
              <thead>
                <tr>
                  <th><input name="selectAll" id="selectAll" type="checkbox"  /></th>
                  <th>问卷名称</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${resultList}" var="resultList">
                	<tr>
                		<td><input name="id" id="id" type="checkbox" value="${resultList.id}" />
                		<td>${resultList.name}</td>
                		<td>
                			<a href="javascript:viewQRCode('${resultList.id}')">查看二维码</a>  
                			<a href="javascript:previewPaper('${resultList.id}')">预览试卷</a>  
                			<a href="javascript:analysis('${resultList.id}')">统计分析</a>  
                			<a href="javascript:exportXls('${resultList.id}')">导出数据</a>  
                			<a href="javascript:goDel('${resultList.id}')">删除</a>  
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
  </div>
</div>
	
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#questionnaireListtb").find("input[name='createTime']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
function analysis(id){
	location.href='questionnaireController.do?analysis&id='+id;
}
function exportXls(id) {
	JeecgExcelExport("questionnaireController.do?exportXls&questionnaireId="+id,"questionnaireList");
}
 </script>

</body>
</html>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
<script>
	//layer 的使用 必须要有这个
	layui.use('layer', function(){
	  var layer = layui.layer;
	});              

	function add(url){
		window.location.href=url;
	}
	//删除
	function goDel(id){
		layer.confirm('确定删除此试卷?', {icon: 3, title:'删除提示'}, function(index){
			$.post(
				"questionnaireController.do?doDel",
				{id:id},
				function(res){
					var reOBJ = $.parseJSON(res);
					layer.msg(reOBJ.msg, {
						  icon: 1,
						  time: 1500 //2秒关闭（如果不配置，默认是3秒）
						}, function(){
							layer.close(index);
							location.reload();
						});   
				}
			  )
			}); 
	}
	
	//查看二维码
	 function viewQRCode(id) {
	    layer.open({
	    	type:2,
	        content: 'questionnaireController.do?generateCode&id=' + id,
	        area:['300px','300px'],
	        title: "查看二维码"
	    })
	}
	
	//预览试卷
	
	 function previewPaper(id) {
	    //var addurl = "questionnaireController.do?generatePaper&id=" + id;
	    layer.open({
	    	type:2,
	        content: 'questionnaireController.do?generatePaper&id=' + id,
	        lock: true,
	        area:['600px','380px'],
	        title: "预览问卷",
	        closeBTN:1,
	    })
	}
</script>
