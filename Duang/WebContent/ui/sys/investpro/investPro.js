var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//表格初始化
	tableObj = $("#tt").datagrid({
		height:$("#body").height()-$('#search_area').height()-5,
		width:$("#body").width(),
		idField:'id',
		loadMsg : "正在加载，请稍后...",
		url:"investpro!queryInvestPro.do",  
		singleSelect:true,  
		nowrap:true,
		rownumbers:true,
		pagination:true,
		fitColumns:false,
		pageSize:10,
		pageList:[10,20,30,40,50],
		sortOrder:'desc',
		columns:[[
			{field:'nameZh',title:'总名称',width:100,halign:"center", align:"center"},
			{field:'name',title:'名称',width:100,halign:"center", align:"center",editor:'center'},
			{field:'nameDescribe',title:'描述',width:200,halign:"center", align:"center" },
			{field:'yieldDescribe',title:'收益率描述',width:250,halign:"center", align:"center" },
			{field:'yield',title:'准确的收益率',width:100,halign:"center", align:"center" ,
				formatter: function(value,row,index){
					return value.toFixed(6);
				}
			},
			{field:'chargeRatio',title:'手续费比例率',width:100,halign:"center", align:"center" },
			{field:'title1',title:'标题1',width:100,halign:"center", align:"center" },
			{field:'title2',title:'标题2',width:150,halign:"center", align:"center" },
			{field:'minDeadline',title:'起投期限',width:100,halign:"center", align:"center" },
			{field:'minMoney',title:'起投金额',width:100,halign:"center", align:"center" },
			{field:'refundType',title:'还款方式',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					if(value==1){
						return "等额本息";
					}else if(value==2){
						return "一次性还款";
					}else if(value==3){
						return "先息后本";
					}
				}
			},
			{field:'isSell',title:'是否起售',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					if(value==1){
						return "起售";
					}else{
						return "停售";
					}
				}
			},
			{field:'isLottery',title:'是否抽奖',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					if(value==1){
						return "是";
					}else{
						return "否";
					}
				}
			},
			{field:'isRedEnvel',title:'是否红包',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					if(value==1){
						return "是";
					}else{
						return "否";
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
		]]
	});
	$('#queryInvestProForm').form({    
	    url:"investpro!queryInvestPro.do",    
	    onSubmit: function(){    
	        
	    },    
	    success:function(data){ 
	    	data = JSON.parse(data);
	    	$('#tt').datagrid('loadData', {
	    		"rows":data.rows,
	    		"total":data.total,
	    		"pageSize":data.pageSize,
	    		"pageNumber":data.currPage
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
	$('#tt').datagrid('resize',{  
		height:$("#body").height()-$('#search_area').height()-5,
		width:$("#body").width()
	});
}
	