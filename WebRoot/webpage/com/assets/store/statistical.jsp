<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<t:base type="jquery,tools,DatePicker"></t:base>
<!--<link href="${pageContext.request.contextPath}/plug-in/frame/default/css/style.css" rel="stylesheet" type="text/css" />-->
<link href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/skin_/table.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/jquery.grid.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/jquery.select.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all">
</link>
<script type="text/javascript">
$(document).ready(function(){
  //$(".click").click(function(){
  //$(".tip").fadeIn(200);
  //});
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>
</head>
<body>
<div class="rightinfo">
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
<ul class="layui-tab-title">
  <li class="layui-this">统计分析</li>
</ul>
<div class="layui-tab-content">
<div class="layui-tab-item layui-show">
<div id="container">
  <div id="hd"></div>
  <div id="bd">
    <div id="main">
      <form name="searchForm" id="searchForm" method="post" action="storeController.do?list" class="layui-form layui-form-pane">
        <div class="layui-form-item" style="width:1980px;">
          <label class="layui-form-label">所属网络:</label>
          <div class="layui-input-inline" style="width:150px;">
            <select name="select">
              <option value="1">市电子政务内网</option>
              <option value="1">市委办公厅内网</option>
              <option value="1">市委机关互联网</option>
              <option value="1">党委办公厅专网</option>
              <option value="1">国家电子政务内网天津接入区</option>
            </select>
          </div>
          <label class="layui-form-label">组别:</label>
          <div class="layui-input-inline" style="width:120px;">
            <select name="select1">
              <option value="1">综合管理组</option>
              <option value="1">网络平台组</option>
              <option value="1">安全平台组</option>
              <option value="1">信息资源组</option>
              <option value="1">应用系统组</option>
              <option value="1">密码设备组</option>
            </select>
          </div>
          <label class="layui-form-label">资产类型:</label>
          <div class="layui-input-inline" style="width:80px;">
            <select name="select2">
              <option value="1">软件</option>
              <option value="1">硬件</option>
            </select>
          </div>
          <label class="layui-form-label">开始时间:</label>
          <div class="layui-input-inline" style="width:90px;">
            <input id="name" name="name" type="text" value="${name}" placeholder="2018-01-01" autocomplete="off" class="layui-input" />
          </div>
          <label class="layui-form-label">结束时间:</label>
          <div class="layui-input-inline" style="width:90px;">
            <input id="name" name="name" type="text" value="${name}" placeholder="2019-01-01" autocomplete="off" class="layui-input" />
          </div>
          <div class="layui-input-inline"> &nbsp;&nbsp;&nbsp;&nbsp;
            <button class="layui-btn layui-btn-normal" onclick="$('#searchForm').submit();">查&nbsp;&nbsp;询</button>
          </div>
        </div>
      </form>
      <button class="layui-btn" onclick="add('storeController.do?goAdd')"><i class="layui-icon"></i> 导出</button>
      <button class="layui-btn" onclick="add('storeController.do?goAdd')"><i class="layui-icon"></i> 打印</button>
      <form name="searchForm" id="searchForm" method="post" action="storeController.do?list" >
        <div class="table">
          <div class="grid" >
            <table class="layui-table">
              <thead>
                <tr>
                  <th colspan="9" align="center" style="text-align: center;">资产总数量：120&nbsp;&nbsp;已使用数量：20&nbsp;&nbsp;未使用数量：8&nbsp;&nbsp;闲置数量：1&nbsp;&nbsp;报废数量：88&nbsp;&nbsp;维修中数量：6</th>
                </tr>
              </thead>
              <thead>
                <tr>
                  <th><input name="selectAll" id="selectAll" type="checkbox"  /></th>
                  <th>组别</th>
                  <th>所属网络</th>
                  <th>资产编码</th>
                  <th>资产名称</th>
                  <th>购买时间</th>
                  <th>维保到期时间</th>
                  <th>价格</th>
                  <th>状态</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td><input name="id" id="id" type="checkbox" value="4028ef816661a719016661b529b20003" />
                  </td>
                  <td>安全平台组</td>
                  <td>市电子政务内网</td>
                  <td>sbbm000001</td>
                  <td>测试设备</td>
                  <td>2018-10-11</td>
                  <td 
									style="background-color: red;"
									2019-10-11
									> 2018-11-11 </td>
                  <td>100</td>
                  <td>未用</td>
                </tr>
                <tr>
                  <td><input name="id" id="id" type="checkbox" value="4028ef816661a719016661b529d20004" />
                  </td>
                  <td>安全平台组</td>
                  <td>市电子政务内网</td>
                  <td>sbbm000002</td>
                  <td>测试设备</td>
                  <td>2018-10-11</td>
                  <td 
									2019-10-11
									style="background-color: yellow;"
									> 2018-12-11 </td>
                  <td>100</td>
                  <td>未用</td>
                </tr>
                <tr>
                  <td><input name="id" id="id" type="checkbox" value="1" />
                  </td>
                  <td>安全平台组</td>
                  <td>市电子政务内网</td>
                  <td>sbbm000003</td>
                  <td>档案柜</td>
                  <td>2017-12-31</td>
                  <td 
									2019-10-11
									2019-10-11
									>
                  2019-11-11
                  </td>
                  <td>12400</td>
                  <td>报废</td>
                </tr>
                <tr>
                  <td><input name="id" id="id" type="checkbox" value="2" />
                  </td>
                  <td>安全平台组</td>
                  <td>市电子政务内网</td>
                  <td>sbbm000004</td>
                  <td>储物柜</td>
                  <td>2017-11-30</td>
                  <td 
									2019-10-11
									2019-10-11
									>
                  2019-11-11
                  </td>
                  <td>2800</td>
                  <td>未用</td>
                </tr>
                <tr>
                  <td><input name="id" id="id" type="checkbox" value="3" />
                  </td>
                  <td>安全平台组</td>
                  <td>市电子政务内网</td>
                  <td>sbbm000005</td>
                  <td>办公桌椅（白）</td>
                  <td>2017-11-30</td>
                  <td 
									2019-10-11
									2019-10-11
									>
                  2019-11-11
                  </td>
                  <td>1860</td>
                  <td>未用</td>
                </tr>
                <tr>
                  <td><input name="id" id="id" type="checkbox" value="4" />
                  </td>
                  <td>安全平台组</td>
                  <td>市电子政务内网</td>
                  <td>sbbm000006</td>
                  <td>屏风工位+办公桌</td>
                  <td>2017-11-30</td>
                  <td 
									2019-10-11
									2019-10-11
									>
                  2019-11-11
                  </td>
                  <td>20720</td>
                  <td>未用</td>
                </tr>
                <tr>
                  <td><input name="id" id="id" type="checkbox" value="5" />
                  </td>
                  <td>安全平台组</td>
                  <td>市电子政务内网</td>
                  <td>sbbm000007</td>
                  <td>档案柜</td>
                  <td>2017-10-31</td>
                  <td 
									2019-10-11
									2019-10-11
									>
                  2019-11-11
                  </td>
                  <td>23250</td>
                  <td>未用</td>
                </tr>
                <tr>
                  <td><input name="id" id="id" type="checkbox" value="6" />
                  </td>
                  <td>安全平台组</td>
                  <td>市电子政务内网</td>
                  <td>sbbm000008</td>
                  <td>档案柜</td>
                  <td>2017-10-31</td>
                  <td 
									2019-10-11
									2019-10-11
									>
                  2019-11-11
                  </td>
                  <td>4650</td>
                  <td>未用</td>
                </tr>
                <tr>
                  <td><input name="id" id="id" type="checkbox" value="7" />
                  </td>
                  <td>安全平台组</td>
                  <td>市电子政务内网</td>
                  <td>sbbm000009</td>
                  <td>园餐桌</td>
                  <td>2017-09-30</td>
                  <td 
									2019-10-11
									2019-10-11
									>
                  2019-11-11
                  </td>
                  <td>3800</td>
                  <td>未用</td>
                </tr>
                <tr>
                  <td><input name="id" id="id" type="checkbox" value="8" />
                  </td>
                  <td>安全平台组</td>
                  <td>市电子政务内网</td>
                  <td>sbbm0000010</td>
                  <td>办公桌椅</td>
                  <td>2017-09-30</td>
                  <td 
									2019-10-11
									2019-10-11
									>
                  2019-11-11
                  </td>
                  <td>1600</td>
                  <td>未用</td>
                </tr>
              </tbody>
            </table>
          </div>
          </tbody>
          </table>
        </div>
      </form>
    </div>
  </div>
</div>
<script type="text/javascript">
	function add(url){
		window.location.href=url;
	}
	function update(url){
		window.location.href=url;
	}
	//$('.tablelist tbody tr:odd').addClass('odd');
	function onSearchForm(url){
		$("#searchForm").attr("action",url);
		$("#searchForm").submit();
	}
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'storeController.do?upload', "storeList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("storeController.do?exportXls","storeList");
	}
// 	function openWin(id){
// 		location.href="storeController.do?print&id="+id;
// 	}
	function openWin(id){
		var addurl="storeController.do?print&id="+id;
		$.dialog({
			content: 'url:'+addurl,
			zIndex: 10000,
			lock : true,
			width:350,
			height: 350,
			title:"标签打印",
			opacity : 0.3,
			cache:false, 
			button : [ {
				name : '打印预览',
				callback : function() {
					iframe = this.iframe.contentWindow;
					iframe.myPreview1();
					return false;
				},
				focus : true
			}],
		    cancel: true /*为true等价于function(){}*/
		});
	}
	function openResumeWin(id){
		var addurl="resumeController.do?list&storeId="+id;
		$.dialog({
			content: 'url:'+addurl,
			zIndex: 10000,
			lock : true,
			width:850,
			height: 350,
			title:"资产履历",
			opacity : 0.3,
			cache:false, 
		    cancel: true /*为true等价于function(){}*/
		});
	}
</script>
</body>
</html>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
<script>
layui.use(['form'], function(){
  var form = layui.form()
});
</script>
