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
  <t:datagrid name="researchGroupTypeList" checkbox="true" fitColumns="false" title="研究领域小类" actionUrl="researchGroupTypeController.do?datagrid&parentId=${groupId }" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="名称"  field="name"  hidden="true"  queryMode="single"  width="320"></t:dgCol>
   <t:dgCol title="备注"  field="content"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建单位"  field="createOrg"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createUser"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="父类ID"  field="parentId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="researchGroupTypeController.do?doDel&id={id}" />
   <t:dgToolBar title="录入小类" icon="icon-add" url="researchGroupTypeController.do?goAdd&groupId=${groupId }" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑小类" icon="icon-edit" url="researchGroupTypeController.do?goUpdate" funname="update"></t:dgToolBar>
<%--    <t:dgToolBar title="批量删除"  icon="icon-remove" url="researchGroupTypeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="查看" icon="icon-search" url="researchGroupTypeController.do?goUpdate" funname="detail"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/school/config/researchGroupTypeList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 </script>
           </body>
</html>