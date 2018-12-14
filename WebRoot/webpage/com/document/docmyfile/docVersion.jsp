<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="ThemeBucket">
  <link rel="shortcut icon" href="#" type="image/png">

  <title>Basic Table</title>

  <link href="${pageContext.request.contextPath}/webpage/login/assets/css/style.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/webpage/login/assets/css/style-responsive.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="${pageContext.request.contextPath}/webpage/login/assets/js/html5shiv.js"></script>
  <script src="${pageContext.request.contextPath}/webpage/login/assets/js/respond.min.js"></script>
  <![endif]-->
</head>

<body style="background-color: white;">
<!-- <section class="panel"> -->
<!--   <header class="panel-heading"> 操作日志 </header> -->
<!--   <div class="panel-body"> -->
<!--     <section id="flip-scroll"> -->
<!--       <table class="table table-bordered table-striped table-condensed cf"> -->
<!--         <thead class="cf"> -->
<!--           <tr> -->
<!--             <th>收件人</th> -->
<!--             <th class="numeric">抄送</th> -->
<!--             <th class="numeric">状态</th> -->
<!--           </tr> -->
<!--         </thead> -->
<!--         <tbody> -->
<%--           <c:forEach items="${propertyList}" var="propertyList"> --%>
<!--             <tr> -->
<%--               <td>${propertyList.addressee}</td> --%>
<%--               <td class="numeric">${propertyList.carbonCopy}</td> --%>
<%--               <td class="numeric">${propertyList.bpmStatus}</td> --%>
<!--             </tr> -->
<%--           </c:forEach> --%>
<!--         </tbody> -->
<!--       </table> -->
<!--     </section> -->
<!--   </div> -->
<!-- </section> -->
<section class="panel">
  <div class="panel-body">
    <section id="flip-scroll">
      <table class="table table-bordered table-striped table-condensed cf">
        <thead class="cf">
          <tr>
            <th>版本号</th>
            <th>创建人</th>
            <th class="numeric">创建时间</th>
            <th>操作</th>
<!--             <th class="numeric">状态</th> -->
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${versionList}" var="versionList">
            <tr>
              <td>${versionList.versionNumber}</td>
              <td>${versionList.createName}</td>
              <td class="numeric"><fmt:formatDate value="${versionList.createDate}" pattern="yyyy-MM-dd hh:MM:dd"/> </td>
			  <td><a href="javascript:void(0);" onclick="restoreThisVersion('${versionList.id}')">恢复此版本</a></td>
<%--          <td class="numeric">${propertyList.bpmStatus}</td> --%>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </section>
  </div>
</section>
<!-- <section class="panel"> -->
<!--   <header class="panel-heading"> 邮件发送记录 </header> -->
<!--   <div class="panel-body"> -->
<!--     <section id="flip-scroll"> -->
<!--       <table class="table table-bordered table-striped table-condensed cf"> -->
<!--         <thead class="cf"> -->
<!--           <tr> -->
<!--             <th>收件人</th> -->
<!--             <th class="numeric">抄送</th> -->
<!--             <th class="numeric">状态</th> -->
<!--           </tr> -->
<!--         </thead> -->
<!--         <tbody> -->
<%--           <c:forEach items="${propertyList}" var="propertyList"> --%>
<!--             <tr> -->
<%--               <td>${propertyList.addressee}</td> --%>
<%--               <td class="numeric">${propertyList.carbonCopy}</td> --%>
<%--               <td class="numeric">${propertyList.bpmStatus}</td> --%>
<!--             </tr> -->
<%--           </c:forEach> --%>
<!--         </tbody> -->
<!--       </table> -->
<!--     </section> -->
<!--   </div> -->
<!-- </section> -->


<!-- Placed js at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/modernizr.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery.nicescroll.js"></script>

<!--common scripts for all pages-->
<script src="js/scripts.js"></script>

</body>
</html>
<script>
function restoreThisVersion(versionId){
	alert(versionId);
}

</script>
