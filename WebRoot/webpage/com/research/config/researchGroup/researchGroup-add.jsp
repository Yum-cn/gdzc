<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>研究领域大类</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="researchGroupController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${researchGroupPage.id }">
					<input id="createUser" name="createUser" type="hidden" value="${researchGroupPage.createUser }">
					<input id="createOrg" name="createOrg" type="hidden" value="${researchGroupPage.createOrg }">
					<input id="createTime" name="createTime" type="hidden" value="${researchGroupPage.createTime }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right" width="120px">
						<label class="Validform_label">
							研究领域名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">研究领域名称</label>
						</td>
				</tr>
				<tr>
					<td align="right" width="120px">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
						  	 <textarea id="content" name="content"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/config/researchGroup.js"></script>		