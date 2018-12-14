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

    <div class="weui-tab">
      <div class="weui-tab__bd">
        <div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
	        <!-- 日程管理开始 -->
		    <div class=" weui-cells_form" style="display:none;">
		      <div class="weui-cell">
		        <div class="weui-cell__hd" style="display:none;"><label for="date3" class="weui-label">日期</label></div>
		        <div class="weui-cell__bd" style="display:none;">
		          <input class="weui-input" id="date3" type="text">
		        </div>
		      </div>
		    </div>
			<div id="inline-calendar"></div>

			<div class="">
	          <div class="weui-cell">
		        <div class="weui-cell__bd" id="selectDate">${viewDate }</div>
		        <div class="weui-cell__ft" style="font-size: 0">
		          <span style="vertical-align:middle; font-size: 17px;">
		          <a href="scheduleManagerController.do?mobileMyScheduleAdd" class="weui-cell_link"  style="font-size: 17px;">
		            <div class="weui-cell__bd">新建日程</div>
		          </a> 
				  </span>
		        </div>
		      </div>
	          <c:forEach items="${resultList }" var="resultList">
		      <div class="weui-cell weui-cell_access" onclick="opt('${resultList.id }');">
		        <div class="weui-cell__bd">${resultList.title }&nbsp;&nbsp;${resultList.timeString }</div>
		        <div class="weui-cell__ft" style="font-size: 0">
<%-- 		          <span style="vertical-align:middle; font-size: 17px;">${resultList.type }</span> --%>
		        </div>
		      </div>
		      </c:forEach>
		      <div class="weui-cell ">
		        <div class="weui-cell__bd"></div>
		        <div class="weui-cell__ft" style="font-size: 0">
		          <span style="vertical-align:middle; font-size: 17px;"></span>
		        </div>
		      </div>
			</div>
			
 			<!-- 日程管理结束 -->
        </div>
        <div id="tab2" class="weui-tab__bd-item">
			
		
			<div class="">
			
			    <div class="weui-search-bar" id="searchBar">
			      <form class="weui-search-bar__form" method="post" action="scheduleManagerController.do?mobileIndex&tab=2">
			        <div class="weui-search-bar__box">
			          <i class="weui-icon-search"></i>
			          <input type="search" class="weui-search-bar__input" id="" placeholder="搜索" required="" name="eventtitle">
			          <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
			        </div>
			        <label class="weui-search-bar__label" id="searchText" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">
			          <i class="weui-icon-search"></i>
			          <span>搜索</span>
			        </label>
			      </form>
			      <a href="javascript:" class="weui-search-bar__cancel-btn">取消</a>
			    </div>	
			
	          <div class="weui-cell">
		        <div class="weui-cell__bd"></div>
		        <div class="weui-cell__ft" style="font-size: 0">
		          <span style="vertical-align:middle; font-size: 17px;">
			          <a href="scEventManagerController.do?mobileMyEventAdd" class="weui-cell_link"  style="font-size: 17px;">
			            <div class="weui-cell__bd">新建事件</div>
			          </a> 
				  </span>
		        </div>
		      </div>
	        <!-- 紧急事件开始 -->
		     <c:forEach items="${eventList }" var="eventList">
		      <div class="weui-cell weui-cell_access" onclick="opt2('${eventList.id }');">
		        <div class="weui-cell__bd">${eventList.title }&nbsp;&nbsp;${eventList.timestring }</div>
		        <div class="weui-cell__ft" style="font-size: 0">
