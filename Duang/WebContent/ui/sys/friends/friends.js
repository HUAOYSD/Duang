var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#friends_open_close').on("click",function(){
		$('#friends_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//表格初始化
	tableObj = $("#friends_table").datagrid({
		height:$("#friends_body").height()-$('#friends_search_area').height()-5,
		width:$("#friends_body").width(),
		loadMsg : "正在加载，请稍后...",
		url:"friends!queryAll.do",  
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
				{field:'selfLoginName',title:'自己登录名',halign:"center", align:"center",width:200},
				{field:'selfRealName',title:'自己真实名',halign:"center", align:"center",width:200 },
		        {field:'targetLoginName',title:'关注登录姓名',halign:"center", align:"center",width:300},  
		        {field:'targetRealName',title:'关注真实姓名',halign:"center", align:"center",width:100}, 
				{field:'together',title:'相互关注',halign:"center", align:"center",width:200},
				{field:'optTime',title:'关注时间',halign:"center", align:"center",width:200}
		]]
	});
	$('#queryfriendsForm').form({    
	    url:"friends!queryByParameter.do",    
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
	$('#friends_table').datagrid('resize',{  
		height:$("#friends_body").height()-$('#friends_search_area').height()-5,
		width:$("#friends_body").width()
	});
}
