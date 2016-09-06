var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#invest_pro_list_open_close').on("click",function(){
		$('#investProList_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	
	//表格初始化
	tableObj = $("#investProListTable").datagrid({
		height:$("#investProListbody").height()-$('#investProList_search_area').height()-5,
		width:$("#investProListbody").width(),
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
		frozenColumns:[[
			{field:'nameZh',title:'总名称',width:100,halign:"center", align:"center"},
			{field:'name',title:'名称',width:100,halign:"center", align:"center",editor:'center'},
			{field:'category',title:'产品类型',width:100,halign:"center", align:"center",editor:'center',
				formatter: function(value,row,index){
					if(value==1){
						return "信贷产品";
					}else if(value==2){
						return "房标产品";
					}else if(value==3){
						return "车标产品";
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
			{field:'days',title:'周期',width:100,halign:"center", align:"center" ,
				formatter: function(value,row,index){
					return value+"天";
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
		]]
	});
	$('#queryInvestProListForm').form({    
	    url:"investpro!queryInvestPro.do",    
	    onSubmit: function(){    
	        
	    },    
	    success:function(data){ 
	    	data = JSON.parse(data);
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
	$('#investProListTable').datagrid('resize',{  
		height:$("#investProListbody").height()-$('#investProList_search_area').height()-5,
		width:$("#investProListbody").width()
	});
}
	