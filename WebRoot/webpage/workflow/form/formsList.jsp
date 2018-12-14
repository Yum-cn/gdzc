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
<t:datagrid name="formList" title="表单管理" actionUrl="processController.do?datagridForm" idField="id">
 <t:dgCol title="表单id" hidden="false" field="id" width="30"></t:dgCol>
 <t:dgCol title="表单名称" field="formname" width="70"></t:dgCol>
 <t:dgCol title="提交路径" field="formaction" width="70"></t:dgCol>
 <t:dgCol title="表单编码" field="formcode" width="70"></t:dgCol>
 <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
 <t:dgFunOpt funname="teplateparam(id)" title="表单设计"></t:dgFunOpt>
 <t:dgDelOpt url="processController.do?delForm&id={id}" title="删除"></t:dgDelOpt>
 <t:dgToolBar title="添加表单" icon="icon-add" url="processController.do?addorupdateForm" funname="add"></t:dgToolBar>
 <t:dgToolBar title="编辑表单" icon="icon-edit" url="processController.do?addorupdateForm" funname="update"></t:dgToolBar>
</t:datagrid>
</div>
</div>
<script type="text/javascript">
 function teplateparam(id) {
	location.href='processController.do?formpro&formid='+id;
  //addOneTab('表单设计', 'processController.do?formpro&formid='+id);
 }
</script>
</body>
</html>
