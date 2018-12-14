<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>库目录</title>

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
    <li class="layui-this">库目录管理</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
	<div id="container">
	<div id="hd"></div>
    <div id="bd">
    	<div id="main">
    	<!-- pId是当前点击的文件或文件夹ID -->
        <input name="pId" value="${pId}" type="hidden" id="pId"/>
    	<form name="searchForm" id="searchForm" method="post" action="docLibraryCommonController.do?list" class="layui-form layui-form-pane">
    	  <!-- <div class="layui-form-item">
		    <div class="layui-input-inline">
		      &nbsp;&nbsp;&nbsp;&nbsp;<button class="layui-btn layui-btn-normal" onclick="$('#searchForm').submit();">查&nbsp;&nbsp;询</button>
		    </div>
		  </div> -->
    	  </form>

    	<%-- <div class="btn-group">
        <button type="button" class="btn btn-primary" data-toggle="dropdown">新建在线文档</button>
        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"> <span class="caret"></span> <span class="sr-only">切换下拉菜单</span> </button>
        <ul class="dropdown-menu" role="menu">
          <li><a href="#" onClick="createOnline('officeWordController.do?createWord&pId=${pId}')">新建Word文档</a></li>
          <li><a href="#" onClick="createOnline('officeExcelController.do?createExcel&pId=${pId}')">新建Excel文档</a></li>
          <li><a href="#" onClick="createOnline('officePPTController.do?createPPT&pId=${pId}')">新建PPT文档</a></li>
        </ul>
      </div> --%>
      <%-- <div class="btn-group">


      <div class="btn-group">

        <button type="button" class="btn btn-primary" data-toggle="dropdown">新建格式文档</button>
        <button type="button" class="btn dropdown-toggle btn-primary" data-toggle="dropdown"> <span class="caret"></span> <span class="sr-only">切换下拉菜单</span> </button>
        <ul class="dropdown-menu" role="menu">
          <c:forEach items="${templateList }" var="templateList">
          <li><a href="#" onClick="javascript:location.href='templateController.do?createTemplateOnline&id=${templateList.id }'">${templateList.title }</a></li>
          <li><a href="#" onClick="createOnline('templateController.do?createTemplateOnline&pId=${pId}&id=${templateList.id }')">${templateList.title }</a></li>
          
          </c:forEach>
        </ul>
      </div>   --%>   
      <p>该文件夹下共有  &nbsp ${fileCount[0]} &nbsp 个文件</p> 
      <div class="btn-group">
		
        <button type="button" class="btn btn-primary" data-toggle="dropdown" onClick="createPackage('${pId}')">新建文件夹</button>
      </div>

