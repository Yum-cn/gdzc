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
/* .from-label-title{font-size:14px;text-align: right;padding-right:16px;width:2100px;} */
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
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
		<form name="form1" id="form1" method="post" action="storeController.do?doAdd">
		<div class="formbody">
		  <ul class="forminfo">
		  	 <li>
		      <label class="from-label-title">组别:</label>
			  <t:dictSelect field="groupTypeCode" type="list"
                                        typeGroupCode="groupType" defaultVal="" hasLabel="false"  title="组别" datatype="*"></t:dictSelect>
		      <i class="Validform_checktip"></i>
		      <label class="Validform_label" style="display: none;">组别</label>
		    </li>
		    <li>
		      <label class="from-label-title">所属网络:</label>
			   <t:dictSelect field="netTypeCode" type="list"
                                        typeGroupCode="netType" defaultVal="" hasLabel="false"  title="所属网络" datatype="*"></t:dictSelect>
		      <i class="Validform_checktip"></i>
		      <label class="Validform_label" style="display: none;">所属网络</label>
		    </li>
		    <li>
		      <label class="from-label-title">所属项目:</label>
			  <!-- <select name="select">
				<option value="1">XXXXXX建设项目</option>	
			  </select> -->
			  <t:dictSelect field="proTypeCode" type="list"
                                        typeGroupCode="proType" defaultVal="" hasLabel="false"  title="所属项目" datatype="*"></t:dictSelect>
		      <i class="Validform_checktip"></i>
		      <label class="Validform_label" style="display: none;">所属项目</label>
		    </li>		    
		    <li>
		      <label class="from-label-title">设备名称:</label>
		      	<input id="code" name="name" type="text" class="dfinput" datatype="*"/>
		      <i class="Validform_checktip"></i>
		      <label class="Validform_label" style="display: none;">设备名称</label>
		    </li>	    
		    <li>
		      <label class="from-label-title">部署软件清单:</label>
		      	<input name="category" id="category" type="hidden" value="${storePage.category}">
		      	<input name="softListIds" id="softListIds" type="hidden" value="">
                <input name="softListNames" class="dfinput" style="vertical-align: top; " value="" id="relationName" datatype="*" />
                <a href="#" class="layui-btn" plain="true" icon="icon-search" onClick="choose_402881f3635381d50163539e13530004()">选择</a>
                <a href="#" class="layui-btn" plain="true" icon="icon-redo" onClick="clearAll_402881f3635381d50163539e13530004();">清空</a>
		      <i class="Validform_checktip">部署软件清单</i>
		      <label class="Validform_label" style="display: none;">部署软件清单</label>
		    </li>	
		    <li>
		      <label class="from-label-title">资产编码:</label>
		      	<input id="code" name="code" type="text" class="dfinput" datatype="*"/>
		      <i class="Validform_checktip"></i>
		      <label class="Validform_label" style="display: none;">资产编码</label>
		    </li>	
		    <li>
             <label class="from-label-title">资产类别:</label>
               <t:dictSelect field="type" type="list"
               typeGroupCode="zclb" defaultVal="${storePage.type}" hasLabel="false"  title="资产类别" datatype="*"></t:dictSelect>  
             <i class="Validform_checktip"></i>
             <label class="Validform_label" style="display: none;">资产类别</label>
           </li>	    
		    <li>
		      <label class="from-label-title">品牌:</label>
		      <input id="code" name="brand" type="text" class="dfinput" datatype="*"/>
		      <i class="Validform_checktip"></i>
		      <label class="Validform_label" style="display: none;">品牌</label>
		    </li>			    
		    <li>
		      <label class="from-label-title">设备型号:</label>
		      <input id="code" name="deviceType" type="text" class="dfinput" />
		      <!-- <select name="select">
				<option value="1">NSG3000-TE15P</option>
				<option value="1">G1500-E005P</option>	
			  </select> -->
		      <i class="Validform_checktip"></i>
		    </li>			    
		    <li>
		      <label class="from-label-title">设备序列号:</label>
		      	<input id="code" name="deviceSN" type="text" class="dfinput" datatype="*" />
		      <i class="Validform_checktip"></i>
		      <label class="Validform_label" style="display: none;">设备序列号</label>
		    </li>
		    <li>
		      <label class="from-label-title">设备价格:</label>
		      	<input id="code" name="amount" type="text" class="dfinput" />
		      <i class="Validform_checktip"></i>
		      <label class="Validform_label" style="display: none;">设备价格</label>
		    </li>
		    <li>
              <label class="from-label-title">设备状态:</label>
              <t:dictSelect field="deviceStatusCode" type="list"
                                        typeGroupCode="deviceStatus" defaultVal="" hasLabel="false"  title="设备状态"  datatype="*" ></t:dictSelect>
              <i class="Validform_checktip"></i>
              <label class="Validform_label" style="display: none;">设备状态</label>
            </li>
            <li>
             <label class="from-label-title">入库时间:</label>
               <input id="storageTime" name="storageTime" type="text" class="Wdate" onClick="WdatePicker()" datatype="*"/>
             <i class="Validform_checktip"></i>
             <label class="Validform_label" style="display: none;">入库时间</label>
            </li>		    
		    <li>
		      <label class="from-label-title">CPU:</label>
		      	<input id="code" name="cpu" type="text" class="dfinput" />
		      <i class="Validform_checktip"></i>
		    </li>		    
		    <li>
		      <label class="from-label-title">内存:</label>
		      	<input id="code" name="memory" type="text" class="dfinput"  />
		      <i class="Validform_checktip"></i>
		    </li>		    
		    <li>
		      <label class="from-label-title">硬盘:</label>
		      	<input id="code" name="disk" type="text" class="dfinput" />
		      <i class="Validform_checktip"></i>
		    </li>		
		    <li>
		      <label class="from-label-title">责任单位:</label>
			  <t:dictSelect field="devicePartmentCode" type="list"
                                        typeGroupCode="zrdw" defaultVal="" hasLabel="false"  title="责任单位"></t:dictSelect>
		      <i class="Validform_checktip"></i>
		    </li>		
		    <li>
		      <label class="from-label-title">责任人:</label>
		      	<input id="code" name="devicePerson" type="text" class="dfinput"/>
		      <i class="Validform_checktip"></i>
		    </li>	
		    <li>
		      <label class="from-label-title">联系方式:</label>
		      	<input id="code" name="devicePhone" type="text" class="dfinput" />
		      <i class="Validform_checktip"></i>
		    </li>
		    <li>
		      <label class="from-label-title">安装地点:</label>
		      	<input id="code" name="installPlace" type="text" class="dfinput" />
		      <i class="Validform_checktip"></i>
		    </li>		
		    <li>
		      <label class="from-label-title">部署位置:</label>
		      	<input id="code" name="deployPlace" type="text" class="dfinput" />
		      <i class="Validform_checktip"></i>
		    </li>
		    <li>
              <label class="from-label-title">部署时间:</label>
                <input id="code" name="deployTime" type="text" class="Wdate" onClick="WdatePicker()"/>
              <i class="Validform_checktip"></i> 
            </li>	
		    <li>
		      <label class="from-label-title">购买时间:</label>
		      	<input id="payTime" name="payTime" type="text" class="Wdate" onClick="WdatePicker()"/>
		      <i class="Validform_checktip"></i> 
		    </li>	
		    <li>
		      <label class="from-label-title">维保到期时间(过保时间):</label>
		      	<input id="code" name="repairEndTime" type="text" class="Wdate"onClick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss' })" datatype="*"/>
		      <i class="Validform_checktip"></i>
		      <label class="Validform_label" style="display: none;">维保到期时间</label>
		    </li>		    		    		        			    	        
				<li>
					<label class="from-label-title">
							合同文件:
					</label>
							<input id="contractPath" name="contractPath" type="hidden" style="width: 150px" class="inputxt">
							<input id="contractName" name="contractName" type="hidden" style="width: 150px" class="inputxt">
							<a id="template_href" style="width:180px;">暂时未上传合同文件</a>
							<input class="ui-button" type="button" value="上传合同文件" onclick="commonUpload(uploadTemplateCallBack);"/>   
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同文件</label>
				</li>		    
		    <li>		    
		    
		    
		    
