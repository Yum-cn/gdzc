<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <t:base type="ckeditor,jquery,tools,DatePicker"></t:base>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="ThemeBucket">
  <link rel="shortcut icon" href="#" type="image/png">

  <title>Mail Compose</title>

  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webpage/login/assets/js/bootstrap-wysihtml5/bootstrap-wysihtml5.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>
  <link href="${pageContext.request.contextPath}/webpage/login/assets/css/style.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/webpage/login/assets/css/style-responsive.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="${pageContext.request.contextPath}/webpage/login/assets/js/html5shiv.js"></script>
  <script src="${pageContext.request.contextPath}/webpage/login/assets/js/respond.min.js"></script>
  <![endif]-->
  <meta charset="utf-8">
</head>

<body>

<!--body wrapper start-->
                <section class="mail-box-info">
                    <section class="mail-list"  style="margin-top: 6px;height: 487px;">
                        <div class="compose-mail">
                            <form class="layui-form" id="layui-form" method="post" action="docLibraryCommonController.do?savePermission">
                                <input name="packageId" id="packageId" value="${id}" type="hidden">
                                <!-- 删除的人员ID字符串 隐藏域 -->
	                            <input type='hidden' name='delUserIds' id="delUserIds"/>
                                
                                <button class="layui-btn layui-btn-normal" onclick="selectUser()" type="button">选择人员</button>
                                <button id="submitBTN" class="layui-btn layui-btn-warm"  onclick="submitForm()" type="button">保存</button>	
                                	<table class="layui-table">
                                		<thead>
                                			<tr>
                                				<th style="width:120px">姓名</th>
                                				<th style="width:400px">权限</th>
                                				<th style="width:90px">操作</th>
                                			</tr>
                                		</thead>
                                		<tbody id="appendUser">
                                			<c:if test="${docPermissionList!='0'}">
                                				<c:forEach items="${docPermissionList}" var="docPermissionList" varStatus="i">
                                					<tr id="trId_${docPermissionList.userId}">
                                						<input type='hidden' name='primarykeyIds' value='${docPermissionList.id}'/>
                                						<input type='hidden' name='userIds' value='${docPermissionList.userId}'/>
														<input type='hidden' name='usernames' value='${docPermissionList.userName}'/>
														<input type='hidden' name='permissions' id="userId_${docPermissionList.userId}" value=""/>
                                						<td>${docPermissionList.userName}</td>
                                						<td>
                                							<input type="checkbox" name="userPermissions" title="编辑" value="0" ${docPermissionList.permissionEdit=='0'?'checked':''}>
															<input type="checkbox" name="userPermissions" title="阅读" value="1" ${docPermissionList.permissionRead=='1'?'checked':''} >
															<input type="checkbox" name="userPermissions" title="上传" value="2" ${docPermissionList.permissionUpload=='2'?'checked':''} >
															<input type="checkbox" name="userPermissions" title="打印" value="3" ${docPermissionList.permissionPrint=='3'?'checked':''} >
														</td>
														<td>
	                                						<button  type="button" onclick="delThisUser('trId_${docPermissionList.userId}')" class="layui-btn layui-btn-small layui-btn-primary">
															<i class="layui-icon">&#xe640;</i>
															</button>
														</td>
                                					</tr>
                                				</c:forEach>
                                			</c:if>
                                		</tbody>
                                	</table>
                            </form>
                        </div>
                    </section>
                </section>

        <!--body wrapper end-->

<!-- Placed js at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/modernizr.min.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/jquery.nicescroll.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/webpage/login/assets/js/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webpage/login/assets/js/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>


<!--common scripts for all pages-->
<script src="${pageContext.request.contextPath}/webpage/login/assets/js/scripts.js"></script>
<script>
    jQuery(document).ready(function(){
        $('.wysihtml5').wysihtml5();
    });
