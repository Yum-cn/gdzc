<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>工作计划</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="processPlanController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${processPlanPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
<!-- 					<tr> -->
<!-- 						<td align="right"> -->
<!-- 							<label class="Validform_label"> -->
<!-- 								开始时间: -->
<!-- 							</label> -->
<!-- 						</td> -->
<!-- 						<td class="value"> -->
<!-- 						     	 <input id="startDate" name="startDate" type="text" style="width: 150px" class="inputxt"   -->
<%-- 									               class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value="${processPlanPage.startDate}" pattern="yyyy-MM-dd"/>'> --%>

<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">开始时间</label> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td align="right"> -->
<!-- 							<label class="Validform_label"> -->
<!-- 								结束时间: -->
<!-- 							</label> -->
<!-- 						</td> -->
<!-- 						<td class="value"> -->
<!-- 						     	 <input id="endDate" name="endDate" type="text" style="width: 150px" class="inputxt"   -->
<%-- 									               class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value="${processPlanPage.endDate}" pattern="yyyy-MM-dd"/>'>  --%>
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">结束时间</label> -->
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<td align="right" style="width:120px">
							<label class="Validform_label">
								研究的主题:
							</label>
						</td>
						<td class="value">
						     	 <input id="theme" name="theme" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${processPlanPage.theme}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">研究的主题</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								研究阶段:
							</label>
						</td>
						<td class="value">
						 <input id="yjjd" name="yjjd" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${processPlanPage.yjjd}'>
<%-- 						     	 <t:dictSelect field="yjjd" typeGroupCode="GZJHJD" hasLabel="false" defaultVal="${processPlanPage.yjjd}"/> --%>
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">研究阶段</label>
							</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								负责人:
							</label>
						</td>
						<td class="value">
						     	 <input id="responsibility" name="responsibility" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${processPlanPage.responsibility}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">负责人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								附件:
							</label>
						</td>
						<td class="value">
							<input id="attFile" name="attFile" type="hidden" style="width: 150px" class="inputxt" value="${processPlanPage.attFile}">
							<input id="attFileName" name="attFileName" type="hidden" style="width: 150px" class="inputxt" value="${processPlanPage.attFileName}">
											<c:if test="${processPlanPage.attFile==''}">
												<a   target="_blank" id="template_href">暂时未上传文件</a>
											</c:if>
											<c:if test="${processPlanPage.attFile!=''}">
												<a href="${processPlanPage.attFile}" target="_blank" id="template_href">${processPlanPage.attFileName}</a>
												<a href="javascript:void(0);" onclick="viewPdf('${processPlanPage.attFile}')">在线阅读</a>
											</c:if>							
								<input class="ui-button" type="button" value="上传附件" onclick="uploadDoc(uploadTemplateCallBack);"/>   
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">附件</label>
							</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								
							</label>
						</td>
						<td class="value">
<t:ckeditor name="taskDescription" isfinder="true" type="width:'100%',height:300" value='${processPlanPage.taskDescription}' ></t:ckeditor>
<%-- 							<textarea id="taskDescription" name="taskDescription" style="width: 350px;height:80px">${processPlanPage.taskDescription}</textarea> --%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;"></label>
						</td>
					</tr>
					<tr style="display:none;">
						<td align="right">
							<label class="Validform_label">
								课题ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="topId" name="topId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${processPlanPage.topId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">课题ID</label>
						</td>
					</tr>

			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/process/processPlan.js"></script>		