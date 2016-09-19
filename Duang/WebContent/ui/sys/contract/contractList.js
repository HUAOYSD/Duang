var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#contractList_open_close').on("click",function(){
		$('#contractList_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//表格初始化
	tableObj = $("#contractList_table").datagrid({
		height:$("#contractList_body").height()-$('#contractList_search_area').height()-5,
		width:$("#contractList_body").width(),
		loadMsg : "正在加载，请稍后...",
		url:"contract!query.do",  
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
				{field:'name',title:'名称',halign:"center", align:"center",width:200,
					formatter:function(value,row,index){
						return '<a href="contract!downContract.do?id='+row.id+'">'+value+'</a>';
					}
				},
		        {field:'createTime',title:'创建时间',halign:"center", align:"center",width:100}, 
				{field:'conPath',title:'存储路径',halign:"center", align:"left",width:400,
		        	formatter:function(value,row,index){
						return '<a href="contract!downContract.do?id='+row.id+'">'+value+'</a>';
					}
				},
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
	$('#querycontractListForm').form({    
	    url:"contract!query.do",    
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
	$('#contractList_table').datagrid('resize',{  
		height:$("#contractList_body").height()-$('#contractList_search_area').height()-5,
		width:$("#contractList_body").width()
	});
}
