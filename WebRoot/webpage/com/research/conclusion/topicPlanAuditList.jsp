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
  <t:datagrid name="applyTopicList" checkbox="false" fitColumns="false" title="结题审核" actionUrl="applyTopicController.do?conclusionAuditDatagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="课题名称"  field="subjectName"  hidden="true"  queryMode="single"  width="520"></t:dgCol>
   <t:dgCol title="学科分类"  field="subjectType"  hidden="false" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="课题负责人"  field="subjectLeading"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预期成果"  field="expectedResults"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预计完成时间"  field="completionTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="联系电话"  field="contactPhone"  hidden="true"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="申报文件"  field="applyFileDoc"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
<%--    <t:dgCol title="状态"  field="status" replace="草稿_0,学校审核中_1,学校退回_2,实施研究_3,结题审核中_4"  hidden="true"  queryMode="single"  width="100"></t:dgCol> --%>
   <t:dgCol title="创建人"  field="createUser"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建单位"  field="createOrg"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="190"></t:dgCol>
   <t:dgFunOpt funname="plantwindow(id)" title="阅读结题申请表"></t:dgFunOpt>
   <t:dgFunOpt funname="researchDataFile(id)" title="阅读研究资料"></t:dgFunOpt>
<%--    <t:dgFunOpt funname="confirmAudit(id)" title="提交结题审核"></t:dgFunOpt> --%>
  </t:datagrid>
  </div>
 </div>

 <script src = "webpage/com/school/process/processPlanList.js"></script>		
 <script type="text/javascript">
	 $(document).ready(function(){
	 		//给时间控件加上样式
	 });
	function plantwindow(id) {
		$.dialog({
			content: 'url:conclusionController.do?goView&topId='+id,
			lock : true,
			width:800,
			height:380,
			title:"结题申请表",
			opacity : 0.3,
			cache:false,
			cancelVal : '关闭',
			cancel : true, /*为true等价于function(){}*/
			button : [ {
				name : '同意结题',
				callback : function() {
					iframe = this.iframe.contentWindow;
					//iframe.document.getElementsById("topId").value
						    	iframe.document.getElementById('status').value="0";
	    	//iframe.document.getElementById('formobj').submit();
					saveObj();
					return false;
				},
				focus : true
			},{
				name : '不同意结题',
				callback : function() {
					iframe = this.iframe.contentWindow;
			   		iframe.document.getElementById('status').value="1";
		    		//iframe.document.getElementById('formobj').submit();
					saveObj();
					return false;
				},
				focus : false
			}]
		});
	}
	function researchDataFile(id) {
		location.href='conclusionController.do?researchDataFile&topId='+id;
	}
	function confirmAudit(id){
    	$.dialog.confirm('您确定要提交学校进行结题审核？', function(r) {
 		   if (r) {
 				$.ajax({   
 				    url:'applyTopicController.do?doUpdate&id='+id,   
 				    type:'post',   
 				    data:'status=4',   
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