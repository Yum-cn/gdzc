<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>部署流程</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:datagrid fitColumns="false" name="historyTaskList" queryMode="group" title="历史任务列表" actionUrl="taskController.do?taskHistoryList" idField="id">
 <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
 <t:dgCol title="流程编号" field="procDefId" width="80" query="true"></t:dgCol>
 <t:dgCol title="流程" field="prodef_name" width="150" query="true"></t:dgCol>
 <t:dgCol title="流程实例" field="proInsl_procInstId" width="100"></t:dgCol>
 <t:dgCol title="任务名称" field="name" width="100"></t:dgCol>
 <t:dgCol title="任务发起人" field="proInsl_startUserId" width="100"></t:dgCol>
 <t:dgCol title="任务处理人" field="assignee" width="100"></t:dgCol>
 <t:dgCol title="开始时间" field="startTime" width="130" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
 <t:dgCol title="结束时间" field="endTime" width="130" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
 <t:dgCol title="持续时间" field="durationStr"  width="100"></t:dgCol>
 <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
 <t:dgFunOpt funname="processView(id,name)" title="<font style=color:red>查看</font>"></t:dgFunOpt>
	<%/*  
	<t:dgConfOpt exp="Process_task_assignee#empty#true" url="activitiController.do?claim&taskId={Process_task_id}" message="确定签收?" title="签收"></t:dgConfOpt>
 	<t:dgFunOpt exp="Process_task_assignee#empty#false" funname="openhandle(Process_task_id,Process_task_name)" title="办理"></t:dgFunOpt>
 	<t:dgFunOpt funname="processimg(Process_task_id,Process_task_name)" title="<font style=color:red>流程进度图</font>"></t:dgFunOpt>
	 */%>
 </t:datagrid>
<SCRIPT type="text/javascript">
//查看任务
function processView(taskid,taskname){
	
}
</SCRIPT>
</body>
</html>
