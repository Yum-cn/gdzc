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
  <t:datagrid name="applyTemplateList" checkbox="false" fitColumns="false" title="申报模板" actionUrl="applyTemplateController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
<%--    <t:dgCol title="文件名称"  field="fileName"  hidden="true"  queryMode="single"  width="520"></t:dgCol> --%>
<%--    <t:dgCol title="文件真实名称"  field="fileRealName"  hidden="false"  queryMode="single"  width="120"></t:dgCol> --%>
<%--    <t:dgCol title="文件路径"  field="filePath"  hidden="false"  queryMode="single"  width="120"></t:dgCol> --%>
<%--    <t:dgCol title="启用状态"  field="status"  hidden="true" replace="启用_0,禁用_1" queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="模板类型"  field="type" dictionary="MBLX" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createUser"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="组织机构"  field="createOrg"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  hidden="true"  queryMode="single" formatter="yyyy-MM-dd" width="100"></t:dgCol>
   <t:dgCol title="操作" field="opt" hidden="true" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="applyTemplateController.do?doDel&id={id}" />
   <t:dgToolBar title="新建模板" icon="icon-add" url="applyTemplateController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑模板" icon="icon-edit" url="applyTemplateController.do?goUpdate" funname="update"></t:dgToolBar>
<%--    <t:dgToolBar title="批量删除"  icon="icon-remove" url="applyTemplateController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="查看" icon="icon-search" url="applyTemplateController.do?goUpdate" funname="detail"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/school/apply/applyTemplateList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 </script>
          </body>
</html>