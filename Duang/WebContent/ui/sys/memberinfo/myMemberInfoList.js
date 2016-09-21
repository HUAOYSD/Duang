var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#myMemberInfoList_open_close').on("click",function(){
		$('#myMemberInfoList_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//表格初始化
	tableObj = $("#myMemberInfoList_table").datagrid({
		height:$("#myMemberInfoList_body").height()-$('#myMemberInfoList_search_area').height()-5,
		width:$("#myMemberInfoList_body").width(),
		loadMsg : "正在加载，请稍后...",
		url:"memberinfo!queryMemberInfoByCustomer.do",  
		singleSelect:true,  
		nowrap:true,
		rownumbers:true,
		pagination:true,
		fitColumns:false,
		pageSize:50,
		pageList:[50,80,100,150,200],
		sortOrder:'desc',
		frozenColumns:[[  
						{field:'id',checkbox:true,halign:"center", align:"center"},
						{field:'loginName',title:'登录名',width:150,halign:"center", align:"center"},
						{field:'realName',title:'真实姓名',width:100,halign:"center", align:"center",editor:'center'},
						{field:'registerStyle',title:'注册方式',width:100,halign:"center", align:"center",
							formatter: function(value,row,index){
								if(value==1){
									return "线下";
								}else if(value==2){
									return "Android";
								}else if(value==3){
									return "IOS";
								}else if (value==4){
									return "平台系统";
								}else{
									return "--";
								}
							}
						}
		            ]],
		columns:[[
		          {title:'基本信息',colspan:16},
		          {title:'理财信息',colspan:7},
		          {title:'借贷信息',colspan:4}
		       ],[
		        {field:'investMember_id',title:'理财id',hidden:true}, 
		        {field:'loanMember_id',title:'借贷id',hidden:true},  
		        
				{field:'nickname',title:'昵称',width:100,halign:"center", align:"center"},
				{field:'isFreeze',title:'状态',width:100,halign:"center", align:"center",
					formatter:function(value,row,index){
						if(value==1){
							return "已冻结";
						}else{
							return "已解冻";
						}
					}
				},
				{field:'idCard',title:'身份证号',width:200,halign:"center", align:"center"},
				{field:'phone',title:'手机号',width:150,halign:"center", align:"center" },
				{field:'email',title:'邮箱',width:150,halign:"center", align:"center" },
				{field:'sex',title:'性别',width:100,halign:"center", align:"center",
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
				{field:'age',title:'年龄',width:100,halign:"center", align:"center" },
				{field:'isEliteAccount',title:'是否为金账户',width:100,halign:"center", align:"center",
					formatter: function(value,row,index){
						if(value==1){
							return "金账户";
						}else{
							return "非金账户";
						}
					}
				},
				{field:'userImg',title:'头像',width:200,halign:"center", align:"center" },
				{field:'idCardImg1',title:'身份证前照',width:200,halign:"center", align:"center"},
				{field:'idCardImg2',title:'身份证后照',width:200,halign:"center", align:"center" },
				{field:'price',title:'财力值',width:100,halign:"center", align:"center" },
				{field:'level',title:'等级',width:100,halign:"center", align:"center" },
				{field:'createTime',title:'开户日期',width:200,halign:"center", align:"center",
					formatter: function(value,row,index){
					   if(value==0){
						   return "-&nbsp;&nbsp;-";
					   }else{ 
						   return new Date(value).format("yyyy-MM-dd hh:mm:ss");
					   }
					}
				},
				{field:'miDescribe',title:'描述',width:200,halign:"center", align:"center" },
				
				//理财信息
				{field:'balance',title:'余额',width:100,halign:"center", align:"center" },
				{field:'investing',title:'投资中金额',width:100,halign:"center", align:"center"},
				{field:'totalIncome',title:'总收益',width:100,halign:"center", align:"center" },
				{field:'totalMoney',title:'总资产',width:100,halign:"center", align:"center" },
				{field:'currentIncome',title:'当期收益',width:100,halign:"center", align:"center" },
				{field:'useableScore',title:'可用积分',width:100,halign:"center", align:"center" },
				{field:'isContract',title:'契约用户',width:100,halign:"center", align:"center",
					formatter: function(value,row,index){
						if(value==1){
							return "是";
						}else{
							return "否";
						}
					}
				},
				//借贷用户信息
				{field:'lendMoney',title:'总借款',width:100,halign:"center", align:"center" },
				{field:'backMoney',title:'总还款',width:100,halign:"center", align:"center"},
				{field:'residueMoney',title:'剩余应还',width:100,halign:"center", align:"center" },
				{field:'expectMoney',title:'总逾期',width:100,halign:"center", align:"center" }
		]],
		onClickCell: function(index,field,value){
			tableObj.datagrid("selectRow",index);
			var selectRowt = tableObj.datagrid("getSelected");
			if(field=='idCardImg1'){
				if(value==null){
					layer.msg("还没有上传图片",{time:2000});
					return;
				}
				indexLayer = layer.open({
					type: 2,
					title: '身份证前照',
					shadeClose: true,
					shade: 0.8,
					area: ['80%', '80%'],
					maxmin:true,
					content: "memberinfo!showMemberInfoImage.do?id="+selectRowt.id+"&type=1" //1代表上传身份证前照
				});  
			}else if(field=='idCardImg2'){
				if(value==null){
					layer.msg("还没有上传图片",{time:2000});
					return;
				}
				indexLayer = layer.open({
					type: 2,
					title: '身份证后照',
					shadeClose: true,
					shade: 0.8,
					area: ['80%', '80%'],
					maxmin:true,
					content: "memberinfo!showMemberInfoImage.do?id="+selectRowt.id+"&type=2" //1代表上传身份证前照
				});  
			}
		},
		onLoadSuccess:function(data){
			if(data.result == false){
				layer.msg(data.msg,{time:2000});
			}
		},
		onLoadError:function(){
			layer.alert('发生未知错误，请联系管理员', {icon: 2});
		}

	});
	$('#querymyMemberInfoListForm').form({    
	    url:"memberinfo!queryMemberInfoByCustomer.do",    
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

var selectedRow = null;
function isSelectedRow(){
	selectedRow = tableObj.datagrid("getSelected");
	if(selectedRow==null){
		//layer.msg("请选择用户！",{time:1000});
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
	$('#myMemberInfoList_table').datagrid('resize',{  
		height:$("#myMemberInfoList_body").height()-$('#myMemberInfoList_search_area').height()-5,
		width:$("#myMemberInfoList_body").width()
	});
}

function reloadDataGrid(){
    $("#invest_member_table").datagrid('reload');  
}

/**
 * 弹窗提示
 * @param msg 信息
 * @param time 时间
 */
function layerMsg(msg,time){
	 layer.msg(msg, {time: time});
}
