<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>教学大纲</title>
  <t:base type="ckeditor,jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="teachingProgramController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${teachingProgramPage.id }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
					<tr height="38px">
						<td align="right" width="120px">
							<label class="Validform_label">
								标题:
							</label>
						</td>
						<td class="value">${teachingProgramPage.title}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
					</tr>
					<tr height="38px">
						<td align="right" width="120px">
							<label class="Validform_label">
								大纲内容:
							</label>
						</td>
						<td class="value">${teachingProgramPage.content}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">大纲内容</label>
						</td>
					</tr>

			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/process/teachingProgram.js"></script>		