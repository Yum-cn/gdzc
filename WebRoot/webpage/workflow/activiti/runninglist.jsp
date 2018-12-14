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
<div region="center" style="padding: 1px;">
<t:datagrid name="runningList" title="流程实例管理" autoLoadData="true" actionUrl="processInstanceController.do?runningProcessDataGrid" sortName="userName" fitColumns="true"
	idField="id" fit="true" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
	<t:dgCol title="流程名称" sortable="false" field="prcocessDefinitionName"></t:dgCol>
	<t:dgCol title="流程定义ID" sortable="false" field="processDefinitionId"></t:dgCol>
	<t:dgCol title="流程实例ID" field="processInstanceId" query="true" ></t:dgCol>
	<t:dgCol title="发起人" field="startUserId" query="true" ></t:dgCol>
	<t:dgCol title="当前任务名称" field="activityName" ></t:dgCol>
	<t:dgCol title="当前任务处理人" field="activityUser" ></t:dgCol>
	<t:dgCol title="开始时间" field="starttime" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
	<t:dgCol title="结束时间" field="endtime" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
	<t:dgCol title="耗时" field="spendTimes" ></t:dgCol>
	<t:dgCol title="状态" sortable="false" field="isSuspended" replace="已结束_finished,启动_false,暂停_true" style="background:red;_true"></t:dgCol>
	<t:dgCol title="操作" field="opt"></t:dgCol>
	<t:dgFunOpt exp="isSuspended#ne#true&&isSuspended#ne#finished" funname="suspendProcessInstance(processInstanceId,isSuspended)" title="暂停"></t:dgFunOpt>
	<t:dgFunOpt exp="isSuspended#ne#false&&isSuspended#ne#finished" funname="startProcessInstance(processInstanceId,isSuspended)" title="启动"></t:dgFunOpt>
	<t:dgFunOpt exp="isSuspended#ne#finished" funname="closeProcessInstance(processInstanceId,isSuspended)" title="关闭"></t:dgFunOpt>
	<t:dgFunOpt exp="isSuspended#ne#true&&isSuspended#ne#finished" funname="reassign(processInstanceId,isSuspended)" title="委派"></t:dgFunOpt>
	<t:dgFunOpt funname="viewHistory(processInstanceId)" title="历史"></t:dgFunOpt>
</t:datagrid>
</div>
</div>
<script type="text/javascript">
		//暂停
		function suspendProcessInstance(processInstanceId,isSuspended){
			confirm('processInstanceController.do?suspend&processInstanceId='+processInstanceId,'确定暂停吗？','runningList');
		}
		
		//启动
		function startProcessInstance(processInstanceId,isSuspended){
			confirm('processInstanceController.do?restart&processInstanceId='+processInstanceId,'确定启动吗？','runningList');
		}
		
		//关闭
		function closeProcessInstance(processInstanceId,isSuspended){
			confirm('processInstanceController.do?close&processInstanceId='+processInstanceId,'确定关闭吗？','runningList');
		}
		
		//委派(重新分配处理人)
		function reassign(processInstanceId,isSuspended){
			createwindow('委派','processInstanceController.do?reassignInit&processInstanceId='+processInstanceId,700,100);
		}
		
		//查看流程历史
		function viewHistory(processInstanceId){
			var url = "";
			var title = "流程历史";
			url = "activitiController.do?viewProcessInstanceHistory&processInstanceId="+processInstanceId+"&isIframe"
			location.href=url;
			//addOneTab(title, url);
		}
		
		$(document).ready(function(){
			$("input[name='starttime_begin']").attr("class","easyui-datebox");
			$("input[name='starttime_end']").attr("class","easyui-datebox");
			$("input[name='endtime_begin']").attr("class","easyui-datebox");
			$("input[name='endtime_end']").attr("class","easyui-datebox");
		});
</script>
</body>
</html>
