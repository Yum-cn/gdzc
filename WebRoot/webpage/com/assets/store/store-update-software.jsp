<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<t:base type="jquery,tools,DatePicker"></t:base>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/Validform_v5.3.2/css/style.css" type="text/css" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/Validform_v5.3.2/js/Validform_v5.3.2_min.js"></script>
<link href="${pageContext.request.contextPath}/plug-in/frame/default/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>
<style>
.from-label-title{font-size:14px;text-align: right;padding-right:16px;}
</style>
<script>
function uploadTemplateCallBack(url,name){
//   	var point = url.lastIndexOf(".");
//   	var type = url.substr(point);
//   	if(type==".doc" || type==".docx"){
		$("#template_href").attr('href',url);
		$("#template_href").html(name);
		$("#contractPath").val(url);
		$("#contractName").val(name);
//   	}else{
//   		alert("模板文件格式不正确");
//   	}
}
</script>
</head>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">基本信息</li>
  </ul>
  <div class="layui-tab-content" >
    <div class="layui-tab-item layui-show">
<form name="form1" id="form1" method="post" action="storeController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${storePage.id }">
					<input id="createName" name="createName" type="hidden" value="${storePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${storePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${storePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${storePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${storePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${storePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${storePage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${storePage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${storePage.bpmStatus }">
                    <input name="category" id="category" type="hidden" value="${storePage.category}">
<div class="formbody">

  <ul class="forminfo">
            <li>
              <label class="from-label-title">组别:</label>
              <t:dictSelect field="groupTypeCode" type="list"
                                        typeGroupCode="groupType" defaultVal="${storePage.groupTypeCode}" hasLabel="false"  title="组别" datatype="*"></t:dictSelect>
              <i class="Validform_checktip"></i>
              <label class="Validform_label" style="display: none;">组别</label>
            </li>
            <li>
              <label class="from-label-title">所属网络:</label>
               <t:dictSelect field="netTypeCode" type="list"
                                        typeGroupCode="netType" defaultVal="${storePage.netTypeCode}" hasLabel="false"  title="所属网络" datatype="*"></t:dictSelect>
              <i class="Validform_checktip"></i>
              <label class="Validform_label" style="display: none;">所属网络</label>
            </li>
            <li>
              <label class="from-label-title">所属项目:</label>
              <t:dictSelect field="proTypeCode" type="list"
                                        typeGroupCode="proType" defaultVal="${storePage.proTypeCode}" hasLabel="false"  title="所属项目" datatype="*"></t:dictSelect>
              <i class="Validform_checktip"></i>
              <label class="Validform_label" style="display: none;">所属项目</label>
            </li>
            <li>
              <label class="from-label-title">应用名称:</label>
                <input id="name" name="name" type="text" class="dfinput" value="${storePage.name}" datatype="*"/>
              <i class="Validform_checktip"></i>
              <label class="Validform_label" style="display: none;">应用名称</label>
            </li>           
            <li>
              <label class="from-label-title">资产编码:</label>
                <input id="code" name="code" type="text" class="dfinput" value="${storePage.code}" datatype="*"/>
              <i class="Validform_checktip"></i>
              <label class="Validform_label" style="display: none;">资产编码</label>
            </li>
            <li>
             <label class="from-label-title">资产类别:</label>
               <label class="from-label-title"><t:listDictParse parseId="${storePage.type}" style="1" typecode="zclb"></t:listDictParse></label>
               <input name="type" id="type" type="hidden" value="${storePage.type}">
           </li>
            <li>
              <label class="from-label-title">版本:</label>
                <input id="version" name="version" type="text" class="dfinput"  value="${storePage.version}"/>
              <i class="Validform_checktip"></i>
            </li>           
            <li>
              <label class="from-label-title">厂商:</label>
              <t:dictSelect field="manufacturer" type="list"
                                        typeGroupCode="mfrs" defaultVal="${storePage.manufacturer}" hasLabel="false"  title="所属项目"></t:dictSelect>
              <i class="Validform_checktip"></i>
            </li>               
            <li>
              <label class="from-label-title">供应商:</label>
                <input id="supplier" name="supplier" type="text" class="dfinput" value="${storePage.supplier}"/>
              <i class="Validform_checktip"></i>
            </li>
            <li>
             <label class="from-label-title">入库时间:</label>
               <input id="storageTime" name="storageTime" type="text" class="Wdate" onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'})" value="${storePage.storageTime}" datatype="*"/>
             <i class="Validform_checktip"></i>
             <label class="Validform_label" style="display: none;">入库时间</label>
            </li>               
            <li>
              <label class="from-label-title">部署时间:</label>
                <input id="deployTime" name="deployTime" type="text"  value="${storePage.deployTime}" class="Wdate" onClick="WdatePicker()"/>
              <i class="Validform_checktip"></i>
            </li>           
            <li>
              <label class="from-label-title">购买时间:</label>
                <input id="payTime" name="payTime" type="text"  value="${storePage.payTime}" class="Wdate" onClick="WdatePicker()" datatype="*"/>
              <i class="Validform_checktip"></i>
              <label class="Validform_label" style="display: none;">购买时间</label>
            </li>           
            <li>
              <label class="from-label-title">维保到期时间:</label>
                <input id="repairEndTime" name="repairEndTime"  value="${storePage.repairEndTime}" type="text" class="Wdate" onClick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss' })" datatype="*"/>
              <i class="Validform_checktip"></i>
              <label class="Validform_label" style="display: none;">维保到期时间</label>
            </li>           
            <li>
              <label class="from-label-title">应用价格:</label>
                <input id="code" name="amount" type="text"  value="${storePage.amount}" class="dfinput" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="不能为空" errormsg="必须为数字，可以有小数"/>