<!--     	  <input class="layui-upload-file" id="fileElem" name="file" type="file" value="上传文件" lay-type="file"  multiple="multiple" lay-title="上传文件"/>  
 -->            <div class="table" style="z-index: 99999999999;">

            <div class="table" style="z-index: 99999999999;">

	          <div class="grid" style="z-index: 99999999999;">
	            <table class="layui-table" style="z-index: 99999999999;">
					    <thead>
					      <tr>
							<th style="width:30px"><input name="selectAll" id="selectAll" type="checkbox"  /></th>
			                  <th>文件夹名称</th>
			                  <!-- <th style="width:120px">文件大小</th>
			                  <th style="width:120px">上传时间</th> -->
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
			                    <td><c:choose>
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
			                      ${resultList.folderName}</td>
			                    <%-- <td>${resultList.fileSize}</td>
			                    <td><fmt:formatDate value="${resultList.uploadTime}" pattern="yyyy-MM-dd"/></td> --%>
			                    <td>
									<div class="btn-group">
									  <button data-toggle="dropdown" class="btn btn-success dropdown-toggle" type="button"> 文件夹操作 <span class="caret"></span> </button>
									  <ul role="menu" class="dropdown-menu">
									  	<li><a href="#" onClick="movePackage('${resultList.id}')">移动文件夹</a></li>
          								<li><a href="#" onClick="reNamePackage('${resultList.id}')">重命名文件夹</a></li>
          								<li><a href="#" onClick="permissionSet('${resultList.id}')">权限设置</a></li>
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
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('docLibraryCommonController.do?list&str_currentPage=<%=currentPage-1 %>')"  style="width:76px;text-decoration: none;"><span class="pagepre"> < 上一页</span></a></li>
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
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('docLibraryCommonController.do?list&str_currentPage=<%=i+1 %>')"><%=i+1 %></a></li>
								
								<%
											}
											}
										}
										if(currentPage<totalPage){
								%>
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('docLibraryCommonController.do?list&str_currentPage=<%=currentPage+1 %>')"  style="width:76px;text-decoration: none;"><span class="pagenxt">下一页 > </span></a></li>
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

    </div><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />

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
		openuploadwin('Excel导入', 'docLibraryCommonController.do?upload', "libCommonFileList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("docLibraryCommonController.do?exportXls","libCommonFileList");
	}
</script>
</body>
</html>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
<script>
//上传文件

layui.use('upload', function(){
	var pId=$("#pId").val();
	var loading;
	layui.upload({
		elem: '#fileElem',
	    url: 'docLibraryCommonController.do?uploadFile&pId='+pId //上传接口
	    ,before:function(input){
	    	loading = layer.load();
	    }
	    ,success: function(res){ //上传成功后的回调
	    	layer.close(loading);   
	    	layer.msg(res.msg, {
	    		  icon: 1,
	    		  time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
	    		}, function(){
	    			location.reload();
	    		});   
	    }
	  });
	layui.upload({
		elem: '#fileZIPElem',
	    url: 'docLibraryCommonController.do?uploadFileZip&pId='+pId //上传接口
	    ,before:function(input){
	    	
	    	loading = layer.load();
	    }
	    ,success: function(res){ //上传成功后的回调
	    	layer.close(loading);   
	    	layer.msg(res.msg, {
	    		  icon: 1,
	    		  time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
	    		}, function(){
	    			location.reload();
	    		});   
	    }
	  });
});

//新建文件夹
function createPackage(pId){
	if(pId!=null&&pId!=""){
		layer.prompt({
			  formType: 0,
			  title: '新建文件夹',
			  area: ['800px', '350px'] //自定义文本域宽高
				},
			  function(value, index, elem){
					$.post(
							"docLibraryCommonController.do?createPackage",
							{pId:pId,pName:value},
							function(res){
								var resO=$.parseJSON(res);
								layer.close(index);
								layer.msg(resO.msg, {
						    		  icon: 1,
						    		  time: 1000 //1秒关闭（如果不配置，默认是3秒） 单位是毫秒
						    	  }, function(){
						    		  //刷新ztree的树节点
						    		  window.parent.location.reload();
						    	}); 
							}
						);
				  layer.close(index);
			  });
	}else{
		layer.msg('请选择一个目录',{icon:2,anim:6});
	}
	
}
	
	


//重命名
function reName(id,name){
	//alert("测试JS");
	layer.prompt({
		  formType:0,
		  value:name,
		  title:"请输入文件名"},
		  function(value, index, elem){
			  $.post("docLibraryCommonController.do?reName",
					  {fileName:value,id:id},
					  function(res){
						  var resObj = $.parseJSON(res);
						  layer.close(index);
						  layer.msg(resObj.msg, {
				    		  icon: 1,
				    		  time: 1000 //2秒关闭（如果不配置，默认是3秒）
				    	  }, function(){
				    			location.reload();
				    	}); 
					  });
		});
}


//移动 
function fileTree(id){
	layer.open({
		content:'docLibraryCommonController.do?fileTree',
		type:2,
		resize:true,
		area:['400px','500px'],
		btn:['确定'],
		yes:function(index,layero){
			var body = layer.getChildFrame('body', index);
			var toMovePackageId=body.find('#toMovePackage').val();
			$.post(
				"docLibraryCommonController.do?dofileTree",
				{newPId:toMovePackageId,fileId:id},
				function(res){
					var resO=$.parseJSON(res);
					layer.close(index);
					layer.msg(resO.msg, {
			    		  icon: 1,
			    		  time: 1000 //1秒关闭（如果不配置，默认是3秒） 单位是毫秒
			    	  }, function(){
			    		location.reload();
			    	}); 
				}
			);
		},
		closeBtn:1
	});
}



//重命名文件夹

function reNamePackage(pId){
	 $.post(
		"docLibraryCommonController.do?queryPackageName",
		{packageId:pId},
		function(res){
			var res =$.parseJSON(res);
			if(res.success){
				//重命名的弹出窗
				layer.prompt({
					  formType:0,
					  value:res.attributes.packageName,
					  title:"请输入文件夹名"},
					  function(value, index, elem){
						  $.post("docLibraryCommonController.do?rePackageName",
								  {newPackageName:value,id:pId},
								  function(res){
									  var res=$.parseJSON(res);
									  layer.close(index);
									  layer.msg(res.msg, {
							    		  icon: 1,
							    		  time: 1000 //2秒关闭（如果不配置，默认是3秒）
							    	  }, function(){
							    			location.reload();
							    	}); 
								  });
					});
			}else{
				layer.msg('请选择文件夹',{anim:6,icon:5});
			}
		}
	); 
}


//分享   调用PDFJS

//	function sharePDF(pdfURL){
//		/* var point = pdfURL.lastIndexOf(".");  //最后一个小数点的位置
//		var type = pdfURL.substr(0,point); */
//		window.open('${pageContext.request.contextPath}/plug-in/pdfjs/web/viewer.html?file=${pageContext.request.contextPath}/'+pdfURL,'PDF','width:50%;height:50%;top:100;left:100;');
//	}


//移动文件夹

function movePackage(packageId){
	layer.open({
		content:'docLibraryCommonController.do?fileTree',//这个请求是为了查询出  菜单树
		type:2,
		resize:true,
		area:['400px','500px'],
		btn:['确定'],
		yes:function(index,layero){
			var body = layer.getChildFrame('body', index);
			var toMovePackageId=body.find('#toMovePackage').val();
			$.post(
					"docLibraryCommonController.do?doMovePackage",
					{newPackageId:toMovePackageId,packageId:packageId},
					function(res){
						var resO=$.parseJSON(res);
						layer.close(index);
						layer.msg(resO.msg, {
				    		  icon: 1,
				    		  time: 1000 //1秒关闭（如果不配置，默认是3秒） 单位是毫秒
				    	  }, function(){
				    		location.reload();
				    	}); 
					}
				);
		},
		closeBtn:1
	});
	
}




	//权限设置
  function permissionSet(id){
	  layer.open({
		  title:'权限设置'
		  ,type:2
		  ,content:'${pageContext.request.contextPath}/docLibraryCommonController.do?goPermissionSetPage&id='+id
		  ,area:['800px','500px']
		  
	  })
  }
</script>
