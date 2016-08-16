var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#memberTicketRecordList_open_close').on("click",function(){
		$('#memberTicketRecordList_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//表格初始化
	tableObj = $("#memberTicketRecordList_table").datagrid({
		height:$("#memberTicketRecordList_body").height()-$('#memberTicketRecordList_search_area').height()-5,
		width:$("#memberTicketRecordList_body").width(),
		loadMsg : "正在加载，请稍后...",
		url:"memberticketrecord!queryAllMemberTicketRecord.do",  
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
				{field:'investTicketId',hidden:true},
				{field:'name',title:'名称',halign:"center", align:"center",width:200},
				{field:'useTime',title:'使用时间',halign:"center", align:"center",width:200 },
		        {field:'remark',title:'说明',halign:"center", align:"center",width:300},  
		        {field:'money',title:'价值',halign:"center", align:"center",width:100}, 
				{field:'beginTime',title:'有效期始',halign:"center", align:"center",width:200},
				{field:'endTime',title:'有效期至',halign:"center", align:"center",width:200},
				{field:'createTime',title:'派发时间',halign:"center", align:"center",width:200},
				{field:'minMoney',title:'最小投资金额权限',halign:"center", align:"center",width:150},
				{field:'describe',title:'详细描述',halign:"center", align:"center",width:600 }
		]]
	});
	$('#querymemberTicketRecordListForm').form({    
	    url:"memberticketrecord!queryMemberTicketRecordByPro.do",    
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
	$('#memberTicketRecordList_table').datagrid('resize',{  
		height:$("#memberTicketRecordList_body").height()-$('#memberTicketRecordList_search_area').height()-5,
		width:$("#memberTicketRecordList_body").width()
	});
}
