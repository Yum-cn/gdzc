<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>题库主表信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="questionLibraryController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${questionLibraryPage.id }">
					<input id="createuser" name="createuser" type="hidden" value="${questionLibraryPage.createuser }">
					<input id="createTime" name="createTime" type="hidden" value="${questionLibraryPage.createTime }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable" id="content">
					<tr>
						<td align="right" width="120px;">
							<label class="Validform_label">
								题库大类:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="type" type="list"
										typeGroupCode="TIKUDALEI" defaultVal="${questionLibraryPage.type}" hasLabel="false"  title="题库大类"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">题库大类</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								题库小类:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="smallType" type="list"
										typeGroupCode="TIKUXIAOL" defaultVal="${questionLibraryPage.smallType}" hasLabel="false"  title="题库小类"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">题库小类</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								试题类型:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="style" id="style" type="list"  extendJson="{ 'onchange': 'selectType()','datatype':'*'}"
										typeGroupCode="TKXXLX" defaultVal="${questionLibraryPage.style}" hasLabel="false"  title="试题类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">试题类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								题目标题:
							</label>
						</td>
						<td class="value">
						     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${questionLibraryPage.title}'>&nbsp;&nbsp;<img src="${webRoot }/webfront/images/icon/add.png" border="0" width="16px" onclick="add_line();"/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">题目标题</label>
						</td>
					</tr>
					<c:forEach items="${optionList }" var="optionList">
					<tr id="optionTR">
						<td align="right" >
							<label class="Validform_label">
								题目选项:
							</label>
						</td>
						<td class="value" valign="middle">
						     	<input id="title" name="option" type="text" style="width: 150px" class="inputxt" value="${optionList.optionName}">&nbsp;&nbsp;<img src="${webRoot }/webfront/images/icon/delete.png" onclick="remove_line(this);" width="18px" border="0" />
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">题目标题</label>
						</td>
					</tr>
					</c:forEach>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/train/questionlibrary/questionLibrary.js"></script>	
    <script type="text/javascript">
	  var currentStep = 0;
	  var max_line_num = 1;
	  function add_line() {
		var html ='<tr>';
		html = html+ '		<td align="right">';
		html = html+ '		<label class="Validform_label">';
		html = html+ '			题目选项:';
		html = html+ '		</label>';
		html = html+ '	</td>';
		html = html+ '		<td class="value" valign="middle">';
		html = html+ '		     	<input id="title" name="option" type="text" style="width: 150px" class="inputxt">&nbsp;&nbsp;<img src="${webRoot }/webfront/images/icon/delete.png" onclick="remove_line(this);" width="18px" border="0" />';
		html = html+ '				<span class="Validform_checktip"></span>';
		html = html+ '				<label class="Validform_label" style="display: none;">题目标题</label>';
		html = html+ '		</td>';
		html = html+ '	</tr>';
		$("#content").append(html);
		max_line_num += 1;

	  }
	  function remove_line(index) {
		  $(index).parent().parent().remove();

	  }
	  function selectType(){
		  var styleVal = $("#style").val();
		  if(styleVal=='TIANKONG' || styleVal=='JIANDATI'){
			  $("#optionTR").hide();
			  $("#title").val("填空标题<fill>后续内容");
		  }else{
			  $("#optionTR").show();
			  $("#title").val("");
		  }
	  }
  </script>
  </html>	