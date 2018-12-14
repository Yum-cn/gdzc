<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>部署流程</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">

<t:datagrid fitColumns="false" name="myTaskList" queryMode="group" title="我的任务列表" actionUrl="taskController.do?taskAllList" idField="id">
 <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
  <t:dgCol title="流程编号" field="Process_processDefinition_id" width="80" query="true"></t:dgCol>
 <t:dgCol title="流程" field="Process_processDefinition_name" width="150" query="true"></t:dgCol>
<t:dgCol title="流程实例" field="Process_task_processInstanceId" width="100"></t:dgCol>
 <t:dgCol title="任务发起人" field="TSUser_userName" width="100"></t:dgCol>
 <t:dgCol title="任务办理人" field="Process_task_assignee" width="100"></t:dgCol>
 <t:dgCol title="开始时间" field="Process_task_createTime" width="130" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
 <t:dgCol title="结束时间" field="Process_task_dueTime" width="130" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
 <t:dgCol title="状态" field="TSPrjstatus_description" width="80"></t:dgCol>
 <t:dgCol title="当前环节" field="Process_task_name" width="100"></t:dgCol>
 <t:dgCol hidden="false" title="TASK ID(该字段隐藏)" field="Process_task_id"></t:dgCol>
 <t:dgCol hidden="false" title="key" field="Process_task_taskDefinitionKey"></t:dgCol>
 <t:dgCol title="操作" field="opt" width="200"></t:dgCol>
 <t:dgConfOpt exp="Process_task_assignee#empty#true" url="activitiController.do?claim&taskId={Process_task_id}" message="确定签收?" title="签收"></t:dgConfOpt>
 <t:dgFunOpt exp="Process_task_assignee#empty#false" funname="openhandleMix(Process_task_id,Process_task_name)"  title="办理"></t:dgFunOpt>
</t:datagrid>

</body>
</html>