<%-- 		          <span style="vertical-align:middle; font-size: 17px;">${resultList.type }</span> --%>
		        </div>
		      </div>
		      </c:forEach>
		      
		      <div class="weui-cell ">
		        <div class="weui-cell__bd"></div>
		        <div class="weui-cell__ft" style="font-size: 0">
		          <span style="vertical-align:middle; font-size: 17px;"></span>
		        </div>
		      </div>
		    </div>
			<!-- 紧急事件结束 -->
			
        </div>
        <div id="tab3" class="weui-tab__bd-item">
          <!-- 我的代课开始 -->
          	<div class="">
		      <c:forEach items="${substituteList }" var="substituteList">
		      <div class="weui-cell weui-cell_access" onclick="javascript:location.href='scSubstituteManagerController.do?mobileMySubstituteView&id=${substituteList.id }'">
		        <div class="weui-cell__bd"><fmt:formatDate pattern="yyyy-MM-dd" value="${substituteList.substituteTime }" /></div>
		        <div class="weui-cell__ft" style="font-size: 0">
		          <span style="vertical-align:middle; font-size: 17px;"><t:listDictParse parseId="${substituteList.substituteSection }" style="1" typecode="JIECI"></t:listDictParse></span>
		        </div>
		      </div>
		      </c:forEach>
		      <div class="weui-cell ">
		        <div class="weui-cell__bd"><c:if test="${fn:length(substituteList)<=0}">暂无代课数据</c:if></div>
		        <div class="weui-cell__ft" style="font-size: 0">
		          <span style="vertical-align:middle; font-size: 17px;"></span>
		        </div>
		      </div>
		    </div>
		      
			<!-- 我的代课结束 -->
        </div>
        <div id="tab4" class="weui-tab__bd-item">
          	<!-- 我的信息开始 -->
		     <div class="">
		      <div class="weui-cell ">
		        <div class="weui-cell__bd">用户名</div>
		        <div class="weui-cell__ft" style="font-size: 0">
		          <span style="vertical-align:middle; font-size: 17px;">${user.userName }</span>
		        </div>
		      </div>
		      <div class="weui-cell ">
		        <div class="weui-cell__bd">真实姓名</div>
		        <div class="weui-cell__ft" style="font-size: 0">
		          <span style="vertical-align:middle; font-size: 17px;">${user.realName }</span>
		        </div>
		      </div>
		      <div class="weui-cell ">
		        <div class="weui-cell__bd">所属部门</div>
		        <div class="weui-cell__ft" style="font-size: 0">
		          <span style="vertical-align:middle; font-size: 17px;">${user.currentDepart.departname }</span>
		        </div>
		      </div>  
		      <div class="weui-cell ">
		        <div class="weui-cell__bd"></div>
		        <div class="weui-cell__ft" style="font-size: 0">
		          <span style="vertical-align:middle; font-size: 17px;"></span>
		        </div>
		      </div>                  
			</div>
			<div class="weui-btn-area">
		      <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips" onclick="javascript:location.href='userController.do?mobileChangepassword'">修改密码</a>
		      <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips" onclick="javascript:location.href='loginController.do?logout'">退出系统</a>
		    </div>
 			<!-- 我的信息结束 -->
          
          
        </div>
      </div>

      <div class="weui-tabbar">
        <a href="#tab1" class="weui-tabbar__item weui-bar__item--on"  id="tab1id">
          <div class="weui-tabbar__icon">
            <img src="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/demos/images/icon_nav_button.png" alt="">
          </div>
          <p class="weui-tabbar__label">日程管理</p>
        </a>
        <a href="#tab2" class="weui-tabbar__item" id="tab2id">
          <div class="weui-tabbar__icon">
            <img src="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/demos/images/icon_nav_msg.png" alt="">
          </div>
          <p class="weui-tabbar__label">紧急事件</p>
        </a>
        <a href="#tab3" class="weui-tabbar__item" id="tab3id">
          <c:if test="${fn:length(substituteList)>0}">
          <span class="weui-badge" style="position: absolute;top: -.4em;right: 1em;">${substituteNum}</span>
          </c:if>
          <div class="weui-tabbar__icon">
            <img src="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/demos/images/icon_nav_article.png" alt="">
          </div>
          <p class="weui-tabbar__label">我的代课</p>
        </a>
        <a href="#tab4" class="weui-tabbar__item" id="tab4id">
          <div class="weui-tabbar__icon">
            <img src="${pageContext.request.contextPath}/plug-in/jquery-weui-gh-pages/dist/demos/images/icon_nav_cell.png" alt="">
          </div>
          <p class="weui-tabbar__label">我的信息</p>
        </a>
      </div>
    </div>


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
      $("#date").calendar({
        onChange: function (p, values, displayValues) {
          console.log(values, displayValues);
        }
      });
      $("#date2").calendar({
        value: ['2016-12-12'],
        dateFormat: 'yyyy年mm月dd日'  // 自定义格式的时候，不能通过 input 的value属性赋值 '2016年12月12日' 来定义初始值，这样会导致无法解析初始值而报错。只能通过js中设置 value 的形式来赋值，并且需要按标准形式赋值（yyyy-mm-dd 或者时间戳)
      });
      $("#date-multiple").calendar({
        multiple: true,
        onChange: function (p, values, displayValues) {
          console.log(values, displayValues);
        }
      });
      $("#date3").calendar({
    	  //multiple: true,
    	  //value: ['2017-01-05','2017-01-06','2017-01-07'],
    	 //value:['2017-01-01,2017-01-02','2017-01-03'],
    	value: ${dateValues},
    	//minDate:'2017-12-12',
        container: "#inline-calendar",
        onChange: function (p, values, displayValues) {
        	//alert(values);
            console.log(values, displayValues);
        },
        onDayClick: function (p, dayContainer, year, month, day) {
        	month = eval(month) + 1;
        	month = month < 10 ? '0' + month : month;
        	day = day < 10 ? '0' + day : day;
        	$("#selectDate").html(year+"-"+month+"-"+day);
        	var selectDate = year+"-"+month+"-"+day;
        	location.href="scheduleManagerController.do?mobileIndex&selectDate="+selectDate;
      		//alert("onDayClick:"+day);
          //console.log(values, displayValues);
      }
      });
    </script>
    
    
      <script>
     
      function opt(id){
          $.actions({
              title: "日程操作",
              onClose: function() {
                console.log("close");
              },
              actions: [
                {
                  text: "编辑",
                  className: "color-warning",
                  onClick: function() {
                	  location.href='scheduleManagerController.do?mobileMyScheduleUpdate&id='+id;
                  }
                },
                {
                  text: "删除",
                  className: 'color-danger',
                  onClick: function() {
                      $.confirm("您确定要删除这条日程吗?", "确认删除?", function() {
                    	  	location.href='scheduleManagerController.do?doDel&id='+id;
                          }, function() {
                            //取消操作
                      });
                	  
                  }
                }
              ]
            });
      }
      function opt2(id){
          $.actions({
              title: "事件操作",
              onClose: function() {
                console.log("close");
              },
              actions: [
                {
                  text: "编辑",
                  className: "color-warning",
                  onClick: function() {
                	  location.href='scEventManagerController.do?mobileMyEventUpdate&id='+id;
                  }
                },
                {
                  text: "删除",
                  className: 'color-danger',
                  onClick: function() {
                      $.confirm("您确定要删除这条事件吗?", "确认删除?", function() {
                    	  	location.href='scEventManagerController.do?doDel&id='+id;
                          }, function() {
                            //取消操作
                      });
                	  
                  }
                }
              ]
            });
      }
      function opt1(){
          $.actions({
              title: "业务操作",
              onClose: function() {
                console.log("close");
              },
              actions: [
                {
                  text: "处理",
                  className: "color-warning",
                  onClick: function() {
                    location.href='scheduleManagerController.do?mobileMySubstituteForm';
                  }
                }
              ]
            });
      }
      $("#time-format").datetimePicker({
          title: '自定义格式',
          yearSplit: '-',
          monthSplit: '-',
          dateSplit: '',
          times: function () {

          },
          onChange: function (picker, values, displayValues) {
            console.log(values);
          }
        });
      $(function(){
    	  var defaultTab="<%=request.getParameter("tab")%>";
    	  if(defaultTab=='2'){
    		  $("#tab1").removeClass('weui-tab__bd-item--active');
    		  $("#tab2").addClass('weui-tab__bd-item--active');
    		  $("#tab1id").removeClass('weui-bar__item--on');
    		  $("#tab2id").addClass('weui-bar__item--on');
    		  
    	  }
      });
    </script>
    