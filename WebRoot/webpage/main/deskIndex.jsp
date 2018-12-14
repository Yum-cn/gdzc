<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/global.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/echarts/echarts.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/bootstrap-3.3.7-dist/runoob/bootstrap.min.css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/bootstrap-3.3.7-dist/runoob/style.css"></link>
<link href="${pageContext.request.contextPath}/plug-in/bootstrap-3.3.7-dist/font-awesome/css/font-awesome.css" rel="stylesheet"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/skin_/index.css" />

<title>首页</title>
</head>

<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">系统首页</li>
  </ul>
  <div class="layui-tab-content" style="height: 100px;">
    <div class="layui-tab-item layui-show">
    
<div id="container">
	<div id="hd">
    </div>
    <div id="bd">
    	<div id="main">
    	<!--mini statistics start-->
<div class="row">

    <div class="col-md-2">
        <div class="mini-stat clearfix">
            <span class="mini-stat-icon green"><i class="fa fa-thumbs-up"></i></span>
            <div class="mini-stat-info" style="width:380px;">
                <span style="font-size:22px;">
					<c:forEach items="${contList}" var="contList">
					${contList }
					</c:forEach>
                </span>
                	资产总数量
            </div>
        </div>
    </div>
    
    <div class="col-md-2">
        <div class="mini-stat clearfix">
            <span class="mini-stat-icon orange"><i class="fa fa-random"></i></span>
            <div class="mini-stat-info" style="width:380px;">
                <span style="font-size:22px;">
					<c:forEach items="${wyList}" var="wyList">
					${wyList }
					</c:forEach>
                </span>
                	未使用数量
            </div>
        </div>
    </div>
    <div class="col-md-2">
        <div class="mini-stat clearfix">
            <span class="mini-stat-icon tar"><i class="fa fa-tag"></i></span>
            <div class="mini-stat-info" style="width:380px;">
                <span style="font-size:22px;">
                	<c:forEach items="${yyList}" var="yyList">
					${yyList }
					</c:forEach>
                </span>
                	已使用数量
            </div>
        </div>
    </div>
    <div class="col-md-2">
        <div class="mini-stat clearfix">
            <span class="mini-stat-icon pink"><i class="fa fa-money"></i></span>
            <div class="mini-stat-info" style="width:380px;">
                <span style="font-size:22px;">
                  	<c:forEach items="${xzList}" var="xzList">
					${xzList }
					</c:forEach>
                </span>
                	闲置数量
            </div>
        </div>
    </div>
    <div class="col-md-2">
        <div class="mini-stat clearfix">
            <span class="mini-stat-icon green"><i class="fa fa-bug"></i></span>
            <div class="mini-stat-info" style="width:380px;">
                <span style="font-size:22px;">
                  	<c:forEach items="${bfList}" var="bfList">
					${bfList }
					</c:forEach>
                </span>
                	报废数量
            </div>
        </div>
    </div>
    <div class="col-md-2">
        <div class="mini-stat clearfix">
            <span class="mini-stat-icon yellow-b"><i class="fa fa-gavel"></i></span>
            <div class="mini-stat-info" style="width:380px;">
                <span style="font-size:22px;">
                  	<c:forEach items="${wxList}" var="wxList">
					${wxList }
					</c:forEach>
                </span>
                	维修中数量
            </div>
        </div>
    </div>
</div>
<!--mini statistics end-->

            
            <ul class="content-list">
            	<li class="content-item system">
                	<h2 class="content-hd">
                    	<span class="title">所属网络分析</span>
                    </h2>
                    <div class="content-bd" style="height:300px">
                    	<ul class="content-list things" id="container2" style="width: 520px;height:300px">

                        </ul>
                    </div>
                </li>
                <li class="content-item dothings">
                	<h2 class="content-hd">
                    	<span class="title">组别管理分析</span>
                    </h2>
                    <div class="content-bd"  style="height:300px">
                    	<ul class="content-list things" id="container3" style="width: 620px;height:300px">

                        </ul>
                    </div>
                </li>
            	<li class="content-item system">
                	<h2 class="content-hd">
                    	<span class="title">近三年采购量分析</span>
                    </h2>
                    <div class="content-bd" style="height:300px">
                    	<ul class="content-list things" id="container4" style="width: 620px;height:300px">

                        </ul>
                    </div>
                </li>
                <li class="content-item dothings">
                	<h2 class="content-hd">
                    	<span class="title">近三年采购经费分析</span>
                    </h2>
                    <div class="content-bd"  style="height:300px">
                    	<ul class="content-list things" id="container5" style="width: 620px;height:300px;text-align: center;">

                        </ul>
                    </div>
                </li>
                
            </ul>
        </div>
    </div>
</div>    
    
    
	</div>
  </div>
</div> 

</body>

<script type="text/javascript">
	var minwidth = 582;
	resizeWidth();
	$(top.window).resize(function(e) {
       resizeWidth();
    });
	$(function() {
// 		$( ".content-list" ).sortable({
// 		  revert: true,
// 		  handle:'h2'
// 		});
		
	});
	
