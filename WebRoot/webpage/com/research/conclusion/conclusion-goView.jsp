<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>结题申请</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
    function uploadMainCallBack(id,url,name){
	  	var point = url.lastIndexOf(".");
	  	//var type = url.substr(point);
	  	//if(type==".doc" || type==".docx"){
		$("#main_href").attr('href',url);
		$("#main_href").html(name);
		$("#fruitMainFileDoc").val(url);
		$("#fruitMainFileDocName").val(name);
	  	//}else{
	  	//alert("模板文件格式不正确");
	  	//}
    }
    function uploadTemplateCallBack(id,url,name){
	  	var point = url.lastIndexOf(".");
	  	var type = url.substr(point);
		//$("#template_href").attr('href',url);
		//$("#template_href").html(name);
		//$("#applyFileDoc").val(url);
		//$("#applyFileDocName").val(name);
		add_line(name,url);
    }
	  var currentStep = 0;
	  var max_line_num = 1;
	  function add_line(name,url) {
		var html ='<tr>';
		html = html+'<td class="value" align="center">&nbsp;'+max_line_num+'</td>';
		html = html+'<td class="value" align="center">&nbsp;<input id="name" name="name" type="text" style="width: 96%" class="inputxt"  value="'+name+'"></td>';
		html = html+'<td class="value" align="center">&nbsp;        <input id="filePath" name="filePath" type="hidden" style="width: 96%" class="inputxt" value="'+url+'"><input id="fileName" name="fileName" type="hidden" style="width: 96%" class="inputxt" value="'+name+'">';
		html = html+'<select name="shape" id="shape">';
		html = html+'  <option value="论文">论文</option>';
		html = html+'  <option value="教育叙事">教育叙事</option>';
		html = html+'  <option value="随笔">随笔</option>';
		html = html+'  <option value="教学设计">教学设计</option>';
		html = html+'  <option value="教学课件">教学课件</option>';
		html = html+'  <option value="教育案例">教育案例</option>';
		html = html+'  <option value="调研报告">调研报告</option>';
		html = html+'  <option value="教具制作">教具制作</option>';
		html = html+'  <option value="其他">其他</option>';
		html = html+'</select>';
		html = html+'</td>';
		html = html+'<td class="value" align="center">&nbsp; <a href="'+url+'">'+name+'</a></td>';
		html = html+'<td class="value" align="center">&nbsp;<input type="button" name="button" value="删除" onclick="remove_line(this);"/></td>';
		html = html+ '	</tr>';
		$("#content").append(html);
		max_line_num += 1;

	  }
	  function remove_line(index) {
		  $(index).parent().parent().remove();

	  }
   $(document).ready(function(){
	   $("#research_value").html($("#research option:selected").text());

	});
  
  </script>
 </head>
 <body>
 		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
					


			</table>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="auditOpinionController.do?doAdd" tiptype="1" >
<%-- 		<input id="id" name="id" type="hidden" value="${conclusionPage.id }"> --%>
							<input id="topId" name="topId" type="hidden" value="${applyTopicPage.id }">
					<input id="type" name="type" type="hidden" value="1">
					<input id="status" name="status" type="hidden" value="0">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
						<td align="right" width="120px" height="30px">
							<label class="Validform_label">
								课题名称:
							</label>
						</td>
						<td class="value">${applyTopicPage.subjectName}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">课题名称</label>
						</td>
											</tr>
					<tr height="30px">
						<td align="right">
							<label class="Validform_label">
								学科:
							</label>
						</td>
						<td class="value">
						
							<c:forEach items="${xkList }" var="xkList">
							<c:if test="${fn:indexOf(applyTopicPage.subjectType, xkList[0] )!=-1}">${xkList[1] }&nbsp;&nbsp;</c:if>
							</c:forEach>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">学科</label>
						</td>
					</tr>
					<tr height="30px">
						<td align="right">
							<label class="Validform_label">
								研究领域:
							</label>
						</td>
						<td class="value" id="research_value">
								<t:dictSelect field="research" id="research" type="list" typeGroupCode="YJLY" hasLabel="false" defaultVal="${applyTopicPage.research}"></t:dictSelect>
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">研究领域</label>
							</td>
					</tr>
					<tr height="30px">
						<td align="right">
							<label class="Validform_label">
								课题负责人:
							</label>
						</td>
						<td class="value">${applyTopicPage.subjectLeading}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">课题负责人</label>
						</td>
											</tr>


					<tr height="30px">
						<td align="right">
							<label class="Validform_label">
								联系电话:
							</label>
						</td>
						<td class="value">${applyTopicPage.contactPhone}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系电话</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								成果主件:
							</label>
						</td>
						<td class="value">
							<input id="fruitMainFileDoc" name="fruitMainFileDoc" type="hidden" style="width: 150px" class="inputxt">
							<input id="fruitMainFileDocName" name="fruitMainFileDocName" type="hidden" style="width: 150px" class="inputxt">
											<c:if test="${applyTopicPage.fruitMainFileDoc=='' || applyTopicPage.fruitMainFileDoc==null}">
												<a   target="_blank" id="main_href">暂时未上传成果主件</a>
											</c:if>
											<c:if test="${applyTopicPage.fruitMainFileDoc!='' && applyTopicPage.fruitMainFileDoc!=null}">
												<a href="${applyTopicPage.fruitMainFileDoc}" target="_blank" id="main_href">${applyTopicPage.fruitMainFileDocName}</a>
											</c:if>							
								   
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">成果主件</label>
							</td>
					</tr>
					<tr height="30px">
						<td align="right">
							<label class="Validform_label">
								结题审核意见:
							</label>
						</td>
						<td class="value"><textarea name="opinion" style="width: 96%;height:66px;" ></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结题审核意见</label>
						</td>
					</tr>
				<tr style="display:none;">
					<td align="right">
						<label class="Validform_label">
							课题ID:
						</label>
					</td>
					<td class="value">
<!-- 							<input id="type" name="type" type="text" style="width: 150px" class="inputxt" value="1"> -->
<!-- 							<input id="status" name="status" type="text" style="width: 150px" class="inputxt" value=""> -->
<%-- 					     	<input id="topId" name="topId" type="text" style="width: 150px" class="inputxt" value="${topId }"> --%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">课题ID</label>
						</td>
				</tr>


			</table>
			<table width="100%" cellpadding="0" cellspacing="1" class="formtable" id="content">
			  <tr style="height:30px">
			    <td align="center" class="Validform_label" valign="middle">序号</td>
			    <td align="center" class="Validform_label" valign="middle">成果名称</td>
			    <td align="center" class="Validform_label" valign="middle">成果形式</td>
			    <td align="center" class="Validform_label" valign="middle">附件</td>
			  </tr>
			  <c:forEach items="${conclusionList }" var="conclusionList" varStatus="stu">
			  <tr style="height:30px">
			    <td class="value" align="center">&nbsp;1</td>
			    <td class="value" align="center">&nbsp;${conclusionList.name}</td>
			    <td class="value" align="center">&nbsp;
			    <input id="filePath" name="filePath" type="hidden" style="width: 96%" class="inputxt" value="${conclusionList.filePath }">
			    <input id="fileName" name="fileName" type="hidden" style="width: 96%" class="inputxt" value="${conclusionList.fileName }">        
			    ${conclusionList.shape}
		        </td>
			    <td class="value" align="center">&nbsp;<a href="${conclusionList.filePath }">${conclusionList.fileName }</a></td>
			  </tr>
			  </c:forEach>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/conclusion/conclusion.js"></script>		