</script>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/lhgDialog/lhgdialog.min.js?skin=metrole"></script>
<script type="text/javascript">
// 	var windowapi = frameElement.api, W = windowapi.opener;
	ids = new Array();
    //加载layer
    layui.use(['layer','form'], function(){
  	  var layer = layui.layer;
  	  var form = layui.form();
  	});
    
	function selectUser() {
		$.dialog({
			content : 'url:userController.do?selectUserCheckBox',
			lock : true,
			width : 1000,
			height : 450,
			title : "选择授权人员",
			opacity : 0.3,
			cache : false,
			cancelVal : '关闭',
			cancel : true, /*为true等价于function(){}*/
			button : [ {
				name : '确定',
				callback : clickcallback_402880e858ceec720158cef6614a0007,
				focus : true
			} ]
		});
	}

	function clickcallback_402880e858ceec720158cef6614a0007() {
		iframe = this.iframe.contentWindow;
		var users = new Array();
		users = iframe.getuserListSelections('realName');
		
	    ids = iframe.getuserListSelections('id');
		if(ids.length>0 && ids.length!=undefined){
			for(var i=0;i<ids.length;i++){
				var str="";  
				str+="<tr id=\""+ids[i]+"\">";
				str+="<input type='hidden' name='primarykeyIds'/>"; // 主键隐藏域    无值 执行save方法  有值执行update方法
				str+="<input type='hidden' name='userIds' value='"+ids[i]+"'/>"; //人员ID的隐藏域
				str+="<input type='hidden' name='usernames' value='"+users[i]+"'/>"; //人员名称的隐藏域
				str+="<input type='hidden' name='permissions' id='userId_"+ids[i]+"' value=''/>"; //权限的隐藏域
				str+="<td>"+users[i]+"</td>";
				str+="<td><input type=\"checkbox\" name=\"userPermissions\" title=\"编辑\" value=\"0\">";
				str+="<input type=\"checkbox\" name=\"userPermissions\" title=\"阅读\" value=\"1\" checked>";
				str+="<input type=\"checkbox\" name=\"userPermissions\" title=\"上传\" value=\"2\">";
				str+="<input type=\"checkbox\" name=\"userPermissions\" title=\"打印\" value=\"3\"></td>";
				str+="<td><button  type=\"button\" onclick=\"delThisUser('"+ids[i]+"')\" class=\"layui-btn layui-btn-small layui-btn-primary\">";
				str+="<i class=\"layui-icon\">&#xe640;</i>";
				str+="</button></td>";
				str+="</tr>";
				$("#appendUser").append(str);
			}
			//框架问题  动态更新  需要再次加载渲染
			layui.use('form', function(){
				  var form = layui.form(); //只有执行了这一步，部分表单元素才会修饰成功
				  form.render();  //重新渲染
			}); 
			
		}
	}
	
	
	//删除某行
	function delThisUser(trId){
		var delId = $("#"+trId+" input[name='primarykeyIds']").val();
		var delStr = $("#delUserIds").val();
		delStr+=delId+"@";
		$("#delUserIds").val(delStr);
		$("#"+trId+"").remove();
	}
	//提交form
	function submitForm(){
		var tbodyVal = $("#appendUser").html();
		tbodyVal=tbodyVal.replace(/(^\s*)|(\s*$)/g, "");
		
		//alert("tbodyVal能不能看到C标签"+tbodyVal); //   tbody的val() 看不到C标签
		//if(tbodyVal!=""){
			//获取已选中的复选框的值
			var userLength = $("#appendUser tr").length;
			//alert("tbody中tr的数量是+++++"+$("#appendUser tr").length);
			for(var j=0;j<userLength;j++){
				var ss = $("#appendUser tr:eq("+j+") input[name='userPermissions']:checked").map(function(index,elem){
					return $(elem).val();
				}).get().join(',');
				//获取每一条userID 拼接权限的ID属性
				var userId = $("input[name='userIds']")[j].value; //数组
				$("#userId_"+userId+"").val(ss);
			}
			
			//提交表单  关闭当前弹出层
			layer.msg('权限设置成功!',{time:2000,icon:1},function(){
				//提交form
				$("#layui-form").submit();
    			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    			parent.layer.close(index); //再执行关闭   
    		});
		//}else{
		//	layer.msg('请选择人员!!',{icon:2,anim:6});
		//}
	}
	
</script>

