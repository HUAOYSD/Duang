var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#smsList_open_close').on("click",function(){
		$('#smsList_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//表格初始化
	tableObj = $("#smsList_table").datagrid({
		height:$("#smsList_body").height()-$('#smsList_search_area').height()-5,
		width:$("#smsList_body").width(),
		loadMsg : "正在加载，请稍后...",
		url:"sms!query.do?state=-1",  
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
				{field:'phone',title:'接收号码',halign:"center", align:"center",width:200},
				{field:'content',title:'内容',halign:"center", align:"center",width:400},
		        {field:'createTime',title:'发送时间',halign:"center", align:"center",width:100}, 
				{field:'platform',title:'平台',halign:"center", align:"left",width:100,
		        	formatter:function(value,row,index){
						if(value==1){
							return "线下";
						}else if(value == 2){
							return "Android";
						}else if(value == 3){
							return "IOS";
						}else if(value == 4){
							return "平台系统";
						}
					}
				},
				{field:'mesNum',title:'短信条数',halign:"center", align:"center",width:100}, 
				{field:'price',title:'单价',halign:"center", align:"center",width:100}, 
				{field:'money',title:'花费',halign:"center", align:"center",width:100}, 
				{field:'state',title:'状态',halign:"center", align:"center",width:100,
					formatter:function(value,row,index){
						if(value==1){
							return "正常";
						}else{
							return "删除";
						}
					}
				}
		]]
	});
	$('#querysmsListForm').form({    
	    url:"sms!query.do",    
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
	$('#smsList_table').datagrid('resize',{  
		height:$("#smsList_body").height()-$('#smsList_search_area').height()-5,
		width:$("#smsList_body").width()
	});
}
