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
  <t:datagrid name="observationRecordList" checkbox="false" fitColumns="false" title="" actionUrl="observationRecordController.do?datagrid&topId=${topId }" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="记录标题"  field="title"  hidden="true"  queryMode="single"  width="320"></t:dgCol>
      <t:dgCol title="课题名称"  field="topId"  hidden="true" dictionary="st_apply_topic,id,subject_name" queryMode="single" query="true"></t:dgCol>
   
<%--    <t:dgCol title="观察描述"  field="content"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="创建时间"  field="createTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
<%--    <t:dgCol title="创建单位"  field="createOrg"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
<%--    <t:dgCol title="创建人"  field="createUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="操作" field="opt" width="120" hidden="true"></t:dgCol>
   <t:dgDelOpt title="删除" url="observationRecordController.do?doDel&id={id}" />
   <t:dgToolBar title="录入研究记录" icon="icon-add" url="observationRecordController.do?goAdd&topId=${topId }" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑研究记录" icon="icon-edit" url="observationRecordController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgFunOpt funname="viewplantwindow(id)" title="阅读"></t:dgFunOpt>

  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#observationRecordListtb").find("input[name='createTime']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
	function viewplantwindow(id) {
		$.dialog({
			content: 'url:observationRecordController.do?goView&id='+id,
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