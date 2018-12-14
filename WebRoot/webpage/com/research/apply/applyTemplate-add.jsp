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
	  	var point = url.lastIndexOf(".");
	  	var type = url.substr(point);
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="applyTemplateController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${applyTemplatePage.id }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right" width="120px">
						<label class="Validform_label">
							模板类型:
						</label>
					</td>
					<td class="value">
					     	<t:dictSelect field="type" id="type" hasLabel="false" typeGroupCode="MBLX"></t:dictSelect>
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
					     	<t:ckeditor name="content" isfinder="true" type="width:'100%',height:300" value="" ></t:ckeditor>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">模板正文</label>
						</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td align="right" width="120px"> -->
<!-- 						<label class="Validform_label"> -->
<!-- 							文件名称: -->
<!-- 						</label> -->
<!-- 					</td> -->
<!-- 					<td class="value"> -->
<!-- 					     	 <input id="fileName" name="fileName" type="text" style="width: 150px" class="inputxt"   -->
								               
<!-- 								               > -->
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">文件名称</label> -->
<!-- 						</td> -->
<!-- 				</tr> -->
<!-- 				<tr style="display:none;"> -->
<!-- 					<td align="right"> -->
<!-- 						<label class="Validform_label"> -->
<!-- 							文件真实名称: -->
<!-- 						</label> -->
<!-- 					</td> -->
<!-- 					<td class="value"> -->
<!-- 					     	 <input id="fileRealName" name="fileRealName" type="text" style="width: 150px" class="inputxt"   -->
								               
<!-- 								               > -->
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">文件真实名称</label> -->
<!-- 						</td> -->
<!-- 				</tr> -->
<!-- 				<tr style="display:none;"> -->
<!-- 					<td align="right"> -->
<!-- 						<label class="Validform_label"> -->
<!-- 							文件路径: -->
<!-- 						</label> -->
<!-- 					</td> -->
<!-- 					<td class="value"> -->
<!-- 					     	 <input id="filePath" name="filePath" type="text" style="width: 150px" class="inputxt"   -->
								               
<!-- 								               > -->
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">文件路径</label> -->
<!-- 						</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td align="right"> -->
<!-- 						<label class="Validform_label"> -->
<!-- 							启用状态: -->
<!-- 						</label> -->
<!-- 					</td> -->
<!-- 					<td class="value"> -->

<!-- 							<input type="radio" name="status" value="0" checked="checked"/>启用 -->
<!--   							<input type="radio" name="status" value="1" />禁用 -->
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">启用状态</label> -->
<!-- 						</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td align="right"> -->
<!-- 						<label class="Validform_label"> -->
<!-- 							模板文件: -->
<!-- 						</label> -->
<!-- 					</td> -->
<!-- 					<td class="value"> -->
<!-- 							<label id="template_href">暂时未上传文件</label> -->
<!-- 							<input class="ui-button" type="button" value="上传附件" onclick="uploadDoc(uploadTemplateCallBack);"/>    -->
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">模板文件</label> -->
<!-- 						</td> -->
<!-- 				</tr> -->
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/apply/applyTemplate.js"></script>		