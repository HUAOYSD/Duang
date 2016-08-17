var proToTicketTable;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#invest_pro_to_ticket_list_open_close').on("click",function(){
		$('#investProToTicketList_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	
	//表格初始化
	proToTicketTable = $("#investProToTicketListTable").datagrid({
		height:$("#investProToTicketListbody").height()-$('#investProToTicketList_search_area').height()-75,
		width:$("#investProToTicketListbody").width(),
		idField:'id',
		loadMsg : "正在加载，请稍后...",
		url:"investpro!queryInvestPro.do",  
		singleSelect:false,  
		nowrap:true,
		rownumbers:true,
		pagination:true,
		fitColumns:false,
		pageSize:50,
		pageList:[50,80,100,150,200],
		sortOrder:'desc',
		frozenColumns:[[
		    {field:'id',checkbox:true},            
			{field:'nameZh',title:'总名称',width:100,halign:"center", align:"center"},
			{field:'name',title:'名称',width:100,halign:"center", align:"center",editor:'center'},
			{field:'category',title:'产品类型',width:100,halign:"center", align:"center",editor:'center',
				formatter: function(value,row,index){
					if(value==0){
						return "信贷产品";
					}else if(value==1){
						return "标类产品";
					}else{
						return "--";
					}
				}
			}
		]],
		columns:[[
			{field:'nameDescribe',title:'描述',width:200,halign:"center", align:"center" },
			{field:'yieldDescribe',title:'收益率描述',width:250,halign:"center", align:"center" },
			{field:'yield',title:'准确的收益率',width:100,halign:"center", align:"center" ,
				formatter: function(value,row,index){
					return value.toFixed(6);
				}
			},
			{field:'title1',title:'标题1',width:100,halign:"center", align:"center" },
			{field:'title2',title:'标题2',width:150,halign:"center", align:"center" },
			{field:'isSell',title:'是否起售',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					if(value==1){
						return "起售";
					}else{
						return "停售";
					}
				}
			},
			{field:'isNewProduct',title:'是否新品',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					if(value==1){
						return "是";
					}else{
						return "否";
					}
				}
			},
			{field:'isRecommend',title:'推荐',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					if(value==1){
						return "是";
					}else{
						return "否";
					}
				}
			},
			{field:'productDescribe',title:'介绍',width:500,halign:"center", align:"left" },
			{field:'riskControl',title:'风险控制',width:500,halign:"center", align:"left" },
			{field:'details',title:'更多详情',width:500,halign:"center", align:"left" }
		]],
		onLoadSuccess: function(index,field,value){
			initSelectRow();
		}
	});
	$('#queryinvestProToTicketListForm').form({    
	    url:"investpro!queryInvestPro.do",    
	    onSubmit: function(){    
	    	
	    },    
	    success:function(data){ 
	    	data = JSON.parse(data);
	    	proToTicketTable.datagrid('loadData', {
	    		"rows":data.rows,
	    		"total":data.total,
	    	}); 
	    }    
	});  
	
});
//初始化表格，就是选择已经绑定的产品
function initSelectRow(){
	if(investProductIds == null ||investProductIds==""){
		return;
	}else{
		var productIdArray = investProductIds.split(";");
		for(var i = 0;i<productIdArray.length;i++){
			proToTicketTable.datagrid("selectRecord",productIdArray[i]);
		}
	}
}
//保存选择的产品，进行与理财券绑定
function saveInvestProTicket(){
	var productIds="";
	var selectRows = proToTicketTable.datagrid("getSelections");
	if(selectRows.length == 0){
		layer.msg("请选择需要的产品",{time:2000});
		return;
	}
	for(var i = 0; i< selectRows.length; i++){
		var selectPro = selectRows[i];
		productIds += selectPro.id;
		if(i<selectRows.length-1){
			productIds+=";";
		}
	}
	$.ajax({
		   type: "POST",
		   url: "investticket!updateInvestTicketProduct.do",
		   data: "id="+investTicketId+"&productIds="+productIds,
		   success: function(data){
			 data = JSON.parse(data);
		     if(data.result==true){
		    	window.parent.reloadDataGrid();
		    	parent.layer.closeAll();
		     }
		    layer.msg(data.msg, {time: 1000});
		   }
	});
}
//点击取消触发
function cancle(){
	parent.layer.closeAll();
}
//监听窗口大小变化
window.onresize = function(){
	setTimeout(domresize,300);
};
//改变表格宽高
function domresize(){
	$('#investProToTicketListTable').datagrid('resize',{  
		height:$("#investProToTicketListbody").height()-$('#investProToTicketList_search_area').height()-75,
		width:$("#investProToTicketListbody").width()
	});
}
	