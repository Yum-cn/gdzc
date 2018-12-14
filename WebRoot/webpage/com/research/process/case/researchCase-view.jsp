<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>案例管理</title>
  <t:base type="ckeditor,jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="researchCaseController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${researchCasePage.id }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right" width="120px">
							<label class="Validform_label">
								案例名称:
							</label>
						</td>
						<td class="value">${researchCasePage.title}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">案例名称</label>
						</td>
					</tr>
													<tr>
					<td align="right" width="120px">
						<label class="Validform_label">
							年级:
						</label>
					</td>
					<td class="value">
					     	<t:dictSelect field="grade" typeGroupCode="nianji" hasLabel="false" defaultVal="${researchCasePage.grade}" extendJson="{'style':'width:98%'}"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">年级</label>
						</td>
				</tr>
				<tr>
									<tr>
					<td align="right" width="120px">
						<label class="Validform_label">
							课程:
						</label>
					</td>
					<td class="value">${researchCasePage.course}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">课程</label>
						</td>
				</tr>
				<tr>
					<td align="right" width="120px">
						<label class="Validform_label">
							册:
						</label>
					</td>
					<td class="value">${researchCasePage.book}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">册</label>
						</td>
				</tr>

					<td align="right" width="120px">
						<label class="Validform_label">
							章节:
						</label>
					</td>
					<td class="value">${researchCasePage.chapter}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">册</label>
						</td>
				</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								摘要:
							</label>
						</td>
						<td class="value">${researchCasePage.abstractString}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">摘要</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								关键词:
							</label>
						</td>
						<td class="value">${researchCasePage.keyword}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关键词</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								引用网址:
							</label>
						</td>
						<td class="value">${researchCasePage.referenceUrl}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">引用网址</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								原作者:
							</label>
						</td>
						<td class="value">${researchCasePage.author}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">原作者</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								正文:
							</label>
						</td>
						<td class="value">${researchCasePage.content}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">正文</label>
						</td>
					</tr>

			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/process/researchCase.js"></script>		