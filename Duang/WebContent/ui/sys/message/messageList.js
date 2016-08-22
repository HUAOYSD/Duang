var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#messageList_open_close').on("click",function(){
		$('#messageList_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//表格初始化
	tableObj = $("#messageList_table").datagrid({
		height:$("#messageList_body").height()-$('#messageList_search_area').height()-5,
		width:$("#messageList_body").width(),
		loadMsg : "正在加载，请稍后...",
		url:"message!queryAllMessage.do",  
		singleSelect:true,  
		nowrap:true,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		pageSize:50,
		pageList:[50,80,100,150,200],
		sortOrder:'desc',
		toolbar:'',
		columns:[[
				{field:'id',hidden:true},
				{field:'title',title:'标题',halign:"center", align:"left",width:400},
		        {field:'senderRealName',title:'发送人',halign:"center", align:"center",width:100}, 
				{field:'receiverRealName',title:'接收人',halign:"center", align:"center",width:100},
				{field:'time',title:'发送时间',halign:"center", align:"center",width:150 },
				{field:'content',title:'内容',halign:"center", align:"left",width:600},
				{field:'readed',title:'是否已读',halign:"center", align:"center",width:100,
					formatter:function(value,row,index){
						if(value==1){
							return "已读";
						}else{
							return "未读";
						}
					}
				}
		]]
	});
	$('#querymessageListForm').form({    
	    url:"message!queryByParameter.do",    
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

//监听窗口大小变化
window.onresize = function(){
	setTimeout(domresize,300);
};
//改变表格宽高
function domresize(){
	$('#messageList_table').datagrid('resize',{  
		height:$("#messageList_body").height()-$('#messageList_search_area').height()-5,
		width:$("#messageList_body").width()
	});
}
