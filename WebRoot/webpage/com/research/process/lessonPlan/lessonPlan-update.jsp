<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>教案管理</title>
  <t:base type="ckeditor,jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
    function uploadTemplateCallBack(id,url,name){
	  	var point = url.lastIndexOf(".");
	  	var type = url.substr(point);
	  	if(type==".doc" || type==".docx"){
			$("#template_href").attr('href',url);
			$("#template_href").html(name);
			$("#fileDocPath").val(url);
			$("#fileDocName").val(name);
	  	}else{
	  		alert("模板文件格式不正确");
	  	}
  }
    function viewPdf(fileUrl){
       	var point = fileUrl.lastIndexOf("."); 
           var type = fileUrl.substr(0,point); 
       	type= type+".pdf";
       	window.open('${pageContext.request.contextPath}/plug-in/pdfjs/web/viewer.html?file=${pageContext.request.contextPath}/'+type,'PDF','width:50%;height:50%;top:100;left:100;');
       }
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lessonPlanController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${lessonPlanPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right" width="120px">
							<label class="Validform_label">
								教案名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${lessonPlanPage.title}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">教案名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								作者:
							</label>
						</td>
						<td class="value">
						     	 <input id="author" name="author" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${lessonPlanPage.author}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">作者</label>
						</td>
					</tr>

					<tr>
						<td align="right">
							<label class="Validform_label">
								教案文件:
							</label>
						</td>
						<td class="value">
							<input id="fileDocPath" name="fileDocPath" type="hidden" style="width: 150px" class="inputxt" value="${lessonPlanPage.fileDocPath}">
							<input id="fileDocName" name="fileDocName" type="hidden" style="width: 150px" class="inputxt" value="${lessonPlanPage.fileDocName}">
											<c:if test="${lessonPlanPage.fileDocPath==''}">
												<a   target="_blank" id="template_href">暂时未上传文件</a>
											</c:if>
											<c:if test="${lessonPlanPage.fileDocPath!=''}">
												<a href="${lessonPlanPage.fileDocPath}" target="_blank" id="template_href">${lessonPlanPage.fileDocName}</a>
												<a href="javascript:void(0);" onclick="viewPdf('${lessonPlanPage.fileDocPath}')">在线阅读</a>
											</c:if>							
								<input class="ui-button" type="button" value="上传申报文件" onclick="uploadDoc(uploadTemplateCallBack);"/>   
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">教案文件</label>
							</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								教案描述:
							</label>
						</td>
						<td class="value">
						<t:ckeditor name="comment" isfinder="true" type="width:'100%',height:300" value="${lessonPlanPage.comment}" ></t:ckeditor>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">教案描述</label>
						</td>
					</tr>
					
			</table>
			<input id="topId" name="topId" type="hidden" style="width: 150px" class="inputxt" value="${lessonPlanPage.topId }">
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/process/lessonPlan.js"></script>		