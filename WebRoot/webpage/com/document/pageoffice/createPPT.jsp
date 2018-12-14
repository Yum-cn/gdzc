<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,com.document.docmyfile.entity.*"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%@include file="/context/mytags.jsp"%>

<%
//******************************卓正PageOffice组件的使用*******************************
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	poCtrl1.setCaption("InnovationSoft");//poCtrl为PageOfficeCtrl对象
	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须
	//隐藏菜单栏
	poCtrl1.setMenubar(false);
	//隐藏工具栏
// 	poCtrl1.setCustomToolbar(false);
	poCtrl1.addCustomToolButton("保存","Save()",1);
// 	poCtrl1.setJsFunction_BeforeDocumentSaved("BeforeDocumentSaved()");
	String pId = String.valueOf(request.getAttribute("pId"));
	DocMyFileEntity dfe = (DocMyFileEntity)request.getAttribute("docMyFilePage");
	//设置保存页面
	poCtrl1.setSaveFilePage("officePPTController.do?saveCreatePPT&pId="+pId+"&id="+dfe.getId());
	//新建Word文件，webCreateNew方法中的两个参数分别指代“操作人”和“新建Word文档的版本号”
	poCtrl1.webCreateNew("琪华软件",DocumentVersion.PowerPoint2003);
	poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div style=" width:auto; height:100%;">
    <po:PageOfficeCtrl id="PageOfficeCtrl1"></po:PageOfficeCtrl>
    </div>
  </body>
</html>
    <script type="text/javascript">
        function Save() {
            document.getElementById("PageOfficeCtrl1").WebSave();
            var result = document.getElementById("PageOfficeCtrl1").CustomSaveResult;
            location.href="officePPTController.do?editPPT&id="+result;
            //alert('保存成功！');
        }
    </script>