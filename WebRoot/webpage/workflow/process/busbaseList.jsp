<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>部署流程</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<div class="easyui-layout" fit="true">
<div region="center" style="padding:1px;">
<t:datagrid name="busbaseList" title="业务参数管理" actionUrl="processController.do?datagridBus&processid=${processid }" idField="id">
 <t:dgCol title="Id" field="id" width="20" hidden="false"></t:dgCol>
 <t:dgCol title="业务名称" field="busname" width="70"></t:dgCol>
 <t:dgCol title="业务实体" field="TSTable_entityName" width="70"></t:dgCol>
 <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
 <t:dgDelOpt url="processController.do?delBus&id={id}" title="删除"></t:dgDelOpt>
 <t:dgToolBar title="选择业务" icon="icon-add" url="processController.do?addorupdateBus&processid=${processid }" funname="add"></t:dgToolBar>
 <t:dgToolBar title="修改业务" icon="icon-edit" url="processController.do?addorupdateBus&processid=${processid }" funname="update"></t:dgToolBar>
</t:datagrid>
</div>
</div>
</body>
</html>
