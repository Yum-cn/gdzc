<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>教师日程管理系统</title>
    <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">

<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">

<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/lib/weui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/css/jquery-weui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/demos/css/demos.css">

  </head>

  <body ontouchstart>



<form name="form1" id="form1" method="post" action="scEventManagerController.do?doAdd">
    <div class="weui-cells_form">
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">事件标题</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input" placeholder="请输入事件标题" name="title">
        </div>
      </div>
      <div class="weui-cell weui-cell_select weui-cell_select-after">
        <div class="weui-cell__hd">
          <label for="" class="weui-label">事件类型</label>
        </div>
        <div class="weui-cell__bd">
			<t:dictSelect field="type" typeGroupCode="JJSJLX" hasLabel="false" extendJson="{'class':'weui-select'}"></t:dictSelect>
        </div>
      </div>
      <div class="weui-cell">
        <div class="weui-cell__hd"><label for="time-format" class="weui-label">事件时间</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input" id="time-format" type="text" name="timestring" value="">
        </div>
      </div>
      <div class="weui-cell">
        <div class="weui-cell__hd"><label for="name" class="weui-label">是否有课</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input" name="isCourse" id="isCourse" type="text" value="无">
        </div>
      </div>            
      <div class="weui-cell">
        <div class="weui-cell__hd"><label for="" class="weui-label"></label></div>
        <div class="weui-cell__bd">
        </div>
      </div>
    </div>
 

    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips" onclick="$('#form1').submit();">保存事件</a>
      <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips" onclick="javascript:location='scheduleManagerController.do?mobileIndex'">返回首页</a>
    </div>
	<br>
	</form>
    <script src="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/lib/jquery-2.1.4.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/lib/fastclick.js"></script>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
<script src="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/js/jquery-weui.js"></script>
<script src="${pageContext.request.contextPath}/webpage/com/sc/schedulemanager/tools.js"></script>
  </body>

</html>
<script>

$("#isCourse").select({
    title: "选择上课节次",
    multi: true,
    min: 1,
    max: 5,
    items: [
      {
        title: "无",
        value: 1,
        description: "无"
      },
      {
        title: "1-2节",
        value: 2,
        description: "1-2节"
      },
      {
        title: "3-4节",
        value: 3,
        description: "3-4节"
      },
      {
        title: "5-6节",
        value: 4,
        description: "5-6节"
      },
      {
        title: "7-8节",
        value: 5,
        description: "7-8节"
      }
    ],
    beforeClose: function(values, titles) {
      return true;
    },
    onChange: function(d) {
      console.log(this, d);
    }
  });
</script>
 <script>
 $("#time-format").datetimePicker({
     title: '选择事件时间',
     yearSplit: '年',
     monthSplit: '月',
     dateSplit: '日',
     times: function () {
       return [  // 自定义的时间
         {
           values: (function () {
             var hours = [];
             for (var i=0; i<24; i++) hours.push(i > 9 ? i : '0'+i);
             return hours;
           })()
         },
         {
           divider: true,  // 这是一个分隔符
           content: '时'
         },
         {
           values: (function () {
             var minutes = [];
             for (var i=0; i<59; i++) minutes.push(i > 9 ? i : '0'+i);
             return minutes;
           })()
         },
         {
           divider: true,  // 这是一个分隔符
           content: '分'
         }
       ];
     },
     onChange: function (picker, values, displayValues) {
       console.log(values);
     }
   });
   $("#time-inline").datetimePicker({
     container: '#time-container',
     onChange: function (picker, values, displayValues) {
       console.log(values);
     }
   })
 </script>
