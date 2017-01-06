var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#loan_member_repay_open_close').on("click",function(){
		$('#loan_member_repay_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});	
	//表格初始化
	tableObj = $("#loan_member_repay_table").datagrid({
		height:$("#loan_member_repay_body").height()-$('#loan_member_repay_search_area').height()-5,
		width:$("#loan_member_repay_body").width(),
		idField:'phone',
		loadMsg : "正在加载，请稍后...",
		url:"repay_date!queryLoanMemberRepay.do",  
		singleSelect:true,  
		nowrap:true,
		rownumbers:true,
		pagination:false,
		fitColumns:true,
		toolbar:'#loan_member_repay_table_toolbar',
		frozenColumns:[[  
						{field:'loginName',title:'登录名',width:150,halign:"center", align:"center"},
						{field:'realName',title:'姓名',width:100,halign:"center", align:"center",editor:'center'},
						{field:'phone',title:'电话',width:100,halign:"center", align:"center",editor:'center'},
		            ]],
		columns:[[
			{field:'repaySum',title:'应该还款金额',width:100,halign:"center", align:"center" },
			{field:'repayRealSum',title:'实际还款金额',width:100,halign:"center", align:"center",
				formatter:function(value,row,index){
					return value+"元";
				}
			},
			{field:'repayDate',title:'应该还款日期',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					   if(value==0){
						   return "-&nbsp;&nbsp;-";
					   }else{ 
						   return new Date(value).format("yyyy-MM-dd");
					   }
					}
			},
			{field:'reRepayDate',title:'实际还款日期',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					   if(value==0){
						   return "-&nbsp;&nbsp;-";
					   }else{ 
						   return new Date(value).format("yyyy-MM-dd");
					   }
				}
			},
			{field:'reapyIndex',title:'期数',width:100,halign:"center", align:"center",
				formatter:function(value,row,index){
					return value+"期";
				}
			},
			{field:'state',title:'状态',width:100,halign:"center", align:"center",
				formatter:function(value,row,index){
					if(value==0){
						return "未还款";
					}else{
						return "已还款";
					}
				},
				styler: function(value,row,index){
					if(value==0){
						return 'background-color:#fe441a;color:#fff;';
					}else{
						return 'background-color:#028252;color:#fff;';
					}
				}

			},
			{field:'isOver',title:'是否逾期',width:100,halign:"center", align:"center",
				formatter:function(value,row,index){
					if(value==0){
						return "否";
					}else{
						return "是";
					}
				}
			},
			{field:'overDays',title:'超期天数',width:100,halign:"center", align:"center",
				formatter:function(value,row,index){
					return value+"天";
				}
			},
			{field:'overSum',title:'逾期费',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					return value+"元";
				}
			}
		]]

	});
	$('#queryloanMemberListRepayForm').form({    
	    url:"repay_date!queryLoanMemberRepay.do",    
	    onSubmit: function(){    
	        
	    },    
	    success:function(data){ 
	    	data = JSON.parse(data);
	    	if(data.result==false){
	    		layer.msg(data.msg,{time:2000});
	    	}
	    	$('#loan_member_repay_table').datagrid('loadData', {
	    		"rows":data.rows,
	    		"total":data.total,
	    	}); 
	    }    
	});
});

function reloadDataGrid(){
    $("#loan_member_repay_table").datagrid('reload');  
}

var selectedRow = null;

function isSelectedRow(){
	selectedRow = tableObj.datagrid("getSelected");
	if(selectedRow==null){
		layer.msg("请选择用户！",{time:1000});
		return false;
	}
	return true;
}

//监听窗口大小变化
window.onresize = function(){
	setTimeout(domresize,300);
};
//改变表格宽高
function domresize(){
	$('#loan_member_repay_table').datagrid('resize',{  
		height:$("#loan_member_repay_body").height()-$('#loan_member_repay_search_area').height()-5,
		width:$("#loan_member_repay_body").width()
	});
}

/**
 * 弹窗提示
 * @param msg 信息
 * @param time 时间
 */
function layerMsg(msg,time){
	 layer.msg(msg, {time: time});
}
	