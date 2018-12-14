<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="certificateRegisterList" checkbox="false" fitColumns="false" title="" actionUrl="certificateRegisterController.do?datagrid&funType=1" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="获奖人/单位"  field="name"  hidden="true"  queryMode="single" query="true" width="220"></t:dgCol>
   <t:dgCol title="等级"  field="grade" dictionary="ZSDJ" hidden="true" query="true" queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="类型"  field="type" dictionary="HJLX" hidden="true" query="true" queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="奖品"  field="prizeName" dictionary="JPMC" hidden="true" query="true" queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="获奖时间"  field="awardTime" formatter="yyyy-MM-dd"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="颁发单位"  field="issuingUnit"  hidden="true"  queryMode="single" query="true" width="220"></t:dgCol>
   <t:dgCol title="状态"  field="stauts"  hidden="true"  queryMode="single" replace="草稿_0,审核中_1,审核通过_2,审核未通过_3" width="80"></t:dgCol>
<%--    <t:dgCol title="创建人"  field="createUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="创建时间"  field="createTime"  hidden="true" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
<%--    <t:dgCol title="创建单位"  field="createOrg"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="操作" field="opt" width="160" hidden="true"></t:dgCol>
   <t:dgFunOpt funname="confirmAudit(id)" title="提交审核" exp="stauts#eq#0"></t:dgFunOpt>
   
   <t:dgFunOpt funname="updatewindow(id)" title="修改" exp="stauts#eq#0" ></t:dgFunOpt>
   <t:dgFunOpt funname="updatewindow(id)" title="修改" exp="stauts#eq#3" ></t:dgFunOpt>
   
   <t:dgDelOpt title="删除" url="certificateRegisterController.do?doDel&id={id}" exp="stauts#eq#0"/>
   <t:dgDelOpt title="删除" url="certificateRegisterController.do?doDel&id={id}" exp="stauts#eq#3"/>
   
   <t:dgFunOpt funname="goViewWindow(id)" title="查看" exp="stauts#eq#2"></t:dgFunOpt>

   
   
   <t:dgToolBar title="登记证书" icon="icon-add" url="certificateRegisterController.do?goAdd&funType=1" funname="add"></t:dgToolBar>
<%--    <t:dgToolBar title="编辑证书" icon="icon-edit" url="certificateRegisterController.do?goUpdate" funname="update"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="批量删除"  icon="icon-remove" url="certificateRegisterController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="查看" icon="icon-search" url="certificateRegisterController.do?goUpdate" funname="detail"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 		$("#certificateRegisterListtb").find("input[name='awardTime']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
	function goViewWindow(id) {
		$.dialog({
			content: 'url:certificateRegisterController.do?goView&id='+id,
			lock : true,
			width:800,
			height:380,
			title:"查看",
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
 				    url:'certificateRegisterController.do?doUpdate&id='+id,   
 				    type:'post',   
 				    data:'stauts=1',   
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
	function updatewindow(id) {
		$.dialog({
			content: 'url:certificateRegisterController.do?goUpdate&id='+id,
			lock : true,
			width:800,
			height:380,
			title:"编辑",
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
 </script>
              </body>
</html>