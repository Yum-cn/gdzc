<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>交流活动</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="interflowController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${interflowPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动时间:
						</label>
					</td>
					<td class="value">
					<input id="hdsj" name="hdsj" type="text" style="width: 150px" class="inputxt" class="Wdate" onClick="WdatePicker()">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">研究标题</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发言标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发言标题</label>
						</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							发言稿:
						</label>
					</td>
					<td class="value">
						  	 <textarea id="content" name="content" cols="50" rows="16"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发言稿</label>
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
								               
								              value="${topId }" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">课题ID</label>
						</td>
				</tr>

			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/process/interflow.js"></script>		