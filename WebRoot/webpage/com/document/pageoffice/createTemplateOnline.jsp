<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,com.document.template.entity.*,com.document.docmyfile.entity.*"
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
// 	poCtrl1.setCustomToolbar(false);//隐藏自定义工具栏
	poCtrl1.addCustomToolButton("保存","Save()",1);
	//设置保存页面
	TemplateEntity dfe = (TemplateEntity)request.getAttribute("template");
	String type="";
	if(dfe.getAttFile()!=null && !"".equals(dfe.getAttFile())){
		int dot = dfe.getAttFile().lastIndexOf('.');   
        if ((dot >-1) && (dot < (dfe.getAttFile().length() - 1))) {   
        	type = dfe.getAttFile().substring(dot + 1);   
        } 
	}
	DocMyFileEntity docMyfile = (DocMyFileEntity)request.getAttribute("docMyFilePage");
	String pId = String.valueOf(request.getAttribute("pId"));
	poCtrl1.setSaveFilePage("templateController.do?saveCreateTemplate&templateId="+dfe.getId()+"&id="+docMyfile.getId()+"&type="+type+"&pId="+pId);
	//打开Word文件

	if(type!=null && ("doc".equals(type) || "docx".equals(type))){
		poCtrl1.webOpen(dfe.getAttFile(), OpenModeType.docNormalEdit, "琪华软件");
	}
	if(type!=null && ("xls".equals(type) || "xlsx".equals(type))){
		poCtrl1.webOpen(dfe.getAttFile(), OpenModeType.xlsNormalEdit, "琪华软件");
	}
	if(type!=null && ("ppt".equals(type) || "pptx".equals(type))){
		poCtrl1.webOpen(dfe.getAttFile(), OpenModeType.pptNormalEdit, "琪华软件");
	}
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
            if('<%=type%>'=='doc' || '<%=type%>'=='docx'){
            	location.href="officeWordController.do?editWord&id="+result;
            }
            if('<%=type%>'=='xls' || '<%=type%>'=='xlsx'){
            	location.href="officeExcelController.do?editExcel&id="+result;
            }
            if('<%=type%>'=='ppt' || '<%=type%>'=='pptx'){
            	location.href="officePPTController.do?editPPT&id="+result;
            }
            //alert('保存成功！');
        }
    </script>