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
  <t:datagrid name="seeResourcesList" checkbox="false" fitColumns="false" title="" actionUrl="seeResourcesController.do?datagrid&topId=${topId }" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="摘记日期"  field="noteDate"  hidden="true" formatter="yyyy-MM-dd" queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="资料名称"  field="title"  hidden="true"  queryMode="single"  width="280"></t:dgCol>
   <t:dgCol title="类型"  field="type" replace="书籍_0,网络_1" hidden="true"  queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="作者"  field="author"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="出处"  field="source"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
<%--    <t:dgCol title="创建人"  field="createUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
<%--    <t:dgCol title="创建单位"  field="createOrg"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="创建时间"  field="createDate"  hidden="true" formatter="yyyy-MM-dd" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100" hidden="true"></t:dgCol>
   <t:dgDelOpt title="删除" url="seeResourcesController.do?doDel&id={id}" />
   <t:dgToolBar title="录入参阅资料" icon="icon-add" url="seeResourcesController.do?goAdd&topId=${topId }" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑参阅资料" icon="icon-edit" url="seeResourcesController.do?goUpdate" funname="update"></t:dgToolBar>
<%--    <t:dgToolBar title="课标管理" icon="icon-edit" funname="jxdg"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
	function jxdg() {
		$.dialog({
			content: 'url:teachingProgramController.do?teachingProgramView',
			lock : true,
			width:800,
			height:380,
			title:"教学大纲",
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