<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>课题申报模板</title>
  <t:base type="ckeditor,jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  function uploadTemplateCallBack(id,url,name){
	  	var point = name.lastIndexOf(".");
	  	var type = name.substr(point);
	  	if(type==".doc" || type==".docx"){
			$("#template_href").attr('href',url);
			$("#template_href").html(name);
			$("#fileRealName").val(name);
			$("#fileName").val(name);
			$("#filePath").val(url);   
	  	}else{
	  		alert("模板文件格式不正确");
	  	}

  }	
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="applyTemplateController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${applyTemplatePage.id }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
						<tr>
					<td align="right" width="120px">
						<label class="Validform_label">
							模板类型:
						</label>
					</td>
					<td class="value">
					     	<t:dictSelect field="type" id="type" hasLabel="false" typeGroupCode="MBLX" defaultVal="${applyTemplatePage.type}"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">模板类型</label>
						</td>
				</tr>
				<tr>
					<td align="right" width="120px">
						<label class="Validform_label">
							模板正文:
						</label>
					</td>
					<td class="value">
					     	<t:ckeditor name="content" isfinder="true" type="width:'100%',height:300" value="${applyTemplatePage.content}" ></t:ckeditor>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">模板正文</label>
						</td>
				</tr>
<!-- 					<tr> -->
<!-- 						<td align="right"> -->
<!-- 							<label class="Validform_label"> -->
<!-- 								文件名称: -->
<!-- 							</label> -->
<!-- 						</td> -->
<!-- 						<td class="value"> -->
<!-- 						     	 <input id="fileName" name="fileName" type="text" style="width: 150px" class="inputxt"   -->
<%-- 									                 value='${applyTemplatePage.fileName}'> --%>
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">文件名称</label> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr style="display:none;"> -->
<!-- 						<td align="right"> -->
<!-- 							<label class="Validform_label"> -->
<!-- 								文件真实名称: -->
<!-- 							</label> -->
<!-- 						</td> -->
<!-- 						<td class="value"> -->
<!-- 						     	 <input id="fileRealName" name="fileRealName" type="text" style="width: 150px" class="inputxt"   -->
									               
<%-- 									                 value='${applyTemplatePage.fileRealName}'> --%>
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">文件真实名称</label> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr style="display:none;"> -->
<!-- 						<td align="right"> -->
<!-- 							<label class="Validform_label"> -->
<!-- 								文件路径: -->
<!-- 							</label> -->
<!-- 						</td> -->
<!-- 						<td class="value"> -->
<!-- 						     	 <input id="filePath" name="filePath" type="text" style="width: 150px" class="inputxt"   -->
									               
<%-- 									                 value='${applyTemplatePage.filePath}'> --%>

<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">文件路径</label> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td align="right"> -->
<!-- 							<label class="Validform_label"> -->
<!-- 								启用状态: -->
<!-- 							</label> -->
<!-- 						</td> -->
<!-- 						<td class="value"> -->
<%-- 							<input type="radio" name="status" value="0"  ${applyTemplatePage.status==0?'checked="checked"':''}/>启用 --%>
<%--   							<input type="radio" name="status" value="1"  ${applyTemplatePage.status==1?'checked="checked"':''}/>禁用 --%>
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">启用状态</label> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td align="right"> -->
<!-- 							<label class="Validform_label"> -->
<!-- 								资源附件: -->
<!-- 							</label> -->
<!-- 						</td> -->
<!-- 						<td class="value"> -->
<%-- 											<c:if test="${applyTemplatePage.filePath==''}"> --%>
<!-- 												<a   target="_blank" id="template_href">暂时未上传文件</a> -->
<%-- 											</c:if> --%>
<%-- 											<c:if test="${applyTemplatePage.filePath!=''}"> --%>
<%-- 												<a href="${applyTemplatePage.filePath}" target="_blank" id="template_href">${applyTemplatePage.fileName}</a> --%>
<%-- 											</c:if>							 --%>
<!-- 								<input class="ui-button" type="button" value="上传附件" onclick="uploadDoc(uploadTemplateCallBack);"/>    -->
<!-- 								<span class="Validform_checktip"></span> -->
<!-- 								<label class="Validform_label" style="display: none;">资源附件</label> -->
<!-- 							</td> -->
<!-- 					</tr> -->
<!-- 					<tr style="display:none;"> -->
<!-- 						<td align="right"> -->
<!-- 							<label class="Validform_label"> -->
<!-- 								创建人: -->
<!-- 							</label> -->
<!-- 						</td> -->
<!-- 						<td class="value"> -->
<!-- 						     	 <input id="createUser" name="createUser" type="text" style="width: 150px" class="inputxt"   -->
									               
<%-- 									                 value='${applyTemplatePage.createUser}'> --%>
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">创建人</label> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr style="display:none;"> -->
<!-- 						<td align="right"> -->
<!-- 							<label class="Validform_label"> -->
<!-- 								组织机构: -->
<!-- 							</label> -->
<!-- 						</td> -->
<!-- 						<td class="value"> -->
<!-- 						     	 <input id="createOrg" name="createOrg" type="text" style="width: 150px" class="inputxt"   -->
									               
<%-- 									                 value='${applyTemplatePage.createOrg}'> --%>
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">组织机构</label> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr style="display:none;"> -->
<!-- 						<td align="right"> -->
<!-- 							<label class="Validform_label"> -->
<!-- 								创建时间: -->
<!-- 							</label> -->
<!-- 						</td> -->
<!-- 						<td class="value"> -->
<!-- 						     	 <input id="createTime" name="createTime" type="text" style="width: 150px" class="inputxt"   -->
									               
<%-- 									                 value='${applyTemplatePage.createTime}'> --%>
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">创建时间</label> -->
<!-- 						</td> -->
<!-- 					</tr> -->
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/apply/applyTemplate.js"></script>		