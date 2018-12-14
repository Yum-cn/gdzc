<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>校本课题申报表</title>
  <t:base type="ckeditor,jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
    function uploadTemplateCallBack(id,url,name){
	  	var point = url.lastIndexOf(".");
	  	var type = url.substr(point);
	  	if(type==".doc" || type==".docx"){
			$("#template_href").attr('href',url);
			$("#template_href").html(name);
			$("#applyFileDoc").val(url);
			$("#applyFileDocName").val(name);
	  	}else{
	  		alert("模板文件格式不正确");
	  	}
  }
    function selectProvince(){
  	  var province = $("#research").val();
  	  var url = "applyTopicController.do?getCity&province="+province;
  		$.ajax({
  			url : url,
  			type : 'post',
  			cache : false,
  			success : function(data) {
  				var d = $.parseJSON(data);
  				$("#province").html("");
  				$.each(d.obj,function(key,value){    					   
  					   $("#province").append("<option value='"+value[0]+"'>"+value[1]+"</option>");
  				});

  			}
  		});
    }
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="applyTopicController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${applyTopicPage.id }">
					<input id="status" name="status" type="hidden" value="0">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right" width="120px">
						<label class="Validform_label">
							课题名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="subjectName" name="subjectName" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">课题名称</label>
						</td>
					</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							学科:
						</label>
					</td>
					<td class="value">
							<c:forEach items="${xkList }" var="xkList">
							<input type="checkbox" name="subjectType" value="${xkList[0] }" />${xkList[1] }
							</c:forEach>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">学科</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							研究领域:
						</label>
					</td>
					<td class="value">
<%-- 							<t:dictSelect field="research" type="list" dictTable="st_research_group" dictField="id" dictText="name" hasLabel="false" ></t:dictSelect> --%>
							  <select name="research" id="research" onchange="selectProvince()">
							  	<option value="">请选择</option>
							  	<c:forEach items="${researchGroupList }" var="list">
							  	<option value="${list[0] }">${list[1] }</option>
							  	</c:forEach>
							  </select>
							  <select name="smallResearch" id="province">
							  	<option value="">请选择</option>
							  </select>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">研究领域</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							课题负责人:
						</label>
					</td>
					<td class="value">
					     	 <input id="subjectLeading" name="subjectLeading" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">课题负责人</label>
						</td>
									</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							课题组成员:
						</label>
					</td>
					<td class="value">
					     	<input name="groupMember" name="groupMember" type="hidden" value="${id}" id="groupMember"> 
					     	<input name="groupMemberName" class="inputxt" value="" id="realName"readonly="readonly"  /> 
					     	<t:choose hiddenName="groupMember" hiddenid="id" url="userController.do?selectUser" name="selectUserList" icon="icon-search" title="用户列表" textname="realName" top="10" width="500" isclear="true"></t:choose>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">课题组成员</label>
						</td>
									</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							预期成果:
						</label>
					</td>
					<td class="value">
					     	 <input id="expectedResults" name="expectedResults" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预期成果</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预计完成时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="completionTime" name="completionTime" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预计完成时间</label>
						</td>
									</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							联系电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="contactPhone" name="contactPhone" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系电话</label>
						</td>
					</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							申报文件:
						</label>
					</td>
					<td class="value">
							<input id="applyFileDoc" name="applyFileDoc" type="hidden" style="width: 150px" class="inputxt">
							<input id="applyFileDocName" name="applyFileDocName" type="hidden" style="width: 150px" class="inputxt">
							<label id="template_href">暂时未上传文件</label>
							<input class="ui-button" type="button" value="上传申报文件" onclick="uploadDoc(uploadTemplateCallBack);"/>   
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申报文件</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							申报表:
						</label>
					</td>
					<td class="value">
<!-- 					        <textarea name="significance" style="width:92%;height:120px;"></textarea> -->
							<t:ckeditor name="significance" isfinder="true" type="width:'100%',height:300" value="${templateEntity.content }" ></t:ckeditor>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">研究目的及实际意义</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/apply/applyTopic.js"></script>		