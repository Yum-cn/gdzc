<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>指标</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="indicatorController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${indicatorPage.id }">
					<input id="parentId" name="parentId" type="hidden" value="${indicatorPage.parentId }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr  style="display:none;">
					<td align="right" width="160px">
						<label class="Validform_label">
							评价项目:
						</label>
					</td>
					<td class="value">
					     	 <t:dictSelect field="pjdl" type="list"
									typeGroupCode="PJDL" defaultVal="${indicatorPage.pjdl}" hasLabel="false"  title="德育评价项目"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">德育评价项目</label>
						</td>
				</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								指标名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="name" name="name" type="text" class="inputxt"  
									               datatype="s1-33"
									                 value='${indicatorPage.name}'>
							<span class="Validform_checktip">1-33个字符</span>
							<label class="Validform_label" style="display: none;">指标名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								指标描述:
							</label>
						</td>
						<td class="value">
						     	 <textarea id="discription" name="discription" style="width: 450px; height : 200px">${indicatorPage.discription}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">指标描述</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/evaluation/base/indicator/indicator.js"></script>		