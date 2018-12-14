<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>确认列表</title>
<t:base type="jquery,easyui,tools"></t:base>

  <script type="text/javascript" src="${webRoot }/plug-in/Highcharts-4.1.8/js/highcharts.js"></script>
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/modules/exporting.js"></script>
  <script type="text/javascript" src="${webRoot }/plug-in/Highcharts-4.1.8/js/highcharts-3d.js"></script>
  <script>
  $(function () {
	    $('#container').highcharts({
	        chart: {
	            type: 'pie',
	            options3d: {
	                enabled: true,
	                alpha: 45,
	                beta: 0
	            }
	        },
	        title: {
	            text: '<strong style="font-size:16px;font-weight: bold;">${title}</strong>'
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                depth: 35,
	                dataLabels: {
	                    enabled: true,
	                    format: '{point.name}'
	                }
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: '投票比例',
	            data: [${result}
	            ]
	        }]
	    });
	});				
  </script>
</head>
<body>
<div id="container" style="min-width:700px;height:400px;"></div>
</body>
</html>