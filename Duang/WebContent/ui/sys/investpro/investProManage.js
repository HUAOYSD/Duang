var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//初始化状态，更新和删除按钮时不能点击的
	$('#investProManage-update-btn').linkbutton('disable');
	$('#investProManage-delete-btn').linkbutton('disable');
	//初始化表格
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
		pageSize:50,
		pageList:[50,100,150,200,250],
		sortOrder:'desc',
		columns:[[
		    {field:'id',checkbox:true},
			{field:'nameZh',title:'总名称',width:100,halign:"center", align:"center"},
			{field:'name',title:'名称',width:100,halign:"center", align:"center",editor:'center'},
			{field:'name_describe',title:'描述',width:200,halign:"center", align:"center" },
			{field:'yield_describe',title:'收益率描述',width:250,halign:"center", align:"center" },
			{field:'yield',title:'准确的收益率',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					return value.toFixed(6);
				}
			},
			{field:'charge_ratio',title:'手续费比例率',width:100,halign:"center", align:"center" },
			{field:'title1',title:'标题1',width:100,halign:"center", align:"center" },
			{field:'title2',title:'标题2',width:150,halign:"center", align:"center" },
			{field:'min_deadline',title:'起投期限',width:100,halign:"center", align:"center" },
			{field:'min_money',title:'起投金额',width:100,halign:"center", align:"center" },
			{field:'refund_type',title:'还款方式',width:100,halign:"center", align:"center" },
			{field:'is_sell',title:'是否起售',width:100,halign:"center", align:"center" },
			{field:'is_lottery',title:'是否抽奖',width:100,halign:"center", align:"center" },
			{field:'is_red_envel',title:'是否红包',width:100,halign:"center", align:"center" },
			{field:'is_new_product',title:'是否新品',width:100,halign:"center", align:"center" },
			{field:'is_recommend',title:'推荐',width:100,halign:"center", align:"center" },
			{field:'product_describe',title:'介绍',width:500,halign:"center", align:"left" },
			{field:'risk_control',title:'风险控制',width:500,halign:"center", align:"left" },
			{field:'details',title:'更多详情',width:500,halign:"center", align:"left" }
		]],
		toolbar:'#tt_toolbar',
		onSelect:function(rowIndex, rowData){
			$('#investProManage-update-btn').linkbutton('enable');
			$('#investProManage-delete-btn').linkbutton('enable');
		},
		onUnselect:function(rowIndex, rowData){
			$('#investProManage-update-btn').linkbutton('disable');
			$('#investProManage-delete-btn').linkbutton('disable');
		},
	});
	
	//新增弹出框
	$("#save").on("click", function(){
		
	});
	//修改
	$("#update").on("click", function(){
		$parent.messager.alert("提示","update", "info");
	});
	//删除
	$("#delete").on("click", function(){
		//获取选择的行
		var selectedRow = tableObj.datagrid('getSelected');
		//获取选择行的索引值index
		var selectedRowIndex = tableObj.datagrid('getRowIndex',selectedRow);
		//从表格中移除index
		tableObj.datagrid('deleteRow',selectedRowIndex);
	});
	$('#queryInvestProForm').form({    
	    url:"investpro!queryInvestPro.do",    
	    onSubmit: function(){    
	        
	    },    
	    success:function(data){ 
	    	data = JSON.parse(data);
	    	console.info(data);
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
	