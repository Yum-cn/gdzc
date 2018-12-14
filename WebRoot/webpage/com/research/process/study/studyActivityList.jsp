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
  <t:datagrid name="studyActivityList" checkbox="false" fitColumns="false" title="" actionUrl="studyActivityController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="课题ID"  field="topId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="研究标题"  field="title"  hidden="true"  queryMode="single"  width="320"></t:dgCol>
<%--    <t:dgCol title="研究内容"  field="content"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="活动时间"  field="hdsj" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
<%--    <t:dgCol title="创建单位"  field="createOrg"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
<%--    <t:dgCol title="创建人"  field="createUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="操作" field="opt" width="100" hidden="true"></t:dgCol>
   <t:dgDelOpt title="删除" url="studyActivityController.do?doDel&id={id}" />
   <t:dgToolBar title="录入研究活动" icon="icon-add" url="studyActivityController.do?goAdd&topId=${topId }" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑研究活动" icon="icon-edit" url="studyActivityController.do?goUpdate" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 </script>
               </body>
</html>