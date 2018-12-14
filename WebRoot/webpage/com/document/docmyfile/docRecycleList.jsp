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
    <li class="layui-this">回收站</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
      <div id="container">
      <div id="hd"></div>
      <div id="bd">
      <div id="main">
      <form name="searchForm" id="searchForm" method="post" action="docMyFileController.do?recycle" class="layui-form ">
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
<!--       <button class="btn btn-primary" onClick="deleteNewStyleALLSelect('提示','docMyFileController.do?doBatchDel','')"><i class="layui-icon">&#xe640;</i> 批量删除</button>  --> 
        <div class="table">
          <div class="grid">
            <table class="layui-table">
              <thead>
                <tr>
                  <th><input name="selectAll" id="selectAll" type="checkbox"  /></th>
                  <th>文件名称/文件夹名称</th>
                  <th>文件大小</th>
                  <th>上传时间</th>
                  <th>操作</th>
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
                    <c:when test="${resultList.fileName!=''&&resultList.fileName!=null}">
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
	                      ${resultList.fileName}.${resultList.fileType}
                      </c:when>
                      <c:when test="${resultList.folderName!=''&&resultList.folderName!=null}">
                      	${resultList.folderName}&nbsp(文件夹)
                      </c:when>
                      </c:choose>
                      </td>
                    <td>
                    	<c:choose>
                    		<c:when test="${resultList.fileSize!=null}">
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
                    		</c:when>
                    		<c:otherwise>
                    			-
                    		</c:otherwise>
                    	</c:choose>
                    </td>
                    <td><fmt:formatDate value="${resultList.uploadTime}" pattern="yyyy-MM-dd"/></td>
                    <td>
                      <a href="javascript:restoreFile('${resultList.id}')">还原</a>  
                      <a href="javascript:del('${resultList.id}')" >彻底删除</a> </td>
                  </tr>
                </c:forEach>
                <c:forEach items="${libFileList}" var="libFileList">
                  <tr>
                    <input id="fileType" id="fileType" value="${libFileList.fileType}" type="hidden">
                    </input>
                    <td><input name="id" id="id" type="checkbox" value="${libFileList.id}" />
                    </td>
                    <td>
                    <c:choose>
                    <c:when test="${libFileList.fileName!=''&&libFileList.fileName!=null}">
	                    <c:choose>
	                        <c:when test="${libFileList.fileType=='pdf'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/pdf.ico"  style="height:18px;"/> </c:when>
	                        <c:when test="${libFileList.fileType=='doc'}"> <img src="${pageContext.request.contextPath}/images/file_icon/office/Word.ico" style="height:18px;"/> </c:when>
	                        <c:when test="${libFileList.fileType=='docx'}"> <img src="${pageContext.request.contextPath}/images/file_icon/office/Word.ico" style="height:18px;" /> </c:when>
	                        <c:when test="${libFileList.fileType=='png'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/png.ico"  style="height:18px;"/> </c:when>
	                        <c:when test="${libFileList.fileType=='txt'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/text.ico"  style="height:18px;"/> </c:when>
	                        <c:when test="${libFileList.fileType=='avi'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/avi.ico"  style="height:18px;"/> </c:when>
	                        <c:when test="${libFileList.fileType=='fla'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/fla.ico"  style="height:18px;"/> </c:when>
	                        <c:when test="${libFileList.fileType=='mp3'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/mp3.ico"  style="height:18px;"/> </c:when>
	                        <c:when test="${libFileList.fileType=='gif'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/gif.ico"  style="height:18px;"/> </c:when>
	                        <c:when test="${libFileList.fileType=='jpeg'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/jpeg.ico"  style="height:18px;"/> </c:when>
	                        <c:when test="${libFileList.fileType=='jpg'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/jpeg.ico"  style="height:18px;"/> </c:when>
	                        <c:when test="${libFileList.fileType=='mov'}"> <img src="${pageContext.request.contextPath}/images/file_icon/small_icons/mov.ico"  style="height:18px;"/> </c:when>
	                        <c:when test="${libFileList.fileType=='xls'}"> <img src="${pageContext.request.contextPath}/images/file_icon/office/Excel.ico"  style="height:18px;"/> </c:when>
	                        <c:when test="${libFileList.fileType=='xlsx'}"> <img src="${pageContext.request.contextPath}/images/file_icon/office/Excel.ico"  style="height:18px;"/> </c:when>
	                        <c:when test="${libFileList.fileType=='ppt'}"> <img src="${pageContext.request.contextPath}/images/file_icon/office/PowerPoint.ico"  style="height:18px;"/> </c:when>
	                        <c:when test="${libFileList.fileType=='pptx'}"> <img src="${pageContext.request.contextPath}/images/file_icon/office/PowerPoint.ico"  style="height:18px;"/> </c:when>
	                      </c:choose>
	                      ${libFileList.fileName}.${libFileList.fileType}
                      </c:when>
                      <%-- <c:when test="${libFileList.folderName!=''&&libFileList.folderName!=null}">
                      	${libFileList.folderName}&nbsp(文件夹)
                      </c:when> --%>
                      </c:choose>
                      </td>
                      
                    <td>
                    	<c:choose>
                    		<c:when test="${libFileList.fileSize!=null}">
                    			<c:choose>
		                    		<c:when test="${libFileList.fileSize>1000000}">
		                    			<fmt:formatNumber value="${libFileList.fileSize/(1024*1024)}" type="number" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber>
		                    			MB
		                    		</c:when>
		                    		<c:when test="${libFileList.fileSize<1000000}">
		                    			<fmt:formatNumber value="${libFileList.fileSize/(1024)}" type="number" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber>
		                    			KB
		                    		</c:when>
		                    	</c:choose>
                    		</c:when>
                    		<c:otherwise>
                    			-
                    		</c:otherwise>
                    	</c:choose>
                    </td>
                    <td><fmt:formatDate value="${libFileList.uploadDate}" pattern="yyyy-MM-dd"/></td>
                    <td>
                      <a href="javascript:restoreLibFile('${libFileList.id}')">还原</a>  
                      <a href="javascript:libFileDel('${libFileList.id}')" >彻底删除</a> </td>
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

