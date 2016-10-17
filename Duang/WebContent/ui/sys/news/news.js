var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#news_open_close').on("click",function(){
		$('#news_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//表格初始化
	tableObj = $("#news_table").datagrid({
		height:$("#news_body").height()-$('#news_search_area').height()-5,
		width:$("#news_body").width(),
		lonewsMsg : "正在加载，请稍后...",
		url:"news!queryNews.do",  
		singleSelect:true,  
		nowrap:true,
		rownumbers:true,
		pagination:false,
		fitColumns:true,
		sortOrder:'desc',
		toolbar:'#newsToolBar',
		columns:[[
				{field:'id',hidden:true},
				{field:'content',title:'内容',halign:"center", align:"left",width:500},
				{field:'img',title:'图片',halign:"center", align:"center",width:200 },
		        {field:'createuser',title:'创建人',halign:"center", align:"center",width:150},  
		        {field:'createtime',title:'创建时间',halign:"center", align:"center",width:150}, 
		        {field:'state',title:'状态',halign:"center", align:"center",width:100,
		        	formatter: function(value,row,index){
						if(value==1){
							return "启用";
						}else if(value == 0){
							return "禁用";
						}else{
							return"--";
						}
					}
		        }
				
		]],
		onClickCell: function(index,field,value){
			tableObj.datagrid("selectRow",index);
			var selectRowt = tableObj.datagrid("getSelected");
			if(field=='img'){
				if(value==null){
					layer.msg("还没有上传图片",{time:2000});
					return;
				}
				indexLayer = layer.open({
					type: 2,
					title: '图片',
					shnewseClose: true,
					shnewse: 0.8,
					area: ['80%', '80%'],
					maxmin:true,
					content: "news!showNewsImage.do?id="+selectRowt.id 
				});  
			}
		},
		onSelect:function(rowIndex, rowData){
			if(rowData.state==1){
				$("#news-delete-btn").linkbutton({    
				    iconCls: 'icon-01',
				    text:'禁用'
				});
			}else{
				$("#news-delete-btn").linkbutton({    
				    iconCls: 'icon-accept',
				    text:'启用'
				});
			}
			showButton();
		},
		onUnselect:function(rowIndex, rowData){
			hideButton();
		},
		onLonewsSuccess: function(data){
			hideButton();
		}
	});
	$('#querynewsForm').form({    
	    url:"news!querynews.do",    
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
	$('#news-delete-btn').linkbutton('disable');
	$('#news-icon-btn').linkbutton('disable');
	
}
/**
 * 设置按钮可用状态
 */
function showButton(){
	//初始化状态，更新和删除按钮时不能点击的
	$('#news-delete-btn').linkbutton('enable');
	$('#news-icon-btn').linkbutton('enable');
}


//监听窗口大小变化
window.onresize = function(){
	setTimeout(domresize,300);
};
//改变表格宽高
function domresize(){
	$('#news_table').datagrid('resize',{  
		height:$("#news_body").height()-$('#news_search_area').height()-5,
		width:$("#news_body").width()
	});
};

function reloadDataGrid(){
	tableObj.datagrid('reload');  
}

/**
 * 打开添加页面
 */
$("#news-add-btn").on("click",function(){
	layer.open({
		type: 2,
		title: '添加新闻资讯',
		shnewseClose: true,
		shnewse: 0.8,
		area: ['460px', '500px'],
		content: 'news!openDialog.do?path=add'
	});  
});

/**
 * 启用，禁用
 */
$("#news-delete-btn").on("click",function(){
	var selectRowt = tableObj.datagrid("getSelected");
	if(selectRowt == null){
		layer.msg("请选择一条广告记录",{time:2000});
		return;
	}
	$.ajax({
		type:'POST',
		url:"news!updateIsUse.do?id="+selectRowt.id,
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
$("#news-icon-btn").on("click",function(){
	var selectRowt = tableObj.datagrid("getSelected");
	if(selectRowt == null){
		layer.msg("请选择一条广告记录",{time:2000});
		return;
	}else if(selectRowt.imagenewsdress != null){
		layer.confirm('已经上传图片，确定重新上传吗？', {icon: 3, title:'提示'}, function(index){
			layer.close(index);
			opennewsImage(selectRowt.id);
		});
	}else{
		opennewsImage(selectRowt.id);
	}
	 
});

function opennewsImage(id){
	indexLayer = layer.open({
		type: 2,
		title: '图片',
		shnewseClose: true,
		shnewse: 0.8,
		area: ['80%', '80%'],
		maxmin:true,
		content: "news!showNewsImage.do?id="+id+"&type=upload" 
	});
}