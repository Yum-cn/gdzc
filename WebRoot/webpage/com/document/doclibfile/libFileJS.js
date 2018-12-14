var pId=$("#pId").val();//获取文件夹ID
//上传
layui.use('upload', function(){
	var loading;
	layui.upload({
		elem: '#fileElem',
	    url: 'docLibFileController.do?uploadFile&pId='+pId //上传接口
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
	/* layui.upload({
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
	  }); */
});

//文件重命名
function reName(id,name){
	//alert("测试JS");
	layer.prompt({
		  formType:0,
		  value:name,
		  title:"请输入文件名"},
		  function(value, index, elem){
			  $.post("docLibFileController.do?reName",
					  {fileName:value,id:id},
					  function(res){
						  var resObj = $.parseJSON(res);
						  layer.close(index);
						  layer.msg(resObj.msg, {
				    		  icon: 1,
				    		  time: 1000 //2秒关闭（如果不配置，默认是3秒）
				    	  }, function(){
				    			location.reload();
				    	}); 
					  });
		});
}


//移动 文件
function fileTree(id){
	layer.open({
		content:'docLibFileController.do?fileTree',
		type:2,
		resize:true,
		area:['400px','500px'],
		btn:['确定'],
		yes:function(index,layero){
			var body = layer.getChildFrame('body', index);
			var toMovePackageId=body.find('#toMovePackage').val();
			$.post(
				"docLibFileController.do?doMoveFile",
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
		content:'docLibFileController.do?fileTree',//这个请求是为了查询出  菜单树
		type:2,
		resize:true,
		area:['400px','500px'],
		btn:['确定'],
		yes:function(index,layero){
			var body = layer.getChildFrame('body', index);
			var toMovePackageId=body.find('#toMovePackage').val();
			$.post(
				"docLibFileController.do?doCopyFile",
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

//逻辑删除
function falseDel(id){
	layer.confirm('您确认要删除此文件至回收站吗?',
			{btn:['是','否']}
		  , function(index){
			  $.post(
						"docLibFileController.do?doDel",
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
		  content: 'docLibFileController.do?showProperty&id='+id, //content可以是string也可以是URL
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
