var proMangeTable;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#queryInvestProManageForm_openOrClose').on("click",function(){
		$('#investProManage_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//初始化状态，更新和删除按钮时不能点击的
	$('#investProManage-update-btn').linkbutton('disable');
	$('#investProManage-delete-btn').linkbutton('disable');
	//初始化表格
	proMangeTable = $("#investProManageTable").datagrid({
		height:$("#investProManage_body").height()-$('#investProManage_search_area').height()-5,
		width:$("#investProManage_body").width(),
		loadMsg : "正在加载，请稍后...",
		url:"investpro!queryInvestPro.do",  
		singleSelect:true, 
		nowrap:true,
		rownumbers:true,
		pagination:true,
		pageSize:50,
		pageList:[50,80,150,200,250],
		sortOrder:'desc',
		toolbar:'#investProManageTable_toolbar',
		frozenColumns:[[
		                {field:'id',checkbox:true},
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
			{field:'yield',title:'准确的收益率',width:100,halign:"center", align:"center",
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
		]],
		onSelect:function(rowIndex, rowData){
			$('#investProManage-update-btn').linkbutton('enable');
			$('#investProManage-delete-btn').linkbutton('enable');
		},
		onUnselect:function(rowIndex, rowData){
			$('#investProManage-update-btn').linkbutton('disable');
			$('#investProManage-delete-btn').linkbutton('disable');
		},
		onLoadSuccess: function(data){
			$('#investProManage-update-btn').linkbutton('disable');
			$('#investProManage-delete-btn').linkbutton('disable');
		}
	});
	
	$('#queryInvestProManageForm').form({    
	    url:"investpro!queryInvestPro.do",    
	    onSubmit: function(){    
	        
	    },    
	    success:function(data){ 
	    	data = JSON.parse(data);
	    	proMangeTable.datagrid('loadData', {
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
	proMangeTable.datagrid('resize',{  
		height:$("#investProManage_body").height()-$('#investProManage_search_area').height()-5,
		width:$("#investProManage_body").width()
	});
}
	