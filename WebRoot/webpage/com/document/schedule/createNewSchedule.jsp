<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>创建新的日程</title>
<link href="${pageContext.request.contextPath}/webpage/com/document/schedule/schedule.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>

</head>
<body>
      <div class="layui-form layui-form-pane "  style="margin-left: 30px;margin-top: 18px;">
        <div class="layui-form-item" align="center">
          <label class="layui-form-label" style="width:20%;">日程内容:</label>
          <div class="layui-input-inline" style="width:68%">
            <input id="content" name="content"  type="text" class="layui-input" />
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label" style="width:20%;">开始时间:</label>
          <div class="layui-input-inline" style="width:68%">
          	<input type="text" id="startTime" name="startTime" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label" style="width:20%;">结束时间:</label>
          <div class="layui-input-inline" style="width:68%">
          	<input type="text" id="endTime" name="endTime" class="layui-input">
          </div>
        </div>        
    </div>

</body>
</html>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
<script>
layui.use('laydate', function(){
  var laydate = layui.laydate;
  
  var start = {
    min: laydate.now()
    ,max: '2099-06-16 23:59:59'
    ,istoday: false
    ,istime: true
    ,format: 'YYYY-MM-DD hh:mm:ss'
    ,choose: function(datas){
      end.min = datas; //开始日选好后，重置结束日的最小日期
      end.start = datas //将结束日的初始值设定为开始日
    }
  };
  
  var end = {
    min: laydate.now()
    ,max: '2099-06-16 23:59:59'
    ,istoday: false
    ,istime: true
    ,format: 'YYYY-MM-DD hh:mm:ss'
    ,choose: function(datas){
      start.max = datas; //结束日选好后，重置开始日的最大日期
    }
  };
  
  document.getElementById('startTime').onclick = function(){
    start.elem = this;
    laydate(start);
  }
  document.getElementById('endTime').onclick = function(){
    end.elem = this
    laydate(end);
  }
  
});
</script>