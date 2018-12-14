<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>确认列表</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body scroll="no">
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="questionLibraryList" checkbox="true" fitColumns="false" title="题库主表信息" actionUrl="questionLibraryController.do?selectDatagrid&qid=${qid}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="题库大类"  field="type"  hidden="true" query="true" queryMode="single" dictionary="TIKUDALEI" width="120"></t:dgCol>
   <t:dgCol title="题库小类"  field="smallType"  hidden="true" query="true" queryMode="single" dictionary="TIKUXIAOL" width="120"></t:dgCol>
   <t:dgCol title="试题类型"  field="style"  hidden="true" query="true" queryMode="single" dictionary="TKXXLX" width="80"></t:dgCol>
   <t:dgCol title="题目标题"  field="title"  hidden="true" query="true" queryMode="single"  ></t:dgCol>
   <t:dgCol title="试题创建人"  field="createuser"  hidden="true" dictionary="t_s_base_user,id,realname" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime" formatter="yyyy-MM-dd hh:mm:ss" hidden="true"  queryMode="group"  width="130"></t:dgCol>

<%--    <t:dgToolBar title="查看" icon="icon-search" url="questionLibraryController.do?goUpdate" funname="detail"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/train/questionlibrary/questionLibraryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#questionLibraryListtb").find("input[name='createTime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#questionLibraryListtb").find("input[name='createTime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 </script>
    </body>
</html>