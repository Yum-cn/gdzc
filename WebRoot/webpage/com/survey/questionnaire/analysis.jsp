<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>确认列表</title>
<t:base type="jquery,easyui,tools"></t:base>
<style>

body {
    width: 600px;
    margin: 40px auto;
    font-family: 'trebuchet MS', 'Lucida sans', Arial;
    font-size: 14px;
    color: #444;
}

table {
    *border-collapse: collapse; /* IE7 and lower */
    border-spacing: 0;
    width: 100%;    
}

.bordered {
    border: solid #ccc 1px;
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    -webkit-box-shadow: 0 1px 1px #ccc; 
    -moz-box-shadow: 0 1px 1px #ccc; 
    box-shadow: 0 1px 1px #ccc;         
}

/*.bordered tr:hover {
    background: #fbf8e9;
    -o-transition: all 0.1s ease-in-out;
    -webkit-transition: all 0.1s ease-in-out;
    -moz-transition: all 0.1s ease-in-out;
    -ms-transition: all 0.1s ease-in-out;
    transition: all 0.1s ease-in-out;     
}    */
    
.bordered td, .bordered th {
    border-left: 1px solid #ccc;
    border-top: 1px solid #ccc;
    padding: 10px;
    text-align: left;    
}

.bordered th {
    background-color: #dce9f9;
    background-image: -webkit-gradient(linear, left top, left bottom, from(#ebf3fc), to(#dce9f9));
    background-image: -webkit-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:    -moz-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:     -ms-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:      -o-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:         linear-gradient(top, #ebf3fc, #dce9f9);
    -webkit-box-shadow: 0 1px 0 rgba(255,255,255,.8) inset; 
    -moz-box-shadow:0 1px 0 rgba(255,255,255,.8) inset;  
    box-shadow: 0 1px 0 rgba(255,255,255,.8) inset;        
    border-top: none;
    text-shadow: 0 1px 0 rgba(255,255,255,.5); 
}

.bordered td:first-child, .bordered th:first-child {
    border-left: none;
}

.bordered th:first-child {
    -moz-border-radius: 6px 0 0 0;
    -webkit-border-radius: 6px 0 0 0;
    border-radius: 6px 0 0 0;
}

.bordered th:last-child {
    -moz-border-radius: 0 6px 0 0;
    -webkit-border-radius: 0 6px 0 0;
    border-radius: 0 6px 0 0;
}

.bordered th:only-child{
    -moz-border-radius: 6px 6px 0 0;
    -webkit-border-radius: 6px 6px 0 0;
    border-radius: 6px 6px 0 0;
}

.bordered tr:last-child td:first-child {
    -moz-border-radius: 0 0 0 6px;
    -webkit-border-radius: 0 0 0 6px;
    border-radius: 0 0 0 6px;
}

.bordered tr:last-child td:last-child {
    -moz-border-radius: 0 0 6px 0;
    -webkit-border-radius: 0 0 6px 0;
    border-radius: 0 0 6px 0;
}






</style>
</head>
<body>
<table class="bordered">
<c:forEach items="${questionLibraryList }" var="questionLibraryList" varStatus="status">
    <tr>
        <th>第${status.index+1}题:${questionLibraryList[4] } 
        <strong style="font-weight: bold;color: red;">
        ${questionLibraryList[3] eq 'JIANDATI'?'[简答题]':'' }
		${questionLibraryList[3] eq 'TIANKONG'?'[填空题]':'' }
		${questionLibraryList[3] eq 'DANXUAN'?'[单选题]':'' }
		${questionLibraryList[3] eq 'DUOXUAN'?'[多选题]':'' }
		${questionLibraryList[3] eq 'PAIXU'?'[排序题]':'' }
		</strong>
        </th>
    </tr>
    <tr>
        <td>
        	<c:if test="${questionLibraryList[3] eq 'TIANKONG' || questionLibraryList[3] eq 'JIANDATI'}">
			  <table class="bordered">
        			<tr>
					    <th>答案文本</th>        
					</tr>
				<c:forEach items="${optionAnswerMap[questionLibraryList[0]] }" var="option" varStatus="status">
					<tr>       
					    <td>${status.index+1}.${option[5] }</td>
					</tr>
				</c:forEach>
			</table>
			</c:if>
			<c:if test="${questionLibraryList[3] eq 'PAIXU'}">
			<p style="color:red;">计算方法为：选项平均综合得分＝（Σ 频数×权值）/本题填写人次</p>
        	<table class="bordered">
        			<tr>
					    <th>选项</th>        
					    <th width="38px">得分</th>
					</tr>
				<c:forEach items="${questionOptionMap[questionLibraryList[0]] }" var="option">
					<tr>
					    <td>${option[2] }

					    </td>        
					    <td>
					    <c:set value="0" var="score"></c:set>
					    <c:forEach items="${sortMap[option[0]] }" var="sort">
					    	<c:set value="${fn:length(questionOptionMap[questionLibraryList[0]])-sort[4]+1}" var="qz"></c:set>
					   		 <c:set value="${score+qz }" var="score"></c:set>
					    </c:forEach>
					    <fmt:formatNumber value="${score/fn:length(sortMap[option[0]])}" pattern="#.##" minFractionDigits="2" />
					    </td>
					</tr>
				</c:forEach>
<!-- 				    <tr> -->
<!-- 					    <td colspan="2" align="center" style="text-align: center;"> -->
<%-- 					    <input type="button" name="Submit" value="饼  图" class="button blue" onclick="previewPieChart('${id}','${questionLibraryList[0] }')"/> --%>
<%-- 						<input type="button" name="Submit" value="柱状图" class="button blue" onclick="previewHistogramChart('${id}','${questionLibraryList[0] }')"/> --%>
<!--  						<input type="button" name="Submit" value="曲线图" class="button blue" onclick="previewDiagramChart('11')"/> --> 
<%-- 						<input type="button" name="Submit" value="条形图" class="button blue" onclick="previewBarChart('${id}','${questionLibraryList[0] }')"/> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
			</table>
			</c:if>
        	<c:if test="${questionLibraryList[3] eq 'DANXUAN' || questionLibraryList[3] eq 'DUOXUAN'}">
        	<table class="bordered">
        			<tr>
					    <th>选项</th>        
					    <th width="38px">票数</th>
					</tr>
				<c:forEach items="${questionOptionMap[questionLibraryList[0]] }" var="option">
					<tr>
					    <td>${option[2] }</td>        
					    <td>${countOptionMap[option[0]] }</td>
					</tr>
				</c:forEach>
				    <tr>
					    <td colspan="2" align="center" style="text-align: center;">
					    <input type="button" name="Submit" value="饼  图" class="scbtn" onclick="previewPieChart('${id}','${questionLibraryList[0] }')"/>
						<input type="button" name="Submit" value="柱状图" class="scbtn" onclick="previewHistogramChart('${id}','${questionLibraryList[0] }')"/>
<!-- 						<input type="button" name="Submit" value="曲线图" class="button blue" onclick="previewDiagramChart('11')"/> -->
						<input type="button" name="Submit" value="条形图" class="scbtn" onclick="previewBarChart('${id}','${questionLibraryList[0] }')"/>
						</td>
					</tr>
			</table>
			</c:if>
		</td>
    </tr>
</c:forEach>

</table>
<br><br><br><br><br>

<script type="text/javascript">
function previewPieChart(id,question_id) {
    var addurl = "questionnaireController.do?pieChart&id=" + id +"&questionId="+question_id;
    $.dialog({
        content: 'url:' + addurl,
        lock: true,
        width: 600,
        height: 380,
        title: "饼图数据分析",
        opacity: 0.3,
        cache: false,
        cancelVal: '关闭',
        cancel: true
    }).max();
}
function previewHistogramChart(id,question_id) {
    var addurl = "questionnaireController.do?histogramChart&id=" + id +"&questionId="+question_id;
    $.dialog({
        content: 'url:' + addurl,
        lock: true,
        width: 600,
        height: 380,
        title: "柱状图数据分析",
        opacity: 0.3,
        cache: false,
        cancelVal: '关闭',
        cancel: true
    }).max();
}
function previewDiagramChart(id,question_id) {
    var addurl = "questionnaireController.do?diagramChart&id=" + id +"&questionId="+question_id;
    $.dialog({
        content: 'url:' + addurl,
        lock: true,
        width: 600,
        height: 380,
        title: "曲线图数据分析",
        opacity: 0.3,
        cache: false,
        cancelVal: '关闭',
        cancel: true
    }).max();
}
function previewBarChart(id,question_id) {
    var addurl = "questionnaireController.do?barChart&id=" + id +"&questionId="+question_id;
    $.dialog({
        content: 'url:' + addurl,
        lock: true,
        width: 600,
        height: 380,
        title: "条形图数据分析",
        opacity: 0.3,
        cache: false,
        cancelVal: '关闭',
        cancel: true
    }).max();
}
</script>
</body>
</html>