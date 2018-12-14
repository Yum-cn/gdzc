<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>证书登记</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
    function uploadMainCallBack(id,url,name){
	  	var point = url.lastIndexOf(".");
	  	//var type = url.substr(point);
	  	//if(type==".doc" || type==".docx"){
		//$("#photo_href").attr('href',url);
		$("#photo_href").html('<img src="'+url+'" width="100" height="100"/>');
		$("#photoPath").val(url);
		$("#photoName").val(name);
	  	//}else{
	  	//alert("模板文件格式不正确");
	  	//}
    }
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="certificateRegisterController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${certificateRegisterPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							获奖人/单位:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							等级:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="grade" typeGroupCode="ZSDJ" hasLabel="false"></t:dictSelect>
					     	
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">等级</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							奖品:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="prizeName" typeGroupCode="JPMC" hasLabel="false"></t:dictSelect>
					     	
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">奖品</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="type" typeGroupCode="HJLX" hasLabel="false"></t:dictSelect>

							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类型</label>
						</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td align="right"> -->
<!-- 						<label class="Validform_label"> -->
<!-- 							获奖名称: -->
<!-- 						</label> -->
<!-- 					</td> -->
<!-- 					<td class="value"> -->
<!-- 					     	 <input id="awardName" name="awardName" type="text" style="width: 150px" class="inputxt"   -->
								               
<!-- 								               > -->
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">获奖名称</label> -->
<!-- 						</td> -->
<!-- 				</tr> -->
				<tr>
					<td align="right">
						<label class="Validform_label">
							获奖时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="awardTime" name="awardTime" type="text" style="width: 150px" class="inputxt"  
								               
								              class="Wdate" onClick="WdatePicker()" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">获奖时间</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							颁发单位:
						</label>
					</td>
					<td class="value">
					     	 <input id="issuingUnit" name="issuingUnit" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">颁发单位</label>
						</td>
				</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								证书照片:
							</label>
						</td>
						<td class="value">
							<input id="photoPath" name="photoPath" type="hidden" style="width: 150px" class="inputxt">
							<input id="photoName" name="photoName" type="hidden" style="width: 150px" class="inputxt">
							
							<label id="photo_href">暂未上传证书照片</label>
							<input class="ui-button" type="button" value="上传证书照片" onclick="commonUpload(uploadMainCallBack);"/>   
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申报文件</label>					
								   
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">成果主件</label>
							</td>
					</tr>
			</table><input id="funType" name="funType" type="hidden" style="width: 150px" class="inputxt" value="${funType }">
			<input id="stauts" name="stauts" type="hidden" style="width: 150px" class="inputxt" value="0">
		</t:formvalid>
 </body>
  <script src = "webpage/com/school/process/certificateRegister.js"></script>		