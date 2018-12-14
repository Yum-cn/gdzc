<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML>
 <HEAD>
  <TITLE> ZTREE DEMO </TITLE>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/ztree/css/zTreeStyle.css" type="text/css">
  <style>
	body {
	background-color: white;
	margin:0; padding:0;
	text-align: center;
	}
	div, p, table, th, td {
		list-style:none;
		margin:0; padding:0;
		color:#333; font-size:12px;
		font-family:dotum, Verdana, Arial, Helvetica, AppleGothic, sans-serif;
	}
	#testIframe {margin-left: 10px;}
  </style>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
  <SCRIPT type="text/javascript" >
  <!--
	var zTree;
	var demoIframe;

	var setting = {
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("tree");
				if (treeNode.isParent) {
					zTree.expandNode(treeNode);
					return false;
				} else {
					demoIframe.attr("src",treeNode.file + ".html");
					return true;
				}
			}
		}
	};

	var zNodes =[
	     		{id:1, pId:1, name:"教育文档库",open:false, url:"${webRoot}/docLibFileController.do?list&pId=1",target:"testIframe"},
	     		<c:forEach items="${resultList}" var="resultList">
	     			{id:'${resultList.id}', pId:'${resultList.pId}', name:"${resultList.folderName}", url:"${webRoot}/docLibFileController.do?list&pId=${resultList.id}",target:"testIframe"},
	     		</c:forEach>
	     		
//	      		{id:101, pId:1, name:"市级以上一等", url:"${webRoot }/fruitController.do?fruit&level=SJYD",target:"testIframe"},
//	      		{id:101, pId:1, name:"市级二等、区级一等", url:"${webRoot }/fruitController.do?fruit&level=SJED",target:"testIframe"},
//	      		{id:101, pId:1, name:"区二等、市三等", url:"${webRoot }/fruitController.do?fruit&level=SJSD",target:"testIframe"},
	     		
	     	];

	$(document).ready(function(){
		var t = $("#tree");
		t = $.fn.zTree.init(t, setting, zNodes);
		demoIframe = $("#testIframe");
		demoIframe.bind("load", loadReady);
		var zTree = $.fn.zTree.getZTreeObj("tree");
		zTree.selectNode(zTree.getNodeByParam("id", 101));

	});

	function loadReady() {
		var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
		htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
		maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
		h = demoIframe.height() >= maxH ? minH:maxH ;
		//if (h < 530) h = 530;
		//demoIframe.height(h);
	}

  //-->
 
  </SCRIPT>
	<script type="text/javascript">
	  	function centerFrameHeight(){
				var mainheight = $(window).height();
				mainheight = mainheight-8;
				$("#testIframe").attr("style","height:"+mainheight+"px;width:99%;");
				$("#tree").css("height",mainheight-9);
				//alert($("#testIframe").height());
				//$("#tree").attr("style","height:500px;width:300px;");
		}
		$(function(){
	
			centerFrameHeight();
		});
  	</script>
 </HEAD>
<body>
<table border=0 align=left>
	<tr>
		<td width=260px align=left valign=top style="BORDER-RIGHT: #999999 1px dashed">
			<ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
		</td>
		<td width="90%" align="left" valign="top"><iframe id="testIframe" name="testIframe" frameborder=0 scrolling="auto" src="${webRoot }/docLibFileController.do?list&pId=1"></iframe></td>
	</tr>
</table>

</body>
</HTML>
