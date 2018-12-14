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
  <t:datagrid name="applyTopicList" checkbox="false" fitColumns="false" title="审核申报" actionUrl="applyTopicController.do?auditdatagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="课题名称"  field="subjectName"  hidden="true"  queryMode="single"  width="520"></t:dgCol>
   <t:dgCol title="学科分类"  field="subjectType"  hidden="false" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="课题负责人"  field="subjectLeading"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预期成果"  field="expectedResults"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预计完成时间"  field="completionTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="联系电话"  field="contactPhone"  hidden="true"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="申报文件"  field="applyFileDoc"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status" replace="草稿_0,学校审核中_1"  hidden="true"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="创建人"  field="createUser"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建单位"  field="createOrg"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" hidden="true" width="150"></t:dgCol>
   <t:dgFunOpt funname="goViewWindow(id)" title="阅读申报表"></t:dgFunOpt>
   
<%--    <t:dgDelOpt title="删除" url="applyTopicController.do?doDel&id={id}" exp="status#eq#0"/> --%>
<%--    <t:dgToolBar title="申报课题" icon="icon-add" url="applyTopicController.do?goAdd" funname="add"></t:dgToolBar> --%>
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
	function goViewWindow(id) {
		$.dialog({
			content: 'url:applyTopicController.do?goView&id='+id,
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
	    	$.dialog.confirm('您确定这个课题进入到研究阶段？', function(r) {
	    	iframe.document.getElementById('status').value="0";
	    	iframe.document.getElementById('formobj').submit();
	 		   if (r) {
	 			  $('#status', iframe.document).val("0");
	 				$.ajax({   
	 				    url:'applyTopicController.do?doUpdate&id='+id,   
	 				    type:'post',   
	 				    data:'status=3',   
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
    	$.dialog.confirm('您确定这个课题需要退回给个人？', function(r) {
    		iframe.document.getElementById('status').value="1";
    		iframe.document.getElementById('formobj').submit();
    		
 		   if (r) {
 			  $('#status', iframe.document).val("1");
 				$.ajax({   
 				    url:'applyTopicController.do?doUpdate&id='+id,   
 				    type:'post',   
 				    data:'status=2',   
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