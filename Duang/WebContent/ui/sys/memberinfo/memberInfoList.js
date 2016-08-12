var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#memberInfoList_open_close').on("click",function(){
		$('#memberInfoList_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//初始化toolbar
	hideButton();
	//表格初始化
	tableObj = $("#memberInfoList_table").datagrid({
		height:$("#memberInfoList_body").height()-$('#memberInfoList_search_area').height()-5,
		width:$("#memberInfoList_body").width(),
		loadMsg : "正在加载，请稍后...",
		url:"memberinfo!queryAllMember.do",  
		singleSelect:true,  
		nowrap:true,
		rownumbers:true,
		pagination:true,
		fitColumns:false,
		pageSize:50,
		pageList:[50,80,100,150,200],
		sortOrder:'desc',
		toolbar:'#memberInfoList_table_toolbar',
		frozenColumns:[[  
						{field:'id',checkbox:true,halign:"center", align:"center"},
						{field:'loginName',title:'登录名',width:150,halign:"center", align:"center"},
						{field:'realName',title:'真实姓名',width:100,halign:"center", align:"center",editor:'center'},
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
				{field:'type',title:'用户类型',width:100,halign:"center", align:"center",
					formatter: function(value,row,index){
						if(value==0){
							return "个体用户";
						}else if(value==1){
							return "企业用户";
						}
					}
				},
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
				{field:'registerStyle',title:'注册方式',width:100,halign:"center", align:"center",
					formatter: function(value,row,index){
						if(value==1){
							return "线下";
						}else if(value==2){
							return "Android";
						}else if(value==3){
							return "IOS";
						}else{
							return "平台系统";
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
		onSelect:function(rowIndex, rowData){
			showButton();
		},
		onUnselect:function(rowIndex, rowData){
			hideButton();
		},
		onLoadSuccess: function(data){
			hideButton();
		}

	});
	$('#queryMemberInfoListForm').form({    
	    url:"memberinfo!queryMemberInfoByParameter.do",    
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

/**
 * 设置按钮不可用状态
 */
function hideButton(){
	//初始化状态，更新和删除按钮时不能点击的
	$('#memberInfoList-sereach-investMember-btn').linkbutton('disable');
	$('#memberInfoList-sereach-loanMember-btn').linkbutton('disable');
	$('#memberInfoList-upload-idcard1-btn').linkbutton('disable');
	$('#memberInfoList-upload-idcard2-btn').linkbutton('disable');
	$('#memberInfoList-unselected-btn').linkbutton('disable');
	
}
/**
 * 设置按钮可用状态
 */
function showButton(){
	//初始化状态，更新和删除按钮时不能点击的
	$('#memberInfoList-sereach-investMember-btn').linkbutton('enable');
	$('#memberInfoList-sereach-loanMember-btn').linkbutton('enable');
	$('#memberInfoList-upload-idcard1-btn').linkbutton('enable');
	$('#memberInfoList-upload-idcard2-btn').linkbutton('enable');
	$('#memberInfoList-unselected-btn').linkbutton('enable');
}

//上传身份证前照
$("#memberInfoList-upload-idcard1-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
	indexLayer = layer.open({
		type: 2,
		title: '上传身份证前照',
		shadeClose: true,
		shade: 0.8,
		area: ['80%', '80%'],
		maxmin:true,
		content: "memberinfo!gotoUploadImg.do?id="+selectedRow.id+"&type=1" //1代表上传身份证前照
	});
});
//上传身份证后照
$("#memberInfoList-upload-idcard2-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
	indexLayer = layer.open({
		type: 2,
		title: '上传身份证后照',
		shadeClose: true,
		shade: 0.8,
		maxmin:true,
		area: ['80%', '80%'],
		content: "memberinfo!gotoUploadImg.do?id="+selectedRow.id+"&type=2" //2代表上传身份证前照
	});
});

//取消选择
$("#memberInfoList-unselected-btn").on('click',function(){
	tableObj.datagrid("unselectAll");
	hideButton();
});

//查询理财用户
$("#memberInfoList-sereach-investMember-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
	//ajax 判断是否为理财用户
	 $.ajax({
         type: "POST",
         url: "investmember!findInvestMemberInfoById.do?id="+selectedRow.investMember_id ,
         success: function(data){
           data = JSON.parse(data);
           if(data.result==true){
        	   indexLayer = layer.open({
        			type: 2,
        			title: '理财信息',
        			shadeClose: true,
        			shade: 0.8,
        			maxmin:true,
        			area: ['350px', '60%'],
        			content: "investmember!showInvestMemberInfoById.do?id="+selectedRow.investMember_id 
        		});
           }else{
            layer.msg(data.msg, {time: 2000});
           }
         }
	 });
});

//查询借贷用户
$("#memberInfoList-sereach-loanMember-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
	//判断是否为借贷用户
	$.ajax({
        type: "POST",
        url: "loanmember!findLoanMemberInfoById.do?id="+selectedRow.loanMember_id,
        success: function(data){
          data = JSON.parse(data);
          if(data.result==true){
        	  indexLayer = layer.open({
        			type: 2,
        			title: '理财信息',
        			shadeClose: true,
        			shade: 0.8,
        			maxmin:true,
        			area: ['350px', '50%'],
        			content: "loanmember!showLoanMemberInfoById.do?id="+selectedRow.loanMember_id 
        		});
          }else{
           layer.msg(data.msg, {time: 2000});
          }
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
	$('#memberInfoList_table').datagrid('resize',{  
		height:$("#memberInfoList_body").height()-$('#memberInfoList_search_area').height()-5,
		width:$("#memberInfoList_body").width()
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
