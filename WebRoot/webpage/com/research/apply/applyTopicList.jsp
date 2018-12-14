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
  <t:datagrid name="applyTopicList" checkbox="false" fitColumns="false" title="课题申报" actionUrl="applyTopicController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="课题名称"  field="subjectName"  hidden="true"  queryMode="single"  width="520"></t:dgCol>
   <t:dgCol title="学科分类"  field="subjectType"  hidden="false" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="课题负责人"  field="subjectLeading"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预期成果"  field="expectedResults"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预计完成时间"  field="completionTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="联系电话"  field="contactPhone"  hidden="true"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="申报文件"  field="applyFileDoc"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status" replace="草稿_0,学校审核中_1,学校退回_2,实施研究_3,结题审核中_4,通过结题_5,未通过结题_6"  hidden="true"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="创建人"  field="createUser"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建单位"  field="createOrg"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="220" hidden="true"></t:dgCol>
   <t:dgFunOpt funname="updatewindow(id)" title="修改" exp="status#eq#0" ></t:dgFunOpt>
   <t:dgFunOpt funname="confirmAudit(id)" title="提交审核" exp="status#eq#0"></t:dgFunOpt>
   <t:dgDelOpt title="删除" url="applyTopicController.do?doDel&id={id}" exp="status#eq#0"/>
   
   <t:dgFunOpt funname="updatewindow(id)" title="修改" exp="status#eq#2" ></t:dgFunOpt>
   <t:dgFunOpt funname="confirmAudit(id)" title="提交审核" exp="status#eq#2"></t:dgFunOpt>
   <t:dgDelOpt title="删除" url="applyTopicController.do?doDel&id={id}" exp="status#eq#2"/>
   
   <t:dgFunOpt funname="viewwindow(id)" title="阅读申报资料" exp="status#eq#3" ></t:dgFunOpt>
   <t:dgFunOpt funname="applyTopicMain(id)" title="课题研究" exp="status#eq#3" ></t:dgFunOpt>
<%--    <t:dgFunOpt funname="processPlan(id)" title="工作计划" exp="status#eq#3" ></t:dgFunOpt> --%>
<%--    <t:dgFunOpt funname="seeResources(id)" title="参阅资料" exp="status#eq#3" ></t:dgFunOpt> --%>
<%--    <t:dgFunOpt funname="researchCase(id)" title="教案管理" exp="status#eq#3" ></t:dgFunOpt> --%>
<%--    <t:dgFunOpt funname="researchActivity(id)" title="活动管理" exp="status#eq#3" ></t:dgFunOpt> --%>
<%--    <t:dgFunOpt funname="researchCase(id)" title="案例管理" exp="status#eq#3" ></t:dgFunOpt> --%>
<%--    <t:dgFunOpt funname="seeResources(id)" title="阶段性总结" exp="status#eq#3" ></t:dgFunOpt> --%>
<%--    <t:dgFunOpt funname="researchReflect(id)" title="研究反思" exp="status#eq#3" ></t:dgFunOpt> --%>
<%--    <t:dgFunOpt funname="researchEssay(id)" title="教学随笔" exp="status#eq#3" ></t:dgFunOpt> --%>
	<t:dgFunOpt funname="applyTopicMainView(id)" title="查看资料" exp="status#eq#5" ></t:dgFunOpt>
	<t:dgFunOpt funname="applyTopicMainView(id)" title="查看资料" exp="status#eq#6" ></t:dgFunOpt>
   <t:dgFunOpt funname="viewOpinionWindow(id)" title="查看意见" ></t:dgFunOpt>
   <t:dgDelOpt title="删除" url="applyTopicController.do?doDel&id={id}" exp="status#eq#5"/>
   <t:dgDelOpt title="删除" url="applyTopicController.do?doDel&id={id}" exp="status#eq#6"/>
   <t:dgToolBar title="申报课题" icon="icon-add" url="applyTopicController.do?goAdd" funname="add" width="1000" height="450"></t:dgToolBar>
<%--    <t:dgToolBar title="修改申报课题" icon="icon-edit" url="applyTopicController.do?goUpdate" funname="update"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="批量删除"  icon="icon-remove" url="applyTopicController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="查看" icon="icon-search" url="applyTopicController.do?goUpdate" funname="detail"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/school/apply/applyTopicList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
	function updatewindow(id) {
		$.dialog({
			content: 'url:applyTopicController.do?goUpdate&id='+id,
			lock : true,
			width:1000,
			height:450,
			title:"课题申报表",
			opacity : 0.3,
			cache:false,
			cancelVal : '关闭',
			cancel : true, /*为true等价于function(){}*/
			button : [ {
				name : '修改',
				callback : function() {
					iframe = this.iframe.contentWindow;
					saveObj();
					return false;
				},
				focus : true
			} ]
		});
	}
	function viewwindow(id) {
		$.dialog({
			content: 'url:applyTopicController.do?goViewNoAudit&id='+id,
			lock : true,
			width:1000,
			height:450,
			title:"课题申报表",
			opacity : 0.3,
			cache:false,
			cancelVal : '关闭',
			cancel : true, /*为true等价于function(){}*/
			button : []
		});
	}
	function viewOpinionWindow(id) {
		$.dialog({
			content: 'url:auditOpinionController.do?auditOpinion&id='+id,
			lock : true,
			width:1000,
			height:450,
			title:"意见表",
			opacity : 0.3,
			cache:false,
			cancelVal : '关闭',
			cancel : true, /*为true等价于function(){}*/
			button : []
		});
	}
	function confirmAudit(id){
	    	$.dialog.confirm('您确定要提交学校审核？', function(r) {
	 		   if (r) {
	 				$.ajax({   
	 				    url:'applyTopicController.do?doUpdate&id='+id,   
	 				    type:'post',   
	 				    data:'status=1',   
	 				    async : false, //默认为true 异步   
	 				    error:function(){   
	 				       alert('error');   
	 				    },   
	 				    success:function(data){
	 				    	location.reload();
	 				    }
	 				});	
	 			}
	 		});
	}
	function applyTopicMain(id) {
		location.href='applyTopicController.do?applyTopicMain&topId='+id;
	}
	function applyTopicMainView(id) {
		location.href='applyTopicController.do?applyTopicMainView&topId='+id;
	}
	function processPlan(id) {
		location.href='processPlanController.do?processPlan&topId='+id;
	}
	function researchCase(id) {
		location.href='researchCaseController.do?researchCase&topId='+id;
	}
	function researchActivity(id) {
		location.href='researchActivityController.do?researchActivity&topId='+id;
	}
	function researchCase(id) {
		location.href='researchCaseController.do?researchCase&topId='+id;
	}
	function seeResources(id) {
		location.href='seeResourcesController.do?seeResources&topId='+id;
	}
	function seeResources(id) {
		location.href='stageSummaryController.do?stageSummary&topId='+id;
	}
	function researchReflect(id) {
		location.href='researchReflectController.do?researchReflect&topId='+id;
	}
	function researchEssay(id) {
		location.href='researchEssayController.do?researchEssay&topId='+id;
	}
 </script>
          </body>
</html>