<!--                 <input type="text" class="input-text" value="" placeholder="请输入**" id="title" name="testtt" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="不能为空" errormsg="必须为数字，可以有小数">
 -->                
              <i class="Validform_checktip"></i>
              <label class="Validform_label" style="display: none;">应用价格</label>
            </li>
				<li>
					<label class="from-label-title">
							合同文件:
					</label>
							<input id="contractPath" name="contractPath" type="hidden" value="${storePage.contractPath }" style="width: 150px" class="inputxt">
							<input id="contractName" name="contractName" type="hidden" value="${storePage.contractName }" style="width: 150px" class="inputxt">
							<c:if test="${storePage.contractPath ne ''}">
							<a id="template_href" style="width:180px;" href="${storePage.contractPath }">${storePage.contractName }</a>
							</c:if>
							<c:if test="${storePage.contractPath eq ''}">
							<a id="template_href" style="width:180px;">暂时未上传合同文件</a>
							</c:if>
							<input class="ui-button" type="button" value="上传合同文件" onclick="commonUpload(uploadTemplateCallBack);"/>   
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同文件</label>
				</li>    
<!--     <li> -->
<!-- 		<label class="from-label-title">数量:</label> -->
<%-- 		<input id="number" name="number" type="text" class="dfinput" value="${storePage.number }" datatype="*"/> --%>
<!-- 		<i class="Validform_checktip"></i> -->
<!-- 	</li>	 -->
<!--     <li> -->
<!--       <label class="from-label-title">预计报废时间:</label> -->
<%--       	<input id="discardedTime" name="discardedTime" type="text" value="<fmt:formatDate value="${storePage.discardedTime }" pattern="yyyy-MM-dd"/>" class="dfinput" class="Wdate" onClick="WdatePicker()" datatype="*"/> --%>
<!--       <i class="Validform_checktip"></i> -->
<!--     </li> -->
    <li>
      <label class="from-label-title">备注:</label>
      	<textarea id="remark" name="remark" cols="" rows="" class="textinput" >${storePage.remark }</textarea>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label>&nbsp;</label>
      <input name="saveForm" id="saveForm" type="button" class="layui-btn" lay-submit="" lay-filter="demo1" value="确认保存"/>
    </li>
  </ul>
</div>
</form>

	</div>
  </div>
</div> 
</body>
</html>
<script type="text/javascript">
$(function(){
	$("#form1").Validform({
		tiptype:3
	});
	$("#saveForm").click(function(){
    	$("#form1").submit();
  	});
})
</script>

