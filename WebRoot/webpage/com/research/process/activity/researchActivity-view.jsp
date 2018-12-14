<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>科研活动管理</title>
  <t:base type="ckeditor,jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="researchActivityController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${researchActivityPage.id }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right" width="120px">
							<label class="Validform_label">
								活动时间:
							</label>
						</td>
						<td class="value"><fmt:formatDate value="${researchActivityPage.activitiesDate}" pattern="yyyy-MM-dd"/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动时间</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								活动主题:
							</label>
						</td>
						<td class="value">${researchActivityPage.title}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动主题</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								参与人员:
							</label>
						</td>
						<td class="value">${researchActivityPage.participant}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">参与人员</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								活动优点:
							</label>
						</td>
						<td class="value">${researchActivityPage.advantage}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动优点</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								活动问题:
							</label>
						</td>
						<td class="value">${researchActivityPage.problem}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动问题</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								活动反思:
							</label>
						</td>
						<td class="value">${researchActivityPage.reflect}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动反思</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								活动内容记录:
							</label>
						</td>
						<td class="value">${researchActivityPage.content}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动内容记录</label>
						</td>
					</tr>
			</table>
			<input id="topId" name="topId" type="hidden" style="width: 150px" class="inputxt" value="${topId }">
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/process/researchActivity.js"></script>		