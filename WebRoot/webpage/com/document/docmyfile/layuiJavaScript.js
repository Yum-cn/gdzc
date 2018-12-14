var pId=$("#pId").val();

	//上传文件
	
		layui.use('upload', function(){
			var loading;
			layui.upload({
				elem: '#fileElem',
			    url: 'docMyFileController.do?uploadFile&pId='+pId //上传接口
			    ,before:function(input){
			    	loading = layer.load();
			    }
			    ,success: function(res){ //上传成功后的回调
			    	layer.close(loading);   
			    	layer.msg(res.msg, {
			    		  icon: 1,
			    		  time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
			    		}, function(){
			    			location.reload();
			    		});   
			    }
			  });
			layui.upload({
				elem: '#fileZIPElem',
			    url: 'docMyFileController.do?uploadFileZip&pId='+pId //上传接口
			    ,before:function(input){
			    	
			    	loading = layer.load();
			    }
			    ,success: function(res){ //上传成功后的回调
			    	layer.close(loading);   
			    	layer.msg(res.msg, {
			    		  icon: 1,
			    		  time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
			    		}, function(){
			    			location.reload();
			    		});   
			    }
			  });
		});
		
   
	
	//重命名
	function reName(id,name){
		//alert("测试JS");
		layer.prompt({
			  formType:0,
			  value:name,
			  title:"请输入文件名"},
			  function(value, index, elem){
				  $.post("docMyFileController.do?reName",
						  {fileName:value,id:id},
						  function(res){
							  var resObj = $.parseJSON(res);
							  layer.close(index);
							  layer.msg(res.msg, {
					    		  icon: 1,
					    		  time: 1000 //2秒关闭（如果不配置，默认是3秒）
					    	  }, function(){
					    			location.reload();
					    	}); 
						  });
			});
	}
	
	
	//移动 
	function fileTree(id){
		layer.open({
			content:'docMyFileController.do?fileTree',
			type:2,
			resize:true,
			area:['400px','500px'],
			btn:['确定'],
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var toMovePackageId=body.find('#toMovePackage').val();
				$.post(
					"docMyFileController.do?doMoveFile",
					{newPId:toMovePackageId,fileId:id},
					function(res){
						var resO=$.parseJSON(res);
						layer.close(index);
						layer.msg(resO.msg, {
				    		  icon: 1,
				    		  time: 1000 //1秒关闭（如果不配置，默认是3秒） 单位是毫秒
				    	  }, function(){
				    		 window.parent.location.reload();
				    	}); 
					}
				);
			},
			closeBtn:1
		});
	}

	
	//复制文件
	function copyFile(id){
		layer.open({
			content:'docMyFileController.do?fileTree',//这个请求是为了查询出  菜单树
			type:2,
			resize:true,
			area:['400px','500px'],
			btn:['确定'],
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var toMovePackageId=body.find('#toMovePackage').val();
				$.post(
					"docMyFileController.do?doCopyFile",
					{newPId:toMovePackageId,fileId:id},
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
			closeBtn:1
		});
	}
	
	//新建文件夹
	function createPackage(){
		//判断  当点击进入文档后没有任何操作时的根目录  默认设置为'我的文档'的ID(1)
		if(pId==''||pId==null){
			pId="1";
		}
		layer.prompt({
			  formType:0,
			  title:"请输入文件名"},
			  function(value, index, elem){
				  if(value!=""&&value!=undefined){
					  $.post(
							"docMyFileController.do?createPackage",
							{pId:pId,pName:value},
							function(res){
								var resO=$.parseJSON(res);
								layer.close(index);
								layer.msg(resO.msg, {
						    		  icon: 1,
						    		  time: 1000 //1秒关闭（如果不配置，默认是3秒） 单位是毫秒
						    	  }, function(){
						    		  //刷新ztree的树节点
						    		  window.parent.location.reload();
						    	}); 
							}
							);
				  }else{
					  layer.alert('文件夹名不能为空!!', {icon: 2}); 
				  }
			});
	}
	
	//删除文件夹   逻辑删除
	
	function delPackage(id){
		$.post(
				"docMyFileController.do?queryChildFile",
				{pId:id},
				function(res){
					var resJSON = $.parseJSON(res);
					layer.close(index);
					layer.msg(resJSON.msg,{time:2000,icon:1},function(){
						location.reload();//刷新页面
					});
				}
			 );
		
		layer.confirm('您确认要删除此文件夹目录至回收站吗?',
				{btn:['是','否']}
			  , function(index){
				  $.post(
						"docMyFileController.do?falseDelPackage",
						{packageId:id},
						function(res){
							var resJSON = $.parseJSON(res);
							layer.close(index);
							layer.msg(resJSON.msg,{time:2000,icon:1},function(){
								location.reload();//刷新页面
							});
						}
					 );
			  
			}); 
		
	}
	
	
	//重命名文件夹
	
	function reNamePackage(pId){
		 $.post(
			"docMyFileController.do?queryPackageName",
			{packageId:pId},
			function(res){
				var res =$.parseJSON(res);
				if(res.success){
					//重命名的弹出窗
					layer.prompt({
						  formType:0,
						  value:res.attributes.packageName,
						  title:"请输入文件夹名"},
						  function(value, index, elem){
							  $.post("docMyFileController.do?rePackageName",
									  {newPackageName:value,id:pId},
									  function(res){
										  var res=$.parseJSON(res);
										  layer.close(index);
										  layer.msg(res.msg, {
								    		  icon: 1,
								    		  time: 1000 //2秒关闭（如果不配置，默认是3秒）
								    	  }, function(){
								    			location.reload();
								    	}); 
									  });
						});
				}else{
					layer.msg('请选择文件夹',{anim:6,icon:5});
				}
			}
		); 
	}
	
	
	//分享   调用PDFJS
	
// 	function sharePDF(pdfURL){
// 		/* var point = pdfURL.lastIndexOf(".");  //最后一个小数点的位置
// 		var type = pdfURL.substr(0,point); */
// 		window.open('${pageContext.request.contextPath}/plug-in/pdfjs/web/viewer.html?file=${pageContext.request.contextPath}/'+pdfURL,'PDF','width:50%;height:50%;top:100;left:100;');
// 	}
	
	
	//移动文件夹
	
	function movePackage(packageId){
		layer.open({
			content:'docMyFileController.do?fileTree',//这个请求是为了查询出  菜单树
			type:2,
			resize:true,
			area:['400px','500px'],
			btn:['确定'],
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var toMovePackageId=body.find('#toMovePackage').val();
				$.post(
						"docMyFileController.do?doMovePackage",
						{newPackageId:toMovePackageId,packageId:packageId},
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
			closeBtn:1
		});
		
	}
	//发送邮箱
	function sentEmail(id,fileName){
			fileName = encodeURI(encodeURI(fileName));  
			layer.open({
			  title:"邮件正文",
			  content: 'docEmailController.do?goSentEmail&id='+id+'&fileName='+fileName, //content可以是string也可以是URL
			  type: 2,
			  resize:true,
			  area:['800px','500px'],
			  btn:['发送邮件'],
			  yes:function(index,layero){
				  var body = layer.getChildFrame('body', index);
				  var addressee=body.find('#addressee').val();
				  var carbonCopy=body.find('#carbonCopy').val();
				  var emailTitle=body.find('#emailTitle').val();
				  var iframeWin = window[layero.find('iframe')[0]['name']];
				  var emailContent=iframeWin.CKEDITOR.instances.emailContent_text.getData();
				  //邮箱验证的正则
				  var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
				  if(!myreg.test(addressee)){
					  layer.msg('请输入正确的邮箱格式',{icon:2,anim:6}); 
				  }else{
					  $.post(
								"docEmailController.do?doSentEmail",
								{addressee:addressee,carbonCopy:carbonCopy,emailTitle:emailTitle,emailContent:emailContent,fileId:id},
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
			  }
			  
			}); 
		
	}
	
	//发布协同
	function sentTeamwork(id,fileName){
			fileName = encodeURI(encodeURI(fileName));  
			layer.open({
			  title:"发送文档协作",
			  content: 'messageController.do?goTeamwork&id='+id+'&fileName='+fileName, //content可以是string也可以是URL
			  type: 2,
			  resize:true,
			  area:['800px','500px'],
			  btn:['发送协作'],
			  yes:function(index,layero){
				  var body = layer.getChildFrame('body', index);
				  var receiveUser=body.find('#receiveUser').val();
				  var receiveUserId=body.find('#receiveUserId').val();
// 				  var carbonCopy=body.find('#carbonCopy').val();
				  var title=body.find('#title').val();
				  var fileType=body.find('#fileType').val();
				  var iframeWin = window[layero.find('iframe')[0]['name']];
				  var content=iframeWin.CKEDITOR.instances.content_text.getData();
// 				  var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
// 				  if(!myreg.test(addressee)){
// 					  layer.msg('请输入正确的邮箱格式',{icon:2,anim:6}); 
// 				  }else{
					  $.post(
								"messageController.do?doTeamwork",
								{receiveUser:receiveUser,receiveUserId:receiveUserId,title:title,content:content,documentId:id,fileType:fileType},
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
// 				  }
			  }
			  
			}); 
		
	}
	
	//内部群发
	function sentMass(id,fileName){
			fileName = encodeURI(encodeURI(fileName));  
			layer.open({
			  title:"发送文档",
			  content: 'messageController.do?goMass&id='+id+'&fileName='+fileName, //content可以是string也可以是URL
			  type: 2,
			  resize:true,
			  area:['800px','500px'],
			  btn:['发送文档'],
			  yes:function(index,layero){
				  var body = layer.getChildFrame('body', index);
				  var receiveUser=body.find('#receiveUser').val();
				  var receiveUserId=body.find('#receiveUserId').val();
// 				  var carbonCopy=body.find('#carbonCopy').val();
				  var title=body.find('#title').val();
				  var fileType=body.find('#fileType').val();
				  var usePermission="";
				  body.find('input[name="usePermission"]:checkbox').each(function(){ 
					  if ("checked" == $(this).attr("checked")) {
						  usePermission = usePermission +","+ $(this).val();
					  }
				  })
				  var iframeWin = window[layero.find('iframe')[0]['name']];
				  var content=iframeWin.CKEDITOR.instances.content_text.getData();
// 				  var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
// 				  if(!myreg.test(addressee)){
// 					  layer.msg('请输入正确的邮箱格式',{icon:2,anim:6}); 
// 				  }else{
					  $.post(
								"messageController.do?doMass",
								{receiveUser:receiveUser,receiveUserId:receiveUserId,title:title,content:content,documentId:id,fileType:fileType,usePermission:usePermission},
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
// 				  }
			  }
			  
			}); 
		
	}
	
	//创建在线文档输入文件名称
	function createOnline(url){
	       //prompt层
        layer.prompt({
            title: '请输入新建文档名称',
            formType: 0 //prompt风格，支持0-2
        }, function(fileName){
        	fileName = encodeURI(encodeURI(fileName));  
        	url = url + "&fileName=" + fileName;
        	//location.href=url;
        	openMaxWindow(url);
        	layer.closeAll();
        	location.reload();
        });
	}
	//查看图片类型文件
	function viewImages(url){
		window.open(url, url, "height="+(window.screen.height)+", width="+(window.screen.width)+", toolbar= no, menubar=no, scrollbars=no, resizable=yes, location=no, status=yes,top=0,left=0");
	}
	function openMaxWindow(url){
		window.open(url, url, "height="+(window.screen.height)+", width="+(window.screen.width)+", toolbar= no, menubar=no, scrollbars=no, resizable=yes, location=no, status=yes,top=0,left=0");
	}
	function onSearchForm(url){
		$("#searchForm").attr("action",url);
		$("#searchForm").submit();
	}
	//文档分享链接
	function createShare(id){
		var domain = document.domain;
		var port = window.location.port;
		layer.open({
			  type: 1,
			  title: '分享链接',
			  area: ['500px', '160px'],
			  shadeClose: true, //点击遮罩关闭
			  content: '\<\div style="padding:20px;"><input type="text" value="http://'+domain+':'+port+'/share.do?link&id='+id+'" class="layui-input" >\<\/div>'
		});
	}
	 
	//显示属性
	function property(id){
		layer.open({
			  title:"文件属性",
			  content: 'docMyFileController.do?showProperty&id='+id, //content可以是string也可以是URL
			  type: 2,
			  resize:true,
			  area:['800px','500px'],
			  btn:['关闭'],
			  yes:function(index,layero){
				  //按钮回调函数
				  layer.close(index);
			  }
		});
	}
	//显示历史版本
	function version(id){
		layer.open({
			  title:"历史版本",
			  content: 'docMyFileController.do?showVersion&id='+id, //content可以是string也可以是URL
			  type: 2,
			  resize:true,
			  area:['800px','500px'],
			  btn:['关闭'],
			  yes:function(index,layero){
				  //按钮回调函数
				  layer.close(index);
			  }
		});
	}
	//逻辑删除
	function falseDel(id){
		layer.confirm('您确认要删除此文件至回收站吗?',
				{btn:['是','否']}
			  , function(index){
				  $.post(
							"docMyFileController.do?doDel",
							{fileId:id},
							function(res){
								var resJSON = $.parseJSON(res);
								layer.close(index);
								layer.msg(resJSON.msg,{time:2000,icon:1},function(){
									location.reload();//刷新页面
								});
							}
						   );
			  
			});       
	}