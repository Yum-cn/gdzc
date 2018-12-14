<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>参阅资料</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="seeResourcesController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${seeResourcesPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								摘记日期:
							</label>
						</td>
						<td class="value">
						     	 <input id="noteDate" name="noteDate" type="text" style="width: 150px" class="inputxt"  
									               class="Wdate" onClick="WdatePicker()"
									            	   value='<fmt:formatDate value="${seeResourcesPage.noteDate}" pattern="yyyy-MM-dd"/>'>	           
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">摘记日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								资料名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${seeResourcesPage.title}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">资料名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								类型:
							</label>
						</td>
						<td class="value">
						    <input type="radio" name="type" id="type" value="0" ${seeResourcesPage.type==0?'checked="checked"':'' }/>书籍
							<input type="radio" name="type" id="type" value="1" ${seeResourcesPage.type==1?'checked="checked"':'' }/>网络
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">0书籍1网络</label>
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
									               
									                 value='${seeResourcesPage.author}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">作者</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								出处:
							</label>
						</td>
						<td class="value">
						     	 <input id="source" name="source" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${seeResourcesPage.source}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出处</label>
						</td>
					</tr>
					<input id="topId" name="topId" type="hidden" style="width: 150px" class="inputxt" value="${seeResourcesPage.topId}">
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/process/seeResources.js"></script>		