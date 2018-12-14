<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>工作计划</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
 <div align="right" style="height:50px;vertical-align: middle;text-align: right;">
 <br><input type="button" name="button" value="添加工作计划条目" onclick="add_line();"/>
 </div>
 
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="processPlanController.do?doUpdate" tiptype="1">
		<table width="100%" style="width: 100%" cellpadding="0" cellspacing="1" class="formtable" id="content">
		  <tr height="30px">
		    <td class="Validform_label" align="center" width="30%">时间计划</td>
		    <td class="Validform_label" align="center" width="25%">研究主题</td>
		    <td class="Validform_label" align="center" width="20%">责任人</td>
		    <td class="Validform_label" align="center" width="25%">任务描述</td>
		  </tr>
<!-- 		  <tr height="30px"> -->
<%-- 		    <td class="value" align="center"><input id="startDate" name="startDate" type="text" style="width: 40%" class="Wdate" onClick="WdatePicker()" value='${processPlanPage.startDate}'>&nbsp;至&nbsp;<input id="endDate" name="endDate" type="text" style="width: 40%" class="Wdate" onClick="WdatePicker()" value='${processPlanPage.endDate}'></td> --%>
<%-- 		    <td class="value"><input id="theme" name="theme" type="text" style="width: 100%" class="inputxt" value='${processPlanPage.theme}'></td> --%>
<%-- 		    <td class="value"><input id="responsibility" name="responsibility" type="text" style="width: 100%" class="inputxt" value='${processPlanPage.responsibility}'></td> --%>
<%-- 		    <td class="value"><input id="taskDescription" name="taskDescription" type="text" style="width: 100%" class="inputxt" value='${processPlanPage.taskDescription}'></td> --%>
<!-- 		  </tr> -->
		</table>
		<input id="id" name="id" type="hidden" value="${processPlanPage.id }">
		
		</t:formvalid>
 </body>
 <script type="text/javascript">
 var currentStep = 0;
 var max_line_num = 1;
 function add_line() {
	var html ='<tr>';
	html = html+ '<td class="value" align="center"><input id="startDate" name="startDate" type="text" style="width: 40%" class="Wdate" onClick="WdatePicker()" >&nbsp;至&nbsp;<input id="endDate" name="endDate" type="text" style="width: 40%" class="Wdate" onClick="WdatePicker()" ></td>';
	html = html+ '<td class="value"><input id="theme" name="theme" type="text" style="width: 100%" class="inputxt"></td>';
	html = html+ '<td class="value"><input id="responsibility" name="responsibility" type="text" style="width: 100%" class="inputxt"></td>';
	html = html+ '<td class="value"><input id="taskDescription" name="taskDescription" type="text" style="width: 100%" class="inputxt"></td>';
	html = html+ '<td class="value"><input type="button" name="button" value="删除" onclick="add_line();"/></td>';

	html = html+ '	</tr>';
	$("#content").append(html);
	max_line_num += 1;

 }
 function remove_line(index) {
	  $(index).parent().parent().remove();

 }
 </script>
  <script src = "webpage/com/school/process/processPlan.js"></script>		