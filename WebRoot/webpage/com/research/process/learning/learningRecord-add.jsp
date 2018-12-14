<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>学习记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="learningRecordController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${learningRecordPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							讲座人:
						</label>
					</td>
					<td class="value">
					     	 <input id="lecture" name="lecture" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">讲座人</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							讲座日期:
						</label>
					</td>
					<td class="value">
							   <input id="lectureDate" name="lectureDate" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
								                
								               >    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">讲座日期</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							感想:
						</label>
					</td>
					<td class="value">
						  	 <textarea id="feel" name="feel" cols="50" rows="16"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">感想</label>
						</td>
				</tr>

			</table><input id="topId" name="topId" type="hidden" style="width: 150px" class="inputxt" value="${topId }">
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/process/learningRecord.js"></script>		