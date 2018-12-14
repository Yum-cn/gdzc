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
  <t:datagrid name="certificateRegisterList" checkbox="false" fitColumns="false" title="获奖审核" actionUrl="certificateRegisterController.do?datagrid&stauts=1" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="获奖人/单位"  field="name"  hidden="true"  queryMode="single"  width="220"></t:dgCol>
   <t:dgCol title="等级"  field="grade" dictionary="ZSDJ" hidden="true"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="类型"  field="type" dictionary="HJLX" hidden="true"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="奖品"  field="prizeName" dictionary="JPMC" hidden="true"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="获奖时间"  field="awardTime" formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="颁发单位"  field="issuingUnit"  hidden="true"  queryMode="single"  width="220"></t:dgCol>
   <t:dgCol title="状态"  field="stauts"  hidden="true"  queryMode="single" replace="草稿_0,审核中_1,审核通过_2,审核未通过_3" width="60"></t:dgCol>
<%--    <t:dgCol title="创建人"  field="createUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="创建时间"  field="createTime"  hidden="true" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
<%--    <t:dgCol title="创建单位"  field="createOrg"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="操作" field="opt" width="100" hidden="true"></t:dgCol>
   <t:dgFunOpt funname="goViewWindow(id)" title="审核"></t:dgFunOpt>

<%--    <t:dgToolBar title="批量删除"  icon="icon-remove" url="certificateRegisterController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="查看" icon="icon-search" url="certificateRegisterController.do?goUpdate" funname="detail"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
	function goViewWindow(id) {
		$.dialog({
			content: 'url:certificateRegisterController.do?goView&id='+id,
			lock : true,
			width:800,
			height:380,
			title:"审核",
			opacity : 0.3,
			cache:false,
			cancelVal : '关闭',
			cancel : true, /*为true等价于function(){}*/
			button : [ {
				name : '通过',
				callback : function() {
					iframe = this.iframe.contentWindow;
					acceptAudit(id,iframe);
					return false;
				},
				focus : true
			},{
				name : '退回',
				callback : function() {
					iframe = this.iframe.contentWindow;
					returnAudit(id,iframe);
					return false;
				},
				focus : false
			} ]
		});
	}
	function acceptAudit(id,iframe){
    	$.dialog.confirm('您确定通过这个证书登记吗？', function(r) {
 		   if (r) {
 			  $('#status', iframe.document).val("0");
 				$.ajax({   
 				    url:'certificateRegisterController.do?doUpdate&id='+id,   
 				    type:'post',   
 				    data:'stauts=2',   
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
function returnAudit(id,iframe){
	$.dialog.confirm('您确定不通过这个证书登记吗？', function(r) {
		   if (r) {
			  $('#status', iframe.document).val("1");
				$.ajax({   
				    url:'certificateRegisterController.do?doUpdate&id='+id,   
				    type:'post',   
				    data:'stauts=3',   
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
 </script>
              </body>
</html>