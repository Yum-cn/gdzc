<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>阶段性总结</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="stageSummaryController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${stageSummaryPage.id }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right" width="120px">
							<label class="Validform_label">
								总结标题:
							</label>
						</td>
						<td class="value">${stageSummaryPage.title}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总结标题</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								附件:
							</label>
						</td>
						<td class="value">
							<input id="attFile" name="attFile" type="hidden" style="width: 150px" class="inputxt" value="${stageSummaryPage.attFile}">
							<input id="attFileName" name="attFileName" type="hidden" style="width: 150px" class="inputxt" value="${stageSummaryPage.attFileName}">
											<c:if test="${stageSummaryPage.attFile==''}">
												<a   target="_blank" id="template_href">暂时未上传文件</a>
											</c:if>
											<c:if test="${stageSummaryPage.attFile!=''}">
												<a href="${stageSummaryPage.attFile}" target="_blank" id="template_href">${stageSummaryPage.attFileName}</a>
												<a href="javascript:void(0);" onclick="viewPdf('${stageSummaryPage.attFile}')">在线阅读</a>
											</c:if>							
<!-- 								<input class="ui-button" type="button" value="上传附件" onclick="uploadDoc(uploadTemplateCallBack);"/>    -->
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">附件</label>
							</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								总结内容:
							</label>
						</td>
						<td class="value">${stageSummaryPage.content}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总结内容</label>
						</td>
					</tr>

					<input id="topId" name="topId" type="hidden" style="width: 150px" class="inputxt" value="${stageSummaryPage.topId }">
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/process/stageSummary.js"></script>		