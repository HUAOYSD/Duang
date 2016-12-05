/**
 * 初始化
 */
$(function() {
	$('#del_btn_memberMiddle').linkbutton('disable');
	loadmemberMiddle("memberMiddle!queryAll.do");
});


/**
 * 改变表格宽高
 */
function domresize(){
	$('#memberMiddle').datagrid('resize',{  
		height:$("#body_memberMiddle").height()-$('#memberMiddle_search_area').height()-8,
		width:$("#body_memberMiddle").width()
	});
}


/**
 * 监听窗口大小变化
 */
window.onresize = function(){
	setTimeout(domresize,300);
};


/**
 * 加载列表
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function loadmemberMiddle(url, dataObj){
	$('#memberMiddle').datagrid({
			height:$("#body_memberMiddle").height()-$('#memberMiddle_search_area').height()-8,
			width:$("#body_memberMiddle").width(),
			loadMsg : "正在加载，请稍后...",
			singleSelect:true, 
			nowrap:true,
			rownumbers:true,
			sortOrder:'desc',
			toolbar:'#tt_toolbar_memberMiddle',
			method:'post',
			autoRowHeight : false,
			border : false,
			url :url,
			queryParams : dataObj,
			fitColumns : true,
			remoteSort : false,
			rowStyler: function(index,row){},
			columns : [ [
			             {field:'id',checkbox:true},
			             {field : "userName", title : "姓名", width : 200, align : "center" },
			             {field : "idcard", title : "证件号", width : 300, align : "center"},
			             {field : "isAuth", title : "是否认证", width : 300, align : "center",
			            	 formatter:function(value,row,index){
			            		 if(value==0)
			            			 return '<font color="#ffffff">未认证</font>';
			            		 else if(value==1)	   
			            			 return '<font color="#ffffff">已认证</font>';
			            	 },
			            	 styler: function(value,row,index){  
					              if (value == 0){  
					                  return 'background-color:#85cebf';  
					              }else{  
					                  return 'background-color:#f88193';  
					              }  
					         }
			             },
			             {field : "totalSum", title : "放款总额", width : 300, align : "center",
			            	 formatter: function(value,row,index){
			            			 return value+"元";
			            	 } 
			             },
			             {field : "lastSum", title : "最后一次放款总额", width : 250, align : "center",
			            	 formatter: function(value,row,index){
			            			 return value+"元";
			            	 } 
			             },
			             {field : "payType", title : "手续费付款方式", width : 450, align : "center",
			            	 formatter: function(value,row,index){
			            		 if(value==0)
			            			 return "从可用账户收取手续费";
			            		 else if(value==1)	   
			            			 return "从预付账户收取手续费";
			            	 } 
			             },
			             {field : "createUserName", title : "创建人", width : 200, align : "center"},
			             {field : "createTime", title : "创建时间", width : 200, align : "center",
			            	 formatter: function(value,row,index){
			            		 if(value==0)
			            			 return "-&nbsp;&nbsp;-";
			            		 else	   
			            			 return new Date(value).format("yyyy-MM-dd");
			            	 }
			             }
			             
			] ],
			onSelect:function(rowIndex, rowData){
				$('#del_btn_memberMiddle').linkbutton('enable');
			},
			onUnselect:function(rowIndex, rowData){
				$('#del_btn_memberMiddle').linkbutton('disable');
			},
			onLoadSuccess: function(data){
				$('#del_btn_memberMiddle').linkbutton('disable');
			}
		});
}


/**
 * 刷新数据
 */
function reloadDataGrid(){
	loadmemberMiddle("memberMiddle!queryAll.do");
}

/**
 * 打开添加页面
 */
$("#add_btn_memberMiddle").on("click",function(){
	layer.open({
		type: 2,
		title: '添加放款中间人',
		shadeClose: true,
		shade: 0.8,
		area: ['460px', '300'],
		content: 'memberMiddle!openDialog.do?path=add'
	});  
});

/**
 * 删除
 */
$("#del_btn_memberMiddle").on("click",function(){
	var selectedRow = $("#memberMiddle").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一个中间人",{time:1500});
		return;
	}else{
		var del_layer_index = layer.confirm('删除后数据无法恢复，确定要删除'+selectedRow.userName, {
			  btn: ['确定','取消'] //按钮
			}, function(){
				$.ajax({
					   type: "POST",
					   url: "memberMiddle!updateIsUse.do",
					   data: "id="+selectedRow.id,
					   success: function(msg){
						    $.messager.progress("close");	
							var result = eval('(' + msg + ')');
							if(result.success){
								layer.close(del_layer_index);
								layer.msg('删除成功！', {icon: 1});
								reloadDataGrid();
							}else{
								layer.msg("删除失败", {time: 1500 });
							}
					   }
				});
			  
			}, function(){
				layer.close(del_layer_index);
			});
	}
});