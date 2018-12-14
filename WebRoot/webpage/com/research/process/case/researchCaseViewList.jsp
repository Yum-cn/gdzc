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
  <t:datagrid name="researchCaseList" checkbox="true" fitColumns="false" title="" actionUrl="researchCaseController.do?datagrid&topId=${topId }" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="案例名称"  field="title"  hidden="true"  queryMode="single"  width="390"></t:dgCol>
<%--    <t:dgCol title="摘要"  field="abstract"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
<t:dgCol title="年级"  field="grade" dictionary="nianji" hidden="true"  queryMode="single"  width="80"></t:dgCol>
<t:dgCol title="课程"  field="course"  hidden="true"  queryMode="single"  width="80"></t:dgCol>
<t:dgCol title="册"  field="book"  hidden="true"  queryMode="single"  width="80"></t:dgCol>
<t:dgCol title="章节"  field="chapter"  hidden="true"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="关键词"  field="keyword"  hidden="true"  queryMode="single"  width="160"></t:dgCol>
<%--    <t:dgCol title="引用网址"  field="referenceUrl"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="原作者"  field="author"  hidden="true"  queryMode="single"  width="100"></t:dgCol>
<%--    <t:dgCol title="正文"  field="content"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
<%--    <t:dgCol title="创建人"  field="createUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
<%--    <t:dgCol title="创建单位"  field="createOrg"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="创建时间"  field="createTime"  hidden="true" formatter="yyyy-MM-dd" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="60" hidden="true"></t:dgCol>
<t:dgFunOpt funname="addplantwindow(id)" title="阅读"></t:dgFunOpt>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
	function addplantwindow(id) {
		$.dialog({
			content: 'url:researchCaseController.do?goView&id='+id,
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