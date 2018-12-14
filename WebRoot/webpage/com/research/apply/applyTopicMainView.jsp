<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<div id="maintabs" class="easyui-tabs" fit="true" border="false">

<%-- <div class="easyui-tab" title="参阅资料" href="seeResourcesController.do?seeResourcesMain&topId=${topId }" style="padding: 2px; overflow: hidden;"></div> --%>


<div class="easyui-tab" title="研究计划" href="processPlanController.do?processViewPlan&topId=${topId }" style="padding: 2px; overflow: hidden;"></div>
<div class="easyui-tab" title="研究记录" href="observationRecordController.do?observationRecordView&topId=${topId }" style="padding: 2px; overflow: hidden;"></div>
<div class="easyui-tab" title="阶段性总结" href="stageSummaryController.do?stageSummaryView&topId=${topId }" style="padding: 2px; overflow: hidden;"></div>

<div class="easyui-tab" title="教案管理" href="lessonPlanController.do?lessonViewPlan&topId=${topId }" style="padding: 2px; overflow: hidden;"></div>
<div class="easyui-tab" title="教学反思" href="researchReflectController.do?researchReflectView&topId=${topId }" style="padding: 2px; overflow: hidden;"></div>
<div class="easyui-tab" title="教学随笔" href="researchEssayController.do?researchEssayView&topId=${topId }" style="padding: 2px; overflow: hidden;"></div>

<%-- <div class="easyui-tab" title="活动管理" href="researchActivityController.do?researchActivityView&topId=${topId }" style="padding: 2px; overflow: hidden;"></div> --%>
<%-- <div class="easyui-tab" title="案例管理" href="researchCaseController.do?researchCaseView&topId=${topId }" style="padding: 2px; overflow: hidden;"></div> --%>
<%-- <div class="easyui-tab" title="研究活动" href="studyActivityController.do?studyActivityView&topId=${topId }" style="padding: 2px; overflow: hidden;"></div> --%>
<%-- <div class="easyui-tab" title="交流活动" href="interflowController.do?interflowView&topId=${topId }" style="padding: 2px; overflow: hidden;"></div> --%>



</div>

               </body>
</html>