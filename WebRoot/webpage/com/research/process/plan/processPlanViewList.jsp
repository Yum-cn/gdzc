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
  <t:datagrid name="processPlanList" checkbox="true" fitColumns="false" title="" actionUrl="processPlanController.do?datagrid&topId=${topId}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="开始时间"  field="startDate"  hidden="false" formatter="yyyy-MM-dd" queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="结束时间"  field="endDate"  hidden="true" formatter="yyyy-MM-dd"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="研究的主题"  field="theme"  hidden="true"  queryMode="single"  width="420"></t:dgCol>
   <t:dgCol title="负责人"  field="responsibility"  hidden="true"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="课题ID"  field="topId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createUser"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建单位"  field="createOrg"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createDate"  hidden="true" formatter="yyyy-MM-dd" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="60" hidden="true"></t:dgCol>
   <t:dgFunOpt funname="addplantwindow(id)" title="阅读"></t:dgFunOpt>
<%--    <t:dgDelOpt title="删除" url="processPlanController.do?doDel&id={id}" /> --%>
<%--    <t:dgToolBar title="新增工作计划" icon="icon-add" url="processPlanController.do?goAdd&topId=${topId}" funname="add"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="编辑工作计划" icon="icon-edit" url="processPlanController.do?goUpdate" funname="update"></t:dgToolBar> --%>

  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 function test(){
	 alert(123);
 }
	function addplantwindow(id) {
		$.dialog({
			content: 'url:processPlanController.do?goView&id='+id,
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