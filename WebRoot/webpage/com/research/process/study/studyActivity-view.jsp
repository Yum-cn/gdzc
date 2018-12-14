<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>研究活动管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="studyActivityController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${studyActivityPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr style="display:none;">
						<td align="right">
							<label class="Validform_label">
								课题ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="topId" name="topId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${studyActivityPage.topId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">课题ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								研究标题:
							</label>
						</td>
						<td class="value">${studyActivityPage.title}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">研究标题</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								研究内容:
							</label>
						</td>
						<td class="value">${studyActivityPage.content}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">研究内容</label>
						</td>
					</tr>
					
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/process/studyActivity.js"></script>		