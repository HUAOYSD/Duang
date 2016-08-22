var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#memberInvestTicketList_open_close').on("click",function(){
		$('#memberInvestTicketList_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//表格初始化
	tableObj = $("#memberInvestTicketList_table").datagrid({
		height:$("#memberInvestTicketList_body").height()-$('#memberInvestTicketList_search_area').height()-5,
		width:$("#memberInvestTicketList_body").width(),
		loadMsg : "正在加载，请稍后...",
		url:"memberinvestticket!queryAllMemberInvestTicket.do",  
		singleSelect:true,  
		nowrap:true,
		rownumbers:true,
		pagination:true,
		fitColumns:false,
		pageSize:50,
		pageList:[50,80,100,150,200],
		sortOrder:'desc',
		toolbar:'',
		frozenColumns:[[
				{field:'id',hidden:true},
				{field:'loginName',title:'登录名',halign:"center", align:"center",width:100},
				{field:'realName',title:'真实姓名',halign:"center", align:"center",width:100 },
				{field:'sex',title:'性别',halign:"center", align:"center",width:50,
					formatter: function(value,row,index){
						if(value==1){
							return "男";
						}else if(value == 0){
							return "女";
						}else{
							return "保密";
						}
					}
				}     
		                
		 ]],
		columns:[[
				{field:'phone',title:'手机',halign:"center", align:"center",width:100},
				{field:'ticketName',title:'理财券名称',halign:"center", align:"center",width:100},
				{field:'isUse',title:'是否已使用',halign:"center", align:"center",width:100,
					formatter: function(value,row,index){
						if(value==0){
							return "未使用";
						}else{
							return "已使用";
						}
					}
				},
		        {field:'resourceStyle',title:'理财券来源',halign:"center", align:"center",width:100}, 
				{field:'state',title:'状态',halign:"center", align:"center",width:100,
					formatter: function(value,row,index){
						  if(value==0){
							   return '<font color="#ffffff">使用</font>';
						  }else{	   
							   return '<font color="#ffffff">过期</font>';
						  }
					 },
					 styler: function(value,row,index){  
			              if (value == 0){  
			                  return 'background-color:#85cebf';  
			              }else{  
			                  return 'background-color:#f88193';  
			              }  
			         }
				},
				{field:'remark',title:'说明',halign:"center", align:"center",width:300},  
		        {field:'money',title:'价值',halign:"center", align:"center",width:100}, 
				{field:'beginTime',title:'有效期始',halign:"center", align:"center",width:200},
				{field:'endTime',title:'有效期至',halign:"center", align:"center",width:200},
				{field:'createTime',title:'派发时间',halign:"center", align:"center",width:200},
				{field:'minMoney',title:'最小投资金额权限',halign:"center", align:"center",width:150},
				{field:'describes',title:'详细描述',halign:"center", align:"left",width:600 }
		]]
	});
	$('#querymemberInvestTicketListForm').form({    
	    url:"memberinvestticket!queryByParameter.do",    
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
	$('#memberInvestTicketList_table').datagrid('resize',{  
		height:$("#memberInvestTicketList_body").height()-$('#memberInvestTicketList_search_area').height()-5,
		width:$("#memberInvestTicketList_body").width()
	});
}
