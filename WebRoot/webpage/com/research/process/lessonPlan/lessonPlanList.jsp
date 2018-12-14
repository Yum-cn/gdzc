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
  <t:datagrid name="lessonPlanList" checkbox="true" fitColumns="false" title="" actionUrl="lessonPlanController.do?datagrid&topId=${topId }" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="教案名称"  field="title"  hidden="true"  queryMode="single"  width="360"></t:dgCol>
   <t:dgCol title="作者"  field="author"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
<%--    <t:dgCol title="教案路径"  field="fileDocPath"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
<%--    <t:dgCol title="教案描述"  field="comment"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
<%--    <t:dgCol title="创建人"  field="createUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
<%--    <t:dgCol title="创建单位"  field="createOrg"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
      <t:dgCol title="课题名称"  field="topId"  hidden="true" dictionary="st_apply_topic,id,subject_name" queryMode="single" query="true"></t:dgCol>
   
   <t:dgCol title="创建时间"  field="createTime"  hidden="true" formatter="yyyy-MM-dd" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="120" hidden="true"></t:dgCol>
   <t:dgDelOpt title="删除" url="lessonPlanController.do?doDel&id={id}" />
   <t:dgToolBar title="创建教案" icon="icon-add" url="lessonPlanController.do?goAdd&topId=${topId }" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑教案" icon="icon-edit" url="lessonPlanController.do?goUpdate" funname="update"></t:dgToolBar>
      <t:dgFunOpt funname="viewplantwindow(id)" title="阅读"></t:dgFunOpt>
<%--    <t:dgToolBar title="批量删除"  icon="icon-remove" url="lessonPlanController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="查看" icon="icon-search" url="lessonPlanController.do?goUpdate" funname="detail"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
	function viewplantwindow(id) {
		$.dialog({
			content: 'url:lessonPlanController.do?goView&id='+id,
			lock : true,
			width:800,
			height:380,
			title:"阅读",
			opacity : 0.3,
			cache:false,
			cancelVal : '关闭',
			cancel : true, /*为true等价于function(){}*/
			button : []
		});
	}
 </script>
             </body>
</html>