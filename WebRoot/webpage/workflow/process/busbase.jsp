<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>业务信息</title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="processController.do?saveBus">
   <input name="id" type="hidden" value="${busbase.id}">
   <input name="TPProcess.id" type="hidden" value="${processid}">
   <fieldset class="step">
   <div class="form">
     <label class="Validform_label">
      业务实体:
     </label>
       <select name="TSTable.id">
       <c:forEach items="${tableList }" var="table">
      <option value="${table.id}" <c:if test="${table.id eq busbase.TSTable.id}">selected="selected"</c:if>>
      ${table.tableTitle}
      </option>
      </c:forEach>
     </select>
    </div>
    <div class="form">
      <label class="Validform_label">
      业务名称:
     </label>
     <input name="busname" value="${busbase.busname }" datatype="s2-50" class="inputxt">
     <span class="Validform_checktip">节点名称范围在2~50位字符,且不为空</span>
    </div>
   </fieldset>
  </t:formvalid>
 </body>
</html>
