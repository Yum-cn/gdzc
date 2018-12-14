<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="ThemeBucket">
  <link rel="shortcut icon" href="#" type="image/png">

  <title>Calendar</title>

  <!--calendar css-->
  <link href="${pageContext.request.contextPath}/webpage/login/assets/js/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />

  <link href="${pageContext.request.contextPath}/webpage/login/assets/css/style.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/webpage/login/assets/css/style-responsive.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="${pageContext.request.contextPath}/webpage/login/assets/js/html5shiv.js"></script>
  <script src="${pageContext.request.contextPath}/webpage/login/assets/js/respond.min.js"></script>
  <![endif]-->
 <style type="text/css">
 body{
 background-color: #eff0f4;
 }
 </style>
</head>

<body>
	<section class="panel">
		<div id="calendar" class="has-toolbar" style="position: absolute;left: 2%;top: 2%;width:96%;"></div>
	</section>
<!-- Placed js at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/modernizr.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery.nicescroll.js"></script>

<script src="${pageContext.request.contextPath}/webpage/login/assets/js/fullcalendar/fullcalendar.js"></script>
<%-- <script src="${pageContext.request.contextPath}/webpage/login/assets/js/external-dragging-calendar.js"></script> --%>

<!--common scripts for all pages-->
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/scripts.js"></script>

</body>
</html>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
<script>
/* initialize the calendar
-----------------------------------------------------------------*/
	layui.use('layer', function(){
	  var layer = layui.layer;
	});              
var date = new Date();
var d = date.getDate();
var m = date.getMonth();
var y = date.getFullYear();

$('#calendar').fullCalendar({
	locale: 'zh-cn',
   header: {
       left: 'prev,next today',
       center: 'title',
       right: 'month,basicWeek,basicDay'
   },
   editable: true,
   droppable: true, // this allows things to be dropped onto the calendar !!!
   drop: function(date, allDay) { // this function is called when something is dropped

       // retrieve the dropped element's stored Event Object
       var originalEventObject = $(this).data('eventObject');

       // we need to copy it, so that multiple events don't have a reference to the same object
       var copiedEventObject = $.extend({}, originalEventObject);

       // assign it the date that was reported
       copiedEventObject.start = date;
       copiedEventObject.allDay = allDay;

       // render the event on the calendar
       // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
       $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

       // is the "remove after drop" checkbox checked?
       if ($('#drop-remove').is(':checked')) {
           // if so, remove the element from the "Draggable Events" list
           $(this).remove();
       }

   },
   events: [
       
       /* eg
       {
           id: 999,
           title: 'Repeating Event',
           start: new Date(y, m, d-3, 16, 0),
           allDay: false
       } */
       
       <c:forEach items="${resultList}" var="resultList">
       {
    	   //el表达式的值要用引号引起来???
    	   id:'${resultList.id}',
           title: '${resultList.content}',
           start: '${resultList.startTime}',
           end: '${resultList.endTime}'
       },
       </c:forEach>
   ],eventClick: function(calEvent, jsEvent, view) {
	   //事件的点击回显
	   var scheduleId = calEvent.id;
	   layer.open({
       	title:'编辑日程',
       	type:2,
       	resize:true,
       	area:['650px','340px'],
		btn:['删除','确定'],
       	//URL 控制器  两种方法都可以
       	content:'scheduleController.do?goUpdate&id='+scheduleId,
       	//content:'${pageContext.request.contextPath}/webpage/com/document/schedule/createNewSchedule.jsp',
       	yes:function(index,layero){
       		   //删除按钮的回调函数
       		   layer.close(index);
       		   $.post(
       			"scheduleController.do?doDel&id="+scheduleId,
       			function(res){
       				var resO=$.parseJSON(res);
					layer.close(index);
					layer.msg(resO.msg, {
			    		  icon: 1,
			    		  time: 1000 //1秒关闭（如果不配置，默认是3秒） 单位是毫秒
			    	  }, function(){
			    		location.reload();
			    	});
       			}
       		   );
       		   
			  },
	    btn2:function(index,layero){
			  var body = layer.getChildFrame('body', index);
			  var content=body.find('#content').val();
			  var startTime=body.find('#startTime').val();
			  var endTime=body.find('#endTime').val();
			  /* var iframeWin = window[layero.find('iframe')[0]['name']];
			  var emailContent=iframeWin.CKEDITOR.instances.emailContent_text.getData(); */
			  $.post(
				"scheduleController.do?doUpdate&id="+scheduleId,
				{content:content,startTime:startTime,endTime:endTime},
				function(res){
					var resO=$.parseJSON(res);
					layer.close(index);
					layer.msg(resO.msg, {
			    		  icon: 1,
			    		  time: 1000 //1秒关闭（如果不配置，默认是3秒） 单位是毫秒
			    	  }, function(){
			    		location.reload();
			    	});
				}
			  );
		  }
       });
   },dayClick: function(date, allDay, jsEvent, view) { 
        var selDate =$.fullCalendar.formatDate(date,'yyyy-MM-dd');//格式化日期 
        //alert(selDate);
        layer.open({
        	title:'创建新的日程',
        	type:2,
        	resize:true,
			area:['650px','340px'],
			btn:['确定'],
        	//URL 控制器  两种方法都可以
        	content:'scheduleController.do?goCreateNewSchedule',
        	//content:'${pageContext.request.contextPath}/webpage/com/document/schedule/createNewSchedule.jsp',
        	yes:function(index,layero){
				  var body = layer.getChildFrame('body', index);
				  var content=body.find('#content').val();
				  var startTime=body.find('#startTime').val();
				  var endTime=body.find('#endTime').val();
				  if(content!=null&&content!=""){
					  $.post(
								"scheduleController.do?saveCreateNewSchedule",
								{content:content,startTime:startTime,endTime:endTime},
								function(res){
									var resO=$.parseJSON(res);
									layer.close(index);
									layer.msg(resO.msg, {
							    		  icon: 1,
							    		  time: 1000 //1秒关闭（如果不配置，默认是3秒） 单位是毫秒
							    	  }, function(){
							    		location.reload();
							    	});
								}
							  );
				  }else{
					  layer.close(index);
				  }
				  
				  /* var iframeWin = window[layero.find('iframe')[0]['name']];
				  var emailContent=iframeWin.CKEDITOR.instances.emailContent_text.getData(); */
				  
			  }
        });
        //com/document/schedule/createNewSchedule.jsp
   }
});


</script>