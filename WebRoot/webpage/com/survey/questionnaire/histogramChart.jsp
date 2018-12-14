<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>确认列表</title>
<t:base type="jquery,easyui,tools"></t:base>
 
  
  <script type="text/javascript" src="${webRoot }/plug-in/Highcharts-4.1.8/js/highcharts.js"></script>
<%--   <script type="text/javascript" src="${webRoot }/plug-in/Highcharts-4.1.8/js/exporting.js"></script> --%>
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/modules/exporting.js"></script>
  <script type="text/javascript" src="${webRoot }/plug-in/Highcharts-4.1.8/js/highcharts-3d.js"></script>
  <script>
  $(function () {
	    
	    var colors = Highcharts.getOptions().colors,
	        categories = ${categories},
	        name = '${title}',
	        data = [${result}];

	    function setChart(name, categories, data, color) {
		chart.xAxis[0].setCategories(categories, false);
		chart.series[0].remove(false);
		chart.addSeries({
			name: name,
			data: data,
			color: color || 'white'
		}, false);
		chart.redraw();
	    }

	    var chart = $('#container').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: ''
	        },
	        subtitle: {
	            text: ' <p>${topTitle}</p> '
	        },
	        xAxis: {
	            categories: categories
	        },
	        yAxis: {
	            title: {
	                text: '  '
	            }
	        },
	        plotOptions: {
	            column: {
	                cursor: 'pointer',
	                point: {
	                    events: {
	                        click: function() {
	                            var drilldown = this.drilldown;
	                            if (drilldown) { // drill down
	                                setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
	                            } else { // restore
	                                setChart(name, categories, data);
	                            }
	                        }
	                    }
	                },
	                dataLabels: {
	                    enabled: true,
	                    color: colors[0],
	                    style: {
	                        fontWeight: 'bold'
	                    },
	                    formatter: function() {
	                        return this.y +'票';
	                    }
	                }
	            }
	        },
	        tooltip: {
	            formatter: function() {
	                var point = this.point,
	                    s = this.x +':<b>'+ this.y +'票</b><br/>';
// 	                if (point.drilldown) {
// 	                    s += 'Click to view '+ point.category +' versions';
// 	                } else {
// 	                    s += 'Click to return to browser brands';
// 	                }
	                return s;
	            }
	        },
	        series: [{
	            name: name,
	            data: data,
	            color: 'white'
	        }],
	        exporting: {
	            enabled: true
	        }
	    })
	    .highcharts(); // return chart
	});	
  </script>
</head>
<body>
<div id="container" style="min-width:700px;height:400px"></div>
</body>
</html>