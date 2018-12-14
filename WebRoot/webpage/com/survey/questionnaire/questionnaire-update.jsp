<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>问卷管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="questionnaireController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${questionnairePage.id }">
					<input id="createUser" name="createUser" type="hidden" value="${questionnairePage.createUser }">
					<input id="createTime" name="createTime" type="hidden" value="${questionnairePage.createTime }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable" id="content">
					<tr>
						<td align="right" width="120px">
							<label class="Validform_label">
								问卷名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="name" name="name" type="text" style="width: 350px" class="inputxt" value='${questionnairePage.name}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">问卷名称</label>
						</td>
						
					</tr>
					<tr>
						<td align="right"  width="120px">
							<label class="Validform_label">
								导语:
							</label>
						</td>
						<td class="value">
								<textarea name="synopsis" id="synopsis" style="width: 350px;height:100px">${questionnairePage.synopsis}</textarea>
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">导语</label>
							</td>
					</tr>
					<tr>
						<td align="right"  width="120px">
							<label class="Validform_label">
								试题列表:
							</label>
						</td>
						<td class="value">
							<a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onClick="choose_402893814fcb1a65014fcb1bef100003()">
							    选择试题
							</a>
						</td>
					</tr>
					<c:forEach items="${questionList }" var="questionList">
					<tr> <input type="hidden" name="questionId" class="inputxt" value="${questionList[1] }" id="questionId">
					    <td align="right">
					        <label class="Validform_label">简答题:</label>
					    </td>
					    <td class="value" valign="middle">${questionList[2] }&nbsp;&nbsp;
					        <img src="${webRoot }/webfront/images/icon/delete.png" onclick="remove_line(this);" width="18px" border="0" />
					    </td>
					</tr>
					</c:forEach>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/train/questionnaire/questionnaire.js"></script>	
  		<script type="text/javascript">
		    var windowapi = frameElement.api,
		    W = windowapi.opener;
		    function choose_402893814fcb1a65014fcb1bef100003() {
		    	var questionIdObj = $("input[name='questionId']");
		    	var questionId = "";
		    	if(questionIdObj != undefined){
			    	for(var i = 0;i < questionIdObj.length;i++){
			    		questionId = questionId + "," + $(questionIdObj[i]).val();
			    	}
			    	questionId = questionId.substr(1,questionId.length);
		    	}
		    	
		        if (typeof(windowapi) == 'undefined') {
		            $.dialog({
		                content: 'url:questionLibraryController.do?selectQuestionLibrary&qid='+questionId,
		                zIndex: 2100,
		                title: '题库列表',
		                lock: false,
		                width: 400,
		                height: 350,
		                left: '85%',
		                top: '65%',
		                opacity: 0.4,
		                button: [{
		                    name: '确认',
		                    callback: clickcallback_402893814fcb1a65014fcb1bef100003,
		                    focus: true
		                },
		                {
		                    name: '取消',
		                    callback: function() {}
		                }]
		            }).max();
		        } else {
		            $.dialog({
		                content: 'url:questionLibraryController.do?selectQuestionLibrary&qid='+questionId,
		                zIndex: 2100,
		                title: '题库列表',
		                lock: false,
		                parent: windowapi,
		                width: 400,
		                height: 350,
		                left: '85%',
		                top: '65%',
		                opacity: 0.4,
		                button: [{
		                    name: '确认',
		                    callback: clickcallback_402893814fcb1a65014fcb1bef100003,
		                    focus: true
		                },
		                {
		                    name: '取消',
		                    callback: function() {}
		                }]
		            }).max();
		        }
		    }

		    function clickcallback_402893814fcb1a65014fcb1bef100003() {
		        iframe = this.iframe.contentWindow;
		        var questionTitle = iframe.getquestionLibraryListSelections('title');
		        var questionStyle = iframe.getquestionLibraryListSelections('style');
		        var questionIds = iframe.getquestionLibraryListSelections('id');
		        //$("#qid").val(questionIds);
		        if (questionTitle != undefined && questionTitle != "") {
		            for(var i=0;i<questionTitle.length;i++){
		            	var styleName = "";
		            	if(questionStyle[i]=='DANXUAN')
		            		styleName='单选题';
		            	if(questionStyle[i]=='DUOXUAN')
		            		styleName='多选题';
		            	if(questionStyle[i]=='PAIXU')
		            		styleName='排序题';
		            	if(questionStyle[i]=='TIANKONG')
		            		styleName='填空题';
		            	if(questionStyle[i]=='JIANDATI')
		            		styleName='简答题';
			    		var html ='<tr><input type="hidden" name="questionId" class="inputxt" value="'+questionIds[i]+'" id="questionId"  />';
			    		html = html+ '		<td align="right">';
			    		html = html+ '		<label class="Validform_label">';
			    		html = html+ '			'+styleName+':';
			    		html = html+ '		</label>';
			    		html = html+ '	</td>';
			    		html = html+ '		<td class="value" valign="middle">';
			    		html = html+ '		     	'+questionTitle[i]+'&nbsp;&nbsp;<img src="${webRoot }/webfront/images/icon/delete.png" onclick="remove_line(this);" width="18px" border="0" />';
			    		html = html+ '				<span class="Validform_checktip"></span>';
			    		html = html+ '				<label class="Validform_label" style="display: none;">题目标题</label>';
			    		html = html+ '		</td>';
			    		html = html+ '	</tr>';
			    		$("#content").append(html);
		            }
		        }
		        //alert($("#content").html());
		    }
			function remove_line(index) {
				$(index).parent().parent().remove();
			}
		</script>
 </html>			