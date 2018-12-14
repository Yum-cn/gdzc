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
	poCtrl1.setAllowCopy(false);//禁止拷贝
	poCtrl1.setMenubar(false);//隐藏菜单栏
	poCtrl1.setOfficeToolbars(false);//隐藏Office工具条
	poCtrl1.setCustomToolbar(false);//隐藏自定义工具栏
	poCtrl1.setJsFunction_AfterDocumentOpened("AfterDocumentOpened");
	//设置保存页面
	poCtrl1.setSaveFilePage("officeExcelController.do?saveCreateExcel");
	//打开Excel文件
	DocMyFileEntity dfe = (DocMyFileEntity)request.getAttribute("docMyFilePage");
	poCtrl1.webOpen(dfe.getFilePath(), OpenModeType.xlsReadOnly, "琪华软件");
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