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
	            type: 'bar'                                                    
	        },                                                                 
	        title: {                                                           
	            text: '${title}'                    
	        },                                                                 
	        subtitle: {                                                        
	            text: '${topTitle}'                                  
	        },                                                                 
	        xAxis: {                                                           
	            categories: ${categories},
	            title: {                                                       
	                text: null                                                 
	            }                                                              
	        },                                                                 
	        yAxis: {                                                           
	            min: 0,                                                        
	            title: {                                                       
	                text: '单位 (票)',                             
	                align: 'high'                                              
	            },                                                             
	            labels: {                                                      
	                overflow: 'justify'                                        
	            }                                                              
	        },                                                                 
	        tooltip: {                                                         
	            valueSuffix: ' 票'                                       
	        },                                                                 
	        plotOptions: {                                                     
	            bar: {                                                         
	                dataLabels: {                                              
	                    enabled: true                                          
	                }                                                          
	            }                                                              
	        },                                                                 
	        legend: {                                                          
	            layout: 'vertical',                                            
	            align: 'right',                                                
	            verticalAlign: 'top',                                          
	            x: -40,                                                        
	            y: 100,                                                        
	            floating: true,                                                
	            borderWidth: 1,                                                
	            backgroundColor: '#FFFFFF',                                    
	            shadow: true                                                   
	        },                                                                 
	        credits: {                                                         
	            enabled: false                                                 
	        },                                                                 
	        series: [{                                                         
	            name: '数据分析',                                             
	            data: ${result}                                   
	        }]                                                                 
	    });                                                                    
	});                                                                                                                                              				
  </script>
</head>
<body>
<div id="container" style="min-width:700px;height:400px"></div>
</body>
</html>