var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#billInvestList_open_close').on("click",function(){
		$('#billInvestList_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//表格初始化
	tableObj = $("#billInvestList_table").datagrid({
		height:$("#billInvestList_body").height()-$('#billInvestList_search_area').height()-5,
		width:$("#billInvestList_body").width(),
		loadMsg : "正在加载，请稍后...",
		url:"billinvest!queryAllBillInvest.do",  
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
						{field:'realName',title:'姓名',halign:"center", align:"center",width:150},
						{field:'nickname',title:'昵称',halign:"center", align:"center",width:150},
						{field:'sex',title:'性别',halign:"center", align:"center",width:100,
							formatter: function(value,row,index){
								if(value==1){
									return "男";
								}else if(value == 0){
									return "女";
								}else{
									return "保密";
								}
							}
						},
		            ]],
		columns:[[
				{field:'email',title:'邮箱',halign:"center", align:"center",width:150},
				{field:'phone',title:'电话',halign:"center", align:"center",width:150},
				{field:'scaleName',title:'投资标名称',halign:"center", align:"center",width:200},
				{field:'status',title:'状态',halign:"center", align:"center",width:150,
					//1进行中，2成功，3失败
					formatter: function(value,row,index){
						if(value==1){
							return "进行中";
						}else if(value == 2){
							return "成功";
						}else if(value == 3){
							return "失败";
						}else{
							return "--";
						}
					}
				},
				{field:'useType',title:'资金类型',halign:"center", align:"center",width:200,
					//资金类型，1充值，2提现，3消费（购买理财产品），4购买手续费，5赎回（仅本金），6收益，7转让手续费
					formatter: function(value,row,index){
						if(value==1){
							return "充值";
						}else if(value == 2){
							return "提现";
						}else if(value == 3){
							return "消费（购买理财产品）";
						}else if(value == 4){
							return "购买手续费";
						}else if(value == 5){
							return "赎回（仅本金）";
						}else if(value == 6){
							return "收益";
						}else if(value == 7){
							return "转让手续费";
						}else{
							return "--";
						}
					}
				},
				{field:'style',title:'方式',halign:"center", align:"center",width:150,
					//方式，1线下，2Android，3IOS，4平台系统
					formatter: function(value,row,index){
						if(value==1){
							return "线下";
						}else if(value == 2){
							return "Android";
						}else if(value == 3){
							return "IOS";
						}else if(value == 4){
							return "平台系统";
						}else{
							return "--";
						}
					}
				},
				{field:'bankNo',title:'银行卡号',halign:"center", align:"center",width:200},
				{field:'openBank',title:'开户行',halign:"center", align:"center",width:150 },
		        {field:'type',title:'类型',halign:"center", align:"center",width:150,
					//类型，1储蓄卡，2借记卡，3信用卡
					formatter: function(value,row,index){
						if(value==1){
							return "储蓄卡";
						}else if(value == 2){
							return "借记卡";
						}else if(value == 3){
							return "信用卡";
						}else{
							return "--";
						}
					}
		        },  
		        {field:'money',title:'变动金额',halign:"center", align:"center",width:200},
				{field:'balance',title:'变动后账户余额',halign:"center", align:"center",width:200},
				{field:'asset',title:'变动后总资产',halign:"center", align:"center",width:200},
				{field:'optTime',title:'操作时间',halign:"center", align:"center",width:200},
				{field:'remark',title:'操作说明',halign:"center", align:"center",width:600}				
		]]
	});
	$('#querybillInvestListForm').form({    
	    url:"billinvest!queryByPar.do",    
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
	$('#billInvestList_table').datagrid('resize',{  
		height:$("#billInvestList_body").height()-$('#billInvestList_search_area').height()-5,
		width:$("#billInvestList_body").width()
	});
}
