<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC"-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1">
<meta name="format-detection" content="telephone=no" />
<title>${questionnaire.name }</title>
<link rel="stylesheet" href="${webRoot }/webfront/style/html5/jqmobo.css?v=4"/>
<script type="text/javascript" src="${webRoot }/plug-in/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src = "${webRoot }/webfront/js/hintinfo.js" ></script>
<script src="${webRoot }/webfront/js/jqmobo3.js?v=98" type="text/javascript"></script>
</head>
<body>
<form id="form1" name="form1" method="post" action="questionnaireController.do?saveAnswer" onsubmit="return onCheck();">
<input type='hidden' value='${id }' name='id'>
<div id="toptitle">
  <h1 class="htitle">${questionnaire.name } </h1>
</div>

<div id="divContent">
        <div id="divDesc" class="formfield">
            <span class="description">
                ${questionnaire.synopsis}
        </div>

  <div id="divQuestion">
    <fieldset class='fieldset' style='' id='fieldset1'>
    <c:forEach items="${questionMap }" var="questionMap" varStatus="status">
    <c:if test="${questionMap.key[3] eq 'DANXUAN' }">
    <div class='field ui-field-contain' id='div1' req='1' topic='1' data-role='fieldcontain' type='3'>
          <div class='field-label'> ${status.index+1 }.${questionMap.key[2] } <span class='qtypetip'>&nbsp;[单选题]</span></div>
		      <div class='ui-controlgroup'>
		         	<c:forEach items="${questionMap.value}" var="optionList">
				    <div class='ui-radio'><span class='jqradiowrapper'>
				      <input type='radio' value='${optionList[0] }' id='q1_1' name='q${status.index+1 }' style='display:none;'/>
				      <a class='jqradio' href='javascript:;'></a></span>
				      <label  for='q1_1'> ${optionList[2] } </label>
				    </div>
				    </c:forEach>
			</div>
	      <div class='errorMessage'> </div>
	    </div>
    </c:if>
    
    
    <c:if test="${questionMap.key[3] eq 'DUOXUAN' }">
	    <div class='field ui-field-contain' id='div1' req='1' topic='1' data-role='fieldcontain' type='4'>
	      <div class='field-label'> ${status.index+1 }.${questionMap.key[2] } <span class='qtypetip'>&nbsp;[多选题]</span></div>
		      <div class='ui-controlgroup'>
		    	<c:forEach items="${questionMap.value}" var="optionList">
			    	<div class='ui-checkbox'> <span class='jqcheckwrapper'>
				      <input type='checkbox' value='${optionList[0] }' id='q2_1' name='q${status.index+1 }' style='display:none;'/>
			          <a class='jqcheck' href='javascript:;'> </a> </span>
			          <label for='q2_1'> ${optionList[2] } </label>
			        </div>
		        </c:forEach>
	      </div>
	      <div class='errorMessage'> </div>
	    </div>
	    
    </c:if>
    <c:if test="${questionMap.key[3] eq 'PAIXU' }">
      <div class='field ui-field-contain' id='div5' req='1' topic='5'  minvalue='-1' maxvalue='-1' data-role='fieldcontain' type='11'>
        <div class='field-label'> ${status.index+1 }.${questionMap.key[2] }  <span class='qtypetip'>&nbsp;[排序题]</span></div>
        <ul class='ui-controlgroup ui-listview'>
        <c:forEach items="${questionMap.value}" var="optionList" varStatus="optionStatus">
          <li class='ui-li-static' style='cursor:pointer;'>
            <input type='hidden' class='custom' value='' id='q_${optionStatus.index+1 }' name='q${status.index+1 }'>
            <input type='hidden' value='${optionList[0] }' id='q_${optionStatus.index+1 }' name='q${status.index+1 }_id'>
            <span class='sortnum'></span><span>${optionList[2] }</span> </li>
        </c:forEach>

        </ul>
        <div class='errorMessage'></div>
      </div>
    </c:if>
    <c:if test="${questionMap.key[3] eq 'JIANDATI' }">
	  <div class='field ui-field-contain' id='div3'  topic='3'  data-role='fieldcontain' type='1'>
        <div class='field-label'> ${status.index+1 }.${questionMap.key[2] } <span class='qtypetip'>&nbsp;[简答题]</span></div>
        <div class='ui-input-text'>
<!--           <input type='text' id='q3' value='' name='q3' style="height:50px;"/> -->
				<textarea name="q${status.index+1 }" id="q${status.index+1 }" style="height:80px;"></textarea>
				<input type="hidden" value="${questionMap.key[0] }" name="q${status.index+1 }_id" style="max-width:80px;" class="ui-input-text"/>
        </div>
        <div class='errorMessage'></div>
      </div>
    </c:if>
    <c:if test="${questionMap.key[3] eq 'TIANKONG' }">
      <div class='field ui-field-contain' id='div4' req='1' topic='4'  data-role='fieldcontain' type='9'>
        <div class='field-label'> ${status.index+1 }.
<c:set var="newInput" value='<input type="text" value="" name="q${status.index+1 }" id="q${status.index+1 }" style="max-width:80px;" class="ui-input-text"/>'></c:set>
<input type="hidden" value="${questionMap.key[0] }" name="q${status.index+1 }_id" style="max-width:80px;" class="ui-input-text"/>
        ${fn:replace(questionMap.key[2], '__', newInput)}
        <span class='qtypetip'>&nbsp;[填空题]</span></div>
        <div class='errorMessage'></div>
      </div>
    </c:if>

        

       
    </c:forEach>
    
    

    </fieldset>
  </div>
  <div class="footer">
    <div class="ValError"> </div>
    <div id="divSubmit" style="padding: 10px; "> <a id="ctlNext" href="javascript:;" onclick="$('#form1').submit();" class="button blue"> 提交 </a> </div>
  </div>
</div>
</form>
<script type="text/javascript">
    var totalPage = 1;

</script>
<script type="text/javascript">
    var submit_text = $("#yucinput")[0];
    var tCode = $("#" + tdCode)[0];
</script>
<script>
function onCheck(){
<c:forEach items="${questionMap }" var="questionMap" varStatus="status">

<c:if test="${questionMap.key[3] eq 'JIANDATI' }">
var val=$("#q${status.index+1}").val();
if(val==undefined || val==''){
	alert("信息填写不完整");
	return false;
}
</c:if>

<c:if test="${questionMap.key[3] eq 'TIANKONG' }">
var val=$("#q${status.index+1 }").val();
if(val==undefined || val==''){
	alert("信息填写不完整");
	return false;
}
</c:if>

<c:if test="${questionMap.key[3] eq 'DANXUAN' }">
	var val=$('input:radio[name="q${status.index+1 }"]:checked').val();
	if(val==undefined){
		alert("信息填写不完整");
		return false;
	}
</c:if>

<c:if test="${questionMap.key[3] eq 'DUOXUAN' }">
	var val=$('input:checkbox[name="q${status.index+1 }"]:checked').val();
	if(val==undefined){
		alert("信息填写不完整");
		return false;
	}
</c:if>

</c:forEach>
return true;
}
</script>
</body>
</html>
