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
		url:"investmember!queryAllInvestMember.do",  
		singleSelect:true,  
		nowrap:true,
		rownumbers:true,
		pagination:true,
		fitColumns:false,
		pageSize:50,
		pageList:[50,80,100,150,200],
		sortOrder:'desc',
		toolbar:'#invest_memeber_table_toolbar',
		columns:[[
		    {field:'id',checkbox:true,halign:"center", align:"center"},
			{field:'name',title:'登录名',width:150,halign:"center", align:"center"},
			{field:'real_name',title:'真实姓名',width:100,halign:"center", align:"center",editor:'center'},
			{field:'nickname',title:'昵称',width:100,halign:"center", align:"center" },
			{field:'invest_money',title:'投资金额',width:100,halign:"center", align:"center" },
			{field:'investing_money',title:'投资中金额',width:100,halign:"center", align:"center"},
			{field:'useable_money',title:'可用余额',width:100,halign:"center", align:"center" },
			{field:'account_total_money',title:'账面总金额',width:100,halign:"center", align:"center" },
			{field:'freeze_money',title:'冻结余额',width:150,halign:"center", align:"center" },
			{field:'unfreeze_money',title:'未冻结余额',width:100,halign:"center", align:"center" },
			{field:'useable_score',title:'可用积分',width:100,halign:"center", align:"center" },
			{field:'is_elite_account',title:'是否为金账户',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					if(value==1){
						return "金账户";
					}else{
						return "非金账户";
					}
				}
			},
			{field:'is_contract',title:'契约用户',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					if(value==1){
						return "是";
					}else{
						return "否";
					}
					
				}
			},
			{field:'createTime',title:'开户日期',width:200,halign:"center", align:"center",
				formatter: function(value,row,index){
				   if(value==0){
					   return "-&nbsp;&nbsp;-";
				   }else{ 
					   return new Date(value).format("yyyy-MM-dd hh:mm:ss");
				   }
				}
			},
			{field:'is_new_product',title:'允许上线',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					if(value==1){
						return "允许";
					}else{
						return "不允许";
					}
				}
			},
			{field:'idCard',title:'身份证号',width:200,halign:"center", align:"center"},
			{field:'phone',title:'手机号',width:150,halign:"center", align:"center" },
			{field:'bank_card',title:'银行卡号',width:200,halign:"center", align:"center" },
			{field:'bank',title:'所属银行',width:100,halign:"center", align:"center" },
			{field:'sex',title:'性别',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					if(value==1){
						return "男";
					}else{
						return "女";
					}
				}
			},
			{field:'age',title:'年龄',width:100,halign:"center", align:"center" },
			{field:'user_image',title:'头像',width:200,halign:"center", align:"center" },
			{field:'idcard_img1',title:'身份证前照',width:200,halign:"center", align:"center" },
			{field:'idcard_img2',title:'身份证后照',width:200,halign:"center", align:"center" },
			{field:'cust_manager_id',title:'客户经理ID',width:100,halign:"center", align:"center" },
			{field:'manager_name',title:'客户经理姓名',width:100,halign:"center", align:"center" },
			{field:'email',title:'邮箱',width:150,halign:"center", align:"center" },
			{field:'memberInfo_id',title:'用户id',hidden:true}
		]]
	});
	$('#queryInvestMemberListForm').form({    
	    url:"investmember!queryInvestMemberByParameter.do",    
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

var selectedRow = null;
//冻结操作
$("#investMemeber-freeze-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
	$.ajax({
	   type: "POST",
	   url: "memberinfo!freezeMemberInfo.do",
	   data: "is_freeze=1&memberInfo_id="+selectedRow.memberInfo_id,
	   success: function(data){
		   data = JSON.parse(data);
		   console.info(data);
	   }
	});
});

//解冻操作
$("#investMemeber-unfreeze-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
	$.ajax({
	   type: "POST",
	   url: "memberinfo!freezeMemberInfo.do",
	   data: "is_freeze=0&memberInfo_id="+selectedRow.memberInfo_id,
	   success: function(data){
		   data = JSON.parse(data);
		   console.info(data);
	   }
	});
});

function isSelectedRow(){
	selectedRow = tableObj.datagrid("getSelected");
	if(selectedRow==null){
		layer.msg("请选择一个产品！",{time:1000});
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
	$('#invest_memeber_table').datagrid('resize',{  
		height:$("#invest_member_body").height()-$('#invest_member_search_area').height()-5,
		width:$("#invest_member_body").width()
	});
}
	