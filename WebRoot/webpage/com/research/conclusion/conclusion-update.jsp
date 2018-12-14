<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>结题申请</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="conclusionController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${conclusionPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								成果名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${conclusionPage.name}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">成果名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								成果形式:
							</label>
						</td>
						<td class="value">
						     	 <input id="shape" name="shape" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${conclusionPage.shape}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">成果形式</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								课题ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="topId" name="topId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${conclusionPage.topId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">课题ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								成果附件地址:
							</label>
						</td>
						<td class="value">
						     	 <input id="filePath" name="filePath" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${conclusionPage.filePath}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">成果附件地址</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								成果附件真实名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="fileName" name="fileName" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${conclusionPage.fileName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">成果附件真实名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建人:
							</label>
						</td>
						<td class="value">
						     	 <input id="createUser" name="createUser" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${conclusionPage.createUser}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建单位:
							</label>
						</td>
						<td class="value">
						     	 <input id="createOrg" name="createOrg" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${conclusionPage.createOrg}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建单位</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建时间:
							</label>
						</td>
						<td class="value">
						     	 <input id="createDate" name="createDate" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${conclusionPage.createDate}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建时间</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/conclusion/conclusion.js"></script>		