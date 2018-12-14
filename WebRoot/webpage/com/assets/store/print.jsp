<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script language="javascript" src="${pageContext.request.contextPath}/plug-in/loop/LodopFuncs.js"></script>
</head>
<body>
<div style="text-align: center;vertical-align: top;"><img src="${qrPath }"></img></div>


<script language="javascript" type="text/javascript"> 
        var LODOP; //声明为全局变量
	function CreateImage() {
// 		LODOP=getLodop();  
// 		LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_打印图片1");
// 		LODOP.SET_PRINT_STYLE("FontSize",25);
// 		LODOP.SET_SHOW_MODE("BKIMG_TOP",100);  
// 		LODOP.ADD_PRINT_IMAGE(0,0,300,300,"<img src='${qrPath }'></img>");
// 		LODOP.ADD_PRINT_BARCODE(130,434,168,146,"QRCode","1234567890版本7的最大值是122个字符123123");
// 		LODOP.ADD_PRINT_IMAGE(30,20,600,250,"<img border='0' src='http://s1.sinaimg.cn/middle/4fe4ba17hb5afe2caa990&690'width='100%' height='250'/>");
// 		LODOP.ADD_PRINT_IMAGE(28,49,171,153,"<img border='0' src='http://static15.photo.sina.com.cn/middle/4fe4ba17t993d651b55ce&690' />");

// 		LODOP.ADD_PRINT_TEXT(260,50,295,160,"秘密文件");
// 		LODOP.SET_PRINT_STYLEA(0,"FontSize",55);
// 		LODOP.ADD_PRINT_TEXT(260,60,295,160,"秘密文件");
		LODOP=getLodop();  	
// 		LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_打印图片4");
// 		LODOP.ADD_PRINT_TEXT(56,56,200,20,"公章底下的文字");
// 		LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
		
// 		LODOP.SET_PRINT_STYLEA(0,"TransColor","#FFFFFF");
// 		LODOP.ADD_PRINT_TEXT(120,46,200,20,"${se.code}");
	 	LODOP.SET_PRINT_STYLE("FontSize",10); 
	 	LODOP.SET_PRINT_STYLEA(1, "Bold", 1);
	 	LODOP.SET_PRINT_STYLEA(2,"FontName","隶书");
		LODOP.SET_PRINT_STYLEA(0,"TransColor","#FFFFFF");
		LODOP.ADD_PRINT_TEXT(15,18,500,20,"中共天津市北辰区纪律检查委员会");
		
		
		LODOP.SET_PRINT_STYLEA(0,"TransColor","#FFFFFF");
		LODOP.ADD_PRINT_TEXT(38,126,180,20,"${se.code}");
		
		LODOP.SET_PRINT_STYLEA(0,"TransColor","#FFFFFF");
		LODOP.ADD_PRINT_TEXT(58,126,180,20,"${se.name}");
		
		LODOP.SET_PRINT_STYLEA(0,"TransColor","#FFFFFF");
		LODOP.ADD_PRINT_TEXT(78,126,180,20,"${se.brand}");
		
		LODOP.SET_PRINT_STYLEA(0,"TransColor","#FFFFFF");
		LODOP.ADD_PRINT_TEXT(98,126,180,20,'<fmt:formatDate value="${se.storageTime}" pattern="yyyy-MM-dd"/>');
		
		LODOP.SET_PRINT_STYLEA(0,"TransColor","#FFFFFF");
		LODOP.ADD_PRINT_TEXT(118,126,180,20,'<fmt:formatDate value="${se.overInsuranceTime}" pattern="yyyy-MM-dd"/>');
		
		LODOP.ADD_PRINT_IMAGE(35,10,106,106,"<img src='${qrPath }' height='106'></img>");
		
// 		LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
		LODOP.PREVIEW();
	};	
	function myPreview1() {		
		CreateImage();
// 		LODOP.PREVIEW();		
	};	
	function myPrint1() {		
		CreateImage();
		LODOP.PRINT();		
	};  
	function myPrintSetup1() {		
		CreateImage();
		LODOP.PRINT_SETUP();		
	}; 		

	
</script> 

</body>
</html>