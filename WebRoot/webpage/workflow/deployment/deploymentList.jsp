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
<t:datagrid name="deploymentList" title="流程部署" actionUrl="activitiController.do?processDeploymentGrid" idField="id">
 <t:dgCol title="ProcessDefinitionId" field="id" width="100" hidden="false"></t:dgCol>
 <t:dgCol title="DeploymentId" field="deploymentId" width="70" hidden="false"></t:dgCol>
 <t:dgCol title="名称" field="name" width="70"></t:dgCol>
 <t:dgCol title="流程KEY" field="key" width="70"></t:dgCol>
 <t:dgCol title="版本号" field="version"></t:dgCol>
 <t:dgCol title="状态" field="suspensionState" replace="已激活_1,已挂起_2"></t:dgCol>
 <t:dgCol title="图片名" field="diagramResourceName" hidden="false"></t:dgCol>
 <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
 <t:dgFunOpt funname="deploymentimg(deploymentId,diagramResourceName,name)" title="流程图"></t:dgFunOpt>
 <t:dgConfOpt exp="suspensionState#eq#1" url="activitiController.do?setProcessState&state=suspend&processDefinitionId={id}" title="挂起" message="是否挂起?"></t:dgConfOpt>
 <t:dgConfOpt exp="suspensionState#eq#2" url="activitiController.do?setProcessState&state=active&processDefinitionId={id}" title="激活" message="是否激活?"></t:dgConfOpt>
 <t:dgDelOpt url="activitiController.do?deleteProcess&key={key}&deploymentId={deploymentId}" title="删除"></t:dgDelOpt>
 <t:dgToolBar title="流程部署" icon="icon-edit" url="activitiController.do?openDeployProcess" funname="openuploadwin"></t:dgToolBar>
</t:datagrid>
</div>
</div>
</body>
</html>