<!-- 		    <li> -->
<!-- 		      <label class="from-label-title">资产编码:</label> -->
<!-- 		      	<input id="code" name="code" type="text" class="dfinput" datatype="*"/> -->
<!-- 		      <i class="Validform_checktip"></i> -->
<!-- 		    </li> -->
<!-- 		    <li> -->
<!-- 		      <label class="from-label-title">资产类别:</label> -->
<%-- 				<t:dictSelect field="type" type="list" --%>
<%-- 				typeGroupCode="zclb" defaultVal="${storePage.type}" hasLabel="false"  title="资产类别"></t:dictSelect>   --%>
		
<!-- 		      <i class="Validform_checktip"></i> -->
<!-- 		    </li> -->
<!-- 		    <li> -->
<!-- 		      <label class="from-label-title">入库时间:</label> -->
<!-- 		      	<input id="storageTime" name="storageTime" type="text" class="dfinput" class="Wdate" onClick="WdatePicker()" datatype="*"/> -->
<!-- 		      <i class="Validform_checktip"></i> -->
<!-- 		    </li> -->
<!-- 		    <li> -->
<!-- 		      <label class="from-label-title">资产名称:</label> -->
<!-- 		      	<input id="name" name="name" type="text" class="dfinput" datatype="*"/> -->
<!-- 		      <i class="Validform_checktip"></i> -->
<!-- 		    </li> -->
<!-- 		    <li> -->
<!-- 		      <label class="from-label-title">来源:</label> -->
<!-- 		      	<input id="source" name="source" type="text" class="dfinput" datatype="*"/> -->
<%-- 		      	<t:dictSelect field="source" type="list" --%>
<%-- 				typeGroupCode="LY" defaultVal="${storePage.source}" hasLabel="false"  title="来源"></t:dictSelect> --%>
<!-- 		      <i class="Validform_checktip"></i> -->
<!-- 		    </li> -->
<!-- 		    <li> -->
<!-- 		      <label class="from-label-title">品牌:</label> -->
<!-- 		      	<input id="brand" name="brand" type="text" class="dfinput" datatype="*"/> -->
<!-- 		      <i class="Validform_checktip"></i> -->
<!-- 		    </li> -->
<!-- 		    <li> -->
<!-- 		      <label class="from-label-title">渠道:</label> -->
<!-- 		      	<input id="channel" name="channel" type="text" class="dfinput" datatype="*"/> -->
<!-- 		      <i class="Validform_checktip"></i> -->
<!-- 		    </li> -->
<!-- 		    <li> -->
<!-- 		      <label class="from-label-title">单价:</label> -->
<!-- 		      	<input id="amount" name="amount" type="text" class="dfinput" datatype="n"/> -->
<!-- 		      <i class="Validform_checktip"></i> -->
<!-- 		    </li> -->
<!-- 		    <li> -->
<!-- 		      <label class="from-label-title">数量:</label> -->
<!-- 		      	<input id="number" name="number" type="text" class="dfinput" datatype="*"/> -->
<!-- 		      <i class="Validform_checktip"></i> -->
<!-- 		    </li>		     -->
<!-- 		    <li> -->
<!-- 		      <label class="from-label-title">过保时间:</label> -->
<!-- 		      	<input id="overInsuranceTime" name="overInsuranceTime" type="text" class="dfinput" class="Wdate" onClick="WdatePicker()" datatype="*"/> -->
<!-- 		      <i class="Validform_checktip"></i> -->
<!-- 		    </li> -->
<!-- 				<li> -->
<!-- 					<label class="from-label-title"> -->
<!-- 							合同文件: -->
<!-- 					</label> -->
<!-- 							<input id="contractPath" name="contractPath" type="hidden" style="width: 150px" class="inputxt"> -->
<!-- 							<input id="contractName" name="contractName" type="hidden" style="width: 150px" class="inputxt"> -->
<!-- 							<a id="template_href" style="width:180px;">暂时未上传合同文件</a> -->
<!-- 							<input class="ui-button" type="button" value="上传合同文件" onclick="commonUpload(uploadTemplateCallBack);"/>    -->
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">合同文件</label> -->
<!-- 				</li>		     -->
<!-- 		    <li> -->
<!-- 		      <label class="from-label-title">预计报废时间:</label> -->
<!-- 		      	<input id="discardedTime" name="discardedTime" type="text" class="dfinput" class="Wdate" onClick="WdatePicker()" datatype="*"/> -->
<!-- 		      <i class="Validform_checktip"></i> -->
<!-- 		    </li> -->
		    <li>
		      <label class="from-label-title">备注:</label>
		      	<textarea id="remark" name="remark" cols="" rows="" class="textinput"></textarea>
		      <i class="Validform_checktip"></i>
		    </li>
		    <li>
		      <label>&nbsp;</label>
		      <button class="layui-btn" lay-submit="" lay-filter="demo1" name="saveForm" id="saveForm">确认保存</button>
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
var windowapi = frameElement.api, W = windowapi.opener;
    function choose_402881f3635381d50163539e13530004() {
        var url = 'storeController.do?selectList&status=ruku&category=software';
        var initValue = $('#roleid').val();
        url += '&ids=' + initValue;
        if (typeof (windowapi) == 'undefined') {
            $.dialog({
                content : 'url:' + url,
                zIndex : 2100,
                title : '资产列表',
                lock : true,
                width : 800,
                height : 350,
                left : '85%',
                top : '65%',
                opacity : 0.4,
                button : [ {
                    name : '确定',
                    callback : clickcallback_402881f3635381d50163539e13530004,
                    focus : true
                }, {
                    name : '取消',
                    callback : function() {
                    }
                } ]
            });
        } else {
            $.dialog({
                content : 'url:' + url,
                zIndex : 2100,
                title : '资产列表',
                lock : true,
                parent : windowapi,
                width : 800,
                height : 350,
                left : '85%',
                top : '65%',
                opacity : 0.4,
                button : [ {
                    name : '确定',
                    callback : clickcallback_402881f3635381d50163539e13530004,
                    focus : true
                }, {
                    name : '取消',
                    callback : function() {
                    }
                } ]
            });
        }
    }
    function clearAll_402881f3635381d50163539e13530004() {
        if ($('#relationName').length >= 1) {
            $('#relationName').val('');
            $('#relationName').blur();
        }
        if ($("input[name='relationName']").length >= 1) {
            $("input[name='relationName']").val('');
            $("input[name='relationName']").blur();
        }
        $('#relationId').val("");
    }
    function clickcallback_402881f3635381d50163539e13530004() {
        iframe = this.iframe.contentWindow;
        var code = iframe.getstoreListSelections('code');
        var name = iframe.getstoreListSelections('name');
        var returnName = name + "("+code+")";
        if ($('#relationName').length >= 1) {
            $('#relationName').val(returnName);
            $('#relationName').blur();
        }
        if ($("input[name='relationName']").length >= 1) {
            $("input[name='relationName']").val(returnName);
            $("input[name='relationName']").blur();
        }
        var id = iframe.getstoreListSelections('id');
        if (id !== undefined && id != "") {
            $('#relationId').val(id);
        }
    }
</script>

