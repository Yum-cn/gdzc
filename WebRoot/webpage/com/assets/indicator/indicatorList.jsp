<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>指标管理</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="indicatorList" checkbox="true" fitColumns="false" title="培养目标设置 > 指标项设置" actionUrl="indicatorController.do?datagrid&pjdl=${pjdl }&parentId=${parentId }" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
<%--    <t:dgCol title="评价项目"  field="pjdl"  hidden="true" dictionary="PJDL" queryMode="single" query="false" width="120"></t:dgCol> --%>
   <t:dgCol title="指标名称"  field="name"  hidden="true" queryMode="single" query="false" width="120"></t:dgCol>
   <t:dgCol title="指标描述"  field="discription"  hidden="false"  queryMode="single"  width="320"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="indicatorController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="indicatorController.do?goAdd&pjdl=${pjdl }&parentId=${parentId }" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="indicatorController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="引用指标" icon="icon-edit" url="indicatorController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="indicatorController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="indicatorController.do?goUpdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>


 <script src = "webpage/com/evaluation/base/indicator/indicatorList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 </script>
  </body>
</html>