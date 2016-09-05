var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#ad_open_close').on("click",function(){
		$('#ad_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//表格初始化
	tableObj = $("#ad_table").datagrid({
		height:$("#ad_body").height()-$('#ad_search_area').height()-5,
		width:$("#ad_body").width(),
		loadMsg : "正在加载，请稍后...",
		url:"ad!queryAll.do",  
		singleSelect:true,  
		nowrap:true,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		pageSize:50,
		pageList:[50,80,100,150,200],
		sortOrder:'desc',
		toolbar:'#adToolBar',
		columns:[[
				{field:'id',hidden:true},
				{field:'name',title:'名称',halign:"center", align:"center",width:200},
				{field:'remark',title:'说明',halign:"center", align:"center",width:300 },
		        {field:'createTime',title:'创建时间',halign:"center", align:"center",width:150},  
		        {field:'isUse',title:'是否使用',halign:"center", align:"center",width:100,
		        	formatter: function(value,row,index){
						if(value==1){
							return "使用";
						}else if(value == 0){
							return "不使用";
						}else{
							return"--";
						}
					}
		        },
		        {field:'imageAddress',title:'图片地址',halign:"center", align:"center",width:500}, 
				{field:'linkAddress',title:'链接地址',halign:"center", align:"center",width:500}
				
		]],
		onClickCell: function(index,field,value){
			tableObj.datagrid("selectRow",index);
			var selectRowt = tableObj.datagrid("getSelected");
			if(field=='imageAddress'){
				if(value==null){
					layer.msg("还没有上传图片",{time:2000});
					return;
				}
				indexLayer = layer.open({
					type: 2,
					title: '广告',
					shadeClose: true,
					shade: 0.8,
					area: ['80%', '80%'],
					maxmin:true,
					content: "ad!showAdImage.do?id="+selectRowt.id 
				});  
			}
		},
		onSelect:function(rowIndex, rowData){
			showButton();
		},
		onUnselect:function(rowIndex, rowData){
			hideButton();
		},
		onLoadSuccess: function(data){
			hideButton();
		}
	});
	$('#queryadForm').form({    
	    url:"ad!queryByParameter.do",    
	    onSubmit: function(){    
	        
	    },    
	    success:function(data){ 
	    	data = JSON.parse(data);
	    	if(data.result==false){
	    		layer.msg(data.msg,{time:2000});
	    	}
	    	tableObj.datagrid('loadData', {
	    		"rows":data.rows,
	    		"total":data.total,
	    	}); 
	    }    
	});
});

/**
 * 设置按钮不可用状态
 */
function hideButton(){
	//初始化状态，更新和删除按钮时不能点击的
	$('#ad-update-btn').linkbutton('disable');
	$('#ad-delete-btn').linkbutton('disable');
	$('#ad-icon-btn').linkbutton('disable');
	
}
/**
 * 设置按钮可用状态
 */
function showButton(){
	//初始化状态，更新和删除按钮时不能点击的
	$('#ad-update-btn').linkbutton('enable');
	$('#ad-delete-btn').linkbutton('enable');
	$('#ad-icon-btn').linkbutton('enable');
}


//监听窗口大小变化
window.onresize = function(){
	setTimeout(domresize,300);
};
//改变表格宽高
function domresize(){
	$('#ad_table').datagrid('resize',{  
		height:$("#ad_body").height()-$('#ad_search_area').height()-5,
		width:$("#ad_body").width()
	});
};

function reloadDataGrid(){
	tableObj.datagrid('reload');  
}

/**
 * 打开添加页面
 */
$("#ad-add-btn").on("click",function(){
	layer.open({
		type: 2,
		title: '添加广告',
		shadeClose: true,
		shade: 0.8,
		area: ['460px', '500px'],
		content: 'ad!openDialog.do?path=add'
	});  
});

/**
 * 打开编辑页面
 */
$("#ad-update-btn").on("click",function(){
	var selectRowt = tableObj.datagrid("getSelected");
	if(selectRowt == null){
		layer.msg("请选择一条广告记录",{time:2000});
		return;
	}
	layer.open({
		type: 2,
		title: '修改广告',
		shadeClose: true,
		shade: 0.8,
		area: ['460px', '500px'],
		content: 'ad!openDialog.do?path=edit&id='+selectRowt.id
	});  
});

/**
 * 删除
 */
$("#ad-delete-btn").on("click",function(){
	var selectRowt = tableObj.datagrid("getSelected");
	if(selectRowt == null){
		layer.msg("请选择一条广告记录",{time:2000});
		return;
	}
	$.ajax({
		type:'POST',
		url:"ad!delete.do?id="+selectRowt.id,
		data:"",
		dataType:'json',
		success:function(msg) {
			reloadDataGrid();
		}
	});
});

/**
 * 打开上传图片页面
 */
$("#ad-icon-btn").on("click",function(){
	var selectRowt = tableObj.datagrid("getSelected");
	if(selectRowt == null){
		layer.msg("请选择一条广告记录",{time:2000});
		return;
	}else if(selectRowt.imageAddress != null){
		layer.confirm('已经上传图片，确定重新上传吗？', {icon: 3, title:'提示'}, function(index){
			layer.close(index);
			openAdImage(selectRowt.id);
		});
	}else{
		openAdImage(selectRowt.id);
	}
	 
});

function openAdImage(id){
	indexLayer = layer.open({
		type: 2,
		title: '广告',
		shadeClose: true,
		shade: 0.8,
		area: ['80%', '80%'],
		maxmin:true,
		content: "ad!showAdImage.do?id="+id+"&type=upload" 
	});
}