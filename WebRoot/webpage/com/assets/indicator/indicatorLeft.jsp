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
	<!--  <script type="text/javascript" src="../../../js/jquery.ztree.excheck.js"></script>
	  <script type="text/javascript" src="../../../js/jquery.ztree.exedit.js"></script>-->
	<SCRIPT type="text/javascript">
		<!--
		var setting = {
			data: {
				key: {
					title:"t"
				},
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
		};

		var zNodes =[
			{ id:1, pId:0, name:"评价指标库", open:true},
			<c:forEach items="${list}" var="list">
			{ id:'${list[0]}',parentId:'${list[0]}', pId:1, name:"${list[2]}"},
			</c:forEach>
			<c:forEach items="${indicatorList}" var="indicatorList">
			{ id:'${indicatorList.id}',parentId:'${indicatorList.id}', pId:'${indicatorList.parentId}', name:"${indicatorList.name}"},
			</c:forEach>
		];

		var log, className = "dark";
		function beforeClick(treeId, treeNode, clickFlag) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeClick ]&nbsp;&nbsp;" + treeNode.name );
			return (treeNode.click != false);
		}
		function onClick(event, treeId, treeNode, clickFlag) {
			var url = "${pageContext.request.contextPath}/indicatorController.do?indicator&parentId="+treeNode.parentId+"&pjdl="+treeNode.id;
			$('#listFrame', parent.document).attr("src",url);
		}		
		function showLog(str) {
			if (!log) log = $("#log");
			log.append("<li class='"+className+"'>"+str+"</li>");
			if(log.children("li").length > 8) {
				log.get(0).removeChild(log.children("li")[0]);
			}
		}
		function getTime() {
			var now= new Date(),
			h=now.getHours(),
			m=now.getMinutes(),
			s=now.getSeconds();
			return (h+":"+m+":"+s);
		}

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		//-->
	</SCRIPT>
</head>
<body>
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
  </body>
</html>