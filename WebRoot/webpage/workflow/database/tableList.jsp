<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>部署流程</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">

  <t:datagrid name="${type.typecode}List" title="数据表列表" actionUrl="dbController.do?tableGrid&typeid=${type.id }" idField="id">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="表名称" field="tableName" width="15"></t:dgCol>
   <t:dgCol title="实体KEY" field="entityKey" width="15"></t:dgCol>
   <t:dgCol title="实体名称" field="entityName" width="50"></t:dgCol>
    <t:dgCol title="描述" field="tableTitle"></t:dgCol>
<%--    <t:dgToolBar title="编辑" icon="icon-edit" url="dbController.do?aouTable" funname="update"></t:dgToolBar>--%>
<%--    <t:dgToolBar title="加载" icon="icon-edit" url="dbController.do?reloadData" funname="doSubmit"></t:dgToolBar>--%>
  </t:datagrid>
 <div id="${type.typecode}Listtb" style="padding: 3px; height: 25px">
  <div style="float: left;">
   <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','dbController.do?aouTable','id')">编辑</a>
   <a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="doSubmit('dbController.do?reloadData','加载')">加载</a>
  </div>
 </div>

</body>
</html>