function resizeWidth (){
	if($('#main').width() / 3 < minwidth){
		$('.content-item').width(($('#main').width() / 2) - 15);
		$('.content-item').height(350);
		
	}else{
		$('.content-item').width(($('#main').width() / 3) - 15);	
	}
		
}

</script>
<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('container3'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: ''
            },
            tooltip: {},
            legend: {
                data:['数量']
            },
            xAxis: {
                data: [
						<c:forEach items="${zcflList}" var="zcflList">
                       "${zcflList[0]}",
                        </c:forEach>
                       ]
            },
            yAxis: {},
            series: [{
                name: '数量',
                type: 'bar',
                data: [
						<c:forEach items="${zcflList}" var="zcflList">
						${zcflList[1]}, 
                        </c:forEach>
                      ]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('container2'));

        option = {
        	    title : {
        	        text: '',
        	        subtext: '',
        	        x:'center'
        	    },
        	    tooltip : {
        	        trigger: 'item',
        	        formatter: "{a} <br/>{b} : {c} ({d}%)"
        	    },
        	    legend: {
        	        orient: 'vertical',
        	        left: 'left',
        	        data: [
							<c:forEach items="${zcztList}" var="zcztList">
        	               '${zcztList[0]}',
        	               </c:forEach>
							]
        	    },
        	    series : [
        	        {
        	            name: '比例',
        	            type: 'pie',
        	            radius : '55%',
        	            center: ['50%', '60%'],
        	            data:[
							<c:forEach items="${zcztList}" var="zcztList">
        	                {value:${zcztList[1]}, name:'${zcztList[0]}'},
        	                </c:forEach>
        	            ],
        	            itemStyle: {
        	                emphasis: {
        	                    shadowBlur: 10,
        	                    shadowOffsetX: 0,
        	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
        	                }
        	            }
        	        }
        	    ]
        	};


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
</html>
<script>
option = {

	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        left: 'left',
	        data: ['市电子政务内网','市委办公厅内网','市委机关互联网','党委办公厅专网','国家电子政务内网天津接入区']
	    },
	    series : [
	        {
	            name: '访问来源',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:[
	                {value:335, name:'市电子政务内网'},
	                {value:310, name:'市委办公厅内网'},
	                {value:234, name:'市委机关互联网'},
	                {value:135, name:'党委办公厅专网'},
	                {value:1548, name:'国家电子政务内网天津接入区'}
	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
var myChart = echarts.init(document.getElementById('container2'));
myChart.setOption(option);


</script>
<script>
option = {
	    xAxis: {
	        type: 'category',
	        data: ['综合管理组', '网络平台组', '安全平台组', '信息资源组', '应用系统组', '密码设备组']
	    },
	    yAxis: {
	        type: 'value'
	    },
	    series: [{
	        data: [120, 200, 150, 80, 70, 110],
	        type: 'bar',
	        label: {
                normal: {
                    show: true,
                    position: 'inside'
                }
            },
	    }]
	};

var myChart = echarts.init(document.getElementById('container3'));
myChart.setOption(option);


</script>
<script>

option = {

    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data: ['软件', '硬件']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01]
    },
    yAxis: {
        type: 'category',
        data: ['2016年','2017年','2018年','近三年总量']
    },
    series: [
        {
            name: '软件',
            type: 'bar',
            data: [18203, 23489, 29034,30230],
            label: {
                normal: {
                    show: true,
                    position: 'inside'
                }
            },
        },
        {
            name: '硬件',
            type: 'bar',
            data: [19325, 23438, 31000, 81807],
            label: {
                normal: {
                    show: true,
                    position: 'inside'
                }
            },
        }
    ]
};
var myChart = echarts.init(document.getElementById('container4'));
myChart.setOption(option);


</script>

<script>
option = {
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['软件经费','硬件经费']
	    },
	    toolbox: {
	        show: true,
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            dataView: {readOnly: false},
	            magicType: {type: ['line', 'bar']},
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    xAxis:  {
	        type: 'category',
	        boundaryGap: false,
	        data: ['2016年','2017年','2018年']
	    },
	    yAxis: {
	        type: 'value',
	        axisLabel: {
	            formatter: '{value} 万元'
	        }
	    },
	    series: [
	        {
	            name:'软件经费',
	            type:'line',
	            data:[211, 311, 651],
	            markPoint: {
	                data: [
	                    {type: 'max', name: '最大值'},
	                    {type: 'min', name: '最小值'}
	                ]
	            },
	            markLine: {
	                data: [
	                    {type: 'average', name: '平均值'}
	                ]
	            }
	        },
	        {
	            name:'硬件经费',
	            type:'line',
	            data:[101, 212, 312],
	            markPoint: {
	                data: [
	                    {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
	                ]
	            },
	            markLine: {
	                data: [
	                    {type: 'average', name: '平均值'},
	                    [{
	                        symbol: 'none',
	                        x: '90%',
	                        yAxis: 'max'
	                    }, {
	                        symbol: 'circle',
	                        label: {
	                            normal: {
	                                position: 'start',
	                                formatter: '最大值'
	                            }
	                        },
	                        type: 'max',
	                        name: '最高点'
	                    }]
	                ]
	            }
	        }
	    ]
	};

var myChart = echarts.init(document.getElementById('container5'));
myChart.setOption(option);


</script>