</body>
</html>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
<script>
	//layer 的使用 必须要有这个
	layui.use('layer', function(){
	  var layer = layui.layer;
	});              
	//我的文件还原
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
		   ,cancel: function(index){ 
		     //右上角关闭回调
			 layer.close(index);
		   }
		 });
   }
	//库目录文件还原
   function restoreLibFile(rfId){
	   layer.open({
		   title:'确认还原',
		   content: '是否还原此文件'
		   ,btn: ['是', '否']
		   ,yes: function(index, layero){
		      //按钮【按钮一】的回调
			   $.post(
				"docLibFileController.do?restoreFile",
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
		   ,cancel: function(index){ 
		     //右上角关闭回调
			 layer.close(index);
		   }
		 });
   }
	
	//我的文件彻底删除
	function del(id){
		layer.open({
			   title:'确认还原',
			   content: '是否彻底删除此文件'
			   ,btn: ['是', '否']
			   ,yes: function(index, layero){
			      //按钮【按钮一】的回调
				   $.post(
					"docMyFileController.do?removeFile",
					{fileId:id},
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
				   layer.close(index);
			   }
			   ,cancel: function(index){ 
			     //右上角关闭回调
			     layer.close(index);
			   }
			 });
		
	}
	//库目录文件彻底删除
	function libFileDel(id){
		layer.open({
			   title:'确认还原',
			   content: '是否彻底删除此文件'
			   ,btn: ['是', '否']
			   ,yes: function(index, layero){
			      //按钮【按钮一】的回调
				   $.post(
					"docLibFileController.do?removeFile",
					{fileId:id},
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
				   layer.close(index);
			   }
			   ,cancel: function(index){ 
			     //右上角关闭回调
			     layer.close(index);
			   }
			 });
		
	}


</script>
