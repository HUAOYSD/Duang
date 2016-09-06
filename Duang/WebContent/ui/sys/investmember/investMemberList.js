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
		frozenColumns:[[  
						{field:'id',checkbox:true,halign:"center", align:"center"},
						{field:'loginName',title:'登录名',width:150,halign:"center", align:"center"},
						{field:'realName',title:'真实姓名',width:100,halign:"center", align:"center",editor:'center'},
						{field:'registerStyle',title:'注册方式',width:100,halign:"center", align:"center",
							formatter: function(value,row,index){
								//1线下，2Android，3IOS，4平台系统
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
						}
		            ]],
		columns:[[
			{field:'nickname',title:'昵称',width:100,halign:"center", align:"center" },
			{field:'isFreeze',title:'状态',width:100,halign:"center", align:"center",
				formatter:function(value,row,index){
					if(value==1){
						return "已冻结";
					}else{
						return "已解冻";
					}
				}
			},
			{field:'price',title:'财力值',width:100,halign:"center", align:"center" },
			{field:'level',title:'等级',width:100,halign:"center", align:"center" },
			{field:'balance',title:'余额',width:100,halign:"center", align:"center" },
			{field:'investing',title:'投资中金额',width:100,halign:"center", align:"center"},
			{field:'totalIncome',title:'总收益',width:100,halign:"center", align:"center" },
			{field:'totalMoney',title:'总资产',width:100,halign:"center", align:"center" },
			{field:'currentIncome',title:'当期收益',width:100,halign:"center", align:"center" },
			{field:'useableScore',title:'可用积分',width:100,halign:"center", align:"center" },
			{field:'isEliteAccount',title:'是否为金账户',width:100,halign:"center", align:"center",
				formatter: function(value,row,index){
					if(value==1){
						return "金账户";
					}else{
						return "非金账户";
					}
				}
			},
			{field:'isContract',title:'契约用户',width:100,halign:"center", align:"center",
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
			{field:'idCard',title:'身份证号',width:200,halign:"center", align:"center"},
			{field:'phone',title:'手机号',width:150,halign:"center", align:"center" },
			{field:'miDescribe',title:'描述',width:200,halign:"center", align:"center" },
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
			{field:'userImg',title:'头像',width:200,halign:"center", align:"center" },
			{field:'idCardImg1',title:'身份证前照',width:200,halign:"center", align:"center"},
			{field:'idCardImg2',title:'身份证后照',width:200,halign:"center", align:"center" },
			{field:'managerName',title:'客户经理姓名',width:100,halign:"center", align:"center" },
			{field:'email',title:'邮箱',width:150,halign:"center", align:"center" },
			{field:'memberInfoId',title:'用户id',hidden:true}
		]],
		onClickCell: function(index,field,value){
			tableObj.datagrid("selectRow",index);
			var selectRowt = tableObj.datagrid("getSelected");
			if(value==null){
				layer.msg("还没有上传图片",{time:2000});
				return;
			}
			if(field=='idCardImg1'){
				indexLayer = layer.open({
					type: 2,
					title: '身份证前照',
					shadeClose: true,
					shade: 0.8,
					area: ['80%', '80%'],
					maxmin:true,
					content: "investmember!showUserImage.do?id="+selectRowt.memberInfoId+"&type=1" //1代表上传身份证前照
				});  
			}else if(field=='idCardImg2'){
				indexLayer = layer.open({
					type: 2,
					title: '身份证后照',
					shadeClose: true,
					shade: 0.8,
					area: ['80%', '80%'],
					maxmin:true,
					content: "investmember!showUserImage.do?id="+selectRowt.memberInfoId+"&type=1" //1代表上传身份证前照
				});  
			}
		}

	});
	$('#queryInvestMemberListForm').form({    
	    url:"investmember!queryInvestMemberByParameter.do",    
	    onSubmit: function(){    
	        
	    },    
	    success:function(data){ 
	    	data = JSON.parse(data);
	    	if(data.result==false){
	    		layer.msg(data.msg,{time:2000});
	    	}
	    	$('#invest_memeber_table').datagrid('loadData', {
	    		"rows":data.rows,
	    		"total":data.total,
	    	}); 
	    }    
	});
});

