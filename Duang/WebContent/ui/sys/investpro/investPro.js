var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
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
			{field:'nameZh',title:'总名称',width:100,halign:"center", align:"center",editor: { type: 'validatebox', options: { required: true}}},
			{field:'name',title:'名称',width:100,halign:"center", align:"center",editor:'center'},
			{field:'name_describe',title:'描述',width:200,halign:"center", align:"center",editor:'text'},
			{field:'yield_describe',title:'收益率描述',width:250,halign:"center", align:"center",editor:'text'},
			{field:'yield',title:'准确的收益率',width:100,halign:"center", align:"center",editor:'text'},
			{field:'charge_ratio',title:'手续费比例率',width:100,halign:"center", align:"center",editor:'text'},
			{field:'title1',title:'标题1',width:100,halign:"center", align:"center",editor:'text'},
			{field:'title2',title:'标题2',width:150,halign:"center", align:"center",editor:'text'},
			{field:'min_deadline',title:'起投期限',width:100,halign:"center", align:"center",editor:'text'},
			{field:'min_money',title:'起投金额',width:100,halign:"center", align:"center",editor:'text'},
			{field:'refund_type',title:'还款方式',width:100,halign:"center", align:"center",editor:'text'},
			{field:'is_sell',title:'是否起售',width:100,halign:"center", align:"center",editor:'text'},
			{field:'is_lottery',title:'是否起售',width:100,halign:"center", align:"center",editor:'text'},
			{field:'is_red_envel',title:'是否红包',width:100,halign:"center", align:"center",editor:'text'},
			{field:'is_new_product',title:'是否新品',width:100,halign:"center", align:"center",editor:'text'},
			{field:'is_recommend',title:'推荐',width:100,halign:"center", align:"center",editor:'text'},
			{field:'product_describe',title:'介绍',width:500,halign:"center", align:"left",editor:'text'},
			{field:'risk_control',title:'风险控制',width:500,halign:"center", align:"left",editor:'text'},
			{field:'details',title:'更多详情',width:500,halign:"center", align:"left",editor:'text'}
		]],
		toolbar:'#tt_btn',
		onDblClickRow:function(rowIndex, rowData){
			//双击开启编辑行
			if (editRow != undefined) {
				tableObj.datagrid("endEdit", editRow);
			}
			if (editRow == undefined) {
				tableObj.datagrid("beginEdit", rowIndex);
				editRow = rowIndex;
			}
		},
		onAfterEdit: function (rowIndex, rowData, changes) {
			//endEdit该方法触发此事件
			console.info(rowData);
			editRow = undefined;
		}
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
	