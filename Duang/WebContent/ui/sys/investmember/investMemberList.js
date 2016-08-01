var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#invest_member_open_close').on("click",function(){
		$('#invest_memeber_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});	
	//表格初始化
	tableObj = $("#invest_memeber_table").datagrid({
		height:$("#invest_member_body").height()-$('#invest_member_search_area').height()-5,
		width:$("#invest_member_body").width(),
		idField:'id',
		loadMsg : "正在加载，请稍后...",
		url:"investmember!queryInvestMember.do",  
		singleSelect:true,  
		nowrap:true,
		rownumbers:true,
		pagination:true,
		fitColumns:false,
		pageSize:50,
		pageList:[50,80,100,150,200],
		sortOrder:'desc',
		columns:[[
			{field:'nameZh',title:'登录名',width:100,halign:"center", align:"center"},
			{field:'name',title:'真实姓名',width:100,halign:"center", align:"center",editor:'center'},
			{field:'name_describe',title:'昵称',width:100,halign:"center", align:"center" },
			{field:'yield_describe',title:'投资金额',width:100,halign:"center", align:"center" },
			{field:'yield',title:'投资中金额',width:100,halign:"center", align:"center"},
			{field:'charge_ratio',title:'可用余额',width:100,halign:"center", align:"center" },
			{field:'title1',title:'账面总金额',width:100,halign:"center", align:"center" },
			{field:'title2',title:'冻结余额',width:150,halign:"center", align:"center" },
			{field:'min_deadline',title:'未冻结余额',width:100,halign:"center", align:"center" },
			{field:'min_money',title:'可用积分',width:100,halign:"center", align:"center" },
			{field:'refund_type',title:'是否为金账户',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					if(value==1){
						return "是";
					}else{
						return "否";
					}
				}
			},
			{field:'is_sell',title:'活动时间',width:100,halign:"center", align:"center"},
			{field:'is_lottery',title:'契约用户',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					if(value==1){
						return "允许";
					}else{
						return "不允许";
					}
				}
			},
			{field:'is_red_envel',title:'开户日期',width:100,halign:"center", align:"center"},
			{field:'is_new_product',title:'允许上线',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					if(value==1){
						return "是";
					}else{
						return "否";
					}
				}
			},
			{field:'is_recommend',title:'身份证号',width:100,halign:"center", align:"center"},
			{field:'product_describe',title:'手机号',width:100,halign:"center", align:"center" },
			{field:'risk_control',title:'银行卡号',width:100,halign:"center", align:"center" },
			{field:'details',title:'所属银行',width:100,halign:"center", align:"center" },
			{field:'risk_control',title:'银行卡号',width:100,halign:"center", align:"center" },
			{field:'risk_control',title:'性别',width:100,halign:"center", align:"center" },
			{field:'risk_control',title:'年龄',width:100,halign:"center", align:"center" },
			{field:'risk_control',title:'头像',width:200,halign:"center", align:"center" },
			{field:'risk_control',title:'身份证前照',width:200,halign:"center", align:"center" },
			{field:'risk_control',title:'身份证后照',width:200,halign:"center", align:"center" },
			{field:'risk_control',title:'客户经理ID',width:100,halign:"center", align:"center" },
			{field:'risk_control',title:'客户经理姓名',width:100,halign:"center", align:"center" },
			{field:'risk_control',title:'邮箱',width:100,halign:"center", align:"center" }
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
	$('#invest_memeber_table').datagrid('resize',{  
		height:$("#invest_member_body").height()-$('#invest_member_search_area').height()-5,
		width:$("#invest_member_body").width()
	});
}
	