<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>研究反思</title>
  <t:base type="ckeditor,jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
       function uploadTemplateCallBack(id,url,name){
	  	var point = url.lastIndexOf(".");
	  	var type = url.substr(point);
		$("#template_href").attr('href',url);
		$("#template_href").html(name);
		$("#attFile").val(url);
		$("#attFileName").val(name);
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="researchReflectController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${researchReflectPage.id }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right" width="120px">
							<label class="Validform_label">
								标题:
							</label>
						</td>
						<td class="value">
						     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${researchReflectPage.title}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								附件:
							</label>
						</td>
						<td class="value">
							<input id="attFile" name="attFile" type="hidden" style="width: 150px" class="inputxt" value="${researchReflectPage.attFile}">
							<input id="attFileName" name="attFileName" type="hidden" style="width: 150px" class="inputxt" value="${researchReflectPage.attFileName}">
											<c:if test="${researchReflectPage.attFile==''}">
												<a   target="_blank" id="template_href">暂时未上传文件</a>
											</c:if>
											<c:if test="${researchReflectPage.attFile!=''}">
												<a href="${researchReflectPage.attFile}" target="_blank" id="template_href">${researchReflectPage.attFileName}</a>
												<a href="javascript:void(0);" onclick="viewPdf('${researchReflectPage.attFile}')">在线阅读</a>
											</c:if>							
								<input class="ui-button" type="button" value="上传附件" onclick="uploadDoc(uploadTemplateCallBack);"/>   
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">附件</label>
							</td>
					</tr>					
					<tr>
						<td align="right">
							<label class="Validform_label">
								反思内容:
							</label>
						</td>
						<td class="value">
						<t:ckeditor name="content" isfinder="true" type="width:'100%',height:300" value="${researchReflectPage.content}" ></t:ckeditor>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">反思内容</label>
						</td>
					</tr>

			</table>
			<input id="topId" name="topId" type="hidden" style="width: 150px" class="inputxt" value="${researchReflectPage.topId }">
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/process/researchReflect.js"></script>		