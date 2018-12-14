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
  <t:datagrid name="stageSummaryList" checkbox="true" fitColumns="false" title="" actionUrl="stageSummaryController.do?datagrid&topId=${topId }" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="总结标题"  field="title"  hidden="true"  queryMode="single"  width="620"></t:dgCol>
<%--    <t:dgCol title="总结内容"  field="content"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
<%--    <t:dgCol title="创建人"  field="createUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
<%--    <t:dgCol title="创建单位"  field="createOrg"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="创建时间"  field="createDate"  hidden="true" formatter="yyyy-MM-dd" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100" hidden="true"></t:dgCol>
   <t:dgFunOpt funname="addplantwindow(id)" title="阅读"></t:dgFunOpt>
<%--    <t:dgDelOpt title="删除" url="stageSummaryController.do?doDel&id={id}" /> --%>
<%--    <t:dgToolBar title="录入阶段性总结" icon="icon-add" url="stageSummaryController.do?goAdd&topId=${topId }" funname="add"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="编辑阶段性总结" icon="icon-edit" url="stageSummaryController.do?goUpdate" funname="update"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="批量删除"  icon="icon-remove" url="stageSummaryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="查看" icon="icon-search" url="stageSummaryController.do?goUpdate" funname="detail"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
	function addplantwindow(id) {
		$.dialog({
			content: 'url:stageSummaryController.do?goView&id='+id,
			lock : true,
			width:800,
			height:380,
			title:"阅读",
			opacity : 0.3,
			cache:false,
			cancelVal : '关闭',
			cancel : true, /*为true等价于function(){}*/
			button : []
		});
	}
 </script>
              </body>
</html>