function reloadDataGrid(){
    $("#invest_memeber_table").datagrid('reload');  
}

var selectedRow = null;
//冻结操作
$("#investMemeberList-freeze-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
	$.ajax({
	   type: "POST",
	   url: "memberinfo!freezeMemberInfo.do",
	   data: "isFreeze=1&id="+selectedRow.memberInfoId,
	   success: function(data){
		   data = JSON.parse(data);
		   layer.msg(data.msg,{time:1000});
		   var selectRowIndex = $('#invest_memeber_table').datagrid('getRowIndex',selectedRow);
		   $('#invest_memeber_table').datagrid('updateRow',{
				index: selectRowIndex,
				row: {
					isFreeze: 1 //冻结
				}
			});
	   }
	});
});

//解冻操作
$("#investMemeberList-unfreeze-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
	$.ajax({
		   type: "POST",
		   url: "memberinfo!freezeMemberInfo.do",
		   data: "isFreeze=0&id="+selectedRow.memberInfoId,
		   success: function(data){
			   data = JSON.parse(data);
			   layer.msg(data.msg,{time:1000});
			   var selectRowIndex = $('#invest_memeber_table').datagrid('getRowIndex',selectedRow);
			   $('#invest_memeber_table').datagrid('updateRow',{
					index: selectRowIndex,
					row: {
						isFreeze: 0 //解冻
					}
				});
		   }
		});
});

//添加操作
$("#investMemeberList-add-btn").on('click',function(){
	indexLayer = layer.open({
		type: 2,
		title: '添加理财用户',
		shadeClose: true,
		shade: 0.8,
		area: ['450px', '97%'],
		content: 'investmember!addInvestMember.do'
	});  
});
//修改
$("#investMemeberList-update-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
	indexLayer = layer.open({
		type: 2,
		title: '修改产品',
		shadeClose: true,
		shade: 0.8,
		area: ['450px', '97%'],
		content: "investmember!eidtInvestMember.do?id="+selectedRow.id
	}); 
});

//删除
$("#investMemeberList-delete-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
    layer.confirm('您确定要删除 '+selectedRow.realName+' 吗？', {
    	  title :'警告',
		  icon: 7,
		  btn: ['确定','取消'] //按钮
		}, function(){ //确定
			$.messager.progress('close');	// 如果提交成功则隐藏进度条
			$.ajax({
				   type: "POST",
				   url: "memberinfo!deleteMemberInfo.do",
				   data: "id="+selectedRow.memberInfoId,
				   success: function(data){
					 data = JSON.parse(data);
				     if(data.result==true){
				    	 var selectedRowIndex = $("#invest_memeber_table").datagrid('getRowIndex',selectedRow);
				    	 $("#invest_memeber_table").datagrid('deleteRow',selectedRowIndex);
				    	 layer.closeAll();
				     }
				    layer.msg(data.msg, {time: 1000});
				   }
				});
		}, function(){//取消
		  return;
	});
});

//上传身份证前照
$("#investMemeberList-upload-idcard1-btn").on('click',function(){
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
		content: "investmember!touUpload.do?id="+selectedRow.memberInfoId+"&type=1" //1代表上传身份证前照
	});
});
//上传身份证前照
$("#investMemeberList-upload-idcard2-btn").on('click',function(){
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
		content: "investmember!touUpload.do?id="+selectedRow.memberInfoId+"&type=2" //2代表上传身份证前照
	});
});

//取消选择
$("#investMemeberList-unselected-btn").on('click',function(){
	tableObj.datagrid("unselectAll");
});

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
	$('#invest_memeber_table').datagrid('resize',{  
		height:$("#invest_member_body").height()-$('#invest_member_search_area').height()-5,
		width:$("#invest_member_body").width()
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
	