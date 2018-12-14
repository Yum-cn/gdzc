<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>指标管理</title>
<t:base type="jquery,easyui,tools"></t:base>
	<link rel="stylesheet" href="plug-in/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<!-- 	<script type="text/javascript" src="../../../js/jquery-1.4.4.min.js"></script> -->
	<script type="text/javascript" src="plug-in/zTree/js/jquery.ztree.core.js"></script>
		<script type="text/javascript" src="plug-in/zTree/js/jquery.ztree.excheck.js"></script>
	<!--  <script type="text/javascript" src="../../../js/jquery.ztree.excheck.js"></script>
	  <script type="text/javascript" src="../../../js/jquery.ztree.exedit.js"></script>-->
	  <SCRIPT type="text/javascript">
		<!--
		var returnId = [];
		var returnName = [];
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onCheck: onCheck
			}
		};
		function onCheck(event, treeId, treeNode, clickFlag) {
			returnId.splice(0,returnId.length);
			returnName.splice(0,returnName.length);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = zTree.getCheckedNodes(true);
			for(var i = 0;i<nodes.length;i++){
				if(nodes[i].isParent==false){
					returnId.push(nodes[i].id);
					returnName.push(nodes[i].name);
				}
// 				var returnName = "";
// 				returnName = returnName + "->" +nodes[i].name;
// 				alert(returnName);
// 				alert(nodes[i].level);
			}
			
			console.log(nodes);
// 			var url = "${pageContext.request.contextPath}/indicatorController.do?indicator&parentId="+treeNode.parentId+"&pjdl="+treeNode.id;
// 			$('#listFrame', parent.document).attr("src",url);
		}	
		var zNodes =[
		    <c:forEach items="${allList}" var="allList">
			{ id:'${allList.id}', pId:'${allList.parentId}', name:"${allList.name}", open:true},
			</c:forEach>
		    <c:forEach items="${list}" var="list">
			{ id:'${pjdl}_${list.id}', pId:'${list.parentId}', name:"${list.name}", open:true},
			</c:forEach>
		];
		
		var code;
		
		function setCheck() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			py = $("#py").attr("checked")? "p":"",
			sy = $("#sy").attr("checked")? "s":"",
			pn = $("#pn").attr("checked")? "p":"",
			sn = $("#sn").attr("checked")? "s":"",
			type = { "Y":"ps", "N":"ps"};
			zTree.setting.check.chkboxType = type;
			showCode('setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };');
		}
		function showCode(str) {
			if (!code) code = $("#code");
			code.empty();
			code.append("<li>"+str+"</li>");
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			setCheck();
			$("#py").bind("change", setCheck);
			$("#sy").bind("change", setCheck);
			$("#pn").bind("change", setCheck);
			$("#sn").bind("change", setCheck);
		});
		//-->
	</SCRIPT>
</head>
<body style="overflow-y: yes" scroll="yes">

<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</div>
  </body>
</html>