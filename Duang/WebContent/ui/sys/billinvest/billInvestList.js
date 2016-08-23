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
		fitColumns:true,
		pageSize:50,
		pageList:[50,80,100,150,200],
		sortOrder:'desc',
		toolbar:'',
		columns:[[
				{field:'id',hidden:true},
				{field:'investListId',hidden:true},
				{field:'bindCardId',hidden:true},
				{field:'investMemberId',hidden:true},
				
				{field:'name',title:'名称',halign:"center", align:"center",width:200},
				{field:'useTime',title:'使用时间',halign:"center", align:"center",width:200 },
		        {field:'remark',title:'说明',halign:"center", align:"center",width:300},  
		        {field:'money',title:'价值',halign:"center", align:"center",width:100}, 
				{field:'beginTime',title:'有效期始',halign:"center", align:"center",width:200},
				{field:'endTime',title:'有效期至',halign:"center", align:"center",width:200},
				{field:'createTime',title:'派发时间',halign:"center", align:"center",width:200},
				{field:'minMoney',title:'最小投资金额权限',halign:"center", align:"center",width:150},
				{field:'describe',title:'详细描述',halign:"center", align:"center",width:600 }
				map.put("id", billInvest.getId());
				map.put("useType", billInvest.getUseType());
				map.put("money", billInvest.getMoney());
				map.put("balance", billInvest.getBalance());
				map.put("asset", billInvest.getAsset());
				map.put("status", billInvest.getStatus());
				map.put("remark", billInvest.getRemark());
				map.put("style", billInvest.getStyle());
				map.put("optTime", DateUtils.date2Str(billInvest.getOptTime()));
				InvestList pk = billInvest.getInvestList();
				map.put("investListId", pk.getId());
				map.put("money", pk.getMoney());
				map.put("yetMoney", pk.getYetMoney());
				map.put("spaceMoney", pk.getSpaceMoney());
				map.put("backIncome", pk.getBackIncome());
				map.put("backMoney", pk.getBackMoney());
				map.put("useTicket", UseTicket.valueOf("UT"+pk.getUseTicket()).toString());
				map.put("expectIncome", pk.getExpectIncome());
				map.put("totalMoney", pk.getTotalMoney());
				map.put("income", pk.getIncome());
				map.put("ticketBonus", pk.getTicketBonus());
				map.put("status", Status.valueOf("S"+pk.getStatus()).toString());
				map.put("openDate", DateUtils.getTimeStamp(pk.getOpenDate()));
				map.put("backDate", DateUtils.getTimeStamp(pk.getBackDate()));
				map.put("calcBeginDate", DateUtils.getTimeStamp(pk.getBackDate()));
				map.put("calcEndDate", DateUtils.getTimeStamp(pk.getBackDate()));
				map.put("pactNumber", pk.getPactNumber());
				map.put("investStyle", Platform.valueOf("P"+pk.getInvestStyle()).toString());
				map.put("poundageTurn", pk.getPoundageTurn());
				map.put("poundagePrivilege", pk.getPoundagePrivilege());
				map.put("isTurn", If.valueOf("If"+pk.getIsTurn()).toString());
				map.put("turnStatus", TurnStatus.valueOf("TS"+pk.getTurnStatus()).toString());
				
				InvestMember im = billInvest.getInvestMember();
				map.put("investMemberId", im.getId());
				map.put("isContract", im.getIsContract());
				map.put("balance", im.getBalance());
				map.put("investing", im.getInvesting());
				map.put("totalIncome", im.getInvesting());
				map.put("totalMoney", im.getTotalMoney());
				map.put("useableScore", im.getUseableScore());
				map.put("registerStyle", im.getRegisterStyle());
				
				BindCard bindCard = billInvest.getBindCard();
				map.put("bindCardId", bindCard.getId());
				map.put("name", bindCard.getName());
				map.put("idcard", bindCard.getIdcard());
				map.put("phone", bindCard.getPhone());
				map.put("bankNo", bindCard.getBankNo());
				map.put("openBank", bindCard.getOpenBank());
				map.put("type", bindCard.getType());
				map.put("photoImg1", bindCard.getPhotoImg1());
				map.put("photoImg2", bindCard.getPhotoImg2());
				map.put("optTime", DateUtils.getTimeStamp(bindCard.getOptTime()));
		]]
	});
	$('#querybillInvestListForm').form({    
	    url:"memberticketrecord!queryMemberTicketRecordByPro.do",    
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
