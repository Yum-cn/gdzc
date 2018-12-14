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
  <t:datagrid name="applyTopicList" checkbox="false" fitColumns="false" title="课题申报" actionUrl="auditOpinionController.do?datagrid&topId=${topId }" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="意见"  field="opinion"  hidden="true"  queryMode="single"  width="720"></t:dgCol>
   <t:dgCol title="节点"  field="type"  hidden="true" replace="申报审核_0,结题审核_1" queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="状态"  field="status"  hidden="true" replace="同意_0,不同意_1" queryMode="single"  width="60"></t:dgCol>

  </t:datagrid>
  </div>
 </div>

          </body>
</html>