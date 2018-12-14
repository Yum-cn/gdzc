<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="teachingProgramList" checkbox="false" fitColumns="false" title="" actionUrl="teachingProgramController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标题"  field="title"  hidden="true"  queryMode="single"  width="520"></t:dgCol>
<%--    <t:dgCol title="大纲内容"  field="content"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="创建时间"  field="createTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
<%--    <t:dgCol title="创建单位"  field="createOrg"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
<%--    <t:dgCol title="创建人"  field="createUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="操作" field="opt" width="100" hidden="true"></t:dgCol>
   <t:dgFunOpt funname="jxdg(id)" title="阅读"></t:dgFunOpt>
<%--    <t:dgDelOpt title="删除" url="teachingProgramController.do?doDel&id={id}" /> --%>
<%--    <t:dgToolBar title="录入教学大纲" icon="icon-add" url="teachingProgramController.do?goAdd" funname="add"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="编辑教学大纲" icon="icon-edit" url="teachingProgramController.do?goUpdate" funname="update"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="批量删除"  icon="icon-remove" url="teachingProgramController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="查看" icon="icon-search" url="teachingProgramController.do?goUpdate" funname="detail"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/school/process/teachingProgramList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
	function jxdg(id) {
		window.open ('teachingProgramController.do?goView&id='+id,'newwindow','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no') 
// 		$.dialog({
// 			content: 'url:teachingProgramController.do?goView&id='+id,
// 			lock : true,
// 			width:800,
// 			height:380,
// 			title:"教学大纲",
// 			opacity : 0.3,
// 			cache:false,
// 			cancelVal : '关闭',
// 			cancel : true, /*为true等价于function(){}*/
// 			button : [ {
// 				name : '修改',
// 				callback : function() {
// 					iframe = this.iframe.contentWindow;
// 					saveObj();
// 					return false;
// 				},
// 				focus : true
// 			} ]
// 		});
	}
 </script>
             </body>
</html>