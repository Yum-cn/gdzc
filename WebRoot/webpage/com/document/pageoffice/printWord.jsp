<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.document.docmyfile.entity.*"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
poCtrl.setCaption("InnovationSoft");//poCtrl为PageOfficeCtrl对象

//设置服务器页面
poCtrl.setServerPage(request.getContextPath()+"/poserver.zz");

poCtrl.setCustomToolbar(false);
poCtrl.setOfficeToolbars(false);
poCtrl.setAllowCopy(false);

poCtrl.setJsFunction_AfterDocumentOpened("AfterDocumentOpened");
//打开Word文档
DocMyFileEntity dfe = (DocMyFileEntity)request.getAttribute("docMyFilePage");
poCtrl.webOpen(dfe.getFilePath(),OpenModeType.docReadOnly,"琪华软件");
poCtrl.setTagId("PageOfficeCtrl1");//此行必需
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title></title>
</head>
<body>
    <script type="text/javascript">
        function AfterDocumentOpened() {
            document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, false); // 禁止保存
            document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(4, false); // 禁止另存
            document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(5, false); //禁止打印
            document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(6, false); // 禁止页面设置
        }
    </script>
    <form id="form1" >
    <div style=" width:auto; height:100%;">
        <po:PageOfficeCtrl id="PageOfficeCtrl1">
        </po:PageOfficeCtrl>
    </div>
    </form>
</body>
</html>
