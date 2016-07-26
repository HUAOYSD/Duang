var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#billLoanList_open_close').on("click",function(){
		$('#billLoanList_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//表格初始化
	tableObj = $("#billLoanList_table").datagrid({
		height:$("#billLoanList_body").height()-$('#billLoanList_search_area').height()-5,
		width:$("#billLoanList_body").width(),
		loadMsg : "正在加载，请稍后...",
		url:"billloan!queryByPar.do",  
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
				{field:'pactNumber',title:'借款合同号',halign:"center", align:"center",width:200},
				{field:'status',title:'状态',halign:"center", align:"center",width:150,
					//状态，1放款，2还款，3增加逾期费，4还逾期费
					formatter: function(value,row,index){
						if(value==1){
							return "放款";
						}else if(value == 2){
							return "还款";
						}else if(value == 3){
							return "增加逾期费";
						}else if(value == 4){
							return "还逾期费";
						}else{
							return "--";
						}
					}
				},
				{field:'billStatus',title:'账单状态',halign:"center", align:"center",width:200,
					//状态，1操作中，2成功，3失败
					formatter: function(value,row,index){
						if(value==1){
							return "操作中";
						}else if(value == 2){
							return "成功";
						}else if(value == 3){
							return "失败";
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
		        {field:'money',title:'金额',halign:"center", align:"center",width:200},
		        {field:'doneMoney',title:'剩余金额',halign:"center", align:"center",width:200},
				{field:'optTime',title:'操作时间',halign:"center", align:"center",width:200},
				{field:'remark',title:'操作说明',halign:"center", align:"center",width:600}				
		]]
	});
	$('#querybillLoanListForm').form({    
	    url:"billloan!queryByPar.do",    
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
	$('#billLoanList_table').datagrid('resize',{  
		height:$("#billLoanList_body").height()-$('#billLoanList_search_area').height()-5,
		width:$("#billLoanList_body").width()
	});
}
